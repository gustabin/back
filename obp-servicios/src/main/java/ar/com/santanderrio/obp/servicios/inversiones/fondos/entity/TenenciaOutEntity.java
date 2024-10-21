/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Record;

/**
 * La entidad con los datos de respuesta del servicio CNSTENVAL 110.
 *
 * @author marcelo.ruiz
 */
@Record
public class TenenciaOutEntity {

	/** The codigo retorno extendido. */
	private String codigoRetornoExtendido;

	/** The nro repeticiones lista. */
	private String nroRepeticionesLista;

	/** The tenencias. */
	List<TenenciaEntity> tenencias;

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
		return new HashCodeBuilder().append(codigoRetornoExtendido).append(nroRepeticionesLista).append(tenencias)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
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

		TenenciaOutEntity other = (TenenciaOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder().append(codigoRetornoExtendido, other.getCodigoRetornoExtendido())
				.append(nroRepeticionesLista, other.getNroRepeticionesLista()).append(tenencias, other.getTenencias());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("codigoRetornoExtendido", codigoRetornoExtendido)
				.append("nroRepeticionesLista", nroRepeticionesLista).append("tenencias", tenencias).toString();
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the nro repeticiones lista.
	 *
	 * @return the nro repeticiones lista
	 */
	public String getNroRepeticionesLista() {
		return nroRepeticionesLista;
	}

	/**
	 * Sets the nro repeticiones lista.
	 *
	 * @param nroRepeticionesLista
	 *            the new nro repeticiones lista
	 */
	public void setNroRepeticionesLista(String nroRepeticionesLista) {
		this.nroRepeticionesLista = nroRepeticionesLista;
	}

	/**
	 * Gets the tenencias.
	 *
	 * @return the tenencias
	 */
	public List<TenenciaEntity> getTenencias() {
		return tenencias;
	}

	/**
	 * Sets the tenencias.
	 *
	 * @param tenencias
	 *            the new tenencias
	 */
	public void setTenencias(List<TenenciaEntity> tenencias) {
		this.tenencias = tenencias;
	}

	/**
	 * The Class TenenciaEntity.
	 */
	class TenenciaEntity {

		/** The sucursal. */
		private String sucursal;

		/** The nro cuenta. */
		private String nroCuenta;

		/** The especie tipo. */
		private String especieTipo;

		/** The especie codigo. */
		private String especieCodigo;

		/** The especie descripcion. */
		private String especieDescripcion;

		/** The tenencia nominal. */
		private String tenenciaNominal;

		/** The valor residual. */
		private String valorResidual;

		/** The tenencia valuada. */
		private String tenenciaValuada;

		/** The cotizacion. */
		private String cotizacion;

		/** The moneda tipo. */
		private String monedaTipo;

		/** The nombre moneda. */
		private String nombreMoneda;

		/** The fecha cotizacion. */
		private String fechaCotizacion;

		/** The hora cotizacion. */
		private String horaCotizacion;

		/** The Cod estado. */
		private String CodEstado;

		/** The estado descripcion. */
		private String estadoDescripcion;

		/** The sigla fondo. */
		private String siglaFondo;

		/** The saldo bruto. */
		private String saldoBruto;

		/** The total suscripciones. */
		private String totalSuscripciones;

		/** The cant cuotas suscriptas. */
		private String cantCuotasSuscriptas;

		/** The total rescates. */
		private String totalRescates;

		/** The cantidad cuotas rescatadas. */
		private String cantidadCuotasRescatadas;

		/** The fecha bloqueo. */
		private String fechaBloqueo;

		/** The fecha respuesta. */
		private String fechaRespuesta;

		/** The hora respuesta. */
		private String horaRespuesta;

		/** The cod error. */
		private String codError;

		/** The desc error. */
		private String descError;

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
			return new HashCodeBuilder().append(sucursal).append(nroCuenta).append(especieTipo).append(especieCodigo)
					.append(especieDescripcion).append(tenenciaNominal).append(valorResidual).append(tenenciaValuada)
					.append(cotizacion).append(monedaTipo).append(nombreMoneda).append(fechaCotizacion)
					.append(horaCotizacion).append(CodEstado).append(estadoDescripcion).append(siglaFondo)
					.append(saldoBruto).append(totalSuscripciones).append(cantCuotasSuscriptas).append(totalRescates)
					.append(cantidadCuotasRescatadas).append(fechaBloqueo).append(fechaRespuesta).append(horaRespuesta)
					.append(codError).append(descError).toHashCode();
		}

		/*
		 * (non-Javadoc)
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

			TenenciaEntity other = (TenenciaEntity) obj;
			EqualsBuilder eb = new EqualsBuilder().append(sucursal, other.getSucursal())
					.append(nroCuenta, other.getNroCuenta()).append(especieTipo, other.getEspecieTipo())
					.append(especieCodigo, other.getEspecieCodigo())
					.append(especieDescripcion, other.getEspecieDescripcion())
					.append(tenenciaNominal, other.getTenenciaNominal()).append(valorResidual, other.getValorResidual())
					.append(tenenciaValuada, other.getTenenciaValuada()).append(cotizacion, other.getCotizacion())
					.append(monedaTipo, other.getMonedaTipo()).append(nombreMoneda, other.getNombreMoneda())
					.append(fechaCotizacion, other.getFechaCotizacion()).append(horaCotizacion, other.horaCotizacion)
					.append(CodEstado, other.getCodEstado()).append(estadoDescripcion, other.getEstadoDescripcion())
					.append(siglaFondo, other.getSiglaFondo()).append(saldoBruto, other.getSaldoBruto())
					.append(totalSuscripciones, other.getTotalSuscripciones())
					.append(cantCuotasSuscriptas, other.getCantCuotasSuscriptas())
					.append(totalRescates, other.getTotalRescates())
					.append(cantidadCuotasRescatadas, other.getCantidadCuotasRescatadas())
					.append(fechaBloqueo, other.getFechaBloqueo()).append(fechaRespuesta, other.getFechaRespuesta())
					.append(horaRespuesta, other.getHoraRespuesta()).append(codError, other.getCodError())
					.append(descError, other.getDescError());
			return eb.isEquals();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return new ToStringBuilder(this).append("sucursal", sucursal).append("nroCuenta", nroCuenta)
					.append("especieTipo", especieTipo).append("especieCodigo", especieCodigo)
					.append("especieDescripcion", especieDescripcion).append("tenenciaNominal", tenenciaNominal)
					.append("valorResidual", valorResidual).append("tenenciaValuada", tenenciaValuada)
					.append("cotizacion", cotizacion).append("monedaTipo", monedaTipo)
					.append("nombreMoneda", nombreMoneda).append("fechaCotizacion", fechaCotizacion)
					.append("horaCotizacion", horaCotizacion).append("CodEstado", CodEstado)
					.append("estadoDescripcion", estadoDescripcion).append("siglaFondo", siglaFondo)
					.append("saldoBruto", saldoBruto).append("totalSuscripciones", totalSuscripciones)
					.append("cantCuotasSuscriptas", cantCuotasSuscriptas).append("totalRescates", totalRescates)
					.append("cantidadCuotasRescatadas", cantidadCuotasRescatadas).append("fechaBloqueo", fechaBloqueo)
					.append("fechaRespuesta", fechaRespuesta).append("horaRespuesta", horaRespuesta)
					.append("codError", codError).append("descError", descError).toString();
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
		 * Gets the tenencia valuada.
		 *
		 * @return the tenencia valuada
		 */
		public String getTenenciaValuada() {
			return tenenciaValuada;
		}

		/**
		 * Sets the tenencia valuada.
		 *
		 * @param tenenciaValuada
		 *            the new tenencia valuada
		 */
		public void setTenenciaValuada(String tenenciaValuada) {
			this.tenenciaValuada = tenenciaValuada;
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
		 * Gets the cod estado.
		 *
		 * @return the cod estado
		 */
		public String getCodEstado() {
			return CodEstado;
		}

		/**
		 * Sets the cod estado.
		 *
		 * @param codEstado
		 *            the new cod estado
		 */
		public void setCodEstado(String codEstado) {
			CodEstado = codEstado;
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
		 * Gets the cantidad cuotas rescatadas.
		 *
		 * @return the cantidad cuotas rescatadas
		 */
		public String getCantidadCuotasRescatadas() {
			return cantidadCuotasRescatadas;
		}

		/**
		 * Sets the cantidad cuotas rescatadas.
		 *
		 * @param cantidadCuotasRescatadas
		 *            the new cantidad cuotas rescatadas
		 */
		public void setCantidadCuotasRescatadas(String cantidadCuotasRescatadas) {
			this.cantidadCuotasRescatadas = cantidadCuotasRescatadas;
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
		 * Gets the cod error.
		 *
		 * @return the cod error
		 */
		public String getCodError() {
			return codError;
		}

		/**
		 * Sets the cod error.
		 *
		 * @param codError
		 *            the new cod error
		 */
		public void setCodError(String codError) {
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

	}

}
