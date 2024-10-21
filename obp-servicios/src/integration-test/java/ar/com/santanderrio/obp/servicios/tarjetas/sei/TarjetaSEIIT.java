/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.sei;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Ignore;
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
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.entities.OtroImportePesosEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ResumenAnteriorBO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDetalleDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionLineaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.PagoTarjetaInfoViewMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReintentarMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimosConsumosDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.sei.impl.TarjetaSEIImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ResumenAnteriorView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ResumenAnteriorViewResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaPagoSeleccionView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaSeleccionada;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.AdhesionTarjetaDebitoAutomaticoManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.CancelarStopDebitTarjetaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ConsultaFinanciacionManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ConsumoPendienteManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.CuotasPendienteManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.DescargaResumenAnteriorManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.FinanciacionTarjetaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.InicioTarjetasManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.PagosTarjetaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ResumenAnteriorManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.UltimoResumenManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.UltimosConsumosManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsultaFinanciacionView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumoPendienteView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosPendientesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.DetalleTarjetaPagoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LimitesYDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LineaDetalleConsumoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.PagoTarjetaInfoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetasView;

/**
 * Created by pablo.martin.gore on 8/5/2016.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEIIT.TarjetaSEITestConfiguration.class })
@Ignore
public class TarjetaSEIIT extends AbstractSEITest {

    /** The Constant NRO_CUENTA. */
    private static final String NRO_CUENTA = "123456789";

    /** The tarjeta manager. */
    @Autowired
    private InicioTarjetasManager inicioTarjetaManager;

    /** The ultimos consumos manager. */
    @Autowired
    private UltimosConsumosManager ultimosConsumosManager;

    /** The ultimo resumen manager. */
    @SuppressWarnings("unused")
    @Autowired
    private UltimoResumenManager ultimoResumenManager;

    /** The consumo pendiente manager. */
    @Autowired
    private ConsumoPendienteManager consumoPendienteManager;

    /** The consulta financiacion manager. */
    @SuppressWarnings("unused")
    @Autowired
    private ConsultaFinanciacionManager consultaFinanciacionManager;

    /** The pagos tarjeta manager. */
    @Autowired
    private PagosTarjetaManager pagosTarjetaManager;

    /** The cuotas pendiente manager. */
    @Autowired
    private CuotasPendienteManager cuotasPendienteManager;

    /** The tarjeta ResumenAnteriorManager. */
    @Autowired
    private ResumenAnteriorManager resumenAnteriorManager;

    /** The on demand puntual resumen manager. */
    @Autowired
    private DescargaResumenAnteriorManager onDemandPuntualResumenManager;

    /** The adhesion tarjeta debito automatico manager. */
    @Autowired
    @SuppressWarnings("unused")
    private AdhesionTarjetaDebitoAutomaticoManager adhesionTarjetaDebitoAutomaticoManager;

    /** The cancelar stop debit tarjeta manager. */
    @Autowired
    @SuppressWarnings("unused")
    private CancelarStopDebitTarjetaManager cancelarStopDebitTarjetaManager;

    /** The tarjeta manager. */
    @Autowired
    private FinanciacionTarjetaManager financiacionTarjetasManager;

    /** The respuesta factory. */
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Inits the.
     */
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("tarjetaSEI");
    }

    /**
     * The Class TarjetaSEITestConfiguration.
     */
    @Configuration
    // @ComponentScan({ "ar.com.santanderrio.obp.servicios.tarjetas.sei" })
    public static class TarjetaSEITestConfiguration {

        /**
         * Tarjeta SEI.
         *
         * @return the tarjeta SEI
         */
        @Bean(name = "tarjetaSEI")
        public TarjetaSEI tarjetaSEI() {
            return new TarjetaSEIImpl();
        }

        /**
         * Tarjeta manager.
         *
         * @return the tarjeta manager
         */
        @Bean
        public InicioTarjetasManager tarjetaManager() {
            return Mockito.mock(InicioTarjetasManager.class);
        }

        /**
         * Ultimos consumos manager.
         *
         * @return the ultimos consumos manager
         */
        @Bean
        public UltimosConsumosManager ultimosConsumosManager() {
            return Mockito.mock(UltimosConsumosManager.class);
        }

        /**
         * Ultimo resumen manager.
         *
         * @return the ultimo resumen manager
         */
        @Bean
        public UltimoResumenManager ultimoResumenManager() {
            return Mockito.mock(UltimoResumenManager.class);
        }

        /**
         * Consumo pendiente manager.
         *
         * @return the consumo pendiente manager
         */
        @Bean
        public ConsumoPendienteManager consumoPendienteManager() {
            return Mockito.mock(ConsumoPendienteManager.class);
        }

        /**
         * Consulta financiacion manager.
         *
         * @return the consulta financiacion manager
         */
        @Bean
        public ConsultaFinanciacionManager consultaFinanciacionManager() {
            return Mockito.mock(ConsultaFinanciacionManager.class);
        }

        /**
         * Pagos tarjeta manager.
         *
         * @return the pagos tarjeta manager
         */
        @Bean
        public PagosTarjetaManager pagosTarjetaManager() {
            return Mockito.mock(PagosTarjetaManager.class);
        }

        /**
         * Cuenta manager.
         *
         * @return the cuenta manager
         */
        @Bean
        public CuentaManager cuentaManager() {
            return Mockito.mock(CuentaManager.class);
        }

        /**
         * Respuesta factory.
         *
         * @return the respuesta factory
         */
        @Bean
        public RespuestaFactory respuestaFactory() {
            return Mockito.mock(RespuestaFactory.class);
        }

        /**
         * Mensaje DAO.
         *
         * @return the mensaje DAO
         */
        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
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
         * Sesion tarjetas util.
         *
         * @return the sesion tarjetas util
         */
        @Bean
        public SesionTarjetas sesionTarjetas() {
            return Mockito.mock(SesionTarjetas.class);
        }

        /**
         * Cuotas pendiente manager.
         *
         * @return the cuotas pendiente manager
         */
        @Bean
        public CuotasPendienteManager cuotasPendienteManager() {
            return Mockito.mock(CuotasPendienteManager.class);
        }

        /**
         * Resumen anterior manager.
         *
         * @return the resumen anterior manager
         */
        @Bean
        public ResumenAnteriorManager resumenAnteriorManager() {
            return Mockito.mock(ResumenAnteriorManager.class);
        }

        /**
         * Resumen anterior BO.
         *
         * @return the resumen anterior BO
         */
        @Bean
        public ResumenAnteriorBO resumenAnteriorBO() {
            return Mockito.mock(ResumenAnteriorBO.class);
        }

        /**
         * Estadistica manager.
         *
         * @return the estadistica manager
         */
        @Bean
        public EstadisticaManager estadisticaManager() {
            return Mockito.mock(EstadisticaManager.class);
        }

        /**
         * On demand puntual resumen manager.
         *
         * @return the on demand puntual resumen manager
         */
        @Bean
        public DescargaResumenAnteriorManager onDemandPuntualResumenManager() {
            return Mockito.mock(DescargaResumenAnteriorManager.class);
        }

        /**
         * Adhesion tarjeta debito automatico manager.
         *
         * @return the adhesion tarjeta debito automatico manager
         */
        @Bean
        public AdhesionTarjetaDebitoAutomaticoManager adhesionTarjetaDebitoAutomaticoManager() {
            return Mockito.mock(AdhesionTarjetaDebitoAutomaticoManager.class);
        }

        /**
         * Cancelar stop debit tarjeta manager.
         *
         * @return the cancelar stop debit tarjeta manager
         */
        @Bean
        public CancelarStopDebitTarjetaManager cancelarStopDebitTarjetaManager() {
            return Mockito.mock(CancelarStopDebitTarjetaManager.class);
        }


        /**
         * Financiacion tarjetas manager.
         *
         * @return the financiacion tarjeta manager
         */
        @Bean
        public FinanciacionTarjetaManager financiacionTarjetasManager() {
            return Mockito.mock(FinanciacionTarjetaManager.class);
        }
    }

    /**
     * Obtiene las tarjetas OK.
     *
     * @return the tarjetas ok
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getTarjetasWithPaamOK() {
        Mockito.when(inicioTarjetaManager.getTarjetas(Matchers.any(TarjetaSeleccionada.class)))
                .thenReturn(respuestaFactory.crearRespuestaOk(TarjetasView.class, generarTarjetaView()));

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/obtenerTarjetas");
        Respuesta<TarjetaActivaView> retorno = client.post(null, Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Gets the tarjetas OK.
     *
     * @return the tarjetas OK
     */
    @Test
    public void getTarjetasOK() {
        Mockito.when(inicioTarjetaManager.getTarjetas(Matchers.any(TarjetaSeleccionada.class)))
                .thenReturn(respuestaFactory.crearRespuestaOk(TarjetasView.class, generarTarjetaView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/obtenerTarjetas");
        @SuppressWarnings("unchecked")
        Respuesta<TarjetaActivaView> retorno = client.post(null, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Actualizar alias OK.
     */
    @SuppressWarnings("unchecked")
    // @Test
    public void actualizarAliasOK() {
        Mockito.when(inicioTarjetaManager.actualizarAlias(Matchers.any(String.class), Matchers.any(String.class)))
                .thenReturn(generarRespuestaVoidOK());

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/actualizarAliasTarjetas");
        Respuesta<Void> retorno = client.post(generarTarjetasView(), Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Actualizar tarjeta favorita OK.
     */
    @SuppressWarnings("unchecked")
    // @Test
    public void actualizarTarjetaFavoritaOK() {
        Respuesta<Void> respuesta = generarRespuestaVoidOK();
        Mockito.when(inicioTarjetaManager.actualizarTarjetaFavorita(Matchers.any(String.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/actualizarFavoritosTarjeta");
        Respuesta<Void> retorno = client.post(NRO_CUENTA, Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Obtener ultimos consumos OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerUltimosConsumosOK() {
        Respuesta<ConsumosView> respuesta = respuestaFactory.crearRespuestaOk(ConsumosView.class,
                generarUltimosConsumosView());
        Mockito.when(ultimosConsumosManager.obtenerUltimosConsumos(Matchers.any(String.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/ultimosConsumos");
        Respuesta<ConsumosView> retorno = client.post(generarTarjetasView(), Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Obtener consumos pendientes OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerConsumosPendientesOK() {
        Respuesta<ConsumosPendientesView> respuesta = respuestaFactory.crearRespuestaOk(ConsumosPendientesView.class,
                generarConsumoPendienteView());
        Mockito.when(consumoPendienteManager.obtenerConsumoPendiente()).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/consumosPendientes");
        Respuesta<ConsumosPendientesView> retorno = client.post(generarTarjetasView(), Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals("2", respuesta.getRespuesta().getCantidadConsumos());
        Assert.assertEquals("VISA", respuesta.getRespuesta().getConsumosPendientes().get(0).getMarca());
        Assert.assertEquals("AMEX", respuesta.getRespuesta().getConsumosPendientes().get(1).getMarca());
        Assert.assertEquals("1234567890123456", respuesta.getRespuesta().getConsumosPendientes().get(0).getNumero());

    }

    /**
     * Obtener consulta financiacion OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerConsultaFinanciacionOK() {
        @SuppressWarnings("unused")
        Respuesta<ConsultaFinanciacionView> respuesta = respuestaFactory
                .crearRespuestaOk(ConsultaFinanciacionView.class, generarConsultaFinanciacionView());
        // Mockito.when(consultaFinanciacionManager.obtenerConsultaFinanciacion(Matchers.any(String.class),
        // Matchers.any(String.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/consultaFinanciacion");
        Respuesta<ConsultaFinanciacionView> retorno = client.post(generarTarjetasView(), Respuesta.class);

        Assert.assertNotNull(retorno);
        // Assert.assertEquals(1,
        // respuesta.getRespuesta().getListaConsultaFinanciaciones().size());
    }

    /**
     * Obtener limites y disponibles OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerLimitesYDisponiblesOK() {
        Respuesta<LimitesYDisponiblesView> respuesta = respuestaFactory.crearRespuestaOk(LimitesYDisponiblesView.class,
                generarLimitesYDisponiblesView(generarLimitesYDisponiblesOK()));
        Mockito.when(inicioTarjetaManager.obtenerLimitesYDisponibles(Matchers.any(String.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/limitesDisponibles");
        Respuesta<LimitesYDisponiblesEntity> retorno = client.post(generarTarjetasView(), Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals("13.000,00", respuesta.getRespuesta().getSaldoDisponibleCompras());
        Assert.assertEquals("20.000,00", respuesta.getRespuesta().getSaldoDisponibleCuotas());
        Assert.assertEquals("30.000,00", respuesta.getRespuesta().getAdelantoEfectivoPesos());
    }

    /**
     * Obtener limites y disponibles ERROR.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerLimitesYDisponiblesERROR() {
        Respuesta<LimitesYDisponiblesView> respuesta = crearRespuestaErrorGenericoParaTest(
                crearListaItemsLimitesYDisponibles());
        Mockito.when(inicioTarjetaManager.obtenerLimitesYDisponibles(Matchers.any(String.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/limitesDisponibles");
        Respuesta<LimitesYDisponiblesEntity> retorno = client.post(generarTarjetasView(), Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Pago tarjeta desde ultimo resumen OK.
     */
    @Test
    public void pagoTarjetaDesdeUltimoResumenOK() {
        Respuesta<PagoTarjetaInfoView> respuesta = respuestaFactory.crearRespuestaOk(PagoTarjetaInfoView.class,
                crearPagoTarjetaInfoView());
        Mockito.when(pagosTarjetaManager.llamarEstadisticaUltimoResumenNuevoPago()).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/pagoTarjetaDesdeUltimoResumen");

        @SuppressWarnings("unchecked")
        Respuesta<PagoTarjetaInfoView> retorno = client.post(new Reintentar(), Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Pago tarjeta desde ultimo resumen OK.
     */
    @Test
    public void resumenesAnterioresDesdeUltimoResumenOK() {
        Respuesta<ResumenAnteriorViewResponse> respuesta = respuestaFactory
                .crearRespuestaOk(ResumenAnteriorViewResponse.class, new ResumenAnteriorViewResponse());
        Mockito.when(resumenAnteriorManager.obtenerResumenesAnteriores(Matchers.any(TarjetaActivaView.class)))
                .thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/resumenesAnterioresDesdeUltimoResumen");

        @SuppressWarnings("unchecked")
        Respuesta<PagoTarjetaInfoView> retorno = client.post(new TarjetaActivaView(), Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Crear pago tarjeta info view.
     *
     * @return the pago tarjeta info view
     */
    private PagoTarjetaInfoView crearPagoTarjetaInfoView() {
        PagoTarjetaInfoView res = new PagoTarjetaInfoView();
        res.setContratoAceptado(true);
        return res;
    }

    /**
     * Pagar OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void pagarOK() {
        OtroImportePesosEntity otroImporte = new OtroImportePesosEntity();
        otroImporte.setMonto("10");
        Respuesta<ComprobantePagoTarjeta> respuesta = respuestaFactory.crearRespuestaOk(ComprobantePagoTarjeta.class,
                generarComprobantePagoTarjeta(null, "4455656567", null));
        Mockito.when(pagosTarjetaManager.pagarTarjeta(Matchers.any(PagoTarjetaCreditoView.class),
                Matchers.any(Cliente.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/pagar");
        Respuesta<ComprobantePagoTarjeta> retorno = client.post(
                generarPagoTarjetaCreditoViewEnPesos(null, "1234567890123456", "2", otroImporte, "12/08/2016"),
                Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals("4455656567", respuesta.getRespuesta().getNroComprobante());
    }

    /**
     * Programar pago OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void programarPagoOK() {
        OtroImportePesosEntity otroImporte = new OtroImportePesosEntity();
        otroImporte.setMonto("10");
        Respuesta<ComprobantePagoTarjeta> respuesta = respuestaFactory.crearRespuestaOk(ComprobantePagoTarjeta.class,
                generarComprobantePagoTarjeta(null, "67664554", null));
        Mockito.when(pagosTarjetaManager.programarPago(Matchers.any(PagoTarjetaCreditoView.class),
                Matchers.any(Cliente.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/pagoProgramado");
        Respuesta<ComprobantePagoTarjeta> retorno = client.post(
                generarPagoTarjetaCreditoViewEnPesos(null, "1234567890123456", "2", otroImporte, "12/08/2016"),
                Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals("67664554", respuesta.getRespuesta().getNroComprobante());
    }

    /**
     * Confirmar aceptacion contrato pago programado OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void confirmarAceptacionContratoPagoProgramadoOK() {
        OtroImportePesosEntity otroImporte = new OtroImportePesosEntity();
        otroImporte.setMonto("10");
        Respuesta<String> respuesta = respuestaFactory.crearRespuestaOk(String.class, "Confirmacion");
        Mockito.when(pagosTarjetaManager.aceptacionContratoPagoProgramado(Matchers.any(Cliente.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/confirmarAceptacionContratoPagoProgramado");
        Respuesta<String> retorno = client.post(
                generarPagoTarjetaCreditoViewEnPesos(null, "1234567890123456", "2", otroImporte, "12/08/2016"),
                Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals("Confirmacion", respuesta.getRespuesta());
    }

    /**
     * Obtener cuotas pendientes OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerCuotasPendientesOK() {
        Respuesta<CuotasPendientesView> respuesta = respuestaFactory.crearRespuestaOk(CuotasPendientesView.class,
                CuotasPendientesMock.completarInfoCuotasPendientes());
        Mockito.when(cuotasPendienteManager.obtenerCuotasPendientes(Matchers.any(TarjetaActivaView.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/listarCuotasPendientes");
        Respuesta<CuotasPendientesView> retorno = client.post(generarTarjetasView(), Respuesta.class);

        Assert.assertNotNull(retorno);
        // Assert.assertEquals("1500",
        // respuesta.getRespuesta().getTotalCuotasPendientes());
    }

    /**
     * Generar pago tarjeta credito view en pesos.
     *
     * @param cbuPesos
     *            the cbu pesos
     * @param nroTarjeta
     *            the nro tarjeta
     * @param tipoPagoTC
     *            the tipo pago tc
     * @param importePagoPesos
     *            the importe pago pesos
     * @param fechaPagoProgramado
     *            the fecha pago programado
     * @return the pago tarjeta credito view
     */
    private PagoTarjetaCreditoView generarPagoTarjetaCreditoViewEnPesos(String cbuPesos, String nroTarjeta,
            String tipoPagoTC, OtroImportePesosEntity importePagoPesos, String fechaPagoProgramado) {
        PagoTarjetaCreditoView pagoTarjetaCreditoView = new PagoTarjetaCreditoView();
        pagoTarjetaCreditoView.setCbuPesos(cbuPesos);
        pagoTarjetaCreditoView.setNumeroTarjeta(nroTarjeta);
        pagoTarjetaCreditoView.setTipoPagoTC(tipoPagoTC);
        pagoTarjetaCreditoView.setImportePagoPesos(importePagoPesos);
        pagoTarjetaCreditoView.setFechaDePago(fechaPagoProgramado);
        return pagoTarjetaCreditoView;
    }

    /**
     * Generar comprobante pago tarjeta.
     *
     * @param comprobantePagoTarjeta
     *            the comprobante pago tarjeta
     * @param comprobantePagoProgramado
     *            the comprobante pago programado
     * @param contrato
     *            the contrato
     * @return the comprobante pago tarjeta
     */
    private ComprobantePagoTarjeta generarComprobantePagoTarjeta(String comprobantePagoTarjeta,
            String comprobantePagoProgramado, String contrato) {
        ComprobantePagoTarjeta comprobantePago = new ComprobantePagoTarjeta();
        comprobantePago.setNroComprobante(comprobantePagoProgramado);

        return comprobantePago;
    }

    /**
     * Generar ultimos consumos view.
     *
     * @return the ultimos consumos view
     */
    private ConsumosView generarUltimosConsumosView() {
        return new ConsumosView(generarConsumoTarjetaDTO(), false
        // , "Notificacion Consumos Pendientes", EstadoRespuesta.OK
        );
    }

    /**
     * Generar respuesta void OK.
     *
     * @return the respuesta
     */
    private Respuesta<Void> generarRespuestaVoidOK() {
        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(false);
        return respuesta;
    }

    /**
     * Generar tarjeta view.
     *
     * @return the tarjeta view
     */
    private TarjetasView generarTarjetaView() {
        TarjetasView tarjetaView = new TarjetasView();
        tarjetaView.setTarjetas(generarListaTarjetasVisaTitularYAmexAdicional());
        tarjetaView.setTarjetaSeleccionada(0);
        tarjetaView.setEstadoRespuestaConsumo(EstadoRespuesta.OK);
        tarjetaView.setConsumoTarjetaDefault(generarConsumoTarjetaDefult());
        return tarjetaView;
    }

    /**
     * Generar consumo tarjeta defult.
     *
     * @return the list
     */
    private UltimosConsumosDTO generarConsumoTarjetaDTO() {
        UltimosConsumosDTO dto = new UltimosConsumosDTO();
        List<ConsumoTarjetaDTO> tarjetaList = new ArrayList<ConsumoTarjetaDTO>();
        tarjetaList.add(generarConsumoTarjetaVisaTitular());
        dto.setUltimosConsumos(tarjetaList);
        dto.setMuestraTarjetasConCabecera(Boolean.FALSE);
        return dto;
    }

    /**
     * Generar consumo tarjeta defult.
     *
     * @return the list
     */
    private List<ConsumoTarjetaView> generarConsumoTarjetaDefult() {
        List<ConsumoTarjetaView> tarjetaList = new ArrayList<ConsumoTarjetaView>();
        tarjetaList.add(new ConsumoTarjetaView(generarConsumoTarjetaVisaTitular()));
        return tarjetaList;
    }

    /**
     * Generar consumo tarjeta para visa titular.
     *
     * @return the consumo tarjeta
     */
    private ConsumoTarjetaDTO generarConsumoTarjetaVisaTitular() {
        ConsumoTarjetaDTO consumo = new ConsumoTarjetaDTO();
        consumo.setMarca("VISA");
        consumo.setNumero("1234567890123456");
        consumo.setConsumoDolares(new BigDecimal(0));
        consumo.setConsumoPesos(new BigDecimal(25000.00));
        consumo.setIsTitular(true);
        consumo.setIsAdicional(false);
        consumo.setHasConsumoDolaresCero(true);
        consumo.setHasConsumoPesosCero(false);
        consumo.setHasError(false);
        consumo.setFechaDesde(null);
        consumo.setLineas(generarLineasConsumoVisaTitular());
        return consumo;
    }

    /**
     * Generar lineas de consumo para una visa titular.
     *
     * @return the list
     */
    private List<LineaDetalleConsumoTarjetaDTO> generarLineasConsumoVisaTitular() {
        List<LineaDetalleConsumoTarjetaDTO> lineasList = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        lineasList.add(generarLineaConsumo1VisaTitular());
        return lineasList;
    }

    /**
     * Generar linea de consumo1 para una visa titular.
     *
     * @return the linea detalle consumo tarjeta
     */
    private LineaDetalleConsumoTarjetaDTO generarLineaConsumo1VisaTitular() {
        LineaDetalleConsumoTarjetaDTO linea = new LineaDetalleConsumoTarjetaDTO();
        linea.setDescripcion("Rouge");
        linea.setCuota("01/02");
        linea.setCuotasCanceladas(1);
        linea.setCuotasTotales(2);
        linea.setTieneCuota(true);
        linea.setImportePesos(new BigDecimal(800.00));
        linea.setFecha(new Date());
        linea.setCodigoEstablecimiento("345678902");
        linea.setNroReferencia("12356564567");
        linea.setNroTarjeta("1234567890123456");
        return linea;
    }

    /**
     * Generar lista de tarjetas: 1visa titular y 1 amex adicional.
     *
     * @return the list
     */
    private List<TarjetaView> generarListaTarjetasVisaTitularYAmexAdicional() {
        List<TarjetaView> tarjetaList = new ArrayList<TarjetaView>();
        tarjetaList.add(generarTarjetaVisaTitularConSaldoUnificado());
        tarjetaList.add(generarTarjetaAmexAdicional());
        return tarjetaList;
    }

    /**
     * Generar tarjeta amex adicional.
     *
     * @return the tarjeta
     */
    private TarjetaView generarTarjetaAmexAdicional() {
        TarjetaView tarjeta = new TarjetaView();
        tarjeta.setMarca("AMEX");
        tarjeta.setNumero("3777567890123456");
        tarjeta.setNumeroCuenta("");
        tarjeta.setSaldoDisponibleCompras("5.000,00");
        tarjeta.setSaldoDisponibleCuotas("15.000,00");
        tarjeta.setMostrarSaldoEnCompras(true);
        tarjeta.setMostrarSaldoEnCuotas(true);
        tarjeta.setHasLimiteUnificado(false);
        tarjeta.setConsumoDolares("0");
        tarjeta.setConsumoPesos("30.000,00");
        tarjeta.setIsAdicional(true);
        tarjeta.setIsTitular(false);
        tarjeta.setIsFavorito(false);
        tarjeta.setFechaCierre("25/07/2016");
        tarjeta.setFechaVencimiento("02/08/2016");
        tarjeta.setIsRecargable(false);
        tarjeta.setHasAlias(false);
        tarjeta.setHasConsumoDolarCero(true);
        tarjeta.setHasConsumoPesosCero(false);
        tarjeta.setHasDisponibleComprasCero(false);
        tarjeta.setHasDisponibleCuotasCero(false);
        return tarjeta;
    }

    /**
     * Generar tarjeta visa titular con saldo unificado.
     *
     * @return the tarjeta
     */
    private TarjetaView generarTarjetaVisaTitularConSaldoUnificado() {
        TarjetaView tarjeta = new TarjetaView();
        tarjeta.setMarca("VISA");
        tarjeta.setNumero("1234567890123456");
        tarjeta.setNumeroCuenta("");
        tarjeta.setSaldoDisponibleCompras("13.000,00");
        tarjeta.setSaldoDisponibleCuotas("13.000,00");
        tarjeta.setMostrarSaldoEnCompras(true);
        tarjeta.setMostrarSaldoEnCuotas(false);
        tarjeta.setHasLimiteUnificado(true);
        tarjeta.setConsumoDolares("0");
        tarjeta.setConsumoPesos("25.000,00");
        tarjeta.setIsAdicional(false);
        tarjeta.setIsTitular(true);
        tarjeta.setIsFavorito(true);
        tarjeta.setFechaCierre("29/07/2016");
        tarjeta.setFechaVencimiento("12/08/2016");
        tarjeta.setIsRecargable(false);
        tarjeta.setHasAlias(false);
        tarjeta.setHasConsumoDolarCero(true);
        tarjeta.setHasConsumoPesosCero(false);
        tarjeta.setHasDisponibleComprasCero(false);
        tarjeta.setHasDisponibleCuotasCero(false);
        return tarjeta;
    }

    /**
     * Generar tarjetas view.
     *
     * @return the tarjetas view
     */
    private TarjetaActivaView generarTarjetasView() {
        TarjetaActivaView tarjetasView = new TarjetaActivaView();
        tarjetasView.setAlias("Mama");
        tarjetasView.setMarca("VISA");
        tarjetasView.setNumeroCuenta("123-445455/6");
        return tarjetasView;
    }

    /**
     * Generar consumo pendiente view.
     *
     * @return the consumo pendiente view
     */
    private ConsumosPendientesView generarConsumoPendienteView() {
        ConsumosPendientesView consumoPendienteView = new ConsumosPendientesView();
        consumoPendienteView.setCantidadConsumos("2");
        consumoPendienteView.setConsumosPendientes(generarListaConsumosPendientes());

        ConsumoPendienteView cView = new ConsumoPendienteView();
        cView.setMarca("VISA");
        cView.setNumero("1234567890123456");
        consumoPendienteView.setConsumoPendiente(cView);
        return consumoPendienteView;
    }

    /**
     * Generar lista de consumos pendientes.
     *
     * @return the list
     */
    private List<ConsumoPendienteView> generarListaConsumosPendientes() {
        List<ConsumoPendienteView> consumoPendienteList = new ArrayList<ConsumoPendienteView>();
        consumoPendienteList.add(generarConsumoPendientePesos("VISA", "1234567890123456", 2.000,
                generarListaLineasConsumoPendienteView()));
        consumoPendienteList.add(generarConsumoPendientePesos("AMEX", "1234567890123456", 3.000,
                generarListaLineasConsumoPendienteView()));
        return consumoPendienteList;
    }

    /**
     * Generar lista de lineas consumo pendiente.
     *
     * @return the list
     */
    private List<LineaDetalleConsumoTarjetaView> generarListaLineasConsumoPendienteView() {
        List<LineaDetalleConsumoTarjetaView> lineas = new ArrayList<LineaDetalleConsumoTarjetaView>();
        LineaDetalleConsumoTarjetaView linea = new LineaDetalleConsumoTarjetaView(generarLineaConsumo1VisaTitular());
        lineas.add(linea);
        return lineas;
    }

    /**
     * Generar consumo pendiente en pesos.
     *
     * @param marca
     *            the marca
     * @param numero
     *            the numero
     * @param totalPesos
     *            the total pesos
     * @param lineas
     *            the lineas
     * @return the consumo pendiente
     */
    private ConsumoPendienteView generarConsumoPendientePesos(String marca, String numero, double totalPesos,
            List<LineaDetalleConsumoTarjetaView> lineas) {
        ConsumoPendienteView consumoPendiente = new ConsumoPendienteView();
        consumoPendiente.setMarca(marca);
        consumoPendiente.setNumero(numero);
        consumoPendiente.setTotalPesos(BigDecimal.valueOf(totalPesos));
        consumoPendiente.setIsTitular(true);
        consumoPendiente.setHasError(false);
        consumoPendiente.setHasTotalPesosCero(true);
        consumoPendiente.setHasTotalDolaresCero(false);
        consumoPendiente.setLineas(lineas);
        return consumoPendiente;
    }

    /**
     * Generar limites Y disponibles view.
     *
     * @param limitesYDisponibles
     *            the limites Y disponibles
     * @return the limites Y disponibles view
     */
    private LimitesYDisponiblesView generarLimitesYDisponiblesView(LimitesYDisponiblesDTO limitesYDisponibles) {
        return new LimitesYDisponiblesView(limitesYDisponibles);
    }

    /**
     * Generar limites y disponibles.
     *
     * @return the limites y disponibles
     */
    private LimitesYDisponiblesDTO generarLimitesYDisponiblesOK() {
        LimitesYDisponiblesDTO limitesYDisponibles = new LimitesYDisponiblesDTO();
        limitesYDisponibles.setMarca("VISA");
        limitesYDisponibles.setNroTarjeta("1234567890123456");
        limitesYDisponibles.setSaldoDisponibleCompras("13.000,00");
        limitesYDisponibles.setLimiteCompraPesos("12.500,00");
        limitesYDisponibles.setSaldoDisponibleCuotas("20.000,00");
        limitesYDisponibles.setLimiteCuotasPesos("19.500,00");
        limitesYDisponibles.setAdelantoEfectivoPesos("30.000,00");
        limitesYDisponibles.setLimiteAdelantoEfectivoPesos("29.500,00");
        limitesYDisponibles.setHasLimiteUnificado(false);
        limitesYDisponibles.setMostrarSaldoEnCompras(true);
        limitesYDisponibles.setMostrarSaldoEnCuotas(true);
        limitesYDisponibles.setIsLimiteAdelantoDolares(false);
        limitesYDisponibles.setIsLimiteAdelantoPesos(true);
        limitesYDisponibles.setIsLimiteCompraDolares(false);
        limitesYDisponibles.setIsLimiteCompraPesos(true);
        limitesYDisponibles.setIsLimiteCuotasDolares(false);
        limitesYDisponibles.setIsLimiteCuotasPesos(true);
        limitesYDisponibles.setHasError(false);
        limitesYDisponibles.setLegales("Legales...");
        return limitesYDisponibles;
    }

    /**
     * Generar consulta financiacion view.
     *
     * @return the consulta financiacion view
     */
    private ConsultaFinanciacionView generarConsultaFinanciacionView() {
        ConsultaFinanciacionView consultaFinanciacionView = new ConsultaFinanciacionView();
        // consultaFinanciacionView.setListaConsultaFinanciaciones(generarListaConsultaFinanciacion());
        return consultaFinanciacionView;
    }

    /**
     * Generar lista de consulta financiacion.
     *
     * @return the list
     */
    @SuppressWarnings("unused")
    private List<ConsultaFinanciacionDetalleDTO> generarListaConsultaFinanciacion() {
        List<ConsultaFinanciacionDetalleDTO> consultaFinanciacionList = new ArrayList<ConsultaFinanciacionDetalleDTO>();
        consultaFinanciacionList
                .add(generarConsultaFinanciacion("VISA", "1234567890123456", generarListaFinanciaciones()));
        return consultaFinanciacionList;
    }

    /**
     * Generar lista de financiaciones.
     *
     * @return the list
     */
    private List<ConsultaFinanciacionLineaDTO> generarListaFinanciaciones() {
        List<ConsultaFinanciacionLineaDTO> financiacionList = new ArrayList<ConsultaFinanciacionLineaDTO>();
        financiacionList.add(generarFinanciacion());
        return financiacionList;
    }

    /**
     * Generar financiacion.
     *
     * @return the financiacion
     */
    private ConsultaFinanciacionLineaDTO generarFinanciacion() {
        ConsultaFinanciacionLineaDTO financiacion = new ConsultaFinanciacionLineaDTO();
        financiacion.setNumeroTarjeta("1234567890123456");
        financiacion.setNumeroDeSolicitud("435345465656");
        // financiacion.setFechaSolicitud("23/07/2016");
        // financiacion.setMontoTotalDelPlanEnPesos("5.000,00");
        // financiacion.setMontoCuotaDelPlan("24");
        // financiacion.setCuotasPagadas("06");
        // financiacion.setCostoFinanciero("3,6767");
        // financiacion.setTasaAnualAplicada("15,345");
        return financiacion;
    }

    /**
     * Generar consulta de financiacion.
     *
     * @param marca
     *            the marca
     * @param numeroTarjeta
     *            the numero tarjeta
     * @param financiaciones
     *            the financiaciones
     * @return the consulta financiacion
     */
    private ConsultaFinanciacionDetalleDTO generarConsultaFinanciacion(String marca, String numeroTarjeta,
            List<ConsultaFinanciacionLineaDTO> financiaciones) {
        ConsultaFinanciacionDetalleDTO consultaFinanciacion = new ConsultaFinanciacionDetalleDTO();
        consultaFinanciacion.setMarca(marca);
        consultaFinanciacion.setNumeroTarjeta(numeroTarjeta);
        consultaFinanciacion.setFinanciaciones(financiaciones);
        return consultaFinanciacion;
    }

    /**
     * Obtener resumen resumen anterior.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerResumenResumenAnterior() {
        // request
        ResumenAnteriorView viewRequest = new ResumenAnteriorView();
        viewRequest.setMarca("Visa");

        TarjetaActivaView tarjetaActiva = new TarjetaActivaView();
        tarjetaActiva.setTarjetaActiva("169-32781786/4");

        // response
        Respuesta<ResumenAnteriorViewResponse> respuesta = new Respuesta<ResumenAnteriorViewResponse>();
        // mock
        Mockito.when(resumenAnteriorManager.obtenerResumenesAnteriores(tarjetaActiva)).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/resumenesAnteriores/obtenerResumenesAnteriores");
        Respuesta<ResumenAnteriorViewResponse> retorno = client.post(viewRequest, Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Crear lista items limites Y disponibles.
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> crearListaItemsLimitesYDisponibles() {
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>(1);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje("Lo sentimos. Hubo un error en la carga de tus consumos.");
        item.setTag(TagItemMensajeTarjetaEnum.LIMITESYDISPONIBLES.getDescripcionTag());
        item.setTipoError(TipoError.ERROR_LIMITES_Y_DISPONIBLES_TARJETA.getDescripcion());
        items.add(item);
        return items;
    }

    /**
     * Crear respuesta error generico para test.
     *
     * @param <T>
     *            the generic type
     * @param items
     *            the items
     * @return the respuesta
     */
    private <T> Respuesta<T> crearRespuestaErrorGenericoParaTest(List<ItemMensajeRespuesta> items) {
        Respuesta<T> respuesta = new Respuesta<T>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setItemMensajeRespuesta(items);
        respuesta.setRespuesta(null);
        respuesta.setRespuestaVacia(true);
        return respuesta;
    }

    // private UltimoResumenView generarUltimoResumenSinAdicionales() {
    // UltimoResumenView ultimoResumenView = new UltimoResumenView();
    // ultimoResumenView.setMarca("VISA");
    // ultimoResumenView.setNumeroTarjeta("XXXX-8596");
    // ultimoResumenView.setTieneAlias(true);
    // ultimoResumenView.setAlias("Mis gastos");
    // ultimoResumenView.setFechaUltimoPeriodo("26 de Septiembre de 2016");
    // ultimoResumenView.setFechaCierreActual("27/09/2016");
    // ultimoResumenView.setFechaVencimientoActual("03/10/2016");
    // ultimoResumenView.setSaldoPesos("3.253,00");
    // ultimoResumenView.setSaldoDolares("45,00");
    // ultimoResumenView.setPagoMinimo("600,00");
    // ultimoResumenView.setLimiteFinanciacion("9.500,00");
    // ultimoResumenView.setLimiteCompra("8.000,00");
    // ultimoResumenView.setLimiteCompraCuotas("15.000,00");
    // ultimoResumenView.setSonLimitesDolar(false);
    // ultimoResumenView.setFechaProximoCierre("27/10/2016");
    // ultimoResumenView.setFechaProximoVencimiento("04/11/2016");
    // ultimoResumenView.setFechaCierreAnterior("27/09/2016");
    // ultimoResumenView.setFechaVencimientoAnterior("04/10/2016");
    // ultimoResumenView.setTasaNominalAnualPesos("58.05");
    // ultimoResumenView.setTasaEfectivaMensualPesos("3,12");
    // ultimoResumenView.setTasaNominalAnualDolares("58.05");
    // ultimoResumenView.setTasaEfectivaMensualDolares("3,12");
    // ultimoResumenView.setMensajeSEUO("Informacion obtenida de VISA@Home
    // (S.E.U.O)");
    // ultimoResumenView.setMuestraTarjetasConCabecera(false);
    //
    // List<LineaUltimoResumenTarjetaView> lineas =
    // generarLineasUltimoResumenTarjeta();
    //
    // UltimoResumenTarjetaView ultimoResumenTarjetaView = new
    // UltimoResumenTarjetaView();
    // ultimoResumenTarjetaView.setLineas(lineas);
    //
    // List<UltimoResumenTarjetaView> tarjetas = new
    // ArrayList<UltimoResumenTarjetaView>();
    // tarjetas.add(ultimoResumenTarjetaView);
    //
    // ultimoResumenView.setTarjetas(tarjetas);
    //
    // return ultimoResumenView;
    // }

    // private UltimoResumenView generarUltimoResumenConAdicionales() {
    // UltimoResumenView ultimoResumenView =
    // generarUltimoResumenSinAdicionales();
    // ultimoResumenView.setMuestraTarjetasConCabecera(true);
    //
    // UltimoResumenTarjetaView tarjeta1 = new UltimoResumenTarjetaView();
    // tarjeta1.setNumeroTarjeta("XXXX-8596");
    // tarjeta1.setEsAdicional(false);
    // tarjeta1.setTotalPesos("1.500,00");
    // tarjeta1.setTotalDolares("45,00");
    // tarjeta1.setNombreAdicional(null);
    // tarjeta1.setLineas(generarLineasUltimoResumenTarjeta());
    //
    // UltimoResumenTarjetaView tarjeta2 = new UltimoResumenTarjetaView();
    // tarjeta2.setNumeroTarjeta("XXXX-1236");
    // tarjeta2.setEsAdicional(true);
    // tarjeta2.setNombreAdicional("Joaquin Blanco");
    // tarjeta2.setTotalPesos("1.500,00");
    // tarjeta2.setTotalDolares("0,00");
    // tarjeta2.setLineas(generarLineasUltimoResumenTarjeta());
    //
    // List<UltimoResumenTarjetaView> tarjetas = new
    // ArrayList<UltimoResumenTarjetaView>();
    // tarjetas.add(tarjeta1);
    // tarjetas.add(tarjeta2);
    //
    // ultimoResumenView.setTarjetas(tarjetas);
    // return ultimoResumenView;
    // }

    // private List<LineaUltimoResumenTarjetaView>
    // generarLineasUltimoResumenTarjeta() {
    // LineaUltimoResumenTarjetaView linea1 = new
    // LineaUltimoResumenTarjetaView();
    // linea1.setFecha("22/09/2016");
    // linea1.setDescripcion("Cheeky");
    // linea1.setImporte("1.000,00");
    // linea1.setEsImportePesos(true);
    //
    // LineaUltimoResumenTarjetaView linea2 = new
    // LineaUltimoResumenTarjetaView();
    // linea2.setFecha("21/09/2016");
    // linea2.setDescripcion("Coto Suc. 132");
    // linea2.setImporte("500,00");
    // linea2.setEsImportePesos(true);
    //
    // LineaUltimoResumenTarjetaView linea3 = new
    // LineaUltimoResumenTarjetaView();
    // linea3.setFecha("13/09/2016");
    // linea3.setDescripcion("Netflix");
    // linea3.setImporte("45,00");
    // linea3.setEsImportePesos(false);
    //
    // List<LineaUltimoResumenTarjetaView> lineas = new
    // ArrayList<LineaUltimoResumenTarjetaView>();
    // lineas.add(linea1);
    // lineas.add(linea2);
    // lineas.add(linea3);
    // return lineas;
    // }
    /**
     * Exportar resumen on demand OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    @Ignore
    public void exportarResumenOnDemandOK() {
        String nroCuenta = "285-34583177/4";
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto(nroCuenta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/exportarResumenPuntualOnDemand");
        ReporteView view = new ReporteView();
        view.setNombre("Prueba");
        Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(view);
        respuesta.setRespuestaVacia(false);
        List<Integer> list = new ArrayList<Integer>(1);
        list.add(1);
        // Mockito.when(onDemandPuntualResumenManager.exportarOnDemandPuntualResumenConEstadistica(nroCuenta,
        // list.get(0)))
        // .thenReturn(respuesta);

        @SuppressWarnings("unused")
        Respuesta<ReporteView> retorno = client.post(cuenta, Respuesta.class);
        // Assert.assertEquals(view.getNombre(),
        // retorno.getRespuesta().getNombre());

    }

    /**
     * Tests TarjetaSEI when gets the solicitarFinanciacionTarjeta.
     * 
     * @author emilio.watemberg
     */
    @SuppressWarnings("unchecked")
    @Test
    public void solicitarFinanciacionTarjeta() {
        FinanciacionTarjetaView financiacionTarjetaView = new FinanciacionTarjetaView("12345");
        Respuesta<FinanciacionTarjetaView> retorno = new Respuesta<FinanciacionTarjetaView>();
        Respuesta<FinanciacionTarjetaView> respManager = new Respuesta<FinanciacionTarjetaView>();

        respManager.setRespuesta(financiacionTarjetaView);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respManager.setRespuestaVacia(false);

        Mockito.when(financiacionTarjetasManager.solicitarFinanciacionTarjeta(financiacionTarjetaView))
                .thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/solicitarFinanciacionTarjeta");
        addSleepTime(5000);
        retorno = client.post(financiacionTarjetaView, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Tests TarjetaSEI when gets the ejecutarFinanciacionTarjeta.
     *
     * @author emilio.watemberg
     */
    @SuppressWarnings("unchecked")
    @Test
    public void simularFinanciacionTarjeta() {
        FinanciacionTarjetaView financiacionTarjetaView = new FinanciacionTarjetaView("12345");
        Respuesta<FinanciacionTarjetaView> retorno = new Respuesta<FinanciacionTarjetaView>();
        Respuesta<FinanciacionTarjetaView> respManager = new Respuesta<FinanciacionTarjetaView>();

        respManager.setRespuesta(financiacionTarjetaView);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respManager.setRespuestaVacia(false);

        Mockito.when(financiacionTarjetasManager.simularFinanciacionTarjeta(financiacionTarjetaView))
                .thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/simularFinanciacionTarjeta");
        addSleepTime(5000);
        retorno = client.post(financiacionTarjetaView, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    // FIXME error de compilacion
    // /**
    // * Tests TarjetaSEI when gets the ejecutarFinanciacionTarjeta.
    // *
    // * @author manuel.vargas
    // */
    // @SuppressWarnings("unchecked")
    // @Test
    // public void ejecutarFinanciacionTarjeta() {
    // FinanciacionTarjetaView financiacionTarjetaView = new
    // FinanciacionTarjetaView("12345");
    // Respuesta<FinanciacionTarjetaView> retorno = new
    // Respuesta<FinanciacionTarjetaView>();
    // Respuesta<FinanciacionTarjetaView> respManager = new
    // Respuesta<FinanciacionTarjetaView>();
    //
    // respManager.setRespuesta(financiacionTarjetaView);
    // respManager.setEstadoRespuesta(EstadoRespuesta.OK);
    // respManager.setRespuestaVacia(false);
    //
    // Mockito.when(this.financiacionTarjetasManager.ejecutarFinanciacionTarjeta(financiacionTarjetaView))
    // .thenReturn(respManager);
    //
    // WebClient client = getWebClient();
    // client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    // client.path("/tarjetas/ejecutarFinanciacionTarjeta");
    // retorno = client.post(financiacionTarjetaView, Respuesta.class);
    //
    // Assert.assertNotNull(retorno);
    // Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
    // }


    /**
     * Comprobante financiacion tarjeta test.
     */
    @Test
    public void comprobanteFinanciacionTarjetaTest() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/comprobanteFinanciacionTarjeta");

        FinanciacionTarjetaView financiacionTarjetaView = new FinanciacionTarjetaView();
        Respuesta<FinanciacionTarjetaView> respManager = new Respuesta<FinanciacionTarjetaView>();

        respManager.setRespuesta(financiacionTarjetaView);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respManager.setRespuestaVacia(false);

        Mockito.when(
                financiacionTarjetasManager.comprobanteFinanciacionTarjeta(Matchers.any(FinanciacionTarjetaView.class)))
                .thenReturn(respManager);
        financiacionTarjetaView.setMarcaTarjetaSeleccionada("VISA");
        @SuppressWarnings("rawtypes")
        Respuesta postRespuesta = client.post(financiacionTarjetaView, Respuesta.class);
        Assert.assertNotNull(postRespuesta);
        Assert.assertTrue(EstadoRespuesta.OK.equals(postRespuesta.getEstadoRespuesta()));
    }

    /**
     * Obtener detalle de pago test.
     */
    @Test
    public void obtenerDetalleDePagoTest() {

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/detalleDePago");

        TarjetaPagoSeleccionView tarjetaPagoSeleccionView = new TarjetaPagoSeleccionView();
        tarjetaPagoSeleccionView.setFechaVencimiento("16/01/2017");
        tarjetaPagoSeleccionView.setNumeroTarjeta("1234567890123456");
        tarjetaPagoSeleccionView.setTipoDePago(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOMINIMO.toString());
        Mockito.when(pagosTarjetaManager.detalleDePago(Matchers.any(TarjetaPagoSeleccionView.class))).thenReturn(
                respuestaFactory.crearRespuestaOk(DetalleTarjetaPagoView.class, new DetalleTarjetaPagoView()));
        @SuppressWarnings("unchecked")
        Respuesta<TarjetaPagoSeleccionView> retorno = client.post(tarjetaPagoSeleccionView, Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Dado reintentar, cuando se invoca al metodo "obtenerDatoTarjeta", obtengo una
     * respuesta OK de tipo PagoTarjetaInfoView.
     * 
     * @author florencia.n.martinez
     */
    @SuppressWarnings("unchecked")
    @Test
    public void dadoReintentarCuandoInvocaObtenerDatoTarjetaObtengoRespuestaOKPagoTarjetaInfoView() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/tarjetas/nuevoPago");

        Mockito.when(pagosTarjetaManager.obtenerDatosInicialesPagoTarjeta(Matchers.any(Reintentar.class)))
                .thenReturn(respuestaFactory.crearRespuestaOk(PagoTarjetaInfoView.class,
                        PagoTarjetaInfoViewMock.obtenerPagoTarjetaInfoViewMockOK()));
        Respuesta<PagoTarjetaInfoView> retorno = client.post(ReintentarMock.obtenerSinReintentos(), Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

}