 package Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

	public static String FileReading(String filePath) throws IOException{
		 return Files.lines(Paths.get(filePath))
			        .reduce("", (prev, line) ->
			            prev + line + System.getProperty("line.separator"));
	}

	public static void main(String[] args) throws IOException {
		String args1 = FileReading("README.md");
		System.out.println(args1);
		System.out.println("end");
	}


}
