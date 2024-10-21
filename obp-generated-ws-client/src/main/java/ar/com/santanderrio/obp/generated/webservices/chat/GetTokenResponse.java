
package ar.com.santanderrio.obp.generated.webservices.chat;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetTokenResult" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ResultData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getTokenResult"
})
@XmlRootElement(name = "GetTokenResponse")
public class GetTokenResponse {

    @XmlElementRef(name = "GetTokenResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ResultData> getTokenResult;

    /**
     * Obtiene el valor de la propiedad getTokenResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ResultData }{@code >}
     *     
     */
    public JAXBElement<ResultData> getGetTokenResult() {
        return getTokenResult;
    }

    /**
     * Define el valor de la propiedad getTokenResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ResultData }{@code >}
     *     
     */
    public void setGetTokenResult(JAXBElement<ResultData> value) {
        this.getTokenResult = value;
    }

}
