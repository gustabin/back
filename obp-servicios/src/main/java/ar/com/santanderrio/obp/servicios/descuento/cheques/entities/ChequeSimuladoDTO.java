/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class ChequeSimuladoDTO.
 */
public class ChequeSimuladoDTO {

	/** The numero cheque. */
	private String numeroCheque;
	
	/** The banco. */
	private String banco;
	
	/** The dias A adelantar. */
	private int diasAAdelantar;
	
	/** The importe cheque. */
	private BigDecimal importeCheque;
	
	/** The importe impuestos. */
	private BigDecimal importeImpuestos;
	
	/** The importe intereses. */
	private BigDecimal importeIntereses;
	
	/** The importe neto. */
	private BigDecimal importeNeto;
	
	/** The importe total. */
	private BigDecimal importeTotal;
	
	/** The importe A acreditar. */
	private BigDecimal importeAAcreditar;
	
	/** The firmante. */
	private String firmante;
	
	/** The fecha de pago. */
	private Date fechaDePago;

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
	 * Gets the banco.
	 *
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * Sets the banco.
	 *
	 * @param banco
	 *            the new banco
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * Gets the dias A adelantar.
	 *
	 * @return the dias A adelantar
	 */
	public int getDiasAAdelantar() {
		return diasAAdelantar;
	}

	/**
	 * Sets the dias A adelantar.
	 *
	 * @param diasAAdelantar
	 *            the new dias A adelantar
	 */
	public void setDiasAAdelantar(int diasAAdelantar) {
		this.diasAAdelantar = diasAAdelantar;
	}

	/**
	 * Gets the importe cheque.
	 *
	 * @return the importe cheque
	 */
	public BigDecimal getImporteCheque() {
		return importeCheque;
	}

	/**
	 * Sets the importe cheque.
	 *
	 * @param importeCheque
	 *            the new importe cheque
	 */
	public void setImporteCheque(BigDecimal importeCheque) {
		this.importeCheque = importeCheque;
	}

	/**
	 * Gets the importe impuestos.
	 *
	 * @return the importe impuestos
	 */
	public BigDecimal getImporteImpuestos() {
		return importeImpuestos;
	}

	/**
	 * Sets the importe impuestos.
	 *
	 * @param importeImpuestos
	 *            the new importe impuestos
	 */
	public void setImporteImpuestos(BigDecimal importeImpuestos) {
		this.importeImpuestos = importeImpuestos;
	}

	/**
	 * Gets the importe intereses.
	 *
	 * @return the importe intereses
	 */
	public BigDecimal getImporteIntereses() {
		return importeIntereses;
	}

	/**
	 * Sets the importe intereses.
	 *
	 * @param importeIntereses
	 *            the new importe intereses
	 */
	public void setImporteIntereses(BigDecimal importeIntereses) {
		this.importeIntereses = importeIntereses;
	}

	/**
	 * Gets the importe neto.
	 *
	 * @return the importe neto
	 */
	public BigDecimal getImporteNeto() {
		return importeNeto;
	}

	/**
	 * Sets the importe neto.
	 *
	 * @param importeNeto
	 *            the new importe neto
	 */
	public void setImporteNeto(BigDecimal importeNeto) {
		this.importeNeto = importeNeto;
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
	 * Gets the importe total.
	 *
	 * @return the importe total
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Sets the importe total.
	 *
	 * @param importeTotal
	 *            the new importe total
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * Gets the importe A acreditar.
	 *
	 * @return the importe A acreditar
	 */
	public BigDecimal getImporteAAcreditar() {
		return importeAAcreditar;
	}

	/**
	 * Sets the importe A acreditar.
	 *
	 * @param importeAAcreditar
	 *            the new importe A acreditar
	 */
	public void setImporteAAcreditar(BigDecimal importeAAcreditar) {
		this.importeAAcreditar = importeAAcreditar;
	}
	
	
	
}
