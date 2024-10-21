
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
 *         &lt;element name="tiposGestion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoActual" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "dias",
    "tiposGestion",
    "estadoActual"
})
@XmlRootElement(name = "obtenerListaGestiones")
public class ObtenerListaGestiones {

    @XmlElement(required = true, nillable = true)
    protected String nup;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer dias;
    @XmlElement(required = true, nillable = true)
    protected String tiposGestion;
    @XmlElement(required = true, nillable = true)
    protected String estadoActual;

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

    /**
     * Gets the value of the tiposGestion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTiposGestion() {
        return tiposGestion;
    }

    /**
     * Sets the value of the tiposGestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTiposGestion(String value) {
        this.tiposGestion = value;
    }

    /**
     * Gets the value of the estadoActual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoActual() {
        return estadoActual;
    }

    /**
     * Sets the value of the estadoActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoActual(String value) {
        this.estadoActual = value;
    }

}
