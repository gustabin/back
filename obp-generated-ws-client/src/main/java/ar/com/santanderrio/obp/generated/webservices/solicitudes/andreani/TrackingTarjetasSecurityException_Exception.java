
package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import javax.xml.ws.WebFault;

@WebFault(name = "fault", targetNamespace = "http://services.andreani.wsdeltarj")
public class TrackingTarjetasSecurityException_Exception extends Exception {

    private static final long serialVersionUID = 7771334702155281111L;
    
    private TrackingTarjetasSecurityException fault;

    public TrackingTarjetasSecurityException_Exception() {
        super();
    }
    
    public TrackingTarjetasSecurityException_Exception(String message) {
        super(message);
    }
    
    public TrackingTarjetasSecurityException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public TrackingTarjetasSecurityException_Exception(String message, TrackingTarjetasSecurityException fault) {
        super(message);
        this.fault = fault;
    }

    public TrackingTarjetasSecurityException_Exception(String message, TrackingTarjetasSecurityException fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public TrackingTarjetasSecurityException getFaultInfo() {
        return this.fault;
    }
}
