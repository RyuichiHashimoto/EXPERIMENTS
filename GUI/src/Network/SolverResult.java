package Network;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

import javax.naming.NamingException;

import lib.experiment.Setting;
import lib.lang.NeedOverriden;
import optsys.platform.RunSetting;
import optsys.platform.StreamProvider;

// TODO: implement AutoCloseable?
abstract public class SolverResult<T extends Solver> implements Buildable {

	protected T solver;
	protected Writer writer;


	@Override
	public void build(CommandSetting s) throws ReflectiveOperationException, NamingException, IOException {
		this.solver = s.get(RunSetting.SOLVER);
		writer = new BufferedWriter(((StreamProvider) solver.setting.get(STREAM_PROVIDER)).getWriter(getOutputName(s)));
	}

	abstract protected String getOutputName(Setting s) throws NamingException;

	@NeedOverriden public void beforeRun() throws IOException {};
	@NeedOverriden public void afterRun() throws IOException {};
	@NeedOverriden public void exceptionRise() throws IOException {};

	abstract public void save() throws IOException;
	abstract public void close() throws IOException;

	abstract public void save(Setting s, Object ... results) throws IOException, NamingException;
	abstract public Serializable getMemento();

}