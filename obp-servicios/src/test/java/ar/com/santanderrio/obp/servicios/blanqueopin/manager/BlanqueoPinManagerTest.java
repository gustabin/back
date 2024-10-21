package ar.com.santanderrio.obp.servicios.blanqueopin.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.blanqueopin.bo.BlanqueoPinBO;
import ar.com.santanderrio.obp.servicios.blanqueopin.web.manager.impl.BlanqueoPinManagerImpl;
import ar.com.santanderrio.obp.servicios.blanqueopin.web.view.BlanqueoPinInicioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.view.BlanqueoPinView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaDTO;

@RunWith(MockitoJUnitRunner.class)
public class BlanqueoPinManagerTest {

	@InjectMocks
	BlanqueoPinManagerImpl blanqueoPinManager;
	
	@Mock
	private SesionCliente sesionCliente;
	
	@Mock
	private SesionParametros sesionParametros;
	
	@Mock
	private BlanqueoPinBO blanqueoPinBO;
	
	@Mock
	private MensajeManager msjManager;
	
	@Mock
	AutentificacionManager autentificacionManager;
	
    @Mock
    private ContadorIntentos contador = new ContadorIntentos(3);
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
	@Mock
	private MensajeBO mensajeBO;
	
    @Spy 
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	@Test
	public void inicioBlanquePinOK() {
		
		//When
		Cliente cliente = mock(Cliente.class);
		List<DetalleTarjetaDTO> tarjetasDTO = crearTarjetasDTOMock();
		
		Mensaje mensajeInicio = new Mensaje();
		mensajeInicio.setMensaje("MENSAJE INICIO BLANQUEO PIN");
		Mensaje mensajeNumerico = new Mensaje();
		mensajeNumerico.setMensaje("MENSAJE BLANQUEO PIN NUMERICO");
		Mensaje mensajeAlfabetico = new Mensaje();
		mensajeAlfabetico.setMensaje("MENSAJE BLANQUEO PIN ALFABETICO");
		Mensaje mensajeAmbas = new Mensaje();
		mensajeAmbas.setMensaje("MENSAJE BLANQUEO PIN AMBAS");
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(blanqueoPinBO.obtenerTarjetasBanelco(Matchers.any(Cliente.class))).thenReturn(tarjetasDTO);
		when(msjManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.BLANQUEOPIN_INICIO)).thenReturn(mensajeInicio);
		when(msjManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.BLANQUEOPIN_NUMERICA)).thenReturn(mensajeNumerico);
		when(msjManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.BLANQUEOPIN_ALFABETICA)).thenReturn(mensajeAlfabetico);
		when(msjManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.BLANQUEOPIN_AMBAS)).thenReturn(mensajeAmbas);

		//Then
		Respuesta<BlanqueoPinInicioView> respuesta = blanqueoPinManager.inicioBlanqueoPin();
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}
	
	@Test
	public void blanqueoPinRSAOK() throws IllegalAccessException, BusinessException {
		
		//When
		Cliente cliente = mock(Cliente.class);
		Respuesta<Object> respuestaRSA = new Respuesta<Object>();
		respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
		BlanqueoPinView blanqueoPinView = new BlanqueoPinView();
		blanqueoPinView.setNumeroTarjeta("XXXX-1234");
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		blanqueoPinView.setDesafio(autentificacionDTO);
		Respuesta<AutentificacionDTO> rstaAutentificacion = new Respuesta<AutentificacionDTO>();
		rstaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        FieldUtils.writeDeclaredField(blanqueoPinManager, "valorDesafio", "2", true);
		Mensaje mensajeInicio = new Mensaje();
		mensajeInicio.setMensaje("MENSAJE INICIO BLANQUEO PIN");
		Mensaje mensajeOK = new Mensaje();
		mensajeOK.setMensaje("MENSAJE OK BLANQUEO PIN");
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt())).thenReturn(respuestaRSA);
		when(autentificacionManager.ejecutarValidacionRSA(autentificacionDTO)).thenReturn(rstaAutentificacion);
        when(sesionParametros.getContador()).thenReturn(contador);
        when(sesionParametros.getContador().permiteReintentar()).thenReturn(true);
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeInicio);
		when(blanqueoPinBO.blanquearPin(Matchers.any(Cliente.class), Matchers.any(BlanqueoPinView.class), Matchers.anyString())).thenReturn(Boolean.TRUE);
		when(msjManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeOK);
		
		//Then
		Respuesta<BlanqueoPinView> respuesta = blanqueoPinManager.blanquearPinRSA(blanqueoPinView);
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
		
	}
	
	
	@Test
	public void blanqueoPinRSAErrorPuedeReintentar() throws IllegalAccessException, BusinessException {
		
		//When
		Cliente cliente = mock(Cliente.class);
		Respuesta<Object> respuestaRSA = new Respuesta<Object>();
		respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
		BlanqueoPinView blanqueoPinView = new BlanqueoPinView();
		blanqueoPinView.setNumeroTarjeta("XXXX-1234");
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		blanqueoPinView.setDesafio(autentificacionDTO);
		Respuesta<AutentificacionDTO> rstaAutentificacion = new Respuesta<AutentificacionDTO>();
		rstaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        FieldUtils.writeDeclaredField(blanqueoPinManager, "valorDesafio", "2", true);
		Mensaje mensajeInicio = new Mensaje();
		mensajeInicio.setMensaje("MENSAJE INICIO BLANQUEO PIN");
		Mensaje mensajeOK = new Mensaje();
		mensajeOK.setMensaje("MENSAJE OK BLANQUEO PIN");
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt())).thenReturn(respuestaRSA);
		when(autentificacionManager.ejecutarValidacionRSA(autentificacionDTO)).thenReturn(rstaAutentificacion);
        when(sesionParametros.getContador()).thenReturn(contador);
        when(sesionParametros.getContador().permiteReintentar()).thenReturn(true);
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeInicio);
		when(blanqueoPinBO.blanquearPin(Matchers.any(Cliente.class), Matchers.any(BlanqueoPinView.class), Matchers.anyString())).thenReturn(Boolean.FALSE);
		when(msjManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeOK);
		
		//Then
		Respuesta<BlanqueoPinView> respuesta = blanqueoPinManager.blanquearPinRSA(blanqueoPinView);
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
		
	}
	
	
	@Test
	public void blanqueoPinRSAErrorNoPuedeReintentar() throws IllegalAccessException, BusinessException {
		
		//When
		Cliente cliente = mock(Cliente.class);
		Respuesta<Object> respuestaRSA = new Respuesta<Object>();
		respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
		BlanqueoPinView blanqueoPinView = new BlanqueoPinView();
		blanqueoPinView.setNumeroTarjeta("XXXX-1234");
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		blanqueoPinView.setDesafio(autentificacionDTO);
		Respuesta<AutentificacionDTO> rstaAutentificacion = new Respuesta<AutentificacionDTO>();
		rstaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        FieldUtils.writeDeclaredField(blanqueoPinManager, "valorDesafio", "2", true);
		Mensaje mensajeInicio = new Mensaje();
		mensajeInicio.setMensaje("MENSAJE INICIO BLANQUEO PIN");
		Mensaje mensajeOK = new Mensaje();
		mensajeOK.setMensaje("MENSAJE OK BLANQUEO PIN");
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt())).thenReturn(respuestaRSA);
		when(autentificacionManager.ejecutarValidacionRSA(autentificacionDTO)).thenReturn(rstaAutentificacion);
        when(sesionParametros.getContador()).thenReturn(contador);
        when(sesionParametros.getContador().permiteReintentar()).thenReturn(false);
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeInicio);
		when(blanqueoPinBO.blanquearPin(Matchers.any(Cliente.class), Matchers.any(BlanqueoPinView.class), Matchers.anyString())).thenReturn(Boolean.FALSE);
		when(msjManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeOK);
		
		//Then
		Respuesta<BlanqueoPinView> respuesta = blanqueoPinManager.blanquearPinRSA(blanqueoPinView);
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
		
	}
	
	private List<DetalleTarjetaDTO> crearTarjetasDTOMock() {
		
		List<DetalleTarjetaDTO> tarjetasDTO = new ArrayList<DetalleTarjetaDTO>();
		DetalleTarjetaDTO tarjeta1 = new DetalleTarjetaDTO();
		tarjeta1.setMarca("VISA");
		tarjeta1.setNroTarjeta("XXXX-1234");
		tarjetasDTO.add(tarjeta1);
		
		DetalleTarjetaDTO tarjeta2 = new DetalleTarjetaDTO();
		tarjeta2.setMarca("AMEX");
		tarjeta2.setNroTarjeta("XXXX-5678");
		tarjetasDTO.add(tarjeta2);
		return tarjetasDTO;
	}
	
}
