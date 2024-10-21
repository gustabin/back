
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
 *         &lt;element name="ConsultaGrupalResult" type="{http://model.alertas.crm.bancorio.com/}ResponseGrupal" minOccurs="0"/>
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
    "consultaGrupalResult"
})
@XmlRootElement(name = "ConsultaGrupalResponse")
public class ConsultaGrupalResponse {

    @XmlElement(name = "ConsultaGrupalResult")
    protected ResponseGrupal consultaGrupalResult;

    /**
     * Gets the value of the consultaGrupalResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseGrupal }
     *     
     */
    public ResponseGrupal getConsultaGrupalResult() {
        return consultaGrupalResult;
    }

    /**
     * Sets the value of the consultaGrupalResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseGrupal }
     *     
     */
    public void setConsultaGrupalResult(ResponseGrupal value) {
        this.consultaGrupalResult = value;
    }

}
