package ar.com.santanderrio.obp.servicios.solicitudes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.solicitudes.dao.SolicitudCtaTitDao;
import ar.com.santanderrio.obp.servicios.solicitudes.entities.PersonasVap;
import ar.com.santanderrio.obp.servicios.solicitudes.view.RequestTokenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.RequestVapView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ResponseTokenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ResponseVapView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ValidaAltaView;

@Component
public class SolicitudCtaTitDaoImpl implements SolicitudCtaTitDao{

	@Autowired
	private RestWebClient restWebClient;
	
	@Autowired
	private Environment environment;
	
	@Autowired
    private IatxComm iatxComm;

	@Autowired
	private InversionWSHelper inversionWSHelper;
	
	@Value("${INVERSIONES.FIRMAR:true}")
	private String firmar;
	
	@Value("${INVERSIONES.FIRMA.DATO:Prueba}")
	private String dato;
	
	@Value("${SERVICIO.PREFIJO.CNSPERSFIS}")
	private String servicioCnsPersFis;

	@Value("${SERVICIO.VERSION.CNSPERSFIS}")
	private String versionCnsPersFis;

	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudCtaTitDao.class);
	
	private static final String NOMBRE_WS = "ENRI";
	
	private static final String CANAL="ENRI.CANAL";
	
	private static final String ENTIDAD="0072";
	
	private static final String PROD_CTA_TIT="60";
	
	private static final String SUBPROD_CTA_TIT="0000";
	
	private static final String ENRI_ENCUESTA="ENRI.ENCUESTA.URL";
	
	private static final String ENRI_URL_BASE="ENRI.URL.BASE";
	
	private static final String PERSONA_JURIDICA = "J";
	
	private static String pathToken="Authenticate";
	
	private static String pathVAP="ValidaAltaProducto20";
	
	@Override
	public ValidaAltaView validaAltaProducto(Cliente cliente) {
		String token = this.getToken();
		RequestVapView request= new RequestVapView();
		request = setRequest(request, cliente);
		WebClient service;
		ResponseVapView respuesta= new ResponseVapView();
		ValidaAltaView altaProducto = new ValidaAltaView();
		try {
			service = crearLlamadaAWebService(pathVAP);
			service.header("Authorization", token);
			respuesta=service.post(request,ResponseVapView.class);
			altaProducto = setAltaProducto(respuesta);
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
		return altaProducto;
	}
	
	private ValidaAltaView setAltaProducto(ResponseVapView respuesta) {
		
		String urlEncuesta = environment.getProperty(ENRI_ENCUESTA);
		ValidaAltaView altaProducto = new ValidaAltaView();
		altaProducto.setBloqueado(respuesta.isBloqueado());
		altaProducto.setFechaPerfilado(respuesta.getInformacion().getPersonas().get(0).getFechaPerfilado());
		altaProducto.setFechaVencimiento(respuesta.getFechaVencimiento());
		altaProducto.setMotivo(respuesta.getInformacion().getPersonas().get(0).getMotivo());
		altaProducto.setRiesgo(respuesta.getInformacion().getPersonas().get(0).getRiesgo());
		altaProducto.setUrl(urlEncuesta+respuesta.getTokenEncuesta());
		altaProducto.setUrlBase(environment.getProperty(ENRI_URL_BASE));
		if(respuesta.getInformacion().getPersonas().get(0).isPermiteContinuar()) {
			altaProducto.setHacerEncuesta(false);
		}else {
			altaProducto.setHacerEncuesta(true);
		}
		return altaProducto;
	}

	private RequestVapView setRequest(RequestVapView request, Cliente cliente) {
		request.setCanal(environment.getProperty(CANAL));
		request.setProducto(PROD_CTA_TIT);
		request.setSubproducto(SUBPROD_CTA_TIT);
		request.setEntidad(ENTIDAD);
		request.setOperador(cliente.getUsuarioRacf());
		List<PersonasVap> personas = new ArrayList<PersonasVap>();
		PersonasVap persona = new PersonasVap();
		persona.setCalidadParticipante("TI");
		persona.setFechaNacimiento(cliente.getFechaNacimiento());
		if(cliente.getTipoPersona().equals(PERSONA_JURIDICA)) {
			persona.setIndicadorFirmante00(true);
		}else {
			persona.setIndicadorFirmante00(false);
		}
		persona.setNumeroDocumento(cliente.getDni());
		persona.setTipoDocumento(cliente.getTipoDocumento());
		persona.setTipoPersona(parseTipoPersona(cliente.getTipoPersona()));
		persona.setNup(cliente.getNup());
		personas.add(persona);
		request.setPersonas(personas);
		request = setPreCarga(request, cliente);
		return request;
	}

	private RequestVapView setPreCarga(RequestVapView request, Cliente cliente) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(cliente.getNup());
		requestData.addBodyValue(cliente.getTipoDocumento());
		requestData.addBodyValue(cliente.getDni());
		requestData.addBodyValue("01");
		IatxResponse responseIatx = new IatxResponse();
		try {
			responseIatx = obtenerResponseIatx(servicioCnsPersFis, versionCnsPersFis, requestData);
			if (Integer.parseInt(responseIatx.getData(22)) > 0) {
				request.setNacionalidad(responseIatx.getData(22));
			}
			
			if (Integer.parseInt(responseIatx.getData(23)) > 0) {
				request.setCodigoPaisResidencia(responseIatx.getData(23));
			}
			
		} catch (Exception e) {
			LOGGER.error("Error al setear valores de " + servicioCnsPersFis+versionCnsPersFis + "para invocar a ENRI", e);
		}
		return request;
	}

	private String parseTipoPersona(String tipoPersona) {
		String tipoP = "";
		if(tipoPersona.equals("I")) { tipoP = "F";} else {tipoP = "J";}    
		return tipoP;
	}

	private String getToken() {
		String token="";
		RequestTokenView request= new RequestTokenView();
		request.setCanal(environment.getProperty(CANAL));
		request.setFirma(this.generarFirma());
		WebClient service;
		try {
			service = crearLlamadaAWebService(pathToken);
			ResponseTokenView respuesta=service.post(request,ResponseTokenView.class);
			token = respuesta.getToken();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return token;
	}
	
	private String generarFirma() {
		String firma = "";
		if (Boolean.TRUE.equals(Boolean.parseBoolean(firmar))) {
			try {
				firma = inversionWSHelper.getDatosFirmados(dato);
			} catch (Exception e) {
				LOGGER.error("Error creando firma", e);
			}
		}
		return firma;
	}

	
	private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

		WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
		service.accept(MediaType.APPLICATION_JSON);
        service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        service.path(pathDeConsulta);
		return service;
	}
	
	
	 public IatxResponse obtenerResponseIatx(String nombreServicio, String version, IatxRequestData data)
	            throws DAOException {
	        IatxResponse iatxResponse = new IatxResponse();
	        try {
	            IatxRequest request = buildIatxRequest(nombreServicio, version, data);
	            iatxResponse = ejecutar(request);
	            return iatxResponse;
	        } catch (IatxException e) {
	            LOGGER.error(e.getMessage(), e);
	            throw new DAOException(e);
	        } catch (RuntimeException e) {
	            LOGGER.error(e.getMessage(), e);
	            throw new DAOException(e, iatxResponse.getErrorMessage());
	        }
	    }
	 
	 public IatxRequest buildIatxRequest(String nombreServicio, String version, IatxRequestData data) {
	        IatxRequest iatxRequest = new IatxRequest(nombreServicio, version);
	        iatxRequest.setData(data);
	        return iatxRequest;
	    }
	 
	 public IatxResponse ejecutar(IatxRequest request) throws IatxException {
	        return iatxComm.exec(request);
	    }


}
