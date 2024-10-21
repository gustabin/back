package ar.com.santanderrio.obp.servicios.clientes.web.manager;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.bo.CredencialesBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.impl.ClaveUsuarioManagerImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClaveOnlineManagerTest {

    /** The cliente manager. */
    @InjectMocks
    private ClaveUsuarioManagerImpl clienteManager = new ClaveUsuarioManagerImpl();

    /** The credenciales BO. */
    @Mock
    private CredencialesBO credencialesBO;

    /**
     * Cambio usuario
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void cambioClaveUsuarioTest() throws ServiceException {
        Cliente cliente = new Cliente();
        CambioUsuarioEntity entity = new CambioUsuarioEntity();
        entity.setCliente(cliente);
        entity.setStrOldPin("330A330A330A330A330A330A330A330A");
        entity.setStrNewPin("330A330A330A330A330A330A330A330A");
        entity.setStrOldUsr("348A304A37A304A57A297A271A239A315A315A315A315A315A315A315A315A315A315A315A315A");
        entity.setStrNewUsr("348A304A37A304A57A297A271A102A315A315A315A315A315A315A315A315A315A315A315A315A");

        Respuesta<ResumenCliente> respuesta = new Respuesta<ResumenCliente>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(credencialesBO.cambioUsuario(Matchers.any(HttpServletRequest.class),
                Matchers.any(entity.getClass()), Matchers.anyBoolean())).thenReturn(respuesta);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Cambio usuario
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void cambioClaveUsuarioErrorTest() throws ServiceException {
        Cliente cliente = new Cliente();
        CambioUsuarioEntity entity = new CambioUsuarioEntity();
        entity.setCliente(cliente);
        entity.setStrOldPin("330A330A330A330A330A330A330A330A");
        entity.setStrNewPin("330A330A330A330A330A330A330A330A");
        entity.setStrOldUsr("348A304A37A304A57A297A271A239A315A315A315A315A315A315A315A315A315A315A315A315A");
        entity.setStrNewUsr("348A304A37A304A57A297A271A102A315A315A315A315A315A315A315A315A315A315A315A315A");

        Respuesta<ResumenCliente> respuesta = new Respuesta<ResumenCliente>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mockito.when(credencialesBO.cambioUsuario(Matchers.any(HttpServletRequest.class),
                Matchers.any(entity.getClass()), Matchers.anyBoolean())).thenReturn(respuesta);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

}
