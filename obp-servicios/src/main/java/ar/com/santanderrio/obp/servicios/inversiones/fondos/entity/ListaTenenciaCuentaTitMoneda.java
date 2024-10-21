/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ListaTenenciaCuentaTitMoneda.
 */
public class ListaTenenciaCuentaTitMoneda {

	/** the GUID Error. */
	@JsonProperty("GUIDError")
	private String error;

	/** the numero cuenta. */
	@JsonProperty("NumeroCuenta")
	private String numeroCuenta;

	/** the sucursal. */
	@JsonProperty("Sucursal")
	private String sucursal;

	/** the Codigo Producto. */
	@JsonProperty("CodigoProducto")
	private String codigoProducto;

	/** the Descripcion Producto. */
	@JsonProperty("DescripcionProducto")
	private String descripcionProducto;

	/** the Codigo Tipo Producto. */
	@JsonProperty("CodigoTipoProducto")
	private String codigoTipoProducto;

	/** the Descripcion Tipo Producto. */
	@JsonProperty("DescripcionTipoProducto")
	private String descripcionTipoProducto;

	/** the Codigo Especie. */
	@JsonProperty("CodigoEspecie")
	private String codigoEspecie;

	/** the Descripcion Especie. */
	@JsonProperty("DescripcionEspecie")
	private String descripcionEspecie;

	/** the Codigo Especie Mercado. */
	@JsonProperty("CodigoEspecieMercado")
	private String codigoEspecieMercado;

	/** the Moneda. */
	@JsonProperty("Moneda")
	private String moneda;

	/** the Tenencia Nominal. */
	@JsonProperty("TenenciaNominal")
	private String tenenciaNominal;

	/** the Cotizacion. */
	@JsonProperty("Cotizacion")
	private String cotizacion;

	/** the Fecha Cotizacion. */
	@JsonProperty("FechaCotizacion")
	private String fechaCotizacion;

	/** the Hora Cotizacion. */
	@JsonProperty("HoraCothzacion")
	private String HoraCotizacion;

	/** the Codigo Estado Tenencia. */
	@JsonProperty("CodigoEstadoTenencia")
	private String codigoEstadoTenencia;

	/** the Descripcion Estado Ten. */
	@JsonProperty("DescripcionEstadoTen")
	private String descripcionEstadoTen;

	/** the Cod Estado Cuenta Titulo. */
	@JsonProperty("CodEstadoCuentaTitulo")
	private String codEstadoCuentaTitulo;

	/** the Motivo Bloqueo Cuenta. */
	@JsonProperty("MotivoBloqueoCuenta")
	private String motivoBloqueoCuenta;

	/** the PPPC. */
	@JsonProperty("PPPC")
	private String pppc;

	/** the Tenencia Valuada. */
	@JsonProperty("TenenciaValuada")
	private String tenenciaValuada;

	/** the Tenencia Valuada Reexp. */
	@JsonProperty("TenenciaValuadaReexp")
	private String tenenciaValuadaReexp;

	/** the Tenencia Valuada Compra. */
	@JsonProperty("TenenciaValuadaCompra")
	private String tenenciaValuadaCompra;

	/** the Tenencia Valuada Compra Reexp. */
	@JsonProperty("TenenciaValuadaCompraReexp")
	private String tenenciaValuadaCompraReexp;

	/** the Resultado Bruto. */
	@JsonProperty("ResultadoBruto")
	private String resultadoBruto;

	/** the Resultado Bruto Reexp. */
	@JsonProperty("ResultadoBrutoReexp")
	private String resultadoBrutoReexp;

	/** the DivPPc. */
	@JsonProperty("DivPPc")
	private String divPPc;

	/** the DivPPc Reexp. */
	@JsonProperty("divPPcReexp")
	private String DivPPcReexp;

	/** the CuPPc. */
	@JsonProperty("CuPPc")
	private String cuPPc;

	/** the CuPPc Reexp. */
	@JsonProperty("CuPPcReexp")
	private String cuPPcReexp;

	/** the AmPPc. */
	@JsonProperty("AmPPc")
	private String amPPc;

	/** the AmPPc Reexp. */
	@JsonProperty("AmPPcReexp")
	private String amPPcReexp;

	/** the Resultado Bruto Corregido. */
	@JsonProperty("ResultadoBrutoCorregido")
	private String resultadoBrutoCorregido;

