
package ar.com.santanderrio.obp.generated.webservices.banelco;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ICajero complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ICajero">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="expendeDolares" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="expendePesos" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="idCajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ICajero", namespace = "http://business.model.cds.banelco.com", propOrder = {
    "estado",
    "expendeDolares",
    "expendePesos",
    "idCajero"
})
public class ICajero {

    protected Boolean estado;
    protected Boolean expendeDolares;
    protected Boolean expendePesos;
    @XmlElementRef(name = "idCajero", namespace = "http://business.model.cds.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> idCajero;

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstado(Boolean value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad expendeDolares.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExpendeDolares() {
        return expendeDolares;
    }

    /**
     * Define el valor de la propiedad expendeDolares.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpendeDolares(Boolean value) {
        this.expendeDolares = value;
    }

    /**
     * Obtiene el valor de la propiedad expendePesos.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExpendePesos() {
        return expendePesos;
    }

    /**
     * Define el valor de la propiedad expendePesos.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpendePesos(Boolean value) {
        this.expendePesos = value;
    }

    /**
     * Obtiene el valor de la propiedad idCajero.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdCajero() {
        return idCajero;
    }

    /**
     * Define el valor de la propiedad idCajero.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdCajero(JAXBElement<String> value) {
        this.idCajero = value;
    }

}
