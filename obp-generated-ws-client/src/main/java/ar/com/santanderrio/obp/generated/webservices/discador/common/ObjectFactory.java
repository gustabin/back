
package ar.com.santanderrio.obp.generated.webservices.discador.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.discador.common package. 
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

    private final static QName _RequestContactCuentasBase_QNAME = new QName("http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", "RequestContactCuentasBase");
    private final static QName _RequestContactCuentasBaseTipoCuenta_QNAME = new QName("http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", "TipoCuenta");
    private final static QName _RequestContactCuentasBaseNroCuenta_QNAME = new QName("http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", "NroCuenta");
    private final static QName _RequestContactCuentasBaseSucursal_QNAME = new QName("http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", "Sucursal");
    private final static QName _RequestContactCuentasBaseIndSinonimo_QNAME = new QName("http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", "IndSinonimo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.discador.common
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RequestContactCuentasBase }
     * 
     */
    public RequestContactCuentasBase createRequestContactCuentasBase() {
        return new RequestContactCuentasBase();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestContactCuentasBase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", name = "RequestContactCuentasBase")
    public JAXBElement<RequestContactCuentasBase> createRequestContactCuentasBase(RequestContactCuentasBase value) {
        return new JAXBElement<RequestContactCuentasBase>(_RequestContactCuentasBase_QNAME, RequestContactCuentasBase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", name = "TipoCuenta", scope = RequestContactCuentasBase.class)
    public JAXBElement<String> createRequestContactCuentasBaseTipoCuenta(String value) {
        return new JAXBElement<String>(_RequestContactCuentasBaseTipoCuenta_QNAME, String.class, RequestContactCuentasBase.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", name = "NroCuenta", scope = RequestContactCuentasBase.class)
    public JAXBElement<String> createRequestContactCuentasBaseNroCuenta(String value) {
        return new JAXBElement<String>(_RequestContactCuentasBaseNroCuenta_QNAME, String.class, RequestContactCuentasBase.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", name = "Sucursal", scope = RequestContactCuentasBase.class)
    public JAXBElement<String> createRequestContactCuentasBaseSucursal(String value) {
        return new JAXBElement<String>(_RequestContactCuentasBaseSucursal_QNAME, String.class, RequestContactCuentasBase.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", name = "IndSinonimo", scope = RequestContactCuentasBase.class)
    public JAXBElement<String> createRequestContactCuentasBaseIndSinonimo(String value) {
        return new JAXBElement<String>(_RequestContactCuentasBaseIndSinonimo_QNAME, String.class, RequestContactCuentasBase.class, value);
    }

}
