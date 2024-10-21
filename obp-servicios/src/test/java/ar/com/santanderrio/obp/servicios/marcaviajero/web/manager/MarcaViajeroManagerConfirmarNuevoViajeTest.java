package ar.com.santanderrio.obp.servicios.marcaviajero.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.marcaviajero.bo.MarcaViajeroBO;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.ViajeHabilitado;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.ConfirmarNuevoViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.DestinoView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.NuevoViajeTarjetaView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.TarjetaAsociadaView;

/**
 * The Class CuentaManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class MarcaViajeroManagerConfirmarNuevoViajeTest {

	/** The cuenta manager. */
	@InjectMocks
	private MarcaViajeroManagerImpl marcaViajeroManager = new MarcaViajeroManagerImpl();

	/** The marca viajero BO. */
	@Mock
	private MarcaViajeroBO marcaViajeroBO;

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	/** The estadistica manager. */
	@Mock
	private EstadisticaManagerImpl estadisticaManager;

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

	/**
	 * Confirmar nuevo viaje test.
	 *
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void confirmarNuevoViajeTest() throws BusinessException, DAOException {
		mockCliente();
		mockearRespuestaConfirmacionBO(EstadoRespuesta.OK);
		mockearCredencialesMya();

		ConfirmarNuevoViajeView confirmarNuevoViaje = new ConfirmarNuevoViajeView();

		// Tarjetas List
		List<NuevoViajeTarjetaView> tarjetasList = new ArrayList<NuevoViajeTarjetaView>();

		NuevoViajeTarjetaView nuevoViaje = new NuevoViajeTarjetaView();

		nuevoViaje.setNombre("PIZZUTI/NORBERTO F");

		List<TarjetaAsociadaView> tarjetaAsociadaList = new ArrayList<TarjetaAsociadaView>();

		TarjetaAsociadaView tarjetaAsociada = new TarjetaAsociadaView();

		tarjetaAsociada.setAlias("");
		tarjetaAsociada.setMarca("MASTER");
		tarjetaAsociada.setNumero("5281");
		tarjetaAsociada.setIsTitular(Boolean.TRUE);

		tarjetaAsociadaList.add(tarjetaAsociada);

		nuevoViaje.setTarjeta(tarjetaAsociadaList);

		tarjetasList.add(nuevoViaje);

		// Destinos List

		List<DestinoView> destinos = new ArrayList<DestinoView>();

		DestinoView destino = new DestinoView();

		destino.setCodigo("AG");
		destino.setDescripcion("ANTIGUA Y BARBUDA");

		destinos.add(destino);

		confirmarNuevoViaje.setDestinosSeleccionados(destinos);
		confirmarNuevoViaje.setEmail("HERNAN_BORSANI@HOTMAIL.COM");
		confirmarNuevoViaje.setFechaDesde("07/05/2018");
		confirmarNuevoViaje.setFechaHasta("07/05/2018");
		confirmarNuevoViaje.setTarjetasSeleccionadas(tarjetasList);

		Respuesta<Void> respuestaNuevoViajeView = marcaViajeroManager.confirmarNuevoViaje(confirmarNuevoViaje);

		assertNotNull(respuestaNuevoViajeView);
		assertEquals(EstadoRespuesta.OK, respuestaNuevoViajeView.getEstadoRespuesta());

	}

	/**
	 * Mockear respuesta nuevo viaje.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 */
	private void mockearRespuestaConfirmacionBO(EstadoRespuesta estadoRespuesta) {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		respuesta.setEstadoRespuesta(estadoRespuesta);
		when(marcaViajeroBO.confirmarNuevoViaje(Matchers.any(ViajeHabilitado.class), 
		        Matchers.any(Cliente.class))).thenReturn(respuesta);

	}

	/**
	 * Mockear credenciales mya.
	 */
	private void mockearCredencialesMya() {
		CredencialesMya credencialesMya = new CredencialesMya();
		credencialesMya.setEmail("juan@prueba.org");
		when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
		ContadorIntentos contador = new ContadorIntentos(1);
		when(sesionParametros.getContador()).thenReturn(contador);
		
		        
	}

	/**
	 * Mock sesion cliente.
	 */
	private void mockCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Pedro");
		cliente.setApellido1("Caseres");
		cliente.setApellido2("Guiterrez");
		cliente.setDni("1234");
		cliente.setTipoDocumento("N");
		when(sesionCliente.getCliente()).thenReturn(cliente);
	}

}
