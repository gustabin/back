package ar.com.santanderrio.obp.servicios.nuevopago.sei;

import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuit.entities.Cuit;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager;
import ar.com.santanderrio.obp.servicios.nuevopago.mock.MedioPagoSelectionViewMock;
import ar.com.santanderrio.obp.servicios.nuevopago.mock.MedioPagoViewMock;
import ar.com.santanderrio.obp.servicios.nuevopago.mock.NuevoPagoMock;
import ar.com.santanderrio.obp.servicios.nuevopago.sei.impl.NuevoPagoSEIImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionRecargaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaConfiguracionView;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;

/**
 * The Class NuevoPagoSEITest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ar.com.santanderrio.obp.servicios.nuevopago.sei.NuevoPagoSEIIT.NuevoPagoSEITestConfiguration.class })
public class NuevoPagoSEIIT extends AbstractSEITest {

    /** The nuevo pago manager. */
    @Autowired
    private NuevoPagoManager nuevoPagoManager;

    /** The respuesta factory. */
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("nuevoPagoSEI");
    }

    /**
     * The Class NuevoPagoSEITestConfiguration.
     */
    @Configuration
    public static class NuevoPagoSEITestConfiguration {

        /**
         * Nuevo pago SEI.
         *
         * @return the nuevo pago SEI
         */
        @Bean(name = "nuevoPagoSEI")
        public NuevoPagoSEI NuevoPagoSEI() {
            return new NuevoPagoSEIImpl();
        }

        /**
         * Nuevo pago manager.
         *
         * @return the nuevo pago manager
         */
        @Bean
        public NuevoPagoManager nuevoPagoManager() {
            return Mockito.mock(NuevoPagoManager.class);
        }

        /**
         * Sesion cliente.
         *
         * @return the sesion cliente
         */
        @Bean
        public SesionCliente sesionCliente() {
            return Mockito.mock(SesionCliente.class);
        }

        /**
         * Sesion parametros.
         *
         * @return the sesion parametros
         */
        @Bean
        public SesionParametros sesionParametros() {
            return Mockito.mock(SesionParametros.class);
        }

        /**
         * Rsa manager.
         *
         * @return the rsa manager
         */
        @Bean
        public RsaManager rsaManager() {
            return Mockito.mock(RsaManager.class);
        }

    }

    /**
     * Confirmar pago puntual OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerPagosOK() {
        // Given
        Respuesta<MedioPagoSelectionView> resp = respuestaFactory.crearRespuestaOk(MedioPagoSelectionView.class,
                MedioPagoSelectionViewMock.completarInfo());

        // When
        when(nuevoPagoManager.obtenerFacturas(Matchers.any(MedioPagoView.class)))
                .thenReturn(resp);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/nuevoPago/obtenerPagos");

        // Then
        Respuesta<NuevoPago> respuesta = client.post(MedioPagoViewMock.completarInfo("RECARGA DE CELULAR",
                "Recarga Claro", "RECL", TipoNuevoPagoEnum.CELULAR_RECARGA.getId()), Respuesta.class);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Validar cuit OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void validarCuitOK() {
        // Given
        Respuesta<String> resp = respuestaFactory.crearRespuestaOk(String.class, "23-12345678-9");

        // When
        when(nuevoPagoManager.validarCuit(Matchers.anyString())).thenReturn(resp);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/nuevoPago/validarCuit");

        // Then
        Respuesta<NuevoPago> respuesta = client.post(new Cuit("23123456789"), Respuesta.class);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener cuentas OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerCuentasOK() {
        // Given
        Respuesta<MedioPagoSelectionView> resp = respuestaFactory.crearRespuestaOk(MedioPagoSelectionView.class,
                MedioPagoSelectionViewMock.completarInfo());

        // When
        when(nuevoPagoManager.obtenerCuentas(Matchers.any(ConsultaConfiguracionView.class))).thenReturn(resp);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/nuevoPago/obtenerCuentas");

        // Then
        Respuesta<NuevoPago> respuesta = client.post(new ConsultaConfiguracionView(), Respuesta.class);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Grabar estadistica recarga ingreso numero.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void grabarEstadisticaRecargaIngresoNumero() {
        // When
        when(nuevoPagoManager.grabarEstadisticaRecargaIngresoNumero(Matchers.any(PuntoDeAccesoView.class)))
                .thenReturn(new Respuesta<Void>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/nuevoPago/grabarEstadisticaRecargaIngresoNumero");
        // Then
        Respuesta<Void> respuesta = client.post(new PuntoDeAccesoView(), Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener configuracion recarga.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerConfiguracionRecarga() {
        // When
        when(nuevoPagoManager.obtenerConfiguracionRecarga(Matchers.any(ConsultaConfiguracionView.class)))
                .thenReturn(respuestaFactory.crearRespuestaOk(ConfiguracionRecargaView.class,
                        new ConfiguracionRecargaView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/nuevoPago/obtenerConfiguracionRecarga");
        // Then
        Respuesta<ConfiguracionRecargaView> respuesta = client.post(new ConsultaConfiguracionView(),
                Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Confirmar pago puntual OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void confirmarPagoPuntualOK() {
        // Given
        Respuesta<NuevoPago> resp = respuestaFactory.crearRespuestaOk(NuevoPago.class,
                NuevoPagoMock.completarInfoConDesafio(Boolean.FALSE, "true"));

        // When
        when(nuevoPagoManager.pagoPuntual(Matchers.any(NuevoPago.class))).thenReturn(resp);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/nuevoPago/confirmarPagoPuntual");

        // Then
        Respuesta<NuevoPago> respuesta = client.post(NuevoPagoMock.completarInfoConDesafio(Boolean.FALSE, "false"),
                Respuesta.class);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    /**
     * Grabar estadistica recarga ingreso numero.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void verificarFactoresAutenticacion() {
        // When
        when(nuevoPagoManager.verificarFactoresAutenticacion())
                .thenReturn(new Respuesta<Boolean>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/nuevoPago/verificarFactoresAutenticacion");
        // Then
        Respuesta<Boolean> respuesta = client.post(null, Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

}
