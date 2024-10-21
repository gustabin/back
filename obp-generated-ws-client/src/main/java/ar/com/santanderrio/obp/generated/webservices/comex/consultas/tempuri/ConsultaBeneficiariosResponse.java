
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.canales.ConsultaBeneficiarioResponse;


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
 *         &lt;element name="ConsultaBeneficiariosResult" type="{Response/Canales}ConsultaBeneficiarioResponse" minOccurs="0"/>
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
    "consultaBeneficiariosResult"
})
@XmlRootElement(name = "ConsultaBeneficiariosResponse")
public class ConsultaBeneficiariosResponse {

    @XmlElementRef(name = "ConsultaBeneficiariosResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ConsultaBeneficiarioResponse> consultaBeneficiariosResult;

    /**
     * Obtiene el valor de la propiedad consultaBeneficiariosResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ConsultaBeneficiarioResponse }{@code >}
     *     
     */
    public JAXBElement<ConsultaBeneficiarioResponse> getConsultaBeneficiariosResult() {
        return consultaBeneficiariosResult;
    }

    /**
     * Define el valor de la propiedad consultaBeneficiariosResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ConsultaBeneficiarioResponse }{@code >}
     *     
     */
    public void setConsultaBeneficiariosResult(JAXBElement<ConsultaBeneficiarioResponse> value) {
        this.consultaBeneficiariosResult = value;
    }

}
