/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view;

/**
 * The Class ConfirmacionVentaTitulosInView.
 */
public class ConfirmacionVentaTitulosInView {

	/** The tipo especie. */
	private String tipoEspecie;
	
	/** The sucursal cuenta. */
	private String sucursalCuenta;
	
	/** The cuenta titulo. */
	private String cuentaTitulo;
	
	/** The tipo cuenta. */
	private String tipoCuenta;
	
	/** The moneda operacion. */
	private String monedaOperacion;
	
	/** The numero cuenta. */
	private String numeroCuenta;
	
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

	/** The hora referencia. */
	private String horaReferencia;
	
	/** The fecha referencia. */
	private String fechaReferencia;
	
	/** The fecha liquidacion. */
	private String fechaLiquidacion;
	
	/** The nombre especie. */
	private String nombreEspecie;
	
	/** The id cumplimiento. */
	private Long idCumplimiento;
	
	/** The instrumento. */
	private String instrumento;
	
	/** The codigo especie. */
	private String codigoEspecie;
	
	/** The es banca privada. */
	private Boolean esBancaPrivada = false;
	
	private String bonificacion;
	
	private String comisionOriginal;
	
	private String comision;
	
	private String informacion;
	
	private boolean tieneBonificacion;
	
	
	/**
	 * Gets the tipo especie.
	 *
	 * @return the tipo especie
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo especie.
	 *
	 * @param tipoEspecie
	 *            the new tipo especie
	 */
	public void setTipoEspecie(String tipoEspecie) {
		this.tipoEspecie = tipoEspecie;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
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
	 * Gets the moneda operacion.
	 *
	 * @return the moneda operacion
	 */
	public String getMonedaOperacion() {
		return monedaOperacion;
	}

	/**
	 * Sets the moneda operacion.
	 *
	 * @param monedaOperacion
	 *            the new moneda operacion
	 */
	public void setMonedaOperacion(String monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
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
	 * Gets the cantidad titulo.
	 *
	 * @return the cantidad titulo
	 */
	public String getCantidadTitulo() {
		return cantidadTitulo;
	}

	/**
	 * Sets the cantidad titulo.
	 *
	 * @param cantidadTitulo
	 *            the new cantidad titulo
	 */
	public void setCantidadTitulo(String cantidadTitulo) {
		this.cantidadTitulo = cantidadTitulo;
	}

	/**
	 * Gets the especie codigo.
	 *
	 * @return the especie codigo
	 */
	public String getEspecieCodigo() {
		return especieCodigo;
	}

	/**
	 * Sets the especie codigo.
	 *
	 * @param especieCodigo
	 *            the new especie codigo
	 */
	public void setEspecieCodigo(String especieCodigo) {
		this.especieCodigo = especieCodigo;
	}

	/**
	 * Gets the cotizacion limite.
	 *
	 * @return the cotizacion limite
	 */
	public String getCotizacionLimite() {
		return cotizacionLimite;
	}

	/**
	 * Sets the cotizacion limite.
	 *
	 * @param cotizacionLimite
	 *            the new cotizacion limite
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
	 *            the new cotizacion
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
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the hora referencia.
	 *
	 * @return the hora referencia
	 */
	public String getHoraReferencia() {
		return horaReferencia;
	}

	/**
	 * Sets the hora referencia.
	 *
	 * @param horaReferencia
	 *            the new hora referencia
	 */
	public void setHoraReferencia(String horaReferencia) {
		this.horaReferencia = horaReferencia;
	}

	/**
	 * Gets the fecha referencia.
	 *
	 * @return the fecha referencia
	 */
	public String getFechaReferencia() {
		return fechaReferencia;
	}

	/**
	 * Sets the fecha referencia.
	 *
	 * @param fechaReferencia
	 *            the new fecha referencia
	 */
	public void setFechaReferencia(String fechaReferencia) {
		this.fechaReferencia = fechaReferencia;
	}

	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fecha liquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the new fecha liquidacion
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the nombre especie.
	 *
	 * @return the nombre especie
	 */
	public String getNombreEspecie() {
		return nombreEspecie;
	}

	/**
	 * Sets the nombre especie.
	 *
	 * @param nombreEspecie
	 *            the new nombre especie
	 */
	public void setNombreEspecie(String nombreEspecie) {
		this.nombreEspecie = nombreEspecie;
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

	/**
	 * Gets the instrumento.
	 *
	 * @return the instrumento
	 */
	public String getInstrumento() {
		return instrumento;
	}

	/**
	 * Sets the instrumento.
	 *
	 * @param instrumento
	 *            the new instrumento
	 */
	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	/**
	 * Gets the codigo especie.
	 *
	 * @return the codigo especie
	 */
	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	/**
	 * Sets the codigo especie.
	 *
	 * @param codigoEspecie
	 *            the new codigo especie
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	/**
	 * Gets the es banca privada.
	 *
	 * @return the es banca privada
	 */
	public Boolean getEsBancaPrivada() {
		return esBancaPrivada;
	}

	/**
	 * Sets the es banca privada.
	 *
	 * @param esBancaPrivada
	 *            the new es banca privada
	 */
	public void setEsBancaPrivada(Boolean esBancaPrivada) {
		this.esBancaPrivada = esBancaPrivada;
	}
	
	/* Gets the bonificacion.
	 *
	 * @return the bonificacion
	 */
	public String getBonificacion() {
		return bonificacion;
	}

	/**
	 * Sets the bonificacion.
	 *
	 * @param bonificacion
	 *            the bonificacion to set
	 */
	public void setBonificacion(String bonificacion) {
		this.bonificacion = bonificacion;
	}

	/**
	 * Gets the comisionOriginal.
	 *
	 * @return the comisionOriginal
	 */
	public String getComisionOriginal() {
		return comisionOriginal;
	}

	/**
	 * Sets the comisionOriginal.
	 *
	 * @param comisionOriginal
	 *            the comisionOriginal to set
	 */
	public void setComisionOriginal(String comisionOriginal) {
		this.comisionOriginal = comisionOriginal;
	}

	/**
	 * Gets the comision.
	 *
	 * @return the comision
	 */
	public String getComision() {
		return comision;
	}

	/**
	 * Sets the comision.
	 *
	 * @param comision
	 *            the comision to set
	 */
	public void setComision(String comision) {
		this.comision = comision;
	}

	/**
	 * Gets the informacion.
	 *
	 * @return the informacion
	 */
	public String getInformacion() {
		return informacion;
	}

	/**
	 * Sets the informacion.
	 *
	 * @param informacion
	 *            the informacion to set
	 */
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	/**
	 * Gets the tieneBonificacion.
	 *
	 * @return the tieneBonificacion
	 */
	public boolean getTieneBonificacion() {
		return tieneBonificacion;
	}

	/**
	 * Sets the tieneBonificacion.
	 *
	 * @param tieneBonificacion
	 *            the tieneBonificacion to set
	 */
	public void setTieneBonificacion(boolean tieneBonificacion) {
		this.tieneBonificacion = tieneBonificacion;
	}
				
}