	/** the Resultado Bruto Corregido Reexp. */
	@JsonProperty("ResultadoBrutoCorregidoReexp")
	private String resultadoBrutoCorregidoReexp;
	
	/** The habilitado. */
	@JsonProperty("Habilitado")
	private String habilitado;

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
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
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto
	 *            the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * Gets the descripcion producto.
	 *
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * Sets the descripcion producto.
	 *
	 * @param descripcionProducto
	 *            the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * Gets the codigo tipo producto.
	 *
	 * @return the codigoTipoProducto
	 */
	public String getCodigoTipoProducto() {
		return codigoTipoProducto;
	}

	/**
	 * Sets the codigo tipo producto.
	 *
	 * @param codigoTipoProducto
	 *            the codigoTipoProducto to set
	 */
	public void setCodigoTipoProducto(String codigoTipoProducto) {
		this.codigoTipoProducto = codigoTipoProducto;
	}

	/**
	 * Gets the descripcion tipo producto.
	 *
	 * @return the descripcionTipoProducto
	 */
	public String getDescripcionTipoProducto() {
		return descripcionTipoProducto;
	}

	/**
	 * Sets the descripcion tipo producto.
	 *
	 * @param descripcionTipoProducto
	 *            the descripcionTipoProducto to set
	 */
	public void setDescripcionTipoProducto(String descripcionTipoProducto) {
		this.descripcionTipoProducto = descripcionTipoProducto;
	}

	/**
	 * Gets the codigo especie.
	 *
	 * @return the codigoEspecie
	 */
	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	/**
	 * Sets the codigo especie.
	 *
	 * @param codigoEspecie
	 *            the codigoEspecie to set
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcionEspecie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the descripcionEspecie to set
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	/**
	 * Gets the codigo especie mercado.
	 *
	 * @return the codigoEspecieMercado
	 */
	public String getCodigoEspecieMercado() {
		return codigoEspecieMercado;
	}

