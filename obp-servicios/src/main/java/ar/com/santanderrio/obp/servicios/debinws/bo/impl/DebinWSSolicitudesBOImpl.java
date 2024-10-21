package ar.com.santanderrio.obp.servicios.debinws.bo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TerminalDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.alias.UsuarioDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.CompradorDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.DetalleDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.EstadoDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConsulta;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestNuevoDebinV3;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseNuevoDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseVendedor;
import ar.com.santanderrio.obp.generated.webservices.debin.VendedorDebinDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.AliasCorrespondienteCuentaPropiaException;
import ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO;
import ar.com.santanderrio.obp.servicios.alias.exception.AliasCorrespondienteCuentaPropiaUnicaException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSSolicitudesBO;
import ar.com.santanderrio.obp.servicios.debinws.common.DebinWSConstants;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSSolicitudesDAO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteSolicitudDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CreacionDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CreacionDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarAliasInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarAliasOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCBUEntityIn;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCbuEntityOut;
import ar.com.santanderrio.obp.servicios.debinws.exceptions.DebinCBUInvalidoDAOException;
import ar.com.santanderrio.obp.servicios.debinws.exceptions.DebinDestinatarioNoVerificadoException;
import ar.com.santanderrio.obp.servicios.debinws.utils.DebinWSUtils;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;

/**
 * The Class DebinWSSolicitudesBOImpl.
 */
@Component("debinwsSolicitudesBO")
@Qualifier("debinWSSolicitudesBOImpl")
public class DebinWSSolicitudesBOImpl extends DebinWSBOImpl implements DebinWSSolicitudesBO {

