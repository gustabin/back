
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
 *         &lt;element name="ConsultaAgendaResult" type="{Response/Consultas}ConsultaAgendaResponse" minOccurs="0"/>
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
    "consultaAgendaResult"
})
@XmlRootElement(name = "ConsultaAgendaResponse")
public class ConsultaAgendaResponse {

    @XmlElementRef(name = "ConsultaAgendaResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse> consultaAgendaResult;

    /**
     * Obtiene el valor de la propiedad consultaAgendaResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse> getConsultaAgendaResult() {
        return consultaAgendaResult;
    }

    /**
     * Define el valor de la propiedad consultaAgendaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse }{@code >}
     *     
     */
    public void setConsultaAgendaResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse> value) {
        this.consultaAgendaResult = value;
    }

}
