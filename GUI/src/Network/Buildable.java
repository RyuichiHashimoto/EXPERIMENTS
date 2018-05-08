package Network;

import java.io.IOException;

import javax.naming.NamingException;

import experiments.Exception.CommandSetting.CannotConvertException;
import experiments.Exception.CommandSetting.notFoundException;
import lib.experiments.CommandSetting;

public interface Buildable {
	void build(CommandSetting s) throws NamingException, IOException, ReflectiveOperationException, notFoundException, IllegalArgumentException, CannotConvertException;
}
