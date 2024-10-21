package ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view;

import java.util.List;

/**
 * The Class ResumenesInversionesView.
 */
public class ResumenesInversionesView {

	/** The cuentas BP. */
	private List<String> cuentasBP;

	/** The cuentas RTL. */
	private List<String> cuentasRTL;

	/**
	 * Gets the cuentas BP.
	 *
	 * @return the cuentas BP
	 */
	public List<String> getCuentasBP() {
		return cuentasBP;
	}

	/**
	 * Sets the cuentas BP.
	 *
	 * @param cuentasBP the new cuentas BP
	 */
	public void setCuentasBP(List<String> cuentasBP) {
		this.cuentasBP = cuentasBP;
	}

	/**
	 * Gets the cuentas RTL.
	 *
	 * @return the cuentas RTL
	 */
	public List<String> getCuentasRTL() {
		return cuentasRTL;
	}

	/**
	 * Sets the cuentas RTL.
	 *
	 * @param cuentasRTL the new cuentas RTL
	 */
	public void setCuentasRTL(List<String> cuentasRTL) {
		this.cuentasRTL = cuentasRTL;
	}
}
