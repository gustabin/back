package ar.com.santanderrio.obp.servicios.home.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.manager.ClienteCitiMananager;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.impl.MensajeDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.SaldosConsolidadosCuentaDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.entitites.HomeDTO;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.CuentaHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeCuentaView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.home.web.view.SaldosConsolidadosView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class CuentaHomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentaHomeManagerTest {

	/** The cuenta home manager. */
	@InjectMocks
	private CuentaHomeManagerImpl cuentaHomeManager = new CuentaHomeManagerImpl();

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

	/** The cuenta BO. */
	@Mock
	private CuentaBO cuentaBO;

	/** The respuesta factory. */
	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The mensaje DAO. */
	@Mock
	private MensajeDAO mensajeDAO = new MensajeDAOImpl();

	/** The session parametros. */
	@Mock
	private SesionParametros sessionParametros;

	@Mock
	private ClienteCitiMananager clienteCitiMananager;

	@Mock
	private EstadisticaManager estadisticaManager;

   @Mock
   private ModuloPermisoBO moduloPermisoBO;
	   
	/**
	 * Obtener cajas cuentas test.
	 */
	@Test
	public void obtenerCajasCuentasTest() {

		Cliente cliente = new Cliente();
		Respuesta<SaldosConsolidadosCuentaDTO> respuestaSaldosConsolidadosCuentaDTO = new Respuesta<SaldosConsolidadosCuentaDTO>();
		SaldosConsolidadosCuentaDTO saldosConsolidadosCuentaDTO = new SaldosConsolidadosCuentaDTO();

		saldosConsolidadosCuentaDTO.setSaldoDolaresValue(new BigDecimal("100"));
		saldosConsolidadosCuentaDTO.setSaldoPesosValue(new BigDecimal("200"));
		respuestaSaldosConsolidadosCuentaDTO.setRespuesta(saldosConsolidadosCuentaDTO);
		respuestaSaldosConsolidadosCuentaDTO.setEstadoRespuesta(EstadoRespuesta.OK);

		HomeDTO homeDTO = new HomeDTO();
		homeDTO.setCantidadIngresos(1);

		Mockito.when(sessionParametros.getHomeDTO()).thenReturn(homeDTO);
		when(cuentaBO.obtenerSaldoConsolidadoCliente(Matchers.any(Cliente.class)))
		        .thenReturn(respuestaSaldosConsolidadosCuentaDTO);
		when(sesionCliente.getCliente()).thenReturn(cliente);

		GrupoCajaHomeView grupoCajaHomeView = cuentaHomeManager.obtenerGrupoElementos();
		assertNotNull(grupoCajaHomeView);

		List<Caja> cajas = grupoCajaHomeView.getCajas();
		assertTrue(cajas.size() > 0);

		CajaHomeCuentaView caja = (CajaHomeCuentaView) cajas.get(0);
		assertEquals(caja.getIcono(), "icono-cuenta");
		assertEquals(caja.getIsCuenta(), true);
		assertEquals(caja.getRecargar(), true);

	}

	/**
	 * Obtener cajas con ingreso primera vez.
	 */
	@Test
	public void obtenerCajasConIngresoPrimeraVez() {
		Respuesta<SaldosConsolidadosCuentaDTO> respuesta = new Respuesta<SaldosConsolidadosCuentaDTO>();
		SaldosConsolidadosCuentaDTO saldosConsolidadosDTO = new SaldosConsolidadosCuentaDTO();
		saldosConsolidadosDTO.setSaldoDolaresValue(new BigDecimal("10"));
		saldosConsolidadosDTO.setSaldoPesosValue(new BigDecimal("20"));
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(saldosConsolidadosDTO);
		Cliente cliente = new Cliente();
		HomeDTO homeDTO = new HomeDTO();
		homeDTO.setCantidadIngresos(1);
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(sessionParametros.getHomeDTO()).thenReturn(homeDTO);
		Mockito.when(cuentaBO.obtenerSaldoConsolidadoCliente(cliente)).thenReturn(respuesta);
		GrupoCajaHomeView grupoCajaHomeView = cuentaHomeManager.obtenerGrupoElementos();
		assertNotNull(grupoCajaHomeView);
		CajaHomeCuentaView caja = (CajaHomeCuentaView) grupoCajaHomeView.getCajas().get(0);
		assertTrue(caja.getIsCuenta());
	}

	/**
	 * Obtener cajas con ingreso mas de una vez.
	 */
	@Test
	public void obtenerCajasConIngresoMasDeUnaVez() {
		Respuesta<SaldosConsolidadosCuentaDTO> respuesta = new Respuesta<SaldosConsolidadosCuentaDTO>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Cliente cliente = new Cliente();
		HomeDTO homeDTO = new HomeDTO();
		homeDTO.setCantidadIngresos(2);
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(sessionParametros.getHomeDTO()).thenReturn(homeDTO);
		Mockito.when(cuentaBO.obtenerSaldoConsolidadoCliente(cliente)).thenReturn(respuesta);
		GrupoCajaHomeView grupoCajaHomeView = cuentaHomeManager.obtenerGrupoElementos();
		assertNotNull(grupoCajaHomeView);
		CajaHomeCuentaView caja = (CajaHomeCuentaView) grupoCajaHomeView.getCajas().get(0);
		assertTrue(caja.getIsCuenta());

	}

	/**
	 * Obtener saldos de cuentas actualizados.
	 * @throws IllegalAccessException 
	 */
	@Test
	public void obtenerSaldosCuentasTest() throws IllegalAccessException {

		Cliente cliente = new Cliente();

		cliente.setNombre("Jorge");
		cliente.setApellido1("Perez");
		cliente.setNumeroCUILCUIT("27-3828272-2");
		Respuesta<SaldosConsolidadosCuentaDTO> respuestaSaldos = new Respuesta<SaldosConsolidadosCuentaDTO>();
		getRespuestaMock(respuestaSaldos, EstadoRespuesta.OK, false, "");

		SaldosConsolidadosCuentaDTO saldosDTO = new SaldosConsolidadosCuentaDTO();
		saldosDTO.setSaldoDolaresValue(new BigDecimal("2222.22"));
		saldosDTO.setSaldoDolares("2222.22");
		saldosDTO.setSaldoPesosValue(new BigDecimal("3333.33"));
		saldosDTO.setSaldoPesos("2222.22");
		respuestaSaldos.setRespuesta(saldosDTO);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cuentaBO.obtenerSaldosConsolidadosActualizados(cliente)).thenReturn(respuestaSaldos);

		ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).thenReturn(moduloPermiso);
        
        Respuesta<SaldosConsolidadosView> respuesta = cuentaHomeManager.obtenerSaldosCuentas();

		SaldosConsolidadosView saldos = respuesta.getRespuesta();

		assertEquals(saldos.getSaldoDolares(), "2.222,22");
		assertEquals(saldos.getSaldoPesos(), "3.333,33");
	}

	/**
	 * Obtener saldos con error.
	 */
