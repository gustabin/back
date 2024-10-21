/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosCompraVtaTitulosEntity.
 */
public class DatosCompraVtaTitulosEntity extends EntityBaseTitulos{
	
	/** The tipo accion. */
	@JsonProperty("TipoAccion")
	private String tipoAccion;
	
	/** The fecha liquidacion. */
	@JsonProperty("FechaLiquidacion")
	private String fechaLiquidacion;
		
	/** The id cumplimiento. */
	@JsonProperty("IdCumplimiento")
	private Long idCumplimiento;
	
	/** The tipo especie. */
	@JsonProperty("TipoEspecie")
	private String tipoEspecie;
	
	/** The sucursal cuenta. */
	@JsonProperty("SucursalCuenta")
	private String sucursalCuenta;
	
	/** The Cuenta titulo. */
	@JsonProperty("CuentaTitulo")
	private String cuentaTitulo;
	
	/** The tipo cuenta operativa. */
	@JsonProperty("TipoCtaOper")
	private String tipoCtaOper;
	
	/** The moneda operacion. */
	@JsonProperty("MonedaOperacion")
	private String monedaOperacion;
	
	/** The Numero cuenta operativa. */
	@JsonProperty("NumeroCuenta")
	private String numeroCuenta;
	
	/** The tipo operacion. */
	@JsonProperty("TipoOperacion")
	private String tipoOperacion;
	
	/** The cantidad Titulo. */
	@JsonProperty("CantidadTitulo")
	private String cantidadTitulo;
	
	/** The Especie Codigo. */
	@JsonProperty("EspecieCodigo")
	private String especieCodigo;
	
	/** The importe Debito Credito. */
	@JsonProperty("ImporteDebitoCredito")
	private String importeDebitoCredito;
	
	/** The cotizacion Limite. */
	@JsonProperty("CotizacionLimite")
	private String cotizacionLimite;
	
	/** The cotizacion. */
	@JsonProperty("Cotizacion")
	private String cotizacion;
	
	/** The plazo. */
	@JsonProperty("Plazo")
	private String plazo;
	
	/** The operador Alta. */
	@JsonProperty("OperadorAlta")
	private String operadorAlta;
	
	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;
	
	/** The fecha Precio Ref. */
	@JsonProperty("FechaPrecioRef")
	private String fechaPrecioRef;
	
	/** The hora Precio Ref. */
	@JsonProperty("HoraPrecioRef")
	private String horaPrecioRef;

	/**
	 * Gets the tipo accion.
	 *
	 * @return the tipoAccion
	 */
	public String getTipoAccion() {
		return tipoAccion;
	}

	/**
	 * Sets the tipo accion.
	 *
	 * @param tipoAccion
	 *            the tipoAccion to set
	 */
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

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
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursalCuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the sucursalCuenta to set
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
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
	 * Gets the tipo cta oper.
	 *
	 * @return the tipoCtaOper
	 */
	public String getTipoCtaOper() {
		return tipoCtaOper;
	}

	/**
	 * Sets the tipo cta oper.
	 *
	 * @param tipoCtaOper
	 *            the tipoCtaOper to set
	 */
	public void setTipoCtaOper(String tipoCtaOper) {
		this.tipoCtaOper = tipoCtaOper;
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
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
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
	 * Gets the operador alta.
	 *
	 * @return the operadorAlta
	 */
	public String getOperadorAlta() {
		return operadorAlta;
	}

	/**
	 * Sets the operador alta.
	 *
	 * @param operadorAlta
	 *            the operadorAlta to set
	 */
	public void setOperadorAlta(String operadorAlta) {
		this.operadorAlta = operadorAlta;
	}

	/**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
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
		
}
