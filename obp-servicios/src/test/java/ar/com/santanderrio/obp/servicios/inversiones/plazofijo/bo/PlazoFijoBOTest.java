package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.DatosPerfilInversorEntity;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMayorAlMaximoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.TenenciaValuadaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosRespuestaPF;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ResultadoTenenciaValuadaDetallePFOL;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.ConsultaPlazoFijoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dao.PlazoFijoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ComprobantePlazoFijoInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ComprobantePlazoFijoOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ContenidoTenenciaBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.FinalizarPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.InteresesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.MinimosPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimulacionPrecancelableDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimularPlazoFijoInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimularPlazoFijoOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TenenciaPlazoFijoBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TenenciaTotalPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TotalesGrillaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.AccionAlVencimientoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaInteresantePlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaInteresantePlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelablePlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelablenPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTasasPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DescripcionAccionAlVencimientoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DetalleTenenciaValuadaPFEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MantenimientoPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MantenimientoPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PFInteresanteEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PrecancelacionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPrecancelableOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.TasasPlazoFijoBPrivEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.TasasPlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.TenenciaValuadaPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel.TenenciaPlazoFijoExcel;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConsultaTasasPlazosFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleCobroInteresesViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleInteresesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ModificarAccionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.RecomendacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.tenencias.dto.SolicitarPrecancelarOutDTO;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

@RunWith(MockitoJUnitRunner.class)

public class PlazoFijoBOTest {

	/** The plazo fijo bo */
	@InjectMocks
	private PlazoFijoBOImpl plazoFijoBOImpl;

	/** The respuesta factory. */
	@Mock
	private RespuestaFactory respuestaFactory;

	@Mock
	private PlazoFijoDAO plazoFijoDAO;

	@Mock
	private MensajeBO mensajeBO;

	/** The legal BO. */
	@Mock
	private LegalBO legalBO;

	@Mock
	private ConsultaPlazoFijoDAO consultaPlazoFijoDAO;

	/** The tenencia valuada */
	@Mock
	private TenenciaValuadaDAO tenenciaValuadaDAO;

	@Mock
	private CuentaBO cuentaBO;

	/** The session parametros. */
	@Mock
	private SesionParametros sessionParametros;

	@Mock
	private ReporteDAO reporteDAO;

	/** The Constant BANCA_PERSONAL. */
	private static final String BANCA_PERSONAL = "BANCAPERSONAL";

	/** The Constant BANCA_RETAIL. */
	private static final String BANCA_RETAIL = "BR";

	@Before
	public void init() {

	}

	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void consultarMinimos() throws DAOException {
		Cliente cliente = new Cliente();

		ConsultaTasasPlazoFijoBPrivOutEntity outDAO = new ConsultaTasasPlazoFijoBPrivOutEntity();
		Mockito.when(plazoFijoDAO.consultarTasas(Matchers.any(Cliente.class), BANCA_PERSONAL, false))
				.thenReturn(outDAO);

		List<PlazoFijoEntity> outDAO2 = new ArrayList<PlazoFijoEntity>();
		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijosHabilitados()).thenReturn(outDAO2);

