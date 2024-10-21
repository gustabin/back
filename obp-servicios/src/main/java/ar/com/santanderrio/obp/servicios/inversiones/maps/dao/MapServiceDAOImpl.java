package ar.com.santanderrio.obp.servicios.inversiones.maps.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaAgendamientoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatoConsultaAgedaRequest;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosFondosAgendEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondosAgendamientoResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.BajaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaFeriadosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaFeriadosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.DatosFeriadosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.Feriados;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.FondosRescatarSuscribirEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ServicioMapsException;

@Component
public class MapServiceDAOImpl implements MapServiceDAO {

	/** The Constant AGD. */
	private static final String AGD = "AGD";

	/** The Constant AGDFH. */
	private static final String AGDFH = "AGDFH";

	/** The rest web client. */
	@Autowired
	private RestWebClient restWebClient;

	/** The Constant NOMBRE_WS. */
	private static final String NOMBRE_WS = "INVERSIONES.MAPS";
	
	/** The Constant SEGMENTO_RETAIL. */
	private static final String SEGMENTO_RETAIL = "RTL";
	
	/** The Constant SUBCANAN_BANCA_RETAIL. */
	private static final String SUBCANAN_BANCA_RETAIL = "0099";

	/** The Constant CANAL_BANCA_RETAIL */
	private static final String CANAL_BANCA_RETAIL = "04";
	
	/** The Constant AR */
	private static final String AR = "AR";

