
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx4004IdentificarClienteResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx4004IdentificarClienteResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Productos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfIdentificacionDelClienteProductResponse" minOccurs="0"/>
 *         &lt;element name="Sesion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx4004IdentificarClienteResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4004", propOrder = {
    "productos",
    "sesion"
})
public class Trx4004IdentificarClienteResponse
    extends ResponseBase
{

    @XmlElementRef(name = "Productos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4004", type = JAXBElement.class)
    protected JAXBElement<ArrayOfIdentificacionDelClienteProductResponse> productos;
    @XmlElement(name = "Sesion")
    protected Integer sesion;

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfIdentificacionDelClienteProductResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfIdentificacionDelClienteProductResponse> getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfIdentificacionDelClienteProductResponse }{@code >}
     *     
     */
    public void setProductos(JAXBElement<ArrayOfIdentificacionDelClienteProductResponse> value) {
        this.productos = value;
    }

    /**
     * Gets the value of the sesion property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSesion() {
        return sesion;
    }

    /**
     * Sets the value of the sesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSesion(Integer value) {
        this.sesion = value;
    }

}
