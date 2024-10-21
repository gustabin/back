
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain.base;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.bpriv.domain.base package. 
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

    private final static QName _Entity_QNAME = new QName("Domain/Base", "Entity");
    private final static QName _EntityId_QNAME = new QName("Domain/Base", "Id");
    private final static QName _EntityFechaAlta_QNAME = new QName("Domain/Base", "FechaAlta");
    private final static QName _EntityUsuarioAlta_QNAME = new QName("Domain/Base", "UsuarioAlta");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.bpriv.domain.base
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Entity }
     * 
     */
    public Entity createEntity() {
        return new Entity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Entity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Domain/Base", name = "Entity")
    public JAXBElement<Entity> createEntity(Entity value) {
        return new JAXBElement<Entity>(_Entity_QNAME, Entity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Domain/Base", name = "Id", scope = Entity.class)
    public JAXBElement<String> createEntityId(String value) {
        return new JAXBElement<String>(_EntityId_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Domain/Base", name = "FechaAlta", scope = Entity.class)
    public JAXBElement<XMLGregorianCalendar> createEntityFechaAlta(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_EntityFechaAlta_QNAME, XMLGregorianCalendar.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Domain/Base", name = "UsuarioAlta", scope = Entity.class)
    public JAXBElement<String> createEntityUsuarioAlta(String value) {
        return new JAXBElement<String>(_EntityUsuarioAlta_QNAME, String.class, Entity.class, value);
    }

}
