package ar.com.santanderrio.obp.servicios.inversiones.descargapdf.bo;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.bo.impl.DescargaPdfBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DescargaPdfBOTest {

	@InjectMocks
	private DescargaPdfBOImpl descargaPDFBO; 
	
    @Mock
    private OndemandDAO ondemanDao;
    
    @Spy 
    @InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    
    @Test
    public void obtenerPDFOKCuentaBancaPrivada() throws WSODException {
    	
    	//When
    	Cuenta cuenta = new Cuenta();
    	ResumenMensualCuenta resumenSeleccionado = new ResumenMensualCuenta();
    	ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
    	
    	when(ondemanDao.obtenerReporteMensualBP(Matchers.any(ResumenMensualCuenta.class), Matchers.any(Cuenta.class))).thenReturn(reporteResumenMensualCuenta);
    	
    	//Then
    	Respuesta<ReporteResumenMensualCuenta> respuesta = descargaPDFBO.obtenerPDF(resumenSeleccionado, cuenta, TipoPDFEnum.CUENTAS_BANCA_PRIVADA);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    
    @Test
    public void obtenerPDFOKTitulosValores() throws WSODException {
    	
    	//When
    	Cuenta cuenta = new Cuenta();
    	ResumenMensualCuenta resumenSeleccionado = new ResumenMensualCuenta();
    	ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
    	
    	when(ondemanDao.obtenerReporteComprobantesBP(Matchers.any(ResumenMensualCuenta.class), Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenReturn(reporteResumenMensualCuenta);
    	
    	//Then
    	Respuesta<ReporteResumenMensualCuenta> respuesta = descargaPDFBO.obtenerPDF(resumenSeleccionado, cuenta, TipoPDFEnum.TITULOS_VALORES);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
	
	@SuppressWarnings("unchecked")
	@Test
    public void obtenerPDFWSODException() throws WSODException {
    	
    	//When
    	Cuenta cuenta = new Cuenta();
    	ResumenMensualCuenta resumenSeleccionado = new ResumenMensualCuenta();
    	
    	when(ondemanDao.obtenerReporteComprobantesBP(Matchers.any(ResumenMensualCuenta.class), Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenThrow(WSODException.class);
    	
    	//Then
    	Respuesta<ReporteResumenMensualCuenta> respuesta = descargaPDFBO.obtenerPDF(resumenSeleccionado, cuenta, TipoPDFEnum.TITULOS_VALORES);
    	
    	//Expected
    	Assert.assertNull(respuesta.getRespuesta());
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerPDFRuntimeException() throws WSODException {
    	
    	//When
    	Cuenta cuenta = new Cuenta();
    	ResumenMensualCuenta resumenSeleccionado = new ResumenMensualCuenta();
    	
    	when(ondemanDao.obtenerReporteComprobantesBP(Matchers.any(ResumenMensualCuenta.class), Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenThrow(RuntimeException.class);
    	
    	//Then
    	Respuesta<ReporteResumenMensualCuenta> respuesta = descargaPDFBO.obtenerPDF(resumenSeleccionado, cuenta, TipoPDFEnum.TITULOS_VALORES);
    	
    	//Expected
    	Assert.assertNull(respuesta.getRespuesta());
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
}
