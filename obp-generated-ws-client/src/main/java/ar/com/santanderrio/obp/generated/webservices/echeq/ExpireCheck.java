
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExpireCheck complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExpireCheck">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intcheque_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="trk_cnl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trk_scnl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trk_jsessionid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpireCheck", propOrder = {
    "intchequeId",
    "trkCnl",
    "trkScnl",
    "trkJsessionid"
})
public class ExpireCheck {

    @XmlElement(name = "intcheque_id", required = true)
    protected String intchequeId;
    @XmlElement(name = "trk_cnl")
    protected String trkCnl;
    @XmlElement(name = "trk_scnl")
    protected String trkScnl;
    @XmlElement(name = "trk_jsessionid")
    protected String trkJsessionid;

    /**
     * Gets the value of the intchequeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchequeId() {
        return intchequeId;
    }

    /**
     * Sets the value of the intchequeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchequeId(String value) {
        this.intchequeId = value;
    }

    /**
     * Gets the value of the trkCnl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrkCnl() {
        return trkCnl;
    }

    /**
     * Sets the value of the trkCnl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrkCnl(String value) {
        this.trkCnl = value;
    }

    /**
     * Gets the value of the trkScnl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrkScnl() {
        return trkScnl;
    }

    /**
     * Sets the value of the trkScnl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrkScnl(String value) {
        this.trkScnl = value;
    }

    /**
     * Gets the value of the trkJsessionid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrkJsessionid() {
        return trkJsessionid;
    }

    /**
     * Sets the value of the trkJsessionid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrkJsessionid(String value) {
        this.trkJsessionid = value;
    }

}
