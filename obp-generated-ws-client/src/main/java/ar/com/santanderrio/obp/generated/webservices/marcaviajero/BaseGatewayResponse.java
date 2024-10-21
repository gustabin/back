
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for baseGatewayResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseGatewayResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://visa.com.ar/visahome/integration/ws/mv/message}baseGatewayMessage">
 *       &lt;sequence>
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="descripcionError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseGatewayResponse", namespace = "http://visa.com.ar/visahome/integration/ws/mv/message", propOrder = {
    "codigoError",
    "descripcionError"
})
@XmlSeeAlso({
    ObtenerTarjetasDelSocioResponse.class,
    ObtenerViajesResponse.class,
    EliminarViajeResponse.class,
    ConfirmarViajeResponse.class
})
public class BaseGatewayResponse
    extends BaseGatewayMessage
{

    @XmlElement(defaultValue = "0")
    protected Integer codigoError;
    protected String descripcionError;

    /**
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoError(Integer value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the descripcionError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionError() {
        return descripcionError;
    }

    /**
     * Sets the value of the descripcionError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionError(String value) {
        this.descripcionError = value;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseGatewayResponse [codigoError=" + codigoError + ", descripcionError=" + descripcionError + "]";
	}

}
