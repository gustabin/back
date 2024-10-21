
package ar.com.santanderrio.obp.generated.webservices.productos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nup" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dias" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nup",
    "dias"
})
@XmlRootElement(name = "obtenerListaReclamos")
public class ObtenerListaReclamos {

    @XmlElement(required = true, nillable = true)
    protected String nup;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer dias;

    /**
     * Gets the value of the nup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNup() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNup(String value) {
        this.nup = value;
    }

    /**
     * Gets the value of the dias property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDias() {
        return dias;
    }

    /**
     * Sets the value of the dias property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDias(Integer value) {
        this.dias = value;
    }

}
