/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.prestamos.dao.ReportePrestamosPreaprobadoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoPreaprobadoMonoproductoInOutView;
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
public class ReportePrestamosPreaprobadoDAOImpl implements ReportePrestamosPreaprobadoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportePrestamosPreaprobadoDAOImpl.class);
	
	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoPie;
	
	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource imagenLogoCabecera;
	
	/** The imagen logo top. */
	@Value("classpath:/report/comprobantes/super-prestamo-personal.png")
	private Resource imagenLogoPersonal;
	
	private static final String PDF_EXTENSION = ".pdf";
    
	
    @Override
    public Reporte generarComprobantePrestamoPreaprobado(PrestamoPreaprobadoMonoproductoInOutView datos)
    		throws DAOException {
    	
    	Reporte reporte = new Reporte();
		try {
			// se carga el reporte
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(datos.obtenerJasper());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, obtenerParametros(datos), new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre("Comprobante de solicitud de prestamo preaprobado monoproducto " + datos.getNroDeComprobante() + PDF_EXTENSION);
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
    private HashMap<String, Object> obtenerParametros(PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobadoView) throws IOException {
        HashMap<String, Object> parametros = prestamoPreaprobadoView.obtenerParametros();
        parametros.put("logoFooter", logoPie.getFile().getPath());
        parametros.put("LOGO_CABECERA", imagenLogoCabecera.getFile().getPath());
        parametros.put("LOGO_PRESTAMO", imagenLogoPersonal.getFile().getPath());
        
        return parametros;
    }

	
}
