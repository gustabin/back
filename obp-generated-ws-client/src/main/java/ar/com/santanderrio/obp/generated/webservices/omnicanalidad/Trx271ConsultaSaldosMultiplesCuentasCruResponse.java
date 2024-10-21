
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx271ConsultaSaldosMultiplesCuentasCruResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx271ConsultaSaldosMultiplesCuentasCruResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadProductos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Delimitador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HeaderTrama" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Longitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Productos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271}ArrayOfTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse" minOccurs="0"/>
 *         &lt;element name="StatusResultado" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}StatusResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx271ConsultaSaldosMultiplesCuentasCruResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", propOrder = {
    "cantidadProductos",
    "delimitador",
    "headerTrama",
    "longitud",
    "productos",
    "statusResultado"
})
public class Trx271ConsultaSaldosMultiplesCuentasCruResponse
    extends ResponseBase
{

    @XmlElementRef(name = "CantidadProductos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> cantidadProductos;
    @XmlElementRef(name = "Delimitador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> delimitador;
    @XmlElementRef(name = "HeaderTrama", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> headerTrama;
    @XmlElementRef(name = "Longitud", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> longitud;
    @XmlElementRef(name = "Productos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse> productos;
    @XmlElementRef(name = "StatusResultado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<StatusResult> statusResultado;

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
     * Gets the value of the delimitador property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelimitador() {
        return delimitador;
    }

    /**
     * Sets the value of the delimitador property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelimitador(JAXBElement<String> value) {
        this.delimitador = value;
    }

    /**
     * Gets the value of the headerTrama property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHeaderTrama() {
        return headerTrama;
    }

    /**
     * Sets the value of the headerTrama property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHeaderTrama(JAXBElement<String> value) {
        this.headerTrama = value;
    }

    /**
     * Gets the value of the longitud property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLongitud() {
        return longitud;
    }

    /**
     * Sets the value of the longitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLongitud(JAXBElement<String> value) {
        this.longitud = value;
    }

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse> getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse }{@code >}
     *     
     */
    public void setProductos(JAXBElement<ArrayOfTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse> value) {
        this.productos = value;
    }

    /**
     * Gets the value of the statusResultado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StatusResult }{@code >}
     *     
     */
    public JAXBElement<StatusResult> getStatusResultado() {
        return statusResultado;
    }

    /**
     * Sets the value of the statusResultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StatusResult }{@code >}
     *     
     */
    public void setStatusResultado(JAXBElement<StatusResult> value) {
        this.statusResultado = value;
    }

}
