package com.airamerica.dataConversion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReadIn {
	
	public static String[] fileReader(String fileName){
		String[] fileInput;
		
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		fileInput = new String[s.nextInt()];
		fileInput[0] = String.valueOf(fileInput.length);
		s.nextLine();
		
		int i = 0;

		while(s.hasNext()) {
		
			fileInput[i] = s.nextLine();
			i++;
		}
		
		return fileInput;
	}
}
