package ar.com.santanderrio.obp.servicios.home.bo;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.home.bo.impl.ConsultasControllerHomeBOImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class ConsultasControllerHomeBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsultasControllerHomeBOTest {

    /** The consultas controller home BO. */
    @InjectMocks
    private ConsultasControllerHomeBOImpl consultasControllerHomeBO = new ConsultasControllerHomeBOImpl();

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;
    
    /** The sesionParametros*/
    @Mock
    private SesionParametros sesionParametros;
    
    /** The sesionCliente*/
    @Mock
    private SesionCliente sesionCliente;
    
    /** The moduloPermisoBO*/
	@Mock
	private ModuloPermisoBO moduloPermisoBO;
    
    @Before
    public void init() {
		
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_FINANCIAL_HEALTH)).thenReturn(Mockito.mock(ModuloPermiso.class));
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_FINANCIAL_HEALTH).tienePermisoDeVisibilidad()).thenReturn(true);
		when(sesionParametros.isAplicaRefi()).thenReturn(true);
		
	}
    
    /**
     * Menu consultas test.
     */
    @Test
    public void menuConsultasTest() {

        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        Cuenta cuenta2 = new Cuenta();

        cuenta2.setTipoCuenta(TipoCuenta.TARJETA_MONEDERO.getCodigo().toString());
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuentas.add(cuenta);
        cuentas.add(cuenta2);
        cliente.setCuentas(cuentas);

        when(cuentaBO.hasCuentasMonetariasActivas(Matchers.any(Cliente.class))).thenReturn(true);
        when(cuentaBO.hasCuentasMonetariasCerradas(Matchers.any(Cliente.class))).thenReturn(true);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        assertTrue(consultasControllerHomeBO.aplicaAhorrosBeneficios(cliente));
        assertTrue(consultasControllerHomeBO.aplicaComprobantes(cliente));
        assertTrue(consultasControllerHomeBO.aplicaCuentas(cliente));
        assertTrue(consultasControllerHomeBO.aplicaPrestamos(cliente));
        assertTrue(consultasControllerHomeBO.aplicaResumenImpositivo(cliente));
        assertTrue(consultasControllerHomeBO.aplicaSeguros(cliente));
        assertTrue(consultasControllerHomeBO.aplicaTarjetaMonedero(cliente));
        assertTrue(consultasControllerHomeBO.aplicaTarjetas(cliente));
        assertTrue(consultasControllerHomeBO.aplicaRecuperaciones());

    }

}
