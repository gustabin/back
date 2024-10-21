
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Operacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Operacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corregida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuenta_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_emb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_emb_est" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_op" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_ct" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="mot_rechazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nro_form" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nro_solicitud" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ref_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_op" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="vigente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Operacion", propOrder = {
    "beneficiario",
    "codEstado",
    "codMoneda",
    "corregida",
    "cuentaCliente",
    "fechaEmb",
    "fechaEmbEst",
    "fechaOp",
    "idCt",
    "monto",
    "motRechazo",
    "nroForm",
    "nroSolicitud",
    "refCliente",
    "tipoOp",
    "vigente"
})
public class Operacion {

    @XmlElementRef(name = "beneficiario", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> beneficiario;
    @XmlElementRef(name = "cod_estado", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> codEstado;
    @XmlElementRef(name = "cod_moneda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> codMoneda;
    @XmlElementRef(name = "corregida", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> corregida;
    @XmlElementRef(name = "cuenta_cliente", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> cuentaCliente;
    @XmlElementRef(name = "fecha_emb", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> fechaEmb;
    @XmlElementRef(name = "fecha_emb_est", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> fechaEmbEst;
    @XmlElementRef(name = "fecha_op", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> fechaOp;
    @XmlElementRef(name = "id_ct", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<Long> idCt;
    @XmlElementRef(name = "monto", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> monto;
    @XmlElementRef(name = "mot_rechazo", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> motRechazo;
    @XmlElementRef(name = "nro_form", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<Long> nroForm;
    @XmlElementRef(name = "nro_solicitud", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<Long> nroSolicitud;
    @XmlElementRef(name = "ref_cliente", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> refCliente;
    @XmlElementRef(name = "tipo_op", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<Short> tipoOp;
    @XmlElementRef(name = "vigente", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> vigente;

    /**
     * Gets the value of the beneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBeneficiario() {
        return beneficiario;
    }

    /**
     * Sets the value of the beneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBeneficiario(JAXBElement<String> value) {
        this.beneficiario = value;
    }

    /**
     * Gets the value of the codEstado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodEstado() {
        return codEstado;
    }

    /**
     * Sets the value of the codEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodEstado(JAXBElement<String> value) {
        this.codEstado = value;
    }

    /**
     * Gets the value of the codMoneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodMoneda() {
        return codMoneda;
    }

    /**
     * Sets the value of the codMoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodMoneda(JAXBElement<String> value) {
        this.codMoneda = value;
    }

    /**
     * Gets the value of the corregida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCorregida() {
        return corregida;
    }

    /**
     * Sets the value of the corregida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCorregida(JAXBElement<String> value) {
        this.corregida = value;
    }

    /**
     * Gets the value of the cuentaCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaCliente() {
        return cuentaCliente;
    }

    /**
     * Sets the value of the cuentaCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaCliente(JAXBElement<String> value) {
        this.cuentaCliente = value;
    }

    /**
     * Gets the value of the fechaEmb property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaEmb() {
        return fechaEmb;
    }

    /**
     * Sets the value of the fechaEmb property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaEmb(JAXBElement<String> value) {
        this.fechaEmb = value;
    }

    /**
     * Gets the value of the fechaEmbEst property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaEmbEst() {
        return fechaEmbEst;
    }

    /**
     * Sets the value of the fechaEmbEst property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaEmbEst(JAXBElement<String> value) {
        this.fechaEmbEst = value;
    }

    /**
     * Gets the value of the fechaOp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaOp() {
        return fechaOp;
    }

    /**
     * Sets the value of the fechaOp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaOp(JAXBElement<String> value) {
        this.fechaOp = value;
    }

    /**
     * Gets the value of the idCt property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdCt() {
        return idCt;
    }

    /**
     * Sets the value of the idCt property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdCt(JAXBElement<Long> value) {
        this.idCt = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMonto() {
        return monto;
    }

    /**
     * Sets the value of the monto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMonto(JAXBElement<BigDecimal> value) {
        this.monto = value;
    }

    /**
     * Gets the value of the motRechazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMotRechazo() {
        return motRechazo;
    }

    /**
     * Sets the value of the motRechazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMotRechazo(JAXBElement<String> value) {
        this.motRechazo = value;
    }

    /**
     * Gets the value of the nroForm property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getNroForm() {
        return nroForm;
    }

    /**
     * Sets the value of the nroForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setNroForm(JAXBElement<Long> value) {
        this.nroForm = value;
    }

    /**
     * Gets the value of the nroSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getNroSolicitud() {
        return nroSolicitud;
    }

    /**
     * Sets the value of the nroSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setNroSolicitud(JAXBElement<Long> value) {
        this.nroSolicitud = value;
    }

    /**
     * Gets the value of the refCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRefCliente() {
        return refCliente;
    }

    /**
     * Sets the value of the refCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRefCliente(JAXBElement<String> value) {
        this.refCliente = value;
    }

    /**
     * Gets the value of the tipoOp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getTipoOp() {
        return tipoOp;
    }

    /**
     * Sets the value of the tipoOp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setTipoOp(JAXBElement<Short> value) {
        this.tipoOp = value;
    }

    /**
     * Gets the value of the vigente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVigente() {
        return vigente;
    }

    /**
     * Sets the value of the vigente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVigente(JAXBElement<String> value) {
        this.vigente = value;
    }

}
