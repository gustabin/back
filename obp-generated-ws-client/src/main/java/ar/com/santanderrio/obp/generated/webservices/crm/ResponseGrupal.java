
package ar.com.santanderrio.obp.generated.webservices.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseGrupal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseGrupal">
 *   &lt;complexContent>
 *     &lt;extension base="{http://model.alertas.crm.bancorio.com/}Response">
 *       &lt;sequence>
 *         &lt;element name="repeticiones" type="{http://model.alertas.crm.bancorio.com/}ArrayOfSubResponseGrupal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseGrupal", propOrder = {
    "repeticiones"
})
public class ResponseGrupal
    extends Response
{

    protected ArrayOfSubResponseGrupal repeticiones;

    /**
     * Gets the value of the repeticiones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSubResponseGrupal }
     *     
     */
    public ArrayOfSubResponseGrupal getRepeticiones() {
        return repeticiones;
    }

    /**
     * Sets the value of the repeticiones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSubResponseGrupal }
     *     
     */
    public void setRepeticiones(ArrayOfSubResponseGrupal value) {
        this.repeticiones = value;
    }

}