		Respuesta<MinimosPlazoFijoDTO> rtaBO = new Respuesta<MinimosPlazoFijoDTO>();
		rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(
				respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(MinimosPlazoFijoDTO.class)))
				.thenReturn(rtaBO);

		Respuesta<MinimosPlazoFijoDTO> respuesta = plazoFijoBOImpl.consultarMinimos(cliente, BANCA_PERSONAL);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Ignore
	@Test
	public void consultarMinimosDAOException() throws DAOException, IatxException {
		Cliente cliente = new Cliente();

		Mockito.when(plazoFijoDAO.consultarTasas(Matchers.any(Cliente.class), BANCA_PERSONAL, false))
				.thenThrow(new DAOException());

		try {
			Respuesta<MinimosPlazoFijoDTO> respuesta = plazoFijoBOImpl.consultarMinimos(cliente, BANCA_PERSONAL);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	@Test
	public void obtenerMinimosPesosBusinessException() throws BusinessException {
		List<TasasPlazoFijoBPrivEntity> plazosHabilitados = new ArrayList<TasasPlazoFijoBPrivEntity>();
		TasasPlazoFijoBPrivEntity tasaPlazoFijo = new TasasPlazoFijoBPrivEntity();
		TasasPlazoFijoBPrivEntity tasaPlazoFijo2 = new TasasPlazoFijoBPrivEntity();
		tasaPlazoFijo.setTipoPF("03");
		tasaPlazoFijo.setMoneda("USD");
		tasaPlazoFijo.setImporteMinimo("1000");
		tasaPlazoFijo.setPlazo("30");
		tasaPlazoFijo2.setTipoPF("16");
		tasaPlazoFijo2.setMoneda("ARS");
		tasaPlazoFijo2.setImporteMinimo("asd");
		tasaPlazoFijo2.setPlazo("30");

		plazosHabilitados.add(tasaPlazoFijo);
		plazosHabilitados.add(tasaPlazoFijo2);

		try {
			MinimosPlazoFijoDTO minimosDTO = plazoFijoBOImpl.obtenerMinimos(plazosHabilitados);
		} catch (Exception e) {
			Assert.assertEquals(BusinessException.class, e.getClass());
		}
	}

	@Test
	public void obtenerMinimosDolaresBusinessException() throws BusinessException {
		List<TasasPlazoFijoBPrivEntity> plazosHabilitados = new ArrayList<TasasPlazoFijoBPrivEntity>();
		TasasPlazoFijoBPrivEntity tasaPlazoFijo = new TasasPlazoFijoBPrivEntity();
		TasasPlazoFijoBPrivEntity tasaPlazoFijo2 = new TasasPlazoFijoBPrivEntity();
		tasaPlazoFijo.setTipoPF("03");
		tasaPlazoFijo.setMoneda("USD");
		tasaPlazoFijo.setImporteMinimo("asd");
		tasaPlazoFijo.setPlazo("30");
		tasaPlazoFijo2.setTipoPF("16");
		tasaPlazoFijo2.setMoneda("ARS");
		tasaPlazoFijo2.setImporteMinimo("500");
		tasaPlazoFijo2.setPlazo("30");

		plazosHabilitados.add(tasaPlazoFijo);
		plazosHabilitados.add(tasaPlazoFijo2);

		try {
			MinimosPlazoFijoDTO minimosDTO = plazoFijoBOImpl.obtenerMinimos(plazosHabilitados);
		} catch (Exception e) {
			Assert.assertEquals(BusinessException.class, e.getClass());
		}
	}

	@Test
	public void testFiltrarPlazosFijosHabilitados() {
		List<PlazoFijoEntity> plazosFijos = new ArrayList<PlazoFijoEntity>();
		PlazoFijoEntity plazoFijo = new PlazoFijoEntity();
		PlazoFijoEntity plazoFijo2 = new PlazoFijoEntity();
		plazoFijo.setCodigoPlazo("03");
		plazoFijo2.setCodigoPlazo("16");
		plazosFijos.add(plazoFijo);
		plazosFijos.add(plazoFijo2);

		List<TasasPlazoFijoBPrivEntity> plazosHabilitados = new ArrayList<TasasPlazoFijoBPrivEntity>();
		TasasPlazoFijoBPrivEntity tasaPlazoFijo = new TasasPlazoFijoBPrivEntity();
		TasasPlazoFijoBPrivEntity tasaPlazoFijo2 = new TasasPlazoFijoBPrivEntity();
		tasaPlazoFijo.setTipoPF("03");
		tasaPlazoFijo2.setTipoPF("16");
		plazosHabilitados.add(tasaPlazoFijo);
		plazosHabilitados.add(tasaPlazoFijo2);

		List<TasasPlazoFijoBPrivEntity> listaFiltrada = plazoFijoBOImpl.filtrarPlazosFijosHabilitados(plazosFijos,
				plazosHabilitados);
		Assert.assertNotNull(listaFiltrada);
	}

	@Test
	public void testObtenerMinimos() throws BusinessException {
		List<TasasPlazoFijoBPrivEntity> plazosHabilitados = new ArrayList<TasasPlazoFijoBPrivEntity>();
		TasasPlazoFijoBPrivEntity tasaPlazoFijo = new TasasPlazoFijoBPrivEntity();
		TasasPlazoFijoBPrivEntity tasaPlazoFijo2 = new TasasPlazoFijoBPrivEntity();
		tasaPlazoFijo.setTipoPF("03");
		tasaPlazoFijo.setMoneda("USD");
		tasaPlazoFijo.setImporteMinimo("1000");
		tasaPlazoFijo.setPlazo("30");
		tasaPlazoFijo2.setTipoPF("16");
		tasaPlazoFijo2.setMoneda("ARS");
		tasaPlazoFijo2.setImporteMinimo("500");
		tasaPlazoFijo2.setPlazo("30");

		plazosHabilitados.add(tasaPlazoFijo);
		plazosHabilitados.add(tasaPlazoFijo2);

		MinimosPlazoFijoDTO minimosDTO = plazoFijoBOImpl.obtenerMinimos(plazosHabilitados);
		Assert.assertNotNull(minimosDTO);
	}

	@Test
	public void accionesAlVencimiento() throws DAOException {
		PlazoFijoEntity plazoFijoEntity = new PlazoFijoEntity();
		List<AccionAlVencimientoOutEntity> accionesAlVencimiento = new ArrayList<AccionAlVencimientoOutEntity>();
		plazoFijoEntity.setAccionesAlVencimiento(accionesAlVencimiento);
		accionesAlVencimiento.add(new AccionAlVencimientoOutEntity());
		Mockito.when(consultaPlazoFijoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(plazoFijoEntity);
		AccionesAlVencimientoInView accion = new AccionesAlVencimientoInView();
		accion.setCodigoPlazo("17");
		Respuesta<AccionesAlVencimientoOutView> rtaBo = plazoFijoBOImpl.accionesAlVencimiento(accion, new Cliente());
		Assert.assertNotNull(rtaBo);
	}

	/**
	 * Simular Plazo Fijo caso OK
	 * 
	 * @throws DAOException
	 */

	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void simularPF_OK() throws DAOException {

		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		inDTO.setDivisa("$");
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("000-063880/1");
		inDTO.setPlazo("500");
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta("02");
		cuenta.setNroSucursal("000");
		cuenta.setNroCuentaProducto("000000000638801");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);
		cliente.setCuentas(cuentas);

		List<PlazoFijoEntity> tiposPlazoFijosHabilitados = new ArrayList<PlazoFijoEntity>();
		PlazoFijoEntity plazoFijoEntity = new PlazoFijoEntity();
		plazoFijoEntity.setNombreLegal("Plazo Fijo Nominativo Intransferible a Tasa Fija");
		plazoFijoEntity.setAyuda(
				"En pesos y en dolares. Plazo minimo 30 dias. Montos minimos: En pesos $500 y en d0lares U$S500.");
		plazoFijoEntity.setCodigoPlazo("3");
		plazoFijoEntity.setDescripcion("Tradicional");
		plazoFijoEntity.setEsAjustable("0");
		plazoFijoEntity.setEsInteresante("0");
		plazoFijoEntity.setEsPrecancelable("0");
		plazoFijoEntity.setEsTasaVariable("0");
		plazoFijoEntity.setHabilitado("1");
		plazoFijoEntity.setMinDiasLiqInt("0");
		plazoFijoEntity.setMoneda("1");
		plazoFijoEntity.setMostrarFrecIntereses("0");

		PlazoFijoEntity plazoFijoEntity1 = new PlazoFijoEntity();
		plazoFijoEntity1.setNombreLegal("Plazo Fijo Nominativo Intransferible con Pago Periodico de Intereses");
		plazoFijoEntity1.setAyuda(
				"Es un plazo fijo en pesos, que ofrece la posibilidad de cobrar el interes antes de la fecha de vencimiento, eligiendo con que frecuencia desea cobrar los intereses. Plazo minimo de colocación 180 dias. Plazo minimo para el cobro anticipado de intereses 30 dias. Monto minimo  $5.000.");
		plazoFijoEntity1.setCodigoPlazo("17");
		plazoFijoEntity1.setDescripcion("Interesante Tasa Fija");
		plazoFijoEntity1.setEsAjustable("0");
		plazoFijoEntity1.setEsInteresante("1");
		plazoFijoEntity1.setEsPrecancelable("0");
		plazoFijoEntity1.setEsTasaVariable("0");
		plazoFijoEntity1.setHabilitado("1");
		plazoFijoEntity1.setMinDiasLiqInt("30");
		plazoFijoEntity1.setMoneda("0");
		plazoFijoEntity1.setMostrarFrecIntereses("1");

		tiposPlazoFijosHabilitados.add(plazoFijoEntity);
		tiposPlazoFijosHabilitados.add(plazoFijoEntity1);

		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijosHabilitadosPorMoneda(Matchers.any(String.class)))
				.thenReturn(tiposPlazoFijosHabilitados);

		SimulacionPlazoFijoOutEntity outDAO = new SimulacionPlazoFijoOutEntity();
		outDAO.setAjusteAlAlta("00000000");
		outDAO.setCantidadRepeticionesInteresante(new Long(0));
		outDAO.setCantImpuestos(new Long(0));
		outDAO.setCodigoRetornoExtendido("0055200000000");
		outDAO.setDescProducto("PLAZO FIJO          ");
		outDAO.setDescSubproducto("PF TRADICIONAL      ");
		outDAO.setFechaAltaReal("2017-06-01");
		outDAO.setFechaMinimaPrecancelar("         ");
		outDAO.setFechaProven("2018-10-15");
		outDAO.setHeaderTrama(
				"200000000000P04HTML0009900010301FRQF31  ********00965471000000062017060115034400000000IBF0095O000000000000CNSSIMPFCN1400000            01576531    00        00 015017332201706011504470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		outDAO.setImporte("000000000890000");
		outDAO.setImporteADebitar("000000000890000");
		outDAO.setImpuCap("000000000000000");
		outDAO.setImpuInt("000000000000000");
		outDAO.setIndicadorPrecancelable("N");
		outDAO.setInteres("000000000342053");
		outDAO.setMinimoDiasPrecancelar("00000");
		outDAO.setPerLiq("V");
		outDAO.setPlazo("00501");
		outDAO.setPorcentajePenalizacion("00000000");
		outDAO.setProducto("01");
		outDAO.setSignoAjusteAlAlta("+");
		outDAO.setSubproducto("0003");
		outDAO.setTarifa("MF");
		outDAO.setTasa("02800000");
		outDAO.setTasaPrimerTramo("000000000");
		outDAO.setTasaRenovacion("00200000");
		outDAO.setTasaVarMinimoGarantizada("000000000");
		outDAO.setTipoPF("03");

		List<TasasPlazoFijoBPrivEntity> tipoPF = new ArrayList<TasasPlazoFijoBPrivEntity>();

		TasasPlazoFijoBPrivEntity tasasPlazoFijoEntity1 = new TasasPlazoFijoBPrivEntity();
		tasasPlazoFijoEntity1.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity1.setImporte("99999999999999");
		tasasPlazoFijoEntity1.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity1.setMoneda("ARS");
		tasasPlazoFijoEntity1.setPlazo("00030");
		tasasPlazoFijoEntity1.setTipoPF("03");

		TasasPlazoFijoBPrivEntity tasasPlazoFijoEntity2 = new TasasPlazoFijoBPrivEntity();
		tasasPlazoFijoEntity2.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity2.setImporte("99999999999999");
		tasasPlazoFijoEntity2.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity2.setMoneda("ARS");
		tasasPlazoFijoEntity2.setPlazo("00045");
		tasasPlazoFijoEntity2.setTipoPF("03");

		tipoPF.add(tasasPlazoFijoEntity1);
		tipoPF.add(tasasPlazoFijoEntity2);

		ConsultaTasasPlazoFijoBPrivOutEntity plazosFijosDisponibles = new ConsultaTasasPlazoFijoBPrivOutEntity();
		plazosFijosDisponibles.setCantTipoPF(new Long("57"));
		plazosFijosDisponibles.setCodigoRetornoExtendido("0520000000000");
		plazosFijosDisponibles.setHeaderTrama(
				"200000000000P04HTML0009900010301FRQF31  ********00722469000000082017060715151100000000IBF00AMU000000000000CNSTASPFCN1200000            01576531    00        00 015115377201706071516180000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		plazosFijosDisponibles.setListaTasas(tipoPF);

		Mockito.when(plazoFijoDAO.consultarTasas(Matchers.any(Cliente.class), BANCA_PERSONAL, false))
				.thenReturn(plazosFijosDisponibles);

		Mockito.when(plazoFijoDAO.simularPlazoFijo(Matchers.any(SimulacionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), false)).thenReturn(outDAO);
		Respuesta<SimularPlazoFijoOutDTO> respuestaSimular = new Respuesta<SimularPlazoFijoOutDTO>();
		respuestaSimular.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(SimularPlazoFijoOutDTO.class))).thenReturn(respuestaSimular);

		Respuesta<SimularPlazoFijoOutDTO> respuesta = plazoFijoBOImpl.simularPlazoFijo(inDTO, cliente, 1);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	/**
	 * Simular Plazo Fijo caso ERROR SaldoInsuficienteException
	 * 
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void simularPFSaldoInsuficiente() throws DAOException {

		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		inDTO.setDivisa("$");
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("000-063880/1");
		inDTO.setPlazo("500");
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta("02");
		cuenta.setNroSucursal("000");
		cuenta.setNroCuentaProducto("000000000638801");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);
		cliente.setCuentas(cuentas);

		List<PlazoFijoEntity> tiposPlazoFijosHabilitados = new ArrayList<PlazoFijoEntity>();
		PlazoFijoEntity plazoFijoEntity = new PlazoFijoEntity();
		plazoFijoEntity.setNombreLegal("Plazo Fijo Nominativo Intransferible a Tasa Fija");
		plazoFijoEntity.setAyuda(
				"En pesos y en dolares. Plazo minimo 30 dias. Montos minimos: En pesos $500 y en d0lares U$S500.");
		plazoFijoEntity.setCodigoPlazo("3");
		plazoFijoEntity.setDescripcion("Tradicional");
		plazoFijoEntity.setEsAjustable("0");
		plazoFijoEntity.setEsInteresante("0");
		plazoFijoEntity.setEsPrecancelable("0");
		plazoFijoEntity.setEsTasaVariable("0");
		plazoFijoEntity.setHabilitado("1");
		plazoFijoEntity.setMinDiasLiqInt("0");
		plazoFijoEntity.setMoneda("1");
		plazoFijoEntity.setMostrarFrecIntereses("0");

		PlazoFijoEntity plazoFijoEntity1 = new PlazoFijoEntity();
		plazoFijoEntity1.setNombreLegal("Plazo Fijo Nominativo Intransferible con Pago Periodico de Intereses");
		plazoFijoEntity1.setAyuda(
				"Es un plazo fijo en pesos, que ofrece la posibilidad de cobrar el interes antes de la fecha de vencimiento, eligiendo con que frecuencia desea cobrar los intereses. Plazo minimo de colocación 180 dias. Plazo minimo para el cobro anticipado de intereses 30 dias. Monto minimo  $5.000.");
		plazoFijoEntity1.setCodigoPlazo("17");
		plazoFijoEntity1.setDescripcion("Interesante Tasa Fija");
		plazoFijoEntity1.setEsAjustable("0");
		plazoFijoEntity1.setEsInteresante("1");
		plazoFijoEntity1.setEsPrecancelable("0");
		plazoFijoEntity1.setEsTasaVariable("0");
		plazoFijoEntity1.setHabilitado("1");
		plazoFijoEntity1.setMinDiasLiqInt("30");
		plazoFijoEntity1.setMoneda("0");
		plazoFijoEntity1.setMostrarFrecIntereses("1");

		tiposPlazoFijosHabilitados.add(plazoFijoEntity);
		tiposPlazoFijosHabilitados.add(plazoFijoEntity1);

		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijosHabilitadosPorMoneda(Matchers.any(String.class)))
				.thenReturn(tiposPlazoFijosHabilitados);

		SimulacionPlazoFijoOutEntity outDAO = new SimulacionPlazoFijoOutEntity();
		outDAO.setAjusteAlAlta("00000000");
		outDAO.setCantidadRepeticionesInteresante(new Long(0));
		outDAO.setCantImpuestos(new Long(0));
		outDAO.setCodigoRetornoExtendido("0055200000000");
		outDAO.setDescProducto("PLAZO FIJO          ");
		outDAO.setDescSubproducto("PF TRADICIONAL      ");
		outDAO.setFechaAltaReal("2017-06-01");
		outDAO.setFechaMinimaPrecancelar("         ");
		outDAO.setFechaProven("2018-10-15");
		outDAO.setHeaderTrama(
				"200000000000P04HTML0009900010301FRQF31  ********00965471000000062017060115034400000000IBF0095O000000000000CNSSIMPFCN1400000            01576531    00        00 015017332201706011504470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		outDAO.setImporte("000000000890000");
		outDAO.setImporteADebitar("000000000890000");
		outDAO.setImpuCap("000000000000000");
		outDAO.setImpuInt("000000000000000");
		outDAO.setIndicadorPrecancelable("N");
		outDAO.setInteres("000000000342053");
		outDAO.setMinimoDiasPrecancelar("00000");
		outDAO.setPerLiq("V");
		outDAO.setPlazo("00501");
		outDAO.setPorcentajePenalizacion("00000000");
		outDAO.setProducto("01");
		outDAO.setSignoAjusteAlAlta("+");
		outDAO.setSubproducto("0003");
		outDAO.setTarifa("MF");
		outDAO.setTasa("02800000");
		outDAO.setTasaPrimerTramo("000000000");
		outDAO.setTasaRenovacion("00200000");
		outDAO.setTasaVarMinimoGarantizada("000000000");
		outDAO.setTipoPF("03");

		List<TasasPlazoFijoBPrivEntity> tipoPF = new ArrayList<TasasPlazoFijoBPrivEntity>();

		TasasPlazoFijoBPrivEntity tasasPlazoFijoEntity1 = new TasasPlazoFijoBPrivEntity();
		tasasPlazoFijoEntity1.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity1.setImporte("99999999999999");
		tasasPlazoFijoEntity1.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity1.setMoneda("ARS");
		tasasPlazoFijoEntity1.setPlazo("00030");
		tasasPlazoFijoEntity1.setTipoPF("03");

		TasasPlazoFijoBPrivEntity tasasPlazoFijoEntity2 = new TasasPlazoFijoBPrivEntity();
		tasasPlazoFijoEntity2.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity2.setImporte("99999999999999");
		tasasPlazoFijoEntity2.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity2.setMoneda("ARS");
		tasasPlazoFijoEntity2.setPlazo("00045");
		tasasPlazoFijoEntity2.setTipoPF("03");

		tipoPF.add(tasasPlazoFijoEntity1);
		tipoPF.add(tasasPlazoFijoEntity2);

		ConsultaTasasPlazoFijoBPrivOutEntity plazosFijosDisponibles = new ConsultaTasasPlazoFijoBPrivOutEntity();
		plazosFijosDisponibles.setCantTipoPF(new Long("57"));
		plazosFijosDisponibles.setCodigoRetornoExtendido("0520000000000");
		plazosFijosDisponibles.setHeaderTrama(
				"200000000000P04HTML0009900010301FRQF31  ********00722469000000082017060715151100000000IBF00AMU000000000000CNSTASPFCN1200000            01576531    00        00 015115377201706071516180000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		plazosFijosDisponibles.setListaTasas(tipoPF);

		Mockito.when(plazoFijoDAO.consultarTasas(Matchers.any(Cliente.class), BANCA_PERSONAL, false))
				.thenReturn(plazosFijosDisponibles);

		Mockito.when(plazoFijoDAO.simularPlazoFijo(Matchers.any(SimulacionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), false)).thenThrow(new SaldoInsuficienteException(""));

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_SALDO_INSUFICIENTE,
				CodigoMensajeConstantes.SALDO_INSUFICIENTE_UNA_CUENTA)).thenReturn(responseFactoryError);

		Respuesta<SimularPlazoFijoOutDTO> respuesta = plazoFijoBOImpl.simularPlazoFijo(inDTO, cliente, 1);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	/**
	 * Simular Plazo Fijo caso ERROR ImporteMayorAlMaximoException
	 * 
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void simularPFImporteMayorAlMaximoException() throws DAOException {

		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		inDTO.setDivisa("$");
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("000-063880/1");
		inDTO.setPlazo("500");
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta("02");
		cuenta.setNroSucursal("000");
		cuenta.setNroCuentaProducto("000000000638801");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);
		cliente.setCuentas(cuentas);

		List<PlazoFijoEntity> tiposPlazoFijosHabilitados = new ArrayList<PlazoFijoEntity>();
		PlazoFijoEntity plazoFijoEntity = new PlazoFijoEntity();
		plazoFijoEntity.setNombreLegal("Plazo Fijo Nominativo Intransferible a Tasa Fija");
		plazoFijoEntity.setAyuda(
				"En pesos y en dolares. Plazo minimo 30 dias. Montos minimos: En pesos $500 y en d0lares U$S500.");
		plazoFijoEntity.setCodigoPlazo("3");
		plazoFijoEntity.setDescripcion("Tradicional");
		plazoFijoEntity.setEsAjustable("0");
		plazoFijoEntity.setEsInteresante("0");
		plazoFijoEntity.setEsPrecancelable("0");
		plazoFijoEntity.setEsTasaVariable("0");
		plazoFijoEntity.setHabilitado("1");
		plazoFijoEntity.setMinDiasLiqInt("0");
		plazoFijoEntity.setMoneda("1");
		plazoFijoEntity.setMostrarFrecIntereses("0");

		PlazoFijoEntity plazoFijoEntity1 = new PlazoFijoEntity();
		plazoFijoEntity1.setNombreLegal("Plazo Fijo Nominativo Intransferible con Pago Periodico de Intereses");
		plazoFijoEntity1.setAyuda(
				"Es un plazo fijo en pesos, que ofrece la posibilidad de cobrar el interes antes de la fecha de vencimiento, eligiendo con que frecuencia desea cobrar los intereses. Plazo minimo de colocación 180 dias. Plazo minimo para el cobro anticipado de intereses 30 dias. Monto minimo  $5.000.");
		plazoFijoEntity1.setCodigoPlazo("17");
		plazoFijoEntity1.setDescripcion("Interesante Tasa Fija");
		plazoFijoEntity1.setEsAjustable("0");
		plazoFijoEntity1.setEsInteresante("1");
		plazoFijoEntity1.setEsPrecancelable("0");
		plazoFijoEntity1.setEsTasaVariable("0");
		plazoFijoEntity1.setHabilitado("1");
		plazoFijoEntity1.setMinDiasLiqInt("30");
		plazoFijoEntity1.setMoneda("0");
		plazoFijoEntity1.setMostrarFrecIntereses("1");

		tiposPlazoFijosHabilitados.add(plazoFijoEntity);
		tiposPlazoFijosHabilitados.add(plazoFijoEntity1);

		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijosHabilitadosPorMoneda(Matchers.any(String.class)))
				.thenReturn(tiposPlazoFijosHabilitados);

		SimulacionPlazoFijoOutEntity outDAO = new SimulacionPlazoFijoOutEntity();
		outDAO.setAjusteAlAlta("00000000");
		outDAO.setCantidadRepeticionesInteresante(new Long(0));
		outDAO.setCantImpuestos(new Long(0));
		outDAO.setCodigoRetornoExtendido("0055200000000");
		outDAO.setDescProducto("PLAZO FIJO          ");
		outDAO.setDescSubproducto("PF TRADICIONAL      ");
		outDAO.setFechaAltaReal("2017-06-01");
		outDAO.setFechaMinimaPrecancelar("         ");
		outDAO.setFechaProven("2018-10-15");
		outDAO.setHeaderTrama(
				"200000000000P04HTML0009900010301FRQF31  ********00965471000000062017060115034400000000IBF0095O000000000000CNSSIMPFCN1400000            01576531    00        00 015017332201706011504470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		outDAO.setImporte("000000000890000");
		outDAO.setImporteADebitar("000000000890000");
		outDAO.setImpuCap("000000000000000");
		outDAO.setImpuInt("000000000000000");
		outDAO.setIndicadorPrecancelable("N");
		outDAO.setInteres("000000000342053");
		outDAO.setMinimoDiasPrecancelar("00000");
		outDAO.setPerLiq("V");
		outDAO.setPlazo("00501");
		outDAO.setPorcentajePenalizacion("00000000");
		outDAO.setProducto("01");
		outDAO.setSignoAjusteAlAlta("+");
		outDAO.setSubproducto("0003");
		outDAO.setTarifa("MF");
		outDAO.setTasa("02800000");
		outDAO.setTasaPrimerTramo("000000000");
		outDAO.setTasaRenovacion("00200000");
		outDAO.setTasaVarMinimoGarantizada("000000000");
		outDAO.setTipoPF("03");

		List<TasasPlazoFijoBPrivEntity> tipoPF = new ArrayList<TasasPlazoFijoBPrivEntity>();

		TasasPlazoFijoBPrivEntity tasasPlazoFijoEntity1 = new TasasPlazoFijoBPrivEntity();
		tasasPlazoFijoEntity1.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity1.setImporte("99999999999999");
		tasasPlazoFijoEntity1.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity1.setMoneda("ARS");
		tasasPlazoFijoEntity1.setPlazo("00030");
		tasasPlazoFijoEntity1.setTipoPF("03");

		TasasPlazoFijoBPrivEntity tasasPlazoFijoEntity2 = new TasasPlazoFijoBPrivEntity();
		tasasPlazoFijoEntity2.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity2.setImporte("99999999999999");
		tasasPlazoFijoEntity2.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity2.setMoneda("ARS");
		tasasPlazoFijoEntity2.setPlazo("00045");
		tasasPlazoFijoEntity2.setTipoPF("03");

		tipoPF.add(tasasPlazoFijoEntity1);
		tipoPF.add(tasasPlazoFijoEntity2);

		ConsultaTasasPlazoFijoBPrivOutEntity plazosFijosDisponibles = new ConsultaTasasPlazoFijoBPrivOutEntity();
		plazosFijosDisponibles.setCantTipoPF(new Long("57"));
		plazosFijosDisponibles.setCodigoRetornoExtendido("0520000000000");
		plazosFijosDisponibles.setHeaderTrama(
				"200000000000P04HTML0009900010301FRQF31  ********00722469000000082017060715151100000000IBF00AMU000000000000CNSTASPFCN1200000            01576531    00        00 015115377201706071516180000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		plazosFijosDisponibles.setListaTasas(tipoPF);

		Mockito.when(plazoFijoDAO.consultarTasas(Matchers.any(Cliente.class), BANCA_PERSONAL, false))
				.thenReturn(plazosFijosDisponibles);

		Mockito.when(plazoFijoDAO.simularPlazoFijo(Matchers.any(SimulacionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), false)).thenThrow(new ImporteMayorAlMaximoException());

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<SimularPlazoFijoOutDTO> respuesta = plazoFijoBOImpl.simularPlazoFijo(inDTO, cliente, 1);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	/**
	 * Simular Plazo Fijo caso ERROR DAOException
	 * 
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void simularPFDAOException() throws DAOException {

		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		inDTO.setDivisa("$");
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("000-063880/1");
		inDTO.setPlazo("500");
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta("02");
		cuenta.setNroSucursal("000");
		cuenta.setNroCuentaProducto("000000000638801");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);
		cliente.setCuentas(cuentas);

		List<PlazoFijoEntity> tiposPlazoFijosHabilitados = new ArrayList<PlazoFijoEntity>();
		PlazoFijoEntity plazoFijoEntity = new PlazoFijoEntity();
		plazoFijoEntity.setNombreLegal("Plazo Fijo Nominativo Intransferible a Tasa Fija");
		plazoFijoEntity.setAyuda(
				"En pesos y en dolares. Plazo minimo 30 dias. Montos minimos: En pesos $500 y en d0lares U$S500.");
		plazoFijoEntity.setCodigoPlazo("3");
		plazoFijoEntity.setDescripcion("Tradicional");
		plazoFijoEntity.setEsAjustable("0");
		plazoFijoEntity.setEsInteresante("0");
		plazoFijoEntity.setEsPrecancelable("0");
		plazoFijoEntity.setEsTasaVariable("0");
		plazoFijoEntity.setHabilitado("1");
		plazoFijoEntity.setMinDiasLiqInt("0");
		plazoFijoEntity.setMoneda("1");
		plazoFijoEntity.setMostrarFrecIntereses("0");

		PlazoFijoEntity plazoFijoEntity1 = new PlazoFijoEntity();
		plazoFijoEntity1.setNombreLegal("Plazo Fijo Nominativo Intransferible con Pago Periodico de Intereses");
		plazoFijoEntity1.setAyuda(
				"Es un plazo fijo en pesos, que ofrece la posibilidad de cobrar el interes antes de la fecha de vencimiento, eligiendo con que frecuencia desea cobrar los intereses. Plazo minimo de colocación 180 dias. Plazo minimo para el cobro anticipado de intereses 30 dias. Monto minimo  $5.000.");
		plazoFijoEntity1.setCodigoPlazo("17");
		plazoFijoEntity1.setDescripcion("Interesante Tasa Fija");
		plazoFijoEntity1.setEsAjustable("0");
		plazoFijoEntity1.setEsInteresante("1");
		plazoFijoEntity1.setEsPrecancelable("0");
		plazoFijoEntity1.setEsTasaVariable("0");
		plazoFijoEntity1.setHabilitado("1");
		plazoFijoEntity1.setMinDiasLiqInt("30");
		plazoFijoEntity1.setMoneda("0");
		plazoFijoEntity1.setMostrarFrecIntereses("1");

		tiposPlazoFijosHabilitados.add(plazoFijoEntity);
		tiposPlazoFijosHabilitados.add(plazoFijoEntity1);

		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijosHabilitadosPorMoneda(Matchers.any(String.class)))
				.thenReturn(tiposPlazoFijosHabilitados);

		SimulacionPlazoFijoOutEntity outDAO = new SimulacionPlazoFijoOutEntity();
		outDAO.setAjusteAlAlta("00000000");
		outDAO.setCantidadRepeticionesInteresante(new Long(0));
		outDAO.setCantImpuestos(new Long(0));
		outDAO.setCodigoRetornoExtendido("0055200000000");
		outDAO.setDescProducto("PLAZO FIJO          ");
		outDAO.setDescSubproducto("PF TRADICIONAL      ");
		outDAO.setFechaAltaReal("2017-06-01");
		outDAO.setFechaMinimaPrecancelar("         ");
		outDAO.setFechaProven("2018-10-15");
		outDAO.setHeaderTrama(
				"200000000000P04HTML0009900010301FRQF31  ********00965471000000062017060115034400000000IBF0095O000000000000CNSSIMPFCN1400000            01576531    00        00 015017332201706011504470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		outDAO.setImporte("000000000890000");
		outDAO.setImporteADebitar("000000000890000");
		outDAO.setImpuCap("000000000000000");
		outDAO.setImpuInt("000000000000000");
		outDAO.setIndicadorPrecancelable("N");
		outDAO.setInteres("000000000342053");
		outDAO.setMinimoDiasPrecancelar("00000");
		outDAO.setPerLiq("V");
		outDAO.setPlazo("00501");
		outDAO.setPorcentajePenalizacion("00000000");
		outDAO.setProducto("01");
		outDAO.setSignoAjusteAlAlta("+");
		outDAO.setSubproducto("0003");
		outDAO.setTarifa("MF");
		outDAO.setTasa("02800000");
		outDAO.setTasaPrimerTramo("000000000");
		outDAO.setTasaRenovacion("00200000");
		outDAO.setTasaVarMinimoGarantizada("000000000");
		outDAO.setTipoPF("03");

		List<TasasPlazoFijoBPrivEntity> tipoPF = new ArrayList<TasasPlazoFijoBPrivEntity>();

		TasasPlazoFijoBPrivEntity tasasPlazoFijoEntity1 = new TasasPlazoFijoBPrivEntity();
		tasasPlazoFijoEntity1.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity1.setImporte("99999999999999");
		tasasPlazoFijoEntity1.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity1.setMoneda("ARS");
		tasasPlazoFijoEntity1.setPlazo("00030");
		tasasPlazoFijoEntity1.setTipoPF("03");

		TasasPlazoFijoBPrivEntity tasasPlazoFijoEntity2 = new TasasPlazoFijoBPrivEntity();
		tasasPlazoFijoEntity2.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity2.setImporte("99999999999999");
		tasasPlazoFijoEntity2.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity2.setMoneda("ARS");
		tasasPlazoFijoEntity2.setPlazo("00045");
		tasasPlazoFijoEntity2.setTipoPF("03");

		tipoPF.add(tasasPlazoFijoEntity1);
		tipoPF.add(tasasPlazoFijoEntity2);

		ConsultaTasasPlazoFijoBPrivOutEntity plazosFijosDisponibles = new ConsultaTasasPlazoFijoBPrivOutEntity();
		plazosFijosDisponibles.setCantTipoPF(new Long("57"));
		plazosFijosDisponibles.setCodigoRetornoExtendido("0520000000000");
		plazosFijosDisponibles.setHeaderTrama(
				"200000000000P04HTML0009900010301FRQF31  ********00722469000000082017060715151100000000IBF00AMU000000000000CNSTASPFCN1200000            01576531    00        00 015115377201706071516180000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		plazosFijosDisponibles.setListaTasas(tipoPF);

		Mockito.when(plazoFijoDAO.consultarTasas(Matchers.any(Cliente.class), BANCA_PERSONAL, false))
				.thenReturn(plazosFijosDisponibles);

		Mockito.when(plazoFijoDAO.simularPlazoFijo(Matchers.any(SimulacionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), false)).thenThrow(new DAOException());

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<SimularPlazoFijoOutDTO> respuesta = plazoFijoBOImpl.simularPlazoFijo(inDTO, cliente, 1);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	/**
	 * Simular Plazo Fijo ERROR Sin PlazosFijos.
	 * 
	 * @throws DAOException
	 */

	@Ignore
	@Test
	public void simularPF_SinPlazoFijos() throws DAOException {

		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		inDTO.setDivisa("$");
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("000-063880/1");
		inDTO.setPlazo("500");
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta("02");
		cuenta.setNroSucursal("000");
		cuenta.setNroCuentaProducto("000000000638801");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);
		cliente.setCuentas(cuentas);

		List<PlazoFijoEntity> tiposPlazoFijosHabilitados = new ArrayList<PlazoFijoEntity>();

		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijosHabilitadosPorMoneda(Matchers.any(String.class)))
				.thenReturn(tiposPlazoFijosHabilitados);

		SimulacionPlazoFijoOutEntity outDAO = new SimulacionPlazoFijoOutEntity();

		List<TasasPlazoFijoBPrivEntity> tipoPF = new ArrayList<TasasPlazoFijoBPrivEntity>();

		TasasPlazoFijoBPrivEntity tasasPlazoFijoEntity1 = new TasasPlazoFijoBPrivEntity();
		tasasPlazoFijoEntity1.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity1.setImporte("99999999999999");
		tasasPlazoFijoEntity1.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity1.setMoneda("ARS");
		tasasPlazoFijoEntity1.setPlazo("00030");
		tasasPlazoFijoEntity1.setTipoPF("03");

		TasasPlazoFijoBPrivEntity tasasPlazoFijoEntity2 = new TasasPlazoFijoBPrivEntity();
		tasasPlazoFijoEntity2.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity2.setImporte("99999999999999");
		tasasPlazoFijoEntity2.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity2.setMoneda("ARS");
		tasasPlazoFijoEntity2.setPlazo("00045");
		tasasPlazoFijoEntity2.setTipoPF("03");

		tipoPF.add(tasasPlazoFijoEntity1);
		tipoPF.add(tasasPlazoFijoEntity2);

		ConsultaTasasPlazoFijoBPrivOutEntity plazosFijosDisponibles = new ConsultaTasasPlazoFijoBPrivOutEntity();
		plazosFijosDisponibles.setCantTipoPF(new Long("57"));
		plazosFijosDisponibles.setCodigoRetornoExtendido("0520000000000");
		plazosFijosDisponibles.setHeaderTrama(
				"200000000000P04HTML0009900010301FRQF31  ********00722469000000082017060715151100000000IBF00AMU000000000000CNSTASPFCN1200000            01576531    00        00 015115377201706071516180000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		plazosFijosDisponibles.setListaTasas(tipoPF);

		Mockito.when(plazoFijoDAO.consultarTasas(Matchers.any(Cliente.class), BANCA_PERSONAL, false))
				.thenReturn(plazosFijosDisponibles);

		Mockito.when(plazoFijoDAO.simularPlazoFijo(Matchers.any(SimulacionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), false)).thenReturn(outDAO);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<SimularPlazoFijoOutDTO> respuesta = plazoFijoBOImpl.simularPlazoFijo(inDTO, cliente, 1);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	/**
	 * simularPF caso ERROR Numero Cuenta No valido.
	 * 
	 * @throws DAOException
	 */

	@Test
	public void simularPF_ERROR_NumeroCuenta() throws DAOException {

		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		inDTO.setDivisa("$");
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("999-999999/9");
		inDTO.setPlazo("500");
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta("02");
		cuenta.setNroSucursal("000");
		cuenta.setNroCuentaProducto("000000000638801");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);
		cliente.setCuentas(cuentas);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<SimularPlazoFijoOutDTO> respuesta = plazoFijoBOImpl.simularPlazoFijo(inDTO, cliente, 1);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	/**
	 * Calcular Intereses caso OK
	 * 
	 * @throws DAOException
	 */

	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void calcularIntereses_OK() throws DAOException {

		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		inDTO.setDivisa("$");
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("000-063880/1");
		inDTO.setPlazo("500");
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta("02");
		cuenta.setNroSucursal("000");
		cuenta.setNroCuentaProducto("000000000638801");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);
		cliente.setCuentas(cuentas);

		SimulacionPlazoFijoOutEntity outDAO = new SimulacionPlazoFijoOutEntity();
		outDAO.setAjusteAlAlta("00000000");
		outDAO.setCantidadRepeticionesInteresante(new Long(0));
		outDAO.setCantImpuestos(new Long(0));
		outDAO.setCodigoRetornoExtendido("0055200000000");
		outDAO.setDescProducto("PLAZO FIJO          ");
		outDAO.setDescSubproducto("PF TRADICIONAL      ");
		outDAO.setFechaAltaReal("2017-06-01");
		outDAO.setFechaMinimaPrecancelar("         ");
		outDAO.setFechaProven("2018-10-15");
		outDAO.setHeaderTrama(
				"200000000000P04HTML0009900010301FRQF31  ********00965471000000062017060115034400000000IBF0095O000000000000CNSSIMPFCN1400000            01576531    00        00 015017332201706011504470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		outDAO.setImporte("000000000890000");
		outDAO.setImporteADebitar("000000000890000");
		outDAO.setImpuCap("000000000000000");
		outDAO.setImpuInt("000000000000000");
		outDAO.setIndicadorPrecancelable("N");
		outDAO.setInteres("000000000342053");
		outDAO.setMinimoDiasPrecancelar("00000");
		outDAO.setPerLiq("V");
		outDAO.setPlazo("00501");
		outDAO.setPorcentajePenalizacion("00000000");
		outDAO.setProducto("01");
		outDAO.setSignoAjusteAlAlta("+");
		outDAO.setSubproducto("0003");
		outDAO.setTarifa("MF");
		outDAO.setTasa("02800000");
		outDAO.setTasaPrimerTramo("000000000");
		outDAO.setTasaRenovacion("00200000");
		outDAO.setTasaVarMinimoGarantizada("000000000");
		outDAO.setTipoPF("03");

		List<TasasPlazoFijoEntity> tipoPF = new ArrayList<TasasPlazoFijoEntity>();

		TasasPlazoFijoEntity tasasPlazoFijoEntity1 = new TasasPlazoFijoEntity();
		tasasPlazoFijoEntity1.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity1.setImporte("99999999999999");
		tasasPlazoFijoEntity1.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity1.setMoneda("ARS");
		tasasPlazoFijoEntity1.setPlazo("00030");
		tasasPlazoFijoEntity1.setTipoPF("03");

		TasasPlazoFijoEntity tasasPlazoFijoEntity2 = new TasasPlazoFijoEntity();
		tasasPlazoFijoEntity2.setDescripcionSubproducto("TRADICIONAL       ");
		tasasPlazoFijoEntity2.setImporte("99999999999999");
		tasasPlazoFijoEntity2.setImporteMinimo("00000000050000");
		tasasPlazoFijoEntity2.setMoneda("ARS");
		tasasPlazoFijoEntity2.setPlazo("00045");
		tasasPlazoFijoEntity2.setTipoPF("03");

		tipoPF.add(tasasPlazoFijoEntity1);
		tipoPF.add(tasasPlazoFijoEntity2);

		Mockito.when(plazoFijoDAO.simularPlazoFijo(Matchers.any(SimulacionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), false)).thenReturn(outDAO);
		Respuesta<SimularPlazoFijoOutDTO> respuestaSimular = new Respuesta<SimularPlazoFijoOutDTO>();
		respuestaSimular.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(TransferenciaDTO.class)))
				.thenReturn(respuestaSimular);

		Respuesta<InteresesDTO> respuesta = plazoFijoBOImpl.calcularIntereses(inDTO, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	/**
	 * Calcular Intereses caso ERROR DAOException.
	 * 
	 * @throws DAOException
	 */

	@Ignore
	@Test
	public void calcularIntereses_ERROR() throws DAOException {

		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		inDTO.setDivisa("$");
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("000-063880/1");
		inDTO.setPlazo("500");
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta("02");
		cuenta.setNroSucursal("000");
		cuenta.setNroCuentaProducto("000000000638801");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);
		cliente.setCuentas(cuentas);

		Mockito.when(plazoFijoDAO.simularPlazoFijo(Matchers.any(SimulacionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), false)).thenThrow(new DAOException());

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<InteresesDTO> respuesta = plazoFijoBOImpl.calcularIntereses(inDTO, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	/**
	 * Calcular Intereses caso ERROR Numero Cuenta No valido.
	 * 
	 * @throws DAOException
	 */

	@Test
	public void calcularIntereses_ERROR_NumeroCuenta() throws DAOException {

		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		inDTO.setDivisa("$");
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("999-999999/9");
		inDTO.setPlazo("500");
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta("02");
		cuenta.setNroSucursal("000");
		cuenta.setNroCuentaProducto("000000000638801");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuenta);
		cliente.setCuentas(cuentas);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<InteresesDTO> respuesta = plazoFijoBOImpl.calcularIntereses(inDTO, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	/**
	 * Finalizar Plazo Fijo OK.
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 */
	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void finalizarPlazoFijoTestOK() throws DAOException, BusinessException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FinalizarPlazoFijoDTO finalizarPlazoFijoDTO = new FinalizarPlazoFijoDTO();
		String nroCuentaProducto = "114-123456/7";
		finalizarPlazoFijoDTO.setNroCuentaDebito(nroCuentaProducto);
		finalizarPlazoFijoDTO.setDivisa("$");
		finalizarPlazoFijoDTO.setPlazo("00180");
		finalizarPlazoFijoDTO.setImporteCertificado(new BigDecimal("000000001250000"));
		finalizarPlazoFijoDTO.setTasa("00127000");
		finalizarPlazoFijoDTO.setCantidadDias("00060");
		finalizarPlazoFijoDTO.setTasaVariable("1200");
		finalizarPlazoFijoDTO.setMinimoDiasPrecancelar("00030");
		finalizarPlazoFijoDTO.setCotizacionCodigoUr(new BigDecimal("1"));
		finalizarPlazoFijoDTO.setSaldoInicUr(new BigDecimal("2"));
		finalizarPlazoFijoDTO.setTasaEfectiva(new BigDecimal("3"));

		ImposicionPlazoFijoOutEntity rtaDAO = new ImposicionPlazoFijoOutEntity();
		rtaDAO.setPlazo("00180");
		rtaDAO.setImporteCertificado("1025000");
		rtaDAO.setEntidadCuentaPlazo("000");
		rtaDAO.setNumeroCuentaPlazo("1234");
		rtaDAO.setSecuencia("001");
		rtaDAO.setMinimoDiasPrecancelar("00030");

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(plazoFijoDAO.confirmarConstitucion(Matchers.any(ImposicionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), BANCA_RETAIL, false)).thenReturn(rtaDAO);

		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("Constitucion del plazo fijo: {0} por el valor de {1} en un plazo de {2} dias.");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<FinalizarPlazoFijoDTO> rtaFactory = new Respuesta<FinalizarPlazoFijoDTO>();
		Mockito.when(
				respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FinalizarPlazoFijoDTO.class)))
				.thenReturn(rtaFactory);

		Respuesta<FinalizarPlazoFijoDTO> respuestaFinalizar = new Respuesta<FinalizarPlazoFijoDTO>();
		respuestaFinalizar.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(TransferenciaDTO.class)))
				.thenReturn(respuestaFinalizar);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cliente.setCuentas(cuentas);
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		String nroSucursal = "114";
		cuenta.setNroSucursal(nroSucursal);
		String nroCuentaProducto2 = "1234567";
		cuenta.setNroCuentaProducto(nroCuentaProducto2);
		cuenta.setTipoCuenta("02");
		Respuesta<FinalizarPlazoFijoDTO> resultadoBO = plazoFijoBOImpl.finalizarPlazoFijo(finalizarPlazoFijoDTO,
				cliente);

		Assert.assertNotNull(resultadoBO);
		Assert.assertEquals(resultadoBO.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	/**
	 * Finalizar Plazo Fijo DAOException.
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 */
	@Ignore
	@Test
	public void finalizarPlazoFijoTestDAOException() throws DAOException, BusinessException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();

		FinalizarPlazoFijoDTO finalizarPlazoFijoDTO = new FinalizarPlazoFijoDTO();
		String nroCuentaProducto = "114-123456/7";
		finalizarPlazoFijoDTO.setNroCuentaDebito(nroCuentaProducto);
		finalizarPlazoFijoDTO.setDivisa("$");
		finalizarPlazoFijoDTO.setPlazo("00180");
		finalizarPlazoFijoDTO.setImporteCertificado(new BigDecimal("000000001250000"));
		finalizarPlazoFijoDTO.setTasa("00127000");
		finalizarPlazoFijoDTO.setCantidadDias("00060");
		finalizarPlazoFijoDTO.setTasaVariable("1200");
		finalizarPlazoFijoDTO.setCotizacionCodigoUr(new BigDecimal("1"));
		finalizarPlazoFijoDTO.setSaldoInicUr(new BigDecimal("2"));
		finalizarPlazoFijoDTO.setTasaEfectiva(new BigDecimal("3"));

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(plazoFijoDAO.confirmarConstitucion(Matchers.any(ImposicionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), BANCA_RETAIL, false)).thenThrow(new DAOException());

		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("<p>No pudimos realizar la <b>{0}</b> por <b>{1}</b> a <b>{2}</b> días.</p>");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(
				respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(Matchers.anyString(), Matchers.anyString()))
				.thenReturn(responseFactoryError);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cliente.setCuentas(cuentas);
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		String nroSucursal = "114";
		cuenta.setNroSucursal(nroSucursal);
		String nroCuentaProducto2 = "1234567";
		cuenta.setNroCuentaProducto(nroCuentaProducto2);
		cuenta.setTipoCuenta("02");
		Respuesta<FinalizarPlazoFijoDTO> resultadoBO = plazoFijoBOImpl.finalizarPlazoFijo(finalizarPlazoFijoDTO,
				cliente);

		Assert.assertNotNull(resultadoBO);
		Assert.assertEquals(resultadoBO, responseFactoryError);
	}

	/**
	 * Finalizar Plazo Fijo DAOException con reintento.
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 */
	@Ignore
	@Test
	public void finalizarPlazoFijoTestDAOException_ConReintento() throws DAOException, BusinessException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();

		FinalizarPlazoFijoDTO finalizarPlazoFijoDTO = new FinalizarPlazoFijoDTO();
		String nroCuentaProducto = "114-123456/7";
		finalizarPlazoFijoDTO.setNroCuentaDebito(nroCuentaProducto);
		finalizarPlazoFijoDTO.setDivisa("$");
		finalizarPlazoFijoDTO.setPlazo("00180");
		finalizarPlazoFijoDTO.setImporteCertificado(new BigDecimal("000000001250000"));
		finalizarPlazoFijoDTO.setTasa("00127000");
		finalizarPlazoFijoDTO.setCantidadDias("00060");
		finalizarPlazoFijoDTO.setTasaVariable("1200");
		finalizarPlazoFijoDTO.setCotizacionCodigoUr(new BigDecimal("1"));
		finalizarPlazoFijoDTO.setSaldoInicUr(new BigDecimal("2"));
		finalizarPlazoFijoDTO.setTasaEfectiva(new BigDecimal("3"));

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(plazoFijoDAO.confirmarConstitucion(Matchers.any(ImposicionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), BANCA_RETAIL, false)).thenThrow(new DAOException());

		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("<p>No pudimos realizar la <b>{0}</b> por <b>{1}</b> a <b>{2}</b> días.</p>");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(
				respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(Matchers.anyString(), Matchers.anyString()))
				.thenReturn(responseFactoryError);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cliente.setCuentas(cuentas);
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		String nroSucursal = "114";
		cuenta.setNroSucursal(nroSucursal);
		String nroCuentaProducto2 = "1234567";
		cuenta.setNroCuentaProducto(nroCuentaProducto2);
		cuenta.setTipoCuenta("02");
		Respuesta<FinalizarPlazoFijoDTO> resultadoBO = plazoFijoBOImpl.finalizarPlazoFijo(finalizarPlazoFijoDTO,
				cliente);

		Assert.assertNotNull(resultadoBO);
		Assert.assertEquals(resultadoBO, responseFactoryError);
	}

	/**
	 * Finalizar Plazo Fijo Fallo Generico.
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 */
	@Ignore
	@Test
	public void finalizarPlazoFijoTestFalloGenerico() throws DAOException, BusinessException {
		FinalizarPlazoFijoDTO finalizarPlazoFijoDTO = new FinalizarPlazoFijoDTO();
		String nroCuentaProducto = "114-123456/7";
		finalizarPlazoFijoDTO.setNroCuentaDebito(nroCuentaProducto);
		finalizarPlazoFijoDTO.setDivisa("$");
		finalizarPlazoFijoDTO.setPlazo("00180");
		finalizarPlazoFijoDTO.setImporteCertificado(new BigDecimal("000000001250000"));
		finalizarPlazoFijoDTO.setTasa("00127000");
		finalizarPlazoFijoDTO.setCantidadDias("00060");
		finalizarPlazoFijoDTO.setTasaVariable("1200");
		Mockito.when(plazoFijoDAO.confirmarConstitucion(Matchers.any(ImposicionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), BANCA_RETAIL, false)).thenThrow(new DAOException());

		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("<p>No pudimos realizar la <b>{0}</b> por <b>{1}</b> a <b>{2}</b> días.</p>");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(responseFactoryError);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cliente.setCuentas(cuentas);
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		String nroSucursal = "114";
		cuenta.setNroSucursal(nroSucursal);
		String nroCuentaProducto2 = "1234567";
		cuenta.setNroCuentaProducto(nroCuentaProducto2);
		cuenta.setTipoCuenta("02");
		Respuesta<FinalizarPlazoFijoDTO> resultadoBO = plazoFijoBOImpl.finalizarPlazoFijo(finalizarPlazoFijoDTO,
				cliente);

		Assert.assertNotNull(resultadoBO);
		Assert.assertEquals(resultadoBO, responseFactoryError);
	}

	/**
	 * Finalizar Plazo Fijo TimeOutException.
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 */
	@Ignore
	@Test
	public void finalizarPlazoFijoTestTimeOutException() throws DAOException, BusinessException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FinalizarPlazoFijoDTO finalizarPlazoFijoDTO = new FinalizarPlazoFijoDTO();
		String nroCuentaProducto = "114-123456/7";
		finalizarPlazoFijoDTO.setNroCuentaDebito(nroCuentaProducto);
		finalizarPlazoFijoDTO.setDivisa("$");
		finalizarPlazoFijoDTO.setPlazo("00180");
		finalizarPlazoFijoDTO.setImporteCertificado(new BigDecimal("000000001250000"));
		finalizarPlazoFijoDTO.setTasa("00127000");
		finalizarPlazoFijoDTO.setCantidadDias("00060");
		finalizarPlazoFijoDTO.setTasaVariable("1200");
		finalizarPlazoFijoDTO.setCotizacionCodigoUr(new BigDecimal("1"));
		finalizarPlazoFijoDTO.setSaldoInicUr(new BigDecimal("2"));
		finalizarPlazoFijoDTO.setTasaEfectiva(new BigDecimal("3"));

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mockito.when(plazoFijoDAO.confirmarConstitucion(Matchers.any(ImposicionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class), BANCA_RETAIL, false)).thenThrow(new TimeOutException(""));

		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("<p>No pudimos realizar la <b>{0}</b> por <b>{1}</b> a <b>{2}</b> días.</p>");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(
				respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(Matchers.anyString(), Matchers.anyString()))
				.thenReturn(responseFactoryError);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cliente.setCuentas(cuentas);
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		String nroSucursal = "114";
		cuenta.setNroSucursal(nroSucursal);
		String nroCuentaProducto2 = "1234567";
		cuenta.setNroCuentaProducto(nroCuentaProducto2);
		cuenta.setTipoCuenta("02");
		Respuesta<FinalizarPlazoFijoDTO> resultadoBO = plazoFijoBOImpl.finalizarPlazoFijo(finalizarPlazoFijoDTO,
				cliente);

		Assert.assertNotNull(resultadoBO);
		Assert.assertEquals(resultadoBO, responseFactoryError);
	}

	/**
	 * Plazo Fijo Ver comprobante Precancelable OK.
	 * 
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void plazoFijoVerComprobante_Precancelable_OK() throws DAOException {

		ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO = new ComprobantePlazoFijoInDTO();
		comprobantePlazoFijoInDTO.setCodigoPlazo("16");
		comprobantePlazoFijoInDTO.setNumeroComprobante("000091170-00037");
		comprobantePlazoFijoInDTO.setMinimoDiasPrecancelar("2017-07-31");
		comprobantePlazoFijoInDTO.setPorcentajePenalizacion("00080000");
		comprobantePlazoFijoInDTO.setPlazo("361");

		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaLegal.setRespuesta("respuesta Mensaje Legal");
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);

		Respuesta<ComprobantePlazoFijoOutDTO> respuestaComprobante = new Respuesta<ComprobantePlazoFijoOutDTO>();
		respuestaComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(ComprobantePlazoFijoOutDTO.class))).thenReturn(respuestaComprobante);

		Respuesta<ComprobantePlazoFijoOutDTO> respuesta = plazoFijoBOImpl.verComprobante(comprobantePlazoFijoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	/**
	 * Plazo Fijo Ver comprobante Precancelable ERROR.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void plazoFijoVerComprobante_Precancelable_ERROR() throws DAOException {

		ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO = new ComprobantePlazoFijoInDTO();
		comprobantePlazoFijoInDTO.setCodigoPlazo("16");
		comprobantePlazoFijoInDTO.setNumeroComprobante("000091170-00037");
		comprobantePlazoFijoInDTO.setMinimoDiasPrecancelar("2017-07-31");
		comprobantePlazoFijoInDTO.setPorcentajePenalizacion("00080000");
		comprobantePlazoFijoInDTO.setPlazo("361");

		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaLegal.setRespuesta("respuesta Mensaje Legal");
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<ComprobantePlazoFijoOutDTO> respuesta = plazoFijoBOImpl.verComprobante(comprobantePlazoFijoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	/**
	 * Plazo Fijo Ver comprobante Tradicional OK.
	 * 
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void plazoFijoVerComprobante_Tradicional_OK() throws DAOException {

		ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO = new ComprobantePlazoFijoInDTO();
		comprobantePlazoFijoInDTO.setCodigoPlazo("03");
		comprobantePlazoFijoInDTO.setNumeroComprobante("000091170-00037");
		comprobantePlazoFijoInDTO.setMinimoDiasPrecancelar("2017-07-31");
		comprobantePlazoFijoInDTO.setPorcentajePenalizacion("00080000");
		comprobantePlazoFijoInDTO.setPlazo("361");

		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaLegal.setRespuesta("respuesta Mensaje Legal");
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);

		Respuesta<ComprobantePlazoFijoOutDTO> respuestaComprobante = new Respuesta<ComprobantePlazoFijoOutDTO>();
		respuestaComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(ComprobantePlazoFijoOutDTO.class))).thenReturn(respuestaComprobante);

		Respuesta<ComprobantePlazoFijoOutDTO> respuesta = plazoFijoBOImpl.verComprobante(comprobantePlazoFijoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	/**
	 * Plazo Fijo Ver comprobante Tradicional ERROR.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void plazoFijoVerComprobante_Tradicional_ERROR() throws DAOException {

		ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO = new ComprobantePlazoFijoInDTO();
		comprobantePlazoFijoInDTO.setCodigoPlazo("03");
		comprobantePlazoFijoInDTO.setNumeroComprobante("000091170-00037");
		comprobantePlazoFijoInDTO.setMinimoDiasPrecancelar("2017-07-31");
		comprobantePlazoFijoInDTO.setPorcentajePenalizacion("00080000");
		comprobantePlazoFijoInDTO.setPlazo("361");

		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaLegal.setRespuesta("respuesta Mensaje Legal");
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<ComprobantePlazoFijoOutDTO> respuesta = plazoFijoBOImpl.verComprobante(comprobantePlazoFijoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	/**
	 * Plazo Fijo Ver comprobante Interesante OK.
	 * 
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void plazoFijoVerComprobante_Interesante_OK() throws DAOException {

		ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO = new ComprobantePlazoFijoInDTO();
		comprobantePlazoFijoInDTO.setCodigoPlazo("17");
		comprobantePlazoFijoInDTO.setNumeroComprobante("000091170-00037");
		comprobantePlazoFijoInDTO.setMinimoDiasPrecancelar("2017-07-31");
		comprobantePlazoFijoInDTO.setPorcentajePenalizacion("00080000");
		comprobantePlazoFijoInDTO.setPlazo("361");

		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaLegal.setRespuesta("respuesta Mensaje Legal");
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);

		Respuesta<ComprobantePlazoFijoOutDTO> respuestaComprobante = new Respuesta<ComprobantePlazoFijoOutDTO>();
		respuestaComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(ComprobantePlazoFijoOutDTO.class))).thenReturn(respuestaComprobante);

		Respuesta<ComprobantePlazoFijoOutDTO> respuesta = plazoFijoBOImpl.verComprobante(comprobantePlazoFijoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	/**
	 * Plazo Fijo Ver comprobante Interesante ERROR.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void plazoFijoVerComprobante_Interesante_ERROR() throws DAOException {

		ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO = new ComprobantePlazoFijoInDTO();
		comprobantePlazoFijoInDTO.setCodigoPlazo("17");
		comprobantePlazoFijoInDTO.setNumeroComprobante("000091170-00037");
		comprobantePlazoFijoInDTO.setMinimoDiasPrecancelar("2017-07-31");
		comprobantePlazoFijoInDTO.setPorcentajePenalizacion("00080000");
		comprobantePlazoFijoInDTO.setPlazo("361");

		Respuesta<String> respuestaLegal = new Respuesta<String>();
		respuestaLegal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaLegal.setRespuesta("respuesta Mensaje Legal");
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<ComprobantePlazoFijoOutDTO> respuesta = plazoFijoBOImpl.verComprobante(comprobantePlazoFijoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	/**
	 * Plazo Fijo Ver comprobante ERROR GENERICO.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void plazoFijoVerComprobante_ERROR_GENERICO() throws DAOException {

		ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO = new ComprobantePlazoFijoInDTO();
		comprobantePlazoFijoInDTO.setCodigoPlazo("01");
		comprobantePlazoFijoInDTO.setNumeroComprobante("000091170-00037");
		comprobantePlazoFijoInDTO.setMinimoDiasPrecancelar("2017-07-31");
		comprobantePlazoFijoInDTO.setPorcentajePenalizacion("00080000");
		comprobantePlazoFijoInDTO.setPlazo("361");

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<ComprobantePlazoFijoOutDTO> respuesta = plazoFijoBOImpl.verComprobante(comprobantePlazoFijoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}

	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void consultarTasas() throws DAOException {

		ConsultaTasasPlazoFijoBPrivOutEntity tasasRespuestaDAO = new ConsultaTasasPlazoFijoBPrivOutEntity();
		List<TasasPlazoFijoBPrivEntity> listaTasas = new ArrayList<TasasPlazoFijoBPrivEntity>();
		tasasRespuestaDAO.setListaTasas(listaTasas);
		TasasPlazoFijoBPrivEntity tasa1 = new TasasPlazoFijoBPrivEntity();
		listaTasas.add(tasa1);
		tasa1.setMoneda("ARS");
		tasa1.setTipoPF("16");
		TasasPlazoFijoBPrivEntity tasa2 = new TasasPlazoFijoBPrivEntity();
		listaTasas.add(tasa2);
		tasa2.setMoneda("ARS");
		tasa2.setTipoPF("16");
		Mockito.when(plazoFijoDAO.consultarTasas(Matchers.any(Cliente.class), Matchers.contains(BANCA_PERSONAL), false))
				.thenReturn(tasasRespuestaDAO);

		List<PlazoFijoEntity> listaPlazosHabilitados = new ArrayList<PlazoFijoEntity>();
		PlazoFijoEntity plazoFijo1 = new PlazoFijoEntity();
		listaPlazosHabilitados.add(plazoFijo1);
		plazoFijo1.setMoneda("0");
		plazoFijo1.setCodigoPlazo("16");
		PlazoFijoEntity plazoFijo2 = new PlazoFijoEntity();
		listaPlazosHabilitados.add(plazoFijo2);
		plazoFijo2.setMoneda("1");
		plazoFijo2.setCodigoPlazo("17");
		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijosHabilitados()).thenReturn(listaPlazosHabilitados);

		Respuesta<ConsultaTasasPlazosFijoView> rtaBO = new Respuesta<ConsultaTasasPlazosFijoView>();
		rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(ConsultaTasasPlazosFijoView.class))).thenReturn(rtaBO);

		Cliente cliente = new Cliente();
		String bancaSeleccionada = "bancaPrivada";
		Respuesta<ConsultaTasasPlazosFijoView> rtaTasasBO = plazoFijoBOImpl.consultarTasas(cliente, bancaSeleccionada);
		Assert.assertNotNull(rtaTasasBO);
		Assert.assertEquals(rtaTasasBO.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Ignore
	@Test
	public void consultarTasasException() throws DAOException {

		Mockito.when(plazoFijoDAO.consultarTasas(Matchers.any(Cliente.class), BANCA_PERSONAL, false))
				.thenThrow(new DAOException());

		Respuesta<Object> respuestaError = new Respuesta<Object>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaError);

		Cliente cliente = new Cliente();
		String bancaSeleccionada = "bancaPrivada";
		Respuesta<ConsultaTasasPlazosFijoView> rtaTasasBO = plazoFijoBOImpl.consultarTasas(cliente, bancaSeleccionada);
		Assert.assertNotNull(rtaTasasBO);
		Assert.assertEquals(rtaTasasBO.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Ignore
	@Test
	public void consultarTasasListaVacia() throws DAOException {

		ConsultaTasasPlazoFijoBPrivOutEntity tasasRespuestaDAO = new ConsultaTasasPlazoFijoBPrivOutEntity();
		List<TasasPlazoFijoBPrivEntity> listaTasas = new ArrayList<TasasPlazoFijoBPrivEntity>();
		tasasRespuestaDAO.setListaTasas(listaTasas);
		Mockito.when(plazoFijoDAO.consultarTasas(Matchers.any(Cliente.class), BANCA_PERSONAL, false))
				.thenReturn(tasasRespuestaDAO);

		List<PlazoFijoEntity> listaPlazosHabilitados = new ArrayList<PlazoFijoEntity>();
		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijosHabilitados()).thenReturn(listaPlazosHabilitados);

		Respuesta<Object> respuestaError = new Respuesta<Object>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaError);

		Cliente cliente = new Cliente();
		String bancaSeleccionada = "bancaPrivada";
		Respuesta<ConsultaTasasPlazosFijoView> rtaTasasBO = plazoFijoBOImpl.consultarTasas(cliente, bancaSeleccionada);
		Assert.assertNotNull(rtaTasasBO);
		Assert.assertEquals(rtaTasasBO.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	/**
	 * Plazo Fijo obtener tenencias OK
	 * 
	 * @throws DAOException
	 */
	@Ignore
	@SuppressWarnings("static-access")
	@Test
	public void obtenerTenenciasPlazoFijo_OK() throws DAOException {
		// TODO: EN EL METODO OBTENER CANAL SE UTILIZA EL CONTEXT, HAY QUE VER COMO
		// MOCKEAR ESO Y ARREGLARLO

		Cliente cliente = new Cliente();

		Cuenta cuenta = new Cuenta();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cuentasRetail.add(cuenta);

		List<ResultadoTenenciaValuadaDetallePFOL> resultado = new ArrayList<ResultadoTenenciaValuadaDetallePFOL>();

		ResultadoTenenciaValuadaDetallePFOL ResultadoTenenciaValuadaDetallePFOL1 = new ResultadoTenenciaValuadaDetallePFOL();
		ResultadoTenenciaValuadaDetallePFOL1.setCapitalInicial((BigDecimal.valueOf(17772)));
		ResultadoTenenciaValuadaDetallePFOL1.setFechaVencimiento("/Date(1395284400000-0300)/");
		ResultadoTenenciaValuadaDetallePFOL1.setDescripcionTipoPlazoFijo("Tradicional");
		ResultadoTenenciaValuadaDetallePFOL1.setTna((BigDecimal.valueOf(4)));
		ResultadoTenenciaValuadaDetallePFOL1.setCodSubProducto("1");
		ResultadoTenenciaValuadaDetallePFOL1.setMoneda("ARS");
		ResultadoTenenciaValuadaDetallePFOL1.setEstado("A");
		ResultadoTenenciaValuadaDetallePFOL1.setSucursalRadicacion(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL1.setPlazoVigencia(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL1.setFechaConstitucion("/Date(1395284400000-0300)/");
		ResultadoTenenciaValuadaDetallePFOL1.setFrecuenciaCobroIntereses(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL1.setAccionVencimiento("accion");
		ResultadoTenenciaValuadaDetallePFOL1.setCanal(BigDecimal.valueOf(10));

		ResultadoTenenciaValuadaDetallePFOL ResultadoTenenciaValuadaDetallePFOL2 = new ResultadoTenenciaValuadaDetallePFOL();
		ResultadoTenenciaValuadaDetallePFOL2.setCapitalInicial((BigDecimal.valueOf(14562)));
		ResultadoTenenciaValuadaDetallePFOL2.setFechaVencimiento("/Date(1395284400000-0300)/");
		ResultadoTenenciaValuadaDetallePFOL2.setDescripcionTipoPlazoFijo("Tradicional");
		ResultadoTenenciaValuadaDetallePFOL2.setTna((BigDecimal.valueOf(4)));
		ResultadoTenenciaValuadaDetallePFOL2.setCodSubProducto("1");
		ResultadoTenenciaValuadaDetallePFOL2.setMoneda("USD");
		ResultadoTenenciaValuadaDetallePFOL2.setEstado("A");
		ResultadoTenenciaValuadaDetallePFOL2.setSucursalRadicacion(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL2.setPlazoVigencia(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL2.setFechaConstitucion("/Date(1395284400000-0300)/");
		ResultadoTenenciaValuadaDetallePFOL2.setFrecuenciaCobroIntereses(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL2.setAccionVencimiento("accion");
		ResultadoTenenciaValuadaDetallePFOL2.setCanal(BigDecimal.valueOf(10));

		ResultadoTenenciaValuadaDetallePFOL ResultadoTenenciaValuadaDetallePFOL3 = new ResultadoTenenciaValuadaDetallePFOL();
		ResultadoTenenciaValuadaDetallePFOL3.setTenenciaValuada((BigDecimal.valueOf(40000.90).toString()));
		ResultadoTenenciaValuadaDetallePFOL3.setFechaVencimiento("/Date(1395284400000+0300)/");
		ResultadoTenenciaValuadaDetallePFOL3.setDescripcionTipoPlazoFijo("Tradicional");
		ResultadoTenenciaValuadaDetallePFOL3.setTna((BigDecimal.valueOf(4)));
		ResultadoTenenciaValuadaDetallePFOL3.setCodSubProducto("1");
		ResultadoTenenciaValuadaDetallePFOL3.setMoneda("USD");
		ResultadoTenenciaValuadaDetallePFOL3.setEstado("A");
		ResultadoTenenciaValuadaDetallePFOL3.setResultadoBruto("800.22");
		ResultadoTenenciaValuadaDetallePFOL3.setSucursalRadicacion(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL3.setPlazoVigencia(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL3.setFechaConstitucion("/Date(1395284400000-0300)/");
		ResultadoTenenciaValuadaDetallePFOL3.setFrecuenciaCobroIntereses(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL3.setAccionVencimiento("accion");
		ResultadoTenenciaValuadaDetallePFOL3.setCanal(BigDecimal.valueOf(10));

		resultado.add(ResultadoTenenciaValuadaDetallePFOL1);
		resultado.add(ResultadoTenenciaValuadaDetallePFOL2);
		resultado.add(ResultadoTenenciaValuadaDetallePFOL3);

		DatosRespuestaPF resultadoPF = new DatosRespuestaPF();
		resultadoPF.setResultado(resultado);
		// datosRespuestaPF.setResultado(resultado);

		cliente.setCuentasRetail(cuentasRetail);
		DetalleTenenciaValuadaPFEntity tenenciaValuada = new DetalleTenenciaValuadaPFEntity();
		tenenciaValuada.setCodigo(0);
		tenenciaValuada.setDatos(resultadoPF);

		List<PlazoFijoEntity> plazosFijos = new ArrayList<PlazoFijoEntity>();

		PlazoFijoEntity plazoFijoEntity1 = new PlazoFijoEntity();
		plazoFijoEntity1.setCodigoPlazo("1");
		plazoFijoEntity1.setPrioridad("1");
		plazoFijoEntity1.setDescripcion("Tradicional");

		PlazoFijoEntity plazoFijoEntity2 = new PlazoFijoEntity();
		plazoFijoEntity2.setCodigoPlazo("1");
		plazoFijoEntity2.setPrioridad("1");
		plazoFijoEntity2.setDescripcion("Tradicional");

		plazosFijos.add(plazoFijoEntity1);
		plazosFijos.add(plazoFijoEntity2);

		DescripcionAccionAlVencimientoOutEntity descripcionAccionAlVencimientoOutEntity = new DescripcionAccionAlVencimientoOutEntity();
		descripcionAccionAlVencimientoOutEntity.setDescripcion("descripcion0");
		DescripcionAccionAlVencimientoOutEntity descripcionAccionAlVencimientoOutEntity1 = new DescripcionAccionAlVencimientoOutEntity();
		descripcionAccionAlVencimientoOutEntity1.setDescripcion("descripcion1");
		DescripcionAccionAlVencimientoOutEntity descripcionAccionAlVencimientoOutEntity2 = new DescripcionAccionAlVencimientoOutEntity();
		descripcionAccionAlVencimientoOutEntity2.setDescripcion("descripcion2");

		List<DescripcionAccionAlVencimientoOutEntity> descripcionesAccionAlVencimiento = new ArrayList<DescripcionAccionAlVencimientoOutEntity>();

		descripcionesAccionAlVencimiento.add(descripcionAccionAlVencimientoOutEntity);
		descripcionesAccionAlVencimiento.add(descripcionAccionAlVencimientoOutEntity1);
		descripcionesAccionAlVencimiento.add(descripcionAccionAlVencimientoOutEntity2);

		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijos()).thenReturn(plazosFijos);
		Mockito.when(
				tenenciaValuadaDAO.obtenerTenenciaValuadaDetallePFOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(tenenciaValuada);

		Mockito.when(consultaPlazoFijoDAO.obtenerDescripcionAccionAlVencimiento(Matchers.anyString()))
				.thenReturn(descripcionesAccionAlVencimiento);

		Respuesta<TenenciaTotalPlazoFijoDTO> respuestaTenencia = new Respuesta<TenenciaTotalPlazoFijoDTO>();
		respuestaTenencia.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(TenenciaTotalPlazoFijoDTO.class)))
				.thenReturn(respuestaTenencia);

		ContextHolder contextHolder = new ContextHolder();
		ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
		contextHolder.setContext(applicationContext);
		when(applicationContext.getBean(Matchers.anyString())).thenReturn("Superlinea");

		Respuesta<TenenciaTotalPlazoFijoDTO> respuesta = plazoFijoBOImpl.obtenerTenenciasPlazoFijo(cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	/**
	 * obtenerTenenciasPlazoFijo Plazo Fijo ERROR SIN CUENTAS DEBITO.
	 * 
	 * @throws DAOException
	 */

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciasPlazoFijo_Error_SinCuentaDebito() throws DAOException {

		Respuesta<ComprobantePlazoFijoOutDTO> respuestaComprobante = new Respuesta<ComprobantePlazoFijoOutDTO>();
		respuestaComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(ComprobantePlazoFijoOutDTO.class))).thenReturn(respuestaComprobante);

		Cliente cliente = new Cliente();

		Cuenta cuenta = new Cuenta();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cuentasRetail.add(cuenta);

		List<ResultadoTenenciaValuadaDetallePFOL> resultadoLista = new ArrayList<ResultadoTenenciaValuadaDetallePFOL>();

		DatosRespuestaPF resultadoPF = new DatosRespuestaPF();
		resultadoPF.setResultado(resultadoLista);

		resultadoPF.setResultado(resultadoLista);

		cliente.setCuentasRetail(cuentasRetail);
		DetalleTenenciaValuadaPFEntity tenenciaValuada = new DetalleTenenciaValuadaPFEntity();
		tenenciaValuada.setCodigo(0);
		tenenciaValuada.setDatos(resultadoPF);

		Mockito.when(
				tenenciaValuadaDAO.obtenerTenenciaValuadaDetallePFOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(tenenciaValuada);

		Mockito.when(cuentaBO.hasCuentasMonetariasActivas(Matchers.any(Cliente.class))).thenReturn(false);

		Respuesta<TenenciaTotalPlazoFijoDTO> respuestaError = new Respuesta<TenenciaTotalPlazoFijoDTO>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(TenenciaTotalPlazoFijoDTO.class),
				Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaError);
		Respuesta<TenenciaTotalPlazoFijoDTO> respuesta = plazoFijoBOImpl.obtenerTenenciasPlazoFijo(cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	/**
	 * Plazo Fijo Error Sin Tenencias.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void obtenerTenenciasPlazoFijo_Error_SinTenencias() throws DAOException {

		Cliente cliente = new Cliente();

		Cuenta cuenta = new Cuenta();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cuentasRetail.add(cuenta);

		List<ResultadoTenenciaValuadaDetallePFOL> resultadoLista = new ArrayList<ResultadoTenenciaValuadaDetallePFOL>();
		DatosRespuestaPF resultadoPF = new DatosRespuestaPF();
		resultadoPF.setResultado(resultadoLista);

		cliente.setCuentasRetail(cuentasRetail);
		DetalleTenenciaValuadaPFEntity tenenciaValuada = new DetalleTenenciaValuadaPFEntity();
		tenenciaValuada.setCodigo(0);
		tenenciaValuada.setDatos(resultadoPF);

		List<PlazoFijoEntity> plazosFijos = new ArrayList<PlazoFijoEntity>();

		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijos()).thenReturn(plazosFijos);
		Mockito.when(
				tenenciaValuadaDAO.obtenerTenenciaValuadaDetallePFOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(tenenciaValuada);

		Mockito.when(cuentaBO.hasCuentasMonetariasActivas(Matchers.any(Cliente.class))).thenReturn(true);

		Respuesta<TenenciaTotalPlazoFijoDTO> respuestaError = new Respuesta<TenenciaTotalPlazoFijoDTO>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(TenenciaTotalPlazoFijoDTO.class),
				Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaError);

		Respuesta<TenenciaTotalPlazoFijoDTO> respuesta = plazoFijoBOImpl.obtenerTenenciasPlazoFijo(cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	/**
	 * Plazo Fijo Error obtenerTenenciaValuadaDetallePFOnline.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void obtenerTenenciasPlazoFijo_Error_obtenerTenenciaValuadaDetallePFOnline() throws DAOException {

		Cliente cliente = new Cliente();

		Cuenta cuenta = new Cuenta();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cuentasRetail.add(cuenta);

		List<ResultadoTenenciaValuadaDetallePFOL> resultadoLista = new ArrayList<ResultadoTenenciaValuadaDetallePFOL>();
		DatosRespuestaPF resultadoPF = new DatosRespuestaPF();
		resultadoPF.setResultado(resultadoLista);

		cliente.setCuentasRetail(cuentasRetail);
		DetalleTenenciaValuadaPFEntity tenenciaValuada = new DetalleTenenciaValuadaPFEntity();
		tenenciaValuada.setCodigo(0);
		tenenciaValuada.setDatos(resultadoPF);

		List<PlazoFijoEntity> plazosFijos = new ArrayList<PlazoFijoEntity>();

		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijos()).thenReturn(plazosFijos);
		Mockito.when(
				tenenciaValuadaDAO.obtenerTenenciaValuadaDetallePFOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenThrow(new DAOException());

		Respuesta<Object> respuestaError = new Respuesta<Object>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaError);

		Respuesta<TenenciaTotalPlazoFijoDTO> respuesta = plazoFijoBOImpl.obtenerTenenciasPlazoFijo(cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	/**
	 * Plazo Fijo Error obtenerTenenciaValuadaDetallePFOnline.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void obtenerTenenciasPlazoFijo_Error_obtenerPlazosFijos() throws DAOException {

		Cliente cliente = new Cliente();

		Cuenta cuenta = new Cuenta();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cuentasRetail.add(cuenta);
		List<ResultadoTenenciaValuadaDetallePFOL> resultadoLista = new ArrayList<ResultadoTenenciaValuadaDetallePFOL>();
		DatosRespuestaPF resultadoPF = new DatosRespuestaPF();
		resultadoPF.setResultado(resultadoLista);
		cliente.setCuentasRetail(cuentasRetail);
		DetalleTenenciaValuadaPFEntity tenenciaValuada = new DetalleTenenciaValuadaPFEntity();
		tenenciaValuada.setCodigo(0);
		tenenciaValuada.setDatos(resultadoPF);
		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijos()).thenThrow(new DAOException());
		Mockito.when(
				tenenciaValuadaDAO.obtenerTenenciaValuadaDetallePFOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(tenenciaValuada);

		Respuesta<Object> respuestaError = new Respuesta<Object>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaError);

		Respuesta<TenenciaTotalPlazoFijoDTO> respuesta = plazoFijoBOImpl.obtenerTenenciasPlazoFijo(cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	/**
	 * Plazo Fijo SolicitarPrecancelarPlazoFijo OK.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void plazoFijoSolicitarPrecancelarPlazoFijo_OK() throws DAOException {

		Cliente cliente = new Cliente();
		SolicitarPrecancelarInView solicitarPrecancelarInView = new SolicitarPrecancelarInView();
		solicitarPrecancelarInView.setCuentaPlazo("00720033001000111841");
		solicitarPrecancelarInView.setNumeroSecuencia("00027");
		solicitarPrecancelarInView.setFechaConstitucion("18/07/2017");

		ConsultaPrecancelablenPlazoFijoOutEntity consultaPrecancelablenPlazoFijoOutEntity = new ConsultaPrecancelablenPlazoFijoOutEntity();
		consultaPrecancelablenPlazoFijoOutEntity.setPorcentajePenalizacion("00080000");
		consultaPrecancelablenPlazoFijoOutEntity.setPlazoMinimoPrecancelacion("00030");

		Respuesta<SolicitarPrecancelarOutDTO> respuestaPrecancelar = new Respuesta<SolicitarPrecancelarOutDTO>();
		respuestaPrecancelar.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(SolicitarPrecancelarOutDTO.class)))
				.thenReturn(respuestaPrecancelar);

		Mockito.when(plazoFijoDAO.consultarPrecancelable(Matchers.any(ConsultaPrecancelablePlazoFijoInEntity.class),
				Matchers.any(Cliente.class), Matchers.anyBoolean()))
				.thenReturn(consultaPrecancelablenPlazoFijoOutEntity);

		Respuesta<SolicitarPrecancelarOutDTO> respuesta = plazoFijoBOImpl
				.solicitarPrecancelarPlazoFijo(solicitarPrecancelarInView, cliente, false, false);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	/**
	 * Plazo Fijo SolicitarPrecancelarPlazoFijo OK.
	 * 
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void plazoFijoSolicitarPrecancelarPlazoFijoDAOException() throws DAOException {

		Cliente cliente = new Cliente();
		SolicitarPrecancelarInView solicitarPrecancelarInView = new SolicitarPrecancelarInView();
		solicitarPrecancelarInView.setCuentaPlazo("00720033001000111841");
		solicitarPrecancelarInView.setNumeroSecuencia("00027");
		solicitarPrecancelarInView.setFechaConstitucion("18/07/2017");

		Respuesta<SolicitarPrecancelarOutDTO> responseFactoryError = new Respuesta<SolicitarPrecancelarOutDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList()))
				.thenReturn(responseFactoryError);
		Mockito.when(plazoFijoDAO.consultarPrecancelable(Matchers.any(ConsultaPrecancelablePlazoFijoInEntity.class),
				Matchers.any(Cliente.class), Matchers.anyBoolean())).thenThrow(new DAOException());

		Respuesta<SolicitarPrecancelarOutDTO> respuesta = plazoFijoBOImpl
				.solicitarPrecancelarPlazoFijo(solicitarPrecancelarInView, cliente, false, false);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	/**
	 * Plazo Fijo SolicitarPrecancelarPlazoFijo Warninig.
	 * 
	 * @throws DAOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void plazoFijoSolicitarPrecancelarPlazoFijo_Warninig() throws DAOException {

		Cliente cliente = new Cliente();
		SolicitarPrecancelarInView solicitarPrecancelarInView = new SolicitarPrecancelarInView();
		solicitarPrecancelarInView.setCuentaPlazo("00720033001000111841");
		solicitarPrecancelarInView.setNumeroSecuencia("00027");

		Date fechaActual = new Date();
		solicitarPrecancelarInView.setFechaConstitucion(new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));

		ConsultaPrecancelablenPlazoFijoOutEntity consultaPrecancelablenPlazoFijoOutEntity = new ConsultaPrecancelablenPlazoFijoOutEntity();
		consultaPrecancelablenPlazoFijoOutEntity.setPorcentajePenalizacion("00080000");
		consultaPrecancelablenPlazoFijoOutEntity.setPlazoMinimoPrecancelacion("00030");

		Respuesta respuestaPrecancelar = new Respuesta();
		respuestaPrecancelar.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.anyString(),
				Matchers.anyString())).thenReturn(respuestaPrecancelar);

		Mockito.when(plazoFijoDAO.consultarPrecancelable(Matchers.any(ConsultaPrecancelablePlazoFijoInEntity.class),
				Matchers.any(Cliente.class), Matchers.anyBoolean()))
				.thenReturn(consultaPrecancelablenPlazoFijoOutEntity);
		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje(
				"Aun no pasaron {0} días desde la constitución del Plazo Fijo. Vas a poder cancelarlo a partir del {1}");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);

		Respuesta<SolicitarPrecancelarOutDTO> respuesta = plazoFijoBOImpl
				.solicitarPrecancelarPlazoFijo(solicitarPrecancelarInView, cliente, false, false);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void modificarAccionOkTest() throws DAOException {
		ModificarAccionInView inView = new ModificarAccionInView();
		String cuentaPlazo = "12345678900987654321";
		inView.setCuentaPlazo(cuentaPlazo);
		String secuencia = "43";
		inView.setSecuencia(secuencia);
		String moneda = "ARS";
		inView.setMoneda(moneda);
		String codigoAccion = "DC";
		inView.setCodigoAccion(codigoAccion);

		Cliente cliente = new Cliente();

		MantenimientoPlazoFijoOutEntity outDaoMock = new MantenimientoPlazoFijoOutEntity();
		when(plazoFijoDAO.confirmarAccionVencimiento(Matchers.any(MantenimientoPlazoFijoInEntity.class),
				Matchers.any(Cliente.class))).thenReturn(outDaoMock);

		Respuesta<Object> respuestaMock = new Respuesta<Object>();
		respuestaMock.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.anyString()))
				.thenReturn(respuestaMock);

		Respuesta<String> respuesta = plazoFijoBOImpl.modificarAccionVencimiento(inView, cliente);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void modificarAccionTimeoutTest() throws DAOException {
		ModificarAccionInView inView = new ModificarAccionInView();
		String cuentaPlazo = "12345678900987654321";
		inView.setCuentaPlazo(cuentaPlazo);
		String secuencia = "43";
		inView.setSecuencia(secuencia);
		String moneda = "ARS";
		inView.setMoneda(moneda);
		String codigoAccion = "DC";
		inView.setCodigoAccion(codigoAccion);

		Cliente cliente = new Cliente();

		when(plazoFijoDAO.confirmarAccionVencimiento(Matchers.any(MantenimientoPlazoFijoInEntity.class),
				Matchers.any(Cliente.class))).thenThrow(new TimeOutException("mensaje"));

		Respuesta<Object> respuestaMock = new Respuesta<Object>();
		respuestaMock.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTipoError(TipoError.TIMEOUT.toString());
		itemMensajeRespuesta.add(item);
		respuestaMock.setItemMensajeRespuesta(itemMensajeRespuesta);
		when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaMock);

		Respuesta<String> respuesta = plazoFijoBOImpl.modificarAccionVencimiento(inView, cliente);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), TipoError.TIMEOUT.toString());
	}

	/**
	 * precancelar OK
	 * 
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void precancelarOK() throws DAOException {

		Cliente cliente = new Cliente();

		SimularPrecancelarInView inView = new SimularPrecancelarInView();
		inView.setCuentaPlazo("00720033001000111841");
		inView.setMoneda("ARS");
		inView.setNumeroCuentaDestino("000-063880/12");
		inView.setSecuencia("68");
		inView.setTipoCuentaDestino("68");
		inView.setTipoPlazo("Precancelable");

		SimulacionPrecancelableOutEntity simulacionPrecancelableOutEntity = new SimulacionPrecancelableOutEntity();
		simulacionPrecancelableOutEntity.setCuentaPlazo("00720194001000020774");
		simulacionPrecancelableOutEntity.setSecuencia("00009");
		simulacionPrecancelableOutEntity.setFormaPago("D");
		simulacionPrecancelableOutEntity.setCodigoOperacion("0778");
		simulacionPrecancelableOutEntity.setCapital("000000000500000");
		simulacionPrecancelableOutEntity.setInteresesPrecancelacion("000000000212262");
		simulacionPrecancelableOutEntity.setImpuestosPrecancelacion("000000000000000");
		simulacionPrecancelableOutEntity.setImpuestosAlta("000000000000000");
		simulacionPrecancelableOutEntity.setPorcentajePenalizacion("00080000");
		simulacionPrecancelableOutEntity.setTotalAPagar("000000000712262");
		simulacionPrecancelableOutEntity.setInteresAlVencimiento("000000000032055");
		simulacionPrecancelableOutEntity.setImpuestosAlVencimiento("000000000000000");
		simulacionPrecancelableOutEntity.setInteresAFecha("000000000165616");
		simulacionPrecancelableOutEntity.setImpuestosAFecha("000000000000000");
		simulacionPrecancelableOutEntity.setTasaRealPF("01300000");
		simulacionPrecancelableOutEntity.setPlazoRealPF("00180");
		simulacionPrecancelableOutEntity.setPlazoActual("00930");
		simulacionPrecancelableOutEntity.setFechaAltaPF("30072014");
		simulacionPrecancelableOutEntity.setFechaVencimientoPF("26012015");
		simulacionPrecancelableOutEntity.setCuentaCredito("00720194005000096717");
		simulacionPrecancelableOutEntity.setDivisaCuentaCredito("ARS");
		simulacionPrecancelableOutEntity.setDiferenciaIntAFecha("000000000046646");
		simulacionPrecancelableOutEntity.setDiferenciaImpAFecha("000000000000000");
		simulacionPrecancelableOutEntity.setDiferenciaIntAVencimiento("000000000180207");
		simulacionPrecancelableOutEntity.setDiferenciaImpAFecha("000000000000000");
		simulacionPrecancelableOutEntity.setTasaMinimaActual("02025000");
		simulacionPrecancelableOutEntity.setTasaMaximaActual("000002025000");
		simulacionPrecancelableOutEntity.setImporteReajuste("0000000000000000");
		simulacionPrecancelableOutEntity.setCantImpuestos(new Long(0));

		when(plazoFijoDAO.simularPrecancelable(Matchers.any(PrecancelacionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class))).thenReturn(simulacionPrecancelableOutEntity);

		Respuesta<Object> respuestaMock = new Respuesta<Object>();
		respuestaMock.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.anyString()))
				.thenReturn(respuestaMock);

		Respuesta<SimulacionPrecancelableDTO> respuesta = plazoFijoBOImpl.precancelar(cliente, inView, "D");

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	@Test
	public void precancelarERROR_DAOException() throws DAOException {

		Cliente cliente = new Cliente();

		PrecancelacionPlazoFijoInEntity precancelacionPlazoFijoInEntity = new PrecancelacionPlazoFijoInEntity();
		precancelacionPlazoFijoInEntity.setCuentaPlazo("00720194001000020774");
		precancelacionPlazoFijoInEntity.setDivisaCuentaCredito("ARS");
		precancelacionPlazoFijoInEntity.setNroCuentaCredito("0096717");
		precancelacionPlazoFijoInEntity.setOpcion("D");
		precancelacionPlazoFijoInEntity.setSecuencia("9");
		precancelacionPlazoFijoInEntity.setSucursalCuentaCredito("194");
		precancelacionPlazoFijoInEntity.setTipoCuentaCredito("00");

		SimularPrecancelarInView inView = new SimularPrecancelarInView();
		inView.setCuentaPlazo("00720033001000111841");
		inView.setMoneda("ARS");
		inView.setNumeroCuentaDestino("000-063880/12");
		inView.setSecuencia("68");
		inView.setTipoCuentaDestino("68");
		inView.setTipoPlazo("Precancelable");
		when(plazoFijoDAO.simularPrecancelable(Matchers.any(PrecancelacionPlazoFijoInEntity.class),
				Matchers.any(Cliente.class))).thenThrow(new DAOException());
		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<SimulacionPrecancelableDTO> respuesta = plazoFijoBOImpl.precancelar(cliente, inView, "D");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);
	}

	/**
	 * Plazo Fijo obtener tenencias Bpriv OK
	 * 
	 * @throws DAOException
	 */
	@Test
	public void obtenerTenenciasPlazoFijoBpriv_OK() throws DAOException {

		Cliente cliente = new Cliente();

		Cuenta cuenta = new Cuenta();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cuentasRetail.add(cuenta);

		List<ResultadoTenenciaValuadaDetallePFOL> listResultado = new ArrayList<ResultadoTenenciaValuadaDetallePFOL>();

		ResultadoTenenciaValuadaDetallePFOL ResultadoTenenciaValuadaDetallePFOL1 = new ResultadoTenenciaValuadaDetallePFOL();
		ResultadoTenenciaValuadaDetallePFOL1.setCapitalInicial((BigDecimal.valueOf(17772)));
		ResultadoTenenciaValuadaDetallePFOL1.setFechaVencimiento("/Date(1395284400000-0300)/");
		ResultadoTenenciaValuadaDetallePFOL1.setDescripcionTipoPlazoFijo("Tradicional");
		ResultadoTenenciaValuadaDetallePFOL1.setTna((BigDecimal.valueOf(4)));
		ResultadoTenenciaValuadaDetallePFOL1.setCodSubProducto("1");
		ResultadoTenenciaValuadaDetallePFOL1.setMoneda("ARS");
		ResultadoTenenciaValuadaDetallePFOL1.setEstado("A");
		ResultadoTenenciaValuadaDetallePFOL1.setSucursalRadicacion(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL1.setPlazoVigencia(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL1.setFechaConstitucion("/Date(1395284400000-0300)/");
		ResultadoTenenciaValuadaDetallePFOL1.setFrecuenciaCobroIntereses(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL1.setAccionVencimiento("accion");
		ResultadoTenenciaValuadaDetallePFOL1.setCanal(BigDecimal.valueOf(10));

		ResultadoTenenciaValuadaDetallePFOL ResultadoTenenciaValuadaDetallePFOL2 = new ResultadoTenenciaValuadaDetallePFOL();
		ResultadoTenenciaValuadaDetallePFOL2.setCapitalInicial((BigDecimal.valueOf(14562)));
		ResultadoTenenciaValuadaDetallePFOL2.setFechaVencimiento("/Date(1395284400000-0300)/");
		ResultadoTenenciaValuadaDetallePFOL2.setDescripcionTipoPlazoFijo("Tradicional");
		ResultadoTenenciaValuadaDetallePFOL2.setTna((BigDecimal.valueOf(4)));
		ResultadoTenenciaValuadaDetallePFOL2.setCodSubProducto("1");
		ResultadoTenenciaValuadaDetallePFOL2.setMoneda("USD");
		ResultadoTenenciaValuadaDetallePFOL2.setEstado("A");
		ResultadoTenenciaValuadaDetallePFOL2.setSucursalRadicacion(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL2.setPlazoVigencia(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL2.setFechaConstitucion("/Date(1395284400000-0300)/");
		ResultadoTenenciaValuadaDetallePFOL2.setFrecuenciaCobroIntereses(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL2.setAccionVencimiento("accion");
		ResultadoTenenciaValuadaDetallePFOL2.setCanal(BigDecimal.valueOf(10));

		ResultadoTenenciaValuadaDetallePFOL ResultadoTenenciaValuadaDetallePFOL3 = new ResultadoTenenciaValuadaDetallePFOL();
		ResultadoTenenciaValuadaDetallePFOL3.setTenenciaValuada((BigDecimal.valueOf(40000.90).toString()));
		ResultadoTenenciaValuadaDetallePFOL3.setFechaVencimiento("/Date(1395284400000+0300)/");
		ResultadoTenenciaValuadaDetallePFOL3.setDescripcionTipoPlazoFijo("Tradicional");
		ResultadoTenenciaValuadaDetallePFOL3.setTna((BigDecimal.valueOf(4)));
		ResultadoTenenciaValuadaDetallePFOL3.setCodSubProducto("1");
		ResultadoTenenciaValuadaDetallePFOL3.setMoneda("USD");
		ResultadoTenenciaValuadaDetallePFOL3.setEstado("A");
		ResultadoTenenciaValuadaDetallePFOL3.setResultadoBruto("800.22");
		ResultadoTenenciaValuadaDetallePFOL3.setSucursalRadicacion(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL3.setPlazoVigencia(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL3.setFechaConstitucion("/Date(1395284400000-0300)/");
		ResultadoTenenciaValuadaDetallePFOL3.setFrecuenciaCobroIntereses(BigDecimal.TEN);
		ResultadoTenenciaValuadaDetallePFOL3.setAccionVencimiento("accion");
		ResultadoTenenciaValuadaDetallePFOL3.setCanal(BigDecimal.valueOf(10));

		listResultado.add(ResultadoTenenciaValuadaDetallePFOL1);
		listResultado.add(ResultadoTenenciaValuadaDetallePFOL2);
		listResultado.add(ResultadoTenenciaValuadaDetallePFOL3);

		DatosRespuestaPF resultado = new DatosRespuestaPF();
		resultado.setResultado(listResultado);

		cliente.setCuentasRetail(cuentasRetail);
		DetalleTenenciaValuadaPFEntity tenenciaValuada = new DetalleTenenciaValuadaPFEntity();
		tenenciaValuada.setCodigo(0);
		tenenciaValuada.setDatos(resultado);

		List<PlazoFijoEntity> plazosFijos = new ArrayList<PlazoFijoEntity>();

		PlazoFijoEntity plazoFijoEntity1 = new PlazoFijoEntity();
		plazoFijoEntity1.setCodigoPlazo("1");
		plazoFijoEntity1.setPrioridad("1");
		plazoFijoEntity1.setDescripcion("Tradicional");

		PlazoFijoEntity plazoFijoEntity2 = new PlazoFijoEntity();
		plazoFijoEntity2.setCodigoPlazo("1");
		plazoFijoEntity2.setPrioridad("1");
		plazoFijoEntity2.setDescripcion("Tradicional");

		plazosFijos.add(plazoFijoEntity1);
		plazosFijos.add(plazoFijoEntity2);

		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijos()).thenReturn(plazosFijos);
		Mockito.when(
				tenenciaValuadaDAO.obtenerTenenciaValuadaDetallePFOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenReturn(tenenciaValuada);

		Respuesta<TenenciaTotalPlazoFijoDTO> respuestaTenencia = new Respuesta<TenenciaTotalPlazoFijoDTO>();
		respuestaTenencia.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(TenenciaTotalPlazoFijoDTO.class)))
				.thenReturn(respuestaTenencia);

		Respuesta<TenenciaPlazoFijoBprivDTO> respuesta = plazoFijoBOImpl.obtenerTenenciasPlazoFijoBpriv(cliente, new ArrayList<CuentaTituloView>());
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	/**
	 * Plazo FijoBpriv Error obtenerTenenciaValuadaDetallePFOnline.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void obtenerTenenciasPlazoFijoBpriv_Error_obtenerTenenciaValuadaDetallePFOnline() throws DAOException {

		Cliente cliente = new Cliente();

		Cuenta cuenta = new Cuenta();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cuentasRetail.add(cuenta);

		List<ResultadoTenenciaValuadaDetallePFOL> resultadoLista = new ArrayList<ResultadoTenenciaValuadaDetallePFOL>();

		DatosRespuestaPF resultadoPF = new DatosRespuestaPF();
		resultadoPF.setResultado(resultadoLista);

		cliente.setCuentasRetail(cuentasRetail);
		DetalleTenenciaValuadaPFEntity tenenciaValuada = new DetalleTenenciaValuadaPFEntity();
		tenenciaValuada.setCodigo(0);
		tenenciaValuada.setDatos(resultadoPF);

		List<PlazoFijoEntity> plazosFijos = new ArrayList<PlazoFijoEntity>();
		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijos()).thenReturn(plazosFijos);
		Mockito.when(
				tenenciaValuadaDAO.obtenerTenenciaValuadaDetallePFOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenThrow(new DAOException());

		Respuesta<Object> respuestaError = new Respuesta<Object>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaError);

		Respuesta<TenenciaPlazoFijoBprivDTO> respuesta = plazoFijoBOImpl.obtenerTenenciasPlazoFijoBpriv(cliente, new ArrayList<CuentaTituloView>());
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	/**
	 * Plazo FijoBpriv Error obtenerTenenciaValuadaDetallePFOnline.
	 * 
	 * @throws DAOException
	 */
	@Test
	public void obtenerTenenciasPlazoFijoBpriv_Error_obtenerPlazosFijos() throws DAOException {

		Cliente cliente = new Cliente();

		Cuenta cuenta = new Cuenta();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cuentasRetail.add(cuenta);

		List<ResultadoTenenciaValuadaDetallePFOL> resultadoLista = new ArrayList<ResultadoTenenciaValuadaDetallePFOL>();

		DatosRespuestaPF resultadoPF = new DatosRespuestaPF();
		resultadoPF.setResultado(resultadoLista);

		cliente.setCuentasRetail(cuentasRetail);
		DetalleTenenciaValuadaPFEntity tenenciaValuada = new DetalleTenenciaValuadaPFEntity();
		tenenciaValuada.setCodigo(0);
		tenenciaValuada.setDatos(resultadoPF);
		Mockito.when(consultaPlazoFijoDAO.obtenerPlazosFijos()).thenThrow(new DAOException());
		Mockito.when(
				tenenciaValuadaDAO.obtenerTenenciaValuadaDetallePFOnline(Matchers.any(DetalleFondoRequestEntity.class)))
				.thenThrow(new DAOException());

		Respuesta<Object> respuestaError = new Respuesta<Object>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaError);

		Respuesta<TenenciaPlazoFijoBprivDTO> respuesta = plazoFijoBOImpl.obtenerTenenciasPlazoFijoBpriv(cliente, new ArrayList<CuentaTituloView>());
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void obbtenerDetalleInteresesOkTest() throws DAOException {
		DetalleInteresesViewRequest viewRequest = new DetalleInteresesViewRequest();
		viewRequest.setCuentaPlazo("1234567898765432");
		viewRequest.setSecuencia("19");

		Cliente cliente = new Cliente();

		ConsultaInteresantePlazoFijoOutEntity outDAO = new ConsultaInteresantePlazoFijoOutEntity();
		List<PFInteresanteEntity> agrElementosLiquidacion = new ArrayList<PFInteresanteEntity>();
		PFInteresanteEntity entity = new PFInteresanteEntity();
		agrElementosLiquidacion.add(entity);
		entity.setCapital("8765");
		entity.setFechaProximaLiquidacion("12345678");
		entity.setImpuesto("2345");
		entity.setIntereses("87654");
		entity.setTotal("123456");
		outDAO.setAgrElementosLiquidacion(agrElementosLiquidacion);
		when(plazoFijoDAO.consultarInteresante(Matchers.any(ConsultaInteresantePlazoFijoInEntity.class),
				Matchers.any(Cliente.class))).thenReturn(outDAO);

		Respuesta<DetalleCobroInteresesViewResponse> respuestaIntereses = new Respuesta<DetalleCobroInteresesViewResponse>();
		respuestaIntereses.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(DetalleCobroInteresesViewResponse.class))).thenReturn(respuestaIntereses);

		Respuesta<DetalleCobroInteresesViewResponse> respuesta = plazoFijoBOImpl.obtenerDetalleIntereses(viewRequest,
				cliente);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	@Test
	public void obbtenerDetalleInteresesDAOExceptionTest() throws DAOException {
		DetalleInteresesViewRequest viewRequest = new DetalleInteresesViewRequest();
		viewRequest.setCuentaPlazo("1234567898765432");
		viewRequest.setSecuencia("19");

		Cliente cliente = new Cliente();

		ConsultaInteresantePlazoFijoOutEntity outDAO = new ConsultaInteresantePlazoFijoOutEntity();
		List<PFInteresanteEntity> agrElementosLiquidacion = new ArrayList<PFInteresanteEntity>();
		PFInteresanteEntity entity = new PFInteresanteEntity();
		agrElementosLiquidacion.add(entity);
		entity.setCapital("8765");
		entity.setFechaUltimaLiquidacion("12345678");
		entity.setImpuesto("2345");
		entity.setIntereses("87654");
		entity.setTotal("123456");
		outDAO.setAgrElementosLiquidacion(agrElementosLiquidacion);
		when(plazoFijoDAO.consultarInteresante(Matchers.any(ConsultaInteresantePlazoFijoInEntity.class),
				Matchers.any(Cliente.class))).thenThrow(new DAOException());

		Respuesta<Object> respuestaError = new Respuesta<Object>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
				Matchers.anyString())).thenReturn(respuestaError);

		Respuesta<DetalleCobroInteresesViewResponse> respuesta = plazoFijoBOImpl.obtenerDetalleIntereses(viewRequest,
				cliente);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	@Test
	public void generarPlazosFijosReporte() {

		// When
		Cliente cliente = Mockito.mock(Cliente.class);
		TenenciaPlazoFijoView tenenciaPFView = armarTenenciaPFView();
		Respuesta<Reporte> respuestaMock = new Respuesta<Reporte>();
		respuestaMock.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(reporteDAO.obtenerReporte(Matchers.any(TenenciaPlazoFijoExcel.class), Matchers.anyString(),
				Matchers.any(Cliente.class), Matchers.anyBoolean())).thenReturn(respuestaMock);

		// Then
		respuestaMock = plazoFijoBOImpl.obtenerPlazosFijosReporte(tenenciaPFView, cliente);

		// Expected
		Assert.assertNotNull(respuestaMock);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaMock.getEstadoRespuesta());

	}

	@Test
	public void generarPlazosFijosBPReporte() {

		// When
		Cliente cliente = Mockito.mock(Cliente.class);
		List<ContenidoTenenciaBprivDTO> listaTenenciaPFView = armarListaTenenciaPFView();
		Respuesta<Reporte> respuestaMock = new Respuesta<Reporte>();
		respuestaMock.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(reporteDAO.obtenerReporte(Matchers.any(TenenciaPlazoFijoExcel.class), Matchers.anyString(),
				Matchers.any(Cliente.class), Matchers.anyBoolean())).thenReturn(respuestaMock);

		// Then
		respuestaMock = plazoFijoBOImpl.obtenerPlazosFijosReporteBP(listaTenenciaPFView, cliente);

		// Expected
		Assert.assertNotNull(respuestaMock);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaMock.getEstadoRespuesta());

	}

	private List<ContenidoTenenciaBprivDTO> armarListaTenenciaPFView() {

		List<ContenidoTenenciaBprivDTO> listaCuentasPlazoFijo = new ArrayList<ContenidoTenenciaBprivDTO>();
		ContenidoTenenciaBprivDTO cuentaTitulosPF = new ContenidoTenenciaBprivDTO();
		cuentaTitulosPF.setNumeroCuenta("250-354956/0");

		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		Interviniente interviniente = new Interviniente();
		interviniente.setApellido("Alfaro");
		interviniente.setNombre("Gustavo");
		intervinientes.add(interviniente);
		cuentaTitulosPF.setIntervinientes(intervinientes);

		TenenciaTotalPlazoFijoDTO tenenciasPF = new TenenciaTotalPlazoFijoDTO();
		TenenciaPlazoFijoView tenencias = armarTenenciaPFView();
		tenenciasPF.setTenenciaPlazoFijoPesos(tenencias.getTenenciaPlazoFijoPesos());
		tenenciasPF.setTenenciaPlazoFijoDolares(tenencias.getTenenciaPlazoFijoDolares());
		tenenciasPF.setTotalesGrilla(tenencias.getTotalesGrilla());

		cuentaTitulosPF.setTenenciaPlazoFijoDTO(tenenciasPF);

		listaCuentasPlazoFijo.add(cuentaTitulosPF);

		return listaCuentasPlazoFijo;

	}

	private TenenciaPlazoFijoView armarTenenciaPFView() {

		TenenciaPlazoFijoView tenenciaPFView = new TenenciaPlazoFijoView();
		List<TenenciaValuadaPlazoFijoDTO> tenenciaPlazoFijoPesos = new ArrayList<TenenciaValuadaPlazoFijoDTO>();

		TenenciaValuadaPlazoFijoDTO tenencia1Pesos = new TenenciaValuadaPlazoFijoDTO();
		tenencia1Pesos.setPrioridad("99");
		tenencia1Pesos.setTipo("Tradicional");
		tenencia1Pesos.setTenenciaValuadaHastaHoy("3020.20");
		tenencia1Pesos.setTna("39.21");
		tenencia1Pesos.setResultado("20.39");
		tenencia1Pesos.setCapitalInicial("4000.00");
		tenencia1Pesos.setFechaVencimiento("24/02/2019");
		tenenciaPlazoFijoPesos.add(tenencia1Pesos);

		TenenciaValuadaPlazoFijoDTO tenencia2Pesos = new TenenciaValuadaPlazoFijoDTO();
		tenencia2Pesos.setPrioridad("99");
		tenencia2Pesos.setTipo("Tradicional");
		tenencia2Pesos.setTenenciaValuadaHastaHoy("3020.20");
		tenencia2Pesos.setTna("39.21");
		tenencia2Pesos.setResultado("20.39");
		tenencia2Pesos.setCapitalInicial("4000.00");
		tenencia2Pesos.setFechaVencimiento("24/03/2019");
		tenenciaPlazoFijoPesos.add(tenencia2Pesos);

		TenenciaValuadaPlazoFijoDTO tenencia3Pesos = new TenenciaValuadaPlazoFijoDTO();
		tenencia3Pesos.setPrioridad("20");
		tenencia3Pesos.setTipo("DIVA");
		tenencia3Pesos.setTenenciaValuadaHastaHoy("3020.20");
		tenencia3Pesos.setTna("39.21");
		tenencia3Pesos.setResultado("20.39");
		tenencia3Pesos.setCapitalInicial("4000.00");
		tenencia3Pesos.setFechaVencimiento("24/03/2019");
		tenenciaPlazoFijoPesos.add(tenencia3Pesos);

		TenenciaValuadaPlazoFijoDTO tenencia4Pesos = new TenenciaValuadaPlazoFijoDTO();
		tenencia4Pesos.setPrioridad("99");
		tenencia4Pesos.setTipo("Precancelable");
		tenencia4Pesos.setTenenciaValuadaHastaHoy("3020.20");
		tenencia4Pesos.setTna("39.21");
		tenencia4Pesos.setResultado("20.39");
		tenencia4Pesos.setCapitalInicial("4000.00");
		tenencia4Pesos.setFechaVencimiento("15/02/2019");
		tenenciaPlazoFijoPesos.add(tenencia4Pesos);

//		TenenciaValuadaPlazoFijoDTO tenencia5Pesos = new TenenciaValuadaPlazoFijoDTO();
//		tenencia5Pesos.setCodigoPlazoFijo("3");
//		tenencia5Pesos.setTipo("Tradicional");
//		tenencia5Pesos.setTenenciaValuadaHastaHoy("3020.20");
//		tenencia5Pesos.setTna("39.21");
//		tenencia5Pesos.setResultado("20.39");
//		tenencia5Pesos.setCapitalInicial("4000.00");
//		tenencia5Pesos.setFechaVencimiento("12/01/2019");
//		tenenciaPlazoFijoPesos.add(tenencia5Pesos);

		tenenciaPFView.setTenenciaPlazoFijoPesos(tenenciaPlazoFijoPesos);

		TotalesGrillaDTO totalesGrillaDTO = new TotalesGrillaDTO();
		totalesGrillaDTO.setCapitalInicialArs(new Double(5000));
		totalesGrillaDTO.setCapitalInicialUsd(new Double(5000));
		totalesGrillaDTO.setTenenciaHoyArs(new Double(5000));
		totalesGrillaDTO.setTenenciaHoyUsd(new Double(5000));
		totalesGrillaDTO.setResultadoArs(new Double(5000));
		totalesGrillaDTO.setResultadoUsd(new Double(5000));

		tenenciaPFView.setTotalesGrilla(totalesGrillaDTO);

		return tenenciaPFView;
	}
	
	@Test
	public void testObtenerRecomendacion() throws BusinessException {
		RecomendacionResponseEntity response = new RecomendacionResponseEntity();
		PerfilInversorRequestEntity request = new PerfilInversorRequestEntity();
		DatosPerfilInversorEntity datos = new DatosPerfilInversorEntity();
		
		datos.setDaysCount(90);
		datos.setNup("03007878");
		datos.setIp("1.1.1.1");
		datos.setUsuario("B999999");
		
		request.setCanal("04");
		request.setSubCanal("0099");
		request.setTipoHash("0");
		request.setDato("Prueba");
		request.setFirma("");
		request.setDatos(datos);
		
		when(plazoFijoDAO.consultaRecomendacion(request)).thenReturn(response);
		
		RecomendacionInView viewRequest = new RecomendacionInView();
		viewRequest.setDaysCount(90);
		RecomendacionResponseEntity respuestaBO = plazoFijoBOImpl.obtenerRecomendacion(viewRequest);
		
		assertEquals(response.getCodigo(), respuestaBO.getCodigo());
	}

}
