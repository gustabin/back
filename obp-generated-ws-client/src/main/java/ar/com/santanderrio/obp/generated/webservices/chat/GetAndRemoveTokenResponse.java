
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
 *         &lt;element name="GetAndRemoveTokenResult" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ResultData" minOccurs="0"/>
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
    "getAndRemoveTokenResult"
})
@XmlRootElement(name = "GetAndRemoveTokenResponse")
public class GetAndRemoveTokenResponse {

    @XmlElementRef(name = "GetAndRemoveTokenResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ResultData> getAndRemoveTokenResult;

    /**
     * Obtiene el valor de la propiedad getAndRemoveTokenResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ResultData }{@code >}
     *     
     */
    public JAXBElement<ResultData> getGetAndRemoveTokenResult() {
        return getAndRemoveTokenResult;
    }

    /**
     * Define el valor de la propiedad getAndRemoveTokenResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ResultData }{@code >}
     *     
     */
    public void setGetAndRemoveTokenResult(JAXBElement<ResultData> value) {
        this.getAndRemoveTokenResult = value;
    }

}
