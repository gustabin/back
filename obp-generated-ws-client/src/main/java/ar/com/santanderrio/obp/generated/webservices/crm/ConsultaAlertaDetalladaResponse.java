
package ar.com.santanderrio.obp.generated.webservices.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultaAlertaDetalladaResult" type="{http://model.alertas.crm.bancorio.com/}ResponseCliente" minOccurs="0"/>
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
    "consultaAlertaDetalladaResult"
})
@XmlRootElement(name = "ConsultaAlertaDetalladaResponse")
public class ConsultaAlertaDetalladaResponse {

    @XmlElement(name = "ConsultaAlertaDetalladaResult")
    protected ResponseCliente consultaAlertaDetalladaResult;

    /**
     * Gets the value of the consultaAlertaDetalladaResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseCliente }
     *     
     */
    public ResponseCliente getConsultaAlertaDetalladaResult() {
        return consultaAlertaDetalladaResult;
    }

    /**
     * Sets the value of the consultaAlertaDetalladaResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseCliente }
     *     
     */
    public void setConsultaAlertaDetalladaResult(ResponseCliente value) {
        this.consultaAlertaDetalladaResult = value;
    }

}
