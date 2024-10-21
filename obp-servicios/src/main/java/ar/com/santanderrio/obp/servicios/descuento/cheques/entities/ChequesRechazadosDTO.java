/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class ChequesRechazadosDTO.
 */
public class ChequesRechazadosDTO {

	/** The numero cheque. */
	private String numeroCheque;
	
	/** The firmante. */
	private String firmante;
	
	/** The fecha de pago. */
	private Date fechaDePago;
	
	/** The importe. */
	private BigDecimal importe;
	
	/** The dni. */
	private String dni;
	
	/** The tipo. */
	private String tipo;

	/**
	 * Gets the numero cheque.
	 *
	 * @return the numero cheque
	 */
	public String getNumeroCheque() {
		return numeroCheque;
	}

	/**
	 * Sets the numero cheque.
	 *
	 * @param numeroCheque
	 *            the new numero cheque
	 */
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	/**
	 * Gets the firmante.
	 *
	 * @return the firmante
	 */
	public String getFirmante() {
		return firmante;
	}

	/**
	 * Sets the firmante.
	 *
	 * @param firmante
	 *            the new firmante
	 */
	public void setFirmante(String firmante) {
		this.firmante = firmante;
	}

	/**
	 * Gets the fecha de pago.
	 *
	 * @return the fecha de pago
	 */
	public Date getFechaDePago() {
		return fechaDePago;
	}

	/**
	 * Sets the fecha de pago.
	 *
	 * @param fechaDePago
	 *            the new fecha de pago
	 */
	public void setFechaDePago(Date fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
