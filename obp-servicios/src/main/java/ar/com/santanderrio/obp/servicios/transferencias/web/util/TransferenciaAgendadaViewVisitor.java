/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.util;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaView;

/**
 * Visitor para transferencias agendadas No thread-safe.
 *
 * @author B039543
 */
public class TransferenciaAgendadaViewVisitor
		extends AbstractTransferenciaAgendadaViewVisitor<TransferenciaAgendadaView> {

	/** The cantidad recordatorios. */
	private int cantidadRecordatorios;

	/** The cantidad automaticas. */
	private int cantidadAutomaticas;

	/**
	 * Instantiates a new transferencia agendada view visitor.
	 *
	 * @param isMobile
	 *            the is mobile
	 */
	public TransferenciaAgendadaViewVisitor(boolean isMobile) {
		super(isMobile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.util.
	 * AbstractTransferenciaAgendadaViewVisitor#visit(ar.com.santanderrio.obp.
	 * servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO)
	 */
	@Override
	public void visit(TransferenciaAgendadaDTO transferenciaAgendadaDTO) {
		super.visit(transferenciaAgendadaDTO);
		if (transferenciaAgendadaDTO.getTipoTransferenciaAgendada().equals(TipoTransferenciaAgendada.RECORDATORIO)) {
			this.getView().setTipo(super.obtenerTipoTransferenciaRecordatorio());
			this.getView().setIsRecordatorio(true);
			cantidadRecordatorios++;
		} else if (transferenciaAgendadaDTO.getTipoTransferenciaAgendada()
				.equals(TipoTransferenciaAgendada.PROGRAMADA)) {
			this.getView().setTipo(super.obtenerTipoTransferenciaAutomatica());
			this.getView().setIsProgramada(true);
			cantidadAutomaticas++;
		} else if (transferenciaAgendadaDTO.getTipoTransferenciaAgendada()
				.equals(TipoTransferenciaAgendada.RECURRENTE)) {
			this.getView().setTipo(super.obtenerTipoTransferenciaAutomatica());
			this.getView().setIsRecurrente(true);
			cantidadAutomaticas++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaViewVisitor#crearVista(ar.com.santanderrio.
	 * obp.transferencias.entities.agenda.TransferenciaAgendadaDTO)
	 */
	@Override
	protected TransferenciaAgendadaView crearVista(TransferenciaAgendadaDTO transferenciaAgendada) {
		return new TransferenciaAgendadaView();
	}

	/**
	 * Gets the cantidad recordatorios.
	 *
	 * @return the cantidadRecordatorios
	 */
	public int getCantidadRecordatorios() {
		return cantidadRecordatorios;
	}

	/**
	 * Gets the cantidad automaticas.
	 *
	 * @return the cantidadAutomaticas
	 */
	public int getCantidadAutomaticas() {
		return cantidadAutomaticas;
	}

}
