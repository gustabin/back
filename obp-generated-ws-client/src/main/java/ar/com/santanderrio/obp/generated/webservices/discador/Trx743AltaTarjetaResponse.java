
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx743AltaTarjetaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx743AltaTarjetaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Delimitador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HeaderTrama" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Longitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Solicitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StatusResult" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}StatusResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx743AltaTarjetaResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", propOrder = {
    "delimitador",
    "headerTrama",
    "longitud",
    "solicitud",
    "statusResult"
})
public class Trx743AltaTarjetaResponse
    extends ResponseBase
{

    @XmlElementRef(name = "Delimitador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> delimitador;
    @XmlElementRef(name = "HeaderTrama", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> headerTrama;
    @XmlElementRef(name = "Longitud", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> longitud;
    @XmlElementRef(name = "Solicitud", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> solicitud;
    @XmlElementRef(name = "StatusResult", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<StatusResult> statusResult;

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
     * Gets the value of the solicitud property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSolicitud() {
        return solicitud;
    }

    /**
     * Sets the value of the solicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSolicitud(JAXBElement<String> value) {
        this.solicitud = value;
    }

    /**
     * Gets the value of the statusResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StatusResult }{@code >}
     *     
     */
    public JAXBElement<StatusResult> getStatusResult() {
        return statusResult;
    }

    /**
     * Sets the value of the statusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StatusResult }{@code >}
     *     
     */
    public void setStatusResult(JAXBElement<StatusResult> value) {
        this.statusResult = value;
    }

}
