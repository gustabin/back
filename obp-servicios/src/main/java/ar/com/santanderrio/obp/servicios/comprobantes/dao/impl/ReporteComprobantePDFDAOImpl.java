/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.impl;

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
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ReporteComprobantePDFDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReporteComprobantePDFDAOImpl.
 */
@Component
public class ReporteComprobantePDFDAOImpl implements ReporteComprobantePDFDAO {

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
    private final String inicioArmadoComprobantes = "Iniciando armado de comprobante...";

    /** The logo top. */
    private final String logoTop = "LOGO_TOP";

    /** The logo cabecera. */
    private final String logoCabecera = "LOGO_CABECERA";

    /** The titulo comprobante. */
    private final String tituloComprobante = "TITULO_COMPROBANTE";

    /** The logo pie. */
    private final String logoPie = "LOGO_PIE";

    /** The extension PDF. */
    private final String extensionPDF = ".pdf";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
     * ReporteComprobantePDFDAO#obtenerReporteComprobantePDF(ar.com.santanderrio
     * .obp.servicios.comprobantes.web.view.DetalleComprobanteView,
     * ar.com.santanderrio.obp.servicios.comprobantes.dto.
     * TipoOperacionComprobanteEnum, java.lang.String)
     */
    @Override
    public Reporte obtenerReporteComprobantePDF(DetalleComprobanteView detalleView) throws DAOException {
        try {
            LOGGER.info(inicioArmadoComprobantes);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(detalleView.obtenerJasper());

            jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

            HashMap<String, Object> parameters = obtenerParametros(detalleView);

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
     * @return the hash map
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private HashMap<String, Object> obtenerParametros(DetalleComprobanteView detalleView) throws IOException {
        HashMap<String, Object> parametros = detalleView.obtenerParametrosPDF();
        obtenerParametrosConLogosYTitulo(detalleView.getTituloComprobante(), parametros);
        return parametros;
    }

    /**
     * Obtener parametros con logos Y titulo.
     *
     * @param titulo
     *            the titulo
     * @param parametros
     *            the parametros
     * @return the hash map
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void obtenerParametrosConLogosYTitulo(String titulo, HashMap<String, Object> parametros)
            throws IOException {
        parametros.put(logoTop, imagenLogoTop.getFile().getPath());
        parametros.put(logoCabecera, imagenLogoCabecera.getFile().getPath());
        parametros.put(tituloComprobante, titulo);
        parametros.put(logoPie, imagenLogoPie.getFile().getPath());
    }

}
