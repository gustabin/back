package ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.CuentaSaldoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPinEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo.impl.MovimientosBancaPrivadaBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.mock.MovimientosMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

@RunWith(MockitoJUnitRunner.class)
public class MovimientosBancaPrivadaBOTest {

	@InjectMocks
	MovimientosBancaPrivadaBOImpl movimientosBancaPrivadaBO;
	
	@Mock
	private CredentialSecurityFactory credentialSecurityFactory;
	
	@Mock
	private CuentaSaldoDAO cuentaSaldoDAO;
	
	@Mock
	private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;
	
	
	@Test
	public void obtenerMovimientosOK () throws BusinessException, SQLException, DAOException {
		
		//When
    	ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
    	consultaUltimosMovimientosView.setMoneda(DivisaEnum.PESO);
    	consultaUltimosMovimientosView.setNumero("250-352350/8");
    	consultaUltimosMovimientosView.setRango(RangoFechaEnum.DEFAULT);
    	consultaUltimosMovimientosView.setTipoCuenta(TipoCuenta.CUENTA_UNICA);
    	
    	Cliente cliente = mock(Cliente.class);
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuenta.setNroCuentaProducto("3523508");
    	List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
    	listaCuentas.add(cuenta);
    	
    	Credential credencial = new Credential();
    	credencial.setUsuario("USUARIO");
    	credencial.setPassword("PASS");
    	
    	when(cuentasBancaPrivadaBO.obtenerCuentasBancaPrivada(Matchers.any(Cliente.class))).thenReturn(listaCuentas);
		when(credentialSecurityFactory.getCredentialBySistema(Matchers.anyString())).thenReturn(credencial);
    	when(cuentaSaldoDAO.verMovimientosCuentaBancaPrivada(Matchers.any(MovimientosCuentaBPinEntity.class))).thenReturn(MovimientosMock.enviarListaMovimientosArmada());
		
		//Then
		MovimientosCuentaBPOutEntity respuesta = movimientosBancaPrivadaBO.obtenerMovimientos(consultaUltimosMovimientosView, cliente, cuenta);
		
		//Expected
		Assert.assertNotNull(respuesta);
		
	}
	
	
	@Test (expected = BusinessException.class)
	public void obtenerMovimientosErrorNoHayCuentas() throws BusinessException, SQLException, DAOException {
		
		//When
    	ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
    	consultaUltimosMovimientosView.setMoneda(DivisaEnum.PESO);
    	consultaUltimosMovimientosView.setNumero("250-352350/8");
    	consultaUltimosMovimientosView.setRango(RangoFechaEnum.DEFAULT);
    	consultaUltimosMovimientosView.setTipoCuenta(TipoCuenta.CUENTA_UNICA);
    	
    	Cliente cliente = mock(Cliente.class);
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuenta.setNroCuentaProducto("352432445");
    	List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
    	listaCuentas.add(cuenta);
    	
    	Credential credencial = new Credential();
    	credencial.setUsuario("USUARIO");
    	credencial.setPassword("PASS");
    	
    	when(cuentasBancaPrivadaBO.obtenerCuentasBancaPrivada(Matchers.any(Cliente.class))).thenReturn(listaCuentas);
		when(credentialSecurityFactory.getCredentialBySistema(Matchers.anyString())).thenReturn(credencial);
    	when(cuentaSaldoDAO.verMovimientosCuentaBancaPrivada(Matchers.any(MovimientosCuentaBPinEntity.class))).thenReturn(MovimientosMock.enviarListaMovimientosArmada());
		
		//Then
		MovimientosCuentaBPOutEntity respuesta = movimientosBancaPrivadaBO.obtenerMovimientos(consultaUltimosMovimientosView, cliente, null);
		
		//Expected
		Assert.assertNull(respuesta);
		
		
	}
	
	
	@Test
	public void limpiarCache() {
		
		//When
		Cliente cliente = mock(Cliente.class);
		
		//Then
		movimientosBancaPrivadaBO.limpiarCache(cliente);
		
	}
	
}