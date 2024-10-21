
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for confirmarViajeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="confirmarViajeRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://visa.com.ar/visahome/integration/ws/mv/message}baseGatewayRequest">
 *       &lt;sequence>
 *         &lt;element name="reintento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viaje" type="{http://visa.com.ar/visahome/integration/ws/mv/model}viaje"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmarViajeRequest", propOrder = {
    "reintento",
    "telefono",
    "viaje"
})
public class ConfirmarViajeRequest
    extends BaseGatewayRequest
{

    protected Boolean reintento;
    protected String telefono;
    @XmlElement(required = true)
    protected Viaje viaje;

    /**
     * Gets the value of the reintento property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReintento() {
        return reintento;
    }

    /**
     * Sets the value of the reintento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReintento(Boolean value) {
        this.reintento = value;
    }

    /**
     * Gets the value of the telefono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the value of the telefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Gets the value of the viaje property.
     * 
     * @return
     *     possible object is
     *     {@link Viaje }
     *     
     */
    public Viaje getViaje() {
        return viaje;
    }

    /**
     * Sets the value of the viaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Viaje }
     *     
     */
    public void setViaje(Viaje value) {
        this.viaje = value;
    }

	@Override
	public String toString() {
		return "ConfirmarViajeRequest [reintento=" + reintento + ", telefono=" + telefono + ", viaje=" + viaje + "]";
	}

    
}
