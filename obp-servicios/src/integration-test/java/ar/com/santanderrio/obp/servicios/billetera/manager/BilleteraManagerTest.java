package ar.com.santanderrio.obp.servicios.billetera.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.bo.BilleteraBO;
import ar.com.santanderrio.obp.servicios.billetera.dto.ValidarUsuarioBilleteraDTO;

import ar.com.santanderrio.obp.servicios.billetera.web.manager.impl.BilleteraManagerImpl;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraInView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;

import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.impl.MensajeManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.junit.Assert;


/**
 * The Class BilleteraManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class BilleteraManagerTest {
	
	@InjectMocks
	/** The billetera manager. */
	private BilleteraManagerImpl billeteraManager = new BilleteraManagerImpl();
	
	@Spy
	@InjectMocks
	/** The respuesta factory. */
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	@Mock
    /** The session parametros. */
    private SesionParametros sessionParametros;
    
	@Mock
    /** The billetera BO. */
    protected BilleteraBO billeteraBO;
	
	@Mock
    /** The respuesta validar usuario billetera dto. */
    protected Respuesta<ValidarUsuarioBilleteraDTO> respuestaValidarUsuarioBilleteraDto;
    
	@Mock
    /** The validar usuario billetera DTO. */
    protected ValidarUsuarioBilleteraDTO validarUsuarioBilleteraDTO;
	
	@Mock
    /** The mensaje manager. */
    protected MensajeManagerImpl mensajeManager = new  MensajeManagerImpl();
        
	@Mock
    /** The sesion cliente. */
    protected SesionCliente sesionCliente;
    
	@Test
    /**
     * Validar usuario Y cuenta mono producto.
     */
	public void validarUsuarioYCuentaMonoProducto(){
		
	ValidarUsuarioBilleteraInView viewRequest = new ValidarUsuarioBilleteraInView();

	Respuesta<ValidarUsuarioBilleteraView> respuestaValidarUsuarioBilletera = new Respuesta<ValidarUsuarioBilleteraView>();
	respuestaValidarUsuarioBilletera.setEstadoRespuesta(EstadoRespuesta.OK);
	
	RegistroSesion registroSesion = Mockito.mock(RegistroSesion.class);
	
	Cliente cliente = new Cliente();
	cliente.setNombre("Diego");
	cliente.setApellido1("Maradona");
	cliente.setTieneTrjCred(true);
	
	Cuenta cuenta1 = new Cuenta();
	cuenta1.setTipoCuenta("07");
	
	Cuenta cuenta2 = new Cuenta();
	cuenta2.setTipoCuenta("42");
	
	List<Cuenta> cuentas = new ArrayList<Cuenta>();
	cuentas.add(cuenta1);
	cuentas.add(cuenta2);
	cliente.setCuentas(cuentas);
	
    Mensaje mensajeFeedback = new Mensaje();
    mensajeFeedback.setMensaje("");
    
	sesionCliente.setCliente(cliente);

	Mockito.when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
	Mockito.when(billeteraBO.validarUsuario(Matchers.anyInt(), Matchers.any(RegistroSesion.class))).thenReturn(respuestaValidarUsuarioBilleteraDto);
	Mockito.when(respuestaValidarUsuarioBilleteraDto.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);
	Mockito.when(respuestaValidarUsuarioBilleteraDto.getRespuesta()).thenReturn(validarUsuarioBilleteraDTO);
	Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
	Mockito.when(respuestaFactory.crearRespuestaOk( Matchers.any(ValidarUsuarioBilleteraView.class))).thenReturn(respuestaValidarUsuarioBilletera);
	
	respuestaValidarUsuarioBilletera = billeteraManager.validarUsuario(viewRequest);
	
	Assert.assertEquals(respuestaValidarUsuarioBilletera.getRespuesta().isEsMonoProducto() , Boolean.TRUE);
	
	}
	
	@Test
    /**
     * Validar usuario Y cuenta no mono producto.
     */
	public void validarUsuarioYCuentaNoMonoProducto(){
		
	ValidarUsuarioBilleteraInView viewRequest = new ValidarUsuarioBilleteraInView();

	Respuesta<ValidarUsuarioBilleteraView> respuestaValidarUsuarioBilletera = new Respuesta<ValidarUsuarioBilleteraView>();
	respuestaValidarUsuarioBilletera.setEstadoRespuesta(EstadoRespuesta.OK);
	
	RegistroSesion registroSesion = Mockito.mock(RegistroSesion.class);
	
	Cliente cliente = new Cliente();
	cliente.setNombre("Diego");
	cliente.setApellido1("Maradona");
	cliente.setTieneTrjCred(true);
	
	Cuenta cuenta1 = new Cuenta();
	cuenta1.setTipoCuenta("07");
	
	Cuenta cuenta2 = new Cuenta();
	cuenta2.setTipoCuenta("42");
	
	Cuenta cuenta3 = new Cuenta();
	cuenta2.setTipoCuenta("00");
	
	List<Cuenta> cuentas = new ArrayList<Cuenta>();
	cuentas.add(cuenta1);
	cuentas.add(cuenta2);
	cuentas.add(cuenta3);
	
	cliente.setCuentas(cuentas);
	
    Mensaje mensajeFeedback = new Mensaje();
    mensajeFeedback.setMensaje("");
    
	sesionCliente.setCliente(cliente);

	Mockito.when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
	Mockito.when(billeteraBO.validarUsuario(Matchers.anyInt(), Matchers.any(RegistroSesion.class))).thenReturn(respuestaValidarUsuarioBilleteraDto);
	Mockito.when(respuestaValidarUsuarioBilleteraDto.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);
	Mockito.when(respuestaValidarUsuarioBilleteraDto.getRespuesta()).thenReturn(validarUsuarioBilleteraDTO);
	Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
	Mockito.when(respuestaFactory.crearRespuestaOk( Matchers.any(ValidarUsuarioBilleteraView.class))).thenReturn(respuestaValidarUsuarioBilletera);
	
	respuestaValidarUsuarioBilletera = billeteraManager.validarUsuario(viewRequest);
	
	Assert.assertEquals(respuestaValidarUsuarioBilletera.getRespuesta().isEsMonoProducto() , Boolean.FALSE);
	
	}
	

	
}
