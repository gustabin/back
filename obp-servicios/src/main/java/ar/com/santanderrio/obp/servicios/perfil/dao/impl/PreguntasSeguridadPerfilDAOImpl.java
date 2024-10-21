/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.perfil.dao.PreguntasSeguridadPerfilDAO;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaPreguntasSeguridadInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.RespuestaSeguridadEntity;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * PreguntasSeguridadDAOImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class PreguntasSeguridadPerfilDAOImpl extends IatxBaseDAO implements PreguntasSeguridadPerfilDAO {

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PreguntasSeguridadPerfilDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The servicio SERVICIO_CNSNPHRECU. */
	private static final String SERVICIO_CNSNPHRECU = "CNSNPHRECU";

	/** The version VERSION_CNCNPHRECU. */
	private static final String VERSION_CNSNPHRECU = "100";

	/** The servicio ACTNPHRECU. */
	private static final String SERVICIO_ACTNPHRECU = "ACTNPHRECU";

	/** The version ACTNPHRECU. */
	private static final String VERSION_ACTNPHRECU = "100";

	/** The file jasper. */
	@Value("classpath:/report/perfil/preguntasSeguridad/comprobantePreguntasSeguridad.jasper")
	private Resource fileJasperComprobantePreguntasSeguridad;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant FECHA. */
	private static final String FECHA = "FECHA";

	/** The Constant NRO_COMPROBANTE. */
	private static final String NRO_COMPROBANTE = "NRO_COMPROBANTE";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.perfil.dao.PreguntasSeguridadPerfilDAO#
	 * consultaPreguntasSeguridad(ar.com.santanderrio.obp.servicios.perfil.
	 * entities.ConsultaPreguntasSeguridadInEntity)
	 */
	@Override
	public ConsultaPreguntasSeguridadOutEntity consultaPreguntasSeguridad(
			ConsultaPreguntasSeguridadInEntity preguntasSeguridadInEntity) throws DAOException {
		LOGGER.info("PreguntasSeguridadDAOImpl _ Iniciando metodo consultaPreguntasSeguridad");
		ConsultaPreguntasSeguridadOutEntity consultaPreguntasSeguridadOutEntity = new ConsultaPreguntasSeguridadOutEntity();
		try {
			IatxRequest request = new IatxRequest(SERVICIO_CNSNPHRECU, VERSION_CNSNPHRECU);
			IatxRequestData requestData = generarRequestDataCnsNphRecu(preguntasSeguridadInEntity);
			request.setData(requestData);
			IatxResponse iatxResponse = null;
			iatxResponse = iatxComm.exec(request);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				LOGGER.debug("PreguntasSeguridadDAOImpl _ Procesando trama consultaPreguntasSeguridad");
				consultaPreguntasSeguridadOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaPreguntasSeguridadOutEntity.class);
			} else {
				manejarErrorCnsPreguntasSeguridad(errorCode, iatxResponse.getErrorSystem());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		LOGGER.debug("PreguntasSeguridadDAOImpl _ Finalizando consultaPreguntasSeguridad");
		return consultaPreguntasSeguridadOutEntity;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.perfil.dao.PreguntasSeguridadPerfilDAO#
	 * guardarPreguntasSeguridad(ar.com.santanderrio.obp.servicios.perfil.
	 * entities.ModificacionPreguntasSeguridadInEntity)
	 */
	@Override
	public ModificacionPreguntasSeguridadOutEntity guardarPreguntasSeguridad(
			ModificacionPreguntasSeguridadInEntity entity) throws DAOException {
		LOGGER.info("PreguntasSeguridadDAOImpl _ Iniciando metodo guardarPreguntasSeguridad");
		ModificacionPreguntasSeguridadOutEntity modificacionPreguntasSeguridadOutEntity = new ModificacionPreguntasSeguridadOutEntity();
		try {
			IatxRequest request = new IatxRequest(SERVICIO_ACTNPHRECU, VERSION_ACTNPHRECU);
			IatxRequestData requestData = generarRequestDataActNPhRecu(entity);
			request.setData(requestData);
			IatxResponse iatxResponse = null;
			iatxResponse = iatxComm.exec(request);
			int errorCode = iatxResponse.getErrorCode();
			modificacionPreguntasSeguridadOutEntity.setCodigoRetorno(errorCode);
			if (OK_CODIGO_RETORNO == errorCode) {
				LOGGER.debug("PreguntasSeguridadDAOImpl _ Guardar Preguntas Seguridad OK");
				modificacionPreguntasSeguridadOutEntity.setNroComprobante(iatxResponse.getNroComprobante());
			} else {
				manejarErrorGuardarPreguntasSeguridad(errorCode, iatxResponse.getErrorSystem());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		LOGGER.debug("PreguntasSeguridadDAOImpl _ Finalizando guardarPreguntasSeguridad");
		return modificacionPreguntasSeguridadOutEntity;

	}

	/**
	 * Manejar error cns preguntas seguridad.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorSystem
	 *            the error system
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void manejarErrorCnsPreguntasSeguridad(int errorCode, String errorSystem) throws DAOException {
		LOGGER.debug("Codigo de error no esperado de iatx en servicio CNSNPHRECU. %s  -  %s ", errorCode, errorSystem);
		throw new DAOException();
	}

	/**
	 * Manejar error guardar preguntas seguridad.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorSystem
	 *            the error system
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void manejarErrorGuardarPreguntasSeguridad(int errorCode, String errorSystem) throws DAOException {
		LOGGER.debug("Codigo de error no esperado de iatx en servicio ACTNPHRECU. %s  -  %s ", errorCode, errorSystem);
		throw new DAOException();
	}

	/**
	 * Generar request data cns nph recu.
	 *
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequestDataCnsNphRecu(ConsultaPreguntasSeguridadInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getRecuperarRespuestas());
		return iatxRequestData;
	}

	/**
	 * Generar request data act N ph recu.
	 *
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequestDataActNPhRecu(ModificacionPreguntasSeguridadInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(ISBANStringUtils.fillNum(entity.getCantidadPreguntas(), 3));
		for (Iterator<RespuestaSeguridadEntity> iterator = entity.getRespuestasSeguridad().iterator(); iterator
				.hasNext();) {
			RespuestaSeguridadEntity pEntity = iterator.next();
			iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(pEntity.getIdPregunta(), 3));
			iatxRequestData.addBodyValue(pEntity.getDescripcionRespuesta());
		}

		return iatxRequestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.perfil.dao.PreguntasSeguridadPerfilDAO#
	 * descargarComprobante(ar.com.santanderrio.obp.servicios.perfil.entities.
	 * DatosComprobanteEntity)
	 */
	@Override
	public Reporte descargarComprobante(DatosComprobanteEntity datos) {
		LOGGER.info("PreguntasSeguridadDAO iniciando descargar comprobante");
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			// se carga el reporte
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobantePreguntasSeguridad.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			// se procesa el archivo jasper

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			LOGGER.debug("Completando parametros de reporte Preguntas de Seguridad");
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(NRO_COMPROBANTE, datos.getNroComprobante());
			parameters.put(FECHA, datos.getFecha());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			byte[] byteArray = outStream.toByteArray();
			Reporte reportePreguntasSeguridad = new Reporte();
			reportePreguntasSeguridad.setTipoArchivo(TipoArchivoEnum.PDF);
			reportePreguntasSeguridad.setBytes(byteArray);
			reportePreguntasSeguridad.setNombre("Comprobante_PreguntasSeguridad.pdf");
			LOGGER.info("PreguntasSeguridad finalizando descargar comprobante");
			return reportePreguntasSeguridad;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
	}

}
