package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.OrdenesBO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenBaseDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ComprobantePlazoFijoInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ComprobantePlazoFijoOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.FinalizarPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.InteresesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.MinimosPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimulacionPrecancelableDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimularPlazoFijoInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimularPlazoFijoOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TenenciaPlazoFijoBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TenenciaTotalPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DatosRecomendacionResponse;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PlazoFijoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.Product;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.CalcularInteresesInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfigPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfigPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfirmacionPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPrecancelableOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.InteresesView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ObtenerOrdenesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ObtenerTenenciasPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.RecomendacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimulacionPrecancelableOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoBprivView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoView;
import ar.com.santanderrio.obp.servicios.tenencias.dto.SolicitarPrecancelarOutDTO;
/**
 * 
 * @author marcelo.ruiz
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PlazoFijoManagerTest {

    /** The plazo fijo manager. */
    @InjectMocks
    PlazoFijoManagerImpl plazoFijoManager;
    
    
    /** The plazo fijo  BO. */
    @Mock
    PlazoFijoBO plazoFijoBO;
    
    /** The plazo fijo  BOImpl. */
    @Mock
    PlazoFijoBOImpl plazoFijoBOImpl;

    /** The sesion cliente. */
    @Mock
    SesionCliente sesionCliente;
    
    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;
    
    /** The validator. */
    @Mock
    private Validator validator;
    
    /** The legal BO. */
	@Mock
	protected LegalBO legalBO;
	
	/** The cuenta manager. */
    @Mock
    private CuentaManager cuentaManager;
    
    @Mock
    private MensajeBO mensajeBO;
    
	/** The session parametros. */
	@Mock
	private SesionParametros sessionParametros;
	
	@Mock
	private EstadisticaManager 	estadisticaManager;
	
	@Mock
	private OrdenesBO ordenesBO;

	
	
    
    private static final String ERROR_GENERICO = "10429";
    
    private static final String ERROR_CUENTAS_VACIAS = "10470";
    
    @Before
    public void init() {
        Respuesta<CuentasView> cuentas = new Respuesta<CuentasView>();
        List<CuentasAdhesionDebitoView> cuentasDebito = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView c2 = new CuentasAdhesionDebitoView();
        c2.setAlias("prueba");
        cuentasDebito.add(c2);
        CuentasView cuentasView = new CuentasView();
        cuentasView.setCuentas(cuentasDebito);
        cuentas.setRespuesta(cuentasView);
        Mockito.when(cuentaManager.getCuentasSaldo()).thenReturn(cuentas);
        
    }
     
    @Ignore
    @SuppressWarnings("unchecked")
    @Test
    public void consultarCondiciones() {
    	
		RegistroSesion registroSesion = Mockito.mock(RegistroSesion.class);
        SimularPlazoFijoInView inView = new SimularPlazoFijoInView();
        inView.setNumeroCuentaDebito("0639170");
        inView.setPlazo("00180");
        inView.setImportePlazoFijo(BigDecimal.valueOf(890));
        inView.setDivisa("ARS");
        Cliente cliente = new Cliente();
       
        Mockito.when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
                   
        Respuesta<SimularPlazoFijoOutDTO> rtaBO = new Respuesta<SimularPlazoFijoOutDTO>();
        SimularPlazoFijoOutDTO dtoOut= new SimularPlazoFijoOutDTO();
        rtaBO.setRespuesta(dtoOut);
        rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(plazoFijoBO.simularPlazoFijo(Matchers.any(SimularPlazoFijoInDTO.class), Matchers.any(Cliente.class),Matchers.any(int.class))).thenReturn(rtaBO);
        
        Respuesta<SimularPlazoFijoOutView> rtaManager = new Respuesta<SimularPlazoFijoOutView>();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(SimularPlazoFijoOutView.class))).thenReturn(rtaManager);
        
        
        Respuesta<SimularPlazoFijoOutView>  respuesta = plazoFijoManager.simularPlazoFijo(inView);
        Assert.assertNotNull(respuesta);
    }
    
    /**
     * Test de configuracion plazo fijo
     */
    @Ignore
    @SuppressWarnings("unchecked")
	@Test
    public void configuracionPlazoFijo(){
    	Cliente cliente = new Cliente();
    	Respuesta<String> respuestaLegales = new Respuesta<String>();
    	respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
    	respuestaLegales.setRespuesta("prueba");
    	Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);
    	
    	List<Cuenta> cuentasCepo = new ArrayList<Cuenta>();
    	Mockito.when(cuentaManager.getCuentasCepo(cliente)).thenReturn(cuentasCepo);
    	
    	MinimosPlazoFijoDTO minimos = new MinimosPlazoFijoDTO();
    	minimos.setImporteMinimoDolares(BigDecimal.valueOf(100.00));
    	minimos.setImporteMinimoPesos(BigDecimal.valueOf(500.00));
    	minimos.setPlazoMinimoDolares(30);
    	minimos.setPlazoMinimoPesos(30);
    	
    	Respuesta<MinimosPlazoFijoDTO> respuestaBO = new Respuesta<MinimosPlazoFijoDTO>();
    	respuestaBO.setRespuesta(minimos);
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	String tipoBanca = "BR";
    	Mockito.when(plazoFijoBO.consultarMinimos(Matchers.any(Cliente.class), tipoBanca)).thenReturn(respuestaBO);
    	
    	Respuesta<ConfigPlazoFijoOutView> rtaManager = new Respuesta<ConfigPlazoFijoOutView>();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(ConfigPlazoFijoOutView.class))).thenReturn(rtaManager);
        
        ConfigPlazoFijoInView configuracionPlazoFijoInView = new ConfigPlazoFijoInView();
    	Respuesta<ConfigPlazoFijoOutView> respuestaManager = plazoFijoManager.configuracionPlazoFijo(configuracionPlazoFijoInView);

        Assert.assertNotNull(respuestaManager);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaManager.getEstadoRespuesta());
    	
    	
    }
    
    /**
     * Test que devuelve error al consultar legal
     */
    @Ignore
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void configuracionPlazoFijoLegalError(){
    	Cliente cliente = new Cliente();
    	
    	
    	List<Cuenta> cuentasCepo = new ArrayList<Cuenta>();
    	Mockito.when(cuentaManager.getCuentasCepo(cliente)).thenReturn(cuentasCepo);
    	
    	Respuesta<String> respuestaLegales = new Respuesta<String>();
    	respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);
    	
    	Respuesta rtaManager = new Respuesta();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.ERROR);
        
        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO)).thenReturn(rtaManager);
        
        ConfigPlazoFijoInView configuracionPlazoFijoInView = new ConfigPlazoFijoInView();
    	Respuesta<ConfigPlazoFijoOutView> respuestaManager = plazoFijoManager.configuracionPlazoFijo(configuracionPlazoFijoInView);
    	
    	Assert.assertNotNull(respuestaManager);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaManager.getEstadoRespuesta());
    }
    
