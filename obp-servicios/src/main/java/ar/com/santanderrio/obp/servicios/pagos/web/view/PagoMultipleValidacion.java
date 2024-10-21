/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;

/**
 * The Class PagoMultipleView. DTO que se utiliza para cargar los mensajes y/o
 * validaciones de un pago multiple. Extiende de la clase item mensaje respuesta
 * ya que utiliza su misma estructura.
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @see {@link ItemMensajeRespuesta}
 * @see {@link PagoMultipleView}
 * @since Dec 29, 2016
 */
@XmlRootElement(name = "pagoMultipleView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagoMultipleValidacion extends ItemMensajeRespuesta {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5989915634272260628L;

}
