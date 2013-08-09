package GenerateBEDFile;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FindDuplicate {

	public static void main(String[] args) {
		
		
		try {
			
			HashMap map = new HashMap();
			
			String fileName = args[0];
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));			
			while (in.ready()) {
				String str = in.readLine();
				if (str.contains(">")) {
					if (map.containsKey(str)) {
						System.out.println(str);
					} else {
						map.put(str,  str);
					}
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
