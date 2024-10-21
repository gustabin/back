package ar.com.santanderrio.obp.servicios.pagos.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.PrestamoManagerImpl;

/**
 * The Class PrestamoManagerNotificarAccesoTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoManagerNotificarAccesoTest {

    /** The prestamo manager. */
    @InjectMocks
    private PrestamoManager prestamoManager = new PrestamoManagerImpl();

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManagerImpl estadisticaManager;


    @Test
    public void notificarAccesoDetalleImporteCuotaPersonalTest() {
        prestamoManager.notificarDetalleImporteCuota("PERSONAL");
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.ACCESO_VER_DETALLE_IMPORTE_CUOTA_PRESTAMO_PERSONAL,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }
    
    @Test
    public void notificarAccesoDetalleImporteCuotaHipotecarioTest() {
        prestamoManager.notificarDetalleImporteCuota("HIPOTECARIO");
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.ACCESO_VER_DETALLE_IMPORTE_CUOTA_PRESTAMO_HIPOTECARIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }
    
    @Test
    public void notificarAccesoDetalleImporteCuotaPrendarioTest() {
        prestamoManager.notificarDetalleImporteCuota("PRENDARIO");
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.ACCESO_VER_DETALLE_IMPORTE_CUOTA_PRESTAMO_PRENDARIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    @Test
    public void notificarDetalleTasasCuotaPersonalTest() {
        prestamoManager.notificarDetalleTasasCuota("PERSONAL");
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.ACCESO_VER_DETALLE_TASAS_CUOTA_PRESTAMO_PERSONAL,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }
    
    @Test
    public void notificarDetalleTasasCuotaHipotecarioTest() {
        prestamoManager.notificarDetalleTasasCuota("HIPOTECARIO");
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.ACCESO_VER_DETALLE_TASAS_CUOTA_PRESTAMO_HIPOTECARIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }
    
    @Test
    public void notificarDetalleTasasCuotaPrendarioTest() {
        prestamoManager.notificarDetalleTasasCuota("PRENDARIO");
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.ACCESO_VER_DETALLE_TASAS_CUOTA_PRESTAMO_PRENDARIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

}
