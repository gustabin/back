
package ar.com.santanderrio.obp.generated.webservices.mercado.canal;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.1
 * 2017-12-12T12:46:59.363-03:00
 * Generated source version: 3.0.1
 */

@WebFault(name = "MercadosServiceFault", targetNamespace = "http://schemas.datacontract.org/2004/07/ISBAN.Mercados.ServiceContracts")
public class IMercadosCanalServiceConsultarOperacionesWsMercadosServiceFaultFaultFaultMessage extends Exception {
    
    private ar.com.santanderrio.obp.generated.webservices.mercado.canal.MercadosServiceFault mercadosServiceFault;

    public IMercadosCanalServiceConsultarOperacionesWsMercadosServiceFaultFaultFaultMessage() {
        super();
    }
    
    public IMercadosCanalServiceConsultarOperacionesWsMercadosServiceFaultFaultFaultMessage(String message) {
        super(message);
    }
    
    public IMercadosCanalServiceConsultarOperacionesWsMercadosServiceFaultFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public IMercadosCanalServiceConsultarOperacionesWsMercadosServiceFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.mercado.canal.MercadosServiceFault mercadosServiceFault) {
        super(message);
        this.mercadosServiceFault = mercadosServiceFault;
    }

    public IMercadosCanalServiceConsultarOperacionesWsMercadosServiceFaultFaultFaultMessage(String message, ar.com.santanderrio.obp.generated.webservices.mercado.canal.MercadosServiceFault mercadosServiceFault, Throwable cause) {
        super(message, cause);
        this.mercadosServiceFault = mercadosServiceFault;
    }

    public ar.com.santanderrio.obp.generated.webservices.mercado.canal.MercadosServiceFault getFaultInfo() {
        return this.mercadosServiceFault;
    }
}
