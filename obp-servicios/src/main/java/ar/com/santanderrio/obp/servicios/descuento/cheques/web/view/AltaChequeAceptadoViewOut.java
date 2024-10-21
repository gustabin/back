/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequeSimuladoDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAceptadosDTO;

/**
 * The Class AltaChequeAceptadoViewOut.
 */
public class AltaChequeAceptadoViewOut {

	/** The numero cheque. */
	private String numeroCheque;

	/** The banco. */
	private String banco;

	/** The dias A adelantar. */
	private String diasAAdelantar;

	/** The fecha pago. */
	private String fechaPago;

	/** The importe total. */
	private String importeTotal;

	/** The importe cheque. */
	private String importeCheque;

	/** The importe impuestos. */
	private String importeImpuestos;

	/** The importe intereses. */
	private String importeIntereses;

	/** The importe neto. */
	private String importeNeto;

	/** The importe A acreditar. */
	private String importeAAcreditar;

	/**
	 * Instantiates a new alta cheque aceptado view out.
	 *
	 * @param chequesAceptados
	 *            the cheques aceptados
	 * @param isSimulado
	 *            the is simulado
	 */
	public AltaChequeAceptadoViewOut(ChequeSimuladoDTO chequesAceptados, boolean isSimulado) {
		numeroCheque = chequesAceptados.getNumeroCheque();
		banco = chequesAceptados.getBanco();
		importeImpuestos = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteImpuestos());
		importeIntereses = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteIntereses());
		fechaPago = ISBANStringUtils.formatearFecha(chequesAceptados.getFechaDePago());
		if (!isSimulado) {
			if (chequesAceptados.getImporteTotal() != null && chequesAceptados.getImporteAAcreditar() != null) {
				importeTotal = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteTotal());
				importeAAcreditar = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteAAcreditar());
			} else {
				importeCheque = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteCheque());
				importeNeto = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteNeto());
			}
		} else {
			importeTotal = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteCheque());
			importeAAcreditar = ISBANStringUtils.formatearSaldo(chequesAceptados.getImporteNeto());
		}
	}

	/**
	 * Instantiates a new alta cheque aceptado view out.
	 *
	 * @param chequeAceptado
	 *            the cheque aceptado
	 */
	public AltaChequeAceptadoViewOut(ChequesAceptadosDTO chequeAceptado) {
		numeroCheque = chequeAceptado.getNumeroCheque();
		banco = chequeAceptado.getBanco();
		importeImpuestos = ISBANStringUtils.formatearSaldo(chequeAceptado.getImporteImpuestos());
		importeIntereses = ISBANStringUtils.formatearSaldo(chequeAceptado.getImporteIntereses());
		diasAAdelantar = chequeAceptado.getDiasAAdelantar();
		importeCheque = ISBANStringUtils.formatearSaldo(chequeAceptado.getImporteTotal());
		importeNeto = ISBANStringUtils.formatearSaldo(chequeAceptado.getImporteAAcreditar());
	}

	/**
	 * Instantiates a new alta cheque aceptado view out.
	 */
	public AltaChequeAceptadoViewOut() {
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
	 * Gets the importe cheque.
	 *
	 * @return the importe cheque
	 */
	public String getImporteCheque() {
		return importeCheque;
	}

	/**
	 * Sets the importe cheque.
	 *
	 * @param importeCheque
	 *            the new importe cheque
	 */
	public void setImporteCheque(String importeCheque) {
		this.importeCheque = importeCheque;
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
