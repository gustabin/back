package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.dao;

import java.util.ArrayList;
import java.util.List;

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
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.DatosDisponiblesRTFEntity;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.DisponiblesRTFEntity;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.InfoRTFCuentaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.InfoRTFEntity;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.ResponsePDFEntity;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.ResponseRTFEntity;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResponsePdfView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenTrimestralView;

@Component
public class ResumenTrimestralDAOImpl implements ResumenTrimestralDAO{

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

	private static final Logger LOGGER = LoggerFactory.getLogger(ResumenTrimestralDAOImpl.class);
	
	private static final String NOMBRE_WS = "INVERSIONES.MAPS";
	
	private static String pathObtenerDisponibles="ObtenerRTFDisponiblesPorCliente";
	
	private static String pathObtenerPdf="ObtenerPdfPorCuentaRTF";
	
	
	

	
	@Override
	public ResumenTrimestralView obtenerDisponibles(String nup) {
		// TODO Auto-generated method stub
		ResumenTrimestralView resumen=new ResumenTrimestralView();
		DisponiblesRTFEntity request=new DisponiblesRTFEntity();
		
		try {
			WebClient service=crearLlamadaAWebService(pathObtenerDisponibles);
			request=armaRequest(request,nup);
			ResponseRTFEntity respuesta=service.post(request,ResponseRTFEntity.class);
			resumen=parseResponse(respuesta,resumen);
			
			
				
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//resumen.setTest("Hola desde  Dao");
		
		return resumen;
	}
	
	@Override
	public ResponsePdfView obtenerPdf(String idPdf, String nup) {
		// TODO Auto-generated method stub
		DisponiblesRTFEntity request=new DisponiblesRTFEntity();
		ResponsePDFEntity respuesta=new ResponsePDFEntity();
		try {
			WebClient service=crearLlamadaAWebService(pathObtenerPdf);
			request=armaRequest(request,idPdf,nup);
			respuesta=service.post(request, ResponsePDFEntity.class);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResponsePdfView infoPdf=new ResponsePdfView();
		infoPdf.setDatos(respuesta.getDatos());

		return infoPdf;
	}
	
	private ResumenTrimestralView parseResponse(ResponseRTFEntity respuesta, ResumenTrimestralView resumen) {
		
		List<InfoRTFCuentaEntity> listaCuentas=new ArrayList<InfoRTFCuentaEntity>();
		List<InfoRTFEntity> listaReportes=new ArrayList<InfoRTFEntity>();
		
		for(InfoRTFCuentaEntity item:respuesta.getDatos().getListaCuentas()) {
			
			listaCuentas.add(item);
			
		}
		
		for(InfoRTFEntity item:respuesta.getDatos().getListaRTF()) {
			item.setTieneMov(true);
			if(item.getDescripcion().equals("ENERO MARZO")) {
				item.setIdPeriodo(1);
			}else if(item.getDescripcion().equals("ABRIL JUNIO")) {
				item.setIdPeriodo(2);
			}else if(item.getDescripcion().equals("JULIO SEPTIEMBRE")) {
				item.setIdPeriodo(3);
			}
			else if(item.getDescripcion().equals("OCTUBRE DICIEMBRE")) {
				item.setIdPeriodo(4);
			}
			listaReportes.add(item);
			
		}
		resumen.setListaRTF(listaReportes);
		resumen.setListaCuentas(listaCuentas);
		resumen.setUltAnio(respuesta.getDatos().getUltAnio());
		resumen.setUltPeriodo(respuesta.getDatos().getUltPeriodo());
		return resumen;
	}

	private DisponiblesRTFEntity armaRequest(DisponiblesRTFEntity request,String nup) {
		
		DatosDisponiblesRTFEntity datos=new DatosDisponiblesRTFEntity();

		
		datos.setNup(nup);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario("system");
		request.setCanal(canalTipo);
		request.setSubCanal(subcanalId);
		request.setDatos(datos);
		request.setDato(dato);
		request.setFirma(generarFirma());
		return request;
		
	}
	
	private DisponiblesRTFEntity armaRequest(DisponiblesRTFEntity request, String idPdf, String nup) {
		
		DatosDisponiblesRTFEntity datos=new DatosDisponiblesRTFEntity();
		
		datos.setNup(nup);
		datos.setId(idPdf);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario("system");
		request.setCanal(canalTipo);
		request.setSubCanal(subcanalId);
		request.setDatos(datos);
		request.setDato(dato);
		request.setFirma(generarFirma());
		return request;
		
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

	

}
