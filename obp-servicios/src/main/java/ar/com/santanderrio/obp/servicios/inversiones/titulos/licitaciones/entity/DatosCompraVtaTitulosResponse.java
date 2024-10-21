/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosCompraVtaTitulosResponse.
 */
public class DatosCompraVtaTitulosResponse {

	/** The importe debito credito. */
	@JsonProperty("ImporteDebitoCredito")
	private Double importeDebitoCredito;
	
	/** The cantidad. */
	@JsonProperty("Cantidad")
	private Double cantidad;
	
	/** The cotizacion. */
	@JsonProperty("Cotizacion")
	private Double cotizacion;
	
	/** The numero orden. */
	@JsonProperty("NumeroOrden")
	private String numeroOrden;
	
	/** The cotizacion limite. */
	@JsonProperty("CotizacionLimite")
	private Double cotizacionLimite;
	
	/** The fecha liquidacion. */
	@JsonProperty("FechaLiquidacion")
	private String fechaLiquidacion;
	
	/** The numero operacion. */
	@JsonProperty("NumeroOperacion")
	private String numeroOperacion;
	
	/** The fecha operacion. */
	@JsonProperty("FechaOperacion")
	private String fechaOperacion;
	
	/** The hora operacion. */
	@JsonProperty("HoraOperacion")
	private String horaOperacion;
	
	/** The fecha debito cuenta. */
	@JsonProperty("FechaDebitoCuenta")
	private String fechaDebitoCuenta;
	
	/** The importe poder compra. */
	@JsonProperty("ImportePoderCompra")
	private Double importePoderCompra;
	
	/** The importe teorico. */
	@JsonProperty("ImporteTeorico")
	private Double importeTeorico;
	
	/** The capital. */
	@JsonProperty("Capital")
	private String capital;
	
	/** The interes. */
	@JsonProperty("Interes")
	private Double interes;
	
	/** The derechos. */
	@JsonProperty("Derechos")
	private Double derechos;
	
	/** The arancel porcel. */
	@JsonProperty("ArancelPorcel")
	private Double arancelPorcel;
	
	/** The iva. */
	@JsonProperty("Iva")
	private Double iva;
	
	/** The comision. */
	@JsonProperty("Comision")
	private Double comision;
	
	/** The impuestos. */
	@JsonProperty("Impuestos")
	private Double impuestos;
	
	/** The instrumento codigo. */
	@JsonProperty("InstrumentoCodigo")
	private String instrumentoCodigo;
	
	/** The instrumento. */
	@JsonProperty("Instrumento")
	private String instrumento;
	
	/** The fecha precio ref. */
	@JsonProperty("FechaPrecioRef")
	private String fechaPrecioRef;
	
	/** The hora precio ref. */
	@JsonProperty("HoraPrecioRef")
	private String horaPrecioRef;
	
	/** The legales. */
	@JsonProperty("Legales")
	private String legales;
	
	/** The respuesta simulacion ERI. */
	@JsonProperty("RespuestaSimulacionERI")
	private RespuestaSimulacionERI respuestaSimulacionERI;
	
	/** The codigo resp ERI. */
	@JsonProperty("CodigoRespERI")
	private int codigoRespERI;
	
	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;

	/** The datosConsultaComision. */
	@JsonProperty("DatosConsultaComisionResponse")
	private DatosConsultaComisionResponse datosConsultaComisionResponse;
	
	/**
	 * Gets the importe debito credito.
	 *
	 * @return the importeDebitoCredito
	 */
	public Double getImporteDebitoCredito() {
		return importeDebitoCredito;
	}

