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
import javax.swing.TransferHandler;

public class MainFrame extends JFrame implements GUIInterface {

	protected JPanel mainPanel;
	private JTextArea textArea;
	List<String> commandList;

	List<JButton> settingButton;

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
		settingButton = new ArrayList<JButton>();
	}

	private void setAddBotton() {
		JPanel buttonPanel = new JPanel();
		textArea = new JTextArea();
		textArea.setBackground(BACKGROUNDCOLOR);

		buttonPanel.setTransferHandler(new DropFileHandler());
		buttonPanel.setBackground(BACKGROUNDCOLOR);
		buttonPanel.add(textArea);
		textArea.setEditable(false);

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
				new ExecuteFrame(commandList).run();
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
		 * �h���b�v���ꂽ���̂��󂯎�邩���f (�t�@�C���̂Ƃ������󂯎��)
		 */
		@Override
		public boolean canImport(TransferSupport support) {
			if (!support.isDrop()) {
				// �h���b�v����łȂ��ꍇ�͎󂯎��Ȃ�
				return false;
			}

			if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				// �h���b�v���ꂽ�̂��t�@�C���łȂ��ꍇ�͎󂯎��Ȃ�
				return false;
			}

			return true;
		}

		/**
		 * �h���b�v���ꂽ�t�@�C�����󂯎��
		 */
		@Override
		public boolean importData(TransferSupport support) {
			// �󂯎���Ă������̂��m�F����
			if (!canImport(support)) {
				return false;
			}

			// �h���b�v����
			Transferable t = support.getTransferable();
			try {
				// �t�@�C�����󂯎��
				List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);

				// �e�L�X�g�G���A�ɕ\������t�@�C�������X�g���쐬����
				StringBuffer fileList = new StringBuffer();

				for (File file : files) {
					fileList.append(file.getName());
					fileList.append("\n");
					commandList.add(file.getPath());
				}

				// �e�L�X�g�G���A�Ƀt�@�C�����̃��X�g��\������
				textArea.setText(textArea.getText()+fileList.toString());

			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

}
