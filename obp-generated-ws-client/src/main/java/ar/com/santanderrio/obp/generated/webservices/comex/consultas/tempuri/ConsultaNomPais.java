
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaNomPaisRequest;


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
 *         &lt;element name="request" type="{Request/Consultas}ConsultaNomPaisRequest" minOccurs="0"/>
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
    "request"
})
@XmlRootElement(name = "ConsultaNomPais")
public class ConsultaNomPais {

    @XmlElementRef(name = "request", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ConsultaNomPaisRequest> request;

    /**
     * Obtiene el valor de la propiedad request.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ConsultaNomPaisRequest }{@code >}
     *     
     */
    public JAXBElement<ConsultaNomPaisRequest> getRequest() {
        return request;
    }

    /**
     * Define el valor de la propiedad request.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ConsultaNomPaisRequest }{@code >}
     *     
     */
    public void setRequest(JAXBElement<ConsultaNomPaisRequest> value) {
        this.request = value;
    }

}
