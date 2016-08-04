package au.edu.unsw.soacourse.topdown;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ConvertData {
	public void Convert(String inFilename, String outFilename, String target) throws IOException, ConvertMarketFaultMsg {
        String inString = "";
        float rate = 0;
        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + "today.csv");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((inString = reader.readLine()) != null) {
        	String tmp[] = inString.split(",");
        	if (tmp[1].equals("N/A")) {
        		ServiceFaultType fault = new ServiceFaultType();
    			fault.setErrcode("ERR_CURRENCY");
    			fault.setErrtext("Target currency is not available");
    			throw new ConvertMarketFaultMsg("Target currency is not available", fault);
        	}
        	rate = new Float(tmp[1]); 
        }
        reader.close();
        File inFile = new File(System.getProperty("java.io.tmpdir") + File.separator + inFilename); 
        File outFile = new File(System.getProperty("java.io.tmpdir") + File.separator + outFilename); 
        reader = new BufferedReader(new FileReader(inFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        String pattern = "[0-9]+\\.[0-9]+";
        int flag = 0;
            while((inString = reader.readLine())!= null) {
            	if (flag == 0) {
            		String comment = inString;
            		String[] s = inString.split(", ");
            		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
                    String curr_date = df.format(new Date());
                    comment = comment.replace(s[5], target);
                    comment = comment.replaceAll("[0-9]{2}-[0-9]{2}-[0-9]{4}", curr_date);
                    writer.write(comment);
                    //System.out.println(comment);
            		flag = 1;
            	}
            	else {
	            	String[] str = inString.split(",");
	            	for (int i = 0;i < str.length;i++) {
	            		if (Pattern.matches(pattern, str[i])) {
	            			double tmp1 = new Double(str[i]);
	            			tmp1 = tmp1 * rate;
	            			tmp1 = Math.round(tmp1 * 100); 
	            			double tmp2 = tmp1 / 100.0;
	            			str[i] = Double.toString(tmp2);
	            		}
	            		writer.write(str[i]);
	            		if (i < str.length - 1) {
	            			writer.write(",");
	            		}
	            	}
            	}
                writer.newLine();
            }
            reader.close();
            writer.close();
	}
}