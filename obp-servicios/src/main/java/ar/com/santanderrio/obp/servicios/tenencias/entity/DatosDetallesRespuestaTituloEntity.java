package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class DatosDetallesRespuestaTituloEntity {
	
	@JsonProperty("TipoEspecie")
	private String tipo_especie;
	
	@JsonProperty("DescripcionEspecie")
	private String descripcionEspecie;
	
	@JsonProperty("CuentaTitulos")
	private String cuentaTitulos;
	
	@JsonProperty("CodEspecie")
	private String codEspecie;
	
	@JsonProperty("Divisa")
	private String monedaEmision;
	
	@JsonProperty("FechaConcertacion")
	private String fechaConcert;
	
	@JsonProperty("TipoOperacion")
	private String concepto;
	
	@JsonProperty("Cantidad")
	private String cantidad;
	
	@JsonProperty("Cotizacion")
	private String cotizacion;
	
	@JsonProperty("ImporteNeto")
	private String importeNeto;
	
	@JsonProperty("NroCuentaOrigen")
	private String cuentaOper;
	
	@JsonProperty("SucursalCuentaOrigen")
	private String sucCtaOper;
	
	@JsonProperty("TipoCtaOrigen")
	private String tipoCtaOper;
	
	@JsonProperty("Comprobante")
	private String comprobante;
	
	@JsonProperty("Gastos")
	private String gastos;
	
	@JsonProperty("ImporteBruto")
	private String importeBruto;
	
	@JsonProperty("Impuesto")
	private String impuestos;
	
	@JsonProperty("MonedaTransaccion")
	private String monedaTrx;
	
	@JsonProperty("NroOrden")
	private String nroOrden;
	
	@JsonProperty("NroBoleto")
	private String nroBoleto;
	
	@JsonProperty("Renta")
	private String renta;
	
	@JsonProperty("Amortizacion")
	private String amortizacion;
	

	
	/**
	 * @return the renta
	 */
	public String getRenta() {
		return renta;
	}

	/**
	 * @param renta the renta to set
	 */
	public void setRenta(String renta) {
		this.renta = renta;
	}

	/**
	 * @return the amortizacion
	 */
	public String getAmortizacion() {
		return amortizacion;
	}

	/**
	 * @param amortizacion the amortizacion to set
	 */
	public void setAmortizacion(String amortizacion) {
		this.amortizacion = amortizacion;
	}

	/**
	 * @return the tipo_especie
	 */
	public String getTipo_especie() {
		return tipo_especie;
	}

	/**
	 * @param tipo_especie the tipo_especie to set
	 */
	public void setTipo_especie(String tipo_especie) {
		this.tipo_especie = tipo_especie;
	}

	/**
	 * @return the cuentaTitulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * @param cuentaTitulos the cuentaTitulos to set
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * @return the codEspecie
	 */
	public String getCodEspecie() {
		return codEspecie;
	}

	/**
	 * @param codEspecie the codEspecie to set
	 */
	public void setCodEspecie(String codEspecie) {
		this.codEspecie = codEspecie;
	}

	/**
	 * @return the descripcionEspecie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * @param descripcionEspecie the descripcionEspecie to set
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	/**
	 * @return the monedaEmision
	 */
	public String getMonedaEmision() {
		return monedaEmision;
	}

	/**
	 * @param monedaEmision the monedaEmision to set
	 */
	public void setMonedaEmision(String monedaEmision) {
		this.monedaEmision = monedaEmision;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fechaConcert;
	}

	/**
	 * @return the fechaConcert
	 */
	public String getFechaConcert() {
		return fechaConcert;
	}

	/**
	 * @param fechaConcert the fechaConcert to set
	 */
	public void setFechaConcert(String fechaConcert) {
		this.fechaConcert = fechaConcert;
	}

	/**
	 * @return the sucCtaOper
	 */
	public String getSucCtaOper() {
		return sucCtaOper;
	}

	/**
	 * @param sucCtaOper the sucCtaOper to set
	 */
	public void setSucCtaOper(String sucCtaOper) {
		this.sucCtaOper = sucCtaOper;
	}

	/**
	 * @return the tipoCtaOper
	 */
	public String getTipoCtaOper() {
		return tipoCtaOper;
	}

	/**
	 * @param tipoCtaOper the tipoCtaOper to set
	 */
	public void setTipoCtaOper(String tipoCtaOper) {
		this.tipoCtaOper = tipoCtaOper;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fechaConcert) {
		this.fechaConcert = fechaConcert;
	}

	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the cotizacion
	 */
	public String getCotizacion() {
		return cotizacion;
	}

	/**
	 * @param cotizacion the cotizacion to set
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * @return the importeNeto
	 */
	public String getImporteNeto() {
		return importeNeto;
	}

	/**
	 * @param importeNeto the importeNeto to set
	 */
	public void setImporteNeto(String importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * @return the cuentaOper
	 */
	public String getCuentaOper() {
		return cuentaOper;
	}

	/**
	 * @param cuentaOper the cuentaOper to set
	 */
	public void setCuentaOper(String cuentaOper) {
		this.cuentaOper = cuentaOper;
	}

	/**
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * @param comprobante the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * @return the gastos
	 */
	public String getGastos() {
		return gastos;
	}

	/**
	 * @param gastos the gastos to set
	 */
	public void setGastos(String gastos) {
		this.gastos = gastos;
	}

	/**
	 * @return the importeBruto
	 */
	public String getImporteBruto() {
		return importeBruto;
	}

	/**
	 * @param importeBruto the importeBruto to set
	 */
	public void setImporteBruto(String importeBruto) {
		this.importeBruto = importeBruto;
	}

	/**
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * @param impuestos the impuestos to set
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * @return the monedaTrx
	 */
	public String getMonedaTrx() {
		return monedaTrx;
	}

	/**
	 * @param monedaTrx the monedaTrx to set
	 */
	public void setMonedaTrx(String monedaTrx) {
		this.monedaTrx = monedaTrx;
	}

	/**
	 * @return the nroOrden
	 */
	public String getNroOrden() {
		return nroOrden;
	}

	/**
	 * @param nroOrden the nroOrden to set
	 */
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}

	/**
	 * @return the nroBoleto
	 */
	public String getNroBoleto() {
		return nroBoleto;
	}

	/**
	 * @param nroBoleto the nroBoleto to set
	 */
	public void setNroBoleto(String nroBoleto) {
		this.nroBoleto = nroBoleto;
	}
	
	

}
