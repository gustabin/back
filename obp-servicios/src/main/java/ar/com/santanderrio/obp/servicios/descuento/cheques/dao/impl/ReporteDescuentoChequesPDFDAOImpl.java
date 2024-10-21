/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ReporteDescuentoChequesPDFDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReporteDescuentoChequesPDFDAOImpl.
 */
@Component
public class ReporteDescuentoChequesPDFDAOImpl implements ReporteDescuentoChequesPDFDAO {
	
	/** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReporteComprobantePDFDAOImpl.class);

    /** The imagen logo top. */
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
    private final String LOGO_PIE = "LOGO_PIE";

    /** The extension PDF. */
    private final String EXTENSION_PDF = ".pdf";
    
    /** The extension PDF. */
    private final String TITULO = "Comprobante de solicitud de descuento de cheques";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ReporteDescuentoChequesPDFDAO#obtenerOperacionPDF(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView)
	 */
	@Override
	public Reporte obtenerOperacionPDF(DetalleOperacionesPrecargadasView detalleIn) throws DAOException {
		try {
            LOGGER.info(INICIO_ARMADO_PDF);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(detalleIn.obtenerJasper());

            jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

            Map<String, Object> parameters = obtenerParametros(detalleIn);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

            byte[] byteArray = outStream.toByteArray();
            Reporte reporte = new Reporte();
            reporte.setTipoArchivo(TipoArchivoEnum.PDF);
            reporte.setBytes(byteArray);
            reporte.setNombre(TITULO + EXTENSION_PDF);
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
	 * @param view
	 *            the view
	 * @return the map
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private Map<String, Object> obtenerParametros(DetalleOperacionesPrecargadasView view) throws IOException {
        Map<String, Object> parametros = view.obtenerParametrosPDF();
        obtenerParametrosConLogosYTitulo(TITULO, parametros);
        return parametros;
    }
	
	/**
	 * Obtener parametros con logos Y titulo.
	 *
	 * @param titulo
	 *            the titulo
	 * @param parametros
	 *            the parametros
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void obtenerParametrosConLogosYTitulo(String titulo, Map<String, Object> parametros)
			throws IOException {
		parametros.put(LOGO_TOP, imagenLogoTop.getFile().getPath());
		parametros.put(LOGO_CABECERA, imagenLogoCabecera.getFile().getPath());
		parametros.put(TITULO_COMPROBANTE, titulo);
		parametros.put(LOGO_PIE, imagenLogoPie.getFile().getPath());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ReporteDescuentoChequesPDFDAO#obtenerOperacionPDF(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut)
	 */
	@Override
	public Reporte obtenerOperacionPDF(AltaChequesViewOut chequesView) throws DAOException {
		try {
            LOGGER.info(INICIO_ARMADO_PDF);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(chequesView.obtenerJasper());

            jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

            Map<String, Object> parameters = obtenerParametros(chequesView);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

            byte[] byteArray = outStream.toByteArray();
            Reporte reporte = new Reporte();
            reporte.setTipoArchivo(TipoArchivoEnum.PDF);
            reporte.setBytes(byteArray);
            reporte.setNombre(TITULO + EXTENSION_PDF);
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
	 * @param chequesView
	 *            the cheques view
	 * @return the map
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private Map<String, Object> obtenerParametros(AltaChequesViewOut chequesView) throws IOException {
		Map<String, Object> parametros = chequesView.obtenerParametrosPDF();
        obtenerParametrosConLogosYTitulo(TITULO, parametros);
        return parametros;
	}

}
