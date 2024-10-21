/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.monedero.dao.MonederoActivacionDAO;
import ar.com.santanderrio.obp.servicios.monedero.entities.MonederoActivacionInEntities;

/**
 * The Class MonederoActivacionDAOImpl.
 *
 * @author alejandro_leal
 */
@Component("monederoActivacionDAO")
public class MonederoActivacionDAOImpl extends IatxBaseDAO implements MonederoActivacionDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MonederoActivacionDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The servicio pag tjc. */
	@Value("${SERVICIO.PREFIJO.ACTCTATJMO}")
	private String servicioActivaMone;

	/** The version pag tjc. */
	@Value("${SERVICIO.VERSION.ACTCTATJMO}")
	private String versionActivaMone;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.monedero.dao.MonederoActivacionDAO#
	 * activar(ar.com.santanderrio.obp.servicios.monedero.entities.
	 * MonederoActivacionInEntities)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> activar(MonederoActivacionInEntities entity) throws DAOException {

		IatxRequest iatxRequest = new IatxRequest(servicioActivaMone, versionActivaMone);

		Respuesta<ResultadoTransaccion> respuestaResultado = new Respuesta<ResultadoTransaccion>();

		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSSUSFCI(entity);

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
			if (errorCode == OK_CODIGO_RETORNO) {
				respuestaResultado.setEstadoRespuesta(EstadoRespuesta.OK);
				SimpleDateFormat formatoFechaHoraTrama = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
				resultadoTransaccion.setFechaTransaccion(
						formatoFechaHoraTrama.parse(formatoFechaHoraTrama.format(iatxResponse.getFechaHoraReq())));
				resultadoTransaccion.setMensajeError(mensajeManager.obtenerMensajePorCodigo("5030").getMensaje());
				resultadoTransaccion.setNumeroComprobante(iatxResponse.getNroComprobante());
				resultadoTransaccion.setCodigoRespuesta(iatxResponse.getErrorCode());
				resultadoTransaccion.setEstadoRespuesta(iatxResponse.getEstadoRespuesta());
				respuestaResultado.setRespuesta(resultadoTransaccion);
				return respuestaResultado;
			} else {
				respuestaResultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
				resultadoTransaccion.setMensajeError(mensajeManager.obtenerMensajePorCodigo("5029").getMensaje());
				respuestaResultado.setRespuesta(resultadoTransaccion);
				return respuestaResultado;
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}

	}

	/**
	 * Genera el objeto IatxRequestData para llamar al servicio iatx.
	 * 
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSSUSFCI(MonederoActivacionInEntities entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getSucursal());
		iatxRequestData.addBodyValue(entity.getCantTarjetaTagPorActivar());
		iatxRequestData.addBodyValue(entity.getNumTarjetaPorActivar());

		return iatxRequestData;
	}

}
