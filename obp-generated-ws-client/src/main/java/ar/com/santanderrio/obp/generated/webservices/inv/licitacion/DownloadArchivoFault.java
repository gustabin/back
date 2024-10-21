
package ar.com.santanderrio.obp.generated.webservices.inv.licitacion;

import javax.xml.ws.WebFault;

/**
 * This class was generated by Apache CXF 3.0.0 2017-09-13T13:02:25.474-03:00
 * Generated source version: 3.0.0
 */

@WebFault(name = "LicitacionesServiceFault", targetNamespace = "http://schemas.datacontract.org/2004/07/Isban.BEL.ServiceContracts")
public class DownloadArchivoFault extends Exception {
    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -5888835879002400850L;
    private ar.com.santanderrio.obp.generated.webservices.inv.licitacion.LicitacionesServiceFault licitacionesServiceFault;

    public DownloadArchivoFault() {
        super();
    }

    public DownloadArchivoFault(String message) {
        super(message);
    }

    public DownloadArchivoFault(String message, Throwable cause) {
        super(message, cause);
    }

    public DownloadArchivoFault(String message,
            ar.com.santanderrio.obp.generated.webservices.inv.licitacion.LicitacionesServiceFault licitacionesServiceFault) {
        super(message);
        this.licitacionesServiceFault = licitacionesServiceFault;
    }

    public DownloadArchivoFault(String message,
            ar.com.santanderrio.obp.generated.webservices.inv.licitacion.LicitacionesServiceFault licitacionesServiceFault,
            Throwable cause) {
        super(message, cause);
        this.licitacionesServiceFault = licitacionesServiceFault;
    }

    public ar.com.santanderrio.obp.generated.webservices.inv.licitacion.LicitacionesServiceFault getFaultInfo() {
        return this.licitacionesServiceFault;
    }
}
