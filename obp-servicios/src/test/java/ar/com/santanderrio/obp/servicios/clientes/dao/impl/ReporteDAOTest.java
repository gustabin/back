package ar.com.santanderrio.obp.servicios.clientes.dao.impl;

import java.io.IOException;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosComprobante;

/**
 * The Class ReporteCBUCuentaDAOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporteDAOTest {

    /** The reporte CBU cuenta DAO. */
    @InjectMocks
    private ReporteDAO reporteDAO = new ReporteDAOImpl();

    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();

    /**
     * Inits the.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Before
    public void init() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        Thread.interrupted();
    }

    /**
     * Test reporte.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void testReporteCambioClaveUsuario() throws IOException, IllegalAccessException {
    	DatosComprobante datos = new DatosComprobante();
        datos.setNroComprobante("123345556");
        datos.setTipoComprobante("Usuario y Clave");
        FieldUtils.writeField(reporteDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
                true);
        FieldUtils.writeField(reporteDAO, "logoPie", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"),
                true);
        FieldUtils.writeField(reporteDAO, "fileJasperCambioClaveUsuario",
                appContext.getResource("classpath:/report/perfil/comprobanteCambioClaveUsuario.jasper"), true);
        Reporte reporte = reporteDAO.descargarComprobante(datos);
        Assert.assertNotNull(reporte.getBytes());
//        FileUtils.writeByteArrayToFile(new File("C:/file11.pdf"),reporte.getBytes());
    }
    
    
    @Test
    public void testReporteCambioUsuario() throws IOException, IllegalAccessException {
    	DatosComprobante datos = new DatosComprobante();
        datos.setNroComprobante("123345556");
        datos.setTipoComprobante("Usuario");
        FieldUtils.writeField(reporteDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
                true);
        FieldUtils.writeField(reporteDAO, "logoPie", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"),
                true);
        FieldUtils.writeField(reporteDAO, "fileJasperCambioUsuario",
                appContext.getResource("classpath:/report/perfil/comprobanteCambioUsuario.jasper"), true);
        Reporte reporte = reporteDAO.descargarComprobante(datos);
        Assert.assertNotNull(reporte.getBytes());
//        FileUtils.writeByteArrayToFile(new File("C:/file12.pdf"),reporte.getBytes());
    }

}
