/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.util;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.comprobante.entities.AbstractComprobante;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ComprobanteTransferenciaAgendadaView;

/**
 * Visitor para comprobante stop debit de transferencias agendadas No
 * thread-safe.
 *
 * @author B039543
 */
public class ComprobanteTransferenciaAgendadaViewVisitor
		extends AbstractTransferenciaAgendadaDetalleViewVisitor<ComprobanteTransferenciaAgendadaView> {

	/** The Constant FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "dd/MM/yyyy HH:mm";

	/** The texto legales. */
	private String textoLegales;

	/** The comprobante transferencia agendada DTO. */
	private AbstractComprobante<String> comprobanteTransferenciaAgendadaDTO;

	/**
	 * Instantiates a new comprobante transferencia agendada view visitor.
	 *
	 * @param textoLegales
	 *            the texto legales
	 * @param comprobanteTransferenciaAgendadaDTO
	 *            the comprobante transferencia agendada DTO
	 */
	public ComprobanteTransferenciaAgendadaViewVisitor(String textoLegales,
			AbstractComprobante comprobanteTransferenciaAgendadaDTO) {
		super(false);
		this.setTextoLegales(textoLegales);
		this.setComprobanteTransferenciaAgendadaDTO(comprobanteTransferenciaAgendadaDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaViewVisitor#crearVista(ar.com.santanderrio.
	 * obp.transferencias.entities.agenda.TransferenciaAgendadaDTO)
	 */
	@Override
	protected ComprobanteTransferenciaAgendadaView crearVista(TransferenciaAgendadaDTO transferenciaAgendada) {
		return new ComprobanteTransferenciaAgendadaView();
	}

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
			ComprobanteTransferenciaAgendadaView viewResponse) {
		super.popularVista(transferenciaAgendada, viewResponse);

		viewResponse.setFechaHoraComprobante(ISBANStringUtils
				.formatearFecha(getComprobanteTransferenciaAgendadaDTO().getFechaHoraComprobante(), FORMATO_FECHA));
		viewResponse.setTextoLegales(textoLegales);
		viewResponse.setNumeroComprobante(getComprobanteTransferenciaAgendadaDTO().getNumeroComprobante());
	}

	/**
	 * Gets the texto legales.
	 *
	 * @return the textoLegales
	 */
	public String getTextoLegales() {
		return textoLegales;
	}

	/**
	 * Sets the texto legales.
	 *
	 * @param textoLegales
	 *            the textoLegales to set
	 */
	public void setTextoLegales(String textoLegales) {
		this.textoLegales = textoLegales;
	}

	/**
	 * Gets the comprobante transferencia agendada DTO.
	 *
	 * @return the comprobante transferencia agendada DTO
	 */
	public AbstractComprobante<String> getComprobanteTransferenciaAgendadaDTO() {
		return comprobanteTransferenciaAgendadaDTO;
	}

	/**
	 * Sets the comprobante transferencia agendada DTO.
	 *
	 * @param comprobanteTransferenciaAgendadaDTO
	 *            the new comprobante transferencia agendada DTO
	 */
	public void setComprobanteTransferenciaAgendadaDTO(
			AbstractComprobante<String> comprobanteTransferenciaAgendadaDTO) {
		this.comprobanteTransferenciaAgendadaDTO = comprobanteTransferenciaAgendadaDTO;
	}

}