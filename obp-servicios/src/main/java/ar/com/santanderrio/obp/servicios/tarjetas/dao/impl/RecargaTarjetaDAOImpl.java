/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.constants.ConstantsTR;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.RecargaTarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobanteRecargaTarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosRecargaTREntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.TarjetaRecargableEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class RecargaTarjetaDAOImpl.
 */
@Component
public class RecargaTarjetaDAOImpl implements RecargaTarjetaDAO {

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** signo $. */
	private final static String SIGNO_PESOS = "$";
	/** The servicio pag tjc. */
	@Value("${SERVICIO.PREFIJO.RECTARJREC}")
	private String servicioRecTjr;

	/** The version pag tjc. */
	@Value("${SERVICIO.VERSION.RECTARJREC}")
	private String versionRecTjr;

	/** The servicio pag tjc. */
	@Value("${SERVICIO.PREFIJO.CMBADHETAR}")
	private String servicioCmbadhetar;

	/** The version pag tjc. */
	@Value("${SERVICIO.VERSION.CMBADHETAR_110}")
	private String versionCmbadhetar;

	/** The Constant ZERO_STR. */
	private static final String ZERO_STR = "0";

	/** The Constant PAD_CHAR. */
	private static final String PAD_CHAR = ZERO_STR;

	/** The Constant LONGITUD_LNG_IMPORTE_RECARGA. */
	private static final int LONGITUD_LNG_IMPORTE_RECARGA = 12;

	/** The Constant LONGITUD_LNG_SUCURSAL_CTA_DESTINO. */
	private static final int LONGITUD_LNG_SUCURSAL_CTA_DESTINO = 4;

	/** The Constant LONGITUD_LNG_NRO_CTA_DESTINO. */
	private static final int LONGITUD_LNG_NRO_CTA_DESTINO = 10;

	/** The Constant LONGITUD_LNG_SUCURSAL_CTA_ORIGEN. */
	private static final int LONGITUD_LNG_SUCURSAL_CTA_ORIGEN = 4;

	/** The Constant LONGITUD_LNG_NRO_CTA_ORIGEN. */
	private static final int LONGITUD_LNG_NRO_CTA_ORIGEN = 12;

	/** The Constant LONGITUD_LNG_3. */
	private static final int LONGITUD_LNG_3 = 3;

	/** The Constant LONGITUD_LNG_NRO_TARJETA. */
	private static final int LONGITUD_LNG_NRO_TARJETA = 16;

	/** The Constant LONGITUD_LNG_7. */
	private static final int LONGITUD_LNG_7 = 7;

	/** The Constant LONGITUD_LNG_8. */
	private static final int LONGITUD_LNG_8 = 8;

	/** The Constant LONGITUD_LNG_2. */
	private static final int LONGITUD_LNG_2 = 2;

	/** The Constant CODIGO_RETORNO_OK. */
	private static final int CODIGO_RETORNO_OK = 0;

