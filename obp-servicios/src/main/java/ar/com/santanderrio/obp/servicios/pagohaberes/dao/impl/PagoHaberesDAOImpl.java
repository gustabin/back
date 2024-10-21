/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.CuentaInvalidaException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagohaberes.dao.PagoHaberesDAO;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobanteAdhesionEmpleadoEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesCBUEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.DatosEmpleadoPagoHaberesEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.CBUInvalidoDAOException;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.RecuperarDatosPorCBUDAOException;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesPagoPorCBUView;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.RequestCNSTITCBU;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * PagoHaberesDAOImpl.
 */
@Component
public class PagoHaberesDAOImpl extends IatxBaseDAO implements PagoHaberesDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagoHaberesDAOImpl.class);

	/** The legal dao. */
	@Autowired
	private TransferenciaDAO transferenciaDAO;

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant CODIGO_RETORNO_36. */
	private static final int CODIGO_RETORNO_36 = 36;

	/** The Constant CODIGO_RETORNO_10000122. */
	private static final int CODIGO_RETORNO_10000122 = 10000122;

	/** The Constant CODIGO_RETORNO_56. */
	private static final int CODIGO_RETORNO_56 = 56;

	/** The Constant CODIGO_RETORNO_65. */
	private static final int CODIGO_RETORNO_65 = 65;

	/** The Constant EMPLEADO_NO_VALIDO. */
	private static final int EMPLEADO_NO_VALIDO = 10000054;

	/** The file jasper. */
	@Value("classpath:/report/pagoHaberes/adhesionPagoHaberes/ComprobanteAdhesionPagoHaberes.jasper")
	private Resource fileJasperComprobanteAdhesionPagoHaberes;

	/** The file jasper. */
	@Value("classpath:/report/pagoHaberes/pagoPorCBU/ComprobantePagoPorCBU.jasper")
	private Resource fileJasperComprobantePagoPorCBU;

	/** The file jasper. */
	@Value("classpath:/report/pagoHaberes/pagoSimpleMultiple/ComprobantePagoSimpleMultiple.jasper")
	private Resource fileJasperComprobantePagoSimple;

	/** The imagen logo top. */
	@Value("classpath:/report/comprobantes/logo-santander-gris-comp.png")
	private Resource imagenLogoTop;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo default. */
	@Value("classpath:/report/comprobantes/logo_default.png")
	private Resource logoDefault;

	/** The Constant LOGO_TOP. */
	private static final String LOGO_TOP = "LOGO_TOP";

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant LOGO_DEFAULT. */
	private static final String LOGO_DEFAULT = "LOGO_DEFAULT";

	/** The Constant NRO_COMP. */
	private static final String NRO_COMP = "NRO_COMPROBANTE";

	/** The Constant FECHA_OP. */
	private static final String FECHA_OP = "FECHA_OPERACION";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/** The Constant ALIAS_DESTINO. */
	private static final String ALIAS_DESTINO = "ALIAS_DESTINO";

	/** The Constant DESCRIPCION_EMPLEADO. */
	private static final String DESCRIPCION_EMPLEADO = "DESCRIPCION_EMPLEADO";

	/** The Constant CUENTA_ORIGEN. */
	private static final String CUENTA_ORIGEN = "CUENTA_ORIGEN";

	/** The Constant CBU_DESTINO. */
	private static final String CBU_DESTINO = "CBU_DESTINO";

	/** The Constant TIPO_CUENTA_ORIGEN. */
	private static final String TIPO_CUENTA_ORIGEN = "TIPO_CUENTA_ORIGEN";

	/** The Constant CUENTA_DESTINO. */
	private static final String CUENTA_DESTINO = "CUENTA_DESTINO";

	/** The Constant PAGO_PROGRAMADO. */
	private static final String PAGO_PROGRAMADO = "PAGO_PROGRAMADO";

	/** The Constant TIPO_CUENTA_DESTINO. */
	private static final String TIPO_CUENTA_DESTINO = "TIPO_CUENTA_DESTINO";

	/** The Constant CUIL. */
	private static final String CUIL = "CUIL";

	/** The Constant IMPORTE. */
	private static final String IMPORTE = "IMPORTE";

	/** The Constant TIPO_PAGO. */
	private static final String TIPO_PAGO = "TIPO_PAGO";

	/** The Constant CONCEPTO. */
	private static final String CONCEPTO = "CONCEPTO";

	/** The Constant FECHA_EJECUCION. */
	private static final String FECHA_EJECUCION = "FECHA_EJECUCION";

	/** The Constant BANCO_DESTINO. */
	private static final String BANCO_DESTINO = "BANCO_DESTINO";

	/** The servicio cnsctatit. */
	private String servicioCnsctatit = "CNSCTATIT_";

	/** The version Cnsctatit . */
	private String versionCnsctatit = "110";

	/** The servicio Paghabcci. */
	private String servicioPaghabcci = "PAGHABCCI_";

	/** The version Paghabcci. */
	private String versionPaghabcci = "100";

	/** The Constant MAX_NRO_SUCURSAL. */
	private static final int MAX_NRO_SUCURSAL = 3;

	/** The Constant MAX_NRO_CUENTA. */
	private static final int MAX_NRO_CUENTA = 7;

	/** The Constant LONGITUD_LNG_CTA_BANE. */
	private static final int LONGITUD_LNG_CTA_BANE = 2;

	/** PREFIJO_HONORARIO. */
	private static final String PREFIJO_HONORARIO = "HONhonorarios";

	/** PREFIJO_SUELDO. */
	private static final String PREFIJO_SUELDO = "SUEsueldos";

	/** TIPO_TRANSF_HONORARIO. */
	public static final String TIPO_TRANSF_HONORARIO = "3";

	/** TIPO_TRANSF_SUELDO. */
	public static final String TIPO_TRANSF_SUELDO = "1";

	/** The Constant DESTINATARIO_NO_VERIFICADO_1. */
	private static final int DESTINATARIO_NO_VERIFICADO_1 = 1;

	/** The Constant DESTINATARIO_NO_VERIFICADO_2. */
	private static final int DESTINATARIO_NO_VERIFICADO_2 = 2;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.dao.PagoHaberesDAO#
	 * isClienteValido(ar.com.santanderrio.obp.servicios.clientes. entities.Cliente)
	 */
	@Override
	public PagoInformadoView isClienteValido(Cliente cliente, PagoInformadoView empleado)
	        throws DAOException, CuentaInvalidaException {
		PagoInformadoView empleadoRetorno = new PagoInformadoView();

		IatxRequest iatxRequest = new IatxRequest(servicioCnsctatit, versionCnsctatit);

		try {
			IatxRequestData iatxRequestData = new IatxRequestData(cliente);
			// Tipo Cuenta
			iatxRequestData.addBodyValue(ISBANStringUtils.tipoCtaIatx(empleado.getTipoCuentaDestino()));
			// Nro Sucursal
			iatxRequestData.addBodyValue(ISBANStringUtils.extraerSucursal(empleado.getCuentaDestino()));
			// Nro Cuenta
			iatxRequestData.addBodyValue(ISBANStringUtils.extraerCuenta(empleado.getCuentaDestino()));
			iatxRequestData.addBodyValue("1");

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();

			switch (errorCode) {
			case OK_CODIGO_RETORNO:
				parsearResponseEmpleadoValido(iatxResponse, empleadoRetorno);
				break;
			case EMPLEADO_NO_VALIDO:
				return null;
			default:
				throw new DAOException();
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
		return empleadoRetorno;
	}

	/**
	 * Parsear response empleado valido.
	 *
	 * @param iatxResponse    the iatx response
	 * @param empleadoRetorno the empleado retorno
	 */
	private void parsearResponseEmpleadoValido(IatxResponse iatxResponse, PagoInformadoView empleadoRetorno) {
		empleadoRetorno.setAliasDestino(ISBANStringUtils.convertirStringToCamelcase(iatxResponse.getNextData()));
		empleadoRetorno.setTipoCuil(iatxResponse.getNextData());
		empleadoRetorno.setCuil(ISBANStringUtils.agregarGuionesANumeroCuitCuil(iatxResponse.getNextData()));
		empleadoRetorno.setNup(iatxResponse.getNextData());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.dao.PagoHaberesDAO#
	 * isClienteValido(ar.com.santanderrio.obp.servicios.clientes. entities.Cliente)
	 */
	@Override
	public ValidacionCuentaOutCBUEntity validarCBU(Cliente clienteIngresado,
	        ValidacionesPagoPorCBUView validacionesPagoPorCBU) throws CBUInvalidoDAOException,
	        DestinatarioNoVerificadoException, RecuperarDatosPorCBUDAOException, DAOException {
		ValidacionCuentaOutCBUEntity datosCliente = new ValidacionCuentaOutCBUEntity();

		RequestCNSTITCBU requestCNSTITCBU = new RequestCNSTITCBU();
		requestCNSTITCBU.setCbuDestino(validacionesPagoPorCBU.getNumeroCBUDestino());
		requestCNSTITCBU.setDireccionIP(validacionesPagoPorCBU.getDireccionIP());
		requestCNSTITCBU.setNroCuenta(StringUtils
		        .right(ISBANStringUtils.extraerCuenta(validacionesPagoPorCBU.getCuentaOrigen()), MAX_NRO_CUENTA));
		requestCNSTITCBU.setNroSucursal(StringUtils
		        .right(ISBANStringUtils.extraerSucursal(validacionesPagoPorCBU.getCuentaOrigen()), MAX_NRO_SUCURSAL));
		requestCNSTITCBU.setNroTarjeta(StringUtils.right(validacionesPagoPorCBU.getTarjetaBanelco(), 18));
		requestCNSTITCBU.setTipoCuenta(StringUtils.leftPad(
		        TipoCuenta.fromAbreviatura(validacionesPagoPorCBU.getTipoCuentaOrigen()).getCodigo().toString(),
		        LONGITUD_LNG_CTA_BANE, "0"));

		IatxResponse iatxResponse = transferenciaDAO.conexionCNSTITICBU(clienteIngresado, requestCNSTITCBU);
		int codeError = Integer.valueOf(StringUtils.right(String.valueOf(iatxResponse.getErrorCode()), 2));
		switch (codeError) {
		case OK_CODIGO_RETORNO:
			datosCliente = processTrama(iatxResponse.getTrama(), ValidacionCuentaOutCBUEntity.class);
			break;
		case CODIGO_RETORNO_36:
			throw new CBUInvalidoDAOException("CBU Invalido");
		case CODIGO_RETORNO_56:
		case CODIGO_RETORNO_65:
			throw new RecuperarDatosPorCBUDAOException("Error al Recuperar datos del usuario por CBU");
		case DESTINATARIO_NO_VERIFICADO_1:
		case DESTINATARIO_NO_VERIFICADO_2:
			throw new DestinatarioNoVerificadoException();
		default:
			// Error Generico
			throw new DestinatarioNoVerificadoException();
		}

		return datosCliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.dao.PagoHaberesDAO#
	 * isClienteValido(ar.com.santanderrio.obp.servicios.clientes. entities.Cliente)
	 */
	@Override
	public IatxResponse pagoHaberesCBU(Cliente clienteIngresado, DatosDestinatarioView datosDestinatarioView,
	        Cuenta cuentaOrigen) throws SaldoInsuficienteException, DAOException {
		IatxResponse respuesta = new IatxResponse();

		IatxRequest iatxRequest = new IatxRequest(servicioPaghabcci, versionPaghabcci);

		try {
			IatxRequestData iatxRequestData = generateRequestDataPAGHABCCI(clienteIngresado, datosDestinatarioView,
			        cuentaOrigen);

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();

			switch (errorCode) {
			case OK_CODIGO_RETORNO:
				// OK -
				respuesta = iatxResponse;
				return respuesta;
			case CODIGO_RETORNO_10000122:
				throw new SaldoInsuficienteException("CBU Invalido");
			default:
				// Error Generico
				throw new DAOException();
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
	}

	/**
	 * Generate request data PAGHABCCI.
	 *
	 * @param cliente      the cliente
	 * @param entity       the entity
	 * @param cuentaOrigen the cuenta origen
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataPAGHABCCI(Cliente cliente, DatosDestinatarioView entity,
	        Cuenta cuentaOrigen) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);

		iatxRequestData.addBodyValue("L");
		try {
			if ("CU".equals(entity.getTipoCuentaOrigen())) {
				entity.setTipoCuentaOrigen("CUP");
			}
			iatxRequestData.addBodyValue(ISBANStringUtils.tipoCtaIatx(entity.getTipoCuentaOrigen()));
		} catch (CuentaInvalidaException e) {
			// Devolver algun error generico.
			iatxRequestData.addBodyValue("");
		}
		iatxRequestData.addBodyValue(
		        ISBANStringUtils.formateadorConCerosIzq(ISBANStringUtils.extraerSucursal(entity.getCuentaOrigen()), 4));
		iatxRequestData.addBodyValue(
		        ISBANStringUtils.formateadorConCerosIzq(ISBANStringUtils.extraerCuenta(entity.getCuentaOrigen()), 12));
		iatxRequestData.addBodyValue(ISBANStringUtils.formateadorConCerosIzq(cuentaOrigen.getNroOrdenFirmante(), 2));
		iatxRequestData.addBodyValue(obtenerTipoTransferencia(entity.getTipoPago()));
		iatxRequestData.addBodyValue(fillStr(obtenerReferenciaUnivoca(entity.getTipoPago()), 15));
		iatxRequestData.addBodyValue(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(entity.getImporte()), 11));
		iatxRequestData.addBodyValue("0");
		iatxRequestData.addBodyValue(fillStr(entity.getConcepto(), 80));
		iatxRequestData.addBodyValue("                    ");
		iatxRequestData.addBodyValue(StringUtils.defaultString(
		        ISBANStringUtils.eliminarGuionesDeCuil(entity.getCuilCuitDestinatario()), StringUtils.repeat(' ', 11)));
		iatxRequestData
		        .addBodyValue(ISBANStringUtils.formateadorConCerosIzq(entity.getNumeroCBUDestino().substring(0, 3), 4));
		iatxRequestData.addBodyValue(entity.getNumeroCBUDestino().substring(3, 7));
		iatxRequestData.addBodyValue(entity.getNumeroCBUDestino().substring(7, 8));
		iatxRequestData
		        .addBodyValue(ISBANStringUtils.formateadorConCerosIzq(entity.getNumeroCBUDestino().substring(8), 17));
		iatxRequestData.addBodyValue("S");
		iatxRequestData.addBodyValue("S");
		iatxRequestData.addBodyValue("02");
		iatxRequestData.addBodyValue("001");
		iatxRequestData.addBodyValue("N");
		if (entity.getBancoDestinatario().length() > 22) {
			iatxRequestData.addBodyValue(entity.getBancoDestinatario().substring(0, 22));
		} else {
			iatxRequestData.addBodyValue(entity.getBancoDestinatario());
		}
		if (entity.getNombreDestinatario().length() > 30) {
			iatxRequestData.addBodyValue(entity.getNombreDestinatario().substring(0, 30));
		} else {
			iatxRequestData.addBodyValue(entity.getNombreDestinatario());
		}
		iatxRequestData.addBodyValue("                                                  ");
		return iatxRequestData;
	}

	/**
	 * Obtener tipo transferencia.
	 *
	 * @param tipoPago the tipo pago
	 * @return the string
	 */
	private String obtenerTipoTransferencia(String tipoPago) {
		if ("S".equals(tipoPago)) {
			return TIPO_TRANSF_SUELDO;
		} else if ("H".equals(tipoPago)) {
			return TIPO_TRANSF_HONORARIO;
		}
		return "";
	}

	/**
	 * Obtener referencia univoca.
	 *
	 * @param tipoPago the tipo pago
	 * @return the string
	 */
	private String obtenerReferenciaUnivoca(String tipoPago) {
		if ("S".equals(tipoPago)) {
			return PREFIJO_SUELDO;
		} else if ("H".equals(tipoPago)) {
			return PREFIJO_HONORARIO;
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.dao.PagoHaberesDAO#
	 * generarComprobanteAdhesion(ar.com.santanderrio.obp.servicios.pagohaberes.
	 * entities.ComprobanteAdhesionEmpleado)
	 */
	@Override
	public Reporte generarComprobanteAdhesion(ComprobanteAdhesionEmpleadoEntity comprobanteAdhesionEmpleado) {

		DatosEmpleadoPagoHaberesEntity datosEmpleadoPagoHaberes = comprobanteAdhesionEmpleado
		        .getDatosEmpleadoPagoHaberes();
		Reporte reporte = new Reporte();

		try {
			// se carga el reporte
			JasperReport jasperReport = (JasperReport) JRLoader
			        .loadObject(fileJasperComprobanteAdhesionPagoHaberes.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEFAULT, logoDefault.getFile().getPath());
			parameters.put(DESCRIPCION_EMPLEADO,
			        StringUtils.isEmpty(datosEmpleadoPagoHaberes.getDescripcionEmpleado()) ? ""
			                : datosEmpleadoPagoHaberes.getDescripcionEmpleado().trim());
			parameters.put(ALIAS_DESTINO, datosEmpleadoPagoHaberes.getAliasDestino());
			parameters.put(CUENTA_ORIGEN, datosEmpleadoPagoHaberes.getCuentaOrigen());
			parameters.put(TIPO_CUENTA_ORIGEN, datosEmpleadoPagoHaberes.getTipoCuentaOrigen());
			parameters.put(CUENTA_DESTINO, datosEmpleadoPagoHaberes.getCuentaDestino());
			parameters.put(TIPO_CUENTA_DESTINO, datosEmpleadoPagoHaberes.getTipoCuentaDestino());
			parameters.put(CUIL,
			        StringUtils.isEmpty(datosEmpleadoPagoHaberes.getCuil()) ? "-" : datosEmpleadoPagoHaberes.getCuil());
			parameters.put(IMPORTE, datosEmpleadoPagoHaberes.getImporte());
			parameters.put(TIPO_PAGO, datosEmpleadoPagoHaberes.getTipoPago());
			parameters.put(CONCEPTO, datosEmpleadoPagoHaberes.getConcepto());
			parameters.put(NRO_COMP, comprobanteAdhesionEmpleado.getNroDeComprobante());
			parameters.put(FECHA_OP, comprobanteAdhesionEmpleado.getFechaHora());

			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre(
			        "Comprobante_Adhesion_" + comprobanteAdhesionEmpleado.getNroDeComprobante() + PDF_EXTENSION);
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
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.dao.PagoHaberesDAO#
	 * generarComprobantePagoPorCBU(ar.com.santanderrio.obp.servicios.
	 * pagohaberes.entities.ComprobantePagoHaberesCBU)
	 */
	@Override
	public Reporte generarComprobantePagoPorCBU(ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU) {

		DatosEmpleadoPagoHaberesEntity datosEmpleadoPagoHaberes = comprobantePagoHaberesCBU
		        .getDatosEmpleadoPagoHaberes();
		Reporte reporte = new Reporte();

		try {
			// se carga el reporte
			JasperReport jasperReport = (JasperReport) JRLoader
			        .loadObject(fileJasperComprobantePagoPorCBU.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(DESCRIPCION_EMPLEADO,
			        StringUtils.isEmpty(datosEmpleadoPagoHaberes.getDescripcionEmpleado()) ? ""
			                : datosEmpleadoPagoHaberes.getDescripcionEmpleado().trim());
			parameters.put(LOGO_DEFAULT, logoDefault.getFile().getPath());
			parameters.put(CUENTA_ORIGEN, datosEmpleadoPagoHaberes.getCuentaOrigen());
			parameters.put(TIPO_CUENTA_ORIGEN, datosEmpleadoPagoHaberes.getTipoCuentaOrigen());
			parameters.put(CBU_DESTINO, datosEmpleadoPagoHaberes.getCbuDestino());
			parameters.put(CUIL,
			        StringUtils.isEmpty(datosEmpleadoPagoHaberes.getCuil()) ? "-" : datosEmpleadoPagoHaberes.getCuil());
			parameters.put(BANCO_DESTINO, datosEmpleadoPagoHaberes.getBancoDestino());
			parameters.put(FECHA_EJECUCION, datosEmpleadoPagoHaberes.getFechaEjecucion());
			parameters.put(IMPORTE, datosEmpleadoPagoHaberes.getImporte());
			parameters.put(TIPO_PAGO, datosEmpleadoPagoHaberes.getTipoPago());
			parameters.put(CONCEPTO, datosEmpleadoPagoHaberes.getConcepto());
			parameters.put(NRO_COMP, comprobantePagoHaberesCBU.getNroDeComprobante());
			parameters.put(FECHA_OP, comprobantePagoHaberesCBU.getFechaHora());

			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre(
			        "Comprobante_Pago_Por_CBU_" + comprobantePagoHaberesCBU.getNroDeComprobante() + PDF_EXTENSION);
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
	 * Fill str.
	 *
	 * @param s    the s
	 * @param size the size
	 * @return the string
	 */
	public static String fillStr(String s, int size) {
		int l = s.length();
		if (l >= size) {
			return s.substring(l - size, l);
		}
		StringBuilder blancos = new StringBuilder();
		for (int n = 0; n < size - l; ++n) {
			blancos.append(" ");
		}
		return s + blancos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.dao.PagoHaberesDAO#
	 * generarComprobantePagoSimple(ar.com.santanderrio.obp.servicios.
	 * pagohaberes.entities.ComprobantePagoHaberesCBUEntity)
	 */
	@Override
	public Reporte generarComprobantePagoSimple(ComprobantePagoHaberesCBUEntity comprobante) {

		Reporte reporte = new Reporte();

		try {
			// se carga el reporte
			JasperReport jasperReport = (JasperReport) JRLoader
			        .loadObject(fileJasperComprobantePagoSimple.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_TOP, imagenLogoTop.getFile().getPath());
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEFAULT, logoDefault.getFile().getPath());
			parameters.put(DESCRIPCION_EMPLEADO,
			        StringUtils.isEmpty(comprobante.getDatosEmpleadoPagoHaberes().getDescripcionEmpleado()) ? ""
			                : comprobante.getDatosEmpleadoPagoHaberes().getDescripcionEmpleado().trim());
			parameters.put(CUENTA_ORIGEN, comprobante.getDatosEmpleadoPagoHaberes().getCuentaOrigen());
			parameters.put(TIPO_CUENTA_ORIGEN, comprobante.getDatosEmpleadoPagoHaberes().getTipoCuentaOrigen());
			parameters.put(CUENTA_DESTINO, comprobante.getDatosEmpleadoPagoHaberes().getCuentaDestino());
			parameters.put(TIPO_CUENTA_DESTINO, comprobante.getDatosEmpleadoPagoHaberes().getTipoCuentaDestino());
			parameters.put(CUIL, StringUtils.isEmpty(comprobante.getDatosEmpleadoPagoHaberes().getCuil()) ? "-"
			        : comprobante.getDatosEmpleadoPagoHaberes().getCuil());
			parameters.put(FECHA_EJECUCION, comprobante.getDatosEmpleadoPagoHaberes().getFechaEjecucion());
			parameters.put(IMPORTE, comprobante.getDatosEmpleadoPagoHaberes().getImporte());
			parameters.put(TIPO_PAGO, comprobante.getDatosEmpleadoPagoHaberes().getTipoPago());
			parameters.put(CONCEPTO, comprobante.getDatosEmpleadoPagoHaberes().getConcepto());
			parameters.put(NRO_COMP, comprobante.getNroDeComprobante());
			parameters.put(FECHA_OP, comprobante.getFechaHora());
			parameters.put(PAGO_PROGRAMADO, !comprobante.getModoEjecucion());

			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre("Comprobante_Pago_Simple_" + comprobante.getNroDeComprobante() + PDF_EXTENSION);
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