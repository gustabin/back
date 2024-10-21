/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

import ar.com.santanderrio.obp.servicios.inversiones.TipoPapel;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author pablo.d.gargaglione
 *
 */
public class ConsultaTenenciaFCIEntity {

	/** The sucursal. */
	@Field()
	private String sucursal;

	/** The nro cuenta. */
	@Field()
	private String nroCuenta;

	/** The tipo papel enum. */
	@Field()
	private TipoPapel tipoPapelEnum;

	/** The especie codigo. */
	@Field()
	private String especieCodigo;

	/** The especie descripcion. */
	@Field()
	private String especieDescripcion;

	/** The tenencia nominal. */
	@Field()
	private String tenenciaNominal;

	/** The valor residual. */
	@Field()
	private String valorResidual;

	/** The tenecia valuada. */
	@Field()
	private String teneciaValuada;

	/** The cotizacion. */
	@Field()
	private String cotizacion;

	/** The moneda tipo. */
	@Field()
	private String monedaTipo;

	/** The nombre moneda. */
	@Field()
	private String nombreMoneda;

	/** The fecha cotizacion. */
	// FORMATO (YYYYMMDD)
	@Field()
	private String fechaCotizacion;

	/** The hora cotizacion. */
	// FORMATO (HHMMSS)
	@Field()
	private String horaCotizacion;

	/** The estado codigo. */
	@Field()
	private String estadoCodigo;

	/** The estado decripcion. */
	@Field()
	private String estadoDecripcion;

	/** The sigla fondo. */
	@Field()
	private String siglaFondo;

	/** The saldo bruto. */
	@Field()
	private String saldoBruto;

	/** The total suscripciones. */
	@Field()
	private String totalSuscripciones;

	/** The cant cuotas suscriptas. */
	@Field()
	private String cantCuotasSuscriptas;

	/** The total rescates. */
	@Field()
	private String totalRescates;

	/** The cant cuotas rescatadas. */
	@Field()
	private String cantCuotasRescatadas;

	/** The fecha bloqueo. */
	// FORMATO (YYYYMMDD)
	@Field()
	private String fechaBloqueo;

	/** The fecha respuesta. */
	// FORMATO (YYYYMMDD)
	@Field()
	private String fechaRespuesta;

	/** The hora respuesta. */
	// FORMATO (HHMMSS)
	@Field()
	private String horaRespuesta;

	/** The error codigo. */
	@Field()
	private String errorCodigo;

	/** The descripcion error. */
	@Field()
	private String descripcionError;

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
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the tipo papel enum.
	 *
	 * @return the tipo papel enum
	 */
	public TipoPapel getTipoPapelEnum() {
		return tipoPapelEnum;
	}

