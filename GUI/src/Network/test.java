package Network;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import Util.FileReader;

public class test implements Serializable{

	public HashMap<String,String>[] getHashmap(String filePath){
		List<String> fileRead = null;
		try {
			fileRead = FileReader.FileReadingAsArray(filePath);
		} catch (IOException e) {
			return null;
		}		
		return decodeSetting(fileRead);
	}
	
	public HashMap<String,String>[] decodeSetting(List<String> filePath){
		
		
		return null;
	}	
}
