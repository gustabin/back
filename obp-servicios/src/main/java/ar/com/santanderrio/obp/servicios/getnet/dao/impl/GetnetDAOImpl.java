package ar.com.santanderrio.obp.servicios.getnet.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.servicios.getnet.exception.GetnetEmailValidationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.getnet.dao.GetnetDAO;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetAdhesionDTO;
import ar.com.santanderrio.obp.servicios.getnet.entities.ActualizacionConsultaMarcaAdhesionEntityOut;
import ar.com.santanderrio.obp.servicios.getnet.entities.ActualizacionConsultaMarcaAdhesionFinal;
import ar.com.santanderrio.obp.servicios.getnet.entities.ConsultaSitImpositivaOutEntity;
import ar.com.santanderrio.obp.servicios.getnet.entities.GetnetInEntity;
import ar.com.santanderrio.obp.servicios.getnet.entities.GetnetOutEntity;
import ar.com.santanderrio.obp.servicios.getnet.exception.GetnetValidationException;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class GetnetDAOImpl.
 */
@Component
public class GetnetDAOImpl extends IatxBaseDAO implements GetnetDAO {

	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GetnetDAOImpl.class);

    /** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;
    
	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;
	
    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;
    
    /** The Constant NOMBRE_WS_TEMPORAL. */
	private static final String NOMBRE_WS_CHECK = "GETNET.CHECK";
	
	/** The Constant NOMBRE_WS. */
	private static final String NOMBRE_WS = "GETNET";
	
	/** The PATH_CHECK. */
	private static final String PATH_CHECK = "/check/obp";
	
	/** The PATH_PERSONS. */
	private static final String PATH_PERSONS = "/persons/";
	
	/** The CODIGO_OK_GET. */
	private static final Integer CODIGO_OK_GET = 200;
	
	/** The CODIGO_POST. */
	private static final Integer CODIGO_OK_POST = 201;
	
	/** The MARCA_RECUPERA_UNA_SIT_IMP. */
	private static final String MARCA_RECUPERA_UNA_SIT_IMP = "S";
	
	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;
	
	/** The Constant TIMEOUT_EXCEPTION. */
	private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envi� la transacci�n al CICS pero no se recibi� respuesta.�";

	/** The KEY_EMAIL_EXISTENTE. */
	private static final String KEY_EMAIL_EXISTENTE = "email_already_exists";

	/** The KEY_VALIDATION_ERROR. */
	private static final String KEY_VALIDATION_ERROR = "person_validation";

	/** The KEY_EMAIL_VALIDATION_ERROR. */
	private static final String KEY_EMAIL_VALIDATION_ERROR = "email_person_validation";

	/** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;
    
    /** GETNET ID SEGURIDAD. */
    @Value("${GETNET.IDSEGURIDAD}")
    private String getnetIdSeguridad;
    
	/** The reporte file adhesion. */
	@Value("classpath:/report/getnet/ComprobanteAdhesionGetnet.jasper")
	private Resource reporteAdhesionGetnet;
	
	/** The Constant NOMBRE_ARCHIVO_ADHESION. */
	private static final String NOMBRE_ARCHIVO_ADHESION = "Comprobante_Adhesion_Getnet";

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo getnet. */
	@Value("classpath:/report/comprobantes/logo_getnet.png")
	private Resource logoGetnet;
	
	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";
	
	/** The Constant PARAM_LOGO_CABECERA. */
	private static final String PARAM_LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant PARAM_LOGO_CIERRE. */
	private static final String PARAM_LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant PARAM_LOGO_GETNET. */
	private static final String PARAM_LOGO_GETNET = "LOGO_GETNET";
	
	/** The Constant PARAM_EMAIL. */
	private static final String PARAM_EMAIL = "EMAIL";

	/** The Constant PARAM_CELULAR. */
	private static final String PARAM_CELULAR = "CELULAR";

	/** The Constant PARAM_DESCRIPCION_CUENTA. */
	private static final String PARAM_DESCRIPCION_CUENTA = "DESCRIPCION_CUENTA";
	
	/** The Constant PARAM_NUMERO_CUENTA. */
	private static final String PARAM_NUMERO_CUENTA = "NUMERO_CUENTA";
	
	/** The Constant PARAM_NOMBRE_FANTASIA. */
	private static final String PARAM_NOMBRE_FANTASIA = "NOMBRE_FANTASIA";

	/** The Constant PARAM_ACTIVIDAD. */
	private static final String PARAM_ACTIVIDAD = "ACTIVIDAD";

	/** The Constant PARAM_INGRESOS. */
	private static final String PARAM_INGRESOS = "INGRESOS";

	/** The Constant PARAM_FECHA_COMPROBANTE. */
	private static final String PARAM_FECHA_COMPROBANTE = "FECHA_COMPROBANTE";

	/** The Constant NUMERO COMPROBANTE. */
	private static final String PARAM_NUMERO_COMPROBANTE = "NUMERO_COMPROBANTE";
	
    /**
     * Chequea si el nup esta habilitado.
     * @param nup
	 *            the nup
     * @return the getnet out entity
     * @throws DAOException 
     */
	@Override
	public GetnetOutEntity check(String nup) throws DAOException {
		LOGGER.info("GetNetDAO: Inicio del metodo check");
		GetnetOutEntity respuesta = new GetnetOutEntity();
		try {
			WebClient getNetClient = restWebClient.obtenerClienteRest(NOMBRE_WS_CHECK);
			getNetClient.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
				.accept(MediaType.APPLICATION_JSON);
			getNetClient.path(PATH_CHECK);
			getNetClient.query("nup", nup);
			respuesta = getNetClient.get(GetnetOutEntity.class);
			Integer codigoRespuesta = getNetClient.getResponse().getStatus();
			if (!CODIGO_OK_GET.equals(codigoRespuesta)) {
				LOGGER.error("Error llamando a la API de GetNet. El codigo de error es: " + codigoRespuesta + ".");
			}
			if (respuesta == null) {
				LOGGER.error("Error llamando a la API de GetNet. La respuesta es nula.");
			}
		} catch (Exception e) {
			LOGGER.error("Error llamando a la API de GetNet.");
		}
		return respuesta;
	}
    
	/**
     * Recupera el estado del cliente.
     * @param nup
	 *            the nup
     * @return the getnet out entity
     * @throws DAOException 
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<GetnetOutEntity> getPersons(String nup) throws DAOException {
		LOGGER.info("GetNetDAO: Inicio del metodo persons");
		List<GetnetOutEntity> respuesta = new ArrayList<GetnetOutEntity>();
		try {
			Credential credential = obtenerCredencialSeguridad();
			String header = credential.getUsuario();
            String password = credential.getPassword();
			WebClient getNetClient = restWebClient.obtenerClienteRest(NOMBRE_WS);
			getNetClient.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
				.accept(MediaType.APPLICATION_JSON);
			getNetClient.header(header, password);
			getNetClient.path(PATH_PERSONS);
			getNetClient.query("nup", nup);
			respuesta = (List<GetnetOutEntity>) getNetClient.getCollection(GetnetOutEntity.class);
			Integer codigoRespuesta = getNetClient.getResponse().getStatus();
			if (!CODIGO_OK_GET.equals(codigoRespuesta)) {
				LOGGER.error("Error llamando a la API de GetNet. El codigo de error es: " + codigoRespuesta + ".");
			}
			if (respuesta == null) {
				LOGGER.error("Error llamando a la API de GetNet. La respuesta es nula.");
			}
		} catch (Exception e) {
			LOGGER.error("Error llamando a la API de GetNet.");
			respuesta = null;
		}
		return respuesta;
	}
	
	/**
	 * consulta la situacion impositiva del cliente.
     * @param cliente
	 *            the cliente
     * @return the consulta situacion impositiva out entity
     * @throws DAOException 
	 * @throws IatxException 
	 */
	@Override
	public ConsultaSitImpositivaOutEntity consultarSitImpositiva(Cliente cliente)
			throws DAOException, IatxException {
		String servicio = "CNSINFIMPO";
		String version = "110";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ConsultaSitImpositivaOutEntity consultaSitImpositivaOutEntity = new ConsultaSitImpositivaOutEntity();

		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSINFIMPO(cliente);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				consultaSitImpositivaOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaSitImpositivaOutEntity.class);
			} else {
				grabarEstadisticasError(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_SERVICIOS_IATX);
				throw new DAOException();
			}
		} catch (IatxException e) {
			handleIatxException(e, true);
		}
		return consultaSitImpositivaOutEntity;
	}

	private IatxRequestData generateRequestDataCNSINFIMPO(Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(cliente.getNup());
		iatxRequestData.addBodyValue(StringUtils.leftPad("", 1, " "));
		iatxRequestData.addBodyValue(StringUtils.leftPad("", 11, "0"));
		iatxRequestData.addBodyValue(StringUtils.leftPad("", 2, " "));
		iatxRequestData.addBodyValue(StringUtils.leftPad("", 8, " "));
		iatxRequestData.addBodyValue(StringUtils.leftPad("", 8, " "));
		iatxRequestData.addBodyValue(MARCA_RECUPERA_UNA_SIT_IMP);
		return iatxRequestData;
	}
	
	/**
	 * consulta la situacion impositiva del cliente.
     * @param cliente
	 *            the cliente
     * @return the consulta situacion impositiva out entity
     * @throws DAOException 
	 * @throws IatxException 
	 */
	@Override
	public ActualizacionConsultaMarcaAdhesionFinal ActualizacionConsultaMarcaAdhesion(Cliente cliente, Boolean esConsultaExpPoliticamente)
			throws DAOException, IatxException {
		String servicio = "ACTADHECNL";
		String version = "100";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ActualizacionConsultaMarcaAdhesionEntityOut actualizacionConsultaMarcaAdhesionEntityOut = new ActualizacionConsultaMarcaAdhesionEntityOut();
		ActualizacionConsultaMarcaAdhesionFinal actualizacionConsultaMarcaAdhesionFinal = new ActualizacionConsultaMarcaAdhesionFinal();
		try {
			IatxRequestData iatxRequestData = generateRequestDataACTADHECNL(cliente, esConsultaExpPoliticamente);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				actualizacionConsultaMarcaAdhesionEntityOut = processTrama(iatxResponse.getTrama(),
						ActualizacionConsultaMarcaAdhesionEntityOut.class);
				actualizacionConsultaMarcaAdhesionFinal = parsearMarcaAdhesion(actualizacionConsultaMarcaAdhesionEntityOut, iatxResponse);
				if (!esConsultaExpPoliticamente) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_MARCA_ADHESION, 
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				}
				
			} else {
				if (!esConsultaExpPoliticamente) {
					grabarEstadisticasError(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_MARCA_ADHESION);
				} else {
					grabarEstadisticasError(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_SERVICIOS_IATX);
					throw new DAOException();
				}
				
			}
		} catch (IatxException e) {
			handleIatxException(e, esConsultaExpPoliticamente);
		}
		return actualizacionConsultaMarcaAdhesionFinal;
	}
	
	private ActualizacionConsultaMarcaAdhesionFinal parsearMarcaAdhesion(
			ActualizacionConsultaMarcaAdhesionEntityOut actualizacionConsultaMarcaAdhesionEntityOut, IatxResponse iatxResponse) {
		ActualizacionConsultaMarcaAdhesionFinal actualizacionConsultaMarcaAdhesionFinal = new ActualizacionConsultaMarcaAdhesionFinal(
				iatxResponse.getNroComprobante(), iatxResponse.getFechaHoraReq());
		actualizacionConsultaMarcaAdhesionFinal.setHeaderTrama(actualizacionConsultaMarcaAdhesionEntityOut.getHeaderTrama());
		actualizacionConsultaMarcaAdhesionFinal.setCodigoRetornoExtendido(actualizacionConsultaMarcaAdhesionEntityOut.getCodigoRetornoExtendido());
		actualizacionConsultaMarcaAdhesionFinal.setCantOcurrencias(actualizacionConsultaMarcaAdhesionEntityOut.getCantOcurrencias());
		actualizacionConsultaMarcaAdhesionFinal.setListaIndicadores(actualizacionConsultaMarcaAdhesionEntityOut.getListaIndicadores());
		
		return actualizacionConsultaMarcaAdhesionFinal;
	}

	private IatxRequestData generateRequestDataACTADHECNL(Cliente cliente, Boolean esConsultaExpPoliticamente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		
		if (esConsultaExpPoliticamente) {
			iatxRequestData.addBodyValue("C");
			iatxRequestData.addBodyValue("FUN");
			iatxRequestData.addBodyValue(StringUtils.leftPad("", 1, " "));
			iatxRequestData.addBodyValue(StringUtils.leftPad("", 26, " "));
		} else {
			iatxRequestData.addBodyValue("A");
			iatxRequestData.addBodyValue("NET");
			iatxRequestData.addBodyValue("S");
			iatxRequestData.addBodyValue(StringUtils.leftPad("", 26, " "));
		}

		
		return iatxRequestData;
	}

	/**
	 * Manejo las exepciones de Iatx.
	 *
	 * @param e
	 *            the e
	 * @throws TimeOutException
	 *             the time out exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws IatxException 
	 */
	private void handleIatxException(IatxException e, Boolean esConsultaExpPoliticamente) throws TimeOutException, IatxException {
		if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
			LOGGER.error(e.getMessage(), e);
			if (esConsultaExpPoliticamente) {
				grabarEstadisticasError(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_SERVICIOS_IATX);
				throw new TimeOutException(e.getMessage());
			} else {
				grabarEstadisticasError(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_MARCA_ADHESION);
			}		
		} else {
			LOGGER.error(e.getMessage(), e);
			if (esConsultaExpPoliticamente) {
				grabarEstadisticasError(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_SERVICIOS_IATX);
				throw new IatxException(e);
			} else {
				grabarEstadisticasError(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_MARCA_ADHESION);
			}	
		}
	}
	
	private void grabarEstadisticasError(String codigoError) {
            estadisticaManager.add(codigoError,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}
	
	/**
     * Confirma la adhesion del cliente.
     * @param inEntity
	 *            the entity
	 * @throws IOException
     */
	@Override
	public void postPersons(GetnetInEntity inEntity, String nroCuentaDestino)
			throws DAOException, ISBANRuntimeException, IOException, GetnetValidationException, GetnetEmailValidationException {
		LOGGER.info("GetNetDAO: Inicio del metodo persons");
		GetnetOutEntity respuesta = new GetnetOutEntity();
		try {
			Credential credential = obtenerCredencialSeguridad();
			String header = credential.getUsuario();
            String password = credential.getPassword();
			WebClient getNetClient = restWebClient.obtenerClienteRest(NOMBRE_WS);
			getNetClient.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
				.accept(MediaType.APPLICATION_JSON);
			getNetClient.header(header, password);
			getNetClient.path(PATH_PERSONS);
			respuesta = getNetClient.post(inEntity, GetnetOutEntity.class);
			Integer codigoRespuesta = getNetClient.getResponse().getStatus();
			if (respuesta == null) {
				LOGGER.error("Error llamando a la API de GetNet. La respuesta es nula.");
				throw new DAOException();
			}
			if (!CODIGO_OK_POST.equals(codigoRespuesta)) {
				LOGGER.error("Error llamando a la API de GetNet. El codigo de error es: " + codigoRespuesta + ".");
				grabarEstadisticasError(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_SERVICIOS_IATX);
				throw new DAOException();
			} else {
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_SERVICIOS_IATX, 
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK, nroCuentaDestino, "", "");
			}
		} catch (BadRequestException e) {
			grabarEstadisticasError(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_SERVICIOS_IATX);
			Object entity = e.getResponse().getEntity();
			InputStream is = InputStream.class.cast(entity);
			ObjectMapper mapper = new ObjectMapper();
			respuesta = mapper.readValue(is, GetnetOutEntity.class);
			String key = respuesta.getError().getKey();
			if (KEY_EMAIL_EXISTENTE.equalsIgnoreCase(key)) {
				LOGGER.error("Error llamando a la API de GetNet. El email ya ha sido utilizado.");
				throw new ISBANRuntimeException();
			} else if (KEY_EMAIL_VALIDATION_ERROR.equalsIgnoreCase(key)) {
				LOGGER.error("Error llamando a la API de GetNet. Peticion rechazada por proceso de validacion. El email no es correcto");
				throw new GetnetEmailValidationException();
			} else if (KEY_VALIDATION_ERROR.equalsIgnoreCase(key)) {
				LOGGER.error("Error llamando a la API de GetNet. Peticion rechazada por proceso de validacion.");
				throw new GetnetValidationException();
			} else {
				Integer codigoRespuesta = e.getResponse().getStatus();
				LOGGER.error("Error llamando a la API de GetNet. El codigo de error es: " + codigoRespuesta + ".");
				throw new DAOException();
			}
		} catch (Exception e) {
			LOGGER.error("Error llamando a la API de GetNet.");
			grabarEstadisticasError(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_SERVICIOS_IATX);
			throw new DAOException();
		}
	}
	
	/**
     * Descarga comprobante adhesion
     * @param dto
     * 			the dto
     * @return the reporte
     */
	@Override
    public Reporte descargaComprobanteAdhesion(GetnetAdhesionDTO dto) {
		Reporte reporte = new Reporte();
		Resource reportFile = reporteAdhesionGetnet;
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(PARAM_LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(PARAM_LOGO_GETNET, logoGetnet.getFile().getPath());
			parameters.put(PARAM_EMAIL, dto.getEmail());
			parameters.put(PARAM_CELULAR, dto.getCelular());
			parameters.put(PARAM_DESCRIPCION_CUENTA, dto.getDescripcionCuenta());
			parameters.put(PARAM_NUMERO_CUENTA, dto.getNumeroCuenta());
			parameters.put(PARAM_NOMBRE_FANTASIA, dto.getNombreFantasia());
			parameters.put(PARAM_ACTIVIDAD, dto.getActividad());
			parameters.put(PARAM_INGRESOS, dto.getIngresos());
			parameters.put(PARAM_NUMERO_COMPROBANTE, dto.getNumeroComprobante());
			parameters.put(PARAM_FECHA_COMPROBANTE, ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
			parameters.put(PARAM_LOGO_CIERRE, logoCierre.getFile().getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			String filenamePrefix = NOMBRE_ARCHIVO_ADHESION;
			reporte.setNombre(filenamePrefix + PDF_EXTENSION);
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
		return reporte;
    }
	
	/**
     * Obtiene la credencial de la DB Seguridad para el header
     *
     * @return the credential
	 * @throws SQLException 
     */
	private Credential obtenerCredencialSeguridad() throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(getnetIdSeguridad);
        return credential;
	}
	
}
