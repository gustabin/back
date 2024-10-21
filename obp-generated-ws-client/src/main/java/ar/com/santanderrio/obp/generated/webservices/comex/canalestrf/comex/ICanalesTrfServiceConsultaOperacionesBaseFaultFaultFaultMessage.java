
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.1
 * 2020-02-21T10:55:46.604-03:00
 * Generated source version: 3.0.1
 */

@WebFault(name = "BaseFault", targetNamespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts")
public class ICanalesTrfServiceConsultaOperacionesBaseFaultFaultFaultMessage extends Exception {
    
    private ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.contracts.BaseFault baseFault;

    public ICanalesTrfServiceConsultaOperacionesBaseFaultFaultFaultMessage() {
        super();
    }
    
    public ICanalesTrfServiceConsultaOperacionesBaseFaultFaultFaultMessage(String message) {
        super(message);
    }
    
    public ICanalesTrfServiceConsultaOperacionesBaseFaultFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ICanalesTrfServiceConsultaOperacionesBaseFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.contracts.BaseFault baseFault) {
        super(message);
        this.baseFault = baseFault;
    }

    public ICanalesTrfServiceConsultaOperacionesBaseFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.contracts.BaseFault baseFault, Throwable cause) {
        super(message, cause);
        this.baseFault = baseFault;
    }

    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.contracts.BaseFault getFaultInfo() {
        return this.baseFault;
    }
}
