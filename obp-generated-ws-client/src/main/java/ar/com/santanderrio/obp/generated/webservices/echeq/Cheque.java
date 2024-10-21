
package ar.com.santanderrio.obp.generated.webservices.echeq;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Cheque complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Cheque">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuenta_emisora" type="{http://echeq.amco.com.ar/}CuentaEmisora"/>
 *         &lt;element name="numero_chequera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intcheque_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmc7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emitido_a" type="{http://echeq.amco.com.ar/}Beneficiario"/>
 *         &lt;element name="tenencia" type="{http://echeq.amco.com.ar/}Beneficiario"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monto_letras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_pago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_emision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_caracter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_modo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_motivo_pago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivo_repudio_emision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivo_anulacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="agrupador_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_visualizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nro_lote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_ult_modif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_pago_vencida" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="cheque_acordado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="solicitando_acuerdo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="re_presentar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="repudio_endoso" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="certificado_emitido" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="chq_referencias_pago" type="{http://echeq.amco.com.ar/}Referencia" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="endosos" type="{http://echeq.amco.com.ar/}Endoso" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rechazos" type="{http://echeq.amco.com.ar/}Rechazo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="onp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="cbu_custodia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cbu_deposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cesion_pendiente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="cedido" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="total_cesiones" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="cesiones" type="{http://echeq.amco.com.ar/}Cesion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="total_avalistas" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="avalistas" type="{http://echeq.amco.com.ar/}Aval" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status" type="{http://echeq.amco.com.ar/}bae_CCE_Stat" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cheque", propOrder = {
    "cuentaEmisora",
    "numeroChequera",
    "intchequeId",
    "cmc7",
    "chequeNumero",
    "estado",
    "emitidoA",
    "tenencia",
    "monto",
    "montoLetras",
    "fechaPago",
    "fechaEmision",
    "chequeTipo",
    "chequeCaracter",
    "chequeModo",
    "chequeConcepto",
    "chequeMotivoPago",
    "motivoRepudioEmision",
    "motivoAnulacion",
    "agrupadorId",
    "codVisualizacion",
    "nroLote",
    "fechaUltModif",
    "fechaPagoVencida",
    "chequeAcordado",
    "solicitandoAcuerdo",
    "rePresentar",
    "repudioEndoso",
    "certificadoEmitido",
    "chqReferenciasPago",
    "endosos",
    "rechazos",
    "onp",
    "cbuCustodia",
    "cbuDeposito",
    "cesionPendiente",
    "cedido",
    "totalCesiones",
    "cesiones",
    "totalAvalistas",
    "avalistas",
    "status"
})
public class Cheque {

    @XmlElement(name = "cuenta_emisora", required = true)
    protected CuentaEmisora cuentaEmisora;
    @XmlElement(name = "numero_chequera")
    protected String numeroChequera;
    @XmlElement(name = "intcheque_id")
    protected String intchequeId;
    protected String cmc7;
    @XmlElement(name = "cheque_numero")
    protected String chequeNumero;
    protected String estado;
    @XmlElement(name = "emitido_a", required = true)
    protected Beneficiario emitidoA;
    @XmlElement(required = true)
    protected Beneficiario tenencia;
    protected String monto;
    @XmlElement(name = "monto_letras")
    protected String montoLetras;
    @XmlElement(name = "fecha_pago")
    protected String fechaPago;
    @XmlElement(name = "fecha_emision")
    protected String fechaEmision;
    @XmlElement(name = "cheque_tipo")
    protected String chequeTipo;
    @XmlElement(name = "cheque_caracter")
    protected String chequeCaracter;
    @XmlElement(name = "cheque_modo")
    protected String chequeModo;
    @XmlElement(name = "cheque_concepto")
    protected String chequeConcepto;
    @XmlElement(name = "cheque_motivo_pago")
    protected String chequeMotivoPago;
    @XmlElement(name = "motivo_repudio_emision")
    protected String motivoRepudioEmision;
    @XmlElement(name = "motivo_anulacion")
    protected String motivoAnulacion;
    @XmlElement(name = "agrupador_id")
    protected String agrupadorId;
    @XmlElement(name = "cod_visualizacion")
    protected String codVisualizacion;
    @XmlElement(name = "nro_lote")
    protected String nroLote;
    @XmlElement(name = "fecha_ult_modif")
    protected String fechaUltModif;
    @XmlElement(name = "fecha_pago_vencida")
    protected Boolean fechaPagoVencida;
    @XmlElement(name = "cheque_acordado")
    protected Boolean chequeAcordado;
    @XmlElement(name = "solicitando_acuerdo")
    protected Boolean solicitandoAcuerdo;
    @XmlElement(name = "re_presentar")
    protected Boolean rePresentar;
    @XmlElement(name = "repudio_endoso")
    protected Boolean repudioEndoso;
    @XmlElement(name = "certificado_emitido")
    protected Boolean certificadoEmitido;
    @XmlElement(name = "chq_referencias_pago")
    protected List<Referencia> chqReferenciasPago;
    protected List<Endoso> endosos;
    protected List<Rechazo> rechazos;
    protected Boolean onp;
    @XmlElement(name = "cbu_custodia")
    protected String cbuCustodia;
    @XmlElement(name = "cbu_deposito")
    protected String cbuDeposito;
    @XmlElement(name = "cesion_pendiente")
    protected Boolean cesionPendiente;
    protected Boolean cedido;
    @XmlElement(name = "total_cesiones")
    protected BigInteger totalCesiones;
    protected List<Cesion> cesiones;
    @XmlElement(name = "total_avalistas")
    protected BigInteger totalAvalistas;
    protected List<Aval> avalistas;
    protected BaeCCEStat status;

