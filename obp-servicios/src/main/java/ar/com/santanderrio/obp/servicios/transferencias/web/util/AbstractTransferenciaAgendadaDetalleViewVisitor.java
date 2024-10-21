/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.util;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptoView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;

/**
 * Visitor para detalle de transferencias agendadas No thread-safe.
 *
 * @author B039543
 * @param <E>
 *            the element type
 */
public abstract class AbstractTransferenciaAgendadaDetalleViewVisitor<E extends TransferenciaAgendadaDetalleView>
		extends AbstractTransferenciaAgendadaViewVisitor<E> {

	/** The Constant CBU. */
	private static final String CBU = "CBU/CVU";

	/**
	 * Instantiates a new abstract transferencia agendada detalle view visitor.
	 *
	 * @param isMobile
	 *            the is mobile
	 */
	public AbstractTransferenciaAgendadaDetalleViewVisitor(boolean isMobile) {
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaViewVisitor#visit(ar.com.santanderrio.obp.
	 * transferencias.entities.agenda.TransferenciaAgendadaRecordatorioDTO)
	 */
	// @Override
	// public void visit(TransferenciaAgendadaRecordatorioDTO
	// transferenciaAgendada) {
	// super.visit(transferenciaAgendada);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaViewVisitor#visit(ar.com.santanderrio.obp.
	 * transferencias.entities.agenda.TransferenciaAgendadaProgramadaDTO)
	 */
	// @Override
	// public void visit(TransferenciaAgendadaProgramadaDTO
	// transferenciaAgendada) {
	// super.visit(transferenciaAgendada);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaViewVisitor#visit(ar.com.santanderrio.obp.
	 * transferencias.entities.agenda.TransferenciaAgendadaRecurrenteDTO)
	 */
	// @Override
	// public void visit(TransferenciaAgendadaRecurrenteDTO
	// transferenciaAgendada) {
	// super.visit(transferenciaAgendada);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaViewVisitor#popularVista(ar.com.santanderrio
	 * .obp.transferencias.entities.agenda.TransferenciaAgendadaDTO,
	 * ar.com.santanderrio.obp.transferencias.web.view.
	 * TransferenciaAgendadaView)
	 */
	@Override
	protected void popularVista(TransferenciaAgendadaDTO transferenciaAgendada, E viewResponse) {
		super.popularVista(transferenciaAgendada, viewResponse);
		if (transferenciaAgendada.getConcepto() != null) {
			viewResponse.setConcepto(new ConceptoView(transferenciaAgendada.getConcepto().getDescripcion(),transferenciaAgendada.getConcepto().getOrdinal(),transferenciaAgendada.getConcepto().getCodigo(),transferenciaAgendada.getConcepto().getDescripcionAbreviada(),transferenciaAgendada.getConcepto().getHabilitaLegal()));
		}
		if (transferenciaAgendada.getDestinatario() != null
				&& !"".equals(transferenciaAgendada.getDestinatario().getNombre())) {
			if (transferenciaAgendada.getDestinatario().getDescripcion() != null
					&& transferenciaAgendada.getDestinatario().getDescripcion() != "") {
				viewResponse.setAbreviatura(
						transferenciaAgendada.getDestinatario().getDescripcion().toUpperCase().trim().substring(0, 2));
			} else {
				viewResponse.setAbreviatura(
						transferenciaAgendada.getDestinatario().getNombre().toUpperCase().trim().substring(0, 2));
			}
		}

		// OTROS
		if (Boolean.TRUE.equals(!transferenciaAgendada.isHaciaOtroBanco())) {
			if (transferenciaAgendada.getCuentaDestinoTipo() != null) {
				viewResponse.setDestinoTipo(transferenciaAgendada.getCuentaDestinoTipo().getDescripcionConMoneda());
			}
		} else {
			viewResponse.setDestinoTipo(CBU);
		}

		if (transferenciaAgendada.getPlazoAcreditacion() != null) {
			viewResponse.setPlazoAcreditacion(transferenciaAgendada.getPlazoAcreditacion().getDescripcion());
		}
		if (transferenciaAgendada.getCuentaOrigenTipo() != null) {
			viewResponse.setOrigenTipo(transferenciaAgendada.getCuentaOrigenTipo().getDescripcionConMoneda());
			viewResponse.setIsRioRio(!transferenciaAgendada.isHaciaOtroBanco());
			if (transferenciaAgendada.getDatosOrigen() != null) {
				viewResponse.setOrigenNombreTitular(
						transferenciaAgendada.getDatosOrigen().getTransferenciaAgendada().getTitular());
			}
		}
		if (transferenciaAgendada.isHaciaOtroBanco()) {
			if (StringUtils.isNotBlank(transferenciaAgendada.getCuitCuil())
					&& ISBANStringUtils.validarCuil(transferenciaAgendada.getCuitCuil())) {
				viewResponse.setNumeroCuilCuit(ISBANStringUtils.formatearCuil(transferenciaAgendada.getCuitCuil()));
			} else {
				viewResponse.setNumeroCuilCuit("-");
			}
		}

		if (StringUtils.isNotBlank(transferenciaAgendada.getEmail())) {
			viewResponse.setEmailActivo(true);
			viewResponse.setEmail(transferenciaAgendada.getEmail());
			viewResponse.setMensajeEmail(transferenciaAgendada.getEmailMensaje());
		} else {
			viewResponse.setEmailActivo(false);
		}

		viewResponse.setOrigenNumero(transferenciaAgendada.getNroCuentaOrigen());

		// CBU OTROS - CUENTA RIORIO
		if (Boolean.TRUE.equals(!transferenciaAgendada.isHaciaOtroBanco())) {
			viewResponse.setDestinoNumero(transferenciaAgendada.getCuentaDestino());
		} else {
			viewResponse.setDestinoNumero(transferenciaAgendada.getCbuCuenta());
		}
        if (transferenciaAgendada.getDatosOrigen() != null
                && transferenciaAgendada.getDatosOrigen().getTransferenciaAgendada() != null && StringUtils.isNotBlank(
                        transferenciaAgendada.getDatosOrigen().getTransferenciaAgendada().getInfoAdicional())) {
            viewResponse.setDescripcion(
                    transferenciaAgendada.getDatosOrigen().getTransferenciaAgendada().getInfoAdicional().trim());
        } else if (StringUtils.isNotBlank(transferenciaAgendada.getDescripcionConcepto())) {
            viewResponse.setDescripcion(transferenciaAgendada.getDescripcionConcepto().trim());
        } else {
            viewResponse.setDescripcion(ConceptoTransferenciaEnum.VARIOS.getDescripcion());
        }
		viewResponse.setAliasDestino(transferenciaAgendada.getCuentaAliasDestino());
		viewResponse.setAliasOrigen(transferenciaAgendada.getCuentaAliasOrigen());

		// fecha larga en detalle
		viewResponse.setFecha(ISBANStringUtils.formatearFecha(transferenciaAgendada.getFechaEjecucion()));
	}

}