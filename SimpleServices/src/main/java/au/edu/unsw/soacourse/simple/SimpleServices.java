package au.edu.unsw.soacourse.simple;

import javax.jws.WebService;

@WebService
public interface SimpleServices {
	public String importMarketData(String sec, String startDate, String endDate, String dataSource);
	public String downloadFile(String eventSetID);
}