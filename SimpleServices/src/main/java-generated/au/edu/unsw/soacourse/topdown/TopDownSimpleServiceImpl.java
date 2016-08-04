package au.edu.unsw.soacourse.topdown;

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.jws.WebService;
import javax.xml.bind.JAXBElement;

@WebService(endpointInterface = "au.edu.unsw.soacourse.topdown.TopDownSimpleService")
public class TopDownSimpleServiceImpl implements TopDownSimpleService {

	// TODO: create an ObjectFactory

	public ImportMarketDataResponse importMarketData(ImportMarketDataRequest req) throws ImportMarketFaultMsg {

		StringBuilder sbf = new StringBuilder();
		ObjectFactory factory = new ObjectFactory();
		ImportMarketDataResponse res = factory.createImportMarketDataResponse();
		ServiceFaultType fault = new ServiceFaultType();

		// check null value
		if (req.getSec() == null || req.getStartDate() == null || req.getEndDate() == null
				|| req.getDataSource() == null) {
			fault.setErrcode("ERR_NULL_VALUE");
			fault.setErrtext("One of the input is null");
			throw new ImportMarketFaultMsg("One of the input is null", fault);
		}

		// check the security code length
		if (req.getSec().length() < 3 || req.getSec().length() > 6) {
			fault.setErrcode("ERR_SEC");
			fault.setErrtext("SEC code should be 3 to 6 characters long");
			throw new ImportMarketFaultMsg("SEC code should be 3 to 6 characters long", fault);
		}

		// check the date format
		String pattern = "[0-9]{2}-[0-9]{2}-[0-9]{4}";
		if (!Pattern.matches(pattern, req.getStartDate()) || !Pattern.matches(pattern, req.getEndDate())) {
			fault.setErrcode("ERR_DATE");
			fault.setErrtext("Date format should be DD-MM-YYYY");
			throw new ImportMarketFaultMsg("Date format should be DD-MM-YYYY", fault);
		}

		// check the range format
		if (!(req.getDataSource().equals("d") || req.getDataSource().equals("w") || req.getDataSource().equals("m")
				|| req.getDataSource().equals("v"))) {
			fault.setErrcode("ERR_RANGE");
			fault.setErrtext("Range format should be d/w/m/v");
			throw new ImportMarketFaultMsg("Range format should be d/w/m/v", fault);
		}

		// input format is correct
		else {

			// check start date before end date
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
			try {
				Date date1 = sdf.parse(req.getStartDate());
				Date date2 = sdf.parse(req.getEndDate());
				if (date2.before(date1)) {
					fault.setErrcode("ERR_DATE");
					fault.setErrtext("Start date should before end date");
					throw new ImportMarketFaultMsg("Start date should before end date", fault);
				}
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			String s = req.getSec();
			// start month
			String a = "";
			// start day
			String b = "";
			// start year
			String c = "";
			// end month
			String d = "";
			// end day
			String e = "";
			// end year
			String f = "";
			String g = req.getDataSource();// date range option
			String[] startDate = req.getStartDate().split("-");
			String[] endDate = req.getEndDate().split("-");

			if (startDate.length != 0) {
				a = Integer.toString(Integer.valueOf(startDate[1]) - 1);
				b = startDate[0];
				c = startDate[2];
			}
			if (endDate.length != 0) {
				d = Integer.toString(Integer.valueOf(endDate[1]) - 1);
				e = endDate[0];
				f = endDate[2];
			}
			String url = "http://real-chart.finance.yahoo.com/table.csv?s=" + s + "&a=" + a + "&b=" + b + "&c=" + c
					+ "&d=" + d + "&e=" + e + "&f=" + f + "&g=" + g + "&ignore=.csv";

			DownloadFromURL dfile = new DownloadFromURL();
			String filename = dfile.getRandomFilename(7);
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
			String curr_date = df.format(new Date());
			String comment = "# Sec, " + s + ", dOption, " + g + ", Currency, AUD, Created, " + curr_date + "\n";
			try {
				dfile.downLoadFromUrl(url, filename, comment, System.getProperty("java.io.tmpdir"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String[] rdata = filename.split("\\.");
			res.returnData = rdata[0];
			return res;
		}
	}

	public DownloadFileResponse downloadFile(DownloadFileRequest req) throws DownloadFileFaultMsg {

		// TODO: create and set ImportMarketResponse object and return the
		// following as response data
		String str = req.eventSetId;
		String filename = str + ".csv";

		// check if the file exists
		File filepath = new File(System.getProperty("java.io.tmpdir") + File.separator + filename);
		if (!filepath.exists()) {
			ServiceFaultType fault = new ServiceFaultType();
			fault.setErrcode("ERR_EVENTSET_ID");
			fault.setErrtext("EventSetId not exist");
			throw new DownloadFileFaultMsg("EventSetId not exist", fault);
		}

		UploadToURL upfile = new UploadToURL();
		try {
			upfile.uploadFile(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ObjectFactory factory = new ObjectFactory();
		DownloadFileResponse res = factory.createDownloadFileResponse();
		res.returnData = "http://localhost:8080/EventSetDownloads/" + filename;
		return res;
	}

	@Override
	public ConvertMarketDataResponse convertMarketData(ConvertMarketDataRequest req) throws ConvertMarketFaultMsg {

		String inFile = req.getEventSetId() + ".csv";
		String targetCurrency = req.getTargetCurrency();

		// check whether the file exists
		File filepath = new File(System.getProperty("java.io.tmpdir") + File.separator + inFile);
		if (!filepath.exists()) {
			ServiceFaultType fault = new ServiceFaultType();
			fault.setErrcode("ERR_EVENTSET_ID");
			fault.setErrtext("EventSetId not exist");
			throw new ConvertMarketFaultMsg("EventSetId not exist", fault);
		}

		String url = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1&s=AUD" + targetCurrency + "=X";
		DownloadFromURL dfile = new DownloadFromURL();

		try {
			dfile.downLoadFromUrl(url, "today.csv", "", System.getProperty("java.io.tmpdir"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ImportMarketFaultMsg e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ConvertData cdata = new ConvertData();
		String outFile = dfile.getRandomFilename(7);
		try {
			cdata.Convert(inFile, outFile, req.targetCurrency);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ObjectFactory factory = new ObjectFactory();
		ConvertMarketDataResponse res = factory.createConvertMarketDataResponse();
		String[] rdata = outFile.split("\\.");
		res.returnData = rdata[0];
		return res;
	}

	@Override
	public ShowMarketDataResponse showMarketData(ShowMarketDataRequest req) throws ShowMarketFaultMsg {
		String file1 = req.getEventSetId1() + ".csv";
		String file2 = req.getEventSetId2() + ".csv";

		// check whether the file exists
		File filepath1 = new File(System.getProperty("java.io.tmpdir") + File.separator + file1);
		File filepath2 = new File(System.getProperty("java.io.tmpdir") + File.separator + file2);
		if (!filepath1.exists() || !filepath2.exists()) {
			ServiceFaultType fault = new ServiceFaultType();
			fault.setErrcode("ERR_EVENTSET_ID");
			fault.setErrtext("EventSetId not exist");
			throw new ShowMarketFaultMsg("EventSetId not exist", fault);
		}

		ShowMarketData sdata = new ShowMarketData();
		try {
			sdata.checkFiles(file1, file2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectFactory factory = new ObjectFactory();
		ShowMarketDataResponse res = factory.createShowMarketDataResponse();
		res.returnData = "http://localhost:8080/EventSetCharts/view-now.html";
		return res;
	}

}