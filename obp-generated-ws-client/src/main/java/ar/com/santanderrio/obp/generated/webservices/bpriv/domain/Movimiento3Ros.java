
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Movimiento3ros complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Movimiento3ros">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_x003C_CONCEPTO_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_CUENTA_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_DIVISA_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_EGRESOS_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="_x003C_FECHA_OPER_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="_x003C_FECHA_VALOR_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="_x003C_INDICAR_MOVIMIENTO_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_INGRESOS_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="_x003C_NROMOV_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_SUCURSAL_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_TEXTO_DEL_APUNTE_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Movimiento3ros", propOrder = {
    "x003CCONCEPTOX003EKBackingField",
    "x003CCUENTAX003EKBackingField",
    "x003CDIVISAX003EKBackingField",
    "x003CEGRESOSX003EKBackingField",
    "x003CFECHAOPERX003EKBackingField",
    "x003CFECHAVALORX003EKBackingField",
    "x003CINDICARMOVIMIENTOX003EKBackingField",
    "x003CINGRESOSX003EKBackingField",
    "x003CNROMOVX003EKBackingField",
    "x003CSUCURSALX003EKBackingField",
    "x003CTEXTODELAPUNTEX003EKBackingField"
})
public class Movimiento3Ros {

    @XmlElement(name = "_x003C_CONCEPTO_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CCONCEPTOX003EKBackingField;
    @XmlElement(name = "_x003C_CUENTA_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CCUENTAX003EKBackingField;
    @XmlElement(name = "_x003C_DIVISA_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CDIVISAX003EKBackingField;
    @XmlElement(name = "_x003C_EGRESOS_x003E_k__BackingField", required = true, nillable = true)
    protected BigDecimal x003CEGRESOSX003EKBackingField;
    @XmlElement(name = "_x003C_FECHA_OPER_x003E_k__BackingField", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar x003CFECHAOPERX003EKBackingField;
    @XmlElement(name = "_x003C_FECHA_VALOR_x003E_k__BackingField", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar x003CFECHAVALORX003EKBackingField;
    @XmlElement(name = "_x003C_INDICAR_MOVIMIENTO_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CINDICARMOVIMIENTOX003EKBackingField;
    @XmlElement(name = "_x003C_INGRESOS_x003E_k__BackingField", required = true, nillable = true)
    protected BigDecimal x003CINGRESOSX003EKBackingField;
    @XmlElement(name = "_x003C_NROMOV_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CNROMOVX003EKBackingField;
    @XmlElement(name = "_x003C_SUCURSAL_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CSUCURSALX003EKBackingField;
    @XmlElement(name = "_x003C_TEXTO_DEL_APUNTE_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CTEXTODELAPUNTEX003EKBackingField;

    /**
     * Gets the value of the x003CCONCEPTOX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CCONCEPTOX003EKBackingField() {
        return x003CCONCEPTOX003EKBackingField;
    }

    /**
     * Sets the value of the x003CCONCEPTOX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CCONCEPTOX003EKBackingField(String value) {
        this.x003CCONCEPTOX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CCUENTAX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CCUENTAX003EKBackingField() {
        return x003CCUENTAX003EKBackingField;
    }

    /**
     * Sets the value of the x003CCUENTAX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CCUENTAX003EKBackingField(String value) {
        this.x003CCUENTAX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CDIVISAX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CDIVISAX003EKBackingField() {
        return x003CDIVISAX003EKBackingField;
    }

    /**
     * Sets the value of the x003CDIVISAX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CDIVISAX003EKBackingField(String value) {
        this.x003CDIVISAX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CEGRESOSX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX003CEGRESOSX003EKBackingField() {
        return x003CEGRESOSX003EKBackingField;
    }

    /**
     * Sets the value of the x003CEGRESOSX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX003CEGRESOSX003EKBackingField(BigDecimal value) {
        this.x003CEGRESOSX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CFECHAOPERX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getX003CFECHAOPERX003EKBackingField() {
        return x003CFECHAOPERX003EKBackingField;
    }

    /**
     * Sets the value of the x003CFECHAOPERX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setX003CFECHAOPERX003EKBackingField(XMLGregorianCalendar value) {
        this.x003CFECHAOPERX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CFECHAVALORX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getX003CFECHAVALORX003EKBackingField() {
        return x003CFECHAVALORX003EKBackingField;
    }

    /**
     * Sets the value of the x003CFECHAVALORX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setX003CFECHAVALORX003EKBackingField(XMLGregorianCalendar value) {
        this.x003CFECHAVALORX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CINDICARMOVIMIENTOX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CINDICARMOVIMIENTOX003EKBackingField() {
        return x003CINDICARMOVIMIENTOX003EKBackingField;
    }

    /**
     * Sets the value of the x003CINDICARMOVIMIENTOX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CINDICARMOVIMIENTOX003EKBackingField(String value) {
        this.x003CINDICARMOVIMIENTOX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CINGRESOSX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX003CINGRESOSX003EKBackingField() {
        return x003CINGRESOSX003EKBackingField;
    }

    /**
     * Sets the value of the x003CINGRESOSX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX003CINGRESOSX003EKBackingField(BigDecimal value) {
        this.x003CINGRESOSX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CNROMOVX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CNROMOVX003EKBackingField() {
        return x003CNROMOVX003EKBackingField;
    }

    /**
     * Sets the value of the x003CNROMOVX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CNROMOVX003EKBackingField(String value) {
        this.x003CNROMOVX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CSUCURSALX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CSUCURSALX003EKBackingField() {
        return x003CSUCURSALX003EKBackingField;
    }

    /**
     * Sets the value of the x003CSUCURSALX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CSUCURSALX003EKBackingField(String value) {
        this.x003CSUCURSALX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CTEXTODELAPUNTEX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CTEXTODELAPUNTEX003EKBackingField() {
        return x003CTEXTODELAPUNTEX003EKBackingField;
    }

    /**
     * Sets the value of the x003CTEXTODELAPUNTEX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CTEXTODELAPUNTEX003EKBackingField(String value) {
        this.x003CTEXTODELAPUNTEX003EKBackingField = value;
    }

}
