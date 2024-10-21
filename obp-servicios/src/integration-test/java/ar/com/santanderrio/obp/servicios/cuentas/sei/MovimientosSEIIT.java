package ar.com.santanderrio.obp.servicios.cuentas.sei;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParseException;
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

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.sei.MovimientosSEIIT.MovimientosSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.cuentas.sei.impl.MovimientosSEIImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.MovimientosManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EstadoDetalleMovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IdMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ItemMovimiento;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoValoresView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientosPendientesDeConfirmacionView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.NumeroCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.impl.ResponseImpl;



/**
 * The Class MovimientosSEITest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { MovimientosSEITestConfiguration.class })
public class MovimientosSEIIT extends AbstractSEITest {

    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The manager. */
    @Autowired
    private MovimientosManager manager;

    /** The session client. */
    @Autowired
    private SesionCliente sessionClient;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class MovimientosSEITestConfiguration.
     */
    @Configuration
    public static class MovimientosSEITestConfiguration {

        /**
         * Sesion cliente.
         *
         * @return the sesion cliente
         */
        @Bean
        public SesionCliente SesionCliente() {
            return Mockito.mock(SesionCliente.class);
        }

        /**
         * Mensaje manager.
         *
         * @return the mensaje manager
         */
        @Bean
        public MensajeManager mensajeManager() {
            return Mockito.mock(MensajeManager.class);
        }

        /**
         * Movimientos manager.
         *
         * @return the movimientos manager
         */
        @Bean
        public MovimientosManager movimientosManager() {
            return Mockito.mock(MovimientosManager.class);
        }

        /**
         * Movimientos SEI.
         *
         * @return the movimientos SEI
         */
        @Bean(name = "movimientosSEI")
        public MovimientosSEI movimientosSEI() {
            return new MovimientosSEIImpl();
        }

    }

    /**
     * Movimientos pendientes test.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void movimientosPendientesTest() throws JsonParseException, IOException {

        Respuesta<MovimientosPendientesDeConfirmacionView> respuesta = new Respuesta();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        MovimientosPendientesDeConfirmacionView movimientosPendientesDeConfirmacionView = new MovimientosPendientesDeConfirmacionView();
        respuesta.setRespuesta(movimientosPendientesDeConfirmacionView);
        Mockito.when(manager.getMovimientosPendientes((Matchers.any(ConsultaUltimosMovimientosView.class))))
                .thenReturn(respuesta);
        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/movimientos/obtenerMovimientosPendientes");
        Respuesta<MovimientosPendientesDeConfirmacionView> retorno = client.post(consultaUltimosMovimientosView,
                Respuesta.class);
        Assert.assertNotNull(retorno.getRespuesta());

    }

    /**
     * Obtener movimientos pendientes test.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void obtenerMovimientosPendientesTest() throws JsonParseException, IOException {
        Respuesta<MovimientoValoresView> respuesta = new Respuesta();
        List<ItemMovimiento> valoresDebito = new ArrayList<ItemMovimiento>();
        List<ItemMovimiento> valoresCredito = new ArrayList<ItemMovimiento>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        MovimientoValoresView movimientoValoresView = new MovimientoValoresView();
        movimientoValoresView.setValoresDebito(valoresDebito);
        movimientoValoresView.setValoresCredito(valoresCredito);
        respuesta.setRespuesta(movimientoValoresView);
        Mockito.when(manager.getMovimientosValores((Matchers.any(ConsultaUltimosMovimientosView.class))))
                .thenReturn(respuesta);
        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/movimientos/obtenerMovimientosValoresPendientes");
        Respuesta<MovimientosPendientesDeConfirmacionView> retorno = client.post(consultaUltimosMovimientosView,
                Respuesta.class);
        Assert.assertNotNull(retorno);

    }

    /**
     * Movimientos pendientes detalle test.
     */
    @Test
    public void movimientosPendientesDetalleTest() {
        Respuesta<MovimientosPendientesDeConfirmacionView> respuesta = new Respuesta();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        NumeroCuentaView numeroCuentaView = new NumeroCuentaView();
        Mockito.when(
                manager.getMovimientosPendientesDetalle((Matchers.any(String.class)), (Matchers.any(Cliente.class))))
                .thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/movimientos/obtenerMovimientosPendientesDetalle");
        Respuesta<MovimientosPendientesDeConfirmacionView> retorno = client.post(numeroCuentaView, Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertTrue(EstadoRespuesta.OK.equals(retorno.getEstadoRespuesta()));
    }

    /**
     * Movimientos valores pendientes detalle test.
     */
    @Test
    public void movimientosValoresPendientesDetalleTest() {
        NumeroCuentaView numeroCuentaView = new NumeroCuentaView();
        Respuesta<MovimientosPendientesDeConfirmacionView> respuesta = new Respuesta();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(manager.getMovimientosValoresPendientesDetalle((Matchers.any(String.class)),
                (Matchers.any(Cliente.class)))).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/movimientos/obtenerMovimientosValoresPendientesDetalle");
        Respuesta<MovimientosPendientesDeConfirmacionView> retorno = client.post(numeroCuentaView, Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertTrue(EstadoRespuesta.OK.equals(retorno.getEstadoRespuesta()));
    }

    /**
     * Movimientos pendientes reporte test.
     */
    @Test
    public void movimientosPendientesReporteTest() {
        NumeroCuentaView numeroCuentaView = new NumeroCuentaView();
        ResponseBuilder responseBuilder = Response.ok((Object) "OK");
        Response respuesta = responseBuilder.build();
        Mockito.when(manager.getMovimientosPendientesReporte((Matchers.any(String.class)))).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/movimientos/obtenerMovimientosPendientesReporte");
        Response retorno = client.post(numeroCuentaView, Response.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Exportar movimientos test.
     */
    @Test
    public void exportarMovimientosTest() {
        // MovimientosPendientesDeConfirmacionView
        // movimientosPendientesDeConfirmacionView = new
        // MovimientosPendientesDeConfirmacionView();
        ResponseBuilder responseBuilder = Response.ok((Object) "OK");
        Response respuesta = responseBuilder.build();
        Mockito.when(manager.exportarMovimientos()).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/movimientos/exportarMovimientos");
        Response retorno = client.post(null, Response.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Movimientos valores pendientes reporte test.
     */
    @Test
    public void movimientosValoresPendientesReporteTest() {
        NumeroCuentaView numeroCuentaView = new NumeroCuentaView();
        ResponseBuilder responseBuilder = Response.ok((Object) "OK");
        Response respuesta = responseBuilder.build();
        Mockito.when(manager.getMovimientosValoresPendientesReporte(Matchers.any(String.class))).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/movimientos/obtenerMovimientosValoresPendientesReporte");
        Response retorno = client.post(numeroCuentaView, Response.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Movimientos test.
     */
    @Test
    public void movimientosTest() {
        Respuesta<MovimientoView> respuesta = new Respuesta();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        MovimientoView movimientoView = new MovimientoView();
        respuesta.setRespuesta(movimientoView);
        Mockito.when(manager.getMovimientos((Matchers.any(ConsultaUltimosMovimientosView.class))))
                .thenReturn(respuesta);
        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/movimientos/obtenerMovimientos");
        Respuesta<MovimientosPendientesDeConfirmacionView> retorno = client.post(consultaUltimosMovimientosView,
                Respuesta.class);
        Assert.assertNotNull(retorno.getRespuesta());

    }

    /**
     * Detalle movimiento test.
     */
    @Test
    public void detalleMovimientoTest() {
        DetalleMovimientosView detalleMovimientosView = new DetalleMovimientosView();
        detalleMovimientosView.setMensajeInformativo("Podras retirar el cheque en la sucursal de tu cuenta {0}");
        Respuesta<DetalleMovimientosView> respuestaDetalleMovimientoView = new Respuesta<DetalleMovimientosView>();
        respuestaDetalleMovimientoView.setRespuesta(detalleMovimientosView);
        Mockito.when(manager.getDetalleMovimiento(Matchers.any(EstadoDetalleMovimientoView.class))).thenReturn(respuestaDetalleMovimientoView);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/movimientos/obtenerDetalleMovimiento");
        IdMovimientosView idMovimientosView = new IdMovimientosView();
        idMovimientosView.setId("3");
        EstadoDetalleMovimientoView view = new EstadoDetalleMovimientoView();
        view.setIsRechazado(true);
        view.setIsDelDia(true);
        Respuesta<DetalleMovimientosView> retorno = client.post(view, Respuesta.class);
        Assert.assertNotNull(retorno.getRespuesta());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("movimientosSEI");
    }

}
