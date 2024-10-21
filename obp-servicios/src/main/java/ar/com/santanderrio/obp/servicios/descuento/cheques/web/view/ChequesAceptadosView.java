/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAceptadosDTO;

/**
 * The Class ChequesAceptadosView.
 */
public class ChequesAceptadosView {
	
	/** The numero cheque. */
	private String numeroCheque;
	
	/** The banco. */
	private String banco;
	
	/** The dias A adelantar. */
	private String diasAAdelantar;
	
	/** The importe total. */
	private String importeTotal;
	
	/** The importe impuestos. */
	private String importeImpuestos;
	
	/** The importe intereses. */
	private String importeIntereses;
	
	/** The importe A acreditar. */
	private String importeAAcreditar;

	/**
	 * Instantiates a new cheques aceptados view.
	 *
	 * @param chequesAceptados
	 *            the cheques aceptados
	 */
	public ChequesAceptadosView(ChequesAceptadosDTO chequesAceptados) {
		numeroCheque = chequesAceptados.getNumeroCheque();
		banco = chequesAceptados.getBanco();
		diasAAdelantar = chequesAceptados.getDiasAAdelantar();
		importeTotal = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteTotal());
		importeImpuestos = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteImpuestos());
		importeIntereses = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteIntereses());
		importeAAcreditar = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteAAcreditar());
	}

	/**
	 * Instantiates a new cheques aceptados view.
	 */
	public ChequesAceptadosView() {
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
	public String getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Sets the importe total.
	 *
	 * @param importeTotal
	 *            the new importe total
	 */
	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * Gets the importe impuestos.
	 *
	 * @return the importe impuestos
	 */
	public String getImporteImpuestos() {
		return importeImpuestos;
	}

	/**
	 * Sets the importe impuestos.
	 *
	 * @param importeImpuestos
	 *            the new importe impuestos
	 */
	public void setImporteImpuestos(String importeImpuestos) {
		this.importeImpuestos = importeImpuestos;
	}

	/**
	 * Gets the importe intereses.
	 *
	 * @return the importe intereses
	 */
	public String getImporteIntereses() {
		return importeIntereses;
	}

	/**
	 * Sets the importe intereses.
	 *
	 * @param importeIntereses
	 *            the new importe intereses
	 */
	public void setImporteIntereses(String importeIntereses) {
		this.importeIntereses = importeIntereses;
	}

	/**
	 * Gets the importe A acreditar.
	 *
	 * @return the importe A acreditar
	 */
	public String getImporteAAcreditar() {
		return importeAAcreditar;
	}

	/**
	 * Sets the importe A acreditar.
	 *
	 * @param importeAAcreditar
	 *            the new importe A acreditar
	 */
	public void setImporteAAcreditar(String importeAAcreditar) {
		this.importeAAcreditar = importeAAcreditar;
	}

}
