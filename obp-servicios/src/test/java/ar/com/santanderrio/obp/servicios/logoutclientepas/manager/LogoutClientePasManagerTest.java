package ar.com.santanderrio.obp.servicios.logoutclientepas.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.logoutclientepas.manager.impl.LogoutClientePasManagerImpl;
import ar.com.santanderrio.obp.servicios.logoutclientepas.view.ResponseLogoutClientePas;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutClientePasManagerTest {

    @InjectMocks
    private LogoutClientePasManagerImpl logoutClientePasManager;

    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private SesionParametros sesionParametros;

    @Test
    public void enviarClienteEsPas() {

        ResponseLogoutClientePas response = new ResponseLogoutClientePas();

        response.setPas(true);

        when(sesionParametros.getPas()).thenReturn(true);
        Respuesta<ResponseLogoutClientePas> respuesta = respuestaFactory.crearRespuestaOk(ResponseLogoutClientePas.class, response);

        assertNotNull(respuesta);
        assertEquals(true, respuesta.getRespuesta().getPas());
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

}
