/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
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

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.monedero.dao.AltaTagMonederoDAO;
import ar.com.santanderrio.obp.servicios.monedero.entities.DatosAltaTagMonederoEntity;
import ar.com.santanderrio.obp.servicios.monedero.exception.ErrorAltaSolicitudException;
import ar.com.santanderrio.obp.servicios.monedero.utils.MonederoUtils;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosParaActivacionView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteConfirmadoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.MonederoMovimientosView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class AltaTagMonederoDAOImpl.
 */
@Component
public class AltaTagMonederoDAOImpl implements AltaTagMonederoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AltaTagMonederoDAOImpl.class);

	/** The Constant COD_ERROR_CERO. */
	private static final int COD_ERROR_CERO = 00000000;

	/** The Constant COD_ERROR_SUCURSAL_FUERA_HORARIO. */
	private static final int COD_ERROR_SUCURSAL_FUERA_HORARIO = 10000077;

	/** The Constant SECUENCIA_DOMICILIO. */
	private static final String SECUENCIA_DOMICILIO = "001";

	/** The Constant SECUENCIA_TELEFONO. */
	private static final String SECUENCIA_TELEFONO = "001";

	/** The Constant ZERO_STRING. */
	private static final String ZERO_STRING = "0";

	/** The Constant ONE_STRING. */
	private static final String ONE_STRING = "1";

	/** The Constant APLIC. */
	private static final String APLIC_MONE = "MONE";

	/** The Constant LIMITE_MENSUAL_RECARGA_LENGTH. */
	private static final int LIMITE_MENSUAL_RECARGA_LENGTH = 11;

	/** The Constant IMPORTE_DE_RECARGA. */
	private static final int IMPORTE_DE_RECARGA = 11;

	/** private static final int NUP_LENGTH = 8;. */

	private static final String BONIFICACION = "SC";

	/** The Constant COD_ERROR_MONEDERO_YA_SOLICITADO. */
	private static final int COD_ERROR_MONEDERO_YA_SOLICITADO_1 = 50000037;

	/** The Constant COD_ERROR_MONEDERO_YA_SOLICITADO. */
	private static final int COD_ERROR_MONEDERO_YA_SOLICITADO_2 = 50000052;

	/** The Constant COD_ERROR_MONEDERO_SERVICIO_ALTA_TAG_INTERRUMPIDO. */
	private static final int COD_ERROR_MONEDERO_SERVICIO_ALTA_TAG_INTERRUMPIDO = 10099975;

	/** The servicio pag tjc. */
	@Value("${SERVICIO.PREFIJO.ALTCTAMONE}")
	private String servicioAltaCtaMone;

	/** The version pag tjc. */
	@Value("${SERVICIO.VERSION.ALTCTAMONE}")
	private String versionAltaCtaMone;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The file jasper. */
	@Value("classpath:/report/monedero/ComprobanteAltaTagMonedero.jasper")
	private Resource fileJasper;

	/** The file jasper movimientos. */
	@Value("classpath:/report/monedero/titular_activo_movimientos.jasper")
	private Resource fileJasperMovimientos;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo default. */
	@Value("classpath:/report/comprobantes/logo_default.png")
	private Resource logoDefault;

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant LOGO_DEFAULT. */
	private static final String LOGO_DEFAULT = "LOGO_DEFAULT";

	/** The Constant NRO_MONEDERO_TAG. */
	private static final String NRO_MONEDERO_TAG = "NRO_MONEDERO_TAG";

	/** The Constant DEBITAR_RECARGA_EN. */
	private static final String DEBITAR_RECARGA_EN = "DEBITAR_RECARGA_EN";

	/** The Constant IMPORTE_A_RECARGAR. */
	private static final String IMPORTE_A_RECARGAR = "IMPORTE_A_RECARGAR";

	/** The Constant LIMITE_RECARGA_MENSUAL. */
	private static final String LIMITE_RECARGA_MENSUAL = "LIMITE_RECARGA_MENSUAL";

	/** The Constant APELLIDO. */
	private static final String APELLIDO = "APELLIDO";

	/** The Constant NOMBRE. */
	private static final String NOMBRE = "NOMBRE";

	/** The Constant FECHA_NACIMIENTO. */
	private static final String FECHA_NACIMIENTO = "FECHA_NACIMIENTO";

	/** The Constant DNI. */
	private static final String DNI = "DNI";

	/** The Constant PAIS_NACIMIENTO. */
	private static final String PAIS_NACIMIENTO = "PAIS_NACIMIENTO";

	/** The Constant SEXO. */
	private static final String SEXO = "SEXO";

	/** The Constant ESTADO_CIVIL. */
	private static final String ESTADO_CIVIL = "ESTADO_CIVIL";

	/** The Constant NACIONALIDAD. */
	private static final String NACIONALIDAD = "NACIONALIDAD";

	/** The Constant DOMICILIO. */
	private static final String DOMICILIO = "DOMICILIO";

	/** The Constant NRO_COMPROBANTE. */
	private static final String NRO_COMPROBANTE = "NRO_COMPROBANTE";

	/** The Constant FECHA_OP. */
	private static final String FECHA_OP = "FECHA_OP";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.dao.AltaTagMonederoDAO#
	 * ejecutarAltaTagMonedero(ar.com.santanderrio.obp.servicios.monedero.
	 * entities.DatosAltaTagMonedero,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public ComprobanteAltaTagMonederoView ejecutarAltaTagMonedero(DatosAltaTagMonederoEntity datosAltaTagMonedero,
			Cliente cliente) throws BusinessException {
		/** LOGGER.info("Invocando Servicio Iatx " + datosAltaTagMonedero); */
		IatxResponse iatxResponse = obtenerResponseAltaTagMonedero(datosAltaTagMonedero, cliente);
		return crearResponseDTO(datosAltaTagMonedero, iatxResponse);
	}

	/**
	 * Crear response DTO.
	 *
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @param iatxResponse
	 *            the iatx response
	 * @return the comprobante alta tag monedero view
	 * @throws BusinessException
	 *             the business exception
	 */
	private ComprobanteAltaTagMonederoView crearResponseDTO(DatosAltaTagMonederoEntity datosAltaTagMonedero,
			IatxResponse iatxResponse) throws BusinessException {
		if (esRespuestaOK(iatxResponse)) {
			return crearRespuestaDTO(datosAltaTagMonedero, iatxResponse);
		} else {
			verTiposDeErrorDesdeIatxResponse(iatxResponse);
		}
		return null;
	}

	/**
	 * Obtener response alta tag monedero.
	 *
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @param cliente
	 *            the cliente
	 * @return the iatx response
	 * @throws BusinessException
	 *             the business exception
	 */
	private IatxResponse obtenerResponseAltaTagMonedero(DatosAltaTagMonederoEntity datosAltaTagMonedero,
			Cliente cliente) throws BusinessException {
		IatxRequestData data = crearRequestData(datosAltaTagMonedero, cliente);
		return obtenerResponseIatx(servicioAltaCtaMone, versionAltaCtaMone, data);
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
	 * @throws BusinessException
	 *             the business exception
	 */
	public IatxResponse obtenerResponseIatx(String nombreServicio, String version, IatxRequestData data)
			throws BusinessException {
		IatxResponse iatxResponse = new IatxResponse();
		try {
			IatxRequest request = buildIatxRequest(nombreServicio, version, data);
			iatxResponse = ejecutar(request);
			return iatxResponse;
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(new DAOException(e));
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(new DAOException(e, iatxResponse.getErrorMessage()));
		}
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
	public IatxRequest buildIatxRequest(String nombreServicio, String version, IatxRequestData data) {
		IatxRequest iatxRequest = new IatxRequest(nombreServicio, version);
		iatxRequest.setData(data);
		return iatxRequest;
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
	public IatxResponse ejecutar(IatxRequest request) throws IatxException {
		return iatxComm.exec(request);
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

	/**
	 * Ver tipos de error desde iatx response.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @throws BusinessException
	 *             the business exception
	 */
	public void verTiposDeErrorDesdeIatxResponse(IatxResponse iatxResponse) throws BusinessException {
		calcularErrorFueraHorario(iatxResponse);
		calcularErrorGenerico(iatxResponse);
	}

	/**
	 * Calcular error fuera horario.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @throws BusinessException
	 *             the business exception
	 */
	private void calcularErrorFueraHorario(IatxResponse iatxResponse) throws BusinessException {
		if (esCodigosDeErrorFueraHorario(iatxResponse)) {
			throw new BusinessException(new OperacionFueraHorarioSucursalException());
		}
	}

	/**
	 * Calcular error generico.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @throws BusinessException
	 *             the business exception
	 */
	private void calcularErrorGenerico(IatxResponse iatxResponse) throws BusinessException {
		if (!esCodigoRetornoOK(iatxResponse)) {
			if (iatxResponse.getErrorCode() == COD_ERROR_MONEDERO_YA_SOLICITADO_1
					|| iatxResponse.getErrorCode() == COD_ERROR_MONEDERO_YA_SOLICITADO_2) {
				throw new BusinessException(new ErrorAltaSolicitudException());
			} else if (iatxResponse.getErrorCode() == COD_ERROR_MONEDERO_SERVICIO_ALTA_TAG_INTERRUMPIDO) {
				throw new BusinessException(new DAOException());
			}
		}
	}

	/**
	 * Es codigos de error fuera horario.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the boolean
	 */
	private Boolean esCodigosDeErrorFueraHorario(IatxResponse iatxResponse) {
		return iatxResponse.getErrorCode() == COD_ERROR_SUCURSAL_FUERA_HORARIO;
	}

	/**
	 * Crear respuesta DTO.
	 *
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @param iatxResponse
	 *            the iatx response
	 * @return the comprobante alta tag monedero view
	 * @throws BusinessException 
	 */
	private ComprobanteAltaTagMonederoView crearRespuestaDTO(DatosAltaTagMonederoEntity datosAltaTagMonedero,
			IatxResponse iatxResponse) throws BusinessException {
		ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView = new ComprobanteAltaTagMonederoView();
		comprobanteAltaTagMonederoView.setComprobante(iatxResponse.getData(1));
		
		// ID 146678 TFS - Formateo de fecha
		SimpleDateFormat sdfIn = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        Date fecha = new Date();
		try {
			fecha = sdfIn.parse(iatxResponse.getFechaHoraReq());
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
        
		comprobanteAltaTagMonederoView.setFechaHora(sdfOut.format(fecha));
		DatosSolicitanteConfirmadoView datosSolicitanteConfirmadoView = new DatosSolicitanteConfirmadoView();
		datosSolicitanteConfirmadoView.setApellido(datosAltaTagMonedero.getApellidoEmbozado());
		datosSolicitanteConfirmadoView.setNombre(datosAltaTagMonedero.getNombreEmbozado());
		datosSolicitanteConfirmadoView.setFechaNacimiento(datosAltaTagMonedero.getFechaNacimiento());
		datosSolicitanteConfirmadoView.setDoc(datosAltaTagMonedero.getDni());
		datosSolicitanteConfirmadoView.setDocTipo(datosAltaTagMonedero.getTipoDocumento());
		datosSolicitanteConfirmadoView.setEstadoCivil(datosAltaTagMonedero.getEstadoCivil());
		datosSolicitanteConfirmadoView
				.setCuentaPesosSeleccionada(datosAltaTagMonedero.getNroTarjetaOrigenRecargasPrincipal());
		datosSolicitanteConfirmadoView.setImporteSeleccionado(datosAltaTagMonedero.getImporteARecargar());
		datosSolicitanteConfirmadoView.setLimiteSeleccionado(datosAltaTagMonedero.getLimiteMensualRecarga());
		datosSolicitanteConfirmadoView.setPaisNacimiento(datosAltaTagMonedero.getPaisDeNacimiento());
		datosSolicitanteConfirmadoView.setSexo(datosAltaTagMonedero.getSexo());
		datosSolicitanteConfirmadoView.setNacionalidad(datosAltaTagMonedero.getNacionalidad());
		datosSolicitanteConfirmadoView.setCalle(datosAltaTagMonedero.getCalle());
		datosSolicitanteConfirmadoView.setCalleNro(datosAltaTagMonedero.getNro());
		datosSolicitanteConfirmadoView.setPiso(datosAltaTagMonedero.getPiso());
		datosSolicitanteConfirmadoView.setProvincia(datosAltaTagMonedero.getProvincia());
		datosSolicitanteConfirmadoView.setDepto(datosAltaTagMonedero.getDepto());
		datosSolicitanteConfirmadoView.setCp(datosAltaTagMonedero.getCp());
		datosSolicitanteConfirmadoView.setLocalidad(datosAltaTagMonedero.getLocalidad());
		comprobanteAltaTagMonederoView.setDatosSolicitanteConfirmadoView(datosSolicitanteConfirmadoView);
		return comprobanteAltaTagMonederoView;
	}

	/**
	 * Crear request data.
	 *
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @param cliente
	 *            the cliente
	 * @return the iatx request data
	 */
	private IatxRequestData crearRequestData(DatosAltaTagMonederoEntity datosAltaTagMonedero, Cliente cliente) {

		Cuenta cuenta = MonederoUtils.obtenerCuentaMonedero(cliente);
		String ayn = ISBANStringUtils.fillStr(normalizarApellidoNombre(datosAltaTagMonedero), 24).substring(0, 24);

		IatxRequestData requestData = rellenarRequestData(datosAltaTagMonedero, cliente, cuenta, ayn);

		return requestData;
	}

	/**
	 * Rellenar request data.
	 *
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @param ayn
	 *            the ayn
	 * @return the iatx request data
	 */
	private IatxRequestData rellenarRequestData(DatosAltaTagMonederoEntity datosAltaTagMonedero, Cliente cliente,
			Cuenta cuenta, String ayn) {

		IatxRequestData requestData = new IatxRequestData(cliente);

		if (datosAltaTagMonedero.isAdicional()) {
			rellenarRequestDataIsAdicionalTrue(requestData, datosAltaTagMonedero, cliente, cuenta, ayn);
		} else {
			rellenarRequestDataIsAdicionalFalse(requestData, datosAltaTagMonedero, cliente, ayn);
		}

		return requestData;
	}

	/**
	 * Rellenar request data is adicional false.
	 *
	 * @param requestData
	 *            the request data
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @param cliente
	 *            the cliente
	 * @param ayn
	 *            the ayn
	 */
	private void rellenarRequestDataIsAdicionalFalse(IatxRequestData requestData,
			DatosAltaTagMonederoEntity datosAltaTagMonedero, Cliente cliente, String ayn) {
		requestData.addBodyValue(MonederoUtils.llenaConCeros(10));
		requestData.addBodyValue(MonederoUtils.llenaConBlancos(4));
		requestData.addBodyValue(MonederoUtils.llenaConBlancos(3));
		requestData.addBodyValue(MonederoUtils.llenaConBlancos(1));
		requestData.addBodyValue(MonederoUtils.llenaConBlancos(13));
		requestData.addBodyValue(MonederoUtils.llenaConBlancos(2));
		requestData.addBodyValue(MonederoUtils.llenaConBlancos(3));
		requestData.addBodyValue(ayn);
		requestData.addBodyValue(SECUENCIA_DOMICILIO);
		requestData.addBodyValue(SECUENCIA_TELEFONO);
		requestData.addBodyValue(StringUtils.leftPad(datosAltaTagMonedero.getLimiteMensualRecarga().trim(),
				LIMITE_MENSUAL_RECARGA_LENGTH, ZERO_STRING) + "00");
		requestData.addBodyValue(
				StringUtils.leftPad(datosAltaTagMonedero.getImporteARecargar().trim(), IMPORTE_DE_RECARGA, ZERO_STRING)
						+ "00");
		requestData.addBodyValue(datosAltaTagMonedero.getMarcaEmisoraTarjetaOrigenPrincipal());
		requestData.addBodyValue(datosAltaTagMonedero.getNroTarjetaOrigenRecargasPrincipal());
		requestData.addBodyValue(datosAltaTagMonedero.getMarcaEmisoraTarjetaOrigenSecundaria());
		requestData.addBodyValue(datosAltaTagMonedero.getNroTarjetaOrigenRecargasSecundaria());
		requestData.addBodyValue(cliente.getNup());
		requestData.addBodyValue(ayn);
		requestData.addBodyValue(cliente.getNup());
		requestData.addBodyValue(BONIFICACION);
		requestData.addBodyValue(ZERO_STRING);

	}

	/**
	 * Rellenar request data is adicional true.
	 *
	 * @param requestData
	 *            the request data
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @param ayn
	 *            the ayn
	 */
	private void rellenarRequestDataIsAdicionalTrue(IatxRequestData requestData,
			DatosAltaTagMonederoEntity datosAltaTagMonedero, Cliente cliente, Cuenta cuenta, String ayn) {
		requestData.addBodyValue(datosAltaTagMonedero.getCuentaPesosSeleccionada().substring(6,
				datosAltaTagMonedero.getCuentaPesosSeleccionada().length()));
		requestData.addBodyValue(APLIC_MONE);
		requestData.addBodyValue(cuenta.getNroSucursal().substring(1));
		requestData.addBodyValue(cuenta.getTipoCuenta().substring(1));
		requestData.addBodyValue(cuenta.getNroCuentaProducto().substring(3));
		requestData.addBodyValue(cuenta.getNroOrdenFirmante().substring(1));
		requestData.addBodyValue(datosAltaTagMonedero.getSucursal().substring(1));
		requestData.addBodyValue(ayn);
		requestData.addBodyValue(SECUENCIA_DOMICILIO);
		requestData.addBodyValue(SECUENCIA_TELEFONO);
		requestData.addBodyValue(StringUtils.leftPad(datosAltaTagMonedero.getLimiteMensualRecarga().trim(),
				LIMITE_MENSUAL_RECARGA_LENGTH, ZERO_STRING) + "00");
		requestData.addBodyValue(
				StringUtils.leftPad(datosAltaTagMonedero.getImporteARecargar().trim(), IMPORTE_DE_RECARGA, ZERO_STRING)
						+ "00");
		requestData.addBodyValue(MonederoUtils.llenaConBlancos(2));
		requestData.addBodyValue(MonederoUtils.llenaConBlancos(16));
		requestData.addBodyValue(MonederoUtils.llenaConBlancos(2));
		requestData.addBodyValue(MonederoUtils.llenaConBlancos(16));
		requestData.addBodyValue(cliente.getNup());
		requestData.addBodyValue(ayn);
		requestData.addBodyValue(datosAltaTagMonedero.getNup());
		requestData.addBodyValue(BONIFICACION);
		requestData.addBodyValue(ONE_STRING);

	}

	/**
	 * Normalizar apellido nombre.
	 *
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @return the string
	 */
	private String normalizarApellidoNombre(DatosAltaTagMonederoEntity datosAltaTagMonedero) {
		String apellidoNombre = datosAltaTagMonedero.getApellidoEmbozado() + " "
				+ datosAltaTagMonedero.getNombreEmbozado();
		return ISBANStringUtils.normalizarCampoAlfanumericoIatx(
				StringUtils.replaceEach(apellidoNombre, new String[] { "#", "?" }, new String[] { "Ñ", "Ñ" }), 24);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.dao.AltaTagMonederoDAO#
	 * generarComprobanteAltaTagMonedero(ar.com.santanderrio.obp.servicios.
	 * monedero.web.view.ComprobanteAltaTagMonederoView)
	 */
	@Override
	public Reporte generarComprobanteAltaTagMonedero(ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView) {
		Reporte reporte = new Reporte();

		DatosSolicitanteConfirmadoView datosSolicitanteConfirmadoView = comprobanteAltaTagMonederoView
				.getDatosSolicitanteConfirmadoView();

		try {

			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(DEBITAR_RECARGA_EN, datosSolicitanteConfirmadoView.getCuentaPesosSeleccionada());
			parameters.put(IMPORTE_A_RECARGAR, datosSolicitanteConfirmadoView.getImporteSeleccionado());
			parameters.put(LIMITE_RECARGA_MENSUAL, datosSolicitanteConfirmadoView.getLimiteSeleccionado());
			parameters.put(APELLIDO, datosSolicitanteConfirmadoView.getApellido());
			parameters.put(NOMBRE, datosSolicitanteConfirmadoView.getNombre());
			parameters.put(FECHA_NACIMIENTO, datosSolicitanteConfirmadoView.getFechaNacimiento());
			parameters.put(DNI, datosSolicitanteConfirmadoView.getDoc());
			parameters.put(PAIS_NACIMIENTO, datosSolicitanteConfirmadoView.getPaisNacimiento());
			parameters.put(SEXO, datosSolicitanteConfirmadoView.getSexo());
			parameters.put(ESTADO_CIVIL, datosSolicitanteConfirmadoView.getEstadoCivil());
			parameters.put(NACIONALIDAD, datosSolicitanteConfirmadoView.getNacionalidad());
			parameters.put(DOMICILIO, datosSolicitanteConfirmadoView.getDomicilio());
			parameters.put(NRO_COMPROBANTE, comprobanteAltaTagMonederoView.getComprobante());
			parameters.put(FECHA_OP, comprobanteAltaTagMonederoView.getFechaHora());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			String nombreReporte = "Comprobante_Alta_Monedero_" + comprobanteAltaTagMonederoView.getComprobante()
					+ PDF_EXTENSION;

			generarReporte(parameters, reporte, nombreReporte);

		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
		return reporte;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.dao.AltaTagMonederoDAO#
	 * generarTitularActivoMovimientos(ar.com.santanderrio.obp.servicios.
	 * monedero.web.view.MonederoMovimientosView)
	 */
	@Override
	public Reporte generarTitularActivoMovimientos(MonederoMovimientosView monederoMovimientoView) {
		Reporte reporte = new Reporte();

		try {

			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			String nombreReporte = "Reporte_titularActivoMovimientos" + PDF_EXTENSION;

			generarReporte(parameters, reporte, nombreReporte);

		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
		return reporte;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.dao.AltaTagMonederoDAO#
	 * activarMonederoTag(ar.com.santanderrio.obp.servicios.monedero.web.view.
	 * DatosParaActivacionView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public ComprobanteActivacionTagMonederoView activarMonederoTag(DatosParaActivacionView datosParaActivacionView,
			Cliente cliente) {

		LOGGER.info("{NumeroTarjeta:'XXXX 1234', Comprobante:'34231355', FechaHora:'23/12/2016'}");
		// METODO MOMENTANEO
		ComprobanteActivacionTagMonederoView comprobanteActivacionView = new ComprobanteActivacionTagMonederoView();
		comprobanteActivacionView.setComprobante("34231355");
		comprobanteActivacionView.setFechaHora(ISBANStringUtils.formatearFecha(new Date()));
		comprobanteActivacionView.setMensaje(
				"<p>¡Listo!</p><p>La activación de tu <b>Monedero Tag XXXX-1234</b><br> se realizo con éxito. </p>");
		comprobanteActivacionView.setLegalesSEO("<p>Conserve este ticket como </p>comprobante S.E.U.O.");
		return comprobanteActivacionView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.dao.AltaTagMonederoDAO#
	 * generarComprobanteActivacionTagMonedero(ar.com.santanderrio.obp.servicios
	 * .monedero.web.view.ComprobanteActivacionTagMonederoView)
	 */
	@Override
	public Reporte generarComprobanteActivacionTagMonedero(
			ComprobanteActivacionTagMonederoView comprobanteActivacionTagMonederoView) {
		Reporte reporte = new Reporte();

		try {
			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEFAULT, logoDefault.getFile().getPath());
			parameters.put(NRO_MONEDERO_TAG, comprobanteActivacionTagMonederoView.getNroTagMonedero());
			parameters.put(NRO_COMPROBANTE, comprobanteActivacionTagMonederoView.getComprobante());
			parameters.put(FECHA_OP, comprobanteActivacionTagMonederoView.getFechaHora());
			parameters.put(FECHA_OP, comprobanteActivacionTagMonederoView.getLegalesSEO());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			String nombreReporte = "Comprobante_Activacion_Monedero_"
					+ comprobanteActivacionTagMonederoView.getComprobante() + PDF_EXTENSION;

			generarReporte(parameters, reporte, nombreReporte);

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
	 * Generar reporte.
	 *
	 * @param parameters
	 *            the parameters
	 * @param reporte
	 *            the reporte
	 * @param nombreReporte
	 *            the nombre reporte
	 * @throws JRException
	 *             the JR exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void generarReporte(HashMap<String, Object> parameters, Reporte reporte, String nombreReporte)
			throws JRException, IOException {

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasper.getInputStream());
		jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

		// se crea el archivo PDF
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

		byte[] byteArray = outStream.toByteArray();
		reporte.setBytes(byteArray);
		reporte.setNombre(nombreReporte);
		reporte.setTipoArchivo(TipoArchivoEnum.PDF);
	}

}
