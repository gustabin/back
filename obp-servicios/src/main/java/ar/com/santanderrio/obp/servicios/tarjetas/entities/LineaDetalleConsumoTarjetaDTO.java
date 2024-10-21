/**
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.tarjetas.util.TipoConsumoTarjeta;

/**
 * The Class LineaDetalleConsumoTarjetaDTO.
 *
 */
public class LineaDetalleConsumoTarjetaDTO extends LineaDetalleConsumoTarjetaGeneral implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cuotas canceladas. */
	private Integer cuotasCanceladas;

	/** The cuotas totales. */
	private Integer cuotasTotales;

	/** The importe pesos. */
	private BigDecimal importePesos;

	/** The importe dolares. */
	private BigDecimal importeDolares;

	/** The fecha. */
	private Date fecha;

	/** The tipo consumo. */
	private TipoConsumoTarjeta tipoConsumo;

	/** The numero comprobante. */
	private String nroComprobante;

	/** The es pendiente confirmacion. */
	private Boolean esPendienteConfirmacion;
	
	/** The fecha excel. */
	private String fechaExcel;
	

	/**
	 * Gets the tipo consumo.
	 *
	 * @return the tipoConsumo
	 */
	public TipoConsumoTarjeta getTipoConsumo() {
		return tipoConsumo;
	}

	/**
	 * Sets the tipo consumo.
	 *
	 * @param tipoConsumo
	 *            the tipoConsumo to set
	 */
	public void setTipoConsumo(TipoConsumoTarjeta tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
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
	 *            the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the importe pesos.
	 *
	 * @return the importe pesos
	 */
	public BigDecimal getImportePesos() {
		return importePesos;
	}

	/**
	 * Sets the importe pesos.
	 *
	 * @param importePesos
	 *            the new importe pesos
	 */
	public void setImportePesos(BigDecimal importePesos) {
		this.importePesos = importePesos;
	}

	/**
	 * Gets the importe dolares.
	 *
	 * @return the importe dolares
	 */
	public BigDecimal getImporteDolares() {
		return importeDolares;
	}

	/**
	 * Sets the importe dolares.
	 *
	 * @param importeDolares
	 *            the new importe dolares
	 */
	public void setImporteDolares(BigDecimal importeDolares) {
		this.importeDolares = importeDolares;
	}

	/**
	 * Gets the cuotas canceladas.
	 *
	 * @return the cuotasCanceladas
	 */
	public Integer getCuotasCanceladas() {
		return cuotasCanceladas;
	}

	/**
	 * Sets the cuotas canceladas.
	 *
	 * @param cuotasCanceladas
	 *            the cuotasCanceladas to set
	 */
	public void setCuotasCanceladas(Integer cuotasCanceladas) {
		this.cuotasCanceladas = cuotasCanceladas;
	}

	/**
	 * Gets the cuotas totales.
	 *
	 * @return the cuotasTotales
	 */
	public Integer getCuotasTotales() {
		return cuotasTotales;
	}

	/**
	 * Sets the cuotas totales.
	 *
	 * @param cuotasTotales
	 *            the cuotasTotales to set
	 */
	public void setCuotasTotales(Integer cuotasTotales) {
		this.cuotasTotales = cuotasTotales;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the es pendiente confirmacion.
	 *
	 * @return the es pendiente confirmacion
	 */
	public Boolean getEsPendienteConfirmacion() {
		return esPendienteConfirmacion;
	}

	/**
	 * Sets the es pendiente confirmacion.
	 *
	 * @param esPendienteConfirmacion
	 *            the new es pendiente confirmacion
	 */
	public void setEsPendienteConfirmacion(Boolean esPendienteConfirmacion) {
		this.esPendienteConfirmacion = esPendienteConfirmacion;
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
	 * HashCode.
	 * 
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hashFiller(hcb, true);
		hcb.append(cuotasCanceladas);
		hcb.append(cuotasTotales);
		hcb.append(getDescripcion());
		hcb.append(fecha);
		hcb.append(importeDolares);
		hcb.append(importePesos);
		hcb.append(esPendienteConfirmacion);
		hashFiller(hcb, false);
		return hcb.hashCode();
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
		LineaDetalleConsumoTarjetaDTO other = (LineaDetalleConsumoTarjetaDTO) obj;

		EqualsBuilder eb = new EqualsBuilder();
		equalsFiller(eb, true, other);
		eb.append(cuotasCanceladas, other.getCuotasCanceladas());
		eb.append(cuotasTotales, other.getCuotasTotales());
		eb.append(getDescripcion(), other.getDescripcion());
		eb.append(fecha, other.getFecha());
		eb.append(importeDolares, other.getImporteDolares());
		eb.append(importePesos, other.getImportePesos());
		eb.append(esPendienteConfirmacion, other.getEsPendienteConfirmacion());
		equalsFiller(eb, false, other);
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
		return new ToStringBuilder(this).append("descripcion", getDescripcion()).append("cuota", getCuota())
				.append("tieneCuota", getTieneCuota()).append("cuotasCanceladas", cuotasCanceladas)
				.append("cuotasTotales", cuotasTotales).append("importePesos", importePesos)
				.append("importeDolares", importeDolares).append("fecha", fecha)
				.append("codigoEstablecimiento", getCodigoEstablecimiento()).append("nroReferencia", getNroReferencia())
				.append("nroTarjeta", getNroTarjeta()).append("consumoPesos", getConsumoPesos())
				.append("consumoDolares", getConsumoDolares())
				.append("esPendienteConfirmacion", getEsPendienteConfirmacion()).toString();
	}

}
