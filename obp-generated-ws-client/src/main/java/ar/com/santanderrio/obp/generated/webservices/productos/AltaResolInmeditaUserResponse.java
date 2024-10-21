
package ar.com.santanderrio.obp.generated.webservices.productos;

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
 *         &lt;element name="altaResolInmeditaUserReturn" type="{http://webService.core.ges.rio.com}ResultadoAltaWS"/>
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
    "altaResolInmeditaUserReturn"
})
@XmlRootElement(name = "altaResolInmeditaUserResponse")
public class AltaResolInmeditaUserResponse {

    @XmlElement(required = true, nillable = true)
    protected ResultadoAltaWS altaResolInmeditaUserReturn;

    /**
     * Gets the value of the altaResolInmeditaUserReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoAltaWS }
     *     
     */
    public ResultadoAltaWS getAltaResolInmeditaUserReturn() {
        return altaResolInmeditaUserReturn;
    }

    /**
     * Sets the value of the altaResolInmeditaUserReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoAltaWS }
     *     
     */
    public void setAltaResolInmeditaUserReturn(ResultadoAltaWS value) {
        this.altaResolInmeditaUserReturn = value;
    }

}
