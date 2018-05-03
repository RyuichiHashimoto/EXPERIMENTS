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

/*
 * Data Fram
 *
 *
 */

public class ErrorFrame extends JFrame implements GUIInterface{

	//This variable is for the font used in ErrorMassage
	public static final Font ERRORFONT = new Font("ARIAL",Font.BOLD,16);
	public static final Font TITLEFONT = new Font("ARIAL",Font.BOLD,16);


	protected String filePath;
	protected JLabel contents;
	protected String frameTitle;

	private JLabel titleLabel;
	private JLabel contentLabel;

	protected String errorTitle;
	protected String errorMassage;
	JTextArea contentArea;
	private JPanel jpanel;

	public ErrorFrame(String  errorTitle_,String errorMassage_){
		errorTitle = errorTitle_;
		errorMassage = errorMassage_;

		settingErrorWindow();
		setIcon();

		initlizePanel();

		initTitleLabel(errorTitle);

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
		contentArea.setBackground(new Color(150, 150, 150));
		contentArea.setForeground(Color.red);
		JScrollPane scrollpanel = new JScrollPane(contentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentArea.setText(errorMassage);
		contentArea.setEditable(false);

		jpanel.add(scrollpanel);
	}

	public void settingErrorWindow(){
		//Specifing the behabior of ...;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// set size and position of this application
		setBounds(100,100,WINDOWHEIGHT/2,WINDOWWIDTH/2);
		// set the background color
		getContentPane().setBackground(BACKGROUNDCOLOR);
	}

	public void setContent(String contentLabel_){
		contentLabel = new JLabel(contentLabel_);
		contentLabel.setFont(TITLEFONT);
		contentLabel.setForeground(Color.red);
		contentLabel.setHorizontalAlignment(JLabel.CENTER);
		contentLabel.setVerticalAlignment(JLabel.BOTTOM);

//		getContentPane().add(title, BorderLayout.NORTH);
		add(contentLabel);
	}

	private void initTitleLabel(String TitleLabel){
		titleLabel = new JLabel(TitleLabel);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setFont(TITLEFONT);
		titleLabel.setForeground(Color.red);
		jpanel.add(titleLabel, TITLESIZE);
	}

	private  void setIcon() {
		setIconImage(new ImageIcon(ICONPATH).getImage());
	}

	public static void main(String[] args) throws IOException {
		new ErrorFrame("IO ERROR","We could not found such a file");
	}

}
