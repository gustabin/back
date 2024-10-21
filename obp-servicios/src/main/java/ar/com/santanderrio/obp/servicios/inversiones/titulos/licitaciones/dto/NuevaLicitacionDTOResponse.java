/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.CuentaTituloParaLicitarView;

/**
 * DTO con las listas de cuentas titulo habilitadas para licitar.
 */
public class NuevaLicitacionDTOResponse {

	/** The cuentas titulo. */
	private List<CuentaTituloParaLicitarView> cuentasTitulo = new ArrayList<CuentaTituloParaLicitarView>();

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentas titulo
	 */
	public List<CuentaTituloParaLicitarView> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the new cuentas titulo
	 */
	public void setCuentasTitulo(List<CuentaTituloParaLicitarView> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}

}
