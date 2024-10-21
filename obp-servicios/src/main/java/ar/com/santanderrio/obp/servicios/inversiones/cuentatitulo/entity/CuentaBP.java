/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity;

/**
 * Modela la asociacion de cuenta titulo con operativa.
 *
 * @author marcelo.ruiz
 */
public class CuentaBP {
	/** The cuenta titulos. */
	private String cuentaTit;

	/** The cuenta operativa. */
	private String cuentaOp;

	/**
	 * Instantiates a new rta load tit.
	 *
	 * @param cuentaTit
	 *            the cuentaatit
	 * @param cuentaOp
	 *            the cuentabp
	 */
	public CuentaBP(String cuentaTit, String cuentaOp) {
		this.cuentaTit = cuentaTit;
		this.cuentaOp = cuentaOp;
	}

	/**
	 * Gets the cuentaatit.
	 *
	 * @return the cuentaatit
	 */
	public String getCuentaTit() {
		return cuentaTit;
	}

	/**
	 * Gets the cuentabp.
	 *
	 * @return the cuenta operativa
	 */
	public String getCuentaOp() {
		return cuentaOp;
	}

}
