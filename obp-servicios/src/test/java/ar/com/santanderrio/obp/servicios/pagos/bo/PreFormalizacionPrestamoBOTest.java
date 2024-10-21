package ar.com.santanderrio.obp.servicios.pagos.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.PreFormalizacionPrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.dao.PreFormalizacionPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

/**
 * The Class PreFormalizacionPrestamoBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PreFormalizacionPrestamoBOTest {

	/** The pre formalizacion prestamo BO. */
	@InjectMocks
	private PreFormalizacionPrestamoBO preFormalizacionPrestamoBO = new PreFormalizacionPrestamoBOImpl();

	/** The pre formalizacion prestamo DAO. */
	@Mock
	private PreFormalizacionPrestamoDAO preFormalizacionPrestamoDAO;

	/** The respuesta BO. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The mensaje DAO. */
	@Mock
	private MensajeBO mensajeBO;

	/**
	 * Dado un prestamo, cuando se invoca al metodo "obtenerPreFormalizacion",
	 * obtengo respuesta de tipo PreFormalizacion con estado OK.
	 *
	 * @author florencia.n.martinez
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void dadoPrestamoCuandoInvocaObtenerPreFormalizacionObtengoRespuestaPreFormalizacionOK()
			throws BusinessException, DAOException {
		Mockito.when(preFormalizacionPrestamoDAO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
				.thenReturn(obtenerPreFormalizacion());

		Respuesta<PreFormalizacion> respuesta = preFormalizacionPrestamoBO.obtenerPreFormalizacion(obtenerPrestamo());

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		Assert.assertNull(respuesta.getItemsMensajeRespuesta());
		Assert.assertEquals("35008", respuesta.getRespuesta().getCodigoDestinoPrestamo());
		Assert.assertEquals("4", respuesta.getRespuesta().getPlazo());
		Assert.assertEquals("2017-09-14", respuesta.getRespuesta().getPrestamoDebitoAdherido().getFechaInicio());
		Assert.assertEquals("00000000600000000", respuesta.getRespuesta().getPrestamoDebitoAdherido().getMontoAPagar());
		Assert.assertEquals("000", respuesta.getRespuesta().getPrestamoDebitoAdherido().getNroSucursal());
		Assert.assertEquals("000000639170", respuesta.getRespuesta().getPrestamoDebitoAdherido().getNumero());
		Assert.assertEquals("0", respuesta.getRespuesta().getPrestamoDebitoAdherido().getTipo());
		Assert.assertEquals("00000000000056789", respuesta.getRespuesta().getPrestamoDebitoAdherido().getImpconce());

	}

	/**
	 * Test Mensaje Error.
	 *
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerPreFormalizacionErrorDAOTest() throws BusinessException, DAOException {
		@SuppressWarnings("unchecked")
		Respuesta<Mensaje> respuestaMje = mock(Respuesta.class);

		Mensaje mensaje = mock(Mensaje.class);

		when(respuestaMje.getRespuesta()).thenReturn(mensaje);

		when(mensaje.getMensaje()).thenReturn("Prueba Mensaje Error");

		Prestamo prestamo = mock(Prestamo.class);

		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		when(preFormalizacionPrestamoDAO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
				.thenThrow(new DAOException());

		@SuppressWarnings("rawtypes")
		Respuesta resp = preFormalizacionPrestamoBO.obtenerPreFormalizacion(prestamo);

		assertEquals(resp.getEstadoRespuesta(), EstadoRespuesta.ERROR);

		assertEquals(((ItemMensajeRespuesta) resp.getItemsMensajeRespuesta().iterator().next()).getTipoError(),
				"ERROR_CONSULTA_PREFORMALIZACION");
	}
	
	/**
	 * Obtiene el prestamo.
	 *
	 * @return the prestamo
	 */
	private Prestamo obtenerPrestamo() {
		Prestamo prestamo = new Prestamo();
		prestamo.setCuenta(CuentaMock.completarInfoCuentaPrestamo());
		return prestamo;
	}

	/**
	 * Obtiene la preFormalizacion.
	 *
	 * @return the pre formalizacion
	 */
	private PreFormalizacion obtenerPreFormalizacion() {
		PreFormalizacion preFormalizacion = new PreFormalizacion();
		preFormalizacion.setCodigoDestinoPrestamo("35008");
		preFormalizacion.setPlazo("4");
		preFormalizacion.getPrestamoDebitoAdherido()
				.setFechaInicio("0001-01-012017-09-142017-03-162017-06-142017-03-162017-06-14");
		preFormalizacion.getPrestamoDebitoAdherido().setMontoAPagar("00000000600000000");
		preFormalizacion.getPrestamoDebitoAdherido().setNroSucursal("000");
		preFormalizacion.getPrestamoDebitoAdherido().setNumero("000000639170");
		preFormalizacion.getPrestamoDebitoAdherido().setTipo("0");
		preFormalizacion.getPrestamoDebitoAdherido().setImpconce("00000000000056789");
		return preFormalizacion;
	}
}
