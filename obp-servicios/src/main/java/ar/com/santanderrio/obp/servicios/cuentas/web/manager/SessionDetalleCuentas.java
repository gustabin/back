/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import java.util.List;

/**
 * The Interface SessionDetalleCuentas.
 */
public interface SessionDetalleCuentas {

	/**
	 * Gets the detalle cuenta list.
	 *
	 * @return the detalle cuenta list
	 */
	List<DetalleCuenta> getDetalleCuentaList();

	/**
	 * Setter para detalle cuenta list.
	 *
	 * @param detalleCuenta
	 *            el nuevo detalle cuenta list
	 */
	void setDetalleCuentaList(List<DetalleCuenta> detalleCuenta);

}
