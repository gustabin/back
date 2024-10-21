/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Objeto utilizado para el Input del RescateSEI.
 * 
 * @author
 *
 */
public class ConfiguracionRescateBPrivInView {

	/** The cuenta operativa. */
	@NotNull
	@Pattern(regexp = "[7][0-9]{9}")
	private String cuentaOperativa;

	/** The sucursal. */
	@NotNull
	private String sucursal;

	@NotNull
	private String codigoFondo;

	/**
	 * Gets the cuenta operativa.
	 *
	 * @return the cuenta operativa
	 */
	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	/**
	 * Sets the cuenta operativa.
	 *
	 * @param cuentaOperativa
	 *            the new cuenta operativa
	 */
	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the codigoFondo.
	 *
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigoFondo.
	 *
	 * @param codigoFondo
	 *            the new codigoFondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

}
