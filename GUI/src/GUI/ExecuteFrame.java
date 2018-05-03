package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import Util.FileReader;

/*
 * Data Fram
 *
 *
 */

public class ExecuteFrame extends JFrame implements GUIInterface {

	String fileContent;
	String filePath;
	public static final Font CONTENTFONT = new Font("TIMES NEW ROMAN", Font.BOLD, 16);

	JLabel contents;
	JLabel title;
	JPanel jpanel;
	JTextArea contentArea;
	
	public ExecuteFrame(String filePath_) throws IOException {
		
		settingWindow();

		setIcon();
		
		initlizePanel();

		title = new JLabel(filePath);
		
		
		
		filePath = filePath_;
		fileContent = FileReader.FileReading(filePath);

		initTitleLabel();
			
		initComponet();
		
		getContentPane().add(jpanel);
		setVisible(true);
	}

	private void initlizePanel(){ 
		jpanel = new JPanel(null);
		jpanel.setBackground(BACKGROUNDCOLOR);
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
		BevelBorder border = new BevelBorder(BevelBorder.RAISED);
		jpanel.setBorder(border);
	}

	private void initComponet(){ 
		contentArea = new JTextArea();
		contentArea.setBackground(new Color(110, 110, 110));

		JScrollPane scrollpanel = new JScrollPane(contentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		contentArea.setText(fileContent);
		contentArea.setEditable(false);
		jpanel.add(scrollpanel);
	}
	
	private void initTitleLabel(){ 
		title = new JLabel(filePath);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(TITLEFONT);
		title.setForeground(Color.black);
		jpanel.add(title, TITLESIZE);
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
			new ExecuteFrame("FinalFUN1_.dat");
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
