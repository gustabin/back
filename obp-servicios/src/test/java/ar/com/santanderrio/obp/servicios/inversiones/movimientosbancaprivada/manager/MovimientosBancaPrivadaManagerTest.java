package ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.SessionMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaMovimientosCuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.MovimientosBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.manager.impl.MovimientosBancaPrivadaManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.mock.MovimientosMock;

@RunWith(MockitoJUnitRunner.class)
public class MovimientosBancaPrivadaManagerTest {

	
	@InjectMocks
	MovimientosBancaPrivadaManagerImpl movimientosBancaPrivadaManager;
	
	@Mock
	private SesionCliente sesionCliente;
	
	@Mock
	private SesionParametros sesionParametros;
	
	@Mock
	MovimientosBancaPrivadaBO movimientosBancaPrivadaBO;
	
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
	SessionMovimientos sesionMovimientos;
	
    @Spy 
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    
    @Test
    public void obtenerMovimientosPrimeraVezOKPesos() throws BusinessException {
    	
    	//When
    	ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
    	consultaUltimosMovimientosView.setMoneda(DivisaEnum.PESO);
    	consultaUltimosMovimientosView.setNumero("250-352350/8");
    	consultaUltimosMovimientosView.setRango(RangoFechaEnum.DEFAULT);
    	consultaUltimosMovimientosView.setTipoCuenta(TipoCuenta.CUENTA_UNICA);
    	
		ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
		consultaUltimosMovimientos.setNumeroConsulta(0);
    	
		RegistroSesion registroSesion = new RegistroSesion();
		registroSesion.setDispositivo("phone");
	      Cuenta cuenta = new Cuenta();
	        cuenta.setProductoAltair("07");
	        when(movimientosBancaPrivadaBO.obtenerCuentaCliente(Matchers.any(Cliente.class), 
	                Matchers.any(ConsultaUltimosMovimientosView.class))).thenReturn(cuenta);
    	when(movimientosBancaPrivadaBO.obtenerMovimientos(Matchers.any(ConsultaUltimosMovimientosView.class), Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(MovimientosMock.enviarListaMovimientosArmada());
    	when(sesionMovimientos.getFiltro()).thenReturn(consultaUltimosMovimientos);
    	when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
    	
    	//Then
    	Respuesta<MovimientoView> respuesta = movimientosBancaPrivadaManager.obtenerMovimientosPrimeraVez(consultaUltimosMovimientosView); 
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());    	
    	
    }
	
	
    @Test
    public void obtenerMovimientosPrimeraVezOKDolares() throws BusinessException {
    	
    	//When
    	ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
    	consultaUltimosMovimientosView.setMoneda(DivisaEnum.DOLAR);
    	consultaUltimosMovimientosView.setNumero("250-352350/8");
    	consultaUltimosMovimientosView.setRango(RangoFechaEnum.DEFAULT);
    	consultaUltimosMovimientosView.setTipoCuenta(TipoCuenta.CUENTA_UNICA);
    	
		ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
		consultaUltimosMovimientos.setNumeroConsulta(0);
    	
		RegistroSesion registroSesion = new RegistroSesion();
		registroSesion.setDispositivo("phone");
		Cuenta cuenta = new Cuenta();
        cuenta.setProductoAltair("07");
        when(movimientosBancaPrivadaBO.obtenerCuentaCliente(Matchers.any(Cliente.class), 
	                Matchers.any(ConsultaUltimosMovimientosView.class))).thenReturn(cuenta);
    	when(movimientosBancaPrivadaBO.obtenerMovimientos(Matchers.any(ConsultaUltimosMovimientosView.class), Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(MovimientosMock.enviarListaMovimientosArmadaDolares());
    	when(sesionMovimientos.getFiltro()).thenReturn(consultaUltimosMovimientos);
    	when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
    	
    	//Then
    	Respuesta<MovimientoView> respuesta = movimientosBancaPrivadaManager.obtenerMovimientosPrimeraVez(consultaUltimosMovimientosView); 
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());    	
    	
    }
    
    
    @Test
    public void obtenerMovimientosPrimeraVezSinMovimientos() throws BusinessException {
    	
    	//When
    	ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
    	consultaUltimosMovimientosView.setMoneda(DivisaEnum.PESO);
    	consultaUltimosMovimientosView.setNumero("250-352350/8");
    	consultaUltimosMovimientosView.setRango(RangoFechaEnum.DEFAULT);
    	consultaUltimosMovimientosView.setTipoCuenta(TipoCuenta.CUENTA_UNICA);
    	
		ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
		consultaUltimosMovimientos.setNumeroConsulta(0);
    	
		RegistroSesion registroSesion = new RegistroSesion();
		registroSesion.setDispositivo("phone");
		
		MovimientosCuentaBPOutEntity movimientosCuentaBPOutEntity = new MovimientosCuentaBPOutEntity();
		movimientosCuentaBPOutEntity.setRespuesta(new ArrayList<RtaMovimientosCuentaBP>());
		
		String codigoErrorNoHayMovimientos = "10506";
		String codigoErrorNoHayMovimientosPersonalizado = "10508";
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("NO HAY MOVIMIENTOS");
		
		movimientosBancaPrivadaBO.obtenerCuentaCliente(sesionCliente.getCliente(), consultaUltimosMovimientosView);
		Cuenta cuenta = new Cuenta();
		cuenta.setProductoAltair("07");
        when(movimientosBancaPrivadaBO.obtenerCuentaCliente(Matchers.any(Cliente.class), 
		        Matchers.any(ConsultaUltimosMovimientosView.class))).thenReturn(cuenta);
    	when(movimientosBancaPrivadaBO.obtenerMovimientos(Matchers.any(ConsultaUltimosMovimientosView.class), Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(movimientosCuentaBPOutEntity);
    	when(sesionMovimientos.getFiltro()).thenReturn(consultaUltimosMovimientos);
    	when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
    	when(mensajeBO.obtenerMensajePorCodigo(codigoErrorNoHayMovimientos)).thenReturn(mensaje);
    	when(mensajeBO.obtenerMensajePorCodigo(codigoErrorNoHayMovimientosPersonalizado)).thenReturn(mensaje);

    	//Then
    	Respuesta<MovimientoView> respuesta = movimientosBancaPrivadaManager.obtenerMovimientosPrimeraVez(consultaUltimosMovimientosView); 
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());    
    	Assert.assertEquals("SIN_MOVIMIENTOS", respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    	
    }
    
    
    @Test
    public void obtenerMasMovimientos() throws BusinessException {
    	
    	//When
		ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
		consultaUltimosMovimientos.setMoneda(DivisaEnum.PESO);
		consultaUltimosMovimientos.setNumeroCuenta("250-352350/8");
		consultaUltimosMovimientos.setTipoCuenta(TipoCuenta.CUENTA_UNICA);
		consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.DEFAULT);
		consultaUltimosMovimientos.setNumeroConsulta(1);
    	consultaUltimosMovimientos.setFechaDesde(new Date());
    	consultaUltimosMovimientos.setFechaHasta(new Date());
		
		RegistroSesion registroSesion = new RegistroSesion();
		registroSesion.setDispositivo("desktop");
		Cuenta cuenta = new Cuenta();
        cuenta.setProductoAltair("07");
        when(movimientosBancaPrivadaBO.obtenerCuentaCliente(Matchers.any(Cliente.class), 
                Matchers.any(ConsultaUltimosMovimientosView.class))).thenReturn(cuenta);
    	when(sesionMovimientos.getFiltro()).thenReturn(consultaUltimosMovimientos);
    	when(movimientosBancaPrivadaBO.obtenerMovimientos(Matchers.any(ConsultaUltimosMovimientosView.class), Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(MovimientosMock.enviarListaMovimientosArmada());
    	when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

    	//Then
    	Respuesta<MovimientoView> respuesta = movimientosBancaPrivadaManager.obtenerMasMovimientos();
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta()); 
    	
    }

}
