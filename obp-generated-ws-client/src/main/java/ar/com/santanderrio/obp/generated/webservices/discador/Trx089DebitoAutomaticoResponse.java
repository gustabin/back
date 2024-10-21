
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx089DebitoAutomaticoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx089DebitoAutomaticoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="CodigoServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroPartida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoFecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoStopDebit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx089DebitoAutomaticoResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", propOrder = {
    "codigoServicio",
    "estadoCliente",
    "estadoServicio",
    "fechaServicio",
    "nombreServicio",
    "numeroPartida",
    "tipoFecha",
    "tipoStopDebit"
})
public class Trx089DebitoAutomaticoResponse
    extends MappingModelBase
{

    @XmlElementRef(name = "CodigoServicio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", type = JAXBElement.class)
    protected JAXBElement<String> codigoServicio;
    @XmlElementRef(name = "EstadoCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", type = JAXBElement.class)
    protected JAXBElement<String> estadoCliente;
    @XmlElementRef(name = "EstadoServicio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", type = JAXBElement.class)
    protected JAXBElement<String> estadoServicio;
    @XmlElementRef(name = "FechaServicio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", type = JAXBElement.class)
    protected JAXBElement<String> fechaServicio;
    @XmlElementRef(name = "NombreServicio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", type = JAXBElement.class)
    protected JAXBElement<String> nombreServicio;
    @XmlElementRef(name = "NumeroPartida", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", type = JAXBElement.class)
    protected JAXBElement<String> numeroPartida;
    @XmlElementRef(name = "TipoFecha", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", type = JAXBElement.class)
    protected JAXBElement<String> tipoFecha;
    @XmlElementRef(name = "TipoStopDebit", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", type = JAXBElement.class)
    protected JAXBElement<String> tipoStopDebit;

    /**
     * Gets the value of the codigoServicio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoServicio() {
        return codigoServicio;
    }

    /**
     * Sets the value of the codigoServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoServicio(JAXBElement<String> value) {
        this.codigoServicio = value;
    }

    /**
     * Gets the value of the estadoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoCliente() {
        return estadoCliente;
    }

    /**
     * Sets the value of the estadoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoCliente(JAXBElement<String> value) {
        this.estadoCliente = value;
    }

    /**
     * Gets the value of the estadoServicio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoServicio() {
        return estadoServicio;
    }

    /**
     * Sets the value of the estadoServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoServicio(JAXBElement<String> value) {
        this.estadoServicio = value;
    }

    /**
     * Gets the value of the fechaServicio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaServicio() {
        return fechaServicio;
    }

    /**
     * Sets the value of the fechaServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaServicio(JAXBElement<String> value) {
        this.fechaServicio = value;
    }

    /**
     * Gets the value of the nombreServicio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreServicio() {
        return nombreServicio;
    }

    /**
     * Sets the value of the nombreServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreServicio(JAXBElement<String> value) {
        this.nombreServicio = value;
    }

    /**
     * Gets the value of the numeroPartida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroPartida() {
        return numeroPartida;
    }

    /**
     * Sets the value of the numeroPartida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroPartida(JAXBElement<String> value) {
        this.numeroPartida = value;
    }

    /**
     * Gets the value of the tipoFecha property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoFecha() {
        return tipoFecha;
    }

    /**
     * Sets the value of the tipoFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoFecha(JAXBElement<String> value) {
        this.tipoFecha = value;
    }

    /**
     * Gets the value of the tipoStopDebit property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoStopDebit() {
        return tipoStopDebit;
    }

    /**
     * Sets the value of the tipoStopDebit property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoStopDebit(JAXBElement<String> value) {
        this.tipoStopDebit = value;
    }

}
