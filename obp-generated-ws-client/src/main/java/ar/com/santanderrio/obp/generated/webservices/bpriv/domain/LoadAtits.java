
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoadAtits complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoadAtits">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_x003C_CNO_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="_x003C_CUENTA_ATIT_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_x003C_CUENTA_BP_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoadAtits", propOrder = {
    "x003CCNOX003EKBackingField",
    "x003CCUENTAATITX003EKBackingField",
    "x003CCUENTABPX003EKBackingField"
})
public class LoadAtits {

    @XmlElement(name = "_x003C_CNO_x003E_k__BackingField", required = true)
    protected BigDecimal x003CCNOX003EKBackingField;
    @XmlElement(name = "_x003C_CUENTA_ATIT_x003E_k__BackingField", required = true, type = Long.class, nillable = true)
    protected Long x003CCUENTAATITX003EKBackingField;
    @XmlElement(name = "_x003C_CUENTA_BP_x003E_k__BackingField", required = true, type = Long.class, nillable = true)
    protected Long x003CCUENTABPX003EKBackingField;

    /**
     * Gets the value of the x003CCNOX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX003CCNOX003EKBackingField() {
        return x003CCNOX003EKBackingField;
    }

    /**
     * Sets the value of the x003CCNOX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX003CCNOX003EKBackingField(BigDecimal value) {
        this.x003CCNOX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CCUENTAATITX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getX003CCUENTAATITX003EKBackingField() {
        return x003CCUENTAATITX003EKBackingField;
    }

    /**
     * Sets the value of the x003CCUENTAATITX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setX003CCUENTAATITX003EKBackingField(Long value) {
        this.x003CCUENTAATITX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CCUENTABPX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getX003CCUENTABPX003EKBackingField() {
        return x003CCUENTABPX003EKBackingField;
    }

    /**
     * Sets the value of the x003CCUENTABPX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setX003CCUENTABPX003EKBackingField(Long value) {
        this.x003CCUENTABPX003EKBackingField = value;
    }

}
