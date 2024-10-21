package ar.com.santanderrio.obp.servicios.debinrecurrente.dao;

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
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.DatosComprobanteDebinRecurrente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class GenerarComprobantePDFDebinRecurrenteTest {

	@InjectMocks
	private DebinRecurrenteDAO debinRecurrenteDAO = new DebinRecurrenteDAOImpl();
	
	@InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
    @Mock
    private DatosComprobanteDebinRecurrente datosComprobanteDebinRecurrente;
    
    private ApplicationContext appContext = new ClassPathXmlApplicationContext();
	
    @Before
    public void init() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        Thread.interrupted();
    }
    
    @Test
    public void testGenerarComprobantePDFExtraccionEfectivo() throws IllegalAccessException, DAOException, IOException {
        FieldUtils.writeField(debinRecurrenteDAO, "logoCabecera",
                appContext.getResource("classpath:/report/comprobantes/cabecera_comprobante.png"), true);
        FieldUtils.writeField(debinRecurrenteDAO, "logoPie",
                appContext.getResource("classpath:/report/comprobantes/cierre de comprobante.png"), true);
        FieldUtils.writeField(debinRecurrenteDAO, "archivoJasperDebinRecurrente",
                appContext.getResource("classpath:/report/debinRecurrente/comprobanteDebinRecurrente.jasper"), true);
        Mockito.when(datosComprobanteDebinRecurrente.obtenerJasper()).thenReturn(
                appContext.getResource("classpath:/report/debinRecurrente/comprobanteDebinRecurrente.jasper").getFile().getPath());
        Mockito.when(datosComprobanteDebinRecurrente.obtenerParametrosPDF()).thenReturn(obtenerParametrosPDF());
        Reporte reporte = debinRecurrenteDAO.generarComprobantePDF(datosComprobanteDebinRecurrente);
        FileUtils.writeByteArrayToFile(new File("C:/tools/DebinRecurrente.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
    }
    
	private HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("NOMBRE_EMPRESA", "BANCO SANTANDER RIO");
		parametros.put("SERVICIO", "BANCA DIGITAL ONLINE");
		parametros.put("CUIT_VENDEDOR", "4783878212");
		parametros.put("REFERENCIA", "1234567");
		parametros.put("NRO_CUENTA_DEBITO", "123-456789/0");
		parametros.put("TIPO_CUENTA_DEBITO", "Cuenta Única en Pesos");
		parametros.put("CUIT_COMPRADOR", "3737387892");
		parametros.put("CONCEPTO", "ALQ");
		parametros.put("DESCRIPCION", "Alquiler");
		parametros.put("FECHA_OPERACION", "05/08/2020");
		parametros.put("NRO_COMPROBANTE", "200805134100");
		parametros.put("NRO_RECURRENCIA", "12400");
		parametros.put("FECHA_HORA_COMPROBANTE", "05/08/2020 - 13:20 hs");

		return parametros;
	}
	
}
