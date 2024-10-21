
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx15ConsultaCotizacionDivisaIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx15ConsultaCotizacionDivisaIterationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="SucursalCotizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambioCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambioVenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx15ConsultaCotizacionDivisaIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx015", propOrder = {
    "sucursalCotizacion",
    "tipoCambioCompra",
    "tipoCambioVenta"
})
public class Trx15ConsultaCotizacionDivisaIterationResponse
    extends MappingModelBase
{

    @XmlElementRef(name = "SucursalCotizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx015", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCotizacion;
    @XmlElementRef(name = "TipoCambioCompra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx015", type = JAXBElement.class)
    protected JAXBElement<String> tipoCambioCompra;
    @XmlElementRef(name = "TipoCambioVenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx015", type = JAXBElement.class)
    protected JAXBElement<String> tipoCambioVenta;

    /**
     * Gets the value of the sucursalCotizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalCotizacion() {
        return sucursalCotizacion;
    }

    /**
     * Sets the value of the sucursalCotizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalCotizacion(JAXBElement<String> value) {
        this.sucursalCotizacion = value;
    }

    /**
     * Gets the value of the tipoCambioCompra property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCambioCompra() {
        return tipoCambioCompra;
    }

    /**
     * Sets the value of the tipoCambioCompra property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCambioCompra(JAXBElement<String> value) {
        this.tipoCambioCompra = value;
    }

    /**
     * Gets the value of the tipoCambioVenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCambioVenta() {
        return tipoCambioVenta;
    }

    /**
     * Sets the value of the tipoCambioVenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCambioVenta(JAXBElement<String> value) {
        this.tipoCambioVenta = value;
    }

}
