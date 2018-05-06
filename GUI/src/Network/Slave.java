package Network;

import constants.NetworkConstants;

public class Slave {

	final int numberOfCPU;

	int numberOfCurrentExe;

	public Slave() {
		numberOfCPU = Runtime.getRuntime().availableProcessors();
		numberOfCurrentExe = 0;
	}

	public static void main(String[] args) {
		Slave slave = new Slave();

		String massage = "";

		do {

		} while (!massage.equalsIgnoreCase(NetworkConstants.FINISH_STATUS));
	}

}
