/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.custodia.entity;

import java.math.BigDecimal;

/**
 * Clase que Modela Resultado del store Custodia_ol. el valor de una tenencia en
 * particular para realizar un rescate
 */
public class Tenencia {

	/** The sucursal. */
	private BigDecimal sucursal;

	/** The nro cuenta. */
	private BigDecimal nroCuenta;

	/** The especie tipo. */
	private String especieTipo;

	/** The especie codigo. */
	private BigDecimal especieCodigo;

	/** The especie descripcion. */
	private String especieDescripcion;

	/** The tenencia nominal. */
	private BigDecimal tenenciaNominal;

	/** The valor residual. */
	private BigDecimal valorResidual;

	/** The tenencia valuada. */
	private BigDecimal tenenciaValuada;

	/** The cotizacion. */
	private BigDecimal cotizacion;

	/** The moneda tipo. */
	private BigDecimal monedaTipo;

	/** The nombre moneda. */
	private String nombreMoneda;

	/** The fecha cotizacion. */
	private BigDecimal fechaCotizacion;

	/** The hora cotizacion. */
	private BigDecimal horaCotizacion;

	/** The cod estado. */
	private String codEstado;

	/** The estado descripcion. */
	private String estadoDescripcion;

	/** The sigla fondo. */
	private String siglaFondo;

	/** The saldo bruto. */
	private BigDecimal saldoBruto;

	/** The total suscripciones. */
	private BigDecimal totalSuscripciones;

	/** The cant cuotas suscriptas. */
	private BigDecimal cantCuotasSuscriptas;

	/** The total rescates. */
	private BigDecimal totalRescates;

	/** The cantidad cuotas rescatadas. */
	private BigDecimal cantidadCuotasRescatadas;

	/** The fecha bloqueo. */
	private BigDecimal fechaBloqueo;

	/** The fecha respuesta. */
	private BigDecimal fechaRespuesta;

	/** The hora respuesta. */
	private BigDecimal horaRespuesta;

	/** The cod error. */
	private BigDecimal codError;

	/** The desc error. */
	private String descError;

