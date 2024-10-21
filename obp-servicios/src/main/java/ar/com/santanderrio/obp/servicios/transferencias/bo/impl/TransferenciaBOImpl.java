
/**
 *
 */
package ar.com.santanderrio.obp.servicios.transferencias.bo.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;

import ar.com.santanderrio.obp.servicios.transferencias.entities.StatusAgendamiento;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.DetalleError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.bo.TransferenciaBO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.HostTimeOutException;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.TransferenciaModtrfe;
import ar.com.santanderrio.obp.servicios.transferencias.entities.IndicadorFuncion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.exception.BeneficiarioNoVerificadException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaCerradaException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaOrigenSinBanelcoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinDebitosNiAcreditacionesException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoERRORenCNSException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ExcedeLimiteDolaresBeneficiarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ImporteLimiteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TitularidadNoVerificadaException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TransferenciaDolaresInhabilitadaException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaAlfaNoVerificadaNuncaOperoUsdException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaAlfaNoVerificadaException;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.DestinatarioDTO;

/**
 * The Class TransferenciaBOImpl.
 *
 * @author B039636
 */
@Component
public class TransferenciaBOImpl implements TransferenciaBO {

    /** The Constant OCURRIO_UN_ERROR_AL_GRABAR_LAS_ESTIDISTICAS. */
    private static final String OCURRIO_UN_ERROR_AL_GRABAR_LAS_ESTIDISTICAS = "Ocurrio un error al grabar las estidisticas.";

    /** The Constant TIPO_MODALIDAD_TRANSFERENCIA_SIN_RESTRICCIONES. */
	private static final String TIPO_MODALIDAD_TRANSFERENCIA_SIN_RESTRICCIONES = "03";

	/** The Constant INDICADOR_ADHESION_BEE_OK. */
	private static final String INDICADOR_ADHESION_BEE_OK = "S";

    /** The Constant TAG_AYUDA. */
    private static final String TAG_AYUDA = "AYUDA";

    /** The Constant IMPORTE. */
    private static final String IMPORTE = "importe";

    /** The Constant CUENTA. */
    private static final String CUENTA = "cuenta";

    /** The Constant BENEFICIARIO_NO_VERIFICADO. */
    private static final String BENEFICIARIO_NO_VERIFICADO = "Beneficiario no verificado";

    /** The Constant TITULAR_NO_VERIFICADO. */
    private static final String TITULAR_NO_VERIFICADO = "Titular no verificado";

    /** The Constant HA_OCURRIDO_AL_REALIZAR_LA_TRANSFERENCIA. */
    private static final String HA_OCURRIDO_AL_REALIZAR_LA_TRANSFERENCIA = "Ha ocurrido al realizar la transferencia";

    /**
     * The Constant HA_OCURRIDO_UN_ERROR_AL_VALIDAR_EL_ORIGEN_DESTINO_TRANSFERENCIA.
     */
    private static final String HA_OCURRIDO_UN_ERROR_AL_VALIDAR_EL_ORIGEN_DESTINO_TRANSFERENCIA = "Ha ocurrido un error al validar el origen destino transferencia";

    /**
     * The Constant
     * HA_OCURRIDO_UN_ERROR_AL_VALIDAR_SI_EL_CLIENTE_ESTA_HABILITADO_PARA_TRANSFERIR.
     */
    private static final String HA_OCURRIDO_UN_ERROR_AL_VALIDAR_SI_EL_CLIENTE_ESTA_HABILITADO_PARA_TRANSFERIR = "Ha ocurrido un error al validar si el cliente esta habilitado para transferir";

    /**
     * The Constant ERRORES_AL_VALIDAR_EL_ORIGEN_DESTINO_TRANSFERENCIA.
     */
    private static final String ERRORES_AL_VALIDAR_EL_ORIGEN_DESTINO_TRANSFERENCIA = "Errores al validar el origen destino transferencia";

    /** The Constant VALIDACION_ORIGEN_DESTINO_DE_TRABSFERENCIA_A_OB_OK. */
    private static final String VALIDACION_ORIGEN_DESTINO_DE_TRABSFERENCIA_A_OB_OK = "Validacion Origen-Destino de trabsferencia a OB ok.";

    /** The Constant TITULAR_NO_VERIFICADO_POR_TIMEOUT. */
    private static final String TITULAR_NO_VERIFICADO_POR_TIMEOUT = "Eror por Time Out al consultar iatx en CNSTITCBU";

    /** The trfinmediata br importedolares max. */
    @Value("${TRFINMEDIATA.BR.IMPORTEDOLARES.MAX}")
    private String trfInmediataBrImporteDolaresMax;

    /** The trfinmediata brt importepesos max. */
    @Value("${TRFINMEDIATA.BRT.IMPORTEPESOS.MAX}")
    private String trfInmediataBrtImportePesosMax;

    /** The trfinmediata ob importepesos max. */
    @Value("${TRFINMEDIATA.OB.IMPORTEPESOS.MAX}")
    private String trfInmediataObImportePesosMax;

    /** The trfinmediata brp importepesos max. */
    @Value("${TRFINMEDIATA.BRP.IMPORTEPESOS.MAX}")
    private String trfInmediataBrpImportePesosMax;

    /** The trfinmediata ob importedolares max. */
    @Value("${TRFINMEDIATA.OB.IMPORTEDOLARES.MAX}")
    private String trfInmediataObImporteDolareMax;

    /** The trfinmediata obp importedolarespesos max. */
    @Value("${TRFINMEDIATA.OBP.IMPORTEDOLARESPESOS.MAX}")
    private String trfInmediataObpImporteDolarespesosMax;

    /** The trf cvu importe pesos max. */
    @Value("${TRFCVU.IMPORTEPESOS.MAX}")
    private String trfCVUImportePesosMax;

