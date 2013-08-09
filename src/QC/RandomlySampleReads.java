package QC;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class RandomlySampleReads {

	public static void main(String[] args) {
		
		
		try {
			
			String fileName = args[0];
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 			
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			
			Random rand = new Random();
			int count = 0;
			while (in.ready()) {
				String str = in.readLine();
				count++;
				if ((count - 2) % 5 == 0) {
					if (rand.nextDouble() < 0.01) {
						if (count > 50000) {
							System.exit(0);
						}
						System.out.println(">" + count + "\n" + str);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
