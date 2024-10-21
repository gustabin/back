
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PhoneManagementRequestPayload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PhoneManagementRequestPayload">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}OOBManagementRequestPayload">
 *       &lt;sequence>
 *         &lt;element name="contactList" type="{http://ws.csd.rsa.com}PhoneInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneManagementRequestPayload", propOrder = {
    "contactList"
})
public class PhoneManagementRequestPayload
    extends OOBManagementRequestPayload
{

    protected List<PhoneInfo> contactList;

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
     * {@link PhoneInfo }
     * 
     * 
     */
    public List<PhoneInfo> getContactList() {
        if (contactList == null) {
            contactList = new ArrayList<PhoneInfo>();
        }
        return this.contactList;
    }

}
