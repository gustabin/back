
package ar.com.santanderrio.obp.generated.webservices.discador.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.discador.tempuri package. 
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

    private final static QName _GrabarDatosClickToCallSinFirmaJson_QNAME = new QName("http://tempuri.org/", "json");
    private final static QName _GrabarDatosClickToCallSigned_QNAME = new QName("http://tempuri.org/", "signed");
    private final static QName _GrabarDatosClickToCallSinFirmaResponseGrabarDatosClickToCallSinFirmaResult_QNAME = new QName("http://tempuri.org/", "GrabarDatosClickToCall_SinFirmaResult");
    private final static QName _GrabarDatosClickToCallResponseGrabarDatosClickToCallResult_QNAME = new QName("http://tempuri.org/", "GrabarDatosClickToCallResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.discador.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GrabarDatosClickToCall }
     * 
     */
    public GrabarDatosClickToCall createGrabarDatosClickToCall() {
        return new GrabarDatosClickToCall();
    }

    /**
     * Create an instance of {@link GrabarDatosClickToCallResponse }
     * 
     */
    public GrabarDatosClickToCallResponse createGrabarDatosClickToCallResponse() {
        return new GrabarDatosClickToCallResponse();
    }

    /**
     * Create an instance of {@link GrabarDatosClickToCallSinFirma }
     * 
     */
    public GrabarDatosClickToCallSinFirma createGrabarDatosClickToCallSinFirma() {
        return new GrabarDatosClickToCallSinFirma();
    }

    /**
     * Create an instance of {@link GrabarDatosClickToCallSinFirmaResponse }
     * 
     */
    public GrabarDatosClickToCallSinFirmaResponse createGrabarDatosClickToCallSinFirmaResponse() {
        return new GrabarDatosClickToCallSinFirmaResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "json", scope = GrabarDatosClickToCallSinFirma.class)
    public JAXBElement<String> createGrabarDatosClickToCallSinFirmaJson(String value) {
        return new JAXBElement<String>(_GrabarDatosClickToCallSinFirmaJson_QNAME, String.class, GrabarDatosClickToCallSinFirma.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "signed", scope = GrabarDatosClickToCall.class)
    public JAXBElement<String> createGrabarDatosClickToCallSigned(String value) {
        return new JAXBElement<String>(_GrabarDatosClickToCallSigned_QNAME, String.class, GrabarDatosClickToCall.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GrabarDatosClickToCall_SinFirmaResult", scope = GrabarDatosClickToCallSinFirmaResponse.class)
    public JAXBElement<String> createGrabarDatosClickToCallSinFirmaResponseGrabarDatosClickToCallSinFirmaResult(String value) {
        return new JAXBElement<String>(_GrabarDatosClickToCallSinFirmaResponseGrabarDatosClickToCallSinFirmaResult_QNAME, String.class, GrabarDatosClickToCallSinFirmaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GrabarDatosClickToCallResult", scope = GrabarDatosClickToCallResponse.class)
    public JAXBElement<String> createGrabarDatosClickToCallResponseGrabarDatosClickToCallResult(String value) {
        return new JAXBElement<String>(_GrabarDatosClickToCallResponseGrabarDatosClickToCallResult_QNAME, String.class, GrabarDatosClickToCallResponse.class, value);
    }

}
