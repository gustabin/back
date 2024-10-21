
package ar.com.santanderrio.obp.generated.webservices.echeq;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Cuenta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Cuenta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="emisor_cbu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="banco_codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_subcuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_cuenta_fecha_alta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal_codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal_nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal_domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal_cp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal_provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_cuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_razon_social" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_cp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuenta_estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuenta_firmantes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_alta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_ult_modificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total_emails" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="emails" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cuenta", propOrder = {
    "emisorCbu",
    "bancoCodigo",
    "emisorCuenta",
    "emisorSubcuenta",
    "emisorMoneda",
    "emisorCuentaFechaAlta",
    "sucursalCodigo",
    "sucursalNombre",
    "sucursalDomicilio",
    "sucursalCp",
    "sucursalProvincia",
    "emisorCuit",
    "emisorRazonSocial",
    "emisorDomicilio",
    "emisorCp",
    "cuentaEstado",
    "cuentaFirmantes",
    "fechaAlta",
    "fechaUltModificacion",
    "totalEmails",
    "emails"
})
public class Cuenta {

    @XmlElement(name = "emisor_cbu")
    protected String emisorCbu;
    @XmlElement(name = "banco_codigo")
    protected String bancoCodigo;
    @XmlElement(name = "emisor_cuenta")
    protected String emisorCuenta;
    @XmlElement(name = "emisor_subcuenta")
    protected String emisorSubcuenta;
    @XmlElement(name = "emisor_moneda")
    protected String emisorMoneda;
    @XmlElement(name = "emisor_cuenta_fecha_alta")
    protected String emisorCuentaFechaAlta;
    @XmlElement(name = "sucursal_codigo")
    protected String sucursalCodigo;
    @XmlElement(name = "sucursal_nombre")
    protected String sucursalNombre;
    @XmlElement(name = "sucursal_domicilio")
    protected String sucursalDomicilio;
    @XmlElement(name = "sucursal_cp")
    protected String sucursalCp;
    @XmlElement(name = "sucursal_provincia")
    protected String sucursalProvincia;
    @XmlElement(name = "emisor_cuit")
    protected String emisorCuit;
    @XmlElement(name = "emisor_razon_social")
    protected String emisorRazonSocial;
    @XmlElement(name = "emisor_domicilio")
    protected String emisorDomicilio;
    @XmlElement(name = "emisor_cp")
    protected String emisorCp;
    @XmlElement(name = "cuenta_estado")
    protected String cuentaEstado;
    @XmlElement(name = "cuenta_firmantes")
    protected String cuentaFirmantes;
    @XmlElement(name = "fecha_alta")
    protected String fechaAlta;
    @XmlElement(name = "fecha_ult_modificacion")
    protected String fechaUltModificacion;
    @XmlElement(name = "total_emails")
    protected Double totalEmails;
    @XmlElement(nillable = true)
    protected List<String> emails;

    /**
     * Gets the value of the emisorCbu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorCbu() {
        return emisorCbu;
    }

    /**
     * Sets the value of the emisorCbu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorCbu(String value) {
        this.emisorCbu = value;
    }

    /**
     * Gets the value of the bancoCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBancoCodigo() {
        return bancoCodigo;
    }

    /**
     * Sets the value of the bancoCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBancoCodigo(String value) {
        this.bancoCodigo = value;
    }

    /**
     * Gets the value of the emisorCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorCuenta() {
        return emisorCuenta;
    }

    /**
     * Sets the value of the emisorCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorCuenta(String value) {
        this.emisorCuenta = value;
    }

    /**
     * Gets the value of the emisorSubcuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorSubcuenta() {
        return emisorSubcuenta;
    }

    /**
     * Sets the value of the emisorSubcuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorSubcuenta(String value) {
        this.emisorSubcuenta = value;
    }

    /**
     * Gets the value of the emisorMoneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorMoneda() {
        return emisorMoneda;
    }

    /**
     * Sets the value of the emisorMoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorMoneda(String value) {
        this.emisorMoneda = value;
    }

    /**
     * Gets the value of the emisorCuentaFechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorCuentaFechaAlta() {
        return emisorCuentaFechaAlta;
    }

    /**
     * Sets the value of the emisorCuentaFechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorCuentaFechaAlta(String value) {
        this.emisorCuentaFechaAlta = value;
    }

    /**
     * Gets the value of the sucursalCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucursalCodigo() {
        return sucursalCodigo;
    }

    /**
     * Sets the value of the sucursalCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucursalCodigo(String value) {
        this.sucursalCodigo = value;
    }

    /**
     * Gets the value of the sucursalNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucursalNombre() {
        return sucursalNombre;
    }

    /**
     * Sets the value of the sucursalNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucursalNombre(String value) {
        this.sucursalNombre = value;
    }

    /**
     * Gets the value of the sucursalDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucursalDomicilio() {
        return sucursalDomicilio;
    }

    /**
     * Sets the value of the sucursalDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucursalDomicilio(String value) {
        this.sucursalDomicilio = value;
    }

    /**
     * Gets the value of the sucursalCp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucursalCp() {
        return sucursalCp;
    }

    /**
     * Sets the value of the sucursalCp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucursalCp(String value) {
        this.sucursalCp = value;
    }

    /**
     * Gets the value of the sucursalProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucursalProvincia() {
        return sucursalProvincia;
    }

    /**
     * Sets the value of the sucursalProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucursalProvincia(String value) {
        this.sucursalProvincia = value;
    }

    /**
     * Gets the value of the emisorCuit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorCuit() {
        return emisorCuit;
    }

    /**
     * Sets the value of the emisorCuit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorCuit(String value) {
        this.emisorCuit = value;
    }

    /**
     * Gets the value of the emisorRazonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorRazonSocial() {
        return emisorRazonSocial;
    }

    /**
     * Sets the value of the emisorRazonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorRazonSocial(String value) {
        this.emisorRazonSocial = value;
    }

    /**
     * Gets the value of the emisorDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorDomicilio() {
        return emisorDomicilio;
    }

    /**
     * Sets the value of the emisorDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorDomicilio(String value) {
        this.emisorDomicilio = value;
    }

    /**
     * Gets the value of the emisorCp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorCp() {
        return emisorCp;
    }

    /**
     * Sets the value of the emisorCp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorCp(String value) {
        this.emisorCp = value;
    }

    /**
     * Gets the value of the cuentaEstado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaEstado() {
        return cuentaEstado;
    }

    /**
     * Sets the value of the cuentaEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaEstado(String value) {
        this.cuentaEstado = value;
    }

    /**
     * Gets the value of the cuentaFirmantes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaFirmantes() {
        return cuentaFirmantes;
    }

    /**
     * Sets the value of the cuentaFirmantes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaFirmantes(String value) {
        this.cuentaFirmantes = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAlta(String value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the fechaUltModificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaUltModificacion() {
        return fechaUltModificacion;
    }

    /**
     * Sets the value of the fechaUltModificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaUltModificacion(String value) {
        this.fechaUltModificacion = value;
    }

    /**
     * Gets the value of the totalEmails property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalEmails() {
        return totalEmails;
    }

    /**
     * Sets the value of the totalEmails property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalEmails(Double value) {
        this.totalEmails = value;
    }

    /**
     * Gets the value of the emails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEmails() {
        if (emails == null) {
            emails = new ArrayList<String>();
        }
        return this.emails;
    }

}
