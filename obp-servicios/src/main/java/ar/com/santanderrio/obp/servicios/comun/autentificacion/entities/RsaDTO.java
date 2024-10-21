/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;

/**
 * The Class rsaDTO. Clase abstracta de la que deben extender las operaciones
 * que necesitan evaluación de riesgo.
 *
 * @author B039542 - ignacio_valek@itrsa.com.ar - 08/11/2016
 */
public abstract class RsaDTO extends View {

	/** The tipo operacion. */
	private OperacionesRSAEnum tipoOperacion;
	
	/** The ignorar RSA. */
	private Boolean ignorarRSA = Boolean.FALSE;
	
	private Boolean validarBloqueo = Boolean.FALSE;
	
	private Boolean stopOperacionErrorRSA = Boolean.FALSE;

	private String reglaRsaTis;
	
	/**
	 * @return the validarBloqueo
	 */
	public Boolean getValidarBloqueo() {
		return validarBloqueo;
	}

	/**
	 * @param validarBloqueo the validarBloqueo to set
	 */
	public void setValidarBloqueo(Boolean validarBloqueo) {
		this.validarBloqueo = validarBloqueo;
	}

	/**
	 * Instantiates a new rsa DTO.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 */
	public RsaDTO(OperacionesRSAEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipo operacion
	 */
	public OperacionesRSAEnum getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(OperacionesRSAEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Gets the ignorar RSA.
	 *
	 * @return the ignorar RSA
	 */
	public Boolean getIgnorarRSA() {
		return ignorarRSA;
	}

	/**
	 * Sets the ignorar RSA.
	 *
	 * @param ignorarRSA
	 *            the new ignorar RSA
	 */
	public void setIgnorarRSA(Boolean ignorarRSA) {
		this.ignorarRSA = ignorarRSA;
	}

	public Boolean getStopOperacionErrorRSA() {
		return stopOperacionErrorRSA;
	}

	public void setStopOperacionErrorRSA(Boolean stopOperacionRSA) {
		this.stopOperacionErrorRSA = stopOperacionRSA;
	}

	public String getReglaRsaTis() {
		return reglaRsaTis;
	}

	public void setReglaRsaTis(String reglaRsaTis) {
		this.reglaRsaTis = reglaRsaTis;
	}
}
