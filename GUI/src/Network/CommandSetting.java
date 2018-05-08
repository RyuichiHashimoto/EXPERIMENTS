package Network;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

import experiments.Generics;
import experiments.Exception.CommandSetting.CannotConvertException;
import experiments.Exception.CommandSetting.notFoundException;
import lib.directory.DirectoryMaker;
import lib.io.FileConstants;

public class CommandSetting implements Serializable {

	protected HashMap<String, Object> parameter;

	public CommandSetting(CommandSetting d) {
		parameter = new HashMap<String, Object>(d.getHashMap());
		// System.out.println("eoupu");
	}

	public CommandSetting() {
		parameter = new HashMap<String, Object>();
	}

	public CommandSetting(HashMap<String, Object> parameter_) {
		parameter = new HashMap<String, Object>(parameter_);
	}

	public String[] getAsSArray(String key, String delimiter) throws NameNotFoundException, notFoundException {
		Object o = get(key);
		if (o instanceof String) {
			return ((String) o).split(delimiter);
		} else if (o instanceof String[]) {
			return (String[]) o;
		} else {
			throw new ClassCastException();
		}
	}

	public String[] getAsSArray(String key, String delimiter, String[] def) throws notFoundException {
		if (containKey(key)) {
			try {
				return getAsSArray(key, delimiter);
			} catch (NameNotFoundException e) {
				throw new IllegalStateException(e);
			}
		} else {
			return def;
		}
	}
	public HashMap<String, Object> getHashMap() {
		return parameter;
	}

	public void clear() {
		parameter.clear();
	}

	public Set<String> getKeySet() {
		return parameter.keySet();
	}

	public void put(String key, Object value) {
		if (!parameter.containsKey(key))
			parameter.put(key, value);
	}

	public void putForce(String key, Object value) {
		parameter.put(key, value);
	}

	public CommandSetting clone() {
		return new CommandSetting(this);
	}

	public boolean containKey(String key) {
		return this.parameter.containsKey(key);
	}

	protected String get(String key) throws notFoundException {
		if (!this.containKey(key)) {
			throw new notFoundException("the key " + key + " is not found");
		}
		return (String) parameter.get(key);
	}

	public String getAsStr(String key) throws notFoundException {
		return this.get(key);
	}

	public double getAsDouble(String key) throws NumberFormatException, notFoundException {
		return Double.parseDouble(this.get(key));
	}

	public int getAsInt(String key) throws NumberFormatException, notFoundException {
		return Integer.parseInt(this.get(key));
	}

	public boolean getAsBoolean(String key) throws notFoundException, CannotConvertException {
		String value = this.get(key).toLowerCase();

		if (value.equals("max") || value.equals("true") || value.equals("truth") || value.equals("y")
				|| value.equals("yes"))
			return true;

		if (value.equals("min") || value.equals("false") || value.equals("fault") || value.equals("n")
				|| value.equals("no"))
			return false;

		throw new CannotConvertException("massage " + value + " cannot convert into boolean");
	}

	public boolean getAsBool(String key) throws notFoundException, CannotConvertException {
		String value = this.get(key).toLowerCase();

		if (value.equals("max") || value.equals("true") || value.equals("truth") || value.equals("y")
				|| value.equals("yes"))
			return true;

		if (value.equals("min") || value.equals("false") || value.equals("fault") || value.equals("n")
				|| value.equals("no"))
			return false;

		throw new CannotConvertException("massage " + value + " cannot convert into boolean");
	}

	public void subscriptHash() {
		System.out.println("------------------start------------------");

		for (String key : parameter.keySet()) {
			System.out.println(key + "\t" + parameter.get(key));
		}
		System.out.println("------------------end------------------");
	}

	public void subcriptToFile(String filePath) {

		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			for (String key : parameter.keySet()) {
				bw.write(key + "	" + parameter.get(key) + FileConstants.NEWLINE_DEMILITER);
			}
			bw.close();
		} catch (IOException e) {

			String[] arg = filePath.split(FileConstants.FILEPATH_DEMILITER);

			// args is the args remove the last element.
			String[] args = new String[arg.length - 1];
			for (int i = 0; i < args.length; i++) {
				args[i] = arg[i];
			}

			String dir = String.join(FileConstants.FILEPATH_DEMILITER, args);
			DirectoryMaker.Make(dir);
			System.out.println(e.getMessage());
			subcriptToFile(filePath);
			return;
		}

	}

	public CommandSetting set(String key, Object Value) {
		put(key, Value);
		return this;
	}

	public <T> T getAsInstance(String key) throws NamingException, ReflectiveOperationException, notFoundException {
		return getAsInstance(key, Thread.currentThread().getContextClassLoader());
	}

	public <T> T getAsInstance(String key, ClassLoader cl)
			throws NamingException, ReflectiveOperationException, notFoundException {
		return getAsInstance_(key, cl);
	}

	private <T> T getAsInstance_(String key, ClassLoader cl)
			throws NamingException, ReflectiveOperationException, notFoundException {
		try {
			return Generics.cast(getToClass(key, cl).newInstance());
		} catch (InstantiationException e) {
			// e has no message in Java7 etc
			throw new InstantiationException(
					"Failed to instantiate " + getAsStr(key) + " (associated with " + key + ") : " + e.getMessage());
		}
	}

	public <T> T getAsInstance(String key, String deflt)
			throws NamingException, ReflectiveOperationException, notFoundException {
		if (!containKey(key))
			set(key, deflt);
		return getAsInstance(key);
	}

	public Class<?> getToClass(String key, String defaultPkg)
			throws NameNotFoundException, ClassNotFoundException, notFoundException {
		return getToClass(key, Thread.currentThread().getContextClassLoader(), defaultPkg);
	}

	public Class<?> getToClass(String key, ClassLoader cl)
			throws NameNotFoundException, ClassNotFoundException, notFoundException {
		return getToClass(key, cl, null);
	}

	public Class<?> getToClass(String key, ClassLoader cl, String defaultPkg)
			throws NameNotFoundException, ClassNotFoundException, notFoundException {
		Object value = get(key);
		if (value instanceof Class) {
			return (Class<?>) value;
		}
		String fqcn = value.toString();
		if (fqcn.startsWith("class ")) {
			// "class " is added by Class#toString
			fqcn = fqcn.substring("class ".length());
		}
		try {
			return (cl == null) ? Class.forName(fqcn) : cl.loadClass(fqcn);
		} catch (ClassNotFoundException e) {
			if (defaultPkg != null) {
				fqcn = defaultPkg + "." + fqcn;
				return (cl == null) ? Class.forName(fqcn) : cl.loadClass(fqcn);
			} else {
				throw e;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, notFoundException {
		CommandSetting commandSetting = new CommandSetting();
		commandSetting.put("A", "B");
		commandSetting.put("B", "0.5");
		commandSetting.put("C", "B");
		commandSetting.put("D", "0.5");
		commandSetting.put("F", "0.5");
		commandSetting.put("E", "outpuit");

		commandSetting.subscriptHash();
		// commandSetting.subcriptToFile("odfasdf/output.dat");
		System.out.println(commandSetting.getAsStr("A"));
	}

}
