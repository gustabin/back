
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bae_CCE_Stat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bae_CCE_Stat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bae_CCERegstatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bae_Optimeposted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bae_Optimeexec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bae_Opid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bae_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bae_message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bae_CCE_Stat", propOrder = {
    "baeCCERegstatus",
    "baeOptimeposted",
    "baeOptimeexec",
    "baeOpid",
    "baeCode",
    "baeMessage"
})
public class BaeCCEStat {

    @XmlElement(name = "bae_CCERegstatus")
    protected String baeCCERegstatus;
    @XmlElement(name = "bae_Optimeposted")
    protected String baeOptimeposted;
    @XmlElement(name = "bae_Optimeexec")
    protected String baeOptimeexec;
    @XmlElement(name = "bae_Opid")
    protected String baeOpid;
    @XmlElement(name = "bae_code")
    protected String baeCode;
    @XmlElement(name = "bae_message")
    protected String baeMessage;

    /**
     * Gets the value of the baeCCERegstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaeCCERegstatus() {
        return baeCCERegstatus;
    }

    /**
     * Sets the value of the baeCCERegstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaeCCERegstatus(String value) {
        this.baeCCERegstatus = value;
    }

    /**
     * Gets the value of the baeOptimeposted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaeOptimeposted() {
        return baeOptimeposted;
    }

    /**
     * Sets the value of the baeOptimeposted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaeOptimeposted(String value) {
        this.baeOptimeposted = value;
    }

    /**
     * Gets the value of the baeOptimeexec property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaeOptimeexec() {
        return baeOptimeexec;
    }

    /**
     * Sets the value of the baeOptimeexec property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaeOptimeexec(String value) {
        this.baeOptimeexec = value;
    }

    /**
     * Gets the value of the baeOpid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaeOpid() {
        return baeOpid;
    }

    /**
     * Sets the value of the baeOpid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaeOpid(String value) {
        this.baeOpid = value;
    }

    /**
     * Gets the value of the baeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaeCode() {
        return baeCode;
    }

    /**
     * Sets the value of the baeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaeCode(String value) {
        this.baeCode = value;
    }

    /**
     * Gets the value of the baeMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaeMessage() {
        return baeMessage;
    }

    /**
     * Sets the value of the baeMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaeMessage(String value) {
        this.baeMessage = value;
    }

}
