package ar.com.santanderrio.obp.servicios.prestamos.dao;

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
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.PrestamoDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteCancelacionTotalPrestamoView;

@RunWith(MockitoJUnitRunner.class)
public class GenerarComprobantePDFCancelacionPrestamoTest {
	
	@InjectMocks
	private PrestamoDAO prestamoDAO = new PrestamoDAOImpl();
	
	@InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	@Mock
	private ComprobanteCancelacionTotalPrestamoView comprobanteCancelacionTotalPrestamoView;
	
    private ApplicationContext appContext = new ClassPathXmlApplicationContext();
	
    @Before
    public void init() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        Thread.interrupted();
    }
    
    
    @Test
    public void testGenerarComprobantePDFExtraccionEfectivo() throws IllegalAccessException, DAOException, IOException {
        FieldUtils.writeField(prestamoDAO, "logoCabecera",
                appContext.getResource("classpath:/report/comprobantes/cabecera_comprobante.png"), true);
        FieldUtils.writeField(prestamoDAO, "logoPie",
                appContext.getResource("classpath:/report/comprobantes/cierre de comprobante.png"), true);
        FieldUtils.writeField(prestamoDAO, "archivoJasperCancelacionTotalPrestamo",
                appContext.getResource("classpath:/report/cancelacionTotalPrestamo/comprobanteCancelacionTotalPrestamo.jasper"), true);
        Mockito.when(comprobanteCancelacionTotalPrestamoView.getJasperFilePath()).thenReturn(
                appContext.getResource("classpath:/report/cancelacionTotalPrestamo/comprobanteCancelacionTotalPrestamo.jasper").getFile().getPath());
        Mockito.when(comprobanteCancelacionTotalPrestamoView.mapPDFParameters()).thenReturn(obtenerParametrosPDF());
        Reporte reporte = prestamoDAO.generarComprobantePDF(comprobanteCancelacionTotalPrestamoView);
        FileUtils.writeByteArrayToFile(new File("C:/tools/comprobanteCancelacionTotalPrestamo.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
    }
    
	private HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("MONTO", "$ 10.000,00");
		parametros.put("NUMERO_PRESTAMO", "000-123456/7");
		parametros.put("CAPITAL", "Cuenta única");
		parametros.put("NRO_CUENTA_DEBITO", "000-123456/7");
		parametros.put("TIPO_CUENTA_DEBITO", "Cuenta única");
		parametros.put("INTERESES", "$ 2.500,00");
		parametros.put("IVA", "$ 1.000,23");
		parametros.put("INGRESOS_BRUTOS", "$ 843,32");
		parametros.put("OTROS_IMPUESTOS", "$ 32,12");
		parametros.put("COMISIONES", "$ 102,45");
		parametros.put("GASTOS", "$ 212,85");
		parametros.put("SEGURO", "$ 33,84");
		parametros.put("NRO_COMPROBANTE", "1281233");
		parametros.put("FECHA_HORA_COMPROBANTE", "13/10/2020 - 12:32 hs");

		return parametros;
	}
    
    
}
