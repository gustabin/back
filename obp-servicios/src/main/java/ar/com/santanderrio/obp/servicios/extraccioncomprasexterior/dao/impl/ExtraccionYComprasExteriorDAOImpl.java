/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

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
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao.ExtraccionYComprasExteriorDAO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ComprobanteOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Extraccion y compras al exterior DAO Impl.
 *
 * @author Silvina_Luque
 */
@Component
public class ExtraccionYComprasExteriorDAOImpl extends IatxBaseDAO implements ExtraccionYComprasExteriorDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExtraccionYComprasExteriorDAOImpl.class);

	/** The servicio CNSMASCTAS. */
	private static final String SERVICIO_CNSMASCTAS = "CNSMASCTAS";

	/** The version CNSMASCTAS. */
	private static final String VERSION_CNSMASCTAS = "100";

	/** The servicio CNSTARXNUP . */
	private static final String SERVICIO_CNSTARXNUP = "CNSTARXNUP";

	/** The version CNSTARXNUP . */
	private static final String VERSION_CNSTARXNUP = "100";

	/** The servicio CMBRIOTARJ . */
	private static final String SERVICIO_CMBRIOTARJ = "CMBRIOTARJ";

	/** The version CMBRIOTARJ . */
	private static final String VERSION_CMBRIOTARJ = "100";

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The file jasper. */
	@Value("classpath:/report/extraccionYComprasExterior/ComprobanteExtYComprasExterior.jasper")
	private Resource fileJasperComprobanteOperaExterior;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo debito. */
	@Value("classpath:/report/comprobantes/logoDebito.png")
	private Resource logoDebito;

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant LOGO_DEBITO. */
	private static final String LOGO_DEBITO = "LOGO_DEBITO";

	/** The Constant FECHA. */
	private static final String FECHA = "FECHA";

	/** The Constant NRO_CUENTA_FORMAT. */
	private static final String NRO_CUENTA_FORMAT = "NRO_CUENTA_FORMAT";

	/** The Constant NRO_TARJETA_FORMAT. */
	private static final String NRO_TARJETA_FORMAT = "NRO_TARJETA_FORMAT";

	/** The Constant NRO_COMPROBANTE. */
	private static final String NRO_COMPROBANTE = "NRO_COMPROBANTE";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/**
	 * Este servicio devuelve las cuantas asociadas a una tarjeta utilizando el
	 * servicio iatx CNSMASCTAS100.
	 *
	 * @param cuentasExteriorInEntity
	 *            the cuentas exterior in entity
	 * @return the consulta cuentas opera exterior out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	public ConsultaCuentasOperaExteriorOutEntity consultarCuentasOperaExterior(
			ConsultaCuentasOperaExteriorInEntity cuentasExteriorInEntity) throws DAOException {
		LOGGER.info("ExtraccionComprasExteriorDAOImpl _ Iniciando metodo consultaCuentas");
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSMASCTAS, VERSION_CNSMASCTAS);
		ConsultaCuentasOperaExteriorOutEntity cuentasExteriorOutEntity = new ConsultaCuentasOperaExteriorOutEntity();

		try {
			LOGGER.debug("ExtraccionComprasExteriorDAOImpl _ Iniciando llamada iatx CNSMASCTAS");
			IatxRequestData iatxRequestData = this.generateRequestDataCnsMasCtas(cuentasExteriorInEntity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			LOGGER.debug("ExtraccionComprasExteriorDAOImpl _ Finalizando llamada iatx CNSMASCTAS");
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				cuentasExteriorOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaCuentasOperaExteriorOutEntity.class);
			} else {
				LOGGER.debug("Error servicio CNSMASCTAS, codigo retorno distinto de 0");
				throw new DAOException("");
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		LOGGER.debug("ExtraccionComprasExteriorDAOImpl _ Finalizando llamada consultaCuentas");
		return cuentasExteriorOutEntity;
	}

	/**
	 * Generate request data cns mas ctas.
	 *
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCnsMasCtas(ConsultaCuentasOperaExteriorInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getTipoConsulta());
		iatxRequestData.addBodyValue(entity.getCuentasRelacionadas());
		iatxRequestData.addBodyValue(entity.getNumeroTarjeta());
		iatxRequestData.addBodyValue(entity.getTipoTarjeta());
		return iatxRequestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao.
	 * ExtraccionYComprasExteriorDAO#consultarTarjetasOperaExterior(ar.com.
	 * santanderrio.obp.servicios.extraccioncomprasexterior.entity.
	 * ConsultaTarjetasOperaExteriorInEntity)
	 */
	@Override
	public ConsultaTarjetasOperaExteriorOutEntity consultarTarjetasOperaExterior(
			ConsultaTarjetasOperaExteriorInEntity tarjetasExteriorInEntity) throws DAOException {
		LOGGER.info("ExtraccionComprasExteriorDAOImpl _ Iniciando metodo consultaTarjetas");
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSTARXNUP, VERSION_CNSTARXNUP);
		ConsultaTarjetasOperaExteriorOutEntity tarjetasExteriorOutEntity = new ConsultaTarjetasOperaExteriorOutEntity();

		try {
			LOGGER.debug("ExtraccionComprasExteriorDAOImpl _ Iniciando llamada iatx SERVICIO_CNSTARXNUP");
			IatxRequestData iatxRequestData = this.generateRequestDataCnsTarXnup(tarjetasExteriorInEntity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			LOGGER.debug("ExtraccionComprasExteriorDAOImpl _ Finalizando llamada iatx SERVICIO_CNSTARXNUP");
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				tarjetasExteriorOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaTarjetasOperaExteriorOutEntity.class);
			} else {
				LOGGER.debug("Error servicio SERVICIO_CNSTARXNUP, codigo retorno distinto de 0");
				throw new DAOException("Error servicio SERVICIO_CNSTARXNUP, codigo retorno distinto de 0");
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		LOGGER.debug("ExtraccionComprasExteriorDAOImpl _ Finalizando llamada consultaTarjetas");
		return tarjetasExteriorOutEntity;
	}

	/**
	 * Generate request data cns tar xnup.
	 *
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCnsTarXnup(ConsultaTarjetasOperaExteriorInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getTipoConsulta());
		iatxRequestData.addBodyValue(entity.getDatosADevolver());
		iatxRequestData.addBodyValue(entity.getCantidadPedida());
		iatxRequestData.addBodyValue(entity.getNup());
		iatxRequestData.addBodyValue(entity.getDatosAdicionaes());
		iatxRequestData.addBodyValue(entity.getClaveSegundoLlamado());

		return iatxRequestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao.
	 * ExtraccionYComprasExteriorDAO#cambioTarjetaOperaExterior(ar.com.
	 * santanderrio.obp.servicios.extraccioncomprasexterior.entity.
	 * CambioTarjetaOperaExteriorInEntity)
	 */
	@Override
	public CambioTarjetaOperaExteriorOutEntity cambioTarjetaOperaExterior(
			CambioTarjetaOperaExteriorInEntity cambioTarjeta) throws DAOException {
		LOGGER.info("ExtraccionComprasExteriorDAOImpl _ Iniciando metodo cambio tarjeta");
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CMBRIOTARJ, VERSION_CMBRIOTARJ);
		CambioTarjetaOperaExteriorOutEntity cambioTarjetaExteriorOutEntity = new CambioTarjetaOperaExteriorOutEntity();

		try {
			LOGGER.debug("ExtraccionComprasExteriorDAOImpl _ Iniciando llamada iatx SERVICIO_CMBRIOTARJ");
			IatxRequestData iatxRequestData = this.generateRequestDataCmbRioTarj(cambioTarjeta);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			LOGGER.debug("ExtraccionComprasExteriorDAOImpl _ Finalizando llamada iatx SERVICIO_CMBRIOTARJ");
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				cambioTarjetaExteriorOutEntity = processTrama(iatxResponse.getTrama(),
						CambioTarjetaOperaExteriorOutEntity.class);
				cambioTarjetaExteriorOutEntity.setNroComprobante(iatxResponse.getNroComprobante());
				cambioTarjetaExteriorOutEntity.setFecha(iatxResponse.getFecha());
				cambioTarjetaExteriorOutEntity.setHora(iatxResponse.getHora());

			} else {
				LOGGER.debug("Error servicio SERVICIO_CMBRIOTARJ, codigo retorno distinto de 0");
				throw new DAOException("Error servicio SERVICIO_CMBRIOTARJ, codigo retorno distinto de 0");
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		LOGGER.debug("ExtraccionComprasExteriorDAOImpl _ Finalizando llamada cambio tarjeta");
		return cambioTarjetaExteriorOutEntity;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao.
	 * ExtraccionYComprasExteriorDAO#descargarComprobante(ar.com.santanderrio.
	 * obp.servicios.extraccioncomprasexterior.entity.
	 * ComprobanteOperaExteriorInEntity)
	 */
	@Override
	public Reporte descargarComprobante(ComprobanteOperaExteriorInEntity datos) {
		LOGGER.info("ExtraccionYCompras exterior iniciando descargar comprobante");
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			// se carga el reporte
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteOperaExterior.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			// se procesa el archivo jasper

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			LOGGER.debug("Completando parametros de reporte ExtYComprasExterio");
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEBITO, logoDebito.getFile().getPath());
			parameters.put(NRO_COMPROBANTE, datos.getNroComprobante());
			parameters.put(NRO_CUENTA_FORMAT, datos.getNroCuenta());
			parameters.put(NRO_TARJETA_FORMAT, datos.getNroTarjeta());
			parameters.put(FECHA, datos.getFecha());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			byte[] byteArray = outStream.toByteArray();
			Reporte reporteOperaExterior = new Reporte();
			reporteOperaExterior.setTipoArchivo(TipoArchivoEnum.PDF);
			reporteOperaExterior.setBytes(byteArray);
			reporteOperaExterior.setNombre("Comprobante_ExtYComprasExterior.pdf");
			LOGGER.debug("ExtraccionYComprasExteriorDAO finalizando descargar comprobante");
			return reporteOperaExterior;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
	}

	/**
	 * Generate request data cmb rio tarj.
	 *
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCmbRioTarj(CambioTarjetaOperaExteriorInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getTipoLlamado()); // M
		iatxRequestData.addBodyValue(entity.getTipoModificacion()); // T
		iatxRequestData.addBodyValue(entity.getClaveModificacion()); // nro
																		// tarjeta
		iatxRequestData.addBodyValue(entity.getCodigoCambio()); // 11
		iatxRequestData.addBodyValue(entity.getDatosVariables()); // cuenta
																	// formato
																	// santader
																	// rio

		return iatxRequestData;
	}

}
