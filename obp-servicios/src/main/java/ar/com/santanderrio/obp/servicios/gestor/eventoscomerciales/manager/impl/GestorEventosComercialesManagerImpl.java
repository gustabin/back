package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.*;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.bo.CampaniaBeneficiosBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.GestorEventoComercialBO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaPoliticaPrivacidadDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.PoliticasPrivacidadDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.AdditionalData;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.FinalizarPromesaEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GestionEventoComercialOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.NotificacionOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertaFinanciacionEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.PaquetesTerminosCondicionesEnum;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.SolicitarFinanciacionEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.SolicitudFinanciacionEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.GestorEventosComercialesManager;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.web.manager.OfertaComercialManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.referidos.manager.ReferidosManager;
import ar.com.santanderrio.obp.servicios.referidos.view.ReferidosInicioResponseView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosDeDomicilioDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.SelectorYCabeceraBO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomicilioView;
import ar.com.santanderrio.obp.servicios.token.externos.TokenManager;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.AgendaTransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendaView;


/**
 * The Class GestorEventosComercialesManagerImpl.
 */
@Component
public class GestorEventosComercialesManagerImpl implements GestorEventosComercialesManager {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestorEventosComercialesManagerImpl.class);

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The gestor evento comercial BO. */
    @Autowired
    private GestorEventoComercialBO gestorEventoComercialBO;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The legal BO. */
    @Autowired
    private LegalBO legalBO;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The cuentas BO. */
    @Autowired
    private CuentaBO cuentasBO;
    
    /** The ModuloPermiso BO. */
    @Autowired
    private ModuloPermisoBO moduloPermisoBO;
    
    /** The agenda transferencia manager impl. */
    @Autowired
    private AgendaTransferenciaManager agendaTransferenciaManagerImpl;	
    
    /** The campania beneficios BO. */
    @Autowired
    private CampaniaBeneficiosBO campaniaBeneficiosBO;

    /** The carrusel imagen default. */
    @Value("${CARRUSEL.DEFAULT.IMAGEN}")
    private String carruselImagenDefault;
    
    /** The carrusel boton default. */
    @Value("${CARRUSEL.DEFAULT.BOTON}")
    private String carruselBotonDefault;

    /** The carrusel call to action default. */
    @Value("${CARRUSEL.DEFAULT.CALLTOACTION}")
    private String carruselCallToActionDefault;

    /** The carrusel autodesplazamiento. */
    @Value("${CARRUSEL.AUTODESPLAZAMIENTO:5}")
    private String carruselAutodesplazamiento;

    /** The Constant CERO *. */
    private static final String CERO = "0";
    
    /** The Constant MOBILE *. */
    private static final String MOBILE = "MOBILE";
    
    /** The Constant CERO *. */
    private static final String DESKTOP = "DESKTOP";
    
	/** The Constant CODIGO_PROMESA_PAGO_VENCIDA. */
	private static final String CODIGO_PROMESA_PAGO_VENCIDA = "PM0003";
	
	/** The Constant CODIGO_AVISO_DEUDA_PROMESA_PAGO. */
	private static final String CODIGO_AVISO_DEUDA_PROMESA_PAGO = "PM0001";
	
	/** The Constant GOTO_AVISO_PROMESA_PAGO. */
	private static final String GOTO_AVISO_PROMESA_PAGO = "gotoAvisoDeudaPP()";
	
	/** The Constant GOTO_SEGUROS_COTIZACION. */
	private static final String GOTO_SEGUROS_COTIZACION = "gotoSeguroCotizarSolicitar()";
	
	/** The Constant GOTO_REFERIDOS. */
	private static final String GOTO_REFERIDOS = "gotoReferidos()";	
	
	/** The Constant TERMINOS_Y_CONDICIONES. */
	private static final String TERMINOS_Y_CONDICIONES = "Términos y Condiciones";
	
	/** The Constant GOTO_MOSTRAR_TYC. */
	private static final String GOTO_MOSTRAR_TYC = "gotoAceptarTerminosCondiciones()";
	
    /** The oferta comercial manager. */
    @Autowired
    private OfertaComercialManager ofertaComercialManager;
    
	/** The sesion tarjetas. */
	@Autowired
	protected SesionTarjetas sesionTarjetas;
	
	/** The selector Y cabecera BO. */
	@Autowired
	private SelectorYCabeceraBO selectorYCabeceraBO;
	
	/** The seguro manager. */
	@Autowired
	private TokenManager seguroManager;
	
    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;
    
    /** The session parametros. */
    @Autowired
    private SesionParametros sessionParametros;
    
	/** The referidos manager. */
	@Autowired
	private ReferidosManager referidosManager;

	/** The financiaciones hora desde. */
	@Value("${FINANCIACIONES.HORA.DESDE}")
	private String financiacionesHoraDesde;

	/** The financiaciones hora hasta. */
	@Value("${FINANCIACIONES.HORA.HASTA}")
	private String financiacionesHoraHasta;

	private static final String GOTO_POLITICAS_PRIVACIDAD = "gotoPoliticasPrivacidad()";
	
	@Value("${LINK.TYC.POLITICAS.PRIVACIDAD}")
	private String linkTyCPoliticasPrivacidad;

    private static final String GOTO_OFERTA_MASTERCARD_BLACK = "TyCgotoMasterBlack()";
    
    private static final String ERROR_GENERICO = "ERROR_GENERICO";

    private static final String GOTO_TYC_DUO = "gotoTyCDUO()";
	
    /**
     * Obtener cantidad notificaciones.
     *
     * @param cliente the cliente
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.
     * GestorEventosComercialesManager#obtenerCantidadNotificaciones(ar.com.
     * santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<NotificacionesComercialesView> obtenerCantidadNotificaciones(Cliente cliente) {
        if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.NOTIFICACIONES)
                .tienePermisoDeVisibilidad()) {
            try {
                GestionEventoComercialOutEntity notificaciones = gestorEventoComercialBO
                        .obtenerCantidadNotificaciones(sesionCliente.getCliente());
                return generarRespuesta(notificaciones);
            } catch (BusinessException e) {
                LOGGER.error("Error al obtener cantidad de notificaciones. ", e);

            }
        }
        return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "1608");
    }

    /**
     * Obtener notificaciones.
     *
     * @param cliente the cliente
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.
     * GestorEventosComercialesManager#obtenerNotificaciones(ar.com.santanderrio
     * .obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<NotificacionesComercialesView> obtenerNotificaciones(Cliente cliente) {
        if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.NOTIFICACIONES)
                .tienePermisoDeVisibilidad()) {
        	if(cliente.getSinProductos()){
        		//Si el cliente no tiene productos, se fuerza el mensaje de las notificaciones.
        		return respuestaFactory.crearRespuestaWarning(null, TipoError.WARNING, "1609");
        	}
            try {
                GestionEventoComercialOutEntity notificaciones = gestorEventoComercialBO
                        .obtenerNotificaciones(sesionCliente.getCliente());
                grabarEstadistica(EstadisticasConstants.CONSULTA_LISTADO_NOTIFICACIONES, EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
                return generarRespuesta(notificaciones);
            } catch (BusinessException e) {
            	grabarEstadistica(EstadisticasConstants.CONSULTA_LISTADO_NOTIFICACIONES, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, sesionCliente.getCliente());
                LOGGER.error("Error al obtener notificaciones. ", e);
            }
        }
        return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "1608");
    }
    
    /**
     * Borrar notificacion.
     *
     * @param cliente the cliente
     * @param oferta the oferta
     * @return the respuesta
     */
    @Override
    public Respuesta<OfertaComercialView> borrarNotificacion(Cliente cliente, OfertaComercialView oferta) {
        try {
            Boolean notificacionBorrada = gestorEventoComercialBO.borrarNotificacion(sesionCliente.getCliente(), oferta.getId());            
            if (notificacionBorrada){
                grabarEstadistica(EstadisticasConstants.ELIMINAR_NOTIFICACION, EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
                return respuestaFactory.crearRespuestaOk(oferta);
            }else {
                grabarEstadistica(EstadisticasConstants.ELIMINAR_NOTIFICACION, EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
                return respuestaFactory.crearRespuestaOk(oferta);
            }
        } catch (BusinessException e) {
        	grabarEstadistica(EstadisticasConstants.ELIMINAR_NOTIFICACION, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, sesionCliente.getCliente());
            LOGGER.error("Error al obtener notificaciones. ", e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "1608");
        }            
    }
    
    
	/**
	 * Confirmar promesa pago.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConfirmarPromesaPagoView> confirmarPromesaPago() {
		grabarEstadistica(EstadisticasConstants.CONFIRMAR_PROMESA_DE_PAGO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaOk(new ConfirmarPromesaPagoView(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CONFIRMAR_PROMESA).getMensaje()));
	}
    	
	
    /**
     * Grabar estadistica.
     *
     * @param codigoEstadistica the codigo estadistica
     * @param codigoError the codigo error
     * @param cliente the cliente
     */
    private void grabarEstadistica(String codigoEstadistica, String codigoError, Cliente cliente) {
        Estadistica estadistica = new Estadistica();
        estadistica.setCodigoTransaccion(codigoEstadistica);
        estadistica.setCodigoError(codigoError);
        estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), cliente);
    }	
    
        
    
	/**
	 * Finalizar promesa pago.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	@Override
    public Respuesta<FinalizarPromesaPagoOutView> finalizarPromesaPago(FinalizarPromesaPagoInView inView) {
        
	    Respuesta<FinalizarPromesaEntity> respuestaBO = gestorEventoComercialBO
                                .finalizarPromesaPago(sesionCliente.getCliente(), inView);
        if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
            return crearRespuestaOkFinalizarPromesaPago(inView);
        } else {
            return crearRespuestaErrorFinalizarPromesaPago(
                    respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError());
        }

    }
    
    
	/**
	 * Crear respuesta ok finalizar promesa pago.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	private Respuesta<FinalizarPromesaPagoOutView> crearRespuestaOkFinalizarPromesaPago(
			FinalizarPromesaPagoInView inView) {
		grabarEstadistica(EstadisticasConstants.FINALIZAR_PROMESA_DE_PAGO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK,
				sesionCliente.getCliente());
		FinalizarPromesaPagoOutView finalizarView = new FinalizarPromesaPagoOutView();
		String mensajeOK = this.mensajeBO.obtenerMensajePorCodigo("1917").getMensaje();	
		mensajeOK = MessageFormat.format(mensajeOK, inView.getImportePromesa().replaceAll("\\$ ", "\\$"), inView.getFechaPromesa());
		finalizarView.setMensajeInformativo(mensajeOK);
		String mensajeWarning = this.mensajeBO.obtenerMensajePorCodigo("1916").getMensaje();
		finalizarView.setMensajeWarning(mensajeWarning);
		return respuestaFactory.crearRespuestaOk(FinalizarPromesaPagoOutView.class, finalizarView);
	}

	
	
	/**
	 * Crear respuesta error finalizar promesa pago.
	 *
	 * @param tipoError the tipo error
	 * @return the respuesta
	 */
	private Respuesta<FinalizarPromesaPagoOutView> crearRespuestaErrorFinalizarPromesaPago(String tipoError) {
		grabarEstadistica(EstadisticasConstants.FINALIZAR_PROMESA_DE_PAGO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
				sesionCliente.getCliente());		
		return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeBO.obtenerMensajePorCodigo("1919").getMensaje(), tipoError);
	}
	
	
	
	
	
	/**
	 * Obetener notificaciones perfil.
	 *
	 * @param cliente the cliente
	 * @return the respuesta
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.GestorEventosComercialesManager#obetenerNotificacionesPerfil(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<NotificacionesComercialesView> obetenerNotificacionesPerfil(Cliente cliente) {
        if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.NOTIFICACIONES)
                .tienePermisoDeVisibilidad()) {
            try {
                GestionEventoComercialOutEntity notificaciones = gestorEventoComercialBO
                        .obtenerNotificacionesPerfil(sesionCliente.getCliente());
                return generarRespuestaPerfil(notificaciones);
            } catch (BusinessException e) {
                LOGGER.error("Error al obtener notificaciones. ", e);
            }
        }
	 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "1608");	    
	}

    /**
     * Generar respuesta.
     *
     * @param notificaciones
     *            the notificaciones
     * @return the respuesta
     */
    private Respuesta<NotificacionesComercialesView> generarRespuesta(GestionEventoComercialOutEntity notificaciones) {
        if (notificaciones.tieneNotificaciones() && !CollectionUtils.isEmpty(notificaciones.getNotificaciones())) {
        	
        	TokenView seguros = cargarTokenSegurosNotificaciones(notificaciones);
        	cargarUrlNotificacionReferidos(notificaciones);
        	Boolean hayPP = revisarSiHayDeudasPP(notificaciones);
        	NotificacionesComercialesView notificacionesComercialesView = new NotificacionesComercialesView(notificaciones, seguros, sesionCliente.getCliente());
        	if (hayPP) {
        		grabarEstadisticasPromesaPago(notificacionesComercialesView);
        	}
            return respuestaFactory.crearRespuestaOk(notificacionesComercialesView);
        } else if (notificaciones.tieneNotificaciones() && CollectionUtils.isEmpty(notificaciones.getNotificaciones())) {
        	return respuestaFactory.crearRespuestaOk(new NotificacionesComercialesView(notificaciones, new TokenView(), sesionCliente.getCliente()));
        }

        return respuestaFactory.crearRespuestaWarning(null, TipoError.WARNING, "1609");
    }
    
    /**
     * Cargar token seguros notificaciones.
     *
     * @param notificaciones the notificaciones
     * @return the token view
     */
    private TokenView cargarTokenSegurosNotificaciones(GestionEventoComercialOutEntity notificaciones) {
    	
    	TokenView token = new TokenView();
    	
    	for (NotificacionOutEntity notificacion : notificaciones.getNotificaciones()) {
    		if (notificacion.getLink().contains(GOTO_SEGUROS_COTIZACION)) {
    			token = seguroManager.obtenerTokenEncriptado().getRespuesta();
    		}
    	}
    	return token;	
    }
    
    /**
     * Cargar url notificacion referidos.
     *
     * @param notificaciones the notificaciones
     */
    private void cargarUrlNotificacionReferidos(GestionEventoComercialOutEntity notificaciones) {    	    	
    	for (NotificacionOutEntity notificacion : notificaciones.getNotificaciones()) {
    		if (notificacion.getLink().contains(GOTO_REFERIDOS)) {
                Respuesta<ReferidosInicioResponseView> resp = referidosManager.obtenerInicioReferidos();               
                String url = resp.getRespuesta().getUrlInvitacion() + resp.getRespuesta().getFirma();                
    			notificacion.setUrl(url);
    		}
    	}
    }
    
    /**
     * Revisar si hay deudas PP.
     *
     * @param notificaciones the notificaciones
     * @return the boolean
     */
    private Boolean revisarSiHayDeudasPP (GestionEventoComercialOutEntity notificaciones) {
    	
    	Boolean hayPP = Boolean.FALSE;
    	
    	for (NotificacionOutEntity notificacion : notificaciones.getNotificaciones()) {
    		if (CODIGO_PROMESA_PAGO_VENCIDA.equals(notificacion.getCodigo())) {
    			String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_PROMESA_PAGO_VENCIDA).getMensaje();
    			notificacion.setMensajePromesaPagoVencida(mensaje);
    		} else if (CODIGO_AVISO_DEUDA_PROMESA_PAGO.equals(notificacion.getCodigo())) {
    			String mensajeDescripcion = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_DESCRIPTIVO_DEUDAS_PP).getMensaje();
    			notificacion.setMensajeDescripcionPP(mensajeDescripcion);
    			String mensajeDescripcionDeudasViejas = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_DESCRIPTIVO_DEUDAS_PP_VIEJAS).getMensaje();
    			notificacion.setMensajeDescripcionPPDeudasViejas(mensajeDescripcionDeudasViejas);
    			String mensajeAyudaPP = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_AYUDA_PROMESA_PAGO).getMensaje();
    			notificacion.setAyudaPromesaPago(mensajeAyudaPP);
    			String mensajeAyudaDescubierto = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_AYUDA_DESCUBIERTO).getMensaje();
    			notificacion.setAyudaDescubierto(mensajeAyudaDescubierto);
    			String mensajeErrorDeudasPP = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_PROMESA_PAGO_ERROR_DEUDAS).getMensaje();
    			notificacion.setMensajeErrorDeudasPP(mensajeErrorDeudasPP);
    			hayPP = Boolean.TRUE;
    			sesionParametros.setIdNotificacionPromesaPago(notificacion.getIdNotificacion());
    		} else {
    			notificacion.setMensajePromesaPagoVencida(StringUtils.EMPTY);
    		}
    	}
    	return hayPP;

    }
    
    /**
     * Grabar estadisticas promesa pago.
     *
     * @param notificacionesComercialesView the notificaciones comerciales view
     */
    private void grabarEstadisticasPromesaPago(NotificacionesComercialesView notificacionesComercialesView) {
    	
    	for (NotificacionComercialView notificacionView : notificacionesComercialesView.getNotificaciones()) {
    		if (GOTO_AVISO_PROMESA_PAGO.equals(notificacionView.getLink())) {
    			if (notificacionView.getPromesaPago().getErrorParcial()) {
    				estadisticaManager.add(EstadisticasConstants.PROMESA_PAGO_DETALLE_DEUDAS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
    			} else if (notificacionView.getPromesaPago().getErrorTotal()) {
    				estadisticaManager.add(EstadisticasConstants.PROMESA_PAGO_DETALLE_DEUDAS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    			} else {
    				estadisticaManager.add(EstadisticasConstants.PROMESA_PAGO_DETALLE_DEUDAS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    			}
    		}
    		
    	}
    	
    }
    
    /**
     * Generar respuesta.
     *
     * @param notificaciones
     *            the notificaciones
     * @return the respuesta
     */
    private Respuesta<NotificacionesComercialesView> generarRespuestaPerfil(GestionEventoComercialOutEntity notificaciones) {
        if (!notificaciones.getNotificaciones().isEmpty()) {
        	notificaciones.setCantNotifSinLeer(CERO);
            return respuestaFactory.crearRespuestaOk(new NotificacionesComercialesView(notificaciones, new TokenView(), sesionCliente.getCliente()));
        }
        return respuestaFactory.crearRespuestaWarning(null, TipoError.WARNING, "1609");
    }

    /**
     * Informa el interes que tiene un cliente en la notificacion.
     *
     * @param idNotificacion
     *            the id notificacion
     * @return the respuesta
     */
    @Override
    public Respuesta<Void> informarInteresEnNotificacion(String idNotificacion) {
        Respuesta<Void> respuesta = new Respuesta<Void>();
        Cliente cliente = obtenerCliente();
        Boolean tieneInteresNotificacion = consultarInformarInteresEnNotificacion(cliente, idNotificacion);
        if (tieneInteresNotificacion) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        } else {
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        }
        return respuesta;
    }

    /**
     * Consultar informar interes en notificacion.
     *
     * @param cliente
     *            the cliente
     * @param id
     *            the id
     * @return the boolean
     */
    private Boolean consultarInformarInteresEnNotificacion(Cliente cliente, String id) {
        return gestorEventoComercialBO.informarInteresEnNotificacion(cliente, id);
    }

    /**
     * Obtener cliente.
     *
     * @return the cliente
     */
    private Cliente obtenerCliente() {
        return sesionCliente.getCliente();
    }

    /**
     * Actualizar notificaciones.
     *
     * @param cliente the cliente
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.
     * GestorEventosComercialesManager#actualizarNotificaciones(ar.com.
     * santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<Boolean> actualizarNotificaciones(Cliente cliente) {
        if (gestorEventoComercialBO.actualizarNotificaciones(cliente)) {
            return respuestaFactory.crearRespuestaOk(Boolean.TRUE);
        }
        return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "1608");
    }

    /**
     * Obtener carrusel.
     *
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.
     * GestorEventosComercialesManager#obtenerCarrusel()
     */
    @Override
    public Respuesta<OfertasComercialesView> obtenerCarrusel() {
        if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.CARRUSEL).isModuloOculto()) {
            return respuestaFactory.crearRespuestaError(OfertasComercialesView.class, null);
        }
        OfertasComercialesView carrusel = new OfertasComercialesView();
        carrusel.setTiempoAutodesplazamiento(Integer.parseInt(carruselAutodesplazamiento) * 1000);

        if (sesionParametros.getOfertasComerciales() != null) {
        	cargarlinkReferidosOfertasComerciales(sesionParametros.getOfertasComerciales());
        }

        if (sesionParametros.getOfertasComerciales() != null && sesionCliente.getCliente().getSinProductos().equals(Boolean.FALSE)) {
        	TokenView seguros = cargarTokenSegurosOfertasComerciales(sesionParametros.getOfertasComerciales());
        	carrusel.cargarOfertasCarrusel(sesionParametros.getOfertasComerciales(), seguros, sesionCliente.getCliente());
        }

        if (carrusel.getOfertas().isEmpty() || sesionCliente.getCliente().getSinProductos()) {
        	OfertaComercialView ofertaDefault = new OfertaComercialView(this.carruselImagenDefault,
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CARRUSEL_GENERICO_TITULO).getMensaje(),
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CARRUSEL_GENERICO_DESCRIPCION)
                            .getMensaje(),
                    this.carruselCallToActionDefault);
            ofertaDefault.setBoton(this.carruselBotonDefault);
            carrusel.getOfertas().add(ofertaDefault);
        }

        EventosComercialesDTO eventoComercialDTO = sesionParametros.getOfertasComerciales();
        for (OfertaComercialDTO tycDuo : eventoComercialDTO.getOfertas()) {
            if (GOTO_TYC_DUO.equals(tycDuo.getGotoLink().getLink())) {
                carrusel.setMostrarAceptacionDuo(true);
                break;
            }
        }

        return respuestaFactory.crearRespuestaOk(carrusel);
    }

    @Override
    public Respuesta<Void> informarFeedbackOferta(OfertaComercialView oferta,String action) {
		String idOferta = oferta.getId();
        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        if (sesionParametros.getOfertasComerciales() != null) {
            OfertaComercialDTO ofertaComercialDTO = sesionParametros.getOfertasComerciales()
                    .obtenerOfertaPorIdRtd(idOferta);
            if (ofertaComercialDTO != null && ofertaComercialDTO.isFeedbackAvailable(action)) {
                Boolean tieneFeedback = gestorEventoComercialBO.informarFeedbackOferta(sesionCliente.getCliente(),
                        oferta.getImagenUrl() == null ? oferta.getSeccion() : null, ofertaComercialDTO,action);
                if (Boolean.TRUE.equals(tieneFeedback)) {
                    respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
					if(action.equals("VIEWED")){
						ofertaComercialDTO.getFeedbackAvailable().remove(action);
					}
                }
            }
        }

        return respuesta;
    }

    /**
     * Obtener ofertas por seccion.
     *
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.
     * GestorEventosComercialesManager#obtenerOfertasPorSeccion()
     */
    @Override
    public Respuesta<OfertasComercialesView> obtenerOfertasPorSeccion() {
    	if (sesionParametros.getOfertasComerciales() == null) {
    		ofertaComercialManager.obtenerOfertasComerciales(); 
    	}
        OfertasComercialesView ofertasPorSecciones = new OfertasComercialesView();
        
        if (sesionParametros.getOfertasComerciales() != null) {
        	cargarlinkReferidosOfertasComerciales(sesionParametros.getOfertasComerciales());
        }
        
        if (sesionParametros.getOfertasComerciales() != null) {
        	TokenView seguros = cargarTokenSegurosOfertasComerciales(sesionParametros.getOfertasComerciales());
            ofertasPorSecciones.cargarOfertasSeccion(sesionParametros.getOfertasComerciales(), seguros);
        }
        if (!StringUtils.isBlank(sesionParametros.getOfertasComerciales().getIdEncuesta()) && 
        		CERO.compareTo(sesionParametros.getOfertasComerciales().getIdEncuesta()) < 0) {
        	ofertasPorSecciones.setMostrarEncuesta(true);
        }
        return respuestaFactory.crearRespuestaOk(ofertasPorSecciones);
    }
    
    /**
     * Cargar token seguros ofertas comerciales.
     *
     * @param ofertasComerciales the ofertas comerciales
     * @return the token view
     */
    private TokenView cargarTokenSegurosOfertasComerciales(EventosComercialesDTO ofertasComerciales) {
    	
    	TokenView token = new TokenView();
    	
    	for (OfertaComercialDTO ofertaDTO : ofertasComerciales.getOfertas()) {
    		if (ofertaDTO.getGotoLink().getLink() != null && ofertaDTO.getGotoLink().getLink().contains(GOTO_SEGUROS_COTIZACION)) {
    			token = seguroManager.obtenerTokenEncriptado().getRespuesta();
    		}
    	}
    	return token;	
    }
    
    /**
     * Cargarlink referidos ofertas comerciales.
     *
     * @param ofertasComerciales the ofertas comerciales
     */
    private void cargarlinkReferidosOfertasComerciales(EventosComercialesDTO ofertasComerciales) {

        for (OfertaComercialDTO ofertaDTO : ofertasComerciales.getOfertas()) {
            if (ofertaDTO.getGotoLink().getLink() != null && ofertaDTO.getGotoLink().getLink().contains(GOTO_REFERIDOS)) {
                Respuesta<ReferidosInicioResponseView> resp = referidosManager.obtenerInicioReferidos();               
                String url = resp.getRespuesta().getUrlInvitacion() + resp.getRespuesta().getFirma();
                ofertaDTO.setUrlReferidos(url);
            }
        }
    }


    /**
     * Obtener oferta sesion por id.
     *
     * @param idOferta
     *            the id oferta
     * @param ofertas
     *            the ofertas
     * @return the oferta comercial DTO
     */
    private OfertaComercialDTO obtenerOfertaSesionPorId(String idOferta, EventosComercialesDTO ofertas) {
        if (ofertas != null) {
            return ofertas.obtenerOfertaPorIdRtd(idOferta);
        }
        return null;
    }
    
    /**
	 * Registrar encuesta.
	 *
	 * @param respuestasEncuesta
	 *            the respuestas encuesta
	 * @return the respuesta
	 */
    @Override
	public Respuesta<Void> registrarEncuesta(List<RespuestaEncuestaView> respuestasEncuesta) {
    	Respuesta<Void> respuesta = new Respuesta<Void>();
    	Cliente cliente = sesionCliente.getCliente();
    	EventosComercialesDTO eventosComercialesDTO = gestorEventoComercialBO.obtenerRespuestasEncuesta(respuestasEncuesta);
    	eventosComercialesDTO.setJsessionId(sesionParametros.getOfertasComerciales().getJsessionId());
    	
    	if (!StringUtils.isBlank(sesionParametros.getOfertasComerciales().getIdEncuesta()) && 
        		CERO.compareTo(sesionParametros.getOfertasComerciales().getIdEncuesta()) < 0) {
        	eventosComercialesDTO.setIdEncuesta(sesionParametros.getOfertasComerciales().getIdEncuesta());
        }
    	
    	gestorEventoComercialBO.registrarEncuesta(cliente, eventosComercialesDTO);
    	return respuesta;
    }
    
    /**
	 * Informar Gestion AC.
	 *
	 * @param domicilioDeReemplazo
	 *            the domicilio de reemplazo
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> informarGestionAC(DomicilioView domicilioDeReemplazo) {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		Cliente cliente = sesionCliente.getCliente();
		String nroSucursal = cliente.obtenerCuentaPrincipal().getNroSucursal();
		DatosDeDomicilioDTO domicilioDTO = new DatosDeDomicilioDTO(domicilioDeReemplazo, nroSucursal);

		if (sesionParametros.getContadorReemplazoTarjetas() == null) {
            sesionParametros.setContadorReemplazoTarjetas(new ContadorIntentos(2));
        }
		
		if (sesionParametros.getContadorReemplazoTarjetas().permiteReintentar()) {
			Boolean estadoRespuesta = gestorEventoComercialBO.informarGestionAC(cliente, domicilioDTO);
			if (estadoRespuesta) {
				sesionParametros.setContadorReemplazoTarjetas(null);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			}
		} else {
			sesionParametros.setContadorReemplazoTarjetas(null);
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS, 
					CodigoMensajeConstantes.ERROR_REINTENTOS_REEMPLAZO_DE_TARJETAS);
		}
		return respuesta;
	}

	/**
	 * Obtener premiaciones.
	 * 
	 * @param fechaCorte
	 * 		the fecha corte
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ChanceView> obtenerPremiaciones(String fechaCorte) {
		Cliente cliente = sesionCliente.getCliente();

		if(cliente == null || cliente.getNup().isEmpty()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_PREMIACIONES,
    				CodigoMensajeConstantes.ERROR_SERVICIO_PREMIACIONES);
		}
		
		String dispositivo = sesionParametros.getRegistroSession().isMobile() ? MOBILE :DESKTOP;
		
		Respuesta<ChancesDTO> respChancesDTO = gestorEventoComercialBO.obtenerPremiaciones(dispositivo, cliente, fechaCorte);
		Respuesta<ChanceHistorialView>  respuestaHistoriaView = gestorEventoComercialBO.obtenerCotasMaximaYMinina(dispositivo, cliente);
		
		if(EstadoRespuesta.ERROR.equals(respChancesDTO.getEstadoRespuesta())) {
			return respuestaFactory.transformar(null, respChancesDTO);
		}
		
		if(EstadoRespuesta.ERROR.equals(respuestaHistoriaView.getEstadoRespuesta())) {
			return respuestaFactory.transformar(null, respuestaHistoriaView);
		}
		
		if(EstadoRespuesta.WARNING.equals(respChancesDTO.getEstadoRespuesta())){
			ChanceView chanceView = new ChanceView();
			chanceView.setHeader(new ChanceHeaderView ("","0"));
			if(respChancesDTO.getRespuesta() == null) {
				return respuestaFactory.transformar(chanceView, respChancesDTO);
			}
			chanceView = new ChanceView(respChancesDTO.getRespuesta(), respuestaHistoriaView.getRespuesta());
			chanceView.setHeader(new ChanceHeaderView ("","0"));
			return respuestaFactory.transformar(chanceView, respChancesDTO);
		}
		
		return respuestaFactory.crearRespuestaOk(new ChanceView(respChancesDTO.getRespuesta(), respuestaHistoriaView.getRespuesta()));
	}



	/**
	 * Actualizar cuenta favorita.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<NumeroCuentaFavoritaView> actualizarCuentaFavorita() {
    	try {
			cuentasBO.cargarAliasYFavoritos(sesionCliente.getCliente(), true);
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener alias y favoritos", e);
		}
    	
    	NumeroCuentaFavoritaView cuentaFavoritaView = new NumeroCuentaFavoritaView(sesionCliente.getCliente());
    	
    	return respuestaFactory.crearRespuestaOk(cuentaFavoritaView);

	}

	/**
	 * Actualizar tarjeta favorita.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<NumeroTarjetaFavoritaView> actualizarTarjetaFavorita() {
    	try {
			cuentasBO.cargarAliasYFavoritos(sesionCliente.getCliente(), false);
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener alias y favoritos", e);
		}
    	NumeroTarjetaFavoritaView tarjetaFavoritaView = new NumeroTarjetaFavoritaView(sesionCliente.getCliente());
    	sesionParametros.setIngresoDesdeGoto(Boolean.TRUE);
    	
		if(sesionTarjetas.getLimitesYDisponibles() == null) {
			Respuesta<DisponiblesYConsumoDTO> rtaDispyConsDTO = selectorYCabeceraBO.obtenerTarjetas(sesionCliente.getCliente());
			if (!rtaDispyConsDTO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				sesionTarjetas.setLimitesYDisponibles(rtaDispyConsDTO.getRespuesta().getLimitesYDisponiblesDTO());
			}
		}
    	
    	return respuestaFactory.crearRespuestaOk(tarjetaFavoritaView);
	}
	
	/**
	 * Cargar contratos transferencia.
	 */
	@Override
	public void cargarContratosTransferencia() {
		if (sesionCliente.getCliente().getContratos().isEmpty()) {
			agendaTransferenciaManagerImpl.validarContratoTransferencias(sesionCliente.getCliente(), new Respuesta<AgendaView>());
		}
	}

	/**
	 * Grabar estadistica ayuda promesa pago.
	 */
	@Override
	public void grabarEstadisticaAyudaPromesaPago() {
		estadisticaManager.add(EstadisticasConstants.PROMESA_PAGO_ABRIR_MENSAJE_AYUDA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Grabar estadistica ayuda descubierto PP.
	 */
	@Override
	public void grabarEstadisticaAyudaDescubiertoPP() {
		estadisticaManager.add(EstadisticasConstants.PROMESA_PAGO_ABRIR_MENSAJE_DESCUBIERTO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}
	
	/**
	 * Grabar estadistica configuracion promesa pago.
	 */
	@Override
	public void grabarEstadisticaConfiguracionPromesaPago() {
		sessionParametros.setContador(new ContadorIntentos(3));
		estadisticaManager.add(EstadisticasConstants.CONFIGURAR_PROMESA_PAGO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Obtener ofertas financiacion.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<OfertasFinanciacionOutView> obtenerOfertasFinanciacion() {
		Respuesta<OfertasFinanciacionOutView> respuesta;
		boolean financiacionesHabilitado = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.FINANCIACIONES)
                .tienePermisoDeVisibilidad();
		boolean esHorarioValidoFinanciaciones = validarHorarioAccesoFinanciaciones();

		if (financiacionesHabilitado && esHorarioValidoFinanciaciones) {
			Respuesta<List<OfertaFinanciacionEntity>> respuestaBO = gestorEventoComercialBO.obtenerOfertasFinanciacion(sesionCliente.getCliente());
			if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
                respuesta = formatearOfertasFinanciacionOutViewRespuesta(respuestaBO);
            } else {

				estadisticaManager.add(obtenerEstadisticaIngresoFinanciacion(), EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				respuesta = respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje() ,
                        respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError());
			}
		} else {
			estadisticaManager.add(obtenerEstadisticaIngresoFinanciacion(),
					!financiacionesHabilitado ? EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.FUERA_DE_HORARIO, CodigoMensajeConstantes.ERROR_FUERA_HORARIO_FINANCIACION);
		}
		return respuesta;
	}

    private String obtenerEstadisticaIngresoFinanciacion() {
	    String tipoOfertaRefinanciacion = sesionCliente.getCliente().getTipoOfertaRefinanciacion();
	    if(Cliente.CUOTAPHONE.equals(tipoOfertaRefinanciacion))
	        return EstadisticasConstants.INGRESO_FINANCIACION_CUOTAPHONE;

	    if(Cliente.EARLY_CURRENT.equals(tipoOfertaRefinanciacion))
            return EstadisticasConstants.INGRESO_FINANCIACION_EARLY_CURRENT;

        return EstadisticasConstants.INGRESO_FINANCIACION_EARLY_REGULAR;
    }

    private Respuesta<OfertasFinanciacionOutView> formatearOfertasFinanciacionOutViewRespuesta(Respuesta<List<OfertaFinanciacionEntity>> respuestaBO) {
        Respuesta<OfertasFinanciacionOutView> respuesta;
        OfertasFinanciacionOutView ofertasFinanciacion = new OfertasFinanciacionOutView();
        for (OfertaFinanciacionEntity oferta : respuestaBO.getRespuesta()) {
            OfertaFinanciacionView ofertaView = createOfertaView(oferta);
            ofertasFinanciacion.getOfertas().add(ofertaView);
            ofertasFinanciacion.setType(RefinanciacionType.valueOf(oferta.getRefinancingType()));
        }
        ofertasFinanciacion.setTieneCuenta(validarCuentaOperativa(sesionCliente.getCliente()));
        ofertasFinanciacion.setDefaultMail(sesionParametros.getCredencialesMya().getEmail());
        String legal = legalBO.obtenerLegalesPorCodigo(ofertasFinanciacion.getType().getLegalCode());

        legal = MessageFormat.format(legal, ofertasFinanciacion.getOfertas().get(0).getCft());
        ofertasFinanciacion.setLegal(legal);
        ofertasFinanciacion.setAyuda(mensajeBO.obtenerMensajePorCodigo(ofertasFinanciacion.getType().getAyudaCode()).getMensaje());
        ofertasFinanciacion.setTyc(mensajeBO.obtenerMensajePorCodigo(ofertasFinanciacion.getType().getTycCode()).getMensaje());
        ofertasFinanciacion.setMasInfo(mensajeBO.obtenerMensajePorCodigo(ofertasFinanciacion.getType().getMasInfoCode()).getMensaje());
        ofertasFinanciacion.setBullets(mensajeBO.obtenerMensajePorCodigo(ofertasFinanciacion.getType().getBulletCode()).getMensaje());
        ofertasFinanciacion.setTitulo(mensajeBO.obtenerMensajePorCodigo(ofertasFinanciacion.getType().getTituloCode()).getMensaje());

        if (ofertasFinanciacion.getType().getTituloMsjConfirmacionCode() != null &&
                ofertasFinanciacion.getType().getMsjConfirmacionCode() != null ) {
            String titulo = mensajeBO.obtenerMensajePorCodigo(ofertasFinanciacion.getType().getTituloMsjConfirmacionCode()).getMensaje();
            String descripcion = mensajeBO.obtenerMensajePorCodigo(ofertasFinanciacion.getType().getMsjConfirmacionCode()).getMensaje();
            ofertasFinanciacion.setMsjConfirmacion(new MensajeConfirmacion(titulo, descripcion));
        }

        estadisticaManager.add(obtenerEstadisticaIngresoFinanciacion(), EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        respuesta = respuestaFactory.crearRespuestaOk(OfertasFinanciacionOutView.class, ofertasFinanciacion);
        return respuesta;
    }

    /**
	 * Validar cuenta operativa.
	 *bp-
	 * @param cliente the cliente
	 * @return the boolean
	 */
	private Boolean validarCuentaOperativa(Cliente cliente) {
		Boolean retorno = false;
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (!cuenta.isCuentaCerrada() && cuenta.isTipoCuentaMonetaria()) {
				retorno = true;
				break;
			}	
		}
		return retorno;
	}

	/**
	 * Creates the oferta view.
	 *
	 * @param oferta the oferta
	 * @return the oferta financiacion out view
	 */
	private OfertaFinanciacionView createOfertaView(OfertaFinanciacionEntity oferta) {
		OfertaFinanciacionView ofertaView = new OfertaFinanciacionView();
		ofertaView.setOfferId(oferta.getOfferId());
		ofertaView.setProductDescription(oferta.getProductDescription());
		ofertaView.setTerm(oferta.getTerm());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date validityDate = sdf.parse(oferta.getValidityDate());
			ofertaView.setValidityDate(sdf2.format(validityDate));
		} catch (ParseException e) {
			LOGGER.error("Error de formato de fecha: {}", oferta.getValidityDate());
		}
		ofertaView.setState(oferta.getState());
        ofertaView.setMaxAmount(oferta.getMaxAmount());
		ofertaView.setRate(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(oferta.getRate())));
		ofertaView.setFirstQuotaValue(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(oferta.getFirstQuotaValue())));
		ofertaView.setInterest(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(oferta.getInterest())));
		ofertaView.setIva(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(oferta.getIva())));
		ofertaView.setInsurance(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(oferta.getInsurance())));
		ofertaView.setOtherCharges(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(oferta.getOtherCharges())));
		ofertaView.setPureQuotaValue(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(oferta.getPureQuotaValue())));
		ofertaView.setCft(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(oferta.getCft())));
		ofertaView.setRateType(oferta.getRateType());
		ofertaView.setTna(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(oferta.getTna())));
		ofertaView.setTea(ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(oferta.getTea())));
		ofertaView.setCreditCardDescription(oferta.getCreditCardDescription());

		return ofertaView;
	}

	/**
	 * Solicitar financiacion.
	 *
	 * @param solicitarFinanciacionInView the solicitar financiacion in view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<String> solicitarFinanciacion(SolicitarFinanciacionInView solicitarFinanciacionInView) {
		Respuesta<String> respuesta;
		boolean financiacionesHabilitado = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.FINANCIACIONES)
                .tienePermisoDeVisibilidad();
		boolean esHorarioValidoFinanciaciones = validarHorarioAccesoFinanciaciones();
		
		if (financiacionesHabilitado && esHorarioValidoFinanciaciones) {
			SolicitarFinanciacionEntity solicitarFinanciacionEntity = createSolicitarFinanciacionEntity(solicitarFinanciacionInView);
			Respuesta<Boolean> respuestaBO = gestorEventoComercialBO.solicitarFinanciacion(sesionCliente.getCliente(), solicitarFinanciacionEntity);
			if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
				respuesta = respuestaFactory.crearRespuestaOk(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.SOLICITUD_FINANCIACION_OK).getMensaje());
			} else {
				respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.SOLICITUD_FINANCIACION_ERROR);
			}
		} else {
			estadisticaManager.add(obtenerestadisticaSolicitarFinanciacion(),
					!financiacionesHabilitado ? EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.FUERA_DE_HORARIO, CodigoMensajeConstantes.ERROR_FUERA_HORARIO_FINANCIACION);
		}
		return respuesta;
	}

    private String obtenerestadisticaSolicitarFinanciacion() {

        String tipoOfertaRefinanciacion = sesionCliente.getCliente().getTipoOfertaRefinanciacion();
        if(Cliente.CUOTAPHONE.equals(tipoOfertaRefinanciacion))
            return EstadisticasConstants.SOLICITAR_FINANCIACION_CUOTAPHONE;

        if(Cliente.EARLY_CURRENT.equals(tipoOfertaRefinanciacion))
            return EstadisticasConstants.SOLICITAR_FINANCIACION_EARLY_CURRENT;

        return EstadisticasConstants.SOLICITAR_FINANCIACION_EARLY_REGULAR;
    }

    /**
	 * Creates the solicitar financiacion entity.
	 *
	 * @param solicitarFinanciacionInView the solicitar financiacion in view
	 * @return the solicitar financiacion entity
	 */
	private SolicitarFinanciacionEntity createSolicitarFinanciacionEntity(SolicitarFinanciacionInView solicitarFinanciacionInView) {
		SolicitarFinanciacionEntity ofertaSolicitada = new SolicitarFinanciacionEntity();
		List<SolicitudFinanciacionEntity> offers = new ArrayList<SolicitudFinanciacionEntity>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (SolicitudFinanciacionView oferta : solicitarFinanciacionInView.getOfertas()) {
			SolicitudFinanciacionEntity offer = new SolicitudFinanciacionEntity();
			offer.setOfferId(oferta.getOfferId());
			offer.setTermRequested(oferta.getTermRequested());
			offer.setFirstDueDate(sdf.format(new Date()));
			offer.setAmountRequested(oferta.getAmountRequested());
			offer.setDeclaredIncome("0");
			offer.setMail(oferta.getMail());
			List<AdditionalData> additionalData = new ArrayList<AdditionalData>();
			additionalData.add(new AdditionalData("ACCOUNT_NUMBER", sesionCliente.getCliente().getNombre().trim()));
			offer.setAdditionalData(additionalData);
			offers.add(offer);
		}
		ofertaSolicitada.setOffers(offers);
		return ofertaSolicitada;
	}

	/**
	 * Validar horario acceso financiaciones.
	 *
	 * @return true, if successful
	 */
	private boolean validarHorarioAccesoFinanciaciones() {
		boolean esHorarioValido = false;
		if (!ISBANStringUtils.isNullOrBlank(financiacionesHoraDesde) && !ISBANStringUtils.isNullOrBlank(financiacionesHoraHasta)) {
			try {
				esHorarioValido = ISBANStringUtils.compararHoras(financiacionesHoraDesde, financiacionesHoraHasta);
			} catch (ParseException e) {
				LOGGER.error("Error al parsear fechas: {} {}", financiacionesHoraDesde, financiacionesHoraHasta);
			}
		}
		return esHorarioValido;
	}

	/**
	 * Mostrar contratos ty C.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AceptarTyCView> mostrarContratosTyC() {

		AceptarTyCView aceptarPoliticasPrivacidadView = null;
		AceptarTyCView aceptarContratosView = null;
		
		if (sesionParametros.getOfertasComerciales() != null) {
			for (OfertaComercialDTO ofertaDTO : sesionParametros.getOfertasComerciales().getOfertas()) {
				if (GOTO_POLITICAS_PRIVACIDAD.equals(ofertaDTO.getGotoLink().getLink())) {
					OfertaPoliticaPrivacidadDTO ofertaPoliticaPrivacidad = new OfertaPoliticaPrivacidadDTO();
					ofertaPoliticaPrivacidad.setOferta(ofertaDTO);
					sesionParametros.setOfertaPoliticaPrivacidad(ofertaPoliticaPrivacidad);
					aceptarPoliticasPrivacidadView = armarStackCompartirDatos();
					if (StringUtils.isBlank(aceptarPoliticasPrivacidadView.getTextoTycDatosPersonales())) {
						return respuestaFactory.crearRespuestaError("", "NO_HAY_CONTRATOS", CodigoMensajeConstantes.ERROR_GENERICO);
					}
				} else if (GOTO_MOSTRAR_TYC.equals(ofertaDTO.getGotoLink().getLink())) {
					aceptarContratosView = cargarContratos(ofertaDTO);
				} 
			}
			
			if (aceptarPoliticasPrivacidadView != null) {
				return respuestaFactory.crearRespuestaOk(aceptarPoliticasPrivacidadView);
			} else if (aceptarContratosView != null) {
				return respuestaFactory.crearRespuestaOk(aceptarContratosView);
			}		
			
		} 
		return respuestaFactory.crearRespuestaError("", "NO_HAY_CONTRATOS", CodigoMensajeConstantes.ERROR_GENERICO);
	}
	
	private AceptarTyCView armarStackCompartirDatos() {
		final AceptarTyCView aceptarCompartirDatosView = new AceptarTyCView();
		
		try {
			aceptarCompartirDatosView.setTitulo("Actualizamos nuestros términos y condiciones");
			aceptarCompartirDatosView.setTextoTycDatosPersonales(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_ACEPTAR_COMPARTIR_DATOS_PERSONALES));
			aceptarCompartirDatosView.setEsStackCompartirDatos(Boolean.TRUE);
			aceptarCompartirDatosView.setLinkPoliticasPrivacidad(linkTyCPoliticasPrivacidad);
		} catch (DAOException e) {
			return aceptarCompartirDatosView;
		}
	
		return aceptarCompartirDatosView;
		
	}
	
	/**
	 * Cargar contratos.
	 *
	 * @param ofertaDTO the oferta DTO
	 * @return the aceptar ty C view
	 */
	private AceptarTyCView cargarContratos(OfertaComercialDTO ofertaDTO) {
		
		final AceptarTyCView aceptarContratosView = new AceptarTyCView();
		PaquetesTerminosCondicionesEnum enumPaquete = PaquetesTerminosCondicionesEnum.buscarPorCodigoPaquete(ofertaDTO.getVariable1Char());
        mapMensajesTyCbyPaquete(enumPaquete, aceptarContratosView);
		aceptarContratosView.setNombrePaquete(TERMINOS_Y_CONDICIONES + " " + enumPaquete.getNombrePaquete());
		ContratosPaqueteTyC contratosTyC = new ContratosPaqueteTyC();
		contratosTyC.setPaquete(enumPaquete.getContratoPaquete());
		contratosTyC.setCajaAhorroPesos(enumPaquete.getContratoCajaAhorroPesos());
		contratosTyC.setTarjetaCredito(enumPaquete.getContratoTC());
		contratosTyC.setComisiones(enumPaquete.getCuadroComisiones());
		
		aceptarContratosView.setContratosTyC(contratosTyC);
		return aceptarContratosView;
	}

	private void mapMensajesTyCbyPaquete(PaquetesTerminosCondicionesEnum paqueteEnum, AceptarTyCView targetView) {
	    switch (paqueteEnum) {
            case TARJECTA_CREDITO_MC:
                targetView.setTitulo(mensajeBO.obtenerMensajePorCodigo(
                        CodigoMensajeConstantes.ACEPTACION_TYC_TARJETA_MC_TITULO).getMensaje());
                targetView.setMensajeInformativo1(mensajeBO.obtenerMensajePorCodigo(
                        CodigoMensajeConstantes.ACEPTACION_TYC_TARJETA_MC_MENSAJE_INFORMATIVO1).getMensaje());
                targetView.setMensajeInformativo2(mensajeBO.obtenerMensajePorCodigo(
                        CodigoMensajeConstantes.ACEPTACION_TYC_TARJETA_MC_MENSAJE_INFORMATIVO2).getMensaje());
            break;

            default:
                targetView.setTitulo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ACEPTACION_TYC_TITULO).getMensaje());
                targetView.setMensajeInformativo1(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ACEPTACION_TYC_MENSAJE_INFORMATIVO1).getMensaje());
                targetView.setMensajeInformativo2(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ACEPTACION_TYC_MENSAJE_INFORMATIVO_2).getMensaje());
        }
    }
	
	
	/**
	 * Aceptar contratos ty C.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AceptarTyCView> aceptarContratosTyC() {

		Cliente cliente = sesionCliente.getCliente();
		OfertaComercialDTO ofertaComercial = new OfertaComercialDTO();
		Respuesta<AceptarTyCView> respuesta;
		
		for (OfertaComercialDTO ofertaDTO : sesionParametros.getOfertasComerciales().getOfertas()) {
			if (GOTO_MOSTRAR_TYC.equals(ofertaDTO.getGotoLink().getLink())) {
				ofertaComercial = ofertaDTO;
			}
		}
		
		Boolean respuestaAceptarContrato = campaniaBeneficiosBO.informarGestionAC(cliente, ofertaComercial);
		
		if (respuestaAceptarContrato) {
			AceptarTyCView respuestaView = new AceptarTyCView();
			respuestaView.setMensajeFeedbackOK(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ACEPTACION_TYC_MENSAJE_FEEDBACK).getMensaje());
			respuesta = respuestaFactory.crearRespuestaOk(respuestaView);
			estadisticaManager.add(EstadisticasConstants.TERMINOS_Y_CONDICIONES_ACEPTAR_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		} else {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
			estadisticaManager.add(EstadisticasConstants.TERMINOS_Y_CONDICIONES_ACEPTAR_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		
		return respuesta;
	}

	/**
	 * Grabar estadistica carrusel mostrar contratos ty C.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaCarruselMostrarContratosTyC() {
		estadisticaManager.add(EstadisticasConstants.TERMINOS_Y_CONDICIONES_INGRESO_STACK_DESDE_CARRUSEL, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/**
	 * Grabar estadistica recordar mas tarde contratos ty C.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaRecordarMasTardeContratosTyC() {
		estadisticaManager.add(EstadisticasConstants.TERMINOS_Y_CONDICIONES_RECORDAR_MAS_TARDE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	@Override
	public Respuesta<ContactabilidadView> informarAceptacionContacto(String idOferta) {

		OfertaComercialDTO oferta = getOfertaAInformar(idOferta);
		if (sesionParametros.getContactabilidadAceptada()) {
			return crearRespuestaOkContactabilidad();
		}
		Boolean informarAC = campaniaBeneficiosBO.informarGestionAC(sesionCliente.getCliente(), oferta);
		
		if (!informarAC) {				
			estadisticaManager.add(EstadisticasConstants.INFORMAR_INTERES_CONTACTO_OFERTAS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.MENSAJE_AVISAR_CONTACTABILIDAD_ERROR);
		}
		sesionParametros.setContactabilidadAceptada(Boolean.TRUE);
        return crearRespuestaOkContactabilidad();
	}
	
	private Respuesta<ContactabilidadView> crearRespuestaOkContactabilidad() {
		ContactabilidadView respuesta = new ContactabilidadView();
		estadisticaManager.add(EstadisticasConstants.INFORMAR_INTERES_CONTACTO_OFERTAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        respuesta.setMensajeOk(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_AVISAR_CONTACTABILIDAD_OK).getMensaje());

        return respuestaFactory.crearRespuestaOk(respuesta);
	}
	
	
    private OfertaComercialDTO getOfertaAInformar(String idOferta) {
    	for (OfertaComercialDTO oferta : sesionParametros.getOfertasComerciales().getOfertas()) {
			if (StringUtils.equalsIgnoreCase(idOferta, oferta.getIdRtd()) || StringUtils.equalsIgnoreCase(idOferta, oferta.getIdInterno())) {
				return oferta;
			}
		}
    	return null;
	}

	@Override
	public Respuesta<Void> cerrarStackPoliticasPrivacidad() {
		if (sesionParametros.getOfertaPoliticaPrivacidad().getAvisoGecOfertaPoliticasPrivacidad()) {
			return respuestaFactory.crearRespuestaOk(Void.class);
		}
		Boolean informarAC = campaniaBeneficiosBO.informarGestionAC(sesionCliente.getCliente(), sesionParametros.getOfertaPoliticaPrivacidad().getOferta());
		
		if (!informarAC) {				
			estadisticaManager.add(EstadisticasConstants.AVISAR_GEC_TYC_POLITICAS_PRIVACIDAD,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_GENERICO);
		}
		estadisticaManager.add(EstadisticasConstants.AVISAR_GEC_TYC_POLITICAS_PRIVACIDAD,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		sesionParametros.getOfertaPoliticaPrivacidad().setAvisoGecOfertaPoliticasPrivacidad(Boolean.TRUE);
		return respuestaFactory.crearRespuestaOk(Void.class);		
		
	}

	@Override
	public Respuesta<AceptarTyCView> grabarEstadisticaVerTycPoliticasPrivacidad() {
		
		AceptarTyCView aceptarTyCView = new AceptarTyCView();
		PoliticasPrivacidadDTO politicasPrivacidadDTO = new PoliticasPrivacidadDTO();
		Cliente cliente = sesionCliente.getCliente();
		
		politicasPrivacidadDTO.setDni(cliente.getDni());
		politicasPrivacidadDTO.setApellido(cliente.getApellido1() + " " + cliente.getApellido2());
		politicasPrivacidadDTO.setNombre(cliente.getNombre());
		politicasPrivacidadDTO.setNup(cliente.getNup());
		
		DateTime fechaHoy = new DateTime();
		DateTimeFormatter formatoFecha = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

		politicasPrivacidadDTO.setFechaAceptacion(formatoFecha.print(fechaHoy));
		politicasPrivacidadDTO.setAceptacion("1");
		
		try {
			String respuesta = gestorEventoComercialBO.registrarRespuestaPoliticasPrivacidad(politicasPrivacidadDTO);
			if ("0".equals(respuesta)) {
				aceptarTyCView.setMensajeFeedbackOK("Respuesta registrada correctamente");
				estadisticaManager.add(EstadisticasConstants.VER_TYC_POLITICAS_PRIVACIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(aceptarTyCView);
			}
			return respuestaFactory.crearRespuestaError("", ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}
	}

	@Override
	public Respuesta<TarjetaMastercardBlackView> iniciarFlujoMastercardBlack() {
		
		Respuesta<TarjetaMastercardBlackView> respuesta = new Respuesta<TarjetaMastercardBlackView>();
		TarjetaMastercardBlackView tarjetaMastercardBlackView = new TarjetaMastercardBlackView();
		
		tarjetaMastercardBlackView.setTitulo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MASTER_BLACK_TITULO).getMensaje());
		tarjetaMastercardBlackView.setDescripcion(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MASTER_BLACK_DESCRIPCION).getMensaje());
		
		try {
			tarjetaMastercardBlackView.setLegal(legalBO.obtenerLegal(CodigoMensajeConstantes.MASTER_BLACK_LEGAL));
		} catch (DAOException e) {
			estadisticaManager.add(EstadisticasConstants.INICIO_FLUJO_MASTERCARD_BLACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}
		respuesta.setRespuesta(tarjetaMastercardBlackView);
		
		estadisticaManager.add(EstadisticasConstants.INICIO_FLUJO_MASTERCARD_BLACK, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(tarjetaMastercardBlackView);
	}

	@Override
	public Respuesta<TarjetaMastercardBlackView> solicitarMastercardBlack() {
		
		EventosComercialesDTO eventoComercialDTO = sesionParametros.getOfertasComerciales();
		OfertaComercialView ofertaView = new OfertaComercialView();
		for (OfertaComercialDTO oferta : eventoComercialDTO.getOfertas()) {
			if (GOTO_OFERTA_MASTERCARD_BLACK.equals(oferta.getGotoLink().getLink())) {
			ofertaView = new OfertaComercialView(oferta, true);
            break;
			}
		}
				
		Respuesta<Void> respuestaInteres = informarFeedbackOferta(ofertaView,"DONE");
		if (EstadoRespuesta.OK.equals(respuestaInteres.getEstadoRespuesta())) {
			Respuesta<TarjetaMastercardBlackView> respuesta = new Respuesta<TarjetaMastercardBlackView>();
			TarjetaMastercardBlackView tarjetaMastercardBlackView = new TarjetaMastercardBlackView();
			
			tarjetaMastercardBlackView.setFeedbackOk(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MASTER_BLACK_FEEDBACK_OK).getMensaje());
			tarjetaMastercardBlackView.setFeedbackConsejoActualizar(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MASTER_BLACK_FEEDBACK_CONSEJO).getMensaje());
			
			respuesta.setRespuesta(tarjetaMastercardBlackView);
			
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_MASTERCARD_BLACK, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(tarjetaMastercardBlackView);
		} else {
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_MASTERCARD_BLACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}

	}

    @Override
    public Respuesta<TyCDuoView> aceptaTyCDUO() {

        EventosComercialesDTO eventoComercialDTO = sesionParametros.getOfertasComerciales();
        OfertaComercialView ofertaView = new OfertaComercialView();
        for (OfertaComercialDTO oferta : eventoComercialDTO.getOfertas()) {
            if (GOTO_TYC_DUO.equals(oferta.getGotoLink().getLink())) {
                ofertaView = new OfertaComercialView(oferta, true);
                break;
            }
        }

        Respuesta<Void> respuestaInteres = informarFeedbackOferta(ofertaView,"DONE");
        if (EstadoRespuesta.OK.equals(respuestaInteres.getEstadoRespuesta())) {
            Respuesta<TyCDuoView> respuesta = new Respuesta<TyCDuoView>();
            TyCDuoView tyCDuoView = new TyCDuoView();

            tyCDuoView.setFeedbackOK(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TYC_DUO_FEEDBACK_OK).getMensaje());

            respuesta.setRespuesta(tyCDuoView);

            return respuestaFactory.crearRespuestaOk(tyCDuoView);
        } else {
            return respuestaFactory.crearRespuestaError("", ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
        }
    }
}
