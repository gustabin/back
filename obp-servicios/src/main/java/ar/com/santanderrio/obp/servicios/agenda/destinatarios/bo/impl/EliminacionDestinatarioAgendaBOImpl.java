/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.EliminacionDestinatarioAgendaBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * Eliminacion de destinatario.
 *
 * @author federico.n.flores
 */
@Component
public class EliminacionDestinatarioAgendaBOImpl implements EliminacionDestinatarioAgendaBO {

	/** The agenda destinatario DAO. */
	@Autowired
	private AgendaDestinatarioDAO agendaDestinatarioDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EliminacionDestinatarioAgendaBOImpl.class);

	/** The Constant CODIGO_OK. */
	private static final String CODIGO_OK = "00000000";

	/** The Constant CODIGO_ERROR_1. */
	private static final String CODIGO_ERROR_1 = "14012";

	/** The Constant CODIGO_ERROR_2. */
	private static final String CODIGO_ERROR_2 = "14013";

	/** The Constant CODIGO_ERROR_3. */
	private static final String CODIGO_ERROR_3 = "14014";

	/** The Constant CODIGO_ERROR_4. */
	private static final String CODIGO_ERROR_4 = "14015";

	/** The Constant CODIGO_ERROR_5. */
	private static final String CODIGO_ERROR_5 = "14016";

	/** The Constant CODIGO_ERROR_6. */
	private static final String CODIGO_ERROR_6 = "14017";

	/** The Constant CODIGO_ERROR_INICIO_1. */
	private static final String CODIGO_ERROR_INICIO_1 = "1";

	/** The Constant CODIGO_ERROR_INICIO_2. */
	private static final String CODIGO_ERROR_INICIO_2 = "2";

	/** The Constant CODIGO_ERROR_INICIO_3. */
	private static final String CODIGO_ERROR_INICIO_3 = "3";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
	 * EliminacionDestinatarioAgendaBO#eliminacionDestinatario(ar.com.
	 * santanderrio.obp.servicios.agenda.destinatarios.entity.
	 * DestinatarioEntity, java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Void> eliminacionDestinatario(DestinatarioEntity destinatario, String ip, Cliente cliente) {
		try {
			AgendaDestinatarioOutEntity outEntity = agendaDestinatarioDAO
					.eliminarUAgregar(crearInEntity(destinatario, cliente), ip, TipoOperacionACTAGEDESTEnum.BAJA);
			return crearRespuesta(outEntity);
		} catch (DAOException e) {
			LOGGER.error("Error de servicio", e);
			ErrorAgendaDestinatariosEnum error = ErrorAgendaDestinatariosEnum.ERROR_GENERAL_ELIMINACION_DESTINATARIO;
			return respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(), error.getCodigoMensaje());
		}
	}

	/**
	 * Crear in entity.
	 *
	 * @param destinatario
	 *            the destinatario
	 * @param cliente
	 *            the cliente
	 * @return the agenda destinatario in entity
	 */
	private AgendaDestinatarioInEntity crearInEntity(DestinatarioEntity destinatario, Cliente cliente) {
		AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity();
		inEntity.setCliente(cliente);
		inEntity.setTipoAgendaOcurrencia(destinatario.getTipoAgendaOcurrencia());
		inEntity.setTipoCuentaDestinatario(destinatario.getTipoCuentaDestinatario());
		inEntity.setSucursalCuentaDestinatario(destinatario.getSucursalCuentaDestinatario());
		inEntity.setNumeroCuentaDestinatario(destinatario.getNumeroCuentaDestinatario());
		inEntity.setCbuDestinatario(destinatario.getCbuDestinatario());
		inEntity.setBancoDestinatario(destinatario.getBancoDestinatario());
		inEntity.setTipoDocumentoDestinatario(destinatario.getTipoDocumentoDestinatario());
		inEntity.setDocumentoDestinatario(destinatario.getDocumentoDestinatario());
		inEntity.setDescripcionCuentaDestinatario(destinatario.getDescripcionCuentaDestinatario());
		inEntity.setCaracteristicasCuentaDestinatario(destinatario.getCaracteristicasCuentaDestinatario());
		inEntity.setTitular(destinatario.getTitular());
		inEntity.setDireccionCorreo(destinatario.getDireccionCorreo());
		inEntity.setTelefonoDestinatario(destinatario.getTelefonoDestinatario());
		return inEntity;
	}

	/**
	 * Crear respuesta.
	 *
	 * @param outEntity
	 *            the out entity
	 * @return the respuesta
	 */
	private Respuesta<Void> crearRespuesta(AgendaDestinatarioOutEntity outEntity) {
		String codigoError = outEntity.getCodigoRetornoExtendido();
		if (StringUtils.equals(CODIGO_OK, codigoError)) {
			return respuestaFactory.crearRespuestaOk(null);
		}
		List<String> codigosError = new ArrayList<String>(Arrays.asList(CODIGO_ERROR_1, CODIGO_ERROR_2, CODIGO_ERROR_3,
				CODIGO_ERROR_4, CODIGO_ERROR_5, CODIGO_ERROR_6));
		List<String> codigosErrorInicio = new ArrayList<String>(
				Arrays.asList(CODIGO_ERROR_INICIO_1, CODIGO_ERROR_INICIO_2, CODIGO_ERROR_INICIO_3));
		if (codigosErrorInicio.contains(StringUtils.left(codigoError, 1))
				&& codigosError.contains(StringUtils.right(codigoError, 5))) {
			ErrorAgendaDestinatariosEnum error = ErrorAgendaDestinatariosEnum.ERROR_BAJA_DESTINATARIO_YA_ELIMINADO;
			return respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(), error.getCodigoMensaje());
		}
		ErrorAgendaDestinatariosEnum error = ErrorAgendaDestinatariosEnum.ERROR_GENERAL_ELIMINACION_DESTINATARIO;
		return respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(), error.getCodigoMensaje());
	}

}
