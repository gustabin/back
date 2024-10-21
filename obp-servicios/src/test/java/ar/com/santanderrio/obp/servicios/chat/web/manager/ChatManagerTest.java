package ar.com.santanderrio.obp.servicios.chat.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chat.bo.ChatBO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatConfigDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatEmailInDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatOutDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.DesconectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatFromEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatMessageEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatResponseEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatSessionEntity;
import ar.com.santanderrio.obp.servicios.chat.web.manager.impl.ChatManagerImpl;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatConfigView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatEnviarEmailInView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatOutView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class ChatManagerTest.
 * 
 * @author ITResources
 */
@RunWith(MockitoJUnitRunner.class)
public class ChatManagerTest {

	/** The chat manager. */
	@InjectMocks
	private ChatManager chatManager = new ChatManagerImpl();
	
    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;
    
    @Mock
    private ModuloPermisoBO moduloPermisoBO;

    /** The mensaje bo. */
    @Mock
    private MensajeBO mensajeBO;
    
    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    /** The chat bo. */
    @Mock
    private ChatBO chatBO;
    
	/** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
	@Test
	public void obtenerConfiguracionOK() {
	
		ChatConfigDTO chatConfigDTO = new ChatConfigDTO();
		chatConfigDTO.setCloseTimeout(60);
		chatConfigDTO.setConnectionTimeout(120);
		chatConfigDTO.setCsrfUrl("http://lxcmrsdesa01.ar.bsch:6090/webapi/api/v2/diagnostics/version");
		chatConfigDTO.setDataUrl("http://lxgnesysdesa01.ar.bsch:8090/genesys/2/chat/CE18_Digital_Chat");
		chatConfigDTO.setHoraDesde("08:00");
		chatConfigDTO.setHoraHasta("20:55");
		chatConfigDTO.setHoraHastaMsj("21:00");
		chatConfigDTO.setIsChatHabilitado(true);
		chatConfigDTO.setMailUrl("http://lxgnesysdesa01.ar.bsch:8090/genesys/2/email/PostEmail");
		chatConfigDTO.setSessionTimeout(300000);
		chatConfigDTO.setWatsonNickname("Tu asistente Virtual");
		Respuesta<ChatConfigDTO> respuestaBO = new Respuesta<ChatConfigDTO>();
		respuestaBO.setRespuesta(chatConfigDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(chatBO.obtenerConfiguracion(Matchers.anyString())).thenReturn(respuestaBO);
		
        Cliente cliente = ClienteMock.infoCliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        
		Mensaje mensajeReconectar = new Mensaje();
		mensajeReconectar.setMensaje("Tu sesión anterior ha caducado. A la brevedad te contactaremos con un nuevo operador.");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_RECONECTAR_SESION_EXPIRADA)).thenReturn(mensajeReconectar);
        
		Mensaje mensajeEnvioMail = new Mensaje();
		mensajeEnvioMail.setMensaje("<p><b>¡Lo sentimos!</b></p><p>En este momento no contamos con operadores disponibles para responder tus dudas.</p><p>Para que podamos responder a la brevedad te pedimos que ingreses tu e-mail.</p>");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_ENVIO_MAIL)).thenReturn(mensajeEnvioMail);
        
        
		Mensaje mensajeConexion = new Mensaje();
		mensajeConexion.setMensaje("En este momento todos nuestros ejecutivos están ocupados. Te pedimos disculpas por las molestias ocasionadas. Dejanos tu consulta y nos contactaremos con vos a la brevedad.");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_CONEXION)).thenReturn(mensajeConexion);
        
        
		Mensaje mensajeGenerico = new Mensaje();
		mensajeGenerico.setMensaje("<p>¡Lo sentimos!</p><p><b>Ocurrió un error en nuestros servicios.</b></p><p>Por favor, volvé a ingresar en unos minutos</p>");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_ERROR_GENERICO)).thenReturn(mensajeGenerico);
		
        
        Mensaje mensajeSinConexion = new Mensaje();
        mensajeSinConexion.setMensaje("TBD CAMBIAR POR EL QUE VA");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_SIN_OPERADORES_CONEXION)).thenReturn(mensajeSinConexion);

        
        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("isban@isbanexternos.com.ar");
        credencialesMya.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");
        credencialesMya.setCelular("11111111");
        credencialesMya.setCodigoArea("11");
        credencialesMya.setCompaniaSeleccionada("Claro");
        credencialesMya.setCelularSecundario("22222222");
        credencialesMya.setCodigoAreaSecundario("11");
        credencialesMya.setCompaniaSeleccionadaSecundaria("Movistar");

        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setModuloEstado(ModuloEstado.MOSTRAR);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.CHAT)).thenReturn(permiso);
        
        Respuesta<ChatConfigView> respuesta = chatManager.obtenerConfiguracion();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}
	
	@Test
	public void obtenerConfiguracionError() {
		
		Respuesta<ChatConfigDTO> respuestaBO = new Respuesta<ChatConfigDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(chatBO.obtenerConfiguracion(Matchers.anyString())).thenReturn(respuestaBO);

		Mensaje mensajeGenerico = new Mensaje();
		mensajeGenerico.setMensaje("<b>Ocurrió un error en nuestros servicios</b>. Por favor, volvé a ingresar en unos minutos.");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensajeGenerico);
		
        Cliente cliente = ClienteMock.infoCliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setModuloEstado(ModuloEstado.MOSTRAR);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.CHAT)).thenReturn(permiso);
        Respuesta<ChatConfigView> respuesta = chatManager.obtenerConfiguracion();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
	@Test
	public void conectarOK() {
		
		ChatFromEntity chatFromEntity = new ChatFromEntity();
		chatFromEntity.setNickname("First Last");
		chatFromEntity.setParticipantId(1);
		chatFromEntity.setType("Client");
		
		ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
		chatMessageEntity.setChatFrom(chatFromEntity);
		
		List<ChatMessageEntity> messages = new ArrayList<ChatMessageEntity>();
		messages.add(chatMessageEntity);
		
		ChatResponseEntity chatResponseEntity = new ChatResponseEntity();
		chatResponseEntity.setAlias("117");
		chatResponseEntity.setSecureKey("4ee15d7e1c343c8e");
		chatResponseEntity.setStatusCode(0);
		chatResponseEntity.setUserId("007555677B20000A");
		chatResponseEntity.setMessages(messages);
		
		ChatOutDTO chatOutDTO = new ChatOutDTO();
		chatOutDTO.setChatMessageEntity(chatResponseEntity);

		Respuesta<ChatOutDTO> respuestaBO = new Respuesta<ChatOutDTO>();
		respuestaBO.setRespuesta(chatOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
    	OfertaComercialView oferta = new OfertaComercialView();
    	oferta.setId("");
		
        Mockito.when(chatBO.conectar(Matchers.anyString(), Matchers.any(OfertaComercialView.class))).thenReturn(respuestaBO);
        
        Respuesta<ChatOutView> respuesta = chatManager.conectar("AE1B4D482F585747F63B6B4A1C188F00", oferta);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void conectarError() {
		
		Respuesta<ChatOutDTO> respuestaBO = new Respuesta<ChatOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(chatBO.conectar(Matchers.anyString(), Matchers.any(OfertaComercialView.class))).thenReturn(respuestaBO);
		
		Mensaje mensajeGenerico = new Mensaje();
		mensajeGenerico.setMensaje("<b>Ocurrió un error en nuestros servicios</b>. Por favor, volvé a ingresar en unos minutos.");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensajeGenerico);

    	OfertaComercialView oferta = new OfertaComercialView();
    	oferta.setId("");
    	
        Respuesta<ChatOutView> respuesta = chatManager.conectar("AE1B4D482F585747F63B6B4A1C188F00", oferta);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void desconectarOK() {
		
		ChatFromEntity chatFromEntity = new ChatFromEntity();
		chatFromEntity.setNickname("First Last");
		chatFromEntity.setParticipantId(1);
		chatFromEntity.setType("Client");
		
		ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
		chatMessageEntity.setChatFrom(chatFromEntity);
		
		List<ChatMessageEntity> messages = new ArrayList<ChatMessageEntity>();
		messages.add(chatMessageEntity);
		
		ChatResponseEntity chatResponseEntity = new ChatResponseEntity();
		chatResponseEntity.setAlias("117");
		chatResponseEntity.setSecureKey("4ee15d7e1c343c8e");
		chatResponseEntity.setStatusCode(0);
		chatResponseEntity.setUserId("007555677B20000A");
		chatResponseEntity.setMessages(messages);
		
		ChatOutDTO chatOutDTO = new ChatOutDTO();
		chatOutDTO.setChatMessageEntity(chatResponseEntity);

		Respuesta<ChatOutDTO> respuestaBO = new Respuesta<ChatOutDTO>();
		respuestaBO.setRespuesta(chatOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(chatBO.desconectar(Matchers.any(DesconectarInDTO.class))).thenReturn(respuestaBO);

		ChatSessionEntity chatSessionEntity = new ChatSessionEntity();
		chatSessionEntity.setAlias("117");
		chatSessionEntity.setChatId("1");
		chatSessionEntity.setSecureKey("4ee15d7e1c343c8e");
		chatSessionEntity.setUserId("007555677B20000A");
		Mockito.when(sesionParametros.getDetalleSesionChat()).thenReturn(chatSessionEntity);
		
        Respuesta<ChatOutView> respuesta = chatManager.desconectar();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}
	
	@Test
	public void desconectarError() {

		Respuesta<ChatOutDTO> respuestaBO = new Respuesta<ChatOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(chatBO.desconectar(Matchers.any(DesconectarInDTO.class))).thenReturn(respuestaBO);

		ChatSessionEntity chatSessionEntity = new ChatSessionEntity();
		chatSessionEntity.setAlias("117");
		chatSessionEntity.setChatId("1");
		chatSessionEntity.setSecureKey("4ee15d7e1c343c8e");
		chatSessionEntity.setUserId("007555677B20000A");
		Mockito.when(sesionParametros.getDetalleSesionChat()).thenReturn(chatSessionEntity);
		
		Mensaje mensajeGenerico = new Mensaje();
		mensajeGenerico.setMensaje("<b>Ocurrió un error en nuestros servicios</b>. Por favor, volvé a ingresar en unos minutos.");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensajeGenerico);
		
        Respuesta<ChatOutView> respuesta = chatManager.desconectar();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
    
	@Test
	public void enviarEmailOK() {
		

		Respuesta<Void> respuestaBO = new Respuesta<Void>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
	    Mockito.when(chatBO.enviarMail(Matchers.any(ChatEmailInDTO.class)) ).thenReturn(respuestaBO);
		
        Cliente cliente = ClienteMock.infoCliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("isban@isbanexternos.com.ar");
        credencialesMya.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");
        credencialesMya.setCelular("11111111");
        credencialesMya.setCodigoArea("11");
        credencialesMya.setCompaniaSeleccionada("Claro");
        credencialesMya.setCelularSecundario("22222222");
        credencialesMya.setCodigoAreaSecundario("11");
        credencialesMya.setCompaniaSeleccionadaSecundaria("Movistar");
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
        
		Mensaje mensajeMailOK = new Mensaje();
		mensajeMailOK.setMensaje("<p><b>¡Listo!</b></p><p>Tu e-mail ha sido registrado</p>");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_ENVIO_MAIL_EXITOSO)).thenReturn(mensajeMailOK);
		
        ChatEnviarEmailInView chatEnviarEmailInView = new ChatEnviarEmailInView();
        chatEnviarEmailInView.setEmail("isban@isbanexternos.com.ar");
        chatEnviarEmailInView.setMensaje("Mensaje prueba");
        
        Respuesta<Void> respuesta = chatManager.enviarEmail(chatEnviarEmailInView, "AE1B4D482F585747F63B6B4A1C188F00");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}
	
	@Test
	public void enviarEmailError() {
		

		Respuesta<Void> respuestaBO = new Respuesta<Void>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
	    Mockito.when(chatBO.enviarMail(Matchers.any(ChatEmailInDTO.class)) ).thenReturn(respuestaBO);
		
        Cliente cliente = ClienteMock.infoCliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("isban@isbanexternos.com.ar");
        credencialesMya.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");
        credencialesMya.setCelular("11111111");
        credencialesMya.setCodigoArea("11");
        credencialesMya.setCompaniaSeleccionada("Claro");
        credencialesMya.setCelularSecundario("22222222");
        credencialesMya.setCodigoAreaSecundario("11");
        credencialesMya.setCompaniaSeleccionadaSecundaria("Movistar");
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);

		Mensaje mensajeMailOK = new Mensaje();
		mensajeMailOK.setMensaje("<p><b>¡Ocurrió un error!</b></p><p>No pudimos registrar tu e-mail</p>");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_ENVIO_MAIL_ERRONEO)).thenReturn(mensajeMailOK);
		
        ChatEnviarEmailInView chatEnviarEmailInView = new ChatEnviarEmailInView();
        chatEnviarEmailInView.setEmail("isban@isbanexternos.com.ar");
        chatEnviarEmailInView.setMensaje("Mensaje prueba");
        
        Respuesta<Void> respuesta = chatManager.enviarEmail(chatEnviarEmailInView, "AE1B4D482F585747F63B6B4A1C188F00");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
    
}
