/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
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
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteAdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagoautomatico.dao.PagoAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.Accion;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class PagoAutomaticoDAOImpl.
 */
@Component
public class PagoAutomaticoDAOImpl implements PagoAutomaticoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagoAutomaticoDAOImpl.class);

	/** The Constant DIECINUEVE_ENTERO. */
	private static final int DIECINUEVE_ENTERO = 19;

	/** The Constant CUATRO_ENTERO. */
	private static final int CUATRO_ENTERO = 4;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The file jasper. */
	@Value("classpath:/report/adhesionDebito/ComprobanteAdhesionDebitoAutomatico.jasper")
	private Resource fileJasper;

    /** The file jasper. */
    @Value("classpath:/report/adhesionPagoAutomatico/ComprobanteAdhesionPagoAutomatico.jasper")
    private Resource fileJasperComprobantePagoAutomatico;
    
    /** The logo logoCierrePagoMisCuentas. */
    @Value("classpath:/report/comprobantes/logo-pago-mis-cuentas.png")
    private Resource logoCierrePagoMisCuentas;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;
	
	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo default. */
	@Value("classpath:/report/comprobantes/logo_default.png")
	private Resource logoDefault;

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant LOGO_DEFAULT. */
	private static final String LOGO_DEFAULT = "LOGO_DEFAULT";

	/** The Constant EMPRESA. */
	private static final String EMPRESA = "EMPRESA";

	/** The Constant NUMERO_PAGO. */
	private static final String NUMERO_PAGO = "NUMERO_PAGO";

	/** The Constant NOMBRE_PAGO. */
	private static final String NOMBRE_PAGO = "NOMBRE_PAGO";

	/** The Constant PES_IDENTIFICACION. */
	private static final String PES_IDENTIFICACION = "PES_IDENTIFICACION";

	/** The Constant NRO_CUENTA. */
	private static final String NRO_CUENTA = "NRO_CUENTA";

	/** The Constant LIMITE. */
	private static final String LIMITE = "LIMITE";

	/** The Constant NRO_COMP. */
	private static final String NRO_COMP = "NRO_COMPROBANTE";

	/** The Constant FECHA_OP. */
	private static final String FECHA_OP = "FECHA_OPERACION";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant POSICION_SISTEMA_ASOCIADO_AL_ERROR. */
	private static final int POSICION_SISTEMA_ASOCIADO_AL_ERROR = 1;

	/** The Constant POSICION_CANTIDAD_DESCRIPCIONES. */
	private static final int POSICION_CANTIDAD_DESCRIPCIONES = 2;

	/** The Constant POSICION_INICIO_DESCRIPCIONES. */
	private static final int POSICION_INICIO_DESCRIPCIONES = 3;

	/** The Constant NOMBRE_SERVICIO_ALTPAUADHE. */
	private static final String NOMBRE_SERVICIO_ALTPAUADHE = "ALTPAUADHE";

	/** The Constant VERSION_SERVICIO_ALTPAUADHE. */
	private static final String VERSION_SERVICIO_ALTPAUADHE = "100";

	/** The Constant NOMBRE_SERVICIO_ALTPESADHE. */
	private static final String NOMBRE_SERVICIO_ALTPESADHE = "ALTPESADHE";

	/** The Constant VERSION_SERVICIO_. */
	private static final String VERSION_SERVICIO_ALTPESADHE = "110";

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant CODIGO_CLIENTE_EMPRESA_PAD. */
	private static final int CODIGO_CLIENTE_EMPRESA_PAD = 19;

	/** The Constant LIMITE_ADHESION_CANTIDAD_DIGITOS. */
	private static final int LIMITE_ADHESION_CANTIDAD_DIGITOS = 14;

	/** The Constant NUMERO_CUENTA_DEBITO_CANTIDAD_DIGITOS. */
	private static final int NUMERO_CUENTA_DEBITO_CANTIDAD_DIGITOS = 19;

	/** The Constant TIPO_CUENTA_CANTIDAD_DIGITOS. */
	private static final int TIPO_CUENTA_CANTIDAD_DIGITOS = 2;

	/** The Constant TIMEOUT_EXCEPTION. */
	public static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";
	
	/** The Constant EMPRESA. */
	private static final String LABEL_FANTASIA = "LABEL_FANTASIA";
	
	/** The Constant LOGO_CIERRE_PAGO_MIS_CUENTAS. */
	private static final String LOGO_CIERRE_PAGO_MIS_CUENTAS = "LOGO_CIERRE_PAGO_MIS_CUENTAS";
	
	
	
	
	
	
	
	/**  ahesion pago automatico*/
	
	/**
	 * genera el reporte de Jasper correspondiente.
	 *
	 * @param comprobanteDebitoAutomatico
	 *            the comprobante debito automatico
	 * @return the reporte
	 */
	@Override
	public Reporte generarComprobanteAdhesion(ComprobanteDebitoAutomatico comprobanteDebitoAutomatico) {
		Reporte reporte = new Reporte();

		try {
			// se carga el reporte
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasper.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEFAULT, logoDefault.getFile().getPath());

			if ("0".equals(comprobanteDebitoAutomatico.getLimite())) {
				comprobanteDebitoAutomatico.setLimite("Sin límite");
			} else {
				BigDecimal importeLimite = new BigDecimal(comprobanteDebitoAutomatico.getLimite());
				comprobanteDebitoAutomatico.setLimite("$ "+ISBANStringUtils.formatearSaldo(importeLimite));
			}

			parameters.put(EMPRESA, comprobanteDebitoAutomatico.getEmpresa());
			parameters.put(NUMERO_PAGO, comprobanteDebitoAutomatico.getNumeroMedioDePago());
			parameters.put(NOMBRE_PAGO, comprobanteDebitoAutomatico.getNombreMedioDePago());
			parameters.put(PES_IDENTIFICACION,
					WordUtils.capitalizeFully(comprobanteDebitoAutomatico.getPesIdentificacion()));
			parameters.put(NRO_CUENTA, comprobanteDebitoAutomatico.getCodigoPagoElectronico());
			parameters.put(LIMITE,comprobanteDebitoAutomatico.getLimite());
			parameters.put(NRO_COMP, comprobanteDebitoAutomatico.getComprobante());
			parameters.put(FECHA_OP, FechaUtils.obtenerFechaYHoraActual());
 
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			if (!ISBANStringUtils.isEmptyOrNull(comprobanteDebitoAutomatico.getLabelFantasia())) {
				parameters.put(LABEL_FANTASIA, comprobanteDebitoAutomatico.getLabelFantasia());
			}else{
				parameters.put(LABEL_FANTASIA, "NRO DE SOCIO");
			}
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre(
					"Comprobante_Adhesion_" + comprobanteDebitoAutomatico.getComprobante() + PDF_EXTENSION);
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
	 * Parsear response error.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param iatxResponse
	 *            the iatx response
	 */
	private void parsearResponseError(PagoPendiente pagoPendiente, IatxResponse iatxResponse) {
		pagoPendiente.getDatosPagoAutomatico().setDescripcionesDeError(new ArrayList<String>());
		pagoPendiente.getDatosPagoAutomatico()
				.setSistemaAsociadoAlError(iatxResponse.getData(POSICION_SISTEMA_ASOCIADO_AL_ERROR));
		int cantidadMensajes = Integer.parseInt(iatxResponse.getData(POSICION_CANTIDAD_DESCRIPCIONES));

		for (int i = POSICION_INICIO_DESCRIPCIONES; i < cantidadMensajes + POSICION_INICIO_DESCRIPCIONES; i++) {
			pagoPendiente.getDatosPagoAutomatico().getDescripcionesDeError().add(iatxResponse.getData(i));
		}

	}

	/**
	 * Generar iatx request data eliminar pago puntual.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @param accion
	 *            the accion
	 * @return the iatx request data
	 */
	private IatxRequestData generarIatxRequestDataEliminarPagoPuntual(PagoPendiente pagoPendiente, Cliente cliente,
			Accion accion) {

		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(accion.getNombreAccion());
		requestData.addBodyValue(StringUtils.rightPad(pagoPendiente.getCodigoEmpresa(), 4));
		requestData.addBodyValue(StringUtils.rightPad(pagoPendiente.getCodigoClienteEmpresa(), 19));

		return requestData;
	}

	/**
	 * Generar iatx request data alta pago.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 * @param accion
	 *            the accion
	 * @return the iatx request data
	 */
	private IatxRequestData generarIatxRequestDataAltaBaja(Cliente cliente,
			AdhesionPagoAutomatico adhesionPagoAutomatico, String accion) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(accion);
		requestData.addBodyValue(
				StringUtils.rightPad(adhesionPagoAutomatico.getFiid().trim(), CUATRO_ENTERO, StringUtils.EMPTY));
		requestData.addBodyValue(StringUtils.rightPad(adhesionPagoAutomatico.getCodigoPagoElectronico().trim(),
				DIECINUEVE_ENTERO, StringUtils.EMPTY));
		return requestData;
	}

	/**
	 * Request data and reponse.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @param accion
	 *            the accion
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Respuesta<ResultadoTransaccion> requestDataAndReponse(PagoPendiente pagoPendiente, Cliente cliente,
			Accion accion) throws DAOException {
		String servicioAltpesadhe = "ALTPESADHE";
		String versionAltpesadhe = "110";
		IatxRequest iatxRequest = new IatxRequest(servicioAltpesadhe, versionAltpesadhe);
		try {
			Respuesta<ResultadoTransaccion> respuestaResultado = new Respuesta<ResultadoTransaccion>();
			ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
			IatxRequestData iatxRequestData = generarIatxRequestDataEliminarPagoPuntual(pagoPendiente, cliente, accion);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				resultadoTransaccion.setNumeroComprobante(iatxResponse.getNroComprobante());
				respuestaResultado.setEstadoRespuesta(EstadoRespuesta.OK);
				return respuestaResultado;
			} else {
				parsearResponseError(pagoPendiente, iatxResponse);
				respuestaResultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
				return respuestaResultado;
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagoautomatico.dao.PagoAutomaticoDAO#
	 * eliminarPagoPuntual(ar.com.santanderrio.obp.servicios.pagos.entities.
	 * PagoPendiente,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> eliminarPagoPuntual(PagoPendiente pagoPendiente, Cliente cliente)
			throws DAOException {
		return requestDataAndReponse(pagoPendiente, cliente, Accion.BAJA);
	}

	/**
	 * Accion baja adhesion de Pago Automatico DTF: 9802 relacionado con 9817,
	 * 10335 boolean ejecutarBajaAdhesion(PagoPendiente pagoPendiente, Cliente
	 * cliente, Cuenta cuentaDebito) throws DAOException;.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */

	@Override
	public ResultadoTransaccion ejecutarBajaAdhesion(PagoPendiente pagoPendiente, Cliente cliente) throws DAOException {

		IatxRequest iatxRequest = new IatxRequest(NOMBRE_SERVICIO_ALTPAUADHE, VERSION_SERVICIO_ALTPAUADHE);
		IatxRequestData iatxRequestData = bajaAdhesionRequestData(cliente, pagoPendiente,
				pagoPendiente.getDatosPagoAutomatico().getTope());

		iatxRequest.setData(iatxRequestData);
		try {
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			if (iatxResponse.getErrorCode() == OK_CODIGO_RETORNO) {
				return iatxResponse.getResultadoTransaccion();
			}
			throw new DAOException(iatxResponse.getErrorMessage(), Integer.toString(iatxResponse.getErrorCode()));
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}

	}

	/**
	 * Metodo auxiliar.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param limiteAdhesion
	 *            the limite adhesion
	 * @return iatxRequestData
	 */
	private IatxRequestData bajaAdhesionRequestData(Cliente cliente, PagoPendiente pagoPendiente,
			BigDecimal limiteAdhesion) {

		IatxRequestData iatxRequestData = new IatxRequestData(cliente);

		// Alta-Baja -A1- B=BAJA. chango to constant VALUE_BAJA
		iatxRequestData.addBodyValue("B");

		// Nombre Servicio Empresa -A4- Nombre del Servicio de la Empresa
		// retornado por CNSPAUDEUD. + Se agrega uppercase
		iatxRequestData.addBodyValue(pagoPendiente.getNombreEmpresaIatx().toUpperCase(Locale.getDefault()));

		// Identificacion del cliente empresa -A19- +se completa con blancos a
		// derecha
		iatxRequestData.addBodyValue(
				StringUtils.rightPad(pagoPendiente.getCodigoClienteEmpresa(), CODIGO_CLIENTE_EMPRESA_PAD));

		// monto tope -N14,2-
		iatxRequestData.addBodyValue(
				ISBANStringUtils.ajustadorBigDecimalIatx(limiteAdhesion, LIMITE_ADHESION_CANTIDAD_DIGITOS));

		// Tipo Cuenta -A2- Tipo de Cta del servicio IDECLTSDO v1.80
		// correspondiente a la cta seleccionada por el cliente
		iatxRequestData.addBodyValue(
				StringUtils.leftPad(pagoPendiente.getDatosPagoAutomatico().getTipoCuenta().getCodigo().toString(),
						TIPO_CUENTA_CANTIDAD_DIGITOS, "0"));
		// Numero cuenta -A19-

		String sucursal = pagoPendiente.getDatosPagoAutomatico().getIdentificacionCuenta().getNroSucursal();
		String numero = pagoPendiente.getDatosPagoAutomatico().getIdentificacionCuenta().getNroCuentaProducto();
		numero = StringUtils.leftPad(numero, 9, '0');
		numero = sucursal + numero;
		iatxRequestData.addBodyValue(StringUtils.rightPad(numero, NUMERO_CUENTA_DEBITO_CANTIDAD_DIGITOS));

		return iatxRequestData;
	}

	/**
	 * Accion alta adhesion de Pago Automatico DTF: 15533,.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionPagoAutomatico
	 *            the adhesionPagoAutomatico
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public String adhierePagoMisCuentas(Cliente cliente, AdhesionPagoAutomatico adhesionPagoAutomatico)
			throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(NOMBRE_SERVICIO_ALTPESADHE, VERSION_SERVICIO_ALTPESADHE);
		IatxRequestData iatxRequestData = generarIatxRequestDataAltaBaja(cliente, adhesionPagoAutomatico, "A");
		iatxRequest.setData(iatxRequestData);

		try {
			IatxResponse response = iatxComm.exec(iatxRequest);
			if (response.getErrorCode() == OK_CODIGO_RETORNO) {
				return response.getSesionUsuario();
			}
			throw new DAOException(response.getErrorMessage(), Integer.toString(response.getErrorCode()));
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Accion alta adhesion de Pago Automatico DTF: 15533,.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionPagoAutomatico
	 *            the adhesionPagoAutomatico
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public IatxResponse bajaPagoMisCuentas(Cliente cliente, AdhesionPagoAutomatico adhesionPagoAutomatico)
			throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(NOMBRE_SERVICIO_ALTPESADHE, VERSION_SERVICIO_ALTPESADHE);
		IatxRequestData iatxRequestData = generarIatxRequestDataAltaBaja(cliente, adhesionPagoAutomatico, "B");
		iatxRequest.setData(iatxRequestData);
		try {
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			if (iatxResponse.getErrorCode() == OK_CODIGO_RETORNO) {
				return iatxResponse;
			}
			throw new DAOException(iatxResponse.getErrorMessage(), Integer.toString(iatxResponse.getErrorCode()));
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Accion alta adhesion de Pago Automatico DTF: 15533,.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public ResultadoTransaccion adhierePagoAutomatico(Cliente cliente, AdhesionPagoAutomatico adhesionPagoAutomatico)
			throws DAOException {

		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue("A");
		iatxRequestData.addBodyValue(StringUtils.rightPad(adhesionPagoAutomatico.getFiid(), 4, " "));
		iatxRequestData.addBodyValue(StringUtils.rightPad(adhesionPagoAutomatico.getCodigoPagoElectronico(), 19, " "));

		String importe1 = StringUtils.isNotBlank(adhesionPagoAutomatico.getAuxImporte()) ?
				adhesionPagoAutomatico.getAuxImporte().replaceAll("[^\\d]", "")
				: StringUtils.EMPTY;
		iatxRequestData.addBodyValue(StringUtils.leftPad(importe1, 14, "0"));

		Cuenta cuenta = cliente.getCuenta(adhesionPagoAutomatico.getCuentaSeleccionada());

		iatxRequestData
				.addBodyValue(StringUtils.leftPad(String.valueOf(cuenta.getTipoCuentaEnum().getCodigo()), 2, "0"));

		String cuentaDelServicio = StringUtils.substring(cuenta.getNroSucursal(), 1)
				+ cuenta.getNroCuentaProducto().substring(0, cuenta.getNroCuentaProducto().length()).substring(9);

		iatxRequestData.addBodyValue(StringUtils.rightPad(cuentaDelServicio, 19, " "));

		IatxRequest iatxReqALTPAUADHE = new IatxRequest(NOMBRE_SERVICIO_ALTPAUADHE, VERSION_SERVICIO_ALTPAUADHE);
		iatxReqALTPAUADHE.setData(iatxRequestData);

		try {
			IatxResponse iatxResponse = iatxComm.exec(iatxReqALTPAUADHE);
			if (iatxResponse.getErrorCode() == OK_CODIGO_RETORNO) {
				return iatxResponse.getResultadoTransaccion();
			}
			throw new DAOException(iatxResponse.getErrorMessage(), Integer.toString(iatxResponse.getErrorCode()));
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	@Override
	public Reporte generarComprobanteAdhesionPagoAutomatico(
			ComprobanteAdhesionDebitoAutomatico comprobanteAdhesionDebitoAutomatico) {
		Reporte reporte = new Reporte();
		try {
			// se carga el reporte
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobantePagoAutomatico.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEFAULT, logoDefault.getFile().getPath());

			if ("0".equals(comprobanteAdhesionDebitoAutomatico.getImporteLimite()))  {
				comprobanteAdhesionDebitoAutomatico.setImporteLimite("Sin límite");
			} else {
				BigDecimal importeLimite = new BigDecimal(comprobanteAdhesionDebitoAutomatico.getImporteLimite());
				comprobanteAdhesionDebitoAutomatico.setImporteLimite(ISBANStringUtils.formatearSaldo(importeLimite));
			}

			parameters.put(EMPRESA, comprobanteAdhesionDebitoAutomatico.getNombreEmpresa());
			parameters.put(NUMERO_PAGO, comprobanteAdhesionDebitoAutomatico.getMedioPago());
			parameters.put(NOMBRE_PAGO, comprobanteAdhesionDebitoAutomatico.getNombreMedioDePago());
			parameters.put(PES_IDENTIFICACION,
					WordUtils.capitalizeFully(comprobanteAdhesionDebitoAutomatico.getNumeroSocio()));
			parameters.put(NRO_CUENTA, comprobanteAdhesionDebitoAutomatico.getCodigoPagoElectronico());
			parameters.put(LIMITE, "$ " + comprobanteAdhesionDebitoAutomatico.getImporteLimite());
			parameters.put(NRO_COMP, comprobanteAdhesionDebitoAutomatico.getComprobante());
			parameters.put(FECHA_OP, comprobanteAdhesionDebitoAutomatico.getFechaHora());

			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
			parameters.put(LOGO_CIERRE_PAGO_MIS_CUENTAS, logoCierrePagoMisCuentas.getFile().getPath());
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre(
					"Comprobante_Adhesion_" + comprobanteAdhesionDebitoAutomatico.getComprobante() + PDF_EXTENSION);
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
}
