package ar.com.santanderrio.obp.servicios.inversiones.maps.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.ReporteComprobantePDFDAOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemLegalComprobante;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReporteCBUCuentaDAOImpl.
 */
@Component
public class ReportesMapsDAOImpl implements ReportesMapsDAO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteComprobantePDFDAOImpl.class);

	@Value("classpath:/report/comprobantes/logo-santander-gris-comp.png")
	private Resource imagenLogoTop;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource imagenLogoCabecera;

	/** The logo pie. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource imagenLogoPie;

	/** The inicio armado comprobantes. */
	private static final String INICIO_ARMADO_PDF = "Armando detalle de operacion...";

	/** The logo top. */
	private static final String LOGO_TOP = "LOGO_TOP";

	/** The logo cabecera. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The titulo comprobante. */
	private static final String TITULO_COMPROBANTE = "TITULO_COMPROBANTE";

	/** The logo pie. */
	private String LOGO_PIE = "LOGO_PIE";

	/** The extension PDF. */
	private static final String EXTENSION_PDF = ".pdf";

	private static final String FECHA_ACTUAL = "FECHA_ACTUAL";

	/** The extension PDF. */
	private static final String TITULO_COMPROBANTE_ALTA = "Comprobante alta adhesion";

	/** The extension PDF. */
	private static final String TITULO_COMPROBANTE_BAJA = "Comprobante baja adhesion";

	private static final String BAJA_ADHESION = "BAJA";

	private static final String ALTA_ADHESION = "ALTA";

	/** The file jasper. */
	@Value("classpath:/report/maps/alta-adhesion-comprobante.jasper")
	private Resource fileJasperAlta;
	
	/** The file jasper. */
	@Value("classpath:/report/maps/baja-adhesion-comprobante.jasper")
	private Resource fileJasperBaja;

	@Override
	public Reporte descargaComprobanteAltaAdhesion(FormularioControl formulario) throws DAOException {
		try {
			LOGGER.info(INICIO_ARMADO_PDF);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasperAlta.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			Map<String, Object> parameters = obtenerParametros(formulario, ALTA_ADHESION);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre(TITULO_COMPROBANTE_ALTA + EXTENSION_PDF);
			return reporte;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	@Override
	public Reporte descargaComprobanteBajaAdhesion(FormularioControl formulario) throws DAOException {
		try {
			LOGGER.info(INICIO_ARMADO_PDF);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasperBaja.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			Map<String, Object> parameters = obtenerParametros(formulario, BAJA_ADHESION);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre(TITULO_COMPROBANTE_BAJA + EXTENSION_PDF);
			return reporte;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	private Map<String, Object> obtenerParametros(FormularioControl formulario, String flujo) throws IOException {

		List<ItemComprobante> controles = new ArrayList<ItemComprobante>();
		List<String> legales = new ArrayList<String>();
		for (ControlMaps control : formulario.getItems()) {
			if (ControlMaps.TIPO_LEGAL.equals(control.tipoComprobante())) {
				legales.addAll(((ItemLegalComprobante) control.imprimirComprobante()).getLegales());
			}
			if (ControlMaps.TIPO_CONTROL.equals(control.tipoComprobante())) {
				controles.add(control.imprimirComprobante());
			}
		}
		ItemComprobante numeroComprobante = new ItemComprobante("Comprobante");
		numeroComprobante.setValor(formulario.getComprobante());
		controles.add(numeroComprobante);

		Map<String, Object> parametros = new HashMap<String, Object>();
		if (BAJA_ADHESION.equals(flujo)) {
			parametros = obtenerParametrosConLogosYTitulo(formulario.getTitulo(), formulario.getFechaComprobante());
		} else {
			parametros = obtenerParametrosConLogosYTitulo("Comprobante de " + formulario.getTitulo(),
					formulario.getFechaComprobante());
			parametros.put("SUBTITULO_COMPROBANTE", formulario.getTitulo());
		}

		parametros.put("CONTROLES", controles);
		parametros.put("LEGALES", legales);
		return parametros;
	}

	private Map<String, Object> obtenerParametrosConLogosYTitulo(String titulo, String fecha) throws IOException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(LOGO_CABECERA, imagenLogoCabecera.getFile().getPath());
		parametros.put(LOGO_TOP, imagenLogoTop.getFile().getPath());
		parametros.put(TITULO_COMPROBANTE, titulo);
		parametros.put(FECHA_ACTUAL, fecha);
		parametros.put(LOGO_PIE, imagenLogoPie.getFile().getPath());
		return parametros;
	}

}

// Análisis del archivo ReportesMapsDAOImpl.java

// El archivo ReportesMapsDAOImpl.java es una implementación de la interfaz ReportesMapsDAO que se encarga de generar reportes en formato PDF para la adhesión y baja de adhesión.

// Observaciones

// La clase utiliza la biblioteca JasperReports para generar los reportes en formato PDF.
// La clase utiliza la anotación @Value para inyectar los valores de las propiedades imagenLogoTop, imagenLogoCabecera y imagenLogoPie que se utilizan para agregar logos al reporte.
// La clase utiliza la anotación @Value para inyectar el valor de la propiedad fileJasperAlta y fileJasperBaja que se utilizan para cargar los archivos de diseño de los reportes.
// La clase define dos métodos: descargaComprobanteAltaAdhesion y descargaComprobanteBajaAdhesion que se encargan de generar los reportes en formato PDF para la adhesión y baja de adhesión, respectivamente.
// La clase utiliza la clase Reporte para devolver el reporte generado en formato PDF.
// Preguntas para avanzar

// ¿Cuál es el propósito de la clase Reporte y cómo se utiliza en la implementación de ReportesMapsDAOImpl?
// ¿Cómo se manejan las excepciones en la implementación de ReportesMapsDAOImpl?
// ¿Qué tipo de reportes se generan en la implementación de ReportesMapsDAOImpl y cómo se personalizan?