	/** The Constant COD_ERROR_CERO. */
	private static final int COD_ERROR_CERO = 00000000;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RecargaTarjetaDAOImpl.class);

	/** The Constant ESTADO_OK. */
	public static final String ESTADO_OK = "OK";

	/** The Constant ESTADO_ERROR. */
	public static final String ESTADO_ERROR = "ERROR";

	/** The Constant TIMEOUT_EXCEPTION. */
	private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant LOGO_DEFAULT. */
	private static final String LOGO_DEFAULT = "LOGO_DEFAULT";

	/** The Constant TITULO_OPERACION. */
	private static final String TITULO_OPERACION = "TITULO_OPERACION";

	/** The Constant TARJETA_RECARGABLE. */
	private static final String TARJETA_RECARGABLE = "TARJETA_RECARGABLE";

	/** The Constant IMPORTE_RECARGADO. */
	private static final String IMPORTE_RECARGADO = "IMPORTE_RECARGADO";

	/** The Constant FECHA_INICIO_RECARGA. */
	private static final String FECHA_INICIO_RECARGA = "FECHA_INICIO_RECARGA";

	/** The Constant FECHA_PROXIMA_EJECUCION. */
	private static final String FECHA_PROXIMA_EJECUCION = "FECHA_PROXIMA_EJECUCION";

	/** The Constant FRECUENCIA. */
	private static final String FRECUENCIA = "FRECUENCIA";

	/** The Constant NRO_CUENTA_ORIGEN. */
	private static final String NRO_CUENTA_ORIGEN = "NRO_CUENTA_ORIGEN";

	/** The Constant TIPO_CUENTA_ORIGEN. */
	private static final String TIPO_CUENTA_ORIGEN = "TIPO_CUENTA_ORIGEN";

	/** The Constant FECHA_DE_RECARGA. */
	private static final String FECHA_DE_RECARGA = "FECHA_DE_RECARGA";

	/** The Constant COMISION_TOTAL_IVA. */
	private static final String COMISION_TOTAL_IVA = "COMISION_TOTAL_IVA";

	/** The Constant COMISION. */
	private static final String COMISION = "COMISION";

	/** The Constant IVA. */
	private static final String IVA = "IVA";

	/** The Constant IMPORTE_ACREDITADO. */
	private static final String IMPORTE_ACREDITADO = "IMPORTE_ACREDITADO";

	/** The Constant NRO_COMPROBANTE. */
	private static final String NRO_COMPROBANTE = "NRO_COMPROBANTE";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/** The Constant TIPOCTA_VISA. */
	private static final String TIPOCTA_VISA = "07";

	/** The Constant FECHA_HORA_OPERACION. */
	private static final String FECHA_HORA_OPERACION = "FECHA_HORA_OPERACION";

	/** The file jasper. */
	@Value("classpath:/report/recargas/ComprobanteRecarga.jasper")
	private Resource fileJasper;

	/** The file jasper agendar. */
	@Value("classpath:/report/recargas/ComprobanteRecargaAgendar.jasper")
	private Resource fileJasperAgendar;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo default. */
	@Value("classpath:/report/comprobantes/logo_visa.png")
	private Resource logoDefault;

	/** The texto footer. */
	private static String textoFooter = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.dao.RecargaTarjetaDAO#recargar
	 * (ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosRecargaTREntity)
	 */
	@Override
	public ComprobanteRecargaTarjetaEntity recargar(Cliente cliente, DatosRecargaTREntity datosRecargaTR)
			throws DAOException {
		IatxRequest request = buildIatxRequestRecTjr(cliente, datosRecargaTR);
		return buildResponseRecTjr(request);
	}

	/**
	 * Builds the response rec tjr.
	 *
	 * @param request
	 *            the request
	 * @return the comprobante recarga tarjeta entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ComprobanteRecargaTarjetaEntity buildResponseRecTjr(IatxRequest request) throws DAOException {
		ComprobanteRecargaTarjetaEntity comprobanteRecargaTarjeta = new ComprobanteRecargaTarjetaEntity();
		try {
			IatxResponse iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getErrorCode() == 0) {
				comprobanteRecargaTarjeta = obtenerDatosDeRecargaTarjeta(iatxResponse);
				return comprobanteRecargaTarjeta;
			} else {
				throw new DAOException(String.valueOf(iatxResponse.getErrorCode()));
			}
		} catch (IatxException e) {
			if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
				String timeOutException = "TimeOutException. Se envio la transaccion al CICS pero no se recibio respuesta";
				LOGGER.error(timeOutException);
				throw new DAOException(timeOutException, "TIME_OUT");
			}
			LOGGER.error(ConstantsTR.ERROR, e);
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Builds the response cmbadhetar.
	 *
	 * @param request
	 *            the request
	 * @return the comprobante recarga tarjeta entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ComprobanteRecargaTarjetaEntity buildResponseCmbadhetar(IatxRequest request) throws DAOException {
		try {
			IatxResponse iatxResponse = iatxComm.exec(request);
			int codigoDeRetorno = iatxResponse.getErrorCode();
			if (CODIGO_RETORNO_OK == codigoDeRetorno) {
				ComprobanteRecargaTarjetaEntity datosFeedback = new ComprobanteRecargaTarjetaEntity();
				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
				String fechaHoraSinFormato = iatxResponse.getFechaHoraReq();
				Date fechaHora = getFormatDate(fechaHoraSinFormato);
				datosFeedback.setNroComprobante(iatxResponse.getNroComprobante());
				datosFeedback.setFechaHora(dateFormatter.format(fechaHora));
				datosFeedback.setFechaDeProxRecarga(formatearFechaIatx(iatxResponse.getData(6)));
				return datosFeedback;

			} else {
				LOGGER.error("Error en Iatx:" + iatxResponse.getErrorCode());
				throw new DAOException(String.valueOf(codigoDeRetorno));
			}
		} catch (IatxException e) {
			if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
				String timeOutException = "TimeOutException. Se envio la transaccion al CICS pero no se recibio respuesta";
				LOGGER.error(timeOutException);
				throw new DAOException(timeOutException, "TIME_OUT");
			}
			LOGGER.error(ConstantsTR.ERROR, e);
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Gets the format date.
	 *
	 * @param yyyyMMddHHmmss
	 *            the yyyy M mdd H hmmss
	 * @return the format date
	 */
	private Date getFormatDate(String yyyyMMddHHmmss) {
		try {
			SimpleDateFormat formatoFechaHoraTrama = new SimpleDateFormat(ConstantsTR.DATE_PATERNyyyyMMddHHmmss);
			return formatoFechaHoraTrama.parse(yyyyMMddHHmmss);
		} catch (java.text.ParseException ex) {
			LOGGER.error(ConstantsTR.ERROR, ex);
			return null;
		}
	}

	/**
	 * Formatear fecha iatx.
	 *
	 * @param yyyyMMdd
	 *            the yyyy M mdd
	 * @return the string
	 */
	private String formatearFechaIatx(String yyyyMMdd) {

		String anio = yyyyMMdd.substring(0, 4);
		String mes = yyyyMMdd.substring(4, 6);
		String dia = yyyyMMdd.substring(6);

		return dia + "/" + mes + "/" + anio;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.RecargaTarjetaDAO#
	 * programarRecarga(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.tarjetas.entities.
	 * TarjetaRecargableEntity)
	 */
	@Override
	public ComprobanteRecargaTarjetaEntity programarRecarga(Cliente cliente, TarjetaRecargableEntity trEntity)
			throws DAOException, ServiceException {

		IatxRequest request = buildIatxRequestCmbadhetar(cliente, trEntity);
		return buildResponseCmbadhetar(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.RecargaTarjetaDAO#
	 * generarComprobanteRecarga(ar.com.santanderrio.obp.servicios.tarjetas.web.
	 * view.ComprobanteRecargaTarjetaView)
	 */
	@Override
	public Reporte generarComprobanteRecarga(ComprobanteRecargaTarjetaView comprobanteRecargaTarjetaView) {
		Reporte reporte = new Reporte();

		try {
			// se carga el reporte
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasper.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEFAULT, logoDefault.getFile().getPath());

			parameters.put(TARJETA_RECARGABLE, ISBANStringUtils
					.mascaraTarjetaCredito(comprobanteRecargaTarjetaView.getNumeroTarjetaDestino(), TIPOCTA_VISA));
			parameters.put(IMPORTE_RECARGADO, SIGNO_PESOS + comprobanteRecargaTarjetaView.getMonto()); // Viene
																										// con
																										// $

			String sucursal = ISBANStringUtils
					.formatearSucursal(comprobanteRecargaTarjetaView.getSucursalCuentaOrigen());
			String numeroCuenta = ISBANStringUtils
					.formatearNumeroCuenta(comprobanteRecargaTarjetaView.getNroCuentaOrigen());

			parameters.put(NRO_CUENTA_ORIGEN, sucursal + "-" + numeroCuenta);
			parameters.put(TIPO_CUENTA_ORIGEN, comprobanteRecargaTarjetaView.getTipoCuentaOrigen());
			parameters.put(FECHA_DE_RECARGA, comprobanteRecargaTarjetaView.getFechaHora());
			parameters.put(COMISION_TOTAL_IVA, comprobanteRecargaTarjetaView.getComisionTotalIVAIncluido());
			parameters.put(COMISION, comprobanteRecargaTarjetaView.getComision());
			parameters.put(IVA, "$ " + comprobanteRecargaTarjetaView.getIva());
			parameters.put(IMPORTE_ACREDITADO, comprobanteRecargaTarjetaView.getImporteAcreditado());
			parameters.put(NRO_COMPROBANTE, comprobanteRecargaTarjetaView.getNroComprobante());
			parameters.put(FECHA_HORA_OPERACION, ISBANStringUtils.formatearFecha(new Date(), "dd/MM/yyyy HH:mm"));
			parameters.put(textoFooter, textoFooter);
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre(
					"Comprobante_Recarga_" + comprobanteRecargaTarjetaView.getNroDeComprobante() + PDF_EXTENSION);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.RecargaTarjetaDAO#
	 * generarComprobanteRecargaAgendar(ar.com.santanderrio.obp.servicios.
	 * tarjetas.entities.ComprobanteRecargaTarjetaEntity)
	 */
	@Override
	public Reporte generarComprobanteRecargaAgendar(ComprobanteRecargaTarjetaEntity comprobanteRecargaTarjetaView) {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(TITULO_OPERACION, "Comprobante de agendamiento de recarga");
		return generarComprobanteRecarga(comprobanteRecargaTarjetaView, parameters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.RecargaTarjetaDAO#
	 * generarComprobanteRecargaModificarAgendar(ar.com.santanderrio.obp.
	 * servicios.tarjetas.entities.ComprobanteRecargaTarjetaEntity)
	 */
	@Override
	public Reporte generarComprobanteRecargaModificarAgendar(
			ComprobanteRecargaTarjetaEntity comprobanteRecargaTarjeta) {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(TITULO_OPERACION, "Comprobante de modificación de recarga automática");

		return generarComprobanteRecarga(comprobanteRecargaTarjeta, parameters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.RecargaTarjetaDAO#
	 * generarComprobanteRecargaStopDebitAgendar(ar.com.santanderrio.obp.
	 * servicios.tarjetas.entities.ComprobanteRecargaTarjetaEntity)
	 */
	@Override
	public Reporte generarComprobanteRecargaStopDebitAgendar(
			ComprobanteRecargaTarjetaEntity comprobanteRecargaTarjeta) {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(TITULO_OPERACION, "Comprobante de stop-debit de recarga automática");
		return generarComprobanteRecarga(comprobanteRecargaTarjeta, parameters);
	}

	/**
	 * Generar comprobante recarga.
	 *
	 * @param comprobanteRecargaTarjeta
	 *            the comprobante recarga tarjeta
	 * @param parameters
	 *            the parameters
	 * @return the reporte
	 */
	private Reporte generarComprobanteRecarga(ComprobanteRecargaTarjetaEntity comprobanteRecargaTarjeta,
			HashMap<String, Object> parameters) {
		Reporte reporte = new Reporte();

		try {
			// se carga el reporte
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasperAgendar.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			// se procesa el archivo jasper
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEFAULT, logoDefault.getFile().getPath());

			parameters.put(TARJETA_RECARGABLE, comprobanteRecargaTarjeta.getNumeroTarjeta());
			parameters.put(IMPORTE_RECARGADO, SIGNO_PESOS + comprobanteRecargaTarjeta.getImporteAcreditado()); // Viene
																												// con
																												// $
			String sucursal = ISBANStringUtils.formatearSucursal(comprobanteRecargaTarjeta.getSucursalCuentaOrigen());
			String numeroCuenta = ISBANStringUtils
					.formatearNumeroCuenta(comprobanteRecargaTarjeta.getNroCuentaOrigen());
			parameters.put(NRO_CUENTA_ORIGEN, sucursal + "-" + numeroCuenta);
			parameters.put(TIPO_CUENTA_ORIGEN, comprobanteRecargaTarjeta.getTipoCuentaOrigen());
			parameters.put(FECHA_INICIO_RECARGA, comprobanteRecargaTarjeta.getFechaInicio()); // Viene
																								// con
																								// $
			parameters.put(FECHA_PROXIMA_EJECUCION, comprobanteRecargaTarjeta.getFechaDeProxRecarga()); // Viene
																										// con
																										// $
			parameters.put(FRECUENCIA, comprobanteRecargaTarjeta.getFrecuencia()); // Viene
																					// con
																					// $
			parameters.put(NRO_COMPROBANTE, comprobanteRecargaTarjeta.getNroComprobante());

			parameters.put(FECHA_HORA_OPERACION, ISBANStringUtils.formatearFecha(new Date(), "dd/MM/yyyy HH:mm"));
			parameters.put(textoFooter, textoFooter);
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre(
					"Comprobante_Recarga_Programada" + comprobanteRecargaTarjeta.getNroComprobante() + PDF_EXTENSION);
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
	 * Builds the iatx request rec tjr.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosRecargaTR
	 *            the datos recarga TR
	 * @return the iatx request
	 */
	private IatxRequest buildIatxRequestRecTjr(Cliente cliente, DatosRecargaTREntity datosRecargaTR) {
		IatxRequest iatxRequest = new IatxRequest(servicioRecTjr, versionRecTjr);
		iatxRequest.setData(generarRequestDataRecTjr(cliente, datosRecargaTR));
		return iatxRequest;
	}

	/**
	 * Builds the iatx request cmbadhetar.
	 *
	 * @param cliente
	 *            the cliente
	 * @param trEntity
	 *            the tr entity
	 * @return the iatx request
	 * @throws ServiceException
	 *             the service exception
	 */
	private IatxRequest buildIatxRequestCmbadhetar(Cliente cliente, TarjetaRecargableEntity trEntity)
			throws ServiceException {
		IatxRequest iatxRequest = new IatxRequest(servicioCmbadhetar, versionCmbadhetar);
		iatxRequest.setData(generarRequestDataCmbadhetar(cliente, trEntity));
		return iatxRequest;
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
	 * Obtener datos de recarga tarjeta.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the comprobante recarga tarjeta entity
	 */
	private ComprobanteRecargaTarjetaEntity obtenerDatosDeRecargaTarjeta(IatxResponse iatxResponse) {
		ComprobanteRecargaTarjetaEntity comprobanteRecargaTarjeta = new ComprobanteRecargaTarjetaEntity();
		comprobanteRecargaTarjeta.setSaldoDisponible(iatxResponse.getNextData().trim());
		comprobanteRecargaTarjeta.setComision(iatxResponse.getNextData().trim());
		comprobanteRecargaTarjeta.setIva(iatxResponse.getNextData().trim());
		comprobanteRecargaTarjeta.setComisionTotal(iatxResponse.getNextData().trim());
		comprobanteRecargaTarjeta.setSaldoFinal(iatxResponse.getNextData().trim());
		comprobanteRecargaTarjeta.setNroComprobante(iatxResponse.getNextData().trim());
		comprobanteRecargaTarjeta.setImporteAcreditado(iatxResponse.getNextData().trim());
		LOGGER.info("Una vez completo, se devuelve el objeto");
		return comprobanteRecargaTarjeta;
	}

	/**
	 * Generar request data rec tjr.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosRecargaTR
	 *            the datos recarga TR
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequestDataRecTjr(Cliente cliente, DatosRecargaTREntity datosRecargaTR) {
		IatxRequestData requestData = new IatxRequestData(cliente);

		BigDecimal bigImporteRecargaTR = convertirImporte(datosRecargaTR.getImporte());
		String importeRecargaTR = ISBANStringUtils.ajustadorBigDecimalIatx(bigImporteRecargaTR,
				LONGITUD_LNG_IMPORTE_RECARGA);
		requestData.addBodyValue(importeRecargaTR);

		String sucursalCuentaDestino = StringUtils.leftPad(datosRecargaTR.getSucursalCuentaDestino(),
				LONGITUD_LNG_SUCURSAL_CTA_DESTINO, PAD_CHAR);
		requestData.addBodyValue(sucursalCuentaDestino);

		String tipoCuentaDestino = datosRecargaTR.getTipoCuentaDestino();
		requestData.addBodyValue(tipoCuentaDestino);

		String nroCuentaDestino = StringUtils.right(datosRecargaTR.getNroCuentaDestino(), LONGITUD_LNG_NRO_CTA_DESTINO);
		requestData.addBodyValue(nroCuentaDestino);

		String sucursalCuentaOrigen = StringUtils.leftPad(datosRecargaTR.getSucursalCuentaOrigen(),
				LONGITUD_LNG_SUCURSAL_CTA_ORIGEN, PAD_CHAR);
		requestData.addBodyValue(sucursalCuentaOrigen);

		String tipoCuentaOrigen = ConstantsTR.getTipoCuentaOrigen(datosRecargaTR.getTipoCuentaOrigenAbreviatura());
		requestData.addBodyValue(tipoCuentaOrigen);

		String nroCuentaOrigen = StringUtils.leftPad(datosRecargaTR.getNroCuentaOrigen(), LONGITUD_LNG_NRO_CTA_ORIGEN,
				PAD_CHAR);
		requestData.addBodyValue(nroCuentaOrigen);

		String divisa = StringUtils.leftPad(datosRecargaTR.getDivisa(), LONGITUD_LNG_3, PAD_CHAR);
		requestData.addBodyValue(divisa);

		String nroTarjeta = datosRecargaTR.getNroTarjeta()
				.substring(datosRecargaTR.getNroTarjeta().length() - LONGITUD_LNG_NRO_TARJETA);
		requestData.addBodyValue(nroTarjeta);

		return requestData;

	}

	/**
	 * Generar request data cmbadhetar.
	 *
	 * @param cliente
	 *            the cliente
	 * @param trEntity
	 *            the tr entity
	 * @return the iatx request data
	 * @throws ServiceException
	 *             the service exception
	 */
	private IatxRequestData generarRequestDataCmbadhetar(Cliente cliente, TarjetaRecargableEntity trEntity)
			throws ServiceException {
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(trEntity.getTipoTarjeta());
		requestData
				.addBodyValue(llenarConCerosIzqOTruncar(trEntity.getNroCuentaTarjeta(), LONGITUD_LNG_NRO_CTA_DESTINO));
		requestData.addBodyValue(trEntity.getTipoCuentaDebito());
		if (trEntity.getSucursalCuentaDebito() != null
				&& trEntity.getSucursalCuentaDebito().length() > LONGITUD_LNG_3) {
			requestData.addBodyValue(trEntity.getSucursalCuentaDebito()
					.substring(trEntity.getSucursalCuentaDebito().length() - LONGITUD_LNG_3));
		} else {
			requestData.addBodyValue(StringUtils.leftPad(trEntity.getSucursalCuentaDebito(), LONGITUD_LNG_3, PAD_CHAR));
		}
		requestData.addBodyValue(StringUtils.leftPad(trEntity.getNroCuentaDebito(), LONGITUD_LNG_7, PAD_CHAR));
		requestData.addBodyValue(StringUtils.leftPad(trEntity.getNroFirmante(), LONGITUD_LNG_3, PAD_CHAR));
		requestData
				.addBodyValue(StringUtils.leftPad(String.valueOf(trEntity.getFormaPago()), LONGITUD_LNG_2, ZERO_STR));

		BigDecimal bigImporteRecargaTR = convertirImporte(trEntity.getImporteAgendamiento());
		String importeRecargaTR = ISBANStringUtils
				.formateadorConCerosIzq(String.valueOf(bigImporteRecargaTR.longValue()), 8);
		requestData.addBodyValue(importeRecargaTR);

		requestData.addBodyValue(ISBANStringUtils.formatearFechaIATX(trEntity.getFechaProximoAgendamiento()));
		return requestData;

	}

	/**
	 * Llenar con ceros izq O trunca.
	 *
	 * @param s
	 *            the s
	 * @param size
	 *            the size
	 * @return the string
	 */
	public String llenarConCerosIzqOTruncar(String s, int size) {
		int l = s.length();
		if (l >= size) {
			return s.substring(l - size, l);
		}
		StringBuffer ceros = new StringBuffer();
		for (int n = 0; n < size - l; ++n) {
			ceros.append(ZERO_STR);
		}
		return ceros + s;
	}

	/**
	 * Convertir importe.
	 *
	 * @param importe
	 *            the importe
	 * @return the big decimal
	 */
	public static BigDecimal convertirImporte(String importe) {
		try {
			return ISBANStringUtils.convertirImporte(importe);
		} catch (ImporteConvertException e) {
			return null;
		}
	}

}
