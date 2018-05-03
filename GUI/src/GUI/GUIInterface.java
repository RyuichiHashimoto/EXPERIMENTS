package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public interface GUIInterface {

	public static final String TITLELABEL = "Evolutionary Multi-objective Multi-tasking";
	public static final Font TITLEFONT = new Font("ARIAL",Font.BOLD,33);
	public static final Color BACKGROUNDCOLOR = Color.GRAY;
	public static final Dimension BOTTUNSIZE = new Dimension(100,20);
	public static final Dimension TITLESIZE = new Dimension(2,2);

	public static final int DEFAULT_X_POSITION = 100;
	public static final int DEFAULT_Y_POSITION = 100;
	
	public static final int WINDOWHEIGHT = 640;
	public static final int WINDOWWIDTH = 480;
	public static final String ICONPATH = "./icon.jpg";
	public static final String IOERRORTITLE = "IO Error";
}
