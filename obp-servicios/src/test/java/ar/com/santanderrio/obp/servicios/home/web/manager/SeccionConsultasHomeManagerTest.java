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
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionConsultasHomeManagerImpl;

/**
 * The Class SeccionConsultasHomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)

public class SeccionConsultasHomeManagerTest {

    /** The seccion consultas home manager. */
    @InjectMocks
    private SeccionConsultasHomeManager seccionConsultasHomeManager = new SeccionConsultasHomeManagerImpl();

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /**
     * Notificar acceso consulta cuentas test.
     */
    @Test
    public void notificarAccesoConsultaCuentasTest() {

        seccionConsultasHomeManager.notificarAccesoCuentas();
        verify(estadisticaManager).add(EstadisticasConstants.ESTADISTICA_ACCESO_CUENTAS_CONTROLLER,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Notificar acceso consulta prestamos test.
     */
    @Test
    public void notificarAccesoConsultaPrestamosTest() {

        seccionConsultasHomeManager.notificarAccesoPrestamos();
        verify(estadisticaManager).add(EstadisticasConstants.ACCESO_CONTROLLER_CONSULTAS_PRESTAMOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Notificar acceso consulta tarjetas test.
     */
    @Test
    public void notificarAccesoConsultaTarjetasTest() {

        seccionConsultasHomeManager.notificarAccesoTarjetas();
        verify(estadisticaManager).add(EstadisticasConstants.ACCESO_CONTROLLER_CONSULTAS_TARJETAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

}
