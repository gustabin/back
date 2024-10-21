package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DatosComprobanteDevolucionDA;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class GenerarComprobantePDFDevolucionDebitoAutomaticoTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class GenerarComprobantePDFDevolucionDebitoAutomaticoTest {

	/** The devolucion debito automatico DAO. */
	@InjectMocks
	private DevolucionDebitoAutomaticoDAO devolucionDebitoAutomaticoDAO = new DevolucionDebitoAutomaticoDAOImpl();
	
	/** The respuesta factory. */
	@InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
    /** The datos comprobante devolucion DA. */
    @Mock
    private DatosComprobanteDevolucionDA datosComprobanteDevolucionDA;
    
    /** The app context. */
    private ApplicationContext appContext = new ClassPathXmlApplicationContext();
	
    /**
     * Inits the.
     *
     * @throws IllegalAccessException the illegal access exception
     */
    @Before
    public void init() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        Thread.interrupted();
    }
    
    /**
     * Test generar comprobante PDF extraccion efectivo.
     *
     * @throws IllegalAccessException the illegal access exception
     * @throws DAOException the DAO exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testGenerarComprobantePDFExtraccionEfectivo() throws IllegalAccessException, DAOException, IOException {
        FieldUtils.writeField(devolucionDebitoAutomaticoDAO, "logoCabecera",
                appContext.getResource("classpath:/report/comprobantes/cabecera_comprobante.png"), true);
        FieldUtils.writeField(devolucionDebitoAutomaticoDAO, "logoPie",
                appContext.getResource("classpath:/report/comprobantes/cierre de comprobante.png"), true);
        FieldUtils.writeField(devolucionDebitoAutomaticoDAO, "archivoJasperDevolucionDebitoAutomatico",
                appContext.getResource("classpath:/report/devolucionDebitoAutomatico/comprobanteDevolucionDebitoAutomatico.jasper"), true);
        Mockito.when(datosComprobanteDevolucionDA.obtenerJasper()).thenReturn(
                appContext.getResource("classpath:/report/devolucionDebitoAutomatico/comprobanteDevolucionDebitoAutomatico.jasper").getFile().getPath());
        Mockito.when(datosComprobanteDevolucionDA.obtenerParametrosPDF()).thenReturn(obtenerParametrosPDF());
        Reporte reporte = devolucionDebitoAutomaticoDAO.generarComprobantePDF(datosComprobanteDevolucionDA);
        FileUtils.writeByteArrayToFile(new File("C:/tools/comprobanteDevolucionDebitoAutomatico.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
    }
    
	/**
	 * Obtener parametros PDF.
	 *
	 * @return the hash map
	 */
	private HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("EMPRESA", "Fibertel");
		parametros.put("NRO_IDENTIFICACION", "10000001");
		parametros.put("NRO_CUENTA_DEBITO", "000-123456/7");
		parametros.put("TIPO_CUENTA_DEBITO", "Cuenta única");
		parametros.put("MONTO", "$ 1.500,00");
		parametros.put("FECHA_VENCIMIENTO", "15/08/2020");
		parametros.put("FECHA_PAGO", "15/08/2020");
		parametros.put("FECHA_DEVOLUCION", "26/08/2020");
		parametros.put("NRO_COMPROBANTE", "128");
		parametros.put("FECHA_HORA_COMPROBANTE", "26/08/2020 - 17:40 hs");
		return parametros;
	}
	
}
