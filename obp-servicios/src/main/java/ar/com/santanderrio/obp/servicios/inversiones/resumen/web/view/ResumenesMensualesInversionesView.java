package ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view;

import java.util.List;

/**
 * The Class ResumenesMensualesInversionesView.
 */
public class ResumenesMensualesInversionesView {

	/** The resumenes. */
	private List<ResumenMensualInversionesView> resumenes;

	/** The numero cuenta. */
	private String numeroCuenta;

	/**
	 * Gets the resumenes.
	 *
	 * @return the resumenes
	 */
	public List<ResumenMensualInversionesView> getResumenes() {
		return resumenes;
	}

	/**
	 * Sets the resumenes.
	 *
	 * @param resumenes the new resumenes
	 */
	public void setResumenes(List<ResumenMensualInversionesView> resumenes) {
		this.resumenes = resumenes;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
}
