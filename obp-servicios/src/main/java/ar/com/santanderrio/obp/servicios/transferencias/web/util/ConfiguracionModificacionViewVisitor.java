/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.util;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.FrecuenciaTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptoTransferenciaAgendadaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FrecuenciaTransferenciaAgendadaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;

/**
 * Visitor para la configuracion de la modificacion de transferencias agendadas
 * No thread-safe.
 *
 *
 * ConfiguracionModificacionView a TransferenciaAgendadaDetalleView
 *
 * @author B039543
 */
public class ConfiguracionModificacionViewVisitor
		extends AbstractTransferenciaAgendadaDetalleViewVisitor<TransferenciaAgendadaDetalleView> {

	/**
	 * Instantiates a new configuracion modificacion view visitor.
	 *
	 * @param isMobile
	 *            the is mobile
	 */
	public ConfiguracionModificacionViewVisitor(boolean isMobile) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaDetalleViewVisitor#visit(ar.com.santanderrio
	 * .obp.transferencias.entities.agenda.TransferenciaAgendadaRecordatorioDTO)
	 */
	@Override
	public void visit(TransferenciaAgendadaDTO transferenciaAgendadaDTO) {
		super.visit(transferenciaAgendadaDTO);
		this.getView().setFrecuenciaCodigo(transferenciaAgendadaDTO.getFrecuencia().getCodigo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaDetalleViewVisitor#visit(ar.com.santanderrio
	 * .obp.transferencias.entities.agenda.TransferenciaAgendadaRecurrenteDTO)
	 */
	// @Override
	// public void visit(TransferenciaAgendadaRecurrenteDTO
	// transferenciaAgendada) {
	// super.visit(transferenciaAgendada);
	// this.getView().setFrecuenciaCodigo(transferenciaAgendada.getFrecuencia().getCodigo());
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaDetalleViewVisitor#popularVista(ar.com.
	 * santanderrio.obp.transferencias.entities.agenda.TransferenciaAgendadaDTO,
	 * ar.com.santanderrio.obp.transferencias.web.view.
	 * TransferenciaAgendadaDetalleView)
	 */
	@Override
	protected void popularVista(TransferenciaAgendadaDTO transferenciaAgendada,
			TransferenciaAgendadaDetalleView viewResponse) {
		super.popularVista(transferenciaAgendada, viewResponse);

		viewResponse.setConceptos(obtenerConceptos());
		viewResponse.setConceptoCodigo(transferenciaAgendada.getConcepto().getOrdinal());
		viewResponse.setFrecuencias(obtenerFrecuencias());

		if (transferenciaAgendada.getMoneda().equals(DivisaEnum.PESO)) {
			viewResponse.setPesos(true);
		} else {
			viewResponse.setPesos(false);
		}
	}

	/**
	 * Obtener conceptos.
	 *
	 * @return the list
	 */
	private List<ConceptoTransferenciaAgendadaView> obtenerConceptos() {
		List<ConceptoTransferenciaAgendadaView> conceptosView = new ArrayList<ConceptoTransferenciaAgendadaView>();

		ConceptoTransferenciaEnum[] conceptos = ConceptoTransferenciaEnum.values();
		for (ConceptoTransferenciaEnum conceptoTransferenciaEnum : conceptos) {
			ConceptoTransferenciaAgendadaView view = new ConceptoTransferenciaAgendadaView();
			view.setDescripcion(conceptoTransferenciaEnum.getDescripcion());
			view.setId(conceptoTransferenciaEnum.getOrdinal());
			conceptosView.add(view);
		}

		return conceptosView;
	}

	/**
	 * Obtener frecuencias.
	 *
	 * @return the list
	 */
	private List<FrecuenciaTransferenciaAgendadaView> obtenerFrecuencias() {
		List<FrecuenciaTransferenciaAgendadaView> frecuenciasView = new ArrayList<FrecuenciaTransferenciaAgendadaView>();

		FrecuenciaTransferenciaAgendada[] values = FrecuenciaTransferenciaAgendada.values();
		for (FrecuenciaTransferenciaAgendada frecuenciaTransferenciaAgendada : values) {
			if (!frecuenciaTransferenciaAgendada.equals(FrecuenciaTransferenciaAgendada.FRECUENCIA_I)
					&& !frecuenciaTransferenciaAgendada.equals(FrecuenciaTransferenciaAgendada.FRECUENCIA_E)) {
				FrecuenciaTransferenciaAgendadaView frecuenciaView = new FrecuenciaTransferenciaAgendadaView();
				frecuenciaView.setId(frecuenciaTransferenciaAgendada.getCodigo());
				frecuenciaView.setDescripcion(frecuenciaTransferenciaAgendada.getDescripcion());
				frecuenciasView.add(frecuenciaView);
			}
		}
		return frecuenciasView;
	}

}