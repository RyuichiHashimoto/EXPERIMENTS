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

import Util.FileReader;
import constants.ExeConstants;
import constants.FileConstants;

/*
 * Data Fram
 *
 *
 */

public class ExecuteFrame extends JFrame implements GUIInterface {

	private String fileContent;
	private String filePath;

	private String mainPC = "null";

	JLabel contents;
	JLabel titleLabel;
	JLabel filePathLabel;

	JPanel TitlejPanel;
	JPanel SettingPanel;

	JButton SCButton;
	JPanel mainPanel = new JPanel();

	JTextArea contentArea;
	JButton[] DirectoryBotton;

	String StatusLabel = "label";

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

		readSettingFile();
		System.out.println(mainPC);
		System.out.println(filePath_.get(0));
		//System.out.println(filePath_.get(1));

		filePath = filePath_.get(0);

		fileContent = FileReader.FileReading(filePath);

		settingWindow();

		setIcon();

		initlizePanel();

		initTitleLabel();

		initSettingPanel();

		initComponet();

		initRunButton();

		setVisible(true);
	}

	private void readSettingFile(){

		try {
			List<String> ret = FileReader.FileReadingAsArray(ExeConstants.SETTING_FILE);
			 mainPC = ret.get(0).split(FileConstants.SETTING_FILE_DELIMITER)[1];
		} catch (IOException e) {
			mainPC = null;
		}
	}

	private void initRunButton() {
		JPanel runButtonPanel = new JPanel();
		JButton runButton = new JButton("run program");
		runButtonPanel.add(runButton);

		runButton.setPreferredSize(new Dimension(150, 40));
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder builder = new ProcessBuilder("sh","setting.sh");
				try {
					Runtime r = Runtime.getRuntime();
					StatusLabel = "EXECUTE";
					r.exec("/bin/sh  /cygdrive/c/user/hashimoto/lab/2.ソースコード/1.my_src/1.java/eclipse/git/EXPERIMENTS/GUI/Setting.sh");
					//Process process = builder.start();
				} catch (IOException e1) {
					StatusLabel = "false";
//					e1.printStackTrace();
				}
				System.out.println(StatusLabel);
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

	private void initSettingPanel(){

		//Initialzation of the Setting panel
		SettingPanel = new JPanel(null);
		SettingPanel.setBackground(BACKGROUNDCOLOR);
		SettingPanel.setLayout(new BoxLayout(SettingPanel, BoxLayout.X_AXIS));

		//initialization of the Steting Label
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
			new ExecuteFrame("Setting.st");
		} catch (IOException e) {
			new ErrorFrame(IOERRORTITLE, "Sorry, I cannot found FinalFUN1.dat");
			// e.printStackTrace();
		}
		System.out.println("ERROR");

	}

	private void setIcon() {
		setIconImage(new ImageIcon(ICONPATH).getImage());
	}


	//繝励Ο繧ｰ繝ｩ繝�繧貞ｮ溯｣�莠亥ｮ�
	public void run(){
		//螳溯｣�莠亥ｮ壹↑縺溘ａ
	}
}
