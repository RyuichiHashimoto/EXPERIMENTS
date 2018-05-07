package Network;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Util.FileReader;
import constants.FileConstants;

public class test implements Serializable{

	public static List<HashMap<String,String>> getHashmap(String filePath){
		List<String> fileRead = null;
		try {
			fileRead = FileReader.FileReadingAsArray(filePath);
		} catch (IOException e) {
			return null;
		}		
		return decodeSetting(fileRead);
	}
	
	public static List<HashMap<String,String>> decodeSetting(List<String> filePath){
		List<String>  command = new ArrayList<String>();
		List<String>  label = new ArrayList<String>();
		
		int size = 1;
		
		for(int i = 0; i < filePath.size();i++){
			if(filePath.get(i).contains(FileConstants.SETTING_FILE_DELIMITER) && !filePath.get(i).contains(FileConstants.SETTING_FILE_COMMENTOUT)){
				command.add(filePath.get(i).split(FileConstants.SETTING_FILE_DELIMITER)[1]);
				label.add(filePath.get(i).split(FileConstants.SETTING_FILE_DELIMITER)[0]);
				System.out.println(command.get(command.size()-1));
				size += command.get(command.size()-1).split(FileConstants.SETTING_FILE_SEQUENCE_DEMILITER).length-1;
			}			
		}
		List<HashMap<String,String>> ret = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> first = new HashMap<String,String>();
		ret.add(first);
		
		for(int i = 0; i<command.size();i++){
			addLabel(ret,label.get(i),command.get(i));
		}
		
		System.out.println(ret.size()+"	"+size);
		
		return ret;
	}	
	
	public static void addLabel(List<HashMap<String,String>> hashmaplist,String label,String componet){
		String [] splitedcomponet = componet.split(FileConstants.SETTING_FILE_SEQUENCE_DEMILITER);
		List<HashMap<String,String>> temphashmaplist = new ArrayList<HashMap<String,String>> (hashmaplist);
		hashmaplist.clear();
		
		for(int i =0;i<splitedcomponet.length;i++){			
			for(int t = 0; t < temphashmaplist.size();t++){
				HashMap<String,String> temp = (HashMap<String, String>) temphashmaplist.get(t).clone();
				temp.put(label, splitedcomponet[i]);
				hashmaplist.add(temp);
			}
			
		}
		
	}
	
	
	public static void main(String[] args){
		System.out.println("ednd");
		List<HashMap<String,String>> ret = getHashmap("setting.st");
		
		for(int i = 0;i<ret.size();i++){
			subscriptHash(ret.get(i));
		}		
	}

	private static void subscriptHash(HashMap<String, String> hashMap) {
		System.out.println("------------------start------------------");
		
		for (String key :hashMap.keySet()){
			System.out.println(key + "	" + hashMap.get(key));
		}
		System.out.println("------------------end------------------");
		
	}
}