	/**
	 * Instantiates a new tenencia.
	 */
	public Tenencia() {
		super();
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public BigDecimal getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(BigDecimal sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public BigDecimal getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(BigDecimal nroCuenta) {
		this.nroCuenta = nroCuenta;
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
	public BigDecimal getEspecieCodigo() {
		return especieCodigo;
	}

	/**
	 * Sets the especie codigo.
	 *
	 * @param especieCodigo
	 *            the new especie codigo
	 */
	public void setEspecieCodigo(BigDecimal especieCodigo) {
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
	 * Gets the tenencia nominal.
	 *
	 * @return the tenencia nominal
	 */
	public BigDecimal getTenenciaNominal() {
		return tenenciaNominal;
	}

	/**
	 * Sets the tenencia nominal.
	 *
	 * @param tenenciaNominal
	 *            the new tenencia nominal
	 */
	public void setTenenciaNominal(BigDecimal tenenciaNominal) {
		this.tenenciaNominal = tenenciaNominal;
	}

	/**
	 * Gets the valor residual.
	 *
	 * @return the valor residual
	 */
	public BigDecimal getValorResidual() {
		return valorResidual;
	}

	/**
	 * Sets the valor residual.
	 *
	 * @param valorResidual
	 *            the new valor residual
	 */
	public void setValorResidual(BigDecimal valorResidual) {
		this.valorResidual = valorResidual;
	}

	/**
	 * Gets the tenencia valuada.
	 *
	 * @return the tenencia valuada
	 */
	public BigDecimal getTenenciaValuada() {
		return tenenciaValuada;
	}

	/**
	 * Sets the tenencia valuada.
	 *
	 * @param tenenciaValuada
	 *            the new tenencia valuada
	 */
	public void setTenenciaValuada(BigDecimal tenenciaValuada) {
		this.tenenciaValuada = tenenciaValuada;
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public BigDecimal getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the new cotizacion
	 */
	public void setCotizacion(BigDecimal cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the moneda tipo.
	 *
	 * @return the moneda tipo
	 */
	public BigDecimal getMonedaTipo() {
		return monedaTipo;
	}

	/**
	 * Sets the moneda tipo.
	 *
	 * @param monedaTipo
	 *            the new moneda tipo
	 */
	public void setMonedaTipo(BigDecimal monedaTipo) {
		this.monedaTipo = monedaTipo;
	}

	/**
	 * Gets the nombre moneda.
	 *
	 * @return the nombre moneda
	 */
	public String getNombreMoneda() {
		return nombreMoneda;
	}

	/**
	 * Sets the nombre moneda.
	 *
	 * @param nombreMoneda
	 *            the new nombre moneda
	 */
	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}

	/**
	 * Gets the fecha cotizacion.
	 *
	 * @return the fecha cotizacion
	 */
	public BigDecimal getFechaCotizacion() {
		return fechaCotizacion;
	}

	/**
	 * Sets the fecha cotizacion.
	 *
	 * @param fechaCotizacion
	 *            the new fecha cotizacion
	 */
	public void setFechaCotizacion(BigDecimal fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	/**
	 * Gets the hora cotizacion.
	 *
	 * @return the hora cotizacion
	 */
	public BigDecimal getHoraCotizacion() {
		return horaCotizacion;
	}

	/**
	 * Sets the hora cotizacion.
	 *
	 * @param horaCotizacion
	 *            the new hora cotizacion
	 */
	public void setHoraCotizacion(BigDecimal horaCotizacion) {
		this.horaCotizacion = horaCotizacion;
	}

	/**
	 * Gets the cod estado.
	 *
	 * @return the cod estado
	 */
	public String getCodEstado() {
		return codEstado;
	}

	/**
	 * Sets the cod estado.
	 *
	 * @param codEstado
	 *            the new cod estado
	 */
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	/**
	 * Gets the estado descripcion.
	 *
	 * @return the estado descripcion
	 */
	public String getEstadoDescripcion() {
		return estadoDescripcion;
	}

	/**
	 * Sets the estado descripcion.
	 *
	 * @param estadoDescripcion
	 *            the new estado descripcion
	 */
	public void setEstadoDescripcion(String estadoDescripcion) {
		this.estadoDescripcion = estadoDescripcion;
	}

	/**
	 * Gets the sigla fondo.
	 *
	 * @return the sigla fondo
	 */
	public String getSiglaFondo() {
		return siglaFondo;
	}

	/**
	 * Sets the sigla fondo.
	 *
	 * @param siglaFondo
	 *            the new sigla fondo
	 */
	public void setSiglaFondo(String siglaFondo) {
		this.siglaFondo = siglaFondo;
	}

	/**
	 * Gets the saldo bruto.
	 *
	 * @return the saldo bruto
	 */
	public BigDecimal getSaldoBruto() {
		return saldoBruto;
	}

	/**
	 * Sets the saldo bruto.
	 *
	 * @param saldoBruto
	 *            the new saldo bruto
	 */
	public void setSaldoBruto(BigDecimal saldoBruto) {
		this.saldoBruto = saldoBruto;
	}

	/**
	 * Gets the total suscripciones.
	 *
	 * @return the total suscripciones
	 */
	public BigDecimal getTotalSuscripciones() {
		return totalSuscripciones;
	}

	/**
	 * Sets the total suscripciones.
	 *
	 * @param totalSuscripciones
	 *            the new total suscripciones
	 */
	public void setTotalSuscripciones(BigDecimal totalSuscripciones) {
		this.totalSuscripciones = totalSuscripciones;
	}

	/**
	 * Gets the cant cuotas suscriptas.
	 *
	 * @return the cant cuotas suscriptas
	 */
	public BigDecimal getCantCuotasSuscriptas() {
		return cantCuotasSuscriptas;
	}

	/**
	 * Sets the cant cuotas suscriptas.
	 *
	 * @param cantCuotasSuscriptas
	 *            the new cant cuotas suscriptas
	 */
	public void setCantCuotasSuscriptas(BigDecimal cantCuotasSuscriptas) {
		this.cantCuotasSuscriptas = cantCuotasSuscriptas;
	}

	/**
	 * Gets the total rescates.
	 *
	 * @return the total rescates
	 */
	public BigDecimal getTotalRescates() {
		return totalRescates;
	}

	/**
	 * Sets the total rescates.
	 *
	 * @param totalRescates
	 *            the new total rescates
	 */
	public void setTotalRescates(BigDecimal totalRescates) {
		this.totalRescates = totalRescates;
	}

	/**
	 * Gets the cantidad cuotas rescatadas.
	 *
	 * @return the cantidad cuotas rescatadas
	 */
	public BigDecimal getCantidadCuotasRescatadas() {
		return cantidadCuotasRescatadas;
	}

	/**
	 * Sets the cantidad cuotas rescatadas.
	 *
	 * @param cantidadCuotasRescatadas
	 *            the new cantidad cuotas rescatadas
	 */
	public void setCantidadCuotasRescatadas(BigDecimal cantidadCuotasRescatadas) {
		this.cantidadCuotasRescatadas = cantidadCuotasRescatadas;
	}

	/**
	 * Gets the fecha bloqueo.
	 *
	 * @return the fecha bloqueo
	 */
	public BigDecimal getFechaBloqueo() {
		return fechaBloqueo;
	}

	/**
	 * Sets the fecha bloqueo.
	 *
	 * @param fechaBloqueo
	 *            the new fecha bloqueo
	 */
	public void setFechaBloqueo(BigDecimal fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}

	/**
	 * Gets the fecha respuesta.
	 *
	 * @return the fecha respuesta
	 */
	public BigDecimal getFechaRespuesta() {
		return fechaRespuesta;
	}

	/**
	 * Sets the fecha respuesta.
	 *
	 * @param fechaRespuesta
	 *            the new fecha respuesta
	 */
	public void setFechaRespuesta(BigDecimal fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	/**
	 * Gets the hora respuesta.
	 *
	 * @return the hora respuesta
	 */
	public BigDecimal getHoraRespuesta() {
		return horaRespuesta;
	}

	/**
	 * Sets the hora respuesta.
	 *
	 * @param horaRespuesta
	 *            the new hora respuesta
	 */
	public void setHoraRespuesta(BigDecimal horaRespuesta) {
		this.horaRespuesta = horaRespuesta;
	}

	/**
	 * Gets the cod error.
	 *
	 * @return the cod error
	 */
	public BigDecimal getCodError() {
		return codError;
	}

	/**
	 * Sets the cod error.
	 *
	 * @param codError
	 *            the new cod error
	 */
	public void setCodError(BigDecimal codError) {
		this.codError = codError;
	}

	/**
	 * Gets the desc error.
	 *
	 * @return the desc error
	 */
	public String getDescError() {
		return descError;
	}

	/**
	 * Sets the desc error.
	 *
	 * @param descError
	 *            the new desc error
	 */
	public void setDescError(String descError) {
		this.descError = descError;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tenencia [sucursal=" + sucursal + ", nroCuenta=" + nroCuenta + ", especieTipo=" + especieTipo
				+ ", especieCodigo=" + especieCodigo + ", especieDescripcion=" + especieDescripcion
				+ ", tenenciaNominal=" + tenenciaNominal + ", valorResidual=" + valorResidual + ", tenenciaValuada="
				+ tenenciaValuada + ", cotizacion=" + cotizacion + ", monedaTipo=" + monedaTipo + ", nombreMoneda="
				+ nombreMoneda + ", fechaCotizacion=" + fechaCotizacion + ", horaCotizacion=" + horaCotizacion
				+ ", codEstado=" + codEstado + ", estadoDescripcion=" + estadoDescripcion + ", siglaFondo=" + siglaFondo
				+ ", saldoBruto=" + saldoBruto + ", totalSuscripciones=" + totalSuscripciones
				+ ", cantCuotasSuscriptas=" + cantCuotasSuscriptas + ", totalRescates=" + totalRescates
				+ ", cantidadCuotasRescatadas=" + cantidadCuotasRescatadas + ", fechaBloqueo=" + fechaBloqueo
				+ ", fechaRespuesta=" + fechaRespuesta + ", horaRespuesta=" + horaRespuesta + ", codError=" + codError
				+ ", descError=" + descError + "]";
	}

}