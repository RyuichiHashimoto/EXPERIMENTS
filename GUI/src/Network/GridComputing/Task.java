package Network.GridComputing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import Network.CommandSetting;
import Network.Constants.NetworkConstants;
import experiments.Exception.CommandSetting.notFoundException;

public class Task implements Runnable, Serializable {

	protected String Status;

	CommandSetting setting;

	protected String mainJarFile = "MOMFO.jar";

	protected int time = 0;

	protected final String command;

	public Task(String Command_, int key) {
		command = Command_;
		time  = key;
	}

	
	public void outputSettingFile(){ 

		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		try {
			fos = new FileOutputStream("CommandSetting/setting"+String.valueOf(time)+ ".st");
		} catch (FileNotFoundException e) {
			File file = new File("CommandSetting");
			if(!file.exists())
				file.mkdir();

			outputSettingFile();
		}

		osw = new OutputStreamWriter(fos);
		bw = new BufferedWriter(osw);
		setting = getSetting();

		for(String key : setting.getKeySet()){
			try {
				bw.write(key+":"+ (setting.getAsStr(key)));
			} catch (IOException | notFoundException e) {
				Status = "FILEOUTPUT"+NetworkConstants.ERROR_STATUS;
			}
		}


		try {
			bw.close();
			osw.close();
			fos.close();
		} catch (IOException e) {
		}

	}

	public CommandSetting getSetting(){
		CommandSetting ret = new CommandSetting();
		ret.put("crossover", "SBXCrossover");
		return ret;
	}

	@Override
	public void run() {

		Runtime r = Runtime.getRuntime();
		outputSettingFile();
		try {
			r.exec(command);
			Status = NetworkConstants.SUCCESS_STATUS;
		} catch (IOException e) {
			Status = NetworkConstants.ERROR_STATUS;
		} catch (SecurityException e) {
			Status = NetworkConstants.ERROR_STATUS;
		}
	}

}
