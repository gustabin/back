
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
 *         &lt;element name="ideGestionSector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ideGestionNro" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codSectorResol" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "ideGestionSector",
    "ideGestionNro",
    "codSectorResol"
})
@XmlRootElement(name = "obtenerInfoAdjResol")
public class ObtenerInfoAdjResol {

    @XmlElement(required = true, nillable = true)
    protected String ideGestionSector;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer ideGestionNro;
    @XmlElement(required = true, nillable = true)
    protected String codSectorResol;

    /**
     * Gets the value of the ideGestionSector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdeGestionSector() {
        return ideGestionSector;
    }

    /**
     * Sets the value of the ideGestionSector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdeGestionSector(String value) {
        this.ideGestionSector = value;
    }

    /**
     * Gets the value of the ideGestionNro property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdeGestionNro() {
        return ideGestionNro;
    }

    /**
     * Sets the value of the ideGestionNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdeGestionNro(Integer value) {
        this.ideGestionNro = value;
    }

    /**
     * Gets the value of the codSectorResol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodSectorResol() {
        return codSectorResol;
    }

    /**
     * Sets the value of the codSectorResol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodSectorResol(String value) {
        this.codSectorResol = value;
    }

}
