package Network;

import java.util.HashMap;

import constants.NetworkConstants;

public class Slave {

	final int numberOfCPU;

	int numberOfCurrentExe;

	int nowExecounter = 0;

	int totalCounter = 0;

	String Command = "";

	public Slave(int nCPU) {
		numberOfCPU = nCPU;
		numberOfCurrentExe = 0;
		Command = "exe.sh";
	}

	public void run() {
		boolean endFlag = false;

		do {
			if (nowExecounter < numberOfCPU) {

				Container container = NetworkConstants.recieveObject();

				String status = (String) container.getStatus();
				String massage = (String) container.get();

				if ((massage.equalsIgnoreCase(NetworkConstants.FINISH_STATUS))){

					System.out.println("end of "+massage);
					endFlag = true;
				} else if (status.equalsIgnoreCase(NetworkConstants.SUCCESS_STATUS)) {
					System.out.println(status+" " + massage + "	" + NetworkConstants.SUCCESS_STATUS + " " + NetworkConstants.FINISH_STATUS);

					nowExecounter++;
					totalCounter++;
					HashMap hash = new HashMap<String, String>();
					Task task = new Task("echo " + massage, totalCounter);
					(new Thread(task)).start();
//					System.out.println("echo " + massage);
					nowExecounter--;
				}
			}
			if(endFlag){
				break;
			}

		} while (true);


	}

	public static void main(String[] args) {
		int maxCPU = Runtime.getRuntime().availableProcessors();
		int useCPU = Integer.parseInt(args[0]);
		Slave slave = new Slave(useCPU < maxCPU ? useCPU : maxCPU);
		slave.run();
		System.out.println("end");
	}

}
