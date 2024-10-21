/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

/**
 * The Interface TransferenciaAgendadaVisitor.
 */
public interface TransferenciaAgendadaVisitor {

	/**
	 * Visit.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 */
	void visit(TransferenciaAgendadaDTO transferenciaAgendada);

}