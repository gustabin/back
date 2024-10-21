
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.AddGetRemoveTokenRequest;


/**
 * <p>Java class for Trx7001AddTokenRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx7001AddTokenRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="AddGetRemoveTokenRequest" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}AddGetRemoveTokenRequest" minOccurs="0"/>
 *         &lt;element name="EncryptedToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx7001AddTokenRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7001", propOrder = {
    "addGetRemoveTokenRequest",
    "encryptedToken"
})
public class Trx7001AddTokenRequest
    extends RequestBase
{

    @XmlElementRef(name = "AddGetRemoveTokenRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7001", type = JAXBElement.class)
    protected JAXBElement<AddGetRemoveTokenRequest> addGetRemoveTokenRequest;
    @XmlElementRef(name = "EncryptedToken", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7001", type = JAXBElement.class)
    protected JAXBElement<String> encryptedToken;

    /**
     * Gets the value of the addGetRemoveTokenRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddGetRemoveTokenRequest }{@code >}
     *     
     */
    public JAXBElement<AddGetRemoveTokenRequest> getAddGetRemoveTokenRequest() {
        return addGetRemoveTokenRequest;
    }

    /**
     * Sets the value of the addGetRemoveTokenRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddGetRemoveTokenRequest }{@code >}
     *     
     */
    public void setAddGetRemoveTokenRequest(JAXBElement<AddGetRemoveTokenRequest> value) {
        this.addGetRemoveTokenRequest = value;
    }

    /**
     * Gets the value of the encryptedToken property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEncryptedToken() {
        return encryptedToken;
    }

    /**
     * Sets the value of the encryptedToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEncryptedToken(JAXBElement<String> value) {
        this.encryptedToken = value;
    }

}
