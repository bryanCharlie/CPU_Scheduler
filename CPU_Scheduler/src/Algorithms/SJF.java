package Algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SJF {
	private PriorityQueue<PCB> ready = new PriorityQueue<PCB>();
	private PriorityQueue<PCB> waiting = new PriorityQueue<PCB>();

	private Queue<PCB> processWaiting;

	private ArrayList<PCB> jobList;

	private PCB currentPCB;
	private boolean processing = true;
	private int currentBurstTime;
	private int time = 0;
	private int jobsCompleted = 0;
	private double totalProTime;
	private double totalWaitTime;
	private double totalTurnTime;

	public SJF(ArrayList<PCB> jobs) {
		jobList = jobs;
		for(int i=0; i != jobList.size(); i++){
			PCB temp = jobList.get(i);
			temp.isReady();
			jobList.set(i, temp);
		}

		//	System.out.println(i.getID() + " " + i.getCurrentBurst());


		fillReadyQueue();
		process();

	}

	private void process() {

		while(processing){

			currentPCB = ready.remove();

			if(!currentPCB.isFinished()){

				currentBurstTime = currentPCB.getCurrentBurst();
				currentPCB.removeCurrentBurst();
				//System.out.println(currentPCB.getID());

				currentPCB.isRunning();
				
				addToTime(currentBurstTime);
				
				currentPCB.isWaiting();
				addToWaiting(currentPCB);
		}

				else 
					System.exit(1);
					
				fillReadyQueue();
				processWaiting();

				if(processingIsFinished())
					processing = false;

			}
		
			System.out.println("Algorithm:SJF. current CPU clock value:"+time+". average processing time:"
					+avgProcessingTime()+". average waiting time:"+avgWaitingTime()+". average turnaround time:"+avgTurnTime());
		}

		private void fillReadyQueue() {
			while(ready.size() + waiting.size() != 10 && jobList.size() != 0){
				if(ready.size() == 0  && waiting.size() == 0 && time < jobList.get(0).getArrivalTime()){
					time = jobList.get(0).getArrivalTime();
					ready.add(jobList.remove(0));
				}
				else if(time < jobList.get(0).getArrivalTime() && ready.size() + waiting.size() >= 1){
					return;
				}
				else
					ready.add(jobList.remove(0));
			}

		}

		private boolean processingIsFinished(){
			if((ready.isEmpty() && waiting.isEmpty()))
				return true;
			return false;
		}

		private void processWaiting(){
			if(!waiting.isEmpty() && ready.isEmpty()){
				PCB p = waiting.remove();
				addToTime(p.getIoTime());
				ready.add(p);
			}
		}
		
		public int waitingTime(){

			return(time - currentPCB.bursttotal()-currentPCB.getArrivalTime());

		}	
		public double avgProcessingTime(){
			return totalProTime/jobsCompleted;
		}
		public double avgWaitingTime(){
			
			return totalWaitTime/jobsCompleted;
		}
		
		public double avgTurnTime(){
			return totalTurnTime/jobsCompleted;
		}
		private void addToWaiting(PCB p){
			if(p.isFinished()){
				jobsCompleted++;
				System.out.println(p.toString() + "at time " + time + ". The job took " +
						currentPCB.totalProcessingTime() + " processing time"+". total waiting time:"+waitingTime()+". turnaround time:"+(time-currentPCB.getArrivalTime()));
				totalProTime += currentPCB.totalProcessingTime();
				totalWaitTime += (time - currentPCB.bursttotal()- currentPCB.getArrivalTime());		
				totalTurnTime+=(time-currentPCB.getArrivalTime());
				return;
			}
			else{
				waiting.add(p);
			}
		}
		
		private void addToTime(int i){
			
			int tempTime = time;
			int size = waiting.size();
			
			while(time != tempTime + i){
				time++;
				currentPCB.incProgramCounter();
				if(time%200==0){
					System.out.println("time is now:"+time);
					System.out.println("Number of jobs completed is: " + jobsCompleted);
					System.out.println("Number of jobs in the ready queue:"+ready.size());
					System.out.println("Number of jobs in the blocked queue:"+waiting.size());
				}
				
				int j = 0;
				while(j != size && !waiting.isEmpty()){
					j++;
					PCB p = waiting.remove();

					if(p.ioIsFinished()){
						ready.add(p);
						size--;
					}
					else
						waiting.add(p);
				}

		}	
	}
}