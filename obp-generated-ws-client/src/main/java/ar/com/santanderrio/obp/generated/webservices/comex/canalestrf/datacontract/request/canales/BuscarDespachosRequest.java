
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.BaseCursor;


/**
 * <p>Java class for BuscarDespachosRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BuscarDespachosRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}BaseCursor">
 *       &lt;sequence>
 *         &lt;element name="Banco_Emisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Desde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Hasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Monto_Desde" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Monto_Hasta" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Nominado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Despacho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Doc_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Doc_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BuscarDespachosRequest", propOrder = {
    "bancoEmisor",
    "fechaDesde",
    "fechaHasta",
    "moneda",
    "montoDesde",
    "montoHasta",
    "nominado",
    "nroDespacho",
    "nroDocCliente",
    "tipoDocCliente"
})
public class BuscarDespachosRequest
    extends BaseCursor
{

    @XmlElementRef(name = "Banco_Emisor", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> bancoEmisor;
    @XmlElementRef(name = "Fecha_Desde", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> fechaDesde;
    @XmlElementRef(name = "Fecha_Hasta", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> fechaHasta;
    @XmlElementRef(name = "Moneda", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> moneda;
    @XmlElementRef(name = "Monto_Desde", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoDesde;
    @XmlElementRef(name = "Monto_Hasta", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoHasta;
    @XmlElementRef(name = "Nominado", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> nominado;
    @XmlElementRef(name = "Nro_Despacho", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> nroDespacho;
    @XmlElementRef(name = "Nro_Doc_Cliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> nroDocCliente;
    @XmlElementRef(name = "Tipo_Doc_Cliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> tipoDocCliente;

    /**
     * Gets the value of the bancoEmisor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBancoEmisor() {
        return bancoEmisor;
    }

    /**
     * Sets the value of the bancoEmisor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBancoEmisor(JAXBElement<String> value) {
        this.bancoEmisor = value;
    }

    /**
     * Gets the value of the fechaDesde property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Sets the value of the fechaDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaDesde(JAXBElement<String> value) {
        this.fechaDesde = value;
    }

    /**
     * Gets the value of the fechaHasta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Sets the value of the fechaHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaHasta(JAXBElement<String> value) {
        this.fechaHasta = value;
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
     * Gets the value of the montoDesde property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoDesde() {
        return montoDesde;
    }

    /**
     * Sets the value of the montoDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoDesde(JAXBElement<BigDecimal> value) {
        this.montoDesde = value;
    }

    /**
     * Gets the value of the montoHasta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoHasta() {
        return montoHasta;
    }

    /**
     * Sets the value of the montoHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoHasta(JAXBElement<BigDecimal> value) {
        this.montoHasta = value;
    }

    /**
     * Gets the value of the nominado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNominado() {
        return nominado;
    }

    /**
     * Sets the value of the nominado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNominado(JAXBElement<String> value) {
        this.nominado = value;
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
     * Gets the value of the nroDocCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroDocCliente() {
        return nroDocCliente;
    }

    /**
     * Sets the value of the nroDocCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroDocCliente(JAXBElement<String> value) {
        this.nroDocCliente = value;
    }

    /**
     * Gets the value of the tipoDocCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDocCliente() {
        return tipoDocCliente;
    }

    /**
     * Sets the value of the tipoDocCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDocCliente(JAXBElement<String> value) {
        this.tipoDocCliente = value;
    }

}
