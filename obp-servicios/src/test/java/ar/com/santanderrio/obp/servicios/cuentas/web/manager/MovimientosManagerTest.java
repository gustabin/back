package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.UltimosMovimientosBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.CuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.UltimosMovimientosBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.MovimientosManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EstadoDetalleMovimientoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class MovimientosManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovimientosManagerTest {

    /** The manager. */
    @InjectMocks
    private MovimientosManager manager = new MovimientosManagerImpl();
    
    

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The session movimientos. */
    @Mock
    private SessionMovimientos sessionMovimientos;

    /** The cuentas service. */
    @Mock
    private CuentaBO cuentasBO = new CuentaBOImpl();

    @Mock
    //private MensajeBO mensajeBO = new MensajeBOImpl();
    private MensajeBO mensajeBO;

    @Mock
    private UltimosMovimientosBO ultimosMovimientosBO = new UltimosMovimientosBOImpl();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;
    
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Gets the detalle movimiento test.
     *
     * @return the detalle movimiento test
     */
    @Test
    public void getDetalleMovimientoTest() {
        DetalleMovimientoEntity detalleMovimiento = new DetalleMovimientoEntity();
        detalleMovimiento.setId("3");
        detalleMovimiento.setDelDia(false);
        List<DetalleMovimientoEntity> detalleMovimientoList = new ArrayList<DetalleMovimientoEntity>();
        detalleMovimientoList.add(detalleMovimiento);
        DetalleUltimosMovimientos detalleUltimosMovimientos = new DetalleUltimosMovimientos();
        detalleUltimosMovimientos.setDetalleMovimiento(detalleMovimientoList);
        Respuesta<DetalleUltimosMovimientos> respuesta = new Respuesta<DetalleUltimosMovimientos>();
        respuesta.setRespuesta(detalleUltimosMovimientos);
        Mockito.when(sessionMovimientos.getDetalleUltimosMovimientosOriginal()).thenReturn(respuesta);

        AbstractCuenta cuenta = new Cuenta();
        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        consultaUltimosMovimientos.setCuenta(cuenta);
        Mockito.when(sessionMovimientos.getFiltro()).thenReturn(consultaUltimosMovimientos);
        String mensajeInformativo = "Podras retirar el cheque en la sucursal de tu cuenta {0}";
        Mockito.when(ultimosMovimientosBO.obtenerMensajeInformativo(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(mensajeInformativo);
        cuenta.setNroSucursal("000");
        cuenta.setNroCuentaProducto("1111112");
        cuenta.setAlias("cuenta sueldo");
        cuenta.setTipoCuenta("1");
        EstadoDetalleMovimientoView movimientoView = new EstadoDetalleMovimientoView();
        movimientoView.setIsDelDia(false);
        movimientoView.setIsRechazado(true);
        Respuesta<DetalleMovimientosView> respuestaManager = manager.getDetalleMovimiento(movimientoView);
        assertNotNull(respuestaManager);
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_MOVIMIENTO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Gets the detalle movimiento dia test.
     *
     * @return the detalle movimiento dia test
     */
    @Test
    public void getDetalleMovimientoDiaTest() {
        DetalleMovimientoEntity detalleMovimiento = new DetalleMovimientoEntity();
        detalleMovimiento.setId("3");
        detalleMovimiento.setDelDia(true);
        List<DetalleMovimientoEntity> detalleMovimientoList = new ArrayList<DetalleMovimientoEntity>();
        detalleMovimientoList.add(detalleMovimiento);
        DetalleUltimosMovimientos detalleUltimosMovimientos = new DetalleUltimosMovimientos();
        detalleUltimosMovimientos.setDetalleMovimiento(detalleMovimientoList);
        Respuesta<DetalleUltimosMovimientos> respuesta = new Respuesta<DetalleUltimosMovimientos>();
        respuesta.setRespuesta(detalleUltimosMovimientos);
        Mockito.when(sessionMovimientos.getDetalleUltimosMovimientosOriginal()).thenReturn(respuesta);

        AbstractCuenta cuenta = new Cuenta();
        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        consultaUltimosMovimientos.setCuenta(cuenta);
        Mockito.when(sessionMovimientos.getFiltro()).thenReturn(consultaUltimosMovimientos);
        String mensajeInformativo = "Podras retirar el cheque en la sucursal de tu cuenta {0}";
        Mockito.when(ultimosMovimientosBO.obtenerMensajeInformativo(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(mensajeInformativo);
        cuenta.setNroSucursal("000");
        cuenta.setNroCuentaProducto("1111112");
        cuenta.setAlias("cuenta sueldo");
        cuenta.setTipoCuenta("1");
        EstadoDetalleMovimientoView movimientoView = new EstadoDetalleMovimientoView();
        movimientoView.setIsDelDia(true);
        movimientoView.setIsRechazado(true);
        Respuesta<DetalleMovimientosView> respuestaManager = manager.getDetalleMovimiento(movimientoView);
        assertNotNull(respuestaManager);
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
        Mockito.verify(estadisticaManager).add(
                EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_MOVIMIENTO_DIA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Detalle movimiento test.
     */
    @Test
    public void detalleMovimientoTest() {
        DetalleMovimientoEntity detalleMovimiento = new DetalleMovimientoEntity();
        detalleMovimiento.setId("3");
        detalleMovimiento.setDelDia(true);
        List<DetalleMovimientoEntity> detalleMovimientoList = new ArrayList<DetalleMovimientoEntity>();
        detalleMovimientoList.add(detalleMovimiento);
        DetalleUltimosMovimientos detalleUltimosMovimientos = new DetalleUltimosMovimientos();
        detalleUltimosMovimientos.setDetalleMovimiento(detalleMovimientoList);
        Respuesta<DetalleUltimosMovimientos> respuesta = new Respuesta<DetalleUltimosMovimientos>();
        respuesta.setRespuesta(detalleUltimosMovimientos);
        Mockito.when(sessionMovimientos.getDetalleUltimosMovimientosOriginal()).thenReturn(respuesta);

        AbstractCuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("123");
        cuenta.setNroSucursal("000");
        cuenta.setNroCuentaProducto("0011112");
        cuenta.setAlias("cuenta sueldo");
        cuenta.setTipoCuenta("1");
        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        consultaUltimosMovimientos.setCuenta(cuenta);
        Mockito.when(sessionMovimientos.getFiltro()).thenReturn(consultaUltimosMovimientos);
        String mensajeInformativo = "Podras retirar el cheque en la sucursal de tu cuenta {0}";
        Mockito.when(ultimosMovimientosBO.obtenerMensajeInformativo(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(mensajeInformativo);
        EstadoDetalleMovimientoView movimientoView = new EstadoDetalleMovimientoView();
        movimientoView.setIsDelDia(true);
        movimientoView.setIsRechazado(true);
        Respuesta<DetalleMovimientosView> respuestaManager = manager.getDetalleMovimiento(movimientoView);
        assertNotNull(respuestaManager);
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuestaManager.getRespuesta().getNumeroCuenta(), "000-001111/2");
        assertEquals(respuestaManager.getRespuesta().getMensajeInformativo(),
                "Podras retirar el cheque en la sucursal de tu cuenta {0}");
        assertEquals(respuestaManager.getRespuesta().getAliasCuenta(), "cuenta sueldo");
        assertEquals(respuestaManager.getRespuesta().getTipoCuenta(), "Caja de ahorro en $");
    }

}