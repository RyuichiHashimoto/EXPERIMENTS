package Network.Test;

import Network.Container;
import Network.test;
import constants.NetworkConstants;

public class ObjectReciever {

	public static void main(String[] args) {
		Container d = NetworkConstants.recieveObject();

		System.out.println(((test)d.get()).get());
		System.out.println(d.getStatus());
	}
}
