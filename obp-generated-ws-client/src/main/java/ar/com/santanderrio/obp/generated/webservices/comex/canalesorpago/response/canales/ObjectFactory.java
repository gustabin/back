
package ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales package. 
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

    private final static QName _ProcesarOrPagoOBPResponse_QNAME = new QName("Response/Canales", "ProcesarOrPagoOBPResponse");
    private final static QName _ProcesarOrPagoOBPResponseNroForm_QNAME = new QName("Response/Canales", "Nro_Form");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcesarOrPagoOBPResponse }
     * 
     */
    public ProcesarOrPagoOBPResponse createProcesarOrPagoOBPResponse() {
        return new ProcesarOrPagoOBPResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarOrPagoOBPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ProcesarOrPagoOBPResponse")
    public JAXBElement<ProcesarOrPagoOBPResponse> createProcesarOrPagoOBPResponse(ProcesarOrPagoOBPResponse value) {
        return new JAXBElement<ProcesarOrPagoOBPResponse>(_ProcesarOrPagoOBPResponse_QNAME, ProcesarOrPagoOBPResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Form", scope = ProcesarOrPagoOBPResponse.class)
    public JAXBElement<Integer> createProcesarOrPagoOBPResponseNroForm(Integer value) {
        return new JAXBElement<Integer>(_ProcesarOrPagoOBPResponseNroForm_QNAME, Integer.class, ProcesarOrPagoOBPResponse.class, value);
    }

}
