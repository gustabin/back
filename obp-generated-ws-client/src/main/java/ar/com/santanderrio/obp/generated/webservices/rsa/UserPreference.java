
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This type defines user preference regarding the RSA Milter
 * 
 * <p>Java class for UserPreference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserPreference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="milterOption" type="{http://ws.csd.rsa.com}MilterOption" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserPreference", propOrder = {
    "milterOption"
})
public class UserPreference {

    protected MilterOption milterOption;

    /**
     * Gets the value of the milterOption property.
     * 
     * @return
     *     possible object is
     *     {@link MilterOption }
     *     
     */
    public MilterOption getMilterOption() {
        return milterOption;
    }

    /**
     * Sets the value of the milterOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link MilterOption }
     *     
     */
    public void setMilterOption(MilterOption value) {
        this.milterOption = value;
    }

}
