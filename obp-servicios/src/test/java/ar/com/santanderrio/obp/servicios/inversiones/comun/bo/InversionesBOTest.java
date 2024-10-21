package ar.com.santanderrio.obp.servicios.inversiones.comun.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.SessionUsuarioData;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoOperacionInversionesEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.DetalleCustodiaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TenenciaConsolidadaPorProductoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TotalesTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.DatosPerfilInversorResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ConsultaPerfilInversorViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorCuentaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaProductosPorMonedaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaProductosPorMonedaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.dao.CuentaTituloDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.TenenciaValuadaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.InicioFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TipoBancaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CarteraTenenciaValuadaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaProductoOnlineEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosRespuestaCartera;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosRespuestaCuentaProductoOnline;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleCustodiaOnlineEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleCustodiaOnlineEntityMock;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ProductoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ResultadoCuentaProductoOL;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ResultadoTenenciaValuadaCuentaProductoOnline;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TenenciaValuadaCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TenenciaValuadaCarteraResultadosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel.TenenciaPlazoFijoExcel;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.TitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

@RunWith(MockitoJUnitRunner.class)
public class InversionesBOTest {

	private static final String AGRESIVO = "AGRESIVO";

	private static final String MODERADO = "MODERADO";

	private static final String NOPERFILADO = "NO PERFILADO";

	private static final String CONSERVADOR = "CONSERVADOR";

	@InjectMocks
	private InversionesBOImpl inversionesBO;

	@Mock
	private InversionWSHelper inversionWSHelper;

	@Mock
	private InversionDAO inversionDAO;

	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private CuentaBO cuentaBO;

	@Mock
	private RespuestaFactory respuestaFactory;

	@Mock
	private CuentaTituloDAO cuentaTituloDAO;

	@Mock
	private TitulosDAO titulosDAO;

	@Mock
	private EstadisticaBO estadisticaBO;

	@Mock
	private SesionParametros sesionParametros;

	@Mock
	private TenenciaValuadaDAO tenenciaValuadaDAO;

	@Mock
	private ReporteDAO reporteDAO;

	@Spy
	private SimpleDateFormat formatter;
    
	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private FundsApi fundsApi;
	
	@InjectMocks
	BaseManager baseManager = new BaseManager();

