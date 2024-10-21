package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade;

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
 *         &lt;element name="ConsSolUpgradeReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "consSolUpgradeReturn"
})
@XmlRootElement(name = "ConsSolUpgradeResponse")
public class ConsSolUpgradeResponse {

    @XmlElement(name = "ConsSolUpgradeReturn", required = true)
    protected String consSolUpgradeReturn;

    /**
     * Gets the value of the consSolUpgradeReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsSolUpgradeReturn() {
        return consSolUpgradeReturn;
    }

    /**
     * Sets the value of the consSolUpgradeReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsSolUpgradeReturn(String value) {
        this.consSolUpgradeReturn = value;
    }

}
