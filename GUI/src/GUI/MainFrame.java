package GUI;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class MainFrame extends JFrame implements GUIInterface {

	protected JPanel mainPanel;
	private JTextArea textArea;
	List<String> commandList;

	public MainFrame() {

		initClassVariable();

		settingWindow();

		setIcon();

		setMainPanel();

		setTitle();

		setAddBotton();

		setRunBottuon();

	}

	private void initClassVariable() {
		commandList = new ArrayList<String>();
	}

	private void setAddBotton() {
		JPanel buttonPanel = new JPanel();
		textArea = new JTextArea();
		textArea.setBackground(BACKGROUNDCOLOR);
		JTextField textField = new JTextField(TextFieldSize);

		// Initialization of button
		JButton btn1 = new JButton("Add Command");
		btn1.setVerticalAlignment(btn1.CENTER);
		btn1.setPreferredSize(BOTTUNSIZE);
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		// add the button and text field in this panel
		buttonPanel.add(textField);
		buttonPanel.add(btn1);
		buttonPanel.setTransferHandler(new DropFileHandler());
		buttonPanel.setBackground(BACKGROUNDCOLOR);
		buttonPanel.add(textArea);
		// add this panel to main panel.
		mainPanel.add(buttonPanel);

	}

	private void setRunBottuon() {
		JPanel buttonPanel = new JPanel();
		JButton btn1 = new JButton("test button");

		btn1.setVerticalAlignment(btn1.CENTER);
		btn1.setPreferredSize(BOTTUNSIZE);
		buttonPanel.add(btn1);

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new ExecuteFrame(commandList).run();
				} catch (IOException e1) {
					new ErrorFrame(IOERRORTITLE, "Sorry, I cannot found FinalFUN1.dat");
					// logger
				}
			}
		});
		mainPanel.add(buttonPanel);
	}

	private void setMainPanel() {
		
		// initialize the main panel 
		mainPanel = new JPanel(null);
		mainPanel.setBackground(BACKGROUNDCOLOR);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setTransferHandler(new DropFileHandler());
		
		getContentPane().add(mainPanel);
	}

	private void setTitle() {
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel(TITLELABEL);
		title.setFont(TITLEFONT);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.TOP);
		titlePanel.add(title);
		titlePanel.setBackground(BACKGROUNDCOLOR);
		mainPanel.add(titlePanel);
	}

	private void settingWindow() {
		// Specifing the behabior of ...;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set size and position of this application
		setBounds(100, 100, WINDOWHEIGHT, WINDOWWIDTH);
		// set the background color
		getContentPane().setBackground(BACKGROUNDCOLOR);
	}

	private void setIcon() {
		setIconImage(new ImageIcon(ICONPATH).getImage());
	}

	public static void main(String[] args) {
		MainFrame gui = new MainFrame();
		gui.setVisible(true);
		System.out.println("string valueof ");
	}

	public class DropFileHandler extends TransferHandler {
		/**
		 * ドロップされたものを受け取るか判断 (ファイルのときだけ受け取る)
		 */
		@Override
		public boolean canImport(TransferSupport support) {
			if (!support.isDrop()) {
				// ドロップ操作でない場合は受け取らない
				return false;
			}

			if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				// ドロップされたのがファイルでない場合は受け取らない
				return false;
			}
			
			return true;
		}

		/**
		 * ドロップされたファイルを受け取る
		 */
		@Override
		public boolean importData(TransferSupport support) {
			// 受け取っていいものか確認する
			if (!canImport(support)) {
				return false;
			}

			// ドロップ処理
			Transferable t = support.getTransferable();
			try {
				// ファイルを受け取る
				List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);

				// テキストエリアに表示するファイル名リストを作成する
				StringBuffer fileList = new StringBuffer();
				
				for (File file : files) {
					fileList.append(file.getName());
					fileList.append("\n");
					commandList.add(file.getPath());
				}

				// テキストエリアにファイル名のリストを表示する
				textArea.setText(fileList.toString());
				
			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

}
