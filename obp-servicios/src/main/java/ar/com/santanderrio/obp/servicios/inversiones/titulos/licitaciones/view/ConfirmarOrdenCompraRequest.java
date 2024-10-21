/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class ConfirmarOrdenCompraRequest.
 */
public class ConfirmarOrdenCompraRequest extends View {
	/** The serial Version UID. */
	private static final long serialVersionUID = -6153480384129757050L;

	/** The tipo especie. */
	private String tipoEspecie;

	/** The sucursal cuenta operativa. */
	private String sucursalCuentaOperativa;

	/** The cuenta titulo. */
	private String cuentaTitulo;

	/** The tipo cuenta operativa. */
	private String tipoCuentaOperativa;

	/** The moneda operacion. */
	private String monedaOperacion;

	/** The numero cuenta debito. */
	private String numeroCuentaDebito;

	/** The cantidad titulo. */
	private String cantidadTitulo;

	/** The especie codigo. */
	private String especieCodigo;

	/** The cotizacion limite. */
	private String cotizacionLimite;

	/** The cotizacion. */
	private String cotizacion;

	/** The plazo. */
	private String plazo;

	/** The fecha precio ref. */
	private String fechaPrecioRef;

	/** The hora precio ref. */
	private String horaPrecioRef;

	/** The importe debito credito. */
	private String importeDebitoCredito;

	/** The descripcion especie. */
	private String descripcionEspecie;

	/** The codigo amigable. */
	private String codigoAmigable;

	/** The aceptar contrato. */
	private boolean aceptarContrato;

	/** The legal contrato. */
	private String legalContrato;
	
	/** The id cumplimiento. */
	private Long idCumplimiento;

	/**
	 * Gets the tipo especie.
	 *
	 * @return the tipoEspecie
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo especie.
	 *
	 * @param tipoEspecie
	 *            the tipoEspecie to set
	 */
	public void setTipoEspecie(String tipoEspecie) {
		this.tipoEspecie = tipoEspecie;
	}

	/**
	 * Gets the sucursal cuenta operativa.
	 *
	 * @return the sucursalCuentaOperativa
	 */
	public String getSucursalCuentaOperativa() {
		return sucursalCuentaOperativa;
	}

