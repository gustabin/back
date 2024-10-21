
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for baseGatewayRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseGatewayRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://visa.com.ar/visahome/integration/ws/mv/message}baseGatewayMessage">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseGatewayRequest", namespace = "http://visa.com.ar/visahome/integration/ws/mv/message")
@XmlSeeAlso({
    EliminarViajeRequest.class,
    ObtenerViajesRequest.class,
    ObtenerTarjetasDelSocioRequest.class,
    ConfirmarViajeRequest.class
})
public class BaseGatewayRequest
    extends BaseGatewayMessage
{


}