	/** The inversion WS helper. */
	@Autowired
	private InversionWSHelper inversionWSHelper;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MapServiceDAOImpl.class);

	/** The path Alta Adhesion. */
	private static String pathAltaAdhesionMaps = "/AltaAdhesion/";

	/** The path Alta Adhesion. */
	private static String pathConsultaAdhesionMaps = "/ConsultaAdhesion/";

	private static String pathBajaAdhesionMaps = "/BajaAdhesion/";

	/** The Constant NOMBRE_WS_MW. */
	private static final String NOMBRE_WS_MW = "ORDENES.TENENCIAS";

	private static final String pathConsultaFeridos = "/ConsultaDiasNoHabiles";

	/** The dato. */
	@Value("${INVERSIONES.MAPS.FIRMA.DATO:Prueba}")
	private String dato;

	/** The firmar. */
	@Value("${INVERSIONES.MAPS.FIRMAR:true}")
	private String firmar;
	
	/** The Class SesionCliente */
	@Autowired
	protected SesionCliente sesionCliente;
	
	/** The path tenencia valuada detalle fondo online. */
	private static String pathConsultaFondosAgendamiento = "/ConsultaFondosAGD/";

	@Override
	public AltaAdhesionMapsResponse altaAdhesionMaps(AltaAdhesionMapsRequestEntity request) throws DAOException {
		request.setFirma(generarFirma());
		request.setDato(dato);
		WebClient service = crearLlamadaAWebService(pathAltaAdhesionMaps);
		AltaAdhesionMapsResponse response = new AltaAdhesionMapsResponse();
		Response respuesta = service.post(request);

		Object entity = respuesta.getEntity();
		InputStream is = InputStream.class.cast(entity);
		if (is != null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			try {
				response = mapper.readValue(IOUtils.toString(is, "UTF-8"), AltaAdhesionMapsResponse.class);
				if (request.getDatos().getIdServicio() != null) {
					if (request.getDatos().getIdServicio().equals(AGD)
							|| request.getDatos().getIdServicio().equals(AGDFH)) {
						FormularioControl datos = response.getDatos();
						List<String> feriados = consultaFeriados(crearRequestFeri());
						datos.setFeriados(feriados);
						if(request.getDatos().getIdServicio().equals(AGD)) {
							FondosRescatarSuscribirEntity fondosResSusc = consultaFondosAgendamiento();
							datos.setPuedeRescatar(fondosResSusc.getPuedeRescatar());
							datos.setPuedeSuscribir(fondosResSusc.getPuedeSuscribir());
						}
					}
				}
			} catch (JsonParseException e) {
				throw new DAOException();
			} catch (JsonMappingException e) {
				throw new DAOException();
			} catch (IOException e) {
				throw new DAOException();
			} catch (Exception e) {
				throw new DAOException();
			}
		}
		if (response.getCodigo() != 0 && response.getCodigo() != 2) {
			LOGGER.error(response.getMensaje());
			throw new ServicioMapsException();
		}
		if (response.getCodigo() == 2) {
			LOGGER.info("Error de validacion en uno de los controles {}", response.getMensaje());
		}
		return response;
	}
	
	
	public FondosRescatarSuscribirEntity consultaFondosAgendamiento() {
		ConsultaAgendamientoRequestEntity request = armarRequestConsultaFondos();
		FondosAgendamientoResponseEntity response = new FondosAgendamientoResponseEntity();
		FondosRescatarSuscribirEntity resp = new FondosRescatarSuscribirEntity();;

		try {
			WebClient consultaAgendamientoService = crearLlamadaAWebService(pathConsultaFondosAgendamiento);
			response = consultaAgendamientoService.post(request, FondosAgendamientoResponseEntity.class);
			if (response == null || response.getCodigo() != 0) {
				throw new DAOException();
			}
			List<String> puedeRescatar= new ArrayList<String>();
			List<String> puedeSuscribir= new ArrayList<String>();
			if (!response.getDatos().isEmpty()) {
				for (DatosFondosAgendEntity fondo : response.getDatos()) {
					if(fondo.isPuedeRescatar()) {
						puedeRescatar.add(fondo.getCodigoFondo());
					}
					if(fondo.isPuedeSuscribir()) {
						puedeSuscribir.add(fondo.getCodigoFondo());
					}
				}
				resp.setPuedeRescatar(puedeRescatar);
				resp.setPuedeSuscribir(puedeSuscribir);
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	private ConsultaAgendamientoRequestEntity armarRequestConsultaFondos() {
		ConsultaAgendamientoRequestEntity request = new ConsultaAgendamientoRequestEntity();
		DatoConsultaAgedaRequest datos = new DatoConsultaAgedaRequest();
		datos.setCodigoFondo("");
		request.setDatos(datos);
		request.setFirma(generarFirma());
		request.setDato(dato);

		return request;
	}

	@Override
	public ConsultaAdhesionMapsResponse consultaAdhesionMaps(ConsultaAdhesionRequestEntity request)
			throws DAOException {
		request.setFirma(generarFirma());
		request.setDato(dato);
		WebClient service = crearLlamadaAWebService(pathConsultaAdhesionMaps);
		ConsultaAdhesionMapsResponse response = new ConsultaAdhesionMapsResponse();
		Response respuesta = service.post(request);

		Object entity = respuesta.getEntity();
		InputStream is = InputStream.class.cast(entity);
		if (is != null) {

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			try {
				response = mapper.readValue(IOUtils.toString(is, "UTF-8"), ConsultaAdhesionMapsResponse.class);
			} catch (JsonParseException e) {
				throw new DAOException();
			} catch (JsonMappingException e) {
				throw new DAOException();
			} catch (IOException e) {
				throw new DAOException();
			} catch (Exception e) {
				throw new DAOException();
			}
		}
		if (response.getCodigo() != 0 && response.getCodigo() != 2) {
			LOGGER.error(response.getMensaje());
			throw new DAOException();
		}
		if (response.getCodigo() == 2) {
			LOGGER.info("Error de validacion en uno de los controles {}", response.getMensaje());
		}
		return response;
	}

	/**
	 * genera la firma para llamar a los servicios de Maps.
	 *
	 * @return the string
	 */
	private String generarFirma() {
		String firma = "";
		if (Boolean.TRUE.equals(Boolean.parseBoolean(firmar))) {
			try {
				firma = inversionWSHelper.getDatosFirmados(dato);
			} catch (Exception e) {
				LOGGER.error("Error creando firma para invocar a Map Service. ", e);
			}
		}
		return firma;
	}

	/**
	 * @param pathDeConsulta
	 * @return
	 * @throws DAOException
	 */
	private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

		WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
		service.accept(MediaType.APPLICATION_JSON);
		service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		service.path(pathDeConsulta);
		return service;
	}

	@Override
	public ConsultaAdhesionMapsResponse bajaAdhesionMaps(BajaAdhesionRequestEntity request) throws DAOException {
		request.setFirma(generarFirma());
		request.setDato(dato);
		WebClient service = crearLlamadaAWebService(pathBajaAdhesionMaps);
		ConsultaAdhesionMapsResponse response = new ConsultaAdhesionMapsResponse();
		Response respuesta = service.post(request);

		Object entity = respuesta.getEntity();
		InputStream is = InputStream.class.cast(entity);
		if (is != null) {

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			try {
				response = mapper.readValue(IOUtils.toString(is, "UTF-8"), ConsultaAdhesionMapsResponse.class);
			} catch (JsonParseException e) {
				throw new DAOException();
			} catch (JsonMappingException e) {
				throw new DAOException();
			} catch (IOException e) {
				throw new DAOException();
			} catch (Exception e) {
				throw new DAOException();
			}
		}
		return response;
	}

	@Override
	public List<String> consultaFeriados(ConsultaFeriadosRequestEntity request) throws DAOException {
		request.setFirma(generarFirma());
		request.setDato(dato);
		WebClient service = crearLlamadaAWebServiceMW(pathConsultaFeridos);
		ConsultaFeriadosResponse response = new ConsultaFeriadosResponse();
		List<String> listFeriados = new ArrayList();
		response = service.post(request, ConsultaFeriadosResponse.class);
		try {
			for (Feriados feriados : response.getDatos().getListaFeriados()) {
				listFeriados.add(feriados.getFecha().substring(0, 10));
			}
		} catch (Exception e) {
			throw new DAOException();
		}
		return listFeriados;
	}

	/**
	 * @param pathDeConsulta
	 * @return
	 * @throws DAOException
	 */
	private WebClient crearLlamadaAWebServiceMW(String pathDeConsulta) throws DAOException {

		WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS_MW);
		service.accept(MediaType.APPLICATION_JSON);
		service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		service.path(pathDeConsulta);
		return service;
	}
	
	public ConsultaFeriadosRequestEntity crearRequestFeri() {
		ConsultaFeriadosRequestEntity request = new ConsultaFeriadosRequestEntity();
		DatosFeriadosEntity datos = new DatosFeriadosEntity();
		datos.setFiltroPais(AR);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario(sesionCliente.getResumenCliente().getUsuarioRacf());
		request.setCanal(CANAL_BANCA_RETAIL);
		request.setDatos(datos);
		request.setSubCanal(SUBCANAN_BANCA_RETAIL);
		
		return request;
	}
}


// Análisis del archivo MapServiceDAOImpl.java

// El archivo MapServiceDAOImpl.java es la implementación de la interfaz MapServiceDAO que se encarga de interactuar con la capa de datos para realizar operaciones relacionadas con la adhesión y consulta de feriados.

// Observaciones

// La clase utiliza la anotación @Component para indicar que es un componente de Spring.
// La clase utiliza la anotación @Autowired para inyectar dependencias, como RestWebClient y InversionWSHelper.
// La clase define varios métodos que implementan la lógica de negocio para realizar operaciones de adhesión y consulta de feriados.
// La clase utiliza la biblioteca Jackson para serializar y deserializar objetos JSON.
// La clase utiliza la biblioteca Apache CXF para realizar llamadas a servicios web.
// Preguntas para avanzar

// ¿Cuál es el propósito de la clase RestWebClient y cómo se utiliza en la implementación de MapServiceDAOImpl?
// ¿Cuál es el propósito de la clase InversionWSHelper y cómo se utiliza en la implementación de MapServiceDAOImpl?
// ¿Cómo se manejan las excepciones en la implementación de MapServiceDAOImpl?