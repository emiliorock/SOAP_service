package au.edu.unsw.soacourse.topdown;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2016-04-17T16:50:40.191+10:00
 * Generated source version: 3.0.4
 * 
 */
@WebService(targetNamespace = "http://topdown.soacourse.unsw.edu.au", name = "TopDownSimpleService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface TopDownSimpleService {

    @WebMethod(action = "http://topdown.soacourse.unsw.edu.au/downloadFile")
    @WebResult(name = "downloadFileResponse", targetNamespace = "http://topdown.soacourse.unsw.edu.au", partName = "parameters")
    public DownloadFileResponse downloadFile(
        @WebParam(partName = "parameters", name = "downloadFileRequest", targetNamespace = "http://topdown.soacourse.unsw.edu.au")
        DownloadFileRequest parameters
    ) throws DownloadFileFaultMsg;

    @WebMethod(action = "http://topdown.soacourse.unsw.edu.au/convertMarketData")
    @WebResult(name = "convertMarketDataResponse", targetNamespace = "http://topdown.soacourse.unsw.edu.au", partName = "parameters")
    public ConvertMarketDataResponse convertMarketData(
        @WebParam(partName = "parameters", name = "convertMarketDataRequest", targetNamespace = "http://topdown.soacourse.unsw.edu.au")
        ConvertMarketDataRequest parameters
    ) throws ConvertMarketFaultMsg, ConvertMarketFaultMsg;

    @WebMethod(action = "http://topdown.soacourse.unsw.edu.au/importMarketData")
    @WebResult(name = "importMarketDataResponse", targetNamespace = "http://topdown.soacourse.unsw.edu.au", partName = "parameters")
    public ImportMarketDataResponse importMarketData(
        @WebParam(partName = "parameters", name = "importMarketDataRequest", targetNamespace = "http://topdown.soacourse.unsw.edu.au")
        ImportMarketDataRequest parameters
    ) throws ImportMarketFaultMsg, ImportMarketFaultMsg, ImportMarketFaultMsg, ImportMarketFaultMsg;

    @WebMethod(action = "http://topdown.soacourse.unsw.edu.au/showMarketData")
    @WebResult(name = "showMarketDataResponse", targetNamespace = "http://topdown.soacourse.unsw.edu.au", partName = "parameters")
    public ShowMarketDataResponse showMarketData(
        @WebParam(partName = "parameters", name = "showMarketDataRequest", targetNamespace = "http://topdown.soacourse.unsw.edu.au")
        ShowMarketDataRequest parameters
    ) throws ShowMarketFaultMsg, ShowMarketFaultMsg;
}
