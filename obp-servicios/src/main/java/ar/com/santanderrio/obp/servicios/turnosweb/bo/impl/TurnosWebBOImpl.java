/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.bo.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ArrayOfMotivoPorSucursalYSectorCliSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.CitasConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetBajaTurnoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaSucursalesSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetModificacionCitaSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetMotivosPorSucursalYSectorCliNoCliSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.HorarioSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.MotivoPorSucursalYSectorCliSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.SucursalSvcResponse;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.ConsultarSucursalesDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO;
import ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.CitaDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.CitaOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.ComprobanteTurnoInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.ComprobanteTurnoRemotoDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.DiasDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.FraccionHorariaDisponibleDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.MotivoTurnoDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.MotivosInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.SucursalesDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.SucursalesOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.AltaModificacionCitaInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.ComprobanteTurnoInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.HorariosDisponiblesInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.MotivosInEntity;

/**
 * The Class TurnosWebBOImpl.
 *
 * @author IT Resources
 */
@Component
public class TurnosWebBOImpl implements TurnosWebBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TurnosWebBOImpl.class);

	/** The turnos Web DAO. */
	@Autowired
	private TurnosWebDAO turnosWebDAO;

	/** The mensaje bo. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The consultar Sucursales DAO. */
	@Autowired
	private ConsultarSucursalesDAO consultarSucursalesDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The session parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The Constant ERROR_DISTINTO_CERO. */
	private static final String ERROR_DISTINTO_CERO = "CODIGO DE ERROR DISTINTO DE 0";

	/** The Constant CAJA. */
	private static final String CAJA = "C";

	/** The Constant LOGO_DEFAULT_CAJA. */
	private static final String LOGO_DEFAULT_CAJA = "Solicitud de turno en caja";

	/** The Constant LOGO_DEFAULT_PLATAFORMA. */
	private static final String LOGO_DEFAULT_PLATAFORMA = "Solicitud de turno con ejecutivo";
	
	/** The Constant LOGO_DEFAULT_AUTOGESTION. */
	private static final String LOGO_DEFAULT_AUTOGESTION = "Turno online";

	/** The Constant TITULO_COMPROBANTE_PLATAFORMA. */
	private static final String TITULO_COMPROBANTE_PLATAFORMA = "Comprobante de Solicitud de Turno con Ejecutivo";
	
	/** The Constant TITULO_COMPROBANTE_AUTOGESTION. */
	private static final String TITULO_COMPROBANTE_AUTOGESTION = "Comprobante Turno";

	/** The Constant TITULO_COMPROBANTE_CAJA. */
	private static final String TITULO_COMPROBANTE_CAJA = "Comprobante de Solicitud de Turno en Caja";
	
	/** The Constant TURNO_HABILITADO. */
	private static final String TURNO_HABILITADO = "HABILITADO";

	/** The Constant ALTA. */
	private static final String ALTA = "A";

	/** The Constant MODIFICACION. */
	private static final String MODIFICACION = "M";
		
	/** The Constant ALTA. */
	private static final int LONGITUD_SUCURSAL = 3;

	/** The Constant CODIGO_ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO. */
	private static final String CODIGO_ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO = "3";

	/** The Constant CODIGO_OK_ALTA_MODIFICACION_TURNO. */
	private static final String CODIGO_OK_ALTA_MODIFICACION_TURNO = "0";

	/** The Constant MOTIVO_ID_CAJA_999. */
	private static final String MOTIVO_ID_CAJA_999 = "999";

	/** The file path. */
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO#consultarCitas(java.lang.String)
	 */
	@Override
	public Respuesta<CitaOutDTO> consultarCitas(String nup){
		List<CitaDTO> citaDTOList = null;
		try {
			GetConsultaCitaConMotivoSvcResponse consultaCitas = turnosWebDAO.consultarCitas(nup);
			if (BigDecimal.ZERO.equals(consultaCitas.getCodigoError())) {
				citaDTOList = consultaCitasToCitaDTOList(consultaCitas.getCitas().getValue().getCitasConMotivoSvcResponse());
				CitaOutDTO citaOutDTO = new CitaOutDTO();
				citaOutDTO.setCitaDTOList(citaDTOList);
				sesionParametros.setCitaOutDTO(citaOutDTO);
				return respuestaFactory.crearRespuestaOk(CitaOutDTO.class, citaOutDTO);
			} else {
				LOGGER.error(ERROR_DISTINTO_CERO);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} catch (DAOException e) {
			LOGGER.error("Error al consultar DAO con nup", nup,e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Consulta citas to cita DTO list.
	 *
	 * @param list the list
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	private List<CitaDTO> consultaCitasToCitaDTOList(List<CitasConMotivoSvcResponse> list) throws DAOException {
		List<CitaDTO> citas = new ArrayList<CitaDTO>();
		for (CitasConMotivoSvcResponse c : list) {
			int anio = Integer.parseInt(c.getFecha().getValue().substring(0, 4));
			int mesNumero = Integer.parseInt(c.getFecha().getValue().substring(4, 6));
			int diaNumero = Integer.parseInt(c.getFecha().getValue().substring(6, c.getFecha().getValue().length()));
			DateTime fechaTurno = new DateTime(anio, mesNumero, diaNumero, 0, 0);
			String dia =  WordUtils.capitalizeFully(fechaTurno.dayOfWeek().getAsText(new Locale("ES")));
			String mes = WordUtils.capitalizeFully(fechaTurno.monthOfYear().getAsText(new Locale("ES")));
			String horario = c.getFraccion().getValue().substring(0, 2) + ":" + c.getFraccion().getValue().substring(2, 4) + " hs";			
			CitaDTO citaDTO = new CitaDTO();
			citaDTO.setFraccion(c.getFraccion().getValue());
			citaDTO.setFecha(c.getFecha().getValue());
			citaDTO.setDireccion(c.getDireccion() != null ? WordUtils.capitalizeFully(c.getDireccion().getValue()) : "");
			citaDTO.setDia(dia);
			citaDTO.setDiaNumero(diaNumero);
			citaDTO.setMes(mes);
			citaDTO.setHorario(horario);
			citaDTO.setSucursal(c.getIdSucursal() != null ? c.getIdSucursal().getValue() : "");
			citaDTO.setSector(c.getSector() != null ? c.getSector().getValue() : "");
			citaDTO.setAnio(anio);
			citaDTO.setIdTurno(c.getIdTurno());
			citaDTO.setMotivoId(c.getIdMotivo().intValue());
			citaDTO.setTipoTurno(c.getTipoTurno().getValue());

			Sucursal sucursal = consultarSucursalesDAO.consultarSucursalPorId(StringUtils.leftPad(c.getIdSucursal().getValue(), 4, "0"));
			if(sucursal != null) {
				citaDTO.setDescripcionSucursal(WordUtils.capitalizeFully(sucursal.getDescripcion()));	
			}
			citas.add(citaDTO);
		}
		return citas;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO#consultaSucursales()
	 */
	@Override
	public Respuesta<SucursalesOutDTO> consultaSucursales(){
		List<SucursalesDTO>  sucursalesDTOList = sesionParametros.getSucursalesTurnosWeb();
		SucursalesOutDTO sucursalesOutDTO = new SucursalesOutDTO();
		if(sucursalesDTOList == null){
			try {
				GetConsultaSucursalesSvcResponse consultaSucursales = turnosWebDAO.consultaSucursales();
				if (BigDecimal.ZERO.equals(consultaSucursales.getCodigoError())) {
					sucursalesDTOList = getConsultaSucursalesSvcResponse2ToSucursalesDTOList(consultaSucursales.getSucursales().getValue().getSucursalSvcResponse());
					sucursalesOutDTO.setSucursalesDTOList(sucursalesDTOList);
					sesionParametros.setSucursalesTurnosWeb(sucursalesDTOList);
					return respuestaFactory.crearRespuestaOk(SucursalesOutDTO.class, sucursalesOutDTO);
				} else {
					LOGGER.error(ERROR_DISTINTO_CERO);
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				}
			} catch (DAOException e) {
				LOGGER.error("Error al consultar DAO", e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}else{
			sucursalesOutDTO.setSucursalesDTOList(sucursalesDTOList);
			return respuestaFactory.crearRespuestaOk(SucursalesOutDTO.class, sucursalesOutDTO);
		}
	}

	/**
	 * Mapea las sucursales obtenidas del DAO al DTO.
	 *
	 * @param sucursalSvcResponse the sucursal svc response
	 * @return the consulta sucursales svc response 2 to sucursales DTO list
	 */
	private List<SucursalesDTO> getConsultaSucursalesSvcResponse2ToSucursalesDTOList(
			List<SucursalSvcResponse> sucursalSvcResponse) {
		List<SucursalesDTO> sucursalesDTOList = new ArrayList<SucursalesDTO>();
		for(SucursalSvcResponse s : sucursalSvcResponse){
			SucursalesDTO sucursalesDTO = new SucursalesDTO();
			sucursalesDTO.setDescripcion(WordUtils.capitalizeFully(s.getDescri().getValue()));
			sucursalesDTO.setDomicilio(WordUtils.capitalizeFully(s.getDomicilio().getValue()));
			sucursalesDTO.setLocalidad(WordUtils.capitalizeFully(s.getLocalidad().getValue()));
			sucursalesDTO.setSucursal(s.getSucursal().getValue());
			sucursalesDTOList.add(sucursalesDTO);
		}
		return sucursalesDTOList;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO#consultaHorariosDisponibles(ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesInDTO)
	 */
	@Override
	public Respuesta<HorariosDisponiblesOutDTO> consultaHorariosDisponibles(
			HorariosDisponiblesInDTO horariosDisponiblesInDTO){
		HorariosDisponiblesInEntity horariosDisponiblesInEntity = horariosDisponibleInDTOToHorariosDisponiblesInEntity(horariosDisponiblesInDTO);
		try {
			GetConsultaHorariosDisponiblesSvcResponse getConsultaHorariosDisponiblesSvcResponse = turnosWebDAO.consultaHorarioDisponibles(horariosDisponiblesInEntity);
			if (BigDecimal.ZERO.equals(getConsultaHorariosDisponiblesSvcResponse.getCodigoError())) {
				List<DiasDisponiblesDTO> diasDisponibles = getConsultaHorariosDisponiblesSvcResponse2ToHorariosDisponiblesOutDTO(getConsultaHorariosDisponiblesSvcResponse.getHorarios().getValue().getHorarioSvcResponse());
				HorariosDisponiblesOutDTO horariosDisponiblesOutDTO = new HorariosDisponiblesOutDTO();
				horariosDisponiblesOutDTO.setDiasDisponibles(diasDisponibles);
				Sucursal descSuc = this.consultarSucursalesDAO.consultarSucursalPorId(StringUtils.leftPad(horariosDisponiblesInEntity.getNroSuc(), 4, "0"));
				Respuesta<SucursalesOutDTO> respuestaSucursales = null;
				if(descSuc == null) {
					respuestaSucursales = this.consultaSucursales();
					if(respuestaSucursales.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
						return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,"");
					}
				}
				setDatosConsultaHorarioDisponibles(horariosDisponiblesOutDTO,horariosDisponiblesInEntity,descSuc,respuestaSucursales);
				ordenarDiasDisponibles(horariosDisponiblesOutDTO);
				ordenarHorariosDisponibles(horariosDisponiblesOutDTO);
				return respuestaFactory.crearRespuestaOk(HorariosDisponiblesOutDTO.class, horariosDisponiblesOutDTO);
			} else {
				LOGGER.error(ERROR_DISTINTO_CERO);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_HORARIOS_DISPONIBLES);
			}
		} catch (DAOException e) {
			LOGGER.error("Error al consultar DAO con", horariosDisponiblesInEntity,e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_HORARIOS_DISPONIBLES);
		}
	}

	/**
	 * Setea los datos cross de todos los horarios para el OUT DTO de los
	 * horarios disponibles.
	 *
	 * @param horariosDisponiblesOutDTO
	 *            the horarios disponibles out DTO
	 * @param horariosDisponiblesInEntity
	 *            the horarios disponibles in entity
	 * @param descSuc
	 *            the desc suc
	 * @param respuestaSucursales
	 *            the respuesta sucursales
	 */
	private void setDatosConsultaHorarioDisponibles(HorariosDisponiblesOutDTO horariosDisponiblesOutDTO,
			HorariosDisponiblesInEntity horariosDisponiblesInEntity, Sucursal descSuc,
			Respuesta<SucursalesOutDTO> respuestaSucursales) {
		
		if(descSuc != null) {
			horariosDisponiblesOutDTO.setDescripcionSucursal(WordUtils.capitalizeFully(descSuc.getDescripcion()));	
			horariosDisponiblesOutDTO.setDireccion(WordUtils.capitalizeFully(descSuc.getDireccion()));
		}else {
			for(int i=0; i<respuestaSucursales.getRespuesta().getSucursalesDTOList().size(); i++) {
				if(StringUtils.equals(respuestaSucursales.getRespuesta().getSucursalesDTOList().get(i).getSucursal(), horariosDisponiblesInEntity.getNroSuc())) {
					horariosDisponiblesOutDTO.setDescripcionSucursal(WordUtils.capitalizeFully(respuestaSucursales.getRespuesta().getSucursalesDTOList().get(i).getDescripcion()));
					horariosDisponiblesOutDTO.setDireccion(WordUtils.capitalizeFully(respuestaSucursales.getRespuesta().getSucursalesDTOList().get(i).getDomicilio()));
					break;
				}
			}
		}
		horariosDisponiblesOutDTO.setIdSuc(horariosDisponiblesInEntity.getNroSuc());
		horariosDisponiblesOutDTO.setSector(horariosDisponiblesInEntity.getSector());		
	}

	/**
	 * Ordena por dia.
	 *
	 * @param horariosDisponiblesOutDTO the horarios disponibles out DTO
	 */
	private void ordenarDiasDisponibles(HorariosDisponiblesOutDTO horariosDisponiblesOutDTO) {
		Collections.sort(horariosDisponiblesOutDTO.getDiasDisponibles());
	}

	/**
	 * Ordena por horario.
	 *
	 * @param horariosDisponiblesOutDTO the horarios disponibles out DTO
	 */
	private void ordenarHorariosDisponibles(HorariosDisponiblesOutDTO horariosDisponiblesOutDTO) {
		for(DiasDisponiblesDTO diasDisponiblesDTO : horariosDisponiblesOutDTO.getDiasDisponibles()) {
			Collections.sort(diasDisponiblesDTO.getFraccionHorariaDisponible());
		}

	}

	/**
	 * Mapea los horarios obtenidos del DAO al DTO.
	 *
	 * @param horarioSvcResponse the horario svc response
	 * @return the consulta horarios disponibles svc response 2 to horarios disponibles out DTO
	 */
	private List<DiasDisponiblesDTO> getConsultaHorariosDisponiblesSvcResponse2ToHorariosDisponiblesOutDTO(
			List<HorarioSvcResponse> horarioSvcResponse) {
		List<DiasDisponiblesDTO> diasDisponiblesDTOList = new ArrayList<DiasDisponiblesDTO>();
		List<FraccionHorariaDisponibleDTO> fraccionHorariaDisponiblesList = new ArrayList<FraccionHorariaDisponibleDTO>();
		for(HorarioSvcResponse h : horarioSvcResponse){
			FraccionHorariaDisponibleDTO fraccionHorariaDisponibleDTO = obtenerFraccionHoraria(h.getFraccion().getValue(),h.getIdTurno());
			if(h.getDescri().getValue().equals(TURNO_HABILITADO)) {
				String fecha = h.getFecha().getValue();
				int anio = Integer.parseInt(fecha.substring(0, 4));
				int mesNumero = Integer.parseInt(fecha.substring(4, 6));
				int diaNumero = Integer.parseInt(fecha.substring(6, fecha.length()));
				DateTime fechaTurno = new DateTime(anio, mesNumero, diaNumero, 0, 0);
				String dia =  WordUtils.capitalizeFully(fechaTurno.dayOfWeek().getAsText(new Locale("ES")));
				String mes = WordUtils.capitalizeFully(fechaTurno.monthOfYear().getAsText(new Locale("ES")));
				String fechaDescripcion = dia +", "+ diaNumero+" "+mes+" "+anio;
				if(fraccionHorariaDisponiblesList.isEmpty()){
					DiasDisponiblesDTO diasDisponiblesDTO = new DiasDisponiblesDTO();
					diasDisponiblesDTO.setId(fecha);
					diasDisponiblesDTO.setDescripcion(fechaDescripcion);
					fraccionHorariaDisponiblesList.add(fraccionHorariaDisponibleDTO);
					diasDisponiblesDTO.setFraccionHorariaDisponible(fraccionHorariaDisponiblesList);
					diasDisponiblesDTOList.add(diasDisponiblesDTO);			
				}else if(diasDisponiblesDTOList.get(diasDisponiblesDTOList.size()-1).getId().equals(fecha)) {
					diasDisponiblesDTOList.get(diasDisponiblesDTOList.size()-1).getFraccionHorariaDisponible().add(fraccionHorariaDisponibleDTO);
				}else if(!diasDisponiblesDTOList.get(diasDisponiblesDTOList.size()-1).getId().equals(fecha)){
					DiasDisponiblesDTO diasDisponiblesDTO = new DiasDisponiblesDTO();
					diasDisponiblesDTO.setId(fecha);
					diasDisponiblesDTO.setDescripcion(fechaDescripcion);
					fraccionHorariaDisponiblesList = new ArrayList<FraccionHorariaDisponibleDTO>();
					fraccionHorariaDisponiblesList.add(fraccionHorariaDisponibleDTO);
					diasDisponiblesDTO.setFraccionHorariaDisponible(fraccionHorariaDisponiblesList);
					diasDisponiblesDTOList.add(diasDisponiblesDTO);
				}
			}
		}
		return diasDisponiblesDTOList;
	}


	/**
	 * Parsea la respuesta del atributo fraccion de la respuesta del DAO al DTO.
	 *
	 * @param fraccionHoraria the fraccion horaria
	 * @param idTurno the id turno
	 * @return the fraccion horaria disponible DTO
	 */
	private FraccionHorariaDisponibleDTO obtenerFraccionHoraria(String fraccionHoraria, Long idTurno) {
		FraccionHorariaDisponibleDTO fraccionHorariaDisponibleDTO = new FraccionHorariaDisponibleDTO();
		String horaInicioFormat = fraccionHoraria.substring(0, 2) + ":" + fraccionHoraria.substring(2, 4) + "hs";			
		String[] parts = fraccionHoraria.split("-");
		fraccionHorariaDisponibleDTO.setId(parts[0]);
		fraccionHorariaDisponibleDTO.setHoraInicio(parts[0]);
		fraccionHorariaDisponibleDTO.setHoraFin(parts[1]);
		fraccionHorariaDisponibleDTO.setFraccion(fraccionHoraria);
		fraccionHorariaDisponibleDTO.setDescripcion(horaInicioFormat);
		fraccionHorariaDisponibleDTO.setIdTurno(idTurno);
		return fraccionHorariaDisponibleDTO;
	}

	/**
	 * Mapea objeto de entrada del DTO al Entity para el DAO para la consulta de horarios disponibles.
	 *
	 * @param horariosDisponiblesInDTO the horarios disponibles in DTO
	 * @return the horarios disponibles in entity
	 */
	private HorariosDisponiblesInEntity horariosDisponibleInDTOToHorariosDisponiblesInEntity(
			HorariosDisponiblesInDTO horariosDisponiblesInDTO) {
		HorariosDisponiblesInEntity horariosDisponiblesInEntity = new HorariosDisponiblesInEntity();
		horariosDisponiblesInEntity.setNroSuc(horariosDisponiblesInDTO.getNroSuc());
		horariosDisponiblesInEntity.setSector(horariosDisponiblesInDTO.getSector());
		if(horariosDisponiblesInDTO.getNroSuc() == null) {
			horariosDisponiblesInEntity.setNroSuc(ISBANStringUtils.fillNum(sesionCliente.getCliente().getSegmento().getPesucadm(), LONGITUD_SUCURSAL));
		}
		return horariosDisponiblesInEntity;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO#bajaTurno(java.lang.Long)
	 */
	@Override
	public Respuesta<Void> bajaTurno(Long idTurno){
		try {
			GetBajaTurnoSvcResponse getBajaTurnoSvcResponse = turnosWebDAO.bajaTurno(idTurno);
			if (BigDecimal.ZERO.equals(getBajaTurnoSvcResponse.getCodigoError())) {
				return respuestaFactory.crearRespuestaOk(null);
			} else {
				LOGGER.error(ERROR_DISTINTO_CERO);
				return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(null, null);
			}	
		} catch (Exception e) {
			LOGGER.error("Error al consultar DAO con id Turno", idTurno,e);
			return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(null, null);
		}
	}

	/**
	 * Busca una cita en el objeto grabado en session .
	 *
	 * @param idTurno the id turno
	 * @return the cita DTO
	 */
	private CitaDTO obtenerCita(Long idTurno) {
		CitaOutDTO citaOutDTO = sesionParametros.getCitaOutDTO();
		if(citaOutDTO != null){
			for(CitaDTO c : citaOutDTO.getCitaDTOList()){
				if(c.getIdTurno().equals(idTurno)){
					return c;
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO#generarComprobanteTurnoWeb(ar.com.santanderrio.obp.servicios.turnosweb.dto.ComprobanteTurnoInDTO)
	 */
	@Override
	public Respuesta<Reporte> generarComprobanteTurnoWeb(ComprobanteTurnoInDTO comprobanteTurnoInDTO) {
		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
		CitaDTO citaDTO = null;
		ComprobanteTurnoInEntity comprobanteTurnoInEntity = null;
		try {
			if(comprobanteTurnoInDTO.getIdTurno() != null) {
				citaDTO = obtenerCita(comprobanteTurnoInDTO.getIdTurno());
				if(citaDTO == null){
					return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
				}
				comprobanteTurnoInEntity = createComprobanteTurnoInEntity(citaDTO,comprobanteTurnoInDTO);
			}else{
				comprobanteTurnoInEntity = sesionParametros.getComprobanteTurno(); 
						//createComprobanteTurnoInEntity(comprobanteTurnoInDTO);
			}
			Reporte reporte = turnosWebDAO.generarComprobante(comprobanteTurnoInEntity, sesionCliente.getCliente());
			respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaReporte.setRespuesta(reporte);
		} catch (Exception e) {
			return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
		}
		LOGGER.debug("TurnosWeb _ finalizando descargarComprobante");
		return respuestaReporte;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO#generarComprobanteTurnoWebRemoto(ar.com.santanderrio.obp.servicios.turnosweb.dto.ComprobanteTurnoRemotoDTO)
	 */
	@Override
	public Respuesta<Reporte> generarComprobanteTurnoWebRemoto(ComprobanteTurnoRemotoDTO comprobanteTurnoRemotoDTO) {
		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
		CitaDTO citaDTO = null;
		ComprobanteTurnoInEntity comprobanteTurnoInEntity = null;
		try {
			if(comprobanteTurnoRemotoDTO.getIdTurno() != null) {
				citaDTO = obtenerCita(comprobanteTurnoRemotoDTO.getIdTurno());
				if(citaDTO == null){
					return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
				}
				comprobanteTurnoInEntity = createComprobanteTurnoInEntity(citaDTO,comprobanteTurnoRemotoDTO);
			}else{
				comprobanteTurnoInEntity = createComprobanteTurnoInEntity(comprobanteTurnoRemotoDTO);
			}			
			Reporte reporte = turnosWebDAO.generarComprobanteRemoto(comprobanteTurnoInEntity, sesionCliente.getCliente());
			respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaReporte.setRespuesta(reporte);
		} catch (Exception e) {
			return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
		}
		LOGGER.debug("TurnosWeb _ finalizando descargarComprobante Remoto");
		return respuestaReporte;
	}

	/**
	 *  Mapea IN DTO del comprobante a la Entity para el dao de generar comprobante.
	 *
	 * @param comprobanteTurnoInDTO the comprobante turno in DTO
	 * @return the comprobante turno in entity
	 */
	private ComprobanteTurnoInEntity createComprobanteTurnoInEntity(ComprobanteTurnoInDTO comprobanteTurnoInDTO) {
		ComprobanteTurnoInEntity comprobanteTurnoInEntity = new ComprobanteTurnoInEntity();
		comprobanteTurnoInEntity.setLogoDefault(LOGO_DEFAULT_PLATAFORMA);
		comprobanteTurnoInEntity.setTitulo(TITULO_COMPROBANTE_PLATAFORMA);
		if (comprobanteTurnoInDTO.getSector().equals(CAJA)) {
			comprobanteTurnoInEntity.setTitulo(TITULO_COMPROBANTE_CAJA);
			comprobanteTurnoInEntity.setLogoDefault(LOGO_DEFAULT_CAJA);
		}
		comprobanteTurnoInEntity.setDia(comprobanteTurnoInDTO.getDia());
		comprobanteTurnoInEntity.setHora(comprobanteTurnoInDTO.getHora());
		comprobanteTurnoInEntity.setSucursal(comprobanteTurnoInDTO.getSucursal());
		comprobanteTurnoInEntity.setDireccion(comprobanteTurnoInDTO.getDireccion());
		comprobanteTurnoInEntity.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		comprobanteTurnoInEntity.setAreaCelular(comprobanteTurnoInDTO.getAreaCelular());
		comprobanteTurnoInEntity.setNumeroCelular(comprobanteTurnoInDTO.getNumeroCelular());
		comprobanteTurnoInEntity.setEmpresaCelular(comprobanteTurnoInDTO.getEmpresaCelular());
		comprobanteTurnoInEntity.setEmail(comprobanteTurnoInDTO.getEmail());
		comprobanteTurnoInEntity.setMotivoDescripcion(comprobanteTurnoInDTO.getMotivoDescripcion());
		comprobanteTurnoInEntity.setSector(comprobanteTurnoInDTO.getSector());
		return comprobanteTurnoInEntity;
	}

	/**
	 *  Mapea IN DTO del comprobante a la Entity para el dao de generar comprobante.
	 *
	 * @param comprobanteTurnoInDTO the comprobante turno in DTO
	 * @return the comprobante turno in entity
	 */
	private ComprobanteTurnoInEntity createComprobanteTurnoInEntity(ComprobanteTurnoRemotoDTO comprobanteTurnoRemotoDTO) {
		ComprobanteTurnoInEntity comprobanteTurnoInEntity = new ComprobanteTurnoInEntity();
		comprobanteTurnoInEntity.setLogoDefault(LOGO_DEFAULT_AUTOGESTION);
		comprobanteTurnoInEntity.setTitulo(TITULO_COMPROBANTE_AUTOGESTION);		
		comprobanteTurnoInEntity.setDia(comprobanteTurnoRemotoDTO.getDia());
		comprobanteTurnoInEntity.setHora(comprobanteTurnoRemotoDTO.getHora());
		comprobanteTurnoInEntity.setSucursal(comprobanteTurnoRemotoDTO.getSucursal());
		comprobanteTurnoInEntity.setDireccion(comprobanteTurnoRemotoDTO.getDireccion());
		comprobanteTurnoInEntity.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		comprobanteTurnoInEntity.setAreaCelular(comprobanteTurnoRemotoDTO.getAreaCelular());
		comprobanteTurnoInEntity.setNumeroCelular(comprobanteTurnoRemotoDTO.getNumeroCelular());
		comprobanteTurnoInEntity.setEmpresaCelular(comprobanteTurnoRemotoDTO.getEmpresaCelular());
		comprobanteTurnoInEntity.setEmail(comprobanteTurnoRemotoDTO.getEmail());
		comprobanteTurnoInEntity.setMotivoDescripcion(comprobanteTurnoRemotoDTO.getMotivoDescripcion());
		comprobanteTurnoInEntity.setSector(comprobanteTurnoRemotoDTO.getSector());
		return comprobanteTurnoInEntity;
	}
	/**
	 *  Mapea IN DTO del comprobante a la Entity para el dao de generar comprobante
	 *  Con la cita obtenida de la session.
	 *
	 * @param citaDTO the cita DTO
	 * @param comprobanteTurnoInDTO the comprobante turno in DTO
	 * @return the comprobante turno in entity
	 * @throws DAOException the DAO exception
	 */
	private ComprobanteTurnoInEntity createComprobanteTurnoInEntity(CitaDTO citaDTO, ComprobanteTurnoInDTO comprobanteTurnoInDTO) throws DAOException {
		ComprobanteTurnoInEntity comprobanteTurnoInEntity = new ComprobanteTurnoInEntity();
		Sucursal sucursal = consultarSucursalesDAO.consultarSucursalPorId(StringUtils.leftPad(citaDTO.getSucursal(), 4, "0"));
		if(sucursal == null) {
			String descSucursal = "Suc. " + citaDTO.getSucursal();
			comprobanteTurnoInEntity.setSucursal(descSucursal);
			comprobanteTurnoInEntity.setDireccion(WordUtils.capitalizeFully(citaDTO.getDireccion()));
		}else {
			String descSucursal = "Suc. " + citaDTO.getSucursal()+ " - "+ WordUtils.capitalizeFully(sucursal.getDescripcion());
			comprobanteTurnoInEntity.setSucursal(descSucursal);
			comprobanteTurnoInEntity.setDireccion(WordUtils.capitalizeFully(sucursal.getDireccion()));
		}
		int anio = Integer.parseInt(citaDTO.getFecha().substring(0, 4));
		int mesNumero = Integer.parseInt(citaDTO.getFecha().substring(4, 6));
		int diaNumero = Integer.parseInt(citaDTO.getFecha().substring(6, citaDTO.getFecha().length()));
		DateTime fechaTurno = new DateTime(anio, mesNumero, diaNumero, 0, 0);
		String dia =  WordUtils.capitalizeFully(fechaTurno.dayOfWeek().getAsText(new Locale("ES")));
		String mes = WordUtils.capitalizeFully(fechaTurno.monthOfYear().getAsText(new Locale("ES")));
		String horario = citaDTO.getFraccion().substring(0, 2) + ":" + citaDTO.getFraccion().substring(2, 4) + " hs";			

		String diaMesAnio = dia+", "+diaNumero+" de "+mes+" "+anio;
		comprobanteTurnoInEntity.setLogoDefault(LOGO_DEFAULT_PLATAFORMA);
		comprobanteTurnoInEntity.setTitulo(TITULO_COMPROBANTE_PLATAFORMA);
		if (citaDTO.getSector().equals(CAJA)) {
			comprobanteTurnoInEntity.setLogoDefault(LOGO_DEFAULT_CAJA);
			comprobanteTurnoInEntity.setTitulo(TITULO_COMPROBANTE_CAJA);
		}
		comprobanteTurnoInEntity.setDia(diaMesAnio);
		comprobanteTurnoInEntity.setHora(horario);
		comprobanteTurnoInEntity.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		comprobanteTurnoInEntity.setAreaCelular(comprobanteTurnoInDTO.getAreaCelular());
		comprobanteTurnoInEntity.setNumeroCelular(comprobanteTurnoInDTO.getNumeroCelular());
		comprobanteTurnoInEntity.setEmpresaCelular(comprobanteTurnoInDTO.getEmpresaCelular());
		comprobanteTurnoInEntity.setEmail(comprobanteTurnoInDTO.getEmail());
		comprobanteTurnoInEntity.setMotivoDescripcion(comprobanteTurnoInDTO.getMotivoDescripcion());
		comprobanteTurnoInEntity.setSector(citaDTO.getSector());
		return comprobanteTurnoInEntity;
	}
	
	/**
	 *  Mapea IN DTO del comprobante a la Entity para el dao de generar comprobante
	 *  Con la cita obtenida de la session.
	 *
	 * @param citaDTO the cita DTO
	 * @param comprobanteTurnoRemotoDTO the comprobante turno Remoto DTO
	 * @return the comprobante turno remoto entity
	 * @throws DAOException the DAO exception
	 */
	private ComprobanteTurnoInEntity createComprobanteTurnoInEntity(CitaDTO citaDTO, ComprobanteTurnoRemotoDTO comprobanteTurnoRemotonDTO) throws DAOException {
		ComprobanteTurnoInEntity comprobanteTurnoInEntity = new ComprobanteTurnoInEntity();
		Sucursal sucursal = consultarSucursalesDAO.consultarSucursalPorId(StringUtils.leftPad(citaDTO.getSucursal(), 4, "0"));
		if(sucursal == null) {
			String descSucursal = "Suc. " + citaDTO.getSucursal();
			comprobanteTurnoInEntity.setSucursal(descSucursal);
			comprobanteTurnoInEntity.setDireccion(WordUtils.capitalizeFully(citaDTO.getDireccion()));
		}else {
			String descSucursal = "Suc. " + citaDTO.getSucursal()+ " - "+ WordUtils.capitalizeFully(sucursal.getDescripcion());
			comprobanteTurnoInEntity.setSucursal(descSucursal);
			comprobanteTurnoInEntity.setDireccion(WordUtils.capitalizeFully(sucursal.getDireccion()));
		}
		int anio = Integer.parseInt(citaDTO.getFecha().substring(0, 4));
		int mesNumero = Integer.parseInt(citaDTO.getFecha().substring(4, 6));
		int diaNumero = Integer.parseInt(citaDTO.getFecha().substring(6, citaDTO.getFecha().length()));
		DateTime fechaTurno = new DateTime(anio, mesNumero, diaNumero, 0, 0);
		String dia =  WordUtils.capitalizeFully(fechaTurno.dayOfWeek().getAsText(new Locale("ES")));
		String mes = WordUtils.capitalizeFully(fechaTurno.monthOfYear().getAsText(new Locale("ES")));
		String horario = citaDTO.getFraccion().substring(0, 2) + ":" + citaDTO.getFraccion().substring(2, 4) + " hs";			

		String diaMesAnio = dia+", "+diaNumero+" de "+mes+" "+anio;
		comprobanteTurnoInEntity.setLogoDefault(LOGO_DEFAULT_PLATAFORMA);
		comprobanteTurnoInEntity.setTitulo(TITULO_COMPROBANTE_PLATAFORMA);
		if (citaDTO.getSector().equals(CAJA)) {
			comprobanteTurnoInEntity.setLogoDefault(LOGO_DEFAULT_CAJA);
			comprobanteTurnoInEntity.setTitulo(TITULO_COMPROBANTE_CAJA);
		}
		comprobanteTurnoInEntity.setDia(diaMesAnio);
		comprobanteTurnoInEntity.setHora(horario);
		comprobanteTurnoInEntity.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		comprobanteTurnoInEntity.setAreaCelular(comprobanteTurnoRemotonDTO.getAreaCelular());
		comprobanteTurnoInEntity.setNumeroCelular(comprobanteTurnoRemotonDTO.getNumeroCelular());
		comprobanteTurnoInEntity.setEmpresaCelular(comprobanteTurnoRemotonDTO.getEmpresaCelular());
		comprobanteTurnoInEntity.setEmail(comprobanteTurnoRemotonDTO.getEmail());
		comprobanteTurnoInEntity.setMotivoDescripcion(comprobanteTurnoRemotonDTO.getMotivoDescripcion());
		comprobanteTurnoInEntity.setSector(citaDTO.getSector());
		return comprobanteTurnoInEntity;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO#altaModificacionCita(ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionInDTO)
	 */
	@Override
	public Respuesta<AltaModificacionOutDTO> altaModificacionCita(AltaModificacionInDTO altaModificacionInDTO) {
		AltaModificacionCitaInEntity altaModificacionCitaInEntity = altaCitaInDTOTOAltaCitaInEntity(altaModificacionInDTO);
		AltaModificacionOutDTO altaModificacionOutDTO = new AltaModificacionOutDTO();
		try {
			if(altaModificacionInDTO.getAccion().equals(ALTA)){
				GetAltaCitaConMotivoSvcResponse response = turnosWebDAO.altaCita(altaModificacionCitaInEntity);
				return armarResponseAltaModificacion(response,altaModificacionInDTO,altaModificacionOutDTO);
			}else if(altaModificacionInDTO.getAccion().equals(MODIFICACION)){
				GetModificacionCitaSvcResponse response = turnosWebDAO.modificacionCita(altaModificacionCitaInEntity);
				return armarResponseAltaModificacion(response,altaModificacionInDTO,altaModificacionOutDTO);
			}else {
				String mensajeFeedback = obtenerMensajeFeedback(CodigoMensajeConstantes.FEEDBACK_TIME_OUT_ALTA_MODIFICACION_TURNO,altaModificacionInDTO);
				altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
				return respuestaFactory.crearRespuestaError(AltaModificacionOutDTO.class, altaModificacionOutDTO, "", TipoError.TIMEOUT,"");				
			}			
		} catch (TimeOutException e) {
			String mensajeFeedback = obtenerMensajeFeedback(CodigoMensajeConstantes.FEEDBACK_TIME_OUT_ALTA_MODIFICACION_TURNO,altaModificacionInDTO);
			altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
			LOGGER.error("Time Out Error alta/modificar cita.", e);
			return respuestaFactory.crearRespuestaError(AltaModificacionOutDTO.class, altaModificacionOutDTO, "", TipoError.TIMEOUT,"");
		} catch (DAOException e) {
			LOGGER.error("Error alta/modificar cita");
			String mensajeFeedback = obtenerMensajeFeedback(CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_MODIFICACION_TURNO,altaModificacionInDTO);
			altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
			return respuestaFactory.crearRespuestaError(AltaModificacionOutDTO.class, altaModificacionOutDTO, "", TipoError.ERROR_GENERICO,"");
		}
	}


	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO#obtenerMotivosTurno()
	 */
	@Override
	public Respuesta<List<MotivoTurnoDTO>> obtenerMotivosTurno(MotivosInDTO motivosInDTO) {
		GetMotivosPorSucursalYSectorCliNoCliSvcResponse response;
		MotivosInEntity motivosInEntity = new MotivosInEntity(motivosInDTO.getNroSuc(), motivosInDTO.getSector());
		try {
			response = turnosWebDAO.obtenerMotivosTurno(motivosInEntity);
			List<MotivoTurnoDTO> motivos = cargarMotivosTurnoDTO(response);
			return respuestaFactory.crearRespuestaOk(motivos);
		} catch (DAOException e) {
			LOGGER.error("Error al obtener motivos turno sucursal");
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
	}

	/**
	 * Cargar motivos turno DTO.
	 *
	 * @param response the response
	 * @return the list
	 */
	private List<MotivoTurnoDTO> cargarMotivosTurnoDTO(GetMotivosPorSucursalYSectorCliNoCliSvcResponse response) {
		List<MotivoTurnoDTO> motivosDTO = new ArrayList<MotivoTurnoDTO>();
		if (response != null && BigDecimal.ZERO.equals(response.getCodigoError())) {
			JAXBElement<ArrayOfMotivoPorSucursalYSectorCliSvcResponse> motivosResponse = response.getMotivos();
			ArrayOfMotivoPorSucursalYSectorCliSvcResponse motivosSvc = motivosResponse.getValue();
			if (motivosSvc != null) {
				List<MotivoPorSucursalYSectorCliSvcResponse> motivosSvcResponse = motivosSvc
						.getMotivoPorSucursalYSectorCliSvcResponse();
				for (MotivoPorSucursalYSectorCliSvcResponse motivoSvcResponse : motivosSvcResponse) {
					String idMotivo = String.valueOf(motivoSvcResponse.getIdMotivo());
					if (motivoSvcResponse.getIdMotivo() != null && !MOTIVO_ID_CAJA_999.equals(idMotivo)) {
						MotivoTurnoDTO motivoDTO = new MotivoTurnoDTO(motivoSvcResponse.getDescripcion().getValue(),
								idMotivo);
						motivosDTO.add(motivoDTO);
					}
				}
			}
		}
		return motivosDTO;
	}

	/**
	 * Arma la respuesta para cuando se da de alta un turno.
	 *
	 * @param response the response
	 * @param altaModificacionInDTO the alta modificacion in DTO
	 * @param altaModificacionOutDTO the alta modificacion out DTO
	 * @return the respuesta
	 */
	private Respuesta<AltaModificacionOutDTO> armarResponseAltaModificacion(GetModificacionCitaSvcResponse response, AltaModificacionInDTO altaModificacionInDTO, AltaModificacionOutDTO altaModificacionOutDTO) {
		if(response.getCodigoError() != null) {
			String codigoError = response.getCodigoError().toString();
			if (codigoError.equals(CODIGO_OK_ALTA_MODIFICACION_TURNO)) {
				String mensajeFeedback = obtenerMensajeFeedback(CodigoMensajeConstantes.FEEDBACK_OK_ALTA_MODIFICACION_TURNO,altaModificacionInDTO);
				altaModificacionOutDTO.setIdTurno(response.getIdTurno());
				altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
				return respuestaFactory.crearRespuestaOk(AltaModificacionOutDTO.class, altaModificacionOutDTO);
			} else if (codigoError.equals(CODIGO_ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO)) {
				LOGGER.error("CODIGO ERROR ALTA MODIFICACION: " + codigoError);
				String mensajeFeedback = obtenerMensajeFeedbackErrorAltaModificacionTurno(altaModificacionInDTO);
				altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
				return respuestaFactory.crearRespuestaError(AltaModificacionOutDTO.class, altaModificacionOutDTO, "", TipoError.ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO,"");
			} else {
				LOGGER.error(ERROR_DISTINTO_CERO);
				String mensajeFeedback = obtenerMensajeFeedback(CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_MODIFICACION_TURNO,altaModificacionInDTO);
				altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
				return respuestaFactory.crearRespuestaError(AltaModificacionOutDTO.class, altaModificacionOutDTO, "", TipoError.ERROR_GENERICO,"");
			}
		}else {
			LOGGER.error("Alta Modificacion Turno Web CODIGO DE ERROR NULO");
			String mensajeFeedback = obtenerMensajeFeedback(CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_MODIFICACION_TURNO,altaModificacionInDTO);
			altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
			return respuestaFactory.crearRespuestaError(AltaModificacionOutDTO.class, altaModificacionOutDTO, "", TipoError.ERROR_GENERICO,"");			
		}
	}

	/**
	 * Arma la respuesta para cuando se modifica un turno.
	 *
	 * @param response the response
	 * @param altaModificacionInDTO the alta modificacion in DTO
	 * @param altaModificacionOutDTO the alta modificacion out DTO
	 * @return the respuesta
	 */
	private Respuesta<AltaModificacionOutDTO> armarResponseAltaModificacion(GetAltaCitaConMotivoSvcResponse response, AltaModificacionInDTO altaModificacionInDTO, AltaModificacionOutDTO altaModificacionOutDTO) {
		if(response.getCodigoError() != null) {
			String codigoError = response.getCodigoError().toString();
			if (codigoError.equals(CODIGO_OK_ALTA_MODIFICACION_TURNO)) {
				String mensajeFeedback = obtenerMensajeFeedback(CodigoMensajeConstantes.FEEDBACK_OK_ALTA_MODIFICACION_TURNO,altaModificacionInDTO);
				altaModificacionOutDTO.setIdTurno(response.getIdTurno());
				altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
				return respuestaFactory.crearRespuestaOk(AltaModificacionOutDTO.class, altaModificacionOutDTO);
			} else if (codigoError.equals(CODIGO_ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO)) {
				LOGGER.error("CODIGO ERROR ALTA MODIFICACION: " + codigoError);
				String mensajeFeedback = obtenerMensajeFeedbackErrorAltaModificacionTurno(altaModificacionInDTO);
				altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
				return respuestaFactory.crearRespuestaError(AltaModificacionOutDTO.class, altaModificacionOutDTO, "", TipoError.ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO,"");
			} else {
				LOGGER.error(ERROR_DISTINTO_CERO);
				String mensajeFeedback = obtenerMensajeFeedback(CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_MODIFICACION_TURNO,altaModificacionInDTO);
				altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
				return respuestaFactory.crearRespuestaError(AltaModificacionOutDTO.class, altaModificacionOutDTO, "", TipoError.ERROR_GENERICO,"");
			}					
		}else {
			LOGGER.error("Alta Modificacion Turno Web CODIGO DE ERROR NULO");
			String mensajeFeedback = obtenerMensajeFeedback(CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_MODIFICACION_TURNO,altaModificacionInDTO);
			altaModificacionOutDTO.setMensajeFeedback(mensajeFeedback);
			return respuestaFactory.crearRespuestaError(AltaModificacionOutDTO.class, altaModificacionOutDTO, "", TipoError.ERROR_GENERICO,"");
		}
	}

	/**
	 * Obtener mensaje feedback error alta modificacion turno.
	 *
	 * @param altaModificacionInDTO
	 *            the alta modificacion in DTO
	 * @return the string
	 */
	private String obtenerMensajeFeedbackErrorAltaModificacionTurno(AltaModificacionInDTO altaModificacionInDTO) {
		Mensaje mensaje = null; 
		if(altaModificacionInDTO.getSector().equals(CAJA)) {
			mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO_CAJA);
		}else{
			mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO_EJECUTIVO);
		}	
		return mensaje.getMensaje();
	}

	/**
	 * Obtiene el mensaje para el feedback cuando se da de alta o modifica un turno.
	 *
	 * @param codigoMensaje the codigo mensaje
	 * @param altaModificacionInDTO the alta modificacion in DTO
	 * @return the string
	 */
	private String obtenerMensajeFeedback(String codigoMensaje, AltaModificacionInDTO altaModificacionInDTO) {
		Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(codigoMensaje);
		if(codigoMensaje.equals(CodigoMensajeConstantes.FEEDBACK_TIME_OUT_ALTA_MODIFICACION_TURNO)) {
			return mensaje.getMensaje();
		}
		return MessageFormat.format(mensaje.getMensaje(), altaModificacionInDTO.getDescripcionSucursal(), altaModificacionInDTO.getDescripcionFecha(), altaModificacionInDTO.getHoraInicio());
	}

	/**
	 * Mapea IN DTO para el objeto de entrada de la Entity para dar de alta o modificar un turno.
	 *
	 * @param altaModificacionInDTO the alta modificacion in DTO
	 * @return the alta modificacion cita in entity
	 */
	private AltaModificacionCitaInEntity altaCitaInDTOTOAltaCitaInEntity(AltaModificacionInDTO altaModificacionInDTO) {
		AltaModificacionCitaInEntity altaModificacionCitaInEntity = new AltaModificacionCitaInEntity();
		altaModificacionCitaInEntity.setFecha(altaModificacionInDTO.getFecha());
		altaModificacionCitaInEntity.setFraccion(altaModificacionInDTO.getFraccion());
		altaModificacionCitaInEntity.setIdTurno(altaModificacionInDTO.getIdTurno());
		altaModificacionCitaInEntity.setNroSuc(altaModificacionInDTO.getNroSuc());
		altaModificacionCitaInEntity.setNup(altaModificacionInDTO.getNup());
		altaModificacionCitaInEntity.setSector(altaModificacionInDTO.getSector());
		altaModificacionCitaInEntity.setMotivoId(altaModificacionInDTO.getMotivoId());
		altaModificacionCitaInEntity.setCuit(altaModificacionInDTO.getCuit());
		return altaModificacionCitaInEntity;
	}

	@Override
	public Boolean consultaNupClienteAutogestion(String nup) {
		Boolean result=false;
		try {			
			for (String lineaTexto : archivoResource.leerArchivo(ExternalPropertyType.FILE_LISTADO_NUP_AUTOGESTION)) {
				if(lineaTexto.indexOf(nup)>-1) {
					result=true;
					break;
				}				
			}			
		}
		catch(DAOException e)
		{
			LOGGER.error("No se puede cargar el archivo {},", ExternalPropertyType.FILE_LISTADO_NUP_AUTOGESTION, e);
		}		
		return result;
	}	
}
