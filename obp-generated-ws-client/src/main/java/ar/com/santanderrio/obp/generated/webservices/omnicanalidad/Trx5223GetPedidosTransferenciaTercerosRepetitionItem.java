
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx5223GetPedidosTransferenciaTercerosRepetitionItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx5223GetPedidosTransferenciaTercerosRepetitionItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CodigoConcepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cotizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescripcionConcepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescripcionPedido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoPedido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdPedidoTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteCuentaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuentaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx5223GetPedidosTransferenciaTercerosRepetitionItem", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", propOrder = {
    "codigoConcepto",
    "cotizacion",
    "descripcionConcepto",
    "descripcionPedido",
    "estadoPedido",
    "idPedidoTransferencia",
    "importeCuentaCredito",
    "importeCuentaDebito",
    "numeroCuentaCredito",
    "numeroCuentaDebito",
    "sucursalCuentaCredito",
    "sucursalCuentaDebito",
    "tipoCuentaCredito",
    "tipoCuentaDebito"
})
public class Trx5223GetPedidosTransferenciaTercerosRepetitionItem
    extends ResponseBase
{

    @XmlElementRef(name = "CodigoConcepto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> codigoConcepto;
    @XmlElementRef(name = "Cotizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> cotizacion;
    @XmlElementRef(name = "DescripcionConcepto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> descripcionConcepto;
    @XmlElementRef(name = "DescripcionPedido", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> descripcionPedido;
    @XmlElementRef(name = "EstadoPedido", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> estadoPedido;
    @XmlElementRef(name = "IdPedidoTransferencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> idPedidoTransferencia;
    @XmlElementRef(name = "ImporteCuentaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> importeCuentaCredito;
    @XmlElementRef(name = "ImporteCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> importeCuentaDebito;
    @XmlElementRef(name = "NumeroCuentaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuentaCredito;
    @XmlElementRef(name = "NumeroCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuentaDebito;
    @XmlElementRef(name = "SucursalCuentaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaCredito;
    @XmlElementRef(name = "SucursalCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaDebito;
    @XmlElementRef(name = "TipoCuentaCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaCredito;
    @XmlElementRef(name = "TipoCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaDebito;

    /**
     * Gets the value of the codigoConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoConcepto() {
        return codigoConcepto;
    }

    /**
     * Sets the value of the codigoConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoConcepto(JAXBElement<String> value) {
        this.codigoConcepto = value;
    }

    /**
     * Gets the value of the cotizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotizacion() {
        return cotizacion;
    }

    /**
     * Sets the value of the cotizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotizacion(JAXBElement<String> value) {
        this.cotizacion = value;
    }

    /**
     * Gets the value of the descripcionConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionConcepto() {
        return descripcionConcepto;
    }

    /**
     * Sets the value of the descripcionConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionConcepto(JAXBElement<String> value) {
        this.descripcionConcepto = value;
    }

    /**
     * Gets the value of the descripcionPedido property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionPedido() {
        return descripcionPedido;
    }

    /**
     * Sets the value of the descripcionPedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionPedido(JAXBElement<String> value) {
        this.descripcionPedido = value;
    }

    /**
     * Gets the value of the estadoPedido property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoPedido() {
        return estadoPedido;
    }

    /**
     * Sets the value of the estadoPedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoPedido(JAXBElement<String> value) {
        this.estadoPedido = value;
    }

    /**
     * Gets the value of the idPedidoTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdPedidoTransferencia() {
        return idPedidoTransferencia;
    }

    /**
     * Sets the value of the idPedidoTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdPedidoTransferencia(JAXBElement<String> value) {
        this.idPedidoTransferencia = value;
    }

    /**
     * Gets the value of the importeCuentaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteCuentaCredito() {
        return importeCuentaCredito;
    }

    /**
     * Sets the value of the importeCuentaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteCuentaCredito(JAXBElement<String> value) {
        this.importeCuentaCredito = value;
    }

    /**
     * Gets the value of the importeCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteCuentaDebito() {
        return importeCuentaDebito;
    }

    /**
     * Sets the value of the importeCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteCuentaDebito(JAXBElement<String> value) {
        this.importeCuentaDebito = value;
    }

    /**
     * Gets the value of the numeroCuentaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCuentaCredito() {
        return numeroCuentaCredito;
    }

    /**
     * Sets the value of the numeroCuentaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCuentaCredito(JAXBElement<String> value) {
        this.numeroCuentaCredito = value;
    }

    /**
     * Gets the value of the numeroCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCuentaDebito() {
        return numeroCuentaDebito;
    }

    /**
     * Sets the value of the numeroCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCuentaDebito(JAXBElement<String> value) {
        this.numeroCuentaDebito = value;
    }

    /**
     * Gets the value of the sucursalCuentaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalCuentaCredito() {
        return sucursalCuentaCredito;
    }

    /**
     * Sets the value of the sucursalCuentaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalCuentaCredito(JAXBElement<String> value) {
        this.sucursalCuentaCredito = value;
    }

    /**
     * Gets the value of the sucursalCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalCuentaDebito() {
        return sucursalCuentaDebito;
    }

    /**
     * Sets the value of the sucursalCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalCuentaDebito(JAXBElement<String> value) {
        this.sucursalCuentaDebito = value;
    }

    /**
     * Gets the value of the tipoCuentaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuentaCredito() {
        return tipoCuentaCredito;
    }

    /**
     * Sets the value of the tipoCuentaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuentaCredito(JAXBElement<String> value) {
        this.tipoCuentaCredito = value;
    }

    /**
     * Gets the value of the tipoCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuentaDebito() {
        return tipoCuentaDebito;
    }

    /**
     * Sets the value of the tipoCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuentaDebito(JAXBElement<String> value) {
        this.tipoCuentaDebito = value;
    }

}
