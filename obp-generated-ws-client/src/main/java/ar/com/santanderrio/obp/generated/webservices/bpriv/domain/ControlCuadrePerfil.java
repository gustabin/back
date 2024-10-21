
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ControlCuadrePerfil complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ControlCuadrePerfil">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_x003C_Control_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="_x003C_Desvio_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="_x003C_Disclaimer_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_Incidente_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_MaxDesvio_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_MaxPorcentaje_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_Porcentaje_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ControlCuadrePerfil", propOrder = {
    "x003CControlX003EKBackingField",
    "x003CDesvioX003EKBackingField",
    "x003CDisclaimerX003EKBackingField",
    "x003CIncidenteX003EKBackingField",
    "x003CMaxDesvioX003EKBackingField",
    "x003CMaxPorcentajeX003EKBackingField",
    "x003CPorcentajeX003EKBackingField"
})
public class ControlCuadrePerfil {

    @XmlElement(name = "_x003C_Control_x003E_k__BackingField", required = true, nillable = true)
    protected BigDecimal x003CControlX003EKBackingField;
    @XmlElement(name = "_x003C_Desvio_x003E_k__BackingField", required = true, nillable = true)
    protected BigDecimal x003CDesvioX003EKBackingField;
    @XmlElement(name = "_x003C_Disclaimer_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CDisclaimerX003EKBackingField;
    @XmlElement(name = "_x003C_Incidente_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CIncidenteX003EKBackingField;
    @XmlElement(name = "_x003C_MaxDesvio_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CMaxDesvioX003EKBackingField;
    @XmlElement(name = "_x003C_MaxPorcentaje_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CMaxPorcentajeX003EKBackingField;
    @XmlElement(name = "_x003C_Porcentaje_x003E_k__BackingField", required = true, nillable = true)
    protected BigDecimal x003CPorcentajeX003EKBackingField;

    /**
     * Gets the value of the x003CControlX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX003CControlX003EKBackingField() {
        return x003CControlX003EKBackingField;
    }

    /**
     * Sets the value of the x003CControlX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX003CControlX003EKBackingField(BigDecimal value) {
        this.x003CControlX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CDesvioX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX003CDesvioX003EKBackingField() {
        return x003CDesvioX003EKBackingField;
    }

    /**
     * Sets the value of the x003CDesvioX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX003CDesvioX003EKBackingField(BigDecimal value) {
        this.x003CDesvioX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CDisclaimerX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CDisclaimerX003EKBackingField() {
        return x003CDisclaimerX003EKBackingField;
    }

    /**
     * Sets the value of the x003CDisclaimerX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CDisclaimerX003EKBackingField(String value) {
        this.x003CDisclaimerX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CIncidenteX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CIncidenteX003EKBackingField() {
        return x003CIncidenteX003EKBackingField;
    }

    /**
     * Sets the value of the x003CIncidenteX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CIncidenteX003EKBackingField(String value) {
        this.x003CIncidenteX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CMaxDesvioX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CMaxDesvioX003EKBackingField() {
        return x003CMaxDesvioX003EKBackingField;
    }

    /**
     * Sets the value of the x003CMaxDesvioX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CMaxDesvioX003EKBackingField(String value) {
        this.x003CMaxDesvioX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CMaxPorcentajeX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CMaxPorcentajeX003EKBackingField() {
        return x003CMaxPorcentajeX003EKBackingField;
    }

    /**
     * Sets the value of the x003CMaxPorcentajeX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CMaxPorcentajeX003EKBackingField(String value) {
        this.x003CMaxPorcentajeX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CPorcentajeX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX003CPorcentajeX003EKBackingField() {
        return x003CPorcentajeX003EKBackingField;
    }

    /**
     * Sets the value of the x003CPorcentajeX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX003CPorcentajeX003EKBackingField(BigDecimal value) {
        this.x003CPorcentajeX003EKBackingField = value;
    }

}
