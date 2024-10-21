/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.legal.manager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class LegalManagerTest.
 *
 * @author sergio.e.goldentair
 */
@RunWith(MockitoJUnitRunner.class)
public class LegalManagerTest {
    /** The legal Manager. */
    @InjectMocks
    private LegalManagerImpl legalManager = new LegalManagerImpl();

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;

    /** generador de respuestas. */
    @Spy
    private RespuestaFactory respuestaFactory;

    /**
     * Limpiar legales.
     */
    @Test
    public void limpiarLegales() {
        Respuesta<Boolean> respOK = respuestaFactory.crearRespuestaOk(true);
        Mockito.when(legalBO.limpiarLegales()).thenReturn(respOK);
        Respuesta<Boolean> respuesta = legalBO.limpiarLegales();
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
}
