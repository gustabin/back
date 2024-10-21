/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.billetera.admcbu.AdministrarCBUBancosPortType;
import ar.com.santanderrio.obp.generated.webservices.billetera.admclave.AdministrarClaveBancosPortType;
import ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.AdministrarMedioPagoBancosPortType;
import ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.MediosPago;
import ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.MediosPagoResponse;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.AltaCuentaRequest;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.BilleteraCuentaPortType;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.ConsultaCuentaResult;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.ConsultarCuenta;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.DataServiceFault_Exception;
import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.billetera.dao.BilleteraDAO;
import ar.com.santanderrio.obp.servicios.billetera.dto.MedioDePagoBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.web.util.BilleteraUtils;
import ar.com.santanderrio.obp.servicios.billetera.web.view.MedioDePagoComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionBilletera;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class BilleteraDAOImpl.
 * 
 */
@Component
public class BilleteraDAOImpl implements BilleteraDAO {

	/** The Constant FILENAME_PREFIX_ADHESION. */
	private static final String FILENAME_PREFIX_ADHESION = "Comprobante_Adhesion_Billetera_";

	/** The Constant FILENAME_PREFIX_MODIFICACION. */
	private static final String FILENAME_PREFIX_MODIFICACION = "Comprobante_Configuracion_Billetera_";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BilleteraDAOImpl.class);

	/** The Constant PARAM_CUENTA_CREDITACION. */
	private static final String PARAM_CUENTA_CREDITACION = "CUENTA_CREDITACION";

	/** The Constant PARAM_EMAIL. */
	private static final String PARAM_EMAIL = "EMAIL";

	/** The Constant PARAM_EMPRESA_CELULAR. */
	private static final String PARAM_EMPRESA_CELULAR = "EMPRESA_CELULAR";

	/** The Constant PARAM_FECHA_OP. */
	private static final String PARAM_FECHA_OP = "FECHA_OP";

	/** The Constant PARAM_LOGO_CABECERA. */
	private static final String PARAM_LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant PARAM_LOGO_CIERRE. */
	private static final String PARAM_LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant PARAM_LOGO_DEFAULT. */
	private static final String PARAM_LOGO_DEFAULT = "LOGO_DEFAULT";

	/** The Constant PARAM_NRO_COMPROBANTE. */
	private static final String PARAM_NRO_COMPROBANTE = "NRO_COMPROBANTE";

	/** The Constant PARAM_SUBREPORT_DIR. */
	private static final String PARAM_SUBREPORT_DIR = "SUBREPORT_DIR";

	/** The Constant PARAM_TARJETAS_OK. */
	private static final String PARAM_TARJETAS_OK = "TARJETAS_OK";

	/** The Constant PARAM_TELEFONO_CELULAR. */
	private static final String PARAM_TELEFONO_CELULAR = "TELEFONO_CELULAR";

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo default. */
	@Value("classpath:/report/comprobantes/logo_default.png")
	private Resource logoDefault;

	/** The report file adhesion. */
	@Value("classpath:/report/billetera/ComprobanteAdhesionBilletera.jasper")
	private Resource reportFileAdhesion;

	/** The report file configuracion. */
	@Value("classpath:/report/billetera/ComprobanteConfiguracionBilletera.jasper")
	private Resource reportFileModificacion;

	/** The sesion billetera. */
	@Autowired
	protected SesionBilletera sesionBilletera;

	/** The ws adm cbu client. */
	@Autowired
	@Qualifier("gestionAdministrarCBU")
	private GestionarWS<AdministrarCBUBancosPortType> wsAdmCbuClient;

	/** The ws adm clave client. */
	@Autowired
	@Qualifier("gestionAdministrarClave")
	private GestionarWS<AdministrarClaveBancosPortType> wsAdmClaveClient;

	/** The ws adm medio pago client. */
	@Autowired
	@Qualifier("gestionAdministrarMedioPago")
	private GestionarWS<AdministrarMedioPagoBancosPortType> wsAdmMedioPagoClient;

	/** The ws billetera client. */
	@Autowired
	@Qualifier("gestionBilleteraCuenta")
	private GestionarWS<BilleteraCuentaPortType> wsBilleteraCuentaClient;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.billetera.dao.BilleteraDAO#
	 * administrarCBUBancos(int, java.lang.String, int, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String administrarCBUBancos(int idCuenta, String banco, int canal, String tipoAcreditacion, String cbu,
			String tipoCuenta, String monedaCuenta, String nroCuenta, String tipoNovedad, String codBanco, String cuit)
			throws DAOException {
		AdministrarCBUBancosPortType port = null;
		try {
			port = wsAdmCbuClient.obtenerPort();
			String resp = port.administrarCBUBancos(idCuenta, banco, canal, tipoAcreditacion, cbu, tipoCuenta,
					monedaCuenta, nroCuenta, tipoNovedad, codBanco, cuit);
			LOGGER.info("Respuesta WS : {}", resp);
			return resp;
		} catch (Exception e) {
			LOGGER.error(
					"Hubo un error al invocar al ws de Billetera en la operacion administrarCBUBancos con los datos idCuenta: {}, banco: {}, canal: {}, tipoAcreditacion: {}, cbu: {}, tipoCuenta: {}, monedaCuenta: {}, nroCuenta: {}, tipoNovedad: {}, codBanco: {}, cuit: {}.",
					idCuenta, banco, canal, tipoAcreditacion, cbu, tipoCuenta, monedaCuenta, nroCuenta, tipoNovedad,
					codBanco, cuit);
			throw new DAOException(e);
		} finally {
			wsAdmCbuClient.liberarPort(port);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.billetera.dao.BilleteraDAO#
	 * administrarClaveBancos(int, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	public String administrarClaveBancos(int idCuenta, String banco, String contrasenia, String respPregSeguridad,
			String tipoNovedad, int canal) throws DAOException {
		AdministrarClaveBancosPortType port = null;
		try {
			port = wsAdmClaveClient.obtenerPort();
			String resp = port.administrarClaveBancos(idCuenta, banco, contrasenia, respPregSeguridad, tipoNovedad,
					canal);
			LOGGER.info("Respuesta : {}", resp);
			return resp;
		} catch (Exception e) {
			LOGGER.error(
					"Hubo un error al invocar al ws de Billetera en la operacion administrarClaveBancos con los datos idCuenta: {}, banco: {}, contrasenia: {}, respPregSeguridad: {}, tipoNovedad: {}, canal: {}.",
					idCuenta, banco, contrasenia, respPregSeguridad, tipoNovedad, canal);
			throw new DAOException(e);
		} finally {
			wsAdmClaveClient.liberarPort(port);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.billetera.dao.BilleteraDAO#
	 * administrarMedioPago(int, java.lang.String, java.lang.String,
	 * ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.
	 * MediosPago)
	 */
	@Override
	public MediosPagoResponse administrarMedioPago(int idCuenta, String banco, String canal, MediosPago mediosPago)
			throws DAOException {
		AdministrarMedioPagoBancosPortType port = null;
		try {
			port = wsAdmMedioPagoClient.obtenerPort();
			MediosPagoResponse resp = port.administrarMedioPago(idCuenta, banco, canal, mediosPago);
			LOGGER.info("Respuesta : {}", resp);
			return resp;
		} catch (Exception e) {
			LOGGER.error(
					"Hubo un error al invocar al ws de Billetera en la operacion administrarMedioPago con los datos idCuenta: {}, banco: {}, canal: {}, mediosPago: {}.",
					idCuenta, banco, canal, mediosPago);
			throw new DAOException(e);
		} finally {
			wsAdmMedioPagoClient.liberarPort(port);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.billetera.dao.BilleteraDAO#altaCuenta(
	 * ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.
	 * AltaCuentaRequest, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	public void altaCuenta(AltaCuentaRequest parameters, Holder<String> status, Holder<String> idCuenta)
			throws DAOException {
		BilleteraCuentaPortType port = null;
		try {
			port = wsBilleteraCuentaClient.obtenerPort();
			port.altaCuenta(parameters, status, idCuenta);
			LOGGER.info("Respuesta : status : {}, idCuenta : {}", status, idCuenta);
		} catch (DataServiceFault_Exception e) {
			LOGGER.error("Hubo un error al invocar al ws de Billetera en la operacion altaCuenta con los datos {}.",
					parameters);
			throw new DAOException(e);
		} finally {
			wsBilleteraCuentaClient.liberarPort(port);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.billetera.dao.BilleteraDAO#
	 * consultarCuenta(ar.com.santanderrio.obp.generated.webservices.billetera.
	 * consultacuenta2.ConsultarCuenta)
	 */
	@Override
	public ConsultaCuentaResult consultarCuenta(ConsultarCuenta parameters) throws DAOException {
		BilleteraCuentaPortType port = null;
		try {
			port = wsBilleteraCuentaClient.obtenerPort();
			ConsultaCuentaResult resp = port.consultarCuenta(parameters);
			LOGGER.info("Respuesta WS : {}", resp);
			return resp;
		} catch (DataServiceFault_Exception e) {
			LOGGER.error(
					"Hubo un error al invocar al ws de Billetera en la operacion consultarCuenta con los datos {}.",
					parameters);
			throw new DAOException(e);
		} finally {
			wsBilleteraCuentaClient.liberarPort(port);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.billetera.dao.BilleteraDAO#
	 * generarComprobante(ar.com.santanderrio.obp.servicios.billetera.dao.
	 * BilleteraDAO.TipoReporte)
	 */
	@Override
	public Reporte generarComprobante(TipoReporte tipoReporte) {
		Reporte reporte = new Reporte();
		Resource reportFile = (tipoReporte == TipoReporte.ADHESION) ? reportFileAdhesion : reportFileModificacion;
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			// Se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(PARAM_LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(PARAM_LOGO_DEFAULT, logoDefault.getFile().getPath());
			parameters.put(PARAM_EMAIL, sesionBilletera.getEmail());
			parameters.put(PARAM_TELEFONO_CELULAR, sesionBilletera.getNumeroCelular());
			parameters.put(PARAM_EMPRESA_CELULAR, sesionBilletera.getEmpresaCelular());
			parameters.put(PARAM_SUBREPORT_DIR, reportFile.getFile().getParent() + File.separator);
			parameters.put(PARAM_TARJETAS_OK, getTarjetasOkReport());
			if (tipoReporte == TipoReporte.ADHESION) {
				parameters.put(PARAM_CUENTA_CREDITACION, sesionBilletera.getCuentaAcreditacion());
			}
			parameters.put(PARAM_LOGO_CIERRE, logoCierre.getFile().getPath());
			parameters.put(PARAM_NRO_COMPROBANTE, sesionBilletera.getNroComprobante());
			parameters.put(PARAM_FECHA_OP, ISBANStringUtils.formatearFecha(new Date(), "dd/MM/yyyy HH:mm"));

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// Se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			String filenamePrefix = (tipoReporte == TipoReporte.ADHESION) ? FILENAME_PREFIX_ADHESION
					: FILENAME_PREFIX_MODIFICACION;
			reporte.setNombre(filenamePrefix + sesionBilletera.getNroComprobante() + PDF_EXTENSION);
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
	 * Gets the tarjetas ok report.
	 *
	 * @return the tarjetas ok report
	 */
	private List<MedioDePagoComprobanteView> getTarjetasOkReport() {
		List<MedioDePagoComprobanteView> tarjetasOkReport = new ArrayList<MedioDePagoComprobanteView>();
		for (MedioDePagoBilleteraDTO tarjetaOk : sesionBilletera.getTarjetasOk()) {
			MedioDePagoComprobanteView tarjetaOkReport = new MedioDePagoComprobanteView();
			Cuenta ctaAsociada = tarjetaOk.getCtaAsociada();
			String alias = ctaAsociada.getAlias();
			if (alias != null && !"".equals(alias.trim())) {
				alias = " \"" + alias + "\"";
			} else {
				alias = "";
			}
			String nroTrjMasked = BilleteraUtils.getNroTrjFmt(ctaAsociada.getNroTarjetaCredito(),
					ctaAsociada.getTipoCuenta());
			tarjetaOkReport.setDescripcion(
					MessageFormat.format("{0}{1} ({2})", BilleteraUtils.getMarcaTrj(ctaAsociada), alias, nroTrjMasked));
			tarjetaOkReport.setFavorita(BilleteraConstants.S.equals(tarjetaOk.getFavorito()) ? "Favorita" : "");
			tarjetasOkReport.add(tarjetaOkReport);
		}
		return tarjetasOkReport;
	}

}
