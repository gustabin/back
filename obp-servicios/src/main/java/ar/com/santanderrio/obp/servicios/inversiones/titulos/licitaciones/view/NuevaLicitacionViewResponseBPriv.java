/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class NuevaLicitacionViewResponseBPriv.
 */
public class NuevaLicitacionViewResponseBPriv {
	
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
