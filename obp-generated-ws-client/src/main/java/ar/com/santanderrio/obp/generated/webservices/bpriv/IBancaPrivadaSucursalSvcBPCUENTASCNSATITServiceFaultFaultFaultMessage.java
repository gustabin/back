
package ar.com.santanderrio.obp.generated.webservices.bpriv;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.1
 * 2019-11-06T16:46:58.754-03:00
 * Generated source version: 3.0.1
 */

@WebFault(name = "ServiceFault", targetNamespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Service.Contract.Fault")
public class IBancaPrivadaSucursalSvcBPCUENTASCNSATITServiceFaultFaultFaultMessage extends Exception {
    
    private ar.com.santanderrio.obp.generated.webservices.bpriv.contract.fault.ServiceFault serviceFault;

    public IBancaPrivadaSucursalSvcBPCUENTASCNSATITServiceFaultFaultFaultMessage() {
        super();
    }
    
    public IBancaPrivadaSucursalSvcBPCUENTASCNSATITServiceFaultFaultFaultMessage(String message) {
        super(message);
    }
    
    public IBancaPrivadaSucursalSvcBPCUENTASCNSATITServiceFaultFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public IBancaPrivadaSucursalSvcBPCUENTASCNSATITServiceFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.bpriv.contract.fault.ServiceFault serviceFault) {
        super(message);
        this.serviceFault = serviceFault;
    }

    public IBancaPrivadaSucursalSvcBPCUENTASCNSATITServiceFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.bpriv.contract.fault.ServiceFault serviceFault, Throwable cause) {
        super(message, cause);
        this.serviceFault = serviceFault;
    }

    public ar.com.santanderrio.obp.generated.webservices.bpriv.contract.fault.ServiceFault getFaultInfo() {
        return this.serviceFault;
    }
}
