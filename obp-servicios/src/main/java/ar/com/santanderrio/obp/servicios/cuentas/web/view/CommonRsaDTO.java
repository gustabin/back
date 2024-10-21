/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class CommonRsaDTO.
 */
public class CommonRsaDTO extends RsaDTO {

	/** The desafio. */
	@JsonIgnore
	@JsonManagedReference
	private AutentificacionDTO desafio;

	/** The valor desafio. */
	private String valorDesafio;

	/** The alias. */
	private String alias;

	/**
	 * Instantiates a new common rsa DTO.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 */
	public CommonRsaDTO(OperacionesRSAEnum tipoOperacion) {
		super(tipoOperacion);
	}

	/**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * Sets the desafio.
	 *
	 * @param desafio
	 *            the new desafio
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * Gets the valor desafio.
	 *
	 * @return the valor desafio
	 */
	public String getValorDesafio() {
		return valorDesafio;
	}

	/**
	 * Sets the valor desafio.
	 *
	 * @param valorDesafio
	 *            the new valor desafio
	 */
	public void setValorDesafio(String valorDesafio) {
		this.valorDesafio = valorDesafio;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

}
