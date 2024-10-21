
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx218MfPagoTarjetaV120Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx218MfPagoTarjetaV120Response">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Canal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClaveRioSelf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaLiquidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaVencimientoLiquidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndBD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreApellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PagoMinimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoDolares" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignoSaldoDolares" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignoSaldoPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx218MfPagoTarjetaV120Response", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", propOrder = {
    "canal",
    "claveRioSelf",
    "fechaLiquidacion",
    "fechaVencimientoLiquidacion",
    "indBD",
    "nombreApellido",
    "numeroCuentaTarjeta",
    "pagoMinimo",
    "saldoDolares",
    "saldoPesos",
    "sesion",
    "signoSaldoDolares",
    "signoSaldoPesos",
    "timestamp",
    "tipoTarjeta"
})
public class Trx218MfPagoTarjetaV120Response
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "Canal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> canal;
    @XmlElementRef(name = "ClaveRioSelf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> claveRioSelf;
    @XmlElementRef(name = "FechaLiquidacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> fechaLiquidacion;
    @XmlElementRef(name = "FechaVencimientoLiquidacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> fechaVencimientoLiquidacion;
    @XmlElementRef(name = "IndBD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> indBD;
    @XmlElementRef(name = "NombreApellido", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> nombreApellido;
    @XmlElementRef(name = "NumeroCuentaTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuentaTarjeta;
    @XmlElementRef(name = "PagoMinimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> pagoMinimo;
    @XmlElementRef(name = "SaldoDolares", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> saldoDolares;
    @XmlElementRef(name = "SaldoPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> saldoPesos;
    @XmlElementRef(name = "Sesion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> sesion;
    @XmlElementRef(name = "SignoSaldoDolares", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> signoSaldoDolares;
    @XmlElementRef(name = "SignoSaldoPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> signoSaldoPesos;
    @XmlElementRef(name = "Timestamp", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> timestamp;
    @XmlElementRef(name = "TipoTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx218", type = JAXBElement.class)
    protected JAXBElement<String> tipoTarjeta;

    /**
     * Gets the value of the canal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCanal(JAXBElement<String> value) {
        this.canal = value;
    }

    /**
     * Gets the value of the claveRioSelf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClaveRioSelf() {
        return claveRioSelf;
    }

    /**
     * Sets the value of the claveRioSelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClaveRioSelf(JAXBElement<String> value) {
        this.claveRioSelf = value;
    }

    /**
     * Gets the value of the fechaLiquidacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    /**
     * Sets the value of the fechaLiquidacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaLiquidacion(JAXBElement<String> value) {
        this.fechaLiquidacion = value;
    }

    /**
     * Gets the value of the fechaVencimientoLiquidacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaVencimientoLiquidacion() {
        return fechaVencimientoLiquidacion;
    }

    /**
     * Sets the value of the fechaVencimientoLiquidacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaVencimientoLiquidacion(JAXBElement<String> value) {
        this.fechaVencimientoLiquidacion = value;
    }

    /**
     * Gets the value of the indBD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndBD() {
        return indBD;
    }

    /**
     * Sets the value of the indBD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndBD(JAXBElement<String> value) {
        this.indBD = value;
    }

    /**
     * Gets the value of the nombreApellido property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreApellido() {
        return nombreApellido;
    }

    /**
     * Sets the value of the nombreApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreApellido(JAXBElement<String> value) {
        this.nombreApellido = value;
    }

    /**
     * Gets the value of the numeroCuentaTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCuentaTarjeta() {
        return numeroCuentaTarjeta;
    }

    /**
     * Sets the value of the numeroCuentaTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCuentaTarjeta(JAXBElement<String> value) {
        this.numeroCuentaTarjeta = value;
    }

    /**
     * Gets the value of the pagoMinimo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPagoMinimo() {
        return pagoMinimo;
    }

    /**
     * Sets the value of the pagoMinimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPagoMinimo(JAXBElement<String> value) {
        this.pagoMinimo = value;
    }

    /**
     * Gets the value of the saldoDolares property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoDolares() {
        return saldoDolares;
    }

    /**
     * Sets the value of the saldoDolares property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoDolares(JAXBElement<String> value) {
        this.saldoDolares = value;
    }

    /**
     * Gets the value of the saldoPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoPesos() {
        return saldoPesos;
    }

    /**
     * Sets the value of the saldoPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoPesos(JAXBElement<String> value) {
        this.saldoPesos = value;
    }

    /**
     * Gets the value of the sesion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSesion() {
        return sesion;
    }

    /**
     * Sets the value of the sesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSesion(JAXBElement<String> value) {
        this.sesion = value;
    }

    /**
     * Gets the value of the signoSaldoDolares property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignoSaldoDolares() {
        return signoSaldoDolares;
    }

    /**
     * Sets the value of the signoSaldoDolares property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignoSaldoDolares(JAXBElement<String> value) {
        this.signoSaldoDolares = value;
    }

    /**
     * Gets the value of the signoSaldoPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignoSaldoPesos() {
        return signoSaldoPesos;
    }

    /**
     * Sets the value of the signoSaldoPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignoSaldoPesos(JAXBElement<String> value) {
        this.signoSaldoPesos = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimestamp(JAXBElement<String> value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the tipoTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Sets the value of the tipoTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoTarjeta(JAXBElement<String> value) {
        this.tipoTarjeta = value;
    }

}
