
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.consultas package. 
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

    private final static QName _ConsultaBeneficiarioRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", "ConsultaBeneficiarioRequest");
    private final static QName _ObtenerCondicionesVentaRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", "ObtenerCondicionesVentaRequest");
    private final static QName _ObtenerCondicionesVentaRequestIdCondVta_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", "Id_Cond_Vta");
    private final static QName _ConsultaBeneficiarioRequestNup_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", "Nup");
    private final static QName _ConsultaBeneficiarioRequestIdBeneficiario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", "Id_beneficiario");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.consultas
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaBeneficiarioRequest }
     * 
     */
    public ConsultaBeneficiarioRequest createConsultaBeneficiarioRequest() {
        return new ConsultaBeneficiarioRequest();
    }

    /**
     * Create an instance of {@link ObtenerCondicionesVentaRequest }
     * 
     */
    public ObtenerCondicionesVentaRequest createObtenerCondicionesVentaRequest() {
        return new ObtenerCondicionesVentaRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaBeneficiarioRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", name = "ConsultaBeneficiarioRequest")
    public JAXBElement<ConsultaBeneficiarioRequest> createConsultaBeneficiarioRequest(ConsultaBeneficiarioRequest value) {
        return new JAXBElement<ConsultaBeneficiarioRequest>(_ConsultaBeneficiarioRequest_QNAME, ConsultaBeneficiarioRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerCondicionesVentaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", name = "ObtenerCondicionesVentaRequest")
    public JAXBElement<ObtenerCondicionesVentaRequest> createObtenerCondicionesVentaRequest(ObtenerCondicionesVentaRequest value) {
        return new JAXBElement<ObtenerCondicionesVentaRequest>(_ObtenerCondicionesVentaRequest_QNAME, ObtenerCondicionesVentaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", name = "Id_Cond_Vta", scope = ObtenerCondicionesVentaRequest.class)
    public JAXBElement<Long> createObtenerCondicionesVentaRequestIdCondVta(Long value) {
        return new JAXBElement<Long>(_ObtenerCondicionesVentaRequestIdCondVta_QNAME, Long.class, ObtenerCondicionesVentaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", name = "Nup", scope = ConsultaBeneficiarioRequest.class)
    public JAXBElement<String> createConsultaBeneficiarioRequestNup(String value) {
        return new JAXBElement<String>(_ConsultaBeneficiarioRequestNup_QNAME, String.class, ConsultaBeneficiarioRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", name = "Id_beneficiario", scope = ConsultaBeneficiarioRequest.class)
    public JAXBElement<Long> createConsultaBeneficiarioRequestIdBeneficiario(Long value) {
        return new JAXBElement<Long>(_ConsultaBeneficiarioRequestIdBeneficiario_QNAME, Long.class, ConsultaBeneficiarioRequest.class, value);
    }

}
