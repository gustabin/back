package ar.com.santanderrio.obp.servicios.debinws.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSSolicitudesBO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteSolicitudDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CreacionDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CreacionDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.DatosSolicitudDebinDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarAliasInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarAliasOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSSolicitudesManager;
import ar.com.santanderrio.obp.servicios.debinws.utils.DebinWSUtils;
import ar.com.santanderrio.obp.servicios.debinws.view.ConceptoDebinWSView;
import ar.com.santanderrio.obp.servicios.debinws.view.CuentasAdheridasDebinOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.SolicitarDebinView;
import ar.com.santanderrio.obp.servicios.debinws.view.ValidarCbuAliasDebinInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ValidarCbuAliasDebinOutView;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;

/**
 * The Class DebinWSSolicitudesManagerImpl.
 */
@Component("debinWSSolicitudesManager")
@Qualifier("debinWSSolicitudesManagerImpl")
public class DebinWSSolicitudesManagerImpl extends DebinWSManagerImpl implements DebinWSSolicitudesManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DebinWSSolicitudesManagerImpl.class);
    
    /** The solicitudes debin WSBO. */
    @Autowired
    @Qualifier("debinWSSolicitudesBOImpl")
    private DebinWSSolicitudesBO solicitudesDebinWSBO;
        
    /** The legal BO. */
    @Autowired
    protected LegalBO legalBO;
    
    /** The valor . */
    @Value("${DEBIN.SOLICITUD_DIAS_VENCIMIENTO}")
    private String cantidadDiasVencimiento;
    
    /** The valor . */
    @Value("${TRJCOORD.OPERAINDISTINTO.SOLICITUDDEBIN}")
    private String valorDesafioSolicitudDebin;

    @Autowired
    private DesafioOperacionRSA<SolicitarDebinView> solicitarDebinViewDesafioOperacionRSA;
    
    @Autowired
    private ClienteManager clienteManager;
    
    /** The Constant MSJ_INFO_OBTENIENDO_TARJETA_BANELCO_ACTIVA. */
    private static final String MSJ_INFO_OBTENIENDO_TARJETA_BANELCO_ACTIVA = "Obteniendo tarjetas banelcos activas...";

    /** The Constant TARJETA_CREDITO_ACTIVA. */
    private static final String TARJETA_BANELCO_ACTIVA = "01";
    
    /** * TIPO CUENTA_BANELCO. */
    private static final int TIPO_CUENTA_BANELCO = 5;
    
    /** The Constant MSJ_INFO_NO_EXISTEN_TARJETA_BANELCO_ACTIVAS. */
    private static final String MSJ_INFO_NO_EXISTEN_TARJETA_BANELCO_ACTIVAS = "El cliente no tiene una tarjeta banelco activa.";

    /** The categoria limite default. */
    @Value("${DEBIN.CATEGORIA_LIMITE_DEFAULT}")
    private int categoriaLimiteDefault;

	/** The rsa manager. */
	@Autowired
	private RsaManager rsaManager;
	
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSSolicitudesManager#ingresoSolicitarDebin()
     */
    @Override
    public Respuesta<CuentasAdheridasDebinOutView> ingresoSolicitarDebin() {
        //VALIDACION USUARIO CON TOKEN - se comenta para ingresar con coordenadas tambien
        /*if (!(sesionCliente.getCliente().tieneSoftToken())) {
            LOGGER.info("DebinWSSolicitudesManager warning cliente sin token");
            CuentasAdheridasDebinOutView outView = new CuentasAdheridasDebinOutView();
            outView.setMensajeSinDesafio(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_SOLICITUDES_ERROR_TOKEN).getMensaje());
            outView.setUrlAyuda(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_URL_AYUDA).getMensaje());
            estadisticaManager.add(EstadisticasConstants.DEBINWS_SOLICITAR_TOKEN,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return this.respuestaFactory.crearRespuestaWarning(outView, "", TipoError.DEBINWS_SOLICITUDES_ERROR_TOKEN, CodigoMensajeConstantes.DEBINWS_SOLICITUDES_ERROR_TOKEN);
        }*/
        estadisticaManager.add(EstadisticasConstants.DEBINWS_SOLICITAR_TOKEN,  EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        
        CuentasAdheridasInDTO solicitudesDebinInDTO = generarSolicitudesDebinInDTO();
        //CONSULTA DE  CUENTAS ADHERIDAS
        Respuesta<CuentasAdheridasOutDTO> respuestaBOCuentasAdheridas = solicitudesDebinWSBO.consultaCuentasAdheridas(solicitudesDebinInDTO);
        if (EstadoRespuesta.ERROR.equals(respuestaBOCuentasAdheridas.getEstadoRespuesta())) {
            //ERROR GENERICO DE SERVICIOS
            LOGGER.info("DebinWSSolicitudesManager Error Generico");
            return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
        }else if (EstadoRespuesta.WARNING.equals(respuestaBOCuentasAdheridas.getEstadoRespuesta())){
            //VENDEDOR NO ADHERIDO
            LOGGER.info("DebinWSSolicitudesManager Vendedor no adherido");
            return respuestaFactory.crearRespuestaWarning("", TipoError.DEBINWS_NO_ADHERIDO,  CodigoMensajeConstantes.DEBINWS_NO_ADHERIDO);
        }else {
            //RESPUESTA OK 
            CuentasAdheridasDebinOutView outView;
            try {
                //CONVERTIMOS LAS CUENTAS ADHERIDAS PARA MOSTRAR EN COMBO SELECTOR
                outView = super.generarCuentasAdheridasView(respuestaBOCuentasAdheridas.getRespuesta());
            } catch (BusinessException e) {
                LOGGER.error("ERROR_OBTENIENDO_CUENTAS_ORIGEN");
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
            }
            if (outView.getCuentasDolares().isEmpty() && outView.getCuentasPesos().isEmpty()) {
                LOGGER.info("DebinWSSolicitudesManager Respuesta OK pero no se encontraron cuentas");
                return respuestaFactory.crearRespuestaWarning("", TipoError.DEBINWS_NO_ADHERIDO,  CodigoMensajeConstantes.DEBINWS_NO_ADHERIDO);
            }
            //GUARDAMOS LAS CUENTAS ADHERIDAS EN SESION
            sesionParametros.setCuentasAdheridasDebin(respuestaBOCuentasAdheridas.getRespuesta());
            
            LOGGER.info("DebinWSSolicitudesManager Respuesta OK");
            return respuestaFactory.crearRespuestaOk(outView);
        }
    }

    /**
	 * generarSolicitudesDebinInDTO.
	 *
	 * @return the cuentas adheridas in DTO
	 */
    private CuentasAdheridasInDTO generarSolicitudesDebinInDTO() {
        Cliente cliente = sesionCliente.getCliente();
        CuentasAdheridasInDTO inDTO = new CuentasAdheridasInDTO();
        inDTO.setCanal(CANAL_DEFAULT);
        inDTO.setNroDocumento(cliente.getDni());
        inDTO.setTipoDocumento(cliente.getTipoDocumento());
        return inDTO;
    }

    /**
	 * validarCbuAliasDebin.
	 *
	 * @param validarCbuAliasInView
	 *            the validar cbu alias in view
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
    @Override
    public Respuesta<ValidarCbuAliasDebinOutView> validarCbuAliasDebin(ValidarCbuAliasDebinInView validarCbuAliasInView, String userAgent) {
        LOGGER.info("DebinWSSolicitudesManager inicio validarCbuAlias");
        Respuesta<ValidarCbuAliasDebinOutView> respuestaView = new Respuesta<ValidarCbuAliasDebinOutView>();
        if (validarCbuAliasInView.getCbu() != null && !validarCbuAliasInView.getCbu().isEmpty()) {
            //VALIDACION POR CBU CNSTITCBU
        	return validarCbu(validarCbuAliasInView, respuestaView);
        } else if (validarCbuAliasInView.getAlias() != null && !validarCbuAliasInView.getAlias().isEmpty()) {
            //VALIDACION ALIAS POR WS PRISMA TITULARIDAD EXTENDIDA
            return validarAlias(validarCbuAliasInView, respuestaView, userAgent);
        }
        LOGGER.info("DebinWS Manager Error validacion CBU/Alias no hay datos para validar");
        return respuestaFactory.crearRespuestaError(null,TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
    }

    /**
	 * Validar alias.
	 *
	 * @param validarCbuAliasInView
	 *            the validar cbu alias in view
	 * @param respuestaView
	 *            the respuesta view
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
    private Respuesta<ValidarCbuAliasDebinOutView> validarAlias(ValidarCbuAliasDebinInView validarCbuAliasInView,
            Respuesta<ValidarCbuAliasDebinOutView> respuestaView, String userAgent) {
        LOGGER.info("DebinWSSolicitudesManager VALIDACION POR ALIAS");

        //VALIDACION DE FORMATO
        if (!ISBANStringUtils.isFormatoAliasValido(validarCbuAliasInView.getAlias())) {
            LOGGER.info("Alias con formato incorrecto");
            return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ALIAS_INCORRECTO); //1571 en transferencias

        }
        //SE VALIDA LA EXISTENCIA DE CUENTA ADHERIDA CON MIMSA MONEDA
        ValidarAliasInDTO validarAliasInDTO = generarValidarAliasInDTO(validarCbuAliasInView, userAgent);
        if (validarAliasInDTO == null) {
            return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ERROR_ALIAS_MONEDA_CUENTA); //1569 en transferencias
        }
        
        //VALIDACION TITULARIDAD EXTENDIDA ALIAS
        Respuesta<ValidarAliasOutDTO> rtaAliasBO = solicitudesDebinWSBO.validarAliasDebin(validarAliasInDTO);
        
        if (EstadoRespuesta.OK.equals(rtaAliasBO.getEstadoRespuesta())) {
            LOGGER.info("DebinWS Validacion Alias OK");
            ValidarAliasOutDTO outAliasDTO = rtaAliasBO.getRespuesta();
            ValidarCbuAliasDebinOutView  outView = new ValidarCbuAliasDebinOutView();
            List<ConceptoDebinWSView> conceptos = buildConceptos(false);
            outView.setConceptos(conceptos);
            outView.setBancoDestinatario(outAliasDTO.getDescripcionBanco());
            outView.setCbuDestinatario(outAliasDTO.getCbu());
            outView.setCuitDestinatario(ISBANStringUtils.formatearCuil(outAliasDTO.getCuit()));
            outView.setNombreDestinatario(outAliasDTO.getTitular());
            outView.setAliasDestinatario(validarAliasInDTO.getAlias());
            outView.setLegales(obtenerLegales(CodigoMensajeConstantes.LEGALES_SOLICITAR_DEBIN));
            outView.setMoneda(validarCbuAliasInView.getMoneda());
            outView.setCantDiasVencimiento(cantidadDiasVencimiento);
            respuestaView.setRespuesta(outView);
            respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
            //GUARDAMOS LOS DATOS DEL CBU DESTINO EN SESION
            DatosSolicitudDebinDTO datosSolicitud = generarDatosSolicitudAlias(outAliasDTO,validarCbuAliasInView);
            sesionParametros.setDatosSolicitudDebin(datosSolicitud );
            return respuestaView;
        } else {
            LOGGER.info("DebinWS Validacion Alias ERROR");
            //PASAMOS EL ERROR QUE VIENE EN EL BO
            respuestaView.setEstadoRespuesta(rtaAliasBO.getEstadoRespuesta());
            respuestaView.setItemMensajeRespuesta(rtaAliasBO.getItemsMensajeRespuesta());
            respuestaView.setRespuestaVacia(true);
            return respuestaView;
        }
    }

    /**
	 * Generar datos solicitud alias.
	 *
	 * @param outAliasDTO
	 *            the out alias DTO
	 * @param validarCbuAliasInView
	 *            the validar cbu alias in view
	 * @return the datos solicitud debin DTO
	 */
    private DatosSolicitudDebinDTO generarDatosSolicitudAlias(ValidarAliasOutDTO outAliasDTO,
            ValidarCbuAliasDebinInView validarCbuAliasInView) {
        DatosSolicitudDebinDTO datosSolicitud = new DatosSolicitudDebinDTO();
        datosSolicitud.setNombreDestinatario(outAliasDTO.getTitular());
        datosSolicitud.setBancoDestinatario(outAliasDTO.getDescripcionBanco());
        datosSolicitud.setCuitDestinatario(outAliasDTO.getCuit());
        datosSolicitud.setCbuDestinatario(outAliasDTO.getCbu());
        datosSolicitud.setAliasDestinatario(validarCbuAliasInView.getAlias());
        datosSolicitud.setMoneda(validarCbuAliasInView.getMoneda());
        return datosSolicitud;
    }

    /**
	 * Generar validar alias in DTO.
	 *
	 * @param validarCbuAliasInView
	 *            the validar cbu alias in view
	 * @param userAgent
	 *            the user agent
	 * @return the validar alias in DTO
	 */
    private ValidarAliasInDTO generarValidarAliasInDTO(ValidarCbuAliasDebinInView validarCbuAliasInView, String userAgent) {
        try {   
             CuentaDebinDTO cuentaAdheridaConMismaMoneda = DebinWSUtils.obtenerPrimerCuentaAdheridaConMonedaDestino(sesionParametros.getCuentasAdheridasDebin(), validarCbuAliasInView.getMoneda());
             Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(cuentaAdheridaConMismaMoneda.getCbu());
             ValidarAliasInDTO inDTO = new ValidarAliasInDTO();
             inDTO.setCuenta(cuentaCliente);
             inDTO.setAlias(validarCbuAliasInView.getAlias());
             inDTO.setMoneda(validarCbuAliasInView.getMoneda());
             inDTO.setUserAgent(userAgent);
             return inDTO;
        } catch (Exception e) {
            LOGGER.error("DEBINWS Solicitud - ERROR obteninedo cuenta con moneda ingresada");
            return null;
        }
    }

    /**
	 * validarCbu.
	 *
	 * @param validarCbuAliasInView
	 *            the validar cbu alias in view
	 * @param respuestaView
	 *            the respuesta view
	 * @return the respuesta
	 */
    private Respuesta<ValidarCbuAliasDebinOutView> validarCbu(ValidarCbuAliasDebinInView validarCbuAliasInView, Respuesta<ValidarCbuAliasDebinOutView> respuestaView) {
        LOGGER.info("DebinWSSolicitudesManager VALIDACION POR CBU");
        //VALIDACION NRO TARJETA BANELCO ACTIVA
        String numeroTarjetaActiva = obtenerTarjetaBanelcoActiva();
        if (numeroTarjetaActiva == null) {
            LOGGER.info("Error en preparacion consulta");
            return respuestaFactory.crearRespuestaError(null, null, TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ERROR_TARJETA_ACTIVA); // 1573
        }
        //SE CARGAN LOS DATOS DE CUENTA CON LA PRIMER CUENTA EN SESION CON MISMA MONEDA
        ValidarCbuInDTO validarCbuInDTO = generarValidarCbuInDTO(validarCbuAliasInView);
        //SE VALIDA LA EXISTENCIA DE CUENTA ADHERIDA CON MIMSA MONEDA
        if (validarCbuInDTO == null) {
            return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ERROR_CBU_MONEDA_CUENTA); //1569 en transferencias
        }

        validarCbuInDTO.setNroTarjeta(numeroTarjetaActiva);
        //VALIDACION TITULARIDAD CBU
        Respuesta<ValidarCbuOutDTO> rtaBO = solicitudesDebinWSBO.validarCbuDebin(validarCbuInDTO);
        
        if (EstadoRespuesta.OK.equals(rtaBO.getEstadoRespuesta())) {
            LOGGER.info("DebinWS Validacion CBU OK");
            ValidarCbuOutDTO outDTO = rtaBO.getRespuesta();
            ValidarCbuAliasDebinOutView  outView = new ValidarCbuAliasDebinOutView();
            List<ConceptoDebinWSView> conceptos = buildConceptos(false);
            outView.setConceptos(conceptos);
            outView.setBancoDestinatario(outDTO.getDescripcionBanco());
            outView.setCbuDestinatario(validarCbuAliasInView.getCbu());
            outView.setCuitDestinatario(ISBANStringUtils.formatearCuil(outDTO.getCuit()));
            outView.setMoneda(validarCbuAliasInView.getMoneda());
            outView.setNombreDestinatario(outDTO.getTitular());
            outView.setCantDiasVencimiento(cantidadDiasVencimiento);
            outView.setLegales(obtenerLegales(CodigoMensajeConstantes.LEGALES_SOLICITAR_DEBIN));
            respuestaView.setRespuesta(outView);
            respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
            //GUARDAMOS LOS DATOS DEL ALIAS DESTINO EN SESION
            DatosSolicitudDebinDTO datosSolicitud = generarDatosSolicitudCbu(outDTO,validarCbuAliasInView);
            sesionParametros.setDatosSolicitudDebin(datosSolicitud );
            return respuestaView;
        }else {
            LOGGER.info("DebinWS Validacion CBU ERROR");
            //PASAMOS EL ERROR QUE VIENE DESDE EL BO
            respuestaView.setEstadoRespuesta(rtaBO.getEstadoRespuesta());
            respuestaView.setItemMensajeRespuesta(rtaBO.getItemsMensajeRespuesta());
            respuestaView.setRespuestaVacia(true);
            return respuestaView;
        }
    }

    /**
	 * Generar datos solicitud cbu.
	 *
	 * @param outDTO
	 *            the out DTO
	 * @param validarCbuAliasInView
	 *            the validar cbu alias in view
	 * @return the datos solicitud debin DTO
	 */
    private DatosSolicitudDebinDTO generarDatosSolicitudCbu(ValidarCbuOutDTO outDTO,
            ValidarCbuAliasDebinInView validarCbuAliasInView) {
        DatosSolicitudDebinDTO datosSolicitud = new DatosSolicitudDebinDTO();
        datosSolicitud.setNombreDestinatario(outDTO.getTitular());
        datosSolicitud.setBancoDestinatario(outDTO.getDescripcionBanco());
        datosSolicitud.setCuitDestinatario(outDTO.getCuit());
        datosSolicitud.setCbuDestinatario(validarCbuAliasInView.getCbu());
        datosSolicitud.setMoneda(validarCbuAliasInView.getMoneda());
        return datosSolicitud;
    }

    /**
	 * Obtener legales.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the string
	 */
    private String obtenerLegales(String codigoMensaje) {
        try {
            return legalBO.obtenerLegal(codigoMensaje);
        } catch (DAOException e) {
            LOGGER.error("Falla al obtenr legales solicitud debin.");
            return StringUtils.EMPTY;
        }
    }

    /**
	 * generarValidarCbuInDTO.
	 *
	 * @param validarCbuAliasInView
	 *            the validar cbu alias in view
	 * @return the validar cbu in DTO
	 */
    private ValidarCbuInDTO generarValidarCbuInDTO(ValidarCbuAliasDebinInView validarCbuAliasInView) {
        ValidarCbuInDTO validarCbuInDTO = new ValidarCbuInDTO();
        validarCbuInDTO.setCbuDestino(validarCbuAliasInView.getCbu());
        try {
            CuentaDebinDTO cuentaAdheridaConMismaMoneda = DebinWSUtils.obtenerPrimerCuentaAdheridaConMonedaDestino(sesionParametros.getCuentasAdheridasDebin(), validarCbuAliasInView.getMoneda());
            Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(cuentaAdheridaConMismaMoneda.getCbu());
            validarCbuInDTO.setNroCuenta(cuentaCliente.getNroCuentaProducto());
            validarCbuInDTO.setTipoCuenta(cuentaCliente.getTipoCuenta());
            validarCbuInDTO.setNroSucursal(cuentaCliente.getNroSucursal());
            return validarCbuInDTO;
        } catch (Exception e) {
            LOGGER.error("DEBINWS Solicitud - ERROR obteninedo cuenta con moneda ingresada");
            return null;
        }
    }

    
    /**
     * Obtener cuenta con misma moneda y activa (5, TIPO_CUENTA_BANELCO).
     * 
     * @return el numero de la tarjeta de credito
     */
    private String obtenerTarjetaBanelcoActiva() {
        LOGGER.info(MSJ_INFO_OBTENIENDO_TARJETA_BANELCO_ACTIVA);
        List<Cuenta> listaCuentas = sesionCliente.getCliente().getCuentas();
        for (Cuenta cuenta : listaCuentas) {
            if (TARJETA_BANELCO_ACTIVA.equals(cuenta.getEstadoTarjetaCredito())) {
                int tipocuenta = TipoCuenta.fromAbreviatura(cuenta.getTipoCuentaEnum().getAbreviatura()).getCodigo();
                if (tipocuenta == TIPO_CUENTA_BANELCO) {
                    return cuenta.getNroTarjetaCredito();
                }
            }
        }
        LOGGER.info(MSJ_INFO_NO_EXISTEN_TARJETA_BANELCO_ACTIVAS);
        return null;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSSolicitudesManager#solicitarDebin(ar.com.santanderrio.obp.servicios.debinws.view.SolicitarDebinView)
     */
    @Override
    public Respuesta<SolicitarDebinView> solicitarDebin(SolicitarDebinView solicitarDebinInView) {
        LOGGER.info("DebinWS Solicitudes Manager Inicio solicitud de debin");
        //SE CARGA HASH DE VALIDACION PARA VALIDARLO EN LA PROXIMA LLAMADA
        if(!sesionCliente.getTieneTokenRSA()){
        	LOGGER.info("RSA Offline no se permite completar la operacion.");
        	return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_OFFLINE,
        			CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE);
        }
        
        if (solicitarDebinInView.getDesafio() == null) {
            cargarHashValidacion(crearMapaDeLaVista(solicitarDebinInView));
        }

        cargarCuitCompradorVendedor(solicitarDebinInView);
        
        cargarDatosClaveToken(solicitarDebinInView);
        
        // RSA
        solicitarDebinInView.setValidarBloqueo(Boolean.TRUE);
        AutentificacionCodEstDTO autentificacionCodEstDTO = obtenerAutenticacionCodEstDTO(EstadisticasConstants.DEBINWS_CREACION_DEBIN_SOLICITUD_TOKEN);
        Respuesta<SolicitarDebinView> respuestaRsa = solicitarDebinViewDesafioOperacionRSA.validarOperacionRSA(
        		solicitarDebinInView, Integer.parseInt(valorDesafioSolicitudDebin), autentificacionCodEstDTO);
         
        if (respuestaRsa == null || !EstadoRespuesta.OK.equals(respuestaRsa.getEstadoRespuesta())) {	
        	if(respuestaRsa.getRespuesta() != null && respuestaRsa.getRespuesta().getDesafio() != null  && respuestaRsa.getRespuesta().getDesafio().getBloquearUsuario()){
    			LOGGER.info("Inicio de bloqueo de usuario.");
	        	 Respuesta<RsaUpdateUserResponseData> responseBloqueoUsuario = rsaManager.updateUser(new RsaUpdateUserRequestData("LOCKOUT"));
	        	 if(EstadoRespuesta.OK.equals(responseBloqueoUsuario.getEstadoRespuesta())){      
	        		 return respuestaFactory.crearRespuestaError(SolicitarDebinView.class, StringUtils.EMPTY, TipoError.RSA_BLOQUEAR_USUARIO,
			                    CodigoMensajeConstantes.RSA_LOGIN_USUARIO_BLOQUEADO);
	        		 
	        	 } else{
	        		 LOGGER.info("Error al bloquear el usuario");
	        		 return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DENY_RSA, CodigoMensajeConstantes.DENY_RSA);
	        	 }
    		} else{
    			 return respuestaRsa;
    		}
        }

        //SE VALIDA QUE NO SE HAYA MODIFICADO EL IMPORTE/CBU CON EL HASH
        validarHash(crearMapaDeLaVista(solicitarDebinInView));

        //SE CARGAN DATOS DE LA SOLICITUD EN SESION 
        DatosSolicitudDebinDTO detalleSolicitud = sesionParametros.getDatosSolicitudDebin();
        String cbuDestino = detalleSolicitud.getCbuDestinatario();
        String cbuOrigen = solicitarDebinInView.getCbuOrigen();

        //VALIDACION CBU COMPRADOR / VENDEDOR
        if (!cbuDestino.equals(cbuOrigen)) {
           CreacionDebinWSInDTO inDTO = generarCreacionDebinInDTO(solicitarDebinInView, detalleSolicitud); 
           LOGGER.info("DebinWS Solicitudes Manager - Se va a relizar la solicitud de Debin ");
           Respuesta<CreacionDebinWSOutDTO> respuestaBOsolicitar = solicitudesDebinWSBO.solicitarDebin(inDTO);
           String importe = ISBANStringUtils.formatearSaldo(new BigDecimal(solicitarDebinInView.getImporte()));
           String simboloMoneda = MONEDA_PESOS_BANELCO.equalsIgnoreCase(detalleSolicitud.getMoneda()) ? SIMBOLO_PESOS : SIMBOLO_DOLARES;
           String nombreDestinatario = detalleSolicitud.getNombreDestinatario();
           if (EstadoRespuesta.ERROR.equals(respuestaBOsolicitar.getEstadoRespuesta())) {
               LOGGER.info("DebinWS Solicitudes Manager - Solicitud ERROR");
               String tipoError = respuestaBOsolicitar.getItemsMensajeRespuesta().get(0).getTipoError();
               if (TipoError.DEBINWS_ERROR_SOLICITUD.getDescripcion().equals(tipoError)) {
                   return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_SOLICITUD, CodigoMensajeConstantes.DEBINWS_ERROR_SOLICITUD, nombreDestinatario, simboloMoneda, importe);
               } else {
                   return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
               }
           } else if (EstadoRespuesta.OK.equals(respuestaBOsolicitar.getEstadoRespuesta())) {
               String mensajeOK = MessageFormat.format(mensajeManager.obtenerMensajePorCodigo
                       (CodigoMensajeConstantes.DEBINWS_SOLICITUD_OK).getMensaje(),  
                        nombreDestinatario, simboloMoneda, importe);
               solicitarDebinInView.setMensajeFeedback(mensajeOK);
               //SE GUARDAN LOS DETALLES DE SOLICITUD EN SESION
               guardarDatosSolicitud(detalleSolicitud,solicitarDebinInView );
               solicitarDebinInView.setNroComprobante(detalleSolicitud.getNroComprobante());
               solicitarDebinInView.setFechaHoraComprobante(detalleSolicitud.getFechaHoraComprobante());
               LOGGER.info("DebinWS Solicitudes Manager - Solicitud OK");
               return respuestaFactory.crearRespuestaOk(solicitarDebinInView);
           }
        //SI SE INGRESA EL MISMO CBU ORIGEN/DESTINO SE MUESTRA ERROR -MENSAJE PENDIENTE DE DEFINICION   
        } else {
            return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_SOLICITUD, CodigoMensajeConstantes.DEBINWS_ERROR_CBU_ORIGEN_DESTINO); 
        }
        return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
    }

    private void cargarCuitCompradorVendedor(SolicitarDebinView solicitarDebinInView) {
        DatosSolicitudDebinDTO detalleSolicitudSesion = sesionParametros.getDatosSolicitudDebin();
        if (detalleSolicitudSesion.getCuitDestinatario() != null && detalleSolicitudSesion.getCuitDestinatario().contains("-")) {
        	solicitarDebinInView.setCuitComprador(detalleSolicitudSesion.getCuitDestinatario());	
        } else {
        	solicitarDebinInView.setCuitComprador(ISBANStringUtils.agregarGuionesANumeroCuitCuil(detalleSolicitudSesion.getCuitDestinatario()));
        }
        
        if (sesionCliente.getCliente().getNumeroCUILCUIT() != null && sesionCliente.getCliente().getNumeroCUILCUIT().contains("-")) {
        	solicitarDebinInView.setCuitVendedor(sesionCliente.getCliente().getNumeroCUILCUIT());	
        } else {
        	solicitarDebinInView.setCuitVendedor(ISBANStringUtils.agregarGuionesANumeroCuitCuil(sesionCliente.getCliente().getNumeroCUILCUIT()));
        }
    }
    
    private void cargarDatosClaveToken(SolicitarDebinView solicitarDebinInView) {
    	Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				solicitarDebinInView.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				solicitarDebinInView.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
    }
    
    /**
     * Crear mapa de la vista.
     *
     * @param solicitarDebinInView
     *            the opera exterior view
     * @return the map
     */
    public Map<String, Object> crearMapaDeLaVista(SolicitarDebinView solicitarDebinInView) {
        LOGGER.info("Creando hash de los atributos...");
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("importe", solicitarDebinInView.getImporte() != null ? solicitarDebinInView.getImporte() : "");
        mapaAtributos.put("cbuOrigen", solicitarDebinInView.getCbuOrigen() != null ? solicitarDebinInView.getCbuOrigen() : "");
        return mapaAtributos;
    }

    /**
	 * Guardar datos solicitud.
	 *
	 * @param detalleSolicitud
	 *            the detalle solicitud
	 * @param solicitarDebinInView
	 *            the solicitar debin in view
	 */
    private void guardarDatosSolicitud(DatosSolicitudDebinDTO detalleSolicitud, SolicitarDebinView solicitarDebinInView) {
        detalleSolicitud.setImporte(solicitarDebinInView.getImporte());
        detalleSolicitud.setConcepto(solicitarDebinInView.getIdConcepto());
        detalleSolicitud.setDescripcion(solicitarDebinInView.getDescripcion());
        detalleSolicitud.setFechaHoraComprobante(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
        detalleSolicitud.setNroComprobante(obtenerNumeroComprobante(new Date()));
        detalleSolicitud.setFechaVencimiento(solicitarDebinInView.getFechaVencimiento());
        obtenerDetallesCuentaAcreditacion(detalleSolicitud, solicitarDebinInView);
    }

    /**
	 * Obtener detalles cuenta acreditacion.
	 *
	 * @param detalleSolicitud
	 *            the detalle solicitud
	 * @param solicitarDebinInView
	 *            the solicitar debin in view
	 */
    private void obtenerDetallesCuentaAcreditacion(DatosSolicitudDebinDTO detalleSolicitud,SolicitarDebinView solicitarDebinInView) {
        CuentasAdheridasOutDTO outAdhesion = sesionParametros.getCuentasAdheridasDebin();
        String cbuAcreditacion = solicitarDebinInView.getCbuOrigen();
        for (CuentaDebinDTO cuentaDebin : outAdhesion.getCuentasAdheridas()) {
            if (cbuAcreditacion.equals(cuentaDebin.getCbu())) {
                Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(cuentaDebin.getCbu());
                detalleSolicitud.setAliasAcreditacion(cuentaCliente.getAlias());
                detalleSolicitud.setTipoCuentaAcreditacion(TipoCuenta.fromCodigo(cuentaCliente.getTipoCuenta()).getDescripcion());
                detalleSolicitud.setNroCuentaAcreditacion(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));
                break;
            }
        }
    }

    /**
     * generarCreacionDebinInDTO
     * 
     * @param solicitudDebinInView
     * @param detalleSolicitud
     * @return
     */
    private CreacionDebinWSInDTO generarCreacionDebinInDTO(SolicitarDebinView solicitudDebinInView, DatosSolicitudDebinDTO detalleSolicitud) {
        CreacionDebinWSInDTO inDTO = new CreacionDebinWSInDTO();
        Cliente cliente = sesionCliente.getCliente();
        inDTO.setCanal(CANAL_DEFAULT);
        inDTO.setNroDocumento(cliente.getDni());
        inDTO.setTipoDocumento(cliente.getTipoDocumento());
        inDTO.setCuitDestinatario(detalleSolicitud.getCuitDestinatario().replaceAll("-", ""));
        inDTO.setTitularDestinatario(detalleSolicitud.getNombreDestinatario());
        inDTO.setCbuDestinatario(detalleSolicitud.getCbuDestinatario());
        inDTO.setAliasDestinatario(detalleSolicitud.getAliasDestinatario());
        inDTO.setConcepto(solicitudDebinInView.getIdConcepto());
        inDTO.setFechaVencimiento(solicitudDebinInView.getFechaVencimiento());
        inDTO.setImporte(solicitudDebinInView.getImporte());
        inDTO.setMoneda(detalleSolicitud.getMoneda());
        inDTO.setDescripcion(solicitudDebinInView.getDescripcion());
        inDTO.setCbuOrigen(solicitudDebinInView.getCbuOrigen());
        inDTO.setPermitePreautorizado(false);
        inDTO.setCategoriaLimite(categoriaLimiteDefault);
        return inDTO;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSSolicitudesManager#descargarComprobanteSolicitar()
     */
    @Override
    public Respuesta<Reporte> descargarComprobanteSolicitar() {
        ComprobanteSolicitudDTO comprobanteDTO = new ComprobanteSolicitudDTO();
        DatosSolicitudDebinDTO detalleDebin = sesionParametros.getDatosSolicitudDebin();
        comprobanteDTO.setNombreDestinatario(detalleDebin.getNombreDestinatario());
        String simboloMoneda= "";
        if (MONEDA_PESOS_BANELCO.equals(detalleDebin.getMoneda())) {
            simboloMoneda =SIMBOLO_PESOS;
        } else if (MONEDA_DOLAR_BANELCO.equals(detalleDebin.getMoneda())) {
            simboloMoneda = SIMBOLO_DOLARES;
        } 
        comprobanteDTO.setImporte(simboloMoneda + " " + ISBANStringUtils.formatearSaldo(new BigDecimal(detalleDebin.getImporte())));
        comprobanteDTO.setCuit(ISBANStringUtils.agregarGuionesANumeroCuitCuil(detalleDebin.getCuitDestinatario()));
        comprobanteDTO.setCbu(detalleDebin.getCbuDestinatario());
        comprobanteDTO.setAlias(detalleDebin.getAliasDestinatario());
        comprobanteDTO.setBanco(detalleDebin.getBancoDestinatario());
        comprobanteDTO.setTipoCuentaAcreditacion(detalleDebin.getTipoCuentaAcreditacion());
        comprobanteDTO.setNroCuentaAcreditacion(detalleDebin.getNroCuentaAcreditacion());
        comprobanteDTO.setAliasAcreditacion(detalleDebin.getAliasAcreditacion());
        comprobanteDTO.setFechaVencimiento(detalleDebin.getFechaVencimiento());
        comprobanteDTO.setConcepto(obtenerDescripcionConcepto(detalleDebin.getConcepto()));
        comprobanteDTO.setDescripcion(detalleDebin.getDescripcion());
        comprobanteDTO.setFechaHora(detalleDebin.getFechaHoraComprobante());
        comprobanteDTO.setNroComprobante(detalleDebin.getNroComprobante());
        return solicitudesDebinWSBO.descargarComprobante(comprobanteDTO);
    }

}
