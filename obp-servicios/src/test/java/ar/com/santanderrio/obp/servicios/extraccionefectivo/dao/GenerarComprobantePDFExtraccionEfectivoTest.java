package ar.com.santanderrio.obp.servicios.extraccionefectivo.dao;

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
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosComprobanteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class GenerarComprobantePDFExtraccionEfectivoTest {

	@InjectMocks
	private ExtraccionEfectivoDAO extraccionEfectivoDAO = new ExtraccionEfectivoDAOImpl();
	
	@InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
    @Mock
    private DatosComprobanteExtraccionEfectivoView datosComprobanteExtraccionEfectivoView;
    
    private ApplicationContext appContext = new ClassPathXmlApplicationContext();
	
    @Before
    public void init() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        Thread.interrupted();
    }
    
    @Test
    public void testGenerarComprobantePDFExtraccionEfectivo() throws IllegalAccessException, DAOException, IOException {
        FieldUtils.writeField(extraccionEfectivoDAO, "logoCabecera",
                appContext.getResource("classpath:/report/comprobantes/cabecera_comprobante.png"), true);
        FieldUtils.writeField(extraccionEfectivoDAO, "logoPie",
                appContext.getResource("classpath:/report/comprobantes/cierre de comprobante.png"), true);
        FieldUtils.writeField(extraccionEfectivoDAO, "archivoJasperExtraccionEfectivo",
                appContext.getResource("classpath:/report/extraccionEfectivo/comprobanteExtraccionEfectivo.jasper"), true);
        Mockito.when(datosComprobanteExtraccionEfectivoView.obtenerJasper()).thenReturn(
                appContext.getResource("classpath:/report/extraccionEfectivo/comprobanteExtraccionEfectivo.jasper").getFile().getPath());
        Mockito.when(datosComprobanteExtraccionEfectivoView.obtenerParametrosPDF()).thenReturn(obtenerParametrosPDF());
        Reporte reporte = extraccionEfectivoDAO.generarComprobantePDF(datosComprobanteExtraccionEfectivoView);
        FileUtils.writeByteArrayToFile(new File("C:/tools/ExtraccionEfectivo.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
    }
    
	private HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("MONTO", "$ 100,00");
		parametros.put("NRO_CUENTA_ORIGEN", "000-123456/7");
		parametros.put("TIPO_CUENTA_ORIGEN", "Cuenta única");
		parametros.put("NOMBRE_DESTINATARIO", "GUY SOFOCLES CAPARROS MELENDEZ CARRANZA GUIDO Y MRA");
		parametros.put("DNI_DESTINATARIO", "DNI 33.119.633");
		parametros.put("EMAIL_DESTINATARIO", "pepetarjota@gmail.com");
		parametros.put("FECHA_VENCIMIENTO", "17/04/2020");
		parametros.put("CODIGO_TRANSACCION", "457457347234");
		parametros.put("NRO_COMPROBANTE", "128");
		parametros.put("FECHA_HORA_COMPROBANTE", "16/04/2020 - 12:20 hs");

		return parametros;
	}
	
}
