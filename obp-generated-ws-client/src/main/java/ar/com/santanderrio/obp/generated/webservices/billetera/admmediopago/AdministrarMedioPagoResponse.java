
package ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="MediosPagoResponse" type="{http://billetera.prismamp.com.ar/administrarMedioPago/}MediosPagoResponse"/>
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
    "mediosPagoResponse"
})
@XmlRootElement(name = "administrarMedioPagoResponse")
public class AdministrarMedioPagoResponse {

    @XmlElement(name = "MediosPagoResponse", required = true)
    protected MediosPagoResponse mediosPagoResponse;

    /**
     * Obtiene el valor de la propiedad mediosPagoResponse.
     * 
     * @return
     *     possible object is
     *     {@link MediosPagoResponse }
     *     
     */
    public MediosPagoResponse getMediosPagoResponse() {
        return mediosPagoResponse;
    }

    /**
     * Define el valor de la propiedad mediosPagoResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link MediosPagoResponse }
     *     
     */
    public void setMediosPagoResponse(MediosPagoResponse value) {
        this.mediosPagoResponse = value;
    }

}
