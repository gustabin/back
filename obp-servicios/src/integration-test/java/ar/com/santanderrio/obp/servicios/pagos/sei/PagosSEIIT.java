/* Tests para PagosSEI
 *
 * Manuel Vargas B041299
 */
package ar.com.santanderrio.obp.servicios.pagos.sei;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IdentificacionCuentaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEIIT.PagosSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.pagos.sei.impl.PagosSEIImpl;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPagosView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagosPendientesView;

/**
 * The Class PagosSEITest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { PagosSEITestConfiguration.class })
public class PagosSEIIT extends AbstractSEITest {

    /** The pagos manager. */
    @Autowired
    private PagosManager pagosManager;

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class PagosSEITestConfiguration.
     */
    @Configuration
    public static class PagosSEITestConfiguration {

        /**
         * Pagos manager.
         *
         * @return the pagos manager
         */
        @Bean
        public PagosManager pagosManager() {
            return Mockito.mock(PagosManager.class);
        }

        /**
         * Pago SEI.
         *
         * @return the pagos SEI
         */
        @Bean(name = "pagosSEI")
        public PagosSEI pagoSEI() {
            return new PagosSEIImpl();
        }
    }

    /**
     * Gets the service bean to test.
     *
     * @return the service bean to test
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("pagosSEI");
    }

    /**
     * Tests pagosManager when gets the pagos pendientes.
     *
     * @return the pagos pendientes
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getPagosPendientes() {
        Respuesta<PagosPendientesView> respManager = new Respuesta<PagosPendientesView>();
        PagoPendienteView pagoPendiente = new PagoPendienteView(new String("33456"));
        pagoPendiente.setNombreEmpresa("GCBA ABL");
        pagoPendiente.setIdentificacionFactura("ABL Mama");
        pagoPendiente.setCodigoClienteEmpresa("51007121009");
        pagoPendiente.setImporte("172,3");
        pagoPendiente.setCodigoEmpresa("ABCD");
        pagoPendiente.setVencimiento("06/06/2016");
        pagoPendiente.setDatosAdicionales("ABL003447");
        pagoPendiente.setInformacionAdicional("info-adicional");
        PagosPendientesView pagos = new PagosPendientesView();
        pagos.addPagoConVencimiento(pagoPendiente);
        respManager.setRespuesta(pagos);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respManager.setRespuestaVacia(false);

        ConsultaPagosView consultaPagosView = new ConsultaPagosView();
        consultaPagosView.setAnio("0");
        consultaPagosView.setMes("0");
        Mockito.when(pagosManager.getPagosTotales(Matchers.any(ConsultaPagosView.class))).thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagos/obtenerPagosPendientes");
        Respuesta<PagoPendienteView> respuestaSEI = new Respuesta<PagoPendienteView>();
        respuestaSEI.setEstadoRespuesta(EstadoRespuesta.OK);

        respuestaSEI = client.post(consultaPagosView, Respuesta.class);

        Assert.assertNotNull(respuestaSEI);
    }

    /**
     * Solicitar stop debit.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void solicitarStopDebit() {
        Respuesta<PagoPendienteView> respManager = new Respuesta<PagoPendienteView>();
        PagoPendienteView pagoPendiente = new PagoPendienteView(new String("33456"));
        pagoPendiente.setNombreEmpresa("GCBA ABL");
        pagoPendiente.setIdentificacionFactura("ABL Mama");
        pagoPendiente.setCodigoClienteEmpresa("51007121009");
        pagoPendiente.setImporte("172,3");
        pagoPendiente.setCodigoEmpresa("ABCD");
        pagoPendiente.setVencimiento("06/06/2016");
        pagoPendiente.setDatosAdicionales("ABL003447");
        pagoPendiente.setInformacionAdicional("info-adicional");
        respManager.setId(99239900L);
        respManager.setRespuestaVacia(false);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(pagosManager.solicitarStopDebit(Matchers.any(PagoPendienteView.class))).thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagos/solicitarStopDebit");
        Respuesta<PagosPendientesView> respSEI = new Respuesta<PagosPendientesView>();
        respSEI.setEstadoRespuesta(EstadoRespuesta.OK);
        respSEI.setId(10000L);
        respSEI.setItemMensajeRespuesta(null);
        Respuesta<String> retorno = client.post(pagoPendiente, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Ejecutar stop debit.
     *
     * @return the respuesta
     */
    // TODO: Revisar este test luego del cambio del constructor de
    // PagoPendienteView
    @Ignore
    @SuppressWarnings("unchecked")
    @Test
    public void ejecutarStopDebit() {
        Respuesta<PagoPendienteView> respManager = new Respuesta<PagoPendienteView>();

        respManager.setId(1L);
        respManager.setRespuestaVacia(false);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);

        PagoPendienteView unPagoPendiente = new PagoPendienteView("");
        unPagoPendiente.setNumeroComprobante("577000");
        unPagoPendiente.setCodigoEmpresa("AYSA");
        unPagoPendiente.setNombreEmpresa("AYSA");
        unPagoPendiente.setCodigoClienteEmpresa("0000004848");
        ;
        unPagoPendiente.setVencimiento("30/07/2016");
        unPagoPendiente.setImporte("150,50");
        unPagoPendiente.setImporteUSS("0,00");
        unPagoPendiente.setMoneda("$");
        unPagoPendiente.setPagoAutomatico(true);
        unPagoPendiente.setIdentificacionFactura("10536148537");
        unPagoPendiente.setTipoPago("SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA");
        unPagoPendiente.setTipoPagoDescripcion("Pago automatico de Pagomiscuentas");
        unPagoPendiente.setEditable(false);
        unPagoPendiente.setId(null);
        unPagoPendiente.setTooltip("");
        unPagoPendiente.setDatosAdicionales("");
        unPagoPendiente.setInformacionAdicional("");
        unPagoPendiente.setBotonHabilitado(false);
        respManager.setRespuesta(unPagoPendiente);

        Mockito.when(this.pagosManager.ejecutarStopDebit(Matchers.any(PagoPendienteView.class)))
                .thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagos/ejecutarStopDebit");

        Respuesta<PagoPendienteView> respuestaSEI = new Respuesta<PagoPendienteView>();
        PagoPendienteView pagoPendiente = new PagoPendienteView(new String("-7540331009235550000"));
        pagoPendiente.setNumeroComprobante("1234577");
        pagoPendiente.setCodigoEmpresa("AYSA");
        pagoPendiente.setNombreEmpresa("AYSA");
        pagoPendiente.setCodigoClienteEmpresa("0000004848");
        ;
        pagoPendiente.setVencimiento("30/07/2016");
        pagoPendiente.setImporte("150,50");
        pagoPendiente.setImporteUSS("0,00");
        pagoPendiente.setMoneda("$");
        pagoPendiente.setPagoAutomatico(true);
        pagoPendiente.setIdentificacionFactura("10536148537");
        pagoPendiente.setTipoPago("SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA");
        pagoPendiente.setTipoPagoDescripcion("Pago automatico de Pagomiscuentas");
        pagoPendiente.setEditable(false);
        pagoPendiente.setId(null);
        pagoPendiente.setTooltip("");
        pagoPendiente.setDatosAdicionales("");
        pagoPendiente.setInformacionAdicional("");
        pagoPendiente.setBotonHabilitado(false);
        IdentificacionCuentaView medioDePago = new IdentificacionCuentaView();
        medioDePago.setId(null);
        medioDePago.setNumeroDeCuenta("000-063917/0");
        medioDePago.setTipoDeCuenta("Cuenta Corriente en Pesos");
        medioDePago.setAliasDeCuenta("t-banco");
        pagoPendiente.setMedioDePago(medioDePago);
        respuestaSEI.setRespuesta(pagoPendiente);
        respuestaSEI.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaSEI.setRespuestaVacia(false);

        respuestaSEI = client.post(pagoPendiente, Respuesta.class);
        Assert.assertNotNull(respuestaSEI);
    }

    /**
     * Alta informacion adicional.
     *
     * @return the respuesta
     */
    @SuppressWarnings("unchecked")
    @Test
    public void altaInformacionAdicional() {
        Respuesta<Boolean> respManager = new Respuesta<Boolean>();
        PagoPendienteView pagoPendiente = new PagoPendienteView(new String("33456"));
        pagoPendiente.setNombreEmpresa("GCBA ABL");
        pagoPendiente.setIdentificacionFactura("ABL Mama");
        pagoPendiente.setCodigoClienteEmpresa("51007121009");
        pagoPendiente.setImporte("172,3");
        pagoPendiente.setCodigoEmpresa("ABCD");
        pagoPendiente.setVencimiento("06/06/2016");
        pagoPendiente.setDatosAdicionales("ABL003447");
        pagoPendiente.setInformacionAdicional("info-adicional");
        respManager.setId(99239900L);
        respManager.setRespuestaVacia(false);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(this.pagosManager.altaDeInformacionAdicional(Matchers.any(PagoPendienteView.class)))
                .thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagos/altaInformacionAdicional");

        Respuesta<PagoPendienteView> respuestaSEI = new Respuesta<PagoPendienteView>();

        respuestaSEI.setRespuesta(null);
        respuestaSEI.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaSEI.setRespuestaVacia(false);

        Respuesta<String> retorno = client.post(pagoPendiente, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Inicio de modificar adhesion a un pago automatico. Estadisticas y
     * sesion-contador. DTFs: 14368, 10281
     */
    @SuppressWarnings("unchecked")
    @Test
    public void solicitarAdhesionPagoAuto() {
        Respuesta<PagoPendienteView> respManager = new Respuesta<PagoPendienteView>();
        respManager.setId(99239900L);
        respManager.setRespuestaVacia(false);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        PagoPendienteView pagoPendiente = new PagoPendienteView(new String("33456"));
        pagoPendiente.setNombreEmpresa("GCBA ABL");
        pagoPendiente.setIdentificacionFactura("ABL Mama");
        pagoPendiente.setCodigoClienteEmpresa("51007121009");
        pagoPendiente.setImporte("172,3");
        pagoPendiente.setCodigoEmpresa("ABCD");
        pagoPendiente.setVencimiento("06/06/2016");
        pagoPendiente.setDatosAdicionales("ABL003447");
        pagoPendiente.setInformacionAdicional("info-adicional");

        Mockito.when(this.pagosManager.solicitarAdhesionPagoAuto(Matchers.any(PagoPendienteView.class)))
                .thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagos/solicitarModificarAdhesionPagoAuto");

        Respuesta<PagoPendienteView> respuestaSEI = new Respuesta<PagoPendienteView>();
        respuestaSEI.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaSEI.setId(10000L);
        respuestaSEI.setRespuestaVacia(false);

        Respuesta<String> retorno = client.post(pagoPendiente, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Modificar adhesion a un pago automatico. cambia monto limite y cuenta de
     * extraccion. pagoPendienteView representa una adhesion. DTFs: 14368, 10281
     */
    @SuppressWarnings("unchecked")
    @Test
    public void modificarAdhesionPagoAuto() {
        // Respuesta<Pago> respManager = new Respuesta<Boolean>();
        // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        // Mockito.when(this.pagosManager.modificarAdhesionPagoAuto(Matchers.any(PagoPendienteView.class))).thenReturn(respManager);
        // WebClient client = getWebClient();
        // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        // client.path("/modificarAdhesionPagoAuto");
        // Respuesta<PagoPendienteView> respuestaSEI = new
        // Respuesta<PagoPendienteView>();
        // PagoPendienteView pagoPendiente = new PagoPendienteView(new
        // String("-7540331009235550000"));
        // pagoPendiente.setNumeroComprobante("1234577");
        // pagoPendiente.setCodigoEmpresa("AYSA");
        // pagoPendiente.setNombreEmpresa("AYSA");
        // pagoPendiente.setCodigoClienteEmpresa("0000004848");;
        // pagoPendiente.setVencimiento("30/07/2016");
        // pagoPendiente.setImporte("150,50");
        // pagoPendiente.setImporteUSS("0,00");
        // pagoPendiente.setMoneda("$");
        // pagoPendiente.setPagoAutomatico(true);
        // pagoPendiente.setIdentificacionFactura("10536148537");
        // pagoPendiente.setTipoPago("SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA");
        // pagoPendiente.setTipoPagoDescripcion("Pago automatico de
        // Pagomiscuentas");
        // pagoPendiente.setEditable(false);
        // pagoPendiente.setId(null);
        // pagoPendiente.setTooltip("");
        // pagoPendiente.setDatosAdicionales("");
        // pagoPendiente.setInformacionAdicional("");
        // pagoPendiente.setBotonHabilitado(false);
        // IdentificacionCuentaView medioDePago = new
        // IdentificacionCuentaView();
        // medioDePago.setId(null);
        // medioDePago.setNumeroDeCuenta("000-063917/0");
        // medioDePago.setTipoDeCuenta("Cuenta Corriente en Pesos");
        // medioDePago.setAliasDeCuenta("t-banco");
        // pagoPendiente.setMedioDePago(medioDePago);
        // respuestaSEI.setRespuesta(pagoPendiente);
        // respuestaSEI.setEstadoRespuesta(EstadoRespuesta.OK);
        // respuestaSEI.setRespuestaVacia(false);
        //
        // respuestaSEI.setEstadoRespuesta(EstadoRespuesta.OK);
        // Respuesta<String> retorno = client.post(respuestaSEI,
        // Respuesta.class);
        // Assert.assertNotNull(retorno);
    }

    /**
     * Tests PagosManager ejecucion baja de adhesion de Debito Auto.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void ejecucionBajaAdhesionDebitoAutomaticoOK() {
        Respuesta<PagoPendienteView> respManager = new Respuesta<PagoPendienteView>();
        PagoPendienteView pagoPendiente = new PagoPendienteView(new String("33456"));
        pagoPendiente.setNombreEmpresa("GCBA ABL");
        pagoPendiente.setIdentificacionFactura("ABL Mama");
        pagoPendiente.setCodigoClienteEmpresa("51007121009");
        pagoPendiente.setImporte("172,3");
        pagoPendiente.setCodigoEmpresa("ABCD");
        pagoPendiente.setVencimiento("06/06/2016");
        pagoPendiente.setDatosAdicionales("ABL003447");
        pagoPendiente.setInformacionAdicional("info-adicional");
        respManager.setRespuesta(pagoPendiente);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respManager.setRespuestaVacia(false);

        ConsultaPagosView consultaPagosView = new ConsultaPagosView();
        Mockito.when(pagosManager.ejecutarBajaAdhesion(Matchers.any(PagoPendienteView.class))).thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagos/ejecutarBajaAdhesionDebitoAutomatico");
        Respuesta<PagoPendienteView> respuestaSEI = new Respuesta<PagoPendienteView>();
        respuestaSEI.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<PagoPendienteView> retorno = client.post(consultaPagosView, Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Ver detalle test.
     */
    @Ignore
    @Test
    public void verDetalleTest() {

        // PagoPendienteView input output
        PagoPendienteView unPagoPendiente = new PagoPendienteView("832163120110033379");
        unPagoPendiente.setCodigoEmpresa("30500143297");
        unPagoPendiente.setNombreEmpresa("ACA SEGUROS");
        unPagoPendiente.setCodigoClienteEmpresa("12341234123");
        ;
        unPagoPendiente.setVencimiento("29/08/2016");
        unPagoPendiente.setImporte("230,00");
        unPagoPendiente.setImporteUSS("0,00");
        unPagoPendiente.setMoneda("$");
        unPagoPendiente.setPagoAutomatico(true);
        unPagoPendiente.setIdentificacionFactura("");
        unPagoPendiente.setTipoPago("SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA");
        unPagoPendiente.setTipoPagoDescripcion("Débito Automático en Cuenta Pago Total");
        unPagoPendiente.setEditable(false);
        unPagoPendiente.setIcono("a");
        unPagoPendiente.setTooltip("Este pago se debitará automáticamente todos los meses");
        unPagoPendiente.setDatosAdicionales("");
        unPagoPendiente.setInformacionAdicional("");
        unPagoPendiente.setBotonHabilitado(false);
        IdentificacionCuentaView medioDePago = new IdentificacionCuentaView();
        medioDePago.setAliasDeCuenta("Con alias");
        medioDePago.setNumeroDeCuenta("033-361253/2");
        medioDePago.setTipoDeCuenta("Cuenta única");
        unPagoPendiente.setMedioDePago(medioDePago);
        unPagoPendiente.setImporteLimiteAdhesion("700,00");

        // Mock respuesta
        Respuesta<PagoPendienteView> respManager = new Respuesta<PagoPendienteView>();
        respManager.setRespuestaVacia(false);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respManager.setRespuesta(unPagoPendiente);

        Mockito.when(this.pagosManager.verDetalle(Matchers.any(PagoPendienteView.class))).thenReturn(respManager);

        // Ejecución
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagos/verDetalle");

        Respuesta<PagoPendienteView> respuestaSEI = client.post(unPagoPendiente, Respuesta.class);
        Assert.assertNotNull(respuestaSEI);
        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaSEI.getEstadoRespuesta()));
        Assert.assertNotNull(respuestaSEI.getRespuesta());
        Assert.assertTrue(unPagoPendiente.getId().equals(respManager.getRespuesta().getId()));

    }

    /**
     * Solicitar adhesion pago automatico.
     */
    @Test
    public void solicitarAdhesionPagoAutomatico() {
        Respuesta<PagoPendienteView> respManager = new Respuesta<PagoPendienteView>();
        respManager.setId(99239900L);
        respManager.setRespuestaVacia(false);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        PagoPendienteView pagoPendiente = new PagoPendienteView(new String("33456"));
        pagoPendiente.setNombreEmpresa("GCBA ABL");
        pagoPendiente.setIdentificacionFactura("ABL Mama");
        pagoPendiente.setCodigoClienteEmpresa("51007121009");
        pagoPendiente.setImporte("172,3");
        pagoPendiente.setCodigoEmpresa("ABCD");
        pagoPendiente.setVencimiento("06/06/2016");
        pagoPendiente.setDatosAdicionales("ABL003447");
        pagoPendiente.setInformacionAdicional("info-adicional");
        addSleepTime(5000);
        Mockito.when(this.pagosManager.solicitarAdhesionPagoAutomatico(Matchers.any(PagoPendienteView.class)))
                .thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagos/solicitarAdhesionPagoAutomatico");

        Respuesta<PagoPendienteView> respuestaSEI = new Respuesta<PagoPendienteView>();
        respuestaSEI.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaSEI.setId(10000L);
        respuestaSEI.setRespuestaVacia(false);

        Respuesta<String> retorno = client.post(pagoPendiente, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Solicitar adhesion debito automatico.
     */
    @Test
    public void solicitarAdhesionDebitoAutomatico() {
        Respuesta<PagoPendienteView> respManager = new Respuesta<PagoPendienteView>();
        respManager.setId(99239900L);
        respManager.setRespuestaVacia(false);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        PagoPendienteView pagoPendiente = new PagoPendienteView(new String("33456"));
        pagoPendiente.setNombreEmpresa("GCBA ABL");
        pagoPendiente.setIdentificacionFactura("ABL Mama");
        pagoPendiente.setCodigoClienteEmpresa("51007121009");
        pagoPendiente.setImporte("172,3");
        pagoPendiente.setCodigoEmpresa("ABCD");
        pagoPendiente.setVencimiento("06/06/2016");
        pagoPendiente.setDatosAdicionales("ABL003447");
        pagoPendiente.setInformacionAdicional("info-adicional");

        Mockito.when(this.pagosManager.solicitarAdhesionDebitoAutomatico(Matchers.any(PagoPendienteView.class)))
                .thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagos/solicitarAdhesionDebitoAutomatico");

        Respuesta<PagoPendienteView> respuestaSEI = new Respuesta<PagoPendienteView>();
        respuestaSEI.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaSEI.setId(10000L);
        respuestaSEI.setRespuestaVacia(false);

        Respuesta<String> retorno = client.post(pagoPendiente, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

}