    /**
     * Gets the value of the cuentaEmisora property.
     * 
     * @return
     *     possible object is
     *     {@link CuentaEmisora }
     *     
     */
    public CuentaEmisora getCuentaEmisora() {
        return cuentaEmisora;
    }

    /**
     * Sets the value of the cuentaEmisora property.
     * 
     * @param value
     *     allowed object is
     *     {@link CuentaEmisora }
     *     
     */
    public void setCuentaEmisora(CuentaEmisora value) {
        this.cuentaEmisora = value;
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
     * Gets the value of the intchequeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchequeId() {
        return intchequeId;
    }

    /**
     * Sets the value of the intchequeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchequeId(String value) {
        this.intchequeId = value;
    }

    /**
     * Gets the value of the cmc7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmc7() {
        return cmc7;
    }

    /**
     * Sets the value of the cmc7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmc7(String value) {
        this.cmc7 = value;
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
     * Gets the value of the emitidoA property.
     * 
     * @return
     *     possible object is
     *     {@link Beneficiario }
     *     
     */
    public Beneficiario getEmitidoA() {
        return emitidoA;
    }

    /**
     * Sets the value of the emitidoA property.
     * 
     * @param value
     *     allowed object is
     *     {@link Beneficiario }
     *     
     */
    public void setEmitidoA(Beneficiario value) {
        this.emitidoA = value;
    }

    /**
     * Gets the value of the tenencia property.
     * 
     * @return
     *     possible object is
     *     {@link Beneficiario }
     *     
     */
    public Beneficiario getTenencia() {
        return tenencia;
    }

    /**
     * Sets the value of the tenencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Beneficiario }
     *     
     */
    public void setTenencia(Beneficiario value) {
        this.tenencia = value;
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
     * Gets the value of the montoLetras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoLetras() {
        return montoLetras;
    }

    /**
     * Sets the value of the montoLetras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoLetras(String value) {
        this.montoLetras = value;
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
     * Gets the value of the fechaEmision property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Sets the value of the fechaEmision property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEmision(String value) {
        this.fechaEmision = value;
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
     * Gets the value of the chequeModo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeModo() {
        return chequeModo;
    }

    /**
     * Sets the value of the chequeModo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeModo(String value) {
        this.chequeModo = value;
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
     * Gets the value of the motivoRepudioEmision property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoRepudioEmision() {
        return motivoRepudioEmision;
    }

    /**
     * Sets the value of the motivoRepudioEmision property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoRepudioEmision(String value) {
        this.motivoRepudioEmision = value;
    }

    /**
     * Gets the value of the motivoAnulacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    /**
     * Sets the value of the motivoAnulacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoAnulacion(String value) {
        this.motivoAnulacion = value;
    }

    /**
     * Gets the value of the agrupadorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgrupadorId() {
        return agrupadorId;
    }

    /**
     * Sets the value of the agrupadorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgrupadorId(String value) {
        this.agrupadorId = value;
    }

    /**
     * Gets the value of the codVisualizacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodVisualizacion() {
        return codVisualizacion;
    }

    /**
     * Sets the value of the codVisualizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodVisualizacion(String value) {
        this.codVisualizacion = value;
    }

    /**
     * Gets the value of the nroLote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroLote() {
        return nroLote;
    }

    /**
     * Sets the value of the nroLote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroLote(String value) {
        this.nroLote = value;
    }

    /**
     * Gets the value of the fechaUltModif property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaUltModif() {
        return fechaUltModif;
    }

    /**
     * Sets the value of the fechaUltModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaUltModif(String value) {
        this.fechaUltModif = value;
    }

    /**
     * Gets the value of the fechaPagoVencida property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFechaPagoVencida() {
        return fechaPagoVencida;
    }

    /**
     * Sets the value of the fechaPagoVencida property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFechaPagoVencida(Boolean value) {
        this.fechaPagoVencida = value;
    }

    /**
     * Gets the value of the chequeAcordado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChequeAcordado() {
        return chequeAcordado;
    }

    /**
     * Sets the value of the chequeAcordado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChequeAcordado(Boolean value) {
        this.chequeAcordado = value;
    }

    /**
     * Gets the value of the solicitandoAcuerdo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSolicitandoAcuerdo() {
        return solicitandoAcuerdo;
    }

    /**
     * Sets the value of the solicitandoAcuerdo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSolicitandoAcuerdo(Boolean value) {
        this.solicitandoAcuerdo = value;
    }

    /**
     * Gets the value of the rePresentar property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRePresentar() {
        return rePresentar;
    }

    /**
     * Sets the value of the rePresentar property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRePresentar(Boolean value) {
        this.rePresentar = value;
    }

    /**
     * Gets the value of the repudioEndoso property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRepudioEndoso() {
        return repudioEndoso;
    }

    /**
     * Sets the value of the repudioEndoso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRepudioEndoso(Boolean value) {
        this.repudioEndoso = value;
    }

    /**
     * Gets the value of the certificadoEmitido property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCertificadoEmitido() {
        return certificadoEmitido;
    }

    /**
     * Sets the value of the certificadoEmitido property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCertificadoEmitido(Boolean value) {
        this.certificadoEmitido = value;
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
     * Gets the value of the endosos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the endosos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEndosos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Endoso }
     * 
     * 
     */
    public List<Endoso> getEndosos() {
        if (endosos == null) {
            endosos = new ArrayList<Endoso>();
        }
        return this.endosos;
    }

    /**
     * Gets the value of the rechazos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rechazos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRechazos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rechazo }
     * 
     * 
     */
    public List<Rechazo> getRechazos() {
        if (rechazos == null) {
            rechazos = new ArrayList<Rechazo>();
        }
        return this.rechazos;
    }

    /**
     * Gets the value of the onp property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnp() {
        return onp;
    }

    /**
     * Sets the value of the onp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnp(Boolean value) {
        this.onp = value;
    }

    /**
     * Gets the value of the cbuCustodia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbuCustodia() {
        return cbuCustodia;
    }

    /**
     * Sets the value of the cbuCustodia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbuCustodia(String value) {
        this.cbuCustodia = value;
    }

    /**
     * Gets the value of the cbuDeposito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbuDeposito() {
        return cbuDeposito;
    }

    /**
     * Sets the value of the cbuDeposito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbuDeposito(String value) {
        this.cbuDeposito = value;
    }

    /**
     * Gets the value of the cesionPendiente property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCesionPendiente() {
        return cesionPendiente;
    }

    /**
     * Sets the value of the cesionPendiente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCesionPendiente(Boolean value) {
        this.cesionPendiente = value;
    }

    /**
     * Gets the value of the cedido property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCedido() {
        return cedido;
    }

    /**
     * Sets the value of the cedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCedido(Boolean value) {
        this.cedido = value;
    }

    /**
     * Gets the value of the totalCesiones property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalCesiones() {
        return totalCesiones;
    }

    /**
     * Sets the value of the totalCesiones property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalCesiones(BigInteger value) {
        this.totalCesiones = value;
    }

    /**
     * Gets the value of the cesiones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cesiones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCesiones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cesion }
     * 
     * 
     */
    public List<Cesion> getCesiones() {
        if (cesiones == null) {
            cesiones = new ArrayList<Cesion>();
        }
        return this.cesiones;
    }

    /**
     * Gets the value of the totalAvalistas property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalAvalistas() {
        return totalAvalistas;
    }

    /**
     * Sets the value of the totalAvalistas property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalAvalistas(BigInteger value) {
        this.totalAvalistas = value;
    }

    /**
     * Gets the value of the avalistas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the avalistas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAvalistas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Aval }
     * 
     * 
     */
    public List<Aval> getAvalistas() {
        if (avalistas == null) {
            avalistas = new ArrayList<Aval>();
        }
        return this.avalistas;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link BaeCCEStat }
     *     
     */
    public BaeCCEStat getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaeCCEStat }
     *     
     */
    public void setStatus(BaeCCEStat value) {
        this.status = value;
    }

}