    /** The solicitudes debin WSDAO. */
    @Autowired
    @Qualifier("debinwsSolicitudesDAO")
    private DebinWSSolicitudesDAO solicitudesDebinWSDAO;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DebinWSSolicitudesBOImpl.class);
    
    /** The Constant UNO_STRING. */
    private static final String UNO_STRING = "1";

    /** The Constant DOS_STRING. */
    private static final String DOS_STRING = "2";
    
    /** The Constant CERO_STRING. */
    private static final String CERO_STRING = "0";

    /** The Constant CUENTA_INACTIVA. */
    private static final String CUENTA_INACTIVA = "0160";
    
    /** The Constant ALIAS_NO_EXISTE. */
    private static final String ALIAS_NO_EXISTE = "0110";

    /** The Constant ALIAS_ELIMINADO. */
    private static final String ALIAS_ELIMINADO = "0190";

    /** The Constant MONEDA_INCOMPATIBLE. */
    private static final String MONEDA_INCOMPATIBLE = "36";
    
    /** fecha *. */
    private static final String FORMATO_FECHA = "dd/MM/yyyy";
    
    /** The valor . */
    @Value("${DEBIN.SOLICITUD_DIAS_VENCIMIENTO}")
    private int cantidadDiasVencimiento;
    
    /** The alias cbu DAO. */
    @Autowired
    private AliasCbuDAO aliasCbuDAO;
    
    /**
	 * consultaCuentasAdheridas.
	 *
	 * @param solicitudesDebinInDTO
	 *            the solicitudes debin in DTO
	 * @return the respuesta
	 */
    public Respuesta<CuentasAdheridasOutDTO> consultaCuentasAdheridas(CuentasAdheridasInDTO solicitudesDebinInDTO) {
        LOGGER.info("DebinWSSolicitudesBOImpl inicio consultaCuentasAdheridas");
        try {
            RequestConsulta request = generarResquestConsultaCuentasAdheridas(solicitudesDebinInDTO);
            LOGGER.info("DebinWSSolicitudesBOImpl iniciando llamada DAO consulta cuentas adheridas");
            ResponseVendedor responseVendedor = solicitudesDebinWSDAO.consultarCuentasAdheridas(request);
            if (responseVendedor.getError() != null && !CODIGO_DEBIN_OK.equals(responseVendedor.getError().getCodigo())) {
                LOGGER.info("DebinWSSolicitudesBOImpl consulta de cuentas adheridas, codigo de respuesta con error");
                estadisticaManager.add(EstadisticasConstants.CONSULTA_CUENTAS_ADHERIDAS_DEBINWS,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                if (DebinWSConstants.CONSULTA_VENDEDOR_NO_ADHERIDO_CODIGO_ERROR.equals(responseVendedor.getError().getCodigo())) {
                    return respuestaFactory.crearRespuestaWarning("", TipoError.DEBINWS_NO_ADHERIDO,  CodigoMensajeConstantes.DEBINWS_NO_ADHERIDO);
                }else {
                    return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
                }
            }
            LOGGER.info("DebinWSSolicitudesBOImpl consulta de cuentas adheridas, codigo respuesta OK");
            CuentasAdheridasOutDTO cuentasAdheridasOutDTO = generarRtaConsultaAdhesion(responseVendedor);
            estadisticaManager.add(EstadisticasConstants.CONSULTA_CUENTAS_ADHERIDAS_DEBINWS,  EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            return respuestaFactory.crearRespuestaOk(cuentasAdheridasOutDTO);
        } catch (DAOException e) {
            LOGGER.error("DebinWSSolicitudesBOImpl Error obteniendo cuentas adheridas a debin");
            if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
                estadisticaManager.add(EstadisticasConstants.CONSULTA_CUENTAS_ADHERIDAS_DEBINWS,EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
                return  this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_TIMEOUT,CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            }else {
                estadisticaManager.add(EstadisticasConstants.CONSULTA_CUENTAS_ADHERIDAS_DEBINWS,EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return  this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO,CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            }
        }
    }

    /**
	 * Generar rta consulta adhesion.
	 *
	 * @param responseVendedor
	 *            the response vendedor
	 * @return the cuentas adheridas out DTO
	 */
    private CuentasAdheridasOutDTO generarRtaConsultaAdhesion(ResponseVendedor responseVendedor) {
        CuentasAdheridasOutDTO rtaCuentas = new CuentasAdheridasOutDTO();
        rtaCuentas.setCuentasAdheridas(responseVendedor.getCuentas());
        return rtaCuentas;
    }

    /**
	 * Generar resquest consulta cuentas adheridas.
	 *
	 * @param solicitudesDebinInDTO
	 *            the solicitudes debin in DTO
	 * @return the request consulta
	 * @throws DAOException
	 *             the DAO exception
	 */
    private RequestConsulta generarResquestConsultaCuentasAdheridas(CuentasAdheridasInDTO solicitudesDebinInDTO) throws DAOException {
        RequestConsulta rq = new RequestConsulta();
        rq.setCanal(solicitudesDebinInDTO.getCanal());
        rq.setCuit(obtenerCuitCliente());
        rq.setIp(sesionCliente.getIpCliente());
        rq.setNroDocumento(ISBANStringUtils.eliminarCeros(solicitudesDebinInDTO.getNroDocumento()));
        rq.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(solicitudesDebinInDTO.getTipoDocumento()).getCodigoNumero());
        return rq;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSSolicitudesBO#validarCbuDebin(ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuInDTO)
     */
    @Override
    public Respuesta<ValidarCbuOutDTO> validarCbuDebin(ValidarCbuInDTO validarCbuInDTO) {
        LOGGER.info("DebinWSSolicitudes BO validarCbuAliasDebin");
        try {
            ConsultaCBUEntityIn entityIn = generarConsultaCbuRequest(validarCbuInDTO);
            LOGGER.info("DebinWSSolicitudesBO iniciando llamada DAO CNSTITCBU");
            ConsultaCbuEntityOut entityOut = solicitudesDebinWSDAO.consultarCNSTITCBU(entityIn);
            //SI NO VIENEN DATOS DEL TITULAR SE GENERA RESPUESTA ERROR
            if (entityOut.getTitular() == null || "".equals(entityOut.getTitular().trim())){
                estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_CBU, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return respuestaFactory.crearRespuestaError(null, TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ERROR_CBU_TITULARIDAD); //1568 en transferencias
            }
            ValidarCbuOutDTO dtoOut = generarValidarCbuOutDTO(entityOut);
            estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_CBU, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            LOGGER.info("DebinSolicitudes BO  Validar CBU Respuesta OK");
            return respuestaFactory.crearRespuestaOk(dtoOut);
           //DESTINATARIO NO VERIFICADO CODIGOS ERROR 1 y 2 
        }  catch (DebinDestinatarioNoVerificadoException e) {
            estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_CBU, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            LOGGER.error("Error validando cbu DAO DestinatarioNoVerificadoException");
            return respuestaFactory.crearRespuestaError(null, TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ERROR_DESTINATARIO_NO_VERIFICADO); //1567 en transferencias
            //CBU INVALIDO CODIGOS 36 57 50  56 Default
        } catch (DebinCBUInvalidoDAOException e) {
            estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_CBU, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            LOGGER.error("Error validando cbu DAO CBUInvalidoDAOException con codigo ERROR: {}", e.getErrorCode());
            if (DebinWSUtils.ERROR_CODIGO_36 == e.getErrorCode() || DebinWSUtils.ERROR_CODIGO_57 == e.getErrorCode()) {
                return respuestaFactory.crearRespuestaError(null, TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ERROR_CBU_INCORRECTO); //1566 en transferencias
            }
            else if (DebinWSUtils.ERROR_USUARIOTARJETA_INEXISTENTE_50 == e.getErrorCode()
                || DebinWSUtils.ERROR_USUARIOTARJETA_INEXISTENTE_56 == e.getErrorCode()) {
                
                return respuestaFactory.crearRespuestaError(null, TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ERROR_CBU_TITULARIDAD); //1568 en transferencias
            }
            
            return respuestaFactory.crearRespuestaError(null, TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ERROR_CBU_TITULARIDAD);
        
        } catch (DAOException e) {
            if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
                LOGGER.error("Error validando cbu DAO TimeOutException");
                estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_CBU, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
                return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ERROR_CBUALIAS_TIMEOUT); // 
            }
            LOGGER.error("Error validando cbu DAO Exception");
            estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_CBU, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_CBU_TITULARIDAD);
        }
    }

    /**
	 * Generar validar cbu out DTO.
	 *
	 * @param entityOut
	 *            the entity out
	 * @return the validar cbu out DTO
	 */
    private ValidarCbuOutDTO generarValidarCbuOutDTO(ConsultaCbuEntityOut entityOut) {
        ValidarCbuOutDTO outDTO = new ValidarCbuOutDTO();
        outDTO.setCuit(entityOut.getCuit1());
        outDTO.setTitular(StringUtils.trimToEmpty(entityOut.getTitular()));
        outDTO.setDescripcionBanco(StringUtils.trimToEmpty(entityOut.getBandes()));
        return outDTO;
    }

    /**
	 * generarConsultaCBURequest.
	 *
	 * @param validarCbuAliasInDTO
	 *            the validar cbu alias in DTO
	 * @return the consulta CBU entity in
	 */
    private ConsultaCBUEntityIn generarConsultaCbuRequest(ValidarCbuInDTO validarCbuAliasInDTO) {
        ConsultaCBUEntityIn rq = new ConsultaCBUEntityIn();
        rq.setCbuDestino(validarCbuAliasInDTO.getCbuDestino());
        rq.setNroCuenta(validarCbuAliasInDTO.getNroCuenta());
        rq.setNroSucursal(validarCbuAliasInDTO.getNroSucursal());
        rq.setTipoCuenta(validarCbuAliasInDTO.getTipoCuenta());
        rq.setCliente(sesionCliente.getCliente());
        rq.setDireccionIP(sesionCliente.obtenerIpV4SinPuntos());
        rq.setNroTarjeta(validarCbuAliasInDTO.getNroTarjeta());
        return rq;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSSolicitudesBO#validarAliasDebin(ar.com.santanderrio.obp.servicios.debinws.dto.ValidarAliasInDTO)
     */
    @Override
    public Respuesta<ValidarAliasOutDTO> validarAliasDebin(ValidarAliasInDTO validarAliasInDTO) {
        LOGGER.info("DebinWS Validacion de Alias para solicitud de Debin");
        try {
            LOGGER.info("DebinWS Validacion de Alias Generando Request de entrada");
            //GENERAMOS REQUEST PARA CONSULTAR TITULARIDAD EXTENDIDA
            ConsultarDatosTitularidadExtendido consultarDatosTitularidadExtendidoIn = generarRequestWsTitularidadExtendida(validarAliasInDTO);
            LOGGER.info("DebinWS Validacion de Alias Llamando validacion alias Dao");
            
            
            //CONSULTA DE TITULARIDAD EXTENDIDA
            ConsultarDatosTitularidadExtendidoResponse response =  aliasCbuDAO.consultarDatosTitularidadExtendido(consultarDatosTitularidadExtendidoIn);
            

            //ERRORES CON CODIGO DETERMINADO 
            if (response.getTitularidadExtendido() == null) {
                estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_ALIAS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                if (StringUtils.equals(response.getCodigo(), CUENTA_INACTIVA)) {
                    return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU,
                            CodigoMensajeConstantes.DEBINWS_ALIAS_CON_CUENTA_INACTIVA); //1572
                } else if (StringUtils.equals(response.getCodigo(), ALIAS_ELIMINADO)) {
                    return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU,
                            CodigoMensajeConstantes.DEBINWS_ALIAS_INEXISTENTE_ELIMINADO); //1570
                } else if (StringUtils.equals(response.getCodigo(), MONEDA_INCOMPATIBLE)) {
                    return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU,
                            CodigoMensajeConstantes.DEBINWS_CLIENTE_NO_COINCIDE_MONEDA); //1595
                } else if (StringUtils.equals(response.getCodigo(), ALIAS_NO_EXISTE)) {
                    return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU,
                            CodigoMensajeConstantes.DEBINWS_FORMATO_ALIAS_INEXISTENTE); //1571
                } else {
                    return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO,
                            CodigoMensajeConstantes.DEBINWS_CONFIGURACION_ALIAS_ERROR);
                }
                
            }
            ValidarAliasOutDTO outDTO = generarValidarAliasOutDTO(response,validarAliasInDTO);
            LOGGER.info("DebinWS BO  Validacion por ALIAS OK");
            //RESPUESTA OK
            estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_ALIAS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            return respuestaFactory.crearRespuestaOk(outDTO);
        } catch (DAOException e) {
            estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_ALIAS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            LOGGER.error("DebinWS Validacion Alias Exception DAO");
            return respuestaFactory.crearRespuestaError(null, TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_CONFIGURACION_ALIAS_ERROR);
        } catch (AliasCorrespondienteCuentaPropiaException e) {
            estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_ALIAS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFactory.crearRespuestaError(null, TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_SOLICITUD_ERROR_MISMA_CUENTA); // 1598
        } catch (AliasCorrespondienteCuentaPropiaUnicaException e) {
            estadisticaManager.add(EstadisticasConstants.DEBINWS_CONSULTA_TITULARIDAD_ALIAS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFactory.crearRespuestaError(null, TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_SOLICITUD_ERROR_MISMA_CUENTA_UNICA); //1562
        }
    }
    
    /**
	 * Generar validar alias out DTO.
	 *
	 * @param response
	 *            the response
	 * @param validarAliasInDTO
	 *            the validar alias in DTO
	 * @return the validar alias out DTO
	 * @throws AliasCorrespondienteCuentaPropiaException
	 *             the alias correspondiente cuenta propia exception
	 * @throws AliasCorrespondienteCuentaPropiaUnicaException
	 *             the alias correspondiente cuenta propia unica exception
	 */
    private ValidarAliasOutDTO generarValidarAliasOutDTO(ConsultarDatosTitularidadExtendidoResponse response, ValidarAliasInDTO validarAliasInDTO) throws AliasCorrespondienteCuentaPropiaException,AliasCorrespondienteCuentaPropiaUnicaException {
        TitularidadExtendido  responseTE = response.getTitularidadExtendido();
        String moneda = responseTE.getCtaDestino().getMoneda().getCodigo();
        if (StringUtils.equals(responseTE.getCtaDestino().getNumeroCBU(), validarAliasInDTO.getCuenta().getCbu())) {
            if ((TransferenciaUtil.obtenerCantidadDeCuentasPesos(sesionCliente.getCliente().getCuentas()) <= 1 && moneda.equals("1"))
                    || (TransferenciaUtil.obtenerCantidadDeCuentasDolares(sesionCliente.getCliente().getCuentas()) <= 1
                            && moneda.equals("2"))) {
                LOGGER.error("El alias ingresado corresponde a la cuenta origen propia");
                throw new AliasCorrespondienteCuentaPropiaUnicaException();
            } else  {
                LOGGER.error("El alias ingresado corresponde a la cuenta unica origen propia");
                throw new AliasCorrespondienteCuentaPropiaException();
            }
        }
        
        CuentaDTO cuentaDestino = responseTE.getCtaDestino();
        ValidarAliasOutDTO outDTO = new ValidarAliasOutDTO();
        outDTO.setCuit(responseTE.getCuits().get(0));
        outDTO.setCbu(cuentaDestino.getNumeroCBU());
        outDTO.setTitular(responseTE.getNombreTitular());
        outDTO.setDescripcionBanco(responseTE.getNombreBanco());
        return outDTO;
    }

    /**
	 * Generar request ws titularidad extendida.
	 *
	 * @param validarAliasInDTO
	 *            the validar alias in DTO
	 * @return the consultar datos titularidad extendido
	 */
    private ConsultarDatosTitularidadExtendido generarRequestWsTitularidadExtendida(ValidarAliasInDTO validarAliasInDTO) {
        Cliente cliente = sesionCliente.getCliente();
        String tipoDoc = TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(sesionCliente.getCliente().getTipoDocumento()).getCodigoNumero();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNroDocumento(cliente.getDni());
        usuarioDTO.setTipoDocumento(tipoDoc);
        TerminalDTO terminalDTO = new TerminalDTO(validarAliasInDTO.getUserAgent(), sesionCliente.getIpCliente()); 
        CuentaDTO cuentaDTO = new CuentaDTO(obtenerCodigoTipoMonedaPorTipoDeCuenta(validarAliasInDTO.getCuenta().getTipoCuentaEnum()),
                                    obtenerNroCuenta(validarAliasInDTO.getCuenta()), 
                                    validarAliasInDTO.getCuenta().getCbu(), 
                                    obtenerCodigoTipoCuenta(validarAliasInDTO.getCuenta().getTipoCuentaEnum()));

        return  new ConsultarDatosTitularidadExtendido(usuarioDTO, terminalDTO, cuentaDTO, validarAliasInDTO.getAlias());


    }

    /**
     * Obtener codigo tipo moneda en base a la cuenta.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the string
     */
    private String obtenerCodigoTipoMonedaPorTipoDeCuenta(TipoCuenta tipoCuenta) {
        if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS)) {
            return UNO_STRING;
        }
        if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_DOLARES)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_DOLARES)) {
            return DOS_STRING;
        }
        return StringUtils.EMPTY;
    }
    
    /**
     * Obtener codigo en base al tipo de cuenta.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the string
     */
    private String obtenerCodigoTipoCuenta(TipoCuenta tipoCuenta) {
        if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS)
                || tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_DOLARES)) {
            return CERO_STRING;
        }
        if (tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_DOLARES)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA)) {
            return UNO_STRING;
        }
        return StringUtils.EMPTY;
    }
    
    /**
     * Obtener nro cuenta.
     *
     * @param cuenta
     *            the cuenta
     * @return the string
     */
    private String obtenerNroCuenta(Cuenta cuenta) {
        return cuenta.getNroSucursal().concat("00").concat(cuenta.getNroCuentaProducto());
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSSolicitudesBO#solicitarDebin(ar.com.santanderrio.obp.servicios.debinws.dto.CreacionDebinWSInDTO)
     */
    @Override
    public Respuesta<CreacionDebinWSOutDTO> solicitarDebin(CreacionDebinWSInDTO creacionDebinInDTO) {
        try {
            LOGGER.info("DebinWSSolicitudesBO se va a solicitar un nuevo debin");
            RequestNuevoDebinV3 requestNuevoDebin = generarRequestNuevoDebin(creacionDebinInDTO);
            //ResponseNuevoDebin responseNuevoDebin = solicitudesDebinWSDAO.solicitarDebin(requestNuevoDebin);
            ResponseNuevoDebin responseNuevoDebin = solicitudesDebinWSDAO.solicitarDebinV3(requestNuevoDebin);

            if (responseNuevoDebin.getError() != null && responseNuevoDebin.getError().getCodigo() != CODIGO_DEBIN_OK  ) {
                LOGGER.info("DebinWSSolicitudesBOImpl solicitud de debin, codigo de respuesta con error");
                estadisticaManager.add(EstadisticasConstants.DEBINWS_SOLICITUD_DEBIN,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_SOLICITUD,  CodigoMensajeConstantes.DEBINWS_ERROR_SOLICITUD);
            }
            estadisticaManager.add(EstadisticasConstants.DEBINWS_SOLICITUD_DEBIN, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            CreacionDebinWSOutDTO outDTO = generarCreacionDebinOutDTO(responseNuevoDebin);
            LOGGER.info("DebinWSSolicitudesBO respuesta OK de solicitud de debin");
            return respuestaFactory.crearRespuestaOk(outDTO);
        } catch (DAOException e) {
            LOGGER.error("DebinWSSolicitudesBO ERROR solicitando nuevo debin");
            estadisticaManager.add(EstadisticasConstants.DEBINWS_SOLICITUD_DEBIN, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
                estadisticaManager.add(EstadisticasConstants.DEBINWS_SOLICITUD_DEBIN,EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
                return  this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_TIMEOUT,CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            }
            return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
        } catch (ImporteConvertException e) {
            LOGGER.error("DebinWSSolicitudesBO ERROR parseando importe ingresado");
            return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);

        } catch (ParseException e) {
            LOGGER.error("DebinWSSolicitudesBO ERROR  parseando fecha de vencimiento ingresada");
            return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);

        } catch (DatatypeConfigurationException e) {
            LOGGER.error("DebinWSSolicitudesBO ERROR parseando fecha de vencimiento");
            return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
        }
    }

	/**
	 * Generar request nuevo debin V3.
	 *
	 * @param creacionDebinInDTO
	 *            the creacion debin in DTO
	 * @return the request nuevo debin
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 * @throws ParseException
	 *             the parse exception
	 * @throws DatatypeConfigurationException
	 *             the datatype configuration exception
	 */
    private RequestNuevoDebinV3 generarRequestNuevoDebin(CreacionDebinWSInDTO creacionDebinInDTO) throws DAOException, ImporteConvertException, ParseException, DatatypeConfigurationException {
    	RequestNuevoDebinV3 rq = new RequestNuevoDebinV3();
        rq.setCanal(creacionDebinInDTO.getCanal());
        rq.setCuit(obtenerCuitCliente());
        rq.setIp(sesionCliente.getIpCliente());
        rq.setNroDocumento(ISBANStringUtils.eliminarCeros(creacionDebinInDTO.getNroDocumento()));
        rq.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(creacionDebinInDTO.getTipoDocumento()).getCodigoNumero());
        rq.setMismoTitular(rq.getCuit().equals(creacionDebinInDTO.getCuitDestinatario()) ? 1 : 0);
        rq.setCategoriaLimite(creacionDebinInDTO.getCategoriaLimite());
        rq.setRecurrencia(false);
        
        CompradorDebinDTO comprador = new CompradorDebinDTO();
        comprador.setTitular(creacionDebinInDTO.getTitularDestinatario());
        comprador.setCuit(creacionDebinInDTO.getCuitDestinatario());
        CuentaDebinDTO cuentaDestinatario = new CuentaDebinDTO();
        cuentaDestinatario.setCbu(creacionDebinInDTO.getCbuDestinatario());
        cuentaDestinatario.setAlias(creacionDebinInDTO.getAliasDestinatario());
        comprador.setCuenta(cuentaDestinatario );
        rq.setComprador(comprador);
        
        DetalleDebinDTO detalleDebin = new DetalleDebinDTO();
        detalleDebin.setConcepto(creacionDebinInDTO.getConcepto());
        detalleDebin.setDescripcion(creacionDebinInDTO.getDescripcion());
        detalleDebin.setFechaExpiracion(obtenerFechaVencimiento(creacionDebinInDTO.getFechaVencimiento()));
        detalleDebin.setFechaCreacion(obtenerFechaCreacion());
        detalleDebin.setImporte(ISBANStringUtils.convertirImporte(creacionDebinInDTO.getImporte()).toString());
        detalleDebin.setMoneda(creacionDebinInDTO.getMoneda());
        rq.setDetalle(detalleDebin);
        
        VendedorDebinDTO vendedor = new VendedorDebinDTO();
        vendedor.setCuit(obtenerCuitCliente());
        vendedor.setTitular(sesionCliente.getCliente().obtenerNombreYApellidos());
        vendedor.setTerminal(StringUtils.EMPTY);
        CuentaDebinDTO cuentaVendedor = new CuentaDebinDTO();
        cuentaVendedor.setCbu(creacionDebinInDTO.getCbuOrigen());
        vendedor.setCuenta(cuentaVendedor );
        rq.setVendedor(vendedor);

        rq.setControlLimite(false);

        return rq;
    }

    /**
	 * Obtener fecha creacion.
	 *
	 * @return the XML gregorian calendar
	 * @throws DatatypeConfigurationException
	 *             the datatype configuration exception
	 */
    private XMLGregorianCalendar obtenerFechaCreacion() throws DatatypeConfigurationException {
        GregorianCalendar respuesta = new GregorianCalendar();
        respuesta.setTime(new Date());
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(respuesta);
    }

    /**
	 * Obtener fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the fecha vencimiento
	 * @return the XML gregorian calendar
	 * @throws ParseException
	 *             the parse exception
	 * @throws DatatypeConfigurationException
	 *             the datatype configuration exception
	 */
    private XMLGregorianCalendar obtenerFechaVencimiento(String fechaVencimiento) throws ParseException, DatatypeConfigurationException {
        Date fechaVencimientoD = new SimpleDateFormat(FORMATO_FECHA).parse(fechaVencimiento);
        GregorianCalendar respuesta = new GregorianCalendar();
        respuesta.setTime(fechaVencimientoD);
        respuesta.set(Calendar.HOUR_OF_DAY, 23);     
        respuesta.set(Calendar.MINUTE, 59);
        respuesta.set(Calendar.SECOND, 59);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(respuesta);
    }
    

    /**
	 * Generar creacion debin out DTO.
	 *
	 * @param responseNuevoDebin
	 *            the response nuevo debin
	 * @return the creacion debin WS out DTO
	 */
    private CreacionDebinWSOutDTO generarCreacionDebinOutDTO(ResponseNuevoDebin responseNuevoDebin) {
        CreacionDebinWSOutDTO outDTO = new CreacionDebinWSOutDTO();
        DetalleDebinDTO detalle =  responseNuevoDebin.getDetalleDebin();
        if (detalle != null) {
            EstadoDebinDTO estadoDTO = detalle.getEstadoDebin();
            outDTO.setEstado(estadoDTO == null ? "" : estadoDTO.getCodigo()); 
            outDTO.setIdComprobante(detalle.getIdComprobante());
            outDTO.setIdDebin(detalle.getIdDebin());
        }

        return outDTO;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSSolicitudesBO#descargarComprobante(ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteSolicitudDTO)
     */
    @Override
    public Respuesta<Reporte> descargarComprobante(ComprobanteSolicitudDTO comprobanteDTO) {
        LOGGER.info("DebinWS Solicitud _  Iniciando descargarComprobante");
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        try {
                Reporte reporte = solicitudesDebinWSDAO.descargarComprobante(comprobanteDTO);
                respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
                respuestaReporte.setRespuesta(reporte);
            } catch (Exception e) {
                return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
            }
        LOGGER.info("DebinWS Solicitud _ Finalizando generacion de comprobante");
        return respuestaReporte;        
      }
       
}