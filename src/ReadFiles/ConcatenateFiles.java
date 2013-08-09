package ReadFiles;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ConcatenateFiles {

	public static void main(String[] args) {
		
		try {
			File folder = new File(args[0]);
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		            
		        } else {		        	
				    FileInputStream fstream = new FileInputStream(fileEntry.getName());
					DataInputStream din = new DataInputStream(fstream); 
					BufferedReader in = new BufferedReader(new InputStreamReader(din));
					in.readLine();
					while (in.ready()) {
						String str = in.readLine();
						System.out.println(str);
					}
					in.close();		            
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
