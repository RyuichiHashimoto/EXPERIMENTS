package Network;

import java.io.IOException;
import java.util.List;

import Util.FileReader;
import constants.MasterConstants;
import constants.NetworkConstants;

public class Master {

	int numberOfTasks;
//	final int[] numberOfEachCPU;

	final String[] PCName;

	String[] massage = {"a","b","c","d","e","f","g","h","i","j","k","l"};


	public static void main(String[] args) {
		System.out.println("dir");
		Master master = new Master();
		master.run();
		System.out.println("end");
	}

	public void SendFinishCommand(){
		for(int i = 0; i < PCName.length;i++){
			do{
				String mas = NetworkConstants.sendObject(NetworkConstants.FINISH_STATUS, PCName[i]);

				if(mas.equalsIgnoreCase(NetworkConstants.SUCCESS_STATUS)){
					break;
				}
			} while(true);

		}
	}

	public void run(){
		int d = 0;
		int key = 0;

		do{

			String tile = NetworkConstants.sendObject(massage[key], PCName[0]);

			if(tile.equalsIgnoreCase(NetworkConstants.SUCCESS_STATUS)){
				System.out.println(massage[key]);
				key++;

			}
			if(key == massage.length){
				SendFinishCommand();




			}

		} while(true);
	}

	public Master(){
		List<String> tempPCList = ReadPCList(MasterConstants.PCListFile);

		PCName = new String[tempPCList.size()];

		for(int t = 0;t<tempPCList.size();t++){
			PCName[t] = tempPCList.get(t);
		}

//		Setting();
	}


	private void SetPCList(){


	}

	private void Setting(){

		for(int t = 0; t < PCName.length;t++){
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
