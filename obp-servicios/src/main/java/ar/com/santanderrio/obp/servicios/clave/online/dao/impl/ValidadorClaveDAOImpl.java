/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao.impl;

import ar.com.santanderrio.obp.servicios.clave.online.excepciones.*;
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
import ar.com.santanderrio.obp.servicios.clave.online.dao.ValidadorClaveDAO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.DatosAutenticacionInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.FuncionEnum;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.IpUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines;

/**
 * The Class ValidadorClaveDAOImpl.
 */
@Component
public class ValidadorClaveDAOImpl implements ValidadorClaveDAO {
    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidadorClaveDAOImpl.class);

    /** The Constant LETRA_A. */
    private static final String LETRA_A = "A";

    /** The Constant RETORNO_OK. */
    private static final Integer RETORNO_OK = 00000000;

    /** The Constant CLIENTE_BLOQUEADO. */
    private static final Integer CLIENTE_BLOQUEADO = 10050011;

    /** The Constant CLIENTE_BLOQUEADO2. */
    private static final Integer CLIENTE_BLOQUEADO2 = 10050014;

    /** The Constant ERROR_CON_REINTENTO. */
    private static final Integer ERROR_CON_REINTENTO = 10050091;

    /** The Constant ERROR_COD_TARJETA_CON_REINTENTO. */
    private static final Integer ERROR_COD_TARJETA_CON_REINTENTO = 10050094;
    /** The Constant ERROR_RUTINA_FECHAS. */
	private static final Integer ERROR_RUTINA_FECHAS = 10050090;
    /** The Constant SIN_REINTENTOS_SMS. */
    private static final Integer SIN_REINTENTOS_SMS = 10050092;
	/** The Constant ERROR_DB2. */
	private static final Integer ERROR_DB2 = 10050097;
    /** The Constant ERROR_PIN_BANELCO. */
	private static final Integer ERROR_PIN_BANELCO = 10050050;
	/** The Constant ERROR_CICS. */
	private static final Integer ERROR_CICS = 10050098 ;
	/** The Constant ERROR_PIN_OTP. */
	private static final Integer ERROR_PIN_OTP = 10050051;
	/** The Constant FUNCION_INVALIDA. */
	private static final Integer FUNCION_INVALIDA = 10050099 ;
	/** The Constant ERROR_MODULO. */
	private static final Integer ERROR_MODULO = 10050094;
	/** The Constant ERROR_LOGICO_OTP_NO_GENERADO. */
	private static final Integer ERROR_LOGICO_OTP_NO_GENERADO = 10050095 ;
    private static final Integer ERROR_OTP_VENCIDO = 10050071;
    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;
    
    /** The encry pines. */
    @Autowired
    private EncryPines encryPines;

    /** The servicio cnsvaldebi. */
    @Value("${SERVICIO.PREFIJO.SEGSGIAUT}")
    private String servicioSegsgiaut;

    /** The version cnsvaldebi. */
    @Value("${SERVICIO.VERSION.SEGSGIAUT}")
    private String versionSegsgiaut;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.dao.ValidadorClaveDAO#
     * validarClave(ar.com.santanderrio.obp.servicios.clave.online.entities.
     * DatosAutenticacionInEntity)
     */
    public void validarClave(DatosAutenticacionInEntity datosAutenticacionInEntity)
            throws DAOException, ErrorClaveOnlineConReintentoException, ClienteBloqueadoException,
            ClienteBloqueado2Exception, SinReintentosSMSException, ErrorEnvioSMSException, ErrorPinBanelcoException, ErrorPinOtpException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException, ErrorLogicoOtpException, OtpVencidoException {

        ResumenCliente resumenCliente = new ResumenCliente();
        resumenCliente.setDni(datosAutenticacionInEntity.getDni());
        resumenCliente
                .setFechaNacimiento(FechaUtils.parsearFechaDeNacimientoParaIATX(datosAutenticacionInEntity.getFecha()));
        resumenCliente.setNup(datosAutenticacionInEntity.getNup());
        try {
            IatxRequest request = new IatxRequest(servicioSegsgiaut, versionSegsgiaut);
            IatxRequestData requestData = new IatxRequestData(resumenCliente);

            requestData.setIndAutenticacion();
            requestData.setRacfInicial();

            String ip = IpUtils.dottedIp2Hex(datosAutenticacionInEntity.getIp());
            String bloqueCifrado = obtenerDatosCifrados(datosAutenticacionInEntity);
            String bloqueCifradoConEspaciosAdicionados = String.format("%-256s", bloqueCifrado);

            requestData.addBodyValue(datosAutenticacionInEntity.getFuncionEnum().getCodigo());
            requestData.addBodyValue(ip);
            requestData.addBodyValue(datosAutenticacionInEntity.getSesion());
            requestData.addBodyValue(datosAutenticacionInEntity.getCiclo());
            requestData.addBodyValue(bloqueCifradoConEspaciosAdicionados);
            requestData.addBodyValue(datosAutenticacionInEntity.getEmpresaCelularElegida().contains("CTI") ? "CTI " : datosAutenticacionInEntity.getEmpresaCelularElegida());

            request.setData(requestData);
            IatxResponse iatxResponse = iatxComm.exec(request);

            Integer codigoRetorno = iatxResponse.getErrorCode();
            String mensajeErrorIatx = iatxResponse.getErrorMessage();

            if (FuncionEnum.ENVIAR_SMS.equals(datosAutenticacionInEntity.getFuncionEnum())) {
                if (SIN_REINTENTOS_SMS.equals(codigoRetorno)) {
                    throw new SinReintentosSMSException(mensajeErrorIatx);
                }
                if (!RETORNO_OK.equals(codigoRetorno)) {
                    throw new ErrorEnvioSMSException(mensajeErrorIatx);
                }
            } else {
                if (CLIENTE_BLOQUEADO.equals(codigoRetorno)) {
                    throw new ClienteBloqueadoException(mensajeErrorIatx);
                }
                if (CLIENTE_BLOQUEADO2.equals(codigoRetorno)) {
                    throw new ClienteBloqueado2Exception(mensajeErrorIatx);
                }
                if (ERROR_CON_REINTENTO.equals(codigoRetorno)) {
                    throw new ErrorClaveOnlineConReintentoException(mensajeErrorIatx);
                }
                if (ERROR_COD_TARJETA_CON_REINTENTO.equals(codigoRetorno)) {
                    throw new DAOException();
                }
                if (ERROR_PIN_BANELCO.equals(codigoRetorno)) {
                    throw new ErrorPinBanelcoException(mensajeErrorIatx);
                }
                if (ERROR_PIN_OTP.equals(codigoRetorno)) {
                    throw new ErrorPinOtpException(mensajeErrorIatx);
                }
                if (ERROR_RUTINA_FECHAS.equals(codigoRetorno)) {
        			throw new ErrorRutinaFechasException(mensajeErrorIatx);
        		}
                if (ERROR_DB2.equals(codigoRetorno)) {
        			throw new ErrorDb2Exception(mensajeErrorIatx);
        		}
                if (ERROR_CICS .equals(codigoRetorno)) {
        			throw new ErrorCicsException(mensajeErrorIatx);
        		}
                if (FUNCION_INVALIDA .equals(codigoRetorno)) {
        			throw new FuncionInvalidaException(mensajeErrorIatx);
        		}
                if (ERROR_MODULO.equals(codigoRetorno)) {
					throw new ErrorModuloException(iatxResponse.getErrorMessage());
				}
                if (ERROR_LOGICO_OTP_NO_GENERADO.equals(codigoRetorno)) {
					throw new ErrorLogicoOtpException(iatxResponse.getErrorMessage());
				}
                if(ERROR_OTP_VENCIDO.equals(codigoRetorno)){
                    throw new OtpVencidoException(iatxResponse.getErrorMessage());
                }
                
                
                // En caso de recibir un codigo de error no reconocido
                if (!RETORNO_OK.equals(codigoRetorno)) {
                    throw new DAOException();
                }
            }

        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }

    }

    /**
     * Obtener datos cifrados.
     *
     * @param datosAutenticacionInEntity
     *            the datos autenticacion in entity
     * @return the string
     */
    private String obtenerDatosCifrados(DatosAutenticacionInEntity datosAutenticacionInEntity) {

        String retorno = datosAutenticacionInEntity.getLetraA() + datosAutenticacionInEntity.getTipoAut()
                + datosAutenticacionInEntity.getNumero() + datosAutenticacionInEntity.getClave() + LETRA_A;

        String datosPorCifrar = StringUtils.rightPad(retorno, 128, " ");
        return encryPines.obtenerCadena3Des(datosPorCifrar);
    }
    
}