	/**
	 * Sets the sucursal cuenta operativa.
	 *
	 * @param sucursalCuentaOperativa
	 *            the sucursalCuentaOperativa to set
	 */
	public void setSucursalCuentaOperativa(String sucursalCuentaOperativa) {
		this.sucursalCuentaOperativa = sucursalCuentaOperativa;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuentaTitulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the cuentaTitulo to set
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the tipo cuenta operativa.
	 *
	 * @return the tipoCuentaOperativa
	 */
	public String getTipoCuentaOperativa() {
		return tipoCuentaOperativa;
	}

	/**
	 * Sets the tipo cuenta operativa.
	 *
	 * @param tipoCuentaOperativa
	 *            the tipoCuentaOperativa to set
	 */
	public void setTipoCuentaOperativa(String tipoCuentaOperativa) {
		this.tipoCuentaOperativa = tipoCuentaOperativa;
	}

	/**
	 * Gets the moneda operacion.
	 *
	 * @return the monedaOperacion
	 */
	public String getMonedaOperacion() {
		return monedaOperacion;
	}

	/**
	 * Sets the moneda operacion.
	 *
	 * @param monedaOperacion
	 *            the monedaOperacion to set
	 */
	public void setMonedaOperacion(String monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}

	/**
	 * Gets the numero cuenta debito.
	 *
	 * @return the numeroCuentaDebito
	 */
	public String getNumeroCuentaDebito() {
		return numeroCuentaDebito;
	}

	/**
	 * Sets the numero cuenta debito.
	 *
	 * @param numeroCuentaDebito
	 *            the numeroCuentaDebito to set
	 */
	public void setNumeroCuentaDebito(String numeroCuentaDebito) {
		this.numeroCuentaDebito = numeroCuentaDebito;
	}

	/**
	 * Gets the cantidad titulo.
	 *
	 * @return the cantidadTitulo
	 */
	public String getCantidadTitulo() {
		return cantidadTitulo;
	}

	/**
	 * Sets the cantidad titulo.
	 *
	 * @param cantidadTitulo
	 *            the cantidadTitulo to set
	 */
	public void setCantidadTitulo(String cantidadTitulo) {
		this.cantidadTitulo = cantidadTitulo;
	}

	/**
	 * Gets the especie codigo.
	 *
	 * @return the especieCodigo
	 */
	public String getEspecieCodigo() {
		return especieCodigo;
	}

	/**
	 * Sets the especie codigo.
	 *
	 * @param especieCodigo
	 *            the especieCodigo to set
	 */
	public void setEspecieCodigo(String especieCodigo) {
		this.especieCodigo = especieCodigo;
	}

	/**
	 * Gets the cotizacion limite.
	 *
	 * @return the cotizacionLimite
	 */
	public String getCotizacionLimite() {
		return cotizacionLimite;
	}

	/**
	 * Sets the cotizacion limite.
	 *
	 * @param cotizacionLimite
	 *            the cotizacionLimite to set
	 */
	public void setCotizacionLimite(String cotizacionLimite) {
		this.cotizacionLimite = cotizacionLimite;
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public String getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacion to set
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the fecha precio ref.
	 *
	 * @return the fechaPrecioRef
	 */
	public String getFechaPrecioRef() {
		return fechaPrecioRef;
	}

	/**
	 * Sets the fecha precio ref.
	 *
	 * @param fechaPrecioRef
	 *            the fechaPrecioRef to set
	 */
	public void setFechaPrecioRef(String fechaPrecioRef) {
		this.fechaPrecioRef = fechaPrecioRef;
	}

	/**
	 * Gets the hora precio ref.
	 *
	 * @return the horaPrecioRef
	 */
	public String getHoraPrecioRef() {
		return horaPrecioRef;
	}

	/**
	 * Sets the hora precio ref.
	 *
	 * @param horaPrecioRef
	 *            the horaPrecioRef to set
	 */
	public void setHoraPrecioRef(String horaPrecioRef) {
		this.horaPrecioRef = horaPrecioRef;
	}

	/**
	 * Gets the importe debito credito.
	 *
	 * @return the importeDebitoCredito
	 */
	public String getImporteDebitoCredito() {
		return importeDebitoCredito;
	}

	/**
	 * Sets the importe debito credito.
	 *
	 * @param importeDebitoCredito
	 *            the importeDebitoCredito to set
	 */
	public void setImporteDebitoCredito(String importeDebitoCredito) {
		this.importeDebitoCredito = importeDebitoCredito;
	}

	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcion especie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the new descripcion especie
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	/**
	 * Gets the codigo amigable.
	 *
	 * @return the codigo amigable
	 */
	public String getCodigoAmigable() {
		return codigoAmigable;
	}

	/**
	 * Sets the codigo amigable.
	 *
	 * @param codigoAmigable
	 *            the new codigo amigable
	 */
	public void setCodigoAmigable(String codigoAmigable) {
		this.codigoAmigable = codigoAmigable;
	}

	/**
	 * Checks if is aceptar contrato.
	 *
	 * @return true, if is aceptar contrato
	 */
	public boolean isAceptarContrato() {
		return aceptarContrato;
	}

	/**
	 * Sets the aceptar contrato.
	 *
	 * @param aceptarContrato
	 *            the new aceptar contrato
	 */
	public void setAceptarContrato(boolean aceptarContrato) {
		this.aceptarContrato = aceptarContrato;
	}

	/**
	 * Gets the legal contrato.
	 *
	 * @return the legal contrato
	 */
	public String getLegalContrato() {
		return legalContrato;
	}

	/**
	 * Sets the legal contrato.
	 *
	 * @param legalContrato
	 *            the new legal contrato
	 */
	public void setLegalContrato(String legalContrato) {
		this.legalContrato = legalContrato;
	}

	/**
	 * Gets the id cumplimiento.
	 *
	 * @return the id cumplimiento
	 */
	public Long getIdCumplimiento() {
		return idCumplimiento;
	}

	/**
	 * Sets the id cumplimiento.
	 *
	 * @param idCumplimiento
	 *            the new id cumplimiento
	 */
	public void setIdCumplimiento(Long idCumplimiento) {
		this.idCumplimiento = idCumplimiento;
	}
}
