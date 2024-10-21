package ar.com.santanderrio.obp.servicios.comun.mensaje.bo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;

/**
 * The Class MensajeBOTest.
 * 
 * @author emilio.watemberg
 * @since Jan 24, 2017.
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class MensajeBOTest {

    /** The mensaje BO. */
    @InjectMocks
    MensajeBOImpl mensajeBO;

    /** The mensaje DAO. */
    @Mock
    MensajeDAO mensajeDAO;

    /**
     * Inits the mocks.
     */
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Validar formato llaves test OK. Debera retornar el mensaje
     * correspondiente de la BD
     */
    @Test
    public void validarFormatoLlavesOkTest() {
        String codigoMensaje = "1118";
        String mensaje = "Recordá que hiciste un stop debit sobre el pago de tu {0}.";
        Mensaje respuestaEsperada = new Mensaje();
        respuestaEsperada.setMensaje(mensaje);
        respuestaEsperada.setCodigo(codigoMensaje);

        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(respuestaEsperada);
        Mensaje respuesta = mensajeBO.obtenerMensajePorCodigo(codigoMensaje);

        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.getMensaje().equals(mensaje));

    }

    /**
     * Validar formato llaves test ERROR. Debera retornar un mensaje con su
     * descripcion vacia ya que no supera la validacion de llaves.
     */
    @Test
    public void validarFormatoLlavesErrorTest() {
        String codigoMensaje = "1118";
        String mensaje = "Recordá que hiciste un stop debit sobre el pago de tu {0}. Lalalala {}";
        Mensaje respuestaEsperada = new Mensaje();
        respuestaEsperada.setMensaje(mensaje);
        respuestaEsperada.setCodigo(codigoMensaje);

        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(respuestaEsperada);
        Mensaje respuesta = mensajeBO.obtenerMensajePorCodigo(codigoMensaje);

        Assert.assertNotNull(respuesta);
        Assert.assertTrue(!respuesta.getMensaje().equals(mensaje));
        Assert.assertTrue(respuesta.getMensaje().equals(""));
    }

}
