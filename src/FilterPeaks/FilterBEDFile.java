package FilterPeaks;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class FilterBEDFile {

	public static void main(String[] args) {
		
		try {
			HashMap map = new HashMap();
			String fileName = args[0];
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 			
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String stuff = split[0] + "\t" + split[1] + "\t" + split[2];
				map.put(stuff, stuff);
			}
			in.close();
			
			fileName = args[1];
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 			
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String chr = split[0];
				int summit = new Integer(split[1]);
				String name = split[3];
				Iterator itr = map.keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					String[] split2 = key.split("\t");
					String mchr = split2[0];
					int start = new Integer(split2[1]);
					int end = new Integer(split2[2]);
					if (mchr.equals(chr) && start <= summit && summit <= end) {
						System.out.println(mchr + "\t" + (summit - 200) + "\t" + (summit + 200) + "\t" + name);						
					}
				}				
			}
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