	/**
	 * Sets the importe debito credito.
	 *
	 * @param importeDebitoCredito
	 *            the importeDebitoCredito to set
	 */
	public void setImporteDebitoCredito(Double importeDebitoCredito) {
		this.importeDebitoCredito = importeDebitoCredito;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public Double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public Double getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacion to set
	 */
	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numeroOrden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the numeroOrden to set
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * Gets the cotizacion limite.
	 *
	 * @return the cotizacionLimite
	 */
	public Double getCotizacionLimite() {
		return cotizacionLimite;
	}

	/**
	 * Sets the cotizacion limite.
	 *
	 * @param cotizacionLimite
	 *            the cotizacionLimite to set
	 */
	public void setCotizacionLimite(Double cotizacionLimite) {
		this.cotizacionLimite = cotizacionLimite;
	}

	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fechaLiquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the fechaLiquidacion to set
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the numero operacion.
	 *
	 * @return the numeroOperacion
	 */
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * Sets the numero operacion.
	 *
	 * @param numeroOperacion
	 *            the numeroOperacion to set
	 */
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/**
	 * Gets the fecha operacion.
	 *
	 * @return the fechaOperacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * Sets the fecha operacion.
	 *
	 * @param fechaOperacion
	 *            the fechaOperacion to set
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * Gets the hora operacion.
	 *
	 * @return the horaOperacion
	 */
	public String getHoraOperacion() {
		return horaOperacion;
	}

	/**
	 * Sets the hora operacion.
	 *
	 * @param horaOperacion
	 *            the horaOperacion to set
	 */
	public void setHoraOperacion(String horaOperacion) {
		this.horaOperacion = horaOperacion;
	}

	/**
	 * Gets the fecha debito cuenta.
	 *
	 * @return the fechaDebitoCuenta
	 */
	public String getFechaDebitoCuenta() {
		return fechaDebitoCuenta;
	}

	/**
	 * Sets the fecha debito cuenta.
	 *
	 * @param fechaDebitoCuenta
	 *            the fechaDebitoCuenta to set
	 */
	public void setFechaDebitoCuenta(String fechaDebitoCuenta) {
		this.fechaDebitoCuenta = fechaDebitoCuenta;
	}

	/**
	 * Gets the importe poder compra.
	 *
	 * @return the importePoderCompra
	 */
	public Double getImportePoderCompra() {
		return importePoderCompra;
	}

	/**
	 * Sets the importe poder compra.
	 *
	 * @param importePoderCompra
	 *            the importePoderCompra to set
	 */
	public void setImportePoderCompra(Double importePoderCompra) {
		this.importePoderCompra = importePoderCompra;
	}

	/**
	 * Gets the importe teorico.
	 *
	 * @return the importeTeorico
	 */
	public Double getImporteTeorico() {
		return importeTeorico;
	}

	/**
	 * Sets the importe teorico.
	 *
	 * @param importeTeorico
	 *            the importeTeorico to set
	 */
	public void setImporteTeorico(Double importeTeorico) {
		this.importeTeorico = importeTeorico;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the capital to set
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Gets the interes.
	 *
	 * @return the interes
	 */
	public Double getInteres() {
		return interes;
	}

	/**
	 * Sets the interes.
	 *
	 * @param interes
	 *            the interes to set
	 */
	public void setInteres(Double interes) {
		this.interes = interes;
	}

	/**
	 * Gets the derechos.
	 *
	 * @return the derechos
	 */
	public Double getDerechos() {
		return derechos;
	}

	/**
	 * Sets the derechos.
	 *
	 * @param derechos
	 *            the derechos to set
	 */
	public void setDerechos(Double derechos) {
		this.derechos = derechos;
	}

	/**
	 * Gets the arancel porcel.
	 *
	 * @return the arancelPorcel
	 */
	public Double getArancelPorcel() {
		return arancelPorcel;
	}

	/**
	 * Sets the arancel porcel.
	 *
	 * @param arancelPorcel
	 *            the arancelPorcel to set
	 */
	public void setArancelPorcel(Double arancelPorcel) {
		this.arancelPorcel = arancelPorcel;
	}

	/**
	 * Gets the iva.
	 *
	 * @return the iva
	 */
	public Double getIva() {
		return iva;
	}

	/**
	 * Sets the iva.
	 *
	 * @param iva
	 *            the iva to set
	 */
	public void setIva(Double iva) {
		this.iva = iva;
	}

	/**
	 * Gets the comision.
	 *
	 * @return the comision
	 */
	public Double getComision() {
		return comision;
	}

	/**
	 * Sets the comision.
	 *
	 * @param comision
	 *            the comision to set
	 */
	public void setComision(Double comision) {
		this.comision = comision;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public Double getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the impuestos to set
	 */
	public void setImpuestos(Double impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the instrumento codigo.
	 *
	 * @return the instrumentoCodigo
	 */
	public String getInstrumentoCodigo() {
		return instrumentoCodigo;
	}

	/**
	 * Sets the instrumento codigo.
	 *
	 * @param instrumentoCodigo
	 *            the instrumentoCodigo to set
	 */
	public void setInstrumentoCodigo(String instrumentoCodigo) {
		this.instrumentoCodigo = instrumentoCodigo;
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
	 *            the instrumento to set
	 */
	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
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
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the respuesta simulacion ERI.
	 *
	 * @return the respuestaSimulacionERI
	 */
	public RespuestaSimulacionERI getRespuestaSimulacionERI() {
		return respuestaSimulacionERI;
	}

	/**
	 * Sets the respuesta simulacion ERI.
	 *
	 * @param respuestaSimulacionERI
	 *            the respuestaSimulacionERI to set
	 */
	public void setRespuestaSimulacionERI(RespuestaSimulacionERI respuestaSimulacionERI) {
		this.respuestaSimulacionERI = respuestaSimulacionERI;
	}

	/**
	 * Gets the codigo resp ERI.
	 *
	 * @return the codigoRespERI
	 */
	public int getCodigoRespERI() {
		return codigoRespERI;
	}

	/**
	 * Sets the codigo resp ERI.
	 *
	 * @param codigoRespERI
	 *            the codigoRespERI to set
	 */
	public void setCodigoRespERI(int codigoRespERI) {
		this.codigoRespERI = codigoRespERI;
	}

	/**
	 * Gets the codigo error middleware.
	 *
	 * @return the codigoErrorMiddleware
	 */
	public String getCodigoErrorMiddleware() {
		return codigoErrorMiddleware;
	}

	/**
	 * Sets the codigo error middleware.
	 *
	 * @param codigoErrorMiddleware
	 *            the codigoErrorMiddleware to set
	 */
	public void setCodigoErrorMiddleware(String codigoErrorMiddleware) {
		this.codigoErrorMiddleware = codigoErrorMiddleware;
	}
	
	/**
	 * Gets the codigo error middleware.
	 *
	 * @return the codigoErrorMiddleware
	 */
	public DatosConsultaComisionResponse getDatosConsultaComisionResponse() {
		return datosConsultaComisionResponse;
	}

	/**
	 * Sets the codigo error middleware.
	 *
	 * @param codigoErrorMiddleware
	 *            the codigoErrorMiddleware to set
	 */
	public void setDatosConsultaComisionResponsee(DatosConsultaComisionResponse datosConsultaComisionResponse) {
		this.datosConsultaComisionResponse = datosConsultaComisionResponse;
	}
}
