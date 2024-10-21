
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListReceivedOrEndorsed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListReceivedOrEndorsed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="beneficiario_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario_documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_emision_desde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_emision_hasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_pago_desde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_pago_hasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_cuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuit_beneficiario_original" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numero_pagina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantidad_registros_pagina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderby" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "ListReceivedOrEndorsed", propOrder = {
    "cuit",
    "beneficiarioDocumentoTipo",
    "beneficiarioDocumento",
    "fechaEmisionDesde",
    "fechaEmisionHasta",
    "fechaPagoDesde",
    "fechaPagoHasta",
    "estado",
    "chequeNumero",
    "emisorCuit",
    "cuitBeneficiarioOriginal",
    "numeroPagina",
    "cantidadRegistrosPagina",
    "orderby",
    "trkCnl",
    "trkScnl",
    "trkJsessionid"
})
public class ListReceivedOrEndorsed {

    @XmlElement(required = true)
    protected String cuit;
    @XmlElement(name = "beneficiario_documento_tipo")
    protected String beneficiarioDocumentoTipo;
    @XmlElement(name = "beneficiario_documento")
    protected String beneficiarioDocumento;
    @XmlElement(name = "fecha_emision_desde")
    protected String fechaEmisionDesde;
    @XmlElement(name = "fecha_emision_hasta")
    protected String fechaEmisionHasta;
    @XmlElement(name = "fecha_pago_desde")
    protected String fechaPagoDesde;
    @XmlElement(name = "fecha_pago_hasta")
    protected String fechaPagoHasta;
    protected String estado;
    @XmlElement(name = "cheque_numero")
    protected String chequeNumero;
    @XmlElement(name = "emisor_cuit")
    protected String emisorCuit;
    @XmlElement(name = "cuit_beneficiario_original")
    protected String cuitBeneficiarioOriginal;
    @XmlElement(name = "numero_pagina")
    protected String numeroPagina;
    @XmlElement(name = "cantidad_registros_pagina")
    protected String cantidadRegistrosPagina;
    protected String orderby;
    @XmlElement(name = "trk_cnl")
    protected String trkCnl;
    @XmlElement(name = "trk_scnl")
    protected String trkScnl;
    @XmlElement(name = "trk_jsessionid")
    protected String trkJsessionid;

    /**
     * Gets the value of the cuit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Sets the value of the cuit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuit(String value) {
        this.cuit = value;
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
     * Gets the value of the fechaEmisionDesde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEmisionDesde() {
        return fechaEmisionDesde;
    }

    /**
     * Sets the value of the fechaEmisionDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEmisionDesde(String value) {
        this.fechaEmisionDesde = value;
    }

    /**
     * Gets the value of the fechaEmisionHasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEmisionHasta() {
        return fechaEmisionHasta;
    }

    /**
     * Sets the value of the fechaEmisionHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEmisionHasta(String value) {
        this.fechaEmisionHasta = value;
    }

    /**
     * Gets the value of the fechaPagoDesde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPagoDesde() {
        return fechaPagoDesde;
    }

    /**
     * Sets the value of the fechaPagoDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPagoDesde(String value) {
        this.fechaPagoDesde = value;
    }

    /**
     * Gets the value of the fechaPagoHasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPagoHasta() {
        return fechaPagoHasta;
    }

    /**
     * Sets the value of the fechaPagoHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPagoHasta(String value) {
        this.fechaPagoHasta = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the chequeNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeNumero() {
        return chequeNumero;
    }

    /**
     * Sets the value of the chequeNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeNumero(String value) {
        this.chequeNumero = value;
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
     * Gets the value of the cuitBeneficiarioOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuitBeneficiarioOriginal() {
        return cuitBeneficiarioOriginal;
    }

    /**
     * Sets the value of the cuitBeneficiarioOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuitBeneficiarioOriginal(String value) {
        this.cuitBeneficiarioOriginal = value;
    }

    /**
     * Gets the value of the numeroPagina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroPagina() {
        return numeroPagina;
    }

    /**
     * Sets the value of the numeroPagina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroPagina(String value) {
        this.numeroPagina = value;
    }

    /**
     * Gets the value of the cantidadRegistrosPagina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadRegistrosPagina() {
        return cantidadRegistrosPagina;
    }

    /**
     * Sets the value of the cantidadRegistrosPagina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadRegistrosPagina(String value) {
        this.cantidadRegistrosPagina = value;
    }

    /**
     * Gets the value of the orderby property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderby() {
        return orderby;
    }

    /**
     * Sets the value of the orderby property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderby(String value) {
        this.orderby = value;
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
