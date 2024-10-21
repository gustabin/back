/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class DetalleCBUView.
 */
public class AltaAliasCBUView extends RsaDTO implements Serializable {

	/** The desafio. */
	@JsonIgnore
	@JsonManagedReference
	private AutentificacionDTO desafio;

	/**
	 * Instantiates a new alta alias CBU view.
	 */
	public AltaAliasCBUView() {
		super(OperacionesRSAEnum.ALTAALIAS);
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

}
