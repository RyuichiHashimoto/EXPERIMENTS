package Network;

import java.io.Serializable;

public class test implements Serializable{

	String componet ="false";

	public test(String d){
		componet = d;
	}

	public String get(){
		return componet;
	}
}
