
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LoadRossi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoadRossi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_x003C_ASESOR_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_x003C_BANELCO_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_CUENTA_ALTAIR_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_x003C_CUENTA_ROSSI_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="_x003C_DIVISA_ALTAIR_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_DIVISA_ROSSI_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_EMP_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_FECHA_BAJA_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="_x003C_PEP_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_PRODUCTO_ALTAIR_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_SUBPRODUCTO_ALTAIR_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_x003C_SUCURSAL_ALTAIR_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="_x003C_SUCURSAL_ROSSI_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoadRossi", propOrder = {
    "x003CASESORX003EKBackingField",
    "x003CBANELCOX003EKBackingField",
    "x003CCUENTAALTAIRX003EKBackingField",
    "x003CCUENTAROSSIX003EKBackingField",
    "x003CDIVISAALTAIRX003EKBackingField",
    "x003CDIVISAROSSIX003EKBackingField",
    "x003CEMPX003EKBackingField",
    "x003CFECHABAJAX003EKBackingField",
    "x003CPEPX003EKBackingField",
    "x003CPRODUCTOALTAIRX003EKBackingField",
    "x003CSUBPRODUCTOALTAIRX003EKBackingField",
    "x003CSUCURSALALTAIRX003EKBackingField",
    "x003CSUCURSALROSSIX003EKBackingField"
})
public class LoadRossi {

    @XmlElement(name = "_x003C_ASESOR_x003E_k__BackingField", required = true, type = Integer.class, nillable = true)
    protected Integer x003CASESORX003EKBackingField;
    @XmlElement(name = "_x003C_BANELCO_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CBANELCOX003EKBackingField;
    @XmlElement(name = "_x003C_CUENTA_ALTAIR_x003E_k__BackingField", required = true, type = Long.class, nillable = true)
    protected Long x003CCUENTAALTAIRX003EKBackingField;
    @XmlElement(name = "_x003C_CUENTA_ROSSI_x003E_k__BackingField", required = true, nillable = true)
    protected BigDecimal x003CCUENTAROSSIX003EKBackingField;
    @XmlElement(name = "_x003C_DIVISA_ALTAIR_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CDIVISAALTAIRX003EKBackingField;
    @XmlElement(name = "_x003C_DIVISA_ROSSI_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CDIVISAROSSIX003EKBackingField;
    @XmlElement(name = "_x003C_EMP_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CEMPX003EKBackingField;
    @XmlElement(name = "_x003C_FECHA_BAJA_x003E_k__BackingField", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar x003CFECHABAJAX003EKBackingField;
    @XmlElement(name = "_x003C_PEP_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CPEPX003EKBackingField;
    @XmlElement(name = "_x003C_PRODUCTO_ALTAIR_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CPRODUCTOALTAIRX003EKBackingField;
    @XmlElement(name = "_x003C_SUBPRODUCTO_ALTAIR_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CSUBPRODUCTOALTAIRX003EKBackingField;
    @XmlElement(name = "_x003C_SUCURSAL_ALTAIR_x003E_k__BackingField", required = true, type = Short.class, nillable = true)
    protected Short x003CSUCURSALALTAIRX003EKBackingField;
    @XmlElement(name = "_x003C_SUCURSAL_ROSSI_x003E_k__BackingField", required = true, type = Short.class, nillable = true)
    protected Short x003CSUCURSALROSSIX003EKBackingField;

    /**
     * Gets the value of the x003CASESORX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getX003CASESORX003EKBackingField() {
        return x003CASESORX003EKBackingField;
    }

    /**
     * Sets the value of the x003CASESORX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setX003CASESORX003EKBackingField(Integer value) {
        this.x003CASESORX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CBANELCOX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CBANELCOX003EKBackingField() {
        return x003CBANELCOX003EKBackingField;
    }

    /**
     * Sets the value of the x003CBANELCOX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CBANELCOX003EKBackingField(String value) {
        this.x003CBANELCOX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CCUENTAALTAIRX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getX003CCUENTAALTAIRX003EKBackingField() {
        return x003CCUENTAALTAIRX003EKBackingField;
    }

    /**
     * Sets the value of the x003CCUENTAALTAIRX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setX003CCUENTAALTAIRX003EKBackingField(Long value) {
        this.x003CCUENTAALTAIRX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CCUENTAROSSIX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX003CCUENTAROSSIX003EKBackingField() {
        return x003CCUENTAROSSIX003EKBackingField;
    }

    /**
     * Sets the value of the x003CCUENTAROSSIX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX003CCUENTAROSSIX003EKBackingField(BigDecimal value) {
        this.x003CCUENTAROSSIX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CDIVISAALTAIRX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CDIVISAALTAIRX003EKBackingField() {
        return x003CDIVISAALTAIRX003EKBackingField;
    }

    /**
     * Sets the value of the x003CDIVISAALTAIRX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CDIVISAALTAIRX003EKBackingField(String value) {
        this.x003CDIVISAALTAIRX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CDIVISAROSSIX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CDIVISAROSSIX003EKBackingField() {
        return x003CDIVISAROSSIX003EKBackingField;
    }

    /**
     * Sets the value of the x003CDIVISAROSSIX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CDIVISAROSSIX003EKBackingField(String value) {
        this.x003CDIVISAROSSIX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CEMPX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CEMPX003EKBackingField() {
        return x003CEMPX003EKBackingField;
    }

    /**
     * Sets the value of the x003CEMPX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CEMPX003EKBackingField(String value) {
        this.x003CEMPX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CFECHABAJAX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getX003CFECHABAJAX003EKBackingField() {
        return x003CFECHABAJAX003EKBackingField;
    }

    /**
     * Sets the value of the x003CFECHABAJAX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setX003CFECHABAJAX003EKBackingField(XMLGregorianCalendar value) {
        this.x003CFECHABAJAX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CPEPX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CPEPX003EKBackingField() {
        return x003CPEPX003EKBackingField;
    }

    /**
     * Sets the value of the x003CPEPX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CPEPX003EKBackingField(String value) {
        this.x003CPEPX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CPRODUCTOALTAIRX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CPRODUCTOALTAIRX003EKBackingField() {
        return x003CPRODUCTOALTAIRX003EKBackingField;
    }

    /**
     * Sets the value of the x003CPRODUCTOALTAIRX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CPRODUCTOALTAIRX003EKBackingField(String value) {
        this.x003CPRODUCTOALTAIRX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CSUBPRODUCTOALTAIRX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CSUBPRODUCTOALTAIRX003EKBackingField() {
        return x003CSUBPRODUCTOALTAIRX003EKBackingField;
    }

    /**
     * Sets the value of the x003CSUBPRODUCTOALTAIRX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CSUBPRODUCTOALTAIRX003EKBackingField(String value) {
        this.x003CSUBPRODUCTOALTAIRX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CSUCURSALALTAIRX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getX003CSUCURSALALTAIRX003EKBackingField() {
        return x003CSUCURSALALTAIRX003EKBackingField;
    }

    /**
     * Sets the value of the x003CSUCURSALALTAIRX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setX003CSUCURSALALTAIRX003EKBackingField(Short value) {
        this.x003CSUCURSALALTAIRX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CSUCURSALROSSIX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getX003CSUCURSALROSSIX003EKBackingField() {
        return x003CSUCURSALROSSIX003EKBackingField;
    }

    /**
     * Sets the value of the x003CSUCURSALROSSIX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setX003CSUCURSALROSSIX003EKBackingField(Short value) {
        this.x003CSUCURSALROSSIX003EKBackingField = value;
    }

}
