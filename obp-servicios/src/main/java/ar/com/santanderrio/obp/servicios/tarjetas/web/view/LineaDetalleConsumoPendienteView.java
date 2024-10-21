/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoPendienteDTO;

/**
 * The Class LineaDetalleConsumoPendienteView.
 */
public class LineaDetalleConsumoPendienteView {

	/** The descripcion. */
	private String descripcion;

	/** The cuota. */
	private String cuota;

	/** The importe pesos. */
	private String importePesos;

	/** The importe dolares. */
	private String importeDolares;

	/** The fecha. */
	private String fecha;

	/** The codigo establecimiento. */
	private String codigoEstablecimiento;

	/** The is consumo pesos. */
	private Boolean isConsumoPesos;

	/**
	 * Instantiates a new linea detalle consumo pendiente view.
	 */
	public LineaDetalleConsumoPendienteView() {
		super();
	}

	/**
	 * Instantiates a new linea detalle consumo pendiente view con dto.
	 *
	 * @param dto
	 *            the dto
	 */
	public LineaDetalleConsumoPendienteView(LineaDetalleConsumoPendienteDTO dto) {
		this.setDescripcion(dto.getDescripcion());
		this.setCuota(dto.getCuota());
		this.setImportePesos(dto.getImportePesos());
		this.setImporteDolares(dto.getImporteDolares());
		this.setFecha(dto.getFecha());
		this.setCodigoEstablecimiento(dto.getCodigoEstablecimiento());
		this.setIsConsumoPesos(dto.getIsConsumoPesos());
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
	 *            the new cuota
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	/**
	 * Gets the codigo establecimiento.
	 *
	 * @return the codigo establecimiento
	 */
	public String getCodigoEstablecimiento() {
		return codigoEstablecimiento;
	}

	/**
	 * Sets the codigo establecimiento.
	 *
	 * @param codigoEstablecimiento
	 *            the new codigo establecimiento
	 */
	public void setCodigoEstablecimiento(String codigoEstablecimiento) {
		this.codigoEstablecimiento = codigoEstablecimiento;
	}

	/**
	 * Gets the consumo pesos.
	 *
	 * @return the consumo pesos
	 */
	public Boolean getConsumoPesos() {
		return isConsumoPesos;
	}

	/**
	 * Sets the consumo pesos.
	 *
	 * @param consumoPesos
	 *            the new consumo pesos
	 */
	public void setConsumoPesos(Boolean consumoPesos) {
		this.isConsumoPesos = consumoPesos;
	}

	/**
	 * Gets the checks if is consumo pesos.
	 *
	 * @return the checks if is consumo pesos
	 */
	public Boolean getIsConsumoPesos() {
		return isConsumoPesos;
	}

	/**
	 * Sets the checks if is consumo pesos.
	 *
	 * @param isConsumoPesos
	 *            the new checks if is consumo pesos
	 */
	public void setIsConsumoPesos(Boolean isConsumoPesos) {
		this.isConsumoPesos = isConsumoPesos;
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
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoEstablecimiento);
		hcb.append(isConsumoPesos);
		hcb.append(cuota);
		hcb.append(descripcion);
		hcb.append(fecha);
		hcb.append(importeDolares);
		hcb.append(importePesos);
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
		LineaDetalleConsumoPendienteView other = (LineaDetalleConsumoPendienteView) obj;
		return new EqualsBuilder().append(codigoEstablecimiento, other.codigoEstablecimiento)
				.append(isConsumoPesos, other.isConsumoPesos).append(cuota, other.cuota)
				.append(descripcion, other.descripcion).append(fecha, other.fecha)
				.append(importeDolares, other.importeDolares).append(importePesos, other.importePesos).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("descripcion", descripcion).append("cuota", cuota)
				.append("importePesos", importePesos).append("importeDolares", importeDolares).append("fecha", fecha)
				.append("codigoEstablecimiento", codigoEstablecimiento).append("isConsumoPesos", isConsumoPesos)
				.toString();
	}

}