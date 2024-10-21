/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ConsultaFinanciacionLineaDTO.
 */
public class ConsultaFinanciacionLineaDTO {

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The numero de solicitud. */
	private String numeroDeSolicitud;

	/** The fecha solicitud. */
	private Date fechaSolicitud;

	/** The monto total del plan en pesos. */
	private BigDecimal montoTotalDelPlanEnPesos;

	/** The monto cuota del plan. */
	private BigDecimal montoCuotaDelPlan;

	/** The cuotas pagadas. */
	private Integer cantidadCuotas;

	/** The cuotas pagadas. */
	private Integer cuotasPendientesALiquidar;

	/** The tasa anual aplicada. */
	private BigDecimal tasaAnualAplicada;

	/** The costo financiero. */
	private BigDecimal costoFinanciero;
	
	/** The cuotas pagadas. */
	private int cuotasPagadas;
	
	/** The fecha excel. */
	private String fechaExcel;
	
	/** The monto total del plan en pesos excel. */
	private String montoTotalDelPlanEnPesosExcel;
	
	/** The monto cuota del plan excel. */
	private String montoCuotaDelPlanExcel;
	
	/** The tasa anual aplicada. */
	private String tasaAnualAplicadaExcel;
	
	/** The costo financiero. */
	private String costoFinancieroExcel;
	

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
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Sets the fecha solicitud.
	 *
	 * @param fechaSolicitud
	 *            the new fecha solicitud
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Gets the monto total del plan en pesos.
	 *
	 * @return the monto total del plan en pesos
	 */
	public BigDecimal getMontoTotalDelPlanEnPesos() {
		return montoTotalDelPlanEnPesos;
	}

	/**
	 * Sets the monto total del plan en pesos.
	 *
	 * @param montoTotalDelPlanEnPesos
	 *            the new monto total del plan en pesos
	 */
	public void setMontoTotalDelPlanEnPesos(BigDecimal montoTotalDelPlanEnPesos) {
		this.montoTotalDelPlanEnPesos = montoTotalDelPlanEnPesos;
	}

	/**
	 * Gets the monto cuota del plan.
	 *
	 * @return the monto cuota del plan
	 */
	public BigDecimal getMontoCuotaDelPlan() {
		return montoCuotaDelPlan;
	}

	/**
	 * Sets the monto cuota del plan.
	 *
	 * @param montoCuotaDelPlan
	 *            the new monto cuota del plan
	 */
	public void setMontoCuotaDelPlan(BigDecimal montoCuotaDelPlan) {
		this.montoCuotaDelPlan = montoCuotaDelPlan;
	}

	/**
	 * Gets the tasa anual aplicada.
	 *
	 * @return the tasa anual aplicada
	 */
	public BigDecimal getTasaAnualAplicada() {
		return tasaAnualAplicada;
	}

	/**
	 * Sets the tasa anual aplicada.
	 *
	 * @param tasaAnualAplicada
	 *            the new tasa anual aplicada
	 */
	public void setTasaAnualAplicada(BigDecimal tasaAnualAplicada) {
		this.tasaAnualAplicada = tasaAnualAplicada;
	}

	/**
	 * Gets the costo financiero.
	 *
	 * @return the costo financiero
	 */
	public BigDecimal getCostoFinanciero() {
		return costoFinanciero;
	}

	/**
	 * Sets the costo financiero.
	 *
	 * @param costoFinanciero
	 *            the new costo financiero
	 */
	public void setCostoFinanciero(BigDecimal costoFinanciero) {
		this.costoFinanciero = costoFinanciero;
	}

