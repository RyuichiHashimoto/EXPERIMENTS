package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements GUIInterface{

	protected JLabel label;
	protected JPanel mainPanel;


	public MainFrame(){

		settingWindow();

		setIcon();

		setMainPanel();

		setTitle();

		setRunBottuon();

	}

	private void setRunBottuon() {
		JPanel buttonPanel = new JPanel();
		JButton btn1 = new JButton("test button");

		btn1.setVerticalAlignment(btn1.CENTER);
		btn1.setPreferredSize(BOTTUNSIZE);
		buttonPanel.add(btn1);

//		buttonPanel.setBounds(100,100,100,100);

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					new ExecuteFrame("FinalFUN1.dat").run();
				} catch (IOException e1) {
					new ErrorFrame(IOERRORTITLE ,"Sorry, I cannot found FinalFUN1.dat");
				//	logger
				}
			}
		});
		mainPanel.add(buttonPanel);
	}

	private void setMainPanel() {
		mainPanel = new JPanel(null);
		mainPanel.setBackground(BACKGROUNDCOLOR);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		getContentPane().add(mainPanel);
	}

	private void setTitle(){
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel(TITLELABEL);
		title.setFont(TITLEFONT);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.TOP);
		titlePanel.add(title);
		titlePanel.setBackground(BACKGROUNDCOLOR);
		mainPanel.add(titlePanel);
	}

	private void settingWindow(){
		//Specifing the behabior of ...;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set size and position of this application
		setBounds(100,100,WINDOWHEIGHT,WINDOWWIDTH);
		// set the background color
		getContentPane().setBackground(BACKGROUNDCOLOR);
	}

	private void setIcon(){
		setIconImage(new ImageIcon(ICONPATH).getImage());
	}

	public static void main(String[] args) {
		MainFrame gui = new MainFrame();
		gui.setVisible(true);
		System.out.println("string valueof ");
	}

}
