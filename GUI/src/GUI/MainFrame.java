package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class MainFrame extends JFrame implements GUIInterface{

	protected JLabel label;

	public MainFrame(){

		settingWindow();

		setTitle();

		setIcon();

		JPanel mainPanel = new JPanel();
		JButton btn1 = new JButton("+");
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		BevelBorder border = new BevelBorder(BevelBorder.RAISED);
		mainPanel.setBorder(border);

		btn1.setVerticalAlignment(btn1.CENTER);
		btn1.setPreferredSize(BOTTUNSIZE);
		mainPanel.add(btn1);
		mainPanel.setBounds(100,100,100,100);

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new DataFrame("FinalFUN1.dat");

				} catch (IOException e1) {
					new ErrorFrame(IOERRORTITLE ,"Sorry, I cannot found FinalFUN1.dat");
				//	logger
				}
			}
		});
		getContentPane().add(mainPanel,BorderLayout.WEST);
	}

	public void setTitle(){
		JLabel title = new JLabel(TITLELABEL);
		title.setFont(TITLEFONT);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.TOP);
		add(title);
	}

	public void settingWindow(){
		//Specifing the behabior of ...;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set size and position of this application
		setBounds(100,100,WINDOWHEIGHT,WINDOWWIDTH);
		// set the background color
		getContentPane().setBackground(BACKGROUNDCOLOR);
	}

	public void setIcon(){
		setIconImage(new ImageIcon(ICONPATH).getImage());
	}


	public static void main(String[] args) {
		MainFrame gui = new MainFrame();
		gui.setVisible(true);
		System.out.println("string valueof ");
	}

}
