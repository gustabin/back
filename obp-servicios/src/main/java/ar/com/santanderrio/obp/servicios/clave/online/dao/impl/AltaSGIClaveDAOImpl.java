/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clave.online.dao.AltaSGIClaveDAO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineClaveRepeException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineClaveTrivialException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineUsuRepeException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineUsuTrivialException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueadoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.IpUtils;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.login.dao.ProxyLoginDAO;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines;
import ar.com.santanderrio.obp.servicios.login.entity.ProxyLoginResponse;

/**
 * The Class AltaSGIClaveDAOImpl.
 */
@Component
public class AltaSGIClaveDAOImpl extends IatxBaseDAO implements AltaSGIClaveDAO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AltaSGIClaveDAOImpl.class);
    /** The Constant FUNCION. */
    private static final String FUNCION = "00";
    /** The Constant CLIENTE_BLOQUEADO. */
    private static final Integer CLIENTE_BLOQUEADO = 10050011;
    /** The Constant ERROR_CLAVE_TRIVIAL. */
    private static final Integer ERROR_CLAVE_TRIVIAL = 10010041;
    /** The Constant ERROR_CLAVEREPE. */
    private static final Integer ERROR_CLAVEREPE = 10010042;
    /** The Constant ERROR_USUARIO_TRIVIAL. */
    private static final Integer ERROR_USUARIO_TRIVIAL = 10010043;
    /** The Constant ERROR_CON_USUREPE. */
    private static final Integer ERROR_CON_USUREPE = 10010044;
    /** The Constant RETORNO_OK. */
    private static final Integer RETORNO_OK = 00000000;
    /** The Constant ERROR_RUTINA_FECHAS. */
	private static final Integer ERROR_RUTINA_FECHAS = 10050090;
	/** The Constant ERROR_DB2. */
	private static final Integer ERROR_DB2 = 10050097;
	/** The Constant ERROR_CICS. */
	private static final Integer ERROR_CICS = 10050098 ;
	/** The Constant FUNCION_INVALIDA. */
	private static final Integer FUNCION_INVALIDA = 10050099 ;
	/** The Constant ERROR_MODULO. */
	private static final Integer ERROR_MODULO = 10050094;
    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /** The encry pines. */
    @Autowired
    private EncryPines encryPines;

    /** The prefijo seginform. */
    @Value("${SERVICIO.PREFIJO.ALTSGICLAV}")
    private String prefijoAltSgiClav;

    /** The version 100. */
    @Value("${SERVICIO.VERSION.ALTSGICLAV}")
    private String version;

	/** The proxy login DAO. */
	@Autowired
	private ProxyLoginDAO proxyLoginDAO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.dao.AltaSGIClaveDAO#
     * altaSGIClave(ar.com.santanderrio.obp.servicios.clave.online.entities.
     * AltaSGIClaveInEntity)
     */
    @Override
    public void altaSGIClave(AltaSGIClaveInEntity altaSGIClaveIn)
            throws DAOException, ClienteBloqueadoException, ClaveOnlineClaveTrivialException,
            ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {

        IatxRequest request = new IatxRequest(prefijoAltSgiClav, version);

        ResumenCliente resumenCliente = new ResumenCliente();
        resumenCliente.setNup(altaSGIClaveIn.getNup());
        resumenCliente.setDni(altaSGIClaveIn.getDni());
        resumenCliente
                .setFechaNacimiento(FechaUtils.parsearFechaDeNacimientoParaIATX(altaSGIClaveIn.getFechaNacimiento()));
        IatxRequestData requestData = new IatxRequestData(resumenCliente);
        requestData.setIndAutenticacion();
        requestData.setRacfInicial();

        String bloqueCifrado = this.obtenerDatosCifrados(altaSGIClaveIn);

        requestData.addBodyValue(FUNCION);
        requestData.addBodyValue(IpUtils.dottedIp2Hex(altaSGIClaveIn.getIp()));
        requestData.addBodyValue(altaSGIClaveIn.getIdSesion());
        requestData.addBodyValue(bloqueCifrado);

        request.setData(requestData);

        Integer errorCode = null;
        IatxResponse iatxResponse = null;
        try {
            iatxResponse = iatxComm.exec(request);
            errorCode = iatxResponse.getErrorCode();

            evaluarRespuesta(errorCode, iatxResponse.getErrorMessage());

        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
        }
    }

	/**
	 * Evaluar respuesta.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @throws ClaveOnlineClaveTrivialException the clave online clave trivial exception
	 * @throws ClaveOnlineUsuTrivialException the clave online usu trivial exception
	 * @throws ClaveOnlineClaveRepeException the clave online clave repe exception
	 * @throws ClaveOnlineUsuRepeException the clave online usu repe exception
	 * @throws ClienteBloqueadoException the cliente bloqueado exception
	 * @throws ErrorRutinaFechasException the error rutina fechas exception
	 * @throws ErrorDb2Exception the error db 2 exception
	 * @throws ErrorCicsException the error cics exception
	 * @throws FuncionInvalidaException the funcion invalida exception
	 * @throws ErrorModuloException the error modulo exception
	 * @throws IatxException the iatx exception
	 */
	private void evaluarRespuesta(Integer errorCode, String errorMessage)
			throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException,
			ClaveOnlineUsuRepeException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception,
			ErrorCicsException, FuncionInvalidaException, ErrorModuloException, IatxException {
		isClaveTrivial(errorCode, errorMessage);
		isUsuarioTrivial(errorCode, errorMessage);
		isClaveRepetida(errorCode, errorMessage);
		isUsuarioRepetido(errorCode, errorMessage);
		if (CLIENTE_BLOQUEADO.equals(errorCode)) {
		    throw new ClienteBloqueadoException(errorMessage);
		}
		if (ERROR_RUTINA_FECHAS.equals(errorCode)) {
			throw new ErrorRutinaFechasException(errorMessage);
		}
		if (ERROR_DB2.equals(errorCode)) {
			throw new ErrorDb2Exception(errorMessage);
		}
		if (ERROR_CICS .equals(errorCode)) {
			throw new ErrorCicsException(errorMessage);
		}
		if (FUNCION_INVALIDA .equals(errorCode)) {
			throw new FuncionInvalidaException(errorMessage);
		}
		if (ERROR_MODULO.equals(errorCode)) {
			throw new ErrorModuloException(errorMessage);
		}
		if (!RETORNO_OK.equals(errorCode)) {
		    throw new IatxException(errorMessage);
		}
	}

    /**
	 * Validar si es error y requiere mostrar mensaje de clave trivial.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the error message
	 * @throws ClaveOnlineClaveTrivialException
	 *             the clave online clave trivial exception
	 */
    private void isClaveTrivial(Integer errorCode, String errorMessage) throws ClaveOnlineClaveTrivialException {
        if (ERROR_CLAVE_TRIVIAL.equals(errorCode)) {
            throw new ClaveOnlineClaveTrivialException(errorMessage);
        }
    }

    /**
	 * Validar si es error y requiere mostrar mensaje de usuario trivial.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the error message
	 * @throws ClaveOnlineUsuTrivialException
	 *             the clave online usu trivial exception
	 */
    private void isUsuarioTrivial(Integer errorCode, String errorMessage) throws ClaveOnlineUsuTrivialException {
        if (ERROR_USUARIO_TRIVIAL.equals(errorCode)) {
            throw new ClaveOnlineUsuTrivialException(errorMessage);
        }
    }

    /**
	 * Validar si es error y requiere mostrar mensaje de clave repetida.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the error message
	 * @throws ClaveOnlineClaveRepeException
	 *             the clave online clave repe exception
	 */
    private void isClaveRepetida(Integer errorCode, String errorMessage) throws ClaveOnlineClaveRepeException {
        if (ERROR_CLAVEREPE.equals(errorCode)) {
            throw new ClaveOnlineClaveRepeException(errorMessage);
        }
    }

    /**
	 * Validar si es error y requiere mostrar mensaje de usuario repetido.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the error message
	 * @throws ClaveOnlineUsuRepeException
	 *             the clave online usu repe exception
	 */
    private void isUsuarioRepetido(Integer errorCode, String errorMessage) throws ClaveOnlineUsuRepeException {
        if (ERROR_CON_USUREPE.equals(errorCode)) {
            throw new ClaveOnlineUsuRepeException(errorMessage);
        }
    }

    /**
     * Obtener datos cifrados.
     *
     * @param altaSGIClaveIn
     *            the alta SGI clave in
     * @return the string
     */
    private String obtenerDatosCifrados(AltaSGIClaveInEntity altaSGIClaveIn) {
        String clave = altaSGIClaveIn.getPrefijo() + altaSGIClaveIn.getClaveEncriptado() + altaSGIClaveIn.getSufijo();
        String usuario = altaSGIClaveIn.getPrefijo() + altaSGIClaveIn.getUsuarioEncriptado()
                + altaSGIClaveIn.getSufijo();
        String claveUsuarioPad = StringUtils.rightPad(encryPines.obtenerCadena3Des(clave), 64, " ");
        String datosPorCifrar = StringUtils.rightPad(encryPines.obtenerCadena3Des(usuario), 192, " ");
        return claveUsuarioPad + datosPorCifrar;
    }

	@Override
	public void altaSGIClaveProxyLogin(AltaSGIClaveInEntity altaSGIClaveIn) throws DAOException,
			ClienteBloqueadoException, ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException,
			ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, ErrorRutinaFechasException, ErrorDb2Exception,
			ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
		ProxyLoginResponse response = null;
		try {
			response = proxyLoginDAO.setCredentials(altaSGIClaveIn);
			if (response.getCode() != null && !"".equals(response.getCode().trim())) {
				Integer errorCode = Integer.parseInt(response.getMessage());
				String errorMessage = response.getDescription();
				evaluarRespuesta(errorCode, errorMessage);
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
            throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
        }
	}

}
