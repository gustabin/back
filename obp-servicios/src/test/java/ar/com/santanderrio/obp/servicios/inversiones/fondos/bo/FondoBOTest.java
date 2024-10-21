package ar.com.santanderrio.obp.servicios.inversiones.fondos.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.prototype.FondosTenenciasPrototype;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import org.apache.commons.collections.Predicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.CuentaSaldoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaLoadSaldo;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.dao.CuentaTituloDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.exception.FondoSinMovimientosException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMayorAlMaximoException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao.FondoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.FondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.TenenciaValuadaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaConTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaServiceTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.GraficoFondosBPrivOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.GraficoFondosRTLOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.InicioFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularSuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SuscripcionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaFondosSuscritosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaPorFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciasFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TipoBancaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteSuscripcionFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleTenenciaValuadaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientoFondoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientosFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientosFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ResultadoTenenciaValuadaDetalleFondoOL;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SuscripcionFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SuscripcionFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateFondoEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CotizacionDeFondosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoIn;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.excel.CuentasTitulosExcelGeneral;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ImporteLimiteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class FondoBOTest.
 */

@RunWith(MockitoJUnitRunner.class)
public class FondoBOTest {

	/** The codigo error. */
	private static String CODIGO_ERROR = "-1";
	/** The fondo BO. */
	@InjectMocks
	private FondoBOImpl fondoBO;

	/** The cuenta saldo DAO. */
	@Mock
	private CuentaSaldoDAO cuentaSaldoDAO;

	/** The fondo B priv DAO. */
	@Mock
	private FondoBPrivDAO fondoBPrivDAO;

	/** The mensaje DAO. */
	@Mock
	private MensajeDAO mensajeDAO;

	/** The fondo dao. */
	@Mock
	private FondoDAO fondoDao;

	/** The consulta fondo DAO. */
	@Mock
	private ConsultaFondoDAO consultaFondoDAO;

	/** The legal BO. */
	@Mock
	private LegalBO legalBO;

	/** The respuesta factory. */
	@Mock
	private RespuestaFactory respuestaFactory;

	@Mock
	private SesionCliente sesionCliente;

	/** The estadistica BO. */
	@Mock
	private EstadisticaBO estadisticaBO;

	/** The cuenta titulo DAO. */
	@Mock
	private CuentaTituloDAO cuentaTituloDAO;

	/** The session parametros. */
	@Mock
	private SesionParametros sessionParametros;

	/** The mensaje bo. */
	@Mock
	private MensajeBO mensajeBo;

	/** The contratos BO. */
	@Mock
	private ContratoBO contratosBO;

	/** The credential security. */
	@Mock
	private CredentialSecurityFactory credentialSecurity;

	/** The tenencia valuada */
	@Mock
	private TenenciaValuadaDAO tenenciaValuadaDAO;

	@Mock
	private ReporteDAO reporteDAO;

	@Mock
	private RescateFondoEspecie rescateFondoEspecie;

	/** The consulta fondo DAO. */
	@Mock
	private FondosTenenciasPrototype fondosTenenciasPrototype;

	/** The ModuloPermiso BO. */
	@Mock
	private ModuloPermisoBO moduloPermisoBO;

	/** The FundsApi BO. */
	@Mock
	private FundsApi fundsApi;

	/** The dto request. */
	private FondoDTO dtoRequestPesos;

	private FondoDTO dtoRequestDolar;

	/**
	 * Inits the.
	 *
	 * @throws ServiceException the service exception
	 */
	@Before
	public void init() throws ServiceException {
		dtoRequestPesos = new FondoDTO();
		dtoRequestPesos.setCodigoFondo("082");
		dtoRequestPesos.setCuentaTitulo("014074/2");
		dtoRequestPesos.setImporte("234.567,00");
		dtoRequestPesos.setMoneda("$");
		dtoRequestPesos.setNumeroCtaDeb("063880/1");
		dtoRequestPesos.setTipoCtaDeb("CU");

		dtoRequestDolar = new FondoDTO();
		dtoRequestDolar.setCodigoFondo("082");
		dtoRequestDolar.setCuentaTitulo("014074/2");
		dtoRequestDolar.setImporte("234.567,00");
		dtoRequestDolar.setMoneda("u$s");
		dtoRequestDolar.setNumeroCtaDeb("063880/1");
		dtoRequestDolar.setTipoCtaDeb("CU");
	}

	/**
	 * Simular suscripcion legales OK.
	 *
	 * @throws DAOException the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void simularSuscripcionLegalesOK() throws DAOException {

		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity respuestaSuscripcionFondoOutEntity = new SuscripcionFondoOutEntity();
		respuestaSuscripcionFondoOutEntity.setCodigoRetornoExtendido("00000000");
		Mockito.when(fondoDao.suscribir(Matchers.any(SuscripcionFondoInEntity.class)))
				.thenReturn(respuestaSuscripcionFondoOutEntity);
		List<ConsultaFondoOutEntity> respuestaConsulta = new ArrayList<ConsultaFondoOutEntity>();
		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setLinkRegla("x");
		respuestaConsulta.add(consultaFondoOutEntity);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);

		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegal);
		Respuesta<FondoDTO> respuestaSimularSuscripcion = new Respuesta<FondoDTO>();
		respuestaSimularSuscripcion.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FondoDTO.class)))
				.thenReturn(respuestaSimularSuscripcion);
		respuestaSimularSuscripcion = fondoBO.simularSuscripcion(dtoRequestPesos, cliente);
		Assert.assertNotNull(respuestaSimularSuscripcion);
		Assert.assertEquals(respuestaSimularSuscripcion.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	/**
	 * Simular suscripcion legales error.
	 *
	 * @throws DAOException the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void simularSuscripcionLegalesError() throws DAOException {

		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity respuestaSuscripcionFondoOutEntity = new SuscripcionFondoOutEntity();
		respuestaSuscripcionFondoOutEntity.setCodigoRetornoExtendido("00000000");
		Mockito.when(fondoDao.suscribir(Matchers.any(SuscripcionFondoInEntity.class)))
				.thenReturn(respuestaSuscripcionFondoOutEntity);
		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setLinkRegla("x");
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);
		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegal);
		@SuppressWarnings("rawtypes")
		Respuesta respuestaSimularSuscripcion = new Respuesta();
		respuestaSimularSuscripcion.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaSimularSuscripcion);
		respuestaSimularSuscripcion = fondoBO.simularSuscripcion(dtoRequestPesos, cliente);
		Assert.assertNotNull(respuestaSimularSuscripcion);
		Assert.assertEquals(respuestaSimularSuscripcion.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	/**
	 * Obtener fondos suscriptos Y disponibles B priv test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void obtenerFondosSuscriptosYDisponiblesBPrivTest() throws DAOException {
		CuentasConsultaFondoDTO cuentasConsultaFondoDTO = new CuentasConsultaFondoDTO();
		cuentasConsultaFondoDTO.setTipoBanca("BP");
		CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
		List<CuentaTituloDTO> cuentasTituloList = new ArrayList<CuentaTituloDTO>();
		cuentasTituloList.add(cuentaTituloDTO);
		cuentasConsultaFondoDTO.setCuentasTitulo(cuentasTituloList);
		Cliente cliente = new Cliente();
		Respuesta<CuentasConsultaFondoDTO> respuestaCuentasConsultaFondoDTO = new Respuesta<CuentasConsultaFondoDTO>();
		respuestaCuentasConsultaFondoDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		List<ConsultaFondoOutEntity> respuestaConsulta = new ArrayList<ConsultaFondoOutEntity>();
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("solo una cuenta");
		Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		CuentaTituloOutEntity outEntityLoadAtits = new CuentaTituloOutEntity();
		List<CuentaBP> cuentasTit = new ArrayList<CuentaBP>();
		outEntityLoadAtits.relacionesOperativaTitulo(cuentasTit);
		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
				.thenReturn(outEntityLoadAtits);
		Mockito.when(consultaFondoDAO.obtenerPorBanca((Matchers.any(String.class)))).thenReturn(respuestaConsulta);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(CuentasConsultaFondoDTO.class))).thenReturn(respuestaCuentasConsultaFondoDTO);

		Mockito.when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		respuestaCuentasConsultaFondoDTO = fondoBO.obtenerFondosSuscriptosYDisponiblesBPriv(cuentasConsultaFondoDTO,
				cliente, true);
		Assert.assertNotNull(respuestaCuentasConsultaFondoDTO);
		Assert.assertEquals(respuestaCuentasConsultaFondoDTO.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	/**
	 * Obtener fondos suscriptos Y disponibles test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void obtenerFondosSuscriptosYDisponiblesTest() throws DAOException {
		CuentasConsultaFondoDTO cuentasConsultaFondoDTO = new CuentasConsultaFondoDTO();
		cuentasConsultaFondoDTO.setTipoBanca("BP");
		CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
		List<CuentaTituloDTO> cuentasTituloList = new ArrayList<CuentaTituloDTO>();
		cuentasTituloList.add(cuentaTituloDTO);
		cuentasConsultaFondoDTO.setCuentasTitulo(cuentasTituloList);
		Cliente cliente = new Cliente();
		Respuesta<CuentasConsultaFondoDTO> respuestaCuentasConsultaFondoDTO = new Respuesta<CuentasConsultaFondoDTO>();
		respuestaCuentasConsultaFondoDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		List<ConsultaFondoOutEntity> respuestaConsulta = new ArrayList<ConsultaFondoOutEntity>();
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("solo una cuenta");
		Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		CuentaTituloOutEntity outEntityLoadAtits = new CuentaTituloOutEntity();
		List<CuentaBP> cuentasTit = new ArrayList<CuentaBP>();
		outEntityLoadAtits.relacionesOperativaTitulo(cuentasTit);
		ConsultaTenenciaFCIOutEntity consultaTenenciaFCI = new ConsultaTenenciaFCIOutEntity();
		Mockito.when(fondoDao.consultaTenenciaFCI(Matchers.any(Cliente.class),
				Matchers.any(ConsultaTenenciaFCIInEntity.class))).thenReturn(consultaTenenciaFCI);
		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
				.thenReturn(outEntityLoadAtits);
		Mockito.when(consultaFondoDAO.obtenerPorBanca((Matchers.any(String.class)))).thenReturn(respuestaConsulta);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(CuentasConsultaFondoDTO.class))).thenReturn(respuestaCuentasConsultaFondoDTO);
		Mockito.when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		respuestaCuentasConsultaFondoDTO = fondoBO.obtenerFondosSuscriptosYDisponibles(cuentasConsultaFondoDTO, cliente,
				true, true);
		Assert.assertNotNull(respuestaCuentasConsultaFondoDTO);
		Assert.assertEquals(respuestaCuentasConsultaFondoDTO.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	/**
	 * Comprobante rescate test.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerSaldosCuentaOperativaTest() {

		CuentaSaldoOutEntity respuestaVerSaldos = new CuentaSaldoOutEntity();
		Respuesta<CuentasAdhesionDebitoView> respuestaObtenerSaldosCuentaOperativa = new Respuesta<CuentasAdhesionDebitoView>();
		List<RtaLoadSaldo> lista = new ArrayList<RtaLoadSaldo>();

		String cuentasaldo = "";
		String descripcion = "";
		String sucursal = "";
		String asesor = "";
		String fecha = "";
		String cAhorroPesos = "1275.50";
		String cAhorroDolares = "5899";
		RtaLoadSaldo elemento = new RtaLoadSaldo(cuentasaldo, descripcion, sucursal, asesor, fecha, cAhorroPesos,
				cAhorroDolares);
		lista.add(elemento);
		respuestaVerSaldos.setRespuesta(lista);
		when(cuentaSaldoDAO.verSaldos(Matchers.any(CuentaSaldoInEntity.class))).thenReturn(respuestaVerSaldos);

		CuentasAdhesionDebitoView cuentasView = new CuentasAdhesionDebitoView();
		cuentasView.setSaldoDolares("5.899,00");
		cuentasView.setSaldoPesos("1.275,50");
		cuentasView.setNumero("250-673891/1");
		cuentasView.setAbreviaturaTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura());
		cuentasView.setDescripcionTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getDescripcion());

		Respuesta<CuentasAdhesionDebitoView> responseFactory = new Respuesta<CuentasAdhesionDebitoView>();
		responseFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		responseFactory.setRespuestaVacia(false);
		responseFactory.setRespuesta(cuentasView);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(CuentasAdhesionDebitoView.class))).thenReturn(responseFactory);

		List<Cuenta> listaCuentasPrivadas = new ArrayList<Cuenta>();
		Cuenta cuentaPrivada = new Cuenta();
		String nroCuentaProducto = "6738911";
		String nroSucursal = "250";

		cuentaPrivada.setNroCuentaProducto(nroCuentaProducto);
		cuentaPrivada.setNroSucursal(nroSucursal);
		cuentaPrivada.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		cuentaPrivada.setProductoAltair("9");

		listaCuentasPrivadas.add(cuentaPrivada);

		Cliente cliente = new Cliente();
		cliente.setCuentasPrivadas(listaCuentasPrivadas);
		String nroCuenta = "7006738911";
		respuestaObtenerSaldosCuentaOperativa = fondoBO.obtenerSaldosCuentaOperativa(nroCuenta, cliente, false);
		Assert.assertNotNull(respuestaObtenerSaldosCuentaOperativa);
		Assert.assertEquals(respuestaObtenerSaldosCuentaOperativa, responseFactory);

	}

	/**
	 * Consultar cotizaciones test.
	 *
	 * @throws DAOException the DAO exception
	 */

