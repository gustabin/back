/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.banelco.ObjectFactory;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTarjetaDTO;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTerminalDTO;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSUserData;
import ar.com.santanderrio.obp.servicios.blanqueopin.dao.BlanqueoPinDAO;
import ar.com.santanderrio.obp.servicios.blanqueopin.entities.TipoDocumentoBlanqueoPin;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.ModifLimiteDebitoDAO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.ConsultaDatosTarjetaDebitoEntity;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.ModificarLimiteDebitoEntity;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteDescargaCambioLimiteView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ModifLimiteDebitoDAOImpl.
 */
/**
 * @author Leonid_Komratov
 *
 */
@Component
public class ModifLimiteDebitoDAOImpl extends IatxBaseDAO implements ModifLimiteDebitoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ModifLimiteDebitoDAOImpl.class);

	/** The Constant CONSULTA_CLASE_TARJETA_INDEX. */
	private static final int CONSULTA_CLASE_TARJETA_INDEX = 3;

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant VISA. */
	private static final String VISA = "VISA ";

	/** The Constant CURRENCY. */
	private static final String CURRENCY = "$ ";

	/** The Constant COD_ERROR_CERO. */
	private static final int COD_ERROR_CERO = 00000000;

	/** The Constant COD_ERROR_TARJETA_INEXISTENTE. */
	private static final int COD_ERROR_TARJETA_INEXISTENTE = 20000226;

	/** The Constant TIPO_LLAMADA. */
	private static final String TIPO_LLAMADA = "M";

	/** The Constant CODIGO_CAMBIO. */
	private static final String CODIGO_CAMBIO = "02";

	/** The Constant CANAL_E. */
	private static final String CANAL_E = "E";

	/** The Constant TERMINAL. */
	private static final String TERMINAL = "OBP";

	/** The file jasper. */
	@Value("classpath:/report/modificacionLimiteDebito/ComprobanteCambioLimiteDebito.jasper")
	private Resource fileJasperComprobanteCambioLimiteDebito;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo default. */
	@Value("classpath:/report/comprobantes/logoDebito.png")
	private Resource logoDebito;

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant LOGO_DEBITO. */
	private static final String LOGO_DEBITO = "LOGO_DEBITO";

	/** The Constant NRO_TARJETA_FORMAT. */
	private static final String NRO_TARJETA_FORMAT = "NRO_TARJETA_FORMAT";

	/** The Constant LIM_EXT_ACTUAL. */
	private static final String LIM_EXT_ACTUAL = "LIM_EXT_ACTUAL";

	/** The Constant LIM_EXT_NUEVO. */
	private static final String LIM_EXT_NUEVO = "LIM_EXT_NUEVO";

	/** The Constant LIM_COMPRA_ACTUAL. */
	private static final String LIM_COMPRA_ACTUAL = "LIM_COMPRA_ACTUAL";
	
	/** The Constant NUEVO_LIM_COMPRA. */
	private static final String NUEVO_LIM_COMPRA = "NUEVO_LIM_COMPRA";

	/** The Constant NRO_COMPROBANTE. */
	private static final String NRO_COMPROBANTE = "NRO_COMPROBANTE";

	/** The Constant FECHA_OPERACION. */
	private static final String FECHA_OPERACION = "FECHA_OPERACION";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/** The servicio CNSTARBANE. */
	@Value("${SERVICIO.PREFIJO.CNSTARBANE}")
	private String servicioCnsTarBane;

	/** The version CNSTARBANE. */
	@Value("${SERVICIO.VERSION.CNSTARBANE}")
	private String versionCnsTarBane;

	/** The servicio cmb tar bane. */
	@Value("${SERVICIO.PREFIJO.CMBTARBANE}")
	private String servicioCmbTarBane;

	/** The version cmb tar bane. */
	@Value("${SERVICIO.VERSION.CMBTARBANE}")
	private String versionCmbTarBane;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The blanqueo pin DAO. */
	@Autowired
	private BlanqueoPinDAO blanqueoPinDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.
	 * ModifLimiteDebitoDAO#getClaseTarjetaBanelco(java.lang.String,
	 * java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public ConsultaDatosTarjetaDebitoEntity getClaseTarjetaBanelco(String sucursal, String numTarjeta, Cliente cliente)
			throws DAOException {
		LOGGER.info("Invocando Servicio Iatx ");

		IatxRequestData data = crearRequestData(sucursal, numTarjeta, cliente);
		IatxResponse iatxResponse;
		try {
			iatxResponse = obtenerResponseIatx(servicioCnsTarBane, versionCnsTarBane, data);
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return crearResponseDTO(iatxResponse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.
	 * ModifLimiteDebitoDAO#modificarLimitesExtraccion(ar.com.santanderrio.obp.
	 * servicios.modificacionlimitedebito.entities.ModificarLimiteDebitoEntity)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> modificarLimitesExtraccion(ModificarLimiteDebitoEntity entity)
			throws DAOException {

		IatxRequest iatxRequest = new IatxRequest(servicioCmbTarBane, versionCmbTarBane);

		Respuesta<ResultadoTransaccion> respuestaResultado = new Respuesta<ResultadoTransaccion>();

		try {
			IatxRequestData iatxRequestData = generateRequestDataCMBTARBANE(entity);

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
			if (errorCode == OK_CODIGO_RETORNO) {
				respuestaResultado.setEstadoRespuesta(EstadoRespuesta.OK);
				SimpleDateFormat formatoFechaHoraTrama = new SimpleDateFormat("yyyyMMddHHmmss");
				resultadoTransaccion.setFechaTransaccion(formatoFechaHoraTrama.parse(iatxResponse.getFechaHoraReq()));
				resultadoTransaccion.setMensajeError(iatxResponse.getErrorMessage());
				resultadoTransaccion.setNumeroComprobante(iatxResponse.getNroComprobante());
				resultadoTransaccion.setCodigoRespuesta(iatxResponse.getErrorCode());
				resultadoTransaccion.setEstadoRespuesta(iatxResponse.getEstadoRespuesta());
				respuestaResultado.setRespuesta(resultadoTransaccion);
				return respuestaResultado;
			} else {
				respuestaResultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
				resultadoTransaccion.setMensajeError(iatxResponse.getErrorMessage());
				resultadoTransaccion.setCodigoRespuesta(iatxResponse.getErrorCode());
				respuestaResultado.setRespuesta(resultadoTransaccion);
				return respuestaResultado;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.
	 * ModifLimiteDebitoDAO#modificarLimitesExtraccion(ar.com.santanderrio.obp.
	 * servicios.modificacionlimitedebito.entities.ModificarLimiteDebitoEntity,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> modificarLimitesExtraccion(ModificarLimiteDebitoEntity entity, String ip, String userAgent)
			throws DAOException {
		Respuesta<ResultadoTransaccion> respuestaResultado = new Respuesta<ResultadoTransaccion>();
		try {
			ObjectFactory objectFactory = new ObjectFactory();
			WSTerminalDTO terminalDTO = obtenerTerminalDTO(objectFactory, ip, userAgent);
			WSUserData userData = obtenerUserData(objectFactory, entity.getCliente());
			WSTarjetaDTO tarjetaDTO = obtenerTarjetaDTO(objectFactory, entity.getNumTarjetaBanelco());
			String respuesta = blanqueoPinDAO.cambiarClase(terminalDTO, userData, tarjetaDTO, entity.getClase(), ip, userAgent);
			ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
			if (StringUtils.equals(respuesta, "00")) {
				respuestaResultado.setEstadoRespuesta(EstadoRespuesta.OK);
				resultadoTransaccion.setFechaTransaccion(new Date());
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
				String nroComprobante = sdf.format(resultadoTransaccion.getFechaTransaccion());
				resultadoTransaccion.setNumeroComprobante(nroComprobante);
				resultadoTransaccion.setEstadoRespuesta(EstadoRespuesta.OK);
				resultadoTransaccion.setCodigoRespuesta(Integer.valueOf(respuesta));
				respuestaResultado.setRespuesta(resultadoTransaccion);
				return respuestaResultado;
			} else {
				respuestaResultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
				resultadoTransaccion.setEstadoRespuesta(EstadoRespuesta.ERROR);
				resultadoTransaccion.setCodigoRespuesta(Integer.valueOf(respuesta));
				respuestaResultado.setRespuesta(resultadoTransaccion);
				return respuestaResultado;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Obtener tarjeta DTO.
	 *
	 * @param objectFactory the object factory
	 * @param cuentaSeleccionada the cuenta seleccionada
	 * @return the WS tarjeta DTO
	 */
	private WSTarjetaDTO obtenerTarjetaDTO(ObjectFactory objectFactory, String numeroTarjeta) {
		WSTarjetaDTO tarjetaDTO = objectFactory.createWSTarjetaDTO();
		tarjetaDTO.setMiembro(objectFactory.createWSTarjetaDTOMiembro("001"));
		tarjetaDTO.setNumero(objectFactory.createWSTarjetaDTONumero(numeroTarjeta));
		return tarjetaDTO;
	}

	/**
	 * Obtener user data.
	 *
	 * @param objectFactory the object factory
	 * @param cliente the cliente
	 * @return the WS user data
	 */
	private WSUserData obtenerUserData(ObjectFactory objectFactory, Cliente cliente) {		
		WSUserData userData = objectFactory.createWSUserData();
		userData.setNumeroDocumento(objectFactory.createWSUserDataNumeroDocumento(cliente.getDni()));
		userData.setTipoDocumento(objectFactory.createWSUserDataTipoDocumento(
				TipoDocumentoBlanqueoPin.obtenerDatoParaBanelco(cliente.getTipoDocumento())));
		return userData;
	}

	/**
	 * Obtener terminal DTO.
	 *
	 * @param objectFactory the object factory
	 * @param ip the ip
	 * @param userAgent the user agent
	 * @return the WS terminal DTO
	 */
	private WSTerminalDTO obtenerTerminalDTO(ObjectFactory objectFactory, String ip, String userAgent) {
		WSTerminalDTO terminalDTO = objectFactory.createWSTerminalDTO();
		terminalDTO.setCanal(objectFactory.createWSTerminalDTOCanal(CANAL_E));
		terminalDTO.setDireccionIP(objectFactory.createWSTerminalDTODireccionIP(ip));
		terminalDTO.setTerminal(objectFactory.createWSTerminalDTOTerminal(TERMINAL));
		terminalDTO.setDatosTerminal(objectFactory.createWSTerminalDataDatosTerminal(userAgent));
		return terminalDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.
	 * ModifLimiteDebitoDAO#comprobanteModifLimitesExtraccion(ar.com.
	 * santanderrio.obp.servicios.modificacionlimitedebito.web.view.
	 * ComprobanteDescargaCambioLimiteView)
	 */
	@Override
	public Reporte comprobanteModifLimitesExtraccion(ComprobanteDescargaCambioLimiteView comprobanteView) {

		Reporte reporte = new Reporte();

		try {
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(fileJasperComprobanteCambioLimiteDebito.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEBITO, logoDebito.getFile().getPath());
			parameters.put(NRO_TARJETA_FORMAT, VISA + comprobanteView.getNumeroTarjetaFormateado());
			parameters.put(LIM_EXT_ACTUAL, CURRENCY + ISBANStringUtils
					.formatearSaldo(BigDecimal.valueOf(Double.parseDouble(comprobanteView.getLimiteExtActual()))));
			parameters.put(LIM_EXT_NUEVO, CURRENCY + ISBANStringUtils
					.formatearSaldo(BigDecimal.valueOf(Double.parseDouble(comprobanteView.getLimiteExtNuevo()))));
			parameters.put(LIM_COMPRA_ACTUAL, CURRENCY + ISBANStringUtils
					.formatearSaldo(BigDecimal.valueOf(Double.parseDouble(comprobanteView.getLimiteCompra()))));
			parameters.put(NUEVO_LIM_COMPRA, CURRENCY + ISBANStringUtils
					.formatearSaldo(BigDecimal.valueOf(Double.parseDouble(comprobanteView.getNuevoLimCompra()))));
			parameters.put(NRO_COMPROBANTE, comprobanteView.getNumeroComprobante());
			parameters.put(FECHA_OPERACION, comprobanteView.getFechaHora());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre(
					"Comprobante_Modificacion_Limite_Debito_" + comprobanteView.getNumeroComprobante() + PDF_EXTENSION);
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
	 * Generate request data CMBTARBANE.
	 *
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCMBTARBANE(ModificarLimiteDebitoEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(TIPO_LLAMADA);
		iatxRequestData.addBodyValue(entity.getNumTarjetaBanelco());
		iatxRequestData.addBodyValue(CODIGO_CAMBIO);
		iatxRequestData.addBodyValue(entity.getClase());
		iatxRequestData.addBodyValue(entity.getMontoActual());
		iatxRequestData.addBodyValue(entity.getMontoSeleccionado());

		return iatxRequestData;
	}

	/**
	 * Crear request data.
	 *
	 * @param sucursal
	 *            the sucursal
	 * @param numTarjeta
	 *            the num tarjeta
	 * @param cliente
	 *            the cliente
	 * @return the iatx request data
	 */
	private IatxRequestData crearRequestData(String sucursal, String numTarjeta, Cliente cliente) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(sucursal);
		requestData.addBodyValue(numTarjeta);
		return requestData;
	}

	/**
	 * Obtener response iatx.
	 *
	 * @param nombreServicio
	 *            the nombre servicio
	 * @param version
	 *            the version
	 * @param data
	 *            the data
	 * @return the iatx response
	 * @throws IatxException
	 *             the iatx exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxResponse obtenerResponseIatx(String nombreServicio, String version, IatxRequestData data)
			throws IatxException, DAOException {
		IatxResponse iatxResponse = crearIatxResponse();
		try {
			IatxRequest iatxRequest = buildIatxRequest(nombreServicio, version, data);
			iatxResponse = ejecutar(iatxRequest);
			return iatxResponse;
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Crear iatx response.
	 *
	 * @return the iatx response
	 */
	private IatxResponse crearIatxResponse() {
		return new IatxResponse();
	}

	/**
	 * Ejecutar.
	 *
	 * @param request
	 *            the request
	 * @return the iatx response
	 * @throws IatxException
	 *             the iatx exception
	 */
	private IatxResponse ejecutar(IatxRequest request) throws IatxException {
		return iatxComm.exec(request);
	}

	/**
	 * Builds the iatx request.
	 *
	 * @param nombreServicio
	 *            the nombre servicio
	 * @param version
	 *            the version
	 * @param data
	 *            the data
	 * @return the iatx request
	 */
	private IatxRequest buildIatxRequest(String nombreServicio, String version, IatxRequestData data) {
		IatxRequest iatxRequest = new IatxRequest(nombreServicio, version);
		iatxRequest.setData(data);
		return iatxRequest;
	}

	/**
	 * Crear response DTO.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the consulta datos tarjeta debito entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaDatosTarjetaDebitoEntity crearResponseDTO(IatxResponse iatxResponse) throws DAOException {
		if (esRespuestaOK(iatxResponse)) {
			return crearRespuestaDTO(iatxResponse);
		} else {
			return crearRespuestaError(iatxResponse);
		}
	}

	/**
	 * Crear respuesta error.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the consulta datos tarjeta debito entity
	 */
	private ConsultaDatosTarjetaDebitoEntity crearRespuestaError(IatxResponse iatxResponse) {
		ConsultaDatosTarjetaDebitoEntity outEntity = new ConsultaDatosTarjetaDebitoEntity();
		outEntity.setTieneError(true);
		if (!esCodigoRetornoOK(iatxResponse) && iatxResponse.getErrorCode() == COD_ERROR_TARJETA_INEXISTENTE) {
			outEntity.setCodError(iatxResponse.getErrorCode());
		}
		return outEntity;
	}

	/**
	 * Crear respuesta DTO.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the consulta datos tarjeta debito entity
	 */
	private ConsultaDatosTarjetaDebitoEntity crearRespuestaDTO(IatxResponse iatxResponse) {
		ConsultaDatosTarjetaDebitoEntity consDatosTarjetaDebitoEntity = new ConsultaDatosTarjetaDebitoEntity();
		consDatosTarjetaDebitoEntity.setClaseTarjetaDebito(iatxResponse.getData(CONSULTA_CLASE_TARJETA_INDEX));

		return consDatosTarjetaDebitoEntity;
	}

	/**
	 * Es respuesta OK.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the boolean
	 */
	public Boolean esRespuestaOK(IatxResponse iatxResponse) {
		return esCodigoRetornoOK(iatxResponse) && esRespuestaEstadoOK(iatxResponse);
	}

	/**
	 * Es codigo retorno OK.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the boolean
	 */
	public Boolean esCodigoRetornoOK(IatxResponse iatxResponse) {
		return iatxResponse.getErrorCode() == COD_ERROR_CERO;
	}

	/**
	 * Es respuesta estado OK.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the boolean
	 */
	public Boolean esRespuestaEstadoOK(IatxResponse iatxResponse) {
		return iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK);
	}

}
