Student ID: z5013846
Name: Mengxin Huang

1. Run-time environment requirement:
	Apache Maven 3.3.9 
	Java version: 1.7.0_79 
	Eclipse Mars Release 4.5.0 
	Tomcat 7.0

2. External jar: 
	wss4j-2.1.jar
	
3. Initialize the project:
	1). Unzip the SimpleServices zip file
	
	2). Copy wss4j-2.1.jar in SimpleServices\src\others to your Tomcat\lib directory
	
	3). Open eclipse(with administrator), make sure of this otherwise Tomcat won't let you access to files under Tomcat directory
	
	4). Import SimpleServices as an existing Maven project into your eclipse
	
	5). In eclipse, right click on the project name - > Build Path -> Configure Build Path -> Libraries -> Add External JARs -> Go to Tomcat\lib, add wss4j-2.1.jar

	6). Right click on the project, Maven -> Update project

	7). Right click on the project, Run as -> Maven install

	8). Right click on the project, Run as -> Run on Server

	9). On the navigation bar, Run -> Launch Web Services Explorer

	10). Enter http://localhost:8080/SimpleServices/TopDownSimpleService?wsdl, if you are using other port for your Tomcat, replace 8080 with your Tomcat port

	11). Go to WSDL PAGE, now you should see 4 operations.

	12). Start testing.
		**Remember** Each time before you send a request, go to Source(at the up right corner of the operation page) -> <soapenv:Header>. Paste the following code into the textbox, then send the request

	<wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" soapenv:mustUnderstand="1">
     	<wsse:UsernameToken xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd">
        <wsse:Username xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">cse</wsse:Username>
        <wsse:Password xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">cse</wsse:Password>
     </wsse:UsernameToken>
  </wsse:Security>

    13). For downloadFile operation, after you send the request, the output file should be placed at your Tomcat\webapps\EventSetDownloads directory. If you want to download the file via a browser, close eclipse, run your Tomcat outside, and go to http://localhost:8080/EventSetDownloads/***.csv, where *** stands for the eventsetid, the file should be available for downloading.

    14). For showMarketData operation, after you send the request, the output file should be placed at your Tomcat\webapps\EventSetCharts directory. Then, go to http://localhost:8080/EventSetCharts/view-now.html to see the results.


