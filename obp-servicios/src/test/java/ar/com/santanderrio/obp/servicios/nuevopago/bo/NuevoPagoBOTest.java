package ar.com.santanderrio.obp.servicios.nuevopago.bo;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSeleccionView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaInDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaOutDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.impl.NuevoPagoBOImpl;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Factura;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasView;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.ElectronicoMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.MediosPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.mock.MedioPagoMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

/**
 * The Class NuevoPagoBOTest.
 * 
 * @author marcelo.ruiz
 */
@RunWith(MockitoJUnitRunner.class)
public class NuevoPagoBOTest {

	/** The NuevoPago BO. */
	@InjectMocks
	private NuevoPagoBOImpl nuevoPagoBO;

	/** The alta BO. */
	@Mock
	private ScompBO altaBO;

	/** The mock cuenta BO. */
	@Mock
	private CuentaBO cuentaBO;

	/** The cuenta manager. */
	@Mock
	private CuentaManagerImpl cuentaManager;

	/** The banelco dao. */
	@Mock
	private BanelcoDAO banelcoDao;

	/** The medios pago BO. */
	@Mock
	private MediosPagoBOImpl mediosPagoBO;

	/** The pago mis cuentas DAO. */
	@Mock
	private PagoMisCuentasDAO pagoMisCuentasDAO;

	@Mock
	private SesionParametros mockSesionParametros;

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private TipoMedioPagoBO tipoMedioPagoBO;

	@Mock
	private ElectronicoMedioPagoBOImpl electronicoMedioPagoBO;

	@Mock
	private DatosSolicitanteDAO datosSolicitanteDAO;

	/** The cuit ok. */
	private String CUIT_OK = "20257080596";

	/** The cuit nok. */
	private String CUIT_NOK = "20257080597";

