
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfBeneficiarios;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.canales package. 
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

    private final static QName _ConsultaBeneficiarioResponse_QNAME = new QName("Response/Canales", "ConsultaBeneficiarioResponse");
    private final static QName _ConsultaBeneficiarioResponseRegistros_QNAME = new QName("Response/Canales", "Registros");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.canales
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaBeneficiarioResponse }
     * 
     */
    public ConsultaBeneficiarioResponse createConsultaBeneficiarioResponse() {
        return new ConsultaBeneficiarioResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaBeneficiarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConsultaBeneficiarioResponse")
    public JAXBElement<ConsultaBeneficiarioResponse> createConsultaBeneficiarioResponse(ConsultaBeneficiarioResponse value) {
        return new JAXBElement<ConsultaBeneficiarioResponse>(_ConsultaBeneficiarioResponse_QNAME, ConsultaBeneficiarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBeneficiarios }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Registros", scope = ConsultaBeneficiarioResponse.class)
    public JAXBElement<ArrayOfBeneficiarios> createConsultaBeneficiarioResponseRegistros(ArrayOfBeneficiarios value) {
        return new JAXBElement<ArrayOfBeneficiarios>(_ConsultaBeneficiarioResponseRegistros_QNAME, ArrayOfBeneficiarios.class, ConsultaBeneficiarioResponse.class, value);
    }

}
