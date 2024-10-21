
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.RequestBase;


/**
 * <p>Java class for ApiConnectTokenRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApiConnectTokenRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="TokenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TokenScope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApiConnectTokenRequest", propOrder = {
    "tokenName",
    "tokenScope"
})
public class ApiConnectTokenRequest
    extends RequestBase
{

    @XmlElementRef(name = "TokenName", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> tokenName;
    @XmlElementRef(name = "TokenScope", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> tokenScope;

    /**
     * Gets the value of the tokenName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTokenName() {
        return tokenName;
    }

    /**
     * Sets the value of the tokenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTokenName(JAXBElement<String> value) {
        this.tokenName = value;
    }

    /**
     * Gets the value of the tokenScope property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTokenScope() {
        return tokenScope;
    }

    /**
     * Sets the value of the tokenScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTokenScope(JAXBElement<String> value) {
        this.tokenScope = value;
    }

}