    /** The error banelco coelsa habilitado. */
    @Value("${ERRORBANELCO.COELSA.HABILITADO}")
    private String errorBanelcoCoelsaHabilitado;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaBOImpl.class);

    /** The transferencia DAO. */
    @Autowired
    private TransferenciaDAO transferenciaDAO;

    /** The agenda destinatario DAO. */
    @Autowired
    private AgendaDestinatarioDAO agendaDestinatarioDAO;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The mensaje bo. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The estadistica BO. */
    @Autowired
    private EstadisticaBO estadisticaBO;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The alta destinatario BO. */
    @Autowired
    private AltaDestinatarioBO altaDestinatarioBO;

    /** The Constant REALIZAR. */
    private static final String REALIZAR = "realizar";

    /** The Constant REALIZO. */
    private static final String REALIZADA = "realizada";

    /** The Constant PROGRAMO. */
    private static final String PROGRAMADA = "programada";

    /** The Constant PROGRAMAR. */
    private static final String PROGRAMAR = "programar";

    /** The Constant MSJ_INFO_TRANSFERENCIA_PROGRAMADA. */
    private static final String MSJ_INFO_TRANSFERENCIA_PROGRAMADA = "Ejecutando una transferencia programada.";

    /** The Constant MSJ_INFO_TRANSFERENCIA_INMEDIATA. */
    private static final String MSJ_INFO_TRANSFERENCIA_INMEDIATA = "Ejecutando una transferencia inmediata.";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.transferencias.bo.TransferenciaBO#
     * isClienteHabilitadoParaTransferir(ar.com.santanderrio.obp.servicios.
     * clientes.entities.Cliente)
     */
    @Override
    public Respuesta<Boolean> isClienteHabilitadoParaTransferir(Cliente cliente) {
        Respuesta<Boolean> respuesta = respuestaFactory.crearRespuestaOk(Boolean.class);
        try {
        	TransferenciaModtrfe transferenciaModtrfe = transferenciaDAO.ejecutarModTrfe(cliente);
        	LOGGER.info("Los datos de cnsmodtrfe sobre los que se evalua si esta permitida la transf son {}", transferenciaModtrfe);
            respuesta.setRespuesta(esTransferenciaPermitida(transferenciaModtrfe));
        } catch (DAOException ex) {
            LOGGER.info(HA_OCURRIDO_UN_ERROR_AL_VALIDAR_SI_EL_CLIENTE_ESTA_HABILITADO_PARA_TRANSFERIR, ex);
            String mensajeError = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CONSULTA_CNSMODTRFE)
                    .getMensaje();
            respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(Boolean.class, mensajeError,
                    TipoError.ERROR_PARCIAL_AGENDA_TRANSFERENCIAS.getDescripcion());
            return respuesta;
        }
        return respuesta;
    }

    /**
     * Validar si es una modalidad permitida.
     * 
     * @param transferenciaModtrfe
     * @return
     */
    private Boolean esTransferenciaPermitida(TransferenciaModtrfe transferenciaModtrfe) {
		return (TIPO_MODALIDAD_TRANSFERENCIA_SIN_RESTRICCIONES.equals(transferenciaModtrfe.getModalidadTransferencia())
				&& INDICADOR_ADHESION_BEE_OK.equals(transferenciaModtrfe.getIndicadorAdhesionBee()));
	}

	/*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.transferencias.bo.TransferenciaBO#
     * adherirContratoTransferencia(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente)
     */
    public Respuesta<Boolean> adherirContratoTransferencia(Cliente cliente) {
        Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
        transferenciaDAO.limpiarCache(cliente);
        try {
            transferenciaDAO.ejecutarALTADHABCF(cliente);
            respuesta.setRespuesta(Boolean.TRUE);
        } catch (DAOException ex) {
            LOGGER.error(HA_OCURRIDO_UN_ERROR_AL_VALIDAR_SI_EL_CLIENTE_ESTA_HABILITADO_PARA_TRANSFERIR, ex);
            respuesta.setRespuesta(Boolean.FALSE);
            return respuesta;
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.transferencias.bo.TransferenciaBO#
     * validarOrigenDestinoTransferencia
     * 
     * (ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.transferencias.entities. TransferenciaDTO,
     * java.lang.String, java.lang.String,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta, java.lang.String)
     */
    @Override
    public Respuesta<TransferenciaDTO> validarOrigenDestinoTransferencia(Cliente cliente,
            TransferenciaDTO transferenciaView, String nroTarjetaActiva, String monedaSeleccionada,
            Cuenta cuentaOrigenSeleccionada, String userAgent) {
        Respuesta<TransferenciaDTO> respuestaTransferenciaDTO = new Respuesta<TransferenciaDTO>();
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setCbuCuenta(transferenciaView.getCbuCuenta());
        transferenciaDTO.setCuentaOrigen(transferenciaView.getCuentaOrigen());
        respuestaTransferenciaDTO.setRespuesta(transferenciaDTO);
        respuestaTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        boolean errorBanelcoCoelsaHab = "1".equals(errorBanelcoCoelsaHabilitado.trim());
        sesionParametros.setDestinatarioView(null);
        try {
            TransferenciaDTO transferenciaRetornada = validarOrigenDestino(cliente, transferenciaView, nroTarjetaActiva,
                    userAgent, monedaSeleccionada, cuentaOrigenSeleccionada);
            guardarDestinatarioDTOenSesion(transferenciaRetornada);
            LOGGER.info(VALIDACION_ORIGEN_DESTINO_DE_TRABSFERENCIA_A_OB_OK);
            respuestaTransferenciaDTO.setRespuesta(transferenciaRetornada);
            transferenciaRetornada.setErrorBanelco(false);
            if (errorBanelcoCoelsaHab) {
                transferenciaDTO.setPlazoAcreditacion(PlazoAcreditacion.INMEDIATO);
                transferenciaDTO.setVaPorCoelsa(false);
            }
            transferenciaDTO.setTransferenciaInmediata(true);
            transferenciaDTO.setCuit(transferenciaRetornada.getCuit());
            transferenciaView.setCuit(transferenciaRetornada.getCuit());
            transferenciaView.setTitular(transferenciaRetornada.getTitular());
            transferenciaView.setCuentaDestinoBanelco(transferenciaRetornada.getCuentaDestinoBanelco());
        } catch (DestinatarioNoVerificadoERRORenCNSException ex) {
            LOGGER.info(ERRORES_AL_VALIDAR_EL_ORIGEN_DESTINO_TRANSFERENCIA, ex);
            String codigoMensajeRespuesta = errorBanelcoCoelsaHab ? null
                    : CodigoMensajeConstantes.ERROR_TITULARIDAD_CBU;
            TipoError tipoError = errorBanelcoCoelsaHab ? TipoError.DEST_NO_VERIFICADO_ERR
                    : getTipoErrorVerifDest(transferenciaView.getCbuCuenta());
            cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.WARNING, codigoMensajeRespuesta, tipoError);
            configTransferencia(transferenciaDTO,
                    errorBanelcoCoelsaHab ? PlazoAcreditacion.INMEDIATO_COELSA : PlazoAcreditacion.PLAZO_48_HS);
        } catch (BeneficiarioNoVerificadException ex) {
            LOGGER.info(BENEFICIARIO_NO_VERIFICADO, ex);
            cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.WARNING,
                    CodigoMensajeConstantes.BENEFICIARIO_NO_VERIFICADO_CBU, TipoError.TRANSFERENCIA_SIN_REINTENTO);
            if (errorBanelcoCoelsaHab) {
                configTransferencia(transferenciaDTO, PlazoAcreditacion.INMEDIATO_COELSA);
            }
        } catch (TimeOutException toex) {
            LOGGER.info(TITULAR_NO_VERIFICADO_POR_TIMEOUT, toex);
            if (!errorBanelcoCoelsaHab) {
                cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.WARNING,
                        CodigoMensajeConstantes.TITULARIDAD_NO_VERIFICADO_CBU,
                        getTipoErrorVerifDest(transferenciaView.getCbuCuenta()));
            } else {
                cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.WARNING, null,
                        TipoError.DEST_NO_VERIFICADO_ERR);
            }
            configTransferencia(transferenciaDTO,
                    errorBanelcoCoelsaHab ? PlazoAcreditacion.INMEDIATO_COELSA : PlazoAcreditacion.PLAZO_24_HS);
        } catch (TitularidadNoVerificadaException ex) {
            LOGGER.info(ERRORES_AL_VALIDAR_EL_ORIGEN_DESTINO_TRANSFERENCIA, ex);
            if (TransferenciaUtil.ERROR_SIN_REITENTO_36 == ex.getErrorCode()) {
                String codigoMensajeRespuesta = "";
                if (StringUtils.isNotBlank(transferenciaView.getAlias())) {
                    codigoMensajeRespuesta = CodigoMensajeConstantes.CBU_NO_VERIFICADO; // 1587
                } else {
                    codigoMensajeRespuesta = errorBanelcoCoelsaHab
                            ? CodigoMensajeConstantes.OPERACION_NO_PUEDE_SER_CURSADA
                            : CodigoMensajeConstantes.CBU_NO_VERIFICADO;
                }
                cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.WARNING, codigoMensajeRespuesta,
                        TipoError.MONEDA_NO_COINCIDE_CON_MONEDA_CBU);
            } else if (TransferenciaUtil.ERROR_SIN_REITENTO_57 == ex.getErrorCode()) {
                cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.ERROR,
                        CodigoMensajeConstantes.OPERACION_NO_PUEDE_SER_CURSADA, TipoError.ERROR_CONSULTA_TITULAR);
            } else if (TransferenciaUtil.ERROR_SIN_REINTENTO_66 == ex.getErrorCode()) {
                cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.ERROR,
                        CodigoMensajeConstantes.ERROR_CBU_INHABILITADA_RECIBIR_TRANSFERENCIAS, TipoError.DEST_NO_VERIFICADO_ERR);
            } else if (!errorBanelcoCoelsaHab) {
                cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.WARNING,
                        CodigoMensajeConstantes.ERROR_TITULARIDAD_CBU,
                        getTipoErrorVerifDest(transferenciaView.getCbuCuenta()));
            } else {
                // Se sigue flujo similar a 48horas pero no se presenta mensaje
                // porque se pasan a manejar por Coelsa
                cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.WARNING, null,
                        TipoError.DEST_NO_VERIFICADO_ERR);
            }
            configTransferencia(transferenciaDTO,
                    errorBanelcoCoelsaHab ? PlazoAcreditacion.INMEDIATO_COELSA : PlazoAcreditacion.PLAZO_24_HS);
        } catch (DestinatarioNoVerificadoException ex) {
            LOGGER.info(TITULAR_NO_VERIFICADO, ex);
            cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.WARNING,
                    CodigoMensajeConstantes.DESTINATARIO_NO_VERIFICADO_CON_REINTENTO_CBU,
                    TipoError.TRANSFERENCIA_CON_REINTENTO);
            configTransferencia(transferenciaDTO,
                    errorBanelcoCoelsaHab ? PlazoAcreditacion.INMEDIATO_COELSA : PlazoAcreditacion.PLAZO_24_HS);
            if (!errorBanelcoCoelsaHab) {
                transferenciaDTO.setVaPorCoelsa(true);
            }
        } catch (DAOException ex) {
            LOGGER.error(HA_OCURRIDO_UN_ERROR_AL_VALIDAR_EL_ORIGEN_DESTINO_TRANSFERENCIA, ex);
            cargarRespuesta(respuestaTransferenciaDTO, EstadoRespuesta.ERROR, CodigoMensajeConstantes.ERROR_GENERICO,
                    TipoError.ERROR_GENERICO);
            respuestaTransferenciaDTO.setRespuestaVacia(true);
            if (errorBanelcoCoelsaHab) {
                configTransferencia(transferenciaDTO, PlazoAcreditacion.INMEDIATO_COELSA);
            } else {
                transferenciaDTO.setErrorBanelco(true);
                transferenciaDTO.setTransferenciaInmediata(false);
            }
        }

        if (ISBANStringUtils.validarCVU(transferenciaView.getCbuCuenta())) {
            transferenciaDTO.setPlazoAcreditacion(PlazoAcreditacion.INMEDIATO_COELSA);
            transferenciaDTO.setTransferenciaInmediata(true);
            transferenciaDTO.setVaPorCoelsa(true);
        }

        return respuestaTransferenciaDTO;
    }

    /**
     * Gets the tipo error verif dest.
     *
     * @param cbu
     *            the cbu
     * @return the tipo error verif dest
     */
    private TipoError getTipoErrorVerifDest(String cbu) {
        return ISBANStringUtils.validarCVU(cbu) ? TipoError.DEST_NO_VERIFICADO_ERR : TipoError.TRANSFERENCIA_48_HORAS;
    }

    /**
     * Cargar mensajes respuesta.
     *
     * @param respuestaTransferenciaDTO
     *            the respuesta transferencia DTO
     * @param estadoRespuesta
     *            the estado respuesta
     * @param codigoMensaje
     *            the codigo mensaje
     * @param tipoError
     *            the tipo error
     */
    private void cargarRespuesta(Respuesta<TransferenciaDTO> respuestaTransferenciaDTO, EstadoRespuesta estadoRespuesta,
            String codigoMensaje, TipoError tipoError) {
        String mensajeRespuesta = codigoMensaje != null ? mensajeBO.obtenerMensajePorCodigo(codigoMensaje).getMensaje()
                : "";
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(mensajeRespuesta);
        itemMensajeRespuesta.setTipoError(tipoError.getDescripcion());
        respuestaTransferenciaDTO.add(itemMensajeRespuesta);
        respuestaTransferenciaDTO.setEstadoRespuesta(estadoRespuesta);
    }

    /**
     * Config transferencia.
     *
     * @param transferenciaDTO
     *            the transferencia DTO
     * @param plazoAcreditacion
     *            the plazo acreditacion
     */
    private void configTransferencia(TransferenciaDTO transferenciaDTO, PlazoAcreditacion plazoAcreditacion) {
        if ("1".equals(errorBanelcoCoelsaHabilitado.trim())) {
            transferenciaDTO.setTransferenciaInmediata(true);
            transferenciaDTO.setVaPorCoelsa(true);
        } else {
            transferenciaDTO.setTransferenciaInmediata(false);
        }
        transferenciaDTO.setErrorBanelco(true);
        transferenciaDTO.setPlazoAcreditacion(plazoAcreditacion);
    }

    /**
     * Validar origen destino.
     *
     * @param cliente
     *            the cliente
     * @param transferenciaView
     *            the transferencia view
     * @param nroTarjetaActiva
     *            the nro tarjeta activa
     * @param userAgent
     *            the user agent
     * @param monedaSeleccionada
     *            the moneda seleccionada
     * @param cuentaOrigenSeleccionada
     *            the cuenta origen seleccionada
     * @return the transferencia DTO
     * @throws DestinatarioNoVerificadoException
     *             the destinatario no verificado exception
     * @throws DAOException
     *             the DAO exception
     * @throws TitularidadNoVerificadaException
     *             the titularidad no verificada exception
     * @throws BeneficiarioNoVerificadException
     *             the beneficiario no verificad exception
     * @throws DestinatarioNoVerificadoERRORenCNSException
     *             the destinatario no verificado ERRO ren CNS exception
     */
    private TransferenciaDTO validarOrigenDestino(Cliente cliente, TransferenciaDTO transferenciaView,
            String nroTarjetaActiva, String userAgent, String monedaSeleccionada, Cuenta cuentaOrigenSeleccionada)
            throws DestinatarioNoVerificadoException, DAOException, TitularidadNoVerificadaException,
            BeneficiarioNoVerificadException, DestinatarioNoVerificadoERRORenCNSException {
        if (ISBANStringUtils.validarCVU(transferenciaView.getCbuCuenta())) {
            return transferenciaDAO.validarOrigenDestinoTransferenciaCVU(transferenciaView, userAgent,
                    monedaSeleccionada, cuentaOrigenSeleccionada);
        } else {
            return transferenciaDAO.validarOrigenDestinoTransferencia(cliente, transferenciaView, nroTarjetaActiva);
        }
    }

    /**
     * Guardar el destinatario en sesion consultado, para luego utilizarlo al
     * ejecutar una transferencia.
     *
     * @param transferenciaDTO
     *            the transferencia DTO
     */
    private void guardarDestinatarioDTOenSesion(TransferenciaDTO transferenciaDTO) {
        DestinatarioDTO destinatarioDTO = new DestinatarioDTO();
        destinatarioDTO.setBancoReceptor(transferenciaDTO.getBancoReceptor());
        destinatarioDTO.setCuentaDestinoBanelco(transferenciaDTO.getCuentaDestinoBanelco());
        destinatarioDTO.setCuit(transferenciaDTO.getCuit());
        destinatarioDTO.setCuit2(transferenciaDTO.getCuit2());
        destinatarioDTO.setCuit3(transferenciaDTO.getCuit3());
        destinatarioDTO.setFiid(transferenciaDTO.getFiid());
        destinatarioDTO.setLongCuentaDestinoBanelco(transferenciaDTO.getLongitudCuentaDestinoBanelco());
        destinatarioDTO.setTipoDeCuentaFromBanelco(transferenciaDTO.getTipoDeCuentaFromBanelco());
        destinatarioDTO.setTipoDeCuentaToBanelco(transferenciaDTO.getTipoDeCuentaToBanelco());
        destinatarioDTO.setTitular(transferenciaDTO.getTitular());
        destinatarioDTO.setUser(transferenciaDTO.getUser());
        destinatarioDTO.setBancoDestino(transferenciaDTO.getBancoDestino());
        sesionParametros.setDestinatarioView(destinatarioDTO);
    }

    /**
     * Obtener importe transferencia.
     *
     * @param importe
     *            the importe
     * @return the big decimal
     * @throws ImporteConvertException
     *             the importe convert exception
     * @versio 2. Manuel Vargas. Deja de usarse la estructura
     *         PedidoTransferenciaAgendada y DatosTransferenciaAgendada
     */
    private BigDecimal obtenerImporteTransferencia(String importe) throws ImporteConvertException {
        return ISBANStringUtils.convertirImporte(importe);
    }

    /**
     * Ejecutar transferencia programada.
     *
     * @param cliente
     *            the cliente
     * @param transferenciaDTO
     *            the transferencia DTO
     * @param isFromAgendaDestinatario
     *            the is from agenda destinatario
     * @param isProgramada
     *            the is programada
     * @return the respuesta
     */
    @Override
    public Respuesta<TransferenciaDTO> ejecutarTransferenciaProgramada(Cliente cliente,
            TransferenciaDTO transferenciaDTO, Boolean isFromAgendaDestinatario, Boolean isProgramada) {
        LOGGER.info(MSJ_INFO_TRANSFERENCIA_PROGRAMADA);
        Respuesta<TransferenciaDTO> respuestaEjecucionTransferenciaDTO;
        TransferenciaDTO transferencia = null;
        String mensajeFormateado = null;

        String estadoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;

        try {
            BigDecimal importeFormateado = obtenerImporteTransferencia(transferenciaDTO.getImporte().toString());
            transferenciaDTO.setImporte(importeFormateado);
            transferenciaDTO.setIsFromAgendaDestinatario(isFromAgendaDestinatario);
            cargarDestinatarioEnTransferenciaDTO(transferenciaDTO);
            transferencia = transferenciaDAO.ejecutarAgendarTransferenciaProgramada(transferenciaDTO, cliente);
            respuestaEjecucionTransferenciaDTO = respuestaFactory.crearRespuestaOk(transferencia);

            mensajeFormateado = mensajeBO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_OK,
                            transferenciaDTO.nombreParaMensaje(), transferenciaDTO.importeConMoneda(), PROGRAMADA)
                    .getMensaje();
            transferencia.setMensaje(mensajeFormateado);

            // es cuarenta y ocho horas;
            transferencia.setTransferenciaInmediata(false);
            respuestaEjecucionTransferenciaDTO.setRespuesta(transferencia);
            estadoEstadistica = transferenciaDTO.isRiesgoAlto() ? EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING
                    : EstadisticasConstants.CODIGO_ESTADISTICAS_OK;

            grabarEstadisticasConfirmacion(transferenciaDTO.esRioRio(), transferenciaDTO.isHaciaCuentaPropia(),
                    isFromAgendaDestinatario, true, transferenciaDTO, estadoEstadistica);
        } catch (Exception e) {
            respuestaEjecucionTransferenciaDTO = armarRespuestaError(transferenciaDTO, transferenciaDTO.esRioRio(),
                    transferenciaDTO.isHaciaCuentaPropia(), isFromAgendaDestinatario, e);
        }
        return agendamientoAutomatico(cliente, respuestaEjecucionTransferenciaDTO, transferenciaDTO,
                isFromAgendaDestinatario, isProgramada);
    }

    /**
     * Armar respuesta error.
     *
     * @param transferenciaDTO
     *            the transferencia DTO
     * @param isRioRio
     *            the is rio rio
     * @param isCuentasPropias
     *            the is cuentas propias
     * @param isFromAgendaDestinatario
     *            the is from agenda destinatario
     * @param e
     *            the e
     * @return the respuesta
     */
    private Respuesta<TransferenciaDTO> armarRespuestaError(TransferenciaDTO transferenciaDTO, Boolean isRioRio,
            Boolean isCuentasPropias, Boolean isFromAgendaDestinatario, Exception e) {
        Respuesta<TransferenciaDTO> respuestaEjecucionTransferenciaDTO;
        String mensajeFormateado;
        LOGGER.error("ERROR_AGENDA_TRANSFERENCIA", e);
        respuestaEjecucionTransferenciaDTO = new Respuesta<TransferenciaDTO>();
        respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        mensajeFormateado = this.mensajeBO
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_GENERICO, PROGRAMAR,
                        transferenciaDTO.getNombreReceptor(), transferenciaDTO.importeConMoneda())
                .getMensaje();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(mensajeFormateado);
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_MENSAJE_ACEPTAR.getDescripcion());
        respuestaEjecucionTransferenciaDTO.add(itemMensajeRespuesta);
        grabarEstadisticasConfirmacion(isRioRio, isCuentasPropias, isFromAgendaDestinatario, true,
                transferenciaDTO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        return respuestaEjecucionTransferenciaDTO;
    }

    /**
     * Cargar los datos del destinatario obtenidos al abrir el stack y guardados en
     * la sesion, al momento de impactar en la transferencia.
     *
     * @param transferenciaDTO
     *            the transferencia DTO
     */
    private void cargarDestinatarioEnTransferenciaDTO(TransferenciaDTO transferenciaDTO) {
        DestinatarioDTO destinatarioView = sesionParametros.getDestinatarioView();
        if (transferenciaDTO.isHaciaOtroBanco() && destinatarioView != null) {
            transferenciaDTO.setBancoReceptor(destinatarioView.getBancoReceptor());
            transferenciaDTO.setCuentaDestinoBanelco(destinatarioView.getCuentaDestinoBanelco());
            transferenciaDTO.setCuit(destinatarioView.getCuit());
            transferenciaDTO.setCuit2(StringUtils.defaultString(destinatarioView.getCuit2()));
            transferenciaDTO.setCuit3(StringUtils.defaultString(destinatarioView.getCuit3()));
            transferenciaDTO.setFiid(destinatarioView.getFiid());
            transferenciaDTO.setLongitudCuentaDestinoBanelco(destinatarioView.getLongCuentaDestinoBanelco());
            transferenciaDTO.setTipoDeCuentaFromBanelco(destinatarioView.getTipoDeCuentaFromBanelco());
            transferenciaDTO.setTipoDeCuentaToBanelco(destinatarioView.getTipoDeCuentaToBanelco());
            if (!StringUtils.isNotBlank(transferenciaDTO.getBancoDestino())) {
                transferenciaDTO.setBancoDestino(destinatarioView.getBancoDestino());
            }
            if (destinatarioView.getTitular() != null) {
                transferenciaDTO.setTitular(destinatarioView.getTitular().trim());
            }
            transferenciaDTO.setUser(destinatarioView.getUser());
        }
    }

    private void registerErrorCVUCBUALIAS(TransferenciaDTO transferenciaView, Respuesta<TransferenciaDTO> transferenciaRespuesta, DetalleError mensajeErrorCvu, DetalleError mensajeErrorCbuAlias) {
        if (ISBANStringUtils.validarCVU(transferenciaView.getCbuCuenta())) {
            LOGGER.info("MSJ_INFO_TRANSFERENCIA_CON_ERROR_POR_CVU");
            transferenciaRespuesta.getRespuesta().setDetalleError(mensajeErrorCvu);
        } else {
            LOGGER.info("MSJ_INFO_TRANSFERENCIA_CON_ERROR_POR_ALIAS_CBU");
            transferenciaRespuesta.getRespuesta().setDetalleError(mensajeErrorCbuAlias);
        }
    }
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.transferencias.bo.TransferenciaBO#
     * realizarTransferencia(ar.com.santanderrio.obp.servicios.clientes.entities
     * .Cliente,
     * ar.com.santanderrio.obp.servicios.transferencias.entities.Transferencia)
     * 
     */
    @Override
    public Respuesta<TransferenciaDTO> ejecutarTransferenciaInmediata(Cliente cliente,
            TransferenciaDTO transferenciaDTO, Boolean isFromAgendaDestinatario, Boolean isProgramada) {
        LOGGER.info(MSJ_INFO_TRANSFERENCIA_INMEDIATA);
        Respuesta<TransferenciaDTO> transferenciaRespuesta = new Respuesta<TransferenciaDTO>();
        transferenciaRespuesta.setRespuestaVacia(Boolean.TRUE);
        transferenciaRespuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        String estadoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
        try {
            TransferenciaDTO respuestaDao = new TransferenciaDTO();
            cargarDestinatarioEnTransferenciaDTO(transferenciaDTO);
            if (ISBANStringUtils.validarCBUCtaRecaudadora(transferenciaDTO.getCbuCuenta()) || transferenciaDTO.isHaciaOtroBanco()) {
                respuestaDao = transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(cliente, transferenciaDTO,
                        IndicadorFuncion.LIQUIDACION);
            } else {
                respuestaDao = transferenciaDAO.realizarTransferenciaInmediataRioRio(cliente, transferenciaDTO);
            }
            transferenciaRespuesta.setRespuestaVacia(Boolean.FALSE);
            transferenciaRespuesta.setRespuesta(respuestaDao);
            transferenciaRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);

            transferenciaRespuesta.getRespuesta()
                    .setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_OK,
                            transferenciaDTO.nombreParaMensaje(), transferenciaDTO.importeConMoneda(), REALIZADA)
                            .getMensaje());
            estadoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
        } catch (CuentaSinOperarException csoe) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(mensajeBO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_CUENTA_SIN_OPERAR)
                    .getMensaje());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_SOLO_MENSAJE.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.CTA_SIN_OPERAR_CVU, DetalleError.CTA_SIN_OPERAR_CBU_ALIAS);
        } catch (ImporteLimiteException ile) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_IMPORTE_LIMITE)
                            .getMensaje());
            itemMensajeRespuesta.setTag(IMPORTE);
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_IMPORTE_LIMITE_TRANSFERENCIA.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            agregarMensajeAyuda(transferenciaRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.IMPORTE_LIMITE_CVU, DetalleError.IMPORTE_LIMITE_CBU_ALIAS);
        } catch (SaldoInsuficienteException sie) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(mensajeBO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_SALDO_INSUFICIENTE)
                    .getMensaje());
            itemMensajeRespuesta.setTag(CUENTA);
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.SALDO_INSUFICIENTE_CVU, DetalleError.SALDO_INSUFICIENTE_CBU_ALIAS);
        } catch (CuentaCerradaException cce) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_CUENTA_CERRADA)
                            .getMensaje());
            itemMensajeRespuesta.setTag(CUENTA);
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_SOLO_MENSAJE.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.CTA_CERRADA_CVU, DetalleError.CTA_CERRADA_CBU_ALIAS);
        } catch (CuentaSinDebitosNiAcreditacionesException csindnia) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(mensajeBO
                    .obtenerMensajePorCodigo(
                            CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_CUENTA_NO_DEBITOS_NI_ACREDITACIONES)
                    .getMensaje());
            itemMensajeRespuesta.setTag(CUENTA);
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_SOLO_MENSAJE.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.CTA_SIN_DEBITO_NI_ACRED_CVU, DetalleError.CTA_SIN_DEBITO_NI_ACRED_CBU_ALIAS);
        } catch (CuentaOrigenSinBanelcoException e) {
            LOGGER.error("La cuenta de origen no está en Banelco", e);
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta
                    .setMensaje(mensajeBO
                            .obtenerMensajePorCodigo(
                                    CodigoMensajeConstantes.TARJETA_SIN_CUENTAS_VINCULADAS_O_CUENTA_INEXISTENTES)
                            .getMensaje());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.CTA_ORIGEN_SIN_BANE_CVU, DetalleError.CTA_ORIGEN_SIN_BANE_CBU_ALIAS);
        } catch (ExcedeLimiteDolaresBeneficiarioException e) {
            LOGGER.error("Excede el limite en dolares del beneficiario.", e);
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta
            .setMensaje(mensajeBO
                    .obtenerMensajePorCodigo(
                            CodigoMensajeConstantes.ERROR_DESTINATARIO_NO_PUEDE_RECIBIR_TRANSFERENCIAS)
                    .getMensaje());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.EXCEDE_LIMITE_USD_BENEF_CVU, DetalleError.EXCEDE_LIMITE_USD_BENEF_CBU_ALIAS);
        } catch (HostTimeOutException htoe) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_HOSTTIMEOUT)
                            .getMensaje());
            itemMensajeRespuesta.setTag(CUENTA);
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_MENSAJE_ACEPTAR.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.HOST_TIME_OUT_CVU, DetalleError.HOST_TIME_OUT_CBU_ALIAS);
        } catch (TransferenciaDolaresInhabilitadaException tdie) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.OPERACION_DOLARES_NO_PERMITIDA_NORMATIVO)
                            .getMensaje());
            itemMensajeRespuesta.setTag(CUENTA);
            itemMensajeRespuesta.setTipoError("ERROR_OPERACION_INHABILITADA_7105");
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.TRANSF_USD_INHABILITADA_CVU, DetalleError.TRANSF_USD_INHABILITADA_CBU_ALIAS);
        } catch (CuentaAlfaNoVerificadaNuncaOperoUsdException tdie) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD)
                            .getMensaje());
            itemMensajeRespuesta.setTag(CUENTA);
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_OPERACION_INHABILITADA.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.CTA_ALFA_NO_VERIF_NUNCA_OP_USD_CVU, DetalleError.CTA_ALFA_NO_VERIF_NUNCA_OP_USD_CBU_ALIAS);
        } catch (CuentaAlfaNoVerificadaException tdie) {
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CUENTA_ALFA_NO_VERIFICADA)
                            .getMensaje());
            itemMensajeRespuesta.setTag(CUENTA);
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_OPERACION_INHABILITADA.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.CTA_ALFA_NO_VERIF_CVU, DetalleError.CTA_ALFA_NO_VERIF_CBU_ALIAS);
        } catch (DAOException ex) {
            LOGGER.error(HA_OCURRIDO_AL_REALIZAR_LA_TRANSFERENCIA, ex);
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta
                    .setMensaje(mensajeBO
                            .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_GENERICO,
                                    REALIZAR, transferenciaDTO.getNombreReceptor(), transferenciaDTO.importeConMoneda())
                            .getMensaje());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_CONOCIDO_TRANSFERENCIA.getDescripcion());
            transferenciaRespuesta.add(itemMensajeRespuesta);
            transferenciaRespuesta.setRespuesta(new TransferenciaDTO());
            registerErrorCVUCBUALIAS(transferenciaDTO, transferenciaRespuesta, DetalleError.DAO_EXCEPTION_CVU, DetalleError.DAO_EXCEPTION_CBU_ALIAS);
        }
        grabarEstadisticasConfirmacion(transferenciaDTO.esRioRio(), transferenciaDTO.isHaciaCuentaPropia(),
                isFromAgendaDestinatario, false, transferenciaDTO, estadoEstadistica);

        return agendamientoAutomatico(cliente, transferenciaRespuesta, transferenciaDTO, isFromAgendaDestinatario,
                isProgramada);
    }


    /**
     * Agendamiento automatico.
     *
     * @param cliente
     *            the cliente
     * @param respuesta
     *            the respuesta
     * @param transferenciaDTO
     *            the transferencia DTO
     * @param isFromAgendaDestinatario
     *            the is from agenda destinatario
     * @param isProgramada
     *            the is programada
     * @return the respuesta
     */
    private Respuesta<TransferenciaDTO> agendamientoAutomatico(Cliente cliente, Respuesta<TransferenciaDTO> respuesta,
            TransferenciaDTO transferenciaDTO, Boolean isFromAgendaDestinatario, Boolean isProgramada) {
        if (!tieneTipoError(respuesta, TipoError.ERROR_SOLO_MENSAJE) && !transferenciaDTO.isHaciaCuentaPropia() && !isFromAgendaDestinatario && transferenciaDTO.isSaveContact()) {
            StatusAgendamiento statusAgendamiento = agendarDestinatario(respuesta, cliente, transferenciaDTO,isProgramada);

            controlarResultadoAgendamiento(respuesta,statusAgendamiento,transferenciaDTO, isProgramada.booleanValue());

            if(statusAgendamiento.isAgendado() && statusAgendamiento.isSePuedeAgendarCuentaBP()) {//si se agendo un destinatario de cuenta BP
            	estadisticaManager.add(EstadisticasConstants.CODIGO_SE_GENERO_ALTA_DESTINATARIO_BP, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else if(!statusAgendamiento.isAgendado() && statusAgendamiento.isSePuedeAgendarCuentaBP()) {// si no se pudo agendar un destinatario de cuenta BP
            	estadisticaManager.add(EstadisticasConstants.CODIGO_SE_GENERO_ALTA_DESTINATARIO_BP, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        }
        return respuesta;
    }

    private StatusAgendamiento agendarDestinatario(Respuesta<TransferenciaDTO> respuesta, Cliente cliente, TransferenciaDTO transferenciaDTO, Boolean isProgramada){
        StatusAgendamiento retStatusAgendamiento = new StatusAgendamiento(false,true);
        AgendaDestinatarioInEntity destinatario = new AgendaDestinatarioInEntity(cliente, transferenciaDTO);

        try {
            if (TransferenciaUtil.puedeAgendarDestinatarioBancaPrivadaRio(transferenciaDTO.esRioRio(), !isProgramada, destinatario.getSucursalCuentaDestinatario())) {
                if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {//se realizo corectamente un transferencia inmediata, hacia una cuenta rio de banca privada
                    destinatario.setIsTransferenciaBP(Boolean.TRUE);
                } else {
                    retStatusAgendamiento.setSePuedeAgendarCuentaBP(false);//no se realizo un transferencia inmediata, hacia una cuenta rio de banca privada (al llamar al servicio dio algun error o warning)
                }
            }

            if (retStatusAgendamiento.isSePuedeAgendarCuentaBP()) {
                AgendaDestinatarioOutEntity outEntity = darAltaOModificarDestinatario(destinatario);

                if ("00000000".equals(outEntity.getCodigoRetornoExtendido())) {
                    retStatusAgendamiento.setAgendado(true);
                }
            }
        } catch (DAOException e) {
            LOGGER.error("Error en la consulta al DAO de agenda destinatario.", e);
        }

        return retStatusAgendamiento;
    }
    /**
     * Dar alta O modificar destinatario.
     *
     * @param destinatario
     *            the destinatario
     * @return the agenda destinatario out entity
     * @throws DAOException
     *             the DAO exception
     */
    private AgendaDestinatarioOutEntity darAltaOModificarDestinatario(AgendaDestinatarioInEntity destinatario)
            throws DAOException {
        String ip = sesionParametros.getRegistroSession().getIp();
        if (!altaDestinatarioBO.existeDestinatarioVacio(destinatario)) {
            return agendaDestinatarioDAO.eliminarUAgregar(destinatario, ip, TipoOperacionACTAGEDESTEnum.ALTA);
        } else {
            return agendaDestinatarioDAO.eliminarUAgregar(destinatario, ip, TipoOperacionACTAGEDESTEnum.MODIFICACION);
        }
    }

    /**
     * Tiene tipo error.
     *
     * @param respuesta
     *            the respuesta
     * @param tipoError
     *            the tipo error
     * @return true, if successful
     */
    private boolean tieneTipoError(Respuesta<TransferenciaDTO> respuesta, TipoError tipoError) {
        if (null == respuesta.getItemsMensajeRespuesta()) {
            return false;
        }
        for (ItemMensajeRespuesta itemMensaje : respuesta.getItemsMensajeRespuesta()) {
            if (tipoError.getDescripcion().equals(itemMensaje.getTipoError())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agregar mensaje ayuda. Si bien no es un error, se utiliza ese medio para
     * pasarlo al front.
     *
     * @param transferenciaRespuesta
     *            the transferencia respuesta
     */
    private void agregarMensajeAyuda(Respuesta<TransferenciaDTO> transferenciaRespuesta) {
        String mensajeAyudaFormateado = MessageFormat.format(TransferenciaUtil.TRANSF_MSG_AYUDA_IMPORTE_MAX,
                trfInmediataBrImporteDolaresMax, trfInmediataBrtImportePesosMax, trfInmediataObImportePesosMax,
                trfInmediataBrpImportePesosMax, trfInmediataObImporteDolareMax, trfInmediataObpImporteDolarespesosMax,
                trfCVUImportePesosMax);
        ItemMensajeRespuesta itemMensajeRespuestaAyuda = new ItemMensajeRespuesta(mensajeAyudaFormateado);
        itemMensajeRespuestaAyuda.setTipoError(TAG_AYUDA);
        transferenciaRespuesta.add(itemMensajeRespuestaAyuda);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.transferencias.bo.TransferenciaBO#
     * generarComprobanteTransferencia(ar.com.santanderrio.obp.servicios.
     * transferencias.entities.Transferencia)
     */
    @Override
    public Respuesta<Reporte> generarComprobanteTransferencia(TransferenciaDTO transferencia) {
        Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
        try {
            Reporte reporte;
            reporte = transferenciaDAO.generarComprobanteTransferencia(transferencia);
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuesta.setRespuesta(reporte);
        } catch (DAOException e) {
            LOGGER.error("Error", e);
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        }
        return respuesta;
    }

    /**
     * Grabar estadisticas confirmacion.
     *
     * @param isRioRio
     *            the is rio rio
     * @param isCuentasPropias
     *            the is cuentas propias
     * @param isFromAgendaDestinatario
     *            the is from agenda destinatario
     * @param isProgramada
     *            the is programada
     * @param transferenciaDTO
     *            the transferencia DTO
     * @param estado
     *            the estado
     */
    private void grabarEstadisticasConfirmacion(boolean isRioRio, boolean isCuentasPropias,
            boolean isFromAgendaDestinatario, boolean isProgramada, TransferenciaDTO transferenciaDTO, String estado) {
        Estadistica estadistica = new Estadistica();
        estadistica.setCodigoError(estado);
        estadistica.setNroCtaDestino(isRioRio ? transferenciaDTO.getNumeroCuentaDestino().getNroCuentaProducto()
                : transferenciaDTO.getCbuCuenta());
        estadistica.setNroCtaOrigen(transferenciaDTO.getCuentaOrigen().getNroCuentaProducto());
        estadistica.setMoneda(transferenciaDTO.getMoneda().getSimbolo());
        estadistica.setImporte(transferenciaDTO.getImporte().toString());
        estadistica.setNroComprobante(transferenciaDTO.getNumeroComprobante());
        if (isProgramada) {
            armarEstadisticaProgramada(isRioRio, isCuentasPropias, estadistica, isFromAgendaDestinatario);
        } else {
            armarEstadisticaInmediata(isRioRio, isCuentasPropias, estadistica, isFromAgendaDestinatario);
        }
        try {
            estadisticaBO.add(estadistica, sesionParametros.getRegistroSession(), sesionCliente.getCliente());
        } catch (BusinessException e) {
            LOGGER.info(OCURRIO_UN_ERROR_AL_GRABAR_LAS_ESTIDISTICAS);
        }
    }

    /**
     * Armar estadistica inmediata.
     *
     * @param isRioRio
     *            the is rio rio
     * @param isCuentasPropias
     *            the is cuentas propias
     * @param estadistica
     *            the estadistica
     * @param isFromAgendaDestinatario
     *            the is from agenda destinatario
     */
    private void armarEstadisticaInmediata(boolean isRioRio, boolean isCuentasPropias, Estadistica estadistica,
            boolean isFromAgendaDestinatario) {
        if (isFromAgendaDestinatario) {
            if (isRioRio && isCuentasPropias) {
                estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_INMEDIATA_CUENTA_PROPIA);
            } else if (isRioRio && !isCuentasPropias) {
                estadistica.setCodigoTransaccion(
                        EstadisticasConstants.CODIGO_ESTADISTICA_INMEDIATA_CUENTAS_RIO_DESDE_AGENDA);
            } else {
                estadistica.setCodigoTransaccion(
                        EstadisticasConstants.CODIGO_ESTADISTICA_INMEDIATA_RIO_OTROS_BANCOS_DESDE_AGENDA);
            }
        } else {
            if (isRioRio && isCuentasPropias) {
                estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_INMEDIATA_CUENTA_PROPIA);
            } else if (isRioRio && !isCuentasPropias) {
                estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_INMEDIATA_RIO_RIO);
            } else {
                estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_INMEDIATA_OTROS_BANCOS);
            }
        }
    }

    /**
     * Armar estadistica programada.
     *
     * @param isRioRio
     *            the is rio rio
     * @param isCuentasPropias
     *            the is cuentas propias
     * @param estadistica
     *            the estadistica
     * @param isFromAgendaDestinatario
     *            the is from agenda destinatario
     */
    private void armarEstadisticaProgramada(boolean isRioRio, boolean isCuentasPropias, Estadistica estadistica,
            boolean isFromAgendaDestinatario) {
        if (isFromAgendaDestinatario) {
            if (isRioRio) {
                estadistica.setCodigoTransaccion(
                        EstadisticasConstants.CODIGO_ESTADISTICA_PROGRAMADA_CUENTAS_RIO_DESDE_AGENDA);
            } else {
                estadistica.setCodigoTransaccion(
                        EstadisticasConstants.CODIGO_ESTADISTICA_PROGRAMADA_RIO_OTROS_BANCOS_DESDE_AGENDA);
            }
        } else {
            if (isRioRio && isCuentasPropias) {
                estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_PROGRAMADA_CUENTAS_PROPIAS);
            } else if (isRioRio) {
                estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_PROGRAMADA_RIO_TERCEROS);
            } else {
                estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_PROGRAMADA_RIO_OTROS_BANCOS);
            }
        }
    }

    private void controlarResultadoAgendamiento (Respuesta<TransferenciaDTO> respuesta,StatusAgendamiento statusAgendamiento,TransferenciaDTO transferenciaDTO, boolean isProgramada){

        if (statusAgendamiento.isAgendado()) {
            if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
                respuesta.getRespuesta().setMensaje(
                        mensajeBO
                                .obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_OK_AGENDAMIENTO_AUTOMATICO,
                                        transferenciaDTO.nombreParaMensaje(), transferenciaDTO.importeConMoneda(),
                                        !isProgramada ? REALIZADA : PROGRAMADA)
                                .getMensaje()
                );
            } else if (tieneTipoError(respuesta, TipoError.ERROR_CONOCIDO_TRANSFERENCIA)) {
                respuesta.getItemsMensajeRespuesta().get(0).setMensaje(
                        mensajeBO.obtenerMensajePorCodigo(
                                CodigoMensajeConstantes.TRANSFERENCIA_ERROR_GENERICO_AGENDAMIENTO_AUTOMATICO,
                                !isProgramada ? REALIZADA : PROGRAMADA, transferenciaDTO.getNombreReceptor(),
                                transferenciaDTO.importeConMoneda()).getMensaje()
                );
            }
            estadisticaManager.add(EstadisticasConstants.AGENDAMIENTO_AUTOMATICO_TRANSFERENCIA_INMEDIATA,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadisticaManager.add(EstadisticasConstants.AGENDAMIENTO_AUTOMATICO_TRANSFERENCIA_INMEDIATA,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
    }
}
