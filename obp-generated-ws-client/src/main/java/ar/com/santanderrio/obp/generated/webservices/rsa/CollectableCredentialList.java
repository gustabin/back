
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This is a list of credentials that are safe for the system to collect
 * 
 * <p>Java class for CollectableCredentialList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CollectableCredentialList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="collectableCredential" type="{http://ws.csd.rsa.com}CollectableCredential" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollectableCredentialList", propOrder = {
    "collectableCredential"
})
public class CollectableCredentialList {

    protected List<CollectableCredential> collectableCredential;

    /**
     * Gets the value of the collectableCredential property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the collectableCredential property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCollectableCredential().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CollectableCredential }
     * 
     * 
     */
    public List<CollectableCredential> getCollectableCredential() {
        if (collectableCredential == null) {
            collectableCredential = new ArrayList<CollectableCredential>();
        }
        return this.collectableCredential;
    }

}