	/**
	 * Sets the codigo especie mercado.
	 *
	 * @param codigoEspecieMercado
	 *            the codigoEspecieMercado to set
	 */
	public void setCodigoEspecieMercado(String codigoEspecieMercado) {
		this.codigoEspecieMercado = codigoEspecieMercado;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the tenencia nominal.
	 *
	 * @return the tenenciaNominal
	 */
	public String getTenenciaNominal() {
		return tenenciaNominal;
	}

	/**
	 * Sets the tenencia nominal.
	 *
	 * @param tenenciaNominal
	 *            the tenenciaNominal to set
	 */
	public void setTenenciaNominal(String tenenciaNominal) {
		this.tenenciaNominal = tenenciaNominal;
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
	 * Gets the fecha cotizacion.
	 *
	 * @return the fechaCotizacion
	 */
	public String getFechaCotizacion() {
		return fechaCotizacion;
	}

	/**
	 * Sets the fecha cotizacion.
	 *
	 * @param fechaCotizacion
	 *            the fechaCotizacion to set
	 */
	public void setFechaCotizacion(String fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	/**
	 * Gets the hora cotizacion.
	 *
	 * @return the horaCotizacion
	 */
	public String getHoraCotizacion() {
		return HoraCotizacion;
	}

	/**
	 * Sets the hora cotizacion.
	 *
	 * @param horaCotizacion
	 *            the horaCotizacion to set
	 */
	public void setHoraCotizacion(String horaCotizacion) {
		HoraCotizacion = horaCotizacion;
	}

	/**
	 * Gets the codigo estado tenencia.
	 *
	 * @return the codigoEstadoTenencia
	 */
	public String getCodigoEstadoTenencia() {
		return codigoEstadoTenencia;
	}

	/**
	 * Sets the codigo estado tenencia.
	 *
	 * @param codigoEstadoTenencia
	 *            the codigoEstadoTenencia to set
	 */
	public void setCodigoEstadoTenencia(String codigoEstadoTenencia) {
		this.codigoEstadoTenencia = codigoEstadoTenencia;
	}

	/**
	 * Gets the descripcion estado ten.
	 *
	 * @return the descripcionEstadoTen
	 */
	public String getDescripcionEstadoTen() {
		return descripcionEstadoTen;
	}

	/**
	 * Sets the descripcion estado ten.
	 *
	 * @param descripcionEstadoTen
	 *            the descripcionEstadoTen to set
	 */
	public void setDescripcionEstadoTen(String descripcionEstadoTen) {
		this.descripcionEstadoTen = descripcionEstadoTen;
	}

	/**
	 * Distinto de 0 = cuenta bloqueada.
	 *
	 * @return the codEstadoCuentaTitulo
	 */
	public String getCodEstadoCuentaTitulo() {
		return codEstadoCuentaTitulo;
	}

	/**
	 * Sets the cod estado cuenta titulo.
	 *
	 * @param codEstadoCuentaTitulo
	 *            the codEstadoCuentaTitulo to set
	 */
	public void setCodEstadoCuentaTitulo(String codEstadoCuentaTitulo) {
		this.codEstadoCuentaTitulo = codEstadoCuentaTitulo;
	}

	/**
	 * Gets the motivo bloqueo cuenta.
	 *
	 * @return the motivoBloqueoCuenta
	 */
	public String getMotivoBloqueoCuenta() {
		return motivoBloqueoCuenta;
	}

	/**
	 * Sets the motivo bloqueo cuenta.
	 *
	 * @param motivoBloqueoCuenta
	 *            the motivoBloqueoCuenta to set
	 */
	public void setMotivoBloqueoCuenta(String motivoBloqueoCuenta) {
		this.motivoBloqueoCuenta = motivoBloqueoCuenta;
	}

	/**
	 * Gets the pppc.
	 *
	 * @return the pppc
	 */
	public String getPppc() {
		return pppc;
	}

	/**
	 * Sets the pppc.
	 *
	 * @param pppc
	 *            the pppc to set
	 */
	public void setPppc(String pppc) {
		this.pppc = pppc;
	}

	/**
	 * Gets the tenencia valuada.
	 *
	 * @return the tenenciaValuada
	 */
	public String getTenenciaValuada() {
		return tenenciaValuada;
	}

	/**
	 * Sets the tenencia valuada.
	 *
	 * @param tenenciaValuada
	 *            the tenenciaValuada to set
	 */
	public void setTenenciaValuada(String tenenciaValuada) {
		this.tenenciaValuada = tenenciaValuada;
	}

	/**
	 * Gets the tenencia valuada reexp.
	 *
	 * @return the tenenciaValuadaReexp
	 */
	public String getTenenciaValuadaReexp() {
		return tenenciaValuadaReexp;
	}

	/**
	 * Sets the tenencia valuada reexp.
	 *
	 * @param tenenciaValuadaReexp
	 *            the tenenciaValuadaReexp to set
	 */
	public void setTenenciaValuadaReexp(String tenenciaValuadaReexp) {
		this.tenenciaValuadaReexp = tenenciaValuadaReexp;
	}

	/**
	 * Gets the tenencia valuada compra.
	 *
	 * @return the tenenciaValuadaCompra
	 */
	public String getTenenciaValuadaCompra() {
		return tenenciaValuadaCompra;
	}

	/**
	 * Sets the tenencia valuada compra.
	 *
	 * @param tenenciaValuadaCompra
	 *            the tenenciaValuadaCompra to set
	 */
	public void setTenenciaValuadaCompra(String tenenciaValuadaCompra) {
		this.tenenciaValuadaCompra = tenenciaValuadaCompra;
	}

	/**
	 * Gets the tenencia valuada compra reexp.
	 *
	 * @return the tenenciaValuadaCompraReexp
	 */
	public String getTenenciaValuadaCompraReexp() {
		return tenenciaValuadaCompraReexp;
	}

	/**
	 * Sets the tenencia valuada compra reexp.
	 *
	 * @param tenenciaValuadaCompraReexp
	 *            the tenenciaValuadaCompraReexp to set
	 */
	public void setTenenciaValuadaCompraReexp(String tenenciaValuadaCompraReexp) {
		this.tenenciaValuadaCompraReexp = tenenciaValuadaCompraReexp;
	}

	/**
	 * Gets the resultado bruto.
	 *
	 * @return the resultadoBruto
	 */
	public String getResultadoBruto() {
		return resultadoBruto;
	}

	/**
	 * Sets the resultado bruto.
	 *
	 * @param resultadoBruto
	 *            the resultadoBruto to set
	 */
	public void setResultadoBruto(String resultadoBruto) {
		this.resultadoBruto = resultadoBruto;
	}

	/**
	 * Gets the resultado bruto reexp.
	 *
	 * @return the resultadoBrutoReexp
	 */
	public String getResultadoBrutoReexp() {
		return resultadoBrutoReexp;
	}

	/**
	 * Sets the resultado bruto reexp.
	 *
	 * @param resultadoBrutoReexp
	 *            the resultadoBrutoReexp to set
	 */
	public void setResultadoBrutoReexp(String resultadoBrutoReexp) {
		this.resultadoBrutoReexp = resultadoBrutoReexp;
	}

	/**
	 * Gets the div P pc.
	 *
	 * @return the divPPc
	 */
	public String getDivPPc() {
		return divPPc;
	}

	/**
	 * Sets the div P pc.
	 *
	 * @param divPPc
	 *            the divPPc to set
	 */
	public void setDivPPc(String divPPc) {
		this.divPPc = divPPc;
	}

	/**
	 * Gets the div P pc reexp.
	 *
	 * @return the divPPcReexp
	 */
	public String getDivPPcReexp() {
		return DivPPcReexp;
	}

	/**
	 * Sets the div P pc reexp.
	 *
	 * @param divPPcReexp
	 *            the divPPcReexp to set
	 */
	public void setDivPPcReexp(String divPPcReexp) {
		DivPPcReexp = divPPcReexp;
	}

	/**
	 * Gets the cu P pc.
	 *
	 * @return the cuPPc
	 */
	public String getCuPPc() {
		return cuPPc;
	}

	/**
	 * Sets the cu P pc.
	 *
	 * @param cuPPc
	 *            the cuPPc to set
	 */
	public void setCuPPc(String cuPPc) {
		this.cuPPc = cuPPc;
	}

	/**
	 * Gets the cu P pc reexp.
	 *
	 * @return the cuPPcReexp
	 */
	public String getCuPPcReexp() {
		return cuPPcReexp;
	}

	/**
	 * Sets the cu P pc reexp.
	 *
	 * @param cuPPcReexp
	 *            the cuPPcReexp to set
	 */
	public void setCuPPcReexp(String cuPPcReexp) {
		this.cuPPcReexp = cuPPcReexp;
	}

	/**
	 * Gets the am P pc.
	 *
	 * @return the amPPc
	 */
	public String getAmPPc() {
		return amPPc;
	}

	/**
	 * Sets the am P pc.
	 *
	 * @param amPPc
	 *            the amPPc to set
	 */
	public void setAmPPc(String amPPc) {
		this.amPPc = amPPc;
	}

	/**
	 * Gets the am P pc reexp.
	 *
	 * @return the amPPcReexp
	 */
	public String getAmPPcReexp() {
		return amPPcReexp;
	}

	/**
	 * Sets the am P pc reexp.
	 *
	 * @param amPPcReexp
	 *            the amPPcReexp to set
	 */
	public void setAmPPcReexp(String amPPcReexp) {
		this.amPPcReexp = amPPcReexp;
	}

	/**
	 * Gets the resultado bruto corregido.
	 *
	 * @return the resultadoBrutoCorregido
	 */
	public String getResultadoBrutoCorregido() {
		return resultadoBrutoCorregido;
	}

	/**
	 * Sets the resultado bruto corregido.
	 *
	 * @param resultadoBrutoCorregido
	 *            the resultadoBrutoCorregido to set
	 */
	public void setResultadoBrutoCorregido(String resultadoBrutoCorregido) {
		this.resultadoBrutoCorregido = resultadoBrutoCorregido;
	}

	/**
	 * Gets the resultado bruto corregido reexp.
	 *
	 * @return the resultadoBrutoCorregidoReexp
	 */
	public String getResultadoBrutoCorregidoReexp() {
		return resultadoBrutoCorregidoReexp;
	}

	/**
	 * Sets the resultado bruto corregido reexp.
	 *
	 * @param resultadoBrutoCorregidoReexp
	 *            the resultadoBrutoCorregidoReexp to set
	 */
	public void setResultadoBrutoCorregidoReexp(String resultadoBrutoCorregidoReexp) {
		this.resultadoBrutoCorregidoReexp = resultadoBrutoCorregidoReexp;
	}

	/**
	 * Gets the habilitado.
	 *
	 * @return the habilitado
	 */
	public String getHabilitado() {
		return habilitado;
	}

	/**
	 * Sets the habilitado.
	 *
	 * @param habilitado
	 *            the new habilitado
	 */
	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

}
