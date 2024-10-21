/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionLineaDTO;

/**
 * Representa un detalle de financiacion.
 *
 * @author sabrina.cis
 */
public class ConsultaFinanciacionLineaView {

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The numero de solicitud. */
	private String numeroDeSolicitud;

	/** The fecha solicitud. */
	private String fechaSolicitud;

	/** The monto total del plan en pesos. */
	private String montoTotalDelPlanEnPesos;

	/** The monto cuota del plan. */
	private String montoCuotaDelPlan;

	/** The cuotas pagadas. */
	private String cantidadCuotas;

	/** The cuotas pagadas. */
	private String cuotasPendientesALiquidar;

	/** The tasa anual aplicada. */
	private String tasaAnualAplicada;

	/** The costo financiero. */
	private String costoFinanciero;

	/**
	 * Instantiates a new consulta financiacion linea view.
	 *
	 * @param financiacion
	 *            the financiacion
	 */
	public ConsultaFinanciacionLineaView(ConsultaFinanciacionLineaDTO financiacion) {
		super();
		this.setCantidadCuotas(financiacion.getCantidadCuotas());
		this.setCostoFinanciero(financiacion.getCostoFinanciero());
		this.setCuotasPendientesALiquidar(financiacion.getCuotasPendientesALiquidar());
		this.setFechaSolicitud(financiacion.getFechaSolicitud());
		this.setMontoCuotaDelPlan(financiacion.getMontoCuotaDelPlan());
		this.setMontoTotalDelPlanEnPesos(financiacion.getMontoTotalDelPlanEnPesos());
		this.setNumeroDeSolicitud(financiacion.getNumeroDeSolicitud());
		this.setNumeroTarjeta(financiacion.getNumeroTarjeta());
		this.setTasaAnualAplicada(financiacion.getTasaAnualAplicada());
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the numero de solicitud.
	 *
	 * @return the numero de solicitud
	 */
	public String getNumeroDeSolicitud() {
		return numeroDeSolicitud;
	}

	/**
	 * Sets the numero de solicitud.
	 *
	 * @param numeroDeSolicitud
	 *            the new numero de solicitud
	 */
	public void setNumeroDeSolicitud(String numeroDeSolicitud) {
		this.numeroDeSolicitud = numeroDeSolicitud;
	}

	/**
	 * Gets the fecha solicitud.
	 *
	 * @return the fecha solicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Sets the fecha solicitud.
	 *
	 * @param fechaSolicitud
	 *            the new fecha solicitud
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		try {
			this.fechaSolicitud = ISBANStringUtils.formatearFechaConAnio(fechaSolicitud);
		} catch (ParseException e) {
			this.fechaSolicitud = ISBANStringUtils.FORMATO_SIN_FECHA_ASOCIADA_CON_ANIO;
		}
	}

	/**
	 * Gets the monto total del plan en pesos.
	 *
	 * @return the monto total del plan en pesos
	 */
	public String getMontoTotalDelPlanEnPesos() {
		return montoTotalDelPlanEnPesos;
	}

	/**
	 * Sets the monto total del plan en pesos.
	 *
	 * @param montoTotalDelPlanEnPesos
	 *            the new monto total del plan en pesos
	 */
	public void setMontoTotalDelPlanEnPesos(BigDecimal montoTotalDelPlanEnPesos) {
		this.montoTotalDelPlanEnPesos = ISBANStringUtils.formatearSaldo(montoTotalDelPlanEnPesos);
	}

	/**
	 * Gets the monto cuota del plan.
	 *
	 * @return the monto cuota del plan
	 */
	public String getMontoCuotaDelPlan() {
		return montoCuotaDelPlan;
	}

	/**
	 * Sets the monto cuota del plan.
	 *
	 * @param montoCuotaDelPlan
	 *            the new monto cuota del plan
	 */
	public void setMontoCuotaDelPlan(BigDecimal montoCuotaDelPlan) {
		this.montoCuotaDelPlan = ISBANStringUtils.formatearSaldo(montoCuotaDelPlan);
	}

	/**
	 * Gets the tasa anual aplicada.
	 *
	 * @return the tasa anual aplicada
	 */
	public String getTasaAnualAplicada() {
		return tasaAnualAplicada;
	}

	/**
	 * Sets the tasa anual aplicada.
	 *
	 * @param tasaAnualAplicada
	 *            the new tasa anual aplicada
	 */
	public void setTasaAnualAplicada(BigDecimal tasaAnualAplicada) {
		this.tasaAnualAplicada = ISBANStringUtils.formatearSaldo(tasaAnualAplicada);
	}

	/**
	 * Gets the costo financiero.
	 *
	 * @return the costo financiero
	 */
	public String getCostoFinanciero() {
		return costoFinanciero;
	}

	/**
	 * Sets the costo financiero.
	 *
	 * @param costoFinanciero
	 *            the new costo financiero
	 */
	public void setCostoFinanciero(BigDecimal costoFinanciero) {
		this.costoFinanciero = ISBANStringUtils.formatearSaldo(costoFinanciero);
	}

	/**
	 * Gets the cuotas pagadas.
	 *
	 * @return the cuotas pagadas
	 */
	public String getCantidadCuotas() {
		return cantidadCuotas;
	}

	/**
	 * Sets the cuotas pagadas.
	 *
	 * @param cantidadCuotas
	 *            the new cuotas pagadas
	 */
	public void setCantidadCuotas(Integer cantidadCuotas) {
		try {
			this.cantidadCuotas = TarjetaBOUtils.parsearString(cantidadCuotas);
		} catch (TarjetaBOUtilsException e) {
			this.cantidadCuotas = "";
		}
	}

	/**
	 * Gets the cuotas pagadas.
	 *
	 * @return the cuotas pagadas
	 */
	public String getCuotasPendientesALiquidar() {
		return cuotasPendientesALiquidar;
	}

	/**
	 * Sets the cuotas pagadas.
	 *
	 * @param cuotasPendientesALiquidar
	 *            the new cuotas pagadas
	 */
	public void setCuotasPendientesALiquidar(Integer cuotasPendientesALiquidar) {
		try {
			this.cuotasPendientesALiquidar = TarjetaBOUtils.parsearString(cuotasPendientesALiquidar);
		} catch (TarjetaBOUtilsException e) {
			this.cuotasPendientesALiquidar = "";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(numeroTarjeta);
		hcb.append(numeroDeSolicitud);
		hcb.append(fechaSolicitud);
		hcb.append(montoTotalDelPlanEnPesos);
		hcb.append(montoCuotaDelPlan);
		hcb.append(cantidadCuotas);
		hcb.append(cuotasPendientesALiquidar);
		hcb.append(tasaAnualAplicada);
		hcb.append(costoFinanciero);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConsultaFinanciacionLineaView other = (ConsultaFinanciacionLineaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(numeroTarjeta, other.numeroTarjeta);
		eb.append(numeroDeSolicitud, other.numeroDeSolicitud);
		eb.append(fechaSolicitud, other.fechaSolicitud);
		eb.append(montoTotalDelPlanEnPesos, other.montoTotalDelPlanEnPesos);
		eb.append(montoCuotaDelPlan, other.montoCuotaDelPlan);
		eb.append(cantidadCuotas, other.cantidadCuotas);
		eb.append(cuotasPendientesALiquidar, other.cuotasPendientesALiquidar);
		eb.append(tasaAnualAplicada, other.tasaAnualAplicada);
		eb.append(costoFinanciero, other.costoFinanciero);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultaFinanciacionLineaView [numeroTarjeta=" + numeroTarjeta + ", numeroDeSolicitud="
				+ numeroDeSolicitud + ", fechaSolicitud=" + fechaSolicitud + ", montoTotalDelPlanEnPesos="
				+ montoTotalDelPlanEnPesos + ", montoCuotaDelPlan=" + montoCuotaDelPlan + ", cantidadCuotas="
				+ cantidadCuotas + ", cuotasPendientesALiquidar=" + cuotasPendientesALiquidar + ", tasaAnualAplicada="
				+ tasaAnualAplicada + ", costoFinanciero=" + costoFinanciero + "]";
	}

}
