
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx201IdentificacionDeLlamadaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx201IdentificacionDeLlamadaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}IdentificacionDeLlamadaResponseBase">
 *       &lt;sequence>
 *         &lt;element name="EsMonoProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StatusResultado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="TipoClave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx201}ArrayOfTrx201IdentificacionDeLlamadaProductoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx201IdentificacionDeLlamadaResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx201", propOrder = {
    "esMonoProducto",
    "statusResultado",
    "tipoClave",
    "productos"
})
public class Trx201IdentificacionDeLlamadaResponse
    extends IdentificacionDeLlamadaResponseBase
{

    @XmlElementRef(name = "EsMonoProducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx201", type = JAXBElement.class)
    protected JAXBElement<String> esMonoProducto;
    @XmlElement(name = "StatusResultado")
    protected Integer statusResultado;
    @XmlElementRef(name = "TipoClave", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx201", type = JAXBElement.class)
    protected JAXBElement<String> tipoClave;
    @XmlElementRef(name = "productos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx201", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx201IdentificacionDeLlamadaProductoResponse> productos;

    /**
     * Gets the value of the esMonoProducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEsMonoProducto() {
        return esMonoProducto;
    }

    /**
     * Sets the value of the esMonoProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEsMonoProducto(JAXBElement<String> value) {
        this.esMonoProducto = value;
    }

    /**
     * Gets the value of the statusResultado property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatusResultado() {
        return statusResultado;
    }

    /**
     * Sets the value of the statusResultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatusResultado(Integer value) {
        this.statusResultado = value;
    }

    /**
     * Gets the value of the tipoClave property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoClave() {
        return tipoClave;
    }

    /**
     * Sets the value of the tipoClave property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoClave(JAXBElement<String> value) {
        this.tipoClave = value;
    }

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx201IdentificacionDeLlamadaProductoResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx201IdentificacionDeLlamadaProductoResponse> getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx201IdentificacionDeLlamadaProductoResponse }{@code >}
     *     
     */
    public void setProductos(JAXBElement<ArrayOfTrx201IdentificacionDeLlamadaProductoResponse> value) {
        this.productos = value;
    }

}
