package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Util.FileReader;

/*
 * Data Fram
 *
 *
 */

public class ExecuteFrame extends JFrame implements GUIInterface {

	String fileContent;
	String filePath;
	private static final String TITLESTRING = "EXECUTE MODE";
	public static final Font CONTENTFONT = new Font("TIMES NEW ROMAN", Font.BOLD, 16);

	JLabel contents;
	JLabel titleLabel;
	JLabel filePathLabel;

	JPanel TitlejPanel;
	JPanel SettingPanel;

	JButton SCButton;
	JPanel mainPanel = new JPanel();

	JTextArea contentArea;
	JButton[] DirectoryBotton;

	public ExecuteFrame(String filePath_) throws IOException {

		settingWindow();

		setIcon();

		initlizePanel();

		filePath = filePath_;
		fileContent = FileReader.FileReading(filePath);
		initTitleLabel();
		initSettingPanel();

		initComponet();

		// getContentPane().add(TitlejPanel);

		setVisible(true);
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

		titleLabel = new JLabel(TITLESTRING);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setFont(TITLEFONT);
		titleLabel.setForeground(Color.black);
		TitlejPanel.add(titleLabel, TITLESIZE);
		mainPanel.add(TitlejPanel);


	}

	private void settingWindow() {
		// Specifing the behabior of ...;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


	//プログラムを実装予定
	public void run(){
		//実装予定なため
	}
}
