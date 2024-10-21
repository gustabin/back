
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RestServiceClientRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RestServiceClientRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="Headers" type="{http://schemas.datacontract.org/2004/07/System.Collections.Generic}ArrayOfKeyValuePairOfstringstring" minOccurs="0"/>
 *         &lt;element name="Method" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.Enums}HttpMethodEnum" minOccurs="0"/>
 *         &lt;element name="Parameters" type="{http://schemas.datacontract.org/2004/07/System.Collections.Generic}ArrayOfKeyValuePairOfstringstring" minOccurs="0"/>
 *         &lt;element name="RequestDto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RestServiceClientRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx1003RestServiceCallerActor", propOrder = {
    "headers",
    "method",
    "parameters",
    "requestDto",
    "url"
})
public class RestServiceClientRequest
    extends RequestBase
{

    @XmlElementRef(name = "Headers", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx1003RestServiceCallerActor", type = JAXBElement.class)
    protected JAXBElement<ArrayOfKeyValuePairOfstringstring> headers;
    @XmlElement(name = "Method")
    protected HttpMethodEnum method;
    @XmlElementRef(name = "Parameters", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx1003RestServiceCallerActor", type = JAXBElement.class)
    protected JAXBElement<ArrayOfKeyValuePairOfstringstring> parameters;
    @XmlElementRef(name = "RequestDto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx1003RestServiceCallerActor", type = JAXBElement.class)
    protected JAXBElement<String> requestDto;
    @XmlElementRef(name = "URL", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx1003RestServiceCallerActor", type = JAXBElement.class)
    protected JAXBElement<String> url;

    /**
     * Gets the value of the headers property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyValuePairOfstringstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfKeyValuePairOfstringstring> getHeaders() {
        return headers;
    }

    /**
     * Sets the value of the headers property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyValuePairOfstringstring }{@code >}
     *     
     */
    public void setHeaders(JAXBElement<ArrayOfKeyValuePairOfstringstring> value) {
        this.headers = value;
    }

    /**
     * Gets the value of the method property.
     * 
     * @return
     *     possible object is
     *     {@link HttpMethodEnum }
     *     
     */
    public HttpMethodEnum getMethod() {
        return method;
    }

    /**
     * Sets the value of the method property.
     * 
     * @param value
     *     allowed object is
     *     {@link HttpMethodEnum }
     *     
     */
    public void setMethod(HttpMethodEnum value) {
        this.method = value;
    }

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyValuePairOfstringstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfKeyValuePairOfstringstring> getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyValuePairOfstringstring }{@code >}
     *     
     */
    public void setParameters(JAXBElement<ArrayOfKeyValuePairOfstringstring> value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the requestDto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRequestDto() {
        return requestDto;
    }

    /**
     * Sets the value of the requestDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRequestDto(JAXBElement<String> value) {
        this.requestDto = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getURL() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setURL(JAXBElement<String> value) {
        this.url = value;
    }

}
