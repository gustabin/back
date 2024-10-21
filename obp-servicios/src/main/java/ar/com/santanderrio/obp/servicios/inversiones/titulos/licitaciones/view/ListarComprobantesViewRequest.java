/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;

/**
 * The Class ListarComprobantesViewRequest.
 */
public class ListarComprobantesViewRequest {

	/** The cuentas titulo. */
	private List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentas titulo
	 */
	public List<CuentaTituloView> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the new cuentas titulo
	 */
	public void setCuentasTitulo(List<CuentaTituloView> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}
}
