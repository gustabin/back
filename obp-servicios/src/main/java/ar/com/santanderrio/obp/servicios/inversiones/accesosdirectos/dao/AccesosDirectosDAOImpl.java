package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.dao;

import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoProductoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.DatosAccesoDirectoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;

@Component
public class AccesosDirectosDAOImpl implements AccesosDirectosDAO{

	@Autowired
	private RestWebClient restWebClient;
	
	@Autowired
	protected SesionCliente sesionCliente;
	
	@Autowired
	private InversionWSHelper inversionWSHelper;
	
	@Value("${FONDOS.CANALTIPO}")
	private String canalTipo;

	/** The canal id. */
	@Value("${FONDOS.CANALID}")
	private String canalId;

	/** The canal version. */
	@Value("${FONDOS.CANALVERSION}")
	private String canalVersion;

	/** The subcanal tipo. */
	@Value("${FONDOS.SUBCANALTIPO}")
	private String subcanalTipo;

	/** The subcanal id. */
	@Value("${FONDOS.SUBCANALID}")
	private String subcanalId;

	/** The dato. */
	@Value("${INVERSIONES.FIRMA.DATO:Prueba}")
	private String dato;

	/** The firmar. */
	@Value("${INVERSIONES.FIRMAR:true}")
	private String firmar;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccesosDirectosDAOImpl.class);
	
	private static final String NOMBRE_WS = "INVERSIONES.TENENCIAS";
	
	private static String pathObtenerAccesoDirecto="ObtenerAccesoDirecto";
	
	
	
	

	
	@Override
	public AccesoDirectoProductoEntity obtenerAccesoDirecto(String nup) {
		
		
		
		// TODO Auto-generated method stub
		AccesoDirectoRequestEntity request = new AccesoDirectoRequestEntity();
		AccesoDirectoResponseEntity respuesta = new AccesoDirectoResponseEntity();
		
		try {
			WebClient service=crearLlamadaAWebService(pathObtenerAccesoDirecto);
			request=armaRequest(request,nup);
			respuesta = service.post(request,AccesoDirectoResponseEntity.class);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			respuesta = null;
			LOGGER.error("ERROR all invocar al servicio ObtenerAccesoDirecto");
			e.printStackTrace();
		}
		
		AccesoDirectoProductoEntity accesoDirecto = new AccesoDirectoProductoEntity();
		
		if(respuesta != null) {
			accesoDirecto.setProducto(respuesta.getDatos().getAccesoDirecto());
		}else {
			accesoDirecto = null;
		}
		
		return accesoDirecto;
	}
	

	private AccesoDirectoRequestEntity armaRequest(AccesoDirectoRequestEntity request,String nup) {
		
		DatosAccesoDirectoRequestEntity datos=new DatosAccesoDirectoRequestEntity();
		
		request.setFirma(generarFirma());
		request.setDato("Prueba");
		request.setDatosFirmados(dato);
		datos.setNup(nup);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario(sesionCliente.getResumenCliente().getUsuarioRacf());
		request.setDatos(datos);
		request.setCanal(canalTipo);
		request.setSubCanal(subcanalId);
		
		return request;
		
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

	
	private String generarFirma() {
		String firma = "";
		if (Boolean.TRUE.equals(Boolean.parseBoolean(firmar))) {
			try {
				firma = inversionWSHelper.getDatosFirmados(dato);
			} catch (Exception e) {
				LOGGER.error("Error creando firma para invocar a. ", e);
			}
		}
		return firma;
	}
}
