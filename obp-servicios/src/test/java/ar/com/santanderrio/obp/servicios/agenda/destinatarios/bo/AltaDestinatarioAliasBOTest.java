/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

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
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.AltaDestinatarioAliasBOImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConsultarDatosTitularidadExtendidoResponseMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.CuentaDTOMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.TitularidadExtendidoMock;
import ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasCuentaNoActivaException;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasInexistenteEliminadoException;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasNoCoincidenMonedasException;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasNoExisteCuentaBanelcoException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class AltaDestinatarioAliasBOTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class AltaDestinatarioAliasBOTest {

	/** The alta destinatario alias BO. */
	@InjectMocks
	private AltaDestinatarioAliasBOImpl altaDestinatarioAliasBO;

	/** The alias cbu DAO. */
	@Mock
	private AliasCbuDAO aliasCbuDAO;

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	/**
	 * Continuar alta destinatario alias rio CC pesos test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioCCPesosTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCCPesos())));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
	}

	/**
	 * Continuar alta destinatario alias rio CC dolares test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioCCDolaresTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCCDolares())));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
	}

	/**
	 * Continuar alta destinatario alias rio CA pesos test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioCAPesosTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCAPesos())));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
	}

	/**
	 * Continuar alta destinatario alias rio CA dolares test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioCADolaresTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCADolares())));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
	}

	/**
	 * Continuar alta destinatario alias rio CU pesos origen test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioCUPesosOrigenTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCADolares())));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
	}

	/**
	 * Continuar alta destinatario alias otro banco CU pesos origen test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasOtroBancoCUPesosOrigenTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadNoRio(CuentaDTOMock.completarInfoCtaDestinoCADolaresOtrosBancos())));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.FALSE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
	}

	/**
	 * Continuar alta destinatario alias rio CU dolares origen test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioCUDolaresOrigenTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCADolares())));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
	}

	/**
	 * Continuar alta destinatario alias rio CA dolares origen test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioCADolaresOrigenTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCADolares())));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
	}

	/**
	 * Continuar alta destinatario alias rio C banelco origen test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioCBanelcoOrigenTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCADolares())));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
	}

	/**
	 * Continuar alta destinatario alias rio warning alias inexistente test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioWarningAliasInexistenteTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");
		Mensaje mensaje = new Mensaje();
		mensaje.setCodigo(null);

		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(
						ConsultarDatosTitularidadExtendidoResponseMock.completarInfoError("0110", "Alias no existe."));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuestaDTO.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ALIAS_INEXISTENTE.getDescripcion(),
				respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Continuar alta destinatario alias rio error sin cuenta activa test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioErrorSinCuentaActivaTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");
		Mensaje mensaje = new Mensaje();
		mensaje.setCodigo("1547");
		mensaje.setMensaje(
				"El alias que ingresaste es incorrecto. ( Cuenta del destinatario inactiva / Alias eliminado )");

		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoError("0160",
						"Alias con cuenta inactiva."));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ALIAS_CON_CTA_INACTIVA.getDescripcion(),
				respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Continuar alta destinatario alias rio error alias eliminado test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioErrorAliasEliminadoTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");
		Mensaje mensaje = new Mensaje();
		mensaje.setCodigo("1547");
		mensaje.setMensaje(
				"El alias que ingresaste es incorrecto. ( Cuenta del destinatario inactiva / Alias eliminado )");

		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(
						ConsultarDatosTitularidadExtendidoResponseMock.completarInfoError("0190", "Alias eliminado."));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ALIAS_ELIMINADO.getDescripcion(),
				respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Continuar alta destinatario alias rio error moneda seleccionada no
	 * coincide moneda cta destino test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioErrorMonedaSeleccionadaNoCoincideMonedaCtaDestinoTest()
			throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");
		Mensaje mensaje = new Mensaje();
		mensaje.setCodigo("1594");
		mensaje.setMensaje(
				"No es posible agregar a este destinatario debido a que la moneda seleccionada no se corresponde con la moneda del CBU ingresado.");

		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoError("36",
						"CBU ingresado no válido."));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
		Assert.assertEquals(TipoError.MONEDA_CTA_NO_COINCIDE_MONEDA_SELECCION.getDescripcion(),
				respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Continuar alta destinatario alias rio error cod no esperado test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@SuppressWarnings("unused")
	@Test(expected = BusinessException.class)
	public void continuarAltaDestinatarioAliasRioErrorCodNoEsperadoTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(
						ConsultarDatosTitularidadExtendidoResponseMock.completarInfoError("0100", "Alias eliminado."));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
	}

	/**
	 * Continuar alta destinatario alias rio corresponde A cta propia test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@Test
	public void continuarAltaDestinatarioAliasRioCorrespondeACtaPropiaTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cliente cliente = ClienteMock.completarInfoCliente();
		cliente.getCuentas().get(0).setCbu("1234567890123456789012");
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");
		Mensaje mensaje = new Mensaje();
		mensaje.setCodigo("1453");
		mensaje.setMensaje(
				"<b>¡Atención!</b><p>No se puede agendar tu cuenta propia. Por favor, ingresa otra cuenta.</p>");

		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		ConsultarDatosTitularidadExtendidoResponse completarInfoRio = ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
				.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCCPesosCtaPropia()));
		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(completarInfoRio);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(cliente, cuenta, "FuncionaAlias014", Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_ALIAS_USADO.getDescripcion(),
				respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Continuar alta destinatario alias rio dao exception test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@SuppressWarnings("unused")
	@Test(expected = BusinessException.class)
	public void continuarAltaDestinatarioAliasRioDaoExceptionTest() throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");
		Mensaje mensaje = new Mensaje();
		mensaje.setCodigo("1453");
		mensaje.setMensaje(
				"<b>¡Atención!</b><p>No se puede agendar tu cuenta propia. Por favor, ingresa otra cuenta.</p>");

		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenThrow(new DAOException("Servicio falló."));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.TRUE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
	}

	/**
	 * Continuar alta destinatario alias otro banco CU pesos origen response sin
	 * tipo cta definido test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException 
	 * @throws ValidacionAliasNoCoincidenMonedasException 
	 * @throws ValidacionAliasCuentaNoActivaException 
	 * @throws ValidacionAliasInexistenteEliminadoException 
	 */
	@SuppressWarnings("unused")
	@Test(expected = BusinessException.class)
	public void continuarAltaDestinatarioAliasOtroBancoCUPesosOrigenResponseSinTipoCtaDefinidoTest()
			throws DAOException, BusinessException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		cuenta.setNroSucursal("0072");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");

		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCBanelcoDolaresRio())));
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = altaDestinatarioAliasBO
				.continuarAltaDestinatarioAlias(ClienteMock.completarInfoCliente(), cuenta, "FuncionaAlias014",
						Boolean.FALSE,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
	}

	/**
	 * Obtener registro sesion.
	 *
	 * @return the registro sesion
	 */
	private RegistroSesion obtenerRegistroSesion() {
		RegistroSesion rs = new RegistroSesion();
		rs.setIp("180.166.12.93");
		return rs;
	}
}
