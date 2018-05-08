package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Network.Constants.NetworkConstants;
import lib.experiments.ExeConstants;
import lib.io.FileConstants;
import lib.io.input.FileReader;

/*
 * Data Fram
 *
 *
 */

public class ExecuteFrame extends JFrame implements GUIInterface {

	private String fileContent;
	private String filePath;

	private String mainPC = "null";
	private String mainScript = "null";

	private List<String> filePaths;
	private Long WaitTime = (long) 500;
	private String mainPC_OSVersion = "null";
	private String userName = "null";
	private String password = "null";
	private String exeJarFile = "null";
	private String MasterSetting = "null";


	private String PClistFile = "REMOTEPCLIST.ini";

	JLabel contents;
	JLabel titleLabel;
	JLabel filePathLabel;
	JLabel StatusLabel;
	JPanel TitlejPanel;
	JPanel SettingPanel;

	JButton SCButton;
	JPanel mainPanel = new JPanel();

	JTextArea contentArea;
	JButton[] DirectoryBotton;

	String StatusStr = "label";

	String ExecuterScript = "null";

	String masterExecuterScript = "null";
	String slaveExecuterScript = "null";


	public ExecuteFrame(String filePath_) throws IOException {
		readSettingFile();

		settingWindow();

		setIcon();

		initlizePanel();

		initTitleLabel();

		initSettingPanel();

		initComponet();

		initRunButton();

		setVisible(true);
	}

	public ExecuteFrame(List<String> filePath_) {
		filePaths = filePath_;
		readSettingFile();
		System.out.println(mainPC);
		System.out.println(filePath_.get(0));
		// System.out.println(filePath_.get(1));
		StatusStr = "do";
		filePath = filePath_.get(0);

		fileContent = FileReader.FileReading(filePath);

		settingWindow();

		setIcon();

		initlizePanel();

		initTitleLabel();

		initStatusLabel();

		initSettingPanel();

		initComponet();

		initRunButton();

		setVisible(true);
	}

	private void initStatusLabel() {
		// Initialzation of the CommandSetting panel
		SettingPanel = new JPanel(null);
		SettingPanel.setBackground(BACKGROUNDCOLOR);
		SettingPanel.setLayout(new BoxLayout(SettingPanel, BoxLayout.X_AXIS));

		// initialization of the Steting Label
		JLabel PCLabel = new JLabel("MainPC:" + mainPC);
		PCLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		PCLabel.setFont(TITLEFONT);
		PCLabel.setForeground(Color.black);
		SettingPanel.add(PCLabel, TITLESIZE);

		// initialization of the Steting Label

		StatusLabel = new JLabel("\ttasl" + StatusLabel);
		StatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		StatusLabel.setFont(TITLEFONT);
		StatusLabel.setForeground(Color.black);
		SettingPanel.add(StatusLabel, TITLESIZE);

		mainPanel.add(SettingPanel);
	}

	private void readSettingFile() {

		try {

			List<String> ret = FileReader.FileReadingAsArray(ExeConstants.SETTING_FILE);
			mainPC = ret.get(0).split(FileConstants.SETTING_FILE_DELIMITER)[1];
			mainPC_OSVersion = ret.get(1).split(FileConstants.SETTING_FILE_DELIMITER)[1];
			mainScript = ret.get(2).split(FileConstants.SETTING_FILE_DELIMITER)[1];
			userName = ret.get(3).split(FileConstants.SETTING_FILE_DELIMITER)[1];
			password = ret.get(4).split(FileConstants.SETTING_FILE_DELIMITER)[1];
			exeJarFile = ret.get(5).split(FileConstants.SETTING_FILE_DELIMITER)[1];
			MasterSetting = ret.get(6).split(FileConstants.SETTING_FILE_DELIMITER)[1];
			masterExecuterScript = ret.get(7).split(FileConstants.SETTING_FILE_DELIMITER)[1];
			slaveExecuterScript= ret.get(8).split(FileConstants.SETTING_FILE_DELIMITER)[1];
		} catch (IOException e) {
			mainPC = null;
		}
	}

	private void SocketTransfer(Runtime r,String filename) throws IOException, InterruptedException{
		SocketTransfer(r,filename,filename);
	}

	private void SocketTransfer(Runtime r,String sendedname,String outputfile) throws IOException, InterruptedException{
		r.exec("sh  " + mainScript + " " + mainPC + " " + outputfile);
		Thread.sleep(WaitTime);
		String safe = NetworkConstants.sendFile(sendedname,mainPC);
		Thread.sleep(WaitTime);
		System.out.println(safe);

	}

