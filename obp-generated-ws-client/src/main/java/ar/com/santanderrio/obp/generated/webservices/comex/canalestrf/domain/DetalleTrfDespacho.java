
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetalleTrfDespacho complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetalleTrfDespacho">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Banco_Seguimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Carga_Manual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Desc_Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Embarque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Motivo_Excepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Importe_Aplicado" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Motivo_Excepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nom_Banco_Seguimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Declaracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Despacho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Saldo_Aforo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Saldo_Despacho" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Tipo_Declaracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleTrfDespacho", propOrder = {
    "bancoSeguimiento",
    "cargaManual",
    "descMoneda",
    "fechaEmbarque",
    "idMotivoExcepcion",
    "importeAplicado",
    "moneda",
    "motivoExcepcion",
    "nomBancoSeguimiento",
    "nroDeclaracion",
    "nroDespacho",
    "saldoAforo",
    "saldoDespacho",
    "tipoDeclaracion"
})
public class DetalleTrfDespacho {

    @XmlElementRef(name = "Banco_Seguimiento", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> bancoSeguimiento;
    @XmlElementRef(name = "Carga_Manual", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> cargaManual;
    @XmlElementRef(name = "Desc_Moneda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> descMoneda;
    @XmlElementRef(name = "Fecha_Embarque", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> fechaEmbarque;
    @XmlElementRef(name = "Id_Motivo_Excepcion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> idMotivoExcepcion;
    @XmlElementRef(name = "Importe_Aplicado", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> importeAplicado;
    @XmlElementRef(name = "Moneda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> moneda;
    @XmlElementRef(name = "Motivo_Excepcion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> motivoExcepcion;
    @XmlElementRef(name = "Nom_Banco_Seguimiento", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nomBancoSeguimiento;
    @XmlElementRef(name = "Nro_Declaracion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nroDeclaracion;
    @XmlElementRef(name = "Nro_Despacho", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nroDespacho;
    @XmlElementRef(name = "Saldo_Aforo", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> saldoAforo;
    @XmlElementRef(name = "Saldo_Despacho", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> saldoDespacho;
    @XmlElementRef(name = "Tipo_Declaracion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> tipoDeclaracion;

    /**
     * Gets the value of the bancoSeguimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBancoSeguimiento() {
        return bancoSeguimiento;
    }

    /**
     * Sets the value of the bancoSeguimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBancoSeguimiento(JAXBElement<String> value) {
        this.bancoSeguimiento = value;
    }

    /**
     * Gets the value of the cargaManual property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCargaManual() {
        return cargaManual;
    }

    /**
     * Sets the value of the cargaManual property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCargaManual(JAXBElement<String> value) {
        this.cargaManual = value;
    }

    /**
     * Gets the value of the descMoneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescMoneda() {
        return descMoneda;
    }

    /**
     * Sets the value of the descMoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescMoneda(JAXBElement<String> value) {
        this.descMoneda = value;
    }

    /**
     * Gets the value of the fechaEmbarque property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaEmbarque() {
        return fechaEmbarque;
    }

    /**
     * Sets the value of the fechaEmbarque property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaEmbarque(JAXBElement<String> value) {
        this.fechaEmbarque = value;
    }

    /**
     * Gets the value of the idMotivoExcepcion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdMotivoExcepcion() {
        return idMotivoExcepcion;
    }

    /**
     * Sets the value of the idMotivoExcepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdMotivoExcepcion(JAXBElement<String> value) {
        this.idMotivoExcepcion = value;
    }

    /**
     * Gets the value of the importeAplicado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getImporteAplicado() {
        return importeAplicado;
    }

    /**
     * Sets the value of the importeAplicado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setImporteAplicado(JAXBElement<BigDecimal> value) {
        this.importeAplicado = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMoneda(JAXBElement<String> value) {
        this.moneda = value;
    }

    /**
     * Gets the value of the motivoExcepcion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMotivoExcepcion() {
        return motivoExcepcion;
    }

    /**
     * Sets the value of the motivoExcepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMotivoExcepcion(JAXBElement<String> value) {
        this.motivoExcepcion = value;
    }

    /**
     * Gets the value of the nomBancoSeguimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNomBancoSeguimiento() {
        return nomBancoSeguimiento;
    }

    /**
     * Sets the value of the nomBancoSeguimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNomBancoSeguimiento(JAXBElement<String> value) {
        this.nomBancoSeguimiento = value;
    }

    /**
     * Gets the value of the nroDeclaracion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroDeclaracion() {
        return nroDeclaracion;
    }

    /**
     * Sets the value of the nroDeclaracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroDeclaracion(JAXBElement<String> value) {
        this.nroDeclaracion = value;
    }

    /**
     * Gets the value of the nroDespacho property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroDespacho() {
        return nroDespacho;
    }

    /**
     * Sets the value of the nroDespacho property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroDespacho(JAXBElement<String> value) {
        this.nroDespacho = value;
    }

    /**
     * Gets the value of the saldoAforo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getSaldoAforo() {
        return saldoAforo;
    }

    /**
     * Sets the value of the saldoAforo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setSaldoAforo(JAXBElement<BigDecimal> value) {
        this.saldoAforo = value;
    }

    /**
     * Gets the value of the saldoDespacho property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getSaldoDespacho() {
        return saldoDespacho;
    }

    /**
     * Sets the value of the saldoDespacho property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setSaldoDespacho(JAXBElement<BigDecimal> value) {
        this.saldoDespacho = value;
    }

    /**
     * Gets the value of the tipoDeclaracion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDeclaracion() {
        return tipoDeclaracion;
    }

    /**
     * Sets the value of the tipoDeclaracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDeclaracion(JAXBElement<String> value) {
        this.tipoDeclaracion = value;
    }

}
