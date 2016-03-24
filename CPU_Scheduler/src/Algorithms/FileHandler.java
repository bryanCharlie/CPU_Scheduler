package Algorithms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
	private ArrayList<PCB> Jobs = new ArrayList<PCB>();
	private Scanner scanner = null;
	
	public FileHandler(String in){


		try {
			scanner = new Scanner(new FileReader(in)); //initialize scanner with file name
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		readSource();
		scanner.close();
	}

	private void readSource()  {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] result = line.split(" ");
			String id = result[0];
			String arrivalTime = result[1];
			String noOfBursts = result[2];
			PCB jobx = null;
			ArrayList<Integer> bursts = new ArrayList<Integer>();
			
			int i = 2;
			
			while(i++ != result.length - 1)
				bursts.add(Integer.parseInt(result[i]));
			
			
			jobx=new PCB(id, arrivalTime, noOfBursts, bursts);
			Jobs.add(jobx);
		}
		
	}

	public ArrayList<PCB> getJobs(){
		return Jobs;
	}
}




