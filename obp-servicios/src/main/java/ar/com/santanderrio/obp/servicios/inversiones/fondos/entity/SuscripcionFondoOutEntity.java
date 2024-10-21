/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * Objeto utilizado para retornar del DAO.
 * 
 * @author
 *
 */
@Record
public class SuscripcionFondoOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The descripcion moneda. */
	@Field
	private String descripcionMoneda;

	/** The numero certificado. */
	@Field
	private Long numeroCertificado;

	/** The importe neto. */
	@Field
	private Long importeNeto;

	/** The porcentaje comision. */
	@Field
	private int porcentajeComision;

	/** The valor comision. */
	@Field
	private Long valorComision;

	/** The estado actual. */
	@Field
	private String estadoActual;

	/** The motivo actual. */
	@Field
	private String motivoActual;

	/** The cotizacion cambio. */
	@Field
	private Long cotizacionCambio;

	/** The dias carencia. */
	@Field
	private int diasCarencia;

	/** The nombre fondo. */
	@Field
	private String nombreFondo;

	/** The importe moneda fondo. */
	@Field
	private Long importeMonedaFondo;

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
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
		return new HashCodeBuilder().append(headerTrama).append(codigoRetornoExtendido).append(descripcionMoneda)
				.append(numeroCertificado).append(importeNeto).append(porcentajeComision).append(valorComision)
				.append(estadoActual).append(motivoActual).append(cotizacionCambio).append(diasCarencia)
				.append(nombreFondo).append(importeMonedaFondo).toHashCode();
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

		SuscripcionFondoOutEntity other = (SuscripcionFondoOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(headerTrama, other.headerTrama).append(codigoRetornoExtendido, other.codigoRetornoExtendido)
				.append(descripcionMoneda, other.descripcionMoneda).append(numeroCertificado, other.numeroCertificado)
				.append(importeNeto, other.importeNeto).append(porcentajeComision, other.porcentajeComision)
				.append(valorComision, other.valorComision).append(estadoActual, other.estadoActual)
				.append(motivoActual, other.motivoActual).append(cotizacionCambio, other.cotizacionCambio)
				.append(diasCarencia, other.diasCarencia).append(nombreFondo, other.nombreFondo)
				.append(importeMonedaFondo, other.importeMonedaFondo).isEquals();
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
		return new ToStringBuilder(this).append("HeaderTrama", headerTrama)
				.append("CodigoRetornoExtendido", codigoRetornoExtendido).append("DescripcionMoneda", descripcionMoneda)
				.append("NumeroCertificado", numeroCertificado).append("ImporteNeto", importeNeto)
				.append("PorcentajeComision", porcentajeComision).append("ValorComision", valorComision)
				.append("EstadoActual", estadoActual).append("MotivoActual", motivoActual)
				.append("CotizacionCambio", cotizacionCambio).append("DiasCarencia", diasCarencia)
				.append("NombreFondo", nombreFondo).append("ImporteMonedaFondo", importeMonedaFondo).toString();
	}

	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the descripcion moneda.
	 *
	 * @return the descripcion moneda
	 */
	public String getDescripcionMoneda() {
		return descripcionMoneda;
	}

	/**
	 * Sets the descripcion moneda.
	 *
	 * @param descripcionMoneda
	 *            the new descripcion moneda
	 */
	public void setDescripcionMoneda(String descripcionMoneda) {
		this.descripcionMoneda = descripcionMoneda;
	}

	/**
	 * Gets the numero certificado.
	 *
	 * @return the numero certificado
	 */
	public Long getNumeroCertificado() {
		return numeroCertificado;
	}

	/**
	 * Sets the numero certificado.
	 *
	 * @param numeroCertificado
	 *            the new numero certificado
	 */
	public void setNumeroCertificado(Long numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	/**
	 * Gets the importe neto.
	 *
	 * @return the importe neto
	 */
	public Long getImporteNeto() {
		return importeNeto;
	}

	/**
	 * Sets the importe neto.
	 *
	 * @param importeNeto
	 *            the new importe neto
	 */
	public void setImporteNeto(Long importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * Gets the porcentaje comision.
	 *
	 * @return the porcentaje comision
	 */
	public int getPorcentajeComision() {
		return porcentajeComision;
	}

	/**
	 * Sets the porcentaje comision.
	 *
	 * @param porcentajeComision
	 *            the new porcentaje comision
	 */
	public void setPorcentajeComision(int porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	/**
	 * Gets the valor comision.
	 *
	 * @return the valor comision
	 */
	public Long getValorComision() {
		return valorComision;
	}

	/**
	 * Sets the valor comision.
	 *
	 * @param valorComision
	 *            the new valor comision
	 */
	public void setValorComision(Long valorComision) {
		this.valorComision = valorComision;
	}

	/**
	 * Gets the estado actual.
	 *
	 * @return the estado actual
	 */
	public String getEstadoActual() {
		return estadoActual;
	}

	/**
	 * Sets the estado actual.
	 *
	 * @param estadoActual
	 *            the new estado actual
	 */
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}

	/**
	 * Gets the motivo actual.
	 *
	 * @return the motivo actual
	 */
	public String getMotivoActual() {
		return motivoActual;
	}

	/**
	 * Sets the motivo actual.
	 *
	 * @param motivoActual
	 *            the new motivo actual
	 */
	public void setMotivoActual(String motivoActual) {
		this.motivoActual = motivoActual;
	}

	/**
	 * Gets the cotizacion cambio.
	 *
	 * @return the cotizacion cambio
	 */
	public Long getCotizacionCambio() {
		return cotizacionCambio;
	}

	/**
	 * Sets the cotizacion cambio.
	 *
	 * @param cotizacionCambio
	 *            the new cotizacion cambio
	 */
	public void setCotizacionCambio(Long cotizacionCambio) {
		this.cotizacionCambio = cotizacionCambio;
	}

	/**
	 * Gets the dias carencia.
	 *
	 * @return the dias carencia
	 */
	public int getDiasCarencia() {
		return diasCarencia;
	}

	/**
	 * Sets the dias carencia.
	 *
	 * @param diasCarencia
	 *            the new dias carencia
	 */
	public void setDiasCarencia(int diasCarencia) {
		this.diasCarencia = diasCarencia;
	}

	/**
	 * Gets the nombre fondo.
	 *
	 * @return the nombre fondo
	 */
	public String getNombreFondo() {
		return nombreFondo;
	}

	/**
	 * Sets the nombre fondo.
	 *
	 * @param nombreFondo
	 *            the new nombre fondo
	 */
	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

	/**
	 * Gets the importe moneda fondo.
	 *
	 * @return the importe moneda fondo
	 */
	public Long getImporteMonedaFondo() {
		return importeMonedaFondo;
	}

	/**
	 * Sets the importe moneda fondo.
	 *
	 * @param importeMonedaFondo
	 *            the new importe moneda fondo
	 */
	public void setImporteMonedaFondo(Long importeMonedaFondo) {
		this.importeMonedaFondo = importeMonedaFondo;
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

}
