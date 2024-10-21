/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The Class ReversarCompraVentaRequestView.
 */
public class ReversarCompraVentaRequestView {

	/** The tipo banca. */
	@NotNull
	@Pattern(regexp = "(BP)|(BR)")
	private String tipoBanca;

	/** The tipo operacion. */
	@NotNull
	private String tipoOperacion;

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

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipo operacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

}
