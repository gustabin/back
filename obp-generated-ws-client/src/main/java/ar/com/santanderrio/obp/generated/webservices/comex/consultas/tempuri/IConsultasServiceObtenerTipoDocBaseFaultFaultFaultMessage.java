
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.6
 * 2019-03-22T17:33:48.105-03:00
 * Generated source version: 2.7.6
 */

@WebFault(name = "BaseFault", targetNamespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts")
public class IConsultasServiceObtenerTipoDocBaseFaultFaultFaultMessage extends Exception {
    
    private ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.contracts.BaseFault baseFault;

    public IConsultasServiceObtenerTipoDocBaseFaultFaultFaultMessage() {
        super();
    }
    
    public IConsultasServiceObtenerTipoDocBaseFaultFaultFaultMessage(String message) {
        super(message);
    }
    
    public IConsultasServiceObtenerTipoDocBaseFaultFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public IConsultasServiceObtenerTipoDocBaseFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.contracts.BaseFault baseFault) {
        super(message);
        this.baseFault = baseFault;
    }

    public IConsultasServiceObtenerTipoDocBaseFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.contracts.BaseFault baseFault, Throwable cause) {
        super(message, cause);
        this.baseFault = baseFault;
    }

    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.contracts.BaseFault getFaultInfo() {
        return this.baseFault;
    }
}
