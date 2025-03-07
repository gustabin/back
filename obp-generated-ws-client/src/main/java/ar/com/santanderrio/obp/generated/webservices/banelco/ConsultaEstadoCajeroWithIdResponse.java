
package ar.com.santanderrio.obp.generated.webservices.banelco;

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
 *         &lt;element name="out" type="{http://banelcomap.services.external.banelco.com}InfoCajeroDTO"/>
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
    "out"
})
@XmlRootElement(name = "consultaEstadoCajeroWithIdResponse")
public class ConsultaEstadoCajeroWithIdResponse {

    @XmlElement(required = true, nillable = true)
    protected InfoCajeroDTO out;

    /**
     * Obtiene el valor de la propiedad out.
     * 
     * @return
     *     possible object is
     *     {@link InfoCajeroDTO }
     *     
     */
    public InfoCajeroDTO getOut() {
        return out;
    }

    /**
     * Define el valor de la propiedad out.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoCajeroDTO }
     *     
     */
    public void setOut(InfoCajeroDTO value) {
        this.out = value;
    }

}
