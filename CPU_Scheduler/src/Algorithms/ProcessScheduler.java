import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
 * Author: Bryan Charlie
 * 10/4/2015
 */
public class ProcessScheduler {
	/*
	 * Takes command line arguments to initialize the FileHandler class.
	 * Sends a job list (and quantum time if applicable) to its respective algorithm
	 */
	public static void main(String[] args) throws FileNotFoundException{

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
		else{
			System.out.println("algorithm not inputted as SJF, FCFS or RR. Quantum Time also cannot be 0 or negative");
		}//else
	}//main

}//class

