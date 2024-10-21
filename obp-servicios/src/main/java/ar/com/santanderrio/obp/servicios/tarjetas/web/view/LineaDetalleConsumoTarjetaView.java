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
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;

/**
 * The Class LineaDetalleConsumoTarjetaView.
 *
 * @author sabrina.cis
 */
public class LineaDetalleConsumoTarjetaView {

	/** The descripcion. */
	private String descripcion;

	/** The cuota. */
	private String cuota;

	/** The tiene cuota. */
	private Boolean tieneCuota;

	/** The cuotas canceladas. */
	private String cuotasCanceladas;

	/** The cuotas totales. */
	private String cuotasTotales;

	/** The importe pesos. */
	private String importePesos;

	/** The importe dolares. */
	private String importeDolares;

	/** The fecha. */
	private String fecha;

	/** The fechaMobile. */
	private String fechaMobile;

	/** The codigo establecimiento. */
	private String codigoEstablecimiento;

	/** The nro referencia. */
	private String nroReferencia;

	/** The nro tarjeta. */
	private String nroTarjeta;

	/** The consumo pesos. */
	private Boolean consumoPesos;

	/** The consumo dolares. */
	private Boolean consumoDolares;

	/** The nro comprobante. */
	private String comprobante;

	/** The es pendiente confirmacion. */
	private Boolean esPendienteConfirmacion;

	/**
	 * Instantiates a new linea detalle consumo tarjeta view.
	 *
	 * @param dto
	 *            the dto
	 */
	public LineaDetalleConsumoTarjetaView(LineaDetalleConsumoTarjetaDTO dto) {
		super();
		this.setDescripcion(dto.getDescripcion());
		this.setCuota(dto.getCuota());
		this.setTieneCuota(dto.getTieneCuota());
		this.setCuotasCanceladas(dto.getCuotasCanceladas());
		this.setCuotasTotales(dto.getCuotasTotales());
		this.setImportePesos(dto.getImportePesos());
		this.setImporteDolares(dto.getImporteDolares());
		this.setFecha(dto.getFecha());
		this.setCodigoEstablecimiento(dto.getCodigoEstablecimiento());
		this.setNroReferencia(dto.getNroReferencia());
		this.setNroTarjeta(dto.getNroTarjetaMascara());
		this.setConsumoPesos(dto.getConsumoPesos());
		this.setConsumoDolares(dto.getConsumoDolares());
		this.setComprobante(dto.getNroReferencia());
		this.setFechaMobile(this.getFecha());
		this.setEsPendienteConfirmacion(dto.getEsPendienteConfirmacion());
	}

