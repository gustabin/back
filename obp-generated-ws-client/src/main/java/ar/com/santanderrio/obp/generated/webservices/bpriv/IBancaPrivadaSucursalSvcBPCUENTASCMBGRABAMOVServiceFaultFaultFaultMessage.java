
package ar.com.santanderrio.obp.generated.webservices.bpriv;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.1
 * 2019-11-06T16:46:58.734-03:00
 * Generated source version: 3.0.1
 */

@WebFault(name = "ServiceFault", targetNamespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Service.Contract.Fault")
public class IBancaPrivadaSucursalSvcBPCUENTASCMBGRABAMOVServiceFaultFaultFaultMessage extends Exception {
    
    private ar.com.santanderrio.obp.generated.webservices.bpriv.contract.fault.ServiceFault serviceFault;

    public IBancaPrivadaSucursalSvcBPCUENTASCMBGRABAMOVServiceFaultFaultFaultMessage() {
        super();
    }
    
    public IBancaPrivadaSucursalSvcBPCUENTASCMBGRABAMOVServiceFaultFaultFaultMessage(String message) {
        super(message);
    }
    
    public IBancaPrivadaSucursalSvcBPCUENTASCMBGRABAMOVServiceFaultFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public IBancaPrivadaSucursalSvcBPCUENTASCMBGRABAMOVServiceFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.bpriv.contract.fault.ServiceFault serviceFault) {
        super(message);
        this.serviceFault = serviceFault;
    }

    public IBancaPrivadaSucursalSvcBPCUENTASCMBGRABAMOVServiceFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.bpriv.contract.fault.ServiceFault serviceFault, Throwable cause) {
        super(message, cause);
        this.serviceFault = serviceFault;
    }

    public ar.com.santanderrio.obp.generated.webservices.bpriv.contract.fault.ServiceFault getFaultInfo() {
        return this.serviceFault;
    }
}
