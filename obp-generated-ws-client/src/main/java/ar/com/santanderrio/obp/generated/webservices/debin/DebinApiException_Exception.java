
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.6
 * 2019-11-22T15:22:45.065-03:00
 * Generated source version: 2.7.6
 */

@WebFault(name = "DebinApiException", targetNamespace = "http://webservices.api.debin.prismamp.com/")
public class DebinApiException_Exception extends Exception {
    
    private ar.com.santanderrio.obp.generated.webservices.debin.DebinApiException debinApiException;

    public DebinApiException_Exception() {
        super();
    }
    
    public DebinApiException_Exception(String message) {
        super(message);
    }
    
    public DebinApiException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public DebinApiException_Exception(String message, ar.com.santanderrio.obp.generated.webservices.debin.DebinApiException debinApiException) {
        super(message);
        this.debinApiException = debinApiException;
    }

    public DebinApiException_Exception(String message, ar.com.santanderrio.obp.generated.webservices.debin.DebinApiException debinApiException, Throwable cause) {
        super(message, cause);
        this.debinApiException = debinApiException;
    }

    public ar.com.santanderrio.obp.generated.webservices.debin.DebinApiException getFaultInfo() {
        return this.debinApiException;
    }
}
