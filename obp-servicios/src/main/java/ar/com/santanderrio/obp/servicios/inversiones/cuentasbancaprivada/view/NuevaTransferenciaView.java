/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * The Class NuevaTransferenciaView.
 */
public class NuevaTransferenciaView {

	/** The cuentas banca privada. */
	private List<CuentasAdhesionDebitoView> cuentasBancaPrivada = new ArrayList<CuentasAdhesionDebitoView>();
	
	/** The cuentas retail. */
	private List<CuentasAdhesionDebitoView> cuentasRetail = new ArrayList<CuentasAdhesionDebitoView>();

	/**
	 * Gets the cuentas banca privada.
	 *
	 * @return the cuentas banca privada
	 */
	public List<CuentasAdhesionDebitoView> getCuentasBancaPrivada() {
		return cuentasBancaPrivada;
	}

	/**
	 * Sets the cuentas banca privada.
	 *
	 * @param cuentasBancaPrivada
	 *            the new cuentas banca privada
	 */
	public void setCuentasBancaPrivada(List<CuentasAdhesionDebitoView> cuentasBancaPrivada) {
		this.cuentasBancaPrivada = cuentasBancaPrivada;
	}

	/**
	 * Gets the cuentas retail.
	 *
	 * @return the cuentas retail
	 */
	public List<CuentasAdhesionDebitoView> getCuentasRetail() {
		return cuentasRetail;
	}

	/**
	 * Sets the cuentas retail.
	 *
	 * @param cuentasRetail
	 *            the new cuentas retail
	 */
	public void setCuentasRetail(List<CuentasAdhesionDebitoView> cuentasRetail) {
		this.cuentasRetail = cuentasRetail;
	}
	
}
