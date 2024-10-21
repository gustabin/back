
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GrabamovAltair complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GrabamovAltair">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_x003C_NIO_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_NuApunte_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="_x003C_ResultAltair_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GrabamovAltair", propOrder = {
    "x003CNIOX003EKBackingField",
    "x003CNuApunteX003EKBackingField",
    "x003CResultAltairX003EKBackingField"
})
public class GrabamovAltair {

    @XmlElement(name = "_x003C_NIO_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CNIOX003EKBackingField;
    @XmlElement(name = "_x003C_NuApunte_x003E_k__BackingField", required = true, nillable = true)
    protected BigDecimal x003CNuApunteX003EKBackingField;
    @XmlElement(name = "_x003C_ResultAltair_x003E_k__BackingField", required = true, nillable = true)
    protected BigDecimal x003CResultAltairX003EKBackingField;

    /**
     * Gets the value of the x003CNIOX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CNIOX003EKBackingField() {
        return x003CNIOX003EKBackingField;
    }

    /**
     * Sets the value of the x003CNIOX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CNIOX003EKBackingField(String value) {
        this.x003CNIOX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CNuApunteX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX003CNuApunteX003EKBackingField() {
        return x003CNuApunteX003EKBackingField;
    }

    /**
     * Sets the value of the x003CNuApunteX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX003CNuApunteX003EKBackingField(BigDecimal value) {
        this.x003CNuApunteX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CResultAltairX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX003CResultAltairX003EKBackingField() {
        return x003CResultAltairX003EKBackingField;
    }

    /**
     * Sets the value of the x003CResultAltairX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX003CResultAltairX003EKBackingField(BigDecimal value) {
        this.x003CResultAltairX003EKBackingField = value;
    }

}
