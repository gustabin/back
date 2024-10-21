/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.segmento.bo.SegmentoClienteBO;
import ar.com.santanderrio.obp.servicios.turnosweb.bo.TurnosWebBO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.CitaDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.CitaOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.ComprobanteTurnoInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.DiasDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.FraccionHorariaDisponibleDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.MotivoTurnoDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.MotivosInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.SucursalesDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.SucursalesOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.manager.impl.TurnosWebManagerImpl;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.BajaTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.CitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.DatosMYAView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.SucursalesOutView;

/**
 * The Class TurnosWebManagerTest.
 * 
 * @author ITResources
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		TurnosWebManagerTest.TurnosWebManagerTestConfiguration.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {
        "TURNOSWEB.CAJA.HABILITADO.GENERAL = 1",
        "TURNOSWEB.EJECUTIVO.HABILITADO.GENERAL = 1",
        "TURNOSWEB.VERIFICAR.DNIPERMITIDOS = 1",
        "TURNOSWEB.DNIPERMITIDOS.1 = 0,1",
        "TURNOSWEB.DNIPERMITIDOS.2 = 2,3",
        "TURNOSWEB.DNIPERMITIDOS.3 = 4,5",
        "TURNOSWEB.DNIPERMITIDOS.4 = 6,7",
        "TURNOSWEB.DNIPERMITIDOS.5 = 8,9",
        "AUTOGESTION.URLCARDCORRESPONSALIA = "
        })
@ActiveProfiles(value = Profiles.TEST)
public class TurnosWebManagerTest {

	/**
     * The Class TurnosWebManagerTestConfiguration.
     */
    @ComponentScan(basePackageClasses = TurnosWebManagerTest.class, useDefaultFilters = false, includeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = TurnosWebManagerTest.class) })
	public static class TurnosWebManagerTestConfiguration {

		/**
		 * Property configurer.
		 *
		 * @return the property sources placeholder configurer
		 */
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
			return new PropertySourcesPlaceholderConfigurer();
		}

        /**
         * Sesion parametros.
         *
         * @return the sesion parametros
         */
        @Bean
        public static SesionParametros sesionParametros() {
            return Mockito.mock(SesionParametros.class);
        }

        /**
         * Sesion cliente.
         *
         * @return the sesion cliente
         */
        @Bean
        public static SesionCliente sesionCliente() {
            return Mockito.mock(SesionCliente.class);
        }

        /**
         * Turnos web BO.
         *
         * @return the turnos web BO
         */
        @Bean
        public static TurnosWebBO turnosWebBO() {
            return Mockito.mock(TurnosWebBO.class);
        }

        /**
         * Estadistica manager.
         *
         * @return the estadistica manager
         */
        @Bean
        public static EstadisticaManager estadisticaManager() {
            return Mockito.mock(EstadisticaManager.class);
        }

        /**
         * Respuesta factory.
         *
         * @return the respuesta factory
         */
        @Bean
        public static RespuestaFactory respuestaFactory() {
            return new RespuestaFactory();
        }

        /**
         * Segmento cliente BO.
         *
         * @return the segmento cliente BO
         */
        @Bean
        public static SegmentoClienteBO segmentoClienteBO() {
            return Mockito.mock(SegmentoClienteBO.class);
        }

        /**
         * Legal BO.
         *
         * @return the legal BO
         */
        @Bean
        public static LegalBO legalBO() {
            return Mockito.mock(LegalBO.class);
        }

        /**
         * Mensaje BO.
         *
         * @return the mensaje BO
         */
        @Bean
        public static MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }

        /**
         * Turnos web manager.
         *
         * @return the turnos web manager
         */
        @Bean
        public static PropertyMap propertyMap() {
        	return Mockito.mock(PropertyMap.class);
        }

        /**
         * Turnos web manager.
         *
         * @return the turnos web manager
         */
        @Bean
        public static TurnosWebManager turnosWebManager() {
            return new TurnosWebManagerImpl();
        }
	}

	/** The turnos web caja habilitado general. */
	@Value("${TURNOSWEB.CAJA.HABILITADO.GENERAL:0}")
	private String turnosWebCajaHabilitadoGeneral;

	/** The turnos web ejecutivo habilitado general. */
	@Value("${TURNOSWEB.EJECUTIVO.HABILITADO.GENERAL:0}")
	private String turnosWebEjecutivoHabilitadoGeneral;

	/** The verificar dias permitidos. */
	@Value("${TURNOSWEB.VERIFICAR.DNIPERMITIDOS:0}")
	private String verificarDiasPermitidos;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /** The turnos web manager. */
	@Autowired
    @InjectMocks
    private TurnosWebManager turnosWebManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    /** The property map. */
    @Mock
    private PropertyMap propertyMap;

    /** The turnos web bo. */
    @Mock
    private TurnosWebBO turnosWebBO;
    
    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

	/** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;

    /** The mensaje bo. */
    @Mock
    private MensajeBO mensajeBO;
    
    /** The estadisticas manager. */
    @Mock
    private EstadisticaManager EstadisticaManager;
    
	/** The segmento cliente BO. */
    @Mock
    private SegmentoClienteBO segmentoClienteBO;
    
	@Test
	public void consultarCitasOK() {
		List<CitaDTO> citaDTOs = new ArrayList<CitaDTO>();
		CitaDTO cita = new CitaDTO();
		cita.setAnio(2018);
		cita.setDescripcionSucursal("");
		cita.setDireccion("Bartolome Mitre 480");
		cita.setDia("Jueves");
		cita.setDiaNumero(14);
		cita.setMes("Junio");
		cita.setHorario("10:20 hs");
		cita.setSucursal("000");
		cita.setIdTurno((long) 214283);
		cita.setSector("C");
		cita.setDescripcionSucursal("Casa central");
		cita.setFecha("20180404");
		cita.setFraccion("1900-2000");
		citaDTOs.add(cita);
		CitaOutDTO citaOutDTO = new CitaOutDTO();
		citaOutDTO.setCitaDTOList(citaDTOs);	
		Respuesta<CitaOutDTO> respuestaBO = new Respuesta<CitaOutDTO>();
		respuestaBO.setRespuesta(citaOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

		Respuesta<List<MotivoTurnoDTO>> respuestaMotivosBO = new Respuesta<List<MotivoTurnoDTO>>();
		List<MotivoTurnoDTO> motivosTurnoDTO = new ArrayList<MotivoTurnoDTO>();
		MotivoTurnoDTO motivoTurnoDTO = new MotivoTurnoDTO("0", "descripcion motivo");
		motivosTurnoDTO.add(motivoTurnoDTO);
		respuestaMotivosBO.setRespuesta(motivosTurnoDTO);
		respuestaMotivosBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        cliente.setDni("12345678");
        
        CredencialesMya credencialesMya = new CredencialesMya();
		credencialesMya.setClienteEstado("SA");
        credencialesMya.setEmail("isban@isbanexternos.com.ar");
        credencialesMya.setCelular("11111111");
        credencialesMya.setCodigoArea("11");
        credencialesMya.setCompaniaSeleccionada("Claro");
        
        Segmento segmentoCliente = new Segmento();
        segmentoCliente.setSelect(true);
        
        Respuesta<Segmento> respuestaSegmento = new Respuesta<Segmento>(); 
        respuestaSegmento.setRespuesta(segmentoCliente);
        
        when(segmentoClienteBO.obtenerSegmento(Matchers.any(Cliente.class))).thenReturn(respuestaSegmento);
        when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(turnosWebBO.consultarCitas(Matchers.anyString())).thenReturn(respuestaBO); 
        when(turnosWebBO.obtenerMotivosTurno(Matchers.any(MotivosInDTO.class))).thenReturn(respuestaMotivosBO);
        Respuesta<CitaOutView> respuesta = turnosWebManager.consultaCita(Boolean.FALSE);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}


	@Test
	public void consultarCitasMYAError() {
		List<CitaDTO> citaDTOs = new ArrayList<CitaDTO>();
		CitaDTO cita = new CitaDTO();
		cita.setAnio(2018);
		cita.setDescripcionSucursal("");
		cita.setDireccion("Bartolome Mitre 480");
		cita.setDia("Jueves");
		cita.setDiaNumero(14);
		cita.setMes("Junio");
		cita.setHorario("10:20 hs");
		cita.setSucursal("000");
		cita.setIdTurno((long) 214283);
		cita.setSector("C");
		cita.setDescripcionSucursal("Casa central");
		cita.setFecha("20180404");
		cita.setFraccion("1900-2000");
		citaDTOs.add(cita);
		CitaOutDTO citaOutDTO = new CitaOutDTO();
		citaOutDTO.setCitaDTOList(citaDTOs);	
		Respuesta<CitaOutDTO> respuestaBO = new Respuesta<CitaOutDTO>();
		respuestaBO.setRespuesta(citaOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

		Respuesta<List<MotivoTurnoDTO>> respuestaMotivosBO = new Respuesta<List<MotivoTurnoDTO>>();
		List<MotivoTurnoDTO> motivosTurnoDTO = new ArrayList<MotivoTurnoDTO>();
		MotivoTurnoDTO motivoTurnoDTO = new MotivoTurnoDTO("0", "descripcion motivo");
		motivosTurnoDTO.add(motivoTurnoDTO);
		respuestaMotivosBO.setRespuesta(motivosTurnoDTO);
		respuestaMotivosBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        cliente.setDni("12345678");
        
        CredencialesMya credencialesMya = new CredencialesMya();
		credencialesMya.setClienteEstado("NS");
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_DETALLE_TURNO)).thenReturn(mensaje);
		
        Segmento segmentoCliente = new Segmento();
        segmentoCliente.setSelect(true);
        
        Respuesta<Segmento> respuestaSegmento = new Respuesta<Segmento>(); 
        respuestaSegmento.setRespuesta(segmentoCliente);
        
        when(segmentoClienteBO.obtenerSegmento(Matchers.any(Cliente.class))).thenReturn(respuestaSegmento);
        
        when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(turnosWebBO.consultarCitas(Matchers.anyString())).thenReturn(respuestaBO); 
        when(turnosWebBO.obtenerMotivosTurno(Matchers.any(MotivosInDTO.class))).thenReturn(respuestaMotivosBO);
        Respuesta<CitaOutView> respuesta = turnosWebManager.consultaCita(Boolean.FALSE);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void consultarCitasErrorBO() {
		Respuesta<CitaOutDTO> respuestaBO = new Respuesta<CitaOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        	
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(turnosWebBO.consultarCitas(Matchers.anyString())).thenReturn(respuestaBO); 
        Respuesta<CitaOutView> respuesta = turnosWebManager.consultaCita(Boolean.FALSE);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	@Test
	public void consultaSucursalesOK() {
		Respuesta<SucursalesOutDTO> respuestaBO = new Respuesta<SucursalesOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		SucursalesOutDTO sucursalesOutDTO = new SucursalesOutDTO();				
		List<SucursalesDTO> sucursalesDTOs = new ArrayList<SucursalesDTO>();
		SucursalesDTO sucursalesDTO = new SucursalesDTO();
		sucursalesDTO.setDescripcion("Casa central");
		sucursalesDTO.setDomicilio("Bartolome mitre 000");
		sucursalesDTO.setLocalidad("Capital Federal");
		sucursalesDTO.setSucursal("000");
		sucursalesDTOs.add(sucursalesDTO);
		sucursalesOutDTO.setSucursalesDTOList(sucursalesDTOs);
		respuestaBO.setRespuesta(sucursalesOutDTO);
        when(turnosWebBO.consultaSucursales()).thenReturn(respuestaBO); 
        Respuesta<SucursalesOutView> respuesta = turnosWebManager.consultaSucursales();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void consultaSucursalesError() {
		Respuesta<SucursalesOutDTO> respuestaBO = new Respuesta<SucursalesOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);	
		when(turnosWebBO.consultaSucursales()).thenReturn(respuestaBO); 
        Respuesta<SucursalesOutView> respuesta = turnosWebManager.consultaSucursales();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
	@Test
	public void consultaHorariosDisponiblesMYADistintoSA() {
		Respuesta<HorariosDisponiblesOutDTO> respuestaBO = new Respuesta<HorariosDisponiblesOutDTO>();
        CredencialesMya credencialesMya = new CredencialesMya();
		credencialesMya.setClienteEstado("NS");        
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_CONFIGURACION_TURNO_MYA)).thenReturn(mensaje);	
		when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
        when(turnosWebBO.consultaHorariosDisponibles(Matchers.any(HorariosDisponiblesInDTO.class))).thenReturn(respuestaBO); 
        HorariosDisponiblesInView horariosDisponiblesInView = new HorariosDisponiblesInView();
        horariosDisponiblesInView.setNroSuc("000");
        horariosDisponiblesInView.setSector("P");
        Respuesta<HorariosDisponiblesOutView> respuesta = turnosWebManager.consultaHorariosDisponibles(horariosDisponiblesInView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
	@Test
	public void consultaHorariosDisponiblesOK() {
		Respuesta<HorariosDisponiblesOutDTO> respuestaBO = new Respuesta<HorariosDisponiblesOutDTO>();
		HorariosDisponiblesOutDTO horariosDisponiblesOutDTO = new HorariosDisponiblesOutDTO();
		horariosDisponiblesOutDTO.setDireccion("Bme.mitre 480- Capital Federal");
		horariosDisponiblesOutDTO.setIdSuc("000");
		horariosDisponiblesOutDTO.setIdTurno(null);
		horariosDisponiblesOutDTO.setSector("P");
		List<DiasDisponiblesDTO> diasDisponiblesDTOs = new ArrayList<DiasDisponiblesDTO>();
		DiasDisponiblesDTO diasDisponiblesDTO = new DiasDisponiblesDTO();
		diasDisponiblesDTO.setDescripcion("Jueves, 14 Junio 2018");
		diasDisponiblesDTO.setId("20180614");
		List<FraccionHorariaDisponibleDTO> fraccionHorariaDisponibleDTOs = new ArrayList<FraccionHorariaDisponibleDTO>();
		FraccionHorariaDisponibleDTO fraccionHorariaDisponibleDTO = new FraccionHorariaDisponibleDTO();
		fraccionHorariaDisponibleDTO.setDescripcion("10:00hs");
		fraccionHorariaDisponibleDTO.setId("1000");
		fraccionHorariaDisponibleDTO.setHoraInicio("1000");
		fraccionHorariaDisponibleDTO.setHoraFin("1030");
		fraccionHorariaDisponibleDTO.setFraccion("1000-1030");
		fraccionHorariaDisponibleDTO.setIdTurno((long) 0);
		fraccionHorariaDisponibleDTOs.add(fraccionHorariaDisponibleDTO);
		diasDisponiblesDTO.setFraccionHorariaDisponible(fraccionHorariaDisponibleDTOs);
		diasDisponiblesDTOs.add(diasDisponiblesDTO);
		horariosDisponiblesOutDTO.setDiasDisponibles(diasDisponiblesDTOs);
		respuestaBO.setRespuesta(horariosDisponiblesOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Respuesta<List<MotivoTurnoDTO>> respuestaMotivosBO = new Respuesta<List<MotivoTurnoDTO>>();
		List<MotivoTurnoDTO> motivosTurnoDTO = new ArrayList<MotivoTurnoDTO>();
		MotivoTurnoDTO motivoTurnoDTO = new MotivoTurnoDTO("0", "descripcion motivo");
		motivosTurnoDTO.add(motivoTurnoDTO);
		respuestaMotivosBO.setRespuesta(motivosTurnoDTO);
		respuestaMotivosBO.setEstadoRespuesta(EstadoRespuesta.OK);

		CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setClienteEstado("SA");
        credencialesMya.setEmail("isban@isbanexternos.com.ar");
        credencialesMya.setCelular("11111111");
        credencialesMya.setCodigoArea("11");
        credencialesMya.setCompaniaSeleccionada("Claro");
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        cliente.setDni("12345678");
        Segmento segmento = new Segmento();
        segmento.setPesucadm("000");
		cliente.setSegmento(segmento);
      
		when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
        when(turnosWebBO.consultaHorariosDisponibles(Matchers.any(HorariosDisponiblesInDTO.class))).thenReturn(respuestaBO); 
        when(sesionCliente.getCliente()).thenReturn(cliente);
		when(turnosWebBO.obtenerMotivosTurno(Matchers.any(MotivosInDTO.class))).thenReturn(respuestaMotivosBO);

        HorariosDisponiblesInView horariosDisponiblesInView = new HorariosDisponiblesInView();
        horariosDisponiblesInView.setNroSuc(null);
        horariosDisponiblesInView.setSector("P");        
        
        Respuesta<HorariosDisponiblesOutView> respuesta = turnosWebManager.consultaHorariosDisponibles(horariosDisponiblesInView);
		
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}
	
	@Test
	public void consultaHorariosDisponiblesErrorBO() {
		Respuesta<HorariosDisponiblesOutDTO> respuestaBO = new Respuesta<HorariosDisponiblesOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");

		CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setClienteEstado("SA");
      
		when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_HORARIOS_DISPONIBLES)).thenReturn(mensaje);	
		when(turnosWebBO.consultaHorariosDisponibles(Matchers.any(HorariosDisponiblesInDTO.class))).thenReturn(respuestaBO); 

        HorariosDisponiblesInView horariosDisponiblesInView = new HorariosDisponiblesInView();
        horariosDisponiblesInView.setNroSuc(null);
        horariosDisponiblesInView.setSector("C");
        
        Respuesta<HorariosDisponiblesOutView> respuesta = turnosWebManager.consultaHorariosDisponibles(horariosDisponiblesInView);
		
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	

	@Test
	public void bajaTurnoCajaOK() {
		Respuesta<Void> respuestaBO = new Respuesta<Void>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		BajaTurnoInView bajaTurnoInView = new BajaTurnoInView();
		bajaTurnoInView.setIdTurno((long) 1234);
		
		CitaOutDTO citaOutDTO  = new CitaOutDTO();
		List<CitaDTO> citaDTOs = new ArrayList<CitaDTO>();
		CitaDTO citaDTO = new CitaDTO();
		citaDTO.setFraccion("2000-2030");
		citaDTO.setFecha("04042018");
		citaDTO.setDireccion("Catamarca 2");
		citaDTO.setDia("Lunes");
		citaDTO.setDiaNumero(1);
		citaDTO.setMes("Febrero");
		citaDTO.setHorario("12:00hs");
		citaDTO.setSucursal("000");
		citaDTO.setSector("C");
		citaDTO.setAnio(2018);
		citaDTO.setIdTurno((long) 1234);
		citaDTO.setDescripcionSucursal("Casa central");
		citaDTOs.add(citaDTO);
		citaOutDTO.setCitaDTOList(citaDTOs);			

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_OK_BAJA_TURNO)).thenReturn(mensaje);			
		when(sesionParametros.getCitaOutDTO()).thenReturn(citaOutDTO);
        when(turnosWebBO.bajaTurno(Matchers.anyLong())).thenReturn(respuestaBO); 
        
        Respuesta<Void> respuesta = turnosWebManager.bajaTurno(bajaTurnoInView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void bajaTurnoError() {
		Respuesta<Void> respuestaBO = new Respuesta<Void>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
		BajaTurnoInView bajaTurnoInView = new BajaTurnoInView();
		bajaTurnoInView.setIdTurno((long) 1234);
		
		CitaOutDTO citaOutDTO  = new CitaOutDTO();
		List<CitaDTO> citaDTOs = new ArrayList<CitaDTO>();
		CitaDTO citaDTO = new CitaDTO();
		citaDTO.setFraccion("2000-2030");
		citaDTO.setFecha("04042018");
		citaDTO.setDireccion("Catamarca 2");
		citaDTO.setDia("Lunes");
		citaDTO.setDiaNumero(1);
		citaDTO.setMes("Febrero");
		citaDTO.setHorario("12:00hs");
		citaDTO.setSucursal("000");
		citaDTO.setSector("P");
		citaDTO.setAnio(2018);
		citaDTO.setIdTurno((long) 1234);
		citaDTO.setDescripcionSucursal("Casa central");
		citaDTOs.add(citaDTO);
		citaOutDTO.setCitaDTOList(citaDTOs);			

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_BAJA_TURNO)).thenReturn(mensaje);			
		when(sesionParametros.getCitaOutDTO()).thenReturn(citaOutDTO);
        when(turnosWebBO.bajaTurno(Matchers.anyLong())).thenReturn(respuestaBO); 
        
        Respuesta<Void> respuesta = turnosWebManager.bajaTurno(bajaTurnoInView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	
	@Test
	public void descargaComprobanteTurnoPDFOK() {
		Respuesta<Reporte> respuestaBO = new Respuesta<Reporte>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);	

		Respuesta<List<MotivoTurnoDTO>> respuestaMotivosBO = new Respuesta<List<MotivoTurnoDTO>>();
		List<MotivoTurnoDTO> motivosTurnoDTO = new ArrayList<MotivoTurnoDTO>();
		MotivoTurnoDTO motivoTurnoDTO = new MotivoTurnoDTO("0", "descripcion motivo");
		motivosTurnoDTO.add(motivoTurnoDTO);
		respuestaMotivosBO.setRespuesta(motivosTurnoDTO);
		respuestaMotivosBO.setEstadoRespuesta(EstadoRespuesta.OK);

        Reporte repo = new Reporte();
        repo.setNombre("nombre");
        repo.setTipoArchivo(TipoArchivoEnum.PDF);
        respuestaBO.setRespuesta(repo);
		DatosMYAView datosMYA = new DatosMYAView();
		datosMYA.setAreaCelular("11");
		datosMYA.setEmail("prueba@mail.com");
		datosMYA.setEmpresaCelular("Claro");
		datosMYA.setNumeroCelular("12345678");
		ComprobanteTurnoInView comprobanteTurnoInView = new ComprobanteTurnoInView();
		comprobanteTurnoInView.setDatosMYA(datosMYA);		
		comprobanteTurnoInView.setMotivoId("1");
		comprobanteTurnoInView.setSector("P");
		comprobanteTurnoInView.setSucursal("000");

		Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        cliente.setDni("12345678");
        Segmento segmento = new Segmento();
        segmento.setPesucadm("000");
		cliente.setSegmento(segmento);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(turnosWebBO.generarComprobanteTurnoWeb(Matchers.any(ComprobanteTurnoInDTO.class))).thenReturn(respuestaBO); 
        when(turnosWebBO.obtenerMotivosTurno(Matchers.any(MotivosInDTO.class))).thenReturn(respuestaMotivosBO);
        Respuesta<ReporteView> respuesta = turnosWebManager.descargaComprobanteTurnoPDF(comprobanteTurnoInView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}
	
	

	@Test
	public void altaCitaCajaOK() {		
		Respuesta<AltaModificacionOutDTO> respuestaBO = new Respuesta<AltaModificacionOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		AltaModificacionOutDTO altaModificacionOutDTO = new AltaModificacionOutDTO();
		altaModificacionOutDTO.setIdTurno((long) 1234);
		altaModificacionOutDTO.setMensajeFeedback("Turno OK");
		respuestaBO.setRespuesta(altaModificacionOutDTO);
		
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        
        AltaModificacionCitaInView altaModificacionCitaInView = new AltaModificacionCitaInView();
        altaModificacionCitaInView.setAccion("A");
        altaModificacionCitaInView.setDescripcionFecha("Lunes 28, de septiembre de 2018");
        altaModificacionCitaInView.setDescripcionSucursal("Casa central");
        altaModificacionCitaInView.setFecha("20180404");
        altaModificacionCitaInView.setFraccion("1900-1930");
        altaModificacionCitaInView.setHoraInicio("1900");
        altaModificacionCitaInView.setIdTurno((long) 0);
        altaModificacionCitaInView.setNroSuc("000");
        altaModificacionCitaInView.setSector("C");
        
        DatosMYAView datosMya = new DatosMYAView();
        datosMya.setAreaCelular("011");
        datosMya.setNumeroCelular("1122334422");
        datosMya.setEmail("pepeTarjota@gmail.com");
        datosMya.setEmpresaCelular("Claro");
        altaModificacionCitaInView.setDatosMYA(datosMya);
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(turnosWebBO.altaModificacionCita(Matchers.any(AltaModificacionInDTO.class))).thenReturn(respuestaBO); 

        Respuesta<AltaModificacionCitaOutView> respuesta = turnosWebManager.altaModificacionCita(altaModificacionCitaInView);
		
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void altaCitaPlafaformaOK() {		
		Respuesta<AltaModificacionOutDTO> respuestaBO = new Respuesta<AltaModificacionOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		AltaModificacionOutDTO altaModificacionOutDTO = new AltaModificacionOutDTO();
		altaModificacionOutDTO.setIdTurno((long) 1234);
		altaModificacionOutDTO.setMensajeFeedback("Turno OK");
		respuestaBO.setRespuesta(altaModificacionOutDTO);
		
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        
        AltaModificacionCitaInView altaModificacionCitaInView = new AltaModificacionCitaInView();
        altaModificacionCitaInView.setAccion("A");
        altaModificacionCitaInView.setDescripcionFecha("Lunes 28, de septiembre de 2018");
        altaModificacionCitaInView.setDescripcionSucursal("Casa central");
        altaModificacionCitaInView.setFecha("20180404");
        altaModificacionCitaInView.setFraccion("1900-1930");
        altaModificacionCitaInView.setHoraInicio("1900");
        altaModificacionCitaInView.setIdTurno((long) 0);
        altaModificacionCitaInView.setNroSuc("000");
        altaModificacionCitaInView.setSector("P");
        
        DatosMYAView datosMya = new DatosMYAView();
        datosMya.setAreaCelular("011");
        datosMya.setNumeroCelular("1122334422");
        datosMya.setEmail("pepeTarjota@gmail.com");
        datosMya.setEmpresaCelular("Claro");
        altaModificacionCitaInView.setDatosMYA(datosMya);
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(turnosWebBO.altaModificacionCita(Matchers.any(AltaModificacionInDTO.class))).thenReturn(respuestaBO); 

        Respuesta<AltaModificacionCitaOutView> respuesta = turnosWebManager.altaModificacionCita(altaModificacionCitaInView);
		
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void modificacionCitaCajaOK() {		
		Respuesta<AltaModificacionOutDTO> respuestaBO = new Respuesta<AltaModificacionOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		AltaModificacionOutDTO altaModificacionOutDTO = new AltaModificacionOutDTO();
		altaModificacionOutDTO.setIdTurno((long) 1234);
		altaModificacionOutDTO.setMensajeFeedback("Turno OK");
		respuestaBO.setRespuesta(altaModificacionOutDTO);
		
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        
        AltaModificacionCitaInView altaModificacionCitaInView = new AltaModificacionCitaInView();
        altaModificacionCitaInView.setAccion("M");
        altaModificacionCitaInView.setDescripcionFecha("Lunes 28, de septiembre de 2018");
        altaModificacionCitaInView.setDescripcionSucursal("Casa central");
        altaModificacionCitaInView.setFecha("20180404");
        altaModificacionCitaInView.setFraccion("1900-1930");
        altaModificacionCitaInView.setHoraInicio("1900");
        altaModificacionCitaInView.setIdTurno((long) 1234);
        altaModificacionCitaInView.setNroSuc("000");
        altaModificacionCitaInView.setSector("C");
        
        DatosMYAView datosMya = new DatosMYAView();
        datosMya.setAreaCelular("011");
        datosMya.setNumeroCelular("1122334422");
        datosMya.setEmail("pepeTarjota@gmail.com");
        datosMya.setEmpresaCelular("Claro");
        altaModificacionCitaInView.setDatosMYA(datosMya);
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(turnosWebBO.altaModificacionCita(Matchers.any(AltaModificacionInDTO.class))).thenReturn(respuestaBO); 

        Respuesta<AltaModificacionCitaOutView> respuesta = turnosWebManager.altaModificacionCita(altaModificacionCitaInView);
		
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}
	
	@Test
	public void modificacionCitaPlataformaOK() {		
		Respuesta<AltaModificacionOutDTO> respuestaBO = new Respuesta<AltaModificacionOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		AltaModificacionOutDTO altaModificacionOutDTO = new AltaModificacionOutDTO();
		altaModificacionOutDTO.setIdTurno((long) 1234);
		altaModificacionOutDTO.setMensajeFeedback("Turno OK");
		respuestaBO.setRespuesta(altaModificacionOutDTO);
		
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        
        AltaModificacionCitaInView altaModificacionCitaInView = new AltaModificacionCitaInView();
        altaModificacionCitaInView.setAccion("M");
        altaModificacionCitaInView.setDescripcionFecha("Lunes 28, de septiembre de 2018");
        altaModificacionCitaInView.setDescripcionSucursal("Casa central");
        altaModificacionCitaInView.setFecha("20180404");
        altaModificacionCitaInView.setFraccion("1900-1930");
        altaModificacionCitaInView.setHoraInicio("1900");
        altaModificacionCitaInView.setIdTurno((long) 1234);
        altaModificacionCitaInView.setNroSuc("000");
        altaModificacionCitaInView.setSector("P");
        
        DatosMYAView datosMya = new DatosMYAView();
        datosMya.setAreaCelular("011");
        datosMya.setNumeroCelular("1122334422");
        datosMya.setEmail("pepeTarjota@gmail.com");
        datosMya.setEmpresaCelular("Claro");
        altaModificacionCitaInView.setDatosMYA(datosMya);
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(turnosWebBO.altaModificacionCita(Matchers.any(AltaModificacionInDTO.class))).thenReturn(respuestaBO); 

        Respuesta<AltaModificacionCitaOutView> respuesta = turnosWebManager.altaModificacionCita(altaModificacionCitaInView);
		
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}
	
	@Test
	public void altaModificacionCitaTimeOutError() {		
		Respuesta<AltaModificacionOutDTO> respuestaBO = new Respuesta<AltaModificacionOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setTipoError(TipoError.TIMEOUT.getDescripcion());
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuestas);

		AltaModificacionOutDTO altaModificacionOutDTO = new AltaModificacionOutDTO();
		altaModificacionOutDTO.setIdTurno(null);
		altaModificacionOutDTO.setMensajeFeedback("Error time out");
		respuestaBO.setRespuesta(altaModificacionOutDTO);
		
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        
        AltaModificacionCitaInView altaModificacionCitaInView = new AltaModificacionCitaInView();
        altaModificacionCitaInView.setAccion("M");
        altaModificacionCitaInView.setDescripcionFecha("Lunes 28, de septiembre de 2018");
        altaModificacionCitaInView.setDescripcionSucursal("Casa central");
        altaModificacionCitaInView.setFecha("20180404");
        altaModificacionCitaInView.setFraccion("1900-1930");
        altaModificacionCitaInView.setHoraInicio("1900");
        altaModificacionCitaInView.setIdTurno((long) 1234);
        altaModificacionCitaInView.setNroSuc("000");
        altaModificacionCitaInView.setSector("P");
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(turnosWebBO.altaModificacionCita(Matchers.any(AltaModificacionInDTO.class))).thenReturn(respuestaBO); 

        Respuesta<AltaModificacionCitaOutView> respuesta = turnosWebManager.altaModificacionCita(altaModificacionCitaInView);
		
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
	@Test
	public void altaModificacionCitaGenericoError() {		
		Respuesta<AltaModificacionOutDTO> respuestaBO = new Respuesta<AltaModificacionOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		itemMensajeRespuestas.add(itemMensajeRespuesta);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuestas);

		AltaModificacionOutDTO altaModificacionOutDTO = new AltaModificacionOutDTO();
		altaModificacionOutDTO.setIdTurno(null);
		altaModificacionOutDTO.setMensajeFeedback("Error time out");
		respuestaBO.setRespuesta(altaModificacionOutDTO);
		
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setApellido1("Prueba");
        cliente.setApellido2("Prueba 2");
        cliente.setNup("12345678");
        
        AltaModificacionCitaInView altaModificacionCitaInView = new AltaModificacionCitaInView();
        altaModificacionCitaInView.setAccion("M");
        altaModificacionCitaInView.setDescripcionFecha("Lunes 28, de septiembre de 2018");
        altaModificacionCitaInView.setDescripcionSucursal("Casa central");
        altaModificacionCitaInView.setFecha("20180404");
        altaModificacionCitaInView.setFraccion("1900-1930");
        altaModificacionCitaInView.setHoraInicio("1900");
        altaModificacionCitaInView.setIdTurno((long) 1234);
        altaModificacionCitaInView.setNroSuc("000");
        altaModificacionCitaInView.setSector("P");
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(turnosWebBO.altaModificacionCita(Matchers.any(AltaModificacionInDTO.class))).thenReturn(respuestaBO); 

        Respuesta<AltaModificacionCitaOutView> respuesta = turnosWebManager.altaModificacionCita(altaModificacionCitaInView);
		
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
}
