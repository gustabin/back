/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.prestamos.dao.ReporteStopDebitPrestamosDAO;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteStopDebitPrestamoView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class StopDebitDAOImpl.
 */
@Component
/**
 * Conector con el servicio STPDEBPAU
 * 
 * @author b039920
 *
 */
public class ReporteStopDebitPrestamosDAOImpl implements ReporteStopDebitPrestamosDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteStopDebitPrestamosDAOImpl.class);
	
	
	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;
	
	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoPie;
	
    /** The CompStopDebitPrestamo. */
    @Value("classpath:/report/prestamos/comprobante/CompStopDebitPrestamo.jasper")
    private Resource fileJasperCompStopDebitPrestamo;
	

	private static final String PDF_EXTENSION = ".pdf";
	
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
		
	
	@Override
	public Reporte generarComprobanteStopDebitPrestamos(ComprobanteStopDebitPrestamoView comprobanteView) {
		Reporte reporte = new Reporte();
		//JasperReport jasperReport = null;
		
		try {
			// se carga el reporte
			//jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteStopDebitPrestamos.getInputStream());
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasperCompStopDebitPrestamo.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			// se procesa el archivo jasper

			parameters.put("LOGO_CABECERA", logoCabecera.getFile().getPath());
			parameters.put("LOGO_PIE", logoPie.getFile().getPath());		
			parameters.put("NRO_COMPROBANTE", comprobanteView.getComprobante());
			parameters.put("FECHA", comprobanteView.getFecha());
        	parameters.put("LEGALES", comprobanteView.getLegalStopDebitPrestamos());
        	parameters.put("PRESTAMOS_PERIODOS", comprobanteView.getPeriodosStopDebitPrestamos());
            parameters.put(SUBREPORT_DIR, fileJasperCompStopDebitPrestamo.getFile().getParent() + File.separator);
			

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre("Comprobante de solicitud de stop debit " + comprobanteView.getComprobante() + PDF_EXTENSION);
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
