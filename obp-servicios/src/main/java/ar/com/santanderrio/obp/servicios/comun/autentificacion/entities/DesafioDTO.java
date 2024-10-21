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
 * The Class DesafioDTO.
 * 
 * @author ignacio.valek
 * @author emilio.watemberg
 * @since Sep 22, 2016.
 * 
 */
@XmlRootElement(name = "desafioDTO", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesafioDTO {

	/** The mensaje. */
	private String mensaje;

	/** The tipo desafio. */
	private TipoDesafioEnum tipoDesafio;

	/** The coordenada 1. */
	private String coordenada1;

	/** The coordenada 2. */
	private String coordenada2;

	/** The token. */
	private String token;

	/**
	 * Instantiates a new desafio DTO.
	 */
	public DesafioDTO() {
	}

	/**
	 * Instantiates a new desafio DTO.
	 *
	 * @param tipoDesafioEnum
	 *            the tipo desafio enum
	 */
	public DesafioDTO(TipoDesafioEnum tipoDesafioEnum) {
		tipoDesafio = tipoDesafioEnum;
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

	/**
	 * Gets the coordenada 1.
	 *
	 * @return the coordenada 1
	 */
	public String getCoordenada1() {
		return coordenada1;
	}

	/**
	 * Sets the coordenada 1.
	 *
	 * @param coordenada1
	 *            the new coordenada 1
	 */
	public void setCoordenada1(String coordenada1) {
		this.coordenada1 = coordenada1;
	}

	/**
	 * Gets the coordenada 2.
	 *
	 * @return the coordenada 2
	 */
	public String getCoordenada2() {
		return coordenada2;
	}

	/**
	 * Sets the coordenada 2.
	 *
	 * @param coordenada2
	 *            the new coordenada 2
	 */
	public void setCoordenada2(String coordenada2) {
		this.coordenada2 = coordenada2;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token
	 *            the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

}
