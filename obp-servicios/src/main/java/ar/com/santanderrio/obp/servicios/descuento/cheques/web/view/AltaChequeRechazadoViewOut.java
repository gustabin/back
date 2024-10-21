/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequeSimuladoDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesRechazadosDTO;

/**
 * The Class AltaChequeRechazadoViewOut.
 */
public class AltaChequeRechazadoViewOut {

	/** The numero cheque. */
	private String numeroCheque;

	/** The firmante. */
	private String firmante;

	/** The fecha pago. */
	private String fechaPago;

	/** The importe. */
	private String importe;

	/**
	 * Instantiates a new alta cheque rechazado view out.
	 *
	 * @param chequesRechazados
	 *            the cheques rechazados
	 */
	public AltaChequeRechazadoViewOut(ChequeSimuladoDTO chequesRechazados) {
		numeroCheque = chequesRechazados.getNumeroCheque();
		firmante = chequesRechazados.getFirmante();
		fechaPago = ISBANStringUtils.formatearFecha(chequesRechazados.getFechaDePago());
		importe = ISBANStringUtils.formatearSaldo(chequesRechazados.getImporteCheque());
	}

	/**
	 * Instantiates a new alta cheque rechazado view out.
	 */
	public AltaChequeRechazadoViewOut() {
		super();
	}

	/**
	 * Instantiates a new alta cheque rechazado view out.
	 *
	 * @param chequesRechazados
	 *            the cheques rechazados
	 */
	public AltaChequeRechazadoViewOut(ChequesRechazadosDTO chequesRechazados) {
		numeroCheque = chequesRechazados.getNumeroCheque();
		firmante = chequesRechazados.getFirmante();
		fechaPago = ISBANStringUtils.formatearFecha(chequesRechazados.getFechaDePago());
		importe = ISBANStringUtils.formatearSaldo(chequesRechazados.getImporte());
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
	 * Gets the fecha pago.
	 *
	 * @return the fecha pago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * Sets the fecha pago.
	 *
	 * @param fechaPago
	 *            the new fecha pago
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
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
