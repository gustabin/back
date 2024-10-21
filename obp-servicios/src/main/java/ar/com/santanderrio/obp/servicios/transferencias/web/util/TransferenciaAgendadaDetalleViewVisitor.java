/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.util;

import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;

/**
 * Visitor para detalle de transferencias agendadas No thread-safe.
 *
 * @author B039543
 */
public class TransferenciaAgendadaDetalleViewVisitor
		extends AbstractTransferenciaAgendadaDetalleViewVisitor<TransferenciaAgendadaDetalleView> {

	/**
	 * Instantiates a new transferencia agendada detalle view visitor.
	 *
	 * @param isMobile
	 *            the is mobile
	 */
	public TransferenciaAgendadaDetalleViewVisitor(boolean isMobile) {
		super(isMobile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.util.
	 * AbstractTransferenciaAgendadaViewVisitor#crearVista(ar.com.santanderrio.
	 * obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO)
	 */
	@Override
	protected TransferenciaAgendadaDetalleView crearVista(TransferenciaAgendadaDTO transferenciaAgendada) {
		return new TransferenciaAgendadaDetalleView();
	}

}
