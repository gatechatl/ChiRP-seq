package CoverageDepth;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CalculateDepth {
	
	public static void main(String[] args) {
		
		
		try {

			String fileName = args[0];
			double total = 0;
			double genomeSize = 0;
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 			
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				int start = new Integer(split[1]);
				int end = new Integer(split[2]);
				genomeSize += end - start;
				double count = new Integer(split[3]);
				total += count;
			}
			in.close();

			System.out.println(total + "\t" + total / genomeSize + "\t" + genomeSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
