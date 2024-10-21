/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import org.beanio.annotation.Field;

/**
 * The Class ComprobanteDebitoAutomatico.
 */
public class ComprobanteDebitoAutomatico {

	/** The fecha vencimiento DDI. */
	@Field
	private String fechaVencimientoDDI;

	/** The fecha debito revision DDI. */
	@Field
	private String fechaDebitoRevisionDDI;

	/** The id debito DDI. */
	@Field
	private String idDebitoDDI;

	/** The importe debito. */
	@Field
	private String importeDebito;

	/** The importe debito original DDI. */
	@Field
	private String importeDebitoOriginalDDI;

	/** The cotizacion adhesion. */
	@Field
	private String cotizacionAdhesion;

	/** The tipo cuenta debito. */
	@Field
	private String tipoCuentaDebito;

	/** The sucursal cuenta debito. */
	@Field
	private String sucursalCuentaDebito;

	/** The nro cuenta producto debito. */
	@Field
	private String nroCuentaProductoDebito;

	/** The tipo consulta DDI. */
	@Field
	private String tipoConsultaDDI;

	/** The codigo estado DDI. */
	@Field
	private String codigoEstadoDDI;

	/**
	 * Gets the fecha vencimiento DDI.
	 *
	 * @return the fecha vencimiento DDI
	 */
	public String getFechaVencimientoDDI() {
		return fechaVencimientoDDI;
	}

	/**
	 * Sets the fecha vencimiento DDI.
	 *
	 * @param fechaVencimientoDDI
	 *            the new fecha vencimiento DDI
	 */
	public void setFechaVencimientoDDI(String fechaVencimientoDDI) {
		this.fechaVencimientoDDI = fechaVencimientoDDI;
	}

	/**
	 * Gets the fecha debito revision DDI.
	 *
	 * @return the fecha debito revision DDI
	 */
	public String getFechaDebitoRevisionDDI() {
		return fechaDebitoRevisionDDI;
	}

	/**
	 * Sets the fecha debito revision DDI.
	 *
	 * @param fechaDebitoRevisionDDI
	 *            the new fecha debito revision DDI
	 */
	public void setFechaDebitoRevisionDDI(String fechaDebitoRevisionDDI) {
		this.fechaDebitoRevisionDDI = fechaDebitoRevisionDDI;
	}

	/**
	 * Gets the id debito DDI.
	 *
	 * @return the id debito DDI
	 */
	public String getIdDebitoDDI() {
		return idDebitoDDI;
	}

	/**
	 * Sets the id debito DDI.
	 *
	 * @param idDebitoDDI
	 *            the new id debito DDI
	 */
	public void setIdDebitoDDI(String idDebitoDDI) {
		this.idDebitoDDI = idDebitoDDI;
	}

	/**
	 * Gets the importe debito.
	 *
	 * @return the importe debito
	 */
	public String getImporteDebito() {
		return importeDebito;
	}

	/**
	 * Sets the importe debito.
	 *
	 * @param importeDebito
	 *            the new importe debito
	 */
	public void setImporteDebito(String importeDebito) {
		this.importeDebito = importeDebito;
	}

	/**
	 * Gets the importe debito original DDI.
	 *
	 * @return the importe debito original DDI
	 */
	public String getImporteDebitoOriginalDDI() {
		return importeDebitoOriginalDDI;
	}

	/**
	 * Sets the importe debito original DDI.
	 *
	 * @param importeDebitoOriginalDDI
	 *            the new importe debito original DDI
	 */
	public void setImporteDebitoOriginalDDI(String importeDebitoOriginalDDI) {
		this.importeDebitoOriginalDDI = importeDebitoOriginalDDI;
	}

	/**
	 * Gets the cotizacion adhesion.
	 *
	 * @return the cotizacion adhesion
	 */
	public String getCotizacionAdhesion() {
		return cotizacionAdhesion;
	}

	/**
	 * Sets the cotizacion adhesion.
	 *
	 * @param cotizacionAdhesion
	 *            the new cotizacion adhesion
	 */
	public void setCotizacionAdhesion(String cotizacionAdhesion) {
		this.cotizacionAdhesion = cotizacionAdhesion;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursal cuenta debito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the new sucursal cuenta debito
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

	/**
	 * Gets the nro cuenta producto debito.
	 *
	 * @return the nro cuenta producto debito
	 */
	public String getNroCuentaProductoDebito() {
		return nroCuentaProductoDebito;
	}

	/**
	 * Sets the nro cuenta producto debito.
	 *
	 * @param nroCuentaProductoDebito
	 *            the new nro cuenta producto debito
	 */
	public void setNroCuentaProductoDebito(String nroCuentaProductoDebito) {
		this.nroCuentaProductoDebito = nroCuentaProductoDebito;
	}

	/**
	 * Gets the tipo consulta DDI.
	 *
	 * @return the tipo consulta DDI
	 */
	public String getTipoConsultaDDI() {
		return tipoConsultaDDI;
	}

	/**
	 * Sets the tipo consulta DDI.
	 *
	 * @param tipoConsultaDDI
	 *            the new tipo consulta DDI
	 */
	public void setTipoConsultaDDI(String tipoConsultaDDI) {
		this.tipoConsultaDDI = tipoConsultaDDI;
	}

	/**
	 * Gets the codigo estado DDI.
	 *
	 * @return the codigo estado DDI
	 */
	public String getCodigoEstadoDDI() {
		return codigoEstadoDDI;
	}

	/**
	 * Sets the codigo estado DDI.
	 *
	 * @param codigoEstadoDDI
	 *            the new codigo estado DDI
	 */
	public void setCodigoEstadoDDI(String codigoEstadoDDI) {
		this.codigoEstadoDDI = codigoEstadoDDI;
	}

}
