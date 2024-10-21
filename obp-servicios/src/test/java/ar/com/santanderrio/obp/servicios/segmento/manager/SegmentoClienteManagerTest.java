package ar.com.santanderrio.obp.servicios.segmento.manager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.segmento.bo.SegmentoClienteBO;
import ar.com.santanderrio.obp.servicios.segmento.manager.impl.SegmentoClienteManagerImpl;

/**
 * The Class SegmentoClienteManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class SegmentoClienteManagerTest {

    /** The segmento cliente manager. */
    @InjectMocks
    private SegmentoClienteManagerImpl segmentoClienteManager = new SegmentoClienteManagerImpl();

    /** The segmento cliente BO. */
    @Mock
    private SegmentoClienteBO segmentoClienteBO;
    
    /** The Estadistica Manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /**
     * Obtener segmento test.
     */
    @Test
    public void obtenerSegmentoTest() {

        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();
        Respuesta<Segmento> respuestaSegmento = new Respuesta<Segmento>();

        segmento.setDuo(true);
        segmento.setPyme(false);
        respuestaSegmento.setRespuesta(segmento);
        respuestaSegmento.setEstadoRespuesta(EstadoRespuesta.OK);

        when(segmentoClienteBO.obtenerSegmento(Matchers.any(Cliente.class))).thenReturn(respuestaSegmento);

        Respuesta<Segmento> respuesta = segmentoClienteManager.obtenerSegmento(cliente);

        assertNotNull(respuesta);
        Segmento segmentoRespuesta = respuesta.getRespuesta();

        assertNotNull(segmentoRespuesta);
        assertTrue(segmentoRespuesta.isDuo());
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.CONSULTA_SERVICIO_SEGMENTO_CLIENTE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

    }

}
