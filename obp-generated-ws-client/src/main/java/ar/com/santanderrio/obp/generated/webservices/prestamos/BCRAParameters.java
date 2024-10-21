
package ar.com.santanderrio.obp.generated.webservices.prestamos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BCRAParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BCRAParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Aplicativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoDocInsc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroDocInsc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BCRAParameters", propOrder = {
    "aplicativo",
    "nup",
    "tipoDocInsc",
    "nroDocInsc"
})
public class BCRAParameters {

    @XmlElement(name = "Aplicativo")
    protected String aplicativo;
    @XmlElement(name = "Nup")
    protected String nup;
    @XmlElement(name = "TipoDocInsc")
    protected String tipoDocInsc;
    @XmlElement(name = "NroDocInsc")
    protected String nroDocInsc;

    /**
     * Gets the value of the aplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplicativo() {
        return aplicativo;
    }

    /**
     * Sets the value of the aplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplicativo(String value) {
        this.aplicativo = value;
    }

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
     * Gets the value of the tipoDocInsc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocInsc() {
        return tipoDocInsc;
    }

    /**
     * Sets the value of the tipoDocInsc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocInsc(String value) {
        this.tipoDocInsc = value;
    }

    /**
     * Gets the value of the nroDocInsc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroDocInsc() {
        return nroDocInsc;
    }

    /**
     * Sets the value of the nroDocInsc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroDocInsc(String value) {
        this.nroDocInsc = value;
    }

}
