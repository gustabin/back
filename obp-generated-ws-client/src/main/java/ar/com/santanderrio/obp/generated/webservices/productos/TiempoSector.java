
package ar.com.santanderrio.obp.generated.webservices.productos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TiempoSector complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TiempoSector">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codRetorno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descRetorno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tiempoSector" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TiempoSector", propOrder = {
    "codRetorno",
    "descRetorno",
    "tiempoSector"
})
public class TiempoSector {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer codRetorno;
    @XmlElement(required = true, nillable = true)
    protected String descRetorno;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double tiempoSector;

    /**
     * Gets the value of the codRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodRetorno() {
        return codRetorno;
    }

    /**
     * Sets the value of the codRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodRetorno(Integer value) {
        this.codRetorno = value;
    }

    /**
     * Gets the value of the descRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRetorno() {
        return descRetorno;
    }

    /**
     * Sets the value of the descRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRetorno(String value) {
        this.descRetorno = value;
    }

    /**
     * Gets the value of the tiempoSector property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTiempoSector() {
        return tiempoSector;
    }

    /**
     * Sets the value of the tiempoSector property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTiempoSector(Double value) {
        this.tiempoSector = value;
    }

}
