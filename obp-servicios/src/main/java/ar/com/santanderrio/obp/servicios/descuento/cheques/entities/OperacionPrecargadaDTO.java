/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class OperacionPrecargadaDTO.
 */
public class OperacionPrecargadaDTO {

	/** The fecha. */
	private Date fecha;
	
	/** The numero operacion. */
	private String numeroOperacion;
	
	/** The cant cheques. */
	private int cantCheques;
	
	/** The cheques rechazados. */
	private int chequesRechazados;
	
	/** The importe bruto. */
	private BigDecimal importeBruto;
	
	/** The importe acreditado. */
	private BigDecimal importeAcreditado;
	
	/** The estado. */
	private String estado;
	
	/** The color. */
	private String color;

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color
	 *            the new color
	 */
	public void setColor(String color) {
		this.color = color;
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
	 * Gets the numero operacion.
	 *
	 * @return the numero operacion
	 */
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * Sets the numero operacion.
	 *
	 * @param numeroOperacion
	 *            the new numero operacion
	 */
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/**
	 * Gets the cant cheques.
	 *
	 * @return the cant cheques
	 */
	public int getCantCheques() {
		return cantCheques;
	}

	/**
	 * Sets the cant cheques.
	 *
	 * @param cantCheques
	 *            the new cant cheques
	 */
	public void setCantCheques(int cantCheques) {
		this.cantCheques = cantCheques;
	}

	/**
	 * Gets the cheques rechazados.
	 *
	 * @return the cheques rechazados
	 */
	public int getChequesRechazados() {
		return chequesRechazados;
	}

	/**
	 * Sets the cheques rechazados.
	 *
	 * @param chequesRechazados
	 *            the new cheques rechazados
	 */
	public void setChequesRechazados(int chequesRechazados) {
		this.chequesRechazados = chequesRechazados;
	}

	/**
	 * Gets the importe bruto.
	 *
	 * @return the importe bruto
	 */
	public BigDecimal getImporteBruto() {
		return importeBruto;
	}

	/**
	 * Sets the importe bruto.
	 *
	 * @param importeBruto
	 *            the new importe bruto
	 */
	public void setImporteBruto(BigDecimal importeBruto) {
		this.importeBruto = importeBruto;
	}

	/**
	 * Gets the importe acreditado.
	 *
	 * @return the importe acreditado
	 */
	public BigDecimal getImporteAcreditado() {
		return importeAcreditado;
	}

	/**
	 * Sets the importe acreditado.
	 *
	 * @param importeAcreditado
	 *            the new importe acreditado
	 */
	public void setImporteAcreditado(BigDecimal importeAcreditado) {
		this.importeAcreditado = importeAcreditado;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
