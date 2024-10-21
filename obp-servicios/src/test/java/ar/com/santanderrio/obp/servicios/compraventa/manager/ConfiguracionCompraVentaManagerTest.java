/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.manager;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaCasuisticaRespuestaBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.ConfiguracionCompraVentaDolaresBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaCasuisticaRespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.dto.CompraVentaInicioDTO;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.Cotizacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.impl.ConfiguracionCompraVentaManagerImpl;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCompraVenta;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ConfiguracionCompraVentaManagerTest.
 * 
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ConfiguracionCompraVentaManagerTest {

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The sesion compra venta. */
    @Mock
    protected SesionCompraVenta sesionCompraVenta;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The util. */
    @Mock
    protected CompraVentaDolaresUtil util;

    /** The cuenta manager. */
    @Mock
    private CuentaManager cuentaManager;

    /** The configuracion compra venta dolares BO. */
    @Mock
    private ConfiguracionCompraVentaDolaresBO configuracionCompraVentaDolaresBO;

    /** The casuistica BO. */
    @InjectMocks
    @Spy
    protected CompraVentaCasuisticaRespuestaBO casuistica = new CompraVentaCasuisticaRespuestaBOImpl();

    /** The manager. */
    @InjectMocks
    private ConfiguracionCompraVentaManagerImpl manager = new ConfiguracionCompraVentaManagerImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    @Mock
    private EstadisticaManager estadisticaManager;

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Obtener datos iniciales ok Y error cuenta inhabilitada test.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerDatosInicialesOkYErrorCuentaInhabilitadaTest()
            throws IllegalAccessException, ServiceException, BusinessException {
        Respuesta<CuentasView> resCuentasView = new Respuesta<CuentasView>();
        resCuentasView.setEstadoRespuesta(EstadoRespuesta.OK);
        resCuentasView.setRespuesta(new CuentasView());
        resCuentasView.getRespuesta().setCuentas(new ArrayList<CuentasAdhesionDebitoView>());

        CuentasAdhesionDebitoView cuentasAdhesion = new CuentasAdhesionDebitoView();
        cuentasAdhesion.setShowSaldoPesos(true);
        cuentasAdhesion.setShowSaldoDolares(true);
        cuentasAdhesion.setNumero("012-124124124/7");
        cuentasAdhesion.setIsFavorito(false);
        resCuentasView.getRespuesta().getCuentas().add(cuentasAdhesion);

        Respuesta<CompraVentaInicioDTO> respuestaCompraVentaInicioDTO = new Respuesta<CompraVentaInicioDTO>();
        respuestaCompraVentaInicioDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCompraVentaInicioDTO.setRespuesta(new CompraVentaInicioDTO());

        FieldUtils.writeDeclaredField(manager, "horaInicioCompraVenta",
                StringUtils.leftPad("" + ((DateTime.now().getHourOfDay() - 1) % 24), 2, '0') + ":"
                        + StringUtils.leftPad("" + DateTime.now().getMinuteOfHour(), 2, '0'),
                true);
        FieldUtils.writeDeclaredField(manager, "horaFinCompraVenta",
                StringUtils.leftPad("" + ((DateTime.now().getHourOfDay() + 1) % 24), 2, '0') + ":"
                        + StringUtils.leftPad("" + DateTime.now().getMinuteOfHour(), 2, '0'),
                true);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(cuentaManager.convertirCuentasView(Matchers.any(Respuesta.class))).thenReturn(resCuentasView);
        Mockito.when(util.tieneCuentasHabilitadas(Matchers.anyList(), Matchers.any(Cliente.class))).thenReturn(true);
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn("012-124124124/7");
        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn("012-124124124/8");
        Mockito.when(util.obtenerCuentaOrigen(Matchers.anyList(), Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(Mockito.mock(Cuenta.class));
        Mockito.when(configuracionCompraVentaDolaresBO.obtenerCotizacion(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), false))
                .thenReturn(respuestaCompraVentaInicioDTO);

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CompraVentaDolarView> res = manager.obtenerDatosIniciales("012-124124124/7", "compra", Boolean.TRUE, null);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn("012-124124124/7");
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn("012-124124124/8");
        Mockito.when(util.obtenerCuentaOrigen(Matchers.anyList(), Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(null);
        res = manager.obtenerDatosIniciales("012-124124124/7", "venta", Boolean.TRUE, null);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());

        cuentasAdhesion.setShowSaldoPesos(false);
        res = manager.obtenerDatosIniciales("012-124124124/7", "compra", Boolean.TRUE, null);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener datos iniciales hora inhabilitada Y excepcion test.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerDatosInicialesHoraInhabilitadaYExcepcionTest() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(manager, "horaInicioCompraVenta", "pepe", true);
        FieldUtils.writeDeclaredField(manager, "horaFinCompraVenta", "pepe2", true);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CompraVentaDolarView> res = manager.obtenerDatosIniciales(null, null, true, null);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());

        FieldUtils.writeDeclaredField(manager, "horaInicioCompraVenta",
                ((DateTime.now().getHourOfDay() + 1) % 24) + ":" + DateTime.now().getMinuteOfHour(), true);
        FieldUtils.writeDeclaredField(manager, "horaFinCompraVenta",
                ((DateTime.now().getHourOfDay() - 1) % 24) + ":" + DateTime.now().getMinuteOfHour(), true);

        res = manager.obtenerDatosIniciales("012-124124124/7", "compra", true, null);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener cotizacion para compra venta ok test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCotizacionParaCompraVentaOkTest() throws ServiceException, BusinessException {
        Respuesta<CompraVentaInicioDTO> resCompraVentaInicioDTO = new Respuesta<CompraVentaInicioDTO>();
        resCompraVentaInicioDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        resCompraVentaInicioDTO.setRespuesta(new CompraVentaInicioDTO());
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setCuentaOrigen("012-124124/7");

        estadisticaManager.add(Mockito.anyString(), Mockito.anyString());

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(new Cuenta());
        Mockito.when(util.estaHabilitadaParaCompraVentaDolar(Matchers.any(Cuenta.class))).thenReturn(true);
        Mockito.when(configuracionCompraVentaDolaresBO.obtenerCotizacion(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), false))
                .thenReturn(resCompraVentaInicioDTO);

        Respuesta<CompraVentaDolarView> res = manager.obtenerCotizacionParaCompra(cotizacion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

        res = manager.obtenerCotizacionParaVenta(cotizacion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener cotizacion para compra venta exception test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCotizacionParaCompraVentaExceptionTest() throws ServiceException, BusinessException {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CompraVentaDolarView> res = manager.obtenerCotizacionParaCompra(null);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());

        res = manager.obtenerCotizacionParaVenta(null);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Es respuesta OK test.
     * 
     * @throws ParseException
     *             the parse exception
     */
    @Test
    public void verificarHoraHabilitadaTest() throws ParseException {
        Boolean respuesta = manager.verificarHoraHabilitada();
        assertNotNull(respuesta);
    }

}
