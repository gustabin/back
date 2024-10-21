/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;

/**
 * The Class ConfiguracionPagoCuotaPrestamo.
 */
public class ConfiguracionPagoCuotaPrestamo extends AbstractPrestamoView {

	/** The cuentas view. */
	private CuentasView cuentasView;

	/** The id proceso. */
	private String idProceso;

	/**
	 * Gets the cuentas view.
	 *
	 * @return the cuentas view
	 */
	public CuentasView getCuentasView() {
		return cuentasView;
	}

	/**
	 * Sets the cuentas view.
	 *
	 * @param cuentasView
	 *            the new cuentas view
	 */
	public void setCuentasView(CuentasView cuentasView) {
		this.cuentasView = cuentasView;
	}

	/**
	 * Gets the id proceso.
	 *
	 * @return the id proceso
	 */
	public String getIdProceso() {
		return idProceso;
	}

	/**
	 * Sets the id proceso.
	 *
	 * @param idProceso
	 *            the new id proceso
	 */
	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}
}
