package Util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DirectoryGetterTest {
	
	@Test
	public static void getSubDirectory(){
		String directory = "src";
		
		File directoryFile = new File(directory);
			
		if (!directoryFile.exists()) {
			fail("I cannnot found the "+directory+" directory");
		}
		
		File [] subFiles = directoryFile.listFiles();

		if(subFiles == null) {
			fail("I cannnot found the "+directory+" directory");
		}
		
		for (File file : subFiles) {	
			System.out.println(file.getPath());
		}
		
	}
}
