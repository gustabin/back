
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdentificacionDeLlamadaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentificacionDeLlamadaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}IdentificacionDeLlamadaResponseBase">
 *       &lt;sequence>
 *         &lt;element name="productos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfIdentificacionDeLlamadaProductoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificacionDeLlamadaResponse", propOrder = {
    "productos"
})
public class IdentificacionDeLlamadaResponse
    extends IdentificacionDeLlamadaResponseBase
{

    @XmlElementRef(name = "productos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<ArrayOfIdentificacionDeLlamadaProductoResponse> productos;

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfIdentificacionDeLlamadaProductoResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfIdentificacionDeLlamadaProductoResponse> getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfIdentificacionDeLlamadaProductoResponse }{@code >}
     *     
     */
    public void setProductos(JAXBElement<ArrayOfIdentificacionDeLlamadaProductoResponse> value) {
        this.productos = value;
    }

}
