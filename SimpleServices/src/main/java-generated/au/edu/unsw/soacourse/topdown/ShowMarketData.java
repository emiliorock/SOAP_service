package au.edu.unsw.soacourse.topdown;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Pattern;

public class ShowMarketData {
	public void checkFiles (String infile1, String infile2) throws IOException, ShowMarketFaultMsg {
		File file1 = new File(System.getProperty("java.io.tmpdir") + File.separator + infile1); 
		File file2 = new File(System.getProperty("java.io.tmpdir") + File.separator + infile2); 
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
		BufferedReader reader2 = new BufferedReader(new FileReader(file2));
		String inString1 = "";
		String inString2 = "";
		String[] comment1 = null;
		String[] comment2 = null;
		String date1 = "";
		String date2 = "";
		String sum1 = "";
		String sum2 = "";
		int count1 = 0;
		int count2 = 0;
		int flag = 0;
		while ((inString1 = reader1.readLine()) != null) {
			// read the first line
			if (flag == 0) {
				comment1 = inString1.split(", ");
				flag = 1;
			}
			else if (flag == 1) {
				flag = 2;
			}
			else if (flag == 2) {
				String[] tmp = inString1.split(",");
				date1 = date1 + "\"" + tmp[0] + "\"" + ", ";
				sum1 = sum1 + "\"" + tmp[4] + "\"" + ", ";
				count1++;
			}
		}
		reader1.close();
		flag = 0;
		while ((inString2 = reader2.readLine()) != null) {
			// read the first line
			if (flag == 0) {
				comment2 = inString2.split(", ");
				flag = 1;
			}
			else if (flag == 1) {
				flag = 2;
			}
			else if (flag == 2) {
				String[] tmp = inString2.split(",");
				date2 = date2 + "\"" + tmp[0] + "\"" + ", ";
				sum2 = sum2 + "\"" + tmp[4] + "\"" + ", ";
				count2++;
			}
		}
		reader2.close();
		//System.out.println(date1 + " " + sum1);
		//System.out.println(date2 + " " + sum2);
		
		// check whether the 2 files have the same format to show
		if (!comment1[3].equals("m") || !comment2[3].equals("m") || !comment1[5].equals(comment2[5]) || !date1.equals(date2)) {
			ServiceFaultType fault = new ServiceFaultType();
			fault.setErrcode("ERR_NOT_COMPARABLE");
			fault.setErrtext("The two files should have same currency and monthly range");
			throw new ShowMarketFaultMsg("The two files should have same currency and monthly range", fault);
		}
		
		// write to html file
		// first, get the absolute path of the line.html file and Chart.js file
		File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();
		File html_file = new File(catalinaBase, "wtpwebapps/SimpleServices/WEB-INF/line.html");	
		File js_file = new File(catalinaBase, "wtpwebapps/SimpleServices/WEB-INF/Chart.js");
		File dir = new File (System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "EventSetCharts");
		if(!dir.exists()){  
            dir.mkdir();  
        }
		// write the js file
		File js_out_file = new File(System.getProperty("catalina.home") + File.separator + "webapps"
		        + File.separator + "EventSetCharts" + File.separator + "Chart.js");
		File out_file = new File(System.getProperty("catalina.home") + File.separator + "webapps"
        + File.separator + "EventSetCharts" + File.separator + "view-now.html");
		BufferedReader reader = new BufferedReader(new FileReader(js_file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(js_out_file));
		String s = "";
		while ((s = reader.readLine()) != null) {
			writer.write(s + "\n");
		}
		reader.close();
		writer.close();
		reader = new BufferedReader(new FileReader(html_file));
		writer = new BufferedWriter(new FileWriter(out_file));
		s = "";
		String pattern = ".*labels.*";
		String pattern1 = ".*data : \\[X\\].*";
		String pattern2 = ".*data : \\[Z\\].*";
		while ((s = reader.readLine()) != null) {
			if (Pattern.matches(pattern, s)) {
				s = "labels : [" + date1 + "],";
			}
			if (Pattern.matches(pattern1, s)) {
				s = "data : [" + sum1 + "],";
			}
			if (Pattern.matches(pattern2, s)) {
				s = "data : [" + sum2 + "],";
			}
			writer.write(s + "\n");
		}
		reader.close();
		writer.close();
	}
}