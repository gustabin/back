package ar.com.santanderrio.obp.servicios.prestamos.manager;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoOpenCreditBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConsultaPagosMinimosOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.DetallePagosMinimosOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.mock.PrestamoOpenCreditObjectsMock;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportePagosMinimosOpenCreditInView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoOpenCreditManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.PrestamoOpenCreditManagerImpl;


/**
 * PrestamoOpenCredit Manager Test
 * @author Silvina_Luque
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoOpenCreditManagerTest {
    
    
    /** The PrestamoOpenCreditManager. */
    @InjectMocks
    private PrestamoOpenCreditManager prestamoOpenCreditManager = new PrestamoOpenCreditManagerImpl();

    /** The prestamoOpenCreditBO. */
    @Mock
    private PrestamoOpenCreditBO prestamoOpenCreditBO;

    /** The mensajeBO. */
    @Mock
    private MensajeBO mensajeBO;
    
    /** The mensajeDAO. */
    @Mock
    private MensajeDAO mensajeDAO;
    
    /** The respuestaFactory. */
    @Mock
    private MensajeManager mensajeManager;
    
    @Mock
    private SesionCliente sesionCliente;
    
    /** The mensajeManager. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    private LegalBO legalBO;
    
    Mensaje mensaje = new Mensaje();
    
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        MockitoAnnotations.initMocks(this);
    }     
    
    /**
     * obtenerPrestamosOpenCreditTest
     */
    @Test
    public void obtenerPrestamosOpenCreditTest() {
        when(sesionCliente.getCliente()).thenReturn(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        Respuesta<List<PrestamoOpenCreditDTO>> respuestaBO =  new Respuesta<List<PrestamoOpenCreditDTO>>();
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(PrestamoOpenCreditObjectsMock.obtenerPrestamosOpenCreditDTO());
        Respuesta<PrestamosOpenCreditView> respuesta = new Respuesta<PrestamosOpenCreditView>();
        when(prestamoOpenCreditBO.obtenerPrestamosOpenCredit(Matchers.any(PrestamoOpenCreditInDTO.class))).thenReturn(respuestaBO);
        respuesta = prestamoOpenCreditManager.obtenerPrestamosOpenCredit();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    
    /**
     * obtenerPrestamosOpenCreditErrorTest
     */
    @Test
    public void obtenerPrestamosOpenCreditErrorTest() {
        when(sesionCliente.getCliente()).thenReturn(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        Respuesta<List<PrestamoOpenCreditDTO>> respuestaBO =  new Respuesta<List<PrestamoOpenCreditDTO>>();
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Respuesta<PrestamosOpenCreditView> respuesta = new Respuesta<PrestamosOpenCreditView>();
        when(prestamoOpenCreditBO.obtenerPrestamosOpenCredit(Matchers.any(PrestamoOpenCreditInDTO.class))).thenReturn(respuestaBO);
        respuesta = prestamoOpenCreditManager.obtenerPrestamosOpenCredit();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    
    /**
     * obtenerDetallePagosMinimosTest
     */
    @Test
    public void obtenerDetallePagosMinimosTest() {
        when(sesionCliente.getCliente()).thenReturn(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        Respuesta<DetallePagosMinimosOpenCreditDTO>  respuestaBO =  new Respuesta<DetallePagosMinimosOpenCreditDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(PrestamoOpenCreditObjectsMock.obtenerDetallePagosMinimosOpenCreditDTO());
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<DetallePagosMinimosOpenCreditView> respuesta = new Respuesta<DetallePagosMinimosOpenCreditView>();
        when(prestamoOpenCreditBO.obtenerDetallePagosMinimos(Matchers.any(ConsultaPagosMinimosOpenCreditInDTO.class))).thenReturn(respuestaBO);
        when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
        respuesta = prestamoOpenCreditManager.obtenerDetallePagosMinimos(PrestamoOpenCreditObjectsMock.obtenerConsultaDetallePagosMMinimos());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    
    /**
     * obtenerDetallePagosMinimosErrorTest
     */
    @Test
    public void obtenerDetallePagosMinimosErrorTest() {
        when(sesionCliente.getCliente()).thenReturn(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        Respuesta<DetallePagosMinimosOpenCreditDTO>  respuestaBO =  new Respuesta<DetallePagosMinimosOpenCreditDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<DetallePagosMinimosOpenCreditView> respuesta = new Respuesta<DetallePagosMinimosOpenCreditView>();
        when(prestamoOpenCreditBO.obtenerDetallePagosMinimos(Matchers.any(ConsultaPagosMinimosOpenCreditInDTO.class))).thenReturn(respuestaBO);
        respuesta = prestamoOpenCreditManager.obtenerDetallePagosMinimos(PrestamoOpenCreditObjectsMock.obtenerConsultaDetallePagosMMinimos());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    
    /**
     * exportarHistorialPagosMinimosTest
     */
    @Test    
    public void exportarHistorialPagosMinimosTest() {
        when(sesionCliente.getCliente()).thenReturn(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        Respuesta<Reporte>  respuestaBO =  new Respuesta<Reporte> ();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(PrestamoOpenCreditObjectsMock.obtenerRespuestaReportePagos());
        Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
        when(prestamoOpenCreditBO.exportarHistorialPagosMinimos(Matchers.any(ReportePagosMinimosOpenCreditInView.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
        respuesta = prestamoOpenCreditManager.exportarHistorialPagosMinimos(PrestamoOpenCreditObjectsMock.obtenerReportePagosMinimosOpenCreditInView());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * exportarHistorialPagosMinimosErrorTest
     */
    @Test    
    public void exportarHistorialPagosMinimosErrorTest() {
        when(sesionCliente.getCliente()).thenReturn(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
        when(prestamoOpenCreditBO.exportarHistorialPagosMinimos(Matchers.any(ReportePagosMinimosOpenCreditInView.class), Matchers.any(Cliente.class))).thenThrow(new RuntimeException());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        respuesta = prestamoOpenCreditManager.exportarHistorialPagosMinimos(PrestamoOpenCreditObjectsMock.obtenerReportePagosMinimosOpenCreditInView());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    
}
