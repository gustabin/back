/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.dao;

import java.io.UnsupportedEncodingException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ConfirmacionOrdenReq;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ConfirmacionOrdenResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ConsultaPerfilInversorReq;
import ar.com.santanderrio.obp.generated.webservices.inversiones.DatosEvaluacionRiesgo;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EriException;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EriService;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EvaluacionDeRiesgoReq;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EvaluacionDeRiesgoResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ParametroDatosConfirmacionOrden;
import ar.com.santanderrio.obp.generated.webservices.inversiones.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.DatosPerfilInversorResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorResponse;


/**
 * The Class InversionWSDAOImpl.
 * 
 * @author marcelo.ruiz
 */
@Component("InversionWSDAO")
public class InversionWSDAOImpl implements InversionDAO {

	/** The Constant CONSULTA_PERFIL_INVERSOR. */
	private static final String CONSULTA_PERFIL_INVERSOR = "/";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(InversionWSDAOImpl.class);

	/** The Constant DATOS_FIRMADOS. */
	private static final String DATOS_FIRMADOS = "santanderinversion";

	/**VARIABLES DE PERFIL DE INVERSOR REST */

	private static final String JKS_PERFIL_INVERSOR = "PERFILINVERSOR";
	
	/** The Constant MENSAJE_ERROR. */
    private static final String MENSAJE_ERROR = "Error llamando al servicio de Consulta de Perfil Inversor";
    
    /** The Constant NOMBRE_WS. */
    private static final String NOMBRE_WS = "PERFIL.INVERSOR";
    
    /** The firmar. */
    @Value("${ORDENES.FIRMAR:true}")
    private String firmar;
    
    /** The dato. */
    @Value("${ORDENES.FIRMA.DATO:Prueba}")
    private String dato;
    
    /** The inversion WS helper. */
    @Autowired
    private InversionWSHelper inversionWSHelper;

	/** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;
    /**FIN CAMBIO VARIABLES PERFIL REST */ 

	/** The ws soap. */
	@Autowired
	private GestionarWS<EriService> wsSoap;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** The sign. */
	@Autowired
	private Sign sign;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO#
	 * evaluacionDeRiesgo(ar.com.santanderrio.obp.generated.webservices.
	 * inversiones.DatosEvaluacionRiesgo)
	 */
	@Override
	public EvaluacionDeRiesgoResponse evaluacionDeRiesgo(DatosEvaluacionRiesgo datosEvaluacionRiesgo)
			throws DAOException {
		EriService cliente = null;
		EvaluacionDeRiesgoResponse response = null;

		try {
		EvaluacionDeRiesgoReq evaluacionDeRiesgoReq = generarFirmaEvaluacionDeRiesgoReq(datosEvaluacionRiesgo.getNup());
		evaluacionDeRiesgoReq.setParametroDatos(datosEvaluacionRiesgo);

			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.evaluacionDeRiesgo(evaluacionDeRiesgoReq);
			} catch (EriException e) {
				LOGGER.error("Error al invocar al web service ERI. {}.", e.getFaultInfo().getMensaje(), e);
				throw new DAOException(e);
			}
		} catch (UnsupportedEncodingException u) {
			LOGGER.error("Error al invocar al web service ERI. {}.", u);
			throw new DAOException(u);
		} finally {
			wsSoap.liberarPort(cliente);
			if (response == null) {
				LOGGER.debug("Error al invocar al web service ERI. ");
				throw new DAOException();
			}
		}

		try {
			Mensaje mensajeDisclaimer = new Mensaje();
			mensajeDisclaimer.setCantidadDeDisclaimers(0);
			if (!StringUtils.isBlank(response.getDisclaimer().toString())) {
				mensajeDisclaimer = JaxbUtils.transformarXmlAObject(response.getDisclaimer().toString(), Mensaje.class);
				if (!StringUtils.isBlank(response.getDisclaimerCumplimiento().toString())) {
					mensajeDisclaimer.add(JaxbUtils
							.transformarXmlAObject(response.getDisclaimerCumplimiento().toString(), Mensaje.class));
				}
			}
			response.setMensaje(mensajeDisclaimer);
		} catch (JAXBException e) {
			LOGGER.error("Error recuperando disclaimer. {}.", e);
			throw new DAOException(e);
		}

