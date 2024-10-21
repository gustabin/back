
package ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.canales.ProcesarOrPagoOBPRequest;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ProcesarOrPagoOBPRequest_QNAME = new QName("Comex", "request");
    private final static QName _ProcesarOrPagoOBPResponseProcesarOrPagoOBPResult_QNAME = new QName("Comex", "ProcesarOrPagoOBPResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcesarOrPagoOBP }
     * 
     */
    public ProcesarOrPagoOBP createProcesarOrPagoOBP() {
        return new ProcesarOrPagoOBP();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.ProcesarOrPagoOBPResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.ProcesarOrPagoOBPResponse createProcesarOrPagoOBPResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.ProcesarOrPagoOBPResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarOrPagoOBPRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ProcesarOrPagoOBP.class)
    public JAXBElement<ProcesarOrPagoOBPRequest> createProcesarOrPagoOBPRequest(ProcesarOrPagoOBPRequest value) {
        return new JAXBElement<ProcesarOrPagoOBPRequest>(_ProcesarOrPagoOBPRequest_QNAME, ProcesarOrPagoOBPRequest.class, ProcesarOrPagoOBP.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ProcesarOrPagoOBPResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.ProcesarOrPagoOBPResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse> createProcesarOrPagoOBPResponseProcesarOrPagoOBPResult(ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse>(_ProcesarOrPagoOBPResponseProcesarOrPagoOBPResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.ProcesarOrPagoOBPResponse.class, value);
    }

}
