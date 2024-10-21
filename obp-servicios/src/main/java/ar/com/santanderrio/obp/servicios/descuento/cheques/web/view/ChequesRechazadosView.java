/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesRechazadosDTO;

/**
 * The Class ChequesRechazadosView.
 */
public class ChequesRechazadosView {

	/** The numero cheque. */
	private String numeroCheque;

	/** The firmante. */
	private String firmante;

	/** The fecha de pago. */
	private String fechaDePago;

	/** The importe. */
	private String importe;

	/**
	 * Instantiates a new cheques rechazados view.
	 *
	 * @param chequesRechazados
	 *            the cheques rechazados
	 */
	public ChequesRechazadosView(ChequesRechazadosDTO chequesRechazados) {
		numeroCheque = chequesRechazados.getNumeroCheque();
		firmante = chequesRechazados.getFirmante();
		fechaDePago = ISBANStringUtils.formatearFecha(chequesRechazados.getFechaDePago());
		importe = ISBANStringUtils.formatearSaldo(chequesRechazados.getImporte());
	}

	/**
	 * Instantiates a new cheques rechazados view.
	 */
	public ChequesRechazadosView() {
		super();
	}

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
	public String getFechaDePago() {
		return fechaDePago;
	}

	/**
	 * Sets the fecha de pago.
	 *
	 * @param fechaDePago
	 *            the new fecha de pago
	 */
	public void setFechaDePago(String fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

}
