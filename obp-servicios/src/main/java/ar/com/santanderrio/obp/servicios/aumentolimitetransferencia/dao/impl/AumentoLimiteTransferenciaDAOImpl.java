/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.WSGC;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao.AumentoLimiteTransferenciaDAO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.AumentoLimiteTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.AumentoLimiteTransferenciaOutEntity;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.DatosComprobanteAumentoLimiteTransferencia;
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
 * The Class AumentoLimiteTransferenciaDAOImpl.
 */
@Component
public class AumentoLimiteTransferenciaDAOImpl implements AumentoLimiteTransferenciaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AumentoLimiteTransferenciaDAOImpl.class);

	/** Gestionar ws SOLICITUDES. */
	@Autowired
	@Qualifier("solicitudProductoWS")
	private GestionarWS<WSGC> wsClient;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo default. */
	@Value("classpath:/report/comprobantes/logo_visa.png")
	private Resource logoDefault;

	/** The file jasper agendar. */
	@Value("classpath:/report/aumentoLimiteTransferencia/ComprobanteSolicitudAumentoLimiteTransferencia.jasper")
	private Resource fileJasperSolicitudAumentoLimiteTransferencia;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoPie;

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant FECHA_EJECUCION. */
	private static final String FECHA_EJECUCION = "FECHA_EJECUCION";

	/** The Constant NRO_CUENTA_ORIGEN. */
	private static final String NRO_CUENTA_ORIGEN = "NRO_CUENTA_ORIGEN";

	/** The Constant TIPO_CUENTA_ORIGEN. */
	private static final String TIPO_CUENTA_ORIGEN = "TIPO_CUENTA_ORIGEN";

	/** The Constant NRO_CUENTA_DESTINO. */
	private static final String NRO_CUENTA_DESTINO = "NRO_CUENTA_DESTINO";

	/** The Constant TIPO_CUENTA_DESTINO. */
	private static final String TIPO_CUENTA_DESTINO = "TIPO_CUENTA_DESTINO";

	/** The Constant BANCO. */
	private static final String BANCO = "BANCO";

	/** The Constant DESTINATARIO. */
	private static final String DESTINATARIO = "DESTINATARIO";

	/** The Constant IMPORTE. */
	private static final String IMPORTE = "IMPORTE";

	/** The Constant NRO_COMPROBANTE. */
	private static final String NRO_COMPROBANTE = "NRO_COMPROBANTE";

	/** Constante de la fecha de operacion. */
	private static final String FECHA_OPERACION = "FECHA_OPERACION";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_PIE = "LOGO_PIE";

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao.
	 * AumentoLimiteTransferenciaDAO#altaSolicitudAumentoLimiteTransferencia(ar.
	 * com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.
	 * AumentoLimiteTransferenciaInEntity)
	 */
	@Override
	public Respuesta<AumentoLimiteTransferenciaOutEntity> altaSolicitudAumentoLimiteTransferencia(
			AumentoLimiteTransferenciaInEntity inEntity) throws DAOException {

		WSGC services = null;
		ResultadoAltaWS resultadoWS;
		AumentoLimiteTransferenciaOutEntity outEntity;
		Respuesta<AumentoLimiteTransferenciaOutEntity> respuestaFinal = new Respuesta<AumentoLimiteTransferenciaOutEntity>();
		try {
			LOGGER.info("Se consume el ws altaGestionUser en AumentoLimiteTransferenciaDAOImpl");
			services = wsClient.obtenerPort();
			resultadoWS = services.altaGestionUser(inEntity.getCodAsociacion(), inEntity.getTipoPersona(),
					inEntity.getNup(), inEntity.getCodUser(), inEntity.getMedioIngreso(), inEntity.getComentario(),
					inEntity.getInfoRequerida());
			outEntity = new AumentoLimiteTransferenciaOutEntity();
			if (resultadoWS.getCodRetorno() == 0) {
				outEntity.setNroGestion(resultadoWS.getIdeGestionNro().toString());
				respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
				respuestaFinal.setRespuesta(outEntity);
			} else {
				respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);
				if (resultadoWS.getDescRetorno() != null) {
					List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
					mensajesList.add(new ItemMensajeRespuesta(resultadoWS.getDescRetorno()));
					respuestaFinal.addAll(mensajesList);
				}
			}
		} catch (RuntimeException e) {
			LOGGER.error("Hubo un error al invocar al ws altaGestionUser en AumentoLimiteTransferenciaDAOImpl", e);
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		} catch (TimeOutException e) {
			LOGGER.error("Hubo un error al invocar al ws altaGestionUser en AumentoLimiteTransferenciaDAOImpl", e);
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		} finally {
			wsClient.liberarPort(services);
		}
		LOGGER.info("Se retorna el resultado del llamado al ws altaGestionUser en AumentoLimiteTransferenciaDAOImpl");
		return respuestaFinal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao.
	 * AumentoLimiteTransferenciaDAO#
	 * generarComprobanteAumentoLimiteTransferencia(ar.com.santanderrio.obp.
	 * servicios.aumentolimitetransferencia.web.view.
	 * DatosComprobanteAumentoLimiteTransferencia)
	 */
	@Override
	public Reporte generarComprobanteAumentoLimiteTransferencia(
			DatosComprobanteAumentoLimiteTransferencia datosComprobante) {
		LOGGER.info("Se genera el comprobante de solicitud de aumento de limite de transferencia");
		Reporte reporte = new Reporte();

		try {
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(fileJasperSolicitudAumentoLimiteTransferencia.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(FECHA_EJECUCION, datosComprobante.getFechaEjecucion());
			parameters.put(NRO_CUENTA_ORIGEN, datosComprobante.getNroCuentaOrigen());
			parameters.put(TIPO_CUENTA_ORIGEN, datosComprobante.getTipoCuentaOrigen());
			parameters.put(NRO_CUENTA_DESTINO, datosComprobante.getNroCuentaDestino());
			parameters.put(TIPO_CUENTA_DESTINO, datosComprobante.getTipoCuentaDestino());
			parameters.put(BANCO, datosComprobante.getBanco());
			parameters.put(DESTINATARIO, datosComprobante.getDestinatario());
			parameters.put(IMPORTE, datosComprobante.getImporte());
			parameters.put(NRO_COMPROBANTE, datosComprobante.getNroComprobante());
			parameters.put(FECHA_OPERACION, datosComprobante.getFechaOperacion());
			parameters.put(LOGO_PIE, logoPie.getFile().getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre("Comprobante_Solicitud_Aumento_Limite_Transferencia_"
					+ datosComprobante.getNroComprobante() + PDF_EXTENSION);
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);

		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
		LOGGER.info("Se retorna el comprobante generado de solicitud de aumento de limite de transferencia");
		return reporte;

	}
}
