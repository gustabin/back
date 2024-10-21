
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.contracts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.contracts package. 
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

    private final static QName _BaseFault_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", "BaseFault");
    private final static QName _BaseFaultMensaje_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", "Mensaje");
    private final static QName _BaseFaultFaultInterna_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", "FaultInterna");
    private final static QName _BaseFaultOrigen_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", "Origen");
    private final static QName _BaseFaultStack_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", "Stack");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.contracts
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BaseFault }
     * 
     */
    public BaseFault createBaseFault() {
        return new BaseFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", name = "BaseFault")
    public JAXBElement<BaseFault> createBaseFault(BaseFault value) {
        return new JAXBElement<BaseFault>(_BaseFault_QNAME, BaseFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", name = "Mensaje", scope = BaseFault.class)
    public JAXBElement<String> createBaseFaultMensaje(String value) {
        return new JAXBElement<String>(_BaseFaultMensaje_QNAME, String.class, BaseFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", name = "FaultInterna", scope = BaseFault.class)
    public JAXBElement<BaseFault> createBaseFaultFaultInterna(BaseFault value) {
        return new JAXBElement<BaseFault>(_BaseFaultFaultInterna_QNAME, BaseFault.class, BaseFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", name = "Origen", scope = BaseFault.class)
    public JAXBElement<String> createBaseFaultOrigen(String value) {
        return new JAXBElement<String>(_BaseFaultOrigen_QNAME, String.class, BaseFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts", name = "Stack", scope = BaseFault.class)
    public JAXBElement<String> createBaseFaultStack(String value) {
        return new JAXBElement<String>(_BaseFaultStack_QNAME, String.class, BaseFault.class, value);
    }

}
