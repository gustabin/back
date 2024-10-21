/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaUltimoResumenTarjetaDTO;

/**
 * View que representa cada consumo de tarjeta de la pantalla de ultimo resumen.
 *
 * @author federico.n.flores
 */
public class LineaUltimoResumenTarjetaView {

	/** The Constant FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	/** The fecha. */
	private String fecha;

	/** The fechaMobile. */
	private String fechaMobile;

	/** The descripcion. */
	private String descripcion;

	/** The importe. */
	private String importePesos;

	/** The es importe pesos. */
	private String importeDolares;

	/** The comprobante. */
	private String comprobante;

	/** The cuota. */
	private String cuota;

	/** The tiene cuota. */
	private Boolean tieneCuota;

	/** The cuotas canceladas. */
	private String cuotasPagadas;

	/** The cuotas totales. */
	private String cuotasTotales;

	/**
	 * Instantiates a new LineaUltimoResumenTarjetaView con argumento
	 * LineaUltimoResumenTarjetaDTO.
	 *
	 * @param dto
	 *            the dto
	 */
	public LineaUltimoResumenTarjetaView(LineaUltimoResumenTarjetaDTO dto) {
		super();
		this.setFecha(dto.getFecha());
		this.setFechaMobile(this.getFecha());
		this.setDescripcion(dto.getDescripcion());
		this.setImportePesos(dto.getImportePesos());
		this.setImporteDolares(dto.getImporteDolares());
		this.setComprobante(dto.getComprobante());
		this.setCuota(dto.getCuota());
		this.setTieneCuota(dto.getTieneCuota());
		this.setCuotasTotales(dto.getCuotasTotales());
		this.setCuotasPagadas(dto.getCuotasCanceladas());
	}

	/**
	 * Instantiates a new LineaUltimoResumenTarjetaView.
	 */
	public LineaUltimoResumenTarjetaView() {
		super();
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
		this.fecha = ISBANStringUtils.formatearFecha(fecha, FORMATO_FECHA);
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the importe pesos.
	 *
	 * @return the importe pesos
	 */
	public String getImportePesos() {
		return importePesos;
	}

	/**
	 * Sets the importe pesos.
	 *
	 * @param importePesos
	 *            the new importe pesos
	 */
	public void setImportePesos(BigDecimal importePesos) {
		if (importePesos != null) {
			this.importePesos = ISBANStringUtils.formatearSaldoConSigno(importePesos);
		}
	}

	/**
	 * Gets the importe dolares.
	 *
	 * @return the importe dolares
	 */
	public String getImporteDolares() {
		return importeDolares;
	}

	/**
	 * Sets the importe dolares.
	 *
	 * @param importeDolares
	 *            the new importe dolares
	 */
	public void setImporteDolares(BigDecimal importeDolares) {
		if (importeDolares != null) {
			this.importeDolares = ISBANStringUtils.formatearSaldoConSigno(importeDolares);
		}
	}

	/**
	 * Gets the fecha mobile.
	 *
	 * @return the fechaMobile
	 */
	public String getFechaMobile() {
		return fechaMobile;
	}

	/**
	 * Sets the fecha mobile.
	 *
	 * @param fecha
	 *            the new fecha mobile
	 */
	public void setFechaMobile(String fecha) {
		this.fechaMobile = ISBANStringUtils.convertirFechaAFormatoMobile(fecha);
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
	 *            the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the cuota.
	 *
	 * @return the cuota
	 */
	public String getCuota() {
		return cuota;
	}

	/**
	 * Sets the cuota.
	 *
	 * @param cuota
	 *            the cuota to set
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	/**
	 * Gets the tiene cuota.
	 *
	 * @return the tieneCuota
	 */
	public Boolean getTieneCuota() {
		return tieneCuota;
	}

	/**
	 * Sets the tiene cuota.
	 *
	 * @param tieneCuota
	 *            the tieneCuota to set
	 */
	public void setTieneCuota(Boolean tieneCuota) {
		this.tieneCuota = tieneCuota;
	}

	/**
	 * Gets the cuotas pagadas.
	 *
	 * @return the cuotasPagadas
	 */
	public String getCuotasPagadas() {
		return cuotasPagadas;
	}

	/**
	 * Sets the cuotas pagadas.
	 *
	 * @param cuotasPagadas
	 *            the cuotasPagadas to set
	 */
	public void setCuotasPagadas(String cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	/**
	 * Gets the cuotas totales.
	 *
	 * @return the cuotasTotales
	 */
	public String getCuotasTotales() {
		return cuotasTotales;
	}

	/**
	 * Sets the cuotas totales.
	 *
	 * @param cuotasTotales
	 *            the cuotasTotales to set
	 */
	public void setCuotasTotales(String cuotasTotales) {
		this.cuotasTotales = cuotasTotales;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(comprobante);
		hcb.append(fecha);
		hcb.append(fechaMobile);
		hcb.append(descripcion);
		hcb.append(importePesos);
		hcb.append(importeDolares);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
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
		LineaUltimoResumenTarjetaView other = (LineaUltimoResumenTarjetaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(comprobante, other.comprobante);
		eb.append(fecha, other.fecha);
		eb.append(fechaMobile, other.fechaMobile);
		eb.append(importePesos, other.importePesos);
		eb.append(importeDolares, other.importeDolares);
		eb.append(descripcion, other.descripcion);
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("fecha", fecha).append("fechaMobile", fechaMobile)
				.append("descripcion", descripcion).append("importePesos", importePesos)
				.append("importeDolares", importeDolares).append("comprobante", comprobante).toString();
	}

}