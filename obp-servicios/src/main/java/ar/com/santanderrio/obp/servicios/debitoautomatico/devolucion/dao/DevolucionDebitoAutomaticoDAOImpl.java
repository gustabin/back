package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DatosComprobanteDevolucionDA;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.SolicitudDevolucionDebitoOutEntity;
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
 * The Class DevolucionDebitoAutomaticoDAOImpl.
 */
@Component
public class DevolucionDebitoAutomaticoDAOImpl  extends IatxBaseDAO implements DevolucionDebitoAutomaticoDAO{

	private static final String FUNCION_INGRESO_REVERSION = "I";

	private static final String VERSION_PROGRAMA_DEVOLUCION = "100";

	private static final String PROGRAMA_DEVOLUCION = "ACTDDIREV_";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;
	
	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;
	
	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/cabecera_comprobante.png")
	private Resource logoCabecera;
	
	/** The logo pie. */
	@Value("classpath:/report/comprobantes/cierre de comprobante.png")
	private Resource logoPie;
	
	/** The archivo jasper devolucion debito automatico. */
	@Value("classpath:/report/devolucionDebitoAutomatico/comprobanteDevolucionDebitoAutomatico.jasper")
	private Resource archivoJasperDevolucionDebitoAutomatico;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DevolucionDebitoAutomaticoDAOImpl.class);

	/**
	 * Generar comprobante PDF.
	 *
	 * @param datosComprobante the datos comprobante
	 * @return the reporte
	 */
	@Override
	public Reporte generarComprobantePDF(DatosComprobanteDevolucionDA datosComprobante) {		
		
		try {		
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(archivoJasperDevolucionDebitoAutomatico.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = datosComprobante.obtenerParametrosPDF();

			parameters.put("LOGO_CABECERA", logoCabecera.getFile().getPath());
			parameters.put("LOGO_PIE", logoPie.getFile().getPath());
		
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("comprobanteDevolucionDebitoAutomatico.pdf");
			return reporte;
		} catch (JRException ex) {
			LOGGER.info("Error generando el PDF");
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.info("Error generando el PDF");
			throw new ISBANRuntimeException(e);
		}
	}

	/**
	 * Ejecutar solicitud devolucion DA.
	 *
	 * @param cliente the cliente
	 * @param cuitEmpresa the cuit empresa
	 * @param servicio the servicio
	 * @param partida the partida
	 * @param idCliente the id cliente
	 * @param fechaVencimiento the fecha vencimiento
	 * @return the solicitud devolucion debito out entity
	 */
	@Override
	public SolicitudDevolucionDebitoOutEntity ejecutarSolicitudDevolucionDA(Cliente cliente, String cuitEmpresa, String servicio, String partida, String idCliente,
			String fechaVencimiento) {

		IatxRequest iatxRequest = new IatxRequest(PROGRAMA_DEVOLUCION, VERSION_PROGRAMA_DEVOLUCION);

		try {
			IatxRequestData iatxRequestData = generateRequestDataACTDDIREV(cliente, cuitEmpresa, servicio, partida, idCliente, fechaVencimiento);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int returnCode = iatxResponse.getErrorCode();
			SolicitudDevolucionDebitoOutEntity entity = new SolicitudDevolucionDebitoOutEntity();
			if (OK_CODIGO_RETORNO == returnCode) {
				entity = processTrama(iatxResponse.getTrama(), SolicitudDevolucionDebitoOutEntity.class);
				LOGGER.info("Se ha realizado la devolucion con exito");
			} else {
				LOGGER.error("Codigo de error no esperado de iatx en servicio {}, return code: {}", PROGRAMA_DEVOLUCION, returnCode);
				entity.setCodigoResultadoExtendido(String.valueOf(returnCode));
			}
			return entity;
		}
		 catch (IatxException e) {
			LOGGER.error("Error al realizar la devolucion del debito para el cliente {}", cliente.getNup(), e);
			return null;
		}
	}

	/**
	 * Generate request data ACTDDIREV.
	 *
	 * @param cliente the cliente
	 * @param cuitEmpresa the cuit empresa
	 * @param servicio the servicio
	 * @param partida the partida
	 * @param idCliente the id cliente
	 * @param fechaVencimiento the fecha vencimiento
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataACTDDIREV(Cliente cliente, String cuitEmpresa, String servicio, String partida, String idCliente,
			String fechaVencimiento) {

		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		// FUNCION A1
		iatxRequestData.addBodyValue(FUNCION_INGRESO_REVERSION);
		// CUIT N11
		iatxRequestData.addBodyValue(cuitEmpresa);
		// SERVICIO A10
		iatxRequestData.addBodyValue(StringUtils.rightPad(servicio, 10, " ")); 
		// PARTIDA A22
		iatxRequestData
				.addBodyValue(StringUtils.rightPad(partida, 22, " "));
		// IDENTIF-UNIV A15
		iatxRequestData.addBodyValue(StringUtils.rightPad(idCliente, 10, " "));
			
		SimpleDateFormat mainframeFormatter = new SimpleDateFormat("yyyyMMdd");
	    try {
	    	SimpleDateFormat frontFormatter = new SimpleDateFormat("dd/MM/yyyy");
	    	Date date = frontFormatter.parse(fechaVencimiento);
	    	String fecVto = mainframeFormatter.format(date);
	    	// FEC-VTO-AAAMMDD A8
	    	iatxRequestData.addBodyValue(fecVto);
	    } catch (ParseException e) {
	      LOGGER.error("No pudo obtener la fecha de vencimiento");
	    }
		      
	    // FEC-PROC-AAAMMDD A8
	    Calendar now = Calendar.getInstance();
	    String today = mainframeFormatter.format(now.getTime());
	    iatxRequestData.addBodyValue(today);
			
		return iatxRequestData;
	}
	

}