//    @Test
//    public void inicioPlazoFijoManagerTest(){
//    	InicioPlazoFijoDTO inicioPF = new InicioPlazoFijoDTO();
//    	CuentaTituloDTO cuentaTitulo = new CuentaTituloDTO();
//    	cuentaTitulo.setCuentaOperativa("prueba");
//    	List<CuentaTituloDTO> listCuentaTitulo = new ArrayList<CuentaTituloDTO>();
//    	listCuentaTitulo.add(cuentaTitulo);
//    	inicioPF.setCuentasBancaPrivada(listCuentaTitulo);
//    	
//    	Respuesta<InicioPlazoFijoDTO> respuestaBO = new Respuesta<InicioPlazoFijoDTO>();
//    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
//    	respuestaBO.setRespuesta(inicioPF);
//    	Mockito.when(plazoFijoBO.inicio(Matchers.any(Cliente.class))).thenReturn(respuestaBO);
//    	
//    	Respuesta<InicioPlazoFijoView> rta = plazoFijoManager.inicio();
//    	Assert.assertNotNull(rta);
//    }
    
    @Ignore
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
    public void configuracionPlazoFijoCuentasVacias(){
    	Cliente cliente = new Cliente();
    	Respuesta<CuentasView> cuentas = new Respuesta<CuentasView>();
    	List<CuentasAdhesionDebitoView> cuentasDebito = new ArrayList<CuentasAdhesionDebitoView>();
    	CuentasView cuentasView = new CuentasView();
    	cuentasView.setCuentas(cuentasDebito);
    	cuentas.setRespuesta(cuentasView);
    	Mockito.when(cuentaManager.getCuentasSaldo()).thenReturn(cuentas);
    	
    	List<Cuenta> cuentasCepo = new ArrayList<Cuenta>();
    	Mockito.when(cuentaManager.getCuentasCepo(cliente)).thenReturn(cuentasCepo);
    	
    	Respuesta rtaManager = new Respuesta();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.WARNING);
        
        Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.WARNING, ERROR_CUENTAS_VACIAS)).thenReturn(rtaManager);
        
        ConfigPlazoFijoInView configuracionPlazoFijoInView = new ConfigPlazoFijoInView();
    	Respuesta<ConfigPlazoFijoOutView> respuestaManager = plazoFijoManager.configuracionPlazoFijo(configuracionPlazoFijoInView);
    	
    	Assert.assertNotNull(respuestaManager);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuestaManager.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void accionesAlVencimientoTestOK(){
    	
    	Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		Respuesta<AccionesAlVencimientoOutView> rtaFactory = new Respuesta<AccionesAlVencimientoOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(AccionesAlVencimientoOutView.class))).thenReturn(rtaFactory);
		
		Respuesta<AccionesAlVencimientoOutView> rtaBo = new Respuesta<AccionesAlVencimientoOutView>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		
		when(plazoFijoBO.accionesAlVencimiento(Matchers.any(AccionesAlVencimientoInView.class), Matchers.any(Cliente.class))).thenReturn(rtaBo);
		
    	AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		Respuesta<AccionesAlVencimientoOutView> rtaManager = plazoFijoManager.accionesAlVencimiento(inView);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void confirmacionTestOk(){
    	
    	Respuesta<String> respuestaLegales = new Respuesta<String>();
    	respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
    	respuestaLegales.setRespuesta("prueba");
    	Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);
    	
    	Respuesta<ConfirmacionPlazoFijoOutView> rtaManager = new Respuesta<ConfirmacionPlazoFijoOutView>();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ConfirmacionPlazoFijoOutView.class))).thenReturn(rtaManager);
    	
    	String codigoPlazo = PlazoFijoEnum.PRECANCELABLE.getCodigo();
		Respuesta<ConfirmacionPlazoFijoOutView> respuestaManager = plazoFijoManager.confirmacionPlazoFijo(codigoPlazo, "rtl");
    	Assert.assertNotNull(respuestaManager);
    }
    
    
	/*
	 * Test simularPlazoFijo OK.
	 */
    @Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void simularPlazoFijoOk() {

		Set<ConstraintViolation<SimularPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<SimularPlazoFijoInView>>();
		when(validator.validate(Matchers.any(SimularPlazoFijoInView.class))).thenReturn(respValidator);

		Respuesta<SimularPlazoFijoOutView> rtaFactory = new Respuesta<SimularPlazoFijoOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SimularPlazoFijoOutView.class)))
				.thenReturn(rtaFactory);

		Respuesta<SimularPlazoFijoOutDTO> rtaBo = new Respuesta<SimularPlazoFijoOutDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		when(plazoFijoBO.simularPlazoFijo(Matchers.any(SimularPlazoFijoInDTO.class), Matchers.any(Cliente.class),Matchers.any(int.class)))
				.thenReturn(rtaBo);

		SimularPlazoFijoInView inView = new SimularPlazoFijoInView();
		inView.setDivisa("ARS");
		inView.setImportePlazoFijo(new BigDecimal("600"));
		inView.setPlazo("180");

		Respuesta<SimularPlazoFijoOutView> rtaManager = plazoFijoManager.simularPlazoFijo(inView);
		Assert.assertNotNull(rtaManager);
        Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());

	}
    
    
    /*
     * Test simularPlazoFijo Error.
     */
    @Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void simularPlazoFijoError() {

		Set<ConstraintViolation<SimularPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<SimularPlazoFijoInView>>();
		when(validator.validate(Matchers.any(SimularPlazoFijoInView.class))).thenReturn(respValidator);

		Respuesta<SimularPlazoFijoOutView> rtaFactory = new Respuesta<SimularPlazoFijoOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SimularPlazoFijoOutView.class)))
				.thenReturn(rtaFactory);
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");

		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);

		Respuesta<SimularPlazoFijoOutDTO> rtaBo = new Respuesta<SimularPlazoFijoOutDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.simularPlazoFijo(Matchers.any(SimularPlazoFijoInDTO.class), Matchers.any(Cliente.class),Matchers.any(int.class)))
				.thenReturn(rtaBo);
		SimularPlazoFijoInView inView = new SimularPlazoFijoInView();
		inView.setDivisa("ARS");
		inView.setImportePlazoFijo(new BigDecimal("600"));
		inView.setPlazo("180");
		Respuesta<SimularPlazoFijoOutView> respuestaViewError = new Respuesta<SimularPlazoFijoOutView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);

		when(respuestaFactory.crearRespuestaError(Matchers.eq(SimularPlazoFijoOutView.class),Matchers.any(String.class),Matchers.any(String.class) )).thenReturn(respuestaViewError);
		

		Respuesta<SimularPlazoFijoOutView> rtaManager = plazoFijoManager.simularPlazoFijo(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.ERROR, rtaManager.getEstadoRespuesta());

	}
    
	
    /*
     * Test simularPlazoFijo Warning.
     */
	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void simularPlazoFijoWarning() {

		Set<ConstraintViolation<SimularPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<SimularPlazoFijoInView>>();
		when(validator.validate(Matchers.any(SimularPlazoFijoInView.class))).thenReturn(respValidator);

		Respuesta<SimularPlazoFijoOutView> rtaFactory = new Respuesta<SimularPlazoFijoOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SimularPlazoFijoOutView.class)))
				.thenReturn(rtaFactory);
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");

		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);

		Respuesta<SimularPlazoFijoOutDTO> rtaBo = new Respuesta<SimularPlazoFijoOutDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.simularPlazoFijo(Matchers.any(SimularPlazoFijoInDTO.class), Matchers.any(Cliente.class),Matchers.any(int.class)))
				.thenReturn(rtaBo);
		SimularPlazoFijoInView inView = new SimularPlazoFijoInView();
		inView.setDivisa("ARS");
		inView.setImportePlazoFijo(new BigDecimal("600"));
		inView.setPlazo("180");
		
			 
		 Respuesta<SimularPlazoFijoOutView> respuestaWarning = new Respuesta<SimularPlazoFijoOutView>();
		 respuestaWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
		  
		 Mockito.when(respuestaFactory
					.crearRespuestaWarning(Matchers.eq(SimularPlazoFijoOutView.class),
							Matchers.any(SimularPlazoFijoOutView.class), Matchers.anyList())).thenReturn(respuestaWarning);
		 
		Respuesta<SimularPlazoFijoOutView> rtaManager = plazoFijoManager.simularPlazoFijo(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaManager.getEstadoRespuesta());
		

	}
	
	
	
	/*metodo auxiliar para ValidacionHash */
	
	private Map<String, Object> crearMapaDeLaVistaCalcularIntereses( String numeroCuentaDebito, BigDecimal  importePlazoFijo) {
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("numeroCuentaDebito", numeroCuentaDebito);
		mapaAtributos.put("importePlazoFijo", importePlazoFijo);
		return mapaAtributos;
	}
	
	
	/*
	 * Test calcular Intereses OK.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void calcularInteresesOk() {
		

		CalcularInteresesInView inDTO = new CalcularInteresesInView();
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("000-063880/1");
		inDTO.setPlazo("500");
		
		when(sessionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaCalcularIntereses(inDTO.getNumeroCuentaDebito(), inDTO.getImportePlazoFijo()))); 
		
		Set<ConstraintViolation<CalcularInteresesInView>> respValidator = new HashSet<ConstraintViolation<CalcularInteresesInView>>();
		when(validator.validate(Matchers.any(CalcularInteresesInView.class))).thenReturn(respValidator);

		Respuesta<InteresesDTO> rtaFactory = new Respuesta<InteresesDTO>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SimularPlazoFijoOutView.class)))
				.thenReturn(rtaFactory);

		Respuesta<InteresesDTO> rtaBo = new Respuesta<InteresesDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		when(plazoFijoBO.calcularIntereses(Matchers.any(SimularPlazoFijoInDTO.class), Matchers.any(Cliente.class)))
				.thenReturn(rtaBo);

		CalcularInteresesInView inView = new CalcularInteresesInView();
		inView.setImportePlazoFijo(BigDecimal.valueOf(890));
		inView.setNumeroCuentaDebito("000-063880/1");
		inView.setPlazo("500");

		Respuesta<InteresesView> rtaManager = plazoFijoManager.calcularIntereses(inView);
		Assert.assertNotNull(rtaManager);

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void finalizarPlazoFijoTestOk(){
		
		Set<ConstraintViolation<FinalizarPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<FinalizarPlazoFijoInView>>();
		when(validator.validate(Matchers.any(FinalizarPlazoFijoInView.class))).thenReturn(respValidator);
		
		FinalizarPlazoFijoInView finalizarPlazoFijoInView = new FinalizarPlazoFijoInView();
		finalizarPlazoFijoInView.setNroCuentaDebito("013-123456/7");
		finalizarPlazoFijoInView.setDivisa("ARS");
		finalizarPlazoFijoInView.setSubproducto("0013");
		finalizarPlazoFijoInView.setAccionAlVencimiento("RC");
		finalizarPlazoFijoInView.setImporteCertificado(new BigDecimal("000103000"));
		finalizarPlazoFijoInView.setDescripcionAccionVencimiento("Déposito en Cuenta");
		finalizarPlazoFijoInView.setTasaEfectiva(new BigDecimal("2"));
		finalizarPlazoFijoInView.setCotizacionCodigoUr(new BigDecimal("1"));
		finalizarPlazoFijoInView.setSaldoInicUr(new BigDecimal("3"));
		
		when(sessionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaCalcularIntereses(finalizarPlazoFijoInView.getNroCuentaDebito(), finalizarPlazoFijoInView.getImporteCertificado())));
		
		Respuesta<FinalizarPlazoFijoDTO> rtaBO = new Respuesta<FinalizarPlazoFijoDTO>();
		rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FinalizarPlazoFijoDTO respuesta = new FinalizarPlazoFijoDTO();
		respuesta.setImporteCertificado(finalizarPlazoFijoInView.getImporteCertificado());
		rtaBO.setRespuesta(respuesta);
		respuesta.setTipoPF("03");
		respuesta.setDivisa("ARS");
		Mockito.when(plazoFijoBO.finalizarPlazoFijo(Matchers.any(FinalizarPlazoFijoDTO.class), Matchers.any(Cliente.class))).thenReturn(rtaBO);
		
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		
		Respuesta<FinalizarPlazoFijoView> rtaFactory = new Respuesta<FinalizarPlazoFijoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FinalizarPlazoFijoView.class))).thenReturn(rtaFactory);
		
		Respuesta<FinalizarPlazoFijoView> rtaManager = plazoFijoManager.finalizarPlazoFijo(finalizarPlazoFijoInView);
		Assert.assertNotNull(rtaManager);
	}
	
	
	/**
	 * finalizar plazo fijo Error.
	 */
	@Test
	public void finalizarPlazoFijoTestERROR(){
		
		Set<ConstraintViolation<FinalizarPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<FinalizarPlazoFijoInView>>();
		when(validator.validate(Matchers.any(FinalizarPlazoFijoInView.class))).thenReturn(respValidator);
		
		FinalizarPlazoFijoInView finalizarPlazoFijoInView = new FinalizarPlazoFijoInView();
		finalizarPlazoFijoInView.setNroCuentaDebito("013-123456/7");
		finalizarPlazoFijoInView.setDivisa("ARS");
		finalizarPlazoFijoInView.setSubproducto("0013");
		finalizarPlazoFijoInView.setAccionAlVencimiento("RC");
		finalizarPlazoFijoInView.setImporteCertificado(new BigDecimal("000103000"));
        finalizarPlazoFijoInView.setDescripcionAccionVencimiento("Déposito en Cuenta");
        finalizarPlazoFijoInView.setTasaEfectiva(new BigDecimal("2"));
        finalizarPlazoFijoInView.setCotizacionCodigoUr(new BigDecimal("1"));
        finalizarPlazoFijoInView.setSaldoInicUr(new BigDecimal("3"));
		
		when(sessionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaCalcularIntereses(finalizarPlazoFijoInView.getNroCuentaDebito(), finalizarPlazoFijoInView.getImporteCertificado())));
		
		Respuesta<FinalizarPlazoFijoDTO> rtaBO = new Respuesta<FinalizarPlazoFijoDTO>();
		rtaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		FinalizarPlazoFijoDTO respuesta = new FinalizarPlazoFijoDTO();
		rtaBO.setRespuesta(respuesta);
		respuesta.setTipoPF("03");
		respuesta.setDivisa("ARS");
		Mockito.when(plazoFijoBO.finalizarPlazoFijo(Matchers.any(FinalizarPlazoFijoDTO.class), Matchers.any(Cliente.class))).thenReturn(rtaBO);
		
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		
		Respuesta<Object> rtaFactory = new Respuesta<Object>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
		when(respuestaFactory.crearRespuestaErrorPersonalizadoSinClase( Matchers.anyString(), Matchers.anyString())).thenReturn(rtaFactory);
			
		Respuesta<FinalizarPlazoFijoView> rtaManager = plazoFijoManager.finalizarPlazoFijo(finalizarPlazoFijoInView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.ERROR, rtaManager.getEstadoRespuesta());
	}
	
	
	
	/*
	 * Test calcular Intereses ERROR.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void calcularInteresesTestERROR() {
		

		CalcularInteresesInView inDTO = new CalcularInteresesInView();
		inDTO.setImportePlazoFijo(BigDecimal.valueOf(890));
		inDTO.setNumeroCuentaDebito("000-063880/1");
		inDTO.setPlazo("500");
		
		when(sessionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaCalcularIntereses(inDTO.getNumeroCuentaDebito(), inDTO.getImportePlazoFijo()))); 
		
		Set<ConstraintViolation<CalcularInteresesInView>> respValidator = new HashSet<ConstraintViolation<CalcularInteresesInView>>();
		when(validator.validate(Matchers.any(CalcularInteresesInView.class))).thenReturn(respValidator);

		Respuesta<InteresesDTO> rtaFactory = new Respuesta<InteresesDTO>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SimularPlazoFijoOutView.class)))
				.thenReturn(rtaFactory);
	
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");	
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<InteresesDTO> rtaBo = new Respuesta<InteresesDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.calcularIntereses(Matchers.any(SimularPlazoFijoInDTO.class), Matchers.any(Cliente.class)))
				.thenReturn(rtaBo);

		CalcularInteresesInView inView = new CalcularInteresesInView();
		inView.setImportePlazoFijo(BigDecimal.valueOf(890));
		inView.setNumeroCuentaDebito("000-063880/1");
		inView.setPlazo("500");
		
		Respuesta<Object> respuestaViewError = new Respuesta<Object>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
		when(respuestaFactory.crearRespuestaError("",
				TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(respuestaViewError);

		Respuesta<InteresesView> rtaManager = plazoFijoManager.calcularIntereses(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.ERROR, rtaManager.getEstadoRespuesta());

	}
	
	
	/**
	 * PlazoFijo ver comprobante ok
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void plazoFijoVerComprobanteTestOK() {

		Set<ConstraintViolation<ComprobantePlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ComprobantePlazoFijoInView>>();
		when(validator.validate(Matchers.any(ComprobantePlazoFijoInView.class))).thenReturn(respValidator);
				

		Respuesta<ComprobantePlazoFijoOutView> rtaFactory = new Respuesta<ComprobantePlazoFijoOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ComprobantePlazoFijoOutView.class)))
				.thenReturn(rtaFactory);

		List<String> legales = new ArrayList<String>();
		legales.add("legal1");
		legales.add("legal2");		
		ComprobantePlazoFijoOutDTO respuesta = new ComprobantePlazoFijoOutDTO();
		respuesta.setLegales(legales);
				
		Respuesta<ComprobantePlazoFijoOutDTO> rtaBo = new Respuesta<ComprobantePlazoFijoOutDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		rtaBo.setRespuesta(respuesta);				
		when(plazoFijoBO.verComprobante(Matchers.any(ComprobantePlazoFijoInDTO.class)))
				.thenReturn(rtaBo);
		
		ComprobantePlazoFijoInView inView = new ComprobantePlazoFijoInView();
		inView.setCodigoPlazo("17");
		inView.setMinimoDiasPrecancelar("2017-07-31");
		inView.setNumeroComprobante("000091170-00037");
		inView.setPlazo("361");
		inView.setPorcentajePenalizacion("00080000");
		Respuesta<ComprobantePlazoFijoOutView> rtaManager = plazoFijoManager.verComprobante(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
		
	}
	
	
	
	
	/**
	 * PlazoFijo ver comprobante ERROR
	 */
	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void plazoFijoVerComprobanteTestERROR() {

		Set<ConstraintViolation<ComprobantePlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ComprobantePlazoFijoInView>>();
		when(validator.validate(Matchers.any(ComprobantePlazoFijoInView.class))).thenReturn(respValidator);
				
		Respuesta<ComprobantePlazoFijoOutView> rtaFactory = new Respuesta<ComprobantePlazoFijoOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ComprobantePlazoFijoOutView.class)))
				.thenReturn(rtaFactory);

		List<String> legales = new ArrayList<String>();
		legales.add("legal1");
		legales.add("legal2");		
		ComprobantePlazoFijoOutDTO respuesta = new ComprobantePlazoFijoOutDTO();
		respuesta.setLegales(legales);
				
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");

		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<ComprobantePlazoFijoOutDTO> rtaBo = new Respuesta<ComprobantePlazoFijoOutDTO>();	
		rtaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		
		rtaBo.setRespuesta(respuesta);				
		when(plazoFijoBO.verComprobante(Matchers.any(ComprobantePlazoFijoInDTO.class)))
				.thenReturn(rtaBo);
		
		ComprobantePlazoFijoInView inView = new ComprobantePlazoFijoInView();
		inView.setCodigoPlazo("17");
		inView.setMinimoDiasPrecancelar("2017-07-31");
		inView.setNumeroComprobante("000091170-00037");
		inView.setPlazo("361");
		inView.setPorcentajePenalizacion("00080000");
		
		Respuesta<Object> respuestaViewError = new Respuesta<Object>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO)).thenReturn(respuestaViewError);
		
		Respuesta<ComprobantePlazoFijoOutView> rtaManager = plazoFijoManager.verComprobante(inView);	
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.ERROR, rtaManager.getEstadoRespuesta());
		
	}
	
	
	/**
	 * Plazo Fijo obtenerTenencias OK.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciasPlazoFijoTestOK_EstadisticaOK(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
			
		Respuesta<TenenciaPlazoFijoView> rtaFactory = new Respuesta<TenenciaPlazoFijoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(TenenciaPlazoFijoView.class)))
				.thenReturn(rtaFactory);

		Respuesta<TenenciaTotalPlazoFijoDTO> rtaBo = new Respuesta<TenenciaTotalPlazoFijoDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		when(plazoFijoBO.obtenerTenenciasPlazoFijo(Matchers.any(Cliente.class)))
				.thenReturn(rtaBo);
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
			
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("OK");
		
		Respuesta<TenenciaPlazoFijoView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijo(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
			
	}
	
	
	/**
	 * Plazo Fijo obtenerTenencias OK.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciasPlazoFijoTestOK_EstadisticaERROR(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
			
		Respuesta<TenenciaPlazoFijoView> rtaFactory = new Respuesta<TenenciaPlazoFijoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(TenenciaPlazoFijoView.class)))
				.thenReturn(rtaFactory);

		Respuesta<TenenciaTotalPlazoFijoDTO> rtaBo = new Respuesta<TenenciaTotalPlazoFijoDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		when(plazoFijoBO.obtenerTenenciasPlazoFijo(Matchers.any(Cliente.class)))
				.thenReturn(rtaBo);
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
			
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("ERROR");
		
		Respuesta<TenenciaPlazoFijoView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijo(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
			
	}
	
	
	/**
	 * Plazo Fijo obtenerTenencias ERROR, estadistica OK.
	 */

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciasPlazoFijoTestERROR_EstadisticaOK(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
						
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<TenenciaTotalPlazoFijoDTO> rtaBo = new Respuesta<TenenciaTotalPlazoFijoDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.obtenerTenenciasPlazoFijo(Matchers.any(Cliente.class)))
		.thenReturn(rtaBo);
				
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("OK");

		Respuesta<TenenciaPlazoFijoView> respuestaViewError = new Respuesta<TenenciaPlazoFijoView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);
		
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(TenenciaPlazoFijoView.class), Matchers.any(TipoError.class), Matchers.anyString(), Matchers.anyString())).thenReturn(respuestaViewError);
		
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
		
						
		Respuesta<TenenciaPlazoFijoView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijo(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaManager.getEstadoRespuesta());
		
		
	}
	
	
	/**
	 * Plazo Fijo obtenerTenencias ERROR, estadistica ERROR.
	 */

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciasPlazoFijoTestERROR_EstadisticaERROR(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
						
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<TenenciaTotalPlazoFijoDTO> rtaBo = new Respuesta<TenenciaTotalPlazoFijoDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.obtenerTenenciasPlazoFijo(Matchers.any(Cliente.class)))
		.thenReturn(rtaBo);
				
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("ERROR");

		Respuesta<TenenciaPlazoFijoView> respuestaViewError = new Respuesta<TenenciaPlazoFijoView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);
		
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(TenenciaPlazoFijoView.class), Matchers.any(TipoError.class), Matchers.anyString(), Matchers.anyString())).thenReturn(respuestaViewError);
		
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
						
		Respuesta<TenenciaPlazoFijoView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijo(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaManager.getEstadoRespuesta());
		
	}
    
	
	
	/**
	 * Plazo Fijo obtenerTenencias WARNINIG, estadistica OK.
	 */

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciasPlazoFijoTestWARNING_EstadisticaOK(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
						
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<TenenciaTotalPlazoFijoDTO> rtaBo = new Respuesta<TenenciaTotalPlazoFijoDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.obtenerTenenciasPlazoFijo(Matchers.any(Cliente.class)))
		.thenReturn(rtaBo);
				
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("OK");

		Respuesta<TenenciaPlazoFijoView> respuestaViewError = new Respuesta<TenenciaPlazoFijoView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);
				
		when(respuestaFactory
				.crearRespuestaWarning(Matchers.eq(TenenciaPlazoFijoView.class),
						Matchers.any(TenenciaPlazoFijoView.class), Matchers.anyList())).thenReturn(respuestaViewError);
		
				
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
			
		Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
				
		Respuesta<TenenciaPlazoFijoView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijo(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaManager.getEstadoRespuesta());
		
	}
	
	
	/**
	 * Plazo Fijo obtenerTenencias WARNINIG, estadistica ERROR.
	 */

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciasPlazoFijoTestWARNING_EstadisticaERROR(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
						
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<TenenciaTotalPlazoFijoDTO> rtaBo = new Respuesta<TenenciaTotalPlazoFijoDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.obtenerTenenciasPlazoFijo(Matchers.any(Cliente.class)))
		.thenReturn(rtaBo);
				
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("ERROR");

		Respuesta<TenenciaPlazoFijoView> respuestaViewError = new Respuesta<TenenciaPlazoFijoView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);
				
		when(respuestaFactory
				.crearRespuestaWarning(Matchers.eq(TenenciaPlazoFijoView.class),
						Matchers.any(TenenciaPlazoFijoView.class), Matchers.anyList())).thenReturn(respuestaViewError);
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
		
			
		Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
				
		
		Respuesta<TenenciaPlazoFijoView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijo(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaManager.getEstadoRespuesta());
		
	}
	
	
	
	/**
	 * Plazo Fijo obtenerTenencias BPriv OK.
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void obtenerTenenciasPlazoFijoBPrivTestOK_EstadisticaOK(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
			
		Respuesta<TenenciaPlazoFijoBprivView> rtaFactory = new Respuesta<TenenciaPlazoFijoBprivView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(TenenciaPlazoFijoBprivView.class)))
				.thenReturn(rtaFactory);

		Respuesta<TenenciaPlazoFijoBprivDTO> rtaBo = new Respuesta<TenenciaPlazoFijoBprivDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		when(plazoFijoBO.obtenerTenenciasPlazoFijoBpriv(Matchers.any(Cliente.class), Matchers.anyList()))
				.thenReturn(rtaBo);
			
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
			
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("OK");
		
		Respuesta<TenenciaPlazoFijoBprivView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijoBPriv(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
			
	}
	
	
	/**
	 * Plazo Fijo obtenerTenencias Estadistica Error.
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void obtenerTenenciasPlazoFijoBPrivTestOK_EstadisticaERROR(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
			
		Respuesta<TenenciaPlazoFijoView> rtaFactory = new Respuesta<TenenciaPlazoFijoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(TenenciaPlazoFijoView.class)))
				.thenReturn(rtaFactory);

		Respuesta<TenenciaPlazoFijoBprivDTO> rtaBo = new Respuesta<TenenciaPlazoFijoBprivDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		when(plazoFijoBO.obtenerTenenciasPlazoFijoBpriv(Matchers.any(Cliente.class), Matchers.anyList()))
				.thenReturn(rtaBo);
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
			
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("ERROR");
		
		Respuesta<TenenciaPlazoFijoBprivView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijoBPriv(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
			
	}
	
	
	
	/**
	 * Plazo Fijo BPriv obtenerTenencias ERROR, estadistica OK.
	 */

	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void obtenerTenenciasPlazoFijoBPrivTestERROR_EstadisticaOK(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
						
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<TenenciaPlazoFijoBprivDTO> rtaBo = new Respuesta<TenenciaPlazoFijoBprivDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.obtenerTenenciasPlazoFijoBpriv(Matchers.any(Cliente.class), Matchers.anyList()))
		.thenReturn(rtaBo);
				
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("OK");
		Respuesta<TenenciaPlazoFijoBprivView> respuestaViewError = new Respuesta<TenenciaPlazoFijoBprivView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);
		
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(TenenciaPlazoFijoView.class), Matchers.any(TipoError.class), Matchers.anyString(), Matchers.anyString())).thenReturn(respuestaViewError);
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
		
						
		Respuesta<TenenciaPlazoFijoBprivView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijoBPriv(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaManager.getEstadoRespuesta());
		
	}
	

	/**
	 * Plazo Fijo BPriv obtenerTenencias ERROR, estadistica ERROR.
	 */

	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void obtenerTenenciasPlazoFijoBPrivTestERROR_EstadisticaERROR(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
						
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<TenenciaPlazoFijoBprivDTO> rtaBo = new Respuesta<TenenciaPlazoFijoBprivDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.obtenerTenenciasPlazoFijoBpriv(Matchers.any(Cliente.class), Matchers.anyList()))
		.thenReturn(rtaBo);
				
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("ERROR");

		Respuesta<TenenciaPlazoFijoBprivView> respuestaViewError = new Respuesta<TenenciaPlazoFijoBprivView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);
		
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(TenenciaPlazoFijoView.class), Matchers.any(TipoError.class), Matchers.anyString(), Matchers.anyString())).thenReturn(respuestaViewError);
		
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
						
		Respuesta<TenenciaPlazoFijoBprivView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijoBPriv(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaManager.getEstadoRespuesta());
		
	}
	
	
	
	/**
	 * Plazo Fijo BPriv obtenerTenencias WARNINIG, estadistica OK.
	 */

	@Test
	@Ignore
	public void obtenerTenenciasPlazoFijoBPrivTestWARNING_EstadisticaOK(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
						
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<TenenciaPlazoFijoBprivDTO> rtaBo = new Respuesta<TenenciaPlazoFijoBprivDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.obtenerTenenciasPlazoFijoBpriv(Matchers.any(Cliente.class), Matchers.anyList()))
		.thenReturn(rtaBo);
				
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("OK");

		Respuesta<TenenciaPlazoFijoBprivView> respuestaViewError = new Respuesta<TenenciaPlazoFijoBprivView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);
				
		when(respuestaFactory
				.crearRespuestaWarning(Matchers.eq(TenenciaPlazoFijoBprivView.class),
						Matchers.any(TenenciaPlazoFijoBprivView.class), Matchers.any(TipoError.class),Matchers.anyString(), Matchers.anyString() )).thenReturn(respuestaViewError);
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
			
		Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
				
		Respuesta<TenenciaPlazoFijoBprivView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijoBPriv(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaManager.getEstadoRespuesta());
		
	}
	
	
	
	/**
	 * Plazo Fijo BPriv obtenerTenencias WARNINIG, estadistica ERROR.
	 */

	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void obtenerTenenciasPlazoFijoBPrivTestWARNING_EstadisticaERROR(){
		
		Set<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>> respValidator = new HashSet<ConstraintViolation<ObtenerTenenciasPlazoFijoInView>>();
		when(validator.validate(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(respValidator);
						
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<TenenciaPlazoFijoBprivDTO> rtaBo = new Respuesta<TenenciaPlazoFijoBprivDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.obtenerTenenciasPlazoFijoBpriv(Matchers.any(Cliente.class), Matchers.anyList()))
		.thenReturn(rtaBo);
		
		ObtenerTenenciasPlazoFijoInView inView = new ObtenerTenenciasPlazoFijoInView();
		inView.setEstadoTotales("ERROR");
				
		Respuesta<TenenciaPlazoFijoBprivView> respuestaViewError = new Respuesta<TenenciaPlazoFijoBprivView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);
				
		when(respuestaFactory
				.crearRespuestaWarning(Matchers.eq(TenenciaPlazoFijoBprivView.class),
						Matchers.any(TenenciaPlazoFijoBprivView.class), Matchers.any(TipoError.class),Matchers.anyString(), Matchers.anyString() )).thenReturn(respuestaViewError);
				
		when(respuestaFactory
				.crearRespuestaWarning(Matchers.eq(TenenciaPlazoFijoView.class),
						Matchers.any(TenenciaPlazoFijoView.class), Matchers.anyList())).thenReturn(respuestaViewError);
		
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
		
			
		Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
				
		
        Respuesta<TenenciaPlazoFijoBprivView> rtaManager = plazoFijoManager.obtenerTenenciasPlazoFijoBPriv(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaManager.getEstadoRespuesta());
		
	}
	
	
	
	/**
	 * Plazo Fijo Solicitar Precancelar OK.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void solicitarPrecancelarTestOK(){
		
		Set<ConstraintViolation<SolicitarPrecancelarInView>> respValidator = new HashSet<ConstraintViolation<SolicitarPrecancelarInView>>();
		when(validator.validate(Matchers.any(SolicitarPrecancelarInView.class))).thenReturn(respValidator);
		
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
    	respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
    	respuestaLegales.setRespuesta("prueba");
    	Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);
		
		Respuesta<SolicitarPrecancelarOutDTO> rtaBo = new Respuesta<SolicitarPrecancelarOutDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.solicitarPrecancelarPlazoFijo(Matchers.any(SolicitarPrecancelarInView.class),Matchers.any(Cliente.class), Matchers.anyBoolean(), Matchers.anyBoolean()))
		.thenReturn(rtaBo);
		
		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("Si cancelas el Plazo Fijo, los intereses se veran reducidos por el porcentaje de penalizacion (FEE).");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);
		
		SolicitarPrecancelarInView inView = new SolicitarPrecancelarInView();
		Respuesta<SolicitarPrecancelarOutView> respuestaView = new Respuesta<SolicitarPrecancelarOutView>();
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SolicitarPrecancelarOutView.class))).thenReturn(respuestaView);
								
		Respuesta<SolicitarPrecancelarOutView> rtaManager = plazoFijoManager.solicitarPrecancelar(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
		
	}
	
	
	
	
	/**
	 * Plazo Fijo Solicitar Precancelar WARNING.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void solicitarPrecancelarTestWARNING(){

		Set<ConstraintViolation<SolicitarPrecancelarInView>> respValidator = new HashSet<ConstraintViolation<SolicitarPrecancelarInView>>();
		when(validator.validate(Matchers.any(SolicitarPrecancelarInView.class))).thenReturn(respValidator);
		
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
    	respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
    	respuestaLegales.setRespuesta("prueba");
    	Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);
		
		Respuesta<SolicitarPrecancelarOutDTO> rtaBo = new Respuesta<SolicitarPrecancelarOutDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.solicitarPrecancelarPlazoFijo(Matchers.any(SolicitarPrecancelarInView.class),Matchers.any(Cliente.class), Matchers.anyBoolean(), Matchers.anyBoolean()))
		.thenReturn(rtaBo);
		
		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("Si cancelas el Plazo Fijo, los intereses se veran reducidos por el porcentaje de penalizacion (FEE).");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);
		
		SolicitarPrecancelarInView inView = new SolicitarPrecancelarInView();
		Respuesta<SolicitarPrecancelarOutView> respuestaView = new Respuesta<SolicitarPrecancelarOutView>();
		respuestaView.setEstadoRespuesta(EstadoRespuesta.WARNING);
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class),Matchers.anyString(), Matchers.anyList())).thenReturn(respuestaView);
		
		Respuesta<SolicitarPrecancelarOutView> rtaManager = plazoFijoManager.solicitarPrecancelar(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.WARNING, rtaManager.getEstadoRespuesta());
		
	}
	
	
	
	/**
	 * Plazo Fijo Solicitar Precancelar ERROR.
	 */

	@Test
	public void solicitarPrecancelarTestERROR(){
		
		Set<ConstraintViolation<SolicitarPrecancelarInView>> respValidator = new HashSet<ConstraintViolation<SolicitarPrecancelarInView>>();
		when(validator.validate(Matchers.any(SolicitarPrecancelarInView.class))).thenReturn(respValidator);
		
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
    	respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
    	respuestaLegales.setRespuesta("prueba");
    	Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);
		
		Respuesta<SolicitarPrecancelarOutDTO> rtaBo = new Respuesta<SolicitarPrecancelarOutDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		when(plazoFijoBO.solicitarPrecancelarPlazoFijo(Matchers.any(SolicitarPrecancelarInView.class),Matchers.any(Cliente.class), Matchers.anyBoolean(), Matchers.anyBoolean()))
		.thenReturn(rtaBo);
		
		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("Si cancelas el Plazo Fijo, los intereses se veran reducidos por el porcentaje de penalizacion (FEE).");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);
		
		SolicitarPrecancelarInView inView = new SolicitarPrecancelarInView();
		Respuesta<Object> respuestaView = new Respuesta<Object>();
		respuestaView.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),Matchers.anyString())).thenReturn(respuestaView);
		
		
		Respuesta<SolicitarPrecancelarOutView> rtaManager = plazoFijoManager.solicitarPrecancelar(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.ERROR, rtaManager.getEstadoRespuesta());
		
	}
	
	
	
	/**
	 * Plazo Fijo simular Precancelar OK.
	 */
	@Test
	public void simularPrecancelarTestOK(){
		
		Set<ConstraintViolation<SolicitarPrecancelarInView>> respValidator = new HashSet<ConstraintViolation<SolicitarPrecancelarInView>>();
		when(validator.validate(Matchers.any(SolicitarPrecancelarInView.class))).thenReturn(respValidator);
		
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		SimulacionPrecancelableDTO simulacionPrecancelableDTO = new SimulacionPrecancelableDTO();
		simulacionPrecancelableDTO.setCapital("5000");
		simulacionPrecancelableDTO.setInteresAlVencimiento("10");
		simulacionPrecancelableDTO.setFechaAltaPF("10/10/2010");
		simulacionPrecancelableDTO.setInteresAFecha("100");
		simulacionPrecancelableDTO.setInteresesPrecancelacion("1");
		simulacionPrecancelableDTO.setPorcentajePenalizacion("1");
		simulacionPrecancelableDTO.setFechaVencimientoPF("10/09/2017");
		simulacionPrecancelableDTO.setFechaAltaPF("10/09/2017");
		simulacionPrecancelableDTO.setTotalAPagar("100");
				
		Respuesta<SimulacionPrecancelableDTO> rtaBo = new Respuesta<SimulacionPrecancelableDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		rtaBo.setRespuesta(simulacionPrecancelableDTO);
		when(plazoFijoBO.precancelar(Matchers.any(Cliente.class),Matchers.any(SimularPrecancelarInView.class),Matchers.anyString())).thenReturn(rtaBo);
	
		
		SimularPrecancelarInView inView = new SimularPrecancelarInView();
		Respuesta<SimulacionPrecancelableOutView> respuestaView = new Respuesta<SimulacionPrecancelableOutView>();
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(SimulacionPrecancelableOutView.class))).thenReturn(respuestaView);
		
		Respuesta<SimulacionPrecancelableOutView> rtaManager = plazoFijoManager.simularPrecancelacion(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
		
	}
	
	
	/**
	 * Plazo Fijo simular Precancelar ERROR.
	 */
	@Test
	public void simularPrecancelarTestERROR(){
		
		Set<ConstraintViolation<SolicitarPrecancelarInView>> respValidator = new HashSet<ConstraintViolation<SolicitarPrecancelarInView>>();
		when(validator.validate(Matchers.any(SolicitarPrecancelarInView.class))).thenReturn(respValidator);
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
				
		SimulacionPrecancelableDTO simulacionPrecancelableDTO = new SimulacionPrecancelableDTO();
		simulacionPrecancelableDTO.setCapital("500");
		simulacionPrecancelableDTO.setInteresAlVencimiento("10");
		simulacionPrecancelableDTO.setFechaAltaPF("10/10/2010");
		simulacionPrecancelableDTO.setInteresAFecha("100");
		simulacionPrecancelableDTO.setInteresesPrecancelacion("1");
		simulacionPrecancelableDTO.setPorcentajePenalizacion("1");
		simulacionPrecancelableDTO.setFechaVencimientoPF("10/09/2017");
		simulacionPrecancelableDTO.setFechaAltaPF("10/09/2017");
		simulacionPrecancelableDTO.setTotalAPagar("100");
				
		Respuesta<SimulacionPrecancelableDTO> rtaBo = new Respuesta<SimulacionPrecancelableDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		rtaBo.setRespuesta(simulacionPrecancelableDTO);
		when(plazoFijoBO.precancelar(Matchers.any(Cliente.class),Matchers.any(SimularPrecancelarInView.class),Matchers.anyString())).thenReturn(rtaBo);
				
		SimularPrecancelarInView inView = new SimularPrecancelarInView();
		Respuesta<Object> respuestaView = new Respuesta<Object>();
		respuestaView.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError(Matchers.anyString(),Matchers.any(TipoError.class),Matchers.anyString())).thenReturn(respuestaView);				
		Respuesta<SimulacionPrecancelableOutView> rtaManager = plazoFijoManager.simularPrecancelacion(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.ERROR, rtaManager.getEstadoRespuesta());		
	}
	
		
	
	@Test
	public void finalizarPrecancelacionTestOK(){
		ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();        
		Set<ConstraintViolation<SolicitarPrecancelarInView>> respValidator = new HashSet<ConstraintViolation<SolicitarPrecancelarInView>>();
		when(validator.validate(Matchers.any(SolicitarPrecancelarInView.class))).thenReturn(respValidator);
		
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		
		SimulacionPrecancelableDTO simulacionPrecancelableDTO = new SimulacionPrecancelableDTO();
		simulacionPrecancelableDTO.setCapital("5000");
		simulacionPrecancelableDTO.setInteresAlVencimiento("10");
		simulacionPrecancelableDTO.setFechaAltaPF("30072014");
		simulacionPrecancelableDTO.setInteresAFecha("100");
		simulacionPrecancelableDTO.setInteresesPrecancelacion("1");
		simulacionPrecancelableDTO.setPorcentajePenalizacion("1");
		simulacionPrecancelableDTO.setFechaVencimientoPF("26012015");
		simulacionPrecancelableDTO.setTotalAPagar("100");
		simulacionPrecancelableDTO.setPlazoActual("30");
		
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		
		Respuesta<SimulacionPrecancelableDTO> rtaBo = new Respuesta<SimulacionPrecancelableDTO>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		rtaBo.setRespuesta(simulacionPrecancelableDTO);
		when(plazoFijoBO.precancelar(Matchers.any(Cliente.class),Matchers.any(SimularPrecancelarInView.class),Matchers.anyString())).thenReturn(rtaBo);
		
		Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		
		SimularPrecancelarInView inView = new SimularPrecancelarInView();
		Respuesta<FinalizarPrecancelableOutView> respuestaView = new Respuesta<FinalizarPrecancelableOutView>();
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(FinalizarPrecancelableOutView.class))).thenReturn(respuestaView);
		
		Respuesta<FinalizarPrecancelableOutView> rtaManager = plazoFijoManager.finalizarPrecancelacion(inView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
				
	}
	
	
	/**
	 * Ver detalle Plazo Fijo OK Ajustable por CER("30")
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void verDetalleAjustablePorCER_OK() {
		Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		inView.setCodigoPlazo("30");

		Respuesta<String> respuestaLegales = new Respuesta<String>();
		respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaLegales.setRespuesta("prueba");
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);

		Respuesta<ConfirmacionPlazoFijoOutView> rtaManager = new Respuesta<ConfirmacionPlazoFijoOutView>();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ConfirmacionPlazoFijoOutView.class))).thenReturn(rtaManager);

		Respuesta<ConfirmacionPlazoFijoOutView> respuesta = plazoFijoManager.verDetalle(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}
	
	
	
	/**
	 * Ver detalle Plazo Fijo ERROR Ajustable por CER("30")
	 */
	@Ignore
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void verDetalleAjustablePorCER_ERROR() {
		Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		inView.setCodigoPlazo("30");
		Respuesta<String> respuestaLegales = new Respuesta<String>();
		respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);

		Respuesta rtaManager = new Respuesta();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO)).thenReturn(rtaManager);

		Respuesta<ConfirmacionPlazoFijoOutView> respuesta = plazoFijoManager.verDetalle(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}
		
	
	
	/**
	 * Ver detalle Plazo Fijo OK UVA("60")
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void verDetalle_UVA_OK() {
		Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		inView.setCodigoPlazo("60");

		Respuesta<String> respuestaLegales = new Respuesta<String>();
		respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaLegales.setRespuesta("prueba");
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);

		Respuesta<ConfirmacionPlazoFijoOutView> rtaManager = new Respuesta<ConfirmacionPlazoFijoOutView>();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ConfirmacionPlazoFijoOutView.class))).thenReturn(rtaManager);

		Respuesta<ConfirmacionPlazoFijoOutView> respuesta = plazoFijoManager.verDetalle(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}
	
	
	/**
	 * Ver detalle Plazo Fijo ERROR UVA ("60")
	 */
	@Ignore
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void verDetalle_UVA_ERROR() {
		Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		inView.setCodigoPlazo("60");
		Respuesta<String> respuestaLegales = new Respuesta<String>();
		respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);

		Respuesta rtaManager = new Respuesta();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO)).thenReturn(rtaManager);

		Respuesta<ConfirmacionPlazoFijoOutView> respuesta = plazoFijoManager.verDetalle(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}
	
	
	/**
	 * Ver detalle Plazo Otro Plazo Fijo
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void verDetalle_Otro_OK() {
		Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		inView.setCodigoPlazo("16");
		
		Respuesta<String> respuestaLegales = new Respuesta<String>();
		respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaLegales.setRespuesta("prueba");
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);

		Respuesta<ConfirmacionPlazoFijoOutView> rtaManager = new Respuesta<ConfirmacionPlazoFijoOutView>();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ConfirmacionPlazoFijoOutView.class))).thenReturn(rtaManager);

		Respuesta<ConfirmacionPlazoFijoOutView> respuesta = plazoFijoManager.verDetalle(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}
	
	
	
	
	/**
	 * Ver detalle Plazo Fijo BPriv OK Ajustable por CER("30")
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void verDetalleAjustablePorCERBPriv_OK() {
		Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		inView.setCodigoPlazo("30");

		Respuesta<String> respuestaLegales = new Respuesta<String>();
		respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaLegales.setRespuesta("prueba");
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);

		Respuesta<ConfirmacionPlazoFijoOutView> rtaManager = new Respuesta<ConfirmacionPlazoFijoOutView>();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ConfirmacionPlazoFijoOutView.class))).thenReturn(rtaManager);

		Respuesta<ConfirmacionPlazoFijoOutView> respuesta = plazoFijoManager.verDetalleBPriv(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Ver detalle Plazo Fijo BPriv ERROR Ajustable por CER("30")
	 */
	@Ignore
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void verDetalleAjustablePorCERBPriv_ERROR() {
		Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		inView.setCodigoPlazo("30");
		Respuesta<String> respuestaLegales = new Respuesta<String>();
		respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);

		Respuesta rtaManager = new Respuesta();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO)).thenReturn(rtaManager);

		Respuesta<ConfirmacionPlazoFijoOutView> respuesta = plazoFijoManager.verDetalleBPriv(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}

	/**
	 * Ver detalle Plazo Fijo BPriv OK UVA("60")
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void verDetalle_UVABPriv_OK() {
		Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		inView.setCodigoPlazo("60");

		Respuesta<String> respuestaLegales = new Respuesta<String>();
		respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaLegales.setRespuesta("prueba");
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);

		Respuesta<ConfirmacionPlazoFijoOutView> rtaManager = new Respuesta<ConfirmacionPlazoFijoOutView>();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ConfirmacionPlazoFijoOutView.class))).thenReturn(rtaManager);

		Respuesta<ConfirmacionPlazoFijoOutView> respuesta = plazoFijoManager.verDetalleBPriv(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Ver detalle Plazo Fijo BPriv ERROR UVA ("60")
	 */
	@Ignore
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void verDetalle_UVABPriv_ERROR() {
		Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		inView.setCodigoPlazo("60");
		Respuesta<String> respuestaLegales = new Respuesta<String>();
		respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);

		Respuesta rtaManager = new Respuesta();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO)).thenReturn(rtaManager);

		Respuesta<ConfirmacionPlazoFijoOutView> respuesta = plazoFijoManager.verDetalleBPriv(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}

	/**
	 * Ver detalle Plazo BPriv Otro Plazo Fijo
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void verDetalle_OtroBPriv_OK() {
		Set<ConstraintViolation<AccionesAlVencimientoInView>> respValidator = new HashSet<ConstraintViolation<AccionesAlVencimientoInView>>();
		when(validator.validate(Matchers.any(AccionesAlVencimientoInView.class))).thenReturn(respValidator);

		Respuesta<String> respuestaLegales = new Respuesta<String>();
		respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaLegales.setRespuesta("prueba");
		Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegales);
		
		AccionesAlVencimientoInView inView = new AccionesAlVencimientoInView();
		inView.setCodigoPlazo("16");

		Respuesta<ConfirmacionPlazoFijoOutView> rtaManager = new Respuesta<ConfirmacionPlazoFijoOutView>();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ConfirmacionPlazoFijoOutView.class))).thenReturn(rtaManager);

		Respuesta<ConfirmacionPlazoFijoOutView> respuesta = plazoFijoManager.verDetalleBPriv(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerOrdenesPorCuentaOK(){
	
		Respuesta<OrdenesDTO> rtaOrdenesDTO = crearRespuestaOkMockOrdenesDTO();
		when(ordenesBO.buscarOrdenesOperacionesPorCuenta(Matchers.anyString(), Matchers.anyString())).thenReturn(rtaOrdenesDTO);

		Respuesta<OrdenesView> respuestaViewOk = new Respuesta<OrdenesView>();
		respuestaViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(OrdenesView.class))).thenReturn(respuestaViewOk);
		
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		
		Cliente cliente = crearClienteConCuentaBancaPrivadaYTitulo();
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		ObtenerOrdenesViewRequest request = new ObtenerOrdenesViewRequest();
		request.setNumeroCuenta("12345678");
		Respuesta<OrdenesView> respuesta = plazoFijoManager.obtenerOrdenesPorCuenta(request);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerOrdenesPorCuentaError(){
	
		Respuesta<OrdenesDTO> rtaOrdenesDTO = crearRespuestaOkMockOrdenesDTO();
		rtaOrdenesDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(ordenesBO.buscarOrdenesOperacionesPorCuenta(Matchers.anyString(), Matchers.anyString())).thenReturn(rtaOrdenesDTO);

		Cliente cliente = crearClienteConCuentaBancaPrivadaYTitulo();
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		@SuppressWarnings("rawtypes")
		Respuesta<Class> respuestaViewError = new Respuesta<Class>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.any(OrdenesView.class), Matchers.any(String.class), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaViewError);
		
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		
		ObtenerOrdenesViewRequest request = new ObtenerOrdenesViewRequest();
		request.setNumeroCuenta("12345678");
		Respuesta<OrdenesView> respuesta = plazoFijoManager.obtenerOrdenesPorCuenta(request);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerOrdenesPorCuentaSinOrdenes(){
	
		Respuesta<OrdenesDTO> rtaOrdenesDTO = new Respuesta<OrdenesDTO>();
		rtaOrdenesDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		OrdenesDTO ordenesDTO = new OrdenesDTO();
		rtaOrdenesDTO.setRespuesta(ordenesDTO);
		List<OrdenBaseDTO> listaOrdenesVaciaDTO = new ArrayList<OrdenBaseDTO>();
		ordenesDTO.setOrdenes(listaOrdenesVaciaDTO);
		when(ordenesBO.buscarOrdenesOperacionesPorCuenta(Matchers.anyString(), Matchers.anyString())).thenReturn(rtaOrdenesDTO);

		Respuesta<Object> respuestaViewError = new Respuesta<Object>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(OrdenesView.class), Matchers.any(TipoError.class), Matchers.anyString(), Matchers.anyString())).thenReturn(respuestaViewError);
		
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		
		Cliente cliente = crearClienteConCuentaBancaPrivadaYTitulo();
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		ObtenerOrdenesViewRequest request = new ObtenerOrdenesViewRequest();
		request.setNumeroCuenta("12345678");
		Respuesta<OrdenesView> respuesta = plazoFijoManager.obtenerOrdenesPorCuenta(request);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerOrdenesPorCuentaConFiltroOK(){
	
		Respuesta<OrdenesDTO> rtaOrdenesDTO = crearRespuestaOkMockOrdenesDTO();
		when(ordenesBO.buscarOrdenesOperaciones(Matchers.any(FiltrosOrdenesView.class), Matchers.anyString())).thenReturn(rtaOrdenesDTO);

		Respuesta<OrdenesView> respuestaViewOk = new Respuesta<OrdenesView>();
		respuestaViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(OrdenesView.class))).thenReturn(respuestaViewOk);
		
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		
		Cliente cliente = crearClienteConCuentaBancaPrivadaYTitulo();
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		ObtenerOrdenesViewRequest request = new ObtenerOrdenesViewRequest();
		request.setNumeroCuenta("12345678");
		request.setTipoConsulta("E");
		request.setFechaDesde("21022018");
		request.setFechaHasta("20032018");
		Respuesta<OrdenesView> respuesta = plazoFijoManager.obtenerOrdenesPorCuenta(request);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	private Respuesta<OrdenesDTO> crearRespuestaOkMockOrdenesDTO() {
		Respuesta<OrdenesDTO> rtaOrdenesDTO = new Respuesta<OrdenesDTO>();
		rtaOrdenesDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		OrdenesDTO ordenesDTO = new OrdenesDTO();
		rtaOrdenesDTO.setRespuesta(ordenesDTO);
		List<OrdenBaseDTO> listaOrdenesDTO = new ArrayList<OrdenBaseDTO>();
		ordenesDTO.setOrdenes(listaOrdenesDTO);
		OrdenPlazoFijoDTO ordenPlazoFijoDTO = new OrdenPlazoFijoDTO();
		listaOrdenesDTO.add((OrdenBaseDTO) ordenPlazoFijoDTO);
		ordenPlazoFijoDTO.setAccionAlVencimiento("Accion al vencimiento");
		ordenPlazoFijoDTO.setMonedaLiq("P");
		ordenPlazoFijoDTO.setTna("1234561234");
		ordenPlazoFijoDTO.setEstado("Aprobada");
		return rtaOrdenesDTO;
	}
	
	private Cliente crearClienteConCuentaBancaPrivadaYTitulo() {
		Cliente cliente = new Cliente();
		List<CuentaBancaPrivada> listaCuentasBancaPrivada = new ArrayList<CuentaBancaPrivada>();
		cliente.setCuentasBancaPrivada(listaCuentasBancaPrivada);
		CuentaBancaPrivada cuentaBancaPrivada = new CuentaBancaPrivada();
		Cuenta cuentaOperativa = new Cuenta();
		cuentaOperativa.setNroCuentaProducto("1234567");
		cuentaBancaPrivada.setCuentaOperativa(cuentaOperativa);
		Cuenta cuentaTitulo = new Cuenta();
		cuentaTitulo.setNroCuentaProducto("7654321");
		cuentaBancaPrivada.setCuentaTitulo(cuentaTitulo);
		listaCuentasBancaPrivada.add(cuentaBancaPrivada);
		return cliente;
	}
	
	@Test
	public void testObtenerRecomendacionManager() {
	
		RecomendacionResponseEntity response = new RecomendacionResponseEntity();
		DatosRecomendacionResponse datos = new DatosRecomendacionResponse();
		datos.setCanSee(true);
		
		response.setDatos(datos);
		
		RecomendacionInView viewRequest = new RecomendacionInView();
		viewRequest.setDaysCount(90);
		
		when(plazoFijoBO.obtenerRecomendacion(viewRequest)).thenReturn(response);
		
		Respuesta<RecomendacionResponseEntity> respuesta;
		
		respuesta = respuestaFactory.crearRespuestaOk(RecomendacionResponseEntity.class,response);
		
		Respuesta<RecomendacionResponseEntity> respuestaManager = plazoFijoManager.obtenerRecomendacionManager(viewRequest);
		
		assertEquals(respuesta, respuestaManager);
	}
}
