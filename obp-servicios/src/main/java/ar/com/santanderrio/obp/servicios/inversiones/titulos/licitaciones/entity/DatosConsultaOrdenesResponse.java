/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaOrdenesResponse.
 */
public class DatosConsultaOrdenesResponse {

//	@JsonProperty("CodigoErrorMiddleware")
//	private String codigoErrorMiddleware;

	/** The fecha ingreso. */
@JsonProperty("FechaIngreso")
	private String fechaIngreso;

	/** The fecha liquidacion. */
	@JsonProperty("FechaLiquidacion")
	private String fechaLiquidacion;

	/** The fecha orden. */
	@JsonProperty("FechaOrden")
	private String fechaOrden;

	/** The cuenta titulos. */
	@JsonProperty("CuentaTitulos")
	private String cuentaTitulos;

	/** The numero orden. */
	@JsonProperty("NumeroOrden")
	private String numeroOrden;

	/** The hora. */
	@JsonProperty("Hora")
	private String hora;

	/** The canal. */
	@JsonProperty("Canal")
	private String canal;

	/** The sub canal. */
	@JsonProperty("SubCanal")
	private String subCanal;

	/** The especie tipo. */
	@JsonProperty("EspecieTipo")
	private String especieTipo;

	/** The especie codigo. */
	@JsonProperty("EspecieCodigo")
	private String especieCodigo;

	/** The especie descripcion. */
	@JsonProperty("EspecieDescripcion")
	private String especieDescripcion;

	/** The ric. */
	@JsonProperty("Ric")
	private String ric;

	/** The tipo operacion. */
	@JsonProperty("TipoOperacion")
	private String tipoOperacion;

	/** The estado. */
	@JsonProperty("Estado")
	private String estado;

	/** The cuenta tipo. */
	@JsonProperty("CuentaTipo")
	private String cuentaTipo;

	/** The sucursal. */
	@JsonProperty("Sucursal")
	private String sucursal;

	/** The cuenta numero. */
	@JsonProperty("CuentaNumero")
	private String cuentaNumero;

	/** The orden por monto. */
	@JsonProperty("OrdenPorMonto")
	private String ordenPorMonto;

	/** The cantidad inicial. */
	@JsonProperty("CantidadInicial")
	private String cantidadInicial;

	/** The cantidad reversada. */
	@JsonProperty("CantidadReversada")
	private String cantidadReversada;

	/** The cantidad operada. */
	@JsonProperty("CantidadOperada")
	private String cantidadOperada;

	/** The cantidad pendiente. */
	@JsonProperty("CantidadPendiente")
	private String cantidadPendiente;

	/** The monto inicial. */
	@JsonProperty("MontoInicial")
	private String montoInicial;

	/** The monto reversado. */
	@JsonProperty("MontoReversado")
	private String montoReversado;

	/** The monto operado. */
	@JsonProperty("MontoOperado")
	private String montoOperado;

	/** The monto pendiente. */
	@JsonProperty("MontoPendiente")
	private String montoPendiente;

	/** The precio cierre. */
	@JsonProperty("PrecioCierre")
	private String precioCierre;

	/** The precio referencia. */
	@JsonProperty("PrecioReferencia")
	private String precioReferencia;

	/** The precio limite. */
	@JsonProperty("PrecioLimite")
	private Double precioLimite;

	/** The motivo reversa. */
	@JsonProperty("MotivoReversa")
	private String motivoReversa;

	/** The cno. */
	@JsonProperty("Cno")
	private String cno;

	/** The debito anticipado. */
	@JsonProperty("DebitoAnticipado")
	private String debitoAnticipado;

	/** The importe teorico. */
	@JsonProperty("ImporteTeorico")
	private String importeTeorico;

	/** The plazo. */
	@JsonProperty("Plazo")
	private String plazo;

	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;

	/** The codigo moneda iso. */
	@JsonProperty("CodigoMonedaIso")
	private String codigoMonedaIso;