//	@Test
//	public void obtenerSaldosCuentasErrorTest() {
//
//		Cliente cliente = new Cliente();
//
//		cliente.setNombre("Jorge");
//		cliente.setApellido1("Perez");
//		cliente.setNumeroCUILCUIT("27-3828272-2");
//
//		Respuesta<SaldosConsolidadosCuentaDTO> respuestaSaldos = new Respuesta<SaldosConsolidadosCuentaDTO>();
//
//		String mensajeError = "No se pudo obtener los saldos.";
//		getRespuestaMock(respuestaSaldos, EstadoRespuesta.ERROR, true, mensajeError);
//
//		when(sesionCliente.getCliente()).thenReturn(cliente);
//		when(cuentaBO.obtenerSaldosConsolidadosActualizados(cliente)).thenReturn(respuestaSaldos);
//
//		Respuesta<SaldosConsolidadosView> respuesta = cuentaHomeManager.obtenerSaldosCuentas();
//
//		assertNotNull(respuesta);
//		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
//		assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "ERROR: " + mensajeError);
//	}

	/**
	 * Respuesta mockeada segun estado y mensaje esperado.
	 *
	 * @param <E>
	 *            the element type
	 * @param respuesta
	 *            the respuesta
	 * @param estado
	 *            the estado
	 * @param isVacia
	 *            the is vacia
	 * @param mensajeEsperado
	 *            the mensaje esperado
	 * @return the respuesta mock
	 */
	public <E> void getRespuestaMock(Respuesta<E> respuesta, EstadoRespuesta estado, boolean isVacia,
	        String mensajeEsperado) {
		respuesta.setEstadoRespuesta(estado);
		respuesta.setRespuestaVacia(isVacia);
		if (EstadoRespuesta.ERROR.equals(estado) || EstadoRespuesta.WARNING.equals(estado)) {
			List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();

			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_TABLERO_HOME.getDescripcion());
			itemMensajeRespuesta.setMensaje(estado.toString() + ": " + mensajeEsperado);

			itemsMensajeRespuesta.add(itemMensajeRespuesta);

			respuesta.setItemMensajeRespuesta(itemsMensajeRespuesta);
		}

	}

}
