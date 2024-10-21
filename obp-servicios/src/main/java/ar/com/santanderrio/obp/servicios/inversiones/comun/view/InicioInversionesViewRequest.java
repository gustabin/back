/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * The Class InicioInversionesViewRequest.
 */
public class InicioInversionesViewRequest {

	/** The tipo de operacion. */
	@NotNull
	@Pattern(regexp = "FCI|PF|TOD|TV|AC")
	private String tipoDeOperacion;

	/**
	 * Gets the tipo de operacion.
	 *
	 * @return the tipo de operacion
	 */
	public String getTipoDeOperacion() {
		return tipoDeOperacion;
	}

	/**
	 * Sets the tipo de operacion.
	 *
	 * @param tipoDeOPeracion
	 *            the new tipo de operacion
	 */
	public void setTipoDeOperacion(String tipoDeOPeracion) {
		this.tipoDeOperacion = tipoDeOPeracion;
	}

}
