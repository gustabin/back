/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.math.BigDecimal;

/**
 * The Class ChequesAceptadosDTO.
 */
public class ChequesAceptadosDTO {
	
	/** The numero cheque. */
	private String numeroCheque;
	
	/** The banco. */
	private String banco;
	
	/** The dias A adelantar. */
	private String diasAAdelantar;
	
	/** The importe total. */
	private BigDecimal importeTotal;
	
	/** The importe impuestos. */
	private BigDecimal importeImpuestos;
	
	/** The importe intereses. */
	private BigDecimal importeIntereses;
	
	/** The importe A acreditar. */
	private BigDecimal importeAAcreditar;
	
	/** The dni. */
	private String dni;
	
	/** The tipo. */
	private String tipo;
	
	/** The fecha pago. */
	private String fechaPago;

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
	public String getDiasAAdelantar() {
		return diasAAdelantar;
	}

	/**
	 * Sets the dias A adelantar.
	 *
	 * @param diasAAdelantar
	 *            the new dias A adelantar
	 */
	public void setDiasAAdelantar(String diasAAdelantar) {
		this.diasAAdelantar = diasAAdelantar;
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
	
	

}
