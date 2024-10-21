/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.sei;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.sei.impl.CompraVentaSEIImpl;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ContinuarCompraVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.Cotizacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.ConfiguracionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.OperacionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.SimulacionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CompraVentaSEITest.
 *
 * @author sabrina.cis
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ar.com.santanderrio.obp.servicios.compraventa.sei.CompraVentaSEIIT.CompraVentaSEITestConfiguration.class })
public class CompraVentaSEIIT extends AbstractSEITest {

    /** The ConfiguracionCompraVentaManager. */
    @Autowired
    private ConfiguracionCompraVentaManager configuracionCompraVentaManager;

    /** The OperacionClienteCompraManager. */
    @Autowired
    private OperacionCompraVentaManager operacionManager;

    /** The simulacion cliente compra manager. */
    @Autowired
    private SimulacionCompraVentaManager simulacionManager;

    /** The respuesta factory. */
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Inits the mock.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Gets the service bean to test.
     *
     * @return the service bean to test
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("compraVentaSEI");
    }

    /**
     * The Class CompraVentaSEITestConfiguration.
     */
    @Configuration
    public static class CompraVentaSEITestConfiguration {

        /**
         * Compra venta SEI.
         *
         * @return the compra venta SEI
         */
        @Bean(name = "compraVentaSEI")
        public CompraVentaSEI compraVentaSEI() {
            return new CompraVentaSEIImpl();
        }

        /**
         * Configuracion compra venta manager.
         *
         * @return the configuracion compra venta manager
         */
        @Bean
        public ConfiguracionCompraVentaManager configuracionCompraVentaManager() {
            return Mockito.mock(ConfiguracionCompraVentaManager.class);
        }

        /**
         * Operacion cliente compra manager.
         *
         * @return the operacion cliente compra manager
         */
        @Bean
        public OperacionCompraVentaManager operacionClienteCompraManager() {
            return Mockito.mock(OperacionCompraVentaManager.class);
        }

        /**
         * Simulacion cliente vende manager.
         *
         * @return the simulacion cliente vende manager
         */
        @Bean
        public SimulacionCompraVentaManager simulacionClienteVendeManager() {
            return Mockito.mock(SimulacionCompraVentaManager.class);
        }

    }

    /**
     * Compra/Venta de dolares inicio. Se obtiene la cotizacion + las cuentas
     * habilitadas para que el cliente pueda operar.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void compraVentaDolaresInicioOK() {
        String nroCuentaProducto = "0000000017450870";
        String operacion = "C";
        Mockito.when(configuracionCompraVentaManager.obtenerDatosIniciales(nroCuentaProducto, operacion, Boolean.FALSE, null))
                .thenReturn(crearRespuestaOK(CompraVentaDolarView.class, generarCompraVentaDolarViewConCuentas()));

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/cuentas/getDatosIniciales");
        addSleepTime(5000);

        Respuesta<CompraVentaDolarView> retorno = client.post(null, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Obtener cotizacion para compra OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerCotizacionParaCompraOK() {
        Mockito.when(configuracionCompraVentaManager.obtenerCotizacionParaCompra(Matchers.any(Cotizacion.class)))
                .thenReturn(crearRespuestaOK(CompraVentaDolarView.class, generarCompraVentaDolarViewSinCuentas()));

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/cuentas/getCotizacionCompra");

        Respuesta<CompraVentaDolarView> retorno = client.post(generarCotizacion(), Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Obtener cotizacion para venta OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerCotizacionParaVentaOK() {
        Mockito.when(configuracionCompraVentaManager.obtenerCotizacionParaVenta(Matchers.any(Cotizacion.class)))
                .thenReturn(crearRespuestaOK(CompraVentaDolarView.class, generarCompraVentaDolarViewSinCuentas()));

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/cuentas/getCotizacionVenta");

        Respuesta<CompraVentaDolarView> retorno = client.post(generarCotizacion(), Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Operacion cliente compra OK. Confirmacion de la operacion de compra.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void operacionClienteCompraOK() throws CompraVentaDolaresException {
        Respuesta<ComprobanteCompraVentaView> respuesta = crearRespuestaOK(ComprobanteCompraVentaView.class,
                generarComprobanteCompraVentaViewParaCompra());
        Mockito.when(operacionManager.operacionClienteCompra(Matchers.any(ConfirmarClienteCompraEntity.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/cuentas/confirmarClienteCompra");

        Respuesta<ComprobanteCompraVentaView> retorno = client.post(generarConfirmarClienteCompra(), Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertEquals("16,05", respuesta.getRespuesta().getCotizacion());
    }

    /**
     * Confirmar cliente vende.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void confirmarClienteVende() throws CompraVentaDolaresException {
        Respuesta<ComprobanteCompraVentaView> respuesta = crearRespuestaOK(ComprobanteCompraVentaView.class,
                generarComprobanteCompraVentaViewParaVenta());
        Mockito.when(operacionManager.operacionClienteVende(Matchers.any(ConfirmarClienteVentaEntity.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/cuentas/confirmarClienteVenta");

        Respuesta<ComprobanteCompraVentaView> retorno = client.post(generarConfirmarClienteCompra(), Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertEquals("16,05", respuesta.getRespuesta().getCotizacion());

    }

    /**
     * Continuar venta dolares OK.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void continuarVentaDolaresOK() throws CompraVentaDolaresException {
        Respuesta<SimulacionCompraVentaDolarView> respuesta = crearRespuestaOK(SimulacionCompraVentaDolarView.class,
                generarSimulacionCompraVentaDolarViewParaVenta());
        Mockito.when(simulacionManager.continuarVentaDolares(Matchers.any(ContinuarCompraVentaEntity.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/cuentas/continuarVentaDolares");

        Respuesta<SimulacionCompraVentaDolarView> retorno = client.post(generarContinuarCompraVentaParaVenta(),
                Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertEquals("16,05", respuesta.getRespuesta().getCotizacion());
        Assert.assertEquals("321-359464/5", respuesta.getRespuesta().getNroCuentaDestino());
    }

    /**
     * Continuar compra dolares OK.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void continuarCompraDolaresOK() throws CompraVentaDolaresException {
        Respuesta<SimulacionCompraVentaDolarView> respuesta = crearRespuestaOK(SimulacionCompraVentaDolarView.class,
                generarSimulacionCompraVentaDolarViewParaCompra());
        Mockito.when(simulacionManager.continuarCompraDolares(Matchers.any(ContinuarCompraVentaEntity.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/cuentas/continuarCompraDolares");

        Respuesta<SimulacionCompraVentaDolarView> retorno = client.post(generarContinuarCompraVentaParaCompra(),
                Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertEquals("990,00", respuesta.getRespuesta().getImporteDebitarPesos());
        Assert.assertEquals("66,00", respuesta.getRespuesta().getImporteCompraDolar());
        Assert.assertEquals("14,30", respuesta.getRespuesta().getCotizacion());
    }

    /**
     * Generar continuar compra/venta para compra.
     *
     * @return the continuar compra venta
     */
    private ContinuarCompraVentaEntity generarContinuarCompraVentaParaCompra() {
        ContinuarCompraVentaEntity continuarCompra = new ContinuarCompraVentaEntity();
        continuarCompra.setNumeroCuentaOrigen("023-123456/7");
        continuarCompra.setNumeroCuentaDestino("034-456789/1");
        continuarCompra.setCotizacion("14,30");
        continuarCompra.setImporte(66.00);
        continuarCompra.setIsDolar(true);
        return continuarCompra;
    }

    /**
     * Generar simulacion compra/venta dolar view para compra.
     *
     * @return the simulacion compra venta dolar view
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    private SimulacionCompraVentaDolarView generarSimulacionCompraVentaDolarViewParaCompra()
            throws CompraVentaDolaresException {
        SimulacionCompraVentaDolarView simulacionCompra = new SimulacionCompraVentaDolarView();
        simulacionCompra.setEsCompra(true);
        simulacionCompra.setEsVenta(false);
        simulacionCompra.setImporteCompraDolar(BigDecimal.valueOf(66.00));
        simulacionCompra.setNroCuentaOrigen("023-123456/7");
        simulacionCompra.setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_PESOS.getDescripcionConMoneda());
        simulacionCompra.setNroCuentaDestino("034-456789/1");
        simulacionCompra.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_DOLARES.getDescripcionConMoneda());
        simulacionCompra.setCotizacion(BigDecimal.valueOf(14.30));
        simulacionCompra.setImporteDebitarPesos(BigDecimal.valueOf(990.00));
        simulacionCompra.setFecha(new Date());
        simulacionCompra.setLegales("Por la presente declaro bajo juramento que:");
        return simulacionCompra;
    }

    /**
     * Generar compra/venta dolar view con cuentas.
     *
     * @return the compra venta dolar view
     */
    private CompraVentaDolarView generarCompraVentaDolarViewConCuentas() {
        CuentasAdhesionDebitoView cuentaView = new CuentasAdhesionDebitoView();
        cuentaView.setAlias("Pepe");
        cuentaView.setApellidoCliente("Rodriguez");
        cuentaView.setCbu("cbu");
        cuentaView.setIsCerrada(false);
        cuentaView.setIsCuentaSueldo(true);
        cuentaView.setSaldoPesos("200000");
        cuentaView.setIsFavorito(true);

        List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
        cuentas.add(cuentaView);

        return crearCompraVentaDolarView(cuentas);
    }

    /**
     * Generar compra/venta dolar view sin cuentas.
     *
     * @return the compra venta dolar view
     */
    private CompraVentaDolarView generarCompraVentaDolarViewSinCuentas() {
        return crearCompraVentaDolarView(null);
    }

    /**
     * Crear compra/venta dolar view.
     *
     * @param cuentas
     *            the cuentas
     * @return the compra venta dolar view
     */
    private CompraVentaDolarView crearCompraVentaDolarView(List<CuentasAdhesionDebitoView> cuentas) {
        CompraVentaDolarView view = new CompraVentaDolarView();
        view.setCotizacion(16.05);
        view.setCotizacionString("16.05");
        view.setCuentasOrigen(cuentas);
        view.setSelected(0);
        return view;
    }

    /**
     * Generar comprobante compra venta view para compra.
     *
     * @return the comprobante compra venta view
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    private ComprobanteCompraVentaView generarComprobanteCompraVentaViewParaCompra()
            throws CompraVentaDolaresException {
        ComprobanteCompraVentaView view = new ComprobanteCompraVentaView();
        view.setCotizacion(BigDecimal.valueOf(16.05));
        view.setEsCompra(true);
        view.setEsVenta(false);
        view.setFecha(new Date());
        view.setImporteCompraDolar(BigDecimal.valueOf(150.00));
        view.setImporteDebitarPesos(BigDecimal.valueOf(15000.00));
        view.setLegalesUno("Conserve este ticket como comprobante S.E.U.O.");
        view.setNroCuentaDestino("321-359464/5");
        view.setNroCuentaOrigen("321-359464/5");
        view.setNumeroComprobante("25-98760/34");
        view.setTipoCuentaDestino("tipo destino");
        view.setTipoCuentaOrigen("tipo origen");
        return view;
    }

    /**
     * Generar comprobante compra venta view para venta.
     *
     * @return the comprobante compra venta view
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    // TODO Falta implementacion cuando se arreglo el comprobante para la venta.
    private ComprobanteCompraVentaView generarComprobanteCompraVentaViewParaVenta() throws CompraVentaDolaresException {
        ComprobanteCompraVentaView view = new ComprobanteCompraVentaView();
        view.setCotizacion(BigDecimal.valueOf(16.05));
        view.setEsCompra(true);
        view.setEsVenta(false);
        view.setFecha(new Date());
        view.setImporteAcreditarPesos(BigDecimal.valueOf(1500));
        view.setImporteCompraDolar(BigDecimal.valueOf(150));
        view.setImporteDebitarPesos(BigDecimal.valueOf(150));
        view.setImporteVentaDolar(BigDecimal.valueOf(150));
        view.setLegalesUno("Conserve este ticket como comprobante S.E.U.O.");
        view.setNroCuentaDestino("321-359464/5");
        view.setNroCuentaOrigen("321-359464/5");
        view.setNumeroComprobante("25-98760/34");
        view.setTipoCuentaDestino("tipo destino");
        view.setTipoCuentaOrigen("tipo origen");
        return view;
    }

    /**
     * Generar confirmar cliente compra.
     *
     * @return the confirmar cliente compra
     */
    private ConfirmarClienteCompraEntity generarConfirmarClienteCompra() {
        ConfirmarClienteCompraEntity confirmarClienteCompra = new ConfirmarClienteCompraEntity();
        confirmarClienteCompra.setNumeroCuentaOrigen("321-359464/5");
        confirmarClienteCompra.setNumeroCuentaDestino("321-359464/5");
        confirmarClienteCompra.setIsDolar(true);
        confirmarClienteCompra.setImporteCredito("100");
        confirmarClienteCompra.setImporteDebito("100");
        confirmarClienteCompra.setNupTipo("L");
        confirmarClienteCompra.setNupNumDoc("34067987");
        confirmarClienteCompra.setCotizacion("16");
        return confirmarClienteCompra;
    }

    /**
     * Generar continuar compra/venta para venta.
     *
     * @return the continuar compra venta
     */
    private ContinuarCompraVentaEntity generarContinuarCompraVentaParaVenta() {
        ContinuarCompraVentaEntity continuarCompraVenta = new ContinuarCompraVentaEntity();
        continuarCompraVenta.setImporte(100);
        continuarCompraVenta.setIsDolar(true);
        continuarCompraVenta.setNumeroCuentaDestino("321-359464/5");
        continuarCompraVenta.setNumeroCuentaOrigen("321-359464/5");
        return continuarCompraVenta;
    }

    /**
     * Generar cotizacion.
     *
     * @return the cotizacion
     */
    private Cotizacion generarCotizacion() {
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setCuentaOrigen("321-359464/5");
        cotizacion.setIsDolar(true);
        return cotizacion;
    }

    /**
     * Generar simulacion compra/venta dolar view para venta.
     *
     * @return the simulacion compra venta dolar view
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    private SimulacionCompraVentaDolarView generarSimulacionCompraVentaDolarViewParaVenta()
            throws CompraVentaDolaresException {
        SimulacionCompraVentaDolarView view = new SimulacionCompraVentaDolarView();
        view.setCotizacion(BigDecimal.valueOf(16.05));
        view.setEsCompra(false);
        view.setEsVenta(true);
        view.setFecha(new Date());
        view.setImporteAcreditarPesos(BigDecimal.valueOf(15090.00));
        view.setImporteVentaDolar(BigDecimal.valueOf(150.00));
        view.setLegales("Legales para la Venta Dolares");
        view.setNroCuentaDestino("321-359464/5");
        view.setNroCuentaOrigen("321-359464/5");
        view.setTipoCuentaDestino("tipo destino");
        view.setTipoCuentaOrigen("tipo origen");
        return view;

    }

    /**
     * Crear respuesta OK generica.
     *
     * @param <T>
     *            the generic type
     * @param classEntity
     *            the class entity
     * @param entity
     *            the entity
     * @return the respuesta
     */
    private <T> Respuesta<T> crearRespuestaOK(Class<T> classEntity, T entity) {
        return respuestaFactory.crearRespuestaOk(classEntity, entity);
    }

}
