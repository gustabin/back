/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

/**
 * Encapsula los datos requeridos para la cunsulta de tenencias de un cliente.
 *
 * @author marcelo.ruiz
 */
public class TipoBancaDTO {

	/** el tipo de banca seleccionada. */
	private String tipoBanca;

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