	private void initRunButton() {
		JPanel runButtonPanel = new JPanel();
		JButton runButton = new JButton("run program");
		runButtonPanel.add(runButton);

		runButton.setPreferredSize(new Dimension(150, 40));
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime r = Runtime.getRuntime();
					StatusStr = "EXECUTE";
					StatusLabel.setText(StatusStr);

					if (mainPC_OSVersion.toLowerCase().startsWith("win")) {
						for (int i = 0; i < filePaths.size(); i++) {

						}
					} else if (mainPC_OSVersion.toLowerCase().startsWith("lin")) {

						//Transfer command list
						for (int i = 0; i < filePaths.size(); i++) {
							SocketTransfer(r,filePaths.get(i) ,"command" + String.valueOf(i + 1)+ ".ini");

						}

						SocketTransfer(r, exeJarFile);

						SocketTransfer(r, PClistFile);

						SocketTransfer(r, MasterSetting);

						SocketTransfer(r, masterExecuterScript);

						SocketTransfer(r, slaveExecuterScript);

						System.out.println("ssh " +userName+"@"+ mainPC + " " + "'cd hashimoto/JavaGate && sh MasterExecuter.sh'");
						r.exec("ssh " +userName+"@"+ mainPC + " " + "'cd hashimoto/JavaGate && sh MasterExecuter.sh'");
//						System.out.println("ssh  " + mainPC + " " + "'cd hashimoto/JavaGate && sh MasterExecuter.sh'");
					}

					StatusStr = "SUCCESS";
					StatusLabel.setText(StatusStr);
				} catch (IOException | InterruptedException e1) {
					StatusStr = "FALSE";
					StatusLabel.setText(StatusStr);
				}
				System.out.println(StatusStr);
			}
		});

		mainPanel.add(runButtonPanel);
	}

	private void initComponet() {

		JPanel jpnep = new JPanel();
		jpnep.setBackground(Color.white);
		jpnep.setLayout(new BorderLayout());
		mainPanel.add(jpnep);

	}

	private void initlizePanel() {
		mainPanel = new JPanel(null);
		mainPanel.setBackground(BACKGROUNDCOLOR);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		getContentPane().add(mainPanel);
	}

	private void initSettingPanel() {

		// Initialzation of the CommandSetting panel
		SettingPanel = new JPanel(null);
		SettingPanel.setBackground(BACKGROUNDCOLOR);
		SettingPanel.setLayout(new BoxLayout(SettingPanel, BoxLayout.X_AXIS));

		// initialization of the Steting Label
		filePathLabel = new JLabel(filePath);
		filePathLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		filePathLabel.setFont(TITLEFONT);
		filePathLabel.setForeground(Color.black);
		SettingPanel.add(filePathLabel, TITLESIZE);

		// add the right-side button
		SCButton = new JButton("Show the settings");
		SCButton.setPreferredSize(new Dimension(150, 40));
		SCButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new DataFrame(filePath);
				} catch (IOException e1) {
					new ErrorFrame(IOERRORTITLE, "Sorry, I cannot found " + filePath);
				}
			}
		});
		SettingPanel.add(SCButton, TITLESIZE);

		mainPanel.add(SettingPanel);
	}

	private void initTitleLabel() {
		TitlejPanel = new JPanel(null);
		TitlejPanel.setBackground(BACKGROUNDCOLOR);
		TitlejPanel.setLayout(new BoxLayout(TitlejPanel, BoxLayout.Y_AXIS));

		titleLabel = new JLabel(ExeConstants.TITLE_STRING);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setFont(TITLEFONT);
		titleLabel.setForeground(Color.black);
		TitlejPanel.add(titleLabel, TITLESIZE);
		mainPanel.add(TitlejPanel);
	}

	private void settingWindow() {
		// Specifing the behabior of ...;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// set size and position of this application
		setBounds(DEFAULT_X_POSITION, DEFAULT_Y_POSITION, WINDOWHEIGHT, WINDOWWIDTH);
		// set the background color
		getContentPane().setBackground(BACKGROUNDCOLOR);
	}

	public static void main(String[] args) {
		try {
			new ExecuteFrame("CommandSetting.st");
		} catch (IOException e) {
			new ErrorFrame(IOERRORTITLE, "Sorry, I cannot found FinalFUN1.dat");
			// e.printStackTrace();
		}
		System.out.println("ERROR");

	}

	private void setIcon() {
		setIconImage(new ImageIcon(ICONPATH).getImage());
	}

	// 繝励Ο繧ｰ繝ｩ繝�繧貞ｮ溯｣�莠亥ｮ�
	public void run() {
		// 螳溯｣�莠亥ｮ壹↑縺溘ａ
	}
}