	/**
	 * Gets the cuotas pagadas.
	 *
	 * @return the cuotas pagadas
	 */
	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}

	/**
	 * Sets the cuotas pagadas.
	 *
	 * @param cantidadCuotas
	 *            the new cuotas pagadas
	 */
	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	/**
	 * Gets the cuotas pagadas.
	 *
	 * @return the cuotas pagadas
	 */
	public Integer getCuotasPendientesALiquidar() {
		return cuotasPendientesALiquidar;
	}

	/**
	 * Sets the cuotas pagadas.
	 *
	 * @param cuotasPendientesALiquidar
	 *            the new cuotas pagadas
	 */
	public void setCuotasPendientesALiquidar(Integer cuotasPendientesALiquidar) {
		this.cuotasPendientesALiquidar = cuotasPendientesALiquidar;
	}
	
	

	/**
	 * Gets the cuotas pagadas.
	 *
	 * @return the cuotas pagadas
	 */
	public int getCuotasPagadas() {
		return cuotasPagadas;
	}

	/**
	 * Sets the cuotas pagadas.
	 *
	 * @param cuotasPagadas
	 *            the new cuotas pagadas
	 */
	public void setCuotasPagadas(int cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	/**
	 * Gets the fecha excel.
	 *
	 * @return the fecha excel
	 */
	public String getFechaExcel() {
		return fechaExcel;
	}

	/**
	 * Sets the fecha excel.
	 *
	 * @param fechaExcel
	 *            the new fecha excel
	 */
	public void setFechaExcel(String fechaExcel) {
		this.fechaExcel = fechaExcel;
	}
	
	/**
	 * Gets the monto total del plan en pesos excel.
	 *
	 * @return the monto total del plan en pesos excel
	 */
	public String getMontoTotalDelPlanEnPesosExcel() {
		return montoTotalDelPlanEnPesosExcel;
	}

	/**
	 * Sets the monto total del plan en pesos excel.
	 *
	 * @param montoTotalDelPlanEnPesosExcel
	 *            the new monto total del plan en pesos excel
	 */
	public void setMontoTotalDelPlanEnPesosExcel(String montoTotalDelPlanEnPesosExcel) {
		this.montoTotalDelPlanEnPesosExcel = montoTotalDelPlanEnPesosExcel;
	}

	/**
	 * Gets the monto cuota del plan excel.
	 *
	 * @return the monto cuota del plan excel
	 */
	public String getMontoCuotaDelPlanExcel() {
		return montoCuotaDelPlanExcel;
	}

	/**
	 * Sets the monto cuota del plan excel.
	 *
	 * @param montoCuotaDelPlanExcel
	 *            the new monto cuota del plan excel
	 */
	public void setMontoCuotaDelPlanExcel(String montoCuotaDelPlanExcel) {
		this.montoCuotaDelPlanExcel = montoCuotaDelPlanExcel;
	}

	/**
	 * Gets the tasa anual aplicada excel.
	 *
	 * @return the tasa anual aplicada excel
	 */
	public String getTasaAnualAplicadaExcel() {
		return tasaAnualAplicadaExcel;
	}

	/**
	 * Sets the tasa anual aplicada excel.
	 *
	 * @param tasaAnualAplicadaExcel
	 *            the new tasa anual aplicada excel
	 */
	public void setTasaAnualAplicadaExcel(String tasaAnualAplicadaExcel) {
		this.tasaAnualAplicadaExcel = tasaAnualAplicadaExcel;
	}

	/**
	 * Gets the costo financiero excel.
	 *
	 * @return the costo financiero excel
	 */
	public String getCostoFinancieroExcel() {
		return costoFinancieroExcel;
	}

	/**
	 * Sets the costo financiero excel.
	 *
	 * @param costoFinancieroExcel
	 *            the new costo financiero excel
	 */
	public void setCostoFinancieroExcel(String costoFinancieroExcel) {
		this.costoFinancieroExcel = costoFinancieroExcel;
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
		ConsultaFinanciacionLineaDTO other = (ConsultaFinanciacionLineaDTO) obj;
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
		return "ConsultaFinanciacionLineaDTO [numeroTarjeta=" + numeroTarjeta + ", numeroDeSolicitud="
				+ numeroDeSolicitud + ", fechaSolicitud=" + fechaSolicitud + ", montoTotalDelPlanEnPesos="
				+ montoTotalDelPlanEnPesos + ", montoCuotaDelPlan=" + montoCuotaDelPlan + ", cantidadCuotas="
				+ cantidadCuotas + ", cuotasPendientesALiquidar=" + cuotasPendientesALiquidar + ", tasaAnualAplicada="
				+ tasaAnualAplicada + ", costoFinanciero=" + costoFinanciero + "]";
	}


}
