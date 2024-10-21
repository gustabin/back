/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.CuentaTituloParaLicitarViewBPriv;

/**
 * The Class NuevaLicitacionDTOResponseBPriv.
 */
public class NuevaLicitacionDTOResponseBPriv {

	/** The cuentas titulo. */
	List<CuentaTituloParaLicitarViewBPriv> cuentasTitulo= new ArrayList<CuentaTituloParaLicitarViewBPriv>();

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentasTitulo
	 */
	public List<CuentaTituloParaLicitarViewBPriv> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the cuentasTitulo to set
	 */
	public void setCuentasTitulo(List<CuentaTituloParaLicitarViewBPriv> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}
}
