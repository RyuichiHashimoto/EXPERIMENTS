package Network.Test;

import Network.CommandSetting;
import Network.Constants.NetworkConstants;
import Network.Container.Container;
import experiments.Exception.CommandSetting.notFoundException;

public class ObjectReciever {

	public static void main(String[] args) {
		Container d = NetworkConstants.recieveObject();
		CommandSetting output = (CommandSetting)d.get();

		try {
			System.out.println(output.getAsStr("what"));

		} catch (notFoundException e) {
			System.out.println("error");
		}
		output.subcriptToFile("output/output.dat");
//		System.out.println(d.getStatus());
	}
}
