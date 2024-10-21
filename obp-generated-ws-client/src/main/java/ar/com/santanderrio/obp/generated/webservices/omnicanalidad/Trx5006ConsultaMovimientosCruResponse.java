
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx5006ConsultaMovimientosCruResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx5006ConsultaMovimientosCruResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadMovimientos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaSaldo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Movimientos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006}ArrayOfTrx5006ConsultaMovimientosCruIterationResponse" minOccurs="0"/>
 *         &lt;element name="SaldoAcad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoAcah" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoAccd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoActe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoDolares" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoPesos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx5006ConsultaMovimientosCruResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", propOrder = {
    "cantidadMovimientos",
    "fechaSaldo",
    "movimientos",
    "saldoAcad",
    "saldoAcah",
    "saldoAccd",
    "saldoActe",
    "saldoDolares",
    "saldoPesos"
})
public class Trx5006ConsultaMovimientosCruResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CantidadMovimientos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> cantidadMovimientos;
    @XmlElementRef(name = "FechaSaldo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> fechaSaldo;
    @XmlElementRef(name = "Movimientos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx5006ConsultaMovimientosCruIterationResponse> movimientos;
    @XmlElementRef(name = "SaldoAcad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> saldoAcad;
    @XmlElementRef(name = "SaldoAcah", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> saldoAcah;
    @XmlElementRef(name = "SaldoAccd", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> saldoAccd;
    @XmlElementRef(name = "SaldoActe", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> saldoActe;
    @XmlElementRef(name = "SaldoDolares", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> saldoDolares;
    @XmlElementRef(name = "SaldoPesos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", type = JAXBElement.class)
    protected JAXBElement<String> saldoPesos;

    /**
     * Gets the value of the cantidadMovimientos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadMovimientos() {
        return cantidadMovimientos;
    }

    /**
     * Sets the value of the cantidadMovimientos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadMovimientos(JAXBElement<String> value) {
        this.cantidadMovimientos = value;
    }

    /**
     * Gets the value of the fechaSaldo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaSaldo() {
        return fechaSaldo;
    }

    /**
     * Sets the value of the fechaSaldo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaSaldo(JAXBElement<String> value) {
        this.fechaSaldo = value;
    }

    /**
     * Gets the value of the movimientos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx5006ConsultaMovimientosCruIterationResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx5006ConsultaMovimientosCruIterationResponse> getMovimientos() {
        return movimientos;
    }

    /**
     * Sets the value of the movimientos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx5006ConsultaMovimientosCruIterationResponse }{@code >}
     *     
     */
    public void setMovimientos(JAXBElement<ArrayOfTrx5006ConsultaMovimientosCruIterationResponse> value) {
        this.movimientos = value;
    }

    /**
     * Gets the value of the saldoAcad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoAcad() {
        return saldoAcad;
    }

    /**
     * Sets the value of the saldoAcad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoAcad(JAXBElement<String> value) {
        this.saldoAcad = value;
    }

    /**
     * Gets the value of the saldoAcah property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoAcah() {
        return saldoAcah;
    }

    /**
     * Sets the value of the saldoAcah property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoAcah(JAXBElement<String> value) {
        this.saldoAcah = value;
    }

    /**
     * Gets the value of the saldoAccd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoAccd() {
        return saldoAccd;
    }

    /**
     * Sets the value of the saldoAccd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoAccd(JAXBElement<String> value) {
        this.saldoAccd = value;
    }

    /**
     * Gets the value of the saldoActe property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoActe() {
        return saldoActe;
    }

    /**
     * Sets the value of the saldoActe property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoActe(JAXBElement<String> value) {
        this.saldoActe = value;
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

}
