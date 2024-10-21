
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx001IdentificacionDeLlamadaAltairPrestamosResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx001IdentificacionDeLlamadaAltairPrestamosResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}IdentificacionDeLlamadaResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Productos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfResultProductoAltairPrestamos" minOccurs="0"/>
 *         &lt;element name="StatusResultado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx001IdentificacionDeLlamadaAltairPrestamosResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx0001", propOrder = {
    "productos",
    "statusResultado"
})
public class Trx001IdentificacionDeLlamadaAltairPrestamosResponse
    extends IdentificacionDeLlamadaResponseBase
{

    @XmlElementRef(name = "Productos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx0001", type = JAXBElement.class)
    protected JAXBElement<ArrayOfResultProductoAltairPrestamos> productos;
    @XmlElement(name = "StatusResultado")
    protected Integer statusResultado;

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfResultProductoAltairPrestamos }{@code >}
     *     
     */
    public JAXBElement<ArrayOfResultProductoAltairPrestamos> getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfResultProductoAltairPrestamos }{@code >}
     *     
     */
    public void setProductos(JAXBElement<ArrayOfResultProductoAltairPrestamos> value) {
        this.productos = value;
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

}
