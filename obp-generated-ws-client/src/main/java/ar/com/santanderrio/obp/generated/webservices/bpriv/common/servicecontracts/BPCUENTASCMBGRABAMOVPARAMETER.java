
package ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.parameters.BaseParameter;


/**
 * <p>Java class for BP_CUENTAS_CMB_GRABAMOV_PARAMETER complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BP_CUENTAS_CMB_GRABAMOV_PARAMETER">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters}BaseParameter">
 *       &lt;sequence>
 *         &lt;element name="AceptaBatch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodAltair" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CuentaAltair" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="DivisaAltair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaValor" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Modo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MovObservacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NUP" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="NuApunte" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PasswordSesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalAltair" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TipoMovimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsuarioSesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BP_CUENTAS_CMB_GRABAMOV_PARAMETER", propOrder = {
    "aceptaBatch",
    "codAltair",
    "cuentaAltair",
    "divisaAltair",
    "fechaValor",
    "importe",
    "modo",
    "movObservacion",
    "nup",
    "nuApunte",
    "origen",
    "passwordSesion",
    "sucursalAltair",
    "tipoMovimiento",
    "usuarioSesion"
})
public class BPCUENTASCMBGRABAMOVPARAMETER
    extends BaseParameter
{

    @XmlElementRef(name = "AceptaBatch", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> aceptaBatch;
    @XmlElementRef(name = "CodAltair", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> codAltair;
    @XmlElementRef(name = "CuentaAltair", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> cuentaAltair;
    @XmlElementRef(name = "DivisaAltair", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> divisaAltair;
    @XmlElement(name = "FechaValor")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaValor;
    @XmlElementRef(name = "Importe", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> importe;
    @XmlElementRef(name = "Modo", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> modo;
    @XmlElementRef(name = "MovObservacion", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> movObservacion;
    @XmlElement(name = "NUP")
    protected BigDecimal nup;
    @XmlElementRef(name = "NuApunte", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> nuApunte;
    @XmlElementRef(name = "Origen", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> origen;
    @XmlElementRef(name = "PasswordSesion", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> passwordSesion;
    @XmlElementRef(name = "SucursalAltair", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> sucursalAltair;
    @XmlElementRef(name = "TipoMovimiento", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> tipoMovimiento;
    @XmlElementRef(name = "UsuarioSesion", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> usuarioSesion;

    /**
     * Gets the value of the aceptaBatch property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAceptaBatch() {
        return aceptaBatch;
    }

    /**
     * Sets the value of the aceptaBatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAceptaBatch(JAXBElement<String> value) {
        this.aceptaBatch = value;
    }

    /**
     * Gets the value of the codAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCodAltair() {
        return codAltair;
    }

    /**
     * Sets the value of the codAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCodAltair(JAXBElement<BigDecimal> value) {
        this.codAltair = value;
    }

    /**
     * Gets the value of the cuentaAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCuentaAltair() {
        return cuentaAltair;
    }

    /**
     * Sets the value of the cuentaAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCuentaAltair(JAXBElement<BigDecimal> value) {
        this.cuentaAltair = value;
    }

    /**
     * Gets the value of the divisaAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDivisaAltair() {
        return divisaAltair;
    }

    /**
     * Sets the value of the divisaAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDivisaAltair(JAXBElement<String> value) {
        this.divisaAltair = value;
    }

    /**
     * Gets the value of the fechaValor property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaValor() {
        return fechaValor;
    }

    /**
     * Sets the value of the fechaValor property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaValor(XMLGregorianCalendar value) {
        this.fechaValor = value;
    }

    /**
     * Gets the value of the importe property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getImporte() {
        return importe;
    }

    /**
     * Sets the value of the importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setImporte(JAXBElement<BigDecimal> value) {
        this.importe = value;
    }

    /**
     * Gets the value of the modo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getModo() {
        return modo;
    }

    /**
     * Sets the value of the modo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setModo(JAXBElement<String> value) {
        this.modo = value;
    }

    /**
     * Gets the value of the movObservacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMovObservacion() {
        return movObservacion;
    }

    /**
     * Sets the value of the movObservacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMovObservacion(JAXBElement<String> value) {
        this.movObservacion = value;
    }

    /**
     * Gets the value of the nup property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNUP() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNUP(BigDecimal value) {
        this.nup = value;
    }

    /**
     * Gets the value of the nuApunte property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getNuApunte() {
        return nuApunte;
    }

    /**
     * Sets the value of the nuApunte property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setNuApunte(JAXBElement<BigDecimal> value) {
        this.nuApunte = value;
    }

    /**
     * Gets the value of the origen property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrigen() {
        return origen;
    }

    /**
     * Sets the value of the origen property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrigen(JAXBElement<String> value) {
        this.origen = value;
    }

    /**
     * Gets the value of the passwordSesion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPasswordSesion() {
        return passwordSesion;
    }

    /**
     * Sets the value of the passwordSesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPasswordSesion(JAXBElement<String> value) {
        this.passwordSesion = value;
    }

    /**
     * Gets the value of the sucursalAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getSucursalAltair() {
        return sucursalAltair;
    }

    /**
     * Sets the value of the sucursalAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setSucursalAltair(JAXBElement<BigDecimal> value) {
        this.sucursalAltair = value;
    }

    /**
     * Gets the value of the tipoMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoMovimiento() {
        return tipoMovimiento;
    }

    /**
     * Sets the value of the tipoMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoMovimiento(JAXBElement<String> value) {
        this.tipoMovimiento = value;
    }

    /**
     * Gets the value of the usuarioSesion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsuarioSesion() {
        return usuarioSesion;
    }

    /**
     * Sets the value of the usuarioSesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsuarioSesion(JAXBElement<String> value) {
        this.usuarioSesion = value;
    }

}
