package ar.com.santanderrio.obp.servicios.home.web.manager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.AdministradorPermisos;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionAadvantageHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.referidos.bo.ReferidosBO;

@RunWith(MockitoJUnitRunner.class)
public class SeccionAadvantageHomeManagerTest {

	@InjectMocks
	private SeccionAadvantageHomeManagerImpl seccionAadvantageHomeManager;

	@Mock
	private SesionParametros sesionParametros;

	@Mock
	private AdministradorPermisos administradorPermisos;

	@Mock
	private ModuloPermisoBO moduloPermisoBO;
	
	@Mock
	private ReferidosBO referidosBO;
	

	@Test
	public void obtenerSeccionOKTest() {
		ModuloPermiso moduloPermiso = new ModuloPermiso();
		moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);

		Mockito.when(sesionParametros.getNumeroSocioAadvantage()).thenReturn("Advantage");
		Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_MILLAS))
				.thenReturn(moduloPermiso);
		Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_CAMBIO_GRUPO_AFINIDAD_POR_SUPERCLUB))
		.thenReturn(moduloPermiso);
		Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.REFERIDOS_COMPARTIR))
		.thenReturn(moduloPermiso);
		Mockito.when(referidosBO.tieneNupEnListado()).thenReturn(true);		
		Mockito.doNothing().when(administradorPermisos).addNewGrant(AccionController.IR_AADVANTAGE);
		SeccionView seccionAadvantage = seccionAadvantageHomeManager.obtenerSeccion();
		Assert.assertNotNull(seccionAadvantage);
	}

	@Test
	public void obtenerSeccionSinAdvantageTest() {
		SeccionView seccionAadvantage = seccionAadvantageHomeManager.obtenerSeccion();
		Assert.assertNull(seccionAadvantage);
	}

	
}
