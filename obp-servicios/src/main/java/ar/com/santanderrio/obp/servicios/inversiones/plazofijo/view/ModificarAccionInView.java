/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Request para modificar la accion al vencimiento de la tenencia de un plazo
 * fijo.
 * 
 * @author b039920
 *
 */
public class ModificarAccionInView {

	/** The cuenta plazo. */
	@NotNull
	private String cuentaPlazo;

	/** The secuencia. */
	@NotNull
	private String secuencia;

	/** The codigo accion. */
	@NotNull
	private String codigoAccion;

	/** The nombre plazo fijo. */
	@NotNull
	private String nombrePlazoFijo;

	/** The moneda. */
	@NotNull
	@Pattern(regexp = "(ARS)|(USD)")
	private String moneda;

	/**
	 * Gets the cuenta plazo.
	 *
	 * @return the cuenta plazo
	 */
	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	/**
	 * Sets the cuenta plazo.
	 *
	 * @param cuentaPlazo
	 *            the new cuenta plazo
	 */
	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the new secuencia
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Gets the codigo accion.
	 *
	 * @return the codigo accion
	 */
	public String getCodigoAccion() {
		return codigoAccion;
	}

	/**
	 * Sets the codigo accion.
	 *
	 * @param codigoAccion
	 *            the new codigo accion
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}

	/**
	 * Gets the nombre plazo fijo.
	 *
	 * @return the nombre plazo fijo
	 */
	public String getNombrePlazoFijo() {
		return nombrePlazoFijo;
	}

	/**
	 * Sets the nombre plazo fijo.
	 *
	 * @param nombrePlazoFijo
	 *            the new nombre plazo fijo
	 */
	public void setNombrePlazoFijo(String nombrePlazoFijo) {
		this.nombrePlazoFijo = nombrePlazoFijo;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

}
