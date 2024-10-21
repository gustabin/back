
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx302CnsCuotaPresAltairRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx302CnsCuotaPresAltairRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestContactBase">
 *       &lt;sequence>
 *         &lt;element name="IndiceSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroPrestamo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalPrestamo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPrestamo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx302CnsCuotaPresAltairRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", propOrder = {
    "indiceSinonimo",
    "numeroPrestamo",
    "sucursalPrestamo",
    "tipoPrestamo"
})
public class Trx302CnsCuotaPresAltairRequest
    extends RequestContactBase
{

    @XmlElementRef(name = "IndiceSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> indiceSinonimo;
    @XmlElementRef(name = "NumeroPrestamo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> numeroPrestamo;
    @XmlElementRef(name = "SucursalPrestamo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> sucursalPrestamo;
    @XmlElementRef(name = "TipoPrestamo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx302", type = JAXBElement.class)
    protected JAXBElement<String> tipoPrestamo;

    /**
     * Gets the value of the indiceSinonimo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndiceSinonimo() {
        return indiceSinonimo;
    }

    /**
     * Sets the value of the indiceSinonimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndiceSinonimo(JAXBElement<String> value) {
        this.indiceSinonimo = value;
    }

    /**
     * Gets the value of the numeroPrestamo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroPrestamo() {
        return numeroPrestamo;
    }

    /**
     * Sets the value of the numeroPrestamo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroPrestamo(JAXBElement<String> value) {
        this.numeroPrestamo = value;
    }

    /**
     * Gets the value of the sucursalPrestamo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalPrestamo() {
        return sucursalPrestamo;
    }

    /**
     * Sets the value of the sucursalPrestamo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalPrestamo(JAXBElement<String> value) {
        this.sucursalPrestamo = value;
    }

    /**
     * Gets the value of the tipoPrestamo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPrestamo() {
        return tipoPrestamo;
    }

    /**
     * Sets the value of the tipoPrestamo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPrestamo(JAXBElement<String> value) {
        this.tipoPrestamo = value;
    }

}
