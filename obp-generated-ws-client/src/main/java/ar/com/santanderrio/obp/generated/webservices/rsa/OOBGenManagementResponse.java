
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OOBGenManagementResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OOBGenManagementResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}AcspManagementResponse">
 *       &lt;sequence>
 *         &lt;element name="action" type="{http://ws.oobgen.csd.rsa.com}Action" minOccurs="0"/>
 *         &lt;element name="contactList" type="{http://ws.oobgen.csd.rsa.com}OOBPhoneInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OOBGenManagementResponse", namespace = "http://ws.oobgen.csd.rsa.com", propOrder = {
    "action",
    "contactList"
})
@XmlSeeAlso({
    OOBSMSManagementResponse.class
})
public abstract class OOBGenManagementResponse
    extends AcspManagementResponse
{

    protected Action2 action;
    protected List<OOBPhoneInfo> contactList;

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link Action2 }
     *     
     */
    public Action2 getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link Action2 }
     *     
     */
    public void setAction(Action2 value) {
        this.action = value;
    }

    /**
     * Gets the value of the contactList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OOBPhoneInfo }
     * 
     * 
     */
    public List<OOBPhoneInfo> getContactList() {
        if (contactList == null) {
            contactList = new ArrayList<OOBPhoneInfo>();
        }
        return this.contactList;
    }

}
