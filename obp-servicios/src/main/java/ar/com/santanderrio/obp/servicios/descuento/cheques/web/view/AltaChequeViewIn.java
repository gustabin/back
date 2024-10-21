/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAceptadosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesRechazadosDTO;

/**
 * The Class AltaChequeViewIn.
 */
public class AltaChequeViewIn {
	
	/** The nro cheque. */
	private String nroCheque;
	
	/** The valor. */
	private String valor;
	
	/** The dni. */
	private String dni;
	
	/** The tipo. */
	private String tipo;
	
	/** The fecha pago. */
	private String fechaPago;
	
	/** The datos cheque. */
	private CuitIn datosCheque;

	/**
	 * Instantiates a new alta cheque view in.
	 */
	public AltaChequeViewIn() {
		super();
	}
	
	/**
	 * Instantiates a new alta cheque view in.
	 *
	 * @param aceptados
	 *            the aceptados
	 */
	public AltaChequeViewIn(ChequesAceptadosDTO aceptados) {
		nroCheque = aceptados.getNumeroCheque();
		valor = ISBANStringUtils.formatearSaldo(aceptados.getImporteTotal());
		dni = aceptados.getDni();
		tipo = aceptados.getTipo();
		fechaPago = aceptados.getFechaPago();
		datosCheque = new CuitIn(aceptados.getNumeroCheque());
	}
	
	/**
	 * Instantiates a new alta cheque view in.
	 *
	 * @param rechazados
	 *            the rechazados
	 */
	public AltaChequeViewIn(ChequesRechazadosDTO rechazados) {
		nroCheque = rechazados.getNumeroCheque();
		valor = ISBANStringUtils.formatearSaldo(rechazados.getImporte());
		dni = rechazados.getDni();
		tipo = rechazados.getTipo();
		fechaPago = ISBANStringUtils.formatearFechaIATX(rechazados.getFechaDePago());
		datosCheque = new CuitIn(rechazados.getNumeroCheque());
	}

	/**
	 * Gets the nro cheque.
	 *
	 * @return the nro cheque
	 */
	public String getNroCheque() {
		return nroCheque;
	}

	/**
	 * Sets the nro cheque.
	 *
	 * @param nroCheque
	 *            the new nro cheque
	 */
	public void setNroCheque(String nroCheque) {
		this.nroCheque = nroCheque;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor
	 *            the new valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
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
	 * Gets the datos cheque.
	 *
	 * @return the datos cheque
	 */
	public CuitIn getDatosCheque() {
		return datosCheque;
	}

	/**
	 * Sets the datos cheque.
	 *
	 * @param datosCheque
	 *            the new datos cheque
	 */
	public void setDatosCheque(CuitIn datosCheque) {
		this.datosCheque = datosCheque;
	}

	

}
