/**
 * 
 */
package ar.com.santanderrio.obp.servicios.factory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ResumenAnteriorViewResponse;

/**
 * The Class RespuestaFactoryTest.
 *
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
public class RespuestaFactoryTest {
    /** The Mensaje dao. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The respuestaFactory. */
    @InjectMocks
    private RespuestaFactory respuestaFactory;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Crear respuesta error test.
     */
    @Test
    public void crearRespuestaErrorTest() {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Un Mensaje de Error");
        when(mensajeDAO.obtenerMensajePorCodigo("110005")).thenReturn(mensaje);

        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTipoError("ERROR_CARGA_RESUMENES_ANTERIORES");
        itemMensajeRespuesta.setDetalleTipoError("ERROR_CARGA_RESUMENES_ANTERIORES");
        itemMensajeRespuesta.setTag("errorCargaCuotasPendiente");
        itemMensajeRespuesta.setMensaje("Un Mensaje de Error");
        Respuesta<ResumenAnteriorViewResponse> respuesta = new Respuesta<ResumenAnteriorViewResponse>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setRespuestaVacia(true);
        respuesta.add(itemMensajeRespuesta);

        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<ResumenAnteriorViewResponse> retorno = respuestaFactory
                .crearRespuestaError("errorCargaCuotasPendiente", "ERROR_CARGA_RESUMENES_ANTERIORES", "110005");

        // Validar
        assertEquals(respuesta, retorno);
    }

    /**
     * Crear respuesta OK test.
     */
    @Test
    public void crearRespuestaOKTest() {
        Respuesta<Boolean> respuesta = new Respuesta<Boolean>();

        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<Boolean> retorno = respuestaFactory.crearRespuestaOk(Boolean.class);

        assertEquals(respuesta, retorno);
    }

    /**
     * Crear respuesta OK data test.
     */
    @Test
    public void crearRespuestaOKDataTest() {

        Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
        respuesta.setRespuesta(new Boolean(true));

        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<Boolean> retorno = respuestaFactory.crearRespuestaOk(Boolean.class, new Boolean(true));

        assertEquals(respuesta, retorno);
    }

}
