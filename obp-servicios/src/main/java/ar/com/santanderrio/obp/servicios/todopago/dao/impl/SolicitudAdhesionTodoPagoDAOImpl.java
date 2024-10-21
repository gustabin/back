/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.todopago.dao.SolicitudAdhesionTodoPagoDAO;
import ar.com.santanderrio.obp.servicios.todopago.entity.AdhesionTodoPagoInEntity;
import ar.com.santanderrio.obp.servicios.todopago.web.view.ComprobanteAdhesionTodoPagoView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Dao para ejecutar los procedure del package de TodoPago.
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class SolicitudAdhesionTodoPagoDAOImpl extends BaseDatoDAOImpl implements SolicitudAdhesionTodoPagoDAO {

	/** The Constant FILENAME_PREFIX_ADHESION. */
	private static final String FILENAME_PREFIX_ADHESION = "Comprobante_De_Solicitud_Adhesion_Todo_Pago_";

	/** The Constant PARAM_NOMBRE. */
	private static final String PARAM_NOMBRE = "NOMBRE";

	/** The Constant PARAM_APELLIDO. */
	private static final String PARAM_APELLIDO = "APELLIDO";

	/** The Constant PARAM_DNI. */
	private static final String PARAM_DNI = "DNI";

	/** The Constant PARAM_CUIT_CUIL. */
	private static final String PARAM_CUIT_CUIL = "CUIT_CUIL";

	/** The Constant PARAM_FECHA_NAC. */
	private static final String PARAM_FECHA_NAC = "FECHA_NAC";

	/** The Constant PARAM_SEXO. */
	private static final String PARAM_SEXO = "SEXO";

	/** The Constant PARAM_PAIS_ORIGEN. */
	private static final String PARAM_PAIS_ORIGEN = "PAIS_ORIGEN";

	/** The Constant PARAM_COND_IVA. */
	private static final String PARAM_COND_IVA = "COND_IVA";

	/** The Constant PARAM_COND_IIBB. */
	private static final String PARAM_COND_IIBB = "COND_IIBB";

	/** The Constant PARAM_ACTIVIDAD. */
	private static final String PARAM_ACTIVIDAD = "ACTIVIDAD";

	/** The Constant PARAM_CALLE. */
	private static final String PARAM_CALLE = "CALLE";

	/** The Constant PARAM_NUMERO. */
	private static final String PARAM_NUMERO = "NUMERO";

	/** The Constant PARAM_PISO. */
	private static final String PARAM_PISO = "PISO";

	/** The Constant PARAM_DEPTO. */
	private static final String PARAM_DEPTO = "DEPTO";

	/** The Constant PARAM_PROVINCIA. */
	private static final String PARAM_PROVINCIA = "PROVINCIA";

	/** The Constant PARAM_LOCALIDAD. */
	private static final String PARAM_LOCALIDAD = "LOCALIDAD";

	/** The Constant PARAM_COD_POSTAL. */
	private static final String PARAM_COD_POSTAL = "COD_POSTAL";

	/** The Constant PARAM_EMAIL. */
	private static final String PARAM_EMAIL = "EMAIL";

	/** The Constant PARAM_TELEFONO_FIJO. */
	private static final String PARAM_TELEFONO_FIJO = "TELEFONO_FIJO";

	/** The Constant PARAM_TELEFONO_CELULAR. */
	private static final String PARAM_TELEFONO_CELULAR = "TELEFONO_CELULAR";

	/** The Constant PARAM_EMPRESA_CELULAR. */
	private static final String PARAM_EMPRESA_CELULAR = "EMPRESA_CELULAR";

	/** The Constant PARAM_CUENTA. */
	private static final String PARAM_CUENTA = "CUENTA";

	/** The Constant PARAM_FECHA_OP. */
	private static final String PARAM_FECHA_OP = "FECHA_OP";

	/** The Constant PARAM_LOGO_CABECERA. */
	private static final String PARAM_LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant PARAM_LOGO_CIERRE. */
	private static final String PARAM_LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant PARAM_LOGO_DEFAULT. */
	private static final String PARAM_LOGO_DEFAULT = "LOGO_DEFAULT";

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/** The Constant NRO COMPROBANTE. */
	private static final String PARAM_NRO_COMPROBANTE = "NRO_COMPROBANTE";

	/** The Constant DESC CUENTA. */
	private static final String PARAM_DESC_CUENTA = "DESC_CUENTA";
	
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

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
	@Value("classpath:/report/todoPago/ComprobanteSolicitudAdhesionTodoPago.jasper")
	private Resource reportFileAdhesion;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudAdhesionTodoPagoDAOImpl.class);

	/** The Constant P_FECHA_SOLICITUD. */
	private static final String P_FECHA_SOLICITUD = "P_FECHA_SOLICITUD";

	/** The Constant P_NUP. */
	private static final String P_NUP = "P_NUP";

	/** The Constant P_TIPO_DOCUMENTO. */
	private static final String P_TIPO_DOCUMENTO = "P_TIPO_DOCUMENTO";

	/** The Constant P_NRO_DOCUMENTO. */
	private static final String P_NRO_DOCUMENTO = "P_NRO_DOCUMENTO";

	/** The Constant P_RAZON_SOCIAL. */
	private static final String P_RAZON_SOCIAL = "P_RAZON_SOCIAL";

	/** The Constant P_CUIT_DNI. */
	private static final String P_CUIT_DNI = "P_CUIT_DNI";

	/** The Constant P_FECHA_NACIMIENTO. */
	private static final String P_FECHA_NACIMIENTO = "P_FECHA_NACIMIENTO";

	/** The Constant P_SEXO. */
	private static final String P_SEXO = "P_SEXO";

	/** The Constant P_PAIS_ORIGEN. */
	private static final String P_PAIS_ORIGEN = "P_PAIS_ORIGEN";

	/** The Constant P_NRO_CUENTA. */
	private static final String P_NRO_CUENTA = "P_NRO_CUENTA";

	/** The Constant P_CBU. */
	private static final String P_CBU = "P_CBU";

	/** The Constant P_EMAIL. */
	private static final String P_EMAIL = "P_EMAIL";

	/** The Constant P_TELEFONO_FIJO. */
	private static final String P_TELEFONO_FIJO = "P_TELEFONO_FIJO";

	/** The Constant P_CELULAR. */
	private static final String P_CELULAR = "P_CELULAR";

	/** The Constant P_EMPRESA_CELULAR. */
	private static final String P_EMPRESA_CELULAR = "P_EMPRESA_CELULAR";

	/** The Constant P_NOMBRE_CONTACTO. */
	private static final String P_NOMBRE_CONTACTO = "P_NOMBRE_CONTACTO";

	/** The Constant P_APELLIDO_CONTACTO. */
	private static final String P_APELLIDO_CONTACTO = "P_APELLIDO_CONTACTO";

	/** The Constant P_CONDICION_IVA. */
	private static final String P_CONDICION_IVA = "P_CONDICION_IVA";

	/** The Constant P_CONDICION_IIBB. */
	private static final String P_CONDICION_IIBB = "P_CONDICION_IIBB";

	/** The Constant P_ACTIVIDAD. */
	private static final String P_ACTIVIDAD = "P_ACTIVIDAD";

	/** The Constant P_DOMLEGAL_CALLE. */
	private static final String P_DOMLEGAL_CALLE = "P_DOMLEGAL_CALLE";

	/** The Constant P_DOMLEGAL_NUMERO. */
	private static final String P_DOMLEGAL_NUMERO = "P_DOMLEGAL_NUMERO";

	/** The Constant P_DOMLEGAL_PISO. */
	private static final String P_DOMLEGAL_PISO = "P_DOMLEGAL_PISO";

	/** The Constant P_DOMLEGAL_DPTO. */
	private static final String P_DOMLEGAL_DPTO = "P_DOMLEGAL_DPTO";

	/** The Constant P_DOMLEGAL_PROVINCIA. */
	private static final String P_DOMLEGAL_PROVINCIA = "P_DOMLEGAL_PROVINCIA";

	/** The Constant P_DOMLEGAL_LOCALIDAD. */
	private static final String P_DOMLEGAL_LOCALIDAD = "P_DOMLEGAL_LOCALIDAD";

	/** The Constant P_DOMLEGAL_COD_POSTAL. */
	private static final String P_DOMLEGAL_COD_POSTAL = "P_DOMLEGAL_COD_POSTAL";

	/** The Constant P_DOMFACT_CALLE. */
	private static final String P_DOMFACT_CALLE = "P_DOMFACT_CALLE";

	/** The Constant P_DOMFACT_NUMERO. */
	private static final String P_DOMFACT_NUMERO = "P_DOMFACT_NUMERO";

	/** The Constant P_DOMFACT_PISO. */
	private static final String P_DOMFACT_PISO = "P_DOMFACT_PISO";

	/** The Constant P_DOMFACT_DPTO. */
	private static final String P_DOMFACT_DPTO = "P_DOMFACT_DPTO";

	/** The Constant P_DOMFACT_PROVINCIA. */
	private static final String P_DOMFACT_PROVINCIA = "P_DOMFACT_PROVINCIA";

	/** The Constant P_DOMFACT_LOCALIDAD. */
	private static final String P_DOMFACT_LOCALIDAD = "P_DOMFACT_LOCALIDAD";

	/** The Constant P_DOMFACT_COD_POSTAL. */
	private static final String P_DOMFACT_COD_POSTAL = "P_DOMFACT_COD_POSTAL";

	/** The Constant P_ENVIO_EMAIL_SATISFACTORIO. */
	private static final String P_ENVIO_EMAIL_SATISFACTORIO = "P_ENVIO_EMAIL_SATISFACTORIO";

	/** The Constant OUT_TECNICO. */
	private static final String OUT_TECNICO = "p_err_tecnico";

	/** The Constant OUT_RESULTADO. */
	private static final String OUT_RESULTADO = "p_resultado";

	/** The Constant OUT_AMIGABLE. */
	private static final String OUT_AMIGABLE = "p_err_amigable";

	/** The Constant SOLICITUD_SCHEMA. */
	private static final String SOLICITUD_SCHEMA = "hbank";

	/** The Constant SOLICITUD_PACKAGE. */
	private static final String SOLICITUD_PACKAGE = "HB_PKG_SOLICITUDADHESIONTP";

	/** The Constant SOLICITUD_PROCEDURE. */
	private static final String SOLICITUD_PROCEDURE = "GRABAR_SOLICITUD2";

	/** The Constant COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "0";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ResultadoTransaccion> grabarSolicitud(AdhesionTodoPagoInEntity inEntity) throws DAOException {
		if (inEntity != null) {
			LOGGER.info("Se consultara el procedure {} con la solicitud {}.", SOLICITUD_PROCEDURE, inEntity);
			List<SqlParameter> parametros = cargarTipoParametros();

			SqlParameterSource in = cargarSource(inEntity);

			try {
				Map<String, Object> respuesta = consultar(SOLICITUD_SCHEMA, SOLICITUD_PACKAGE, SOLICITUD_PROCEDURE, in,
						parametros.toArray(new SqlParameter[parametros.size()]));
				String codRetorno = (String) respuesta.get(OUT_RESULTADO);
				ResultadoTransaccion resultado = new ResultadoTransaccion();
				resultado.setCodigoRespuesta(Integer.parseInt(codRetorno));
				if (COD_RETORNO_OK.equals(codRetorno)) {
					LOGGER.info("Se registro la solicitudTodoPago {} correctamente.", inEntity);
					return respuestaFactory.crearRespuestaOk(ResultadoTransaccion.class, null);
				} else {
					LOGGER.info("Error en store {}.{}.{}. al agregar solicitudTodoPago {}.", SOLICITUD_SCHEMA,
							SOLICITUD_PACKAGE, SOLICITUD_PROCEDURE, inEntity);
					if (respuesta.get(OUT_AMIGABLE) != null) {
						resultado.setMensajeError((String) respuesta.get(OUT_AMIGABLE));
					}
					Respuesta<ResultadoTransaccion> respuestaError = new Respuesta<ResultadoTransaccion>();
					respuestaError.setRespuesta(resultado);
					respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
					return respuestaError;
				}
			} catch (SQLException sqle) {
				LOGGER.error("Error en store {}.{}.{} al agregar solicitudTodoPago {}.", SOLICITUD_SCHEMA,
						SOLICITUD_PACKAGE, SOLICITUD_PROCEDURE, inEntity, sqle);
				throw new DAOException(sqle);
			} catch (RuntimeException ex) {
				LOGGER.error("Error en base del store {}.{}.{} al agregar solicitudTodoPago {}.", SOLICITUD_SCHEMA,
						SOLICITUD_PACKAGE, SOLICITUD_PROCEDURE, inEntity, ex);
				throw new DAOException(ex);
			}
		}
		return respuestaFactory.crearRespuestaError(ResultadoTransaccion.class, null);

	}

	/**
	 * Carga los datos para ejecutar el store procedures.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return el parametro cargado con los valores para ser ejecutado
	 */
	private SqlParameterSource cargarSource(AdhesionTodoPagoInEntity inEntity) {
		return new MapSqlParameterSource()
		        .addValue(P_FECHA_SOLICITUD, inEntity.getFechaSolicitud())
				.addValue(P_NUP, inEntity.getNup())
				.addValue(P_TIPO_DOCUMENTO, inEntity.getTipoDocumento())
				.addValue(P_NRO_DOCUMENTO, inEntity.getNumeroDocumento())
				.addValue(P_RAZON_SOCIAL, inEntity.getRazonSocial())
				.addValue(P_CUIT_DNI, inEntity.getCuitDni())
				.addValue(P_FECHA_NACIMIENTO, inEntity.getFechaNacimiento())
				.addValue(P_SEXO, inEntity.getSexo())
				.addValue(P_PAIS_ORIGEN, inEntity.getPaisOrigen())
				.addValue(P_NRO_CUENTA, inEntity.getNumeroCuenta())
				.addValue(P_CBU, inEntity.getCbu())
				.addValue(P_EMAIL, inEntity.getEmail())
				.addValue(P_TELEFONO_FIJO, inEntity.getTelefonoFijo())
				.addValue(P_CELULAR, inEntity.getCelular())
				.addValue(P_EMPRESA_CELULAR, inEntity.getEmpresaCelular())
				.addValue(P_NOMBRE_CONTACTO, inEntity.getNombreContacto())
				.addValue(P_APELLIDO_CONTACTO, inEntity.getApellidoContacto())
				.addValue(P_CONDICION_IVA, inEntity.getCondicionIVA())
				.addValue(P_CONDICION_IIBB, inEntity.getCondicionIIBB())
				.addValue(P_ACTIVIDAD, inEntity.getActividad())
				.addValue(P_DOMLEGAL_CALLE, inEntity.getDomicilioLegal().getCalle())
				.addValue(P_DOMLEGAL_NUMERO, inEntity.getDomicilioLegal().getNumero())
				.addValue(P_DOMLEGAL_PISO, inEntity.getDomicilioLegal().getPiso())
				.addValue(P_DOMLEGAL_DPTO, inEntity.getDomicilioLegal().getDepartamento())
				.addValue(P_DOMLEGAL_PROVINCIA, inEntity.getDomicilioLegal().getProvincia())
				.addValue(P_DOMLEGAL_LOCALIDAD, inEntity.getDomicilioLegal().getLocalidad())
				.addValue(P_DOMLEGAL_COD_POSTAL, inEntity.getDomicilioLegal().getCodigoPostal())
				.addValue(P_DOMFACT_CALLE, inEntity.getDomicilioFacturacion().getCalle())
				.addValue(P_DOMFACT_NUMERO, inEntity.getDomicilioFacturacion().getNumero())
				.addValue(P_DOMFACT_PISO, inEntity.getDomicilioFacturacion().getPiso())
				.addValue(P_DOMFACT_DPTO, inEntity.getDomicilioFacturacion().getDepartamento())
				.addValue(P_DOMFACT_PROVINCIA, inEntity.getDomicilioFacturacion().getProvincia())
				.addValue(P_DOMFACT_LOCALIDAD, inEntity.getDomicilioFacturacion().getLocalidad())
				.addValue(P_DOMFACT_COD_POSTAL, inEntity.getDomicilioFacturacion().getCodigoPostal())
				.addValue(P_ENVIO_EMAIL_SATISFACTORIO, inEntity.getEnvioMailSatisfactorio());
	}

	/**
	 * Cargar los tipos de parametros para ejecutar el store procedure.
	 *
	 * @return the list
	 */
	private List<SqlParameter> cargarTipoParametros() {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(P_FECHA_SOLICITUD, Types.DATE));
		parametros.add(new SqlParameter(P_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(P_TIPO_DOCUMENTO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_NRO_DOCUMENTO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_RAZON_SOCIAL, Types.VARCHAR));
		parametros.add(new SqlParameter(P_CUIT_DNI, Types.VARCHAR));
		parametros.add(new SqlParameter(P_FECHA_NACIMIENTO, Types.DATE));
		parametros.add(new SqlParameter(P_SEXO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_PAIS_ORIGEN, Types.VARCHAR));
		parametros.add(new SqlParameter(P_NRO_CUENTA, Types.VARCHAR));
		parametros.add(new SqlParameter(P_CBU, Types.VARCHAR));
		parametros.add(new SqlParameter(P_EMAIL, Types.VARCHAR));
		parametros.add(new SqlParameter(P_TELEFONO_FIJO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_CELULAR, Types.VARCHAR));
		parametros.add(new SqlParameter(P_EMPRESA_CELULAR, Types.VARCHAR));
		parametros.add(new SqlParameter(P_NOMBRE_CONTACTO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_APELLIDO_CONTACTO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_CONDICION_IVA, Types.VARCHAR));
		parametros.add(new SqlParameter(P_CONDICION_IIBB, Types.VARCHAR));
		parametros.add(new SqlParameter(P_ACTIVIDAD, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMLEGAL_CALLE, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMLEGAL_NUMERO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMLEGAL_PISO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMLEGAL_DPTO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMLEGAL_PROVINCIA, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMLEGAL_LOCALIDAD, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMLEGAL_COD_POSTAL, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMFACT_CALLE, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMFACT_NUMERO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMFACT_PISO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMFACT_DPTO, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMFACT_PROVINCIA, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMFACT_LOCALIDAD, Types.VARCHAR));
		parametros.add(new SqlParameter(P_DOMFACT_COD_POSTAL, Types.VARCHAR));
		parametros.add(new SqlParameter(P_ENVIO_EMAIL_SATISFACTORIO, Types.VARCHAR));

		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));

		return parametros;
	}

	/* 
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.todopago.dao.SolicitudAdhesionTodoPagoDAO#
	 * generarComprobante(ar.com.santanderrio.obp.servicios.todopago.web.view.ComprobanteAdhesionTodoPagoView)
	 */
	@Override
	public Reporte generarComprobante(ComprobanteAdhesionTodoPagoView view) {
		Reporte reporte = new Reporte();
		Resource reportFile = reportFileAdhesion;
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(PARAM_LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(PARAM_LOGO_DEFAULT, logoDefault.getFile().getPath());

			parameters.put(PARAM_NOMBRE, view.getNombreContacto());
			parameters.put(PARAM_APELLIDO, view.getApellidoContacto());
			parameters.put(PARAM_DNI, view.getNumeroDocumento());
			parameters.put(PARAM_CUIT_CUIL, view.getCuitDni());
			parameters.put(PARAM_FECHA_NAC, ISBANStringUtils.formatearFecha(view.getFechaNacimiento(), "dd/MM/yyyy"));
			parameters.put(PARAM_SEXO, view.getSexo());
			parameters.put(PARAM_PAIS_ORIGEN, view.getPaisOrigen());
			parameters.put(PARAM_COND_IVA, view.getCondicionIVA());
			parameters.put(PARAM_COND_IIBB, view.getCondicionIIBB() != null ? view.getCondicionIIBB() : "");
			parameters.put(PARAM_ACTIVIDAD, view.getActividad() != null ? view.getActividad() : "");
			parameters.put(PARAM_CALLE, view.getDomicilioLegal().getCalle());
			parameters.put(PARAM_NUMERO, view.getDomicilioLegal().getNumero());
			parameters.put(PARAM_PISO, view.getDomicilioLegal().getPiso());
			parameters.put(PARAM_DEPTO, view.getDomicilioLegal().getDepartamento());
			parameters.put(PARAM_PROVINCIA, view.getDomicilioLegal().getProvincia());
			parameters.put(PARAM_LOCALIDAD, view.getDomicilioLegal().getLocalidad());
			parameters.put(PARAM_COD_POSTAL, view.getDomicilioLegal().getCodigoPostal());
			parameters.put(PARAM_EMAIL, view.getEmail());
			parameters.put(PARAM_TELEFONO_FIJO, view.getTelefonoFijo());
			parameters.put(PARAM_TELEFONO_CELULAR, (view.getCelular() != null && !view.getCelular().startsWith("(0)")) ? view.getCelular() : "");
			parameters.put(PARAM_EMPRESA_CELULAR, view.getEmpresaCelular() != null ? view.getEmpresaCelular() : "");
			parameters.put(PARAM_CUENTA, view.getNumeroCuenta());
			parameters.put(PARAM_DESC_CUENTA, view.getDescripcionTipoCuenta());
			parameters.put(PARAM_NRO_COMPROBANTE, FechaUtils.getDateTimeYYYYMMDDhhmmssff2(view.getFechaSolicitud()));
			parameters.put(PARAM_LOGO_CIERRE, logoCierre.getFile().getPath());
			parameters.put(PARAM_FECHA_OP, ISBANStringUtils.formatearFecha(view.getFechaSolicitud(), "dd/MM/yyyy HH:mm"));

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			String filenamePrefix = FILENAME_PREFIX_ADHESION;
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
}
