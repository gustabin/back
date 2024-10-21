
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This object defines the contact information for a user
 * 
 * <p>Java class for PersonInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ssnInfo" type="{http://ws.kba.csd.rsa.com}SSNInfo" minOccurs="0"/>
 *         &lt;element name="nameInfo" type="{http://ws.kba.csd.rsa.com}NameInfo" minOccurs="0"/>
 *         &lt;element name="addressInfo" type="{http://ws.kba.csd.rsa.com}AddressInfo" minOccurs="0"/>
 *         &lt;element name="birthdayInfo" type="{http://ws.kba.csd.rsa.com}BirthdayInfo" minOccurs="0"/>
 *         &lt;element name="languageInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonInfo", namespace = "http://ws.kba.csd.rsa.com", propOrder = {
    "ssnInfo",
    "nameInfo",
    "addressInfo",
    "birthdayInfo",
    "languageInfo"
})
public class PersonInfo {

    protected SSNInfo ssnInfo;
    protected NameInfo nameInfo;
    protected AddressInfo addressInfo;
    protected BirthdayInfo birthdayInfo;
    protected String languageInfo;

    /**
     * Gets the value of the ssnInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SSNInfo }
     *     
     */
    public SSNInfo getSsnInfo() {
        return ssnInfo;
    }

    /**
     * Sets the value of the ssnInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SSNInfo }
     *     
     */
    public void setSsnInfo(SSNInfo value) {
        this.ssnInfo = value;
    }

    /**
     * Gets the value of the nameInfo property.
     * 
     * @return
     *     possible object is
     *     {@link NameInfo }
     *     
     */
    public NameInfo getNameInfo() {
        return nameInfo;
    }

    /**
     * Sets the value of the nameInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameInfo }
     *     
     */
    public void setNameInfo(NameInfo value) {
        this.nameInfo = value;
    }

    /**
     * Gets the value of the addressInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AddressInfo }
     *     
     */
    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    /**
     * Sets the value of the addressInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressInfo }
     *     
     */
    public void setAddressInfo(AddressInfo value) {
        this.addressInfo = value;
    }

    /**
     * Gets the value of the birthdayInfo property.
     * 
     * @return
     *     possible object is
     *     {@link BirthdayInfo }
     *     
     */
    public BirthdayInfo getBirthdayInfo() {
        return birthdayInfo;
    }

    /**
     * Sets the value of the birthdayInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BirthdayInfo }
     *     
     */
    public void setBirthdayInfo(BirthdayInfo value) {
        this.birthdayInfo = value;
    }

    /**
     * Gets the value of the languageInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageInfo() {
        return languageInfo;
    }

    /**
     * Sets the value of the languageInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageInfo(String value) {
        this.languageInfo = value;
    }

}
