package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements GUIInterface{


	public MainFrame(){
		settingWindow();
		setTitle();
		setIcon();
		//JTextField textfield = new JTextField("初期値");
		JPanel panel1 = new JPanel();
		JButton btn1 = new JButton("RUN");

		btn1.setVerticalAlignment(btn1.CENTER);
		btn1.setPreferredSize(BOTTUNSIZE);
		panel1.add(btn1);
		panel1.setBounds(100,100,100,100);

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new DataFrame("FinalFUN1.dat");
				} catch (IOException e1) {
					new ErrorFrame(IOERRORTITLE ,"Sorry, I cannot found FinalFUN1.dat");
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(panel1,BorderLayout.WEST);
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