	/** The descripcion de estado. */
	@JsonProperty("DescripcionDeEstado")
	private String descripcionDeEstado;

	/** The instanciaorden. */
	@JsonProperty("Instanciaorden")
	private String instanciaorden;

	/** The instrumento. */
	@JsonProperty("Instrumento")
	private String instrumento;
	
	/** The instrumento codigo. */
	@JsonProperty("InstrumentoCodigo")
	private String instrumentoCodigo;

	
	
//	public String getCodigoErrorMiddleware() {
//		return codigoErrorMiddleware;
//	}
//
//	public void setCodigoErrorMiddleware(String codigoErrorMiddleware) {
//		this.codigoErrorMiddleware = codigoErrorMiddleware;
//	}

	/**
 * Gets the fecha ingreso.
 *
 * @return the fecha ingreso
 */
public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * Sets the fecha ingreso.
	 *
	 * @param fechaIngreso
	 *            the new fecha ingreso
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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
	 * Gets the fecha orden.
	 *
	 * @return the fecha orden
	 */
	public String getFechaOrden() {
		return fechaOrden;
	}

	/**
	 * Sets the fecha orden.
	 *
	 * @param fechaOrden
	 *            the new fecha orden
	 */
	public void setFechaOrden(String fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	/**
	 * Gets the cuenta titulos.
	 *
	 * @return the cuenta titulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulos
	 *            the new cuenta titulos
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the sub canal.
	 *
	 * @return the sub canal
	 */
	public String getSubCanal() {
		return subCanal;
	}

	/**
	 * Sets the sub canal.
	 *
	 * @param subCanal
	 *            the new sub canal
	 */
	public void setSubCanal(String subCanal) {
		this.subCanal = subCanal;
	}

	/**
	 * Gets the especie tipo.
	 *
	 * @return the especie tipo
	 */
	public String getEspecieTipo() {
		return especieTipo;
	}

	/**
	 * Sets the especie tipo.
	 *
	 * @param especieTipo
	 *            the new especie tipo
	 */
	public void setEspecieTipo(String especieTipo) {
		this.especieTipo = especieTipo;
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
	 * Gets the especie descripcion.
	 *
	 * @return the especie descripcion
	 */
	public String getEspecieDescripcion() {
		return especieDescripcion;
	}

	/**
	 * Sets the especie descripcion.
	 *
	 * @param especieDescripcion
	 *            the new especie descripcion
	 */
	public void setEspecieDescripcion(String especieDescripcion) {
		this.especieDescripcion = especieDescripcion;
	}

	/**
	 * Gets the ric.
	 *
	 * @return the ric
	 */
	public String getRic() {
		return ric;
	}

	/**
	 * Sets the ric.
	 *
	 * @param ric
	 *            the new ric
	 */
	public void setRic(String ric) {
		this.ric = ric;
	}

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipo operacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the cuenta tipo.
	 *
	 * @return the cuenta tipo
	 */
	public String getCuentaTipo() {
		return cuentaTipo;
	}

	/**
	 * Sets the cuenta tipo.
	 *
	 * @param cuentaTipo
	 *            the new cuenta tipo
	 */
	public void setCuentaTipo(String cuentaTipo) {
		this.cuentaTipo = cuentaTipo;
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
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the cuenta numero.
	 *
	 * @return the cuenta numero
	 */
	public String getCuentaNumero() {
		return cuentaNumero;
	}

	/**
	 * Sets the cuenta numero.
	 *
	 * @param cuentaNumero
	 *            the new cuenta numero
	 */
	public void setCuentaNumero(String cuentaNumero) {
		this.cuentaNumero = cuentaNumero;
	}

	/**
	 * Gets the orden por monto.
	 *
	 * @return the orden por monto
	 */
	public String getOrdenPorMonto() {
		return ordenPorMonto;
	}

	/**
	 * Sets the orden por monto.
	 *
	 * @param ordenPorMonto
	 *            the new orden por monto
	 */
	public void setOrdenPorMonto(String ordenPorMonto) {
		this.ordenPorMonto = ordenPorMonto;
	}

	/**
	 * Gets the cantidad inicial.
	 *
	 * @return the cantidad inicial
	 */
	public String getCantidadInicial() {
		return cantidadInicial;
	}

	/**
	 * Sets the cantidad inicial.
	 *
	 * @param cantidadInicial
	 *            the new cantidad inicial
	 */
	public void setCantidadInicial(String cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	/**
	 * Gets the cantidad reversada.
	 *
	 * @return the cantidad reversada
	 */
	public String getCantidadReversada() {
		return cantidadReversada;
	}

	/**
	 * Sets the cantidad reversada.
	 *
	 * @param cantidadReversada
	 *            the new cantidad reversada
	 */
	public void setCantidadReversada(String cantidadReversada) {
		this.cantidadReversada = cantidadReversada;
	}

	/**
	 * Gets the cantidad operada.
	 *
	 * @return the cantidad operada
	 */
	public String getCantidadOperada() {
		return cantidadOperada;
	}

	/**
	 * Sets the cantidad operada.
	 *
	 * @param cantidadOperada
	 *            the new cantidad operada
	 */
	public void setCantidadOperada(String cantidadOperada) {
		this.cantidadOperada = cantidadOperada;
	}

	/**
	 * Gets the cantidad pendiente.
	 *
	 * @return the cantidad pendiente
	 */
	public String getCantidadPendiente() {
		return cantidadPendiente;
	}

	/**
	 * Sets the cantidad pendiente.
	 *
	 * @param cantidadPendiente
	 *            the new cantidad pendiente
	 */
	public void setCantidadPendiente(String cantidadPendiente) {
		this.cantidadPendiente = cantidadPendiente;
	}

	/**
	 * Gets the monto inicial.
	 *
	 * @return the monto inicial
	 */
	public String getMontoInicial() {
		return montoInicial;
	}

	/**
	 * Sets the monto inicial.
	 *
	 * @param montoInicial
	 *            the new monto inicial
	 */
	public void setMontoInicial(String montoInicial) {
		this.montoInicial = montoInicial;
	}

	/**
	 * Gets the monto reversado.
	 *
	 * @return the monto reversado
	 */
	public String getMontoReversado() {
		return montoReversado;
	}

	/**
	 * Sets the monto reversado.
	 *
	 * @param montoReversado
	 *            the new monto reversado
	 */
	public void setMontoReversado(String montoReversado) {
		this.montoReversado = montoReversado;
	}

	/**
	 * Gets the monto operado.
	 *
	 * @return the monto operado
	 */
	public String getMontoOperado() {
		return montoOperado;
	}

	/**
	 * Sets the monto operado.
	 *
	 * @param montoOperado
	 *            the new monto operado
	 */
	public void setMontoOperado(String montoOperado) {
		this.montoOperado = montoOperado;
	}

	/**
	 * Gets the monto pendiente.
	 *
	 * @return the monto pendiente
	 */
	public String getMontoPendiente() {
		return montoPendiente;
	}

	/**
	 * Sets the monto pendiente.
	 *
	 * @param montoPendiente
	 *            the new monto pendiente
	 */
	public void setMontoPendiente(String montoPendiente) {
		this.montoPendiente = montoPendiente;
	}

	/**
	 * Gets the precio cierre.
	 *
	 * @return the precio cierre
	 */
	public String getPrecioCierre() {
		return precioCierre;
	}

	/**
	 * Sets the precio cierre.
	 *
	 * @param precioCierre
	 *            the new precio cierre
	 */
	public void setPrecioCierre(String precioCierre) {
		this.precioCierre = precioCierre;
	}

	/**
	 * Gets the precio referencia.
	 *
	 * @return the precio referencia
	 */
	public String getPrecioReferencia() {
		return precioReferencia;
	}

	/**
	 * Sets the precio referencia.
	 *
	 * @param precioReferencia
	 *            the new precio referencia
	 */
	public void setPrecioReferencia(String precioReferencia) {
		this.precioReferencia = precioReferencia;
	}

	/**
	 * Gets the precio limite.
	 *
	 * @return the precio limite
	 */
	public Double getPrecioLimite() {
		return precioLimite;
	}

	/**
	 * Sets the precio limite.
	 *
	 * @param precioLimite
	 *            the new precio limite
	 */
	public void setPrecioLimite(Double precioLimite) {
		this.precioLimite = precioLimite;
	}

	/**
	 * Gets the motivo reversa.
	 *
	 * @return the motivo reversa
	 */
	public String getMotivoReversa() {
		return motivoReversa;
	}

	/**
	 * Sets the motivo reversa.
	 *
	 * @param motivoReversa
	 *            the new motivo reversa
	 */
	public void setMotivoReversa(String motivoReversa) {
		this.motivoReversa = motivoReversa;
	}

	/**
	 * Gets the cno.
	 *
	 * @return the cno
	 */
	public String getCno() {
		return cno;
	}

	/**
	 * Sets the cno.
	 *
	 * @param cno
	 *            the new cno
	 */
	public void setCno(String cno) {
		this.cno = cno;
	}

	/**
	 * Gets the debito anticipado.
	 *
	 * @return the debito anticipado
	 */
	public String getDebitoAnticipado() {
		return debitoAnticipado;
	}

	/**
	 * Sets the debito anticipado.
	 *
	 * @param debitoAnticipado
	 *            the new debito anticipado
	 */
	public void setDebitoAnticipado(String debitoAnticipado) {
		this.debitoAnticipado = debitoAnticipado;
	}

	/**
	 * Gets the importe teorico.
	 *
	 * @return the importe teorico
	 */
	public String getImporteTeorico() {
		return importeTeorico;
	}

	/**
	 * Sets the importe teorico.
	 *
	 * @param importeTeorico
	 *            the new importe teorico
	 */
	public void setImporteTeorico(String importeTeorico) {
		this.importeTeorico = importeTeorico;
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
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the codigo moneda iso.
	 *
	 * @return the codigo moneda iso
	 */
	public String getCodigoMonedaIso() {
		return codigoMonedaIso;
	}

	/**
	 * Sets the codigo moneda iso.
	 *
	 * @param codigoMonedaIso
	 *            the new codigo moneda iso
	 */
	public void setCodigoMonedaIso(String codigoMonedaIso) {
		this.codigoMonedaIso = codigoMonedaIso;
	}

	/**
	 * Gets the descripcion de estado.
	 *
	 * @return the descripcion de estado
	 */
	public String getDescripcionDeEstado() {
		return descripcionDeEstado;
	}

	/**
	 * Sets the descripcion de estado.
	 *
	 * @param descripcionDeEstado
	 *            the new descripcion de estado
	 */
	public void setDescripcionDeEstado(String descripcionDeEstado) {
		this.descripcionDeEstado = descripcionDeEstado;
	}

	/**
	 * Gets the instanciaorden.
	 *
	 * @return the instanciaorden
	 */
	public String getInstanciaorden() {
		return instanciaorden;
	}

	/**
	 * Sets the instanciaorden.
	 *
	 * @param instanciaorden
	 *            the new instanciaorden
	 */
	public void setInstanciaorden(String instanciaorden) {
		this.instanciaorden = instanciaorden;
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
	 * Gets the instrumento codigo.
	 *
	 * @return the instrumento codigo
	 */
	public String getInstrumentoCodigo() {
		return instrumentoCodigo;
	}

	/**
	 * Sets the instrumento codigo.
	 *
	 * @param instrumentoCodigo
	 *            the new instrumento codigo
	 */
	public void setInstrumentoCodigo(String instrumentoCodigo) {
		this.instrumentoCodigo = instrumentoCodigo;
	}
		

}
