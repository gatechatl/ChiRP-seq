package GenerateBEDFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class GenerateBEDTwo {

	
	public static void main(String[] args) {
		
		
		try {
			HashMap map = new HashMap();
			
			String fileName = "C:\\School Notes\\ChirpSeq\\Result\\Report\\Homo_sapiens.GRCh37.67.CHR.gtf";
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));			
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String chr = split[0];
				int start = new Integer(split[3]);
				int end = new Integer(split[4]);
				String stuff = split[8];
				String geneName = stuff.split(";")[3].split("gene_name")[1].replaceAll("\"", "").trim();
				if (map.containsKey(geneName)) {
					String stuff2 = (String)map.get(geneName);
					String oldchr = stuff2.split(":")[0];
					int oldstart = new Integer(stuff2.split(":")[1]);
					int oldend = new Integer(stuff2.split(":")[2]);
					if (oldstart < start) {
						start = oldstart;
					}
					if (oldend > end) {
						end = oldend;
					}
					map.put(geneName, chr + ":" + start + ":" + end);
				} else {
					map.put(geneName, chr + ":" + start + ":" + end);
				}
			}
			in.close();
			
			HashMap macs_map = new HashMap();
			fileName = "C:\\School Notes\\ChirpSeq\\Result\\Report\\20121019-public-2.0.2-bWfzdR-hg19-all-gene.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));			
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String[] peaks = split[1].split(",");
				for (String peak: peaks) {
					String macs = peak.split(" ")[0];
					String key = split[0];
					String geneName = split[0];
					if (macs_map.containsKey(macs)) {
						String stuff = (String)macs_map.get(macs);
						String extra = (String)map.get(key);
						macs_map.put(macs, geneName + "\t" + stuff);
					} else {
						String extra = (String)map.get(key);
						macs_map.put(macs, geneName);
					}
				}
			}
			in.close();

			HashMap macs_map_two = new HashMap();
			fileName = "C:\\School Notes\\ChirpSeq\\Result\\Report\\two.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));			
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String[] peaks = split[1].split(",");
				for (String peak: peaks) {
					String macs = peak.split(" ")[0];
					String key = split[0];
					String geneName = split[0];
					
					HashMap geneList = new HashMap();
					if (macs_map.containsKey(macs)) {
						String[] gah = ((String)macs_map.get(macs)).split("\t");
						for (int i = 0; i < gah.length; i++) {
							geneList.put(gah[i], gah[i]);
						}
					}
					
					if (!geneList.containsKey(geneName)) {
						if (macs_map_two.containsKey(macs)) {
							String stuff = (String)macs_map_two.get(macs);
							String extra = (String)map.get(key);						
							macs_map_two.put(macs, geneName + "(" + extra + ")[" + "ChirpPeak dist to TSS" + peak.split(" ")[1] + "]" + "\t" + stuff);
						} else {
							String extra = (String)map.get(key);
							macs_map_two.put(macs, geneName + "(" + extra + ")[" + "ChirpPeak dist to TSS" + peak.split(" ")[1] + "]");
						}
					}
				}
			}
			in.close();

			HashMap macs_map_single = new HashMap();
			fileName = "C:\\School Notes\\ChirpSeq\\Result\\Report\\single.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));			
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String[] peaks = split[1].split(",");
				for (String peak: peaks) {
					String macs = peak.split(" ")[0];
					String key = split[0];
					String geneName = split[0];
					
					HashMap geneList = new HashMap();
					if (macs_map.containsKey(macs)) {
						String[] gah = ((String)macs_map.get(macs)).split("\t");
						for (int i = 0; i < gah.length; i++) {
							geneList.put(gah[i], gah[i]);
						}
					}
					if (!geneList.containsKey(geneName)) {
						if (macs_map_single.containsKey(macs)) {
							String stuff = (String)macs_map_single.get(macs);
							String extra = (String)map.get(key);						
							macs_map_single.put(macs, geneName + "(" + extra + ")[" + "ChirpPeak dist to TSS" + peak.split(" ")[1] + "]" + "\t" + stuff);
						} else {
							String extra = (String)map.get(key);
							macs_map_single.put(macs, geneName + "(" + extra + ")[" + "ChirpPeak dist to TSS" + peak.split(" ")[1] + "]");
						}
					}
				}
			}
			in.close();

			
			String outputFile = "C:\\School Notes\\ChirpSeq\\Result\\Report\\TwoNearestGene_Filtered_chirp_treatment_vs_control.txt";
		    FileWriter fwriter_ncRNA = new FileWriter(outputFile);
		    BufferedWriter outTwo  = new BufferedWriter(fwriter_ncRNA);

			String outputFileSingle = "C:\\School Notes\\ChirpSeq\\Result\\Report\\SingleNearestGene_Filtered_chirp_treatment_vs_control.txt";
		    FileWriter fwriter_ncRNASingle = new FileWriter(outputFileSingle);
		    BufferedWriter outSingle  = new BufferedWriter(fwriter_ncRNASingle);
		    
			fileName = "C:\\School Notes\\ChirpSeq\\Result\\Report\\Filtered_chirp_treatment_vs_control_peaks.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			outTwo.write(in.readLine() + "\n");
			
			
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				
				if (macs_map_two.containsKey(split[3])) {
					outTwo.write(str + "\t" + (String)macs_map_two.get(split[3]) + "\n");
				}
				if (macs_map_single.containsKey(split[3])) {
					outSingle.write(str + "\t" + (String)macs_map_single.get(split[3]) + "\n");
				}
				
			}
			in.close();
			outTwo.close();
			outSingle.close();
			
			System.out.println(macs_map_two.size());
			System.out.println(macs_map_single.size());
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