	/**
	 * Checks if is formato codigo pago electronico cuit valid OK.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void isFormatoCodigoPagoElectronicoCuitValidOK() throws BusinessException {
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setCodigoPagoElectronico(CUIT_OK);
		medioPagoView.setDatosAdicionales("2");
		Boolean isCuit = nuevoPagoBO.isFormatoCodigoPagoElectronicoValid(medioPagoView);
		Assert.assertNotNull(isCuit);
		Assert.assertEquals(isCuit, new Boolean(true));
	}

	/**
	 * Checks if is formato codigo pago electronico valid OK.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void isFormatoCodigoPagoElectronicoValidOK() throws BusinessException {
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setCodigoPagoElectronico(CUIT_OK);
		Boolean isCuit = nuevoPagoBO.isFormatoCodigoPagoElectronicoValid(medioPagoView);
		Assert.assertNotNull(isCuit);
		Assert.assertEquals(isCuit, new Boolean(true));
	}

	/**
	 * Checks if is formato codigo pago electronico valid empresa 2.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void isFormatoCodigoPagoElectronicoValidEmpresa2() throws BusinessException {
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setCodigoPagoElectronico(CUIT_OK);
		medioPagoView.setDatosAdicionales("789");

		Boolean isCuit = nuevoPagoBO.isFormatoCodigoPagoElectronicoValid(medioPagoView);
		Assert.assertNotNull(isCuit);
		Assert.assertEquals(isCuit, new Boolean(true));
	}

	/**
	 * Checks if is formato codigo pago electronico valid empresa 3.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void isFormatoCodigoPagoElectronicoValidEmpresa3() throws BusinessException {
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setCodigoPagoElectronico(CUIT_OK);
		medioPagoView.setTipoEmpresa("MF");

		Boolean isCuit = nuevoPagoBO.isFormatoCodigoPagoElectronicoValid(medioPagoView);
		Assert.assertNotNull(isCuit);
		Assert.assertEquals(isCuit, new Boolean(true));
	}

	/**
	 * Checks if is formato codigo pago electronico valid empresa 4.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void isFormatoCodigoPagoElectronicoValidEmpresa4() throws BusinessException {
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setCodigoPagoElectronico(CUIT_OK);
		medioPagoView.setTipoEmpresa("pepe");

		Boolean isCuit = nuevoPagoBO.isFormatoCodigoPagoElectronicoValid(medioPagoView);
		Assert.assertNotNull(isCuit);
		Assert.assertEquals(isCuit, new Boolean(true));
	}

	/**
	 * Checks if is formato codigo pago electronico valid false.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void isFormatoCodigoPagoElectronicoValidFalse() throws BusinessException {
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setCodigoPagoElectronico("1");
		Boolean isCuit = nuevoPagoBO.isFormatoCodigoPagoElectronicoValid(medioPagoView);
		Assert.assertNotNull(isCuit);
		Assert.assertEquals(isCuit, new Boolean(Boolean.TRUE));
	}

	/**
	 * Checks if is formato codigo pago electronico valid longitud.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void isFormatoCodigoPagoElectronicoValidLongitud() throws BusinessException {
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setCodigoPagoElectronico("11111111111111111111");
		Boolean isCuit = nuevoPagoBO.isFormatoCodigoPagoElectronicoValid(medioPagoView);
		Assert.assertNotNull(isCuit);
		Assert.assertEquals(isCuit, new Boolean(Boolean.TRUE));
	}

	/**
	 * Checks if is formato codigo pago electronico cuit valid NOK.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void isFormatoCodigoPagoElectronicoCuitValidNOK() throws BusinessException {
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setCodigoPagoElectronico(CUIT_NOK);
		medioPagoView.setDatosAdicionales("2");
		Boolean isCuit = nuevoPagoBO.isFormatoCodigoPagoElectronicoValid(medioPagoView);
		Assert.assertNotNull(isCuit);
		Assert.assertEquals(isCuit, new Boolean(false));
	}

	/**
	 * Obtener cuentas.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void obtenerCuentas() throws BusinessException {
		// Given
		Cliente cliente = new Cliente();
		List<Cuenta> listCuenta = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuenta.setNroCuentaProducto("2348767");
		cuenta.setNroSucursal("033");
		cuenta.setSaldoCuenta("200000");
		cuenta.setAlias("un alias");
		cuenta.setCbu("1234567890");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
		cuenta.setSaldoCUDls("23000");
		cuenta.setSaldoCUPesos("10");
		Respuesta<CuentasView> resp = new Respuesta<CuentasView>();
		CuentaSeleccionView view = new CuentaSeleccionView();
		view.setDescripcionTipoCuenta(TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda());
		CuentasView cuentasView = new CuentasView();
		List<CuentaSeleccionView> listCuentaView = new ArrayList<CuentaSeleccionView>();
		listCuentaView.add(view);
		resp.setRespuesta(cuentasView);
		listCuenta.add(cuenta);

		// When
		Mockito.when(cuentaManager.getCuenta(Matchers.anyString())).thenReturn(resp);
		Mockito.when(cuentaBO.obtenerCuentasBanelcoPesos(Matchers.any(Cliente.class))).thenReturn(listCuenta);

		// Then
		MedioPagoSelectionView medioPagoSelectionView = nuevoPagoBO.obtenerCuentas(cliente);

		// Expected
		Assert.assertNotNull(medioPagoSelectionView);
	}

	/**
	 * Obtener pago OK.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void obtenerPagoOK() throws BusinessException, DAOException {
		Cliente cli = mock(Cliente.class);
		List<Cuenta> listCuenta = new ArrayList<Cuenta>();
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setFiid("");

		Mockito.when(cli.getNup()).thenReturn("");
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenReturn(new ArrayList<Factura>());
		Mockito.when(cuentaBO.obtenerCuentasBanelcoPesos(Matchers.any(Cliente.class))).thenReturn(listCuenta);
		mockRespuestaMP();

		Respuesta<MedioPagoSelectionView> resp = nuevoPagoBO.obtenerPagos(cli, medioPagoView);
		Assert.assertNotNull(resp);
		Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());

	}

	/**
	 * Obtener pago OK several facturas.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void obtenerPagoOKSeveralFacturas() throws BusinessException, DAOException, IOException {
		Cliente cli = mock(Cliente.class);

		// String fiid= "";
		List<Cuenta> listCuenta = new ArrayList<Cuenta>();
		List<Factura> listaFacturas = new ArrayList<Factura>();
		Factura f1 = new Factura();
		f1.setFechaVencimiento("12/05/2017");
		Factura f2 = new Factura();
		f2.setFechaVencimiento("03/05/2017");
		listaFacturas.add(f1);
		listaFacturas.add(f2);
		Respuesta<MedioPagoSelectionView> resp;
		Mensaje mensaje = new Mensaje();
		mensaje.setCodigo("1640");
		mensaje.setMensaje("Un mensaje");
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setFiid("");

		Mockito.when(cli.getNup()).thenReturn("");
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenReturn(listaFacturas);
		Mockito.when(cuentaBO.obtenerCuentasBanelcoPesos(Matchers.any(Cliente.class))).thenReturn(listCuenta);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		mockRespuestaMP();

		resp = nuevoPagoBO.obtenerPagos(cli, medioPagoView);
		Assert.assertNotNull(resp);
		Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());

	}

	/**
	 * Obtener pago ERROR.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerPagoErrorException() throws BusinessException, DAOException {
		// Given
		Cliente cli = mock(Cliente.class);

		// String fiid= "";
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setFiid("");
		Mensaje mensaje = MensajeMock.completarInfoMensaje("1056",
				"El Código de Pago ingresado es inválido. Por favor, revisá los datos de la factura.");

		// When
		Mockito.when(cli.getNup()).thenReturn("");
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenReturn(null);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		mockRespuestaMP();

		// Then
		Respuesta<MedioPagoSelectionView> resp = nuevoPagoBO.obtenerPagos(cli, medioPagoView);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				resp.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Obtener pago warning.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerPagoWarning() throws BusinessException, DAOException {
		// Given
		Cliente cli = mock(Cliente.class);
		Respuesta<MedioPagoSelectionView> resp;
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setFiid("");
		medioPagoView.setCodigoPagoElectronico("1234");
		Mensaje mensaje = MensajeMock.completarInfoMensaje("1206",
				"<p>No se registran deudas para la factura correspondiente al código ingresado.</p>");

		// When
		Mockito.when(cli.getNup()).thenReturn("");
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenThrow(new DAOException());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
		mockRespuestaMP();
		// Then
		resp = nuevoPagoBO.obtenerPagos(cli, medioPagoView);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				resp.getItemsMensajeRespuesta().get(0).getTipoError());

	}

	/**
	 * Obtener pago exception codigo pago electronico 2 not null cuit 2 valido.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerPagoExceptionCodigoPagoElectronico2NotNullCuit2Valido() throws BusinessException, DAOException {
		// Given
		Cliente cli = mock(Cliente.class);
		Respuesta<MedioPagoSelectionView> resp;
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setFiid("");
		medioPagoView.setCodigoPagoElectronico("1234");
		medioPagoView.setCodigoPagoElectronico2("20378668817");
		Mensaje mensaje = MensajeMock.completarInfoMensaje("1205",
				"No se registran deudas para los CUIT/CUIL ingresados.");
		// When
		Mockito.when(cli.getNup()).thenReturn("");
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenThrow(new DAOException());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
		mockRespuestaMP();

		// Then
		resp = nuevoPagoBO.obtenerPagos(cli, medioPagoView);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				resp.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Obtener pago exception codigo pago electronico null cuit valido.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerPagoExceptionCodigoPagoElectronicoNullCuitValido() throws BusinessException, DAOException {
		// Given
		Cliente cli = mock(Cliente.class);
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setFiid("");
		medioPagoView.setCodigoPagoElectronico("20378668817");
		Mensaje mensaje = MensajeMock.completarInfoMensaje("1204",
				"No se registran deudas para el CUIT/CUIL ingresado.");

		// When
		Mockito.when(cli.getNup()).thenReturn("");
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenThrow(new DAOException());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
		mockRespuestaMP();

		// Then
		Respuesta<MedioPagoSelectionView> resp = nuevoPagoBO.obtenerPagos(cli, medioPagoView);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				resp.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Obtener pago exception codigo pago electronico null cuit in valido.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerPagoExceptionCodigoPagoElectronicoNullCuitInValido() throws BusinessException, DAOException {
		// Given
		Cliente cli = mock(Cliente.class);
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setFiid("");
		medioPagoView.setCodigoPagoElectronico("2037866");
		medioPagoView.setCodigoPagoElectronico2("20378668");
		Mensaje mensaje = MensajeMock.completarInfoMensaje("1206",
				"<p>No se registran deudas para la factura correspondiente al código ingresado.</p>");

		// When
		Mockito.when(cli.getNup()).thenReturn("");
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenThrow(new DAOException());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
		mockRespuestaMP();

		// Then
		Respuesta<MedioPagoSelectionView> resp = nuevoPagoBO.obtenerPagos(cli, medioPagoView);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				resp.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Obtener pago exception error timeout banelco.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerPagoExceptionErrorTimeoutBanelco() throws BusinessException, DAOException {
		// Given
		Cliente cli = mock(Cliente.class);
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setFiid("");
		medioPagoView.setCodigoPagoElectronico("2037866");
		medioPagoView.setCodigoPagoElectronico2("20378668");
		Mensaje mensaje = MensajeMock.completarInfoMensaje("1129",
				"<p>¡Lo sentimos!</p><p>Ocurrió un error en nuestros servicios, y no podemos mostrarte esta operación. Por favor, volvé a intentarlo en unos minutos.</p>");

		// When
		Mockito.when(cli.getNup()).thenReturn("");
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenThrow(new DAOException("Mensaje Error.", "10000004"));
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
		mockRespuestaMP();

		// Then
		Respuesta<MedioPagoSelectionView> resp = nuevoPagoBO.obtenerPagos(cli, medioPagoView);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				resp.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Obtener pagos OK.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerPagosOK() throws BusinessException, DAOException {
		Cliente cli = mock(Cliente.class);
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setCodigoPagoElectronico(CUIT_OK);
		medioPagoView.setCodigoPagoElectronico2(CUIT_OK);
		medioPagoView.setDatosAdicionales("2");

		Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
		MedioPago medioPago = new MedioPago();
		medioPago.setTipoPago(4);
		respMP.setRespuesta(medioPago);

		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		Respuesta<MedioPagoSelectionView> resp = nuevoPagoBO.obtenerPagos(cli, medioPagoView);

		Assert.assertNotNull(resp);
		Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());

	}

	/**
	 * Obtener pagos warning.
	 * 
	 * @throws BusinessException
	 * @throws DAOException
	 */
	@Test
	public void obtenerPagosWarning() throws BusinessException, DAOException {
		// Given
		Cliente cli = mock(Cliente.class);
		MedioPagoView medioPagoView = new MedioPagoView();
		medioPagoView.setTipoEmpresa("a");
		medioPagoView.setDatosAdicionales("2");
		medioPagoView.setCodigoPagoElectronico("123");
		Mensaje mensaje = MensajeMock.completarInfoMensaje("1206",
				"<p>No se registran deudas para la factura correspondiente al código ingresado.</p>");
		// When
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenThrow(new DAOException());
		mockRespuestaMP();

		// Then
		Respuesta<MedioPagoSelectionView> resp = nuevoPagoBO.obtenerPagos(cli, medioPagoView);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());

	}

	/**
	 * Obtener facturas OK.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void obtenerFacturasOK() throws DAOException {
		Cliente cli = new Cliente();
		NuevoPago nuevoP = mock(NuevoPago.class);
		Mockito.when(nuevoP.getFiid()).thenReturn("");
		Mockito.when(nuevoP.getCodigoPagoElectronico()).thenReturn("");

		List<Factura> list = new ArrayList<Factura>();
		Factura fact = new Factura();
		list.add(fact);

		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenReturn(list);

		Respuesta<List<Factura>> resp = nuevoPagoBO.obtenerFacturas(cli, nuevoP);

		Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
	}

	/**
	 * Validar factura ok warning.
	 */
	@Test
	public void validarFacturaOk() {
		// Given
		Cliente cli = new Cliente();
		NuevoPago nuevoP = mock(NuevoPago.class);
		Mockito.when(nuevoP.getCodigoPagoElectronico()).thenReturn("35");
		Mockito.when(nuevoP.getCodigoPagoElectronico2()).thenReturn("35");

		// Then
		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);

		// Expected
		Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
		Assert.assertEquals(Boolean.TRUE, resp.getRespuesta());
	}

	/**
	 * Validar factura warning.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void validarFacturaWarning() throws DAOException, BusinessException {
		// Given
		Cliente cli = new Cliente();
		NuevoPago nuevoP = mock(NuevoPago.class);
		Mockito.when(nuevoP.getCodigoPagoElectronico()).thenReturn("");
		Mockito.when(nuevoP.getCodigoPagoElectronico2()).thenReturn("35");
		Mensaje mensaje = MensajeMock.completarInfoMensaje("1060",
				"El Código ingresado para el medio de pago seleccionado es inválido. Por favor, revisá los datos de la factura.");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Respuesta<MedioPago> respMP = respuestaFactory.crearRespuestaWarning(MedioPago.class, null,
				TipoError.ERROR_GENERICO, "1060", "");

		// When
		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		// Then
		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);

		// Expected
		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
	}

	/**
	 * Validar factura warning 2.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void validarFacturaWarning2() throws DAOException, BusinessException {
		// Given
		Cliente cli = new Cliente();
		NuevoPago nuevoP = mock(NuevoPago.class);
		Mockito.when(nuevoP.getCodigoPagoElectronico()).thenReturn("45");
		Mockito.when(nuevoP.getCodigoPagoElectronico2()).thenReturn("");
		Mensaje mensaje = MensajeMock.completarInfoMensaje("1060",
				"El Código ingresado para el medio de pago seleccionado es inválido. Por favor, revisá los datos de la factura.");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Respuesta<MedioPago> respMP = respuestaFactory.crearRespuestaWarning(MedioPago.class, null,
				TipoError.ERROR_GENERICO, "1060", "");

		// When
		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		// Then
		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);

		// Expected
		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
	}

	private Respuesta<List<PagoPendiente>> obtenerRespuestaPagoPendiente() {
		Respuesta<List<PagoPendiente>> pagosRespuesta = new Respuesta<List<PagoPendiente>>();
		List<PagoPendiente> pagos = new ArrayList<PagoPendiente>();
		PagoPendiente pago = new PagoPendiente();
		pago.setCodigoEmpresa("FFF");
		pago.setCodigoClienteEmpresa("23");
		pago.setIdentificacionDeFactura("3232");
		pago.setImporte(new BigDecimal(123));
		pagos.add(pago);
		pagosRespuesta.setRespuesta(pagos);
		return pagosRespuesta;
	}

	/**
	 * Validar factura warning sin facturas.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void validarFacturaWarningSinFacturas() throws DAOException, BusinessException {
		// Given
		Cliente cli = new Cliente();
		Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();

		Respuesta<MedioPago> respMP = respuestaFactory.crearRespuestaOk(MedioPago.class,
				MedioPagoMock.completarInfo("Cablevision", 1, "Con factura"));
		respMP.getRespuesta().setTipoImporte(NuevoPagoBO.MODIFICA_SOLO_POR_IMPORTE_IGUAL_O_MAYOR);
		NuevoPago nuevoP = mock(NuevoPago.class);
		Mockito.when(nuevoP.getCodigoPagoElectronico()).thenReturn("45");
		Mockito.when(nuevoP.getCodigoPagoElectronico2()).thenReturn("");
		Mockito.when(nuevoP.getFiid()).thenReturn("CARC");
		Mockito.when(nuevoP.getFacturaNumero()).thenReturn("1234");
		Mockito.when(nuevoP.getMonto()).thenReturn("123");
		// When
		Mockito.when(mockSesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);
		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.anyString())).thenReturn(new ArrayList<Factura>());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
				.thenReturn(MensajeMock.completarInfoMensaje("1061", "No se encontraron facturas."));

		// Tthen
		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);

		// Expected
		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
	}

	/**
	 * Validar factura warning sin facturas DAO exception.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void validarFacturaWarningSinFacturasDAOException() throws DAOException, BusinessException {
		// Given
		Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();
		Mockito.when(mockSesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);

		Cliente cli = new Cliente();
		Respuesta<MedioPago> respMP = respuestaFactory.crearRespuestaOk(MedioPago.class,
				MedioPagoMock.completarInfo("Cablevision", 1, "Con factura"));
		NuevoPago nuevoP = mock(NuevoPago.class);
		Mockito.when(nuevoP.getCodigoPagoElectronico()).thenReturn("23");
		Mockito.when(nuevoP.getCodigoPagoElectronico2()).thenReturn("");
		Mockito.when(nuevoP.getFiid()).thenReturn("RRR");
		Mockito.when(nuevoP.getFacturaNumero()).thenReturn("43");
		Mockito.when(nuevoP.getMonto()).thenReturn("123");

		List<Factura> list = new ArrayList<Factura>();
		// When
		Mockito.when(mockSesionParametros.getFacturasPagosPendientes()).thenReturn(list);
		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.anyString())).thenThrow(new DAOException("Error Factura sin deuda."));
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
				.thenReturn(MensajeMock.completarInfoMensaje("1061", "No se encontraron facturas."));

		// Tthen
		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);

		// Expected
		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
	}

	/**
	 * Validar factura importe.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void validarFacturaImporte() throws DAOException, BusinessException {
		Cliente cli = new Cliente();
		NuevoPago nuevoP = mock(NuevoPago.class);
		Mockito.when(nuevoP.getCodigoPagoElectronico()).thenReturn("");
		Mockito.when(nuevoP.getCodigoPagoElectronico2()).thenReturn("35");

		Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
		respMP.setRespuestaVacia(Boolean.TRUE);

		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);

		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
	}

	/**
	 * Validar factura valid importe warning 2.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void validarFacturaValidImporteWarning2() throws DAOException, BusinessException {
		Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();
		Mockito.when(mockSesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);

		Cliente cli = new Cliente();
		NuevoPago nuevoP = mock(NuevoPago.class);
		Mockito.when(nuevoP.getCodigoPagoElectronico()).thenReturn("");
		Mockito.when(nuevoP.getCodigoPagoElectronico2()).thenReturn("");
		Mockito.when(nuevoP.getFacturaNumero()).thenReturn("789");
		Mockito.when(nuevoP.getFiid()).thenReturn("TRR");
		Mockito.when(nuevoP.getMonto()).thenReturn("123");

		Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
		respMP.setRespuestaVacia(Boolean.FALSE);

		List<Factura> list = new ArrayList<Factura>();
		Factura fact = new Factura();
		fact.setNumeroFactura("456");
		list.add(fact);

		Mockito.when(mockSesionParametros.getFacturasPagosPendientes()).thenReturn(list);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
				.thenReturn(MensajeMock.completarInfoMensaje("1061", "No se encontraron facturas."));
		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);

		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
	}

	/**
	 * Ejecutar validacion importe error.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void ejecutarValidacionImporteError() throws DAOException, BusinessException {
		Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();
		Mockito.when(mockSesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);

		Cliente cli = new Cliente();
		NuevoPago nuevoP = new NuevoPago();
		nuevoP.setCodigoPagoElectronico("23");
		nuevoP.setCodigoPagoElectronico2("");
		nuevoP.setFacturaNumero("3232");
		nuevoP.setMonto("20");
		nuevoP.setFiid("FFF");

		Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
		MedioPago medioPago = new MedioPago();
		medioPago.setTipoPago(4);
		medioPago.setTipoImporte("0");

		respMP.setRespuesta(medioPago);
		respMP.setRespuestaVacia(Boolean.FALSE);
		respMP.getRespuesta().setTipoImporte("Tipo Importe Erroneo");

		List<Factura> list = new ArrayList<Factura>();
		Factura fact = new Factura();
		fact.setNumeroFactura("456");
		fact.setMonto("30");
		list.add(fact);

		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		list = new ArrayList<Factura>();
		fact = new Factura();
		fact.setNumeroFactura("456");
		fact.setMonto("a");
		list.add(fact);

		Mockito.when(mockSesionParametros.getFacturasPagosPendientes()).thenReturn(list);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
				.thenReturn(MensajeMock.completarInfoMensaje("1061", "No se encontraron facturas."));

		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);
		Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());

	}

	/**
	 * Ejecutar validacion importe error 2.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void ejecutarValidacionImporteError2() throws DAOException, BusinessException {

		Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();
		Mockito.when(mockSesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);

		Cliente cli = new Cliente();
		NuevoPago nuevoP = new NuevoPago();
		nuevoP.setCodigoPagoElectronico("23");
		nuevoP.setCodigoPagoElectronico2("");
		nuevoP.setFacturaNumero("3232");
		nuevoP.setMonto("20");
		nuevoP.setFiid("FFF");

		Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
		MedioPago medioPago = new MedioPago();
		medioPago.setTipoPago(4);
		medioPago.setTipoImporte("3");

		respMP.setRespuesta(medioPago);
		respMP.setRespuestaVacia(Boolean.FALSE);

		List<Factura> list = new ArrayList<Factura>();
		Factura fact = new Factura();
		fact.setNumeroFactura("456");
		fact.setMonto("30");
		list.add(fact);

		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
				.thenReturn(MensajeMock.completarInfoMensaje("1061", "No se encontraron facturas."));

		list = new ArrayList<Factura>();
		fact = new Factura();
		fact.setNumeroFactura("456");
		fact.setMonto("23");
		list.add(fact);

		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenReturn(list);

		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);
		Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());

	}

	/**
	 * Ejecutar validacion importe 01.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void ejecutarValidacionImporte01() throws DAOException, BusinessException {

		Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();
		Mockito.when(mockSesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
				.thenReturn(MensajeMock.completarInfoMensaje("1061", "No se encontraron facturas."));

		Cliente cli = new Cliente();
		NuevoPago nuevoP = new NuevoPago();
		nuevoP.setCodigoPagoElectronico("23");
		nuevoP.setCodigoPagoElectronico2("");
		nuevoP.setFacturaNumero("3232");
		nuevoP.setMonto("20");
		nuevoP.setFiid("FFF");

		Integer i = 0;
		while (i < 2) {
			Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
			MedioPago medioPago = new MedioPago();
			medioPago.setTipoPago(4);
			medioPago.setTipoImporte(i.toString());

			respMP.setRespuesta(medioPago);
			respMP.setRespuestaVacia(Boolean.FALSE);
			respMP.getRespuesta().setTipoImporte(NuevoPagoBO.MODIFICA_SOLO_POR_IMPORTE_IGUAL_O_MAYOR);

			List<Factura> list = new ArrayList<Factura>();
			Factura fact = new Factura();
			fact.setNumeroFactura("456");
			fact.setMonto("30");
			list.add(fact);

			Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
					Matchers.any(String.class))).thenReturn(list);
			Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

			Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);
			Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());

			list = new ArrayList<Factura>();
			fact = new Factura();
			fact.setNumeroFactura("456");
			fact.setMonto("20");
			list.add(fact);
			NuevoPago nuevoP2 = new NuevoPago();
			nuevoP2.setCodigoPagoElectronico("23");
			nuevoP2.setCodigoPagoElectronico2("");
			nuevoP2.setFacturaNumero("3232");
			nuevoP2.setMonto("123");
			nuevoP2.setFiid("FFF");

			Mockito.when(mockSesionParametros.getFacturasPagosPendientes()).thenReturn(list);

			resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP2);
			Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
			++i;
		}
	}

	/**
	 * Ejecutar validacion importe 1.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void ejecutarValidacionImporte1() throws DAOException, BusinessException {
		Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();
		Mockito.when(mockSesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);

		Cliente cli = new Cliente();
		NuevoPago nuevoP = new NuevoPago();
		nuevoP.setCodigoPagoElectronico("23");
		nuevoP.setCodigoPagoElectronico2("");
		nuevoP.setFacturaNumero("3232");
		nuevoP.setMonto("123");
		nuevoP.setFiid("FFF");

		Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
		MedioPago medioPago = new MedioPago();
		medioPago.setTipoPago(4);
		medioPago.setTipoImporte("1");

		respMP.setRespuesta(medioPago);
		respMP.setRespuestaVacia(Boolean.FALSE);

		List<Factura> list = new ArrayList<Factura>();
		Factura fact = new Factura();
		fact.setNumeroFactura("456");
		fact.setMonto("30");
		list.add(fact);
		Mockito.when(mockSesionParametros.getFacturasPagosPendientes()).thenReturn(list);
		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenReturn(list);
		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);
		Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());

	}

	/**
	 * Ejecutar validacion importe 2.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void ejecutarValidacionImporte2() throws DAOException, BusinessException {
		Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();
		Mockito.when(mockSesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);

		Cliente cli = new Cliente();
		NuevoPago nuevoP = new NuevoPago();
		nuevoP.setCodigoPagoElectronico("23");
		nuevoP.setCodigoPagoElectronico2("");
		nuevoP.setFacturaNumero("3232");
		nuevoP.setMonto("123");
		nuevoP.setFiid("FFF");

		Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
		MedioPago medioPago = new MedioPago();
		medioPago.setTipoPago(4);
		medioPago.setTipoImporte("2");

		respMP.setRespuesta(medioPago);
		respMP.setRespuestaVacia(Boolean.FALSE);

		List<Factura> list = new ArrayList<Factura>();
		Factura fact = new Factura();
		fact.setNumeroFactura("456");
		fact.setMonto("30");
		list.add(fact);

		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenReturn(list);
		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		Respuesta<Boolean> resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);
		Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());

		list = new ArrayList<Factura>();
		fact = new Factura();
		fact.setNumeroFactura("456");
		fact.setMonto("10");
		list.add(fact);
		Mockito.when(mockSesionParametros.getFacturasPagosPendientes()).thenReturn(list);
		nuevoP.setMonto("0");

		Mockito.when(banelcoDao.obtenerListaFacturas(Matchers.any(Cliente.class), Matchers.any(String.class),
				Matchers.any(String.class))).thenReturn(list);

		resp = nuevoPagoBO.validarImporteFactura(cli, nuevoP);
		Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
	}

	/**
	 * Modificar codigo pago electronico si VEPOK.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void modificarCodigoPagoElectronicoSiVEPOK() throws DAOException, BusinessException {
		String codigoUno = "codigo1";
		MedioPagoView medioPagoView = new MedioPagoView();

		medioPagoView.setFiid("pepe");
		medioPagoView.setCodigoPagoElectronico(codigoUno);

		Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
		MedioPago medioPago = new MedioPago();
		medioPago.setTipoPago(4);
		respMP.setRespuesta(medioPago);

		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		String resp = nuevoPagoBO.modificarCodigoPagoElectronicoSiVEP(medioPagoView);
		Assert.assertEquals(codigoUno, resp);

		medioPago.setTipoPago(3);
		medioPago.setTipoEmpresa("M");

		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		resp = nuevoPagoBO.modificarCodigoPagoElectronicoSiVEP(medioPagoView);
		Assert.assertEquals(codigoUno, resp);

		medioPago.setTipoPago(4);
		medioPago.setTipoEmpresa("F");

		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		resp = nuevoPagoBO.modificarCodigoPagoElectronicoSiVEP(medioPagoView);
		Assert.assertEquals(codigoUno, resp);
	}

	/**
	 * Modificar codigo pago electronico si VEPOK 2.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void modificarCodigoPagoElectronicoSiVEPOK2() throws DAOException, BusinessException {
		String codigoUno = "1234567890";
		MedioPagoView medioPagoView = new MedioPagoView();

		medioPagoView.setFiid("pepe");
		medioPagoView.setCodigoPagoElectronico(codigoUno);
		medioPagoView.setCodigoPagoElectronico2(codigoUno);

		Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
		MedioPago medioPago = new MedioPago();
		medioPago.setTipoPago(3);
		medioPago.setTipoEmpresa("F");
		respMP.setRespuesta(medioPago);

		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);

		String resp = nuevoPagoBO.modificarCodigoPagoElectronicoSiVEP(medioPagoView);
		Assert.assertEquals("123456789034567890", resp);

	}

	/**
	 * Pagar DAOE xception test 2.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings({ "unchecked"})
	@Test(expected = BusinessException.class)
	public void pagarDAOEXceptionTest() throws BusinessException, DAOException {
		// Given
		NuevoPago nuevoPago = new NuevoPago();
		nuevoPago.setCodigoPagoElectronico("20-38766765-4");
		nuevoPago.setCodigoPagoElectronico2("13-87654987-7");
		nuevoPago.setMonto("12");
		nuevoPago.setFacturaNumero("                    ");
		nuevoPago.setPeriodo("112014");
		nuevoPago.setFiid("CLBV");
		nuevoPago.setNumeroDeAnticipo("1213234234345");
		nuevoPago.setNumeroDeCuota("12");
		nuevoPago.setNumeroReferenciaPago("2323435356456");
		nuevoPago.setReintentar("true");
		Cuenta cuenta = new Cuenta();
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setNroSucursal("0123");
		cuenta.setMonedaAltair("ARS");
		cuenta.setTipoCuenta("02");
		MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "3");
		PagoPMC pago = new PagoPMC();
		pago.setCodigoEmpresa("UNCF");
		pago.setComprobantePorServicio("000000002222");
		pago.setEstadoPago(0);
		pago.setFechaHoraBody("20180208110622");
		pago.setIdentificacion("111111");
		pago.setMoneda("ARS");
		pago.setMonto("00000000002000");
		pago.setNumeroControl("1234");
		pago.setNumeroCuenta("000017024994");
		pago.setNumeroFactura("                    ");
		pago.setOperacionDescripcion("pago");
		pago.setReintentar(Boolean.FALSE);
		pago.setRespuestaOK(Boolean.TRUE);
		pago.setSucursalCuenta("0037");
		pago.setTipoCuenta("07");
		pago.setTipoMonto("0");
		pago.setTipoSeleccion("I");
		

		// When
		Mockito.when(pagoMisCuentasDAO.invocarPagoMisCuentas(Matchers.any(List.class), Matchers.any(Cliente.class)))
				.thenThrow(new DAOException("DAOException."));
		Mockito.when(mediosPagoBO.obtenerTipoMedioPago(medioPago)).thenReturn(electronicoMedioPagoBO);
		Mockito.when(electronicoMedioPagoBO.generarPagoInEntity(Matchers.any(PagoMisCuentasDTO.class)))
				.thenReturn(pago);
		// Then
		NuevaRecargaOutDTO res = nuevoPagoBO.pagar(medioPago, new PagoMisCuentasDTO(nuevoPago, cuenta, medioPago, true),
				new Cliente());
	}

	/**
	 * Pagar response OK test.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void pagarResponseOKTest() throws DAOException, BusinessException {
		// Given
		Cliente cliente = ClienteMock.completarInfoCliente();
		NuevoPago nuevoPago = new NuevoPago();
		nuevoPago.setCodigoPagoElectronico("20-38766765-4");
		nuevoPago.setCodigoPagoElectronico2("13-87654987-7");
		nuevoPago.setMonto("12");
		nuevoPago.setFacturaNumero("                    ");
		nuevoPago.setPeriodo("112014");
		nuevoPago.setFiid("CLBV");
		nuevoPago.setNumeroDeAnticipo("1213234234345");
		nuevoPago.setNumeroDeCuota("12");
		nuevoPago.setNumeroReferenciaPago("2323435356456");
		nuevoPago.setReintentar("true");
		Cuenta cuenta = new Cuenta();
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setNroSucursal("0123");
		cuenta.setMonedaAltair("ARS");
		cuenta.setTipoCuenta("02");
		MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "3");
		PagoPMC pago = new PagoPMC();
		pago.setRespuestaOK(Boolean.TRUE);

		// When
		Mockito.when(pagoMisCuentasDAO.invocarPagoMisCuentas(Matchers.any(List.class), Matchers.any(Cliente.class)))
				.thenReturn(pago);
		Mockito.when(altaBO.altaScompTransferencia(Matchers.any(AltaComprobanteRequestBuilder.class)))
				.thenReturn(new Respuesta<Void>());
		Mockito.when(mediosPagoBO.obtenerTipoMedioPago(medioPago)).thenReturn(electronicoMedioPagoBO);
		Mockito.when(electronicoMedioPagoBO.generarPagoInEntity(Matchers.any(PagoMisCuentasDTO.class)))
				.thenReturn(pago);

		// Then
		NuevaRecargaOutDTO resp = nuevoPagoBO.pagar(medioPago,
				new PagoMisCuentasDTO(nuevoPago, cuenta, medioPago, true), cliente);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(Boolean.TRUE, resp.getRespuestaOK());
	}

	/**
	 * Pagar response no OK test.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void pagarResponseNoOKTest() throws DAOException, BusinessException {
		// Given
		Cliente cliente = ClienteMock.completarInfoCliente();
		NuevoPago nuevoPago = new NuevoPago();
		nuevoPago.setCodigoPagoElectronico("20-38766765-4");
		nuevoPago.setCodigoPagoElectronico2("13-87654987-7");
		nuevoPago.setMonto("12");
		nuevoPago.setFacturaNumero("                    ");
		nuevoPago.setPeriodo("112014");
		nuevoPago.setFiid("CLBV");
		nuevoPago.setNumeroDeAnticipo("1213234234345");
		nuevoPago.setNumeroDeCuota("12");
		nuevoPago.setNumeroReferenciaPago("2323435356456");
		nuevoPago.setReintentar("true");
		Cuenta cuenta = new Cuenta();
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setNroSucursal("0123");
		cuenta.setMonedaAltair("ARS");
		cuenta.setTipoCuenta("02");
		MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "3");
		PagoPMC pago = new PagoPMC();
		pago.setRespuestaOK(Boolean.FALSE);

		// When
		Mockito.when(pagoMisCuentasDAO.invocarPagoMisCuentas(Matchers.any(List.class), Matchers.any(Cliente.class)))
				.thenReturn(pago);
		Mockito.when(altaBO.altaScompTransferencia(Matchers.any(AltaComprobanteRequestBuilder.class)))
				.thenReturn(new Respuesta<Void>());
		Mockito.when(mediosPagoBO.obtenerTipoMedioPago(medioPago)).thenReturn(electronicoMedioPagoBO);
		Mockito.when(electronicoMedioPagoBO.generarPagoInEntity(Matchers.any(PagoMisCuentasDTO.class)))
				.thenReturn(pago);


		// Then
		NuevaRecargaOutDTO resp = nuevoPagoBO.pagar(medioPago,
				new PagoMisCuentasDTO(nuevoPago, cuenta, medioPago, true), cliente);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(Boolean.FALSE, resp.getRespuestaOK());
	}

	/**
	 * Pagar response iatx response error test.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void pagarResponseIatxResponseErrorTest() throws DAOException {
		PagoMisCuentasView nuevoPago = new PagoMisCuentasView();
		IatxRequest iatxRequest = new IatxRequest("PAGCMASSIO", "100");
		IatxRequestData data = new IatxRequestData();
		IatxResponse iatxResponse = new IatxResponse();

		nuevoPago.setTipoPago("2");
		nuevoPago.setTipoEmpresa(" ");
		nuevoPago.setCuitCliente("20-38766765-4");
		nuevoPago.setCuitEmpleador("13-87654987-7");
		nuevoPago.setTipoPagoEmpresa("3");
		nuevoPago.setMonto("12");
		nuevoPago.setNumeroFactura("                    ");
		nuevoPago.setServicioDomestico(false);
		nuevoPago.setMoneda("ARS");
		nuevoPago.setPeriodoPago("112020");

		iatxResponse.setIatxBody(new Vector<String>());
		iatxResponse.getIatxBody().add("000");
		iatxResponse.getIatxBody().add("000");
		iatxResponse.getIatxBody().add("000");
		iatxResponse.setErrorCode(1);

		for (int i = 0; i < 11; i++) {
			iatxResponse.getIatxBody().add("000");
		}

		iatxRequest.setData(data);

		Mockito.when(pagoMisCuentasDAO.invocarPagoMisCuentas(Matchers.any(List.class), Matchers.any(Cliente.class)))
				.thenThrow(new DAOException());

		// Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
	}

	/**
	 * Cuando pago una recarga falla invocar pago mis cuentas devuelve
	 * exception.
	 * 
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = BusinessException.class)
	public void cuandoPagoUnaRecargaFallaInvocarPagoMisCuentasDevuelveException()
			throws BusinessException, DAOException {
		// cuando
		Cliente cliente = ClienteMock.completarInfoCliente();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);
		cliente.setCuentas(cuentas);

		// entonces
		NuevaRecargaInDTO inDTO = new NuevaRecargaInDTO();
		inDTO.setMontoId("100");
		Mockito.when(pagoMisCuentasDAO.invocarPagoMisCuentas(Matchers.anyList(), Matchers.any(Cliente.class)))
				.thenThrow(new DAOException());
		nuevoPagoBO.pagarRecarga(cliente, cuenta, inDTO);
	}

	/**
	 * Pagar recarga OK.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	// @SuppressWarnings("unchecked")
	// @Test
	// public void pagarRecargaOK() throws DAOException, BusinessException {
	// // Given
	// Cliente cliente = ClienteMock.completarInfoCliente();
	// Cuenta cuenta = new Cuenta();
	// cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
	// cuenta.setNroSucursal("0033");
	// cuenta.setNroCuentaProducto("0000000001234567");
	// cuenta.setCbu("1234567890123456789012");
	// cliente.getCuentas().add(cuenta);
	// NuevaRecargaInDTO recargaDTO = new NuevaRecargaInDTO();
	// recargaDTO.setCbuCuenta("1234567890123456789012");
	// recargaDTO.setMontoId("20");
	// recargaDTO.setCodigoEmpresa("123");
	// recargaDTO.setIdentificacionCliente("1123456734");
	// recargaDTO.setNombreFantasia("Recarga Claro");
	// recargaDTO.setIdentificacionMensajeFeedback(
	// "Tu recarga de <p>Claro</b> por <b>$ 20,00</b> al número de celular
	// <b>1123456734</b> se realizó con éxito en el día de hoy.");
	// recargaDTO.setMontoMensajeFeedback("$ 20,00");
	// PagoPMC pcm = PagoPMCMock.completarInfo(Boolean.TRUE, "Claro");
	//
	// // When
	// Mockito.when(pagoMisCuentasDAO.invocarPagoMisCuentas(Matchers.anyList(),
	// Matchers.any(Cliente.class)))
	// .thenReturn(pcm);
	//
	// // Then
	// NuevaRecargaOutDTO dto = nuevoPagoBO.pagarRecarga(cliente, recargaDTO);
	//
	// // Expected
	// Assert.assertNotNull(dto);
	// Assert.assertEquals(Boolean.TRUE, dto.getRespuestaOK());
	// }

	/**
	 * Pagar recarga no OK.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	// @SuppressWarnings("unchecked")
	// @Test
	// public void pagarRecargaNoOK() throws DAOException, BusinessException {
	// // Given
	// Cliente cliente = ClienteMock.completarInfoCliente();
	// Cuenta cuenta = new Cuenta();
	// cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
	// cuenta.setNroSucursal("0033");
	// cuenta.setNroCuentaProducto("0000000001234567");
	// cuenta.setCbu("1234567890123456789012");
	// cliente.getCuentas().add(cuenta);
	// NuevaRecargaInDTO recargaDTO = new NuevaRecargaInDTO();
	// recargaDTO.setCbuCuenta("1234567890123456789012");
	// recargaDTO.setMontoId("20");
	// recargaDTO.setCodigoEmpresa("123");
	// recargaDTO.setIdentificacionCliente("1123456734");
	// recargaDTO.setNombreFantasia("Recarga Claro");
	// recargaDTO.setIdentificacionMensajeFeedback(
	// "Tu recarga de <p>Claro</b> por <b>$ 20,00</b> al número de celular
	// <b>1123456734</b> se realizó con éxito en el día de hoy.");
	// recargaDTO.setMontoMensajeFeedback("$ 20,00");
	// PagoPMC pcm = PagoPMCMock.completarInfo(Boolean.FALSE, "Claro");
	//
	// // When
	// Mockito.when(pagoMisCuentasDAO.invocarPagoMisCuentas(Matchers.anyList(),
	// Matchers.any(Cliente.class)))
	// .thenReturn(pcm);
	//
	// // Then
	// NuevaRecargaOutDTO dto = nuevoPagoBO.pagarRecarga(cliente, recargaDTO);
	//
	// // Expected
	// Assert.assertNotNull(dto);
	// Assert.assertEquals(Boolean.FALSE, dto.getRespuestaOK());
	// }

	/**
	 * Pagar recarga DAO exception.
	 * 
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Test(expected = BusinessException.class)
	public void pagarRecargaDAOException() throws DAOException, BusinessException {
		// Given
		Cliente cliente = ClienteMock.completarInfoCliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
		cuenta.setNroSucursal("0033");
		cuenta.setNroCuentaProducto("0000000001234567");
		cuenta.setCbu("1234567890123456789012");
		cliente.getCuentas().add(cuenta);
		NuevaRecargaInDTO recargaDTO = new NuevaRecargaInDTO();
		recargaDTO.setCbuCuenta("1234567890123456789012");
		recargaDTO.setMontoId("20");
		recargaDTO.setCodigoEmpresa("123");
		recargaDTO.setIdentificacionCliente("1123456734");
		recargaDTO.setNombreFantasia("Recarga Claro");
		recargaDTO.setIdentificacionMensajeFeedback(
				"Tu recarga de <p>Claro</b> por <b>$ 20,00</b> al número de celular <b>1123456734</b> se realizó con éxito en el día de hoy.");
		recargaDTO.setMontoMensajeFeedback("$ 20,00");

		// When
		Mockito.when(pagoMisCuentasDAO.invocarPagoMisCuentas(Matchers.anyList(), Matchers.any(Cliente.class)))
				.thenThrow(new DAOException("Error en el DAO de PagoMisCuentas."));

		// Then
		NuevaRecargaOutDTO dto = nuevoPagoBO.pagarRecarga(cliente, cuenta, recargaDTO);
	}

	/**
	 * Validacion monto OK.
	 */
	@Test
	public void validacionMontoOK() {
		// Given
		String montoFactura = "20,00";
		String montoPago = "20,00";
		String tipoImporte = "0";

		// Then
		Respuesta<Boolean> respMontoValido = nuevoPagoBO.validacionMonto(montoFactura, montoPago, tipoImporte);

		// Expected
		Assert.assertNotNull(respMontoValido);
		Assert.assertEquals(EstadoRespuesta.OK, respMontoValido.getEstadoRespuesta());
		Assert.assertEquals(Boolean.TRUE, respMontoValido.getRespuesta());
	}

	/**
	 * Validacion monto con monto pago inferior monto factura.
	 */
	@Test
	public void validacionMontoConMontoPagoInferiorMontoFactura() {
		// Given
		String montoFactura = "20,00";
		String montoPago = "10,00";
		String tipoImporte = "0";

		// Then
		Respuesta<Boolean> respMontoValido = nuevoPagoBO.validacionMonto(montoFactura, montoPago, tipoImporte);

		// Expected
		Assert.assertNotNull(respMontoValido);
		Assert.assertEquals(EstadoRespuesta.ERROR, respMontoValido.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				respMontoValido.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Validacion monto con tipo importe distinto cero.
	 */
	@Test
	public void validacionMontoConTipoImporteDistintoCero() {
		// Given
		String montoFactura = "20,00";
		String montoPago = "20,00";
		String tipoImporte = "1";

		// Then
		Respuesta<Boolean> respMontoValido = nuevoPagoBO.validacionMonto(montoFactura, montoPago, tipoImporte);

		// Expected
		Assert.assertNotNull(respMontoValido);
		Assert.assertEquals(EstadoRespuesta.OK, respMontoValido.getEstadoRespuesta());
		Assert.assertEquals(Boolean.TRUE, respMontoValido.getRespuesta());
	}

	/**
	 * Validacion monto importe convert exception.
	 */
	@Test
	public void validacionMontoImporteConvertException() {
		// Given
		String montoFactura = "20,00";
		String montoPago = "10#,00";
		String tipoImporte = "0";

		// Then
		Respuesta<Boolean> respMontoValido = nuevoPagoBO.validacionMonto(montoFactura, montoPago, tipoImporte);

		// Expected
		Assert.assertNotNull(respMontoValido);
		Assert.assertEquals(EstadoRespuesta.ERROR, respMontoValido.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				respMontoValido.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Validar cuit OK.
	 */
	@Test
	public void validarCuitOK() {
		// Given
		String cuit = "30619689255";

		// Then
		Respuesta<String> respCuitValido = nuevoPagoBO.validarCuit(cuit);

		// Expected
		Assert.assertNotNull(respCuitValido);
		Assert.assertEquals(EstadoRespuesta.OK, respCuitValido.getEstadoRespuesta());
		Assert.assertEquals("CUIT valido", respCuitValido.getRespuesta());
	}

	/**
	 * Validar cuit error.
	 */
	@Test
	public void validarCuitError() {
		// Given
		String cuit = "30619689250";
		Mensaje mensaje = MensajeMock.completarInfoMensaje("1206",
				"<p>No se registran deudas para la factura correspondiente al código ingresado.</p>");

		// When
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		// Then
		Respuesta<String> respCuitValido = nuevoPagoBO.validarCuit(cuit);

		// Expected
		Assert.assertNotNull(respCuitValido);
		Assert.assertEquals(EstadoRespuesta.WARNING, respCuitValido.getEstadoRespuesta());
		Assert.assertEquals(mensaje.getMensaje(), respCuitValido.getRespuesta());
	}

	/**
	 * Validar formateo Importe PMC.
	 */
	@Test
	public void formatearImportePMCTest() {
		String importe = "23777";
		String importeSinEspacios = importe.trim();
		String entero = importeSinEspacios.substring(0, importeSinEspacios.length() - 2);
		String decimal = importeSinEspacios.substring(importeSinEspacios.length() - 2);
		String importeF = entero + "," + decimal;
		Assert.assertEquals("237,77", importeF);
	}

	public void mockRespuestaMP() throws BusinessException {
		Respuesta<MedioPago> respMP = new Respuesta<MedioPago>();
		MedioPago medioPago = new MedioPago();
		medioPago.setTipoPago(3);
		respMP.setRespuesta(medioPago);
		Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respMP);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void pagarConTarjetaDeCreditoOKTest() throws DAOException, BusinessException, IllegalAccessException {
		// Given
		Cliente cliente = ClienteMock.completarInfoCliente();
		NuevoPago nuevoPago = new NuevoPago();
		nuevoPago.setFiid("UNCF");
		nuevoPago.setCodigoPagoElectronico("111111");
		nuevoPago.setMonto("20");
		nuevoPago.setFacturaNumero("                    ");
		nuevoPago.setReintentar("true");
		nuevoPago.setIsFromCalendario(Boolean.FALSE);
		Cuenta cuenta = new Cuenta();
		cuenta.setNroCuentaProducto("0000000017024994");
		cuenta.setNroSucursal("0037");
		cuenta.setMonedaAltair("ARS");
		cuenta.setTipoCuenta("07");
		cuenta.setNroTarjetaCredito("00004050710032218971");
		cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
		MedioPago medioPago = MedioPagoMock.completarInfo("UNICEF", 1, "1");
		PagoMisCuentasDTO pagoMisCuentasDTO = new PagoMisCuentasDTO(nuevoPago, cuenta, medioPago, true);
		PagoPMC pago = new PagoPMC();
		pago.setCodigoEmpresa("UNCF");
		pago.setComprobantePorServicio("000000002222");
		pago.setEstadoPago(0);
		pago.setFechaHoraBody("20180208110622");
		pago.setIdentificacion("111111");
		pago.setMoneda("ARS");
		pago.setMonto("00000000002000");
		pago.setNumeroControl("1234");
		pago.setNumeroCuenta("000017024994");
		pago.setNumeroFactura("                    ");
		pago.setOperacionDescripcion("pago");
		pago.setReintentar(Boolean.FALSE);
		pago.setRespuestaOK(Boolean.TRUE);
		pago.setSucursalCuenta("0037");
		pago.setTipoCuenta("07");
		pago.setTipoMonto("0");
		pago.setTipoSeleccion("I");

		// When
		Mockito.when(mediosPagoBO.obtenerTipoMedioPago(medioPago)).thenReturn(electronicoMedioPagoBO);
		Mockito.when(electronicoMedioPagoBO.generarPagoInEntity(Matchers.any(PagoMisCuentasDTO.class)))
				.thenReturn(pago);
		Mockito.when(
				pagoMisCuentasDAO.invocarPMCConTarjetaCredito(Matchers.any(List.class), Matchers.any(Cliente.class)))
				.thenReturn(pago);
		Mockito.when(altaBO.altaScompTransferencia(Matchers.any(AltaComprobanteRequestBuilder.class)))
				.thenReturn(new Respuesta<Void>());

		// Then
		NuevaRecargaOutDTO resp = nuevoPagoBO.pagar(medioPago, pagoMisCuentasDTO, cliente);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(Boolean.TRUE, resp.getRespuestaOK());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void pagarConTarjetaDeCreditoError2Test() throws DAOException, BusinessException, IllegalAccessException {
		// Given
		Cliente cliente = ClienteMock.completarInfoCliente();
		NuevoPago nuevoPago = new NuevoPago();
		nuevoPago.setFiid("UNCF");
		nuevoPago.setCodigoPagoElectronico("111111");
		nuevoPago.setMonto("20");
		nuevoPago.setFacturaNumero("                    ");
		nuevoPago.setReintentar("true");
		nuevoPago.setIsFromCalendario(Boolean.FALSE);
		Cuenta cuenta = new Cuenta();
		cuenta.setNroCuentaProducto("0000000017024994");
		cuenta.setNroSucursal("0037");
		cuenta.setMonedaAltair("ARS");
		cuenta.setTipoCuenta("07");
		cuenta.setNroTarjetaCredito("00004050710032218971");
		cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
		MedioPago medioPago = MedioPagoMock.completarInfo("UNICEF", 1, "1");
		PagoMisCuentasDTO pagoMisCuentasDTO = new PagoMisCuentasDTO(nuevoPago, cuenta, medioPago, true);
		PagoPMC pago = new PagoPMC();
		pago.setCodigoEmpresa("UNCF");
		pago.setComprobantePorServicio("            ");
		pago.setErrorGeneralRollback(Boolean.FALSE);
		pago.setEstadoPago(0);
		pago.setFechaHoraBody("20180208110622");
		pago.setIdentificacion("111111                          ");
		pago.setMensaje("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ $ 20,00</b>.</p>");
		pago.setMoneda("ARS");
		pago.setMoneda("00000000002000");
		pago.setNumeroControl("    ");
		pago.setNumeroCuenta("000017024994");
		pago.setNumeroFactura("                    ");
		pago.setOperacionDescripcion("pago");
		pago.setReintentar(Boolean.FALSE);
		pago.setRespuestaOK(Boolean.FALSE);
		pago.setSucursalCuenta("0037");
		pago.setTipoCuenta("07");
		pago.setTipoMonto("0");
		pago.setTipoSeleccion("I");
		pago.setTipoError(TipoError.ERROR_GENERICO);

		// When
		Mockito.when(mediosPagoBO.obtenerTipoMedioPago(medioPago)).thenReturn(electronicoMedioPagoBO);
		Mockito.when(electronicoMedioPagoBO.generarPagoInEntity(Matchers.any(PagoMisCuentasDTO.class)))
				.thenReturn(pago);
		Mockito.when(
				pagoMisCuentasDAO.invocarPMCConTarjetaCredito(Matchers.any(List.class), Matchers.any(Cliente.class)))
				.thenReturn(pago);
		Mockito.when(altaBO.altaScompTransferencia(Matchers.any(AltaComprobanteRequestBuilder.class)))
				.thenReturn(new Respuesta<Void>());

		// Then
		NuevaRecargaOutDTO resp = nuevoPagoBO.pagar(medioPago, pagoMisCuentasDTO, cliente);

		// Expected
		Assert.assertNotNull(resp);
		Assert.assertEquals(Boolean.FALSE, resp.getRespuestaOK());
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Test(expected = BusinessException.class)
	public void pagarConTarjetaDeCreditoError1Test() throws DAOException, BusinessException, IllegalAccessException {
		// Given
		Cliente cliente = ClienteMock.completarInfoCliente();
		NuevoPago nuevoPago = new NuevoPago();
		nuevoPago.setFiid("UNCF");
		nuevoPago.setCodigoPagoElectronico("111111");
		nuevoPago.setMonto("20");
		nuevoPago.setFacturaNumero("                    ");
		nuevoPago.setReintentar("true");
		nuevoPago.setIsFromCalendario(Boolean.FALSE);
		Cuenta cuenta = new Cuenta();
		cuenta.setNroCuentaProducto("0000000017024994");
		cuenta.setNroSucursal("0037");
		cuenta.setMonedaAltair("ARS");
		cuenta.setTipoCuenta("07");
		cuenta.setNroTarjetaCredito("00004050710032218971");
		cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
		MedioPago medioPago = MedioPagoMock.completarInfo("UNICEF", 1, "1");
		PagoMisCuentasDTO pagoMisCuentasDTO = new PagoMisCuentasDTO(nuevoPago, cuenta, medioPago, true);
		PagoPMC pago = new PagoPMC();
		pago.setCodigoEmpresa("UNCF");
		pago.setComprobantePorServicio("            ");
		pago.setErrorGeneralRollback(Boolean.FALSE);
		pago.setEstadoPago(0);
		pago.setFechaHoraBody("20180208110622");
		pago.setIdentificacion("111111                          ");
		pago.setMensaje("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ $ 20,00</b>.</p>");
		pago.setMoneda("ARS");
		pago.setMoneda("00000000002000");
		pago.setNumeroControl("    ");
		pago.setNumeroCuenta("000017024994");
		pago.setNumeroFactura("                    ");
		pago.setOperacionDescripcion("pago");
		pago.setReintentar(Boolean.FALSE);
		pago.setRespuestaOK(Boolean.FALSE);
		pago.setSucursalCuenta("0037");
		pago.setTipoCuenta("07");
		pago.setTipoMonto("0");
		pago.setTipoSeleccion("I");
		pago.setTipoError(TipoError.ERROR_GENERICO);

		// When
		Mockito.when(mediosPagoBO.obtenerTipoMedioPago(medioPago)).thenReturn(electronicoMedioPagoBO);
		Mockito.when(electronicoMedioPagoBO.generarPagoInEntity(Matchers.any(PagoMisCuentasDTO.class)))
				.thenReturn(pago);
		Mockito.when(
				pagoMisCuentasDAO.invocarPMCConTarjetaCredito(Matchers.any(List.class), Matchers.any(Cliente.class)))
				.thenThrow(new DAOException("Cod de error 10000000"));
		Mockito.when(altaBO.altaScompTransferencia(Matchers.any(AltaComprobanteRequestBuilder.class)))
				.thenReturn(new Respuesta<Void>());

		// Then
		NuevaRecargaOutDTO resp = nuevoPagoBO.pagar(medioPago, pagoMisCuentasDTO, cliente);
	}
}
