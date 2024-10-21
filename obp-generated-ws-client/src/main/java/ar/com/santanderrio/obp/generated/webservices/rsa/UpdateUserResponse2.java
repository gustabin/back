
package ar.com.santanderrio.obp.generated.webservices.rsa;

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
 *         &lt;element name="updateUserReturn" type="{http://ws.csd.rsa.com}UpdateUserResponse"/>
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
    "updateUserReturn"
})
@XmlRootElement(name = "updateUserResponse")
public class UpdateUserResponse2 {

    @XmlElement(required = true)
    protected UpdateUserResponse updateUserReturn;

    /**
     * Gets the value of the updateUserReturn property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateUserResponse }
     *     
     */
    public UpdateUserResponse getUpdateUserReturn() {
        return updateUserReturn;
    }

    /**
     * Sets the value of the updateUserReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateUserResponse }
     *     
     */
    public void setUpdateUserReturn(UpdateUserResponse value) {
        this.updateUserReturn = value;
    }

}
