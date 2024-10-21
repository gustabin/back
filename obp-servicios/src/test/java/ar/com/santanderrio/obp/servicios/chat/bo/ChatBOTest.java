package ar.com.santanderrio.obp.servicios.chat.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.chat.bo.impl.ChatBOImpl;
import ar.com.santanderrio.obp.servicios.chat.dao.ChatDAO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatConfigDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatEmailInDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatOutDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ConectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.DesconectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatConfigEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailRequestEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailResponseEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatFromEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatMessageEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatResponseEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class ChatBOTest.
 * 
 * @author ITResources
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ChatBOTest {
	
	/** The chat dao. */
	@Mock
	private ChatDAO chatDAO;

	/** The chat bo. */
	@InjectMocks
	private ChatBO chatBO = new ChatBOImpl();

	/** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;
    
    /** The mensaje bo. */
    @Mock
    private MensajeBO mensajeBO;
 
   
    /**
     * Inits.
     */
    @Before
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
    }

	@Test
	public void obtenerConfiguracionOK() throws DAOException {
		
	
		ChatConfigEntity chatConfigEntity = new ChatConfigEntity();
		chatConfigEntity.setCloseTimeout(60);
		chatConfigEntity.setConnectionTimeout(120);
		chatConfigEntity.setCsrfUrl("http://lxcmrsdesa01.ar.bsch:6090/webapi/api/v2/diagnostics/version");
		chatConfigEntity.setDataUrl("http://lxgnesysdesa01.ar.bsch:8090/genesys/2/chat/CE18_Digital_Chat");
		chatConfigEntity.setHoraDesde("08:00");
		chatConfigEntity.setHoraHasta("20:55");
		chatConfigEntity.setHoraHastaMsj("21:00");
		chatConfigEntity.setIsChatHabilitado(true);
		chatConfigEntity.setMailUrl("http://lxgnesysdesa01.ar.bsch:8090/genesys/2/email/PostEmail");
		chatConfigEntity.setSessionTimeout(300000);
		chatConfigEntity.setWatsonNickname("Tu asistente Virtual");
		Mockito.when(chatDAO.obtenerConfiguracion("03566668")).thenReturn(chatConfigEntity);
		Respuesta<ChatConfigDTO> respuesta = chatBO.obtenerConfiguracion("03566668");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void obtenerConfiguracionDAOException() throws DAOException {
		Mockito.when(chatDAO.obtenerConfiguracion("03566668")).thenThrow(new DAOException());
		Respuesta<ChatConfigDTO> respuesta = chatBO.obtenerConfiguracion("03566668");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	@Test	
	public void conectarOK() throws DAOException {
				
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
        
		Mockito.when(chatDAO.conectarGenesys(Matchers.any(ConectarInDTO.class))).thenReturn(chatResponseEntity);
		
    	OfertaComercialView oferta = new OfertaComercialView();
    	oferta.setId("");
    	
		Respuesta<ChatOutDTO> respuesta = chatBO.conectar("AE1B4D482F585747F63B6B4A1C188F00", oferta);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void conectarCodeERROR() throws DAOException {
				
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
		chatResponseEntity.setStatusCode(1);
		chatResponseEntity.setUserId("007555677B20000A");
		chatResponseEntity.setMessages(messages);
		
        Cliente cliente = ClienteMock.infoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000023234343454545453");
        cuenta.setCliente(cliente);
        cuenta.setNroSucursal("1100");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);   
        cuenta.setTipoCuenta("07");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setNroTarjetaCredito("1111222233334444");
        Cuenta cuentaDos = new Cuenta();
        cuentaDos.setNroCuentaProducto("0000023234343454545453");
        cuentaDos.setCliente(cliente);
        cuentaDos.setNroSucursal("1100");
        cuentaDos.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuentaDos.setTipoCuenta("42");
        cuentaDos.setCodigoTitularidad("TI");        
        cuentaDos.setNroTarjetaCredito("1111222233334444");
        Cuenta cuentaTres = new Cuenta();
        cuentaTres.setNroCuentaProducto("0000023234343454545453");
        cuentaTres.setCliente(cliente);
        cuentaTres.setNroSucursal("1100");
        cuentaTres.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuentaTres.setTipoCuenta("42");
        cuentaTres.setCodigoTitularidad("TI");        
        cuentaTres.setNroTarjetaCredito("111122223333");        
        Cuenta cuentaBanelco = new Cuenta();
        cuentaBanelco.setNroCuentaProducto("0000023234343454545453");
        cuentaBanelco.setCliente(cliente);
        cuentaBanelco.setNroSucursal("1100");
        cuentaBanelco.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuentaBanelco.setTipoCuenta("05");
        cuentaBanelco.setCodigoTitularidad("TI");
        cliente.setCuentas(new ArrayList<Cuenta>());
        cliente.getCuentas().add(cuenta);
        cliente.getCuentas().add(cuentaDos);
        cliente.getCuentas().add(cuentaBanelco);

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
        
		Mockito.when(chatDAO.conectarGenesys(Matchers.any(ConectarInDTO.class))).thenReturn(chatResponseEntity);
		/*Respuesta<ChatOutDTO> respuesta = chatBO.conectar("AE1B4D482F585747F63B6B4A1C188F00", "");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    */
	}

	@Test
	public void conectarDAOException() throws DAOException {
				
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
        
		Mockito.when(chatDAO.conectarGenesys(Matchers.any(ConectarInDTO.class))).thenThrow(new DAOException());
		/*Respuesta<ChatOutDTO> respuesta = chatBO.conectar("AE1B4D482F585747F63B6B4A1C188F00", "");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    */
	}

	
	@Test
	public void desconectarOK() throws DAOException {
						
		ChatResponseEntity chatResponseEntity = new ChatResponseEntity();
		chatResponseEntity.setStatusCode(0);
	
		Mockito.when(chatDAO.desconectarGenesys(Matchers.any(DesconectarInDTO.class))).thenReturn(chatResponseEntity);
		
		DesconectarInDTO desconectarInDto = new DesconectarInDTO();
		desconectarInDto.setAlias("261");
		desconectarInDto.setChatId("0001EaDK9R0J02E0");
		desconectarInDto.setSecureKey("c0bfcd3c1f0ef19428ad");
		desconectarInDto.setUserId("01055B7EB82001AD");
		
		Respuesta<ChatOutDTO> respuesta = chatBO.desconectar(desconectarInDto);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void desconectarCodeERROR() throws DAOException {
						
		ChatResponseEntity chatResponseEntity = new ChatResponseEntity();
		chatResponseEntity.setStatusCode(1);
	
		Mockito.when(chatDAO.desconectarGenesys(Matchers.any(DesconectarInDTO.class))).thenReturn(chatResponseEntity);
		
		DesconectarInDTO desconectarInDto = new DesconectarInDTO();
		desconectarInDto.setAlias("261");
		desconectarInDto.setChatId("0001EaDK9R0J02E0");
		desconectarInDto.setSecureKey("c0bfcd3c1f0ef19428ad");
		desconectarInDto.setUserId("01055B7EB82001AD");
		
		Respuesta<ChatOutDTO> respuesta = chatBO.desconectar(desconectarInDto);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
	@Test
	public void desconectarDAOException() throws DAOException {
						
		Mockito.when(chatDAO.desconectarGenesys(Matchers.any(DesconectarInDTO.class))).thenThrow(new DAOException());
		
		DesconectarInDTO desconectarInDto = new DesconectarInDTO();
		desconectarInDto.setAlias("261");
		desconectarInDto.setChatId("0001EaDK9R0J02E0");
		desconectarInDto.setSecureKey("c0bfcd3c1f0ef19428ad");
		desconectarInDto.setUserId("01055B7EB82001AD");
		
		Respuesta<ChatOutDTO> respuesta = chatBO.desconectar(desconectarInDto);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	@Test
	public void enviarMailOK() throws DAOException {
						
		ChatEmailInDTO chatEmailInDTO = new ChatEmailInDTO();
		chatEmailInDTO.setEmail("dgalindez4@servexternos.isban-santander.com.ar");
		chatEmailInDTO.setMensaje("Test de envio de email");
		chatEmailInDTO.setUserData("NUP=00011903");

		ChatEmailResponseEntity chatEmailResponseEntity = new ChatEmailResponseEntity();
		chatEmailResponseEntity.setInteractionId("0");

        Cliente cliente = ClienteMock.infoCliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		
		Mockito.when(chatDAO.enviarEmailGenesys(Matchers.any(ChatEmailRequestEntity.class))).thenReturn(chatEmailResponseEntity);
		
		Respuesta<Void> respuesta = chatBO.enviarMail(chatEmailInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void enviarMailInteractionNULL() throws DAOException {
						
		ChatEmailInDTO chatEmailInDTO = new ChatEmailInDTO();
		chatEmailInDTO.setEmail("dgalindez4@servexternos.isban-santander.com.ar");
		chatEmailInDTO.setMensaje("Test de envio de email");
		chatEmailInDTO.setUserData("NUP=00011903");

		ChatEmailResponseEntity chatEmailResponseEntity = new ChatEmailResponseEntity();
		chatEmailResponseEntity.setInteractionId(null);

        Cliente cliente = ClienteMock.infoCliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		
		Mockito.when(chatDAO.enviarEmailGenesys(Matchers.any(ChatEmailRequestEntity.class))).thenReturn(chatEmailResponseEntity);
		
		Respuesta<Void> respuesta = chatBO.enviarMail(chatEmailInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void enviarMailException() throws DAOException {
						
		ChatEmailInDTO chatEmailInDTO = new ChatEmailInDTO();
		chatEmailInDTO.setEmail("dgalindez4@servexternos.isban-santander.com.ar");
		chatEmailInDTO.setMensaje("Test de envio de email");
		chatEmailInDTO.setUserData("NUP=00011903");

        Cliente cliente = ClienteMock.infoCliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		
		Mockito.when(chatDAO.enviarEmailGenesys(Matchers.any(ChatEmailRequestEntity.class))).thenThrow(new DAOException());
		
		Respuesta<Void> respuesta = chatBO.enviarMail(chatEmailInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
}
