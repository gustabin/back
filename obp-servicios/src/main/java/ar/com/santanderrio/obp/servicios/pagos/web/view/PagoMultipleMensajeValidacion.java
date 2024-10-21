/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class PagoMultipleView. DTO que se utiliza para cargar los mensajes y/o
 * validaciones de un pago multiple.
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @see {@link PagoMultipleView}
 * @since Dec 29, 2016
 */
@XmlRootElement(name = "pagoMultipleMensajeValidacion", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagoMultipleMensajeValidacion {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5989915634272260628L;

	/** The codigo. */
	String codigo;

	/** The mensaje. */
	String mensaje;

	/**
	 * Instantiates a new pago multiple validacion.
	 */
	public PagoMultipleMensajeValidacion() {
		super();
	}

	/**
	 * Instantiates a new pago multiple validacion.
	 *
	 * @param codigo
	 *            the codigo
	 * @param mensaje
	 *            the mensaje
	 */
	public PagoMultipleMensajeValidacion(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta#
	 * getMensaje()
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta#
	 * setMensaje(java.lang.String)
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
