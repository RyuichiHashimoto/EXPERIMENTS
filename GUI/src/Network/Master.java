package Network;

import java.io.IOException;
import java.util.List;

import Util.FileReader;
import constants.MasterConstants;

public class Master {

	int numberOfTasks;
//	final int[] numberOfEachCPU;

	final String[] PCName;


	public static void main(String[] args) {
		System.out.println("dir");
		Master master = new Master();


	}

	public Master(){
		List<String> tempPCList = ReadPCList(MasterConstants.PCListFile);

		PCName = new String[tempPCList.size()];

		for(int t = 0; t < tempPCList.size();t++){
			PCName[t] = tempPCList.get(t);
			Runtime r = Runtime.getRuntime();
			try {
				r.exec("sh  " + "MasterSetting.sh" + " " + PCName[t]+" " + "MOMFO.jar"+ " "+ "GUI.jar"+ " "+"SlaveExecuter.sh");
			} catch (IOException e) {
				System.exit(-1);
			}
//			Thread.sleep();
		}



	}


	public List<String> ReadPCList(String filePath){
		List<String> PCName_ = null;
		try {
			PCName_ = FileReader.FileReadingAsArray(filePath);

		} catch (IOException e) {
			System.exit(-1);
		}
		return PCName_;
	}

}
