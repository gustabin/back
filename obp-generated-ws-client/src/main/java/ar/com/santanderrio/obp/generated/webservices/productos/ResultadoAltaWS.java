
package ar.com.santanderrio.obp.generated.webservices.productos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoAltaWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoAltaWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codRetorno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descRetorno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ideGestionNro" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ideGestionSector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoAltaWS", namespace = "http://webService.core.ges.rio.com", propOrder = {
    "codRetorno",
    "descRetorno",
    "ideGestionNro",
    "ideGestionSector"
})
public class ResultadoAltaWS {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer codRetorno;
    @XmlElement(required = true, nillable = true)
    protected String descRetorno;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer ideGestionNro;
    @XmlElement(required = true, nillable = true)
    protected String ideGestionSector;

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

}
