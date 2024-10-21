package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CuentasObtenerReporteCuentaTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasObtenerReporteCuentaTest {

    /** The cuenta manager. */
    @InjectMocks
    private CuentaManagerImpl cuentaManager = new CuentaManagerImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The cuentas service. */
    @Mock
    private CuentaBO cuentaBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;
    
    /** The sesion resumen cuenta. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Obtener reporte cuenta test.
     *
     * @throws BusinessException
     *             the service exception
     * @throws IOException 
     */
    @Test
    public void obtenerReporteCuentaTest() throws BusinessException, IOException {

        ConsultaCuentaView consultaCuentaView = new ConsultaCuentaView();
        Cliente cliente = new Cliente();
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        Reporte reporte = new Reporte();

        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        respuestaReporte.setRespuesta(reporte);
        respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
        consultaCuentaView.setNumeroCuenta("123-1234567");

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerReporteCBUCuenta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(respuestaReporte);

        Respuesta<ReporteView> respuesta = cuentaManager.obtenerReporteCuenta(consultaCuentaView);

        assertNotNull(respuesta.getRespuesta());

        ReporteView reporteView = respuesta.getRespuesta();

        assertEquals(reporteView.getTipoArchivo(), "application/pdf");
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_REPORTE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener reporte cuenta error test.
     *
     * @throws BusinessException
     *             the service exception
     */
    @Test
    public void obtenerReporteCuentaErrorTest() throws BusinessException {

        ConsultaCuentaView consultaCuentaView = new ConsultaCuentaView();
        Cliente cliente = new Cliente();
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();

        respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);
        consultaCuentaView.setNumeroCuenta("123-1234567");

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerReporteCBUCuenta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(respuestaReporte);

        cuentaManager.obtenerReporteCuenta(consultaCuentaView);

        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_REPORTE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Obtener reporte cuenta exception test.
     *
     * @throws BusinessException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerReporteCuentaExceptionTest() throws BusinessException {

        ConsultaCuentaView consultaCuentaView = new ConsultaCuentaView();
        Cliente cliente = new Cliente();
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();

        respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);
        consultaCuentaView.setNumeroCuenta("123-1234567");

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerReporteCBUCuenta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class), Matchers.anyString())).thenThrow(Exception.class);

        cuentaManager.obtenerReporteCuenta(consultaCuentaView);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_REPORTE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

}
