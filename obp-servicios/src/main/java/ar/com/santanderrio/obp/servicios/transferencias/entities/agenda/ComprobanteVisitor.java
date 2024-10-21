/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

/**
 * The Interface ComprobanteVisitor.
 */
public interface ComprobanteVisitor {

	/**
	 * Visit.
	 *
	 * @param comprobanteStopDebitDTO
	 *            the comprobante stop debit DTO
	 */
	void visit(ComprobanteStopDebitDTO comprobanteStopDebitDTO);

}