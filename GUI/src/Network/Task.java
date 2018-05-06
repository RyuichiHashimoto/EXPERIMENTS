package Network;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.HashMap;

import constants.NetworkConstants;

public class Task implements Runnable, Serializable {

	private String Status;

	HashMap<String, String> setting;

	private String mainJarFile = "MOMFO.jar";

	private int time = 0;

	private final String command;

	public Task(String Command_, int key) {
		command = Command_;
	}

	public void outputSettingFile(){

		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		try {
			fos = new FileOutputStream("Setting/setting"+String.valueOf(time)+ ".st");
		} catch (FileNotFoundException e) {
			File file = new File("Setting");
			if(!file.exists())
				file.mkdir();

			outputSettingFile();
		}

		osw = new OutputStreamWriter(fos);
		bw = new BufferedWriter(osw);

		for(String key : setting.keySet()){
			try {
				bw.write(key+":"+ ((String)setting.get(key)));
			} catch (IOException e) {
				Status = "FILEOUTPUT"+NetworkConstants.ERROR_STATUS;
			}
		}

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
