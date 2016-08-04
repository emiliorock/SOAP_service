package au.edu.unsw.soacourse.topdown;

import java.io.*;
import java.net.*;
import java.util.Random;

public class DownloadFromURL {
	/**  
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
	 * @throws ImportMarketFaultMsg 
     */  
	public void downLoadFromUrl(String urlStr, String fileName, String comment, String Path) throws IOException, ImportMarketFaultMsg{  
        
		URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
         // set timeout as 3 sec  
        conn.setConnectTimeout(3*1000);  
 
         // check whether the url is valid
        int state = conn.getResponseCode();   
        if (state != 200) {  
        	ServiceFaultType fault = new ServiceFaultType();
        	fault.setErrcode("ERR_INVALID_URL");
    		fault.setErrtext("Invalid URL");
        	throw new ImportMarketFaultMsg("Invalid URL", fault);
        }  
        
        InputStream inputStream = conn.getInputStream();    
        byte[] getData = readInputStream(inputStream, comment);      
        File Dir = new File(Path);  
        if(!Dir.exists()){  
            Dir.mkdir();  
        }  
        File file = new File(Dir + File.separator + fileName);      
        FileOutputStream fos = new FileOutputStream(file);       
        fos.write(getData);   
        if(fos != null){  
            fos.close();    
        }  
        if(inputStream != null){  
            inputStream.close();  
        }  
        System.out.println("info:" + url + " download success");    
    }  
  
    /** 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream, String comment) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
        byte[] data = comment.getBytes();
        bos.write(data, 0, data.length);
        while ((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
    
    public String getRandomFilename(int length) { 
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
        Random random = new Random();     
        StringBuffer sb = new StringBuffer();     
        for (int i = 0; i < length; i++) {     
            int number = random.nextInt(base.length());     
            sb.append(base.charAt(number));     
        }
        sb.append(".csv");
        return sb.toString();     
     }     
}