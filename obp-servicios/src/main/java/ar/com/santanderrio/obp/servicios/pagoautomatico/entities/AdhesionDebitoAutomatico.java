/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.entities;

import java.io.Serializable;

/**
 * The Class AdhesionDebitoAutomatico.
 */
public class AdhesionDebitoAutomatico implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6361253919075296266L;

	/** The cuit. */
	private String cuit;

	/** The nombre servicio empresa. */
	private String nombreServicioEmpresa;

	/** The nro partida servicio empresa. */
	private String nroPartidaServicioEmpresa;

	/** The limite adhesion. */
	private String limiteAdhesion;

	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** The sucursal cuenta debito. */
	private String sucursalCuentaDebito;

	/** The nro cuenta producto debito. */
	private String nroCuentaProductoDebito;

	/** The nro orden firmante. */
	private String nroOrdenFirmante;

	/** The nro de comprobante. */
	private String nroDeComprobante;

	/** The fecha hora. */
	private String fechaHora;

	/** The mensaje feedback. */
	private String mensajeFeedback;

	/** The nombre fantasia. */
	private String nombreFantasia;

	/** The legales SEUO. */
	private String legalesSEUO;

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the nombre servicio empresa.
	 *
	 * @return the nombreServicioCuenta
	 */
	public String getNombreServicioEmpresa() {
		return nombreServicioEmpresa;
	}

	/**
	 * Sets the nombre servicio empresa.
	 *
	 * @param nombreServicioEmpresa
	 *            the nombreServicioCuenta to set
	 */
	public void setNombreServicioEmpresa(String nombreServicioEmpresa) {
		this.nombreServicioEmpresa = nombreServicioEmpresa;
	}

	/**
	 * Gets the nro partida servicio empresa.
	 *
	 * @return the nroPartidaServicioEmpresa
	 */
	public String getNroPartidaServicioEmpresa() {
		return nroPartidaServicioEmpresa;
	}

	/**
	 * Sets the nro partida servicio empresa.
	 *
	 * @param nroPartidaServicioEmpresa
	 *            the nroPartidaServicioEmpresa to set
	 */
	public void setNroPartidaServicioEmpresa(String nroPartidaServicioEmpresa) {
		this.nroPartidaServicioEmpresa = nroPartidaServicioEmpresa;
	}

	/**
	 * Gets the limite adhesion.
	 *
	 * @return the limiteAdhesion
	 */
	public String getLimiteAdhesion() {
		return limiteAdhesion;
	}

	/**
	 * Sets the limite adhesion.
	 *
	 * @param limiteAdhesion
	 *            the limiteAdhesion to set
	 */
	public void setLimiteAdhesion(String limiteAdhesion) {
		this.limiteAdhesion = limiteAdhesion;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipoCuentaDebito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the tipoCuentaDebito to set
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursalCuentaDebito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the sucursalCuentaDebito to set
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

	/**
	 * Gets the nro cuenta producto debito.
	 *
	 * @return the nroCuentaProductoDebito
	 */
	public String getNroCuentaProductoDebito() {
		return nroCuentaProductoDebito;
	}

	/**
	 * Sets the nro cuenta producto debito.
	 *
	 * @param nroCuentaProductoDebito
	 *            the nroCuentaProductoDebito to set
	 */
	public void setNroCuentaProductoDebito(String nroCuentaProductoDebito) {
		this.nroCuentaProductoDebito = nroCuentaProductoDebito;
	}

	/**
	 * Gets the nro orden firmante.
	 *
	 * @return the nroOrdenFirmante
	 */
	public String getNroOrdenFirmante() {
		return nroOrdenFirmante;
	}

	/**
	 * Sets the nro orden firmante.
	 *
	 * @param nroOrdenFirmante
	 *            the nroOrdenFirmante to set
	 */
	public void setNroOrdenFirmante(String nroOrdenFirmante) {
		this.nroOrdenFirmante = nroOrdenFirmante;
	}

	/**
	 * Gets the nro de comprobante.
	 *
	 * @return the nro de comprobante
	 */
	public String getNroDeComprobante() {
		return nroDeComprobante;
	}

	/**
	 * Sets the nro de comprobante.
	 *
	 * @param nroOrdenComprobante
	 *            the new nro de comprobante
	 */
	public void setNroDeComprobante(String nroOrdenComprobante) {
		this.nroDeComprobante = nroOrdenComprobante;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaComprobante
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaComprobante) {
		this.fechaHora = fechaComprobante;
	}

	/**
	 * Gets the mensajeFeedback.
	 *
	 * @return the mensajeFeedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param mensajeFeedback
	 *            the new mensaje feedback
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Gets the nombre fantasia.
	 *
	 * @return the nombre fantasia
	 */
	public String getNombreFantasia() {
		return nombreFantasia;
	}

	/**
	 * Sets the nombre fantasia.
	 *
	 * @param nombreFantasia
	 *            the new nombre fantasia
	 */
	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	/**
	 * Sets the legales SEUO.
	 *
	 * @param legalesSEUO
	 *            the new legales SEUO
	 */
	public void setLegalesSEUO(String legalesSEUO) {
		this.legalesSEUO = legalesSEUO;
	}

	/**
	 * Gets the legales SEUO.
	 *
	 * @return the legales SEUO
	 */
	public String getLegalesSEUO() {
		return legalesSEUO;
	}

	/**
	 * Sets the ear tipo cuenta debito.
	 *
	 * @param abreviatura
	 *            the new ear tipo cuenta debito
	 */
	public void setearTipoCuentaDebito(String abreviatura) {

		if ("CCP".equals(abreviatura)) {
			this.tipoCuentaDebito = "00";
		} else if ("CAP".equals(abreviatura)) {
			this.tipoCuentaDebito = "01";
		} else if ("CUP".equals(abreviatura) || "CUD".equals(abreviatura)) {
			this.tipoCuentaDebito = "02";
		} else if ("CCD".equals(abreviatura)) {
			this.tipoCuentaDebito = "03";
		} else if ("CAD".equals(abreviatura)) {
			this.tipoCuentaDebito = "04";
		}
	}

}
