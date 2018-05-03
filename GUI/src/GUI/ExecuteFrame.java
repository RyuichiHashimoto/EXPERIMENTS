package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		
		initButton();

	//	initComponet();

		//getContentPane().add(TitlejPanel);
		
		setVisible(true);
	}

	private void initButton() {
		SCButton = new JButton();
		
	}

	private void initlizePanel(){
		mainPanel = new JPanel(null);
		mainPanel.setBackground(BACKGROUNDCOLOR);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		getContentPane().add(mainPanel);
	}

	private void initComponet(){

		contentArea = new JTextArea();
		contentArea.setBackground(new Color(110, 110, 110));

		JScrollPane scrollpanel = new JScrollPane(contentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		contentArea.setText(fileContent);
		contentArea.setEditable(false);
		mainPanel.add(scrollpanel);
//		getContentPane().add(scrollpanel);
//		jpanel.add(scrollpanel);
	}
	
	private void initTitleLabel(){
		TitlejPanel = new JPanel(null);
		TitlejPanel.setBackground(BACKGROUNDCOLOR);
		TitlejPanel.setLayout(new BoxLayout(TitlejPanel, BoxLayout.Y_AXIS));
		
		titleLabel = new JLabel(TITLESTRING);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setFont(TITLEFONT);
		titleLabel.setForeground(Color.black);
		TitlejPanel.add(titleLabel, TITLESIZE);		
		filePathLabel = new JLabel(filePath);		
		filePathLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		filePathLabel.setFont(TITLEFONT);
		filePathLabel.setForeground(Color.black);
		TitlejPanel.add(filePathLabel, TITLESIZE);
		
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
			new ExecuteFrame("FinalFUN1.dat");
		} catch (IOException e) {
			new ErrorFrame(IOERRORTITLE, "Sorry, I cannot found FinalFUN1.dat");
//			e.printStackTrace();
		}
		System.out.println("ERROR");

	}


	private void setIcon(){
		setIconImage(new ImageIcon(ICONPATH).getImage());

	}
}
