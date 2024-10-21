/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.List;

/**
 * The Class ResumenesMensualesCuentaView.
 */
public class ResumenesMensualesCuentaView {

	/** The resumenes. */
	private List<ResumenMesualCuentaView> resumenes;

	/** The mensaje. */
	private String mensaje;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The resumen Online. */
	private Boolean resumenOnline;

	/**
	 * Gets the resumenes.
	 *
	 * @return the resumenes
	 */
	public List<ResumenMesualCuentaView> getResumenes() {
		return resumenes;
	}

	/**
	 * Sets the resumenes.
	 *
	 * @param resumenes
	 *            the new resumenes
	 */
	public void setResumenes(List<ResumenMesualCuentaView> resumenes) {
		this.resumenes = resumenes;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
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
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the resumen online.
	 *
	 * @return the resumenOnline
	 */
	public Boolean getResumenOnline() {
		return resumenOnline;
	}

	/**
	 * Sets the resumen online.
	 *
	 * @param resumenOnline
	 *            the resumenOnline to set
	 */
	public void setResumenOnline(Boolean resumenOnline) {
		this.resumenOnline = resumenOnline;
	}

}