	@Test
	public void consultarCotizacionesTest() throws DAOException {

		FondoOutEntity respuestaConsultarCotizaciones = new FondoOutEntity();
		List<FondoEntity> fondos = new ArrayList<FondoEntity>();
		FondoEntity fondo = new FondoEntity();
		String valor_a_la_fecha = "123456789876543";
		String codigoFondo = "035";
		String linkCartera = "x";

		fondo.setValor_a_la_fecha(valor_a_la_fecha);
		fondo.setTipo_Fondo(codigoFondo);

		fondos.add(fondo);
		respuestaConsultarCotizaciones.setfondos(fondos);

		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());

		Mockito.when(fondoDao.consultarCotizaciones())
				.thenReturn(respuestaConsultarCotizaciones);

		List<ConsultaFondoOutEntity> listaFondos = new ArrayList<ConsultaFondoOutEntity>();
		ConsultaFondoOutEntity fondoConsulta = new ConsultaFondoOutEntity();

		fondoConsulta.setCodigoFondo(codigoFondo);
		fondoConsulta.setHonorariosAdmin("5.0");
		fondoConsulta.setHonorariosSalida("5.45");
		fondoConsulta.setHonorariosEntrada("5.45");

		fondoConsulta.setLinkCartera(linkCartera);
		listaFondos.add(fondoConsulta);

