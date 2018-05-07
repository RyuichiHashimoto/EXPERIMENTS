package experiments;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.HashMap;

import Util.DirectoryMaker;
import Util.FileConstants;
import experiments.Exception.CommandSetting.CannotConvertException;
import experiments.Exception.CommandSetting.notFoundException;

public class CommandSetting implements Serializable{

	protected HashMap<String,String> parameter;

	public CommandSetting(CommandSetting d){
		parameter = new HashMap<String,String>(d.getHashMap());
		//System.out.println("eoupu");
	}

	public CommandSetting(){
		parameter = new HashMap<String,String>();
	}
	public CommandSetting(HashMap<String,String> parameter_){
		parameter = new HashMap<String,String>(parameter_);
	}

	public HashMap<String,String> getHashMap(){
		return parameter;
	}

	public void clear(){
		parameter.clear();
	}

	public void put(String key, String value){
		if(!parameter.containsKey(key))
			parameter.put(key,value);
	}

	public void putForce(String key,String value){
		parameter.put(key, value);
	}

	public CommandSetting clone(){
		return new CommandSetting(this);
	}

	public boolean containKey(String key){
		return this.parameter.containsKey(key);
	}

	protected  String get(String key) throws notFoundException{
		if(!this.containKey(key)){
			throw new notFoundException("the key " + key + " is not found");
		}
		return parameter.get(key);
	}

	public String getAsStr(String key) throws notFoundException {
		return this.get(key);
	}

	public double getAsDouble(String key) throws NumberFormatException, notFoundException{
		return Double.parseDouble(this.get(key));
	}

	public int getAsInt(String key) throws NumberFormatException, notFoundException{
		return Integer.parseInt(this.get(key));
	}

	public boolean getAsBoolean(String key) throws notFoundException,CannotConvertException{
		String value = this.get(key).toLowerCase();

		if(value.equals("max") || value.equals("true")|| value.equals("truth")|| value.equals("y")|| value.equals("yes") )
			return true;

		if(value.equals("min") || value.equals("false")|| value.equals("fault")|| value.equals("n")|| value.equals("no") )
			return false;

		throw new CannotConvertException("massage " + value + " cannot convert into boolean");
	}

	public void subscriptHash(){
		System.out.println("------------------start------------------");

		for (String key :parameter.keySet()){
			System.out.println(key + "\t" + parameter.get(key));
		}
		System.out.println("------------------end------------------");
	}

	public void subcriptToFile(String filePath){

		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			for (String key : parameter.keySet()){
				bw.write(key + "	" + parameter.get(key)+FileConstants.NEWLINE_DEMILITER);
			}
			bw.close();
		} catch (IOException e) {


			String[] arg = filePath.split(FileConstants.FILEPATH_DEMILITER);

			// args is the args remove the last element.
			String[]  args = new String[arg.length-1];
			for(int i= 0;i < args.length ;i++){
				args[i] = arg[i];
			}

			String dir = String.join(FileConstants.FILEPATH_DEMILITER, args);
			DirectoryMaker.Make(dir);
			System.out.println(e.getMessage());
			subcriptToFile(filePath);
			return;
		}

	}


	public static void main(String[] args) throws NumberFormatException, notFoundException {
		CommandSetting commandSetting = new CommandSetting();
		commandSetting.put("A","B");
		commandSetting.put("B","0.5");
		commandSetting.put("C","B");
		commandSetting.put("D","0.5");
//		commandSetting.put("E","B");
		commandSetting.put("F","0.5");
		commandSetting.put("E","outpuit");

		commandSetting.subscriptHash();
//		commandSetting.subcriptToFile("odfasdf/output.dat");
		System.out.println(commandSetting.getAsStr("A"));
	}


}
