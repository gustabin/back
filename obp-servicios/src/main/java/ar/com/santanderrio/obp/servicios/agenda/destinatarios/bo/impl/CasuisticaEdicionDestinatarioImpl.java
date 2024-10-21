/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaEdicionDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CasuisticaEdicionDestinatarioImpl.
 *
 * @author dante.omar.olmedo
 */
@Component
public class CasuisticaEdicionDestinatarioImpl extends DestinatarioCommon implements CasuisticaEdicionDestinatario {

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant CODIGO_OK. */
	private static final String CODIGO_OK = "00000000";

	/*
	 * CTA_DADA_DE_BAJA_RIO 10011634 CTA_INEXISTENTE_RIO 10011635
	 * CBU_DADO_DE_BAJA_OTROSBANCOS 10014006 CBU_INEXISTENTE_OTROSBANCOS
	 * 10014007 CTA_DADA_DE_BAJA_EXT 10011631 CTA_INEXISTENTE_EXT 10011632
	 */

	/** The Constant DESTINATARIO_INEXISTENTE. */
	private static final String[] DESTINATARIO_INEXISTENTE = { "10011634", "10011684", "10011637", "10011638",
			"10014006", "10014007", "10011631", "10011632" };

	/** The Constant MENSAJE_FEEDBACK_OK. */
	private static final String MENSAJE_FEEDBACK_OK = "1456";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
	 * CasuisticaEdicionDestinatario#crearRespuesta(ar.com.santanderrio.obp.
	 * servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity,
	 * java.lang.String)
	 */
	@Override
	public Respuesta<ComprobanteAltaDestinatarioDTO> crearRespuestaEdicion(AgendaDestinatarioOutEntity outEntity,
			AgendaDestinatarioInEntity inEntity) {
		String codigoError = outEntity.getCodigoRetornoExtendido();
		if (StringUtils.equals(CODIGO_OK, codigoError)) {
			ComprobanteAltaDestinatarioDTO dto = new ComprobanteAltaDestinatarioDTO(outEntity);
			String descripcion = StringUtils.isNotBlank(inEntity.getDescripcionCuentaDestinatario())
					? inEntity.getDescripcionCuentaDestinatario() : inEntity.getTitular();
			dto.setMensajeEfectivizacion(completarMensajeFeedbackOK(dto, descripcion, MENSAJE_FEEDBACK_OK));
			return respuestaFactory.crearRespuestaOk(dto);
		} else {
			if (ArrayUtils.contains(DESTINATARIO_INEXISTENTE, outEntity.getCodigoRetornoExtendido())) {
				ErrorAgendaDestinatariosEnum error = ErrorAgendaDestinatariosEnum.ERROR_EDITAR_DESTINATARIO_INVALIDO;
				return respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(),
						error.getCodigoMensaje());
			}
			return crearErrorTimeOut();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
	 * CasuisticaEdicionDestinatario#crearErrorTimeOut()
	 */
	@Override
	public Respuesta<ComprobanteAltaDestinatarioDTO> crearErrorTimeOut() {
		ErrorAgendaDestinatariosEnum error = ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_EDICION;
		return respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(), error.getCodigoMensaje());
	}

}
