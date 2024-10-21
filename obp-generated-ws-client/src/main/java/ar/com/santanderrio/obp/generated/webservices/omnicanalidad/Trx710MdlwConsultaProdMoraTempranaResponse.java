
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx710MdlwConsultaProdMoraTempranaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx710MdlwConsultaProdMoraTempranaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadProductos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductosMora" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfProductoMoraResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx710MdlwConsultaProdMoraTempranaResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx710", propOrder = {
    "cantidadProductos",
    "productosMora"
})
public class Trx710MdlwConsultaProdMoraTempranaResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CantidadProductos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx710", type = JAXBElement.class)
    protected JAXBElement<String> cantidadProductos;
    @XmlElementRef(name = "ProductosMora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx710", type = JAXBElement.class)
    protected JAXBElement<ArrayOfProductoMoraResponse> productosMora;

    /**
     * Gets the value of the cantidadProductos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadProductos() {
        return cantidadProductos;
    }

    /**
     * Sets the value of the cantidadProductos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadProductos(JAXBElement<String> value) {
        this.cantidadProductos = value;
    }

    /**
     * Gets the value of the productosMora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfProductoMoraResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfProductoMoraResponse> getProductosMora() {
        return productosMora;
    }

    /**
     * Sets the value of the productosMora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfProductoMoraResponse }{@code >}
     *     
     */
    public void setProductosMora(JAXBElement<ArrayOfProductoMoraResponse> value) {
        this.productosMora = value;
    }

}
