
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.FirmaRequest;


/**
 * <p>Java class for ObtenerMsgSwiftRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObtenerMsgSwiftRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="Nro_Formulario" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Producto" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObtenerMsgSwiftRequest", propOrder = {
    "nroFormulario",
    "producto"
})
public class ObtenerMsgSwiftRequest
    extends FirmaRequest
{

    @XmlElementRef(name = "Nro_Formulario", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Integer> nroFormulario;
    @XmlElementRef(name = "Producto", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> producto;

    /**
     * Gets the value of the nroFormulario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNroFormulario() {
        return nroFormulario;
    }

    /**
     * Sets the value of the nroFormulario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNroFormulario(JAXBElement<Integer> value) {
        this.nroFormulario = value;
    }

    /**
     * Gets the value of the producto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getProducto() {
        return producto;
    }

    /**
     * Sets the value of the producto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setProducto(JAXBElement<Short> value) {
        this.producto = value;
    }

}
