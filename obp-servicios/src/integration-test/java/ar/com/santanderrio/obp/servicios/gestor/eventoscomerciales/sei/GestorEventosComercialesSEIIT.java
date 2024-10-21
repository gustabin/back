/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
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
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GestionEventoComercialOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.NotificacionOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertaComercialEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertasComercialesEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.GestorEventosComercialesManager;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei.GestorEventosComercialesSEIIT.GestorEventosComercialesSEIITConfiguration;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei.impl.GestorEventosComercialesSEIImpl;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.NotificacionesComercialesView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertasComercialesView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Class GestorEventosComercialesSEIIT.
 *
 * @author florencia.n.martinez
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        GestorEventosComercialesSEIITConfiguration.class })
public class GestorEventosComercialesSEIIT extends AbstractSEITest {

    /** The Constant GOTOPAGODETARJETAS. */
    private static final String GOTOPAGODETARJETAS = "gotoPagoTarjetaCredito()";

    /** The gestor manager. */
    @Autowired
    private GestorEventosComercialesManager gestorManager;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The respuesta factory. */
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The mensaje BO. */
    @SuppressWarnings("unused")
    @Autowired
    private MensajeBO mensajeBO;

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
        return applicationContext.getBean("gestorEventosComercialesSEI");
    }

    /**
     * The Class GestorEventosComercialesSEIITConfiguration.
     */
    @Configuration
    public static class GestorEventosComercialesSEIITConfiguration {

        /**
         * Gestor eventos comerciales SEI.
         *
         * @return the gestor eventos comerciales SEI
         */
        @Bean(name = "gestorEventosComercialesSEI")
        public GestorEventosComercialesSEI gestorEventosComercialesSEI() {
            return new GestorEventosComercialesSEIImpl();
        }

        /**
         * Gestor manager.
         *
         * @return the gestor eventos comerciales manager
         */
        @Bean
        public GestorEventosComercialesManager gestorManager() {
            return Mockito.mock(GestorEventosComercialesManager.class);
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
         * Mensaje BO.
         *
         * @return the mensaje BO
         */
        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }

        @Bean
        public TokenView tokenView() {
            return Mockito.mock(TokenView.class);
        }
        
    }

    /**
     * Obtener cantidad notificaciones OK test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerCantidadNotificacionesOKTest() {
        // Given
        GestionEventoComercialOutEntity notificacionesEntity = new GestionEventoComercialOutEntity();
        notificacionesEntity.setCantNotifSinLeer("3");
        notificacionesEntity.setTotalNotif("5");
        NotificacionesComercialesView notificaciones = new NotificacionesComercialesView(notificacionesEntity, new TokenView(), null);
        Respuesta<NotificacionesComercialesView> respNot = respuestaFactory
                .crearRespuestaOk(NotificacionesComercialesView.class, notificaciones);
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(gestorManager.obtenerCantidadNotificaciones(Matchers.any(Cliente.class))).thenReturn(respNot);

        // Then
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/gestorEventosComerciales/obtenerCantNotificaciones");

        Respuesta<NotificacionesComercialesView> retorno = client.post(null, Respuesta.class);

        // Expected
        Assert.assertNotNull(retorno);
    }

    /**
     * Obtener notificaciones OK test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerNotificacionesOKTest() {
        // Given
        NotificacionOutEntity notifEntity = new NotificacionOutEntity();
        notifEntity.setIdNotificacion("1");
        notifEntity.setTitulo("Titulo notificacion");
        notifEntity.setMensajeAmigable("Mensaje amigable.");
        notifEntity.setLink(GOTOPAGODETARJETAS);
        notifEntity.setUrl("http://www.google.com");
        notifEntity.setIndicaLectura("S");
        notifEntity.setFechaAlta("2017-09-22");
        notifEntity.setGotoLink(new GotoLink(GOTOPAGODETARJETAS));
        List<NotificacionOutEntity> notificacionesEnt = new ArrayList<NotificacionOutEntity>();
        notificacionesEnt.add(notifEntity);
        GestionEventoComercialOutEntity notificacionesEntity = new GestionEventoComercialOutEntity();
        notificacionesEntity.setCantNotifSinLeer("3");
        notificacionesEntity.setTotalNotif("5");
        notificacionesEntity.setNotificaciones(notificacionesEnt);
        NotificacionesComercialesView notificaciones = new NotificacionesComercialesView(notificacionesEntity, new TokenView(), null);
        Respuesta<NotificacionesComercialesView> respNot = respuestaFactory
                .crearRespuestaOk(NotificacionesComercialesView.class, notificaciones);
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(gestorManager.obtenerNotificaciones(Matchers.any(Cliente.class))).thenReturn(respNot);

        // Then
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/gestorEventosComerciales/obtenerNotificaciones");

        Respuesta<NotificacionesComercialesView> retorno = client.post(null, Respuesta.class);

        // Expected
        Assert.assertNotNull(retorno);
    }

    /**
     * Actualizar notificaciones OK test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void actualizarNotificacionesOKTest() {
        // Given
        Respuesta<Boolean> respNot = new Respuesta<Boolean>();
        respNot.setEstadoRespuesta(EstadoRespuesta.OK);
        respNot.setRespuesta(Boolean.TRUE);
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(gestorManager.actualizarNotificaciones(Matchers.any(Cliente.class))).thenReturn(respNot);

        // Then
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/gestorEventosComerciales/actualizarNotificaciones");

        Respuesta<NotificacionesComercialesView> retorno = client.post(null, Respuesta.class);

        // Expected
        Assert.assertNotNull(retorno);
    }

    /**
     * Informar interes en notificacion OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void informarInteresEnNotificacionOK() {
        // Given
        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        // When
        Mockito.when(gestorManager.informarInteresEnNotificacion(Matchers.anyString())).thenReturn(respuesta);

        // Then
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/gestorEventosComerciales/informarInteresNotificacion");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1234");
        Respuesta<NotificacionesComercialesView> retorno = client.post(map, Respuesta.class);

        // Expected
        Assert.assertNotNull(retorno);
    }

    /**
     * Obtener carrusel OK test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerCarruselOKTest() {
        // Given
        OfertasComercialesEntity ofertasComercialesEntity = new OfertasComercialesEntity();
        ofertasComercialesEntity.setNup("00276937");
        ofertasComercialesEntity.setJsessionId(
                "JSESSIONID=eY78iCk6iHxvEPKuzzPRfbTFJhdX5vJRhkz-l3R_6pyAvAEuSwZb!-552872447; path=/; HttpOnly");
        ofertasComercialesEntity.setDatosOfertaSc("");
        ofertasComercialesEntity.setPuntosSuperclub("5000");
        ofertasComercialesEntity.setCantOfertas("1");
        List<OfertaComercialEntity> ofertas = new ArrayList<OfertaComercialEntity>();
        OfertaComercialEntity oferta1 = new OfertaComercialEntity();
        oferta1.setOfertaDefault("N");
        oferta1.setIdOfertaRtd("HOGAR$BBB003");
        oferta1.setIdOfertaInterno("BETA - Smart TV LG 43\" 4K");
        oferta1.setTipoOferta("SUPERCLUB");
        oferta1.setCategoriaOferta("TBANCO");
        oferta1.setUrl("http://webimages.santanderrio.com.ar/encabezado.jpg");
        oferta1.setDescripcion("");
        oferta1.setLink(
                "gotoLanding(;https://www.santanderrio.com.ar/banco/online/landings/stack-super-club-2; gotoPagoTarjetaCredito())");
        oferta1.setGrupoControl("F");
        oferta1.setPuntosOfertaSc("10");
        oferta1.setPrecioOfertaSc("");
        oferta1.setTitulo("Smart TV LG 43\" 4K");
        oferta1.setDescripcionSeccion("");
        oferta1.setSeccionOferta("");
        oferta1.setIndicadorClicSeccion("");
        oferta1.setOrdenPrioridad("1");
        oferta1.setUbicacionCarrusel("S");
        oferta1.setUbicacionSeccion("");
        oferta1.setOrigen("RTD");
        oferta1.setTipoOfertaSc("PRODUCTO");
        oferta1.setTipoProductoLoyalty("1");
        oferta1.setIdLoyalty("1-324M05");
        oferta1.setGotoLink(new GotoLink("gotoPagoTarjetaCredito()"));
        ofertas.add(oferta1);
        ofertasComercialesEntity.setOfertas(ofertas);
        ofertasComercialesEntity.setCodigoError("ERROR000");
        ofertasComercialesEntity.setDescripcionError("");
        EventosComercialesDTO eventosComercialesDTO = new EventosComercialesDTO();
        eventosComercialesDTO.cargarOfertas(ofertasComercialesEntity);
        OfertasComercialesView ofertasComerciales = new OfertasComercialesView();
        ofertasComerciales.cargarOfertasCarrusel(eventosComercialesDTO, new TokenView(), sesionCliente.getCliente());

        Respuesta<OfertasComercialesView> respOfertasCom = respuestaFactory
                .crearRespuestaOk(OfertasComercialesView.class, ofertasComerciales);
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(gestorManager.obtenerCarrusel()).thenReturn(respOfertasCom);

        // Then
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/gestorEventosComerciales/obtenerCarrusel");

        Respuesta<NotificacionesComercialesView> retorno = client.post(null, Respuesta.class);

        // Expected
        Assert.assertNotNull(retorno);
    }

    /**
     * Informar interes oferta.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void informarInteresOferta() {
        // When
        Mockito.when(gestorManager.informarFeedbackOferta(Mockito.any(OfertaComercialView.class), Mockito.anyString())).thenReturn(new Respuesta<Void>());

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/gestorEventosComerciales/informarInteresOferta");

        // Then
        Respuesta<Void> respuesta = client.post("{\"id\":\"TEST\"}", Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener ofertas por seccion.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerOfertasPorSeccion() {
        // When
        Mockito.when(gestorManager.obtenerOfertasPorSeccion()).thenReturn(new Respuesta<OfertasComercialesView>());

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/gestorEventosComerciales/obtenerOfertasPorSeccion");

        // Then
        Respuesta<OfertasComercialesView> respuesta = client.post(null, Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Informar rechazo oferta OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void informarRechazoOfertaOK() {
        // Given
        Respuesta<Void> response = new Respuesta<Void>();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
        OfertaComercialView view = new OfertaComercialView();
        view.setId("CABLE");

        // When
        Mockito.when(gestorManager.informarFeedbackOferta(Mockito.any(OfertaComercialView.class), Mockito.anyString())).thenReturn(response);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/gestorEventosComerciales/informarRechazoOferta");

        // Then
        Respuesta<Void> respuesta = client.post(view, Respuesta.class);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
}
