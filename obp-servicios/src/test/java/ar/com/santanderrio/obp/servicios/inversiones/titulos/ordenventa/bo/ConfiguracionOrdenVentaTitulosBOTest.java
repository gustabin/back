package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.SessionUsuarioData;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.extractos.ArrayOfCuentaTituloFirmada;
import ar.com.santanderrio.obp.generated.webservices.extractos.CuentaTituloFirmada;
import ar.com.santanderrio.obp.generated.webservices.extractos.CuentasFirmadasResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.extracto.dao.ExtractoDAO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.TitulosBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConfiguracionOrdenVentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.AperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.DatosAperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ListaAperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

@RunWith(MockitoJUnitRunner.class)
public class ConfiguracionOrdenVentaTitulosBOTest {

	@InjectMocks
	private ConfiguracionOrdenVentaTituloBOImpl configuracionOrdenVentaTituloBOImpl;

	@Mock
	private OrdenesTitulosDAO ordenesTitulosDAO;

	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private LegalBO legalBO;

	@Mock
	private TitulosBO titulosBO;

	@Mock
	private ExtractoDAO extractoDAO;

	@Mock
	private MyaBO myaBO;

	@Mock
	private FondoBO fondoBO;
	
	@Mock
	private ModuloPermisoBO moduloPermisoBO;

	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Before
	public void init() {
		Mensaje mensajeOK = new Mensaje();
		mensajeOK.setMensaje("msj ok");

		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaLegal.setRespuesta("legal ok");

		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeOK);
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VENTAS_SIN_VALIDAR_FIRMAS)).thenReturn(Mockito.mock(ModuloPermiso.class));
	}

	@Test
	public void obtenerConfiguracionOrdenVentaOk() throws DAOException, IllegalAccessException {

		// When
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);

		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("93823");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 29193/4");
		datosEntrada.setPlazo("24");
		datosEntrada.setEsBancaPrivada(false);

		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuenta.setTipoCuenta("02");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		listaCuentas.add(cuenta);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);
        
		CuentasFirmadasResponse cuentasFirmadas = new CuentasFirmadasResponse();
		CuentaTituloFirmada cuentaTit = new CuentaTituloFirmada();
		cuentaTit.setAtit(new JAXBElement<String>(QName.valueOf("Atit"), String.class, "1234567"));
		cuentaTit.setFirmada(new JAXBElement<String>(QName.valueOf("Firmada"), String.class, "S"));
		cuentaTit.setSuc(new JAXBElement<String>(QName.valueOf("Suc"), String.class, "321"));
		ArrayOfCuentaTituloFirmada arrayCuentas = new ArrayOfCuentaTituloFirmada();
		arrayCuentas.getCuentaTituloFirmada().add(cuentaTit);
		cuentasFirmadas.setListaCuentas(new JAXBElement<ArrayOfCuentaTituloFirmada>(QName.valueOf("ListaCuentas"),
				ArrayOfCuentaTituloFirmada.class, arrayCuentas));

		MyaDTOOut mya = new MyaDTOOut();
		mya.setEmail("mail@prueba.com");

		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(cliente.getSegmento()).thenReturn(segmento);
		when(cliente.getCuentas()).thenReturn(listaCuentas);
		when(ordenesTitulosDAO.consultaAperturaEspecie(Matchers.any(ConsultaAperturaEspecieRequestEntity.class)))
				.thenReturn(armarConsultaAperturaEspecieResponse());
		when(extractoDAO.firmasCuentasPorNup(Matchers.anyString(), Matchers.anyString())).thenReturn(cuentasFirmadas);
		when(myaBO.consultaWsEstadoCliente(Matchers.any(Cliente.class), Matchers.any(MyaDTOIn.class))).thenReturn(mya);

		when(ordenesTitulosDAO.consultaSuscripcionSaldoPDC(Matchers.any(ConsultaSuscripcionSaldoPDCRequest.class)))
		.thenThrow(DAOException.class);
		
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VENTAS_SIN_VALIDAR_FIRMAS).tienePermisoDeVisibilidad()).thenReturn(true);
		
		// Then
		Respuesta<ConfiguracionOrdenVentaDTO> respuesta = configuracionOrdenVentaTituloBOImpl
				.obtenerConfiguracionOrdenVenta(cliente, datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@Test
	public void obtenerConfiguracionOrdenVentaBancaPrivadaOk() throws DAOException, IllegalAccessException {

		// When
		Cliente cliente = new Cliente();
		cliente.setUsuarioRacf("3983");
		List<CuentaBancaPrivada> listaCuentasBancaPrivada = new ArrayList<CuentaBancaPrivada>();
		CuentaBancaPrivada cuentaBancaPrivada = new CuentaBancaPrivada();
		Cuenta cuentaTitulo = new Cuenta();
		cuentaTitulo.setNroCuentaProducto("0000012345678");
		Cuenta cuentaOperativa = new Cuenta();
		cuentaOperativa.setNroCuentaProducto("000001291934");
		cuentaBancaPrivada.setCuentaOperativa(cuentaOperativa);
		cuentaBancaPrivada.setCuentaTitulo(cuentaTitulo);
		listaCuentasBancaPrivada.add(cuentaBancaPrivada);
		cliente.setCuentasBancaPrivada(listaCuentasBancaPrivada);
		
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);

		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("93823");
		datosEntrada.setNumeroCuentaTitulo("Cuenta Banca Privada 250-129193/4");
		datosEntrada.setPlazo("24");
		datosEntrada.setEsBancaPrivada(true);

		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuenta.setTipoCuenta("09");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		listaCuentas.add(cuenta);
		cliente.setCuentas(listaCuentas);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);
		cliente.setSegmento(segmento);
		
		CuentasFirmadasResponse cuentasFirmadas = new CuentasFirmadasResponse();
		CuentaTituloFirmada cuentaTit = new CuentaTituloFirmada();
		cuentaTit.setAtit(new JAXBElement<String>(QName.valueOf("Atit"), String.class, "1234567"));
		cuentaTit.setFirmada(new JAXBElement<String>(QName.valueOf("Firmada"), String.class, "S"));
		cuentaTit.setSuc(new JAXBElement<String>(QName.valueOf("Suc"), String.class, "321"));
		ArrayOfCuentaTituloFirmada arrayCuentas = new ArrayOfCuentaTituloFirmada();
		arrayCuentas.getCuentaTituloFirmada().add(cuentaTit);
		cuentasFirmadas.setListaCuentas(new JAXBElement<ArrayOfCuentaTituloFirmada>(QName.valueOf("ListaCuentas"),
				ArrayOfCuentaTituloFirmada.class, arrayCuentas));

		MyaDTOOut mya = new MyaDTOOut();
		mya.setEmail("mail@prueba.com");

		Respuesta<CuentasAdhesionDebitoView> respuestaSaldos = new Respuesta<CuentasAdhesionDebitoView>();
		respuestaSaldos.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaSaldos.setRespuesta(new CuentasAdhesionDebitoView());

		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(ordenesTitulosDAO.consultaAperturaEspecie(Matchers.any(ConsultaAperturaEspecieRequestEntity.class)))
				.thenReturn(armarConsultaAperturaEspecieResponse());
		when(extractoDAO.firmasCuentasPorNup(Matchers.anyString(), Matchers.anyString())).thenReturn(cuentasFirmadas);
		when(myaBO.consultaWsEstadoCliente(Matchers.any(Cliente.class), Matchers.any(MyaDTOIn.class))).thenReturn(mya);
		when(fondoBO.obtenerSaldosCuentaOperativa(Matchers.anyString(), Matchers.any(Cliente.class),
				Matchers.anyBoolean())).thenReturn(respuestaSaldos);
		
		
		when(ordenesTitulosDAO.consultaSuscripcionSaldoPDC(Matchers.any(ConsultaSuscripcionSaldoPDCRequest.class)))
		.thenThrow(DAOException.class);

		// Then
		Respuesta<ConfiguracionOrdenVentaDTO> respuesta = configuracionOrdenVentaTituloBOImpl
				.obtenerConfiguracionOrdenVenta(cliente, datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@Test
	public void obtenerConfiguracionOrdenVentaErrorNoHayPlazosCorrectos() throws DAOException, IllegalAccessException {

		// When
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);

		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("93823");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 29193/4");
		datosEntrada.setPlazo("24");
		datosEntrada.setEsBancaPrivada(false);

		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuenta.setTipoCuenta("09");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		listaCuentas.add(cuenta);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);

		CuentasFirmadasResponse cuentasFirmadas = new CuentasFirmadasResponse();
		CuentaTituloFirmada cuentaTit = new CuentaTituloFirmada();
		cuentaTit.setAtit(new JAXBElement<String>(QName.valueOf("Atit"), String.class, "1234567"));
		cuentaTit.setFirmada(new JAXBElement<String>(QName.valueOf("Firmada"), String.class, "S"));
		cuentaTit.setSuc(new JAXBElement<String>(QName.valueOf("Suc"), String.class, "321"));
		ArrayOfCuentaTituloFirmada arrayCuentas = new ArrayOfCuentaTituloFirmada();
		arrayCuentas.getCuentaTituloFirmada().add(cuentaTit);
		cuentasFirmadas.setListaCuentas(new JAXBElement<ArrayOfCuentaTituloFirmada>(QName.valueOf("ListaCuentas"),
				ArrayOfCuentaTituloFirmada.class, arrayCuentas));

		ConsultaAperturaEspecieResponse response = armarConsultaAperturaEspecieResponse();
		response.getDatos().getListaAperturaEspecie().getAperturasEspecie().get(0).setCodHabilitacion("ERROR");

		MyaDTOOut mya = new MyaDTOOut();
		mya.setEmail("mail@prueba.com");

		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(cliente.getSegmento()).thenReturn(segmento);
		when(cliente.getCuentas()).thenReturn(listaCuentas);
		when(ordenesTitulosDAO.consultaAperturaEspecie(Matchers.any(ConsultaAperturaEspecieRequestEntity.class)))
				.thenReturn(response);
		when(extractoDAO.firmasCuentasPorNup(Matchers.anyString(), Matchers.anyString())).thenReturn(cuentasFirmadas);
		when(myaBO.consultaWsEstadoCliente(Matchers.any(Cliente.class), Matchers.any(MyaDTOIn.class))).thenReturn(mya);

		// Then
		Respuesta<ConfiguracionOrdenVentaDTO> respuesta = configuracionOrdenVentaTituloBOImpl
				.obtenerConfiguracionOrdenVenta(cliente, datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfiguracionOrdenVentaErrorDAOException() throws DAOException, IllegalAccessException {

		// When
		Cliente cliente = mock(Cliente.class);
		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("93823");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 29193/4");
		datosEntrada.setPlazo("24");
		datosEntrada.setEsBancaPrivada(false);

		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuenta.setTipoCuenta("09");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		listaCuentas.add(cuenta);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);

		Mensaje mensajeError = new Mensaje();
		mensajeError.setMensaje("ERROR DE SERVICIO WEB");

		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);

		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);

		when(ordenesTitulosDAO.consultaAperturaEspecie(Matchers.any(ConsultaAperturaEspecieRequestEntity.class)))
				.thenThrow(DAOException.class);
		when(cliente.getSegmento()).thenReturn(segmento);
		when(cliente.getCuentas()).thenReturn(listaCuentas);
		when(mensajeBO.obtenerMensajePorCodigo("10422")).thenReturn(mensajeError);

		// Then
		Respuesta<ConfiguracionOrdenVentaDTO> respuesta = configuracionOrdenVentaTituloBOImpl
				.obtenerConfiguracionOrdenVenta(cliente, datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}

	@Test
	public void obtenerConfiguracionOrdenVentaErrorDeServicio() throws DAOException {

		// When
		Cliente cliente = mock(Cliente.class);
		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("93823");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 29193/4");
		datosEntrada.setPlazo("24");
		datosEntrada.setEsBancaPrivada(false);

		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuenta.setTipoCuenta("09");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		listaCuentas.add(cuenta);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);

		Mensaje mensajeError = new Mensaje();
		mensajeError.setMensaje("ERROR DE SERVICIO WEB");

		ConsultaAperturaEspecieResponse consultaAperturaEspecieResponse = armarConsultaAperturaEspecieResponse();
		consultaAperturaEspecieResponse.setCodigo(1);

		when(ordenesTitulosDAO.consultaAperturaEspecie(Matchers.any(ConsultaAperturaEspecieRequestEntity.class)))
				.thenReturn(consultaAperturaEspecieResponse);
		when(cliente.getSegmento()).thenReturn(segmento);
		when(cliente.getCuentas()).thenReturn(listaCuentas);
		when(mensajeBO.obtenerMensajePorCodigo("10422")).thenReturn(mensajeError);

		// Then
		Respuesta<ConfiguracionOrdenVentaDTO> respuesta = configuracionOrdenVentaTituloBOImpl
				.obtenerConfiguracionOrdenVenta(cliente, datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}

	@Test
	public void obtenerConfiguracionOrdenVentaErrorNoHayCuentas() throws DAOException {

		// When
		Cliente cliente = mock(Cliente.class);
		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("93823");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 29193/4");
		datosEntrada.setPlazo("24");
		datosEntrada.setEsBancaPrivada(false);

		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		listaCuentas.add(cuenta);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);

		Mensaje mensajeError = new Mensaje();
		mensajeError.setMensaje("NO HAY CUENTAS DESTINO");

		ConsultaAperturaEspecieResponse consultaAperturaEspecieResponse = armarConsultaAperturaEspecieResponse();
		consultaAperturaEspecieResponse.setCodigo(0);

		when(ordenesTitulosDAO.consultaAperturaEspecie(Matchers.any(ConsultaAperturaEspecieRequestEntity.class)))
				.thenReturn(consultaAperturaEspecieResponse);
		when(cliente.getSegmento()).thenReturn(segmento);
		when(cliente.getCuentas()).thenReturn(listaCuentas);
		when(mensajeBO.obtenerMensajePorCodigo("10527")).thenReturn(mensajeError);

		when(ordenesTitulosDAO.consultaSuscripcionSaldoPDC(Matchers.any(ConsultaSuscripcionSaldoPDCRequest.class)))
		.thenThrow(DAOException.class);
		
		// Then
		Respuesta<ConfiguracionOrdenVentaDTO> respuesta = configuracionOrdenVentaTituloBOImpl
				.obtenerConfiguracionOrdenVenta(cliente, datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}

	private ConsultaAperturaEspecieResponse armarConsultaAperturaEspecieResponse() {

		ConsultaAperturaEspecieResponse consultaAperturaEspecieResponse = new ConsultaAperturaEspecieResponse();
		DatosAperturaEspecieResponse datos = new DatosAperturaEspecieResponse();

		ListaAperturaEspecieResponse listaAperturaEspecie = new ListaAperturaEspecieResponse();
		listaAperturaEspecie.setDescripcionEspecie("Tenaris");
		listaAperturaEspecie.setCodigoAmigable("TS");
		listaAperturaEspecie.setCodigoAmigable("92784");
		listaAperturaEspecie.setTipoEspecie("SHS");
		listaAperturaEspecie.setInstrumento("Acciones");
		listaAperturaEspecie.setEmisionMonedaEspecie("ARS");
		listaAperturaEspecie.setLeyendaLegal("Condiciones...");

		List<AperturaEspecieResponse> aperturasEspecie = new ArrayList<AperturaEspecieResponse>();
		AperturaEspecieResponse aperturaEspecieResponse = new AperturaEspecieResponse();
		aperturaEspecieResponse.setPlazo(24);
		aperturaEspecieResponse.setFechaLiquidacion("2018/03/20");
		aperturaEspecieResponse.setMonedaNegociacion("ARS");
		aperturaEspecieResponse.setCodHabilitacion("OK");
		aperturaEspecieResponse.setCotizacionEspecie(new BigDecimal("100.25"));
		aperturaEspecieResponse.setFechaCotizacionEspecie("0001-01-01T00:00:00-03:00");
		aperturaEspecieResponse.setHoraCotizacionEspecie("12:05:35");
		aperturaEspecieResponse.setCantidadMinimoIncremento(new BigDecimal("5"));
		aperturaEspecieResponse.setCantidadMinimaNegociable(new BigDecimal("10"));
		aperturaEspecieResponse.setCantidadMaximaNegociable(new BigDecimal("500"));
		aperturaEspecieResponse.setImporteMinimoIncremento(new BigDecimal("0"));
		aperturaEspecieResponse.setImporteMinimoInvertir(new BigDecimal("0"));
		aperturaEspecieResponse.setImporteMaximoInvertir(new BigDecimal("0"));
		aperturaEspecieResponse.setRequierePrecioLimite("N");
		aperturaEspecieResponse.setPrecioLimiteMinimo(new BigDecimal("0"));
		aperturaEspecieResponse.setPrecioLimiteMaximo(new BigDecimal("0"));
		aperturaEspecieResponse.setPermiteMonto("N");
		aperturaEspecieResponse.setTenenciaNominalNegociable(new BigDecimal("10"));
		aperturaEspecieResponse.setTenenciaNominal(new BigDecimal("200"));

		aperturasEspecie.add(aperturaEspecieResponse);
		listaAperturaEspecie.setAperturasEspecie(aperturasEspecie);
		datos.setListaAperturaEspecie(listaAperturaEspecie);
		consultaAperturaEspecieResponse.setDatos(datos);

		return consultaAperturaEspecieResponse;

	}

}
