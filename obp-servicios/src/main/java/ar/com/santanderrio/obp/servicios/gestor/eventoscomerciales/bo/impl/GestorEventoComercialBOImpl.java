/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidationEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.GestorEventoComercialBO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.AceptarCompartirDatosDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.GestorEventoComercialDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChanceDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.PeriodoActualDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.PoliticasPrivacidadDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ProcessResultResponseDto;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.ChanceGrillaOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.ChanceOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.FinalizarPromesaEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GestionEventoComercialOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.NotificacionOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertaFinanciacionEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertasComercialesEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.PeriodoActualOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.SolicitarFinanciacionEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.ChanceHistorialView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.FinalizarPromesaPagoInView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.RespuestaEncuestaView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosDeDomicilioDTO;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class GestorEventoComercialBOImpl.
 */
@Component
public class GestorEventoComercialBOImpl implements GestorEventoComercialBO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestorEventoComercialBOImpl.class);

    /** The Constant COD_OK. */
    private static final String COD_OK = "error000";
    /** The Constant COD_OK2. */
    private static final String COD_OK_2 = "ERROR000";

    /** The Constant MENSAJE_ERROR. */
    private static final String MENSAJE_ERROR = "Se produjo un error: ";

    /** The Constant SIN_PUNTOS. */
    private static final String SIN_PUNTOS = "SIN_PUNTOS_SUPERCLUB";

    /** The Constant GUION_STRING. */
    private static final String GUION_STRING = "-";

    /** The Constant DESCRIPCION_PUNTOS_CON_GUION. */
    private static final String DESCRIPCION_PUNTOS_CON_GUION = "SIN_PUNTOS_AD_ERROR";

    /** The Constant GOTO_RECAMBIO_CHIP. */
    private static final String GOTO_RECAMBIO_CHIP = "gotoRecambioChip()";

    /** The Constant GUION. */
    private static final String GUION = "-";
    
    /** The gestor evento comercial DAO. */
    @Autowired
    private GestorEventoComercialDAO gestorEventoComercialDAO;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;
    
	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;
	
    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;
    
    @Autowired
    private AceptarCompartirDatosDAO aceptarCompartirDatosDAO;
    
    
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.
     * GestorEventoComercialBO#obtenerCantidadNotificaciones(ar.com.santanderrio
     * .obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public GestionEventoComercialOutEntity obtenerCantidadNotificaciones(Cliente cliente) throws BusinessException {
        try {
            GestionEventoComercialOutEntity notificaciones = gestorEventoComercialDAO
                    .obtenerCantidadNotificaciones(cliente);
            if (ValidationEntity.validate(notificaciones)) {
                return notificaciones;
            }

            throw new BusinessException("Error al validar cantidad de notificaciones");
        } catch (DAOException e) {
            LOGGER.error("Error al obtener cantidad de notificaciones del gestor de eventos comerciales");
            throw new BusinessException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.
     * GestorEventoComercialBO#obtenerNotificaciones(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente)
     */
    @Override
    public GestionEventoComercialOutEntity obtenerNotificaciones(Cliente cliente) throws BusinessException {
        try {
			GestionEventoComercialOutEntity notificacionesOutEntity = gestorEventoComercialDAO
					.obtenerNotificaciones(cliente);
			procesarNotificaciones(notificacionesOutEntity);
			return notificacionesOutEntity;
        } catch (DAOException e) {
            LOGGER.error("Error al tratar de obtener notificaciones.");
            throw new BusinessException(e);
        }
    }
    
    @Override
    public Boolean borrarNotificacion(Cliente cliente, String id) throws BusinessException {
    	try {
    		GestionEventoComercialOutEntity response = gestorEventoComercialDAO.borrarNotificacion(cliente,
                id);
    		LOGGER.info("Respuesta obtenida: {}.", response);

    		if (StringUtils.equalsIgnoreCase(COD_OK_2, response.getCodigoError())) {
    			return true;
    		} else {
    			return Boolean.FALSE;
    		}
    	} catch (DAOException e) {
        LOGGER.error(MENSAJE_ERROR, e);
        return Boolean.FALSE;
    }
    	/*try {
            GestionEventoComercialOutEntity notificacionesOutEntity = gestorEventoComercialDAO
                    .borrarNotificacion(cliente,id);
            procesarNotificaciones(notificacionesOutEntity);
            return notificacionesOutEntity;
        } catch (DAOException e) {
            LOGGER.error("Error al tratar de obtener notificaciones.");
            throw new BusinessException(e);
        }*/
    }
    

    /**
     * Procesar notificaciones.
     *
     * @param notificacionesOutEntity
     *            the notificaciones out entity
     * @throws BusinessException
     *             the business exception
     */
    private void procesarNotificaciones(GestionEventoComercialOutEntity notificacionesOutEntity)
            throws BusinessException {
        if (!ValidationEntity.validate(notificacionesOutEntity)) {
            throw new BusinessException("Error validando notificaciones.");
        }
        List<NotificacionOutEntity> notificaciones = new ArrayList<NotificacionOutEntity>();
        int cantidadNotificacionesInvalidas = 0;
        for (NotificacionOutEntity notificacion : notificacionesOutEntity.getNotificaciones()) {
            if (ValidationEntity.validate(notificacion)) {
                notificaciones.add(notificacion);
            } else {
                ++cantidadNotificacionesInvalidas;
            }
        }
        LOGGER.info("Cantidad de notificaciones invalidas: {}", cantidadNotificacionesInvalidas);
        notificacionesOutEntity.setNotificaciones(notificaciones);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.
     * GestorEventoComercialBO#actualizarNotificaciones(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente)
     */
    @Override
    public boolean actualizarNotificaciones(Cliente cliente) {

        try {
            GestionEventoComercialOutEntity respuesta = gestorEventoComercialDAO.actualizarNotificaciones(cliente);
            return respuesta.sinErrorDeWS();
        } catch (DAOException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            return false;
        }
    }

    /**
     * Informa si el cliente tiene interes en una notificacion.
     *
     * @param cliente
     *            the cliente
     * @param idNotificacion
     *            the id notificacion
     * @return the boolean
     */
    @Override
    public Boolean informarInteresEnNotificacion(Cliente cliente, String idNotificacion) {
        try {
            GestionEventoComercialOutEntity response = gestorEventoComercialDAO.informarInteresEnNotificacion(cliente,
                    idNotificacion);
            LOGGER.info("Respuesta obtenida: {}.", response);

            if (StringUtils.equalsIgnoreCase(COD_OK, response.getCodigoError())) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (DAOException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            return Boolean.FALSE;
        }
    }

    
    @Override
    public Boolean informarFeedbackOferta(Cliente cliente, String seccion, OfertaComercialDTO ofertaComercialDTO,String action) {
        try {
            return gestorEventoComercialDAO.informarFeedbackOferta(cliente, seccion, ofertaComercialDTO,action);
        } catch (DAOException e) {
            LOGGER.error("Error al informar feedback en oferta.", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.
     * OfertaComercialBO#obtenerOfertasComerciales(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente, java.lang.String)
     */
    @Override
    public EventosComercialesDTO obtenerOfertasComerciales(Cliente cliente, String dispositivo) {
        EventosComercialesDTO ofertasComercialesDTO = new EventosComercialesDTO();
        
        OfertasComercialesEntity ofertasComerciales = null;
        try {
            LOGGER.info("Se realizar la consulta al DAO que obtiene ofertas comerciales.");
            ofertasComerciales = gestorEventoComercialDAO.obtenerOfertasComerciales(cliente, dispositivo);

            LOGGER.info("Respuesta obtenida del WS: {}.", ofertasComerciales);
            ofertasComercialesDTO.setJsessionId(ofertasComerciales.getJsessionId());
            ofertasComercialesDTO.setLineaCrediticia(validarImporteLineaCrediticia(ofertasComerciales.getMontoDisponiblePpp()));
            if (ofertasComerciales.getOfertas() != null) {
                ofertasComercialesDTO.cargarOfertas(ofertasComerciales);
            }
            ofertasComercialesDTO.setIdEncuesta(ofertasComerciales.getIdEncuesta());

        } catch (DAOException e) {
            LOGGER.error("Error del WS.", e);
        } catch (Exception ex) {
            LOGGER.error("Error inesperado del WS.", ex);
        }
        cargarDatosSuperclub(cliente, ofertasComercialesDTO, ofertasComerciales);

        return ofertasComercialesDTO;

    }

    /**
     * metodo para validar que el importe de la linea crediticia sea mayor a 1000 
     * para informar al cliente que puede solicitar un prestamo desde la card en la home.
     */
    private String validarImporteLineaCrediticia(String importe){
    	if(StringUtils.isBlank(importe) || (Double.valueOf(importe) < 1000)){
    		return null;
    	}
    	return "$ " + ISBANStringUtils.formatearConComaYDosDecimales(importe);
    }

    private void cargarDatosSuperclub(Cliente cliente, EventosComercialesDTO ofertasComercialesDTO,
            OfertasComercialesEntity ofertasComerciales) {

        if (ofertasComerciales == null || StringUtils.equals("0", ofertasComerciales.getPuntosSuperclub())
                || StringUtils.isBlank(ofertasComerciales.getPuntosSuperclub())) {

            ofertasComercialesDTO.setMostrarCaja(true);
            if (ofertasComerciales != null && StringUtils.equals("0", ofertasComerciales.getPuntosSuperclub())) {
                ofertasComercialesDTO.setDescripcion(SIN_PUNTOS);
            } else {
                ofertasComercialesDTO.setCantidadPuntos(GUION_STRING);
                ofertasComercialesDTO.setDescripcion(DESCRIPCION_PUNTOS_CON_GUION);
            }
        } else {
            LOGGER.info("Tiene puntos superclub.");
            BigDecimal puntosSuperclubBD = new BigDecimal(ofertasComerciales.getPuntosSuperclub());
            ofertasComercialesDTO.setMostrarCaja(true);
            ofertasComercialesDTO.setCantidadPuntos(formatearPuntos(puntosSuperclubBD));
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.
     * GestorEventoComercialBO#obtenerNotificaciones(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente)
     */
    @Override
    public GestionEventoComercialOutEntity obtenerNotificacionesPerfil(Cliente cliente) throws BusinessException {
        try {
            GestionEventoComercialOutEntity notificacionesOutEntity = gestorEventoComercialDAO
                    .obtenerNotificacionesPerfil(cliente);
            return notificacionesOutEntity;
        } catch (DAOException e) {
            LOGGER.error("Error al tratar de obtener notificaciones perfil.");
            throw new BusinessException(e);
        }
    }

    /**
     * Registrar encuesta.
     *
     * @param cliente
     *            the cliente
     * @param eventosComercialesDTO
     *            the eventos comerciales DTO
     * @return the boolean
     */
    @Override
    public Boolean registrarEncuesta(Cliente cliente, EventosComercialesDTO eventosComercialesDTO) {
        try {
            return gestorEventoComercialDAO.registrarEncuesta(cliente, eventosComercialesDTO);
        } catch (DAOException e) {
            LOGGER.error("Error al registar encuesta.", e);
            return false;
        }
    }

    /**
     * Obtener respuestas encuesta.
     *
     * @param respuestasEncuesta
     *            the respuestas encuesta
     * @return the eventos comerciales DTO
     */
    @Override
    public EventosComercialesDTO obtenerRespuestasEncuesta(List<RespuestaEncuestaView> respuestasEncuesta) {
        EventosComercialesDTO eventosComercialesDTO = new EventosComercialesDTO();
        eventosComercialesDTO.cargarRespuestasEncuesta(respuestasEncuesta);
        return eventosComercialesDTO;
    }

    /**
     * Formatear puntos.
     *
     * @param puntos
     *            the puntos
     * @return the string
     */
    public static String formatearPuntos(BigDecimal puntos) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,###.#######", simbolos);
        return df.format(puntos);
    }

    /**
	 * Informar Gestion AC.
	 *
	 * @param domicilioDeReemplazo
	 *            the domicilio de reemplazo
	 * @return the respuesta
	 */
    public Boolean informarGestionAC(Cliente cliente, DatosDeDomicilioDTO domicilioDeReemplazo) {
    	OfertaComercialDTO ofertaRecambioChip = new OfertaComercialDTO();
    	if (sesionParametros.getOfertaRecambioChip() != null) {
    		ofertaRecambioChip = sesionParametros.getOfertaRecambioChip();
    	} else {
	    	List<OfertaComercialDTO> ofertasComerciales = sesionParametros.getOfertasComerciales().getOfertas();
	    	for (OfertaComercialDTO oferta : ofertasComerciales) {
	    		if (GOTO_RECAMBIO_CHIP.equals(oferta.getGotoLink().getLink())) {
	    			ofertaRecambioChip = oferta;
	    			break;
	    		}
	    	}
    	}
    	
    	try {
            return gestorEventoComercialDAO.informarGestionAC(cliente, domicilioDeReemplazo, ofertaRecambioChip);
        } catch (DAOException e) {
            LOGGER.error("Error al informar gestion AC.", e);
            return false;
        }
    }
    

    @Override
    public Respuesta<ChancesDTO> obtenerPremiaciones(String dispositivo, Cliente cliente, String fechaCorte) {
    	
    	ChancesDTO chancesDTO = new ChancesDTO();
    	ChanceOutEntity chanceOutEntity = new ChanceOutEntity();

    	try {
			chanceOutEntity = gestorEventoComercialDAO.obtenerPremiaciones(dispositivo, cliente);
		} catch (DAOException e) {
			LOGGER.error("Error al obtener premiaciones. BO", e);
			//error1
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_PREMIACIONES,
					CodigoMensajeConstantes.ERROR_SERVICIO_PREMIACIONES);
		}

    	if( !StringUtils.equalsIgnoreCase(COD_OK, chanceOutEntity.getCodigoError()) ) {
    		//error1
    		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_PREMIACIONES,
    				CodigoMensajeConstantes.ERROR_SERVICIO_PREMIACIONES);
    	}
    	
    	//obtengo la fecha actual 
    	Date dateFechaAct = new Date();
    	//datos para mes actual y el anterior 
    	ChanceGrillaOutEntity mesActual = new ChanceGrillaOutEntity();
    	ChanceGrillaOutEntity mesAnterior = new ChanceGrillaOutEntity();
    	ChanceGrillaOutEntity mesMayor = null;
    	//obtener el formato de fecha que necesito
    	SimpleDateFormat formato = new SimpleDateFormat("yyyyMM");
    	String fechaAct = formato.format(dateFechaAct);
    	//seteo los datos necesarios para mi logica
    	mesActual.setPeriodo(fechaAct);
    	Boolean HayMesActual = Boolean.FALSE;
    	Boolean HayMesAnterior = Boolean.FALSE;
    
    	//filtro para obtener los mes
    	for (ChanceGrillaOutEntity chanceMes : chanceOutEntity.getChancesGrillaOutEntity()) {
    		int resul = chanceMes.compareTo(mesActual);
    		//si la fecha (periodo) de "chanceMes" en menor o igual a "mesActual" ==> true
    		if(resul <= 0) {
        		//si la fecha (periodo) de "chanceMes" es igual a "mesActual" ==> true
    			if(resul == 0) {
    				mesActual = chanceMes;
    				if(!HayMesActual) {HayMesActual = Boolean.TRUE;}
    			}
				//si la fecha (periodo) de "chanceMes" es el anterior a "mesActual"  ==> true
    			else if( resul == -1 ){
    				mesAnterior = chanceMes;
    				if(!HayMesAnterior) {HayMesAnterior = Boolean.TRUE;}
    			}
    		}
    		//obtengo el periodo mayor a todos
    		if(mesMayor == null || chanceMes.compareTo(mesMayor)>0) {
    			mesMayor = chanceMes;
    		}
		}
    	//lista vacia
    	if(chanceOutEntity.getChancesGrillaOutEntity() == null || chanceOutEntity.getChancesGrillaOutEntity().isEmpty()) {
    		return respuestaFactory.crearRespuestaWarning("", TipoError.WARNING_SIN_CHANCES,
    				CodigoMensajeConstantes.WARNING_SIN_CHANCES);
    	}
    	
    	Boolean resp = mapearChances(chancesDTO, mesMayor, null, Boolean.TRUE, Boolean.FALSE);
    	if(!resp) {
    		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_PREMIACIONES,
    				CodigoMensajeConstantes.ERROR_SERVICIO_PREMIACIONES);
    	}
    	
    	//no hay mesActual Y no hay mesAnterior (posible caso: hay chances pero no hay chances actual ni anterior segun fecha de hoy )
    	if(!HayMesActual && !HayMesAnterior) {
    		//error2
    		return respuestaFactory.crearRespuestaWarning(chancesDTO,"", TipoError.WARNING_SIN_CHANCES,
    				CodigoMensajeConstantes.WARNING_SIN_CHANCES);
    	}
    	// si el mes actual y el mes anterior tiene como total 0 
    	if( (HayMesActual && mesActual.getTotal() == 0) &&
    			(HayMesAnterior && mesAnterior.getTotal() == 0) ) {
    		//error2
    		return respuestaFactory.crearRespuestaWarning(chancesDTO,"", TipoError.WARNING_SIN_CHANCES,
    				CodigoMensajeConstantes.WARNING_SIN_CHANCES);
    	}
    	
    	//pasar los datos Out Entity a datos DTO. Si resp es FALSE es porque hay una exception en parseo del String a DATE
    	resp = mapearChances(chancesDTO, mesActual, mesAnterior,HayMesActual, HayMesAnterior, fechaCorte);
    	if(!resp) {
    		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_PREMIACIONES,
    				CodigoMensajeConstantes.ERROR_SERVICIO_PREMIACIONES);
    	}
    	
    	return respuestaFactory.crearRespuestaOk(chancesDTO);
    }
    
    private Boolean mapearChances(ChancesDTO chancesDTO, ChanceGrillaOutEntity mesActual,
    		ChanceGrillaOutEntity mesAnterior, Boolean HayMesActual, Boolean HayMesAnterior, String fechaCorte) {
		
		// Logica para saber si va el total del mes actual o mes anterior
		String formato = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
    	Date diaActual = new Date();
    	String diaActualString = sdf.format(diaActual);
    	Date fechaDeCorte;
    	
    	String total;
    	
    	if (StringUtils.isBlank(fechaCorte)) {
    		total = GUION;
    		return Boolean.FALSE;
    	}
    	
    	Boolean resp = mapearChances(chancesDTO, mesActual, mesAnterior, HayMesActual, HayMesAnterior);
    	if(!resp) {
    		return resp;
    	}
    	
		try {
			fechaDeCorte = sdf.parse(fechaCorte);
			diaActual = sdf.parse(diaActualString);
		} catch (ParseException e) {
			LOGGER.error("Error al formatear fecha en Gestor BO.", e);
			return Boolean.FALSE;
		}
		
		//after >
		//before <
    	if (diaActual.after(fechaDeCorte)) {
    		total = HayMesActual ? String.valueOf(mesActual.getTotal()) : "0";
    		chancesDTO.setTotal(total);
    	} else {
    		total = HayMesAnterior ? String.valueOf(mesAnterior.getTotal()) : "0";
    		chancesDTO.setTotal(total);
    	}
    	
    	return Boolean.TRUE;
    }
    
    public static Boolean mapearChances(ChancesDTO chancesDTO, ChanceGrillaOutEntity mesActual,
    		ChanceGrillaOutEntity mesAnterior, Boolean HayMesActual, Boolean HayMesAnterior) {
    	if(chancesDTO != null) {
    		chancesDTO.setListaChances(new ArrayList<ChanceDTO>());
    	}
    	Date fechaActual = new Date();
    	Date fechaAnterior = new Date();
    	String nombreMesAnte = null;
    	String nombreMesAct = null;
    	try {
    		
    		if(HayMesActual) {
    			fechaActual = new SimpleDateFormat("yyyyMM").parse(mesActual.getPeriodo());//obtengo el objeto date en base a un string
    			nombreMesAct = new SimpleDateFormat("MMMM",new Locale("es","AR")).format(fechaActual);//obtengo el nombre del mes
    	    	nombreMesAct = nombreMesAct.substring(0, 1).toUpperCase() + nombreMesAct.substring(1).toLowerCase();//Formato ejemplo. "mayo" ==> "Mayo"
    	    	nombreMesAct = ChanceDTO.CHANCES + " " + nombreMesAct;
    		}
    		
    		if(HayMesAnterior) {
    			fechaAnterior = new SimpleDateFormat("yyyyMM").parse(mesAnterior.getPeriodo());
    	    	nombreMesAnte = new SimpleDateFormat("MMMM",new Locale("es","AR")).format(fechaAnterior);
    	    	nombreMesAnte = nombreMesAnte.substring(0, 1).toUpperCase() + nombreMesAnte.substring(1).toLowerCase();
    	    	nombreMesAnte = ChanceDTO.CHANCES + " " + nombreMesAnte;
    		}
			

		} catch (ParseException e) {
			LOGGER.error("Error al formatear fecha en Gestor BO.", e);
			return Boolean.FALSE;
		}
    	

    	chancesDTO.setHeader(new ChanceDTO(ChanceDTO.ACCION, nombreMesAnte, nombreMesAct));
    	
    	String valorActual = HayMesActual ? String.valueOf(mesActual.getChances_acreditacion()):"";
		String valorAnterior = HayMesAnterior ? String.valueOf(mesAnterior.getChances_acreditacion()):"";
		chancesDTO.getListaChances().add(new ChanceDTO(ChanceDTO.COBRO_POR_SUELDO, valorAnterior, valorActual));
    	
    	valorActual = HayMesActual ? String.valueOf(mesActual.getChances_td()):"";
		valorAnterior = HayMesAnterior ? String.valueOf(mesAnterior.getChances_td()):"";
		chancesDTO.getListaChances().add(new ChanceDTO(ChanceDTO.COMPRA_SANT_DEBITO, valorAnterior, valorActual));
    	
    	valorActual = HayMesActual ? String.valueOf(mesActual.getChances_visa()):"";
		valorAnterior = HayMesAnterior ? String.valueOf(mesAnterior.getChances_visa()):"";
		chancesDTO.getListaChances().add(new ChanceDTO(ChanceDTO.COMPRA_SANT_VISA, valorAnterior, valorActual));
    	
    	valorActual = HayMesActual ? String.valueOf(mesActual.getChances_amex()):"";
		valorAnterior = HayMesAnterior ? String.valueOf(mesAnterior.getChances_amex()):"";
		chancesDTO.getListaChances().add(new ChanceDTO(ChanceDTO.COMPRA_SANT_AMERICAN_EXPRESS, valorAnterior, valorActual));
    	
        valorActual = HayMesActual ? String.valueOf(mesActual.getChances_master()):"";
        valorAnterior = HayMesAnterior ? String.valueOf(mesAnterior.getChances_master()):"";
        chancesDTO.getListaChances().add(new ChanceDTO(ChanceDTO.COMPRA_SANT_MASTER, valorAnterior, valorActual));

		valorActual = HayMesActual ? String.valueOf(mesActual.getTotal()):"";
		valorAnterior = HayMesAnterior ? String.valueOf(mesAnterior.getTotal()):"";
		chancesDTO.getListaChances().add(new ChanceDTO(ChanceDTO.TOTAL, valorAnterior, valorActual));
		
		valorActual = HayMesActual ? String.valueOf(mesActual.getTotal()):"";
		chancesDTO.setTotal(valorActual);
    	return true;
    }
    
    /**
   	 * Premiaciones periodo actual.
   	 *
   	 * @param cliente 
   	 * 		the cliente
   	 * @return Respuesta Chances DTO
   	 */
    @Override
    public Respuesta<PeriodoActualDTO> premiacionesPeriodoActual(Cliente cliente) {
    	PeriodoActualOutEntity periodoActual;
    	try {
			periodoActual = gestorEventoComercialDAO.premiacionesPeriodoActual(cliente);
		} catch (DAOException e) {
			LOGGER.error("Error al obtener premiaciones periodo actual.", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_PREMIACIONES,
    				CodigoMensajeConstantes.ERROR_SERVICIO_PREMIACIONES);
		}
    	
    	if(!StringUtils.equalsIgnoreCase(COD_OK, periodoActual.getCodigoError())) {
    		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_PREMIACIONES,
    				CodigoMensajeConstantes.ERROR_SERVICIO_PREMIACIONES);
    	}
    	
    	PeriodoActualDTO periodoActualDTO = new PeriodoActualDTO(periodoActual);
    	return respuestaFactory.crearRespuestaOk(periodoActualDTO);
    }

	@Override
	public Respuesta<ChanceHistorialView> obtenerCotasMaximaYMinina(String dispositivo, Cliente cliente) {
		ChanceOutEntity chanceOutEntity = new ChanceOutEntity();
		try {
			chanceOutEntity = gestorEventoComercialDAO.obtenerPremiaciones(dispositivo, cliente);
		 } catch (BeansException e) {
			 LOGGER.error("Error al obtener chances mes. BO", e);
			 return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_PREMIACIONES,
	    				CodigoMensajeConstantes.ERROR_SERVICIO_PREMIACIONES);
		} catch (DAOException e) {
			LOGGER.error("Error al obtener chances mes. BO", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_PREMIACIONES,
    				CodigoMensajeConstantes.ERROR_SERVICIO_PREMIACIONES);
		}
		
		List<ChanceGrillaOutEntity> listaGrilla = chanceOutEntity.getChancesGrillaOutEntity();
		if(listaGrilla == null || listaGrilla.isEmpty()) {
			return respuestaFactory.crearRespuestaWarning("", TipoError.WARNING_SIN_CHANCES,
    				CodigoMensajeConstantes.WARNING_SIN_CHANCES);
		}
		//ordeno las chances de mayor periodo a menor periodo
		Collections.sort(listaGrilla,
					new Comparator<ChanceGrillaOutEntity>() {
					@Override
					public int compare(ChanceGrillaOutEntity grillaUno, ChanceGrillaOutEntity grillaDos) {
						return grillaDos.compareTo(grillaUno);
					}
			
		} );
		
		Integer cantElem = listaGrilla.size();
		ChanceGrillaOutEntity chanceCotaMin = listaGrilla.get(cantElem-1);//menor periodo
		ChanceGrillaOutEntity chanceCotaMax = listaGrilla.get(0);//mayor periodo
		ChanceHistorialView chanceHistorial = new  ChanceHistorialView(chanceCotaMin.getPeriodo(),chanceCotaMax.getPeriodo());
		
		return respuestaFactory.crearRespuestaOk(chanceHistorial);
	}

	
	@Override
	public Respuesta<FinalizarPromesaEntity> finalizarPromesaPago(Cliente cliente, FinalizarPromesaPagoInView inView) {
		boolean permiteReintentar;
		FinalizarPromesaEntity finalizarPromesaEntity = null;

		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.error("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		try {
			finalizarPromesaEntity = gestorEventoComercialDAO.finalizarPromesaPago(cliente, inView);
			return respuestaFactory.crearRespuestaOk(finalizarPromesaEntity);
		} catch (TimeOutException e) {
			LOGGER.error("Error en BO Time out. ", e);
			String mensajeError = armarMensajeErrorFinalizarPromesa();
			return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeError,
					TipoError.ERROR_FINALIZAR_PROMESA_PAGO_SIN_REINTENTO.toString());
		} catch (DAOException e) {
			return crearRespuestaReintentoFinalizarPromesaPago(permiteReintentar, e);
		}

	}
	
	
	
	Respuesta<FinalizarPromesaEntity> crearRespuestaReintentoFinalizarPromesaPago(boolean permiteReintentar,
			Exception e) {
		LOGGER.error("Error en GestorEventoComercialBO metodo finalizarPromesaPago. ", e);
		String mensajeError = armarMensajeErrorFinalizarPromesa();
		if (permiteReintentar) {
			return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeError,
					TipoError.ERROR_FINALIZAR_PROMESA_PAGO_CON_REINTENTO.toString());
		}
		return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeError,
				TipoError.ERROR_FINALIZAR_PROMESA_PAGO_SIN_REINTENTO.toString());
	}

	private String armarMensajeErrorFinalizarPromesa() {
		return  mensajeBO.obtenerMensajePorCodigo("1919").getMensaje();
	
	}

	/**
	 * Obtener ofertas financiacion.
	 *
	 * @param cliente the cliente
	 * @return the respuesta
	 */
	@Override
	public Respuesta<List<OfertaFinanciacionEntity>> obtenerOfertasFinanciacion(Cliente cliente) {
	    Respuesta<List<OfertaFinanciacionEntity>> respuesta;
	    try {
	        List<OfertaFinanciacionEntity> ofertas = gestorEventoComercialDAO.obtenerOfertasFinanciacion(cliente.getNup());
	        respuesta = respuestaFactory.crearRespuestaOk(ofertas);

	        //si NO tiene ofertas validas
            if (ofertas.size() == 0) {
                ProcessResultResponseDto processResultResponse = gestorEventoComercialDAO.getProcessesCreatedToday(cliente.getNup());
                if (processResultResponse.getResults() != null && processResultResponse.getResults().size() > 0) {
                    LOGGER.info("NUP {} ya financiado.", cliente.getNup());
                    respuesta = respuestaFactory.crearRespuestaError("", TipoError.YA_FINANCIASTE, CodigoMensajeConstantes.ERROR_FINANCIADO);
                } else {
                    LOGGER.info("El NUP {} no tiene ofertas validas.", cliente.getNup());
                    respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_CARGA_FINANCIACIONES, CodigoMensajeConstantes.ERROR_CARGA_OFERTAS);
                }
            }

        } catch (DAOException e) {
            LOGGER.error("Error al obtener financiaciones", e);
            respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
        }

        return respuesta;
	}

	/**
	 * Solicitar financiacion.
	 *
	 * @param cliente the cliente
	 * @param solicitarFinanciacionEntity the solicitar financiacion entity
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Boolean> solicitarFinanciacion(Cliente cliente, SolicitarFinanciacionEntity solicitarFinanciacionEntity) {
		Respuesta<Boolean> respuesta; 
		try {
			Boolean solicitudOk = gestorEventoComercialDAO.solicitarFinanciacion(cliente, solicitarFinanciacionEntity);
			respuesta = respuestaFactory.crearRespuestaOk(solicitudOk);
		} catch (DAOException e) {
			LOGGER.error("Error al solicitar financiacion", e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		} catch (IOException e) {
			LOGGER.error("Error al solicitar financiacion", e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}
		return respuesta;
	}

	@Override
	public String registrarRespuestaPoliticasPrivacidad(PoliticasPrivacidadDTO politicasPrivacidadDTO) throws DAOException {
		return aceptarCompartirDatosDAO.grabarDecisionCliente(politicasPrivacidadDTO);
	}

}	
