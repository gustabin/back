
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx021PedChequeraResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx021PedChequeraResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="DomicilioSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaEntrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndBD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocalidadSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx021PedChequeraResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx021", propOrder = {
    "domicilioSucursal",
    "fechaEntrega",
    "indBD",
    "localidadSucursal",
    "nombreSucursal"
})
public class Trx021PedChequeraResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "DomicilioSucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx021", type = JAXBElement.class)
    protected JAXBElement<String> domicilioSucursal;
    @XmlElementRef(name = "FechaEntrega", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx021", type = JAXBElement.class)
    protected JAXBElement<String> fechaEntrega;
    @XmlElementRef(name = "IndBD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx021", type = JAXBElement.class)
    protected JAXBElement<String> indBD;
    @XmlElementRef(name = "LocalidadSucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx021", type = JAXBElement.class)
    protected JAXBElement<String> localidadSucursal;
    @XmlElementRef(name = "NombreSucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx021", type = JAXBElement.class)
    protected JAXBElement<String> nombreSucursal;

    /**
     * Gets the value of the domicilioSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomicilioSucursal() {
        return domicilioSucursal;
    }

    /**
     * Sets the value of the domicilioSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomicilioSucursal(JAXBElement<String> value) {
        this.domicilioSucursal = value;
    }

    /**
     * Gets the value of the fechaEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * Sets the value of the fechaEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaEntrega(JAXBElement<String> value) {
        this.fechaEntrega = value;
    }

    /**
     * Gets the value of the indBD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndBD() {
        return indBD;
    }

    /**
     * Sets the value of the indBD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndBD(JAXBElement<String> value) {
        this.indBD = value;
    }

    /**
     * Gets the value of the localidadSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalidadSucursal() {
        return localidadSucursal;
    }

    /**
     * Sets the value of the localidadSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalidadSucursal(JAXBElement<String> value) {
        this.localidadSucursal = value;
    }

    /**
     * Gets the value of the nombreSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreSucursal() {
        return nombreSucursal;
    }

    /**
     * Sets the value of the nombreSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreSucursal(JAXBElement<String> value) {
        this.nombreSucursal = value;
    }

}
