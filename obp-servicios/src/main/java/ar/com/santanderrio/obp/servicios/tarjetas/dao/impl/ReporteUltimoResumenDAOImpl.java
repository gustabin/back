/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.ReporteUltimoResumenDAO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReporteUltimoResumenDAOImpl.
 */
@Repository
public class ReporteUltimoResumenDAOImpl implements ReporteUltimoResumenDAO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteUltimoResumenDAOImpl.class);

	// TODO Apuntar al archivo .jasper (cambiar el nombre del archivo
	// NOMBREARCHIVO)
	/** The file jasper. */
	@Value("classpath:/report/NOMBREARCHIVO.jasper")
	private Resource fileJasper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.dao.ReporteUltimoResumenDAO#
	 * exportarUltimoResumen(java.util.Map)
	 */
	@Override
	public Reporte exportarUltimoResumen(Map<String, Object> parameters) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasper.getInputStream());

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setBytes(byteArray);
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
