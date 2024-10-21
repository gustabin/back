
package ar.com.santanderrio.obp.servicios.pagos.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoMultipleBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoMultipleDTO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.PagoMultipleManager;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.impl.PagoMultipleManagerImpl;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleListView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * Test para {@link PagoMultipleManager}.
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 29, 2016
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class PagoMultipleManagerTest {

	/** Elementos de pago para test. */
	PagoMultipleView pagoOK1;

	/** Elementos de pago para test. */
	PagoMultipleView pagoOK2;

	/** Elementos de pago para test. */
	PagoMultipleView pagoOK3;

	/** The pago DTO 1. */
	PagoInEntity pagoDTO1 = new PagoPMC();

	/** The pago DTO 2. */
	PagoInEntity pagoDTO2 = new PagoPMC();

	/** The pago DTO 3. */
	PagoInEntity pagoDTO3 = new PagoPMC();

	/** Respuestas de pago para test. */
	Respuesta<PagoMultipleView> respuestaError = new Respuesta<PagoMultipleView>();

	/** Respuestas de validacion de pago para test. */
	Respuesta<PagoMultipleView> respuestaWarnValidacion = new Respuesta<PagoMultipleView>();

	/** The respuesta pagos pendientes BO. */
	Respuesta<List<PagoPendiente>> respuestaPagosPendientesBO = new Respuesta<List<PagoPendiente>>();

	/** The respuesta pagos pendientes OK. */
	Respuesta<List<PagoPendiente>> respuestaPagosPendientesOK = new Respuesta<List<PagoPendiente>>();

	/** The medio de pago. */
	MedioPago mediodePago = new MedioPago();
	
	String idSesion;

	/** The pago multiple manager. */
	@InjectMocks
	private PagoMultipleManagerImpl pagoMultipleManager;

	/** The pago multiple BO. */
	@Mock
	private PagoMultipleBO pagoMultipleBO;

	/** The nuevo pago BO. */
	@Mock
	private NuevoPagoBO nuevoPagoBO;
	
	@Mock
    private ScompBO altaScompBO;

	/** The estadistica manager. */
	@Mock
	private EstadisticaManager estadisticaManager;

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Spy
	private SesionParametros sesionParametros;

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	/** The pagos pendientes BO. */
	@Mock
	private PagosPendientesBO pagosPendientesBO;

	/** The medios pago BO. */
	@Mock
	private MediosPagoBO mediosPagoBO;

	/** The legal BO. */
	@Mock
	private LegalBO legalBO;

	/** The respuesta factory. */
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/**
	 * Inits the test.
	 */
	@Before
	public void init() {
		Mockito.when(sesionCliente.getCliente()).thenReturn(Mockito.mock(Cliente.class));
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(Mockito.mock(Mensaje.class));
		PagoMultipleListView pagoMultipleListView = new PagoMultipleListView();
		pagoMultipleListView.generarNuevoIdSesion();
		idSesion = pagoMultipleListView.getIdSesion();
		
		ContadorIntentos contador = new ContadorIntentos();
		contador.setIdView(idSesion, 2, "pegarElError");
		
		Mockito.when(sesionParametros.getContador()).thenReturn(contador);

		pagoOK1 = new PagoMultipleView();
		pagoOK1.setCuitCliente("20334564567");
		pagoOK1.setCuitEmpleador("34334564567");
		pagoOK1.setPeriodoPago("042017");
		pagoOK1.setTipoMonto("0");
		pagoOK1.setTipoDeSeleccion("I");
		pagoOK1.setTipoPago("3");
		pagoOK1.setTipoEmpresa("F");
		pagoOK1.setMonto("000000000123.34");
		pagoOK1.setMoneda("ARS");
		pagoOK1.setCodigoEmpresa("REPE");
		pagoOK1.setIdentificacionCliente("1135141112         ");
		pagoOK1.setNumeroFactura("");
		pagoOK1.setSucursal("0100");
		pagoOK1.setNumeroCuenta("000002005294");
		pagoOK1.setNumeroFactura("1234567890123456789");

		pagoOK2 = new PagoMultipleView();
		pagoOK2.setCuitCliente("20334564567");
		pagoOK2.setCuitEmpleador("34334564567");
		pagoOK2.setPeriodoPago("042017");
		pagoOK2.setTipoMonto("0");
		pagoOK2.setTipoDeSeleccion("I");
		pagoOK2.setMonto("000000000123.34");
		pagoOK2.setMoneda("ARS");
		pagoOK2.setCodigoEmpresa("D265");
		pagoOK2.setIdentificacionCliente("20123458622082016  ");
		pagoOK2.setNumeroFactura("20123458622         ");
		pagoOK2.setSucursal("0033");
		pagoOK2.setNumeroCuenta("000000081995");
		pagoOK2.setNumeroFactura("1234567890123456789");
		
		pagoOK3 = new PagoMultipleView();
		pagoOK3.setCuitCliente("20334564567");
		pagoOK3.setCuitEmpleador("34334564567");
		pagoOK3.setPeriodoPago("042017");
		pagoOK3.setTipoMonto("0");
		pagoOK3.setTipoDeSeleccion("I");
		pagoOK3.setMonto("000000009123.55");
		pagoOK3.setMoneda("ARS");
		pagoOK3.setCodigoEmpresa("REPE");
		pagoOK3.setIdentificacionCliente("1135151112         ");
		pagoOK3.setNumeroFactura("");
		pagoOK3.setSucursal("0100");
		pagoOK3.setNumeroCuenta("000002005294");
		pagoOK3.setTipoPago("1");
		pagoOK3.setNumeroFactura("1234567890123456789");
		
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaWarnValidacion.setEstadoRespuesta(EstadoRespuesta.WARNING);

		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(Mockito.mock(Mensaje.class));
		mocksRespuestasOK();
		mocksRespuestasError();
		pagosPendientes();
	}

	/**
	 * Pagos pendientes.
	 */
	private void pagosPendientes() {
		List<PagoPendiente> listadeudas = new ArrayList<PagoPendiente>();
		respuestaPagosPendientesBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuestaPagosPendientesBO.setRespuestaVacia(false);
		respuestaPagosPendientesBO.setId(1L);
		PagoPendiente pago1 = new PagoPendiente();

		pagoOK3.setCuitCliente("20334564567");
		pagoOK3.setCuitEmpleador("33693450239");
		pagoOK3.setPeriodoPago("042017");
		pagoOK3.setTipoMonto("0");
		pagoOK3.setTipoDeSeleccion("I");
		pagoOK3.setMonto("000000009123.55");
		pagoOK3.setMoneda("ARS");
		pagoOK3.setCodigoEmpresa("SEA");
		pagoOK3.setIdentificacionCliente("2013238861021587183");
		pagoOK3.setNumeroFactura("0001109253641208000");
		pagoOK3.setSucursal("0100");
		pagoOK3.setNumeroCuenta("000002005294");

		pago1.setCodigoEmpresa("SEA");
		pago1.setCodigoClienteEmpresa("2013238861021587183");
		pago1.setNombreEmpresa("AFIP - PAGO DE IMPUESTOS AFIP (VEP)");
		pago1.setImporte(new BigDecimal("708.65"));
		pago1.setImporteUSS(new BigDecimal("0.00"));
		pago1.setTipoPago(TipoDePagoEnum.PAGOMISCUENTASPUNTUAL);
		pago1.setDivisa(DivisaEnum.PESO);
		pago1.setIdentificacionDeFactura("0001109253641208000");
		listadeudas.add(pago1);
		respuestaPagosPendientesBO.setRespuesta(listadeudas);
	}

	/**
	 * Pagos pendientes. para caso de ejecucion OK.
	 */
	private void pagosPendientesOK() {
		List<PagoPendiente> listadeudas = new ArrayList<PagoPendiente>();
		respuestaPagosPendientesOK.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuestaPagosPendientesOK.setRespuestaVacia(false);
		respuestaPagosPendientesOK.setId(1L);
		PagoPendiente pago1 = new PagoPendiente();

		pagoOK3.setCuitCliente("20334564567");
		pagoOK3.setCuitEmpleador("33693450239");
		pagoOK3.setPeriodoPago("042017");
		pagoOK3.setTipoMonto("0");
		pagoOK3.setTipoDeSeleccion("I");
		pagoOK3.setTipoPago("3");
		pagoOK3.setTipoEmpresa("F");
		pagoOK3.setMonto("000000009123.55");
		pagoOK3.setMontoInicial("000000009123.55");
		pagoOK3.setMontoSinFormatear("000000009123.55");
		pagoOK3.setMoneda("ARS");
		pagoOK3.setCodigoEmpresa("SEA");
		pagoOK3.setIdentificacionCliente("2013238861021587183");
		pagoOK3.setNumeroFactura("0001109253641208000");
		pagoOK3.setSucursal("0100");
		pagoOK3.setNumeroCuenta("000002005294");

		pago1.setCodigoEmpresa("SEA");
		pago1.setCodigoClienteEmpresa("2013238861021587183");
		pago1.setNombreEmpresa("AFIP - PAGO DE IMPUESTOS AFIP (VEP)");
		pago1.setImporte(new BigDecimal("708.65"));
		pago1.setImporteUSS(new BigDecimal("0.00"));
		pago1.setTipoPago(TipoDePagoEnum.PAGOMISCUENTASPUNTUAL);
		pago1.setDivisa(DivisaEnum.PESO);
		pago1.setIdentificacionDeFactura("0001109253641208000");
		this.medioDePagos();
		pago1.setMedioPago(mediodePago);
		listadeudas.add(pago1);
		respuestaPagosPendientesOK.setRespuesta(listadeudas);
	}

	/**
	 * Mocks respuestas error.
	 */
	private void mocksRespuestasError() {
		Respuesta<PagoMultipleView> respuestaPagoMultiple = new Respuesta<PagoMultipleView>();
		respuestaPagoMultiple.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.doReturn(respuestaPagoMultiple).when(respuestaFactory).crearRespuestaError(Matchers.any(Class.class),
				Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString());
		Mockito.doReturn(respuestaPagoMultiple).when(respuestaFactory)
				.crearRespuestaOk(Matchers.any(PagoMultipleView.class));

	}

	/**
	 * Mocks respuestas OK. Respuesta<List<PagoPendiente>> pagosPendientes =
	 * pagosPendientesBO.obtenerPagosPendientes(sesionCliente.getCliente());
	 * 
	 */
	private void mocksRespuestasOK() {
		Respuesta<Object> respuesta = new Respuesta();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.doReturn(respuesta).when(respuestaFactory).validate(Matchers.any());
		Respuesta<PagoMultipleView> respuestaPagoMultiple = new Respuesta<PagoMultipleView>();
		respuestaPagoMultiple.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.doReturn(respuestaPagoMultiple).when(respuestaFactory).crearRespuestaOk(Matchers.any(Class.class));
		Mockito.doReturn(respuestaPagoMultiple).when(respuestaFactory)
				.crearRespuestaOk(Matchers.any(PagoMultipleView.class));
		Respuesta<PagoMultipleDTO> respuestaEjecutar = new Respuesta<PagoMultipleDTO>();
		respuestaEjecutar.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(this.pagosPendientesBO.obtenerPagosPendientes(Matchers.any(Cliente.class)))
				.thenReturn(respuestaPagosPendientesBO);
		// Respuesta<List<PagoPendiente>> pagosPendientes =
		// pagosPendientesBO.obtenerPagosPendientes(sesionCliente.getCliente());

		Mockito.doReturn(respuestaEjecutar).when(pagoMultipleBO)
				.ejecutarPagoMultiple(Matchers.any(PagoMultipleListView.class), Matchers.any(Cliente.class));
	}

	/**
	 * Solicitar pago multiple test.
	 *
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	@Ignore
	public void solicitarPagoMultipleTest() throws BusinessException {
		PagoMultipleListView pagoMultipleListView = new PagoMultipleListView();
		Respuesta<MedioPagoSelectionView> respuestaCuentasMock = Mockito.mock(Respuesta.class);
		MedioPagoSelectionView medioPagoSelectionMock = Mockito.mock(MedioPagoSelectionView.class);

		Mockito.when(medioPagoSelectionMock.getListaCuentas()).thenReturn(Mockito.mock(List.class));
		Mockito.when(respuestaCuentasMock.getRespuesta()).thenReturn(medioPagoSelectionMock);
		Mockito.when(respuestaCuentasMock.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);
		Mockito.when(nuevoPagoBO.obtenerCuentas(Matchers.any(Cliente.class))).thenReturn(medioPagoSelectionMock);
		Respuesta<PagoMultipleListView> respuestaManager = pagoMultipleManager
				.solicitarPagoMultiple(pagoMultipleListView);
		Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaManager.getEstadoRespuesta()));
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ACCESO_PAGO_MULTIPLE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	@Test
	@Ignore
	public void solicitarPagoMultipleErrorTest() throws BusinessException {
		PagoMultipleListView pagoMultipleListView = new PagoMultipleListView();
		MedioPagoSelectionView medioPagoSelectionMock = Mockito.mock(MedioPagoSelectionView.class);

		Mockito.when(nuevoPagoBO.obtenerCuentas(Matchers.any(Cliente.class))).thenReturn(medioPagoSelectionMock);

		Respuesta<PagoMultipleListView> respuestaManager = pagoMultipleManager
				.solicitarPagoMultiple(pagoMultipleListView);

		Assert.assertNotNull(respuestaManager);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaManager.getEstadoRespuesta());
	}

	/**
	 * Ejecutar pago multiple. Caso 1: validate con WARNING.
	 */
	@Test
	public void ejecutarPagoMultipleValidationWarning() {
		PagoMultipleListView pagoMultipleListView = new PagoMultipleListView();
		List<PagoMultipleView> pagos = new ArrayList<PagoMultipleView>();
		pagos.add(pagoOK1);
		pagos.add(pagoOK2);
		pagos.add(pagoOK3);
		pagoMultipleListView.setPagos(pagos);
		pagoMultipleListView.setIdSesion(idSesion);

		Respuesta<PagoMultipleDTO> respBO = new Respuesta<PagoMultipleDTO>();
		//
		PagoMultipleDTO pagoMultipleDTO = new PagoMultipleDTO();
		List<PagoInEntity> pagosDTO = new ArrayList<PagoInEntity>();
		cargarDTOs();
		pagosDTO.add(pagoDTO1);
		pagosDTO.add(pagoDTO2);
		pagosDTO.add(pagoDTO3);
		pagoMultipleDTO.setPagos(pagosDTO);
		respBO.setRespuesta(pagoMultipleDTO);
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respBO.setRespuestaVacia(false);
		//

		respuestaWarnValidacion.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.eq(PagoMultipleView.class), Matchers.anyString(),
				Matchers.anyString())).thenReturn(respuestaError);
		Mockito.when(respuestaFactory.validate(Matchers.any(PagoMultipleView.class)))
				.thenReturn(respuestaWarnValidacion);
		Mockito.when(pagoMultipleBO.ejecutarPagoMultiple(Matchers.any(PagoMultipleListView.class),
				Matchers.any(Cliente.class))).thenReturn(respBO);
		Mockito.when(sesionParametros.getValidacionHash()).thenReturn("415CA21CB042634B3C76773D3ABD2149F4DD57A7391B69A1643571E33B6D3BEFF312EC71B2840C737734C8DD96CAC25D");

		Respuesta<PagoMultipleListView> respuestaMng = pagoMultipleManager.ejecutarPagoMultiple(pagoMultipleListView);

		Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuestaMng.getEstadoRespuesta()));
	}

	/**
	 * Ejecutar pago multiple. Caso 2: view con errores.
	 */
	@Test
	public void ejecutarPagoMultipleViewError() {
		PagoMultipleListView pagoMultipleListView = new PagoMultipleListView();
		List<PagoMultipleView> pagos = new ArrayList<PagoMultipleView>();
		pagos.add(pagoOK1);
		pagos.add(pagoOK2);
		pagos.add(pagoOK3);
		pagoMultipleListView.setPagos(pagos);
		pagoMultipleListView.setIdSesion(idSesion);
		//
		Respuesta<PagoMultipleDTO> respBO = new Respuesta<PagoMultipleDTO>();
		//
		PagoMultipleDTO pagoMultipleDTO = new PagoMultipleDTO();
		List<PagoInEntity> pagosDTO = new ArrayList();
		cargarDTOs();
		pagosDTO.add(pagoDTO1);
		pagosDTO.add(pagoDTO2);
		pagosDTO.add(pagoDTO3);
		pagoMultipleDTO.setPagos(pagosDTO);
		respBO.setRespuesta(pagoMultipleDTO);
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respBO.setRespuestaVacia(false);
		//
		Mensaje mensajeInformativo = new Mensaje();
		mensajeInformativo.setMensaje("Mensaje informativo");

		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.eq(PagoMultipleView.class), Matchers.anyString(),
				Matchers.anyString())).thenReturn(respuestaError);
		Mockito.when(respuestaFactory.validate(Matchers.any(PagoMultipleView.class)))
				.thenReturn(respuestaWarnValidacion);
		Mockito.when(pagoMultipleBO.ejecutarPagoMultiple(Matchers.any(PagoMultipleListView.class),
				Matchers.any(Cliente.class))).thenReturn(respBO);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensajeInformativo);
		Mockito.when(sesionParametros.getValidacionHash()).thenReturn("415CA21CB042634B3C76773D3ABD2149F4DD57A7391B69A1643571E33B6D3BEFF312EC71B2840C737734C8DD96CAC25D");
		Respuesta<PagoMultipleListView> respuestaMsg = pagoMultipleManager.ejecutarPagoMultiple(pagoMultipleListView);

		Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuestaMsg.getEstadoRespuesta()));
	}

	/**
	 * Cargar DT os.
	 * 
	 */
	private void cargarDTOs() {
		pagoDTO1.setReintentar(true);
		pagoDTO1.setMensaje("Mensaje 1");
		pagoDTO1.setFechaDePago(new Date());
		pagoDTO1.setNumeroControl("8931");
		pagoDTO1.setMonto("       44.15");
		pagoDTO1.setComprobantePorServicio("110432036968");
		pagoDTO1.setEstadoPago(0);
		pagoDTO1.setNumeroFactura("1234567890123456789");
		pagoDTO2.setReintentar(true);
		pagoDTO2.setMensaje("Mensaje 2");
		pagoDTO2.setFechaDePago(new Date());
		pagoDTO2.setNumeroControl("8931");
		pagoDTO2.setMonto("      144.15");
		pagoDTO2.setComprobantePorServicio("210432036968");
		pagoDTO2.setEstadoPago(0);
		pagoDTO2.setNumeroFactura("1234567890123456789");
		pagoDTO3.setReintentar(true);
		pagoDTO3.setMensaje("Mensaje 3");
		pagoDTO3.setFechaDePago(new Date());
		pagoDTO3.setNumeroControl("8931");
		pagoDTO3.setMonto("      944.15");
		pagoDTO3.setComprobantePorServicio("310432036968");
		pagoDTO3.setEstadoPago(0);
		pagoDTO3.setNumeroFactura("1234567890123456789");
	}

	/**
	 * Ejecutar pago multiple. Caso 3: view con Ok, y validacion OK. Desde la
	 * sesion en pagosPendientes:Respuesta<List<PagoPendiente>> esta CNSPESDEUD.
	 * @throws BusinessException 
	 */
	@Test
	public void ejecutarPagoMultipleViewOK() throws BusinessException {
		PagoMultipleListView pagoMultipleListView = new PagoMultipleListView();
		List<PagoMultipleView> pagos = new ArrayList<PagoMultipleView>();
		pagosPendientesOK();
		medioDePagos();
		pagos.add(pagoOK1);
	    pagos.add(pagoOK2);
	    pagos.add(pagoOK3);
		pagoMultipleListView.setPagos(pagos);

		PagoMultipleDTO pagoMultipleDTO = new PagoMultipleDTO();
		List<PagoInEntity> pagosDTO = new ArrayList();
		cargarDTOs();
		pagosDTO.add(pagoDTO1);
		pagosDTO.add(pagoDTO2);
		pagosDTO.add(pagoDTO3);
		pagoMultipleDTO.setPagos(pagosDTO);
		Respuesta<PagoMultipleDTO> respBO = new Respuesta<PagoMultipleDTO>();
		respBO.setRespuesta(pagoMultipleDTO);
		respBO.setEstadoRespuesta(EstadoRespuesta.OK);
		respBO.setRespuestaVacia(false);

		respuestaWarnValidacion.setEstadoRespuesta(EstadoRespuesta.OK);

		Respuesta<String> mnsLegal = new Respuesta<String>();
		mnsLegal.setRespuesta("mensaje de Legales.");
		mnsLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		Respuesta<MedioPago> medioPago = new Respuesta<MedioPago>();
		medioPago.setRespuesta(new MedioPago());
		medioPago.getRespuesta().setNombreFantasia("DOMESTICO ");
		medioPago.getRespuesta().setTipoPago(2);
		Mockito.when(mediosPagoBO.getByCodigo(Mockito.anyString())).thenReturn(medioPago);
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(mnsLegal);
		Cliente cliente = new Cliente();
		cliente.setDni("37887776");
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaPagosPendientesOK);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.eq(PagoMultipleView.class), Matchers.anyString(),
				Matchers.anyString())).thenReturn(respuestaError);
		Mockito.when(respuestaFactory.validate(Matchers.any(PagoMultipleView.class)))
				.thenReturn(respuestaWarnValidacion);
		Mockito.when(pagoMultipleBO.ejecutarPagoMultiple(Matchers.any(PagoMultipleListView.class),
				Matchers.any(Cliente.class))).thenReturn(respBO);
		Mockito.when(sesionParametros.getValidacionHash()).thenReturn("415CA21CB042634B3C76773D3ABD2149F4DD57A7391B69A1643571E33B6D3BEFF312EC71B2840C737734C8DD96CAC25D");
		
		Respuesta<PagoMultipleListView> respuestaMng = pagoMultipleManager.ejecutarPagoMultiple(pagoMultipleListView);

		Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaMng.getEstadoRespuesta()));
	}

	/**
	 * Ejecutar pago multiple. Caso 4: validate con ERR. GENERICO.
	 */
	@Test
	public void ejecutarPagoMultipleValidation() {
		PagoMultipleListView pagoMultipleListView = new PagoMultipleListView();
		List<PagoMultipleView> pagos = new ArrayList<PagoMultipleView>();
		pagosPendientesOK();
		medioDePagos();
		pagoOK3.setIdentificacionCliente(null);
		pagoOK3.setCuitCliente("500");
		pagos.add(pagoOK3);
		pagoMultipleListView.setPagos(pagos);
		pagoMultipleListView.setIdSesion(idSesion);

		Respuesta<PagoMultipleDTO> respBO = new Respuesta<PagoMultipleDTO>();
		//
		PagoMultipleDTO pagoMultipleDTO = new PagoMultipleDTO();
		List<PagoInEntity> pagosDTO = new ArrayList();
		cargarDTOs();
		pagosDTO.add(pagoDTO1);
		pagosDTO.add(pagoDTO2);
		pagosDTO.add(pagoDTO3);
		pagoMultipleDTO.setPagos(pagosDTO);
		respBO.setRespuesta(pagoMultipleDTO);
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respBO.setRespuestaVacia(false);
		//
		
		Respuesta<PagoMultipleListView> respuestaPagoMultipleListView = respuestaFactory
				.crearRespuestaOk(PagoMultipleListView.class);
		respuestaPagoMultipleListView.setEstadoRespuesta(respBO.getEstadoRespuesta());
		respuestaPagoMultipleListView.setRespuestaVacia(respBO.isRespuestaVacia());
		respuestaPagoMultipleListView.setRespuesta(pagoMultipleListView);

		respuestaWarnValidacion.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.eq(PagoMultipleView.class), Matchers.anyString(),
				Matchers.anyString())).thenReturn(respuestaError);
		Mockito.when(respuestaFactory.validate(Matchers.any(PagoMultipleView.class)))
				.thenReturn(respuestaWarnValidacion);
		Mockito.when(pagoMultipleBO.ejecutarPagoMultiple(Matchers.any(PagoMultipleListView.class),
				Matchers.any(Cliente.class))).thenReturn(respBO);
		Mockito.when(sesionParametros.getValidacionHash()).thenReturn("20FD27A8CD21DEE3A82D905E0EA7D2D4");

		Respuesta<PagoMultipleListView> respuestaMng = pagoMultipleManager.ejecutarPagoMultiple(pagoMultipleListView);

		Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuestaMng.getEstadoRespuesta()));
	}

	/**
	 * Medio de pagos.
	 */
	private void medioDePagos() {
		mediodePago.setTipoPago(2);
		mediodePago.setNombreFantasia("lala");
	}
}
