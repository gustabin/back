/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Representa un registro de cuota pendiente de una tarjeta de credito.
 */
public class CuotasPendientesLineaDTO {

	/** The establecimiento. */
	private String establecimiento;

	/** The operacion. */
	private String operacion;

	/** The fecha. */
	private Date fecha;

	/** The cantidad cuotas. */
	private Integer cantidadCuotas;

	/** The cuotas pendientes. */
	private Integer cuotasPendientes;

	/** The restante. */
	private BigDecimal restante;

	/** The comprobante. */
	private String comprobante;
	
	/** The plan de cuotas. */
	private String planDeCuotas;
	
	/** The fecha excel. */
	private String fechaExcel;
	
	/** The restante excel. */
	private String restanteExcel;
	
	/**
	 * Gets the establecimiento.
	 *
	 * @return the establecimiento
	 */
	public String getEstablecimiento() {
		return establecimiento;
	}

	/**
	 * Sets the establecimiento.
	 *
	 * @param establecimiento
	 *            the new establecimiento
	 */
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	/**
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * Sets the operacion.
	 *
	 * @param operacion
	 *            the new operacion
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the cantidad cuotas.
	 *
	 * @return the cantidadCuotas
	 */
	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}

	/**
	 * Sets the cantidad cuotas.
	 *
	 * @param cantidadCuotas
	 *            the cantidadCuotas to set
	 */
	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	/**
	 * Gets the restante.
	 *
	 * @return the restante
	 */
	public BigDecimal getRestante() {
		return restante;
	}

	/**
	 * Sets the restante.
	 *
	 * @param restante
	 *            the restante to set
	 */
	public void setRestante(BigDecimal restante) {
		this.restante = restante;
	}
	
	

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the new comprobante
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the plan de cuotas.
	 *
	 * @return the plan de cuotas
	 */
	public String getPlanDeCuotas() {
		return planDeCuotas;
	}

	/**
	 * Sets the plan de cuotas.
	 *
	 * @param planDeCuotas
	 *            the new plan de cuotas
	 */
	public void setPlanDeCuotas(String planDeCuotas) {
		this.planDeCuotas = planDeCuotas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantidadCuotas);
		hcb.append(cuotasPendientes);
		hcb.append(establecimiento);
		hcb.append(fecha);
		hcb.append(operacion);
		hcb.append(restante);
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
		CuotasPendientesLineaDTO other = (CuotasPendientesLineaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantidadCuotas, other.cantidadCuotas);
		eb.append(cuotasPendientes, other.cuotasPendientes);
		eb.append(establecimiento, other.establecimiento);
		eb.append(fecha, other.fecha);
		eb.append(operacion, other.operacion);
		eb.append(restante, other.restante);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuotasPendientesLineaDTO [establecimiento=" + establecimiento + ", operacion=" + operacion + ", fecha="
				+ fecha + ", cantidadCuotas=" + cantidadCuotas + ", cuotasPendientes=" + cuotasPendientes
				+ ", restante=" + restante + "]";
	}

	/**
	 * Gets the cuotas pendientes.
	 *
	 * @return the cuotas pendientes
	 */
	public Integer getCuotasPendientes() {
		return cuotasPendientes;
	}

	/**
	 * Sets the cuotas pendientes.
	 *
	 * @param cuotasPendientes
	 *            the new cuotas pendientes
	 */
	public void setCuotasPendientes(Integer cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
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
	 * Gets the restante excel.
	 *
	 * @return the restante excel
	 */
	public String getRestanteExcel() {
		return restanteExcel;
	}

	/**
	 * Sets the restante excel.
	 *
	 * @param restanteExcel
	 *            the new restante excel
	 */
	public void setRestanteExcel(String restanteExcel) {
		this.restanteExcel = restanteExcel;
	}


}
