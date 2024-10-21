
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx5006ConsultaMovimientosCruIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx5006ConsultaMovimientosCruIterationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Clase_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Codigo_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Codigo_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripcion_Adicional_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripcion_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Valor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Hora_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Importe_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Indicador_movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Indicador_observacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Moneda_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Comprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Observacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Saldo_Cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Subcodigo_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sucursal_Movimiento_Origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx5006ConsultaMovimientosCruIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", propOrder = {
    "claseMovimiento",
    "codigoMovimiento",
    "codigoAltair",
    "descripcionAdicionalMovimiento",
    "descripcionMovimiento",
    "fechaMovimiento",
    "fechaValor",
    "horaMovimiento",
    "importeMovimiento",
    "indicadorMovimiento",
    "indicadorObservacion",
    "monedaMovimiento",
    "nroComprobante",
    "observacion",
    "saldoCuenta",
    "subcodigoMovimiento",
    "sucursalMovimientoOrigen"
})
public class Trx5006ConsultaMovimientosCruIterationResponse
    extends ResponseBase
{

    @XmlElementRef(name = "Clase_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> claseMovimiento;
    @XmlElementRef(name = "Codigo_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> codigoMovimiento;
    @XmlElementRef(name = "Codigo_altair", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> codigoAltair;
    @XmlElementRef(name = "Descripcion_Adicional_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> descripcionAdicionalMovimiento;
    @XmlElementRef(name = "Descripcion_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> descripcionMovimiento;
    @XmlElementRef(name = "Fecha_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> fechaMovimiento;
    @XmlElementRef(name = "Fecha_Valor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> fechaValor;
    @XmlElementRef(name = "Hora_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> horaMovimiento;
    @XmlElementRef(name = "Importe_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> importeMovimiento;
    @XmlElementRef(name = "Indicador_movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> indicadorMovimiento;
    @XmlElementRef(name = "Indicador_observacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> indicadorObservacion;
    @XmlElementRef(name = "Moneda_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> monedaMovimiento;
    @XmlElementRef(name = "Nro_Comprobante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> nroComprobante;
    @XmlElementRef(name = "Observacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> observacion;
    @XmlElementRef(name = "Saldo_Cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> saldoCuenta;
    @XmlElementRef(name = "Subcodigo_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> subcodigoMovimiento;
    @XmlElementRef(name = "Sucursal_Movimiento_Origen", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> sucursalMovimientoOrigen;

    /**
     * Gets the value of the claseMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClaseMovimiento() {
        return claseMovimiento;
    }

    /**
     * Sets the value of the claseMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClaseMovimiento(JAXBElement<String> value) {
        this.claseMovimiento = value;
    }

    /**
     * Gets the value of the codigoMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoMovimiento() {
        return codigoMovimiento;
    }

    /**
     * Sets the value of the codigoMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoMovimiento(JAXBElement<String> value) {
        this.codigoMovimiento = value;
    }

    /**
     * Gets the value of the codigoAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoAltair() {
        return codigoAltair;
    }

    /**
     * Sets the value of the codigoAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoAltair(JAXBElement<String> value) {
        this.codigoAltair = value;
    }

    /**
     * Gets the value of the descripcionAdicionalMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionAdicionalMovimiento() {
        return descripcionAdicionalMovimiento;
    }

    /**
     * Sets the value of the descripcionAdicionalMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionAdicionalMovimiento(JAXBElement<String> value) {
        this.descripcionAdicionalMovimiento = value;
    }

    /**
     * Gets the value of the descripcionMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionMovimiento() {
        return descripcionMovimiento;
    }

    /**
     * Sets the value of the descripcionMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionMovimiento(JAXBElement<String> value) {
        this.descripcionMovimiento = value;
    }

    /**
     * Gets the value of the fechaMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaMovimiento() {
        return fechaMovimiento;
    }

    /**
     * Sets the value of the fechaMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaMovimiento(JAXBElement<String> value) {
        this.fechaMovimiento = value;
    }

    /**
     * Gets the value of the fechaValor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaValor() {
        return fechaValor;
    }

    /**
     * Sets the value of the fechaValor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaValor(JAXBElement<String> value) {
        this.fechaValor = value;
    }

    /**
     * Gets the value of the horaMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHoraMovimiento() {
        return horaMovimiento;
    }

    /**
     * Sets the value of the horaMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHoraMovimiento(JAXBElement<String> value) {
        this.horaMovimiento = value;
    }

    /**
     * Gets the value of the importeMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteMovimiento() {
        return importeMovimiento;
    }

    /**
     * Sets the value of the importeMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteMovimiento(JAXBElement<String> value) {
        this.importeMovimiento = value;
    }

    /**
     * Gets the value of the indicadorMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorMovimiento() {
        return indicadorMovimiento;
    }

    /**
     * Sets the value of the indicadorMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorMovimiento(JAXBElement<String> value) {
        this.indicadorMovimiento = value;
    }

    /**
     * Gets the value of the indicadorObservacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorObservacion() {
        return indicadorObservacion;
    }

    /**
     * Sets the value of the indicadorObservacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorObservacion(JAXBElement<String> value) {
        this.indicadorObservacion = value;
    }

    /**
     * Gets the value of the monedaMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMonedaMovimiento() {
        return monedaMovimiento;
    }

    /**
     * Sets the value of the monedaMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMonedaMovimiento(JAXBElement<String> value) {
        this.monedaMovimiento = value;
    }

    /**
     * Gets the value of the nroComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroComprobante() {
        return nroComprobante;
    }

    /**
     * Sets the value of the nroComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroComprobante(JAXBElement<String> value) {
        this.nroComprobante = value;
    }

    /**
     * Gets the value of the observacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getObservacion() {
        return observacion;
    }

    /**
     * Sets the value of the observacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setObservacion(JAXBElement<String> value) {
        this.observacion = value;
    }

    /**
     * Gets the value of the saldoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoCuenta() {
        return saldoCuenta;
    }

    /**
     * Sets the value of the saldoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoCuenta(JAXBElement<String> value) {
        this.saldoCuenta = value;
    }

    /**
     * Gets the value of the subcodigoMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubcodigoMovimiento() {
        return subcodigoMovimiento;
    }

    /**
     * Sets the value of the subcodigoMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubcodigoMovimiento(JAXBElement<String> value) {
        this.subcodigoMovimiento = value;
    }

    /**
     * Gets the value of the sucursalMovimientoOrigen property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalMovimientoOrigen() {
        return sucursalMovimientoOrigen;
    }

    /**
     * Sets the value of the sucursalMovimientoOrigen property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalMovimientoOrigen(JAXBElement<String> value) {
        this.sucursalMovimientoOrigen = value;
    }

}
