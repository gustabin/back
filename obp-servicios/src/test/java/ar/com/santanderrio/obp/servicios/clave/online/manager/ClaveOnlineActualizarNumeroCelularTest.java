package ar.com.santanderrio.obp.servicios.clave.online.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.manager.impl.ClaveOnlineManagerImpl;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.impl.ClienteManagerImpl;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;

@RunWith(MockitoJUnitRunner.class)
public class ClaveOnlineActualizarNumeroCelularTest {

	/** The claveOnline manager. */
	@InjectMocks
	private ClaveOnlineManagerImpl claveOnlineManager;

	/** The clienteManager manager. */
	@InjectMocks
	private ClienteManagerImpl clienteManager;
	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

	/** The Respuesta factory. */
	@Spy
	private RespuestaFactory RespuestaFactory = new RespuestaFactory();
	/** The mya BO. */
	@Mock
	private MyaBO myaBO;
	@Mock
	private MensajeManager mensajeManager;

	@Mock
	private EstadisticaManager estadisticaManager;

	@Mock
	private SesionParametros sesionParametros;

	@Test
	public void enviarPantallaCelularErrorTest1() {
		CambioDatosContactoView cambioDatosContacto = new CambioDatosContactoView();
		Respuesta<CambioDatosContactoView> respuestaOk = this.claveOnlineManager.actualizarNumeroCelular(cambioDatosContacto);
		assertNotNull(respuestaOk);
		assertEquals(EstadoRespuesta.ERROR, respuestaOk.getEstadoRespuesta());

	}

}
