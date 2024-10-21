/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AgendaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioAliasBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioCbuBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.EdicionDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.EliminacionDestinatarioAgendaBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity.DatosEntradaAgendaDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosRCAManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AltaAliasCBUHashView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioCBUOutView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioInView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasInexistenteEliminadoException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * Manager para agenda destinatarios.
 */
@Component
public class AgendaDestinatariosManagerImpl implements AgendaDestinatariosManager {

    /** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
    public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaDestinatariosManagerImpl.class);

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The agendaDestinatarioBO. */
    @Autowired
    private AgendaDestinatarioBO agendaDestinatarioBO;

    /** The alta cbu BO. */
    @Autowired
    private AltaDestinatarioCbuBO altaCBUBO;

    /** The agendaDestinatarioBO. */
    @Autowired
    private AltaDestinatarioBO altaDestinatarioBO;

    /** The alta destinatario alias BO. */
    @Autowired
    private AltaDestinatarioAliasBO altaDestinatarioAliasBO;

    /** The eliminacionDestinatarioAgendaBO. */
    @Autowired
    private EliminacionDestinatarioAgendaBO eliminacionDestinatarioAgendaBO;

    /** The edicion destinatario BO. */
    @Autowired
    private EdicionDestinatarioBO edicionDestinatarioBO;

    /** The agenda destinatarios RCA Manager. */
    @Autowired
    private AgendaDestinatariosRCAManager agendaDestinatariosRCAManager;

    /** The respuestaFactory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesionCliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The casuistica de la agenda de destinatarios. */
    @Autowired
    private CasuisticaAgendaDestinatarios casuistica;

    /** The sesion. */
    @Autowired
    private SesionAgendaDestinatarios sesionAgenda;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The Constant REINTENTAR. */
    private static final String REINTENTAR = "reintentar";

    /** The Constant CONTINUAR. */
    private static final String CONTINUAR = "continuar";

