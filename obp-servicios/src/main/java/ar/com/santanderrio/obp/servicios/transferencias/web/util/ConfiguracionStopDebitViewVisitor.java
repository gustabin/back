/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.util;

import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConfiguracionStopDebitView;

/**
 * Visitor para detalle de transferencias agendadas No thread-safe.
 *
 * @author B039543
 */
public class ConfiguracionStopDebitViewVisitor
		extends AbstractTransferenciaAgendadaDetalleViewVisitor<ConfiguracionStopDebitView> {

	/**
	 * Instantiates a new configuracion stop debit view visitor.
	 *
	 * @param isMobile
	 *            the is mobile
	 */
	public ConfiguracionStopDebitViewVisitor(boolean isMobile) {
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
	protected ConfiguracionStopDebitView crearVista(TransferenciaAgendadaDTO transferenciaAgendada) {
		return new ConfiguracionStopDebitView();
	}
}