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
 * The Class RescateFondoOutEntity.
 *
 * @author pablo.d.gargaglione
 */
@Record
public class RescateFondoOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The status resultado. */
	@Field
	private String statusResultado;

	/** The descripcion moneda. */
	@Field
	private String descripcionMoneda;

	/** The estado actual. */
	@Field
	private String estadoActual;

	/** The motivo actual. */
	@Field
	private String motivoActual;

	/** The nombre fondo. */
	@Field
	private String nombreFondo;

	/** The importe rescate neto. */
	@Field
	private String importeRescateNeto;

	/** The importe rescate comision. */
	@Field
	private String importeRescateComision;

	/** The importe rescate bruto. */
	@Field
	private String importeRescateBruto;

	/** The totalCuotasPartes. */
	@Field
	private String totalCuotasPartes;

	/** The montoCambio. */
	@Field
	private String montoCambio;

	/** The CotacaoPact. */
	@Field
	private String cotacaoPact;

	/** The MarcaKu. */
	@Field
	private String marcaKu;

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
		return new HashCodeBuilder().append(headerTrama).append(statusResultado).append(descripcionMoneda)
				.append(estadoActual).append(motivoActual).append(nombreFondo).append(importeRescateNeto)
				.append(importeRescateComision).append(importeRescateBruto).append(totalCuotasPartes)
				.append(montoCambio).append(cotacaoPact).append(marcaKu).toHashCode();
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

		RescateFondoOutEntity other = (RescateFondoOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(headerTrama, other.headerTrama).append(statusResultado, other.statusResultado)
				.append(descripcionMoneda, other.descripcionMoneda).append(estadoActual, other.estadoActual)
				.append(motivoActual, other.motivoActual).append(nombreFondo, other.nombreFondo)
				.append(importeRescateNeto, other.importeRescateNeto)
				.append(importeRescateComision, other.importeRescateComision)
				.append(importeRescateBruto, other.importeRescateBruto)
				.append(totalCuotasPartes, other.totalCuotasPartes).append(montoCambio, other.montoCambio)
				.append(cotacaoPact, other.cotacaoPact).append(marcaKu, other.marcaKu).isEquals();
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
		return new ToStringBuilder(this).append("headerTrama", headerTrama).append("statusResultado", statusResultado)
				.append("descripcionMoneda", descripcionMoneda).append("estadoActual", estadoActual)
				.append("motivoActual", motivoActual).append("nombreFondo", nombreFondo)
				.append("importeRescateNeto", importeRescateNeto)
				.append("importeRescateComision", importeRescateComision)
				.append("importeRescateBruto", importeRescateBruto).append("totalCuotasPartes", totalCuotasPartes)
				.append("montoCambio", montoCambio).append("cotacaoPact", cotacaoPact).append("marcaKu", marcaKu)
				.toString();
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
	 * Gets the status resultado.
	 *
	 * @return the status resultado
	 */
	public String getStatusResultado() {
		return statusResultado;
	}

	/**
	 * Sets the status resultado.
	 *
	 * @param statusResultado
	 *            the new status resultado
	 */
	public void setStatusResultado(String statusResultado) {
		this.statusResultado = statusResultado;
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
	 * Gets the importe rescate neto.
	 *
	 * @return the importe rescate neto
	 */
	public String getImporteRescateNeto() {
		return importeRescateNeto;
	}

	/**
	 * Sets the importe rescate neto.
	 *
	 * @param importeRescateNeto
	 *            the new importe rescate neto
	 */
	public void setImporteRescateNeto(String importeRescateNeto) {
		this.importeRescateNeto = importeRescateNeto;
	}

	/**
	 * Gets the importe rescate comision.
	 *
	 * @return the importe rescate comision
	 */
	public String getImporteRescateComision() {
		return importeRescateComision;
	}

	/**
	 * Sets the importe rescate comision.
	 *
	 * @param importeRescateComision
	 *            the new importe rescate comision
	 */
	public void setImporteRescateComision(String importeRescateComision) {
		this.importeRescateComision = importeRescateComision;
	}

	/**
	 * Gets the importe rescate bruto.
	 *
	 * @return the importe rescate bruto
	 */
	public String getImporteRescateBruto() {
		return importeRescateBruto;
	}

	/**
	 * Sets the importe rescate bruto.
	 *
	 * @param importeRescateBruto
	 *            the new importe rescate bruto
	 */
	public void setImporteRescateBruto(String importeRescateBruto) {
		this.importeRescateBruto = importeRescateBruto;
	}

	/**
	 * Gets the total cuotas partes.
	 *
	 * @return the total cuotas partes
	 */
	public String getTotalCuotasPartes() {
		return totalCuotasPartes;
	}

	/**
	 * Sets the total cuotas partes.
	 *
	 * @param totalCuotasPartes
	 *            the new total cuotas partes
	 */
	public void setTotalCuotasPartes(String totalCuotasPartes) {
		this.totalCuotasPartes = totalCuotasPartes;
	}

	/**
	 * Gets the monto cambio.
	 *
	 * @return the monto cambio
	 */
	public String getMontoCambio() {
		return montoCambio;
	}

	/**
	 * Sets the monto cambio.
	 *
	 * @param montoCambio
	 *            the new monto cambio
	 */
	public void setMontoCambio(String montoCambio) {
		this.montoCambio = montoCambio;
	}

	/**
	 * Gets the cotacao pact.
	 *
	 * @return the cotacao pact
	 */
	public String getCotacaoPact() {
		return cotacaoPact;
	}

	/**
	 * Sets the cotacao pact.
	 *
	 * @param cotacaoPact
	 *            the new cotacao pact
	 */
	public void setCotacaoPact(String cotacaoPact) {
		this.cotacaoPact = cotacaoPact;
	}

	/**
	 * Gets the marca ku.
	 *
	 * @return the marca ku
	 */
	public String getMarcaKu() {
		return marcaKu;
	}

	/**
	 * Sets the marca ku.
	 *
	 * @param marcaKu
	 *            the new marca ku
	 */
	public void setMarcaKu(String marcaKu) {
		this.marcaKu = marcaKu;
	}

}