    /** The Constant DESTINATARIO_EXISTENTE. */
    private static final String DESTINATARIO_EXISTENTE = "DESTINATARIO_EXISTENTE";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#obtenerAgendaDestinatarios()
     */
    @Override
    public Respuesta<AgendaDestinatarioView> obtenerAgendaDestinatarios(DatosEntradaAgendaDestinatario dato) {
        LOGGER.info("Obteniendo lista de destinatarios desde BO");
        try {
            boolean filtroDestinatario = dato.getFiltroDestinatario();
            Respuesta<AgendaDestinatarioDTO> respuestaDTO = agendaDestinatarioBO
                    .obtenerAgendaDestinatarios(sesionCliente.getCliente(), filtroDestinatario);
            if (respuestaDTO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
                sesionAgenda.setDestinatariosEntity(respuestaDTO.getRespuesta().getDestinatariosEntity());
                return crearRespuestaViewWarning(respuestaDTO, filtroDestinatario, dato.getGrabaEstadisticas());
            } else if (respuestaDTO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
                return crearRespuestaViewError(respuestaDTO, filtroDestinatario, dato.getGrabaEstadisticas());
            }
            sesionAgenda.setDestinatariosEntity(respuestaDTO.getRespuesta().getDestinatariosEntity());
            return crearRespuestaViewOk(respuestaDTO, filtroDestinatario, dato.getGrabaEstadisticas());
        } catch (Exception e) {
            LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
            return crearRespuestaErrorServicios(dato.getGrabaEstadisticas());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#grabarEstadisticaDetalleAgenda(java.lang.
     * String)
     */
    @Override
    public void grabarEstadisticaDetalleAgenda(String tipoDestinatario) {
        LOGGER.info("Grabando estadistica de ver detalle para destinatario de tipo: {}", tipoDestinatario);
        sesionAgenda.setContadorAlta(null);
        if (StringUtils.equals(tipoDestinatario, TipoAgendaEnum.AGENDA_RIO.getTag())) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_VER_DETALLE_AGENDA_RIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (StringUtils.equals(tipoDestinatario, TipoAgendaEnum.AGENDA_OTROS_BANCOS.getTag())) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_VER_DETALLE_OTROS_BANCOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (StringUtils.equals(tipoDestinatario, TipoAgendaEnum.AGENDA_EXTRACCIONES.getTag())) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_VER_DETALLE_ENVIO_EFECTIVO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (StringUtils.equals(tipoDestinatario, TipoAgendaEnum.AGENDA_ALIAS.getTag())) {
            estadisticaManager.add(EstadisticasConstants.VER_DETALLE_ALTA_DESTINATARIO_ALIAS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#grabarEstadisticaIngresoAltaDestinatarioRio()
     */
    @Override
    public void grabarEstadisticaIngresoAltaDestinatarioRio() {
        // Limpiar sesion
        sesionAgenda.setContadorAlta(null);
        sesionAgenda.setCuitCuil(null);
        // Grabar estadistica
        estadisticaManager.add(EstadisticasConstants.ACCESO_ALTA_DESTINATARIO_RIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#grabarEstadisticaIngresoAltaDestinatarioRio()
     */
    @Override
    public void grabarEstadisticaVerComprobanteRio() {
        // Limpiar sesion
        sesionAgenda.setContadorAlta(null);
        sesionAgenda.setCuitCuil(null);
        // Grabar estadistica
        estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_ALTA_DESTINATARIO_RIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener informacion destinatario.
     *
     * @param configuracionAltaDestinatarioInView
     *            the configuracion alta destinatario in view
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#obtenerInformacionDestinatario(ar.com.
     * santanderrio.obp.servicios.agenda.destinatarios.web.view.
     * ConfiguracionAltaDestinatarioInView)
     */
    private Respuesta<ConfiguracionAltaDestinatarioCBUOutView> obtenerInformacionDestinatario(
            ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioInView) {
        Respuesta<ConfiguracionAltaDestinatarioInView> validacion = respuestaFactory
                .validate(configuracionAltaDestinatarioInView);
        if (EstadoRespuesta.WARNING.equals(validacion.getEstadoRespuesta())) {
            return casuistica.respuestaErrorAltaConfiguracionCuentaInvalida();
        }
        Cliente cliente = sesionCliente.getCliente();
        String numeroCuenta = configuracionAltaDestinatarioInView.getNroCuenta();
        String tipoCuenta = configuracionAltaDestinatarioInView.getIdTipoCuenta();
        Respuesta<ConfiguracionAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO
                .continuarConfiguracionAltaDestinatario(cliente, numeroCuenta, tipoCuenta);
        if (EstadoRespuesta.OK.equals(respuestaDTO.getEstadoRespuesta())) {
            sesionAgenda.setCuitCuil(respuestaDTO.getRespuesta().getCuitOCuil());
            ConfiguracionAltaDestinatarioCBUOutView view = new ConfiguracionAltaDestinatarioCBUOutView(
                    respuestaDTO.getRespuesta());
            view.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
            return respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioCBUOutView.class, view);
        } else if (EstadoRespuesta.ERROR.equals(respuestaDTO.getEstadoRespuesta())) {
            return respuestaFactory.crearRespuestaError(ConfiguracionAltaDestinatarioCBUOutView.class,
                    respuestaDTO.getItemsMensajeRespuesta());
        }
        return respuestaFactory.crearRespuestaWarning(ConfiguracionAltaDestinatarioCBUOutView.class,
                new ConfiguracionAltaDestinatarioCBUOutView(respuestaDTO.getRespuesta()),
                respuestaDTO.getItemsMensajeRespuesta());
    }

    /**
	 * Crear respuesta view ok.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @param filtroDestinatario
	 *            the filtro destinatario
	 * @param grabaEstadisticas
	 *            the graba estadisticas
	 * @return the respuesta
	 */
    private Respuesta<AgendaDestinatarioView> crearRespuestaViewOk(Respuesta<AgendaDestinatarioDTO> respuestaDTO,
            boolean filtroDestinatario, boolean grabaEstadisticas) {
        Respuesta<AgendaDestinatarioView> respuestaView = respuestaFactory
                .crearRespuestaOk(new AgendaDestinatarioView(respuestaDTO.getRespuesta()));
        if (grabaEstadisticas) {
            if (filtroDestinatario) {
                estadisticaManager.add(EstadisticasConstants.INICIO_TRANSFERENCIA_AGENDA_DESTINATARIOS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {
                estadisticaManager.add(EstadisticasConstants.INICIO_AGENDA_DESTINATARIOS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            }
        }
        return respuestaView;
    }

    /**
	 * Crear respuesta view warning.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @param filtroDestinatario
	 *            the filtro destinatario
	 * @param grabaEstadisticas
	 *            the graba estadisticas
	 * @return the respuesta
	 */
    private Respuesta<AgendaDestinatarioView> crearRespuestaViewWarning(Respuesta<AgendaDestinatarioDTO> respuestaDTO,
            boolean filtroDestinatario, boolean grabaEstadisticas) {
        Respuesta<AgendaDestinatarioView> respuestaView = respuestaFactory.crearRespuestaWarning(
                AgendaDestinatarioView.class, new AgendaDestinatarioView(respuestaDTO.getRespuesta()),
                respuestaDTO.getItemsMensajeRespuesta());
        if (grabaEstadisticas) {
            if (filtroDestinatario) {
                estadisticaManager.add(EstadisticasConstants.INICIO_TRANSFERENCIA_AGENDA_DESTINATARIOS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
            } else {
                estadisticaManager.add(EstadisticasConstants.INICIO_AGENDA_DESTINATARIOS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
            }
        }
        return respuestaView;
    }

    /**
	 * Crear respuesta view error.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @param filtroDestinatario
	 *            the filtro destinatario
	 * @param grabaEstadisticas
	 *            the graba estadisticas
	 * @return the respuesta
	 */
    private Respuesta<AgendaDestinatarioView> crearRespuestaViewError(Respuesta<AgendaDestinatarioDTO> respuestaDTO,
            boolean filtroDestinatario, boolean grabaEstadisticas) {
        Respuesta<AgendaDestinatarioView> respuestaView = respuestaFactory
                .crearRespuestaError(AgendaDestinatarioView.class, respuestaDTO.getItemsMensajeRespuesta());
        if (grabaEstadisticas) {
            if (filtroDestinatario) {
                estadisticaManager.add(EstadisticasConstants.INICIO_TRANSFERENCIA_AGENDA_DESTINATARIOS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            } else {
                estadisticaManager.add(EstadisticasConstants.INICIO_AGENDA_DESTINATARIOS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        }
        return respuestaView;
    }

    /**
	 * Crear respuesta error servicios.
	 *
	 * @param grabaEstadisticas
	 *            the graba estadisticas
	 * @return the respuesta
	 */
    private Respuesta<AgendaDestinatarioView> crearRespuestaErrorServicios(boolean grabaEstadisticas) {
        Respuesta<AgendaDestinatarioView> respuestaView = casuistica.respuestaErrorServicios();
        if (grabaEstadisticas) {
            estadisticaManager.add(EstadisticasConstants.INICIO_AGENDA_DESTINATARIOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaView;
    }

    /**
	 * Obtener informacion destinatario transferencia CBU.
	 *
	 * @param configuracionAltaDestinatarioInView
	 *            the configuracion alta destinatario in view
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
    private Respuesta<ConfiguracionAltaDestinatarioCBUOutView> obtenerInformacionDestinatarioTransferenciaCBU(
            ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioInView, String userAgent) {
        Respuesta<ConfiguracionAltaDestinatarioInView> validacion = respuestaFactory
                .validate(configuracionAltaDestinatarioInView);
        if (EstadoRespuesta.WARNING.equals(validacion.getEstadoRespuesta())) {
            return casuistica.respuestaErrorAltaConfiguracionCuentaInvalida();
        }
        String ip = sesionCliente.obtenerIpV4SinPuntos();
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> res = altaCBUBO.continuarAltaDestinatarioCBU(
                sesionCliente.getCliente(), configuracionAltaDestinatarioInView.getNroCbu(),
                configuracionAltaDestinatarioInView.getIsPesos(), ip, userAgent);
        if (EstadoRespuesta.OK.equals(res.getEstadoRespuesta())) {
            sesionAgenda.setCuitCuil(res.getRespuesta().getNumeroCuil());
            return respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioCBUOutView.class,
                    new ConfiguracionAltaDestinatarioCBUOutView(res.getRespuesta()));
        }
        return respuestaFactory.crearRespuestaError(ConfiguracionAltaDestinatarioCBUOutView.class,
                res.getItemsMensajeRespuesta());
    }

    /**
     * Obtiene la informacion del destinatario transferencia por alias.
     *
     * @param configuracionAltaDestinatarioInView
     *            the configuracion alta destinatario in view
     * @param userAgent
     *            the user agent
     * @return the respuesta
     */
    private Respuesta<ConfiguracionAltaDestinatarioCBUOutView> obtenerInformacionDestinatarioTransferenciaAlias(
            ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioInView, String userAgent) {
        Cliente cliente = sesionCliente.getCliente();
        Cuenta cuenta = obtenerCuenta(cliente, configuracionAltaDestinatarioInView.getIsPesos());
        if (cuenta != null) {
            Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = new Respuesta<ConfiguracionAltaDestinatarioCBUDTO>();
            try {
                respuestaDTO = altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(cliente, cuenta,
                        configuracionAltaDestinatarioInView.getAlias(),
                        configuracionAltaDestinatarioInView.getIsPesos(), userAgent);
            } catch (BusinessException be) {
                LOGGER.error(be.getMessage(), be);
                return respuestaFactory.crearRespuestaError(ConfiguracionAltaDestinatarioCBUOutView.class, "",
                        TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_TRACKING_TARJETAS);
            } catch (ValidacionAliasInexistenteEliminadoException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return crearRespuestaAliasCBU(respuestaDTO, configuracionAltaDestinatarioInView);
        } else {
            return respuestaFactory.crearRespuestaError(ConfiguracionAltaDestinatarioCBUOutView.class, "",
                    TipoError.SIN_CUENTAS_TIPO_MONEDA, CodigoMensajeConstantes.SIN_CUENTAS_TIPO_MONEDA);
        }
    }

    /**
     * Obtiene la cuenta habilitada para el alta de un alias como destinatario.
     *
     * @param cliente
     *            the cliente
     * @param isPesos
     *            the is pesos
     * @return the cuenta
     */
    private Cuenta obtenerCuenta(Cliente cliente, Boolean isPesos) {
        Cuenta cuenta = altaCBUBO.obtenerCuentaPorMoneda(cliente, isPesos);
        if (cuenta != null && (TipoCuenta.CUENTA_UNICA_PESOS.equals(cuenta.getTipoCuentaEnum()) && !isPesos)) {
            return new Cuenta(cuenta, TipoCuenta.CUENTA_UNICA_DOLARES);
        }
        if (cuenta != null && (TipoCuenta.CUENTA_UNICA_DOLARES.equals(cuenta.getTipoCuentaEnum()) && isPesos)) {
            return new Cuenta(cuenta, TipoCuenta.CUENTA_UNICA_PESOS);
        }
        return cuenta;
    }

    /**
     * Crea la respuesta de alias CBU.
     *
     * @param respuestaDTO
     *            the respuesta DTO
     * @param configuracionAltaDestinatarioInView
     *            the configuracion alta destinatario in view
     * @return the respuesta
     */
    private Respuesta<ConfiguracionAltaDestinatarioCBUOutView> crearRespuestaAliasCBU(
            Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO,
            ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioInView) {
        if (EstadoRespuesta.OK.equals(respuestaDTO.getEstadoRespuesta())) {
            ConfiguracionAltaDestinatarioCBUOutView view = conversionDTOAView(respuestaDTO);
            DestinatarioAgendaDTO destinatarioExistente = agendaDestinatarioBO
                    .obtenerDestinatarioDTO(buscarDestinatarioExistente(view));
            if (destinatarioExistente != null) {
                destinatarioExistente.setAlias(configuracionAltaDestinatarioInView.getAlias());
                DestinatarioView destinatarioActualizado = new DestinatarioView(destinatarioExistente);
                view.setDestinatarioAgenda(destinatarioActualizado);
                return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(view, DESTINATARIO_EXISTENTE,
                        CodigoMensajeConstantes.ALTA_AGENDAMIENTO_ALIAS_EXISTENTE, null);
            }
            construirHashSeguridad(view, configuracionAltaDestinatarioInView);
            return respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioCBUOutView.class, view);
        }
        if (EstadoRespuesta.WARNING.equals(respuestaDTO.getEstadoRespuesta())) {
            return respuestaFactory.crearRespuestaWarning(ConfiguracionAltaDestinatarioCBUOutView.class, null,
                    respuestaDTO.getItemsMensajeRespuesta());
        }
        return respuestaFactory.crearRespuestaError(ConfiguracionAltaDestinatarioCBUOutView.class,
                respuestaDTO.getItemsMensajeRespuesta());
    }

    /**
     * Buscar destinatario existente.
     *
     * @param view
     *            the view
     * @return the destinatario entity
     */
    private DestinatarioEntity buscarDestinatarioExistente(ConfiguracionAltaDestinatarioCBUOutView view) {
        for (DestinatarioEntity entity : sesionAgenda.getDestinatariosEntity()) {
            if (view.getIsRio() && TipoAgendaEnum.AGENDA_RIO.getCampo().equals(entity.getTipoAgendaOcurrencia())) {
                String numeroCuenta = new IdentificacionCuenta(
                        ISBANStringUtils.eliminarCeros(entity.getSucursalCuentaDestinatario()),
                        ISBANStringUtils.eliminarCeros(entity.getNumeroCuentaDestinatario())).toString();
                if (StringUtils.equals(view.getNroCuenta(), numeroCuenta)) {
                    return entity;
                }
            } else if (TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo().equals(entity.getTipoAgendaOcurrencia())
                    && StringUtils.equals(view.getCbu(), entity.getCbuDestinatario())) {
                return entity;
            }
        }
        return null;
    }

    /**
     * Convierte DTO a view.
     *
     * @param respuestaDTO
     *            the respuesta DTO
     * @return the configuracion alta destinatario CBU out view
     */
    private ConfiguracionAltaDestinatarioCBUOutView conversionDTOAView(
            Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO) {
        ConfiguracionAltaDestinatarioCBUOutView view = new ConfiguracionAltaDestinatarioCBUOutView();
        BeanUtils.copyProperties(respuestaDTO.getRespuesta(), view);
        view.setNroCuenta(respuestaDTO.getRespuesta().getNumeroCuenta());
        return view;
    }

    /**
     * Construir hash seguridad.
     *
     * @param view
     *            the view
     * @param configuracionAltaDestinatarioInView
     *            the configuracion alta destinatario in view
     */
    private void construirHashSeguridad(ConfiguracionAltaDestinatarioCBUOutView view,
            ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioInView) {
        AltaAliasCBUHashView hash = new AltaAliasCBUHashView();
        BeanUtils.copyProperties(view, hash);
        hash.setAlias(configuracionAltaDestinatarioInView.getAlias());
        sesionParametros.setValidacionHash(HashUtils.obtenerHash(hash));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#derivarServicioAltaCorrespondiente(ar.com.
     * santanderrio.obp.servicios.agenda.destinatarios.web.view.
     * ConfiguracionAltaDestinatarioInView)
     */
    @Override
    public Respuesta<ConfiguracionAltaDestinatarioCBUOutView> derivarServicioAltaCorrespondiente(
            ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioInView, String userAgent) {
        if (configuracionAltaDestinatarioInView.getNroCbu() == null
                && configuracionAltaDestinatarioInView.getAlias() == null) {
            return obtenerInformacionDestinatario(configuracionAltaDestinatarioInView);
        } else {
            if (StringUtils.isNotBlank(configuracionAltaDestinatarioInView.getAlias())) {
                return obtenerInformacionDestinatarioTransferenciaAlias(configuracionAltaDestinatarioInView, userAgent);
            }
            return obtenerInformacionDestinatarioTransferenciaCBU(configuracionAltaDestinatarioInView, userAgent);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#grabarEstadisticaIngresoAltaDestinatarioCBU()
     */
    @Override
    public void grabarEstadisticaIngresoAltaDestinatarioCBU() {
        // Limpiar sesion
        sesionAgenda.setContadorAlta(null);
        sesionAgenda.setCuitCuil(null);

        estadisticaManager.add(EstadisticasConstants.ACCESO_ALTA_DESTINATARIO_CBU,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#grabarEstadisticaIngresoEnvioEfectivo()
     */
    @Override
    public void grabarEstadisticaIngresoEnvioEfectivo() {
        // Limpiar sesion
        sesionAgenda.setContadorAlta(null);
        sesionAgenda.setCuitCuil(null);

        estadisticaManager.add(EstadisticasConstants.ACCESO_ALTA_DESTINATARIO_ENVIO_EFECTIVO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#grabarEstadisticaAltaEnvioEfectivo()
     */
    @Override
    public void grabarEstadisticaAltaEnvioEfectivo() {
        estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_ENVIO_EFECTIVO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#eliminacionDestinatario(java.lang.String)
     */
    @Override
    public Respuesta<Void> eliminacionDestinatario(String id) {
        LOGGER.info("Entro al manager para eliminar Destinatario");
        sesionAgenda.inicializarContadorReintentos(2);
        DestinatarioEntity destinatario = buscarDestinatario(id);
        Respuesta<Void> respuesta = eliminacionDestinatarioAgendaBO.eliminacionDestinatario(destinatario,
                sesionParametros.getRegistroSession().getIp(), sesionCliente.getCliente());
        String tipoDestinatario = destinatario.getTipoAgendaOcurrencia();
        if (respuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            grabarEstadisticaEliminacion(tipoDestinatario, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            ItemMensajeRespuesta item = respuesta.getItemsMensajeRespuesta().get(0);
            if (StringUtils.equals(item.getTipoError(), TipoError.ERROR_DESTINATARIO_YA_ELIMINADO.getDescripcion())) {
                item.setDetalleTipoError(CONTINUAR);
            } else {
                if (sesionAgenda.getContadorAlta().permiteReintentar()) {
                    item.setDetalleTipoError(REINTENTAR);
                } else {
                    item.setDetalleTipoError(CONTINUAR);
                    sesionAgenda.setContadorAlta(null);
                }
            }
        } else {
            boolean eliminado = eliminarDestinatarioSesion(id);
            LOGGER.info("El destinatario {} de la sesion", eliminado ? "ha sido eliminado" : "no pudo ser");
            grabarEstadisticaEliminacion(tipoDestinatario, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
        return respuesta;
    }

    /**
     * Grabar estadistica eliminacion.
     *
     * @param tipoDestinatario
     *            the tipo destinatario
     * @param resultado
     *            the resultado
     */
    private void grabarEstadisticaEliminacion(String tipoDestinatario, String resultado) {
        LOGGER.info("Grabo estadistica de eliminacion");
        if (StringUtils.equals(tipoDestinatario, TipoAgendaEnum.AGENDA_RIO.getCampo())) {
            estadisticaManager.add(EstadisticasConstants.ELIMINACION_DESTINATARIO_RIO, resultado);
        } else if (StringUtils.equals(tipoDestinatario, TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo())) {
            estadisticaManager.add(EstadisticasConstants.ELIMINACION_DESTINATARIO_OTROS_BANCOS, resultado);
        } else {
            estadisticaManager.add(EstadisticasConstants.ELIMINACION_DESTINATARIO_ENVIO_EFECTIVO, resultado);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#grabarEstadisticaComprobanteCBU()
     */
    @Override
    public void grabarEstadisticaComprobanteCBU() {
        estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_ALTA_DESTINATARIO_CBU,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#
     * grabarEstadisticaConfiguracionModificacionDestinatario(ar.com.
     * santanderrio. obp.servicios.agenda.destinatarios.web.entity.
     * DatosEntradaAgendaDestinatario)
     */
    @Override
    public void grabarEstadisticaConfiguracionModificacionDestinatario(DatosEntradaAgendaDestinatario datos) {
        LOGGER.info("Grabo estadistica de configuracion de modificar Destinatario");
        if (TipoAgendaEnum.AGENDA_ALIAS.getTag().equals(datos.getTipoDestinatario())) {
            estadisticaManager.add(EstadisticasConstants.INICIO_MODIFICACION_AGENDADESTINATARIOS_ALIAS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (TipoAgendaEnum.AGENDA_RIO.getTag().equals(datos.getTipoDestinatario())) {
            estadisticaManager.add(EstadisticasConstants.INICIO_MODIFICACION_AGENDADESTINATARIOS_RIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (TipoAgendaEnum.AGENDA_OTROS_BANCOS.getTag().equals(datos.getTipoDestinatario())) {
            estadisticaManager.add(EstadisticasConstants.INICIO_MODIFICACION_AGENDADESTINATARIOS_OTROS_BANCOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (TipoAgendaEnum.AGENDA_EXTRACCIONES.getTag().equals(datos.getTipoDestinatario())) {
            estadisticaManager.add(EstadisticasConstants.INICIO_MODIFICACION_AGENDADESTINATARIOS_ENVIO_EFECTIVO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#obtenerConfirmacionEdicionDestinatario(ar.com.
     * santanderrio.obp.servicios.agenda.destinatarios.web.entity.
     * DatosEntradaEdicionDestinatarioView)
     */
    @Override
    public Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionEdicionDestinatario(
            ConfirmacionAltaDestinatarioView datosEntrada) {
        LOGGER.info("Entro al manager para editar Destinatario");
        DestinatarioEntity destinatario = buscarDestinatario(datosEntrada.getId());
        LOGGER.info("Ejecutando modulo de RSA");
        Respuesta<ConfirmacionAltaDestinatarioView> respuestaRSA = agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                datosEntrada, TipoOperacionACTAGEDESTEnum.MODIFICACION, destinatario.obtenerTipoAgenda());
        if (!EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())) {
            return respuestaRSA;
        }
        LOGGER.info("Validacion RSA Exitosa. Ejecutando Edicion Destinatario.");

        sesionAgenda.inicializarContadorReintentos(2);
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = edicionDestinatarioBO.editarDestinatario(destinatario,
                sesionParametros.getRegistroSession().getIp(), sesionCliente.getCliente(), datosEntrada);

        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            grabarEstadisticaComprobanteEdicion(destinatario, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            datosEntrada.cargarComprobante(respuesta.getRespuesta());
            
            return respuestaFactory.crearRespuestaOk(ConfirmacionAltaDestinatarioView.class, datosEntrada);
        } else {
            grabarEstadisticaComprobanteEdicion(destinatario, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            ItemMensajeRespuesta item = respuesta.getItemsMensajeRespuesta().get(0);
            item.setDetalleTipoError(obtenerDetalleError(item.getTipoError()));
            return respuestaFactory.crearRespuestaError(ConfirmacionAltaDestinatarioView.class,
                    respuesta.getItemsMensajeRespuesta());
        }
    }

    /**
     * Grabar estadistica comprobante edicion.
     *
     * @param destinatario
     *            the destinatario
     * @param resultado
     *            the resultado
     */
    private void grabarEstadisticaComprobanteEdicion(DestinatarioEntity destinatario, String resultado) {
        LOGGER.info("Grabo estadistica de edicion Destinatario");
        if (TipoAgendaEnum.AGENDA_ALIAS.equals(destinatario.obtenerTipoAgenda())) {
            estadisticaManager.add(EstadisticasConstants.EDICION_DESTINATARIO_ALIAS, resultado);
        } else if (TipoAgendaEnum.AGENDA_RIO.equals(destinatario.obtenerTipoAgenda())) {
            estadisticaManager.add(EstadisticasConstants.EDICION_DESTINATARIO_RIO, resultado);
        } else if (TipoAgendaEnum.AGENDA_OTROS_BANCOS.equals(destinatario.obtenerTipoAgenda())) {
            estadisticaManager.add(EstadisticasConstants.EDICION_DESTINATARIO_OTROS_BANCOS, resultado);
        } else if (TipoAgendaEnum.AGENDA_EXTRACCIONES.equals(destinatario.obtenerTipoAgenda())) {
            estadisticaManager.add(EstadisticasConstants.EDICION_DESTINATARIO_ENVIO_EFECTIVO, resultado);
        }
    }

    /**
     * Obtener detalle error.
     *
     * @param respuestaError
     *            the respuesta error
     * @return the string
     */
    private String obtenerDetalleError(String respuestaError) {
        LOGGER.info("Verifico si permito reintentar");
        if (!StringUtils.equals(respuestaError, TipoError.ERROR_EDITAR_DESTINATARIO_INVALIDO.getDescripcion())) {
            if (sesionAgenda.getContadorAlta().permiteReintentar()) {
                return REINTENTAR;
            } else {
                sesionAgenda.setContadorAlta(null);
            }
        }
        return CONTINUAR;
    }

    /**
     * Buscar destinatario.
     *
     * @param id
     *            the id
     * @return the destinatario entity
     */
    private DestinatarioEntity buscarDestinatario(String id) {
        LOGGER.info("Busco destinatario por id");
        DestinatarioEntity destinatario = new DestinatarioEntity();
        for (DestinatarioEntity entity : sesionAgenda.getDestinatariosEntity()) {
            if (StringUtils.equals(entity.getId(), id)) {
                destinatario = entity;
                break;
            }
        }
        return destinatario;
    }

    /**
     * Eliminar destinatario sesion.
     *
     * @param id
     *            the id
     * @return true, if successful
     */
    private boolean eliminarDestinatarioSesion(String id) {
        LOGGER.info("Busco destinatario por id");
        for (DestinatarioEntity entity : sesionAgenda.getDestinatariosEntity()) {
            if (StringUtils.equals(entity.getId(), id)) {
                return sesionAgenda.getDestinatariosEntity().remove(entity);
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
     * AgendaDestinatariosManager#
     * grabarEstadisticaFeedbackModificacionDestinatario(
     * ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity.
     * DatosEntradaAgendaDestinatario)
     */
    @Override
    public void grabarEstadisticaComprobanteModificacionDestinatario(DatosEntradaAgendaDestinatario datos) {
        LOGGER.info("Grabo estadistica de comprobante de modificar Destinatario");
        if (TipoAgendaEnum.AGENDA_ALIAS.getTag().equals(datos.getTipoDestinatario())) {
            estadisticaManager.add(EstadisticasConstants.FEEDBACK_MODIFICACION_AGENDADESTINATARIOS_ALIAS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (TipoAgendaEnum.AGENDA_RIO.getTag().equals(datos.getTipoDestinatario())) {
            estadisticaManager.add(EstadisticasConstants.FEEDBACK_MODIFICACION_AGENDADESTINATARIOS_RIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (TipoAgendaEnum.AGENDA_OTROS_BANCOS.getTag().equals(datos.getTipoDestinatario())) {
            estadisticaManager.add(EstadisticasConstants.FEEDBACK_MODIFICACION_AGENDADESTINATARIOS_OTROS_BANCOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (TipoAgendaEnum.AGENDA_EXTRACCIONES.getTag().equals(datos.getTipoDestinatario())) {
            estadisticaManager.add(EstadisticasConstants.FEEDBACK_MODIFICACION_AGENDADESTINATARIOS_ENVIO_EFECTIVO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
    }
}
