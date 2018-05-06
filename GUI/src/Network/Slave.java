package Network;

import java.util.HashMap;

import constants.NetworkConstants;

public class Slave {

	final int numberOfCPU;

	int numberOfCurrentExe;

	int nowExecounter=0;

	int totalCounter=0;

	String Command = "";

	public Slave() {
		numberOfCPU = Runtime.getRuntime().availableProcessors();
		numberOfCurrentExe = 0;
		Command = "exe.sh";
	}

	public void run(){

		do {

			if(nowExecounter <numberOfCPU ){
				//
				String massage = "";

				if (massage.equalsIgnoreCase(NetworkConstants.FINISH_STATUS)){
					break; // end of the program
				} else if (massage.equalsIgnoreCase(NetworkConstants.FINISH_STATUS)){

				} else if (massage.equalsIgnoreCase(NetworkConstants.FINISH_STATUS)){
					nowExecounter++;
					totalCounter++;
					HashMap hash = new HashMap<String,String>();
					Task task = new Task(Command,totalCounter);
					(new Thread(task)).start();;
					nowExecounter--;
				}
			} else {
				

			}

		} while (true);
	}

	public static void main(String[] args) {
		Slave slave = new Slave();
		slave.run();
	}



}
