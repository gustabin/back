
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ArrayOfProductoMoraResponse;


/**
 * <p>Java class for Trx1010MdlwConsultaProdMoraTemprana_130Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx1010MdlwConsultaProdMoraTemprana_130Response">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Cant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FecMaxProm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Productos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfProductoMoraResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx1010MdlwConsultaProdMoraTemprana_130Response", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx1010", propOrder = {
    "cant",
    "estado",
    "fecMaxProm",
    "productos"
})
public class Trx1010MdlwConsultaProdMoraTemprana130Response
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "Cant", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx1010", type = JAXBElement.class)
    protected JAXBElement<String> cant;
    @XmlElementRef(name = "Estado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx1010", type = JAXBElement.class)
    protected JAXBElement<String> estado;
    @XmlElementRef(name = "FecMaxProm", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx1010", type = JAXBElement.class)
    protected JAXBElement<String> fecMaxProm;
    @XmlElementRef(name = "Productos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx1010", type = JAXBElement.class)
    protected JAXBElement<ArrayOfProductoMoraResponse> productos;

    /**
     * Gets the value of the cant property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCant() {
        return cant;
    }

    /**
     * Sets the value of the cant property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCant(JAXBElement<String> value) {
        this.cant = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstado(JAXBElement<String> value) {
        this.estado = value;
    }

    /**
     * Gets the value of the fecMaxProm property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFecMaxProm() {
        return fecMaxProm;
    }

    /**
     * Sets the value of the fecMaxProm property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFecMaxProm(JAXBElement<String> value) {
        this.fecMaxProm = value;
    }

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfProductoMoraResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfProductoMoraResponse> getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfProductoMoraResponse }{@code >}
     *     
     */
    public void setProductos(JAXBElement<ArrayOfProductoMoraResponse> value) {
        this.productos = value;
    }

}
