package FilterPeaks;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class FilterPeakBad {

	
	public static void main(String[] args) {
		
		try {
			String fileName = args[0];
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 			
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (split.length > 5) {
					if (!split[1].equals("start")) {
						String chr = split[0];
						int start = new Integer(split[1]);
						int end = new Integer(split[2]);
						double pval = new Double(split[6]);					
						if (pval > 50) {
							double[] coverage24 = getCoverage(chr, "24", start, end);
							double[] coverage25 = getCoverage(chr, "25", start, end);
							double correlation = getPearsonCorrelation(coverage24, coverage25);
							if (correlation > 0.3) {
								// need to update based on the file changes in minglab
							}
							
						}
					}
				}
			}
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static double[] getCoverage(String chr, String id, int start, int end) {
		double[] values = new double[end - start + 1];
		try {
			String fileName = "chirp" + id + "vs26_MACS_wiggle/treat/chirp" + id + "vs26_treat_afterfiting_" + chr + ".wig";
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 			
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			int v_index = 0;
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (split.length == 2) {
					int index = new Integer(split[0]);
					if (start <= index && index <= end) {
						values[v_index] = new Integer(split[1]);
						v_index++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return values;
	}
	
    public static double getPearsonCorrelation(double[] scores1,double[] scores2){
        double result = 0;
        double sum_sq_x = 0;
        double sum_sq_y = 0;
        double sum_coproduct = 0;
        double mean_x = scores1[0];
        double mean_y = scores2[0];
        for(int i=2;i<scores1.length+1;i+=1){
            double sweep =Double.valueOf(i-1)/i;
            double delta_x = scores1[i-1]-mean_x;
            double delta_y = scores2[i-1]-mean_y;
            sum_sq_x += delta_x * delta_x * sweep;
            sum_sq_y += delta_y * delta_y * sweep;
            sum_coproduct += delta_x * delta_y * sweep;
            mean_x += delta_x / i;
            mean_y += delta_y / i;
        }
        double pop_sd_x = (double) Math.sqrt(sum_sq_x/scores1.length);
        double pop_sd_y = (double) Math.sqrt(sum_sq_y/scores1.length);
        double cov_x_y = sum_coproduct / scores1.length;
        result = cov_x_y / (pop_sd_x*pop_sd_y);
        return result;
    }

}
