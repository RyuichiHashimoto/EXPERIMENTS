package Network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public interface SocketInterface {

	int portNumber = 11127;

	int bufferSize = 512;

	static final String FINISHSTATUS = "###FINISH###";

	static final String BUSYSTATUS = "###BUSY###";

	static final String YETSTATUS = "###YET###";

	static final String FREESTATUS = "###FREE###";

	static final String ERRORSTATUS = "###ERROR###";

	static final String SUCCESSSTATUS = "###SUCCESS###";

	static final String CLEARSTATUS = "###CLEAR###";
	
	
	

	// public void transferfiles();
	public default String recieveFile(String filePath) {  
		byte[] buffer = new byte[bufferSize];

		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream inputStream = null;

		OutputStream outputStream = null;

		try {
			serverSocket = new ServerSocket(portNumber);
			socket = serverSocket.accept();
			inputStream = socket.getInputStream();
			outputStream = new FileOutputStream(filePath);

			int fileLength = 0;

			while ((fileLength = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, fileLength);

			}
			// I—¹ˆ—
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			socket.close();
			serverSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERRORSTATUS;
		} 
		return SUCCESSSTATUS;
	}

	public default String sendFile(String filepath, String hostName) {  
		String fileName = filepath;
		File file = new File(fileName);

		byte[] buffer = new byte[bufferSize];
		Socket socket = null;

		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			socket = new Socket(hostName, portNumber);

			inputStream = new FileInputStream(fileName);
			outputStream = socket.getOutputStream();

			int fileLength = 0;
			while ((fileLength = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, fileLength);
			}
			
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return ERRORSTATUS;
		}
		return SUCCESSSTATUS;
	}

	public default String sendMassage(String massage, String hostName) {
		return null;
	}
	
	public default String receiveingMassage() {
		return null;
	}
	
	
}
