
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for confirmarViajeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="confirmarViajeResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://visa.com.ar/visahome/integration/ws/mv/message}baseGatewayResponse">
 *       &lt;sequence>
 *         &lt;element name="viaje" type="{http://visa.com.ar/visahome/integration/ws/mv/model}viaje" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmarViajeResponse", propOrder = {
    "viaje"
})
public class ConfirmarViajeResponse
    extends BaseGatewayResponse
{

    protected Viaje viaje;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfirmarViajeResponse [viaje=" + viaje + ", CodigoError=" + getCodigoError()
				+ ", DescripcionError=" + getDescripcionError() + ", TipoDocumento=" + getTipoDocumento()
				+ ", NumeroDocumento=" + getNumeroDocumento() + ", Sexo=" + getSexo() + ", EmailSocio="
				+ getEmailSocio() + "]";
	}
    
}
