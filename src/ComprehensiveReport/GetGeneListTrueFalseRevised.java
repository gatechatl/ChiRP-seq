package ComprehensiveReport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

import java.util.Iterator;

public class GetGeneListTrueFalseRevised {

	public static void main(String[] args) {
		HashMap comprehensive = getGeneList();
		HashMap mapping = new HashMap();
		HashMap direction = new HashMap();
		HashMap lowest = new HashMap();
		HashMap highest = new HashMap();
		try {
			
			
			String inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\GTFFile\\Homo_sapiens.GRCh37.69.gtf";
		    FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));

			int num = 0;
			while (in.ready()) {			
				String str = in.readLine();
				String[] split = str.split("\t");
				String geneName = grabMeta(split[8], "gene_name");
				String ensembl = grabMeta(split[8], "gene_id");
				direction.put(geneName, split[6]);
				mapping.put(geneName, ensembl);
				int low = new Integer(3);
				int high = new Integer(4);
				if (lowest.containsKey(geneName)) {
					int oldLow = (Integer)lowest.get(geneName);
					if (oldLow > low) {
						lowest.put(geneName, low);
					}
				} else {
					lowest.put(geneName, low);
				}
				
				if (highest.containsKey(geneName)) {
					int highLow = (Integer)highest.get(geneName);
					if (highLow < high) {
						highest.put(geneName, high);
					}
				} else {
					highest.put(geneName, high);
				}
			}
			in.close();
					
			String[] exps = {"Exp1", "Exp2", "Exp3"};
			String[] types = {"Even", "Odd"};
			HashMap Exp1_Even = new HashMap();
			HashMap Exp1_Odd = new HashMap();
			HashMap Exp2_Even = new HashMap();
			HashMap Exp2_Odd = new HashMap();
			HashMap Exp3_Even = new HashMap();
			HashMap Exp3_Odd = new HashMap();

			
			
			String exp = "Exp1";
			String type = "Even";
			
			HashMap Exp1_Even_Coord = new HashMap();
			HashMap Exp1_Odd_Coord = new HashMap();
			HashMap Exp2_Even_Coord = new HashMap();
			HashMap Exp2_Odd_Coord = new HashMap();
			HashMap Exp3_Even_Coord = new HashMap();
			HashMap Exp3_Odd_Coord = new HashMap();
			
			inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "_GREAT_MetaInfo_Genes.txt";
		    fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				
				Exp1_Even_Coord.put(split[0], split[1].split(":")[1].split("-")[0]);
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
					for (int i = 0; i < genes.length; i++) {
						if (!genes[i].equals("NONE")) {
							String[] split2 = genes[i].split(" ");
							String geneName = split2[0];
							int distance = new Integer(split2[1].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", ""));
							
							//if (Math.abs(distance) <= 5000) {
							if (Math.abs(distance) >= -1) {
								
								String coord = (String)Exp1_Even_Coord.get(macs);
								String direct = (String)direction.get(geneName);
								if (direction.containsKey(geneName)) {
								if (direct.equals("+")) {
									if (new Integer(coord) < (Integer)lowest.get(geneName)) {
										Exp1_Even.put(geneName, geneName);
									}
								} else {
									if (new Integer(coord) > (Integer)highest.get(geneName)) {
										Exp1_Even.put(geneName, geneName);
									}									
								}
								
								}
							}														
						}
					}
					
				}
			}
			in.close();						
			
			exp = "Exp1";
			type = "Odd";			
			inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "_GREAT_MetaInfo_Genes.txt";
		    fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				Exp1_Odd_Coord.put(split[0], split[1].split(":")[1].split("-")[0]);
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
					for (int i = 0; i < genes.length; i++) {
						if (!genes[i].equals("NONE")) {
							String[] split2 = genes[i].split(" ");
							String geneName = split2[0];
							int distance = new Integer(split2[1].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", ""));
							//if (Math.abs(distance) <= 5000) {
							if (Math.abs(distance) >= -1) {
								
								String coord = (String)Exp1_Odd_Coord.get(macs);
								String direct = (String)direction.get(geneName);
								if (direction.containsKey(geneName)) {
								if (direct.equals("+")) {
									if (new Integer(coord) < (Integer)lowest.get(geneName)) {
										Exp1_Odd.put(geneName, geneName);
									}
								} else {
									if (new Integer(coord) > (Integer)highest.get(geneName)) {
										Exp1_Odd.put(geneName, geneName);
									}									
								}
								}
								//Exp1_Odd.put(geneName, geneName);
							}
							
							
						}
					}
					
				}
			}
			in.close();

			
			exp = "Exp2";
			type = "Even";
			
			inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "_GREAT_MetaInfo_Genes.txt";
		    fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				Exp2_Even_Coord.put(split[0], split[1].split(":")[1].split("-")[0]);
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
					for (int i = 0; i < genes.length; i++) {
						if (!genes[i].equals("NONE")) {
							String[] split2 = genes[i].split(" ");
							String geneName = split2[0];
							int distance = new Integer(split2[1].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", ""));
							if (Math.abs(distance) >= -1) {
								String coord = (String)Exp2_Even_Coord.get(macs);
								if (direction.containsKey(geneName)) {
								String direct = (String)direction.get(geneName);
								if (direct.equals("+")) {
									if (new Integer(coord) < (Integer)lowest.get(geneName)) {
										Exp2_Even.put(geneName, geneName);
									}
								} else {
									if (new Integer(coord) > (Integer)highest.get(geneName)) {
										Exp2_Even.put(geneName, geneName);
									}									
								}
								}
								//Exp2_Even.put(geneName, geneName);
							}
							
							
						}
					}
					
				}
			}
			in.close();
			

			exp = "Exp2";
			type = "Odd";
			
			inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "_GREAT_MetaInfo_Genes.txt";
		    fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				Exp2_Odd_Coord.put(split[0], split[1].split(":")[1].split("-")[0]);
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
					for (int i = 0; i < genes.length; i++) {
						if (!genes[i].equals("NONE")) {
							String[] split2 = genes[i].split(" ");
							String geneName = split2[0];
							int distance = new Integer(split2[1].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", ""));
							//if (Math.abs(distance) <= 5000) {
							if (Math.abs(distance) >= -1) {
								

								String coord = (String)Exp2_Odd_Coord.get(macs);
								String direct = (String)direction.get(geneName);
								if (direction.containsKey(geneName)) {
								if (direct.equals("+")) {
									if (new Integer(coord) < (Integer)lowest.get(geneName)) {
										Exp2_Odd.put(geneName, geneName);
									}
								} else {
									if (new Integer(coord) > (Integer)highest.get(geneName)) {
										Exp2_Odd.put(geneName, geneName);
									}									
								}
								
								}

																
								//Exp2_Odd.put(geneName, geneName);
							}
							
							
						}
					}
					
				}
			}
			in.close();

			
			
			exp = "Exp3";
			type = "Even";
			
			inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "_GREAT_MetaInfo_Genes.txt";
		    fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				Exp3_Even_Coord.put(split[0], split[1].split(":")[1].split("-")[0]);
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
					for (int i = 0; i < genes.length; i++) {
						if (!genes[i].equals("NONE")) {
							String[] split2 = genes[i].split(" ");
							String geneName = split2[0];
							int distance = new Integer(split2[1].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", ""));
							
							//if (Math.abs(distance) <= 5000) {
							if (Math.abs(distance) >= -1) {
								String coord = (String)Exp3_Even_Coord.get(macs);
								if (direction.containsKey(geneName)) {
								String direct = (String)direction.get(geneName);
								if (direct.equals("+")) {
									if (new Integer(coord) < (Integer)lowest.get(geneName)) {
										Exp3_Even.put(geneName, geneName);
									}
								} else {
									if (new Integer(coord) > (Integer)highest.get(geneName)) {
										Exp3_Even.put(geneName, geneName);
									}									
								}
								
								}
								//Exp3_Even.put(geneName, geneName);
							}
							
							
						}
					}
					
				}
			}
			in.close();
			

			exp = "Exp3";
			type = "Odd";
			
			inputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\" + exp + "\\" + exp + "_" + type + "_GREAT_MetaInfo_Genes.txt";
		    fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				Exp3_Odd_Coord.put(split[0], split[1].split(":")[1].split("-")[0]);
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
					for (int i = 0; i < genes.length; i++) {
						if (!genes[i].equals("NONE")) {
							String[] split2 = genes[i].split(" ");
							String geneName = split2[0];
							int distance = new Integer(split2[1].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\+", ""));
							//if (Math.abs(distance) <= 5000) {
							if (Math.abs(distance) >= -1) {
								
								String coord = (String)Exp3_Odd_Coord.get(macs);
								if (direction.containsKey(geneName)) {
								String direct = (String)direction.get(geneName);
								if (direct.equals("+")) {
									if (new Integer(coord) < (Integer)lowest.get(geneName)) {
										Exp3_Odd.put(geneName, geneName);
									}
								} else {
									if (new Integer(coord) > (Integer)highest.get(geneName)) {
										Exp3_Odd.put(geneName, geneName);
									}									
								}
								}
								//Exp3_Odd.put(geneName, geneName);
							}														
						}
					}					
				}
			}
			in.close();
			
			String outputFile = "C:\\School Notes\\ChirpSeq\\BED Files\\ComprehensiveGeneList.txt";
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);			

			//out.write("GeneName\tEnsemblID\tExp1_Even\tExp1_Odd\tExp2_Even\tExp2_Odd\tExp3_Even\tExp3_Odd\n");
			out.write("GeneName\tEnsemblID\tExp1\tExp2\tExp3\n");
			Iterator itr = comprehensive.keySet().iterator();
			while (itr.hasNext()) {
				int count = 0;
				String key = (String)itr.next();
				
				out.write(key + "\t" + mapping.get(key));
				if (Exp1_Even.containsKey(key) && Exp1_Odd.containsKey(key)) {
					out.write("\ttrue");
					count++;
				} else {
					out.write("\tfalse");
				}

				

				if (Exp2_Even.containsKey(key) && Exp2_Odd.containsKey(key)) {
					out.write("\ttrue");
					count++;
				} else {
					out.write("\tfalse");
				}

				
				if (Exp3_Even.containsKey(key) && Exp3_Odd.containsKey(key)) {
					out.write("\ttrue");
					count++;
				} else {
					out.write("\tfalse");
				}

				out.write("\t" + count + "\n");
			}
			out.close();
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
		
									//if (Math.abs(distance) >= 5000) {
										map.put(geneName, geneName);
									//}
									
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
