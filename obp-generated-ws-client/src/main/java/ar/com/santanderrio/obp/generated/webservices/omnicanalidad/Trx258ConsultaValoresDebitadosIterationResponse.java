
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx258ConsultaValoresDebitadosIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx258ConsultaValoresDebitadosIterationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="Descripcion_Adicional_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripcion_Movimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Comprobante_Debito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Importe_Debitar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "Trx258ConsultaValoresDebitadosIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", propOrder = {
    "descripcionAdicionalMovimiento",
    "descripcionMovimiento",
    "fechaComprobanteDebito",
    "importeDebitar",
    "monedaMovimiento",
    "nroComprobante",
    "sucursalMovimientoOrigen"
})
public class Trx258ConsultaValoresDebitadosIterationResponse
    extends MappingModelBase
{

    @XmlElementRef(name = "Descripcion_Adicional_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
    protected JAXBElement<String> descripcionAdicionalMovimiento;
    @XmlElementRef(name = "Descripcion_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
    protected JAXBElement<String> descripcionMovimiento;
    @XmlElementRef(name = "Fecha_Comprobante_Debito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
    protected JAXBElement<String> fechaComprobanteDebito;
    @XmlElementRef(name = "Importe_Debitar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
    protected JAXBElement<String> importeDebitar;
    @XmlElementRef(name = "Moneda_Movimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
    protected JAXBElement<String> monedaMovimiento;
    @XmlElementRef(name = "Nro_Comprobante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
    protected JAXBElement<String> nroComprobante;
    @XmlElementRef(name = "Sucursal_Movimiento_Origen", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
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
     * Gets the value of the fechaComprobanteDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaComprobanteDebito() {
        return fechaComprobanteDebito;
    }

    /**
     * Sets the value of the fechaComprobanteDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaComprobanteDebito(JAXBElement<String> value) {
        this.fechaComprobanteDebito = value;
    }

    /**
     * Gets the value of the importeDebitar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteDebitar() {
        return importeDebitar;
    }

    /**
     * Sets the value of the importeDebitar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteDebitar(JAXBElement<String> value) {
        this.importeDebitar = value;
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
