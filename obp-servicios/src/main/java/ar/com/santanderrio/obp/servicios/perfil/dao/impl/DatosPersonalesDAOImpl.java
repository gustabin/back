package ar.com.santanderrio.obp.servicios.perfil.dao.impl;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CelularResponse;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.echeq.common.TiposGrilla;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.perfil.dao.DatosPersonalesDAO;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosPersonalesPDF;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionSolicitudesHomeManagerImpl.LOGGER;

@Component
public class DatosPersonalesDAOImpl implements DatosPersonalesDAO {

    @Value("classpath:/report/perfil/datosPersonalesPDF.jasper")
    private Resource fileJasperDatosPersonales;

    @Value("classpath:/report/comprobantes/logo-santander-gris-comp.png")
    private Resource logoTop;

    @Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
    private Resource logoCabecera;

    @Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
    private Resource logoCierre;

    private static final String LOGO_TOP = "LOGO_TOP";

    private static final String LOGO_CABECERA = "LOGO_CABECERA";

    private static final String NOMBRE_RESPONSABLE_DATOS = "NOMBRE_RESPONSABLE_DATOS";

    private static final String CUIT_RESPONSABLE_DATOS = "CUIT_RESPONSABLE_DATOS";

    private static final String DOMICILIO_RESPONSABLE_DATOS = "DOMICILIO_RESPONSABLE_DATOS";

    private static final String NOMBRE_SOLICITANTE = "NOMBRE_SOLICITANTE";

    private static final String APELLIDO_SOLICITANTE = "APELLIDO_SOLICITANTE";

    private static final String DOCUMENTO_SOLICITANTE = "DOCUMENTO_SOLICITANTE";

    private static final String DOMICILIO_PRINCIPAL = "DOMICILIO_PRINCIPAL";

    private static final String DOMICILIO_LABORAL = "DOMICILIO_LABORAL";

    private static final String NUMERO_CELULAR_PRIMARIO = "NUMERO_CELULAR_PRIMARIO";

    private static final String EMPRESA_CELULAR_PRIMARIO = "EMPRESA_CELULAR_PRIMARIO";

    private static final String NUMERO_CELULAR_SECUNDARIO = "NUMERO_CELULAR_SECUNDARIO";

    private static final String EMPRESA_CELULAR_SECUNDARIO = "EMPRESA_CELULAR_SECUNDARIO";

    private static final String EMAIL_PRIMARIO = "EMAIL_PRIMARIO";

    private static final String EMAIL_SECUNDARIO = "EMAIL_SECUNDARIO";

    private static final String LEGALES = "LEGALES";

    private static final String FECHA = "FECHA";

    private static final String LOGO_CIERRE = "LOGO_CIERRE";

    @Override
    public Reporte descargarDatosPersonalesPDF(DatosPersonalesPDF datosPersonalesPDF) throws IOException, JRException {
        String nombreArchivo = "Datos Personales.pdf";
        JasperPrint jasperPrint = null;
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasperDatosPersonales.getInputStream());
        Map<String, Object> parameters = new HashMap<String, Object>();
        try {
            parameters.put(LOGO_TOP, logoTop.getFile().getPath());
            parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
            parameters.put(NOMBRE_RESPONSABLE_DATOS, datosPersonalesPDF.getResponsableDatosNombre());
            parameters.put(CUIT_RESPONSABLE_DATOS, datosPersonalesPDF.getResponsableDatosCuit());
            parameters.put(DOMICILIO_RESPONSABLE_DATOS, datosPersonalesPDF.getResponsableDatosDomicilio());
            parameters.put(NOMBRE_SOLICITANTE, datosPersonalesPDF.getNombreSolicitante());
            parameters.put(APELLIDO_SOLICITANTE, datosPersonalesPDF.getApellidoSolictante());
            parameters.put(DOCUMENTO_SOLICITANTE, datosPersonalesPDF.getDocumentoSolicitante());
            parameters.put(DOMICILIO_PRINCIPAL, datosPersonalesPDF.getDomcilioPrincipal());
            parameters.put(DOMICILIO_LABORAL, datosPersonalesPDF.getDomicilioLaboral());
            parameters.put(NUMERO_CELULAR_PRIMARIO, datosPersonalesPDF.getNumeroCelularPrimario());
            parameters.put(EMPRESA_CELULAR_PRIMARIO, datosPersonalesPDF.getEmpresaCelularPrimario());
            parameters.put(NUMERO_CELULAR_SECUNDARIO, datosPersonalesPDF.getNumeroCelularSecundario());
            parameters.put(EMPRESA_CELULAR_SECUNDARIO, datosPersonalesPDF.getEmpresaCelularSecundario());
            parameters.put(EMAIL_PRIMARIO, datosPersonalesPDF.getEmailPrimario());
            parameters.put(EMAIL_SECUNDARIO, datosPersonalesPDF.getEmailSecundario());
            parameters.put(LEGALES, datosPersonalesPDF.getLegales());
            parameters.put(FECHA, datosPersonalesPDF.getFechaComprobante());
            parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

            jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
            // se procesa el archivo jasper
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            byte[] byteArray = outStream.toByteArray();
            Reporte reporte = new Reporte();
            reporte.setTipoArchivo(TipoArchivoEnum.PDF);
            reporte.setBytes(byteArray);
            reporte.setNombre(nombreArchivo);
            return reporte;
        } catch (JRException ex) {
            LOGGER.error("Error al generar PDF 1", ex);
            throw new ISBANRuntimeException(ex);
        } catch (IOException e) {
            LOGGER.error("Error al generar PDF 2", e);
            throw new ISBANRuntimeException(e);
        }
    }

}
