/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.ComprobantePrestamoReporte;
import ar.com.santanderrio.obp.servicios.pagos.dao.ReporteComprobanteDAO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReporteComprobanteDAOImpl.
 */
@Component
public class ReporteComprobanteDAOImpl implements ReporteComprobanteDAO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteComprobanteDAOImpl.class);

	/** The archivo comprobante jasper. */
	@Value("classpath:/report/prestamos/comprobante/comprobante.jasper")
	private Resource archivoComprobanteJasper;

	/** The archivo detalle jasper. */
	@Value("classpath:/report/prestamos/detalle/comprobante.jasper")
	private Resource archivoDetalleJasper;

	/** The legales. */
	private static String legales = "Para préstamos personales contratados después del 26/12/2016,"
			+ " la cobertura incluye invalidez total y permanente.";

	/** The texto footer. */
	private static String textoFooter = "Conserve este ticket como comprobante S.E.U.O";

	/** The cabecera logo. */
	@Value("classpath:/report/comprobantes/cabecera_comprobante.png")
	private Resource cabeceraLogo;

	/** The encabezado logo. */
	@Value("classpath:/report/comprobantes/logoEncbezado.png")
	private Resource encabezadoLogo;

	/** The footer logo. */
	@Value("classpath:/report/comprobantes/cierre de comprobante.png")
	private Resource footerLogo;

	/** The logo super prestamo personal. */
	@Value("classpath:/report/comprobantes/super-prestamo-personal.png")
	private Resource logoSuperPrestamoPersonal;

	/** The logo super prestamo. */
	@Value("classpath:/report/comprobantes/super-prestamo.png")
	private Resource logoSuperPrestamo;

	/** The logo super prestamo hipotecario. */
	@Value("classpath:/report/comprobantes/super-prestamo-hipotecario.png")
	private Resource logoSuperPrestamoHipotecario;

	/** The logo super prestamo prendario. */
	@Value("classpath:/report/comprobantes/super-prestamo-prendario.png")
	private Resource logoSuperPrestamoPrendario;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.ReporteComprobanteDAO#
	 * buildReportComprobante(ar.com.santanderrio.obp.servicios.pagos.bo.
	 * ComprobantePrestamoReporte)
	 */
	public byte[] buildReportComprobante(ComprobantePrestamoReporte comprobantePrestamoReporte) throws DAOException {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			// se carga el reporte
			jasperReport = (JasperReport) JRLoader.loadObject(archivoComprobanteJasper.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			// se procesa el archivo jasper

			HashMap<String, Object> parameters = new HashMap<String, Object>();

			buildContentReport(comprobantePrestamoReporte, parameters);
			parameters.put("SUBREPORT_DIR", archivoComprobanteJasper.getFile().getParent() + File.separator);
			parameters.put("nroComprobante", comprobantePrestamoReporte.getNroComprobante());
			parameters.put("logoFooter", footerLogo.getFile().getPath());
			parameters.put("textoFooter", textoFooter);
			;

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();

			return byteArray;
		} catch (JRException ex) {
			throw new DAOException(ex);
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.ReporteComprobanteDAO#
	 * buildReportComprobanteDetalle(ar.com.santanderrio.obp.servicios.pagos.bo.
	 * ComprobantePrestamoReporte)
	 */
	public byte[] buildReportComprobanteDetalle(ComprobantePrestamoReporte comprobantePrestamoReporte)
			throws DAOException {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			// se carga el reporte
			jasperReport = (JasperReport) JRLoader.loadObject(archivoDetalleJasper.getInputStream());
			// se procesa el archivo jasper

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("aliasCuenta", comprobantePrestamoReporte.getAliasCuentaDebito());

			buildContentReport(comprobantePrestamoReporte, parameters);
			parameters.put("SUBREPORT_DIR", archivoDetalleJasper.getFile().getParent() + File.separator);

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();

			return byteArray;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Builds the logo prestamo.
	 *
	 * @param tipoPrestamo
	 *            the tipo prestamo
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private String buildLogoPrestamo(TipoPrestamoEnum tipoPrestamo) throws IOException {
		try {
			if (TipoPrestamoEnum.PERSONAL.equals(tipoPrestamo)) {
				return logoSuperPrestamoPersonal.getFile().getPath();
			} else if (TipoPrestamoEnum.HIPOTECARIOS.equals(tipoPrestamo)) {
				return logoSuperPrestamoHipotecario.getFile().getPath();
			} else if (TipoPrestamoEnum.PRENDARIO.equals(tipoPrestamo)) {
				return logoSuperPrestamoPrendario.getFile().getPath();
			}
			return logoSuperPrestamo.getFile().getPath();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Builds the content report.
	 *
	 * @param comprobantePrestamoReporte
	 *            the comprobante prestamo reporte
	 * @param parameters
	 *            the parameters
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void buildContentReport(ComprobantePrestamoReporte comprobantePrestamoReporte,
			HashMap<String, Object> parameters) throws IOException {
		parameters.put("logo", cabeceraLogo.getFile().getPath());
		parameters.put("logoEncabezado", encabezadoLogo.getFile().getPath());
		parameters.put("logoPrestamo", buildLogoPrestamo(comprobantePrestamoReporte.getTipoPrestamo()));
		parameters.put("descripcionPrestamo", comprobantePrestamoReporte.getDescripcionPrestamo());
		parameters.put("importeCuotaPrincipal", comprobantePrestamoReporte.getImporteCuotaPrincipal());
		parameters.put("valorDescripcionPrestamo", comprobantePrestamoReporte.getValorDescripcionPrestamo());
		parameters.put("titular", comprobantePrestamoReporte.getTitular());
		parameters.put("cuil", comprobantePrestamoReporte.getCuil());
		parameters.put("condicionIVA", comprobantePrestamoReporte.getCondicionIVA());
		parameters.put("tipoCuenta", comprobantePrestamoReporte.getTipoCuenta());
		parameters.put("cuentaDebito", comprobantePrestamoReporte.getCuentaDebito());
		parameters.put("cuotaPrestamo", comprobantePrestamoReporte.getCuotaPrestamo());
		parameters.put("plazoPrestamo", comprobantePrestamoReporte.getPlazoPrestamo());
		parameters.put("importeCuota", comprobantePrestamoReporte.getImporteCuota());
		parameters.put("importes", comprobantePrestamoReporte.getImportes());
		parameters.put("tasas", comprobantePrestamoReporte.getTasas());
		parameters.put("fechaVencimiento", comprobantePrestamoReporte.getFechaVencimiento());
		parameters.put("legales", legales);
	}

}
