package ComprehensiveReport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class UpstreamGenes {

	
	public static void main(String[] args) {
		
		try {
			HashMap gtfGene = new HashMap();
			HashMap mapping = new HashMap();
			
			HashMap goodGene = getGeneList();

			
			System.out.println(goodGene.size());
			
			
			String inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\GTFFile\\Homo_sapiens.GRCh37.69.gtf";
		    FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));

			int num = 0;
			while (in.ready()) {
				
				String str = in.readLine();
				String[] split = str.split("\t");
				String geneName = grabMeta(split[8], "gene_name");
				if (goodGene.containsKey(geneName)) {
					System.out.println(mapping.size());
					String ensembl = grabMeta(split[8], "gene_id");
					int start = new Integer(split[3]);
					int end = new Integer(split[4]);
					String direction = split[6];
					if (direction.equals("+")) {
						direction = "forward_strand";
					} else {
						direction = "reverse_strand";
					}
					String chr = split[0];
					
					if (gtfGene.containsKey(geneName)) {
						
						String text = (String)gtfGene.get(geneName);
						
						String[] split2 = text.split("\t");					
											
						String[] coordinates = split2[1].split(",");
						
						String finalText = geneName + "\t";
						boolean found = false;
						for (int i = 0; i < coordinates.length; i++) {
							
							//System.out.println(coordinates[i]);
							String newChr = coordinates[i].split(":")[0];
							int newStart = new Integer(coordinates[i].split(":")[1].split("-")[0]);
							int newEnd = new Integer(coordinates[i].split(":")[1].split("-")[1]);
							String newDirection = coordinates[i].split(":")[1].split("-")[2];
	
							
							if (overlap(start, end, newStart, newEnd) && chr.equals(newChr) && direction.equals(newDirection)) {							
								if (start < newStart) {
									newStart = start;							
								}								
								if (end > newEnd) {
									newEnd = end;									
								}
								
								if (i == 0) {
									finalText += newChr + ":" + newStart + "-" + newEnd + "-" + newDirection;
								} else {
									finalText += "," + newChr + ":" + newStart + "-" + newEnd + "-" + newDirection;
								}
								found = true;
								
								
							} else {							
								if (i == 0) {
									finalText += newChr + ":" + newStart + "-" + newEnd + "-" + newDirection;
								} else {
									finalText += "," + newChr + ":" + newStart + "-" + newEnd + "-" + newDirection;
								}						
							}
							
						}
						if (!found) {
							
							finalText += "," + chr + ":" + start + "-" + end + "-" + direction;
							System.out.println(finalText);;
						}
						//String stuff = text + "," + chr + ":" + start + "-" + end + "(" + direction + ")";
						//gtfGene.put(geneName, geneName + "\t" + chr + "\t" + newStart + "\t" + newEnd + "\t" + direction);
						//System.out.println(finalText);						
						gtfGene.put(geneName, finalText);
					} else {
						//gtfGene.put(geneName, geneName + "\t" + chr + ":" + start + "-" + end + "(" + direction + ")");
						gtfGene.put(geneName, geneName + "\t" + chr + ":" + start + "-" + end + "-" + direction);
					}
					mapping.put(geneName, ensembl);
					
				}
			}
			in.close();
			
			String[] exps = {"Exp1", "Exp2", "Exp3"};
			String[] types = {"Even", "Odd"};
			
			for (String exp: exps) {
				for (String type: types) {
											
					String outputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "_GREAT_MetaInfo_Genes.txt";
					FileWriter fwriter = new FileWriter(outputFile);
					BufferedWriter out = new BufferedWriter(fwriter);			

					out.write("MACS_ID\tMACS_Coordinate\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\n");
					
					
					String outputFile2 = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "5k_GREAT_MetaInfo_Genes.txt";
					FileWriter fwriter2 = new FileWriter(outputFile2);
					BufferedWriter out2 = new BufferedWriter(fwriter2);			
					out2.write("MACS_ID\tMACS_Coordinate\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\tEnsemblID\tGeneID\tGeneCoordinate\tMACS_Peak_Distance_To_GeneTSS\n");
					
					HashMap macsList = new HashMap();
					inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "_summits.bed";
				    fstream = new FileInputStream(inputFile);
					din = new DataInputStream(fstream); 
					in = new BufferedReader(new InputStreamReader(din));
					while (in.ready()) {
						String str = in.readLine();
						String[] split = str.split("\t");
						macsList.put(split[3], split[3] + "\t" + split[0] + ":" + split[1] + "-" + split[2]);
					}
					in.close();
					
					
					inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "_GREAT_Genes.txt";
				    fstream = new FileInputStream(inputFile);
					din = new DataInputStream(fstream); 
					in = new BufferedReader(new InputStreamReader(din));
					while (in.ready()) {
						String str = in.readLine();
						String[] split = str.split("\t");
						if (split.length > 1) {
							String macs = split[0];
							//System.out.println(str);
							String[] genes = split[1].split(", ");
							out.write(macsList.get(macs) + "");
							//out2.write(macsList.get(macs) + "");
							String text = macsList.get(macs) + "";
							boolean dist5000 = false;
							for (int i = 0; i < genes.length; i++) {
								if (!genes[i].equals("NONE")) {
									String[] split2 = genes[i].split(" ");
									String geneName = split2[0];
									//System.out.println(genes[i]);
									//System.out.println(split2[1].replaceAll("\\(", "").replaceAll("\\)", ""));
									int distance = new Integer(split2[1].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", ""));
									out.write("\t" + mapping.get(geneName) + "\t" + gtfGene.get(geneName) + "\t" + distance);
									if (distance >= -5000 && distance < 5000) {
										text += "\t" + mapping.get(geneName) + "\t" + gtfGene.get(geneName) + "\t" + distance;
										dist5000 = true;
										//out2.write("\t" + gtfGene.get(geneName) + "\t" + distance);	
									}
								}
							}
							out.write("\n");
							if (dist5000) {
								out2.write(text + "\n");
							}
							//out2.write("\n");
						}
					}
					in.close();
					out.close();
					out2.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static HashMap getGeneList() {
		HashMap map = new HashMap();
		try {
			
			String[] exps = {"Exp1", "Exp2", "Exp3"};
			String[] types = {"Even", "Odd"};
			
			for (String exp: exps) {
				for (String type: types) {
											
					
					String inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "_GREAT_Genes.txt";
				    FileInputStream fstream = new FileInputStream(inputFile);
					DataInputStream din = new DataInputStream(fstream); 
					BufferedReader in = new BufferedReader(new InputStreamReader(din));
					while (in.ready()) {
						String str = in.readLine();
						String[] split = str.split("\t");
						if (split.length > 1) {
							String macs = split[0];
							//System.out.println(str);
							String[] genes = split[1].split(", ");					
							for (int i = 0; i < genes.length; i++) {
								if (!genes[i].equals("NONE")) {
									String[] split2 = genes[i].split(" ");
									String geneName = split2[0];
									int distance = new Integer(split2[1].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", ""));
									
									map.put(geneName, geneName);
									
								}
							}
							
						}
					}
					in.close();
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public static boolean overlap(int a1, int a2, int q1, int q2) {
		if (Math.abs(q1 - a1) < 100000) {
			return true;
		}
		if (Math.abs(q2 - q2) < 100000) {
			return true;
		}
		/*if (a1 <= q1 && q1 <= a2) {
			return true;
		}
		if (a1 <= q2 && q2 <= a2) {
			return true;
		}
		if (q1 <= a1 && a1 <= q2) {
			return true;
		}
		if (q1 <= a2 && a2 <= q2) {
			return true;
		}*/
		return false;
	}
	public static String grabMeta(String text, String id) {
		String returnval = "";
		if (text.contains(id)) {
			String val = text.split(id)[1].split(";")[0].trim();
			val = val.replaceAll("\"", "");
			val.trim();
			returnval = val;				
		}
		return returnval;
	}
}
