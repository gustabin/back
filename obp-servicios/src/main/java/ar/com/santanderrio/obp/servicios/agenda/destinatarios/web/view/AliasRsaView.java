/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class AliasRsaView.
 */
public class AliasRsaView extends RsaDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new alias rsa view.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 */
	public AliasRsaView(OperacionesRSAEnum tipoOperacion) {
		super(tipoOperacion);
	}

	/** The desafio. */
	@JsonIgnore
	@JsonManagedReference
	protected AutentificacionDTO desafio;

	/** The alias cbu. */
	protected String aliasCbu;

	/** The cbu. */
	@Pattern(regexp = "^[0-9]{22}$")
	protected String cbu;

	/** The cuit. */
	protected String cuit;

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
	 * Gets the alias cbu.
	 *
	 * @return the aliasCbu
	 */
	public String getAliasCbu() {
		return aliasCbu;
	}

	/**
	 * Sets the alias cbu.
	 *
	 * @param aliasCbu
	 *            the aliasCbu to set
	 */
	public void setAliasCbu(String aliasCbu) {
		this.aliasCbu = aliasCbu;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

}
