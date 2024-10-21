/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.bo;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.transferencias.feature.TransferenciaPorAliasBOFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TitularidadExtendido;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.AliasCorrespondienteCuentaPropiaException;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.AltaDestinatarioAliasBOImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConsultarDatosTitularidadExtendidoResponseMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.CuentaDTOMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.TitularidadExtendidoMock;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasInexistenteEliminadoException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.web.view.AyudaView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.TransferenciaPorAliasBOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


/**
 * BO Alias
 * 
 * @author B041299
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TransferenciaPorAliasBOTest {

	private static final String PESOS = "PESOS";

	private static final String NroCTAPRODUCTO = "0601364";

	private static final String NroSUCURSAL = "000";

	private static final String CUITCUIL = "27057993575";

	private static final String CBU = "0720126020000002176936";
	
	private static final String CBU2 = "0720000755000006013640";

	/** The mock estadistica manager. */
	@Mock
	private EstadisticaManagerImpl mockEstadisticaManager;

	/** The respuesta factory. */
	@Mock
	private RespuestaFactory respuestaFactory;

	@Mock
	private MensajeBO mockMensajeBO;

	/** The mock mensaje manager. */
	@Mock
	private MensajeManager mockMensajeManager;

	@Mock
	private Cliente clienteMock;

	/** The mock cuenta manager. */
	@Mock
	private CuentaManagerImpl mockCuentaManager;

	@Mock
	private AltaDestinatarioAliasBOImpl mockAltaDestinatarioAliasBO;

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

	/** The sesion del cliente. */
	@Mock
	private SesionCliente sesionCliente;

	@InjectMocks
	private TransferenciaPorAliasBOImpl transferenciaPorAliasBOImpl;

	private List<AyudaView> ayudaViewList;

	private Cliente cliente;

	/** The respuesta cuentas view ok. */
	private Respuesta<CuentasView> respuestaCuentasViewOk;
	
	private Respuesta<CuentasView> cuentasViewUnicaCta;

	private Respuesta<TransferenciaDTO> respuestaTransferenciaDTO1;
	private Respuesta<TransferenciaDTO> respuestaTransferenciaDTO2;
	private Respuesta<TransferenciaDTO> respuestaTransferenciaDTO3;
	private Respuesta<TransferenciaDTO> respuestaTransferenciaDTO4;
	private TransferenciaDTO transferenciaDTO4;

	private List<Cuenta> listaCuentas = new ArrayList<Cuenta>();

	private String agent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";

	/**
	 * Inits tests.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		ayudaViewList = new ArrayList<AyudaView>();
		AyudaView ayuda = new AyudaView("title", "message");
		ayudaViewList.add(ayuda);

		cliente = new Cliente();
		Cuenta cuenta1 = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		cuenta1.setEstadoTarjetaCredito("01");
		cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		cuenta1.setNroSucursal("152");
		cuenta1.setNroTarjetaCredito("4517660024736620");
		cuenta1.setNroCuentaProducto("0000000000639170");
		cuenta1.setTipoCuentaSinUnificar("1");
		cuenta2.setEstadoTarjetaCredito("01");
		cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
		cuenta2.setNroSucursal("152");
		cuenta2.setNroTarjetaCredito("4517660024736620");
		cuenta2.setTipoCuentaSinUnificar("2");
		listaCuentas.add(cuenta1);
		listaCuentas.add(cuenta2);
		cliente.setCuentas(listaCuentas);
		
		//
		respuestaTransferenciaDTO1 = new Respuesta<TransferenciaDTO>();
		respuestaTransferenciaDTO1.setEstadoRespuesta(EstadoRespuesta.OK);
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO.setHaciaCuentaPropia(true);
		transferenciaDTO.setHaciaOtroBanco(false);
		respuestaTransferenciaDTO1.setRespuesta(transferenciaDTO);
		respuestaTransferenciaDTO2 = new Respuesta<TransferenciaDTO>();
		respuestaTransferenciaDTO3 = new Respuesta<TransferenciaDTO>();
		transferenciaDTO4 = new TransferenciaDTO();

		//
		CuentasView cuentaView = new CuentasView();
		List<CuentasAdhesionDebitoView> listCuentaView = new ArrayList<CuentasAdhesionDebitoView>();
		CuentasAdhesionDebitoView ctaView = new CuentasAdhesionDebitoView();
		ctaView.setNumero("333-666654/7");
		ctaView.setSaldoPesos("500000");
		ctaView.setSaldoDolares("35000");
		ctaView.setSignoSaldoPesos("");
		ctaView.setIsCerrada(false);
		ctaView.setDescripcionTipoCuenta("Caja de ahorro en $");
		ctaView.setAbreviaturaTipoCuenta("CAP");
		ctaView.setCbu("0720000755000006013640");
		listCuentaView.add(ctaView);
		cuentaView.setCuentas(listCuentaView);
		cuentasViewUnicaCta = new Respuesta<CuentasView>();
		cuentasViewUnicaCta.setEstadoRespuesta(EstadoRespuesta.OK);
		cuentasViewUnicaCta.setRespuesta(cuentaView);
		
		// Cuentas saldo
		CuentasView cuentasView = new CuentasView();
		List<CuentasAdhesionDebitoView> listCuentasView = new ArrayList<CuentasAdhesionDebitoView>();
		CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
		CuentasAdhesionDebitoView cuentaView2 = new CuentasAdhesionDebitoView();
		cuentaView1.setNumero("333-666654/7");
		cuentaView1.setSaldoPesos("500000");
		cuentaView1.setSaldoDolares("35000");
		cuentaView1.setSignoSaldoPesos("");
		cuentaView1.setIsCerrada(false);
		cuentaView1.setDescripcionTipoCuenta("Caja de ahorro en $");
		cuentaView1.setAbreviaturaTipoCuenta("CAP");
		cuentaView1.setCbu("0720126020000002176936");
		listCuentasView.add(cuentaView1);
		cuentaView2.setIsCerrada(false);
		cuentaView2.setNumero("333-666655/7");
		cuentaView2.setSaldoPesos("500000");
		cuentaView2.setSaldoDolares("35000");
		cuentaView2.setSignoSaldoPesos("");
		cuentaView2.setDescripcionTipoCuenta("Caja de ahorro en $");
		cuentaView2.setAbreviaturaTipoCuenta("CAP");
		listCuentasView.add(cuentaView2);
		cuentasView.setCuentas(listCuentasView);
		respuestaCuentasViewOk = new Respuesta<CuentasView>();
		respuestaCuentasViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaCuentasViewOk.setRespuesta(cuentasView);
	}

	/**
	 * en TransferenciaPorAliasBO. Respuesta
	 * <TransferenciaDTO> consultarDatosTitularidadExtendida( TransferenciaView
	 * transferenciaView, Cliente cliente, String userAgent)
	 *
	 * invoca a altaDestinatarioAliasBO.
	 * ConsultarDatosTitularidadExtendidoResponse
	 * realizarConsultaWSconCuentaSaldo( cliente, userAgent, cuentaSeleccionada,
	 * transferenciaView.getAliasDestino(),
	 * getNombreMonedaSingular(transferenciaView.getMoneda()))
	 * 
	 * @throws ValidacionAliasInexistenteEliminadoException
	 * @throws DAOException
	 */
	@Test
	@Ignore
	public void consultarDatosTitularidadExtendida_OK_RioRio()
			throws DAOException, ValidacionAliasInexistenteEliminadoException {
		TransferenciaView transferenciaView = armarTransferenciaView_CUP_RRio();
		transferenciaView.setCbu(CBU);
		
		IdentificacionCuenta numeroCuentaDestino = new IdentificacionCuenta();
		numeroCuentaDestino.setNroSucursal(NroSUCURSAL);
		numeroCuentaDestino.setNroCuentaProducto(NroCTAPRODUCTO);
		transferenciaDTO4.setNumeroCuentaDestino(numeroCuentaDestino );
		transferenciaDTO4.setCuit(CUITCUIL);
		Respuesta<TransferenciaDTO> responseFactoryOK = new Respuesta<TransferenciaDTO>();
		responseFactoryOK.setEstadoRespuesta(EstadoRespuesta.OK);
		responseFactoryOK.setRespuesta(transferenciaDTO4);
		responseFactoryOK.setRespuestaVacia(false);
		
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(TransferenciaDTO.class), Matchers.anyString(),
				Matchers.anyString())).thenReturn(responseFactoryOK);
		Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewOk);
		Mockito.when(mockAltaDestinatarioAliasBO.realizarConsultaWSconCuentaSaldo(Matchers.any(Cliente.class),
				Matchers.anyString(), Matchers.any(CuentasAdhesionDebitoView.class), Matchers.anyString(),
				Matchers.any(DivisaEnum.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCBanelcoDolaresRio())));
		//
		respuestaTransferenciaDTO1 = transferenciaPorAliasBOImpl.consultarDatosTitularidadExtendida(transferenciaView,
				cliente, agent);
		//
		Assert.assertNotNull(respuestaTransferenciaDTO1);
		Assert.assertNotNull(respuestaTransferenciaDTO1.getEstadoRespuesta());
		Assert.assertEquals(NroCTAPRODUCTO,
				respuestaTransferenciaDTO1.getRespuesta().getNumeroCuentaDestino().getNroCuentaProducto());
		Assert.assertEquals(NroSUCURSAL,
				respuestaTransferenciaDTO1.getRespuesta().getNumeroCuentaDestino().getNroSucursal());
		Assert.assertEquals(CUITCUIL, respuestaTransferenciaDTO1.getRespuesta().getCuit());
	}
	
	/**
	 * Consultar datos titularidad extendida WARNING 
	 * rio-rio CUENTA INACTIVA.
	 * con (Titular nulo).
	 * 
	 * 
	 * @throws DAOException the DAO exception
	 * @throws ValidacionAliasInexistenteEliminadoException the validacion alias inexistente eliminado exception
	 */
	@Test
	@Ignore
	public void consultarDatosTitularidadExtendida_WARNING_RioRio_CUENTA_INACTIVA()
			throws DAOException, ValidacionAliasInexistenteEliminadoException {
		String CUENTA_INACTIVA_160 = "0160";
		String CUENTA_INACTIVA_mensaje = "CUENTA_INACTIVA_160;";
		TransferenciaView transferenciaView = armarTransferenciaView_CUP_RRio();

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.WARNING);
		responseFactoryError.setItemMensajeRespuesta(
				this.obtenerListaMensajesRespuesta(TipoError.ALIAS_CUENTA_INACTIVA.getDescripcion(), "ALIAS_CUENTA_INACTIVA"));
		responseFactoryError.setRespuestaVacia(true);

		ConsultarDatosTitularidadExtendidoResponse consultarTitularidadConCodigoError = new ConsultarDatosTitularidadExtendidoResponse();
		consultarTitularidadConCodigoError.setCodigo (CUENTA_INACTIVA_160);		
		consultarTitularidadConCodigoError.setMensaje(CUENTA_INACTIVA_mensaje);
		
		Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_CUENTA_INACTIVA,
				CodigoMensajeConstantes.ALIAS_CON_CUENTA_INACTIVA)).thenReturn(responseFactoryError);
		Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewOk);
		Mockito.when(mockAltaDestinatarioAliasBO.realizarConsultaWSconCuentaSaldo(Matchers.any(Cliente.class),
				Matchers.anyString(), Matchers.any(CuentasAdhesionDebitoView.class), Matchers.anyString(),
				Matchers.any(DivisaEnum.class))).thenReturn(consultarTitularidadConCodigoError);
		//
		respuestaTransferenciaDTO4 = transferenciaPorAliasBOImpl.consultarDatosTitularidadExtendida(transferenciaView,
				cliente, agent);
		//
		Assert.assertNotNull(respuestaTransferenciaDTO4);
		Assert.assertNull(respuestaTransferenciaDTO4.getRespuesta());
		Assert.assertEquals(TipoError.ALIAS_CUENTA_INACTIVA.getDescripcion(),
				respuestaTransferenciaDTO4.getItemsMensajeRespuesta().get(0).getTipoError());
	}
	
	
	
	/**
	 * Consultar datos titularidad extendida WARNING 
	 * rio-rio ALIAS_ELIMINADO.
	 * con (Titular nulo).
	 * 
	 * 
	 * @throws DAOException the DAO exception
	 * @throws ValidacionAliasInexistenteEliminadoException the validacion alias inexistente eliminado exception
	 */
	@Test
	@Ignore
	public void consultarDatosTitularidadExtendida_WARNING_RioRio_ALIAS_ELIMINADO()
			throws DAOException, ValidacionAliasInexistenteEliminadoException {
		String CUENTA_INACTIVA_160 = "0190";
		String CUENTA_INACTIVA_mensaje = "ALIAS_ELIMINADO_0190;";
		TransferenciaView transferenciaView = armarTransferenciaView_CUP_RRio();

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.WARNING);
		responseFactoryError.setItemMensajeRespuesta(
				this.obtenerListaMensajesRespuesta(TipoError.ALIAS_ELIMINADO.getDescripcion(), "ALIAS_ELIMINADO"));
		responseFactoryError.setRespuestaVacia(true);

		ConsultarDatosTitularidadExtendidoResponse consultarTitularidadConCodigoError = new ConsultarDatosTitularidadExtendidoResponse();
		consultarTitularidadConCodigoError.setCodigo (CUENTA_INACTIVA_160);		
		consultarTitularidadConCodigoError.setMensaje(CUENTA_INACTIVA_mensaje);
		
		Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_ELIMINADO,
				CodigoMensajeConstantes.ALIAS_INEXISTENTE_ELIMINADO)).thenReturn(responseFactoryError);
		Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewOk);
		Mockito.when(mockAltaDestinatarioAliasBO.realizarConsultaWSconCuentaSaldo(Matchers.any(Cliente.class),
				Matchers.anyString(), Matchers.any(CuentasAdhesionDebitoView.class), Matchers.anyString(),
				Matchers.any(DivisaEnum.class))).thenReturn(consultarTitularidadConCodigoError);
		//
		respuestaTransferenciaDTO4 = transferenciaPorAliasBOImpl.consultarDatosTitularidadExtendida(transferenciaView,
				cliente, agent);
		//
		Assert.assertNotNull(respuestaTransferenciaDTO4);
		Assert.assertNull(respuestaTransferenciaDTO4.getRespuesta());
		Assert.assertEquals(TipoError.ALIAS_ELIMINADO.getDescripcion(),
				respuestaTransferenciaDTO4.getItemsMensajeRespuesta().get(0).getTipoError());
	}
	
	/**
	 * Consultar datos titularidad extendida WARNING 
	 * rio-rio MONEDA_INCOMPATIBLE.
	 * con (Titular nulo).
	 * 
	 * 
	 * @throws DAOException the DAO exception
	 * @throws ValidacionAliasInexistenteEliminadoException the validacion alias inexistente eliminado exception
	 */
	@Test
	@Ignore
	public void consultarDatosTitularidadExtendida_WARNING_RioRio_MONEDA_INCOMPATIBLE()
			throws DAOException, ValidacionAliasInexistenteEliminadoException {
		String MONEDA_INCOMPATIBLE_36 = "0036";
		String MONEDA_INCOMPATIBLE_mensaje = "MONEDA_INCOMPATIBLE_36;";
		TransferenciaView transferenciaView = armarTransferenciaView_CUP_RRio();

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.WARNING);
		responseFactoryError.setItemMensajeRespuesta(
				this.obtenerListaMensajesRespuesta(TipoError.ALIAS_NO_COINCIDE_MONEDA.getDescripcion(), "MONEDA_INCOMPATIBLE"));
		responseFactoryError.setRespuestaVacia(true);

		ConsultarDatosTitularidadExtendidoResponse consultarTitularidadConCodigoError = new ConsultarDatosTitularidadExtendidoResponse();
		consultarTitularidadConCodigoError.setCodigo (MONEDA_INCOMPATIBLE_36);		
		consultarTitularidadConCodigoError.setMensaje(MONEDA_INCOMPATIBLE_mensaje);
		
		Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_NO_COINCIDE_MONEDA,
				CodigoMensajeConstantes.CLIENTE_NO_COINCIDE_MONEDA)).thenReturn(responseFactoryError);
		Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewOk);
		Mockito.when(mockAltaDestinatarioAliasBO.realizarConsultaWSconCuentaSaldo(Matchers.any(Cliente.class),
				Matchers.anyString(), Matchers.any(CuentasAdhesionDebitoView.class), Matchers.anyString(),
				Matchers.any(DivisaEnum.class))).thenReturn(consultarTitularidadConCodigoError);
		//
		respuestaTransferenciaDTO4 = transferenciaPorAliasBOImpl.consultarDatosTitularidadExtendida(transferenciaView,
				cliente, agent);
		//
		Assert.assertNotNull(respuestaTransferenciaDTO4);
		Assert.assertNull(respuestaTransferenciaDTO4.getRespuesta());
		Assert.assertEquals(TipoError.ALIAS_NO_COINCIDE_MONEDA.getDescripcion(),
				respuestaTransferenciaDTO4.getItemsMensajeRespuesta().get(0).getTipoError());
	}
	
	/**
	 * Consultar datos titularidad extendida WARNING 
	 * rio-rio ALIAS_NO_EXISTE.
	 * con (Titular nulo).
	 * 
	 * 
	 * @throws DAOException the DAO exception
	 * @throws ValidacionAliasInexistenteEliminadoException the validacion alias inexistente eliminado exception
	 */
	@Test
	@Ignore
	public void consultarDatosTitularidadExtendida_WARNING_RioRio_ALIAS_NO_EXISTE()
			throws DAOException, ValidacionAliasInexistenteEliminadoException {
		String ALIAS_NO_EXISTE_0110 = "0110";
		String ALIAS_NO_EXISTE_mensaje = "ALIAS_NO_EXISTE_0110;";
		TransferenciaView transferenciaView = armarTransferenciaView_CUP_RRio();

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.WARNING);
		responseFactoryError.setItemMensajeRespuesta(
				this.obtenerListaMensajesRespuesta(TipoError.ALIAS_INEXISTENTE.getDescripcion(), "ALIAS_NO_EXISTE"));
		responseFactoryError.setRespuestaVacia(true);

		ConsultarDatosTitularidadExtendidoResponse consultarTitularidadConCodigoError = new ConsultarDatosTitularidadExtendidoResponse();
		consultarTitularidadConCodigoError.setCodigo (ALIAS_NO_EXISTE_0110);		
		consultarTitularidadConCodigoError.setMensaje(ALIAS_NO_EXISTE_mensaje);
		
		Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_INEXISTENTE,
				CodigoMensajeConstantes.FORMATO_ALIAS_INEXISTENTE)).thenReturn(responseFactoryError);
		Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewOk);
		Mockito.when(mockAltaDestinatarioAliasBO.realizarConsultaWSconCuentaSaldo(Matchers.any(Cliente.class),
				Matchers.anyString(), Matchers.any(CuentasAdhesionDebitoView.class), Matchers.anyString(),
				Matchers.any(DivisaEnum.class))).thenReturn(consultarTitularidadConCodigoError);
		//
		respuestaTransferenciaDTO4 = transferenciaPorAliasBOImpl.consultarDatosTitularidadExtendida(transferenciaView,
				cliente, agent);
		//
		Assert.assertNotNull(respuestaTransferenciaDTO4);
		Assert.assertNull(respuestaTransferenciaDTO4.getRespuesta());
		Assert.assertEquals(TipoError.ALIAS_INEXISTENTE.getDescripcion(),
				respuestaTransferenciaDTO4.getItemsMensajeRespuesta().get(0).getTipoError());
	}
	
	
	/**
	 * Consultar datos titularidad extendida ERROR 
	 * rio-rio ERROR_GENERICO.
	 * con (Titular nulo).
	 * 
	 * 
	 * @throws DAOException the DAO exception
	 * @throws ValidacionAliasInexistenteEliminadoException the validacion alias inexistente eliminado exception
	 */
	@Test
	@Ignore
	public void consultarDatosTitularidadExtendida_WARNING_RioRio_ERROR_GENERICO()
			throws DAOException, ValidacionAliasInexistenteEliminadoException {

		TransferenciaView transferenciaView = armarTransferenciaView_CUP_RRio();

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setItemMensajeRespuesta(
				this.obtenerListaMensajesRespuesta(TipoError.ERROR_GENERICO.getDescripcion(), "ERROR_GENERICO"));
		responseFactoryError.setRespuestaVacia(true);

		ConsultarDatosTitularidadExtendidoResponse consultarTitularidadConCodigoError = new ConsultarDatosTitularidadExtendidoResponse();

		
		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_GENERICO)).thenReturn(responseFactoryError);
		Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewOk);
		Mockito.when(mockAltaDestinatarioAliasBO.realizarConsultaWSconCuentaSaldo(Matchers.any(Cliente.class),
				Matchers.anyString(), Matchers.any(CuentasAdhesionDebitoView.class), Matchers.anyString(),
				Matchers.any(DivisaEnum.class))).thenReturn(consultarTitularidadConCodigoError);
		//
		respuestaTransferenciaDTO4 = transferenciaPorAliasBOImpl.consultarDatosTitularidadExtendida(transferenciaView,
				cliente, agent);
		//
		Assert.assertNotNull(respuestaTransferenciaDTO4);
		Assert.assertNull(respuestaTransferenciaDTO4.getRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				respuestaTransferenciaDTO4.getItemsMensajeRespuesta().get(0).getTipoError());
	}
	
	/**
	 * Consultar datos titularidad extendida ERROR rio-rio CTA DEST PROPIA Y TIENE 1 CTA.
	 *
	 * @throws ValidacionAliasInexistenteEliminadoException the validacion alias inexistente eliminado exception
	 * @throws DAOException the DAO exception
	 */
	@Test
	@Ignore
	public void consultarDatosTitularidadExtendida_ERROR_RioRio_CTA_DEST_PROPIA_Y_TIENE_1_CTA()
			throws ValidacionAliasInexistenteEliminadoException, DAOException {
		
		Cliente cliente = armarClienteConUnaCTA();		
		TransferenciaView transferenciaView = armarTransferenciaView_CUP_RRio();

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setItemMensajeRespuesta(this.obtenerListaMensajesRespuesta(
				TipoError.ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN_CON_SOLO_UNA_CUENTA.getDescripcion(), "error"));
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("",
				TipoError.ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN_CON_SOLO_UNA_CUENTA,
				CodigoMensajeConstantes.CUENTA_PROPIA_Y_UNICA)).thenReturn(responseFactoryError);
		Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(cuentasViewUnicaCta);
		Mockito.when(mockAltaDestinatarioAliasBO.realizarConsultaWSconCuentaSaldo(Matchers.any(Cliente.class),
				Matchers.anyString(), Matchers.any(CuentasAdhesionDebitoView.class), Matchers.anyString(),
				Matchers.any(DivisaEnum.class)))
				.thenReturn(ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
						.completarInfoTitularidadRio(CuentaDTOMock.completarInfoCtaDestinoCBanelcoDolaresRio())));
		//
		respuestaTransferenciaDTO2 = transferenciaPorAliasBOImpl.consultarDatosTitularidadExtendida(transferenciaView,
				cliente, agent);
		//
		Assert.assertNotNull(respuestaTransferenciaDTO2);
		Assert.assertNull(respuestaTransferenciaDTO2.getRespuesta());
		Assert.assertEquals(TipoError.ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN_CON_SOLO_UNA_CUENTA.getDescripcion(),
				respuestaTransferenciaDTO2.getItemsMensajeRespuesta().get(0).getTipoError());
	}
	
	
	
	/**
	 * Consultar datos titularidad extendida ERRO Tx a RioRio CTA DEST PROPIA.
	 *
	 * desde Agenda de destinatarios.
	 *
	 * @throws ValidacionAliasInexistenteEliminadoException the validacion alias inexistente eliminado exception
	 * @throws DAOException the DAO exception
	 */
	//TODO: completar para Agenda
	@Test
	@Ignore
	public void consultarDatosTitularidadExtendida_ERROR_RR_CTA_DEST_PROPIA_desde_agenda()
			throws ValidacionAliasInexistenteEliminadoException, DAOException {
		
		//Cliente cliente = armarClienteConUnaCTA();		
		TransferenciaView transferenciaView = armarTransferenciaView_CUP_RRio();
		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setItemMensajeRespuesta(this.obtenerListaMensajesRespuesta(
				TipoError.ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN_CON_SOLO_UNA_CUENTA.getDescripcion(), "error"));
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("",
				TipoError.ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN_CON_SOLO_UNA_CUENTA,
				CodigoMensajeConstantes.CUENTA_PROPIA_Y_UNICA)).thenReturn(responseFactoryError);

		CuentaDTO completarInfoCtaDestinoCCPesosCtaPropia = CuentaDTOMock.completarInfoCtaDestinoCCPesosCtaPropia();
		completarInfoCtaDestinoCCPesosCtaPropia.setNumeroCBU(CBU2);
		ConsultarDatosTitularidadExtendidoResponse datosTitularDestinoPropio = ConsultarDatosTitularidadExtendidoResponseMock.completarInfoRio(TitularidadExtendidoMock
				.completarInfoTitularidadRio(completarInfoCtaDestinoCCPesosCtaPropia));
		
		Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(cuentasViewUnicaCta);				
		Mockito.when(mockAltaDestinatarioAliasBO.realizarConsultaWSconCuentaSaldo(Matchers.any(Cliente.class),
				Matchers.anyString(), Matchers.any(CuentasAdhesionDebitoView.class), Matchers.anyString(),
				Matchers.any(DivisaEnum.class)))
				.thenReturn(datosTitularDestinoPropio);
		//
		respuestaTransferenciaDTO2 = transferenciaPorAliasBOImpl.consultarDatosTitularidadExtendida(transferenciaView,
				cliente, agent);
		//
		Assert.assertNotNull(respuestaTransferenciaDTO2);
		Assert.assertNull(respuestaTransferenciaDTO2.getRespuesta());
		Assert.assertEquals(TipoError.ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN_CON_SOLO_UNA_CUENTA.getDescripcion(),
				respuestaTransferenciaDTO2.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * @return
	 */
	private Cliente armarClienteConUnaCTA() {
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		List<Cuenta> listaUnaCuenta = new ArrayList<Cuenta>();
		cuenta.setEstadoTarjetaCredito("01");
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		cuenta.setNroSucursal("152");
		cuenta.setNroTarjetaCredito("4517660024736620");
		cuenta.setNroCuentaProducto("0000000000639170");
		listaUnaCuenta.add(cuenta);
		cliente.setCuentas(listaUnaCuenta);
		return cliente;
	}

	/**
	 * Armar transferencia view CU Pesos Rio-Rio.
	 *
	 * @return the transferencia view
	 */
	private TransferenciaView armarTransferenciaView_CUP_RRio() {
		TransferenciaView transferenciaView = new TransferenciaView();
		transferenciaView.setNroCuentaDestino("168-356669/3");
		transferenciaView.setConceptoTransferencia(ConceptoTransferenciaEnum.getConceptoView());
		transferenciaView.setTitular("Nombre Titular Cuenta");
		transferenciaView.setTipoCuentaDestino("CU");
		transferenciaView.setCuentasView(respuestaCuentasViewOk.getRespuesta());
		transferenciaView.setMoneda(PESOS);
		ArrayList<String> monedasDisponibles = new ArrayList<String>();
		monedasDisponibles.add("Pesos");
		transferenciaView.setMonedasDisponibles(monedasDisponibles);
		return transferenciaView;
	}

	/**
	 * Obtener lista mensajes respuesta. Util.
	 *
	 * @param descripcionError
	 *            the descripcion error
	 * @param msj
	 *            the msj
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerListaMensajesRespuesta(String descripcionError, String msj) {
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTipoError(descripcionError);
		item.setMensaje(msj);
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(item);
		return items;
	}
}
