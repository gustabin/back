
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri;

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
 *         &lt;element name="RegimenesGananciasResult" type="{Response/Consultas}RegimenesGananciasResponse" minOccurs="0"/>
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
    "regimenesGananciasResult"
})
@XmlRootElement(name = "RegimenesGananciasResponse")
public class RegimenesGananciasResponse {

    @XmlElementRef(name = "RegimenesGananciasResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse> regimenesGananciasResult;

    /**
     * Obtiene el valor de la propiedad regimenesGananciasResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse> getRegimenesGananciasResult() {
        return regimenesGananciasResult;
    }

    /**
     * Define el valor de la propiedad regimenesGananciasResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse }{@code >}
     *     
     */
    public void setRegimenesGananciasResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse> value) {
        this.regimenesGananciasResult = value;
    }

}
