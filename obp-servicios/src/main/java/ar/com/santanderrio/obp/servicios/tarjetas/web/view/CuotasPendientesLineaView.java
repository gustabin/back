/*
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
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesLineaDTO;

/**
 * Representa un registro de cuota pendiente de una tarjeta de credito.
 */
public class CuotasPendientesLineaView {

	/** The establecimiento. */
	private String establecimiento;

	/** The operacion. */
	private String operacion;

	/** The fecha. */
	private String fecha;

	/** The cantidad cuotas. */
	private String cantidadCuotas;

	/** The cuotas pendientes. */
	private String cuotasPendientes;

	/** The restante. */
	private String restante;

	/**
	 * Instantiates a new cuotas pendientes linea view.
	 *
	 * @param cuotasPendientesLineaDTO
	 *            the cuotas pendientes linea DTO
	 */
	public CuotasPendientesLineaView(CuotasPendientesLineaDTO cuotasPendientesLineaDTO) {
		super();
		this.setEstablecimiento(cuotasPendientesLineaDTO.getEstablecimiento());
		this.setOperacion(cuotasPendientesLineaDTO.getOperacion());
		this.setFecha(cuotasPendientesLineaDTO.getFecha());
		this.setCantidadCuotas(cuotasPendientesLineaDTO.getCantidadCuotas());
		this.setCuotasPendientes(cuotasPendientesLineaDTO.getCuotasPendientes());
		this.setRestante(cuotasPendientesLineaDTO.getRestante());
	}

	/**
	 * Instantiates a new cuotas pendientes linea view.
	 */
	public CuotasPendientesLineaView() {
		super();
	}

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
		this.establecimiento = establecimiento.trim();
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
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(Date fecha) {
		try {
			this.fecha = ISBANStringUtils.formatearFechaConAnio(fecha);
		} catch (ParseException e) {
			this.fecha = ISBANStringUtils.FORMATO_SIN_FECHA_ASOCIADA_CON_ANIO;
		}
	}

	/**
	 * Gets the cantidad cuotas.
	 *
	 * @return the cantidad cuotas
	 */
	public String getCantidadCuotas() {
		return cantidadCuotas;
	}

	/**
	 * Sets the cantidad cuotas.
	 *
	 * @param cantidadCuotas
	 *            the new cantidad cuotas
	 */
	public void setCantidadCuotas(Integer cantidadCuotas) {
		try {
			this.cantidadCuotas = TarjetaBOUtils.parsearString(cantidadCuotas);
		} catch (TarjetaBOUtilsException e) {
			this.cantidadCuotas = "";
		}
	}

	/**
	 * Gets the cuotas pendientes.
	 *
	 * @return the cuotas pendientes
	 */
	public String getCuotasPendientes() {
		return cuotasPendientes;
	}

	/**
	 * Sets the cuotas pendientes.
	 *
	 * @param cuotasPendientes
	 *            the new cuotas pendientes
	 */
	public void setCuotasPendientes(Integer cuotasPendientes) {
		this.cuotasPendientes = Integer.toString(cuotasPendientes);
		;
	}

	/**
	 * Gets the restante.
	 *
	 * @return the restante
	 */
	public String getRestante() {
		return restante;
	}

	/**
	 * Sets the restante.
	 *
	 * @param restante
	 *            the new restante
	 */
	public void setRestante(BigDecimal restante) {
		this.restante = ISBANStringUtils.formatearSaldo(restante);
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Sets the restante.
	 *
	 * @param restante
	 *            the restante to set
	 */
	public void setRestante(String restante) {
		this.restante = restante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuotasPendientesLineaView [establecimiento=" + establecimiento + ", operacion=" + operacion + ", fecha="
				+ fecha + ", cantidadCuotas=" + cantidadCuotas + ", cuotasPendientes=" + cuotasPendientes
				+ ", restante=" + restante + "]";
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
		CuotasPendientesLineaView other = (CuotasPendientesLineaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantidadCuotas, other.cantidadCuotas);
		eb.append(cuotasPendientes, other.cuotasPendientes);
		eb.append(establecimiento, other.establecimiento);
		eb.append(fecha, other.fecha);
		eb.append(operacion, other.operacion);
		eb.append(restante, other.restante);
		return eb.isEquals();
	}

}
