package ar.com.santanderrio.obp.servicios.login.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.login.bo.impl.LoginBOImpl;

/**
 * The Class LoginBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginBOTest {

    /** The login BO. */
    @InjectMocks
    private LoginBOImpl loginBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /**
     * Obtener inicio login test.
     */
    @Test
    public void obtenerInicioLoginTest() {

        Mensaje mensaje = new Mensaje();

        mensaje.setMensaje("Mensaje del BO");

        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<Mensaje> respuesta = loginBO.obtenerInicioLogin();

        assertNotNull(respuesta);
        assertNotNull(respuesta.getRespuesta());
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta().getMensaje(), "Mensaje del BO");
    }

}
