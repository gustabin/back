/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.manager.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.bo.TrackingTarjetasBO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dto.TrackingPiezaDTO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.entities.EstadoTrackingEnum;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.manager.TrackingTarjetasManager;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.utils.TrackingTarjetasUtils;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.view.DatosTrackingTarjetaView;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.view.EstadoTrackingView;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.view.ProductoTrackingView;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.view.TrackingTarjetasView;

/**
 * TrackingTarjetasManagerImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class TrackingTarjetasManagerImpl implements TrackingTarjetasManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingTarjetasManagerImpl.class);

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    
    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;
    
    /** TrackingTarjetas BO. */
    @Autowired
    private TrackingTarjetasBO trackingTarjetasBO;
    
    /** ConsultaSucursales BO. */
    @Autowired
    private ConsultarSucursalesBO consultarSucursalesBO;
    
    
    /** CODIGO_TITULARIDAD_TI. */
    public static final String CODIGO_TITULARIDAD_TI          = "TI";
    
    /** CODIGO_TITULARIDAD_CT. */
    public static final String CODIGO_TITULARIDAD_CT          = "CT";
    
    /** TIPO_CUENTA_DEBITO. */
    public static final String TIPO_CUENTA_DEBITO             = "05";
    
    /** The Constant TIME_FORMAT_PATTERN. */
    public static final String TIME_FORMAT_PATTERN            = "HH:mm";
 
    /** The Constant ESTADO_12. */
    private static final String ESTADO_12 = "12";

    /** The Constant ESTADO_19. */
    private static final String ESTADO_19 = "19";
    
    /** The Constant estadosEnSucursal. */
    private static final String[] estadosEnSucursal  =  { "16", "17", "24", "32", "SU","22"};
    
    /** The Constant estadosEntregados. */
    private static final String[] estadosEntregados  =  { "23", "CL" };
    
    /** The Constant estadosConUrlAndreani. */
    private static final String[] estadosConUrlAndreani = {"14","15","18","21"};
    
    /** The Constant ESTADO_DESTRUIDO. */
    private static final String ESTADO_DESTRUIDO  = "1219";
    
    /** The hora desde . */
    @Value("${TRACKINGTARJETAS.HORA.DESDE}")
    private String horaDesde;

    /** The hora hasta . */
    @Value("${TRACKINGTARJETAS.HORA.HASTA}")
    private String horaHasta;
    
    /**
	 * Obtener estados de tarjetas, tracking.
	 *
	 * @return the respuesta
	 */
    @Override
    public Respuesta<TrackingTarjetasView> obtenerTrackingTarjetas(){
        
        if(!cumpleHorario()) {
            LOGGER.debug("Consulta de tracking fuera de horario permitido");
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_FUERA_HORARIO_TRACKING, CodigoMensajeConstantes.ERROR_FUERA_HORARIO_TRACKING);
        }
        
        LOGGER.info("Obteniendo tracking de tarjetas"); 
        Cliente cliente = sesionCliente.getCliente();
        Respuesta<List<TrackingPiezaDTO>> respuestaPiezasDTO =  trackingTarjetasBO.obtenerTrackingTarjetas(cliente);
        if(EstadoRespuesta.OK.equals(respuestaPiezasDTO.getEstadoRespuesta())) {
            try {
                TrackingTarjetasView trackingView = generarTrackingTarjetasView(respuestaPiezasDTO.getRespuesta());
                estadisticaManager.add(EstadisticasConstants.TRACKING_TARJETAS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                return respuestaFactory.crearRespuestaOk(trackingView); 
            }catch(ParseException e) {
                LOGGER.error("Error parseando datos de tracking de tarjetas");
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_TRACKING_TARJETAS, CodigoMensajeConstantes.ERROR_GENERICO_TRACKING_TARJETAS);
            }
        }
        if(EstadoRespuesta.WARNING.equals(respuestaPiezasDTO.getEstadoRespuesta())) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.WARNING_TRACKING_TARJETAS_SIN_DATOS, CodigoMensajeConstantes.WARNING_TRACKING_TARJETAS_SIN_DATOS);
        }
        estadisticaManager.add(EstadisticasConstants.TRACKING_TARJETAS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        LOGGER.info("ExtraccionYComprasExteriorManagerImpl finalizando consulta de cuentas Error");
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_TRACKING_TARJETAS, CodigoMensajeConstantes.ERROR_GENERICO_TRACKING_TARJETAS); 
        
    }

    /**
	 * Valida que la consulta se realice dentro de un horario.
	 *
	 * @return true, if successful
	 */
    private boolean cumpleHorario() {
        LOGGER.debug("Verificando si la consulta de tracking es dentro del horario permitido...");
        DateTimeFormatter df = DateTimeFormat.forPattern(TIME_FORMAT_PATTERN);
        DateTime horarioBancarioFinal = df.parseLocalTime(horaHasta).toDateTimeToday();
        DateTime horarioBancarioInicio = df.parseLocalTime(horaDesde).toDateTimeToday().plusDays(1);
        DateTime horaActual = new DateTime();
        Interval intervaloFueraHorario = new Interval(horarioBancarioFinal, horarioBancarioInicio);
        return !(intervaloFueraHorario.contains(horaActual)); 
    }

    /**
	 * Genera la vista de todas las piezas de tracking.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the tracking tarjetas view
	 * @throws ParseException
	 *             the parse exception
	 */
    private TrackingTarjetasView generarTrackingTarjetasView(List<TrackingPiezaDTO> respuesta) throws ParseException {
        TrackingTarjetasView trackingView = new TrackingTarjetasView();
        for (TrackingPiezaDTO piezaDTO : respuesta) {
                DatosTrackingTarjetaView tarjetaView = generarDatosTrackingView(piezaDTO);
                trackingView.addDatosTarjetaView(tarjetaView);
        }        
        return trackingView;
    }


    /**
	 * Genera los datos para la vista de una pieza de tracking.
	 *
	 * @param piezaDTO
	 *            the pieza DTO
	 * @return the datos tracking tarjeta view
	 * @throws ParseException
	 *             the parse exception
	 */
    private DatosTrackingTarjetaView generarDatosTrackingView(TrackingPiezaDTO piezaDTO) throws ParseException {
        DatosTrackingTarjetaView tarjetaView = new DatosTrackingTarjetaView();
        LOGGER.debug("Generando datos view de las piezas de tracking...");
        EstadoTrackingEnum estadoEnum = obtenerEstadoTracking(piezaDTO);
        tarjetaView.setMostrarLinkReimpresion(mostrarLinkReimpresionHabilitado(piezaDTO));
        tarjetaView.setNumeroEstado(estadoEnum.getIdEstadoView());
        tarjetaView.setUrlAndreani(obtenerUrlAndreani(estadoEnum.getIdEstadoWS()));
        tarjetaView.setDescripcionEstado(obtenerDescripcionEstado(estadoEnum.getIdEstadoWS(), estadoEnum.getIdEstadoView()));
        tarjetaView.setDetalleFecha(TrackingTarjetasUtils.obtenerFechaTracking(piezaDTO.getFecha()));
        tarjetaView.setCodigoProducto(piezaDTO.getTipoCuentaEnum().getCodigo().toString());
        String nroSucursal = obtenerNroSucursal(piezaDTO);
        String direccionSucursal = obtenerDireccionSucursal(piezaDTO);
        String mensaje ="";
        if(EstadoTrackingEnum.ANDREANI35.getIdEstadoWS().equals(piezaDTO.getIdEstado())){
            mensaje =piezaDTO.getEstado();
        }else if(EstadoTrackingEnum.ASGMSU.getIdEstadoWS().equals(piezaDTO.getIdEstado())) {
            String fechaRetiroHasta= TrackingTarjetasUtils.obtenerFechaRetorno(piezaDTO.getFecha());
            mensaje = MessageFormat.format(mensajeManager.obtenerMensajePorCodigo(estadoEnum.getCodigoMensajeDB()).getMensaje(), fechaRetiroHasta, nroSucursal, direccionSucursal);        
        }else {
            mensaje = MessageFormat.format(mensajeManager.obtenerMensajePorCodigo(estadoEnum.getCodigoMensajeDB()).getMensaje(), nroSucursal, direccionSucursal);
            if(esEstadoConUrlAndreani(estadoEnum.getIdEstadoWS())) {
               mensaje = mensaje  + mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRACKING_TARJETAS_SEGUNDO_MSJ).getMensaje();  
            }
        }
        tarjetaView.setMensajeEstado(mensaje);
        tarjetaView.setProducto(ProductoTrackingView.obtenerTituloPorTipoCuenta(piezaDTO.getTipoCuentaEnum().getCodigo())+ " " + piezaDTO.getNumeroTarjetaMascara());
        tarjetaView.setSubtituloProducto(ProductoTrackingView.obtenerDescPorTipoCuenta(piezaDTO.getTipoCuentaEnum().getCodigo()));
        return tarjetaView;
    }
    
    /**
	 * Url de andreani.
	 *
	 * @param idEstadoWS
	 *            the id estado WS
	 * @return the string
	 */
    private String obtenerUrlAndreani(String idEstadoWS) {
        if (esEstadoConUrlAndreani(idEstadoWS)) {
             return mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRACKING_TARJETAS_URL).getMensaje();
        }
        return null;
    }

    /**
	 * esEstadoConUrlAndreani.
	 *
	 * @param idEstadoWS
	 *            the id estado WS
	 * @return true, if successful
	 */
    private boolean esEstadoConUrlAndreani(String idEstadoWS) {
        for (String est : estadosConUrlAndreani) {
            if (est.equals(idEstadoWS)) {
                return true;
            }
        }
        return false;
    }
    
    /**
	 * obtenerDescripcionEstado.
	 *
	 * @param idEstadoWS
	 *            the id estado WS
	 * @param idEstadoView
	 *            the id estado view
	 * @return the string
	 */
    private String obtenerDescripcionEstado(String idEstadoWS, String idEstadoView) {
        String descripcionEstado = "";
        if(esEstadoEnSucursal(idEstadoWS)){
            descripcionEstado = EstadoTrackingView.obtenerDescripcionPorId("4");
        }else if(esEstadoEntregado(idEstadoWS)) {
            descripcionEstado = EstadoTrackingView.obtenerDescripcionPorId("3");
        }else if(ESTADO_DESTRUIDO.equals(idEstadoWS)){
            descripcionEstado = EstadoTrackingView.obtenerDescripcionPorId("5");
        }else {
            descripcionEstado = EstadoTrackingView.obtenerDescripcionPorId(idEstadoView);
        }
        return descripcionEstado;
        
    }

    /**
	 * esEstadoEntregado.
	 *
	 * @param idEstadoView
	 *            the id estado view
	 * @return true, if successful
	 */
    private boolean esEstadoEntregado(String idEstadoView) {
        for (String est : estadosEntregados) {
            if (est.equals(idEstadoView)) {
                return true;
            }
        }
        return false;
    }

    /**
	 * esEstadoEnSucursal.
	 *
	 * @param idEstadoView
	 *            the id estado view
	 * @return true, if successful
	 */
    private boolean esEstadoEnSucursal(String idEstadoView) {
        for (String est : estadosEnSucursal) {
            if (est.equals(idEstadoView)) {
                return true;
            }
        }
        return false;
    }

    /**
	 * Determina si se muestra el link hacia reimpresion de tarjetas.
	 *
	 * @param piezaDTO
	 *            the pieza DTO
	 * @return true, if successful
	 */
    private boolean mostrarLinkReimpresionHabilitado(TrackingPiezaDTO piezaDTO) {
        Integer codigoTipoCuenta = piezaDTO.getTipoCuentaEnum().getCodigo();
        return permiteLinkReimpresion(piezaDTO.getIdEstado()) &&
                !TipoCuenta.VISA_RECARGABLE.getCodigo().equals(codigoTipoCuenta) && 
                !TipoCuenta.TARJETA_MONEDERO.getCodigo().equals(codigoTipoCuenta);
        
    }

    /**
	 * obtenerDireccionSucursal.
	 *
	 * @param piezaDTO
	 *            the pieza DTO
	 * @return the string
	 */
    private String obtenerDireccionSucursal(TrackingPiezaDTO piezaDTO) {
        String direccionSucursal = "";
        Respuesta<Sucursal> respuestaSuc = consultarSucursalesBO.consultarSucursalPorId(ISBANStringUtils.fillNum(piezaDTO.getSucursal(), 4));
        if(EstadoRespuesta.OK.equals(respuestaSuc.getEstadoRespuesta()) && respuestaSuc.getRespuesta() != null){
            direccionSucursal = respuestaSuc.getRespuesta().getDireccion();
        }
        return direccionSucursal;
    }

    /**
	 * obtenerNroSucursal.
	 *
	 * @param piezaDTO
	 *            the pieza DTO
	 * @return the string
	 */
    private String obtenerNroSucursal(TrackingPiezaDTO piezaDTO) {
        String nroSucursal = "";
        if(EstadoTrackingEnum.ANDREANI17.getIdEstadoWS().equals(piezaDTO.getIdEstado()) &&
                piezaDTO.getEventoSucursal() != null && !"".equals(piezaDTO.getEventoSucursal())){
            nroSucursal = piezaDTO.getEventoSucursal();
        }else {
            nroSucursal = ISBANStringUtils.formatearSucursal(piezaDTO.getSucursal());
        }
        return nroSucursal;
    }

    /**
	 * obtenerEstadoTracking.
	 *
	 * @param piezaDTO
	 *            the pieza DTO
	 * @return the estado tracking enum
	 */
    private EstadoTrackingEnum obtenerEstadoTracking(TrackingPiezaDTO piezaDTO) {
        EstadoTrackingEnum estadoEnum = null;
        if(piezaDTO.getIdEstado().equals(ESTADO_12) || piezaDTO.getIdEstado().equals(ESTADO_19)) {
            if(!permiteLinkReimpresion(piezaDTO.getIdEstado())) {
                estadoEnum =  EstadoTrackingEnum.ANDREANI1219_SIN_REIMPRESION; 
            }else {

                Integer codigoTipoCuenta = piezaDTO.getTipoCuentaEnum().getCodigo();
                if(codigoTipoCuenta.equals(TipoCuenta.VISA_RECARGABLE.getCodigo())) {
                    estadoEnum =  EstadoTrackingEnum.ANDREANI1219_REIMPRESION_RECARGABLE;
                }else if(codigoTipoCuenta.equals(TipoCuenta.TARJETA_MONEDERO.getCodigo())) {
                    estadoEnum =  EstadoTrackingEnum.ANDREANI1219_REIMPRESION_MONEDERO;
                }else {
                    estadoEnum = EstadoTrackingEnum.ANDREANI1219_REIMPRESION;
                }
            }
        }else {
           estadoEnum =  EstadoTrackingEnum.obtenerPorEstado(piezaDTO.getIdEstado()); 
        }
        return estadoEnum;
    }

    /**
	 * Para habilitar el link re reimpresion el cliente tiene que tener
	 * validacion por coordenadas por OPT y ademas ser titular o cootitular de
	 * una tarjeta de debito.
	 *
	 * @param idEstado
	 *            the id estado
	 * @return true or false
	 */
    private boolean permiteLinkReimpresion(String idEstado) {
        if("12".equals(idEstado) || "19".equals(idEstado)) {
            Cliente cliente = sesionCliente.getCliente();
                if(cliente.tieneSoftToken() || cliente.tieneTarjetaCoordenadas()) {
                for(Cuenta cuenta: cliente.getCuentas()) {
                    if ( (CODIGO_TITULARIDAD_TI.equals(cuenta.getCodigoTitularidad()) || CODIGO_TITULARIDAD_CT.equals(cuenta.getCodigoTitularidad())) 
                        && TIPO_CUENTA_DEBITO.equals(cuenta.getTipoCuenta())){
                       return true; 
                    }
                }
            }
        }
        return false;
    }

}
