package ar.com.santanderrio.obp.servicios.prestamos.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.InicioPrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.InicioPrestamoManagerImpl;

/**
 * The Class InicioPrestamoNotificarAccesoHomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class InicioPrestamoNotificarAccesoHomeManagerTest {

    /** The inicio prestamo manager. */
    @InjectMocks
    private InicioPrestamoManager inicioPrestamoManager = new InicioPrestamoManagerImpl();

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManagerImpl estadisticaManager;

    /**
     * Notificar acceso desde home.
     */
    @Test
    public void notificarAccesoDesdeHome() {
        Respuesta<Void> respuesta = inicioPrestamoManager.notificarAccesoDesdeHome();
        assertNotNull(respuesta);
        assertNull(respuesta.getRespuesta());
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Mockito.verify(estadisticaManager).add("13193", "1");
    }
}
