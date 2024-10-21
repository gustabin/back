/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.manager.TipoDeCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;

/**
 * The Class AgregarEmpleadoView.
 */
public class AgregarEmpleadoView {

	/** The cuentas view. */
	private CuentasView cuentasView;

	/** The tipos de cuentas cuentas view. */
	private List<TipoDeCuentaView> tiposDeCuentasView;

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
	 * Gets the tipos de cuentas view.
	 *
	 * @return the tipos de cuentas view
	 */
	public List<TipoDeCuentaView> getTiposDeCuentasView() {
		return tiposDeCuentasView;
	}

	/**
	 * Sets the tipos de cuentas view.
	 *
	 * @param tiposDeCuentasView
	 *            the new tipos de cuentas view
	 */
	public void setTiposDeCuentasView(List<TipoDeCuentaView> tiposDeCuentasView) {
		this.tiposDeCuentasView = tiposDeCuentasView;
	}

}
