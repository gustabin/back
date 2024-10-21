
package ar.com.santanderrio.obp.generated.webservices.echeq;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddAccountAndIssue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddAccountAndIssue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sucursal_codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal_nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal_domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal_cp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal_provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_cuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_razon_social" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_cbu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_subcuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_cuenta_fecha_alta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_cp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_emails" type="{http://echeq.amco.com.ar/}Email" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="numero_chequera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_pago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario_documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario_email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firmantes" type="{http://echeq.amco.com.ar/}Firmante" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="numeracion" type="{http://echeq.amco.com.ar/}Numeracion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cheque_version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_instrumento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_caracter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_motivo_pago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_cruzado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="chq_referencias_pago" type="{http://echeq.amco.com.ar/}Referencia" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="trk_cnl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trk_scnl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trk_jsessionid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddAccountAndIssue", propOrder = {
    "sucursalCodigo",
    "sucursalNombre",
    "sucursalDomicilio",
    "sucursalCp",
    "sucursalProvincia",
    "emisorCuit",
    "emisorRazonSocial",
    "emisorCbu",
    "emisorCuenta",
    "emisorSubcuenta",
    "emisorCuentaFechaAlta",
    "emisorMoneda",
    "emisorDomicilio",
    "emisorCp",
    "emisorEmails",
    "numeroChequera",
    "fechaPago",
    "monto",
    "beneficiarioDocumentoTipo",
    "beneficiarioDocumento",
    "beneficiarioEmail",
    "firmantes",
    "numeracion",
    "chequeVersion",
    "chequeInstrumentoTipo",
    "chequeTipo",
    "chequeCaracter",
    "chequeMotivoPago",
    "chequeConcepto",
    "chequeCruzado",
    "chqReferenciasPago",
    "trkCnl",
    "trkScnl",
    "trkJsessionid"
})
public class AddAccountAndIssue {

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
    @XmlElement(name = "emisor_cbu")
    protected String emisorCbu;
    @XmlElement(name = "emisor_cuenta")
    protected String emisorCuenta;
    @XmlElement(name = "emisor_subcuenta")
    protected String emisorSubcuenta;
    @XmlElement(name = "emisor_cuenta_fecha_alta")
    protected String emisorCuentaFechaAlta;
    @XmlElement(name = "emisor_moneda")
    protected String emisorMoneda;
    @XmlElement(name = "emisor_domicilio")
    protected String emisorDomicilio;
    @XmlElement(name = "emisor_cp")
    protected String emisorCp;
    @XmlElement(name = "emisor_emails")
    protected List<Email> emisorEmails;
    @XmlElement(name = "numero_chequera")
    protected String numeroChequera;
    @XmlElement(name = "fecha_pago")
    protected String fechaPago;
    protected String monto;
    @XmlElement(name = "beneficiario_documento_tipo")
    protected String beneficiarioDocumentoTipo;
    @XmlElement(name = "beneficiario_documento")
    protected String beneficiarioDocumento;
    @XmlElement(name = "beneficiario_email")
    protected String beneficiarioEmail;
    protected List<Firmante> firmantes;
    protected List<Numeracion> numeracion;
    @XmlElement(name = "cheque_version")
    protected String chequeVersion;
    @XmlElement(name = "cheque_instrumento_tipo")
    protected String chequeInstrumentoTipo;
    @XmlElement(name = "cheque_tipo")
    protected String chequeTipo;
    @XmlElement(name = "cheque_caracter")
    protected String chequeCaracter;
    @XmlElement(name = "cheque_motivo_pago")
    protected String chequeMotivoPago;
    @XmlElement(name = "cheque_concepto")
    protected String chequeConcepto;
    @XmlElement(name = "cheque_cruzado")
    protected Boolean chequeCruzado;
    @XmlElement(name = "chq_referencias_pago")
    protected List<Referencia> chqReferenciasPago;
    @XmlElement(name = "trk_cnl")
    protected String trkCnl;
    @XmlElement(name = "trk_scnl")
    protected String trkScnl;
    @XmlElement(name = "trk_jsessionid")
    protected String trkJsessionid;

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
     * Gets the value of the emisorEmails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emisorEmails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmisorEmails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Email }
     * 
     * 
     */
    public List<Email> getEmisorEmails() {
        if (emisorEmails == null) {
            emisorEmails = new ArrayList<Email>();
        }
        return this.emisorEmails;
    }

    /**
     * Gets the value of the numeroChequera property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroChequera() {
        return numeroChequera;
    }

    /**
     * Sets the value of the numeroChequera property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroChequera(String value) {
        this.numeroChequera = value;
    }

    /**
     * Gets the value of the fechaPago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * Sets the value of the fechaPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPago(String value) {
        this.fechaPago = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Sets the value of the monto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonto(String value) {
        this.monto = value;
    }

    /**
     * Gets the value of the beneficiarioDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioDocumentoTipo() {
        return beneficiarioDocumentoTipo;
    }

    /**
     * Sets the value of the beneficiarioDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioDocumentoTipo(String value) {
        this.beneficiarioDocumentoTipo = value;
    }

    /**
     * Gets the value of the beneficiarioDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioDocumento() {
        return beneficiarioDocumento;
    }

    /**
     * Sets the value of the beneficiarioDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioDocumento(String value) {
        this.beneficiarioDocumento = value;
    }

    /**
     * Gets the value of the beneficiarioEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioEmail() {
        return beneficiarioEmail;
    }

    /**
     * Sets the value of the beneficiarioEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioEmail(String value) {
        this.beneficiarioEmail = value;
    }

    /**
     * Gets the value of the firmantes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the firmantes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFirmantes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Firmante }
     * 
     * 
     */
    public List<Firmante> getFirmantes() {
        if (firmantes == null) {
            firmantes = new ArrayList<Firmante>();
        }
        return this.firmantes;
    }

    /**
     * Gets the value of the numeracion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numeracion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNumeracion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Numeracion }
     * 
     * 
     */
    public List<Numeracion> getNumeracion() {
        if (numeracion == null) {
            numeracion = new ArrayList<Numeracion>();
        }
        return this.numeracion;
    }

    /**
     * Gets the value of the chequeVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeVersion() {
        return chequeVersion;
    }

    /**
     * Sets the value of the chequeVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeVersion(String value) {
        this.chequeVersion = value;
    }

    /**
     * Gets the value of the chequeInstrumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeInstrumentoTipo() {
        return chequeInstrumentoTipo;
    }

    /**
     * Sets the value of the chequeInstrumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeInstrumentoTipo(String value) {
        this.chequeInstrumentoTipo = value;
    }

    /**
     * Gets the value of the chequeTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeTipo() {
        return chequeTipo;
    }

    /**
     * Sets the value of the chequeTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeTipo(String value) {
        this.chequeTipo = value;
    }

    /**
     * Gets the value of the chequeCaracter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeCaracter() {
        return chequeCaracter;
    }

    /**
     * Sets the value of the chequeCaracter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeCaracter(String value) {
        this.chequeCaracter = value;
    }

    /**
     * Gets the value of the chequeMotivoPago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeMotivoPago() {
        return chequeMotivoPago;
    }

    /**
     * Sets the value of the chequeMotivoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeMotivoPago(String value) {
        this.chequeMotivoPago = value;
    }

    /**
     * Gets the value of the chequeConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeConcepto() {
        return chequeConcepto;
    }

    /**
     * Sets the value of the chequeConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeConcepto(String value) {
        this.chequeConcepto = value;
    }

    /**
     * Gets the value of the chequeCruzado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChequeCruzado() {
        return chequeCruzado;
    }

    /**
     * Sets the value of the chequeCruzado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChequeCruzado(Boolean value) {
        this.chequeCruzado = value;
    }

    /**
     * Gets the value of the chqReferenciasPago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chqReferenciasPago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChqReferenciasPago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Referencia }
     * 
     * 
     */
    public List<Referencia> getChqReferenciasPago() {
        if (chqReferenciasPago == null) {
            chqReferenciasPago = new ArrayList<Referencia>();
        }
        return this.chqReferenciasPago;
    }

    /**
     * Gets the value of the trkCnl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrkCnl() {
        return trkCnl;
    }

    /**
     * Sets the value of the trkCnl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrkCnl(String value) {
        this.trkCnl = value;
    }

    /**
     * Gets the value of the trkScnl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrkScnl() {
        return trkScnl;
    }

    /**
     * Sets the value of the trkScnl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrkScnl(String value) {
        this.trkScnl = value;
    }

    /**
     * Gets the value of the trkJsessionid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrkJsessionid() {
        return trkJsessionid;
    }

    /**
     * Sets the value of the trkJsessionid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrkJsessionid(String value) {
        this.trkJsessionid = value;
    }

}
