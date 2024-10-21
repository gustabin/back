package ar.com.santanderrio.obp.servicios.ultimosmovimientos.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConfig;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.impl.MensajeDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.MovimientosBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.MovimientosBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.dao.UltimosMovimientosDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoConsultaMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.UltimosMovimientosDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;

/**
 * The Class UltimosMovimientosBOTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		MovimientosBOTest.MovimientosBOTestConfiguration.class, CacheConfig.class })
public class MovimientosBOTest {

	/** The Constant INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO. */
	private static final String INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO = "01";

	@Autowired
	private MovimientosBO movimientosBO;

	@Autowired
	private UltimosMovimientosDAO ultimosMovimientosDAO;

	@Autowired
	private SesionParametros sesionParametros;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class UltimosMovimientosDAOTestConfiguration.
	 */
	@Configuration
	public static class MovimientosBOTestConfiguration {

		/**
		 * Operacion cliente compra DAO.
		 *
		 * @return the ultimos movimientos DAO
		 */
		@Bean
		public MovimientosBO movimientosBO() {
			return new MovimientosBOImpl();
		}

		@Bean
		public UltimosMovimientosDAO ultimosMovimientosDAO() {
			return Mockito.mock(UltimosMovimientosDAO.class);
		}

		@Bean
		public RespuestaFactory respuestaFactory() {
			return new RespuestaFactory();
		}

		@Bean
		public MensajeBO mensajeBO() {
			return Mockito.mock(MensajeBO.class);
		}

		@Bean
		public SesionParametros sesionParametros() {
			return new SesionParametros();
		}

		/**
		 * Property configurer.
		 *
		 * @return the property sources placeholder configurer
		 */
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
			return new PropertySourcesPlaceholderConfigurer();
		}
	}

	/** The mensaje dao. */
	@Mock
	private MensajeDAO mensajeDao = new MensajeDAOImpl();

	/**
	 * Obtener ultimos movimientos test.
	 *
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void obtenerMovimientosTest() throws BusinessException, DAOException, IllegalAccessException {

		List<String> stringArrayList = new ArrayList<String>();
		stringArrayList.add("0101");
		stringArrayList.add("0103");

		ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
		consultaUltimosMovimientos.setCuenta(crearCuenta());
		consultaUltimosMovimientos.setCantidadMovimientos(0);
		consultaUltimosMovimientos.setFechaDesde(new Date());
		consultaUltimosMovimientos.setFechaHasta(new Date());
		consultaUltimosMovimientos.setImporteDesde(new BigDecimal(0));
		consultaUltimosMovimientos.setImporteHasta(new BigDecimal(999));
		consultaUltimosMovimientos.setPalabraClave("");
		consultaUltimosMovimientos.setIsTraspasoAutomatico(true);
		consultaUltimosMovimientos.setTipoConsulta(TipoConsultaMovimientos.MOVIMIENTOS_DEL_DIA);
		consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.DEFAULT);
		consultaUltimosMovimientos.setMoneda(DivisaEnum.PESO);

		UltimosMovimientosDTO ultimosMovimientos = new UltimosMovimientosDTO();
		ultimosMovimientos.setMovimientos(crearDatosCuenta());
		Respuesta<UltimosMovimientosDTO> respuestaMovimientosBO = new Respuesta<UltimosMovimientosDTO>();
		respuestaMovimientosBO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaMovimientosBO.setRespuesta(ultimosMovimientos);

		UltimosMovimientosDTO ultimosMovimientosVacio = new UltimosMovimientosDTO();
		when(ultimosMovimientosDAO.consultarExtractoUltimosMovimientos(consultaUltimosMovimientos)).thenReturn(
				ultimosMovimientos, ultimosMovimientosVacio, ultimosMovimientosVacio, ultimosMovimientosVacio,
				ultimosMovimientosVacio);

		when(ultimosMovimientosDAO.consultarExtractoUltimosMovimientos(Matchers.any(ConsultaUltimosMovimientos.class)))
				.thenReturn(ultimosMovimientos);
		Respuesta<UltimosMovimientosDTO> respuesta = movimientosBO.obtenerMovimientos(consultaUltimosMovimientos);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

		respuesta = movimientosBO.obtenerMovimientos(consultaUltimosMovimientos);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

		movimientosBO.limpiarCache(consultaUltimosMovimientos);

		respuesta = movimientosBO.obtenerMovimientos(consultaUltimosMovimientos);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		assertNotNull(respuesta.getRespuesta());
	}

	/**
	 * Crear cuenta.
	 *
	 * @return the abstract cuenta
	 */
	public AbstractCuenta crearCuenta() {
		AbstractCuenta cuenta = new Cuenta();
		Cliente cliente = new Cliente();
		cliente.setNup("32423");
		cuenta.setCliente(cliente);
		cuenta.setNroCuentaProducto("0000000004043627");
		cuenta.setCbu("0720000788000040436272");
		cuenta.setNroSucursal("0000");
		return cuenta;
	}

	/**
	 * Operar dias date.
	 *
	 * @param fecha
	 *            the fecha
	 * @param days
	 *            the days
	 * @return the date
	 */
	public static Date operarDiasDate(Date fecha, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		c.add(Calendar.DATE, days);
		fecha.setTime(c.getTime().getTime());
		return fecha;
	}

	/**
	 * Crear datos cuenta.
	 *
	 * @return the list
	 */
	private List<Movimiento> crearDatosCuenta() {

		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		Movimiento movimiento = null;
		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("200");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("200");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("10082016");
		movimiento.setFechaValor("10082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);

		movimiento = new Movimiento();
		movimiento.setFechaMovimiento("12082016");
		movimiento.setFechaValor("12082016");
		movimiento.setImporteMovimiento("100");
		movimiento.setSaldoCuenta("1000");
		movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
		movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
		movimiento.setHoraMovimiento("1000");
		movimiento.setSucursalOrigen("000");
		movimientos.add(movimiento);
		return movimientos;
	}

}
