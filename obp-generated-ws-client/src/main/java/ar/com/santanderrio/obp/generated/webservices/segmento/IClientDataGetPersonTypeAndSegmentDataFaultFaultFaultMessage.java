
package ar.com.santanderrio.obp.generated.webservices.segmento;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.6
 * 2020-10-19T17:53:19.470-03:00
 * Generated source version: 2.7.6
 */

@WebFault(name = "DataFault", targetNamespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts")
public class IClientDataGetPersonTypeAndSegmentDataFaultFaultFaultMessage extends Exception {
    
    private ar.com.santanderrio.obp.generated.webservices.segmento.DataFault dataFault;

    public IClientDataGetPersonTypeAndSegmentDataFaultFaultFaultMessage() {
        super();
    }
    
    public IClientDataGetPersonTypeAndSegmentDataFaultFaultFaultMessage(String message) {
        super(message);
    }
    
    public IClientDataGetPersonTypeAndSegmentDataFaultFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public IClientDataGetPersonTypeAndSegmentDataFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.segmento.DataFault dataFault) {
        super(message);
        this.dataFault = dataFault;
    }

    public IClientDataGetPersonTypeAndSegmentDataFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.segmento.DataFault dataFault, Throwable cause) {
        super(message, cause);
        this.dataFault = dataFault;
    }

    public ar.com.santanderrio.obp.generated.webservices.segmento.DataFault getFaultInfo() {
        return this.dataFault;
    }
}
