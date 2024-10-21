package ar.com.santanderrio.obp.servicios.turnosweb.manager.impl;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.OperadorEjecutivo;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.view.ComboView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.OperadorEjecutivoView;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.segmento.bo.SegmentoClienteBO;
import ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.CitaDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.CitaOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.ComprobanteTurnoInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.ComprobanteTurnoRemotoDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.MotivoTurnoDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.MotivosInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.SucursalesDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.SucursalesOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.ComprobanteTurnoInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.manager.TurnosWebManager;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.BajaTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.CitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.CitaView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoRemotoView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.DatosMYAView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.SucursalesOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.SucursalesView;

/**
 * The Class TurnosWebManagerImpl.
 *
 * @author IT Resources
 */
@Component
public class TurnosWebManagerImpl implements TurnosWebManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TurnosWebManagerImpl.class);

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The turnos web bo. */
	@Autowired
	private TurnosWebBO turnosWebBO;

	/** The legal bo. */
	@Autowired
	private LegalBO legalBO;

	/** The mensaje bo. */
	@Autowired
	private MensajeBO mensajeBO;

    /** The property map. */
	@Autowired
	private PropertyMap propertyMap;

	/** The Constant CAJA. */
	private static final String CAJA = "C";

	/** The Constant PLATAFORMA. */
	private static final String PLATAFORMA = "P";

	/** The Constant SOLICITAR_TURNO_EJECUTIVO. */
	private static final String SOLICITAR_TURNO_EJECUTIVO = "solicitarTurnoEjecutivo";

	/** The Constant SOLICITAR_TURNO_CAJA. */
	private static final String SOLICITAR_TURNO_CAJA = "solicitarTurnoCaja";
	
	/*** The Constant SOLICITAR_TURNO_EJECTUVO_AUTOGESTION*/
	private static final String SOLICITAR_TURNO_EJECUTIVO_AUTOGESTION = "solicitarTurnoEjecutivoAutogestion";

	/*** The Constant SOLICITAR_INFO_SANTANDER_EXPRESS*/
	private static final String INFO_SANTANDER_EXPRESS="infoSantanderExpress";

	/** The Constant VER_TURNO_EJECUTIVO. */
	private static final String VER_TURNO_EJECUTIVO = "verTurnoEjecutivo";

	/** The Constant VER_TURNO_CAJA. */
	private static final String VER_TURNO_CAJA = "verTurnoCaja";
	
	/** The Constant VER_TURNO_EJECUTIVO_AUTOGESTION. */
	private static final String VER_TURNO_EJECUTIVO_AUTOGESTION = "verTurnoEjecutivoAutogestion";

	/** The Constant ALTA. */
	private static final String ALTA = "A";
	
	/** The Constant MODIFICACION. */
	private static final String MODIFICACION = "M";

	/** The Constant DIAS_PERMITIDOS_PREFIX. */
	private static final String DIAS_PERMITIDOS_PREFIX = "TURNOSWEB.DNIPERMITIDOS.";

	/** The Constant LONGITUD_SUCURSAL. */
	private static final int LONGITUD_SUCURSAL = 3;

	/** The segmento cliente BO. */
	@Autowired
	private SegmentoClienteBO segmentoClienteBO;

	/** The turnos web caja habilitado general. */
	@Value("${TURNOSWEB.CAJA.HABILITADO.GENERAL:0}")
	private String turnosWebCajaHabilitadoGeneral;

	/** The turnos web ejecutivo habilitado general. */
	@Value("${TURNOSWEB.EJECUTIVO.HABILITADO.GENERAL:0}")
	private String turnosWebEjecutivoHabilitadoGeneral;

	/** The verificar dias permitidos. */
	@Value("${TURNOSWEB.VERIFICAR.DNIPERMITIDOS:0}")
	private String verificarDiasPermitidos;
		
	/** The urlCardCorresponsalia for autogestion*/
	@Value("${AUTOGESTION.URLCARDCORRESPONSALIA}")
	protected String urlCardCorresponsalia;
	
	/** The Constant TITULO_COMPROBANTE_PLATAFORMA. */
	private static final String TITULO_COMPROBANTE_PLATAFORMA = "Comprobante de Solicitud de Turno con Ejecutivo";
	
	/** The Constant TITULO_COMPROBANTE_AUTOGESTION. */
	private static final String TITULO_COMPROBANTE_AUTOGESTION = "Comprobante Turno";

	/** The Constant TITULO_COMPROBANTE_CAJA. */
	private static final String TITULO_COMPROBANTE_CAJA = "Comprobante de Solicitud de Turno en Caja";
	
	/** The Constant LOGO_DEFAULT_CAJA. */
	private static final String LOGO_DEFAULT_CAJA = "Solicitud de turno en caja";

	/** The Constant LOGO_DEFAULT_PLATAFORMA. */
	private static final String LOGO_DEFAULT_PLATAFORMA = "Solicitud de turno con ejecutivo";
	
	/** The Constant LOGO_DEFAULT_AUTOGESTION. */
	private static final String LOGO_DEFAULT_AUTOGESTION = "Turno online";
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.manager.TurnosWebManager#consultaCita(Boolean)
	 */
	@Override
	public Respuesta<CitaOutView> consultaCita(Boolean primerLlamado) {
		Respuesta<CitaOutView> respuesta;
		LOGGER.info("Turnos Web : Consulta Citas");
		boolean grabarEstadistica = primerLlamado != null && primerLlamado.booleanValue();
		Respuesta<CitaOutDTO> respuestaBO = turnosWebBO.consultarCitas(sesionCliente.getCliente().getNup());
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			if (grabarEstadistica) {
				estadisticaManager.add(EstadisticasConstants.ACCESO_CONSULTA_SOLICITUD_TURNOS, 
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else {
			if (grabarEstadistica) {
				estadisticaManager.add(EstadisticasConstants.ACCESO_CONSULTA_SOLICITUD_TURNOS, 
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
			LOGGER.info("{} Citas encontradas ", respuestaBO.getRespuesta().getCitaDTOList().size());
			CitaOutView citaOutView = citaOutDTOToCitaOutView(respuestaBO.getRespuesta().getCitaDTOList());
			respuesta =  respuestaFactory.crearRespuestaOk(CitaOutView.class,citaOutView);	
		}

		return respuesta;
	}

	/**
	 * Mapea la salida del BO del consulta cita al objeto View de salida.
	 * Tambien setea las acciones del action sheet para la vista
	 *
	 * @param citaDTOList the cita DTO list
	 * @return the cita out view
	 */	
	private CitaOutView citaOutDTOToCitaOutView(List<CitaDTO> citaDTOList) {						
		CitaOutView citaOutView = new CitaOutView();
		CitaView citaEjecutivo = new CitaView();
		CitaView citaCaja = new CitaView();
		CitaView actionSheetCaja = new CitaView();
		CitaView actionSheetEjecutivo = new CitaView();		
		citaCaja.setTipo(SOLICITAR_TURNO_CAJA);
		actionSheetCaja.setTipo(SOLICITAR_TURNO_CAJA);	 
		//Set info Santander Express
		CitaView citaInfoSantanderExpress = new CitaView();			
		CitaView actionSheetInfoSantanderExpress=new CitaView();
		citaInfoSantanderExpress.setTipo(INFO_SANTANDER_EXPRESS);
		actionSheetInfoSantanderExpress.setTipo(INFO_SANTANDER_EXPRESS);
		//Set info Santander Express
		citaOutView.getActionSheet().add(actionSheetInfoSantanderExpress);
		citaOutView.getGestiones().add(citaInfoSantanderExpress);			
		//Si el NUP del cliente se encuentra en la nomina de los que son permitidos
		actionSheetEjecutivo.setTipo(SOLICITAR_TURNO_EJECUTIVO_AUTOGESTION);
		citaEjecutivo.setTipo(SOLICITAR_TURNO_EJECUTIVO_AUTOGESTION);				
		for(CitaDTO c : citaDTOList){
			if(c.getSector().equals(CAJA)) {
				citaCaja.setDireccion(c.getDireccion());
				citaCaja.setDia(c.getDia());
				citaCaja.setDiaNumero(c.getDiaNumero());
				citaCaja.setMes(c.getMes());
				citaCaja.setHorario(c.getHorario());
				citaCaja.setSucursal(c.getSucursal());
				citaCaja.setSector(c.getSector());
				citaCaja.setAnio(c.getAnio());
				citaCaja.setIdTurno(c.getIdTurno());
				citaCaja.setDescripcionSucursal(c.getDescripcionSucursal());
				citaCaja.setIdDiaSeleccionado(c.getFecha());
				citaCaja.setIdHoraSeleccionado(c.getFraccion().substring(0, 4));
				citaCaja.setMotivoDescripcion(obtenerDescripcionMotivo(c.getMotivoId(), c.getSucursal(), c.getSector()));
				citaCaja.setMotivoId(String.valueOf(c.getMotivoId()));
				citaCaja.setTipo(VER_TURNO_CAJA);
				actionSheetCaja.setTipo(VER_TURNO_CAJA);
			} else if (c.getSector().equals(PLATAFORMA)) {
				citaEjecutivo.setDireccion(c.getDireccion());
				citaEjecutivo.setDia(c.getDia());
				citaEjecutivo.setDiaNumero(c.getDiaNumero());
				citaEjecutivo.setMes(c.getMes());
				citaEjecutivo.setHorario(c.getHorario());
				citaEjecutivo.setSucursal(c.getSucursal());
				citaEjecutivo.setSector(c.getSector());
				citaEjecutivo.setAnio(c.getAnio());
				citaEjecutivo.setIdTurno(c.getIdTurno());
				citaEjecutivo.setDescripcionSucursal(c.getDescripcionSucursal());
				citaEjecutivo.setIdDiaSeleccionado(c.getFecha());
				citaEjecutivo.setIdHoraSeleccionado(c.getFraccion().substring(0, 4));
				citaEjecutivo.setMotivoDescripcion(obtenerDescripcionMotivo(c.getMotivoId(), c.getSucursal(), c.getSector()));
				citaEjecutivo.setMotivoId(String.valueOf(c.getMotivoId()));
				//Validadion del tipo de turno
				if(c.getTipoTurno()!=null && c.getTipoTurno().equals("REMOTO")) {
					citaEjecutivo.setTipo(VER_TURNO_EJECUTIVO_AUTOGESTION);
					actionSheetEjecutivo.setTipo(VER_TURNO_EJECUTIVO_AUTOGESTION);
				}
				else {
					citaEjecutivo.setTipo(VER_TURNO_EJECUTIVO);	
					actionSheetEjecutivo.setTipo(VER_TURNO_EJECUTIVO);
				}													
			}
		}
		if (esCajaHabilitadoGeneral()) {
			citaOutView.getActionSheet().add(actionSheetCaja);
		}
		if (esEjecutivoHabilitadoGeneral()) {
			citaOutView.getActionSheet().add(actionSheetEjecutivo);
		}
		if (esCajaHabilitadoGeneral()) {
			citaOutView.getGestiones().add(citaCaja);
		}
		if (esEjecutivoHabilitadoGeneral()) {
			citaOutView.getGestiones().add(citaEjecutivo);
		}
		citaOutView.setDatosMYA(obtenerDatosMYA());
		Segmento segmentoCliente = segmentoClienteBO.obtenerSegmento(sesionCliente.getCliente()).getRespuesta();
		citaOutView.setEsClienteSelect(segmentoCliente.isSelect());
		Cliente cliente = sesionCliente.getCliente();
		citaOutView.setNroDocumento(ISBANStringUtils.formatearDocumento(ISBANStringUtils.eliminarCeros(cliente.getDni())));
		String nya = cliente.getNombre().concat(ISBANStringUtils.ESPACIO_STRING).concat(cliente.getApellido1());
		citaOutView.setNombreApellido(WordUtils.capitalizeFully(nya));
		return citaOutView;
	}

	/**
	 * Obtener descripcion motivo.
	 *
	 * @param motivoId the motivo id
	 * @return the string
	 */
	private String obtenerDescripcionMotivo(Integer motivoId, String sucursal, String sector) {
		List<ComboView> motivos = obtenerMotivos(sucursal, sector);
		for (ComboView motivo : motivos) {
			if (motivoId != null && motivo.getId().equals(motivoId.toString())) {
				return motivo.getDescripcion();
			}
		}
		return "";
	}


	/**
	 * Es caja habilitado general.
	 *
	 * @return true, if successful
	 */
	private boolean esCajaHabilitadoGeneral() {
		return "1".equals(turnosWebCajaHabilitadoGeneral);
	}

	/**
	 * Es ejecutivo habilitado general.
	 *
	 * @return true, if successful
	 */
	private boolean esEjecutivoHabilitadoGeneral() {
		return "1".equals(turnosWebEjecutivoHabilitadoGeneral);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.manager.TurnosWebManager#consultaSucursales()
	 */
	@Override
	public Respuesta<SucursalesOutView> consultaSucursales() {
		LOGGER.info("Turnos Web : Consulta Sucursales");
		Respuesta<SucursalesOutDTO> respuestaBO = turnosWebBO.consultaSucursales();
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		LOGGER.info("{} Cantidad de sucursales select disponibles.", respuestaBO.getRespuesta().getSucursalesDTOList().size());
		SucursalesOutView sucursalesOutView = sucursalesDTOToView(respuestaBO.getRespuesta().getSucursalesDTOList());
		return respuestaFactory.crearRespuestaOk(SucursalesOutView.class,sucursalesOutView);
	}


	/**
	 * Mapea salida del BO de consulta sucursales a la objeto de la vista.
	 *
	 * @param sucursalesDTOList the sucursales DTO list
	 * @return the sucursales out view
	 */
	private SucursalesOutView sucursalesDTOToView(List<SucursalesDTO> sucursalesDTOList) {
		SucursalesOutView sucursalesOutView = new SucursalesOutView();
		for(SucursalesDTO s : sucursalesDTOList) {
			SucursalesView sv = new SucursalesView();
			try {
				BeanUtils.copyProperties(sv, s);
				sucursalesOutView.getSucursalesViewList().add(sv);
			} catch (Exception e) {
				LOGGER.error("Error creando View", e);
			}
		}
		return sucursalesOutView;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.manager.TurnosWebManager#consultaHorariosDisponibles(ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesInView)
	 */
	@Override
	public Respuesta<HorariosDisponiblesOutView> consultaHorariosDisponibles(
			HorariosDisponiblesInView horariosDisponiblesInView) {
		LOGGER.info("Consulta de horarios disponibles");
		if (noExisteSuscripcionMya()) {
			return respuestaFactory.crearRespuestaError("", TipoError.MYA_ERROR,
					CodigoMensajeConstantes.CODIGO_ERROR_CONFIGURACION_TURNO_MYA);
		}
		HorariosDisponiblesInDTO horariosDisponiblesInDTO = horariosDisponiblesInViewToHorariosDisponiblesInDTO(horariosDisponiblesInView);
		Respuesta<HorariosDisponiblesOutDTO> respuestaBO = turnosWebBO.consultaHorariosDisponibles(horariosDisponiblesInDTO);
		grabarEstadisticaConsultaHorariosDisponibles(respuestaBO,horariosDisponiblesInView);
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_HORARIOS_DISPONIBLES);
		}
		HorariosDisponiblesOutView horariosDisponiblesOutView = horariosDisponiblesOutDTOTohorariosDisponiblesOutView(respuestaBO.getRespuesta());
		horariosDisponiblesOutView.setDatosMYA(obtenerDatosMYA());
		if ("1".equals(verificarDiasPermitidos.trim())) {
			horariosDisponiblesOutView.setDiaPermitido(obtenerDiaPermitido());
		}
		if (PLATAFORMA.equals(horariosDisponiblesInView.getSector())) {
			horariosDisponiblesOutView.setMotivos(obtenerMotivos(horariosDisponiblesInView.getNroSuc(), horariosDisponiblesInView.getSector()));
		} else {
			horariosDisponiblesOutView.setMensajeCaja(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TURNOS_MENSAJE_CAJA).getMensaje());
		}
		horariosDisponiblesOutView.setLegalComprobante(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_COMPROBANTE_TURNOS));
		horariosDisponiblesOutView.setLegalTyC(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_TYC_TURNOS));
		return respuestaFactory.crearRespuestaOk(HorariosDisponiblesOutView.class,horariosDisponiblesOutView); 
	}

	/**
	 * Cuando se consulta horarios disponibles la primera vez graba estadisticas
	 * tanto para caja o plataforma.
	 *
	 * @param respuestaBO the respuesta BO
	 * @param horariosDisponiblesInView the horarios disponibles in view
	 */
	private void grabarEstadisticaConsultaHorariosDisponibles(Respuesta<HorariosDisponiblesOutDTO> respuestaBO,
			HorariosDisponiblesInView horariosDisponiblesInView) {
		if(horariosDisponiblesInView.getNroSuc() == null) {
			String estadisticaResultado = EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())
					? EstadisticasConstants.CODIGO_ESTADISTICAS_OK : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
			if(horariosDisponiblesInView.getSector().equals(CAJA)){
				estadisticaManager.add(EstadisticasConstants.SOLICITA_TURNO_CAJA, estadisticaResultado);
			}else if(horariosDisponiblesInView.getSector().equals(PLATAFORMA)) {
				estadisticaManager.add(EstadisticasConstants.SOLICITA_TURNO_PLATAFORMA, estadisticaResultado);
			}
		}		
	}

	/**
	 * Obtiene un obteo datos mya.
	 *
	 * @return the datos MYA view
	 */
	private DatosMYAView obtenerDatosMYA() {
		DatosMYAView datosMYAView = new DatosMYAView();
        CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
        datosMYAView.setEstadoMYA(credencialesMya.getClienteEstado());
        if (!ClienteEstadoEnum.NO_SUSCRIPTO.getCodigo().equals(credencialesMya.getClienteEstado())
                && credencialesMya.getEmail() != null
                && StringUtils.isNotEmpty(credencialesMya.getEmail().trim())) {
        	
        	String email = ISBANStringUtils.ofuscarMail(credencialesMya.getEmail().trim());
        	datosMYAView.setEmail(email);
        }
        if (credencialesMya.getCelular() != null && !"".equals(credencialesMya.getCelular().trim())) {
        	datosMYAView.setAreaCelular(credencialesMya.getCodigoArea());
        	datosMYAView.setNumeroCelular(credencialesMya.getCelular());
            TipoCompaniaEnum compania = TipoCompaniaEnum.fromDescripcion(credencialesMya.getCompaniaSeleccionada());
            datosMYAView.setEmpresaCelular(WordUtils.capitalizeFully(compania.getDescripcion()));
        }
        if(!ClienteEstadoEnum.SUSCRIPTO_ACTIVO.getCodigo().equals(credencialesMya.getClienteEstado())){
			Mensaje msg = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_DETALLE_TURNO);
        	datosMYAView.setMensajeErrorDetalle(msg.getMensaje());
        }
        return datosMYAView;
	}

	/**
	 * Mapea objeto de salida de la respuesta del BO del consulta horarios disponibles al objeto view de salida.
	 *
	 * @param horariosDisponiblesOutDTO the horarios disponibles out DTO
	 * @return the horarios disponibles out view
	 */
	private HorariosDisponiblesOutView horariosDisponiblesOutDTOTohorariosDisponiblesOutView(
			HorariosDisponiblesOutDTO horariosDisponiblesOutDTO) {
		HorariosDisponiblesOutView horariosDisponiblesOutView = new HorariosDisponiblesOutView();
		try {
			BeanUtils.copyProperties(horariosDisponiblesOutView,horariosDisponiblesOutDTO);
			if(horariosDisponiblesOutDTO.getDiasDisponibles().isEmpty()){
				Mensaje msg = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_FEEDBACK_TURNO_BUSQUEDA_SUCURSAL);
				horariosDisponiblesOutView.setMensajeFeedbackError(msg.getMensaje());
			}
		} catch (Exception e) {
			LOGGER.error("Error creando View",e);
		}
        return horariosDisponiblesOutView;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.manager.TurnosWebManager#bajaTurno(ar.com.santanderrio.obp.servicios.turnosweb.view.BajaTurnoInView)
	 */
	@Override
	public Respuesta<Void> bajaTurno(BajaTurnoInView bajaTurnoInView) {
		LOGGER.info("Turnos Web : Baja Turno");
		Respuesta<Void> respuestaBO = turnosWebBO.bajaTurno(bajaTurnoInView.getIdTurno());
		String estadisticaResultado = EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())
				? EstadisticasConstants.CODIGO_ESTADISTICAS_OK : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		String sector = obtenerSector(bajaTurnoInView.getIdTurno());
		if(sector.equals(CAJA)){
			estadisticaManager.add(EstadisticasConstants.BAJA_TURNO_CAJA, estadisticaResultado);
		}else if(sector.equals(PLATAFORMA)){
			estadisticaManager.add(EstadisticasConstants.BAJA_TURNO_PLATAFORMA, estadisticaResultado);
		}
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_BAJA_TURNO);
		}
		LOGGER.info("Baja Id Turno : " + bajaTurnoInView.getIdTurno());
		return respuestaFactory.crearRespuestaOk(null,null,CodigoMensajeConstantes.CODIGO_OK_BAJA_TURNO);
	}

	/**
	 * Obtiene el sector del objeto grabado en session del consulta cita.
	 *
	 * @param idTurno the id turno
	 * @return the string
	 */
	private String obtenerSector(Long idTurno) {
		CitaOutDTO citaOutDTO = sesionParametros.getCitaOutDTO();
		if(citaOutDTO != null){
			for(CitaDTO c : citaOutDTO.getCitaDTOList()){
				if(c.getIdTurno().equals(idTurno)){
					return c.getSector();
				}
			}
		}
		return null;
	}

	/**
	 * Mapea objeto de entrada de la view para objeto de entrada del BO para horarios disponibles.
	 *
	 * @param horariosDisponiblesInView the horarios disponibles in view
	 * @return the horarios disponibles in DTO
	 */
	private HorariosDisponiblesInDTO horariosDisponiblesInViewToHorariosDisponiblesInDTO(
			HorariosDisponiblesInView horariosDisponiblesInView) {
		HorariosDisponiblesInDTO horariosDisponiblesInDTO = new HorariosDisponiblesInDTO();
		try {
			BeanUtils.copyProperties(horariosDisponiblesInDTO,horariosDisponiblesInView);
		} catch (Exception e) {
			LOGGER.error("Error creando DTO",e);
		}
		return horariosDisponiblesInDTO;
	}

	/**
	 * Devuelve falso en caso que estado mya sea distinto de SA.
	 *
	 * @return true, if successful
	 */
	private boolean noExisteSuscripcionMya() {
		return !"SA".equals(sesionParametros.getCredencialesMya().getClienteEstado());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.manager.TurnosWebManager#descargaComprobanteTurnoPDF(ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoInView)
	 */
	@Override
	public Respuesta<ReporteView> descargaComprobanteTurnoPDF(ComprobanteTurnoInView comprobanteTurnoInView) {
		ComprobanteTurnoInDTO comprobanteTurnoInDTO = crearComprobanteTurnoInDTO(comprobanteTurnoInView);
		Respuesta<Reporte> reporteRespuesta = turnosWebBO.generarComprobanteTurnoWeb(comprobanteTurnoInDTO);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		}
		return respuestaView;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.manager.TurnosWebManager#descargaComprobanteTurnoRemotoPDF(ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoRemotoView)
	 */
	@Override
	public Respuesta<ReporteView> descargaComprobanteTurnoRemotoPDF(ComprobanteTurnoRemotoView comprobanteTurnoRemotoView) {
		ComprobanteTurnoRemotoDTO comprobanteTurnoRemotoDTO = crearComprobanteTurnoRemotoDTO(comprobanteTurnoRemotoView);
		Respuesta<Reporte> reporteRespuesta = turnosWebBO.generarComprobanteTurnoWebRemoto(comprobanteTurnoRemotoDTO);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		}
		return respuestaView;
	}
	
	/**
	 * Mapea objeto de entrada de la view para el objeto de entrada del BO para descargar comprobante.
	 *
	 * @param comprobanteTurnoInView the comprobante turno in view
	 * @return the comprobante turno in DTO
	 */
	private ComprobanteTurnoInDTO crearComprobanteTurnoInDTO(ComprobanteTurnoInView comprobanteTurnoInView) {
		ComprobanteTurnoInDTO comprobanteTurnoInDTO = new ComprobanteTurnoInDTO();
		comprobanteTurnoInDTO.setDia(comprobanteTurnoInView.getDia());
		comprobanteTurnoInDTO.setDireccion(comprobanteTurnoInView.getDireccion());
		comprobanteTurnoInDTO.setFechaAlta(comprobanteTurnoInView.getFechaAlta());
		comprobanteTurnoInDTO.setHora(comprobanteTurnoInView.getHora());
		comprobanteTurnoInDTO.setIdTurno(comprobanteTurnoInView.getIdTurno());
		comprobanteTurnoInDTO.setSucursal(comprobanteTurnoInView.getSucursal());
		comprobanteTurnoInDTO.setSector(comprobanteTurnoInView.getSector());
		comprobanteTurnoInDTO.setAreaCelular(comprobanteTurnoInView.getDatosMYA().getAreaCelular());
		comprobanteTurnoInDTO.setNumeroCelular(comprobanteTurnoInView.getDatosMYA().getNumeroCelular());
		comprobanteTurnoInDTO.setEmpresaCelular(comprobanteTurnoInView.getDatosMYA().getEmpresaCelular());
		comprobanteTurnoInDTO.setEmail(comprobanteTurnoInView.getDatosMYA().getEmail());
		Integer motivoId = !"".equals(comprobanteTurnoInView.getMotivoId()) ? Integer.valueOf(comprobanteTurnoInView.getMotivoId()) : null;
		String sucursal = comprobanteTurnoInView.getSucursal().substring(0, LONGITUD_SUCURSAL);
		comprobanteTurnoInDTO.setMotivoDescripcion(obtenerDescripcionMotivo(motivoId, sucursal.trim(), comprobanteTurnoInView.getSector()));
		return comprobanteTurnoInDTO;
	}
	/**
	 * Mapea objeto de entrada de la view para el objeto de entrada del BO para descargar comprobante remoto.
	 *
	 * @param comprobanteTurnoRemotoView the comprobante turno remoto view
	 * @return the comprobante turno remoto DTO
	 */
	private ComprobanteTurnoRemotoDTO crearComprobanteTurnoRemotoDTO(ComprobanteTurnoRemotoView comprobanteTurnoRemotoView) {
		ComprobanteTurnoRemotoDTO comprobanteTurnoRemotoDTO = new ComprobanteTurnoRemotoDTO();
		comprobanteTurnoRemotoDTO.setDia(comprobanteTurnoRemotoView.getDia());
		comprobanteTurnoRemotoDTO.setDireccion(comprobanteTurnoRemotoView.getDireccion());
		comprobanteTurnoRemotoDTO.setFechaAlta(comprobanteTurnoRemotoView.getFechaAlta());
		comprobanteTurnoRemotoDTO.setHora(comprobanteTurnoRemotoView.getHora());
		comprobanteTurnoRemotoDTO.setIdTurno(comprobanteTurnoRemotoView.getIdTurno());
		comprobanteTurnoRemotoDTO.setSucursal(comprobanteTurnoRemotoView.getSucursal());
		comprobanteTurnoRemotoDTO.setSector(comprobanteTurnoRemotoView.getSector());
		comprobanteTurnoRemotoDTO.setAreaCelular(comprobanteTurnoRemotoView.getDatosMYA().getAreaCelular());
		comprobanteTurnoRemotoDTO.setNumeroCelular(comprobanteTurnoRemotoView.getDatosMYA().getNumeroCelular());
		comprobanteTurnoRemotoDTO.setEmpresaCelular(comprobanteTurnoRemotoView.getDatosMYA().getEmpresaCelular());
		comprobanteTurnoRemotoDTO.setEmail(comprobanteTurnoRemotoView.getDatosMYA().getEmail());
		Integer motivoId = !"".equals(comprobanteTurnoRemotoView.getMotivoId()) ? Integer.valueOf(comprobanteTurnoRemotoView.getMotivoId()) : null;
		String sucursal = comprobanteTurnoRemotoView.getSucursal().substring(0, LONGITUD_SUCURSAL);
		comprobanteTurnoRemotoDTO.setMotivoDescripcion(obtenerDescripcionMotivo(motivoId, sucursal.trim(), comprobanteTurnoRemotoView.getSector()));
		comprobanteTurnoRemotoDTO.setTipoTurno(comprobanteTurnoRemotoView.getTipoTurno());
		return comprobanteTurnoRemotoDTO;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.manager.TurnosWebManager#altaModificacionCita(ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaInView)
	 */
	@Override
	public Respuesta<AltaModificacionCitaOutView> altaModificacionCita(
			AltaModificacionCitaInView altaModificacionCitaInView) {
		LOGGER.info("Turnos Web : Alta Cita");
		if (!esCajaHabilitadoGeneral() && CAJA.equals(altaModificacionCitaInView.getSector())) {
			LOGGER.error("Error - Cita por Caja no habilitada");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		if (!esEjecutivoHabilitadoGeneral() && PLATAFORMA.equals(altaModificacionCitaInView.getSector())) {
			LOGGER.error("Error - Cita por Ejecutivo no habilitada");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		AltaModificacionInDTO altaModificacionInDTO = altaCitaInViewToAltaModificacionInDTO(altaModificacionCitaInView);
		Respuesta<AltaModificacionOutDTO> respuestaBO = turnosWebBO.altaModificacionCita(altaModificacionInDTO);
		AltaModificacionCitaOutView altaModificacionCitaOutView = crearAltaModificacionCitaOutView(respuestaBO);
		grabarEstadistica(altaModificacionCitaInView.getAccion(),altaModificacionCitaInView.getSector(),respuestaBO.getEstadoRespuesta());
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			if(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError().equals(TipoError.TIMEOUT.getDescripcion())) {
				return respuestaFactory.crearRespuestaError(AltaModificacionCitaOutView.class, altaModificacionCitaOutView, "", TipoError.TIMEOUT,"");
			}else if(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError().equals(TipoError.ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO.getDescripcion())) {
				return respuestaFactory.crearRespuestaError(AltaModificacionCitaOutView.class, altaModificacionCitaOutView, "", TipoError.ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO,"");				
			}else{
				return respuestaFactory.crearRespuestaError(AltaModificacionCitaOutView.class, altaModificacionCitaOutView, "", TipoError.ERROR_GENERICO,"");
			}
		}
		LOGGER.info("Alta Turno id: ", respuestaBO.getRespuesta().getIdTurno());
		cargarDatosComprobanteSesion(altaModificacionCitaInView, respuestaBO.getRespuesta().getIdTurno());
		return respuestaFactory.crearRespuestaOk(AltaModificacionCitaOutView.class,altaModificacionCitaOutView);
	}

	/**
	 * Carga los datos en sesion para generar el comprobante
	 * @param respuestaBO
	 * @param altaModificacionCitaInView
	 * @return
	 */
	private void cargarDatosComprobanteSesion(AltaModificacionCitaInView altaModificacionCitaInView, Long idTurno){
		LOGGER.info("Cargo los datos del comprobante en sesión: ");
		ComprobanteTurnoInEntity comprobanteTurnoInEntity = new ComprobanteTurnoInEntity();
		comprobanteTurnoInEntity.setIdTurno(idTurno);
		comprobanteTurnoInEntity.setLogoDefault(LOGO_DEFAULT_PLATAFORMA);
		comprobanteTurnoInEntity.setTitulo(TITULO_COMPROBANTE_PLATAFORMA);
		if (altaModificacionCitaInView.getSector().equals(CAJA)) {
			comprobanteTurnoInEntity.setTitulo(TITULO_COMPROBANTE_CAJA);
			comprobanteTurnoInEntity.setLogoDefault(LOGO_DEFAULT_CAJA);
		}

		SimpleDateFormat format1=new SimpleDateFormat("yyyyMMdd");
		Date dia = new Date();
		try {
			dia = format1.parse(altaModificacionCitaInView.getFecha());
		} catch (ParseException e) {
			LOGGER.error("Error al obtener el día del turno", e);
		}
		DateFormat format2 = new SimpleDateFormat("EEEE"); 

		comprobanteTurnoInEntity.setDia(StringUtils.capitalize(format2.format(dia)));
		comprobanteTurnoInEntity.setHora(altaModificacionCitaInView.getHoraInicio());

		String direccion = "";
		for (SucursalesDTO sucursal : sesionParametros.getSucursalesTurnosWeb()) {
			if(altaModificacionCitaInView.getNroSuc().equals(sucursal.getSucursal())){
				direccion = sucursal.getDomicilio();
			}
		}
		comprobanteTurnoInEntity.setSucursal(altaModificacionCitaInView.getNroSuc());
		comprobanteTurnoInEntity.setDireccion(direccion);
		comprobanteTurnoInEntity.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		comprobanteTurnoInEntity.setAreaCelular(altaModificacionCitaInView.getDatosMYA().getAreaCelular());
		comprobanteTurnoInEntity.setNumeroCelular(altaModificacionCitaInView.getDatosMYA().getNumeroCelular());
		comprobanteTurnoInEntity.setEmpresaCelular(altaModificacionCitaInView.getDatosMYA().getEmpresaCelular());
		comprobanteTurnoInEntity.setEmail(altaModificacionCitaInView.getDatosMYA().getEmail());
		comprobanteTurnoInEntity.setMotivoDescripcion(altaModificacionCitaInView.getMotivoId());
		comprobanteTurnoInEntity.setSector(altaModificacionCitaInView.getSector());

		sesionParametros.setComprobanteTurno(comprobanteTurnoInEntity);
	}	
	
	/**
	 * Mapea respuesta de alta del BO para el objeto de view de salida.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the alta modificacion cita out view
	 */
	private AltaModificacionCitaOutView crearAltaModificacionCitaOutView(
			Respuesta<AltaModificacionOutDTO> respuestaBO) {
		AltaModificacionCitaOutView altaModificacionCitaOutView = new AltaModificacionCitaOutView();
		altaModificacionCitaOutView.setIdTurno(respuestaBO.getRespuesta().getIdTurno());
		altaModificacionCitaOutView.setMensajeFeedback(respuestaBO.getRespuesta().getMensajeFeedback());
		altaModificacionCitaOutView.setFechaAlta(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		return altaModificacionCitaOutView;
	}

	/**
	 * graba la estadistica para alta caja/plataforma o modificacion caja/plataforma.
	 *
	 * @param accion the accion
	 * @param sector the sector
	 * @param estadoRespuesta the estado respuesta
	 */
	private void grabarEstadistica(String accion, String sector, EstadoRespuesta estadoRespuesta) {
		String estadisticaResultado = EstadoRespuesta.OK.equals(estadoRespuesta)
				? EstadisticasConstants.CODIGO_ESTADISTICAS_OK : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		if(accion.equals(ALTA)) {
			if(sector.equals(CAJA)){
				estadisticaManager.add(EstadisticasConstants.ALTA_TURNO_CAJA, estadisticaResultado);
			}else if(sector.equals(PLATAFORMA)){
				estadisticaManager.add(EstadisticasConstants.ALTA_TURNO_PLATAFORMA, estadisticaResultado);
			}		
		}else if(accion.equals(MODIFICACION)) {
			if(sector.equals(CAJA)){
				estadisticaManager.add(EstadisticasConstants.MODIFICACION_TURNO_CAJA, estadisticaResultado);
			}else if(sector.equals(PLATAFORMA)){
				estadisticaManager.add(EstadisticasConstants.MODIFICACION_TURNO_PLATAFORMA, estadisticaResultado);
			}	
		}
	}

	/**
	 * Mapea objeto de entrada de la vista para el bo para alta o modificacion de cita.
	 *
	 * @param altaModificacionCitaInView the alta modificacion cita in view
	 * @return the alta modificacion in DTO
	 */
	private AltaModificacionInDTO altaCitaInViewToAltaModificacionInDTO(
			AltaModificacionCitaInView altaModificacionCitaInView) {
		AltaModificacionInDTO altaModificacionInDTO = new AltaModificacionInDTO();
		altaModificacionInDTO.setFecha(altaModificacionCitaInView.getFecha());
		altaModificacionInDTO.setFraccion(altaModificacionCitaInView.getFraccion());
		altaModificacionInDTO.setIdTurno(altaModificacionCitaInView.getIdTurno());
		altaModificacionInDTO.setNroSuc(altaModificacionCitaInView.getNroSuc());
		altaModificacionInDTO.setNup(sesionCliente.getCliente().getNup());
		altaModificacionInDTO.setSector(altaModificacionCitaInView.getSector());
		altaModificacionInDTO.setDescripcionFecha(altaModificacionCitaInView.getDescripcionFecha());
		altaModificacionInDTO.setDescripcionSucursal(altaModificacionCitaInView.getDescripcionSucursal());
		altaModificacionInDTO.setHoraInicio(altaModificacionCitaInView.getHoraInicio());
		altaModificacionInDTO.setAccion(altaModificacionCitaInView.getAccion());
		altaModificacionInDTO.setMotivoId(altaModificacionCitaInView.getMotivoId());
		altaModificacionInDTO.setCuit(altaModificacionCitaInView.getCuit());
		return altaModificacionInDTO;	
	}

	/**
	 * Obtener dia permitido.
	 *
	 * @return the string
	 */
	private String obtenerDiaPermitido() {
		Cliente cliente = sesionCliente.getCliente();
		String ultimoDigito = cliente.getDni().trim().substring(cliente.getDni().length() - 1);
		Map<String, Object> props = propertyMap.getProperties();
        for (String key : props.keySet()) {
        	String propValue = props.get(key).toString().trim();
        	if (key.contains(DIAS_PERMITIDOS_PREFIX) && propValue.contains(ultimoDigito)) {
        		String diaPermitido = key.substring(DIAS_PERMITIDOS_PREFIX.length()).trim();
        		int diaPermitidoNro = Integer.valueOf(diaPermitido);
        		DateFormatSymbols dfs = new DateFormatSymbols(new Locale("es", "ES"));
        		String[] weekdays = dfs.getWeekdays();
        		String dayName = weekdays[diaPermitidoNro + 1];
        		return WordUtils.capitalize(dayName);
        	}
        }
        return "";
	}

	/**
	 * Obtener motivos.
	 *
	 * @return the list
	 */
	private List<ComboView> obtenerMotivos(String nroSuc, String sector) {
		List<ComboView> comboOpts = new ArrayList<ComboView>();
		if (nroSuc == null) {
			nroSuc = ISBANStringUtils.fillNum(sesionCliente.getCliente().getSegmento().getPesucadm(), LONGITUD_SUCURSAL);
		}
		String motivoKey = nroSuc.concat(sector);
		Map<String, List<MotivoTurnoDTO>> motivosTurno = sesionParametros.getMotivosTurno();
		if (motivosTurno == null) {
			motivosTurno = new HashMap<String, List<MotivoTurnoDTO>>();
			sesionParametros.setMotivosTurno(motivosTurno);
		}
		if (motivosTurno.get(motivoKey) != null) {
			cargarComboOpts(comboOpts, motivosTurno.get(motivoKey));
		} else {
			Respuesta<List<MotivoTurnoDTO>> respuestaBO = turnosWebBO.obtenerMotivosTurno(new MotivosInDTO(nroSuc, sector));
			if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
				motivosTurno.put(motivoKey, respuestaBO.getRespuesta());
				cargarComboOpts(comboOpts, respuestaBO.getRespuesta());
			}
		}
		return comboOpts;
	}

	/**
	 * Cargar combo opts.
	 *
	 * @param comboOpts the combo opts
	 * @param motivosDTO the motivos DTO
	 */
	private void cargarComboOpts(List<ComboView> comboOpts, List<MotivoTurnoDTO> motivosDTO) {
		for (MotivoTurnoDTO motivo : motivosDTO) {
			ComboView viewOpt = new ComboView(motivo.getDescripcion(), motivo.getId());
			comboOpts.add(viewOpt);
		}
	}

	/**
	 * Consulta consultaSantanderExpress
	 * Autogestion
	 */
	@Override
	public Respuesta<String> consultaSantanderExpress() {
		Respuesta<String>resultado =new Respuesta<String>();
		resultado.setEstadoRespuesta(EstadoRespuesta.OK);
		resultado.setRespuesta(urlCardCorresponsalia);
		return resultado;
	}	

	/**
	 * Mapear operador ejecutivo.
	 *
	 * @param segmento the segmento
	 * @return the operador ejecutivo view
	 */
	private OperadorEjecutivoView mapearOperadorEjecutivo(Segmento segmento) {
		OperadorEjecutivoView ejecutivoView = null;
		if (segmento.getOperadorEjecutivo() != null) {
			ejecutivoView = new OperadorEjecutivoView();
			OperadorEjecutivo operadorEjecutivo = segmento.getOperadorEjecutivo();
			ejecutivoView.setFotoEjecutivo(operadorEjecutivo.getFotoEjecutivo());
			if (!operadorEjecutivo.getErrorOperador()) {
				ejecutivoView.setEmail(operadorEjecutivo.getEmail());
				ejecutivoView.setHorarioAtencion(operadorEjecutivo.getHorarioAtencion());
				Boolean estadoActivo = validarHorarioEjecutivoActivo(operadorEjecutivo) && operadorEjecutivo.getDiaLaborable();
				ejecutivoView.setEstadoEjecutivo(estadoActivo);
				ejecutivoView.setDescripcionHorario(obtenerDescripcionHorario(estadoActivo, operadorEjecutivo));
				ejecutivoView.setNombreEjecutivo(operadorEjecutivo.getNombreEjecutivo());
				ejecutivoView.setWhatsappEjecutivo(operadorEjecutivo.getWhatsappEjecutivo());
				ejecutivoView.setTelefonoMasivo(operadorEjecutivo.getTelefonoMasivo());
				ejecutivoView.setMensajeEjecutivoTransitorio(operadorEjecutivo.getMensajeEjecutivoTransitorio());
				ejecutivoView.setIdEjecutivo(operadorEjecutivo.getIdEjecutivo());
			} else {
				ejecutivoView.setMensajeErrorEjecutivo(mensajeBO
				        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_MENSAJE_EJECUTIVO).getMensaje());
			}
		}
		return ejecutivoView;
	}

	/**
	 * Determinar si corresponde actualizar la disponibilidad/indisponibilidad del
	 * ejecutivo si esta fuera de horario, status.
	 * 
	 * @param operadorEjecutivo
	 * @return
	 */
	private Boolean validarHorarioEjecutivoActivo(OperadorEjecutivo operadorEjecutivo) {
		SimpleDateFormat sdfHM = new SimpleDateFormat("HH:mm");
		if (StringUtils.isNotBlank(operadorEjecutivo.getHorarioAtencionInicio())
		        && StringUtils.isNotBlank(operadorEjecutivo.getHorarioAtencionFin())) {
			try {
				int from = getSegs(sdfHM, operadorEjecutivo.getHorarioAtencionInicio());
				int to = getSegs(sdfHM, operadorEjecutivo.getHorarioAtencionFin());
				int ahora = getSegs(sdfHM, null);
				return to > from && ahora >= from && ahora <= to || to < from && (ahora >= from || ahora <= to);
			} catch (ParseException e) {
				LOGGER.error(
				        "No se pudo calcular el horario del supervisor para activar el topbar se solicita que se muestre activo",
				        e);
			}
		} else {
			LOGGER.info(
			        "No se pudo obtener el horario de atencion del ejecutivo se opta devolverlo activo por definicion");
		}
		return Boolean.TRUE;
	}

	/**
	 * Obtener los segundos apartir de una hora recibida.
	 * 
	 * @param sdfHM
	 * @param hora
	 * @return
	 * @throws ParseException
	 */
	private int getSegs(SimpleDateFormat sdfHM, String hora) throws ParseException {
		Calendar cal = Calendar.getInstance();
		if (StringUtils.isEmpty(hora)) {
			Date date = new Date();
			cal.setTime(date);
		} else {
			Date dateHM = sdfHM.parse(hora);
			cal.setTime(dateHM);
		}
		return cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
	}
	
	/**
	 * Determinar si corresponde actualizar la disponibilidad/indisponibilidad del
	 * ejecutivo si esta fuera de horario, mensaje.
	 * 
	 * @param estadoActivo
	 * @param operadorEjecutivo
	 * @return
	 */	
	private String obtenerDescripcionHorario(Boolean estadoActivo, OperadorEjecutivo operadorEjecutivo) {
		if (estadoActivo) {
			return legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.CODIGO_EJECUTIVO_DIPONIBLE);
		} else {
			if (StringUtils.isNotBlank(operadorEjecutivo.getHorarioAtencionInicio())) {
				return MessageFormat.format(
				        legalBO.obtenerLegalesPorCodigo(
				                CodigoMensajeConstantes.CODIGO_MODELO_HORARIOS_OPERADOR_EJECUTIVO_NO_DISPONIBLE),
				        operadorEjecutivo.getHorarioAtencionInicio(), operadorEjecutivo.getHorarioAtencionFin());
			} else {
				LOGGER.info("Se devuelve descripcion vacia por no disponer de horario de atencion notificado");
				return StringUtils.EMPTY;
			}
		}
	}

	/**
     * Retorna el Operador Ejecutivo obtenido desde la sesión del cliente.
     * 
     * @param
     * 
     * @return the json object respuesta
     */
	@Override
	public Respuesta<OperadorEjecutivoView> obtenerOperadorEjecutivoSelectOnline() {		
		return respuestaFactory.crearRespuestaOk(mapearOperadorEjecutivo(sesionCliente.getCliente().getSegmento()));
	} 
	
}
