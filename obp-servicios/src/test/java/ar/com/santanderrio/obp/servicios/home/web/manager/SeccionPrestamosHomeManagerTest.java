package ar.com.santanderrio.obp.servicios.home.web.manager;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionSolicitudesHomeManagerImpl;

/**
 * The Class SeccionPrestamosHomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)

public class SeccionPrestamosHomeManagerTest {

    /** The seccion solicitudes home manager. */
    @InjectMocks
    private SeccionSolicitudesHomeManager seccionSolicitudesHomeManager = new SeccionSolicitudesHomeManagerImpl();

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /**
     * Notificar acceso prestamos test.
     */
    @Test
    public void notificarAccesoPrestamosTest() {

        seccionSolicitudesHomeManager.notificarAccesoPrestamos();
        verify(estadisticaManager).add(EstadisticasConstants.ACCESO_CONTROLLER_SOLICITUDES_PRESTAMOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

}
