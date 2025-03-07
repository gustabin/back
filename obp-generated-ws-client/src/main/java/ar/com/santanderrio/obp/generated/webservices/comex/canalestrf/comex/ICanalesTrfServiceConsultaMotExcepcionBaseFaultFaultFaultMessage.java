
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.1
 * 2020-02-21T10:55:46.519-03:00
 * Generated source version: 3.0.1
 */

@WebFault(name = "BaseFault", targetNamespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts")
public class ICanalesTrfServiceConsultaMotExcepcionBaseFaultFaultFaultMessage extends Exception {
    
    private ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.contracts.BaseFault baseFault;

    public ICanalesTrfServiceConsultaMotExcepcionBaseFaultFaultFaultMessage() {
        super();
    }
    
    public ICanalesTrfServiceConsultaMotExcepcionBaseFaultFaultFaultMessage(String message) {
        super(message);
    }
    
    public ICanalesTrfServiceConsultaMotExcepcionBaseFaultFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ICanalesTrfServiceConsultaMotExcepcionBaseFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.contracts.BaseFault baseFault) {
        super(message);
        this.baseFault = baseFault;
    }

    public ICanalesTrfServiceConsultaMotExcepcionBaseFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.contracts.BaseFault baseFault, Throwable cause) {
        super(message, cause);
        this.baseFault = baseFault;
    }

    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.contracts.BaseFault getFaultInfo() {
        return this.baseFault;
    }
}
