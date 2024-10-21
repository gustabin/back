/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;

/**
 * The Class OperacionDTO.
 * 
 * @author ignacio.valek
 * @author emilio.watemberg
 * @since Sep 22, 2016.
 * 
 */
@XmlRootElement(name = "autentificacionDTO", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class OperacionDTO {

	/** The mensaje. */
	private String mensaje;

	/** The tipo desafio. */
	private TipoDesafioEnum tipoDesafio;

	/**
	 * Instantiates a new operacion DTO.
	 *
	 * @param tipoDesafioEnum
	 *            the tipo desafio enum
	 */
	protected OperacionDTO(TipoDesafioEnum tipoDesafioEnum) {
		tipoDesafio = tipoDesafioEnum;
	}

	/**
	 * Instantiates a new operacion DTO.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @param tipoDesafio
	 *            the tipo desafio
	 */
	public OperacionDTO(String mensaje, TipoDesafioEnum tipoDesafio) {
		super();
		this.mensaje = mensaje;
		this.tipoDesafio = tipoDesafio;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
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
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the tipo desafio.
	 *
	 * @return the tipo desafio
	 */
	public TipoDesafioEnum getTipoDesafio() {
		return tipoDesafio;
	}

	/**
	 * Sets the tipo desafio.
	 *
	 * @param tipoDesafio
	 *            the new tipo desafio
	 */
	public void setTipoDesafio(TipoDesafioEnum tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}

}
