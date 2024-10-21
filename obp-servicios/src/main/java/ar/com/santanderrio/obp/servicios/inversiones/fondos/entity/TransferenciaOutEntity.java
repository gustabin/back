/**
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
public class TransferenciaOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The descripcion moneda. */
	@Field
	private String descripcionMoneda;

	/** The importe bruto. */
	@Field
	private String importeBruto;

	/** The importe neto. */
	@Field
	private String importeNeto;

	/** The cant cuotas partes. */
	@Field
	private String cantCuotasPartes;

	/** The porcent comision. */
	@Field
	private String porcentComision;

	/** The comision value. */
	@Field
	private String comisionValue;

	/** The forma pago. */
	@Field
	private String formaPago;

	/** The estado actual. */
	@Field
	private String estadoActual;

	/** The motivo actual. */
	@Field
	private String motivoActual;

	/** The dias carencia. */
	@Field
	private String diasCarencia;

	/** The porcentaje comis D. */
	@Field
	private String porcentajeComisD;

	/** The estado destino. */
	@Field
	private String estadoDestino;

	/** The motivo destino. */
	@Field
	private String motivoDestino;

	/** The nombre fondo. */
	@Field
	private String nombreFondo;

	/** The descripcion moneda destino. */
	@Field
	private String descripcionMonedaDestino;

	/** The nombre fondo destino. */
	@Field
	private String nombreFondoDestino;

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
				.append(importeBruto).append(importeNeto).append(cantCuotasPartes).append(porcentComision)
				.append(comisionValue).append(formaPago).append(estadoActual).append(motivoActual).append(diasCarencia)
				.append(porcentajeComisD).append(estadoDestino).append(motivoDestino).append(nombreFondo)
				.append(descripcionMonedaDestino).append(nombreFondoDestino).toHashCode();
	}

	/*
	 * (non-Javadoc)
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

		TransferenciaOutEntity other = (TransferenciaOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(headerTrama, other.getHeaderTrama())
				.append(codigoRetornoExtendido, other.getCodigoRetornoExtendido())
				.append(descripcionMoneda, other.getDescripcionMoneda()).append(importeBruto, other.getImporteBruto())
				.append(importeNeto, other.getImporteNeto()).append(cantCuotasPartes, other.getCantCuotasPartes())
				.append(porcentComision, other.getPorcentComision()).append(comisionValue, other.getComisionValue())
				.append(formaPago, other.getFormaPago()).append(estadoActual, other.getEstadoActual())
				.append(motivoActual, other.getMotivoActual()).append(diasCarencia, other.getDiasCarencia())
				.append(porcentajeComisD, other.getPorcentajeComisD()).append(estadoDestino, other.getEstadoDestino())
				.append(motivoDestino, other.getMotivoDestino()).append(nombreFondo, other.getNombreFondo())
				.append(descripcionMonedaDestino, other.getDescripcionMonedaDestino())
				.append(nombreFondoDestino, other.getNombreFondoDestino()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("importeNeto", importeNeto).append("cantCuotasPartes", cantCuotasPartes)
				.append("porcentComision", porcentComision).append("comisionValue", comisionValue)
				.append("formaPago", formaPago).append("estadoActual", estadoActual)
				.append("motivoActual", motivoActual).append("diasCarencia", diasCarencia)
				.append("porcentajeComisD", porcentajeComisD).append("estadoDestino", estadoDestino)
				.append("motivoDestino", motivoDestino).append("nombreFondo", nombreFondo)
				.append("descripcionMonedaDestino", descripcionMonedaDestino)
				.append("nombreFondoDestino", nombreFondoDestino).toString();
	}

	/**
	 * Get the headerTrama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Set the headerTrama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */

	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Get the codigoRetornoExtendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Set the CodigoRetornoExtendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
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
	 * Gets the importe bruto.
	 *
	 * @return the importe bruto
	 */
	public String getImporteBruto() {
		return importeBruto;
	}

	/**
	 * Sets the importe bruto.
	 *
	 * @param importeBruto
	 *            the new importe bruto
	 */
	public void setImporteBruto(String importeBruto) {
		this.importeBruto = importeBruto;
	}

	/**
	 * Gets the importe neto.
	 *
	 * @return the importe neto
	 */
	public String getImporteNeto() {
		return importeNeto;
	}

	/**
	 * Sets the importe neto.
	 *
	 * @param importeNeto
	 *            the new importe neto
	 */
	public void setImporteNeto(String importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * Gets the cant cuotas partes.
	 *
	 * @return the cant cuotas partes
	 */
	public String getCantCuotasPartes() {
		return cantCuotasPartes;
	}

	/**
	 * Sets the cant cuotas partes.
	 *
	 * @param cantCuotasPartes
	 *            the new cant cuotas partes
	 */
	public void setCantCuotasPartes(String cantCuotasPartes) {
		this.cantCuotasPartes = cantCuotasPartes;
	}

	/**
	 * Gets the porcent comision.
	 *
	 * @return the porcent comision
	 */
	public String getPorcentComision() {
		return porcentComision;
	}

	/**
	 * Sets the porcent comision.
	 *
	 * @param porcentComision
	 *            the new porcent comision
	 */
	public void setPorcentComision(String porcentComision) {
		this.porcentComision = porcentComision;
	}

	/**
	 * Gets the comision value.
	 *
	 * @return the comision value
	 */
	public String getComisionValue() {
		return comisionValue;
	}

	/**
	 * Sets the comision value.
	 *
	 * @param comisionValue
	 *            the new comision value
	 */
	public void setComisionValue(String comisionValue) {
		this.comisionValue = comisionValue;
	}

	/**
	 * Gets the forma pago.
	 *
	 * @return the forma pago
	 */
	public String getFormaPago() {
		return formaPago;
	}

	/**
	 * Sets the forma pago.
	 *
	 * @param formaPago
	 *            the new forma pago
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
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
	 * Gets the dias carencia.
	 *
	 * @return the dias carencia
	 */
	public String getDiasCarencia() {
		return diasCarencia;
	}

	/**
	 * Sets the dias carencia.
	 *
	 * @param diasCarencia
	 *            the new dias carencia
	 */
	public void setDiasCarencia(String diasCarencia) {
		this.diasCarencia = diasCarencia;
	}

	/**
	 * Gets the porcentaje comis D.
	 *
	 * @return the porcentaje comis D
	 */
	public String getPorcentajeComisD() {
		return porcentajeComisD;
	}

	/**
	 * Sets the porcentaje comis D.
	 *
	 * @param porcentajeComisD
	 *            the new porcentaje comis D
	 */
	public void setPorcentajeComisD(String porcentajeComisD) {
		this.porcentajeComisD = porcentajeComisD;
	}

	/**
	 * Gets the estado destino.
	 *
	 * @return the estado destino
	 */
	public String getEstadoDestino() {
		return estadoDestino;
	}

	/**
	 * Sets the estado destino.
	 *
	 * @param estadoDestino
	 *            the new estado destino
	 */
	public void setEstadoDestino(String estadoDestino) {
		this.estadoDestino = estadoDestino;
	}

	/**
	 * Gets the motivo destino.
	 *
	 * @return the motivo destino
	 */
	public String getMotivoDestino() {
		return motivoDestino;
	}

	/**
	 * Sets the motivo destino.
	 *
	 * @param motivoDestino
	 *            the new motivo destino
	 */
	public void setMotivoDestino(String motivoDestino) {
		this.motivoDestino = motivoDestino;
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
	 * Gets the descripcion moneda destino.
	 *
	 * @return the descripcion moneda destino
	 */
	public String getDescripcionMonedaDestino() {
		return descripcionMonedaDestino;
	}

	/**
	 * Sets the descripcion moneda destino.
	 *
	 * @param descripcionMonedaDestino
	 *            the new descripcion moneda destino
	 */
	public void setDescripcionMonedaDestino(String descripcionMonedaDestino) {
		this.descripcionMonedaDestino = descripcionMonedaDestino;
	}

	/**
	 * Gets the nombre fondo destino.
	 *
	 * @return the nombre fondo destino
	 */
	public String getNombreFondoDestino() {
		return nombreFondoDestino;
	}

	/**
	 * Sets the nombre fondo destino.
	 *
	 * @param nombreFondoDestino
	 *            the new nombre fondo destino
	 */
	public void setNombreFondoDestino(String nombreFondoDestino) {
		this.nombreFondoDestino = nombreFondoDestino;
	}

}
