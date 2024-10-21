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
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView;
import ar.com.santanderrio.obp.servicios.prestamos.dao.ReportePrestamoDAO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReportePrestamoDAOImpl.
 */
@Component
public class ReportePrestamoDAOImpl implements ReportePrestamoDAO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportePrestamoDAOImpl.class);

	/** The inicio armado comprobantes. */
	private final String inicioArmadoComprobantes = "Iniciando armado de comprobante...";

	/** The extension PDF. */
	private final String extensionPDF = ".pdf";

	/** The logo top. */
	private final String logoTop = "LOGO_TOP";

	/** The logo cabecera. */
	private final String logoCabecera = "LOGO_CABECERA";

	/** The logo pie. */
	private final String logoPie = "LOGO_PIE";

	/** The logo prestamo. */
	private final String logoPrestamo = "LOGO_PRESTAMO";

	/** The imagen logo hipotecario top. */
	@Value("classpath:/report/comprobantes/super-prestamo-hipotecario.png")
	private Resource imagenLogoHipotecario;

	/** The imagen logo top. */
	@Value("classpath:/report/comprobantes/super-prestamo-personal.png")
	private Resource imagenLogoPersonal;

	/** The imagen logo top. */
	@Value("classpath:/report/comprobantes/super-prestamo-prendario.png")
	private Resource imagenLogoPrendario;
	
	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource imagenLogoCabecera;

	/** The logo pie. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource imagenLogoPie;

	/** The imagen logo top. */
	@Value("classpath:/report/comprobantes/logo-santander-gris-comp.png")
	private Resource imagenLogoTop;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.prestamos.dao.ReportePrestamoDAO#obtenerReportePrestamoPDF(ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView)
	 */
	@Override
	public Reporte obtenerReportePrestamoPDF(ReportComprobanteView detalleView) throws DAOException {
		try {
			LOGGER.info(inicioArmadoComprobantes);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(detalleView.obtenerJasper());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			HashMap<String, Object> parameters = obtenerParametros(detalleView, false);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre(detalleView.getTituloComprobante() + extensionPDF);
			return reporte;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.prestamos.dao.ReportePrestamoDAO#obtenerReportePrestamoPDF(ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView, boolean)
	 */
	@Override
	public Reporte obtenerReportePrestamoPDF(ReportComprobanteView detalleView, boolean isUva) throws DAOException {
		try {
			LOGGER.info(inicioArmadoComprobantes);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(detalleView.obtenerJasper());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			HashMap<String, Object> parameters = obtenerParametros(detalleView, isUva);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre(detalleView.getTituloComprobante() + extensionPDF);
			return reporte;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}
	
	/**
	 * Obtener parametros.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @param isUva
	 *            the is uva
	 * @return the hash map
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private HashMap<String, Object> obtenerParametros(ReportComprobanteView detalleView, boolean isUva) throws IOException {
		HashMap<String, Object> parametros = detalleView.obtenerParametrosPDF(isUva);
		obtenerParametrosConLogosYTitulo(detalleView.getTipoPrestamo(), parametros);
		return parametros;
	}

	/**
	 * Obtener parametros con logos Y titulo.
	 *
	 * @param tipoPrestamo
	 *            the tipo prestamo
	 * @param parametros
	 *            the parametros
	 * @return the hash map
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void obtenerParametrosConLogosYTitulo(String tipoPrestamo, HashMap<String, Object> parametros)
			throws IOException {
		if ("PERSONAL".equals(tipoPrestamo) || "UVA".equals(tipoPrestamo)) {
			parametros.put(logoPrestamo, imagenLogoPersonal.getFile().getPath());
		} else if ("HIPOTECARIOS".equals(tipoPrestamo)||"HIPOTECARIO".equals(tipoPrestamo)) {
			parametros.put(logoPrestamo, imagenLogoHipotecario.getFile().getPath());
		} else {
			parametros.put(logoPrestamo, imagenLogoPrendario.getFile().getPath());
		}
		parametros.put(logoTop, imagenLogoTop.getFile().getPath());

		parametros.put(logoCabecera, imagenLogoCabecera.getFile().getPath());
		parametros.put(logoPie, imagenLogoPie.getFile().getPath());
	}
	
}
