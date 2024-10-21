package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.RespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CuentasGetCuentaTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasGetCuentaTest {

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
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The respuesta BO. */
    @InjectMocks
    @Spy
    private RespuestaBOImpl respuestaBO = new RespuestaBOImpl();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;
    
    @Mock
    private MensajeBO mensajeBO;
    
    /**
     * Gets the cuenta test.
     *
     * @return the cuenta test
     * @throws BusinessException
     *             the service exception
     */
    @Test
    public void getCuentaTest() throws BusinessException {

        Cliente cliente = new Cliente();
        Respuesta<ResumenDetalleCuenta> respuestaResumenDetalleCuenta = new Respuesta<ResumenDetalleCuenta>();
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();

        cliente.setNombre("Ricardo");
        cliente.setApellido1("Iorio");
        cliente.setTipoDocumento("N");
        cliente.setDni("12345678");
        resumenDetalleCuenta.setCliente(cliente);
        resumenDetalleCuenta.setCuentaTraspasoAutomatico(false);
        resumenDetalleCuenta.setCuentaCerrada(false);
        resumenDetalleCuenta.setTipoCuenta("1");
        resumenDetalleCuenta.setNroSucursal("123");
        resumenDetalleCuenta.setCbu("1234567890123456789012");
        resumenDetalleCuenta.setNroCuentaProducto("1234567");
        respuestaResumenDetalleCuenta.setRespuesta(resumenDetalleCuenta);
        respuestaResumenDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.OK);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerCuenta(Matchers.any(Cliente.class), Matchers.anyString()))
                .thenReturn(respuestaResumenDetalleCuenta);
        

        Respuesta<CuentasView> respuesta = cuentaManager.getCuenta("123456/7");

        assertNotNull(respuesta);
        assertNotNull(respuesta.getRespuesta());
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertTrue(respuesta.getRespuesta().getCuentas().size() > 0);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CTA_PRINCIPAL,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Gets the cuenta exception test.
     *
     * @return the cuenta exception test
     * @throws BusinessException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
	@Test
    public void getCuentaExceptionTest() throws BusinessException {

        Cliente cliente = new Cliente();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();

        respuestaMensaje.setRespuesta(mensaje);
        cliente.setNombre("Ricardo");
        cliente.setApellido1("Iorio");
        cliente.setTipoDocumento("N");
        cliente.setDni("12345678");

        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerCuenta(Matchers.any(Cliente.class), Matchers.anyString()))
                .thenThrow(BusinessException.class);

        Respuesta<CuentasView> respuesta = cuentaManager.getCuenta("123456/7");

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CTA_PRINCIPAL,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

}
