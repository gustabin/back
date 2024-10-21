
package ar.com.santanderrio.obp.generated.webservices.echeq;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IssueECHEQ complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IssueECHEQ">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numero_chequera" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emisor_cuit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emisor_cbu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecha_pago" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="beneficiario_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="beneficiario_documento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="beneficiario_email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firmantes" type="{http://echeq.amco.com.ar/}Firmante" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="numeracion" type="{http://echeq.amco.com.ar/}Numeracion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cheque_version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cheque_instrumento_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cheque_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cheque_caracter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cheque_motivo_pago" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cheque_concepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cheque_cruzado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "IssueECHEQ", propOrder = {
    "numeroChequera",
    "emisorCuit",
    "emisorCbu",
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
public class IssueECHEQ {

    @XmlElement(name = "numero_chequera", required = true)
    protected String numeroChequera;
    @XmlElement(name = "emisor_cuit", required = true)
    protected String emisorCuit;
    @XmlElement(name = "emisor_cbu", required = true)
    protected String emisorCbu;
    @XmlElement(name = "fecha_pago", required = true)
    protected String fechaPago;
    @XmlElement(required = true)
    protected String monto;
    @XmlElement(name = "beneficiario_documento_tipo", required = true)
    protected String beneficiarioDocumentoTipo;
    @XmlElement(name = "beneficiario_documento", required = true)
    protected String beneficiarioDocumento;
    @XmlElement(name = "beneficiario_email")
    protected String beneficiarioEmail;
    protected List<Firmante> firmantes;
    protected List<Numeracion> numeracion;
    @XmlElement(name = "cheque_version", required = true)
    protected String chequeVersion;
    @XmlElement(name = "cheque_instrumento_tipo", required = true)
    protected String chequeInstrumentoTipo;
    @XmlElement(name = "cheque_tipo", required = true)
    protected String chequeTipo;
    @XmlElement(name = "cheque_caracter", required = true)
    protected String chequeCaracter;
    @XmlElement(name = "cheque_motivo_pago", required = true)
    protected String chequeMotivoPago;
    @XmlElement(name = "cheque_concepto", required = true)
    protected String chequeConcepto;
    @XmlElement(name = "cheque_cruzado")
    protected boolean chequeCruzado;
    @XmlElement(name = "chq_referencias_pago")
    protected List<Referencia> chqReferenciasPago;
    @XmlElement(name = "trk_cnl")
    protected String trkCnl;
    @XmlElement(name = "trk_scnl")
    protected String trkScnl;
    @XmlElement(name = "trk_jsessionid")
    protected String trkJsessionid;

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
     */
    public boolean isChequeCruzado() {
        return chequeCruzado;
    }

    /**
     * Sets the value of the chequeCruzado property.
     * 
     */
    public void setChequeCruzado(boolean value) {
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