	/**
	 * Instantiates a new linea detalle consumo tarjeta view.
	 */
	public LineaDetalleConsumoTarjetaView() {
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
		if (fecha != null) {
			try {
				this.fecha = ISBANStringUtils.formatearFechaConAnio(fecha);
			} catch (ParseException e) {
				this.fecha = ISBANStringUtils.inicializarFecha();
			}
		}
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
	 * Gets the tiene cuota.
	 *
	 * @return the tiene cuota
	 */
	public Boolean getTieneCuota() {
		return tieneCuota;
	}

	/**
	 * Sets the tiene cuota.
	 *
	 * @param tieneCuota
	 *            the new tiene cuota
	 */
	public void setTieneCuota(Boolean tieneCuota) {
		this.tieneCuota = tieneCuota;
	}

	/**
	 * Gets the codigo establecimiento.
	 *
	 * @return the codigoEstablecimiento
	 */
	public String getCodigoEstablecimiento() {
		return codigoEstablecimiento;
	}

	/**
	 * Sets the codigo establecimiento.
	 *
	 * @param codigoEstablecimiento
	 *            the codigoEstablecimiento to set
	 */
	public void setCodigoEstablecimiento(String codigoEstablecimiento) {
		this.codigoEstablecimiento = codigoEstablecimiento;
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nroTarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the nroTarjeta to set
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the nro referencia.
	 *
	 * @return the nroReferencia
	 */
	public String getNroReferencia() {
		return nroReferencia;
	}

	/**
	 * Sets the nro referencia.
	 *
	 * @param nroReferencia
	 *            the nroReferencia to set
	 */
	public void setNroReferencia(String nroReferencia) {
		this.nroReferencia = nroReferencia;
	}

	/**
	 * Gets the consumo pesos.
	 *
	 * @return the consumoPesos
	 */
	public Boolean getConsumoPesos() {
		return consumoPesos;
	}

	/**
	 * Sets the consumo pesos.
	 *
	 * @param consumoPesos
	 *            the consumoPesos to set
	 */
	public void setConsumoPesos(Boolean consumoPesos) {
		this.consumoPesos = consumoPesos;
	}

	/**
	 * Gets the consumo dolares.
	 *
	 * @return the consumoDolares
	 */
	public Boolean getConsumoDolares() {
		return consumoDolares;
	}

	/**
	 * Sets the consumo dolares.
	 *
	 * @param consumoDolares
	 *            the consumoDolares to set
	 */
	public void setConsumoDolares(Boolean consumoDolares) {
		this.consumoDolares = consumoDolares;
	}

	/**
	 * Gets the cuotas canceladas.
	 *
	 * @return the cuotasCanceladas
	 */
	public String getCuotasCanceladas() {
		return cuotasCanceladas;
	}

	/**
	 * Sets the cuotas canceladas.
	 *
	 * @param cuotasCanceladas
	 *            the cuotasCanceladas to set
	 */
	public void setCuotasCanceladas(Integer cuotasCanceladas) {
		if (cuotasCanceladas != null) {
			this.cuotasCanceladas = String.valueOf(cuotasCanceladas);
		}
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
	public void setCuotasTotales(Integer cuotasTotales) {
		if (cuotasTotales != null) {
			this.cuotasTotales = String.valueOf(cuotasTotales);
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
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoEstablecimiento);
		hcb.append(consumoDolares);
		hcb.append(consumoPesos);
		hcb.append(cuota);
		hcb.append(cuotasCanceladas);
		hcb.append(cuotasTotales);
		hcb.append(descripcion);
		hcb.append(fecha);
		hcb.append(importeDolares);
		hcb.append(importePesos);
		hcb.append(nroReferencia);
		hcb.append(nroTarjeta);
		hcb.append(tieneCuota);
		hcb.append(fechaMobile);
		return hcb.hashCode();
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
		LineaDetalleConsumoTarjetaView other = (LineaDetalleConsumoTarjetaView) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codigoEstablecimiento, other.getCodigoEstablecimiento());
		eb.append(consumoDolares, other.getConsumoDolares());
		eb.append(consumoPesos, other.getConsumoPesos());
		eb.append(cuota, other.cuota);
		eb.append(cuotasCanceladas, other.getCuotasCanceladas());
		eb.append(cuotasTotales, other.getCuotasTotales());
		eb.append(descripcion, other.getDescripcion());
		eb.append(fecha, other.getFecha());
		eb.append(importeDolares, other.getImporteDolares());
		eb.append(importePesos, other.getImportePesos());
		eb.append(nroReferencia, other.getNroReferencia());
		eb.append(nroTarjeta, other.getNroTarjeta());
		eb.append(tieneCuota, other.getTieneCuota());
		eb.append(fechaMobile, other.getFechaMobile());
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
		return new ToStringBuilder(this).append("descripcion", descripcion).append("cuota", cuota)
				.append("tieneCuota", tieneCuota).append("cuotasCanceladas", cuotasCanceladas)
				.append("cuotasTotales", cuotasTotales).append("importePesos", importePesos)
				.append("importeDolares", importeDolares).append("fecha", fecha)
				.append("codigoEstablecimiento", codigoEstablecimiento).append("nroReferencia", nroReferencia)
				.append("nroTarjeta", nroTarjeta).append("consumoPesos", consumoPesos)
				.append("consumoDolares", consumoDolares).append("fechaMobile", fechaMobile).toString();
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

}