	@SuppressWarnings("unchecked")
	@Test
	public void consultarPerfilInversor() throws DAOException {

		PerfilInversorResponse perfilInversorOutEntity = new PerfilInversorResponse();
		DatosPerfilInversorResponse datos = new DatosPerfilInversorResponse();
		datos.setDescripcion(NOPERFILADO);
		datos.setIdPerfil(0);
		perfilInversorOutEntity.setDatos(datos);
		Mockito.when(inversionDAO.consultaPerfilInversor(Matchers.any(PerfilInversorRequestEntity.class)))
				.thenReturn(perfilInversorOutEntity);

		Mensaje mensajeMock = new Mensaje();
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<ConsultaPerfilInversorViewResponse> respuestaOk = new Respuesta<ConsultaPerfilInversorViewResponse>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(ConsultaPerfilInversorViewResponse.class))).thenReturn(respuestaOk);

		Cliente cliente = new Cliente();
		cliente.setNup("00062748");
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		ApplicationContext context = Mockito.mock(ApplicationContext.class);
	    SessionUsuarioData sesionUsuarioData = new SessionUsuarioData();
	    sesionUsuarioData.setIatxNroReqSessionName("5");
	    sesionUsuarioData.setIatxSessionUserName("371769");
	    ContextHolder.setContext(context);
	    Mockito.when(context.getBean(SessionUsuarioData.class)).thenReturn(sesionUsuarioData);

		Respuesta<PerfilInversorResponse> respuesta = inversionesBO.consultarPerfilInversor(false);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultarPerfilInversorModerado() throws DAOException {

		PerfilInversorResponse perfilInversorOutEntity = new PerfilInversorResponse();
		DatosPerfilInversorResponse datos = new DatosPerfilInversorResponse();
		datos.setDescripcion(MODERADO);
		datos.setIdPerfil(2);
		perfilInversorOutEntity.setDatos(datos);
		Mockito.when(inversionDAO.consultaPerfilInversor(Matchers.any(PerfilInversorRequestEntity.class)))
				.thenReturn(perfilInversorOutEntity);

		Mensaje mensajeMock = new Mensaje();
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<ConsultaPerfilInversorViewResponse> respuestaOk = new Respuesta<ConsultaPerfilInversorViewResponse>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(ConsultaPerfilInversorViewResponse.class))).thenReturn(respuestaOk);

		Cliente cliente = new Cliente();
		cliente.setNup("02591302");
		when(sesionCliente.getCliente()).thenReturn(cliente);

		ApplicationContext context = Mockito.mock(ApplicationContext.class);
	    SessionUsuarioData sesionUsuarioData = new SessionUsuarioData();
	    sesionUsuarioData.setIatxNroReqSessionName("5");
	    sesionUsuarioData.setIatxSessionUserName("371769");
	    ContextHolder.setContext(context);
	    Mockito.when(context.getBean(SessionUsuarioData.class)).thenReturn(sesionUsuarioData);
		    
		Respuesta<PerfilInversorResponse> respuesta = inversionesBO.consultarPerfilInversor(false);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultarPerfilInversorAgresivo() throws DAOException {
		
		PerfilInversorResponse perfilInversorOutEntity = new PerfilInversorResponse();
		DatosPerfilInversorResponse datos = new DatosPerfilInversorResponse();
		datos.setDescripcion(AGRESIVO);
		datos.setIdPerfil(3);
		perfilInversorOutEntity.setDatos(datos);

		Mockito.when(inversionDAO.consultaPerfilInversor(Matchers.any(PerfilInversorRequestEntity.class)))
				.thenReturn(perfilInversorOutEntity);

		Mensaje mensajeMock = new Mensaje();
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<ConsultaPerfilInversorViewResponse> respuestaOk = new Respuesta<ConsultaPerfilInversorViewResponse>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(ConsultaPerfilInversorViewResponse.class))).thenReturn(respuestaOk);

		Cliente cliente = new Cliente();
		cliente.setNup("50051154");
		when(sesionCliente.getCliente()).thenReturn(cliente);
		Respuesta<PerfilInversorResponse> respuesta = inversionesBO.consultarPerfilInversor(false);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultarPerfilInversorConservador() throws DAOException {
		
		PerfilInversorResponse perfilInversorOutEntity = new PerfilInversorResponse();
		DatosPerfilInversorResponse datos = new DatosPerfilInversorResponse();
		datos.setDescripcion(CONSERVADOR);
		datos.setIdPerfil(1);
		perfilInversorOutEntity.setDatos(datos);
		Mockito.when(inversionDAO.consultaPerfilInversor(Matchers.any(PerfilInversorRequestEntity.class)))
				.thenReturn(perfilInversorOutEntity);

		Mensaje mensajeMock = new Mensaje();
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<ConsultaPerfilInversorViewResponse> respuestaOk = new Respuesta<ConsultaPerfilInversorViewResponse>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(ConsultaPerfilInversorViewResponse.class))).thenReturn(respuestaOk);

		Cliente cliente = new Cliente();
		cliente.setNup("00062748");
		when(sesionCliente.getCliente()).thenReturn(cliente);
	
        ApplicationContext context = Mockito.mock(ApplicationContext.class);
    	SessionUsuarioData sesionUsuarioData = new SessionUsuarioData();
    	sesionUsuarioData.setIatxNroReqSessionName("5");
    	sesionUsuarioData.setIatxSessionUserName("371769");
    	ContextHolder.setContext(context);
    	Mockito.when(context.getBean(SessionUsuarioData.class)).thenReturn(sesionUsuarioData);

		Respuesta<PerfilInversorResponse> respuesta = inversionesBO.consultarPerfilInversor(false);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void consultarPerfilInversorDAOExcepcion() throws DAOException {

		Mockito.when(inversionDAO.consultaPerfilInversor(Matchers.any(PerfilInversorRequestEntity.class))).thenThrow(new DAOException());

		Respuesta respuestaErrorPerfilInversor = new Respuesta();
		respuestaErrorPerfilInversor.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaErrorPerfilInversor);
		
		Cliente cliente = new Cliente();
		cliente.setNup("1234567");
		when(sesionCliente.getCliente()).thenReturn(cliente);

		ApplicationContext context = Mockito.mock(ApplicationContext.class);
	    SessionUsuarioData sesionUsuarioData = new SessionUsuarioData();
	    sesionUsuarioData.setIatxNroReqSessionName("5");
	    sesionUsuarioData.setIatxSessionUserName("371769");
	    ContextHolder.setContext(context);
	    Mockito.when(context.getBean(SessionUsuarioData.class)).thenReturn(sesionUsuarioData);

		Respuesta<PerfilInversorResponse> respuesta = inversionesBO.consultarPerfilInversor(false);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void inicioInversionesTestOK() throws DAOException, BusinessException {

		Estadistica estadisticaRetorno = new Estadistica();
		Mockito.when(estadisticaBO.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
				Matchers.any(Cliente.class))).thenReturn(estadisticaRetorno);

		Mensaje mensajeMock = new Mensaje();
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);
		Cuenta cuentaRetail = new Cuenta();
		cuentasRetail.add(cuentaRetail);
		cuentaRetail.setCodigoPaquete("1040");
		cuentaRetail.setCodigoAplicacion("ATIT");
		cuentaRetail.setNroCuentaProducto("6543");
		cuentaRetail.setNroSucursal("250");
		
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		cuentaRetail.setIntervinientes(intervinientes);
		List<Cuenta> cuentasPrivadas = new ArrayList<Cuenta>();
		cliente.setCuentasPrivadas(cuentasPrivadas);
		Cuenta cuentaPrivada = new Cuenta();
		cuentasPrivadas.add(cuentaPrivada);
		cuentaPrivada.setCodigoPaquete("1040");
		cuentaPrivada.setCodigoAplicacion("ATIT");
		cuentaPrivada.setNroCuentaProducto("6543");
		cuentaPrivada.setNroSucursal("250");
		cuentaPrivada.setProductoAltair("9");
		cuentaPrivada.setIntervinientes(intervinientes);

		Respuesta<InicioFondoDTO> respuestaOk = new Respuesta<InicioFondoDTO>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(InicioFondoDTO.class)))
				.thenReturn(respuestaOk);

		CuentaTituloOutEntity outEntityLoadAtits = new CuentaTituloOutEntity();
		List<CuentaBP> cuentasTit = new ArrayList<CuentaBP>();
		outEntityLoadAtits.relacionesOperativaTitulo(cuentasTit);
		CuentaBP cuentaTit = new CuentaBP("1234567", "7654321");
		cuentasTit.add(cuentaTit);
		ObtenerCuentasTitulosResponse cuentaTitulosResponse = new ObtenerCuentasTitulosResponse();
		List<DatosObtenerCuentasTitulosResponse> datos = new ArrayList<DatosObtenerCuentasTitulosResponse>();
		cuentaTitulosResponse.setDatos(datos);
		Mockito.when(titulosDAO.obtenerCuentasTitulos(Matchers.any(ObtenerCuentasTitulos.class)))
				.thenReturn(cuentaTitulosResponse);
		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
				.thenReturn(outEntityLoadAtits);
		Mockito.when(cuentaBO.hasCuentasMonetariasActivas(Matchers.any(Cliente.class))).thenReturn(false);
		RegistroSesion registroSesion = new RegistroSesion();
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		Mockito.when(inversionWSHelper.getDatosFirmados(Matchers.anyString())).thenReturn("BEL");

		String tipoDeOperacion = TipoOperacionInversionesEnum.TITULO_VALORES.getCodigoProducto();
		Respuesta<InicioFondoDTO> respuesta = inversionesBO.inicioInversiones(cliente, tipoDeOperacion);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void inicioInversionesTestSinCuentasPrivadasOK() throws DAOException, BusinessException {

		Estadistica estadisticaRetorno = new Estadistica();
		Mockito.when(estadisticaBO.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
				Matchers.any(Cliente.class))).thenReturn(estadisticaRetorno);

		Mensaje mensajeMock = new Mensaje();
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);
		Cuenta cuentaRetail = new Cuenta();
		cuentasRetail.add(cuentaRetail);
		cuentaRetail.setCodigoPaquete("1040");
		cuentaRetail.setCodigoAplicacion("ATIT");
		cuentaRetail.setNroCuentaProducto("6543");
		cuentaRetail.setNroSucursal("250");
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		cuentaRetail.setIntervinientes(intervinientes);
		List<Cuenta> cuentasPrivadas = new ArrayList<Cuenta>();
		cliente.setCuentasPrivadas(cuentasPrivadas);
		Respuesta<InicioFondoDTO> respuestaOk = new Respuesta<InicioFondoDTO>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		ObtenerCuentasTitulosResponse cuentaTitulosResponse = new ObtenerCuentasTitulosResponse();
		List<DatosObtenerCuentasTitulosResponse> datos = new ArrayList<DatosObtenerCuentasTitulosResponse>();
		cuentaTitulosResponse.setDatos(datos);
		CuentaTituloOutEntity outEntityLoadAtits = new CuentaTituloOutEntity();
		List<CuentaBP> cuentasTit = new ArrayList<CuentaBP>();
		outEntityLoadAtits.relacionesOperativaTitulo(cuentasTit);
		CuentaBP cuentaTit = new CuentaBP("1234567", "7654321");
		cuentasTit.add(cuentaTit);
		String tipoDeOperacion = TipoOperacionInversionesEnum.TITULO_VALORES.getCodigoProducto();
		RegistroSesion registroSesion = new RegistroSesion();

		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		Mockito.when(titulosDAO.obtenerCuentasTitulos(Matchers.any(ObtenerCuentasTitulos.class)))
				.thenReturn(cuentaTitulosResponse);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(InicioFondoDTO.class)))
				.thenReturn(respuestaOk);
		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
				.thenReturn(outEntityLoadAtits);

		Respuesta<InicioFondoDTO> respuesta = inversionesBO.inicioInversiones(cliente, tipoDeOperacion);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void inicioInversionesTestSinCuentasOK() throws DAOException, BusinessException {

		Estadistica estadisticaRetorno = new Estadistica();
		Mockito.when(estadisticaBO.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
				Matchers.any(Cliente.class))).thenReturn(estadisticaRetorno);

		Mensaje mensajeMock = new Mensaje();
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);
		List<Cuenta> cuentasPrivadas = new ArrayList<Cuenta>();
		cliente.setCuentasPrivadas(cuentasPrivadas);

		Respuesta<InicioFondoDTO> respuestaOk = new Respuesta<InicioFondoDTO>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(InicioFondoDTO.class)))
				.thenReturn(respuestaOk);

		CuentaTituloOutEntity outEntityLoadAtits = new CuentaTituloOutEntity();
		List<CuentaBP> cuentasTit = new ArrayList<CuentaBP>();
		outEntityLoadAtits.relacionesOperativaTitulo(cuentasTit);
		CuentaBP cuentaTit = new CuentaBP("1234567", "7654321");
		cuentasTit.add(cuentaTit);
		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
				.thenReturn(outEntityLoadAtits);

		RegistroSesion registroSesion = new RegistroSesion();
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

		String tipoDeOperacion = TipoOperacionInversionesEnum.TITULO_VALORES.getCodigoProducto();
		Respuesta<InicioFondoDTO> respuesta = inversionesBO.inicioInversiones(cliente, tipoDeOperacion);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTotalesTenenciaTest() throws DAOException {

		CarteraTenenciaValuadaEntity tenenciaReturn = new CarteraTenenciaValuadaEntity();
		DatosRespuestaCartera datosCartera = new DatosRespuestaCartera();
		tenenciaReturn.setDatos(datosCartera);
		TenenciaValuadaCarteraResultadosEntity tenencia = new TenenciaValuadaCarteraResultadosEntity();
		datosCartera.setTenenciaTotalCarteraBP(tenencia);
		datosCartera.setTenenciaTotalCarteraRTL(tenencia);
		tenencia.setDolares("1500");
		tenencia.setPesos("1500");
		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaCarteraTotalOnline(Matchers.any(TenenciaValuadaCarteraRequestEntity.class)))
				.thenReturn(tenenciaReturn);

		Respuesta<TotalesTenenciaDTO> respuestaOk = new Respuesta<TotalesTenenciaDTO>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(
				respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(TotalesTenenciaDTO.class)))
				.thenReturn(respuestaOk);

		Cliente cliente = new Cliente();
		cliente.setNup("00128235");
		List<Cuenta> listaCuentaRTL = new ArrayList<Cuenta>();
		List<CuentaBancaPrivada> listCuentaBP = new ArrayList<CuentaBancaPrivada>();
		Cuenta cuenta1 = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		cuenta1.setNroCuentaProducto("12345");
		CuentaBancaPrivada cuenta3 = new CuentaBancaPrivada();
		CuentaBancaPrivada cuenta4 = new CuentaBancaPrivada();
		cuenta3.setCuentaOperativa(cuenta1);
		cuenta4.setCuentaOperativa(cuenta1);
		listaCuentaRTL.add(cuenta1);
		listaCuentaRTL.add(cuenta2);
		listCuentaBP.add(cuenta3);
		listCuentaBP.add(cuenta4);
		cliente.setCuentasRetail(listaCuentaRTL);
		cliente.setCuentasBancaPrivada(listCuentaBP);
		String tipoDeOperacion = "FCI";
		inversionesBO.obtenerTotalesTenencia(cliente, tipoDeOperacion, TipoBancaEnum.BANCA_PERSONAL);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void obtenerTotalesTenenciaBusinessExeptionTest() throws DAOException {

		CarteraTenenciaValuadaEntity tenenciaReturn = new CarteraTenenciaValuadaEntity();
		tenenciaReturn.setDatos(null);
		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaCarteraTotalOnline(Matchers.any(TenenciaValuadaCarteraRequestEntity.class)))
				.thenReturn(tenenciaReturn);

		Respuesta respuestaErrorTotalesTenencia = new Respuesta();
		respuestaErrorTotalesTenencia.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaErrorTotalesTenencia);

		Cliente cliente = new Cliente();
		cliente.setNup("543224");
		String tipoDeOperacion = "FCI";
		inversionesBO.obtenerTotalesTenencia(cliente, tipoDeOperacion, TipoBancaEnum.BANCA_PERSONAL);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void obtenerTotalesTenenciaDAOExeptionTest() throws DAOException {

		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaCarteraTotalOnline(Matchers.any(TenenciaValuadaCarteraRequestEntity.class)))
				.thenThrow(new DAOException());

		Respuesta respuestaErrorTotalesTenencia = new Respuesta();
		respuestaErrorTotalesTenencia.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaErrorTotalesTenencia);

		Cliente cliente = new Cliente();
		cliente.setNup("543224");
		String tipoDeOperacion = "FCI";
		inversionesBO.obtenerTotalesTenencia(cliente, tipoDeOperacion, TipoBancaEnum.BANCA_PERSONAL);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciaConsolidadaPorProductoOkBP() throws DAOException {
		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);
		Cuenta cuentaRetail = new Cuenta();
		cuentasRetail.add(cuentaRetail);
		cuentaRetail.setCodigoPaquete("1040");
		cuentaRetail.setCodigoAplicacion("ATIT");
		cuentaRetail.setNroCuentaProducto("6543");
		cuentaRetail.setNroSucursal("250");
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		cuentaRetail.setIntervinientes(intervinientes);
		List<Cuenta> cuentasPrivadas = new ArrayList<Cuenta>();
		cliente.setCuentasPrivadas(cuentasPrivadas);

		CuentaProductoOnlineEntity tenenciaConsolidada = new CuentaProductoOnlineEntity();
		DatosRespuestaCuentaProductoOnline datosCuentaProducto = new DatosRespuestaCuentaProductoOnline();
		List<ResultadoCuentaProductoOL> resultadotenenciaList1 = new ArrayList<ResultadoCuentaProductoOL>();
		ResultadoCuentaProductoOL resultadotenencia = new ResultadoCuentaProductoOL();
		List<ProductoEntity> list = new ArrayList<ProductoEntity>();
		ProductoEntity prod = new ProductoEntity();

		prod.setCodigoProducto("FCI");
		prod.setResultado("0");
		list.add(prod);

		resultadotenencia.setAmortizaciones("0001");
		resultadotenencia.setAmortizacionesReexp("0111");
		resultadotenencia.setCodProducto("FCI");
		resultadotenencia.setDividendos("111111");
		resultadotenencia.setDividendosReexp("11111111");
		resultadotenencia.setMoneda("ARS");
		resultadotenencia.setNumeroCuenta("numeroCuenta");
		resultadotenencia.setRentas("111111");
		resultadotenencia.setRentasReexp("11111");
		resultadotenencia.setResultadoBruto("1111");
		resultadotenencia.setResultadoBrutoCorregido("1111");
		resultadotenencia.setResultadoBrutoCorregidoReexp("111111");
		resultadotenencia.setResultadoBrutoReexp("11111");
		resultadotenencia.setSucursal("sucursal");
		resultadotenencia.setTenenciaValuada("111111");
		resultadotenencia.setTenenciaValuadaCompra("111111");
		resultadotenencia.setTenenciaValuadaCompraReexp("111111");
		resultadotenencia.setTenenciaValuadaReexp("1111111");
		resultadotenenciaList1.add(resultadotenencia);
		List<ResultadoCuentaProductoOL> resultado = new ArrayList<ResultadoCuentaProductoOL>();
		datosCuentaProducto.setResultado(resultado);
		tenenciaConsolidada.setDatos(datosCuentaProducto);
		tenenciaConsolidada.getDatos().setListaResultadoPorProducto(list);

		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaCuentaProductoOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(tenenciaConsolidada);

		CuentaTituloOutEntity cuentaTitulo = new CuentaTituloOutEntity();
		List<CuentaBP> relacionesOperativaTituloList = new ArrayList<CuentaBP>();
		String cuentaTit = "Titulo";
		String cuentaOp = "Operativa";
		CuentaBP cuentaBP = new CuentaBP(cuentaTit, cuentaOp);
		relacionesOperativaTituloList.add(cuentaBP);
		cuentaTitulo.relacionesOperativaTitulo(relacionesOperativaTituloList);

		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
				.thenReturn(cuentaTitulo);

		Respuesta<TenenciaConsolidadaPorProductoDTO> respuestaOk = new Respuesta<TenenciaConsolidadaPorProductoDTO>();
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(TenenciaConsolidadaPorProductoDTO.class))).thenReturn(respuestaOk);

		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("Mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<TenenciaConsolidadaPorProductoDTO> obtenerTenenciaConsolidadaPorProducto = inversionesBO
				.obtenerTenenciaConsolidadaPorProducto(cliente);
		Assert.assertNotNull(obtenerTenenciaConsolidadaPorProducto);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciaConsolidadaPorProductoOkBR() throws DAOException {
		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);
		Cuenta cuentaRetail = new Cuenta();
		cuentasRetail.add(cuentaRetail);
		cuentaRetail.setCodigoPaquete("1040");
		cuentaRetail.setCodigoAplicacion("ATIT");
		cuentaRetail.setNroCuentaProducto("6543");
		cuentaRetail.setNroSucursal("250");
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		cuentaRetail.setIntervinientes(intervinientes);
		List<Cuenta> cuentasPrivadas = new ArrayList<Cuenta>();
		cliente.setCuentasPrivadas(cuentasPrivadas);

		TipoBancaDTO tipoBanca = new TipoBancaDTO();
		tipoBanca.setTipoBanca("BR");

		CuentaProductoOnlineEntity tenenciaConsolidada = new CuentaProductoOnlineEntity();
		DatosRespuestaCuentaProductoOnline datosCuentaProducto = new DatosRespuestaCuentaProductoOnline();
		List<ResultadoTenenciaValuadaCuentaProductoOnline> resultadotenenciaList1 = new ArrayList<ResultadoTenenciaValuadaCuentaProductoOnline>();
		ResultadoTenenciaValuadaCuentaProductoOnline resultadotenencia = new ResultadoTenenciaValuadaCuentaProductoOnline();
		List<ProductoEntity> list = new ArrayList<ProductoEntity>();
		ProductoEntity prod = new ProductoEntity();

		prod.setCodigoProducto("FCI");
		prod.setResultado("0");
		list.add(prod);

		resultadotenencia.setAmortizaciones("0001");
		resultadotenencia.setAmortizacionesReexp("0111");
		resultadotenencia.setCodProducto("FCI");
		resultadotenencia.setDividendos("111111");
		resultadotenencia.setDividendosReexp("11111111");
		resultadotenencia.setMoneda("USD");
		resultadotenencia.setNumeroCuenta("numeroCuenta");
		resultadotenencia.setRentas("111111");
		resultadotenencia.setRentasReexp("11111");
		resultadotenencia.setResultadoBruto("1111");
		resultadotenencia.setResultadoBrutoCorregido("1111");
		resultadotenencia.setResultadoBrutoCorregidoReexp("111111");
		resultadotenencia.setResultadoBrutoReexp("11111");
		resultadotenencia.setSucursal("sucursal");
		resultadotenencia.setTenenciaValuada("111111");
		resultadotenencia.setTenenciaValuadaCompra("111111");
		resultadotenencia.setTenenciaValuadaCompraReexp("111111");
		resultadotenencia.setTenenciaValuadaReexp("1111111");
		resultadotenenciaList1.add(resultadotenencia);
		List<ResultadoCuentaProductoOL> resultado = new ArrayList<ResultadoCuentaProductoOL>();
		datosCuentaProducto.setResultado(resultado);
		tenenciaConsolidada.setDatos(datosCuentaProducto);
		tenenciaConsolidada.getDatos().setListaResultadoPorProducto(list);

		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaCuentaProductoOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(tenenciaConsolidada);

		CuentaTituloOutEntity cuentaTitulo = new CuentaTituloOutEntity();
		List<CuentaBP> relacionesOperativaTituloList = new ArrayList<CuentaBP>();
		String cuentaTit = "Titulo";
		String cuentaOp = "Operativa";
		CuentaBP cuentaBP = new CuentaBP(cuentaTit, cuentaOp);
		relacionesOperativaTituloList.add(cuentaBP);
		cuentaTitulo.relacionesOperativaTitulo(relacionesOperativaTituloList);

		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
				.thenReturn(cuentaTitulo);

		Respuesta<TenenciaConsolidadaPorProductoDTO> respuestaOk = new Respuesta<TenenciaConsolidadaPorProductoDTO>();
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(TenenciaConsolidadaPorProductoDTO.class))).thenReturn(respuestaOk);

		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("Mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<TenenciaConsolidadaPorProductoDTO> obtenerTenenciaConsolidadaPorProducto = inversionesBO
				.obtenerTenenciaConsolidadaPorProducto(cliente);
		Assert.assertNotNull(obtenerTenenciaConsolidadaPorProducto);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void obtenerTenenciaConsolidadaPorProductoErrorDao() throws DAOException {
		Cliente cliente = new Cliente();

		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaCuentaProductoOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenThrow(new DAOException());
		Respuesta respuestaErrorTotalesTenencia = new Respuesta();
		respuestaErrorTotalesTenencia.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO))
				.thenReturn(respuestaErrorTotalesTenencia);

		CuentaTituloOutEntity cuentaTitulo = new CuentaTituloOutEntity();
		List<CuentaBP> relacionesOperativaTituloList = new ArrayList<CuentaBP>();
		String cuentaTit = "Titulo";
		String cuentaOp = "Operativa";
		CuentaBP cuentaBP = new CuentaBP(cuentaTit, cuentaOp);
		relacionesOperativaTituloList.add(cuentaBP);
		cuentaTitulo.relacionesOperativaTitulo(relacionesOperativaTituloList);

		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
				.thenReturn(cuentaTitulo);

		Respuesta<TenenciaConsolidadaPorProductoDTO> obtenerTenenciaConsolidadaPorProducto = inversionesBO
				.obtenerTenenciaConsolidadaPorProducto(cliente);
		Assert.assertNotNull(obtenerTenenciaConsolidadaPorProducto);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciaConsolidadaPorProductoErrorBusiness() throws DAOException {
		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);
		Cuenta cuentaRetail = new Cuenta();
		cuentasRetail.add(cuentaRetail);
		cuentaRetail.setCodigoPaquete("1040");
		cuentaRetail.setCodigoAplicacion("ATIT");
		cuentaRetail.setNroCuentaProducto("6543");
		cuentaRetail.setNroSucursal("250");
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		cuentaRetail.setIntervinientes(intervinientes);
		List<Cuenta> cuentasPrivadas = new ArrayList<Cuenta>();
		cliente.setCuentasPrivadas(cuentasPrivadas);

		CuentaProductoOnlineEntity tenenciaConsolidada = new CuentaProductoOnlineEntity();
		DatosRespuestaCuentaProductoOnline datosCuentaProducto = new DatosRespuestaCuentaProductoOnline();
		List<ResultadoTenenciaValuadaCuentaProductoOnline> resultadotenenciaList1 = new ArrayList<ResultadoTenenciaValuadaCuentaProductoOnline>();
		ResultadoTenenciaValuadaCuentaProductoOnline resultadotenencia = new ResultadoTenenciaValuadaCuentaProductoOnline();
		List<ProductoEntity> list = new ArrayList<ProductoEntity>();
		ProductoEntity prod = new ProductoEntity();

		prod.setCodigoProducto("FCI");
		prod.setResultado("0");
		list.add(prod);

		resultadotenencia.setAmortizaciones("0001");
		resultadotenencia.setAmortizacionesReexp("0111");
		resultadotenencia.setCodProducto("FCI");
		resultadotenencia.setDividendos("111111");
		resultadotenencia.setDividendosReexp("11111111");
		resultadotenencia.setMoneda("ARS");
		resultadotenencia.setNumeroCuenta("numeroCuenta");
		resultadotenencia.setRentas("111111");
		resultadotenencia.setRentasReexp("11111");
		resultadotenencia.setResultadoBruto("1111");
		resultadotenencia.setResultadoBrutoCorregido("1111");
		resultadotenencia.setResultadoBrutoCorregidoReexp("111111");
		resultadotenencia.setResultadoBrutoReexp("11111");
		resultadotenencia.setSucursal("sucursal");
		resultadotenencia.setTenenciaValuada("111111");
		resultadotenencia.setTenenciaValuadaCompra("111111");
		resultadotenencia.setTenenciaValuadaCompraReexp("111111");
		resultadotenencia.setTenenciaValuadaReexp("1111111");
		resultadotenenciaList1.add(resultadotenencia);
		List<ResultadoCuentaProductoOL> resultado = new ArrayList<ResultadoCuentaProductoOL>();
		datosCuentaProducto.setResultado(resultado);
		tenenciaConsolidada.setDatos(datosCuentaProducto);
		tenenciaConsolidada.getDatos().setListaResultadoPorProducto(list);

		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaCuentaProductoOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(tenenciaConsolidada);

		CuentaTituloOutEntity cuentaTitulo = new CuentaTituloOutEntity();
		List<CuentaBP> relacionesOperativaTituloList = new ArrayList<CuentaBP>();
		String cuentaTit = "Titulo";
		String cuentaOp = "Operativa";
		CuentaBP cuentaBP = new CuentaBP(cuentaTit, cuentaOp);
		relacionesOperativaTituloList.add(cuentaBP);
		cuentaTitulo.relacionesOperativaTitulo(relacionesOperativaTituloList);

		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
				.thenThrow(new DAOException());

		Respuesta<TenenciaConsolidadaPorProductoDTO> respuestaOk = new Respuesta<TenenciaConsolidadaPorProductoDTO>();
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(TenenciaConsolidadaPorProductoDTO.class))).thenReturn(respuestaOk);

		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("Mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<TenenciaConsolidadaPorProductoDTO> obtenerTenenciaConsolidadaPorProducto = inversionesBO
				.obtenerTenenciaConsolidadaPorProducto(cliente);
		Assert.assertNotNull(obtenerTenenciaConsolidadaPorProducto);
	}

	@Test
	public void obtenerCustodiaOK() throws DAOException {

		// When
		Cliente cliente = mock(Cliente.class);

		DetalleCustodiaOnlineEntity detalleCustodiaOnlineEntity = DetalleCustodiaOnlineEntityMock
				.armarMockDetalleCustodiaOnlineEntity();

		DetalleCustodiaInView detalleCustodiaInView = new DetalleCustodiaInView();
		detalleCustodiaInView.setEsBancaPrivada(false);
		detalleCustodiaInView.setNumeroCuenta("03549140");

		when(tenenciaValuadaDAO.obtenerTenenciaValuadaDetalleCustOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(detalleCustodiaOnlineEntity);

		// Then
		DetalleCustodiaDTO detalleDTO = inversionesBO.obtenerCustodia(cliente, detalleCustodiaInView);

		// Expected
		Assert.assertNotNull(detalleDTO);
		Assert.assertEquals(false, detalleDTO.getHayError());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciaConsolidadaPorProductoBPrivTestOK() throws DAOException {

		Cliente cliente = cargarClienteTenenciaConsolidadaBPriv();

		CuentaProductoOnlineEntity outEntity = cargarOutEntityCuentaProductoOnline();
		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaCuentaProductoOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(outEntity);

		Respuesta<TenenciaConsolidadaBPrivDTO> respuestaOK = new Respuesta<TenenciaConsolidadaBPrivDTO>();
		respuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(TenenciaConsolidadaBPrivDTO.class))).thenReturn(respuestaOK);
		Respuesta<TenenciaConsolidadaBPrivDTO> rtaBo = inversionesBO
				.obtenerTenenciaConsolidadaPorProductoBPriv(cliente);
		Assert.assertEquals(EstadoRespuesta.OK, rtaBo.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciaConsolidadaPorProductoBPrivTestWarning() throws DAOException {

		Cliente cliente = cargarClienteTenenciaConsolidadaBPriv();

		CuentaProductoOnlineEntity outEntity = cargarOutEntityCuentaProductoOnline();
		marcarResultadoParcialParaRespuestaWarning(outEntity);
		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaCuentaProductoOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(outEntity);

		Respuesta<TenenciaConsolidadaBPrivDTO> respuestaWarning = new Respuesta<TenenciaConsolidadaBPrivDTO>();
		respuestaWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class),
				Matchers.any(TenenciaConsolidadaBPrivDTO.class), Matchers.any(TipoError.class), Matchers.anyString(),
				Matchers.anyString())).thenReturn(respuestaWarning);
		Respuesta<TenenciaConsolidadaBPrivDTO> rtaBo = inversionesBO
				.obtenerTenenciaConsolidadaPorProductoBPriv(cliente);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaBo.getEstadoRespuesta());
	}

	@Test
	public void obtenerTenenciaConsolidadaReporte() {

		// When
		Cliente cliente = Mockito.mock(Cliente.class);
		List<TenenciaProductosPorMonedaView> listaTenencias = crearInfoTenenciaConsolidadaExcel();
		Respuesta<Reporte> respuestaMock = new Respuesta<Reporte>();
		respuestaMock.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(reporteDAO.obtenerReporte(Matchers.any(TenenciaPlazoFijoExcel.class), Matchers.anyString(),
				Matchers.any(Cliente.class), Matchers.anyBoolean())).thenReturn(respuestaMock);

		// Then
		respuestaMock = inversionesBO.obtenerTenenciaConsolidadaReporte(listaTenencias, cliente);

		// Expected
		Assert.assertNotNull(respuestaMock);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaMock.getEstadoRespuesta());

	}

	@Test
	public void obtenerTenenciaConsolidadaPrivadaReporte() {

		// When
		Cliente cliente = Mockito.mock(Cliente.class);
		List<TenenciaPorCuentaBPrivDTO> listaTenencias = crearInfoTenenciaConsolidadaBPExcel();
		Respuesta<Reporte> respuestaMock = new Respuesta<Reporte>();
		respuestaMock.setEstadoRespuesta(EstadoRespuesta.OK);

		Cuenta cuenta = new Cuenta();
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		Interviniente interviniente = new Interviniente();
		interviniente.setApellido("Alfaro");
		interviniente.setNombre("Gustavo");
		intervinientes.add(interviniente);
		cuenta.setIntervinientes(intervinientes);

		Mockito.when(cliente.getCuentaOperativaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
		Mockito.when(reporteDAO.obtenerReporte(Matchers.any(TenenciaPlazoFijoExcel.class), Matchers.anyString(),
				Matchers.any(Cliente.class), Matchers.anyBoolean())).thenReturn(respuestaMock);

		// Then
		respuestaMock = inversionesBO.obtenerTenenciaConsolidadaReporteBP(listaTenencias, cliente);

		// Expected
		Assert.assertNotNull(respuestaMock);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaMock.getEstadoRespuesta());

	}

	private List<TenenciaPorCuentaBPrivDTO> crearInfoTenenciaConsolidadaBPExcel() {

		List<TenenciaPorCuentaBPrivDTO> listaTenencias = new ArrayList<TenenciaPorCuentaBPrivDTO>();
		TenenciaPorCuentaBPrivDTO cuentaTC = new TenenciaPorCuentaBPrivDTO();

		cuentaTC.setNroCuenta("03549140");
		cuentaTC.setNroCuentaFormateado("250-354914/0");

		TenenciaProductosPorMonedaDTO tenenciaPesos = new TenenciaProductosPorMonedaDTO();
		List<TenenciaPorProductoBPrivDTO> listaTenenciaProductos = new ArrayList<TenenciaPorProductoBPrivDTO>();
		TenenciaPorProductoBPrivDTO producto1 = new TenenciaPorProductoBPrivDTO();
		producto1.setProducto("PF");
		producto1.setResultado("1760.65");
		producto1.setTenenciaValuadaCosto("30000.00");
		producto1.setTenenciaValuadaHoy("31760.65");
		listaTenenciaProductos.add(producto1);

		TenenciaPorProductoBPrivDTO producto2 = new TenenciaPorProductoBPrivDTO();
		producto2.setProducto("FCI");
		producto2.setResultado("10495182.07");
		producto2.setTenenciaValuadaCosto("0.00");
		producto2.setTenenciaValuadaHoy("10495182.07");
		listaTenenciaProductos.add(producto2);

		TenenciaPorProductoBPrivDTO producto3 = new TenenciaPorProductoBPrivDTO();
		producto3.setProducto("TV");
		producto3.setResultado("0.00");
		producto3.setTenenciaValuadaCosto("0.00");
		producto3.setTenenciaValuadaHoy("31760.65");
		listaTenenciaProductos.add(producto3);

		TenenciaPorProductoBPrivDTO producto4 = new TenenciaPorProductoBPrivDTO();
		producto4.setProducto("LIQ");
		producto4.setResultado("-");
		producto4.setTenenciaValuadaCosto("-");
		producto4.setTenenciaValuadaHoy("31760.65");
		listaTenenciaProductos.add(producto4);

		TenenciaPorProductoBPrivDTO producto5 = new TenenciaPorProductoBPrivDTO();
		producto5.setProducto("CUS");
		producto5.setResultado("0.00");
		producto5.setTenenciaValuadaCosto("0.00");
		producto5.setTenenciaValuadaHoy("0.00");
		listaTenenciaProductos.add(producto5);

		tenenciaPesos.setListaTenenciaProductos(listaTenenciaProductos);
		tenenciaPesos.setTotalResultado("31760.65");
		tenenciaPesos.setTotalTenenciaValuadaCosto("31760.65");
		tenenciaPesos.setTotalTenenciaValuadaHoy("31760.65");

		cuentaTC.setTenenciaPesos(tenenciaPesos);
		listaTenencias.add(cuentaTC);

		return listaTenencias;

	}

	private List<TenenciaProductosPorMonedaView> crearInfoTenenciaConsolidadaExcel() {

		List<TenenciaProductosPorMonedaView> listaTenenciaView = new ArrayList<TenenciaProductosPorMonedaView>();

		TenenciaProductosPorMonedaView tenenciaPesos = new TenenciaProductosPorMonedaView();
		tenenciaPesos.setMoneda("ARS");
		tenenciaPesos.setTotalResultado("216.078,11");
		tenenciaPesos.setTotalTenenciaValuadaCosto("4.139.670,05");
		tenenciaPesos.setTotalTenenciaValuadaHoy("5.758.939,20");

		List<TenenciaPorProductoView> listaTenenciaProductos = new ArrayList<TenenciaPorProductoView>();
		TenenciaPorProductoView producto1 = new TenenciaPorProductoView();
		producto1.setProducto("PF");
		producto1.setResultado("77.223,37");
		producto1.setTenenciaValuadaCosto("4.129.834,78");
		producto1.setTenenciaValuadaHoy("4.206.829,19");
		listaTenenciaProductos.add(producto1);

		TenenciaPorProductoView producto2 = new TenenciaPorProductoView();
		producto2.setProducto("FCI");
		producto2.setResultado("138.854,74");
		producto2.setTenenciaValuadaCosto("9.835,27");
		producto2.setTenenciaValuadaHoy("148.690,01");
		listaTenenciaProductos.add(producto2);

		TenenciaPorProductoView producto3 = new TenenciaPorProductoView();
		producto3.setProducto("TV");
		producto3.setResultado("-");
		producto3.setTenenciaValuadaCosto("-");
		producto3.setTenenciaValuadaHoy("1.403.420,00");
		listaTenenciaProductos.add(producto3);

		tenenciaPesos.setListaTenenciaProductos(listaTenenciaProductos);

		TenenciaProductosPorMonedaView tenenciaDolares = new TenenciaProductosPorMonedaView();
		tenenciaDolares.setMoneda("USD");
		tenenciaDolares.setTotalResultado("4.000,93");
		tenenciaDolares.setTotalTenenciaValuadaCosto("4.154,00");
		tenenciaDolares.setTotalTenenciaValuadaHoy("4.158,93");

		List<TenenciaPorProductoView> listaTenenciaProductosDolares = new ArrayList<TenenciaPorProductoView>();
		TenenciaPorProductoView producto4 = new TenenciaPorProductoView();
		producto4.setProducto("PF");
		producto4.setResultado("4.000,93");
		producto4.setTenenciaValuadaCosto("4.154,00");
		producto4.setTenenciaValuadaHoy("4.158,93");
		listaTenenciaProductosDolares.add(producto4);

		TenenciaPorProductoView producto5 = new TenenciaPorProductoView();
		producto5.setProducto("FCI");
		producto5.setResultado("0,00");
		producto5.setTenenciaValuadaCosto("0,00");
		producto5.setTenenciaValuadaHoy("0,00");
		listaTenenciaProductosDolares.add(producto5);

		TenenciaPorProductoView producto6 = new TenenciaPorProductoView();
		producto6.setProducto("TV");
		producto6.setResultado("0,00");
		producto6.setTenenciaValuadaCosto("0,00");
		producto6.setTenenciaValuadaHoy("0,00");
		listaTenenciaProductosDolares.add(producto6);

		tenenciaDolares.setListaTenenciaProductos(listaTenenciaProductosDolares);

		listaTenenciaView.add(tenenciaPesos);
		listaTenenciaView.add(tenenciaDolares);

		return listaTenenciaView;

	}

	private void marcarResultadoParcialParaRespuestaWarning(CuentaProductoOnlineEntity outEntity) {
		outEntity.getDatos().getListaResultadoPorProducto().get(0).setResultado("1");
	}

	private CuentaProductoOnlineEntity cargarOutEntityCuentaProductoOnline() {

		CuentaProductoOnlineEntity outEntity = new CuentaProductoOnlineEntity();

		DatosRespuestaCuentaProductoOnline datos = new DatosRespuestaCuentaProductoOnline();

		ProductoEntity producto1 = new ProductoEntity();
		producto1.setCodigoProducto("FCI");
		producto1.setResultado("0");

		ProductoEntity producto2 = new ProductoEntity();
		producto2.setCodigoProducto("TV");
		producto2.setResultado("0");

		ProductoEntity producto3 = new ProductoEntity();
		producto3.setCodigoProducto("CUS");
		producto3.setResultado("0");

		ProductoEntity producto4 = new ProductoEntity();
		producto4.setCodigoProducto("PF");
		producto4.setResultado("0");

		ProductoEntity producto5 = new ProductoEntity();
		producto5.setCodigoProducto("LIQ");
		producto5.setResultado("0");

		List<ProductoEntity> listaResultadoPorProducto = new ArrayList<ProductoEntity>();
		listaResultadoPorProducto.add(producto1);
		listaResultadoPorProducto.add(producto2);
		listaResultadoPorProducto.add(producto3);
		listaResultadoPorProducto.add(producto4);
		listaResultadoPorProducto.add(producto5);
		datos.setListaResultadoPorProducto(listaResultadoPorProducto);

		ResultadoCuentaProductoOL resultadoProducto1 = new ResultadoCuentaProductoOL();
		resultadoProducto1.setNumeroCuenta("0001234567");
		resultadoProducto1.setSucursal("250");
		resultadoProducto1.setMoneda("ARS");
		resultadoProducto1.setTenenciaValuadaCompra("1050");
		resultadoProducto1.setTenenciaValuada("2030");
		resultadoProducto1.setResultadoBruto("3040");
		resultadoProducto1.setCodProducto("FCI");
		resultadoProducto1.setTenenciaValuadaReexp("4020");

		ResultadoCuentaProductoOL resultadoProducto2 = new ResultadoCuentaProductoOL();
		resultadoProducto2.setNumeroCuenta("0001234567");
		resultadoProducto2.setSucursal("250");
		resultadoProducto2.setMoneda("ARS");
		resultadoProducto2.setTenenciaValuadaCompra("1020");
		resultadoProducto2.setTenenciaValuada("2020");
		resultadoProducto2.setResultadoBrutoCorregido("3010");
		resultadoProducto2.setCodProducto("TV");
		resultadoProducto2.setTenenciaValuadaReexp("4010");

		ResultadoCuentaProductoOL resultadoProducto3 = new ResultadoCuentaProductoOL();
		resultadoProducto3.setNumeroCuenta("0001234567");
		resultadoProducto3.setSucursal("250");
		resultadoProducto3.setMoneda("ARS");
		resultadoProducto3.setTenenciaValuadaCompra("1020");
		resultadoProducto3.setTenenciaValuada("2020");
		resultadoProducto3.setResultadoBruto("3020");
		resultadoProducto3.setCodProducto("CUS");
		resultadoProducto3.setTenenciaValuadaReexp("4060");

		ResultadoCuentaProductoOL resultadoProducto4 = new ResultadoCuentaProductoOL();
		resultadoProducto4.setNumeroCuenta("0001234567");
		resultadoProducto4.setSucursal("250");
		resultadoProducto4.setMoneda("ARS");
		resultadoProducto4.setTenenciaValuadaCompra("1020");
		resultadoProducto4.setTenenciaValuada("2010");
		resultadoProducto4.setResultadoBruto("3020");
		resultadoProducto4.setCodProducto("PF");
		resultadoProducto4.setTenenciaValuadaReexp("4040");

		ResultadoCuentaProductoOL resultadoProducto5 = new ResultadoCuentaProductoOL();
		resultadoProducto5.setNumeroCuenta("0001234567");
		resultadoProducto5.setSucursal("250");
		resultadoProducto5.setMoneda("USD");
		resultadoProducto5.setTenenciaValuadaCompra("1010");
		resultadoProducto5.setTenenciaValuada("2030");
		resultadoProducto5.setResultadoBruto("3030");
		resultadoProducto5.setCodProducto("CUS");
		resultadoProducto5.setTenenciaValuadaReexp("4050");

		ResultadoCuentaProductoOL resultadoProducto6 = new ResultadoCuentaProductoOL();
		resultadoProducto6.setNumeroCuenta("0001234567");
		resultadoProducto6.setSucursal("250");
		resultadoProducto6.setMoneda("USD");
		resultadoProducto6.setTenenciaValuadaCompra("1030");
		resultadoProducto6.setTenenciaValuada("2010");
		resultadoProducto6.setResultadoBruto("3010");
		resultadoProducto6.setCodProducto("PF");
		resultadoProducto6.setTenenciaValuadaReexp("4010");

		ResultadoCuentaProductoOL resultadoProducto1Cuenta2 = new ResultadoCuentaProductoOL();
		resultadoProducto1Cuenta2.setNumeroCuenta("0007654321");
		resultadoProducto1Cuenta2.setSucursal("250");
		resultadoProducto1Cuenta2.setMoneda("ARS");
		resultadoProducto1Cuenta2.setTenenciaValuadaCompra("1020");
		resultadoProducto1Cuenta2.setTenenciaValuada(null);
		resultadoProducto1Cuenta2.setResultadoBruto("3020");
		resultadoProducto1Cuenta2.setCodProducto("CUS");
		resultadoProducto1Cuenta2.setTenenciaValuadaReexp("4030");

		ResultadoCuentaProductoOL resultadoProducto2Cuenta2 = new ResultadoCuentaProductoOL();
		resultadoProducto2Cuenta2.setNumeroCuenta("0007654321");
		resultadoProducto2Cuenta2.setSucursal("250");
		resultadoProducto2Cuenta2.setMoneda("ARS");
		resultadoProducto2Cuenta2.setTenenciaValuadaCompra("1020");
		resultadoProducto2Cuenta2.setTenenciaValuada("2010");
		resultadoProducto2Cuenta2.setResultadoBruto("3020");
		resultadoProducto2Cuenta2.setCodProducto("PF");
		resultadoProducto2Cuenta2.setTenenciaValuadaReexp("4040");

		ResultadoCuentaProductoOL resultadoProducto3Cuenta2 = new ResultadoCuentaProductoOL();
		resultadoProducto3Cuenta2.setNumeroCuenta("0007654321");
		resultadoProducto3Cuenta2.setSucursal("250");
		resultadoProducto3Cuenta2.setMoneda("USD");
		resultadoProducto3Cuenta2.setTenenciaValuadaCompra("1010");
		resultadoProducto3Cuenta2.setTenenciaValuada("2030");
		resultadoProducto3Cuenta2.setResultadoBruto("3030");
		resultadoProducto3Cuenta2.setCodProducto("CUS");
		resultadoProducto3Cuenta2.setTenenciaValuadaReexp("4060");

		ResultadoCuentaProductoOL resultadoProducto4Cuenta2 = new ResultadoCuentaProductoOL();
		resultadoProducto4Cuenta2.setNumeroCuenta("0007654321");
		resultadoProducto4Cuenta2.setSucursal("250");
		resultadoProducto4Cuenta2.setMoneda("USD");
		resultadoProducto4Cuenta2.setTenenciaValuadaCompra("1030");
		resultadoProducto4Cuenta2.setTenenciaValuada("2010");
		resultadoProducto4Cuenta2.setResultadoBruto("3010");
		resultadoProducto4Cuenta2.setCodProducto("PF");
		resultadoProducto4Cuenta2.setTenenciaValuadaReexp("4040");

		List<ResultadoCuentaProductoOL> listaResultado = new ArrayList<ResultadoCuentaProductoOL>();
		listaResultado.add(resultadoProducto1);
		listaResultado.add(resultadoProducto2);
		listaResultado.add(resultadoProducto3);
		listaResultado.add(resultadoProducto4);
		listaResultado.add(resultadoProducto5);
		listaResultado.add(resultadoProducto6);
		listaResultado.add(resultadoProducto1Cuenta2);
		listaResultado.add(resultadoProducto2Cuenta2);
		listaResultado.add(resultadoProducto3Cuenta2);
		listaResultado.add(resultadoProducto4Cuenta2);
		datos.setResultado(listaResultado);

		outEntity.setDatos(datos);

		return outEntity;
	}

	private Cliente cargarClienteTenenciaConsolidadaBPriv() {

		Cliente cliente = new Cliente();
		List<CuentaBancaPrivada> cuentasBancaPrivadaList = new ArrayList<CuentaBancaPrivada>();
		cliente.setCuentasBancaPrivada(cuentasBancaPrivadaList);

		CuentaBancaPrivada cuentaBP1 = new CuentaBancaPrivada();
		Cuenta cuentaOperativa1 = new Cuenta();
		cuentaOperativa1.setNroCuentaProducto("0001234567");
		cuentaOperativa1.setNroSucursal("250");
		cuentaOperativa1.setProductoAltair("9");
		cuentaBP1.setCuentaOperativa(cuentaOperativa1);

		CuentaBancaPrivada cuentaBP2 = new CuentaBancaPrivada();
		Cuenta cuentaOperativa2 = new Cuenta();
		cuentaOperativa2.setNroCuentaProducto("0007654321");
		cuentaOperativa2.setNroSucursal("250");
		cuentaOperativa2.setProductoAltair("9");
		cuentaBP2.setCuentaOperativa(cuentaOperativa2);

		cuentasBancaPrivadaList.add(cuentaBP1);
		cuentasBancaPrivadaList.add(cuentaBP2);

		return cliente;
	}

}