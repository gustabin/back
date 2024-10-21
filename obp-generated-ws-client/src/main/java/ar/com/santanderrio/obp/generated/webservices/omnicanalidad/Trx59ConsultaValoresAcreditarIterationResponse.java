
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx59ConsultaValoresAcreditarIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx59ConsultaValoresAcreditarIterationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="Descripcion_Adicional_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripcion_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Comprobante_Credito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Importe_Acreditar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Moneda_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Comprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "Trx59ConsultaValoresAcreditarIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", propOrder = {
    "descripcionAdicionalMovimiento",
    "descripcionMovimiento",
    "fechaComprobanteCredito",
    "importeAcreditar",
    "monedaMovimiento",
    "nroComprobante",
    "sucursalMovimientoOrigen"
})
public class Trx59ConsultaValoresAcreditarIterationResponse
    extends MappingModelBase
{

    @XmlElementRef(name = "Descripcion_Adicional_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> descripcionAdicionalMovimiento;
    @XmlElementRef(name = "Descripcion_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> descripcionMovimiento;
    @XmlElementRef(name = "Fecha_Comprobante_Credito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> fechaComprobanteCredito;
    @XmlElementRef(name = "Importe_Acreditar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> importeAcreditar;
    @XmlElementRef(name = "Moneda_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> monedaMovimiento;
    @XmlElementRef(name = "Nro_Comprobante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> nroComprobante;
    @XmlElementRef(name = "Sucursal_Movimiento_Origen", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> sucursalMovimientoOrigen;

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
     * Gets the value of the fechaComprobanteCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaComprobanteCredito() {
        return fechaComprobanteCredito;
    }

    /**
     * Sets the value of the fechaComprobanteCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaComprobanteCredito(JAXBElement<String> value) {
        this.fechaComprobanteCredito = value;
    }

    /**
     * Gets the value of the importeAcreditar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteAcreditar() {
        return importeAcreditar;
    }

    /**
     * Sets the value of the importeAcreditar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteAcreditar(JAXBElement<String> value) {
        this.importeAcreditar = value;
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
