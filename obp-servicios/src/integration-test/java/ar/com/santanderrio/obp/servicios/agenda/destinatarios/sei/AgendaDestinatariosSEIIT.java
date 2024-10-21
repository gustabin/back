/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei;

import java.util.ArrayList;
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

import com.google.gson.GsonBuilder;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConfiguracionAltaDestinatarioCBUOutViewMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConfiguracionAltaDestinatarioInViewMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.impl.AgendaDestinatariosSEIImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity.DatosEntradaAgendaDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosRCAManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AltaDestinatarioManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AltaDestinatarioInView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioCBUOutView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioInView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioOutView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AgendaDestinatariosSEITest.
 *
 * @author sabrina.cis
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.AgendaDestinatariosSEIIT.AgendaDestinatariosSEITestConfiguration.class })
public class AgendaDestinatariosSEIIT extends AbstractSEITest {

    /** The agenda destinatarios manager. */
    @Autowired
    private AgendaDestinatariosManager agendaDestinatariosManager;

    /** The alta destinatario manager. */
    @Autowired
    private AltaDestinatarioManager altaDestinatarioManager;
    
    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;
    
    /** The agenda rsa manager. */
    @Autowired
    private AgendaDestinatariosRCAManager agendaRSAManager;

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
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("agendaDestinatariosSEI");
    }

    /**
     * The Class AgendaDestinatariosSEITestConfiguration.
     */
    @Configuration
    public static class AgendaDestinatariosSEITestConfiguration {

        /**
         * Agenda destinatarios SEI.
         *
         * @return the agenda destinatarios SEI
         */
        @Bean(name = "agendaDestinatariosSEI")
        public AgendaDestinatariosSEI agendaDestinatariosSEI() {
            return new AgendaDestinatariosSEIImpl();
        }

        /**
         * Agenda destinatarios manager.
         *
         * @return the agenda destinatarios manager
         */
        @Bean
        public AgendaDestinatariosManager agendaDestinatariosManager() {
            return Mockito.mock(AgendaDestinatariosManager.class);
        }

        /**
         * Alta destinatarios manager.
         *
         * @return the alta destinatario manager
         */
        @Bean
        public AltaDestinatarioManager altaDestinatariosManager() {
            return Mockito.mock(AltaDestinatarioManager.class);
        }
        
        @Bean
        public EstadisticaManager estadisticaManager() {
            return Mockito.mock(EstadisticaManager.class);
        }

        @Bean
        public AgendaDestinatariosRCAManager agendaDestinatariosRCAManager() {
            return Mockito.mock(AgendaDestinatariosRCAManager.class);
        }

        
    }

    /**
     * Obtiene la agenda destinatarios con respuesta OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerAgendaDestinatariosOK() {
        Respuesta<AgendaDestinatarioView> view = crearRespuestaOK();
        DatosEntradaAgendaDestinatario dato = Mockito.mock(DatosEntradaAgendaDestinatario.class);
        Mockito.when(dato.getFiltroDestinatario()).thenReturn(false);
        Mockito.when(agendaDestinatariosManager.obtenerAgendaDestinatarios(dato)).thenReturn(view);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/obtenerListaDestinatario");
        addSleepTime(5000);
        Respuesta<AgendaDestinatarioView> retorno = client.post(null, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Ver detalle destinatario OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void verDetalleDestinatarioOK() {
        Mockito.doNothing().when(agendaDestinatariosManager).grabarEstadisticaDetalleAgenda(Matchers.anyString());

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/verDetalleDestinatario");

        Respuesta<CompraVentaDolarView> retorno = client.post(new DatosEntradaAgendaDestinatario(), Respuesta.class);
        Assert.assertNull(retorno);
    }

    /**
     * Testea el llamado del manager del grabado de estadistica para ver
     * comprobante rio.
     */
    @Test
    public void grabarEstadisticaVerComprobanteRioOK() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/configuracionVerComprobanteRio");

        client.post(null, Respuesta.class);
        Mockito.verify(agendaDestinatariosManager).grabarEstadisticaVerComprobanteRio();

    }

    /**
     * Testea el llamado del manager del grabado de estadistica para entrar a
     * alta envio efectivo.
     */
    @Test
    public void grabarEstadisticaIngresoAltaDestinatarioEnvioEfectivoOK() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/configuracionAltaDestinatarioEnvioEfectivo");
        addSleepTime(5000);
        client.post(null, Respuesta.class);
        Mockito.verify(agendaDestinatariosManager).grabarEstadisticaIngresoEnvioEfectivo();
    }

    /**
     * Testea el llamado del manager del grabado de estadistica para entrar a
     * alta envio efectivo.
     */
    @Test
    public void grabarEstadisticaAltaDestinatarioEnvioEfectivoOK() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/configuracionVerComprobanteEnvioEfectivo");

        client.post(null, Respuesta.class);
        Mockito.verify(agendaDestinatariosManager).grabarEstadisticaAltaEnvioEfectivo();
    }

    /**
     * Testea el llamado del manager del grabado de estadistica para entrar a
     * alta otros bancos.
     */
    @Test
    public void grabarEstadisticaIngresoAltaDestinatarioCBUOK() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/configuracionAltaDestinatarioCBU");

        client.post(null, Respuesta.class);
        Mockito.verify(agendaDestinatariosManager).grabarEstadisticaIngresoAltaDestinatarioCBU();
    }

    /**
     * Testea el llamado del manager del grabado de estadistica para entrar a
     * alta otros bancos.
     */
    @Test
    public void grabarEstadisticaModificacionDestinatarioRioOK() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/configuracionEditarDestinatario");
        addSleepTime(5000);
        client.post(new DatosEntradaAgendaDestinatario(), Respuesta.class);
        Mockito.verify(agendaDestinatariosManager).grabarEstadisticaConfiguracionModificacionDestinatario(
                Matchers.any(DatosEntradaAgendaDestinatario.class));
    }

    /**
     * Testea el llamado del manager del grabado de estadistica para entrar a
     * alta cbu.
     */
    @Test
    public void grabarEstadisticaModificacionDestinatarioCBUOK() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/configuracionVerComprobanteCBU");
        addSleepTime(500);
        client.post(null, Respuesta.class);
        Mockito.verify(agendaDestinatariosManager).grabarEstadisticaComprobanteCBU();
    }

    /**
     * alta destinatario rio OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void altaDestinatarioRioOK() {
        Mockito.doNothing().when(agendaDestinatariosManager).grabarEstadisticaIngresoAltaDestinatarioRio();

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/configuracionAltaDestinatarioRio");
        addSleepTime(1000);
        Respuesta<CompraVentaDolarView> retorno = client.post(null, Respuesta.class);
        Assert.assertNull(retorno);
    }

    /**
     * caso Ok de configuracion alta destinatario.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void configuracionAltaDestinatariosOK() {

        Mockito.when(agendaDestinatariosManager.derivarServicioAltaCorrespondiente(
                Matchers.any(ConfiguracionAltaDestinatarioInView.class), Matchers.anyString()))
                .thenReturn(crearRespuestaOKAlta());

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/continuarAltaDestinatario");
        addSleepTime(500);
        Respuesta<CompraVentaDolarView> retorno = client.post(new AltaDestinatarioInView(), Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Test de la configuracion del alta de un destinatarios por alias de una
     * cuenta rio.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void configuracionAltaDestinatariosOKAliasRio() {
        Mockito.when(agendaDestinatariosManager.derivarServicioAltaCorrespondiente(
                Matchers.any(ConfiguracionAltaDestinatarioInView.class), Matchers.anyString()))
                .thenReturn(respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioCBUOutView.class,
                        ConfiguracionAltaDestinatarioCBUOutViewMock.completarInfoRio()));

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/continuarAltaDestinatario");
        addSleepTime(500);
        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> retorno = client
                .post(ConfiguracionAltaDestinatarioInViewMock.completarInfoParaAliasValido(), Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Test de la configuracion del alta de un destinatarios por alias de una
     * cuenta rio.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void configuracionAltaDestinatariosOKAliasNoRio() {
        Mockito.when(agendaDestinatariosManager.derivarServicioAltaCorrespondiente(
                Matchers.any(ConfiguracionAltaDestinatarioInView.class), Matchers.anyString()))
                .thenReturn(respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioCBUOutView.class,
                        ConfiguracionAltaDestinatarioCBUOutViewMock.completarInfoNoRio()));

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/continuarAltaDestinatario");
        addSleepTime(500);
        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> retorno = client
                .post(ConfiguracionAltaDestinatarioInViewMock.completarInfoParaAliasValido(), Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Obtiene la respuesta de error generico para el inicio de agenda
     * destinatarios.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerListaDestinatariosConErrorGenerico() {
        DatosEntradaAgendaDestinatario dato = Mockito.mock(DatosEntradaAgendaDestinatario.class);
        Mockito.when(dato.getFiltroDestinatario()).thenReturn(false);
        Mockito.when(agendaDestinatariosManager.obtenerAgendaDestinatarios(dato))
                .thenReturn(crearRespuestaErrorGenerico());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/obtenerListaDestinatario");
        addSleepTime(5000);
        Respuesta<AgendaDestinatarioView> retorno = client.post(null, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Confirmar alta destinatario envio efectivo respuesta OK.
     * 
     * @throws BusinessException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void confirmarAltaDestinatarioEnvioEfectivoRespuestaOK() throws BusinessException {
        ConfirmacionAltaDestinatarioView confirmacion = new ConfirmacionAltaDestinatarioView();
        Mockito.when(altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioEnvioEfectivo(Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(crearRespuestaComprobanteAltaDestinatarioOK());

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/confirmarAltaDestinatario");

        Respuesta<ComprobanteAltaDestinatarioView> retorno = client.post(confirmacion, Respuesta.class);
        String miJson = (new GsonBuilder()).create().toJson(retorno);
        System.out.println(miJson);
        Assert.assertNotNull(retorno);
    }

    /**
     * Confirmar alta destinatario envio efectivo respuesta OK.
     * 
     * @throws BusinessException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void confirmarAltaDestinatarioRioRespuestaOK() throws BusinessException {
        ConfirmacionAltaDestinatarioView confirmacion = new ConfirmacionAltaDestinatarioView();
        confirmacion.setNroCuenta("25");
        Mockito.when(altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioRio(Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(crearRespuestaComprobanteAltaDestinatarioOK());

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/confirmarAltaDestinatario");
        addSleepTime(5000);
        Respuesta<ComprobanteAltaDestinatarioView> retorno = client.post(confirmacion, Respuesta.class);
        String miJson = (new GsonBuilder()).create().toJson(retorno);
        System.out.println(miJson);
        Assert.assertNotNull(retorno);
    }

    /**
     * Confirmar alta destinatario otros bancos respuesta OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void confirmarAltaDestinatarioOtrosBancosRespuestaOK() {
        ConfirmacionAltaDestinatarioView confirmacion = new ConfirmacionAltaDestinatarioView();
        confirmacion.setCbu("cbu");
        Mockito.when(altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioOtrosBancos(Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(crearRespuestaComprobanteAltaDestinatarioOK());

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/confirmarAltaDestinatario");
        addSleepTime(5000);

        Respuesta<ComprobanteAltaDestinatarioView> retorno = client.post(confirmacion, Respuesta.class);
        String miJson = (new GsonBuilder()).create().toJson(retorno);
        System.out.println(miJson);
        Assert.assertNotNull(retorno);
    }

    /**
     * alta destinatario rio OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void altaDestinatarioRioTransferenciaCBUOK() {
        Mockito.when(agendaDestinatariosManager.derivarServicioAltaCorrespondiente(
                Matchers.any(ConfiguracionAltaDestinatarioInView.class), Matchers.anyString()))
                .thenReturn(new Respuesta<ConfiguracionAltaDestinatarioCBUOutView>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/continuarAltaDestinatarioTransferenciaCBU");
        addSleepTime(5000);
        Respuesta<ConfiguracionAltaDestinatarioOutView> retorno = client.post(new ConfiguracionAltaDestinatarioInView(),
                Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Testea el llamado del manager del grabado de estadistica para entrar a
     * alta otros bancos.
     */
    @Test
    public void eliminacionDestinatarioOKTest() {
        DatosEntradaAgendaDestinatario datos = new DatosEntradaAgendaDestinatario();
        datos.setId("12");

        Mockito.when(agendaDestinatariosManager.eliminacionDestinatario(datos.getId()))
                .thenReturn(new Respuesta<Void>());

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/eliminarDestinatario");
        addSleepTime(5000);
        client.post(datos, Respuesta.class);
        Mockito.verify(agendaDestinatariosManager).eliminacionDestinatario(datos.getId());
    }

    /**
     * Testea el llamado del manager del grabado de estadistica para entrar a
     * alta otros bancos.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void confirmarEdicionDestinatarioOKTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setId("12");
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = respuestaFactory.crearRespuestaOk(ConfirmacionAltaDestinatarioView.class, datosEntrada);

        Mockito.when(agendaDestinatariosManager.obtenerConfirmacionEdicionDestinatario(Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/confirmarEdicionDestinatario");

        Respuesta<ConfirmacionAltaDestinatarioView> retorno = client.post(datosEntrada, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Grabar estadistica configuracion alta destinatario alias CBU OK.
     */
    @Test
    public void grabarEstadisticaConfiguracionAltaDestinatarioAliasCBUOKTest() {
        Mockito.doNothing().when(altaDestinatarioManager).grabarEstadisticaConfiguracionAltaDestinatarioAliasCBU();

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferencias/iniciarAltaDestinatarioAliasCBU");
        addSleepTime(5000);
        client.post(null, Respuesta.class);
    }

    /**
     * Crea la respuesta de comprobante alta destinatario OK.
     *
     * @return the respuesta
     */
    private Respuesta<ConfirmacionAltaDestinatarioView> crearRespuestaComprobanteAltaDestinatarioOK() {
        return respuestaFactory.crearRespuestaOk(ConfirmacionAltaDestinatarioView.class,
                obtenerComprobanteAltaDestinatarioView());
    }

    /**
     * Obtiene el comprobante del alta del destinatario view.
     *
     * @return the comprobante alta destinatario view
     */
    private ConfirmacionAltaDestinatarioView obtenerComprobanteAltaDestinatarioView() {
        ConfirmacionAltaDestinatarioView view = new ConfirmacionAltaDestinatarioView();
        view.setNroComprobante("1234567890");
        view.setFecha("25/2/2017");
        view.setHora("12:32");
        view.setMensajeEfectivizacion(
                "<p>El destinatario <b>\"pepe\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>");
        return view;
    }

    /**
     * Crea una respuesta de error generico.
     *
     * @return the respuesta
     */
    private Respuesta<AgendaDestinatarioView> crearRespuestaErrorGenerico() {
        Respuesta<AgendaDestinatarioView> respuestaError = new Respuesta<AgendaDestinatarioView>();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje("Hubo un error no deseado");
        item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        respuestaError.add(item);
        return respuestaError;
    }

    /**
     * Generar compra/venta dolar view con cuentas.
     *
     * @return the compra venta dolar view
     */
    private AgendaDestinatarioView crearRespuestaAgendaDestinatariosOK() {
        DestinatarioAgendaDTO dto = new DestinatarioAgendaDTO();

        dto.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        dto.setCbu("012894129847124790");
        dto.setCuitCuil(null);
        dto.setDocumento("37866881");
        dto.setEmail("dante.omar.olmedo@accenture.com");
        dto.setReferenciaApodo("Otro apodo");
        dto.setTipoAgendaEnum(TipoAgendaEnum.AGENDA_RIO);
        dto.setTipoCuenta("Cuenta unica");
        dto.setNroCuenta("123-456788/9");
        dto.setTitular("OLMEDO, DANTE OMAR");

        DestinatarioAgendaDTO dto1 = new DestinatarioAgendaDTO();

        dto1.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        dto1.setCbu("012894129847124789");
        dto1.setCuitCuil(null);
        dto1.setDocumento("37866881");
        dto1.setEmail("dante.omar.olmedo@accenture.com");
        dto1.setReferenciaApodo("Rio Rio");
        dto1.setTipoAgendaEnum(TipoAgendaEnum.AGENDA_RIO);
        dto1.setTipoCuenta("Cuenta Corriente");
        dto1.setNroCuenta("123-456734/9");
        dto1.setTitular("OLMEDO, DANTE OMAR");

        List<DestinatarioAgendaDTO> listaDestinatariosDto = new ArrayList<DestinatarioAgendaDTO>();
        listaDestinatariosDto.add(dto);
        listaDestinatariosDto.add(dto1);

        DestinatarioView destinatarioView = new DestinatarioView(dto);
        DestinatarioView destinatarioView1 = new DestinatarioView(dto1);
        List<DestinatarioView> listaDestinatarios = new ArrayList<DestinatarioView>();
        listaDestinatarios.add(destinatarioView);
        listaDestinatarios.add(destinatarioView1);

        AgendaDestinatarioDTO dto2 = new AgendaDestinatarioDTO();
        dto2.setMensajeCabecera("Mensaje cabecera");
        dto2.setListaDestinatarios(listaDestinatariosDto);

        AgendaDestinatarioView view = new AgendaDestinatarioView(dto2);
        view.setMensajeCabecera("");
        view.setListaDestinatarios(listaDestinatarios);

        return view;
    }

    /**
     * Crear respuesta OK.
     *
     * @return the respuesta
     */
    private Respuesta<AgendaDestinatarioView> crearRespuestaOK() {
        return respuestaFactory.crearRespuestaOk(AgendaDestinatarioView.class, crearRespuestaAgendaDestinatariosOK());
    }

    /**
     * Crear respuesta OK.
     *
     * @return the respuesta
     */
    private Respuesta<ConfiguracionAltaDestinatarioCBUOutView> crearRespuestaOKAlta() {
        return respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioCBUOutView.class,
                new ConfiguracionAltaDestinatarioCBUOutView(new ConfiguracionAltaDestinatarioDTO()));
    }

}
