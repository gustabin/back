
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response package. 
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

    private final static QName _BaseResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", "BaseResponse");
    private final static QName _CursorResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", "CursorResponse");
    private final static QName _BaseResponseErrorSistema_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", "Error_Sistema");
    private final static QName _BaseResponseErrorInterface_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", "Error_Interface");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BaseResponse }
     * 
     */
    public BaseResponse createBaseResponse() {
        return new BaseResponse();
    }

    /**
     * Create an instance of {@link CursorResponse }
     * 
     */
    public CursorResponse createCursorResponse() {
        return new CursorResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", name = "BaseResponse")
    public JAXBElement<BaseResponse> createBaseResponse(BaseResponse value) {
        return new JAXBElement<BaseResponse>(_BaseResponse_QNAME, BaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CursorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", name = "CursorResponse")
    public JAXBElement<CursorResponse> createCursorResponse(CursorResponse value) {
        return new JAXBElement<CursorResponse>(_CursorResponse_QNAME, CursorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", name = "Error_Sistema", scope = BaseResponse.class)
    public JAXBElement<String> createBaseResponseErrorSistema(String value) {
        return new JAXBElement<String>(_BaseResponseErrorSistema_QNAME, String.class, BaseResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", name = "Error_Interface", scope = BaseResponse.class)
    public JAXBElement<String> createBaseResponseErrorInterface(String value) {
        return new JAXBElement<String>(_BaseResponseErrorInterface_QNAME, String.class, BaseResponse.class, value);
    }

}
