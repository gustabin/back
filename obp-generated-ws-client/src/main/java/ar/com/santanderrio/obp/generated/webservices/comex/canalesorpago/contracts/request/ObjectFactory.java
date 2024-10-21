
package ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request package. 
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

    private final static QName _BaseRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", "BaseRequest");
    private final static QName _FirmaRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", "FirmaRequest");
    private final static QName _FirmaRequestFirmaHash_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", "Firma_hash");
    private final static QName _FirmaRequestFirmaDatosDentro_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", "Firma_datos_dentro");
    private final static QName _FirmaRequestFirmaDatos_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", "Firma_datos");
    private final static QName _FirmaRequestFirmaFormato_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", "Firma_formato");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FirmaRequest }
     * 
     */
    public FirmaRequest createFirmaRequest() {
        return new FirmaRequest();
    }

    /**
     * Create an instance of {@link BaseRequest }
     * 
     */
    public BaseRequest createBaseRequest() {
        return new BaseRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", name = "BaseRequest")
    public JAXBElement<BaseRequest> createBaseRequest(BaseRequest value) {
        return new JAXBElement<BaseRequest>(_BaseRequest_QNAME, BaseRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FirmaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", name = "FirmaRequest")
    public JAXBElement<FirmaRequest> createFirmaRequest(FirmaRequest value) {
        return new JAXBElement<FirmaRequest>(_FirmaRequest_QNAME, FirmaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", name = "Firma_hash", scope = FirmaRequest.class)
    public JAXBElement<String> createFirmaRequestFirmaHash(String value) {
        return new JAXBElement<String>(_FirmaRequestFirmaHash_QNAME, String.class, FirmaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", name = "Firma_datos_dentro", scope = FirmaRequest.class)
    public JAXBElement<String> createFirmaRequestFirmaDatosDentro(String value) {
        return new JAXBElement<String>(_FirmaRequestFirmaDatosDentro_QNAME, String.class, FirmaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", name = "Firma_datos", scope = FirmaRequest.class)
    public JAXBElement<String> createFirmaRequestFirmaDatos(String value) {
        return new JAXBElement<String>(_FirmaRequestFirmaDatos_QNAME, String.class, FirmaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", name = "Firma_formato", scope = FirmaRequest.class)
    public JAXBElement<String> createFirmaRequestFirmaFormato(String value) {
        return new JAXBElement<String>(_FirmaRequestFirmaFormato_QNAME, String.class, FirmaRequest.class, value);
    }

}
