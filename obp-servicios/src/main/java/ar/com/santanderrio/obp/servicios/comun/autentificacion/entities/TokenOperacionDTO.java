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
 * Esta clase representa el objeto DTO de Token para la autentificacion.
 *
 * @author ignacio.valek
 * @since Sep 26, 2016.
 */
@XmlRootElement(name = "tokenDTO", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenOperacionDTO extends OperacionDTO {

	/** The ingreso token. */
	private String ingresoToken;

	/**
	 * Instantiates a new token operacion DTO.
	 */
	public TokenOperacionDTO() {
		super(TipoDesafioEnum.TOKEN);
	}

	/**
	 * Instantiates a new token operacion DTO.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public TokenOperacionDTO(String mensaje) {
		super(mensaje, TipoDesafioEnum.TOKEN);
	}

	/**
	 * Gets the ingreso token.
	 *
	 * @return the ingreso token
	 */
	public String getIngresoToken() {
		return ingresoToken;
	}

	/**
	 * Sets the ingreso token.
	 *
	 * @param ingresoToken
	 *            the new ingreso token
	 */
	public void setIngresoToken(String ingresoToken) {
		this.ingresoToken = ingresoToken;
	}

}