	/**
	 * Sets the tipo papel enum.
	 *
	 * @param tipoPapelEnum
	 *            the new tipo papel enum
	 */
	public void setTipoPapelEnum(TipoPapel tipoPapelEnum) {
		this.tipoPapelEnum = tipoPapelEnum;
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
	 * Gets the tenencia nominal.
	 *
	 * @return the tenencia nominal
	 */
	public String getTenenciaNominal() {
		return tenenciaNominal;
	}

	/**
	 * Sets the tenencia nominal.
	 *
	 * @param tenenciaNominal
	 *            the new tenencia nominal
	 */
	public void setTenenciaNominal(String tenenciaNominal) {
		this.tenenciaNominal = tenenciaNominal;
	}

	/**
	 * Gets the valor residual.
	 *
	 * @return the valor residual
	 */
	public String getValorResidual() {
		return valorResidual;
	}

	/**
	 * Sets the valor residual.
	 *
	 * @param valorResidual
	 *            the new valor residual
	 */
	public void setValorResidual(String valorResidual) {
		this.valorResidual = valorResidual;
	}

	/**
	 * Gets the tenecia valuada.
	 *
	 * @return the tenecia valuada
	 */
	public String getTeneciaValuada() {
		return teneciaValuada;
	}

	/**
	 * Sets the tenecia valuada.
	 *
	 * @param teneciaValuada
	 *            the new tenecia valuada
	 */
	public void setTeneciaValuada(String teneciaValuada) {
		this.teneciaValuada = teneciaValuada;
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
	 * Gets the moneda tipo.
	 *
	 * @return the moneda tipo
	 */
	public String getMonedaTipo() {
		return monedaTipo;
	}

	/**
	 * Sets the moneda tipo.
	 *
	 * @param monedaTipo
	 *            the new moneda tipo
	 */
	public void setMonedaTipo(String monedaTipo) {
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
	public String getFechaCotizacion() {
		return fechaCotizacion;
	}

	/**
	 * Sets the fecha cotizacion.
	 *
	 * @param fechaCotizacion
	 *            the new fecha cotizacion
	 */
	public void setFechaCotizacion(String fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	/**
	 * Gets the hora cotizacion.
	 *
	 * @return the hora cotizacion
	 */
	public String getHoraCotizacion() {
		return horaCotizacion;
	}

	/**
	 * Sets the hora cotizacion.
	 *
	 * @param horaCotizacion
	 *            the new hora cotizacion
	 */
	public void setHoraCotizacion(String horaCotizacion) {
		this.horaCotizacion = horaCotizacion;
	}

	/**
	 * Gets the estado codigo.
	 *
	 * @return the estado codigo
	 */
	public String getEstadoCodigo() {
		return estadoCodigo;
	}

	/**
	 * Sets the estado codigo.
	 *
	 * @param estadoCodigo
	 *            the new estado codigo
	 */
	public void setEstadoCodigo(String estadoCodigo) {
		this.estadoCodigo = estadoCodigo;
	}

	/**
	 * Gets the estado decripcion.
	 *
	 * @return the estado decripcion
	 */
	public String getEstadoDecripcion() {
		return estadoDecripcion;
	}

	/**
	 * Sets the estado decripcion.
	 *
	 * @param estadoDecripcion
	 *            the new estado decripcion
	 */
	public void setEstadoDecripcion(String estadoDecripcion) {
		this.estadoDecripcion = estadoDecripcion;
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
	public String getSaldoBruto() {
		return saldoBruto;
	}

	/**
	 * Sets the saldo bruto.
	 *
	 * @param saldoBruto
	 *            the new saldo bruto
	 */
	public void setSaldoBruto(String saldoBruto) {
		this.saldoBruto = saldoBruto;
	}

	/**
	 * Gets the total suscripciones.
	 *
	 * @return the total suscripciones
	 */
	public String getTotalSuscripciones() {
		return totalSuscripciones;
	}

	/**
	 * Sets the total suscripciones.
	 *
	 * @param totalSuscripciones
	 *            the new total suscripciones
	 */
	public void setTotalSuscripciones(String totalSuscripciones) {
		this.totalSuscripciones = totalSuscripciones;
	}

	/**
	 * Gets the cant cuotas suscriptas.
	 *
	 * @return the cant cuotas suscriptas
	 */
	public String getCantCuotasSuscriptas() {
		return cantCuotasSuscriptas;
	}

	/**
	 * Sets the cant cuotas suscriptas.
	 *
	 * @param cantCuotasSuscriptas
	 *            the new cant cuotas suscriptas
	 */
	public void setCantCuotasSuscriptas(String cantCuotasSuscriptas) {
		this.cantCuotasSuscriptas = cantCuotasSuscriptas;
	}

	/**
	 * Gets the total rescates.
	 *
	 * @return the total rescates
	 */
	public String getTotalRescates() {
		return totalRescates;
	}

	/**
	 * Sets the total rescates.
	 *
	 * @param totalRescates
	 *            the new total rescates
	 */
	public void setTotalRescates(String totalRescates) {
		this.totalRescates = totalRescates;
	}

	/**
	 * Gets the cant cuotas rescatadas.
	 *
	 * @return the cant cuotas rescatadas
	 */
	public String getCantCuotasRescatadas() {
		return cantCuotasRescatadas;
	}

	/**
	 * Sets the cant cuotas rescatadas.
	 *
	 * @param cantCuotasRescatadas
	 *            the new cant cuotas rescatadas
	 */
	public void setCantCuotasRescatadas(String cantCuotasRescatadas) {
		this.cantCuotasRescatadas = cantCuotasRescatadas;
	}

	/**
	 * Gets the fecha bloqueo.
	 *
	 * @return the fecha bloqueo
	 */
	public String getFechaBloqueo() {
		return fechaBloqueo;
	}

	/**
	 * Sets the fecha bloqueo.
	 *
	 * @param fechaBloqueo
	 *            the new fecha bloqueo
	 */
	public void setFechaBloqueo(String fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}

	/**
	 * Gets the fecha respuesta.
	 *
	 * @return the fecha respuesta
	 */
	public String getFechaRespuesta() {
		return fechaRespuesta;
	}

	/**
	 * Sets the fecha respuesta.
	 *
	 * @param fechaRespuesta
	 *            the new fecha respuesta
	 */
	public void setFechaRespuesta(String fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	/**
	 * Gets the hora respuesta.
	 *
	 * @return the hora respuesta
	 */
	public String getHoraRespuesta() {
		return horaRespuesta;
	}

	/**
	 * Sets the hora respuesta.
	 *
	 * @param horaRespuesta
	 *            the new hora respuesta
	 */
	public void setHoraRespuesta(String horaRespuesta) {
		this.horaRespuesta = horaRespuesta;
	}

	/**
	 * Gets the error codigo.
	 *
	 * @return the error codigo
	 */
	public String getErrorCodigo() {
		return errorCodigo;
	}

	/**
	 * Sets the error codigo.
	 *
	 * @param errorCodigo
	 *            the new error codigo
	 */
	public void setErrorCodigo(String errorCodigo) {
		this.errorCodigo = errorCodigo;
	}

	/**
	 * Gets the descripcion error.
	 *
	 * @return the descripcion error
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * Sets the descripcion error.
	 *
	 * @param descripcionError
	 *            the new descripcion error
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("sucursal", sucursal).append("nroCuenta", nroCuenta)
				.append("tipoPapelEnum", tipoPapelEnum).append("especieCodigo", especieCodigo)
				.append("especieDescripcion", especieDescripcion).append("tenenciaNominal", tenenciaNominal)
				.append("valorResidual", valorResidual).append("teneciaValuada", teneciaValuada)
				.append("cotizacion", cotizacion).append("monedaTipo", monedaTipo).append("nombreMoneda", nombreMoneda)
				.append("fechaCotizacion", fechaCotizacion).append("horaCotizacion", horaCotizacion)
				.append("estadoCodigo", estadoCodigo).append("estadoDecripcion", estadoDecripcion)
				.append("siglaFondo", siglaFondo).append("saldoBruto", saldoBruto)
				.append("totalSuscripciones", totalSuscripciones).append("cantCuotasSuscriptas", cantCuotasSuscriptas)
				.append("totalRescates", totalRescates).append("cantCuotasRescatadas", cantCuotasRescatadas)
				.append("fechaBloqueo", fechaBloqueo).append("fechaRespuesta", fechaRespuesta)
				.append("horaRespuesta", horaRespuesta).append("errorCodigo", errorCodigo)
				.append("descripcionError", descripcionError).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	/*
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cantCuotasRescatadas).append(cantCuotasSuscriptas).append(cotizacion)
				.append(descripcionError).append(errorCodigo).append(especieCodigo).append(especieDescripcion)
				.append(estadoCodigo).append(estadoDecripcion).append(fechaBloqueo).append(fechaCotizacion)
				.append(fechaRespuesta).append(horaCotizacion).append(horaRespuesta).append(monedaTipo)
				.append(nombreMoneda).append(nroCuenta).append(saldoBruto).append(siglaFondo).append(sucursal)
				.append(teneciaValuada).append(tenenciaNominal).append(tipoPapelEnum).append(totalRescates)
				.append(totalSuscripciones).append(valorResidual).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		ConsultaTenenciaFCIEntity other = (ConsultaTenenciaFCIEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(cantCuotasRescatadas, other.cantCuotasRescatadas)
				.append(cantCuotasSuscriptas, other.cantCuotasSuscriptas).append(cotizacion, other.cotizacion)
				.append(descripcionError, other.descripcionError).append(errorCodigo, other.errorCodigo)
				.append(especieCodigo, other.especieCodigo).append(especieDescripcion, other.especieDescripcion)
				.append(estadoCodigo, other.estadoCodigo).append(estadoDecripcion, other.estadoDecripcion)
				.append(fechaBloqueo, other.fechaBloqueo).append(fechaCotizacion, other.fechaCotizacion)
				.append(fechaRespuesta, other.fechaRespuesta).append(horaCotizacion, other.horaCotizacion)
				.append(horaRespuesta, other.horaRespuesta).append(monedaTipo, other.monedaTipo)
				.append(nombreMoneda, other.nombreMoneda).append(nroCuenta, other.nroCuenta)
				.append(saldoBruto, other.saldoBruto).append(siglaFondo, other.siglaFondo)
				.append(sucursal, other.sucursal).append(teneciaValuada, other.teneciaValuada)
				.append(tenenciaNominal, other.tenenciaNominal).append(tipoPapelEnum, other.tipoPapelEnum)
				.append(totalRescates, other.totalRescates).append(totalSuscripciones, other.totalSuscripciones)
				.append(valorResidual, other.valorResidual).isEquals();
	}

}
