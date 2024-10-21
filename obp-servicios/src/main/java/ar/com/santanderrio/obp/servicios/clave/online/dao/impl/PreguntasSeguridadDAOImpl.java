/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clave.online.dao.PreguntasSeguridadDAO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridadResponse;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ProductoEntity;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.HayRespuestasErroneasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.IpUtils;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class PreguntasSeguridadDAOImpl.
 */
@Component
public class PreguntasSeguridadDAOImpl extends IatxBaseDAO implements PreguntasSeguridadDAO {

	/** The Constant FUNCION. */
	private static final String FUNCION = "00";

	/** The Constant RESPUESTA_OK. */
	private static final Integer RESPUESTA_OK = 0;
	/** The Constant ERROR_DB2. */
	private static final Integer ERROR_DB2 = 10050097;
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PreguntasSeguridadDAOImpl.class);
	/** The Constant ERROR_RUTINA_FECHAS. */
	private static final Integer ERROR_RUTINA_FECHAS = 10050090;
	/** The Constant ERROR_CICS. */
	private static final Integer ERROR_CICS = 10050098 ;
	/** The Constant FUNCION_INVALIDA. */
	private static final Integer FUNCION_INVALIDA = 10050099 ;
	/** The Constant ERROR_MODULO. */
	private static final Integer ERROR_MODULO = 10050094;
	/** The Constant ERROR_HAY_RESPUESTAS_ERRONEAS. */
	private static final Integer ERROR_HAY_RESPUESTAS_ERRONEAS = 10050094;
	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The prefijo seginform. */
	@Value("${SERVICIO.PREFIJO.ACTSGIDESF}")
	private String prefijoActsgidesf;

	/** The version130. */
	@Value("${SERVICIO.VERSION.ACTSGIDESF}")
	private String version;

	/**
	 * Obtener preguntas seguridad.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the preguntas seguridad response
	 * @throws ErrorRutinaFechasException 
	 * @throws ErrorDb2Exception 
	 * @throws ErrorCicsException 
	 * @throws FuncionInvalidaException 
	 * @throws ErrorModuloException 
	 * @throws HayRespuestasErroneasException
	 */
	@Override
	public PreguntasSeguridadResponse obtenerPreguntasSeguridad(IdentificadorClienteInEntity cliente) throws ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException, HayRespuestasErroneasException {

		IatxRequest request = new IatxRequest(prefijoActsgidesf, version);

		IatxRequestData requestData = getRequestData(cliente);

		request.setData(requestData);
		IatxResponse iatxResponse = null;
		PreguntasSeguridadResponse preguntasSeguridadResponse = null;
		try {

			iatxResponse = iatxComm.exec(request);

			int errorCode = iatxResponse.getErrorCode();

			if (RESPUESTA_OK.equals(errorCode)) {
				preguntasSeguridadResponse = processTrama(iatxResponse.getTrama(), PreguntasSeguridadResponse.class);
			} else {
				if (ERROR_RUTINA_FECHAS.equals(errorCode)) {
					throw new ErrorRutinaFechasException(iatxResponse.getErrorMessage());
				}
				if (ERROR_DB2.equals(errorCode)) {
					throw new ErrorDb2Exception(iatxResponse.getErrorMessage());
				}
				if (ERROR_CICS .equals(errorCode)) {
					throw new ErrorCicsException(iatxResponse.getErrorMessage());
				}
				if (FUNCION_INVALIDA.equals(errorCode)) {
					throw new FuncionInvalidaException(iatxResponse.getErrorMessage());
				}
				if (ERROR_MODULO.equals(errorCode)) {
					throw new ErrorModuloException(iatxResponse.getErrorMessage());
				}
				if (ERROR_HAY_RESPUESTAS_ERRONEAS.equals(errorCode)) {
					throw new HayRespuestasErroneasException(iatxResponse.getErrorMessage());
				}
				throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
			}

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}

		return preguntasSeguridadResponse;

	}

	/**
	 * Gets the request data.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the request data
	 */
	private IatxRequestData getRequestData(IdentificadorClienteInEntity cliente) {

		ResumenCliente resumenCliente = new ResumenCliente();
		resumenCliente.setNup(cliente.getNup());
		resumenCliente.setDni(cliente.getDni());
		resumenCliente.setFechaNacimiento(FechaUtils.parsearFechaDeNacimientoParaIATX(cliente.getFechaNacimiento()));

		IatxRequestData requestData = new IatxRequestData(resumenCliente);

		requestData.setIndAutenticacion();
		requestData.setRacfInicial();
		requestData.addBodyValue(FUNCION);

		requestData.addBodyValue(IpUtils.dottedIp2Hex(cliente.getIp()));
		requestData.addBodyValue(cliente.getIdSesion());

		requestData.addBodyValue(String.valueOf(cliente.getCiclo()));
		requestData.addBodyValue(String.valueOf(cliente.getCantidadOcurrencias()));
		requestData = appendProductos(cliente, requestData);

		return requestData;
	}

	/**
	 * Append productos.
	 *
	 * @param cliente
	 *            the cliente
	 * @param requestData
	 *            the request data
	 * @return the iatx request data
	 */
	private IatxRequestData appendProductos(IdentificadorClienteInEntity cliente, IatxRequestData requestData) {

		for (ProductoEntity producto : cliente.getProductos()) {

			requestData.addBodyValue(producto.getCodigoProducto());
			requestData.addBodyValue(producto.getCodigoSubProducto());
			requestData.addBodyValue(producto.getCalidadParticipacion());
			requestData.addBodyValue(producto.getCodigoOficina());
			requestData.addBodyValue(producto.getNroContrato());
		}
		return requestData;
	}
}
