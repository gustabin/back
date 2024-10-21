/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.dao.ResumenesCitiDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandDAOException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.ResumenAnteriorBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ResumenMensualTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ResumenAnteriorViewResponse;

/**
 * The Class ResumenAnteriorBOTest.
 *
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
public class ResumenAnteriorBOTest {

	/** The resumen anterior manager. */
	@InjectMocks
	private ResumenAnteriorBOImpl resumenAnteriorBO;

	/** The ondemand DAO. */
	@Mock
	private OndemandDAO ondemandDAO;

	@Mock
	private ResumenesCitiDAO resumenesCitiDAO;
	/** The respuesta factory. */
	@Mock
	private RespuestaFactory respuestaFactory;

	/** The sesion tarjetas. */
	@Mock
	private SesionTarjetas sesionTarjetas;

	/** The cuenta BO. */
	@Mock
	private CuentaBO cuentaBO;

	/**
	 * Convertir formato fecha test.
	 */
	@Test
	public void convertirFormatoFechaTest() {
		Date fecha = ISBANStringUtils.formatearFecha("10/02/2016");

		String convertirFormatoFecha = TarjetaBOUtils.convertirFechaAlEspaniol(fecha);
		// Validar
		Assert.assertNotNull(convertirFormatoFecha);
	}

	/**
	 * identificacionCuenta formato aceptado:
	 * sucursal-numeroDeCuenta/digitoVerificador,regex: [0-9]{3}-[0-9]{6}/[0-9]
	 * ejemplo: 033-000231/4.
	 *
	 * @param nroCuentaProducto
	 *            the nro cuenta producto
	 * @return the identificacion cuenta
	 */
	public IdentificacionCuenta obtenerIdentificacionCuenta(String nroCuentaProducto) {
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroCuentaProducto(nroCuentaProducto);
		return identificacionCuenta;
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
	 * Obtener resumenes anteriores ok test.
	 *
	 * @throws BusinessException
	 *             the business exception
	 * @throws WSODException
	 *             the WSOD exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void obtenerResumenesAnterioresOkTest()
			throws BusinessException, WSODException, OnDemandDAOException, IllegalAccessException {

		List<ResumenMensualTarjetaDTO> listaResumen = new ArrayList<ResumenMensualTarjetaDTO>();
		Respuesta<ResumenAnteriorViewResponse> resResumenAnteriorView = new Respuesta<ResumenAnteriorViewResponse>();
		ResumenMensualTarjetaDTO resumenMensualDTO = new ResumenMensualTarjetaDTO();
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(new Cliente());

		resumenMensualDTO.setCarpeta("C://Folder");
		resumenMensualDTO.setFecha(new Date());
		listaResumen.add(resumenMensualDTO);
		cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
		cuenta.setCbu("12345678910111213141516");
		cuenta.setNroTarjetaCredito("12312468127");
		cuenta.setNroCuentaProducto("0000000047766149");
		cuenta.setNroSucursal("131");

		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroCuentaProducto("47766149");
		identificacionCuenta.setNroSucursal("131");

		resResumenAnteriorView.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(ondemandDAO.obtenerListaResumenesAnterioresTarjeta(Matchers.any(ResumenParams.class)))
				.thenReturn(listaResumen);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(ResumenAnteriorViewResponse.class),
				Matchers.any(ResumenAnteriorViewResponse.class))).thenReturn(resResumenAnteriorView);
		Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
				.thenReturn((AbstractCuenta) cuenta);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadDiasFechaHasta", "5", true);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadMeses", "2", true);
		Respuesta<ResumenAnteriorViewResponse> res = resumenAnteriorBO.obtenerResumenesAnteriores(identificacionCuenta,
				getCliente(), "131-4776614/9");
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

	}

	/**
	 * Obtener resumenes anteriores ok not amex test.
	 *
	 * @throws BusinessException
	 *             the business exception
	 * @throws WSODException
	 *             the WSOD exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void obtenerResumenesAnterioresOkNotAmexTest()
			throws BusinessException, WSODException, OnDemandDAOException, IllegalAccessException {

		List<ResumenMensualTarjetaDTO> listaResumen = new ArrayList<ResumenMensualTarjetaDTO>();
		Respuesta<ResumenAnteriorViewResponse> resResumenAnteriorView = new Respuesta<ResumenAnteriorViewResponse>();
		ResumenMensualTarjetaDTO resumenMensualDTO = new ResumenMensualTarjetaDTO();
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(new Cliente());

		resumenMensualDTO.setCarpeta("C://Folder");
		resumenMensualDTO.setFecha(new Date());
		listaResumen.add(resumenMensualDTO);
		cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
		cuenta.setCbu("12345678910111213141516");
		cuenta.setNroTarjetaCredito("12312468127");

		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroCuentaProducto("5679");

		resResumenAnteriorView.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(ondemandDAO.obtenerListaResumenesAnterioresTarjeta(Matchers.any(ResumenParams.class)))
				.thenReturn(listaResumen);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(ResumenAnteriorViewResponse.class),
				Matchers.any(ResumenAnteriorViewResponse.class))).thenReturn(resResumenAnteriorView);
		Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
				.thenReturn((AbstractCuenta) cuenta);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadDiasFechaHasta", "5", true);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadMeses", "2", true);
		Respuesta<ResumenAnteriorViewResponse> res = resumenAnteriorBO.obtenerResumenesAnteriores(identificacionCuenta,
				getCliente(), "131-4776614/9");
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

	}

	/**
	 * Obtener datos resumen mensual tarjeta WSOD exception test.
	 *
	 * @throws BusinessException
	 *             the business exception
	 * @throws WSODException
	 *             the WSOD exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test(expected = BusinessException.class)
	public void obtenerDatosResumenMensualTarjetaWSODExceptionTest()
			throws BusinessException, WSODException, OnDemandDAOException, IllegalAccessException {

		List<ResumenMensualTarjetaDTO> listaResumen = new ArrayList<ResumenMensualTarjetaDTO>();
		Respuesta<ResumenAnteriorViewResponse> resResumenAnteriorView = new Respuesta<ResumenAnteriorViewResponse>();
		ResumenMensualTarjetaDTO resumenMensualDTO = new ResumenMensualTarjetaDTO();
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(new Cliente());

		resumenMensualDTO.setCarpeta("C://Folder");
		resumenMensualDTO.setFecha(new Date());
		listaResumen.add(resumenMensualDTO);
		cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
		cuenta.setCbu("12345678910111213141516");
		cuenta.setNroTarjetaCredito("12312468127");

		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroCuentaProducto("5679");

		resResumenAnteriorView.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(ondemandDAO.obtenerListaResumenesAnterioresTarjeta(Matchers.any(ResumenParams.class)))
				.thenThrow(new WSODException(null));
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(ResumenAnteriorViewResponse.class),
				Matchers.any(ResumenAnteriorViewResponse.class))).thenReturn(resResumenAnteriorView);
		Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
				.thenReturn((AbstractCuenta) cuenta);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadDiasFechaHasta", "5", true);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadMeses", "2", true);
		Respuesta<ResumenAnteriorViewResponse> res = resumenAnteriorBO.obtenerResumenesAnteriores(identificacionCuenta,
				getCliente(), "131-4776614/9");
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

	}

	/**
	 * Validar fechas else test.
	 *
	 * @throws BusinessException
	 *             the business exception
	 * @throws WSODException
	 *             the WSOD exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void validarFechasElseTest()
			throws BusinessException, WSODException, OnDemandDAOException, IllegalAccessException {

		List<ResumenMensualTarjetaDTO> listaResumen = new ArrayList<ResumenMensualTarjetaDTO>();
		Respuesta<ResumenAnteriorViewResponse> resResumenAnteriorView = new Respuesta<ResumenAnteriorViewResponse>();
		ResumenMensualTarjetaDTO resumenMensualDTO = new ResumenMensualTarjetaDTO();
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(new Cliente());

		resumenMensualDTO.setCarpeta("C://Folder");
		resumenMensualDTO.setFecha(new Date());
		listaResumen.add(resumenMensualDTO);
		cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
		cuenta.setCbu("12345678910111213141516");
		cuenta.setNroTarjetaCredito("12312468127");
		cuenta.setNroCuentaProducto("0000000047766149");
		cuenta.setNroSucursal("131");

		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroCuentaProducto("5679");

		resResumenAnteriorView.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(ondemandDAO.obtenerListaResumenesAnterioresTarjeta(Matchers.any(ResumenParams.class)))
				.thenReturn(listaResumen);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(ResumenAnteriorViewResponse.class),
				Matchers.any(ResumenAnteriorViewResponse.class))).thenReturn(resResumenAnteriorView);
		Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
				.thenReturn((AbstractCuenta) cuenta);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadDiasFechaHasta", "-2", true);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadMeses", "-13", true);
		Respuesta<ResumenAnteriorViewResponse> res = resumenAnteriorBO.obtenerResumenesAnteriores(identificacionCuenta,
				getCliente(), "131-4776614/9");
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

	}

	/**
	 * Obtener resumenes anteriores on demand DAO exception test.
	 *
	 * @throws BusinessException
	 *             the business exception
	 * @throws WSODException
	 *             the WSOD exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void obtenerResumenesAnterioresOnDemandDAOExceptionTest()
			throws BusinessException, WSODException, OnDemandDAOException, IllegalAccessException {

		List<ResumenMensualTarjetaDTO> listaResumen = new ArrayList<ResumenMensualTarjetaDTO>();
		Respuesta<Object> resResumenAnteriorView = new Respuesta<Object>();
		ResumenMensualTarjetaDTO resumenMensualDTO = new ResumenMensualTarjetaDTO();
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(new Cliente());
		cuenta.setNroCuentaProducto("0000000047766149");
		cuenta.setNroSucursal("131");

		resumenMensualDTO.setCarpeta("C://Folder");
		resumenMensualDTO.setFecha(new Date());
		listaResumen.add(resumenMensualDTO);
		cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
		cuenta.setCbu("12345678910111213141516");
		cuenta.setNroTarjetaCredito("12312468127");

		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroCuentaProducto("0000000047766149");
		identificacionCuenta.setNroSucursal("131");

		resResumenAnteriorView.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(ondemandDAO.obtenerListaResumenesAnterioresTarjeta(Matchers.any(ResumenParams.class)))
				.thenThrow(new OnDemandDAOException());
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(resResumenAnteriorView);
		Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
				.thenReturn((AbstractCuenta) cuenta);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadDiasFechaHasta", "5", true);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadMeses", "2", true);
		Respuesta<ResumenAnteriorViewResponse> res = resumenAnteriorBO.obtenerResumenesAnteriores(identificacionCuenta,
				getCliente(), "131-4776614/9");
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
	}

	/**
	 * Obtener resumenes anteriores business exception test.
	 *
	 * @throws BusinessException
	 *             the business exception
	 * @throws WSODException
	 *             the WSOD exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test(expected = BusinessException.class)
	public void obtenerResumenesAnterioresBusinessExceptionTest()
			throws BusinessException, WSODException, OnDemandDAOException, IllegalAccessException {

		List<ResumenMensualTarjetaDTO> listaResumen = new ArrayList<ResumenMensualTarjetaDTO>();
		Respuesta<Object> resResumenAnteriorView = new Respuesta<Object>();
		ResumenMensualTarjetaDTO resumenMensualDTO = new ResumenMensualTarjetaDTO();

		resumenMensualDTO.setCarpeta("C://Folder");
		resumenMensualDTO.setFecha(new Date());
		listaResumen.add(resumenMensualDTO);

		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroCuentaProducto("5679");

		resResumenAnteriorView.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(ondemandDAO.obtenerListaResumenesAnterioresTarjeta(Matchers.any(ResumenParams.class)))
				.thenThrow(new WSODException(null));
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(resResumenAnteriorView);

		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadDiasFechaHasta", "5", true);
		FieldUtils.writeDeclaredField(resumenAnteriorBO, "cantidadMeses", "2", true);
		Respuesta<ResumenAnteriorViewResponse> res = resumenAnteriorBO.obtenerResumenesAnteriores(identificacionCuenta,
				getCliente(), "131-4776614/9");
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
	}

	/**
	 * Crear respuesta erronea sin resumenes anteriores test.
	 */
	@Test
	public void crearRespuestaErroneaSinResumenesAnterioresTest() {
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(new Cliente());

		cuenta.getCliente().setNup("faffsad");
		cuenta.setTipoCuenta("13241234");
		cuenta.setClaseCuenta("12341234");
		cuenta.setNroTarjetaCredito("2134151");
		cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);

		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(new Respuesta<Object>());

		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(new Respuesta<Object>());
		Respuesta<ResumenAnteriorViewResponse> res = resumenAnteriorBO
				.crearRespuestaErroneaSinResumenesAnteriores(cuenta);

		Assert.assertEquals(res.getRespuesta().getMarca(), "AMEX");
	}

}
