package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.RespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.service.impl.RespuestaServiceImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CuentasActualizarAliasTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasActualizarAliasTest {

    /** The cuenta manager. */
    @InjectMocks
    private CuentaManagerImpl cuentaManager = new CuentaManagerImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The cuentas service. */
    @Mock
    private CuentaBO cuentaBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The respuesta service. */
    @InjectMocks
    @Spy
    private RespuestaServiceImpl respuestaService = new RespuestaServiceImpl();

    /** The respuesta BO. */
    @InjectMocks
    @Spy
    private RespuestaBOImpl respuestaBO = new RespuestaBOImpl();

    /** The mensaje BO. */
    @Mock
    private MensajeBOImpl mensajeBO = new MensajeBOImpl();

    
    /** The sesion resumen cuenta. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Actualizar alias test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException 
     */
    @Test
    public void actualizarAliasTest() throws BusinessException {

        Cliente cliente = new Cliente();
        Respuesta<Void> respuestaVoid = new Respuesta<Void>();

        respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);

        when(cuentaBO.actualizarAlias(Matchers.any(IdentificacionCuenta.class), Matchers.anyString(),
                Matchers.any(Cliente.class))).thenReturn(respuestaVoid);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<Void> respuesta = cuentaManager.actualizarAlias("123-123456/7", "Alias");

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta, respuestaVoid);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ALIAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Actualizar alias error test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException 
     */
    @Test
    public void actualizarAliasErrorTest() throws BusinessException {

        Cliente cliente = new Cliente();
        Respuesta<Void> respuestaVoid = new Respuesta<Void>();

        respuestaVoid.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(cuentaBO.actualizarAlias(Matchers.any(IdentificacionCuenta.class), Matchers.anyString(),
                Matchers.any(Cliente.class))).thenReturn(respuestaVoid);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<Void> respuesta = cuentaManager.actualizarAlias("123-123456/7", "Alias");

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuesta, respuestaVoid);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ALIAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Actualizar alias exception test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException 
     */
    @SuppressWarnings("unchecked")
	@Test
    public void actualizarAliasExceptionTest() throws BusinessException {

        Cliente cliente = new Cliente();

        when(cuentaBO.actualizarAlias(Matchers.any(IdentificacionCuenta.class), Matchers.anyString(),
                Matchers.any(Cliente.class))).thenThrow(Exception.class);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Error");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        cuentaManager.actualizarAlias("123-123456/7", "Alias");

        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ALIAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

}
