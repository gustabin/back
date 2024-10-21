
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eliminarViajeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eliminarViajeRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://visa.com.ar/visahome/integration/ws/mv/message}baseGatewayRequest">
 *       &lt;sequence>
 *         &lt;element name="identificadorViaje" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eliminarViajeRequest", propOrder = {
    "identificadorViaje"
})
public class EliminarViajeRequest
    extends BaseGatewayRequest
{

    protected long identificadorViaje;

    /**
     * Gets the value of the identificadorViaje property.
     * 
     */
    public long getIdentificadorViaje() {
        return identificadorViaje;
    }

    /**
     * Sets the value of the identificadorViaje property.
     * 
     */
    public void setIdentificadorViaje(long value) {
        this.identificadorViaje = value;
    }

}
