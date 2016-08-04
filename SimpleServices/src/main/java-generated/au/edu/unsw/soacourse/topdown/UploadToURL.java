package au.edu.unsw.soacourse.topdown;

import java.io.*;

public class UploadToURL {
	
	public void uploadFile(String filename) throws IOException {
		File infile = new File(System.getProperty("java.io.tmpdir") + File.separator + filename); 
		//System.out.println(System.getProperty("catalina.home"));;
		File dir = new File (System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "EventSetDownloads");
		if(!dir.exists()){  
            dir.mkdir();  
        }
		File outfile = new File(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "EventSetDownloads" + File.separator + filename);
		BufferedReader reader = new BufferedReader(new FileReader(infile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));
		String inString = "";
		while((inString = reader.readLine())!= null) {
			writer.write(inString + "\n");
		}
		reader.close();
		writer.close();
//	    // should change the port number
//	    String addr = "http://localhost:8001/EventSetDownloads/" + filename;
//	    try {
//	        URL url = new URL(addr);
//	        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//	        connection.setDoInput(true);
//	        connection.setDoOutput(true);
//	        connection.setRequestMethod("POST");
//	        connection.addRequestProperty("FileName", filename);
//	        connection.setRequestProperty("content-type", "text/html");
//	        BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
//	        
//	        // read local csv file and upload to server
//	        String filePath = System.getProperty("java.io.tmpdir") + filename;
//	        File file = new File(filePath);
//	        FileInputStream inputStream = new FileInputStream(file);
//	        byte[] buffer = new byte[1024];
//	        int len = 0;
//	        while ((len = inputStream.read(buffer)) != -1) {
//	            out.write(buffer, 0, len);
//	        }
//	        out.flush();
//	        inputStream.close();
//	        // URLConnection 
//	        DataInputStream in = new DataInputStream(connection.getInputStream());
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
	}	
}