		return response;
	}

	/**
	 * Genero la firma para el llamado de evaluacion de riesgo problemas de
	 * bouncy castle, va dura la firma.
	 *
	 * @return the evaluacion de riesgo req
	 * @throws DAOException 
	 * @throws UnsupportedEncodingException 
	 */
	private EvaluacionDeRiesgoReq generarFirmaEvaluacionDeRiesgoReq(String nup) throws UnsupportedEncodingException, DAOException {
		EvaluacionDeRiesgoReq evaluacionDeRiesgoReq = new EvaluacionDeRiesgoReq();
		evaluacionDeRiesgoReq.setFirmaDatosDentro("S");
		evaluacionDeRiesgoReq.setFirmaDatosFirmados(DATOS_FIRMADOS);
		evaluacionDeRiesgoReq.setFirmaFormato("B64");
		String firma = new String(sign.buildB64Signature(nup.getBytes(appEncoding), JKS_PERFIL_INVERSOR, true));
		evaluacionDeRiesgoReq.setFirmaHash(firma);
		//evaluacionDeRiesgoReq.setFirmaHash(
		//		"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");
		return evaluacionDeRiesgoReq;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO#
	 * confirmacionOrden(ar.com.santanderrio.obp.generated.webservices.
	 * inversiones.ParametroDatosConfirmacionOrden)
	 */
	@Override
	public ConfirmacionOrdenResponse confirmacionOrden(ParametroDatosConfirmacionOrden datosConfirmacionOrden)
			throws DAOException {
		EriService cliente = null;
		ConfirmacionOrdenResponse response = null;

		try {
		ConfirmacionOrdenReq confirmacionOrdenReq = generarFirmaConfirmarOrden(datosConfirmacionOrden.getNroOperacion());
		confirmacionOrdenReq.setParametroDatos(datosConfirmacionOrden);
		
			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.confirmacionOrden(confirmacionOrdenReq);
			} catch (EriException e) {
				LOGGER.error("Error al invocar al web service Confirmar orden. {}.", e.getFaultInfo().getMensaje(), e);
				throw new DAOException(e);
			}
		} catch (UnsupportedEncodingException u) {
			LOGGER.error("Error al invocar al web service Confirmar orden generando firma jks", u);
			throw new DAOException(u);
		} finally {
			wsSoap.liberarPort(cliente);
			if (response == null) {
				LOGGER.debug("Error al invocar al web service Confirmar orden. ");
				throw new DAOException();
			}
		}

		return response;
	}

	/**
	 * Genero la firma para el llamado de confirmar orden problemas de bouncy
	 * castle, va dura la firma.
	 *
	 * @return the confirmacion orden req
	 * @throws DAOException 
	 * @throws UnsupportedEncodingException 
	 */
	private ConfirmacionOrdenReq generarFirmaConfirmarOrden(String nroOperacion) throws UnsupportedEncodingException, DAOException {
		ConfirmacionOrdenReq confirmacionOrdenReq = new ConfirmacionOrdenReq();
		confirmacionOrdenReq.setFirmaDatosDentro("S");
		confirmacionOrdenReq.setFirmaDatosFirmados(DATOS_FIRMADOS);
		confirmacionOrdenReq.setFirmaFormato("B64");
		String firma = new String(sign.buildB64Signature(nroOperacion.getBytes(appEncoding), JKS_PERFIL_INVERSOR, true));
		confirmacionOrdenReq.setFirmaHash(firma);
		//confirmacionOrdenReq.setFirmaHash(
		//		"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");
		return confirmacionOrdenReq;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO#
	 * consultaPerfilInversor(ar.com.santanderrio.obp.generated.webservices.
	 * inversiones.ParametroDatos)
	 */
	@Override
	public PerfilInversorResponse consultaPerfilInversor(PerfilInversorRequestEntity request)
			throws DAOException {
		request.setDato(dato);
		request.setFirma(generarFirma());
			
		PerfilInversorResponse rta = new PerfilInversorResponse();
		rta.setDatos(new DatosPerfilInversorResponse());
		try {
			WebClient service = crearLlamadaAWebService(CONSULTA_PERFIL_INVERSOR);
			rta = service.post(request,PerfilInversorResponse.class);
			if (rta == null) {
				LOGGER.error("Error al invocar Consulta de Perfil Inversor: rta null");
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(MENSAJE_ERROR + " Error Response", e);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(MENSAJE_ERROR + " ProcessingException", e);
            throw new DAOException();
		} catch (RuntimeException e) {
			LOGGER.error(MENSAJE_ERROR + " RuntimeException", e);
			throw new DAOException();
		}
		return rta;
	}

	/**
	 * Crear llamada A web service.
	 *
	 * @param pathDeConsulta
	 *            the path de consulta
	 * @return the web client
	 * @throws DAOException
	 *             the DAO exception
	 */
    private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

        WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
        service.accept(MediaType.APPLICATION_JSON);
        service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        service.path(pathDeConsulta);

        return service;
    }

	/**
	 * Genero la firma para el llamado de consultar perfil inversor problemas de
	 * bouncy castle, va dura la firma.
	 *
	 * @return the evaluacion de riesgo req
	 * @throws DAOException 
	 * @throws UnsupportedEncodingException 
	 */
	private ConsultaPerfilInversorReq generarFirmaConsultarPerfilInversor(String nup) throws UnsupportedEncodingException, DAOException {
		ConsultaPerfilInversorReq evaluacionDeRiesgoReq = new ConsultaPerfilInversorReq();
		evaluacionDeRiesgoReq.setFirmaDatosDentro("S");
		evaluacionDeRiesgoReq.setFirmaDatosFirmados(DATOS_FIRMADOS);
		evaluacionDeRiesgoReq.setFirmaFormato("B64");
		String firma = new String(sign.buildB64Signature(nup.getBytes(appEncoding), JKS_PERFIL_INVERSOR, true));
		evaluacionDeRiesgoReq.setFirmaHash(firma);
		//evaluacionDeRiesgoReq.setFirmaHash(
		//		"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");
		return evaluacionDeRiesgoReq;
	}

	/**
     * genera la firma para llamar a los servicios de Perfil del Inversor.
     *
     * @return the string
     */
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
}
