package ar.com.santanderrio.obp.servicios.refinancing.mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.bo.impl.ConsultasControllerHomeBOImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

@RunWith(MockitoJUnitRunner.class)
public class RefinancingVisibilityTest {
	
    /** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;
	
	/** The consultasControllerHomeBO*/
	@InjectMocks
	private ConsultasControllerHomeBO consultasControllerHomeBO = new ConsultasControllerHomeBOImpl();
	
	/** The moduloPermisoBO*/
	@Mock
	private ModuloPermisoBO moduloPermisoBO;
	
	/** The sesionCliente*/
	@Mock
	private SesionCliente sesionCliente;
	
	@Before
    public void init() {
		
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_FINANCIAL_HEALTH)).thenReturn(Mockito.mock(ModuloPermiso.class));
		when(sesionCliente.getCliente()).thenReturn(Mockito.mock(Cliente.class));
		when(sesionParametros.isAplicaRefi()).thenReturn(true);
		
	}
	
    @Test
    public void shouldShowRefinancing() {
    	
    	when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_FINANCIAL_HEALTH).tienePermisoDeVisibilidad()).thenReturn(true);
    	
    	assertTrue(consultasControllerHomeBO.aplicaRecuperaciones());
    	
    }
    
    @Test
    public void shouldntShowRefinancing() {
    	
    	when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_FINANCIAL_HEALTH).tienePermisoDeVisibilidad()).thenReturn(false);
    	
    	assertFalse(consultasControllerHomeBO.aplicaRecuperaciones());
    	
    }
}
