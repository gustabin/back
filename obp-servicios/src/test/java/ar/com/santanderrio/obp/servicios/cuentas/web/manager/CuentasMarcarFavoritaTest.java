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
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CuentasMarcarFavoritaTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasMarcarFavoritaTest {

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
    
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    MensajeBO mensajeBO = new MensajeBOImpl();

    /**
     * Marcar favorita test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException 
     */
    @Test
    public void marcarFavoritaTest() throws BusinessException {

        Cliente cliente = new Cliente();
        Respuesta<Void> respuestaVoid = new Respuesta<Void>();

        respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);
        when(cuentaBO.marcarFavorita(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(respuestaVoid);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<Void> respuesta = cuentaManager.marcarFavorita("123-123456/7");

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta, respuestaVoid);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_FAVORITA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

    }

    /**
     * Marcar favorita error test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException 
     */
    @Test
    public void marcarFavoritaErrorTest() throws BusinessException {

        Cliente cliente = new Cliente();
        Respuesta<Void> respuestaVoid = new Respuesta<Void>();

        respuestaVoid.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(cuentaBO.marcarFavorita(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(respuestaVoid);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<Void> respuesta = cuentaManager.marcarFavorita("123-123456/7");

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuesta, respuestaVoid);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_FAVORITA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

    }

    /**
     * Marcar favorita exception test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException 
     */
    @SuppressWarnings("unchecked")
	@Test
    public void marcarFavoritaExceptionTest() throws BusinessException {

        Cliente cliente = new Cliente();

        when(cuentaBO.marcarFavorita(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenThrow(Exception.class);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        Mensaje msg = new Mensaje();
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(msg);

        cuentaManager.marcarFavorita("123-123456/7");

        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_FAVORITA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

    }

}
