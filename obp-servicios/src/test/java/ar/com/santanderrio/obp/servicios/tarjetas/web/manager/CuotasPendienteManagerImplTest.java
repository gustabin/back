/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CuotasPendientesBO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesLineaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.CuotasPendienteManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesLineaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesView;

// TODO: Auto-generated Javadoc
/**
 * The Class CuotasPendienteManagerImplTest.
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class CuotasPendienteManagerImplTest {

	/** The manager. */
	@InjectMocks
	private CuotasPendienteManager manager = new CuotasPendienteManagerImpl();

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente = new SesionCliente();

	/** The sesion tarjetas. */
	@Mock
	private SesionTarjetas sesionTarjetas;

	/** The cliente. */
	@Mock
	private Cliente cliente;

	/** The cuenta BO. */
	@Mock
	private CuentaBO cuentaBO;

	/** The cuotas pendientes BO. */
	@Mock
	private CuotasPendientesBO cuotasPendientesBO;

	/** The estadistica manager. */
	@Mock
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

	/**
	 * Obtener cuotas pendientes OK test.
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void obtenerCuotasPendientesOKTest() throws IllegalAccessException, BusinessException {
		Cuenta cuenta = getCuenta();
		cuenta.setNroTarjetaCredito("00000000000000600460");
		cuenta.setTipoCuenta("7");
		cuenta.setClaseCuenta("R");
		cuenta.setCbu("30216574908");
		cuenta.setNroCuentaProducto("5679");
		cuenta.setNroSucursal("1234");

		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);

		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		TarjetaActivaView tarjetasView = new TarjetaActivaView();
		tarjetasView.setMarca("VISA");
		tarjetasView.setTarjetaActiva("1234-56789");
		tarjetasView.setNumeroCuenta("1234-56780");
		tarjetasView.setAlias("Mama");

		LimitesYDisponiblesDTO tarjActiva = new LimitesYDisponiblesDTO();
		List<LimitesYDisponiblesDTO> tarjetasActiva = new ArrayList<LimitesYDisponiblesDTO>();
		tarjetasActiva.add(tarjActiva);
		Mockito.when(sesionTarjetas.getLimitesYDisponibles()).thenReturn(tarjetasActiva);

		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroCuentaProducto("5679");
		Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
				.thenReturn(cuenta);

		Respuesta<CuotasPendientesDTO> respuesta = new Respuesta<CuotasPendientesDTO>();
		CuotasPendientesDTO cuotasPendientesDTO = new CuotasPendientesDTO();
		cuotasPendientesDTO.setTotalCuotasPendientes(new BigDecimal(5));
		cuotasPendientesDTO.setTarjetasCuotasPendientes(new ArrayList<CuotasPendientesTarjetaDTO>());
		respuesta.setRespuesta(cuotasPendientesDTO);
		Mockito.when(cuotasPendientesBO.obtenerCuotasPendientes(Matchers.any(IdentificacionCuenta.class),
				Matchers.any(Cliente.class))).thenReturn(respuesta);

		// Mock de estadisticaManager
		when(estadisticaManager.add("10689", "1")).thenReturn(true);

		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

		Respuesta<CuotasPendientesView> retorno = manager.obtenerCuotasPendientes(tarjetasView);
		// view esperado view generado
		Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());

	}

	/**
	 * Obtener cuotas pendientes cliente null.
	 */
	@Ignore
	@Test(expected = NullPointerException.class)
	public void obtenerCuotasPendientesClienteNull() {
		Mockito.when(sesionCliente.getCliente()).thenReturn(null);

		TarjetaActivaView tarjetasView = new TarjetaActivaView();
		tarjetasView.setMarca("marca");
		// Respuesta<CuotasPendientesView> respuesta =
		// manager.obtenerCuotasPendientes(tarjetasView);
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	private Cuenta getCuenta() {
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(getCliente());
		cuenta.setNroCuentaProducto("0000000338501392");
		cuenta.setNroSucursal("0033");
		return cuenta;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	private Cliente getCliente() {
		return mock(Cliente.class);
	}

	/**
	 * Genera el Json con OK con Cuotas Pendientes.
	 */
	@Test
	public void respuestaOKCuotasPendientes() {

		CuotasPendientesView cuotasPendientesView = new CuotasPendientesView();
		cuotasPendientesView.setTotalCuotasPendientes(new BigDecimal(2.500));

		CuotasPendientesTarjetaView tarjeta = new CuotasPendientesTarjetaView();
		tarjeta.setMarca("VISA");
		tarjeta.setNumero("XXXX-8569");
		tarjeta.setEsTitular(true);
		tarjeta.setTotal(new BigDecimal(1.500));

		CuotasPendientesLineaView linea = new CuotasPendientesLineaView();
		linea.setEstablecimiento("GARBARINO                          ");
		linea.setOperacion("0123456789");
		linea.setFecha(new Date());
		linea.setCantidadCuotas(3);
		linea.setCuotasPendientes(6);
		linea.setRestante(new BigDecimal(900.00));

		CuotasPendientesLineaView linea2 = new CuotasPendientesLineaView();
		linea2.setEstablecimiento("El Rápido S.A.");
		linea2.setOperacion("9876543210");
		linea2.setFecha(new Date());
		linea2.setCantidadCuotas(3);
		linea2.setCuotasPendientes(4);
		linea2.setRestante(new BigDecimal(600));

		List<CuotasPendientesLineaView> lineas = new ArrayList<CuotasPendientesLineaView>();
		lineas.add(linea);
		lineas.add(linea2);

		tarjeta.setLineasCuotasPendientes(lineas);

		CuotasPendientesTarjetaView tarjeta2 = new CuotasPendientesTarjetaView();
		tarjeta2.setMarca("VISA");
		tarjeta2.setNumero("XXXX-5075");
		tarjeta2.setTotal(new BigDecimal(1.000));
		tarjeta2.setEsTitular(false);
		tarjeta2.setNombreAdicional("Joaquín Blanco");

		tarjeta2.setLineasCuotasPendientes(lineas);

		List<CuotasPendientesTarjetaView> tarjetas = new ArrayList<CuotasPendientesTarjetaView>();
		tarjetas.add(tarjeta);
		tarjetas.add(tarjeta2);

		cuotasPendientesView.setTarjetasCuotasPendientes(tarjetas);

		Respuesta<CuotasPendientesView> respuesta = new Respuesta<CuotasPendientesView>();
		respuesta.setRespuesta(cuotasPendientesView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(false);

		Gson gson = new Gson();
		String json = gson.toJson(respuesta);
		Assert.assertNotNull(json);
		System.out.println(json);
	}

	/**
	 * Genera el Json con Error Sin Cuotas Pendientes.
	 */
	@Test
	public void crearRespuestaErrorSinCuotasPendientes() {
		Respuesta<CuotasPendientesView> respuesta = new Respuesta<CuotasPendientesView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuesta.setRespuestaVacia(true);

		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTipoError(TipoError.SIN_CUOTAS_PENDIENTES.getDescripcion());
		item.setMensaje(
				"<p>¡No tenés cuotas pendientes!</p><p>Aprovechá 12 cuotas sin interés en Despegar.com con tus Tarjetas Santander.</p>");

		respuesta.add(item);

		Gson gson = new Gson();
		String json = gson.toJson(respuesta);
		Assert.assertNotNull(json);
		System.out.println(json);

	}

	/**
	 * Genera el Json con Error Generico.
	 */
	@Test
	public void crearRespuestaErrorGenerico() {
		Respuesta<CuotasPendientesView> respuesta = new Respuesta<CuotasPendientesView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuesta.setRespuestaVacia(true);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		item.setMensaje(
				"Lo sentimos. Hubo un error con la carga de tus cuotas pendientes. Por favor, intentalo en unos minutos.");

		respuesta.add(item);

		Gson gson = new Gson();
		String json = gson.toJson(respuesta);
		Assert.assertNotNull(json);
		System.out.println(json);
	}

	/**
	 * Genera el Json con OK con Cuotas Pendientes.
	 */
	@Test
	public void copyPropertiesTest() {
		CuotasPendientesView view = new CuotasPendientesView(obtenerCuotasPendientesDTO());
		Assert.assertNotNull(view);
	}

	/**
	 * Obtener cuotas pendientes DTO.
	 *
	 * @return the cuotas pendientes DTO
	 */
	public CuotasPendientesDTO obtenerCuotasPendientesDTO() {
		CuotasPendientesDTO dto = new CuotasPendientesDTO();
		List<CuotasPendientesTarjetaDTO> tarjetasCuotasPendientes = new ArrayList<CuotasPendientesTarjetaDTO>();
		CuotasPendientesTarjetaDTO cuotasPendientesTarjetaDTO = new CuotasPendientesTarjetaDTO();
		cuotasPendientesTarjetaDTO.setEsTitular(true);
		List<CuotasPendientesLineaDTO> lineasCuotasPendientes = new ArrayList<CuotasPendientesLineaDTO>();
		CuotasPendientesLineaDTO cuotasPendientesLineaDTO = new CuotasPendientesLineaDTO();
		cuotasPendientesLineaDTO.setCantidadCuotas(4);
		cuotasPendientesLineaDTO.setCuotasPendientes(2);
		cuotasPendientesLineaDTO.setEstablecimiento("GARBARINO                          ");
		cuotasPendientesLineaDTO.setFecha(new Date());
		cuotasPendientesLineaDTO.setOperacion(null);
		cuotasPendientesLineaDTO.setRestante(new BigDecimal(224.49));
		lineasCuotasPendientes.add(cuotasPendientesLineaDTO);
		cuotasPendientesTarjetaDTO.setLineasCuotasPendientes(lineasCuotasPendientes);
		cuotasPendientesTarjetaDTO.setMarca("VISA");
		cuotasPendientesTarjetaDTO.setNombreAdicional("GARAY O/FEDERICO N");
		cuotasPendientesTarjetaDTO.setNumero("4050710080543775");
		cuotasPendientesTarjetaDTO.setTotal(new BigDecimal(224.49));
		tarjetasCuotasPendientes.add(cuotasPendientesTarjetaDTO);
		dto.setTarjetasCuotasPendientes(tarjetasCuotasPendientes);
		dto.setTotalCuotasPendientes(new BigDecimal(150.00));
		return dto;
	}

}
