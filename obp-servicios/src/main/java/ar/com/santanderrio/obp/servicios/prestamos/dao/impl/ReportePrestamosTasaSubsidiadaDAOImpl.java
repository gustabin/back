/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.prestamos.dao.ReportePrestamosTasaSubsidiadaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaSubsidiadaView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReportePrestamosTasaSubsidiadaDAOImpl.
 */
@Component
public class ReportePrestamosTasaSubsidiadaDAOImpl implements ReportePrestamosTasaSubsidiadaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportePrestamosTasaSubsidiadaDAOImpl.class);
	
	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoPie;
	
    /** The CompStopDebitPrestamo. */
    @Value("classpath:/report/prestamos/comprobante/comprobantePrestamoTasaSub.jasper")
    private Resource fileJasperCompPrestamoTasaSub;
	

	private static final String PDF_EXTENSION = ".pdf";
    
    private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	
	@Override
	public Reporte generarComprobantePrestamoTasaSubsidiada(ComprobantePrestamoTasaSubsidiadaView comprobantePrestamoTasaSubsidiadaView) {
		Reporte reporte = new Reporte();
		
		try {
			// se carga el reporte
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(comprobantePrestamoTasaSubsidiadaView.obtenerJasper());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, obtenerParametros(comprobantePrestamoTasaSubsidiadaView), new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre("Comprobante de solicitud de prestamo a tasa subsidiada " + comprobantePrestamoTasaSubsidiadaView.getNroComprobante() + PDF_EXTENSION);
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
     * Obtener parametros.
     *
     * @param comprobantePrestamoTasaSubsidiadaView
     * @return the hash map
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private HashMap<String, Object> obtenerParametros(ComprobantePrestamoTasaSubsidiadaView comprobantePrestamoTasaSubsidiadaView) throws IOException {
        HashMap<String, Object> parametros = comprobantePrestamoTasaSubsidiadaView.obtenerParametros();
        parametros.put("logoFooter", logoPie.getFile().getPath());	
        parametros.put(SUBREPORT_DIR, fileJasperCompPrestamoTasaSub.getFile().getParent() + File.separator);
        
        return parametros;
    }

	
}
