package ar.com.santanderrio.obp.servicios.comun.util.manager;

import java.util.Calendar;

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

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.util.view.MicroFEAccessView;
import ar.com.santanderrio.obp.servicios.comun.view.FechaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ComunManagerTest {

	/** The comun manager. */
	@InjectMocks
	private ComunManager comunManager = new ComunManagerImpl();

	@Mock
    private EstadisticaManager estadisticaManager;

	@Mock
	private SesionParametros sesionParametros;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Init
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void accesoMFOk() {
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		MicroFEAccessView microFEAccessView = new MicroFEAccessView("gotoCuentas", "/app/current/cuentas");
		Respuesta<Void> resultado = comunManager.accesoMF(microFEAccessView);
		Assert.assertNotNull(resultado);
	}

	@Test
	public void cancelarDesafioEnCursoOK() {
		comunManager.cancelarDesafioEnCurso();
		Assert.assertNull(sesionParametros.getDesafioEnCurso());
		Assert.assertFalse(sesionParametros.isExisteDesafioEnCurso());
	}

	@Test
	public void grabarEstadisticaVisualizacionResumenTyCOK() {
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		Respuesta<Void> resultado = comunManager.grabarEstadisticaVisualizacionResumenTyC();
		Assert.assertNotNull(resultado);
	}

	@Test
	public void getFechaActualOK() {
		FechaView fechaView = comunManager.getFechaActual();
		Calendar current = Calendar.getInstance();
		Assert.assertEquals(Integer.valueOf(current.get(Calendar.DAY_OF_MONTH)), fechaView.getDay());
		Assert.assertEquals(Integer.valueOf(current.get(Calendar.MONTH) + 1), fechaView.getMonth());
		Assert.assertEquals(Integer.valueOf(current.get(Calendar.YEAR)), fechaView.getYear());
	}

}
