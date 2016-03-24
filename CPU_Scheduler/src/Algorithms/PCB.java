package Algorithms;
import java.awt.List;
import java.util.ArrayList;

public class PCB implements Comparable<PCB>{
	private String ID;
	private int arrivalTime;
	private int noOfBursts;
	private ArrayList<Integer> bursts = new ArrayList<Integer>();
	private int currentBurst = 0;
	private int nextBurst = 0;
	private int ioTime = 10;
	private boolean running = false;
	private boolean waiting = false;
	private boolean ready= false;
	private int burstTotal = 0;
	private int programCounter=0;
	private static int PC = 0;


	public PCB(String ID, String ArrivalTime, String noOfBursts , ArrayList<Integer> bursts){
		if(bursts == null)
			System.out.println("error");
		
		this.ID = ID;
		this.arrivalTime = Integer.parseInt(ArrivalTime);
		this.noOfBursts = Integer.parseInt(noOfBursts);
		this.bursts = bursts;
		for (int i: bursts)
			burstTotal += i;
		
		//System.out.println("entered pcb" + ID + " " + noOfBursts);
	}
	
	public int getCurrentBurst(){
		if(!bursts.isEmpty())
			return currentBurst = bursts.get(0);
		else
			return currentBurst;
			
	}
	
	public int getNumberofBursts(){
		return noOfBursts;
	}

	public void isRunning(){
		running = true;
		waiting = false;
		ready = false;
	}

	public void isWaiting(){
		waiting = true;
		running = false;
		ready = false;
	}

	public void isReady(){
		ready = true;
		running = false;
		waiting = false;
	}

	int GetPC(){
		return PC;
	}

	public void incPC(){
		PC = PC+1;
	}
	public boolean isFinished(){
		return(bursts.isEmpty());
	}
	public String toString(){
		String s = String.format("Job id %s arrived at time %s with number of "
				+ "bursts %d and has completed ", ID, arrivalTime, noOfBursts);
		return s;
	}
	public String getID(){
		return ID;
	}
	
	public void removeCurrentBurst(){
		bursts.remove(0);
	}
	
	public int getArrivalTime(){
		return arrivalTime;
	}

	@Override
	public int compareTo(PCB o) {
		if(this.getCurrentBurst() > o.getCurrentBurst())
			return 1;
		else if((this.getCurrentBurst() < o.getCurrentBurst()))
			return -1;
		else
			return 0;
	}
	
	public int totalProcessingTime(){
		return ((noOfBursts - 1) * 10) + burstTotal;
	}
	 public int bursttotal(){
		return burstTotal;
	}
	
	public void putInt(int i){
		bursts.set(0, i);
	}
	
	public boolean ioIsFinished(){
		if(ioTime == 0){
			ioTime = 10;
			return true;
		}
		else 
			ioTime--;
		
		System.out.println("currently in ioisfinished method, in id: "+ this.ID +"iotime is: "+ ioTime);
		
		return false;
	}
	
	public int getIoTime(){
		System.out .println("currently in getiotime method, in id: "+ this.ID +"iotime is: "+ ioTime);
		return ioTime;
	}
	public void incProgramCounter(){
		programCounter++;
	}

}