		Mockito.when(consultaFondoDAO.obtenerFondos()).thenReturn(listaFondos);

		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);

		Respuesta<CotizacionDeFondosView> respuesta = fondoBO.consultarCotizaciones();

		Assert.assertNotNull(respuesta);
	}

	/**
	 * Consultar cotizaciones error test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void consultarCotizacionesErrorTest() throws DAOException {

		Mockito.when(fondoDao.consultarCotizaciones()).thenThrow(new DAOException());
		Respuesta<FondoDTO> responseFactoryError = new Respuesta<FondoDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);
		Mockito.when(respuestaFactory.crearRespuestaError(FondoDTO.class, null)).thenReturn(responseFactoryError);

		Respuesta<CotizacionDeFondosView> respuesta = fondoBO.consultarCotizaciones();

		Assert.assertNotNull(respuesta);
	}

	/**
	 * Obtener tenencias ok test.
	 *
	 * @throws BusinessException the business exception
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void obtenerTenenciasOkTest() throws BusinessException, DAOException {
		TipoBancaDTO tipoBanca = new TipoBancaDTO();
		tipoBanca.setTipoBanca(
				ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.Segmento.BANCA_PERSONAL.getCodigo());
		Cliente cliente = new Cliente();
		CuentaTituloOutEntity outEntityLoadAtits = new CuentaTituloOutEntity();
		List<CuentaBP> list = new ArrayList<CuentaBP>();
		CuentaBP cuenta = new CuentaBP("123456", "456789");
		list.add(cuenta);
		outEntityLoadAtits.relacionesOperativaTitulo(list);
		RegistroSesion respuestaSesion = new RegistroSesion();
		Mockito.when(sessionParametros.getRegistroSession()).thenReturn(respuestaSesion);
		Estadistica respuestaEstadistica = new Estadistica();
		Mockito.when(estadisticaBO.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
				Matchers.any(Cliente.class))).thenReturn(respuestaEstadistica);
		Respuesta<TenenciasFondoDTO> responseFactoryOk = new Respuesta<TenenciasFondoDTO>();
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(TenenciasFondoDTO.class)))
				.thenReturn(responseFactoryOk);

		DetalleTenenciaValuadaEntity tenenciaValuada = new DetalleTenenciaValuadaEntity();
		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaDetalleFondoOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(tenenciaValuada);
		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class)))
				.thenReturn(outEntityLoadAtits);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<TenenciasFondoDTO> respuestaBo = fondoBO.obtenerTenencias(tipoBanca, cliente);

		Assert.assertNotNull(respuestaBo);
		Assert.assertEquals(respuestaBo, responseFactoryOk);
	}

	/**
	 * suscribir Fondos BPriv OK test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void finalizarSuscribirFondosBPrivOKTest() throws DAOException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();

		SuscripcionInDTO suscripcionInDTO = new SuscripcionInDTO();
		suscripcionInDTO.setCodigoFondo("007");
		suscripcionInDTO.setDentroDelPerfil("S-1.6");
		suscripcionInDTO.setImporte(new BigDecimal("600"));
		suscripcionInDTO.setNombreFondo("Super Ahorro $");
		suscripcionInDTO.setNroCuentaBancaPrivada("7003549140");

		// daoOUT
		EjecucionFondoBancaPrivadaOutEntity respuestaSuscribir = new EjecucionFondoBancaPrivadaOutEntity();
		respuestaSuscribir.setDisclaimer(
				"El cliente ASCANELLI SA manifiesta que, ha decidido por su propia iniciativa y tras su propio análisis, la Suscripción de SUPER AHORRO $ por  por el importe de $600. Los fondos serán debitados en su cuenta 250-7003549140. Asimismo declara que entiende caraterísticas y riesgos de la inversión, incluyendo la posibilidad de pérdida de capital, y que la misma es adecuada a su perfil de inversión.");
		respuestaSuscribir.setNroCertificado("22");
		respuestaSuscribir.setNroOrden("0130183");

		when(fondoBPrivDAO.suscribir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class))).thenReturn(respuestaSuscribir);

		Segmento segmento = new Segmento();
		segmento.setSelect(false);
		segmento.setDuo(false);
        segmento.setPyme(false);
		segmento.setUniversidad(false);

		Cliente cliente = new Cliente();
		cliente.setNombre("AMELIO RAMIRO");
		cliente.setApellido1("SANCHEZ FERRER");
		cliente.setClienteGold(true);
		cliente.setSegmento(segmento);
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		ConsultaFondoOutEntity consultaFondoOutEntity1 = new ConsultaFondoOutEntity();
		consultaFondoOutEntity1.setAgrupadorSuscripcion("Money Market");
		consultaFondoOutEntity1.setCodigoAgrupador("1");
		consultaFondoOutEntity1.setCodigoFondo("007");
		consultaFondoOutEntity1.setContratoSuscripcion("x");
		consultaFondoOutEntity1.setDescripcion(
				"Fondo de Mercado de Dinero dinámico que invierte en Plazo Fijos, Cauciones, Letras/Notas del BCRA y deuda provincial y corporativa de corto plazo. Plazo de acreditación: inmediato.");
		consultaFondoOutEntity1.setEspecie("BR07");
		consultaFondoOutEntity1.setHabilitarRescate("1");
		consultaFondoOutEntity1.setHabilitarSuscripcion("1");
		consultaFondoOutEntity1.setHabilitarTransferencia("1");
		consultaFondoOutEntity1.setIdMensajeGastos(null);
		consultaFondoOutEntity1.setLimiteMaximoRescate("500000");
		consultaFondoOutEntity1.setLimiteMaximoSuscripcion("999999.99");
		consultaFondoOutEntity1.setLimiteMinimoRescate("600");
		consultaFondoOutEntity1.setLimiteMinimoSuscripcion("1");
		consultaFondoOutEntity1.setLinkCartera("FactSheet_Superahorro$A.pdf");
		consultaFondoOutEntity1.setLinkRegla("3-231.pdf");
		consultaFondoOutEntity1.setMoneda("ARS");
		consultaFondoOutEntity1.setNombreFondo("Super Ahorro $");
		consultaFondoOutEntity1.setOrdenAgrupacion("1");
		consultaFondoOutEntity1.setPlazoEfectivo("24");
		consultaFondoOutEntity1.setTipoBanca("RP");

		List<ConsultaFondoOutEntity> listaConsultaFondoOutEntity = new ArrayList<ConsultaFondoOutEntity>();
		listaConsultaFondoOutEntity.add(consultaFondoOutEntity1);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Credential credential = new Credential();
		credential.setPassword("password");
		credential.setUsuario("usuario");

		when(credentialSecurity.getCredentialBySistema(Matchers.anyString())).thenReturn(credential);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity1);
		Respuesta<SuscripcionOutDTO> respuestaFinalizarSuscribirFondosBPriv = new Respuesta<SuscripcionOutDTO>();
		respuestaFinalizarSuscribirFondosBPriv.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FondoDTO.class)))
				.thenReturn(respuestaFinalizarSuscribirFondosBPriv);
		respuestaFinalizarSuscribirFondosBPriv = fondoBO.finalizarSuscribirFondosBPriv(suscripcionInDTO, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondosBPriv);
		Assert.assertEquals(respuestaFinalizarSuscribirFondosBPriv.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	/**
	 * suscribir Fondos BPriv Fallo DAOException con Reintento test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void finalizarSuscribirFondosBPrivFALLODAOExceptionConReintentoTest() throws DAOException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();

		SuscripcionInDTO suscripcionInDTO = new SuscripcionInDTO();
		suscripcionInDTO.setCodigoFondo("007");
		suscripcionInDTO.setDentroDelPerfil("S-1.6");
		suscripcionInDTO.setImporte(new BigDecimal("600"));
		suscripcionInDTO.setNombreFondo("Super Ahorro $");
		suscripcionInDTO.setNroCuentaBancaPrivada("7003549140");

		// daoOUT
		EjecucionFondoBancaPrivadaOutEntity respuestaSuscribir = new EjecucionFondoBancaPrivadaOutEntity();
		respuestaSuscribir.setDisclaimer(
				"El cliente ASCANELLI SA manifiesta que, ha decidido por su propia iniciativa y tras su propio análisis, la Suscripción de SUPER AHORRO $ por  por el importe de $600. Los fondos serán debitados en su cuenta 250-7003549140. Asimismo declara que entiende caraterísticas y riesgos de la inversión, incluyendo la posibilidad de pérdida de capital, y que la misma es adecuada a su perfil de inversión.");
		respuestaSuscribir.setNroCertificado("22");
		respuestaSuscribir.setNroOrden("0130183");
		when(fondoBPrivDAO.suscribir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class))).thenThrow(new DAOException());
		Segmento segmento = new Segmento();
		segmento.setSelect(false);
		segmento.setDuo(false);
        segmento.setPyme(false);
		segmento.setUniversidad(false);

		Cliente cliente = new Cliente();
		cliente.setNombre("AMELIO RAMIRO");
		cliente.setApellido1("SANCHEZ FERRER");
		cliente.setClienteGold(true);
		cliente.setSegmento(segmento);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		ConsultaFondoOutEntity consultaFondoOutEntity1 = new ConsultaFondoOutEntity();
		consultaFondoOutEntity1.setAgrupadorSuscripcion("Money Market");
		consultaFondoOutEntity1.setCodigoAgrupador("1");
		consultaFondoOutEntity1.setCodigoFondo("007");
		consultaFondoOutEntity1.setContratoSuscripcion("x");
		consultaFondoOutEntity1.setDescripcion(
				"Fondo de Mercado de Dinero dinámico que invierte en Plazo Fijos, Cauciones, Letras/Notas del BCRA y deuda provincial y corporativa de corto plazo. Plazo de acreditación: inmediato.");
		consultaFondoOutEntity1.setEspecie("BR07");
		consultaFondoOutEntity1.setHabilitarRescate("1");
		consultaFondoOutEntity1.setHabilitarSuscripcion("1");
		consultaFondoOutEntity1.setHabilitarTransferencia("1");
		consultaFondoOutEntity1.setIdMensajeGastos(null);
		consultaFondoOutEntity1.setLimiteMaximoRescate("500000");
		consultaFondoOutEntity1.setLimiteMaximoSuscripcion("999999.99");
		consultaFondoOutEntity1.setLimiteMinimoRescate("600");
		consultaFondoOutEntity1.setLimiteMinimoSuscripcion("1");
		consultaFondoOutEntity1.setLinkCartera("FactSheet_Superahorro$A.pdf");
		consultaFondoOutEntity1.setLinkRegla("3-231.pdf");
		consultaFondoOutEntity1.setMoneda("ARS");
		consultaFondoOutEntity1.setNombreFondo("Super Ahorro $");
		consultaFondoOutEntity1.setOrdenAgrupacion("1");
		consultaFondoOutEntity1.setPlazoEfectivo("24");
		consultaFondoOutEntity1.setTipoBanca("RP");

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");

		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Credential credential = new Credential();
		credential.setPassword("password");
		credential.setUsuario("usuario");

		when(credentialSecurity.getCredentialBySistema(Matchers.anyString())).thenReturn(credential);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity1);
		Respuesta<SuscripcionOutDTO> responseFactoryError = new Respuesta<SuscripcionOutDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(SuscripcionOutDTO.class, "mensaje",
				TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_CON_REINTENTO.toString())).thenReturn(responseFactoryError);

		Respuesta<SuscripcionOutDTO> respuestaFinalizarSuscribirFondosBPriv = fondoBO
				.finalizarSuscribirFondosBPriv(suscripcionInDTO, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondosBPriv);
		Assert.assertEquals(respuestaFinalizarSuscribirFondosBPriv, responseFactoryError);
	}

	/**
	 * suscribir Fondos BPriv Fallo DAOException Sin Reintento test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void finalizarSuscribirFondosBPrivFALLODAOExceptionSinReintentoTest() throws DAOException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		SuscripcionInDTO suscripcionInDTO = new SuscripcionInDTO();
		suscripcionInDTO.setCodigoFondo("007");
		suscripcionInDTO.setDentroDelPerfil("S-1.6");
		suscripcionInDTO.setImporte(new BigDecimal("600"));
		suscripcionInDTO.setNombreFondo("Super Ahorro $");
		suscripcionInDTO.setNroCuentaBancaPrivada("7003549140");

		// daoOUT
		EjecucionFondoBancaPrivadaOutEntity respuestaSuscribir = new EjecucionFondoBancaPrivadaOutEntity();
		respuestaSuscribir.setDisclaimer(
				"El cliente ASCANELLI SA manifiesta que, ha decidido por su propia iniciativa y tras su propio análisis, la Suscripción de SUPER AHORRO $ por  por el importe de $600. Los fondos serán debitados en su cuenta 250-7003549140. Asimismo declara que entiende caraterísticas y riesgos de la inversión, incluyendo la posibilidad de pérdida de capital, y que la misma es adecuada a su perfil de inversión.");
		respuestaSuscribir.setNroCertificado("22");
		respuestaSuscribir.setNroOrden("0130183");
		when(fondoBPrivDAO.suscribir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class))).thenThrow(new DAOException());
		Segmento segmento = new Segmento();
		segmento.setSelect(false);
		segmento.setDuo(false);
        segmento.setPyme(false);
		segmento.setUniversidad(false);

		Cliente cliente = new Cliente();
		cliente.setNombre("AMELIO RAMIRO");
		cliente.setApellido1("SANCHEZ FERRER");
		cliente.setClienteGold(true);
		cliente.setSegmento(segmento);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		ConsultaFondoOutEntity consultaFondoOutEntity1 = new ConsultaFondoOutEntity();
		consultaFondoOutEntity1.setAgrupadorSuscripcion("Money Market");
		consultaFondoOutEntity1.setCodigoAgrupador("1");
		consultaFondoOutEntity1.setCodigoFondo("007");
		consultaFondoOutEntity1.setContratoSuscripcion("x");
		consultaFondoOutEntity1.setDescripcion(
				"Fondo de Mercado de Dinero dinámico que invierte en Plazo Fijos, Cauciones, Letras/Notas del BCRA y deuda provincial y corporativa de corto plazo. Plazo de acreditación: inmediato.");
		consultaFondoOutEntity1.setEspecie("BR07");
		consultaFondoOutEntity1.setHabilitarRescate("1");
		consultaFondoOutEntity1.setHabilitarSuscripcion("1");
		consultaFondoOutEntity1.setHabilitarTransferencia("1");
		consultaFondoOutEntity1.setIdMensajeGastos(null);
		consultaFondoOutEntity1.setLimiteMaximoRescate("500000");
		consultaFondoOutEntity1.setLimiteMaximoSuscripcion("999999.99");
		consultaFondoOutEntity1.setLimiteMinimoRescate("600");
		consultaFondoOutEntity1.setLimiteMinimoSuscripcion("1");
		consultaFondoOutEntity1.setLinkCartera("FactSheet_Superahorro$A.pdf");
		consultaFondoOutEntity1.setLinkRegla("3-231.pdf");
		consultaFondoOutEntity1.setMoneda("ARS");
		consultaFondoOutEntity1.setNombreFondo("Super Ahorro $");
		consultaFondoOutEntity1.setOrdenAgrupacion("1");
		consultaFondoOutEntity1.setPlazoEfectivo("24");
		consultaFondoOutEntity1.setTipoBanca("RP");

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");

		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Credential credential = new Credential();
		credential.setPassword("password");
		credential.setUsuario("usuario");

		when(credentialSecurity.getCredentialBySistema(Matchers.anyString())).thenReturn(credential);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity1);
		Respuesta<SuscripcionOutDTO> responseFactoryError = new Respuesta<SuscripcionOutDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(SuscripcionOutDTO.class, "mensaje",
				TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO.toString())).thenReturn(responseFactoryError);

		Respuesta<SuscripcionOutDTO> respuestaFinalizarSuscribirFondosBPriv = fondoBO
				.finalizarSuscribirFondosBPriv(suscripcionInDTO, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondosBPriv);
		Assert.assertEquals(respuestaFinalizarSuscribirFondosBPriv, responseFactoryError);
	}

	/**
	 * suscribir Fondos BPriv Fallo TimeOutException test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void finalizarSuscribirFondosBPrivFALLOTimeOutExceptionTest() throws DAOException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();

		SuscripcionInDTO suscripcionInDTO = new SuscripcionInDTO();
		suscripcionInDTO.setCodigoFondo("007");
		suscripcionInDTO.setDentroDelPerfil("S-1.6");
		suscripcionInDTO.setImporte(new BigDecimal("600"));
		suscripcionInDTO.setNombreFondo("Super Ahorro $");
		suscripcionInDTO.setNroCuentaBancaPrivada("7003549140");

		// daoOUT
		EjecucionFondoBancaPrivadaOutEntity respuestaSuscribir = new EjecucionFondoBancaPrivadaOutEntity();
		respuestaSuscribir.setDisclaimer(
				"El cliente ASCANELLI SA manifiesta que, ha decidido por su propia iniciativa y tras su propio análisis, la Suscripción de SUPER AHORRO $ por  por el importe de $600. Los fondos serán debitados en su cuenta 250-7003549140. Asimismo declara que entiende caraterísticas y riesgos de la inversión, incluyendo la posibilidad de pérdida de capital, y que la misma es adecuada a su perfil de inversión.");
		respuestaSuscribir.setNroCertificado("22");
		respuestaSuscribir.setNroOrden("0130183");
		when(fondoBPrivDAO.suscribir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class))).thenThrow(new TimeOutException(""));
		Segmento segmento = new Segmento();
		segmento.setSelect(false);
		segmento.setDuo(false);
        segmento.setPyme(false);
		segmento.setUniversidad(false);

		Cliente cliente = new Cliente();
		cliente.setNombre("AMELIO RAMIRO");
		cliente.setApellido1("SANCHEZ FERRER");
		cliente.setClienteGold(true);
		cliente.setSegmento(segmento);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		ConsultaFondoOutEntity consultaFondoOutEntity1 = new ConsultaFondoOutEntity();
		consultaFondoOutEntity1.setAgrupadorSuscripcion("Money Market");
		consultaFondoOutEntity1.setCodigoAgrupador("1");
		consultaFondoOutEntity1.setCodigoFondo("007");
		consultaFondoOutEntity1.setContratoSuscripcion("x");
		consultaFondoOutEntity1.setDescripcion(
				"Fondo de Mercado de Dinero dinámico que invierte en Plazo Fijos, Cauciones, Letras/Notas del BCRA y deuda provincial y corporativa de corto plazo. Plazo de acreditación: inmediato.");
		consultaFondoOutEntity1.setEspecie("BR07");
		consultaFondoOutEntity1.setHabilitarRescate("1");
		consultaFondoOutEntity1.setHabilitarSuscripcion("1");
		consultaFondoOutEntity1.setHabilitarTransferencia("1");
		consultaFondoOutEntity1.setIdMensajeGastos(null);
		consultaFondoOutEntity1.setLimiteMaximoRescate("500000");
		consultaFondoOutEntity1.setLimiteMaximoSuscripcion("999999.99");
		consultaFondoOutEntity1.setLimiteMinimoRescate("600");
		consultaFondoOutEntity1.setLimiteMinimoSuscripcion("1");
		consultaFondoOutEntity1.setLinkCartera("FactSheet_Superahorro$A.pdf");
		consultaFondoOutEntity1.setLinkRegla("3-231.pdf");
		consultaFondoOutEntity1.setMoneda("ARS");
		consultaFondoOutEntity1.setNombreFondo("Super Ahorro $");
		consultaFondoOutEntity1.setOrdenAgrupacion("1");
		consultaFondoOutEntity1.setPlazoEfectivo("24");
		consultaFondoOutEntity1.setTipoBanca("RP");

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");

		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Credential credential = new Credential();
		credential.setPassword("password");
		credential.setUsuario("usuario");

		when(credentialSecurity.getCredentialBySistema(Matchers.anyString())).thenReturn(credential);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity1);
		Respuesta<SuscripcionOutDTO> responseFactoryError = new Respuesta<SuscripcionOutDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(SuscripcionOutDTO.class, "mensaje",
				TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO.toString())).thenReturn(responseFactoryError);

		Respuesta<SuscripcionOutDTO> respuestaFinalizarSuscribirFondosBPriv = fondoBO
				.finalizarSuscribirFondosBPriv(suscripcionInDTO, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondosBPriv);
		Assert.assertEquals(respuestaFinalizarSuscribirFondosBPriv, responseFactoryError);
	}

	/**
	 * suscribir Fondos BPriv Fallo ImporteLimiteException test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void finalizarSuscribirFondosBPrivFALLOImporteLimiteExceptionTest() throws DAOException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();

		SuscripcionInDTO suscripcionInDTO = new SuscripcionInDTO();
		suscripcionInDTO.setCodigoFondo("007");
		suscripcionInDTO.setDentroDelPerfil("S-1.6");
		suscripcionInDTO.setImporte(new BigDecimal("600"));
		suscripcionInDTO.setNombreFondo("Super Ahorro $");
		suscripcionInDTO.setNroCuentaBancaPrivada("7003549140");

		// daoOUT
		EjecucionFondoBancaPrivadaOutEntity respuestaSuscribir = new EjecucionFondoBancaPrivadaOutEntity();
		respuestaSuscribir.setDisclaimer(
				"El cliente ASCANELLI SA manifiesta que, ha decidido por su propia iniciativa y tras su propio análisis, la Suscripción de SUPER AHORRO $ por  por el importe de $600. Los fondos serán debitados en su cuenta 250-7003549140. Asimismo declara que entiende caraterísticas y riesgos de la inversión, incluyendo la posibilidad de pérdida de capital, y que la misma es adecuada a su perfil de inversión.");
		respuestaSuscribir.setNroCertificado("22");
		respuestaSuscribir.setNroOrden("0130183");
		when(fondoBPrivDAO.suscribir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new ImporteLimiteException(""));
		Segmento segmento = new Segmento();
		segmento.setSelect(false);
		segmento.setDuo(false);
        segmento.setPyme(false);
		segmento.setUniversidad(false);

		Cliente cliente = new Cliente();
		cliente.setNombre("AMELIO RAMIRO");
		cliente.setApellido1("SANCHEZ FERRER");
		cliente.setClienteGold(true);
		cliente.setSegmento(segmento);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		ConsultaFondoOutEntity consultaFondoOutEntity1 = new ConsultaFondoOutEntity();
		consultaFondoOutEntity1.setAgrupadorSuscripcion("Money Market");
		consultaFondoOutEntity1.setCodigoAgrupador("1");
		consultaFondoOutEntity1.setCodigoFondo("007");
		consultaFondoOutEntity1.setContratoSuscripcion("x");
		consultaFondoOutEntity1.setDescripcion(
				"Fondo de Mercado de Dinero dinámico que invierte en Plazo Fijos, Cauciones, Letras/Notas del BCRA y deuda provincial y corporativa de corto plazo. Plazo de acreditación: inmediato.");
		consultaFondoOutEntity1.setEspecie("BR07");
		consultaFondoOutEntity1.setHabilitarRescate("1");
		consultaFondoOutEntity1.setHabilitarSuscripcion("1");
		consultaFondoOutEntity1.setHabilitarTransferencia("1");
		consultaFondoOutEntity1.setIdMensajeGastos(null);
		consultaFondoOutEntity1.setLimiteMaximoRescate("500000");
		consultaFondoOutEntity1.setLimiteMaximoSuscripcion("999999.99");
		consultaFondoOutEntity1.setLimiteMinimoRescate("600");
		consultaFondoOutEntity1.setLimiteMinimoSuscripcion("1");
		consultaFondoOutEntity1.setLinkCartera("FactSheet_Superahorro$A.pdf");
		consultaFondoOutEntity1.setLinkRegla("3-231.pdf");
		consultaFondoOutEntity1.setMoneda("ARS");
		consultaFondoOutEntity1.setNombreFondo("Super Ahorro $");
		consultaFondoOutEntity1.setOrdenAgrupacion("1");
		consultaFondoOutEntity1.setPlazoEfectivo("24");
		consultaFondoOutEntity1.setTipoBanca("RP");

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");

		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Credential credential = new Credential();
		credential.setPassword("password");
		credential.setUsuario("usuario");

		when(credentialSecurity.getCredentialBySistema(Matchers.anyString())).thenReturn(credential);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity1);
		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(),
				Matchers.eq(TipoError.ERROR_LIMITE_MINIMO), Matchers.anyString())).thenReturn(responseFactoryError);
		Respuesta<SuscripcionOutDTO> respuestaFinalizarSuscribirFondosBPriv = fondoBO
				.finalizarSuscribirFondosBPriv(suscripcionInDTO, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondosBPriv);
		Assert.assertEquals(respuestaFinalizarSuscribirFondosBPriv, responseFactoryError);
	}

	/**
	 * suscribir Fondos BPriv Fallo FueraDeHorarioException test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void finalizarSuscribirFondosBPrivFALLOFueraDeHorarioExceptionTest() throws DAOException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();

		SuscripcionInDTO suscripcionInDTO = new SuscripcionInDTO();
		suscripcionInDTO.setCodigoFondo("007");
		suscripcionInDTO.setDentroDelPerfil("S-1.6");
		suscripcionInDTO.setImporte(new BigDecimal("600"));
		suscripcionInDTO.setNombreFondo("Super Ahorro $");
		suscripcionInDTO.setNroCuentaBancaPrivada("7003549140");

		// daoOUT
		EjecucionFondoBancaPrivadaOutEntity respuestaSuscribir = new EjecucionFondoBancaPrivadaOutEntity();
		respuestaSuscribir.setDisclaimer(
				"El cliente ASCANELLI SA manifiesta que, ha decidido por su propia iniciativa y tras su propio análisis, la Suscripción de SUPER AHORRO $ por  por el importe de $600. Los fondos serán debitados en su cuenta 250-7003549140. Asimismo declara que entiende caraterísticas y riesgos de la inversión, incluyendo la posibilidad de pérdida de capital, y que la misma es adecuada a su perfil de inversión.");
		respuestaSuscribir.setNroCertificado("22");
		respuestaSuscribir.setNroOrden("0130183");
		when(fondoBPrivDAO.suscribir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new FueraDeHorarioException());
		Segmento segmento = new Segmento();
		segmento.setSelect(false);
		segmento.setDuo(false);
        segmento.setPyme(false);
		segmento.setUniversidad(false);

		Cliente cliente = new Cliente();
		cliente.setNombre("AMELIO RAMIRO");
		cliente.setApellido1("SANCHEZ FERRER");
		cliente.setClienteGold(true);
		cliente.setSegmento(segmento);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		ConsultaFondoOutEntity consultaFondoOutEntity1 = new ConsultaFondoOutEntity();
		consultaFondoOutEntity1.setAgrupadorSuscripcion("Money Market");
		consultaFondoOutEntity1.setCodigoAgrupador("1");
		consultaFondoOutEntity1.setCodigoFondo("007");
		consultaFondoOutEntity1.setContratoSuscripcion("x");
		consultaFondoOutEntity1.setDescripcion(
				"Fondo de Mercado de Dinero dinámico que invierte en Plazo Fijos, Cauciones, Letras/Notas del BCRA y deuda provincial y corporativa de corto plazo. Plazo de acreditación: inmediato.");
		consultaFondoOutEntity1.setEspecie("BR07");
		consultaFondoOutEntity1.setHabilitarRescate("1");
		consultaFondoOutEntity1.setHabilitarSuscripcion("1");
		consultaFondoOutEntity1.setHabilitarTransferencia("1");
		consultaFondoOutEntity1.setIdMensajeGastos(null);
		consultaFondoOutEntity1.setLimiteMaximoRescate("500000");
		consultaFondoOutEntity1.setLimiteMaximoSuscripcion("999999.99");
		consultaFondoOutEntity1.setLimiteMinimoRescate("600");
		consultaFondoOutEntity1.setLimiteMinimoSuscripcion("1");
		consultaFondoOutEntity1.setLinkCartera("FactSheet_Superahorro$A.pdf");
		consultaFondoOutEntity1.setLinkRegla("3-231.pdf");
		consultaFondoOutEntity1.setMoneda("ARS");
		consultaFondoOutEntity1.setNombreFondo("Super Ahorro $");
		consultaFondoOutEntity1.setOrdenAgrupacion("1");
		consultaFondoOutEntity1.setPlazoEfectivo("24");
		consultaFondoOutEntity1.setTipoBanca("RP");

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");

		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Credential credential = new Credential();
		credential.setPassword("password");
		credential.setUsuario("usuario");

		when(credentialSecurity.getCredentialBySistema(Matchers.anyString())).thenReturn(credential);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity1);
		Respuesta<SuscripcionOutDTO> responseFactoryError = new Respuesta<SuscripcionOutDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(SuscripcionOutDTO.class), Matchers.anyString(),
				Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(responseFactoryError);
		Respuesta<SuscripcionOutDTO> respuestaFinalizarSuscribirFondosBPriv = fondoBO
				.finalizarSuscribirFondosBPriv(suscripcionInDTO, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondosBPriv);
		Assert.assertEquals(respuestaFinalizarSuscribirFondosBPriv, responseFactoryError);
	}

	/**
	 * suscribir Fondos BPriv Fallo SaldoInsuficienteException test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void finalizarSuscribirFondosBPrivFALLOSaldoInsuficienteExceptionTest() throws DAOException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();

		SuscripcionInDTO suscripcionInDTO = new SuscripcionInDTO();
		suscripcionInDTO.setCodigoFondo("007");
		suscripcionInDTO.setDentroDelPerfil("S-1.6");
		suscripcionInDTO.setImporte(new BigDecimal("600"));
		suscripcionInDTO.setNombreFondo("Super Ahorro $");
		suscripcionInDTO.setNroCuentaBancaPrivada("7003549140");

		// daoOUT
		EjecucionFondoBancaPrivadaOutEntity respuestaSuscribir = new EjecucionFondoBancaPrivadaOutEntity();
		respuestaSuscribir.setDisclaimer(
				"El cliente ASCANELLI SA manifiesta que, ha decidido por su propia iniciativa y tras su propio análisis, la Suscripción de SUPER AHORRO $ por  por el importe de $600. Los fondos serán debitados en su cuenta 250-7003549140. Asimismo declara que entiende caraterísticas y riesgos de la inversión, incluyendo la posibilidad de pérdida de capital, y que la misma es adecuada a su perfil de inversión.");
		respuestaSuscribir.setNroCertificado("22");
		respuestaSuscribir.setNroOrden("0130183");
		when(fondoBPrivDAO.suscribir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new SaldoInsuficienteException());
		Segmento segmento = new Segmento();
		segmento.setSelect(false);
		segmento.setDuo(false);
        segmento.setPyme(false);
		segmento.setUniversidad(false);

		Cliente cliente = new Cliente();
		cliente.setNombre("AMELIO RAMIRO");
		cliente.setApellido1("SANCHEZ FERRER");
		cliente.setClienteGold(true);
		cliente.setSegmento(segmento);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		ConsultaFondoOutEntity consultaFondoOutEntity1 = new ConsultaFondoOutEntity();
		consultaFondoOutEntity1.setAgrupadorSuscripcion("Money Market");
		consultaFondoOutEntity1.setCodigoAgrupador("1");
		consultaFondoOutEntity1.setCodigoFondo("007");
		consultaFondoOutEntity1.setContratoSuscripcion("x");
		consultaFondoOutEntity1.setDescripcion(
				"Fondo de Mercado de Dinero dinámico que invierte en Plazo Fijos, Cauciones, Letras/Notas del BCRA y deuda provincial y corporativa de corto plazo. Plazo de acreditación: inmediato.");
		consultaFondoOutEntity1.setEspecie("BR07");
		consultaFondoOutEntity1.setHabilitarRescate("1");
		consultaFondoOutEntity1.setHabilitarSuscripcion("1");
		consultaFondoOutEntity1.setHabilitarTransferencia("1");
		consultaFondoOutEntity1.setIdMensajeGastos(null);
		consultaFondoOutEntity1.setLimiteMaximoRescate("500000");
		consultaFondoOutEntity1.setLimiteMaximoSuscripcion("999999.99");
		consultaFondoOutEntity1.setLimiteMinimoRescate("600");
		consultaFondoOutEntity1.setLimiteMinimoSuscripcion("1");
		consultaFondoOutEntity1.setLinkCartera("FactSheet_Superahorro$A.pdf");
		consultaFondoOutEntity1.setLinkRegla("3-231.pdf");
		consultaFondoOutEntity1.setMoneda("ARS");
		consultaFondoOutEntity1.setNombreFondo("Super Ahorro $");
		consultaFondoOutEntity1.setOrdenAgrupacion("1");
		consultaFondoOutEntity1.setPlazoEfectivo("24");
		consultaFondoOutEntity1.setTipoBanca("RP");

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");

		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Credential credential = new Credential();
		credential.setPassword("password");
		credential.setUsuario("usuario");

		when(credentialSecurity.getCredentialBySistema(Matchers.anyString())).thenReturn(credential);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity1);
		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SALDO_INSUFICIENTE,
				CodigoMensajeConstantes.FINALIZAR_SUSCRIPCION_SALDO_INSUFICIENTE)).thenReturn(responseFactoryError);
		Respuesta<SuscripcionOutDTO> respuestaFinalizarSuscribirFondosBPriv = fondoBO
				.finalizarSuscribirFondosBPriv(suscripcionInDTO, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondosBPriv);
		Assert.assertEquals(respuestaFinalizarSuscribirFondosBPriv, responseFactoryError);
	}

	/**
	 * Simular suscripcion B priv legales OK test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void simularSuscripcionBPrivLegalesOKTest() throws DAOException, SQLException {
		SimulacionInDTO requestDTO = new SimulacionInDTO();
		requestDTO.setCodigoFondo("082");
		requestDTO.setNroCuentaBancaPrivada("7003523508");
		Cliente cliente = new Cliente();
		SimulacionFondoBancaPrivadaOutEntity suscripcionBPrivOut = new SimulacionFondoBancaPrivadaOutEntity();
		suscripcionBPrivOut.setDentroDelPerfil("S-89.3");
		Mockito.when(fondoBPrivDAO.simularSuscripcionBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class)))
				.thenReturn(suscripcionBPrivOut);
		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);
		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setLinkRegla("x");
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);
		Credential credential = new Credential();
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.anyString())).thenReturn(credential);
		Respuesta<SimularSuscripcionOutDTO> respuesta = new Respuesta<SimularSuscripcionOutDTO>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(SimularSuscripcionOutDTO.class))).thenReturn(respuesta);
		respuesta = fondoBO.simularSuscripcionBPriv(requestDTO, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	/**
	 * Simular suscripcion B priv legales error test.
	 *
	 * @throws DAOException the DAO exception
	 * @throws SQLException the SQL exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void simularSuscripcionBPrivLegalesErrorTest() throws DAOException, SQLException {
		SimulacionInDTO requestDTO = new SimulacionInDTO();
		requestDTO.setCodigoFondo("082");
		requestDTO.setNroCuentaBancaPrivada("7003523508");
		Cliente cliente = new Cliente();
		SimulacionFondoBancaPrivadaOutEntity suscripcionBPrivOut = new SimulacionFondoBancaPrivadaOutEntity();
		suscripcionBPrivOut.setDentroDelPerfil("S-89.3");
		Mockito.when(fondoBPrivDAO.simularSuscripcionBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class)))
				.thenReturn(suscripcionBPrivOut);
		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);
		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setLinkRegla("x");
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);
		Credential credential = new Credential();
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.anyString())).thenReturn(credential);
		@SuppressWarnings("rawtypes")
		Respuesta respuesta = new Respuesta<SimularSuscripcionOutDTO>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuesta);
		respuesta = fondoBO.simularSuscripcionBPriv(requestDTO, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	/**
	 * suscribir Fondos OK test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void finalizarSuscribirFondosOKTest() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FondoDTO dtoRequest = new FondoDTO();
		dtoRequest.setCodigoFondo("002");
		dtoRequest.setCuentaTitulo("032103/7");
		dtoRequest.setImporte("600,00");
		dtoRequest.setMoneda("$");
		dtoRequest.setNombreFondo("Superfondo Renta $");
		dtoRequest.setNumeroCtaDeb("061236/8");
		dtoRequest.setSucursalCtaDeb("000");
		dtoRequest.setTipoCtaDeb("CU");
		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity respuestaDAO = new SuscripcionFondoOutEntity();
		respuestaDAO.setCodigoRetornoExtendido("00000000");
		respuestaDAO.setDescripcionMoneda("PESOS");
		respuestaDAO.setDiasCarencia(1);
		respuestaDAO.setEstadoActual("ACTIVA      ");
		respuestaDAO.setHeaderTrama(
				"200000000000P04HTML0009900010302MNTR40  ********00473939000000052017012715213300000000IBF26304000000000000SUSFCI____1500000            02239740    00        00 000000000201701271525540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		respuestaDAO.setNombreFondo("SUPERFONDO RENTA $ C");
		respuestaDAO.setMotivoActual("COBRADA    ");
		respuestaDAO.setImporteNeto(new Long("600"));
		respuestaDAO.setNumeroCertificado(new Long("200"));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(fondoDao.comprobanteSuscripcionFondo(Matchers.any(Cliente.class),
				Matchers.any(ComprobanteSuscripcionFondoInEntity.class))).thenReturn(respuestaDAO);
		Respuesta<FondoDTO> respuestaFinalizarSuscribirFondos = new Respuesta<FondoDTO>();
		respuestaFinalizarSuscribirFondos.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FondoDTO.class)))
				.thenReturn(respuestaFinalizarSuscribirFondos);
		respuestaFinalizarSuscribirFondos = fondoBO.finalizarSuscribirFondos(dtoRequest, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondos);
		Assert.assertEquals(respuestaFinalizarSuscribirFondos.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	/**
	 * suscribir Fondos FALLO DAOException con Reintento test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void finalizarSuscribirFondosFALLODAOExceptionConReintentoTest() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FondoDTO dtoRequest = new FondoDTO();
		dtoRequest.setCodigoFondo("002");
		dtoRequest.setCuentaTitulo("032103/7");
		dtoRequest.setImporte("600,00");
		dtoRequest.setMoneda("$");
		dtoRequest.setNombreFondo("Superfondo Renta $");
		dtoRequest.setNumeroCtaDeb("061236/8");
		dtoRequest.setSucursalCtaDeb("000");
		dtoRequest.setTipoCtaDeb("CU");
		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity respuestaDAO = new SuscripcionFondoOutEntity();
		respuestaDAO.setCodigoRetornoExtendido("00000000");
		respuestaDAO.setDescripcionMoneda("PESOS");
		respuestaDAO.setDiasCarencia(1);
		respuestaDAO.setEstadoActual("ACTIVA      ");
		respuestaDAO.setHeaderTrama(
				"200000000000P04HTML0009900010302MNTR40  ********00473939000000052017012715213300000000IBF26304000000000000SUSFCI____1500000            02239740    00        00 000000000201701271525540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		respuestaDAO.setNombreFondo("SUPERFONDO RENTA $ C");
		respuestaDAO.setMotivoActual("COBRADA    ");
		respuestaDAO.setImporteNeto(new Long("600"));
		respuestaDAO.setNumeroCertificado(new Long("200"));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(fondoDao.comprobanteSuscripcionFondo(Matchers.any(Cliente.class),
				Matchers.any(ComprobanteSuscripcionFondoInEntity.class))).thenThrow(new DAOException());

		Respuesta<FondoDTO> responseFactoryError = new Respuesta<FondoDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(FondoDTO.class, "mensaje",
				TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_CON_REINTENTO.toString())).thenReturn(responseFactoryError);

		Respuesta<FondoDTO> respuestaFinalizarSuscribirFondos = fondoBO.finalizarSuscribirFondos(dtoRequest, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondos);
		Assert.assertEquals(respuestaFinalizarSuscribirFondos, responseFactoryError);
	}

	/**
	 * suscribir Fondos FALLO DAOException con Reintento test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void finalizarSuscribirFondosFALLODAOExceptionSinReintentoTest() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		FondoDTO dtoRequest = new FondoDTO();
		dtoRequest.setCodigoFondo("002");
		dtoRequest.setCuentaTitulo("032103/7");
		dtoRequest.setImporte("600,00");
		dtoRequest.setMoneda("$");
		dtoRequest.setNombreFondo("Superfondo Renta $");
		dtoRequest.setNumeroCtaDeb("061236/8");
		dtoRequest.setSucursalCtaDeb("000");
		dtoRequest.setTipoCtaDeb("CU");
		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity respuestaDAO = new SuscripcionFondoOutEntity();
		respuestaDAO.setCodigoRetornoExtendido("00000000");
		respuestaDAO.setDescripcionMoneda("PESOS");
		respuestaDAO.setDiasCarencia(1);
		respuestaDAO.setEstadoActual("ACTIVA      ");
		respuestaDAO.setHeaderTrama(
				"200000000000P04HTML0009900010302MNTR40  ********00473939000000052017012715213300000000IBF26304000000000000SUSFCI____1500000            02239740    00        00 000000000201701271525540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		respuestaDAO.setNombreFondo("SUPERFONDO RENTA $ C");
		respuestaDAO.setMotivoActual("COBRADA    ");
		respuestaDAO.setImporteNeto(new Long("600"));
		respuestaDAO.setNumeroCertificado(new Long("200"));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(fondoDao.comprobanteSuscripcionFondo(Matchers.any(Cliente.class),
				Matchers.any(ComprobanteSuscripcionFondoInEntity.class))).thenThrow(new DAOException());

		Respuesta<FondoDTO> responseFactoryError = new Respuesta<FondoDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(FondoDTO.class, "mensaje",
				TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO.toString())).thenReturn(responseFactoryError);

		Respuesta<FondoDTO> respuestaFinalizarSuscribirFondos = fondoBO.finalizarSuscribirFondos(dtoRequest, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondos);
		Assert.assertEquals(respuestaFinalizarSuscribirFondos, responseFactoryError);
	}

	/**
	 * suscribir Fondos FALLO TimeOutException test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void finalizarSuscribirFondosFALLOTimeOutExceptionTest() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FondoDTO dtoRequest = new FondoDTO();
		dtoRequest.setCodigoFondo("002");
		dtoRequest.setCuentaTitulo("032103/7");
		dtoRequest.setImporte("600,00");
		dtoRequest.setMoneda("$");
		dtoRequest.setNombreFondo("Superfondo Renta $");
		dtoRequest.setNumeroCtaDeb("061236/8");
		dtoRequest.setSucursalCtaDeb("000");
		dtoRequest.setTipoCtaDeb("CU");
		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity respuestaDAO = new SuscripcionFondoOutEntity();
		respuestaDAO.setCodigoRetornoExtendido("00000000");
		respuestaDAO.setDescripcionMoneda("PESOS");
		respuestaDAO.setDiasCarencia(1);
		respuestaDAO.setEstadoActual("ACTIVA      ");
		respuestaDAO.setHeaderTrama(
				"200000000000P04HTML0009900010302MNTR40  ********00473939000000052017012715213300000000IBF26304000000000000SUSFCI____1500000            02239740    00        00 000000000201701271525540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		respuestaDAO.setNombreFondo("SUPERFONDO RENTA $ C");
		respuestaDAO.setMotivoActual("COBRADA    ");
		respuestaDAO.setImporteNeto(new Long("600"));
		respuestaDAO.setNumeroCertificado(new Long("200"));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(fondoDao.comprobanteSuscripcionFondo(Matchers.any(Cliente.class),
				Matchers.any(ComprobanteSuscripcionFondoInEntity.class))).thenThrow(new TimeOutException(""));

		Respuesta<FondoDTO> responseFactoryError = new Respuesta<FondoDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(FondoDTO.class, "mensaje",
				TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO.toString())).thenReturn(responseFactoryError);

		Respuesta<FondoDTO> respuestaFinalizarSuscribirFondos = fondoBO.finalizarSuscribirFondos(dtoRequest, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondos);
		Assert.assertEquals(respuestaFinalizarSuscribirFondos, responseFactoryError);

	}

	/**
	 * suscribir Fondos FALLO FueraDeHorarioException test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void finalizarSuscribirFondosFALLOFueraDeHorarioExceptionTest() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FondoDTO dtoRequest = new FondoDTO();
		dtoRequest.setCodigoFondo("002");
		dtoRequest.setCuentaTitulo("032103/7");
		dtoRequest.setImporte("600,00");
		dtoRequest.setMoneda("$");
		dtoRequest.setNombreFondo("Superfondo Renta $");
		dtoRequest.setNumeroCtaDeb("061236/8");
		dtoRequest.setSucursalCtaDeb("000");
		dtoRequest.setTipoCtaDeb("CU");
		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity respuestaDAO = new SuscripcionFondoOutEntity();
		respuestaDAO.setCodigoRetornoExtendido("00000000");
		respuestaDAO.setDescripcionMoneda("PESOS");
		respuestaDAO.setDiasCarencia(1);
		respuestaDAO.setEstadoActual("ACTIVA      ");
		respuestaDAO.setHeaderTrama(
				"200000000000P04HTML0009900010302MNTR40  ********00473939000000052017012715213300000000IBF26304000000000000SUSFCI____1500000            02239740    00        00 000000000201701271525540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		respuestaDAO.setNombreFondo("SUPERFONDO RENTA $ C");
		respuestaDAO.setMotivoActual("COBRADA    ");
		respuestaDAO.setImporteNeto(new Long("600"));
		respuestaDAO.setNumeroCertificado(new Long("200"));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(fondoDao.comprobanteSuscripcionFondo(Matchers.any(Cliente.class),
				Matchers.any(ComprobanteSuscripcionFondoInEntity.class))).thenThrow(new FueraDeHorarioException());

		Respuesta<SuscripcionOutDTO> responseFactoryError = new Respuesta<SuscripcionOutDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(SuscripcionOutDTO.class), Matchers.anyString(),
				Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(responseFactoryError);
		Respuesta<FondoDTO> respuestaFinalizarSuscribirFondos = fondoBO.finalizarSuscribirFondos(dtoRequest, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondos);
		Assert.assertEquals(respuestaFinalizarSuscribirFondos, responseFactoryError);

	}

	/**
	 * suscribir Fondos FALLO SaldoInsuficienteException test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void finalizarSuscribirFondosFALLOSaldoInsuficienteExceptionTest() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FondoDTO dtoRequest = new FondoDTO();
		dtoRequest.setCodigoFondo("002");
		dtoRequest.setCuentaTitulo("032103/7");
		dtoRequest.setImporte("600,00");
		dtoRequest.setMoneda("$");
		dtoRequest.setNombreFondo("Superfondo Renta $");
		dtoRequest.setNumeroCtaDeb("061236/8");
		dtoRequest.setSucursalCtaDeb("000");
		dtoRequest.setTipoCtaDeb("CU");
		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity respuestaDAO = new SuscripcionFondoOutEntity();
		respuestaDAO.setCodigoRetornoExtendido("00000000");
		respuestaDAO.setDescripcionMoneda("PESOS");
		respuestaDAO.setDiasCarencia(1);
		respuestaDAO.setEstadoActual("ACTIVA      ");
		respuestaDAO.setHeaderTrama(
				"200000000000P04HTML0009900010302MNTR40  ********00473939000000052017012715213300000000IBF26304000000000000SUSFCI____1500000            02239740    00        00 000000000201701271525540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		respuestaDAO.setNombreFondo("SUPERFONDO RENTA $ C");
		respuestaDAO.setMotivoActual("COBRADA    ");
		respuestaDAO.setImporteNeto(new Long("600"));
		respuestaDAO.setNumeroCertificado(new Long("200"));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(fondoDao.comprobanteSuscripcionFondo(Matchers.any(Cliente.class),
				Matchers.any(ComprobanteSuscripcionFondoInEntity.class))).thenThrow(new SaldoInsuficienteException());

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SALDO_INSUFICIENTE,
				CodigoMensajeConstantes.FINALIZAR_SUSCRIPCION_SALDO_INSUFICIENTE)).thenReturn(responseFactoryError);

		Respuesta<FondoDTO> respuestaFinalizarSuscribirFondos = fondoBO.finalizarSuscribirFondos(dtoRequest, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondos);
		Assert.assertEquals(respuestaFinalizarSuscribirFondos, responseFactoryError);

	}

	/**
	 * suscribir Fondos FALLO CuentaSinOperarException con reintentos test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void finalizarSuscribirFondosFALLOCuentaSinOperarExceptionConReintentoTest() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FondoDTO dtoRequest = new FondoDTO();
		dtoRequest.setCodigoFondo("002");
		dtoRequest.setCuentaTitulo("032103/7");
		dtoRequest.setImporte("600,00");
		dtoRequest.setMoneda("$");
		dtoRequest.setNombreFondo("Superfondo Renta $");
		dtoRequest.setNumeroCtaDeb("061236/8");
		dtoRequest.setSucursalCtaDeb("000");
		dtoRequest.setTipoCtaDeb("CU");
		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity respuestaDAO = new SuscripcionFondoOutEntity();
		respuestaDAO.setCodigoRetornoExtendido("00000000");
		respuestaDAO.setDescripcionMoneda("PESOS");
		respuestaDAO.setDiasCarencia(1);
		respuestaDAO.setEstadoActual("ACTIVA      ");
		respuestaDAO.setHeaderTrama(
				"200000000000P04HTML0009900010302MNTR40  ********00473939000000052017012715213300000000IBF26304000000000000SUSFCI____1500000            02239740    00        00 000000000201701271525540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		respuestaDAO.setNombreFondo("SUPERFONDO RENTA $ C");
		respuestaDAO.setMotivoActual("COBRADA    ");
		respuestaDAO.setImporteNeto(new Long("600"));
		respuestaDAO.setNumeroCertificado(new Long("200"));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(fondoDao.comprobanteSuscripcionFondo(Matchers.any(Cliente.class),
				Matchers.any(ComprobanteSuscripcionFondoInEntity.class))).thenThrow(new CuentaSinOperarException());

		Respuesta<FondoDTO> responseFactoryError = new Respuesta<FondoDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(FondoDTO.class, "mensaje",
				TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_CON_REINTENTO.toString())).thenReturn(responseFactoryError);

		Respuesta<FondoDTO> respuestaFinalizarSuscribirFondos = fondoBO.finalizarSuscribirFondos(dtoRequest, cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondos);
		Assert.assertEquals(respuestaFinalizarSuscribirFondos, responseFactoryError);

	}

	/**
	 * suscribir Fondos FALLO CuentaSinOperarException Sin reintentos test.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void finalizarSuscribirFondosFALLOCuentaSinOperarExceptionSinReintentoTest() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();

		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity respuestaDAO = new SuscripcionFondoOutEntity();
		respuestaDAO.setCodigoRetornoExtendido("00000000");
		respuestaDAO.setDescripcionMoneda("PESOS");
		respuestaDAO.setDiasCarencia(1);
		respuestaDAO.setEstadoActual("ACTIVA      ");
		respuestaDAO.setHeaderTrama(
				"200000000000P04HTML0009900010302MNTR40  ********00473939000000052017012715213300000000IBF26304000000000000SUSFCI____1500000            02239740    00        00 000000000201701271525540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		respuestaDAO.setNombreFondo("SUPERFONDO RENTA $ C");
		respuestaDAO.setMotivoActual("COBRADA    ");
		respuestaDAO.setImporteNeto(new Long("600"));
		respuestaDAO.setNumeroCertificado(new Long("200"));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(fondoDao.comprobanteSuscripcionFondo(Matchers.any(Cliente.class),
				Matchers.any(ComprobanteSuscripcionFondoInEntity.class))).thenThrow(new CuentaSinOperarException());

		Respuesta<FondoDTO> responseFactoryError = new Respuesta<FondoDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(FondoDTO.class, "mensaje",
				TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO.toString())).thenReturn(responseFactoryError);

		Respuesta<FondoDTO> respuestaFinalizarSuscribirFondos = fondoBO.finalizarSuscribirFondos(dtoRequestPesos,
				cliente);
		Assert.assertNotNull(respuestaFinalizarSuscribirFondos);
		Assert.assertEquals(respuestaFinalizarSuscribirFondos, responseFactoryError);

	}

	/**
	 * Simular suscripcion fuera de horario exception.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void simularSuscripcionFueraDeHorarioException() throws DAOException {
		FondoDTO dtoRequest = new FondoDTO();
		dtoRequest.setCodigoFondo("082");
		dtoRequest.setCuentaTitulo("014074/2");
		dtoRequest.setImporte("234.567,00");
		dtoRequest.setMoneda("$");
		dtoRequest.setNumeroCtaDeb("063880/1");
		dtoRequest.setTipoCtaDeb("CU");
		Cliente cliente = mock(Cliente.class);

		Respuesta<FondoDTO> respuestaSimularSuscripcion = new Respuesta<FondoDTO>();
		respuestaSimularSuscripcion.setEstadoRespuesta(EstadoRespuesta.WARNING);
		ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
		imr.setTipoError(TipoError.FUERADEHORARIO.getDescripcion());
		respuestaSimularSuscripcion.add(imr);

		Mockito.when(fondoDao.suscribir(Matchers.any(SuscripcionFondoInEntity.class)))
				.thenThrow(new FueraDeHorarioException());

		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(FondoDTO.class), Matchers.anyString(),
				Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaSimularSuscripcion);
		
		Respuesta<FondoDTO> rtaSimulacion = fondoBO.simularSuscripcion(dtoRequest, cliente);

		Assert.assertNotNull(rtaSimulacion);
		Assert.assertEquals(respuestaSimularSuscripcion, rtaSimulacion);

	}

	/**
	 * Simular suscripcion importe mayor al maximo exception.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void simularSuscripcionImporteMayorAlMaximoException() throws DAOException {

		Cliente cliente = mock(Cliente.class);

		Respuesta<Object> respuestaSimularSuscripcion = new Respuesta<Object>();
		respuestaSimularSuscripcion.setEstadoRespuesta(EstadoRespuesta.WARNING);
		ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
		imr.setTipoError(TipoError.ERROR_LIMITE_MAXIMO.getDescripcion());
		respuestaSimularSuscripcion.add(imr);

		Mockito.when(fondoDao.suscribir(Matchers.any(SuscripcionFondoInEntity.class)))
				.thenThrow(new ImporteMayorAlMaximoException());
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(String.class),
				Matchers.eq(TipoError.ERROR_LIMITE_MAXIMO), Matchers.any(String.class)))
				.thenReturn(respuestaSimularSuscripcion);

		Respuesta<FondoDTO> rtaSimulacion = fondoBO.simularSuscripcion(dtoRequestPesos, cliente);

		Assert.assertNotNull(rtaSimulacion);
		Assert.assertEquals(respuestaSimularSuscripcion, rtaSimulacion);

	}

	/**
	 * Simular suscripcion importe menor al minimo exception.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void simularSuscripcionImporteMenorAlMinimoException() throws DAOException {

		Cliente cliente = mock(Cliente.class);

		Respuesta<Object> respuestaSimularSuscripcion = new Respuesta<Object>();
		respuestaSimularSuscripcion.setEstadoRespuesta(EstadoRespuesta.WARNING);
		ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
		imr.setTipoError(TipoError.ERROR_LIMITE_MINIMO.getDescripcion());
		respuestaSimularSuscripcion.add(imr);

		Mockito.when(fondoDao.suscribir(Matchers.any(SuscripcionFondoInEntity.class)))
				.thenThrow(new ImporteMenorAlMinimoException());
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(String.class),
				Matchers.eq(TipoError.ERROR_LIMITE_MINIMO), Matchers.any(String.class)))
				.thenReturn(respuestaSimularSuscripcion);

		Respuesta<FondoDTO> rtaSimulacion = fondoBO.simularSuscripcion(dtoRequestPesos, cliente);

		Assert.assertNotNull(rtaSimulacion);
		Assert.assertEquals(respuestaSimularSuscripcion, rtaSimulacion);

	}

	/**
	 * Simular suscripcion business exception.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void simularSuscripcionBusinessException() throws DAOException {
		Cliente cliente = mock(Cliente.class);
		Respuesta<Object> respuestaSimularSuscripcion = new Respuesta<Object>();
		respuestaSimularSuscripcion.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
		imr.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		respuestaSimularSuscripcion.add(imr);

		// Mockito.when(fondoDao.suscribir(Matchers.any(SuscripcionFondoInEntity.class))).thenThrow(new
		// BusinessException());
		Mockito.when(
				respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.eq(TipoError.ERROR_GENERICO),
						Matchers.eq(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)))
				.thenReturn(respuestaSimularSuscripcion);
		Respuesta<FondoDTO> rtaSimulacion = fondoBO.simularSuscripcion(null, cliente);
		Assert.assertNotNull(rtaSimulacion);
		Assert.assertEquals(respuestaSimularSuscripcion, rtaSimulacion);

	}

	/**
	 * Simular suscripcio DAO exception.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void simularSuscripcioDAOException() throws DAOException {
		Cliente cliente = mock(Cliente.class);
		Respuesta<Object> respuestaSimularSuscripcion = new Respuesta<Object>();
		respuestaSimularSuscripcion.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
		imr.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		respuestaSimularSuscripcion.add(imr);
		Mockito.when(fondoDao.suscribir(Matchers.any(SuscripcionFondoInEntity.class))).thenThrow(new DAOException());
		Mockito.when(
				respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.eq(TipoError.ERROR_GENERICO),
						Matchers.eq(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)))
				.thenReturn(respuestaSimularSuscripcion);
		Respuesta<FondoDTO> rtaSimulacion = fondoBO.simularSuscripcion(dtoRequestPesos, cliente);
		Assert.assertNotNull(rtaSimulacion);
		Assert.assertEquals(respuestaSimularSuscripcion, rtaSimulacion);
	}

	/**
	 * Simular suscripcio codigo retorno NOOK.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void simularSuscripcioCodigoRetornoNOOK() throws DAOException {
		Cliente cliente = mock(Cliente.class);
		Respuesta<Object> respuestaSimularSuscripcion = new Respuesta<Object>();
		respuestaSimularSuscripcion.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
		imr.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		respuestaSimularSuscripcion.add(imr);

		SuscripcionFondoOutEntity salidaDAO = new SuscripcionFondoOutEntity();
		salidaDAO.setCodigoRetornoExtendido(CODIGO_ERROR);

		Mockito.when(fondoDao.suscribir(Matchers.any(SuscripcionFondoInEntity.class))).thenReturn(salidaDAO);
		Mockito.when(
				respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.eq(TipoError.ERROR_GENERICO),
						Matchers.eq(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)))
				.thenReturn(respuestaSimularSuscripcion);
		Respuesta<FondoDTO> rtaSimulacion = fondoBO.simularSuscripcion(dtoRequestPesos, cliente);
		Assert.assertNotNull(rtaSimulacion);
		Assert.assertEquals(respuestaSimularSuscripcion, rtaSimulacion);

	}

	/**
	 * suscribir
	 *
	 * @throws DAOException the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void suscribirOK() throws DAOException {
		Cliente cliente = mock(Cliente.class);
		SuscripcionFondoOutEntity salidaDAO = new SuscripcionFondoOutEntity();
		salidaDAO.setCodigoRetornoExtendido(CODIGO_ERROR);
		Respuesta<FondoDTO> respuestaSuscripcion = new Respuesta<FondoDTO>();
		respuestaSuscripcion.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(fondoDao.suscribir(Matchers.any(SuscripcionFondoInEntity.class))).thenReturn(salidaDAO);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FondoDTO.class)))
				.thenReturn(respuestaSuscripcion);
		Respuesta<FondoDTO> rtaSuscribir = fondoBO.suscribir(dtoRequestPesos, cliente);
		Assert.assertNotNull(rtaSuscribir);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void suscribirError() throws DAOException {
		Cliente cliente = mock(Cliente.class);
		Respuesta<FondoDTO> respuestaSuscripcionError = new Respuesta<FondoDTO>();
		respuestaSuscripcionError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(fondoDao.suscribir(Matchers.any(SuscripcionFondoInEntity.class))).thenThrow(new DAOException());
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList()))
				.thenReturn(respuestaSuscripcionError);
		Respuesta<FondoDTO> rtaSuscribir = fondoBO.suscribir(dtoRequestPesos, cliente);
		Assert.assertNotNull(rtaSuscribir);
	}

	@Test
	@Ignore
	public void obtenerDetalleDeFondo() throws DAOException {

		Cliente cliente = mock(Cliente.class);
		FondoOutEntity consultarFondo = new FondoOutEntity();
		FondoEntity objetoListaFondo = new FondoEntity();
		objetoListaFondo.setTipo_Fondo("022");
		objetoListaFondo.setVariacion_Inicio("00001060+");
		objetoListaFondo.setVariacion_Inicio("00000032");
		objetoListaFondo.setValor_a_la_fecha("123456789876543");
		List<FondoEntity> listaFondos = new ArrayList<FondoEntity>();
		listaFondos.add(objetoListaFondo);

		List<ConsultaFondoOutEntity> consultaFondoOutEntities = new ArrayList<ConsultaFondoOutEntity>();
		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();

		consultaFondoOutEntity.setCodigoFondo("022");
		consultaFondoOutEntity.setHonorariosAdmin("5.0");
		consultaFondoOutEntity.setHonorariosSalida("5.45");
		consultaFondoOutEntity.setHonorariosEntrada("5.45");
		consultaFondoOutEntity.setLinkCartera("x");

		consultaFondoOutEntities.add(consultaFondoOutEntity);

		consultarFondo.setfondos(listaFondos);

		Respuesta<String> respuestaLegal = respuestaFactory.crearRespuestaOk("TextoLegal");

		Mockito.when(legalBO.buscarLegal(Mockito.anyString())).thenReturn(respuestaLegal);
		Mockito.when(fondoDao.consultarCotizaciones()).thenReturn(consultarFondo);
		Mockito.when(consultaFondoDAO.obtenerFondos()).thenReturn(consultaFondoOutEntities);
		ConsultaFondoOutEntity detalleHonorarios = new ConsultaFondoOutEntity();
		detalleHonorarios.setHonorariosAdmin("0000");
		detalleHonorarios.setHonorariosEntrada("0000");
		detalleHonorarios.setHonorariosSalida("0000");
		detalleHonorarios.setPlazoEfectivo("24");
		detalleHonorarios.setHorario("7.30 a 16.00hs");
		detalleHonorarios.setsGTE("0000");
		detalleHonorarios.setsDEP("0000");
		Mockito.when(consultaFondoDAO.obtenerPorCodigo(Matchers.any(String.class))).thenReturn(detalleHonorarios);

		DetalleDeFondoIn datoEntrada = new DetalleDeFondoIn();
		datoEntrada.setCodigoFondo("022");

		Respuesta<DetalleDeFondoOutView> detallesTest = new Respuesta<DetalleDeFondoOutView>();

		DetalleDeFondoOutView respuestaTest = new DetalleDeFondoOutView();
		respuestaTest.setCotizacion("0000011");
		respuestaTest.setStatusCotizacion("00001067+");
		respuestaTest.setAdministrarFondos("00,00");
		respuestaTest.setEntrada("00,00");
		respuestaTest.setSalida("00,00");
		respuestaTest.setHorarios("7.30 a 16.00hs");
		respuestaTest.setsGTE("0000");
		respuestaTest.setsDEP("0000");
		detallesTest.setRespuesta(respuestaTest);

		TenenciaFondosSuscritosDTO tenencia = new TenenciaFondosSuscritosDTO();
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(DetalleDeFondoOutView.class)))
				.thenReturn(detallesTest);

		detallesTest = fondoBO.obtenerDetalleDeFondo(datoEntrada, cliente, tenencia);

		Assert.assertNotNull(respuestaTest);

	}

	@Test
	public void obtenerTenencias() throws DAOException {
		TipoBancaDTO bancaDTO = new TipoBancaDTO();
		bancaDTO.setTipoBanca("BR");
		Cliente cliente = mock(Cliente.class);

		InicioFondoDTO inicioFondoDTO = new InicioFondoDTO();

		inicioFondoDTO.setCuentasBancaPersonal(new ArrayList<CuentaTituloDTO>());
		inicioFondoDTO.setCuentasBancaPersonal(new ArrayList<CuentaTituloDTO>());

		List<ConsultaFondoOutEntity> listFondoOutEntity = new ArrayList<ConsultaFondoOutEntity>();

		@SuppressWarnings("unused")
		List<CuentaServiceTenenciaDTO> listaCuentasServiceTenencia = new ArrayList<CuentaServiceTenenciaDTO>();

		DetalleTenenciaValuadaEntity tenenciaValuada = new DetalleTenenciaValuadaEntity();

		@SuppressWarnings("unused")
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();

		Mockito.when(consultaFondoDAO.obtenerFondos(Matchers.any(Predicate.class))).thenReturn(listFondoOutEntity);
		Mockito.when(tenenciaValuadaDAO
				.obtenerTenenciaValuadaDetalleFondoOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(tenenciaValuada);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		when(mensajeBo.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		@SuppressWarnings("unused")
		Respuesta<TenenciasFondoDTO> rta = fondoBO.obtenerTenencias(bancaDTO, cliente);

	}

	@Test
	public void cargarMapTenenciasPesos() {
		List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosPesos = new ArrayList<TenenciaFondosSuscritosDTO>();
		List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosDolares = new ArrayList<TenenciaFondosSuscritosDTO>();
		ResultadoTenenciaValuadaDetalleFondoOL dato = new ResultadoTenenciaValuadaDetalleFondoOL();
		ConsultaFondoOutEntity fondo1 = new ConsultaFondoOutEntity();
		CuentaTituloDTO cuenta = new CuentaTituloDTO();
		Map<String, TenenciaPorFondoDTO> tenenciaMap = new HashMap<String, TenenciaPorFondoDTO>();

		dato.setNumeroCuenta("1");
		dato.setMoneda("ARS");
		dato.setCodigoFondo(2);
		dato.setCantidadCuotapartes(new BigDecimal("1"));
		dato.setValorCuotaparte(new BigDecimal("25"));
		dato.setTenenciaValuada(new BigDecimal("100"));
		dato.setResultadoBruto(new BigDecimal("200"));

		fondo1.setCodigoFondo("2");
		fondo1.setNombreFondo("Prueba");
		fondo1.setAgrupadorSuscripcion("Prueba");
		fondo1.setCodigoAgrupador("1");
		fondo1.setOrdenAgrupacion("1");

		cuenta.setNroCuenta("1");
		cuenta.setCuentaOperativaSinFormatear("1");
		tenenciaMap.put("1", new TenenciaPorFondoDTO());

		fondoBO.cargarMapTenencias(tenenciaFondosSuscritosPesos, tenenciaFondosSuscritosDolares, dato, fondo1, cuenta);

	}

	@Test
	public void consultarMovimientos() throws DAOException {
		Cliente cliente = mock(Cliente.class);
		MovimientosInView fondoAConsultar = new MovimientosInView();
		MovimientosFondoOutEntity movimientos = new MovimientosFondoOutEntity();
		fondoAConsultar.setCodigoFondo("007");
		fondoAConsultar.setCuentaTitulo("26382210");
		fondoAConsultar.setFechaDesde("30062016");
		fondoAConsultar.setFechaHasta("01072017");
		List<MovimientoFondoEntity> lista = new ArrayList<MovimientoFondoEntity>();
		MovimientoFondoEntity mov = new MovimientoFondoEntity();
		mov.setCodigoFondo("007");
		mov.setDescripcionMovimiento("prueba");
		lista.add(mov);
		movimientos.setMovimientos(lista);
		Respuesta<MovimientosFondoOutEntity> rtaBO = new Respuesta<MovimientosFondoOutEntity>();
		rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(fondoDao.consultarMovimientos(Matchers.any(Cliente.class),
				Matchers.any(MovimientosFondoInEntity.class))).thenReturn(movimientos);
		Mockito.when(respuestaFactory.crearRespuestaOk(MovimientosFondoOutEntity.class, movimientos)).thenReturn(rtaBO);
		Respuesta<MovimientosFondoOutEntity> rta = fondoBO.consultarMovimientos(cliente, fondoAConsultar);
		Assert.assertNotNull(rta);
	}

	@Test
	public void consultarMovimientosFondoSinMovimientosException() throws DAOException {
		Mockito.when(fondoDao.consultarMovimientos(Matchers.any(Cliente.class),
				Matchers.any(MovimientosFondoInEntity.class))).thenThrow(new FondoSinMovimientosException());
		Cliente cliente = mock(Cliente.class);
		MovimientosInView fondoAConsultar = new MovimientosInView();
		fondoAConsultar.setCodigoFondo("007");
		fondoAConsultar.setCuentaTitulo("26382210");
		fondoAConsultar.setFechaDesde("30062016");
		fondoAConsultar.setFechaHasta("01072017");

		try {
			@SuppressWarnings("unused")
			Respuesta<MovimientosFondoOutEntity> rta = fondoBO.consultarMovimientos(cliente, fondoAConsultar);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	@Test
	public void consultarMovimientosDAOException() throws DAOException {
		Mockito.when(fondoDao.consultarMovimientos(Matchers.any(Cliente.class),
				Matchers.any(MovimientosFondoInEntity.class))).thenThrow(new DAOException());
		Cliente cliente = mock(Cliente.class);
		MovimientosInView fondoAConsultar = new MovimientosInView();
		fondoAConsultar.setCodigoFondo("007");
		fondoAConsultar.setCuentaTitulo("26382210");
		fondoAConsultar.setFechaDesde("30062016");
		fondoAConsultar.setFechaHasta("01072017");

		try {
			@SuppressWarnings("unused")
			Respuesta<MovimientosFondoOutEntity> rta = fondoBO.consultarMovimientos(cliente, fondoAConsultar);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerDetalleDeFondoError() throws DAOException {
		Cliente cliente = mock(Cliente.class);
		DetalleDeFondoIn codigoFondo = new DetalleDeFondoIn();
		codigoFondo.setCodigoFondo("007");
		Respuesta<DetalleDeFondoOutView> detallesTestError = new Respuesta<DetalleDeFondoOutView>();
		detallesTestError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(fondoDao.consultarCotizaciones()).thenThrow(new DAOException());
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList()))
				.thenReturn(detallesTestError);
		Respuesta<Object> rtaDetalle = new Respuesta<Object>();
		rtaDetalle.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
		imr.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		rtaDetalle.add(imr);

		TenenciaFondosSuscritosDTO tenencia = new TenenciaFondosSuscritosDTO();
		Mockito.when(
				respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.eq(TipoError.ERROR_GENERICO),
						Matchers.eq(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)))
				.thenReturn(rtaDetalle);

		Respuesta<DetalleDeFondoOutView> respuesta = fondoBO.obtenerDetalleDeFondo(codigoFondo, cliente, tenencia);

		Assert.assertNotNull(respuesta);
	}

	@Test
	public void convertirStrToBigDecimalVC() throws ImporteConvertException {

		String Cantidad = "00000001989420321";
		int cantidadDecimales = 6;

		BigDecimal resultado = BigDecimal.valueOf(19.894203);

		BigDecimal resultadoFinal = fondoBO.convertirStrToBigDecimalVC(Cantidad, cantidadDecimales);

		Assert.assertEquals(resultado, resultadoFinal);

	}

	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerGraficosRTLOK() {
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		List<CuentaConTenenciaDTO> listCuentas = new ArrayList<CuentaConTenenciaDTO>();
		CuentaConTenenciaDTO cuenta = new CuentaConTenenciaDTO();
		Respuesta<TenenciaPorFondoDTO> resp = new Respuesta<TenenciaPorFondoDTO>();
		TenenciaPorFondoDTO tenenciaPorFondoDTO = new TenenciaPorFondoDTO();
		List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosPesos = new ArrayList<TenenciaFondosSuscritosDTO>();
		TenenciaFondosSuscritosDTO tenenciaPesos = new TenenciaFondosSuscritosDTO();
		tenenciaPesos.setCantidadCuotapartes(new BigDecimal("10"));
		tenenciaPesos.setCodigoAgrupador("1");
		tenenciaPesos.setCodigoFondo("007");
		tenenciaPesos.setValuacion(new BigDecimal("1234.56"));
		tenenciaPesos.setNombreFondo("prueba");
		tenenciaPesos.setResultado(new BigDecimal("456.87"));
		tenenciaFondosSuscritosPesos.add(tenenciaPesos);
		List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosDolares = new ArrayList<TenenciaFondosSuscritosDTO>();
		TenenciaFondosSuscritosDTO tenenciaDolares = new TenenciaFondosSuscritosDTO();
		tenenciaDolares.setCantidadCuotapartes(new BigDecimal("10.00"));
		tenenciaDolares.setCodigoAgrupador("1");
		tenenciaDolares.setCodigoFondo("007");
		tenenciaDolares.setValuacion(new BigDecimal("1234.56"));
		tenenciaDolares.setNombreFondo("prueba");
		tenenciaDolares.setResultado(new BigDecimal("456.87"));
		tenenciaFondosSuscritosDolares.add(tenenciaDolares);
		tenenciaPorFondoDTO.setTenenciaFondosSuscritosPesos(tenenciaFondosSuscritosPesos);
		tenenciaPorFondoDTO.setTenenciaFondosSuscritosDolares(tenenciaFondosSuscritosDolares);
		resp.setRespuesta(tenenciaPorFondoDTO);
		cuenta.setRespuesta(resp);
		listCuentas.add(cuenta);
		tenencias.setListaCuentas(listCuentas);

		Mockito.when(sessionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);

		Respuesta<GraficoFondosRTLOutDTO> rtaBO = new Respuesta<GraficoFondosRTLOutDTO>();
		rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(GraficoFondosRTLOutDTO.class)))
				.thenReturn(rtaBO);
		Cliente cliente = new Cliente();
		Respuesta<GraficoFondosRTLOutDTO> rta = fondoBO.obtenerGraficosRTL(cliente);

		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void obtenerGraficosRTLError() {
		Cliente cliente = new Cliente();
		TenenciasFondoDTO tenencias = null;
		Mockito.when(sessionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
		Respuesta<GraficoFondosRTLOutDTO> rta = fondoBO.obtenerGraficosRTL(cliente);

		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerGraficosBPrivOK() {
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		List<CuentaConTenenciaDTO> listCuentas = new ArrayList<CuentaConTenenciaDTO>();
		CuentaConTenenciaDTO cuenta = new CuentaConTenenciaDTO();
		Respuesta<TenenciaPorFondoDTO> resp = new Respuesta<TenenciaPorFondoDTO>();
		TenenciaPorFondoDTO tenenciaPorFondoDTO = new TenenciaPorFondoDTO();
		List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosPesos = new ArrayList<TenenciaFondosSuscritosDTO>();
		TenenciaFondosSuscritosDTO tenenciaPesos = new TenenciaFondosSuscritosDTO();
		tenenciaPesos.setCantidadCuotapartes(new BigDecimal("10.00"));
		tenenciaPesos.setCodigoAgrupador("1");
		tenenciaPesos.setCodigoFondo("007");
		tenenciaPesos.setValuacion(new BigDecimal("1234.56"));
		tenenciaPesos.setNombreFondo("prueba");
		tenenciaPesos.setResultado(new BigDecimal("456.87"));
		tenenciaFondosSuscritosPesos.add(tenenciaPesos);
		List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosDolares = new ArrayList<TenenciaFondosSuscritosDTO>();
		TenenciaFondosSuscritosDTO tenenciaDolares = new TenenciaFondosSuscritosDTO();
		tenenciaDolares.setCantidadCuotapartes(new BigDecimal("10.00"));
		tenenciaDolares.setCodigoAgrupador("1");
		tenenciaDolares.setCodigoFondo("007");
		tenenciaDolares.setValuacion(new BigDecimal("1234.56"));
		tenenciaDolares.setNombreFondo("prueba");
		tenenciaDolares.setResultado(new BigDecimal("456.87"));
		tenenciaFondosSuscritosDolares.add(tenenciaDolares);
		tenenciaPorFondoDTO.setTenenciaFondosSuscritosPesos(tenenciaFondosSuscritosPesos);
		tenenciaPorFondoDTO.setTenenciaFondosSuscritosDolares(tenenciaFondosSuscritosDolares);
		resp.setRespuesta(tenenciaPorFondoDTO);
		cuenta.setRespuesta(resp);
		listCuentas.add(cuenta);
		tenencias.setListaCuentas(listCuentas);

		Mockito.when(sessionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);

		Respuesta<GraficoFondosBPrivOutDTO> rtaBO = new Respuesta<GraficoFondosBPrivOutDTO>();
		rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(GraficoFondosBPrivOutDTO.class)))
				.thenReturn(rtaBO);
		Cliente cliente = new Cliente();
		Respuesta<GraficoFondosBPrivOutDTO> rta = fondoBO.obtenerGraficosBpriv(cliente);

		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	@Test
	public void obtenerGraficosBPrivError() {
		Cliente cliente = new Cliente();
		TenenciasFondoDTO tenencias = null;
		Mockito.when(sessionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
		Respuesta<GraficoFondosBPrivOutDTO> rta = fondoBO.obtenerGraficosBpriv(cliente);

		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void obtenerFondosReporte() {

		// When
		Cliente cliente = Mockito.mock(Cliente.class);
		List<CuentaConTenenciaDTO> fondosView = armarListaFondos();
		Respuesta<Reporte> respuestaMock = new Respuesta<Reporte>();
		respuestaMock.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(reporteDAO.obtenerReporte(Matchers.any(CuentasTitulosExcelGeneral.class), Matchers.anyString(),
				Matchers.any(Cliente.class), Matchers.anyBoolean())).thenReturn(respuestaMock);

		// Then
		respuestaMock = fondoBO.obtenerFondosReporte(fondosView, TipoBancaEnum.BANCA_PERSONAL, cliente);

		// Expected
		Assert.assertNotNull(respuestaMock);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaMock.getEstadoRespuesta());

	}

	private List<CuentaConTenenciaDTO> armarListaFondos() {

		List<CuentaConTenenciaDTO> listaFondos = new ArrayList<CuentaConTenenciaDTO>();
		CuentaConTenenciaDTO cuentaTitulos = new CuentaConTenenciaDTO();
		cuentaTitulos.setNumeroCuentaTitulo("565029/3");

		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		Interviniente interviniente = new Interviniente();
		interviniente.setApellido("Alfaro");
		interviniente.setNombre("Gustavo");
		intervinientes.add(interviniente);
		cuentaTitulos.setIntervinientes(intervinientes);

		TenenciaPorFondoDTO tenenciasCuentaDTO = new TenenciaPorFondoDTO();
		List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosPesos = new ArrayList<TenenciaFondosSuscritosDTO>();
		TenenciaFondosSuscritosDTO fondo1 = new TenenciaFondosSuscritosDTO();
		fondo1.setTipoFondo("Mercado de Dinero");
		fondo1.setNombreFondo("Super Ahorro $");
		fondo1.setCantidadCuotapartes(new BigDecimal("1373.6264"));
		fondo1.setValorCuotaparte(new BigDecimal("7.285874"));
		fondo1.setValuacion(new BigDecimal("10000"));
		fondo1.setResultado(new BigDecimal("10000"));
		fondo1.setCodigoAgrupador("1 ");
		fondo1.setOrdenAgrupacion("1  ");
		tenenciaFondosSuscritosPesos.add(fondo1);

		TenenciaFondosSuscritosDTO fondo2 = new TenenciaFondosSuscritosDTO();
		fondo2.setTipoFondo("Mercado de Dinero");
		fondo2.setNombreFondo("Super Ahorro PLUS A");
		fondo2.setCantidadCuotapartes(new BigDecimal("1077.4143"));
		fondo2.setValorCuotaparte(new BigDecimal("9.282408"));
		fondo2.setValuacion(new BigDecimal("10000.99"));
		fondo2.setResultado(new BigDecimal("10000.99"));
		fondo2.setCodigoAgrupador("1 ");
		fondo2.setOrdenAgrupacion("2  ");
		tenenciaFondosSuscritosPesos.add(fondo2);

		TenenciaFondosSuscritosDTO fondo3 = new TenenciaFondosSuscritosDTO();
		fondo3.setTipoFondo("Renta Fija en Pesos");
		fondo3.setNombreFondo("Supergestión MIX VI");
		fondo3.setCantidadCuotapartes(new BigDecimal("4376.8695"));
		fondo3.setValorCuotaparte(new BigDecimal("2.284738"));
		fondo3.setValuacion(new BigDecimal("10000"));
		fondo3.setResultado(new BigDecimal("10000"));
		fondo3.setCodigoAgrupador("2 ");
		fondo3.setOrdenAgrupacion("1  ");
		tenenciaFondosSuscritosPesos.add(fondo3);

		TenenciaFondosSuscritosDTO fondo4 = new TenenciaFondosSuscritosDTO();
		fondo4.setTipoFondo("Renta Fija en Pesos");
		fondo4.setNombreFondo("Superfondo Renta $");
		fondo4.setCantidadCuotapartes(new BigDecimal("8781.4911"));
		fondo4.setValorCuotaparte(new BigDecimal("1.28"));
		fondo4.setValuacion(new BigDecimal("11240.3"));
		fondo4.setResultado(new BigDecimal("11240.3"));
		fondo4.setCodigoAgrupador("2 ");
		fondo4.setOrdenAgrupacion("2  ");
		tenenciaFondosSuscritosPesos.add(fondo4);

		TenenciaFondosSuscritosDTO fondo5 = new TenenciaFondosSuscritosDTO();
		fondo5.setTipoFondo("Renta Mixta");
		fondo5.setNombreFondo("Superfondo Combinado F.C.I. A");
		fondo5.setCantidadCuotapartes(new BigDecimal("8781.4911"));
		fondo5.setValorCuotaparte(new BigDecimal("1.28"));
		fondo5.setValuacion(new BigDecimal("11240.3"));
		fondo5.setResultado(new BigDecimal("11240.3"));
		fondo5.setCodigoAgrupador("3 ");
		fondo5.setOrdenAgrupacion("2  ");
		tenenciaFondosSuscritosPesos.add(fondo5);

		TenenciaFondosSuscritosDTO fondo6 = new TenenciaFondosSuscritosDTO();
		fondo6.setTipoFondo("Renta Variable en Pesos");
		fondo6.setNombreFondo("Superfondo Renta Variable");
		fondo6.setCantidadCuotapartes(new BigDecimal("8781.4911"));
		fondo6.setValorCuotaparte(new BigDecimal("1.28"));
		fondo6.setValuacion(new BigDecimal("11240.3"));
		fondo6.setResultado(new BigDecimal("11240.3"));
		fondo6.setCodigoAgrupador("4 ");
		fondo6.setOrdenAgrupacion("1  ");
		tenenciaFondosSuscritosPesos.add(fondo6);

		tenenciasCuentaDTO.setTenenciaFondosSuscritosPesos(tenenciaFondosSuscritosPesos);
//		tenenciasCuentaDTO.setTenenciaFondosSuscritosDolares(tenenciaFondosSuscritosDolares);

		tenenciasCuentaDTO.setTotalValuacionPesos(new BigDecimal("21240.3"));
		tenenciasCuentaDTO.setTotalResultadoPesos(new BigDecimal("21240.3"));
//		tenenciasCuentaDTO.setTotalValuacionDolares(new BigDecimal("20000.99"));
//		tenenciasCuentaDTO.setTotalResultadoDolares(new BigDecimal("20000.99"));

		Respuesta<TenenciaPorFondoDTO> respuestaTenencias = new Respuesta<TenenciaPorFondoDTO>();
		respuestaTenencias.setRespuesta(tenenciasCuentaDTO);

		cuentaTitulos.setRespuesta(respuestaTenencias);
		listaFondos.add(cuentaTitulos);

		return listaFondos;
	}

}
