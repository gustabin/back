
package ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.parameters;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.parameters package. 
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

    private final static QName _BaseParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", "BaseParameter");
    private final static QName _BaseParameterPassword_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", "Password");
    private final static QName _BaseParameterTipoUser_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", "TipoUser");
    private final static QName _BaseParameterUsuario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", "Usuario");
    private final static QName _BaseParameterCantidadDeRegistros_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", "CantidadDeRegistros");
    private final static QName _BaseParameterIp_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", "Ip");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.parameters
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BaseParameter }
     * 
     */
    public BaseParameter createBaseParameter() {
        return new BaseParameter();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", name = "BaseParameter")
    public JAXBElement<BaseParameter> createBaseParameter(BaseParameter value) {
        return new JAXBElement<BaseParameter>(_BaseParameter_QNAME, BaseParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", name = "Password", scope = BaseParameter.class)
    public JAXBElement<String> createBaseParameterPassword(String value) {
        return new JAXBElement<String>(_BaseParameterPassword_QNAME, String.class, BaseParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", name = "TipoUser", scope = BaseParameter.class)
    public JAXBElement<String> createBaseParameterTipoUser(String value) {
        return new JAXBElement<String>(_BaseParameterTipoUser_QNAME, String.class, BaseParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", name = "Usuario", scope = BaseParameter.class)
    public JAXBElement<String> createBaseParameterUsuario(String value) {
        return new JAXBElement<String>(_BaseParameterUsuario_QNAME, String.class, BaseParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", name = "CantidadDeRegistros", scope = BaseParameter.class)
    public JAXBElement<Integer> createBaseParameterCantidadDeRegistros(Integer value) {
        return new JAXBElement<Integer>(_BaseParameterCantidadDeRegistros_QNAME, Integer.class, BaseParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", name = "Ip", scope = BaseParameter.class)
    public JAXBElement<String> createBaseParameterIp(String value) {
        return new JAXBElement<String>(_BaseParameterIp_QNAME, String.class, BaseParameter.class, value);
    }

}
