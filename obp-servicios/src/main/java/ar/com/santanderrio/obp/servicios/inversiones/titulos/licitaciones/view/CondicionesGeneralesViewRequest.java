/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The Class CondicionesGeneralesViewRequest.
 */
public class CondicionesGeneralesViewRequest {


	/** The condiciones aceptadas. */
	@NotNull
	private boolean condicionesAceptadas;
	
	/** The tipo banca. */
	@Pattern(regexp = "BPr|BPe")
	private String tipoBanca;

	/**
	 * Checks if is condiciones aceptadas.
	 *
	 * @return true, if is condiciones aceptadas
	 */
	public boolean isCondicionesAceptadas() {
		return condicionesAceptadas;
	}

	/**
	 * Sets the condiciones aceptadas.
	 *
	 * @param condicionesAceptadas
	 *            the new condiciones aceptadas
	 */
	public void setCondicionesAceptadas(boolean condicionesAceptadas) {
		this.condicionesAceptadas = condicionesAceptadas;
	}
	
	/**
	 * Gets the tipo banca.
	 *
	 * @return the tipo banca
	 */
	public String getTipoBanca() {
		return tipoBanca;
	}
	
	/**
	 * Sets the tipo banca.
	 *
	 * @param tipoBanca
	 *            the new tipo banca
	 */
	public void setTipoBanca(String tipoBanca) {
		this.tipoBanca = tipoBanca;
	}
	
	
}
