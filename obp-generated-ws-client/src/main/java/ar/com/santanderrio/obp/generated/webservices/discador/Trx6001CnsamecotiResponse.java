
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx6001CnsamecotiResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx6001CnsamecotiResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CotiMaxEuro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CotiMaxUsd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CotiMiniEuro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CotiMiniUsd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Error777" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ErrorMensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonedaDolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonedaEuro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx6001CnsamecotiResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6001Cnsamecoti", propOrder = {
    "cotiMaxEuro",
    "cotiMaxUsd",
    "cotiMiniEuro",
    "cotiMiniUsd",
    "error777",
    "errorMensaje",
    "monedaDolar",
    "monedaEuro"
})
public class Trx6001CnsamecotiResponse
    extends ResponseBase
{

    @XmlElementRef(name = "CotiMaxEuro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6001Cnsamecoti", type = JAXBElement.class)
    protected JAXBElement<String> cotiMaxEuro;
    @XmlElementRef(name = "CotiMaxUsd", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6001Cnsamecoti", type = JAXBElement.class)
    protected JAXBElement<String> cotiMaxUsd;
    @XmlElementRef(name = "CotiMiniEuro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6001Cnsamecoti", type = JAXBElement.class)
    protected JAXBElement<String> cotiMiniEuro;
    @XmlElementRef(name = "CotiMiniUsd", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6001Cnsamecoti", type = JAXBElement.class)
    protected JAXBElement<String> cotiMiniUsd;
    @XmlElement(name = "Error777")
    protected Boolean error777;
    @XmlElementRef(name = "ErrorMensaje", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6001Cnsamecoti", type = JAXBElement.class)
    protected JAXBElement<String> errorMensaje;
    @XmlElementRef(name = "MonedaDolar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6001Cnsamecoti", type = JAXBElement.class)
    protected JAXBElement<String> monedaDolar;
    @XmlElementRef(name = "MonedaEuro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6001Cnsamecoti", type = JAXBElement.class)
    protected JAXBElement<String> monedaEuro;

    /**
     * Gets the value of the cotiMaxEuro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotiMaxEuro() {
        return cotiMaxEuro;
    }

    /**
     * Sets the value of the cotiMaxEuro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotiMaxEuro(JAXBElement<String> value) {
        this.cotiMaxEuro = value;
    }

    /**
     * Gets the value of the cotiMaxUsd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotiMaxUsd() {
        return cotiMaxUsd;
    }

    /**
     * Sets the value of the cotiMaxUsd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotiMaxUsd(JAXBElement<String> value) {
        this.cotiMaxUsd = value;
    }

    /**
     * Gets the value of the cotiMiniEuro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotiMiniEuro() {
        return cotiMiniEuro;
    }

    /**
     * Sets the value of the cotiMiniEuro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotiMiniEuro(JAXBElement<String> value) {
        this.cotiMiniEuro = value;
    }

    /**
     * Gets the value of the cotiMiniUsd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCotiMiniUsd() {
        return cotiMiniUsd;
    }

    /**
     * Sets the value of the cotiMiniUsd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCotiMiniUsd(JAXBElement<String> value) {
        this.cotiMiniUsd = value;
    }

    /**
     * Gets the value of the error777 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isError777() {
        return error777;
    }

    /**
     * Sets the value of the error777 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setError777(Boolean value) {
        this.error777 = value;
    }

    /**
     * Gets the value of the errorMensaje property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErrorMensaje() {
        return errorMensaje;
    }

    /**
     * Sets the value of the errorMensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErrorMensaje(JAXBElement<String> value) {
        this.errorMensaje = value;
    }

    /**
     * Gets the value of the monedaDolar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMonedaDolar() {
        return monedaDolar;
    }

    /**
     * Sets the value of the monedaDolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMonedaDolar(JAXBElement<String> value) {
        this.monedaDolar = value;
    }

    /**
     * Gets the value of the monedaEuro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMonedaEuro() {
        return monedaEuro;
    }

    /**
     * Sets the value of the monedaEuro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMonedaEuro(JAXBElement<String> value) {
        this.monedaEuro = value;
    }

}
