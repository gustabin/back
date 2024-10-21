
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This describes why a collection is being initiated and the reasons behind it
 * 
 * <p>Java class for CollectionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CollectionRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="collectionInitiator" type="{http://ws.csd.rsa.com}CollectionInitiator" minOccurs="0"/>
 *         &lt;element name="collectionReason" type="{http://ws.csd.rsa.com}CollectionReason" minOccurs="0"/>
 *         &lt;element name="forceCollection" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="orgCredentialList" type="{http://ws.csd.rsa.com}CredentialList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollectionRequest", propOrder = {
    "collectionInitiator",
    "collectionReason",
    "forceCollection",
    "orgCredentialList"
})
public class CollectionRequest {

    protected CollectionInitiator collectionInitiator;
    protected CollectionReason collectionReason;
    protected Boolean forceCollection;
    protected CredentialList orgCredentialList;

    /**
     * Gets the value of the collectionInitiator property.
     * 
     * @return
     *     possible object is
     *     {@link CollectionInitiator }
     *     
     */
    public CollectionInitiator getCollectionInitiator() {
        return collectionInitiator;
    }

    /**
     * Sets the value of the collectionInitiator property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionInitiator }
     *     
     */
    public void setCollectionInitiator(CollectionInitiator value) {
        this.collectionInitiator = value;
    }

    /**
     * Gets the value of the collectionReason property.
     * 
     * @return
     *     possible object is
     *     {@link CollectionReason }
     *     
     */
    public CollectionReason getCollectionReason() {
        return collectionReason;
    }

    /**
     * Sets the value of the collectionReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionReason }
     *     
     */
    public void setCollectionReason(CollectionReason value) {
        this.collectionReason = value;
    }

    /**
     * Gets the value of the forceCollection property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isForceCollection() {
        return forceCollection;
    }

    /**
     * Sets the value of the forceCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setForceCollection(Boolean value) {
        this.forceCollection = value;
    }

    /**
     * Gets the value of the orgCredentialList property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialList }
     *     
     */
    public CredentialList getOrgCredentialList() {
        return orgCredentialList;
    }

    /**
     * Sets the value of the orgCredentialList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialList }
     *     
     */
    public void setOrgCredentialList(CredentialList value) {
        this.orgCredentialList = value;
    }

}
