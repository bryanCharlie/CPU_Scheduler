package Algorithms;

import java.util.ArrayList;

public class ProcessScheduler {
	public static void main(String[] args){
		int quantumTime=0;
		String algorithm;
		ArrayList<PCB> Jobs = new ArrayList<PCB>();
		FileHandler fh;
		
		if(args.length == 3)
			fh = new FileHandler(args[2]);
		else
			fh = new FileHandler(args[1]);


		Jobs = fh.getJobs();

		if(args[0].equals("SJF")){
				SJF sjfOut = new SJF(Jobs);	
		}//if
		else if(args[0].equals("FCFS")){
			FCFS out = new FCFS(Jobs);
		}//else
		else if(args[0].equals("RR") && Integer.parseInt(args[1]) > 0){
			RR round= new RR(Jobs, Integer.parseInt(args[1]));				
		}//else
		else
			System.out.println("algorithm not inputted as SJF, FCFS or RR. Quantum Time also cannot be 0 or negative");
	}

}

