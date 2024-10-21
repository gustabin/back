/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.BancoDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.DestinatarioDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaVisitor;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaView;

/**
 * The Class AbstractTransferenciaAgendadaViewVisitor.
 *
 * @author B039543
 * @param <E>
 *            the element type
 */
public abstract class AbstractTransferenciaAgendadaViewVisitor<E extends TransferenciaAgendadaView>
		implements TransferenciaAgendadaVisitor {

	/** The Constant FORMATO_FECHA_MOBILE. */
	private static final String FORMATO_FECHA_MOBILE = "dd/MM/yy";

	/** The Constant AUTOMATICA. */
	private static final String AUTOMATICA = "Transferencia automática";

	/** The Constant RECORDATORIO. */
	private static final String RECORDATORIO = "Recordatorio de transferencia";

	/** The Constant AUTOMATICA_MOBILE. */
	private static final String AUTOMATICA_MOBILE = "Automática";

	/** The Constant RECORDATORIO_MOBILE. */
	private static final String RECORDATORIO_MOBILE = "Recordatorio";

	/** The is mobile. */
	private boolean isMobile;

	/** The view. */
	private E view;

	/**
	 * Instantiates a new abstract transferencia agendada view visitor.
	 *
	 * @param isMobile
	 *            the is mobile
	 */
	public AbstractTransferenciaAgendadaViewVisitor(boolean isMobile) {
		this.isMobile = isMobile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.
	 * TransferenciaAgendadaVisitor#visit(ar.com.santanderrio.obp.servicios.
	 * transferencias.entities.agenda.TransferenciaAgendadaDTO)
	 */
	@Override
	public void visit(TransferenciaAgendadaDTO transferenciaAgendada) {
		this.setView(crearVista(transferenciaAgendada));
		this.popularVista(transferenciaAgendada, getView());
		this.getView().setTipo(obtenerTipoTransferenciaAutomatica());
		if (transferenciaAgendada.getFrecuencia() != null) {
			this.getView().setFrecuencia(transferenciaAgendada.getFrecuencia().getDescripcion());
		}
	}

	/**
	 * Crear vista.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @return the e
	 */
	protected abstract E crearVista(TransferenciaAgendadaDTO transferenciaAgendada);

	/**
	 * Popular vista.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @param viewResponse
	 *            the view response
	 */
	protected void popularVista(TransferenciaAgendadaDTO transferenciaAgendada, E viewResponse) {

		if (transferenciaAgendada.getId() != null) {
			viewResponse.setId(transferenciaAgendada.getId().toString());
		}

		DestinatarioDTO destinatario = transferenciaAgendada.getDestinatario();
		if (destinatario != null) {
			BancoDTO destinatarioBanco = destinatario.getBanco();
			if (destinatarioBanco != null) {
				String destinatarioNombre = StringUtils.isEmpty(destinatarioBanco.getNombre()) ? "-"
						: destinatarioBanco.getDetalle();
				viewResponse.setDestinatarioBanco(WordUtils.capitalizeFully(destinatarioNombre).trim());
			}
			viewResponse.setDestinatarioNombre(
					WordUtils.capitalizeFully(transferenciaAgendada.getDestinatario().getNombre()));

			if (null != destinatario.getDescripcion() && !"".equals(destinatario.getDescripcion())) {
				viewResponse.setApodoTransferencia(
						WordUtils.capitalizeFully(transferenciaAgendada.getDestinatario().getDescripcion()));
			} else {
				viewResponse.setApodoTransferencia(
						WordUtils.capitalizeFully(transferenciaAgendada.getDestinatario().getNombre()));
			}
		}

		if (transferenciaAgendada.getImporte() != null) {
			viewResponse.setImporte(ISBANStringUtils.formatearSaldoConSigno(transferenciaAgendada.getImporte()));
		}
		if (transferenciaAgendada.getMoneda() != null) {
			viewResponse.setDivisa(transferenciaAgendada.getMoneda().getSimbolo());
		}
		if (transferenciaAgendada.getFechaEjecucion() != null) {
			if (isMobile) {
				viewResponse.setFecha(ISBANStringUtils.formatearFecha(transferenciaAgendada.getFechaEjecucion(),
						FORMATO_FECHA_MOBILE));
			} else {
				viewResponse.setFecha(ISBANStringUtils.formatearFecha(transferenciaAgendada.getFechaEjecucion()));
			}
		}
		if (!transferenciaAgendada.isHaciaOtroBanco()) {
			viewResponse.setIsRioRio(true);
		}

		if (null != transferenciaAgendada.getDatosOrigen()
				&& null != transferenciaAgendada.getDatosOrigen().getTransferenciaAgendada().getCuentaPropia()
				&& !"".equals(transferenciaAgendada.getDatosOrigen().getTransferenciaAgendada().getCuentaPropia())) {
			viewResponse.setCuentaPropia(
					transferenciaAgendada.getDatosOrigen().getTransferenciaAgendada().getCuentaPropia());
		}

		if (StringUtils.isNotBlank(transferenciaAgendada.getEmail())) {
			viewResponse.setEmail(transferenciaAgendada.getEmail());
			viewResponse.setMensajeEmail(transferenciaAgendada.getEmailMensaje());
		}
		viewResponse.setIsProgramada(transferenciaAgendada.getTipoTransferenciaAgendada().getDescripcion()
				.equals(TipoTransferenciaAgendada.PROGRAMADA.getDescripcion()) ? true : false);
		viewResponse.setIsRecordatorio(transferenciaAgendada.getTipoTransferenciaAgendada().getDescripcion()
				.equals(TipoTransferenciaAgendada.RECORDATORIO.getDescripcion()) ? true : false);
		viewResponse.setIsRecurrente(transferenciaAgendada.getTipoTransferenciaAgendada().getDescripcion()
				.equals(TipoTransferenciaAgendada.RECURRENTE.getDescripcion()) ? true : false);
	}

	/**
	 * Obtener tipo transferencia automatica.
	 *
	 * @return the string
	 */
	protected String obtenerTipoTransferenciaAutomatica() {
		return isMobile ? AUTOMATICA_MOBILE : AUTOMATICA;
	}

	/**
	 * Obtener tipo transferencia recordatorio.
	 *
	 * @return the string
	 */
	protected String obtenerTipoTransferenciaRecordatorio() {
		return isMobile ? RECORDATORIO_MOBILE : RECORDATORIO;
	}

	/**
	 * Sets the view.
	 *
	 * @param view
	 *            the view to set
	 */
	public void setView(E view) {
		this.view = view;
	}

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public E getView() {
		return view;
	}

}
