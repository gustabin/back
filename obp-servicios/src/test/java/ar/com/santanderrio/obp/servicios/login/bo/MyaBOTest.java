package ar.com.santanderrio.obp.servicios.login.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCodOperacionEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaCodigoRetornoErrorException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaMailRegistradoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaServiceException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaTelefonoRegistradoException;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.dao.ContratosMyaDAO;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ConsultaCliente;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ConsultaClienteParam;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.RegistroClienteParam;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.impl.MyaBOImpl;
import ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMyaIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.suscripciones.dao.SuscripcionesParametrosDAO;

/**
 * The Class MyaBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class MyaBOTest {

	/** The mya BO. */
	@InjectMocks
	private MyaBOImpl myaBO;

	/** The mya WSDAO. */
	@Mock
	private MyaWSDAO myaWSDAO;

	/** The contratos mya DAO. */
	@Mock
	private ContratosMyaDAO contratosMyaDAO;

	/** The respuesta factory. */
	@Spy
	private RespuestaFactory respuestaFactory;

	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private ContratosMyaDAO contratosMyaDao;

	@Mock
	private SuscripcionesParametrosDAO suscripcionesParametrosDAO;

	/**
	 * Obtener cliente BD test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws MyaServiceException
	 *             the mya service exception
	 */
	@Test
	public void obtenerClienteBDTest() throws DAOException, MyaServiceException {
		Cliente cliente = new Cliente();
		ConsultaCliente consultaCliente = new ConsultaCliente();
		MyaDTOOut myaDTOOUT = new MyaDTOOut();

		cliente.setDni("12345678");
		cliente.setNup("12345678");
		cliente.setFechaNacimiento("12345678");
		myaDTOOUT.setCelular("12-12345678");
		myaDTOOUT.setClienteEstadoEnum(ClienteEstadoEnum.NO_SUSCRIPTO);
		myaDTOOUT.setEmail("asd@asd.com");
		myaDTOOUT.setTipoCompaniaEnum(TipoCompaniaEnum.CLARO);
		myaDTOOUT.setClienteEstadoEnum(ClienteEstadoEnum.SUSCRIPTO_ACTIVO);
		consultaCliente.setAceptacionContrato("1");

		when(contratosMyaDAO.consultaClientes(Matchers.any(ConsultaClienteParam.class))).thenReturn(consultaCliente);
		when(myaWSDAO.getStatusCliente(Matchers.any(MyaDTOIn.class))).thenReturn(myaDTOOUT);

		Respuesta<CredencialesMya> respuesta = new Respuesta<CredencialesMya>();
		respuesta = myaBO.obtenerEstadoMya(cliente, false);

		assertNotNull(respuesta);
		assertNotNull(respuesta.getRespuesta());
	}

	/**
	 * Alta Cliente OK
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void altaClienteTrueTest() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setApellido1("SANCHEZ");
		cliente.setNombre("PEPE");

		Mockito.when(contratosMyaDAO.crearRegistro(Matchers.any(RegistroClienteParam.class))).thenReturn(Boolean.TRUE);
		Respuesta<Void> respuesta = myaBO.altaCliente(cliente);
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	/**
	 * Alta Cliente con Error
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws IllegalAccessException
	 */
	@Test
	public void altaClienteConErrorTest() throws DAOException, IllegalAccessException {
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");

		Cliente cliente = new Cliente();
		cliente.setApellido1("SANCHEZ");
		cliente.setNombre("PEPE");

		Mockito.when(contratosMyaDAO.crearRegistro(Matchers.any(RegistroClienteParam.class))).thenReturn(Boolean.FALSE);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.LOGIN_ERROR_TOTAL)).thenReturn(mensaje);
		FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);
		Respuesta<Void> respuesta = myaBO.altaCliente(cliente);
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	}

	/**
	 * Alta Cliente con Error
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws IllegalAccessException
	 * @throws MyaServiceException
	 * @throws MyaCodigoRetornoErrorException
	 * @throws MyaMailRegistradoException
	 * @throws MyaTelefonoRegistradoException
	 */
	@Test
	public void updateDestinosOKTest() throws DAOException, IllegalAccessException, MyaCodigoRetornoErrorException,
			MyaServiceException, MyaMailRegistradoException, MyaTelefonoRegistradoException {
		Cliente cliente = new Cliente();
		CredencialesMyaIn datos = new CredencialesMyaIn();
		Mockito.doNothing().when(myaWSDAO).updateDestino(Matchers.any(MyaDTOIn.class));
		Respuesta<Void> respuesta = myaBO.updateDestinos(datos, cliente);
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@Test
	public void registrarClienteMyaTest() {
		Cliente cliente = new Cliente();
		CredencialesMyaIn datos = new CredencialesMyaIn();

		datos.setTipoOperacionCelular(MyaCodOperacionEnum.MODIFICACION);
		datos.setTipoOperacionEmail(MyaCodOperacionEnum.ALTA);
		datos.setCompaniaSeleccionada(TipoCompaniaEnum.MOVISTAR);
		;
		Respuesta<Void> respuesta = myaBO.registrarClienteMya(cliente, datos);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void actualizaContratoTest() {

		Cliente cliente = new Cliente();

		Respuesta<Void> respuesta = myaBO.actualizaContrato(cliente, false);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void actualizarEstadoMyaTest() {

		Cliente cliente = new Cliente();

		Respuesta<Void> respuesta = myaBO.actualizarEstadoMya(cliente);

		assertNotNull(respuesta);

	}

}