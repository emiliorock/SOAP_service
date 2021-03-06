
package au.edu.unsw.soacourse.topdown;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.4
 * 2016-04-17T16:50:40.183+10:00
 * Generated source version: 3.0.4
 */

@WebFault(name = "showMarketFault", targetNamespace = "http://topdown.soacourse.unsw.edu.au")
public class ShowMarketFaultMsg extends Exception {
    
    private au.edu.unsw.soacourse.topdown.ServiceFaultType showMarketFault;

    public ShowMarketFaultMsg() {
        super();
    }
    
    public ShowMarketFaultMsg(String message) {
        super(message);
    }
    
    public ShowMarketFaultMsg(String message, Throwable cause) {
        super(message, cause);
    }

    public ShowMarketFaultMsg(String message, au.edu.unsw.soacourse.topdown.ServiceFaultType showMarketFault) {
        super(message);
        this.showMarketFault = showMarketFault;
    }

    public ShowMarketFaultMsg(String message, au.edu.unsw.soacourse.topdown.ServiceFaultType showMarketFault, Throwable cause) {
        super(message, cause);
        this.showMarketFault = showMarketFault;
    }

    public au.edu.unsw.soacourse.topdown.ServiceFaultType getFaultInfo() {
        return this.showMarketFault;
    }
}
