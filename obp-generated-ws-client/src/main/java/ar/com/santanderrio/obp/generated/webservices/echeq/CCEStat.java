
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CCEStat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCEStat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CCERegstatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Optimeposted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Optimeexec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Opid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CCEStat", propOrder = {
    "cceRegstatus",
    "optimeposted",
    "optimeexec",
    "opid"
})
public class CCEStat {

    @XmlElement(name = "CCERegstatus")
    protected String cceRegstatus;
    @XmlElement(name = "Optimeposted")
    protected String optimeposted;
    @XmlElement(name = "Optimeexec")
    protected String optimeexec;
    @XmlElement(name = "Opid")
    protected String opid;

    /**
     * Gets the value of the cceRegstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCERegstatus() {
        return cceRegstatus;
    }

    /**
     * Sets the value of the cceRegstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCCERegstatus(String value) {
        this.cceRegstatus = value;
    }

    /**
     * Gets the value of the optimeposted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptimeposted() {
        return optimeposted;
    }

    /**
     * Sets the value of the optimeposted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptimeposted(String value) {
        this.optimeposted = value;
    }

    /**
     * Gets the value of the optimeexec property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptimeexec() {
        return optimeexec;
    }

    /**
     * Sets the value of the optimeexec property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptimeexec(String value) {
        this.optimeexec = value;
    }

    /**
     * Gets the value of the opid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpid() {
        return opid;
    }

    /**
     * Sets the value of the opid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpid(String value) {
        this.opid = value;
    }

}
