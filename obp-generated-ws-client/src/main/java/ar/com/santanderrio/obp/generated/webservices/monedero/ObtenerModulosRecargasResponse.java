
package ar.com.santanderrio.obp.generated.webservices.monedero;

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
 *         &lt;element name="ObtenerModulosRecargasResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "obtenerModulosRecargasResult"
})
@XmlRootElement(name = "ObtenerModulosRecargasResponse")
public class ObtenerModulosRecargasResponse {

    @XmlElement(name = "ObtenerModulosRecargasResult")
    protected String obtenerModulosRecargasResult;

    /**
     * Gets the value of the obtenerModulosRecargasResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObtenerModulosRecargasResult() {
        return obtenerModulosRecargasResult;
    }

    /**
     * Sets the value of the obtenerModulosRecargasResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObtenerModulosRecargasResult(String value) {
        this.obtenerModulosRecargasResult = value;
    }

}
