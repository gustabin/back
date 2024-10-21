/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.dao.impl;

import java.io.ByteArrayOutputStream;
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
import ar.com.santanderrio.obp.servicios.login.dao.ReporteTyCDAO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReporteTyCDAOImpl.
 */
@Component("reporteTyCDAO")
public class ReporteTyCDAOImpl implements ReporteTyCDAO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteTyCDAOImpl.class);

	/** The file jasper. */
	@Value("classpath:/report/mya/terminosycondiciones/reportMyaTyC.jasper")
	private Resource fileJasper;

	/** The logo. */
	@Value("classpath:/report/comprobantes/logo_cabecera_tyc.png")
	private Resource logo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.login.dao.ReporteTyCDAO#
	 * obtenerReporteTyCMya(java.lang.String)
	 */
	@Override
	public Reporte obtenerReporteTyCMya(String texto) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			// se carga el reporte
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasper.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			// se procesa el archivo jasper

			HashMap<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("logo_cabecera", logo.getFile().getPath());
			parameters.put("cuerpo_tyc", texto);

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("terminos_y_condiciones.pdf");
			return reporte;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
	}

}
