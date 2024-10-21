
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.RequestBase;


/**
 * <p>Java class for GetRemoveTokenRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetRemoveTokenRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="AddGetRemoveTokenRequest" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}AddGetRemoveTokenRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetRemoveTokenRequest", propOrder = {
    "addGetRemoveTokenRequest"
})
public class GetRemoveTokenRequest
    extends RequestBase
{

    @XmlElementRef(name = "AddGetRemoveTokenRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<AddGetRemoveTokenRequest> addGetRemoveTokenRequest;

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

}
