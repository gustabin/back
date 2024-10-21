
package ar.com.santanderrio.obp.generated.webservices.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestGrupal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestGrupal">
 *   &lt;complexContent>
 *     &lt;extension base="{http://model.alertas.crm.bancorio.com/}Request">
 *       &lt;sequence>
 *         &lt;element name="estGestion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestGrupal", propOrder = {
    "estGestion"
})
public class RequestGrupal
    extends Request
{

    protected String estGestion;

    /**
     * Gets the value of the estGestion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstGestion() {
        return estGestion;
    }

    /**
     * Sets the value of the estGestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstGestion(String value) {
        this.estGestion = value;
    }

}
