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
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionTransaccionesHomeManagerImpl;

/**
 * The Class SeccionTransaccionesHomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)

public class SeccionTransaccionesHomeManagerTest {

    /** The seccion transacciones home manager. */
    @InjectMocks
    private SeccionTransaccionesHomeManager seccionTransaccionesHomeManager = new SeccionTransaccionesHomeManagerImpl();

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

        seccionTransaccionesHomeManager.notificarAccesoCalendario();
        verify(estadisticaManager).add(EstadisticasConstants.ACCESO_CONTROLLER_TRANSACCIONES_CALENDARIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Notificar acceso transacciones transferencias test.
     */
    @Test
    public void notificarAccesoTransaccionesTransferenciasTest() {

        seccionTransaccionesHomeManager.notificarAccesoTransferencias();
        verify(estadisticaManager).add(EstadisticasConstants.ACCESO_CONTROLLER_TRANSACCIONES_TRANSFERENCIAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

}
