package ar.com.santanderrio.obp.servicios.prestamos.manager;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.config.SegurosUrls;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.mock.OfertaComercialDTOMock;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoManagerBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CalificacionCrediticiaDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CalificacionCrediticiaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.sei.RangoCuota;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DestinoPrestamoSeleccionView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportSimulacionPrestamosView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ResultadoSimulacionView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudSimulacionView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.SimuladorPrestamoManagerImpl;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dto.ConfiguracionPrestamoDTO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.ConfiguracionPrestamoView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * The Class SimuladorPrestamoManagerTest.
 *
 * @author mariano.g.pulera
 *
 */


@RunWith(MockitoJUnitRunner.class)
public class SimuladorPrestamoManagerTest {

	@InjectMocks
	private SimuladorPrestamoManagerImpl simuladorPrestamoManager;
	
	@Mock
	private SesionCliente sesionCliente;
    
    @Mock
    private SesionParametros sesionParametros;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private SimuladorPrestamoBO simuladorPrestamoBo;
    @Mock
    private SegurosUrls segurosUrl;
    
    @Mock
    private MensajeBO mensajeBo;
    
    @Mock
    private DestinoPrestamoBO destinoPrestamoBo;
    
    @Mock
    private EstadisticaBO estadisticaBo;
    
    @Mock
    private LegalBO legalBo;
    
    @Mock
    private ContadorIntentos contadorIntentos;

    @Mock
    private PrestamoManagerBO prestamoTokenBO;

    @Spy 
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Before
	public void init() throws BusinessException {

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("");
		when(mensajeBo.obtenerMensajePorCodigo(anyString())).thenReturn(mensaje);
		when(prestamoTokenBO.isValidToken(anyString())).thenReturn(true);

	}

    @Test
    public void grabarEstadisticaInicioSimuladorPrestamoDesdeActionSheet() {
    	
    	//When
    	PuntoDeAccesoView puntoDeAcceso = new PuntoDeAccesoView();
    	puntoDeAcceso.setPuntoDeAcceso("0");
    	
    	//Then
    	Respuesta<Void> respuesta = simuladorPrestamoManager.grabarEstadisticaInicioSimuladorPrestamo(puntoDeAcceso);
    	
    	//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
    	
    }
    
    
    @Test
    public void grabarEstadisticaInicioSimuladorPrestamoDesdeSolapaPrestamos() {
    	
    	//When
    	PuntoDeAccesoView puntoDeAcceso = new PuntoDeAccesoView();
    	puntoDeAcceso.setPuntoDeAcceso("1");
    	
    	//Then
    	Respuesta<Void> respuesta = simuladorPrestamoManager.grabarEstadisticaInicioSimuladorPrestamo(puntoDeAcceso);
    	
    	//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
    	
    }
    
    
    @Test
    public void grabarEstadisticaInicioSimuladorPrestamoOtroPuntoAcceso() {
    	
    	//When
    	PuntoDeAccesoView puntoDeAcceso = new PuntoDeAccesoView();
    	puntoDeAcceso.setPuntoDeAcceso("4");
    	
    	//Then
    	Respuesta<Void> respuesta = simuladorPrestamoManager.grabarEstadisticaInicioSimuladorPrestamo(puntoDeAcceso);
    	
    	//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
    	
    }
    
    
    @Test
    public void obtenerDatosSimulacionOk() throws BusinessException {
    	
    	//When
    	Cliente cliente = mock(Cliente.class);
    	
		Cuenta cuentaUno = crearCuenta();
		Cuenta cuentaDos = crearCuenta();
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuentaUno);
		cuentas.add(cuentaDos);
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());

		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		
		List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuentaUno);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
		
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentasUnicasPesos()).thenReturn(cuentas);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo
				(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		
    	//Then
    	Respuesta<List<ConfiguracionPrestamoView>> respuesta = simuladorPrestamoManager.obtenerDatosSimulacion();
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    	
    }
    
    
    @Test
    public void obtenerDatosSimulacionErrorUsuarioInhabilitado() throws BusinessException {
    	
    	//When
    	Cliente cliente = mock(Cliente.class);
    	
		Cuenta cuentaUno = crearCuenta();
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuentaUno);
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		configPrestDto.setMensajeInhabilitado("Usuario Inhabilitado");
		
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuentaUno);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
		
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentasUnicasPesos()).thenReturn(cuentas);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo
				(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		
    	//Then
    	Respuesta<List<ConfiguracionPrestamoView>> respuesta = simuladorPrestamoManager.obtenerDatosSimulacion();
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	
    }
    
    
    @Test
    public void obtenerDatosSimulacionOkUsuarioConUnaCuenta() throws BusinessException {
    	
    	//When
    	Cliente cliente = mock(Cliente.class);
    	
		Cuenta cuentaUno = crearCuenta();
				
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuentaUno);
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		
		List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuentaUno);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
		
		
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentasUnicasPesos()).thenReturn(cuentas);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo
				(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		
    	//Then
    	Respuesta<List<ConfiguracionPrestamoView>> respuesta = simuladorPrestamoManager.obtenerDatosSimulacion();
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    	
    }
    
    
    @Test
    public void obtenerDatosSimulacionOkConCuentaPrincipal() throws BusinessException {
    	
    	//poner en sesion calificaciones creditiias

    	//When
    	Cliente cliente = mock(Cliente.class);
    	
		Cuenta cuentaUno = crearCuenta();
		cuentaUno.setJerarquiaCuenta("P");
		Cuenta cuentaDos = crearCuenta();
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(cuentaUno);
		cuentas.add(cuentaDos);
		
		
    	List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuentaUno);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
    	
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());

		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentasUnicasPesos()).thenReturn(cuentas);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo
				(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		
		
		
    	//Then
    	Respuesta<List<ConfiguracionPrestamoView>> respuesta = simuladorPrestamoManager.obtenerDatosSimulacion();
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    	
    }
    
    
	@Test
    public void obtenerDatosSimulacionErrorPrestamoInhabilitado() throws BusinessException {
    	
    	//When
    	Cliente cliente = mock(Cliente.class);
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuentaUno = crearCuenta();
		Cuenta cuentaDos = crearCuenta();
		
		cuentas.add(cuentaUno);
		cuentas.add(cuentaDos);
		
    	Mensaje mensajeError = new Mensaje();
    	mensajeError.setMensaje("ERROR PRESTAMO INHABILITADO");
    	
    	BusinessException businessException = new BusinessException("1509");
    	
		when(mensajeBo.obtenerMensajePorCodigo(CodigoMensajeConstantes.PRESTAMO_INHABILITADO)).thenReturn(mensajeError);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentasUnicasPesos()).thenReturn(cuentas);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo
				(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenThrow(businessException);
		
    	//Then
    	Respuesta<List<ConfiguracionPrestamoView>> respuesta = simuladorPrestamoManager.obtenerDatosSimulacion();
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	
    }
    
    
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerDatosSimulacionErrorGenerico() throws BusinessException {
    	
    	//When
    	Cliente cliente = mock(Cliente.class);
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuentaUno = crearCuenta();
		Cuenta cuentaDos = crearCuenta();
		
		cuentas.add(cuentaUno);
		cuentas.add(cuentaDos);
		
    	Mensaje mensajeError = new Mensaje();
    	mensajeError.setMensaje("ERROR GENERICO");
    	
    	List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuentaUno);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
    	
		when(mensajeBo.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_GENERICO_SIMULADOR)).thenReturn(mensajeError);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentasUnicasPesos()).thenReturn(cuentas);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo
				(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenThrow(BusinessException.class);
		
    	//Then
    	Respuesta<List<ConfiguracionPrestamoView>> respuesta = simuladorPrestamoManager.obtenerDatosSimulacion();
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	
    }
    
    
    @Test
    public void obtenerDatosSimulacionErrorFueraDeHorario() throws BusinessException {
    	
    	//When	
    	Mensaje mensajeError = new Mensaje();
    	mensajeError.setMensaje("ERROR FUERA DE HORARIO");
    	
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(false);
		when(mensajeBo.obtenerMensajePorCodigo
				(CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_SIMULADOR_PRESTAMOS)).thenReturn(mensajeError);
		
    	//Then
    	Respuesta<List<ConfiguracionPrestamoView>> respuesta = simuladorPrestamoManager.obtenerDatosSimulacion();
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	
    }
    
    
    @Test
    public void simularOk() throws BusinessException {
    	
    	//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
		
		PrestamoOutEntity simuladorPrestamoOutEntity = crearPrestamoOutEntity();

		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		
		Estadistica estadistica = new Estadistica();
		
		
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenReturn(simuladorPrestamoOutEntity);
		when(destinoPrestamoBo.buscarPorId(Matchers.anyString())).thenReturn(destino);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenReturn(estadistica);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);
		when(sesionCliente.getCliente().getSegmento()).thenReturn(segmento);
		
    	//Then
    	Respuesta<ResultadoSimulacionView> respuesta = simuladorPrestamoManager.simular(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
    }
    

    @Test
    public void simularErrorValidacionBean() throws BusinessException {
    	
    	//When
        Cliente cliente = mock(Cliente.class);

    	SolicitudSimulacionView solicitud = new SolicitudSimulacionView();
    	
    	solicitud.setCbu("0720000788000006391704");
    	solicitud.setCuotaSeleccionada("3");
    	solicitud.setFechaSeleccionada("FechaSeleccionada");
    	solicitud.setImporteSeleccionado("Importe");

    	DestinoPrestamoSeleccionView destinoView = new DestinoPrestamoSeleccionView();
    	destinoView.setDescripcion("Viajes y turismo");
    	destinoView.setId("350023ARS35001");
    	
    	solicitud.setMotivoSeleccionado(destinoView);
    			
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
			
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

		//Then
    	Respuesta<ResultadoSimulacionView> respuesta = simuladorPrestamoManager.simular(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());	
    }
    
    
    @Test
    public void simularErrorFueraDeHorario() throws BusinessException {
    	
    	//When
        Cliente cliente = mock(Cliente.class);

    	SolicitudSimulacionView solicitud = new SolicitudSimulacionView();
    			
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("FUERA DE HORARIO");
				
		when(mensajeBo.obtenerMensajePorCodigo("1400")).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(false);

		//Then
    	Respuesta<ResultadoSimulacionView> respuesta = simuladorPrestamoManager.simular(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());	

    }
    

    @Test
    public void simularWarning() throws BusinessException {
    	
    	//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta error = new ItemMensajeRespuesta("Error con el importe");
		error.setTipoError("ERROR_IMPORTE_FORMULARIO");
		errores.add(error);
		
		PrestamoOutEntity simuladorPrestamoOutEntity = crearPrestamoOutEntity();

		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		
		Estadistica estadistica = new Estadistica();
		
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenReturn(simuladorPrestamoOutEntity);
		when(destinoPrestamoBo.buscarPorId(Matchers.anyString())).thenReturn(destino);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenReturn(estadistica);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

    	//Then
    	Respuesta<ResultadoSimulacionView> respuesta = simuladorPrestamoManager.simular(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
    

    @SuppressWarnings("unchecked")
	@Test
    public void simularArrojaBusinessExcepcionValidacion() throws BusinessException {
    	
    	//When
        Cliente cliente = mock(Cliente.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
				
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenThrow(BusinessException.class);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

    	//Then
    	Respuesta<ResultadoSimulacionView> respuesta = simuladorPrestamoManager.simular(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    

    @Test
    public void simularWarningErrorRangoFecha() throws BusinessException {
    	
    	//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
		
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
				
		BusinessException businessException = new BusinessException("1483");
		
		Mensaje mensajeErrorFecha = new Mensaje();
		mensajeErrorFecha.setMensaje("MENSAJE ERROR DE RANGO DE FECHA");
		
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(mensajeBo.obtenerMensajePorCodigo("1483")).thenReturn(mensajeErrorFecha);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenThrow(businessException);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);
		when(sesionCliente.getCliente().getSegmento()).thenReturn(segmento);
		
    	//Then
    	Respuesta<ResultadoSimulacionView> respuesta = simuladorPrestamoManager.simular(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
    

    @Test
    public void simularWarningErrorCantidadCuotas() throws BusinessException {
    	
    	//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
		
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
				
		BusinessException businessException = new BusinessException("1525");
		
		Mensaje mensajeErrorCuotas = new Mensaje();
		mensajeErrorCuotas.setMensaje("MENSAJE ERROR CANTIDAD DE CUOTAS");
		
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(mensajeBo.obtenerMensajePorCodigo("1525")).thenReturn(mensajeErrorCuotas);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenThrow(businessException);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);
		when(sesionCliente.getCliente().getSegmento()).thenReturn(segmento);
		
		//Then
    	Respuesta<ResultadoSimulacionView> respuesta = simuladorPrestamoManager.simular(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
    
    
    @Test
    public void simularWarningErrorDiaNoHabilFecha() throws BusinessException {
    	
    	//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
		
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
				
		BusinessException businessException = new BusinessException("1350");
		
		Mensaje mensajeErrorFecha = new Mensaje();
		mensajeErrorFecha.setMensaje("MENSAJE ERROR DE DIA NO HABIL DE FECHA");
		
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(mensajeBo.obtenerMensajePorCodigo("1350")).thenReturn(mensajeErrorFecha);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenThrow(businessException);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);
		when(sesionCliente.getCliente().getSegmento()).thenReturn(segmento);
		
		//Then
    	Respuesta<ResultadoSimulacionView> respuesta = simuladorPrestamoManager.simular(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
	

    @SuppressWarnings("unchecked")
	@Test
    public void simularErrorMetodoObtenerSimulacion() throws BusinessException {
    	
    	//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
		
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
				
		Estadistica estadistica = new Estadistica();
		
    	List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO calificacion = new CalificacionCrediticiaDTO();
    	calificacion.setCuenta(cuenta);
    	calificacion.setLineaActualDisponible(BigDecimal.valueOf(50000.0));
    	calificacion.setLineaTotalCrediticia(BigDecimal.valueOf(50000.0));
    	calificaciones.add(calificacion);
    	when(sesionParametros.getCalificacionesCrediticiasDTO()).thenReturn(calificaciones);
		
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
    	when(mensajeBo.obtenerMensajePorCodigo("1347")).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		
		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);
		when(sesionCliente.getCliente().getSegmento()).thenReturn(segmento);

		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenThrow(BusinessException.class);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenReturn(estadistica);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

    	//Then
    	Respuesta<ResultadoSimulacionView> respuesta = simuladorPrestamoManager.simular(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    
    @SuppressWarnings("unchecked")
	@Test
    public void simularErrorMetodoObtenerSimulacionErrorGrabadoEstadistica() throws BusinessException {
    	
    	//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
		
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
						
		when(mensajeBo.obtenerMensajePorCodigo("1347")).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenThrow(BusinessException.class);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenThrow(BusinessException.class);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);
		when(sesionCliente.getCliente().getSegmento()).thenReturn(segmento);
		
		//Then
    	Respuesta<ResultadoSimulacionView> respuesta = simuladorPrestamoManager.simular(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    
	@Test
	public void adquirirPrestamoOk() throws BusinessException {

		//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
		
		PrestamoOutEntity simuladorPrestamoOutEntity = crearPrestamoOutEntity();

		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		
		Estadistica estadistica = new Estadistica();
		
		Mensaje mensajeOk = new Mensaje();
		mensajeOk.setMensaje("MENSAJE PRESTAMO APROBADO");
		
		Respuesta<String> legalSeuo = new Respuesta<String>();
		legalSeuo.setRespuesta("LEGALES SEUO");
		legalSeuo.setEstadoRespuesta(EstadoRespuesta.OK);
		
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(mensajeBo.obtenerMensajePorCodigo("1355")).thenReturn(mensajeOk);
		when(sesionParametros.getReportSimulacion()).thenReturn(new ReportSimulacionPrestamosView());
		when(legalBo.buscarLegal("1005")).thenReturn(legalSeuo);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		Segmento segmento = new Segmento();
		segmento.setDuo(true);
		segmento.setPyme(false);
		when(sesionCliente.getCliente().getSegmento()).thenReturn(segmento);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenReturn(simuladorPrestamoOutEntity);
		when(destinoPrestamoBo.buscarPorId(Matchers.anyString())).thenReturn(destino);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenReturn(estadistica);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

        List<String> data = Arrays.asList("vida", "casa");
	    when(segurosUrl.getUrls(Matchers.anyString())).thenReturn(data);
	    when(segurosUrl.getOfertaDefault()).thenReturn("ip");
	    
        EventosComercialesDTO eventosComercialesDTO = new EventosComercialesDTO();
        List<OfertaComercialDTO> ofertas = new ArrayList<OfertaComercialDTO>();
        ofertas.add(OfertaComercialDTOMock.completarPrestamosCarrusel());
        eventosComercialesDTO.setOfertas(ofertas);
        when(sesionParametros.getOfertasComerciales()).thenReturn(eventosComercialesDTO);
		//Then
    	Respuesta<ComprobanteFeedbackView> respuesta = simuladorPrestamoManager.adquirirPrestamo(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
		Assert.assertEquals("Esta es una prueba para ver como se ve el valor 51.416", respuesta.getRespuesta().getContenido().getDescripcion());	
    
	}
	
	
	@Test
	public void adquirirPrestamoErrorValidacionBean() throws BusinessException {

		//When
        Cliente cliente = mock(Cliente.class);

    	SolicitudSimulacionView solicitud = new SolicitudSimulacionView();
    	solicitud.setCbu("0720000788000006391704");
    	solicitud.setCuotaSeleccionada("3");
    	solicitud.setFechaSeleccionada("FECHA");
    	solicitud.setImporteSeleccionado("IMPORTE");

    	DestinoPrestamoSeleccionView destinoView = new DestinoPrestamoSeleccionView();
    	destinoView.setDescripcion("Viajes y turismo");
    	destinoView.setId("350023ARS35001");
    	
    	solicitud.setMotivoSeleccionado(destinoView);
    		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
				
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

    	//Then
    	Respuesta<ComprobanteFeedbackView> respuesta = simuladorPrestamoManager.adquirirPrestamo(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());	
    
	}
	
	
	@Test
	public void adquirirPrestamoErrorFueraDeHorario() throws BusinessException {

		//When
        Cliente cliente = mock(Cliente.class);

    	SolicitudSimulacionView solicitud = new SolicitudSimulacionView();
    		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("FUERA DE HORARIO");
				
		when(mensajeBo.obtenerMensajePorCodigo("1400")).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(false);

    	//Then
    	Respuesta<ComprobanteFeedbackView> respuesta = simuladorPrestamoManager.adquirirPrestamo(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
	}

	
	@Test
	public void adquirirPrestamoOkConMensajeFeedbackVacio() throws BusinessException {

		//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
		
		PrestamoOutEntity simuladorPrestamoOutEntity = crearPrestamoOutEntity();
		
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		
		Estadistica estadistica = new Estadistica();
				
		Respuesta<String> legalSeuo = new Respuesta<String>();
		legalSeuo.setRespuesta("LEGALES SEUO");
		
		EventosComercialesDTO ofertasComerciales = new EventosComercialesDTO();
		ofertasComerciales.setOfertas(new ArrayList<OfertaComercialDTO>());
		
		Mensaje mensajeVacio = new Mensaje();
		when(sesionParametros.getReportSimulacion()).thenReturn(new ReportSimulacionPrestamosView());
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(mensajeBo.obtenerMensajePorCodigo("1355")).thenReturn(mensajeVacio);
		when(legalBo.buscarLegal("1005")).thenReturn(legalSeuo);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenReturn(simuladorPrestamoOutEntity);
		when(destinoPrestamoBo.buscarPorId(Matchers.anyString())).thenReturn(destino);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenReturn(estadistica);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
		when(sesionParametros.getOfertasComerciales()).thenReturn(ofertasComerciales);
		Segmento segmento = new Segmento();
		segmento.setDuo(true);
		segmento.setPyme(false);
		when(sesionCliente.getCliente().getSegmento()).thenReturn(segmento);


    	//Then
    	Respuesta<ComprobanteFeedbackView> respuesta = simuladorPrestamoManager.adquirirPrestamo(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
    
	}
	
	
	@Test
	public void adquirirPrestamoOkConLegalesSeuoVacio() throws BusinessException {

		//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
		
		PrestamoOutEntity simuladorPrestamoOutEntity = crearPrestamoOutEntity();
		
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		
		Estadistica estadistica = new Estadistica();
				
		Respuesta<String> legalSeuo = new Respuesta<String>();
		legalSeuo.setRespuestaVacia(true);
		
		EventosComercialesDTO ofertasComerciales = new EventosComercialesDTO();
        ofertasComerciales.setOfertas(new ArrayList<OfertaComercialDTO>());
		
		Mensaje mensajeVacio = new Mensaje();
		when(sesionParametros.getReportSimulacion()).thenReturn(new ReportSimulacionPrestamosView());
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(mensajeBo.obtenerMensajePorCodigo("1355")).thenReturn(mensajeVacio);
		when(legalBo.buscarLegal("1005")).thenReturn(legalSeuo);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		Segmento segmento = new Segmento();
		segmento.setDuo(true);
		segmento.setPyme(false);
		when(sesionCliente.getCliente().getSegmento()).thenReturn(segmento);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenReturn(simuladorPrestamoOutEntity);
		when(destinoPrestamoBo.buscarPorId(Matchers.anyString())).thenReturn(destino);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenReturn(estadistica);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
		when(sesionParametros.getOfertasComerciales()).thenReturn(ofertasComerciales);

    	//Then
    	Respuesta<ComprobanteFeedbackView> respuesta = simuladorPrestamoManager.adquirirPrestamo(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
    
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void adquirirPrestamoBusinessException() throws BusinessException {

		//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
				
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		
		Estadistica estadistica = new Estadistica();
		
		Mensaje mensajeOk = new Mensaje();
		mensajeOk.setMensaje("MENSAJE ERROR APROBACION PRESTAMO");
		
        ContadorIntentos contador = new ContadorIntentos();

        when(mensajeBo.obtenerMensajePorCodigo("1344")).thenReturn(mensaje);
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(mensajeBo.obtenerMensajePorCodigo("1356")).thenReturn(mensajeOk);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionParametros.getContador()).thenReturn(contador);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenThrow(BusinessException.class);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenReturn(estadistica);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

    	//Then
    	Respuesta<ComprobanteFeedbackView> respuesta = simuladorPrestamoManager.adquirirPrestamo(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());	
    
	}
	
	
	@Test
	public void adquirirPrestamoTimeOutException() throws BusinessException {

		//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);

    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MENSAJE ERROR GENERICO");
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
				
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
		
		Estadistica estadistica = new Estadistica();
		
		Mensaje mensajeOk = new Mensaje();
		mensajeOk.setMensaje("MENSAJE ERROR TIMEOUT EXCEPTION");
		
        ContadorIntentos contador = new ContadorIntentos();

        BusinessException businessException = new BusinessException("1344");
        
		when(mensajeBo.obtenerMensajePorCodigo("1129")).thenReturn(mensaje);
		when(mensajeBo.obtenerMensajePorCodigo("1344")).thenReturn(mensajeOk);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionParametros.getContador()).thenReturn(contador);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenThrow(businessException);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenReturn(estadistica);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

    	//Then
    	Respuesta<ComprobanteFeedbackView> respuesta = simuladorPrestamoManager.adquirirPrestamo(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());	
    
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void adquirirPrestamoBusinessExceptionAlGrabarEstadisticaError() throws BusinessException {

		//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);
        
    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
				
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
				
		Mensaje mensajeError = new Mensaje();
		mensajeError.setMensaje("MENSAJE ERROR APROBACION PRESTAMO");
		
        ContadorIntentos contador = new ContadorIntentos();
        
        when(mensajeBo.obtenerMensajePorCodigo("1344")).thenReturn(mensajeError);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionParametros.getContador()).thenReturn(contador);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenThrow(BusinessException.class);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenThrow(BusinessException.class);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

    	//Then
    	Respuesta<ComprobanteFeedbackView> respuesta = simuladorPrestamoManager.adquirirPrestamo(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());	
    
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void adquirirPrestamoBusinessExceptionNoPermiteReintentos() throws BusinessException {

		//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);
        ContadorIntentos contador = mock(ContadorIntentos.class);
        
    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	solicitud.setReintentar("true");
    	Cuenta cuenta = crearCuenta();
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
				
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
					
		Mensaje mensajeError = new Mensaje();
		mensajeError.setMensaje("MENSAJE ERROR APROBACION PRESTAMO");
	        
		when(mensajeBo.obtenerMensajePorCodigo("1344")).thenReturn(mensajeError);
		when(mensajeBo.obtenerMensajePorCodigo("1356")).thenReturn(mensajeError);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionParametros.getContador()).thenReturn(contador);
		when(contadorIntentos.permiteReintentar()).thenReturn(false);		
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenThrow(BusinessException.class);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenThrow(BusinessException.class);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);

    	//Then
    	Respuesta<ComprobanteFeedbackView> respuesta = simuladorPrestamoManager.adquirirPrestamo(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		Assert.assertEquals("ERROR_REINTENTOS_AGOTADOS", respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void adquirirPrestamoBusinessExceptionAlGrabarEstadisticaOK() throws BusinessException {

		//When
        Cliente cliente = mock(Cliente.class);
        RegistroSesion registroSesion = mock(RegistroSesion.class);
        
    	SolicitudSimulacionView solicitud = crearSolicitudSimulacionViewCorrecto();
    	Cuenta cuenta = crearCuenta();
		
		CalificacionCrediticiaOutEntity calificacionCrediticia = new CalificacionCrediticiaOutEntity();
		PrestamoPermitidoOutEntity prestamoPermitido = new PrestamoPermitidoOutEntity();
		
		PrestamoPermitidoEntity infoPrestamo = crearPrestamoPermitidoEntity();

		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoPermitido.setListaResult(listaInfoPrestamos);
		
		ConfiguracionPrestamoDTO configPrestDto = new ConfiguracionPrestamoDTO(calificacionCrediticia, prestamoPermitido, crearListaDeCuotas());
		
		List <ItemMensajeRespuesta> errores = new ArrayList<ItemMensajeRespuesta>();
				
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setDescripcionUG("Viajes y turismo");
				
		Mensaje mensajeError = new Mensaje();
		mensajeError.setMensaje("MENSAJE ERROR APROBACION PRESTAMO");
		
        ContadorIntentos contador = new ContadorIntentos();
        
        PrestamoOutEntity simuladorPrestamoOutEntity = crearPrestamoOutEntity();
        EventosComercialesDTO ofertasComerciales = new EventosComercialesDTO();
        ofertasComerciales.setOfertas(new ArrayList<OfertaComercialDTO>());
        
		Respuesta<String> legalSeuo = new Respuesta<String>();
		legalSeuo.setRespuesta("LEGALES SEUO");
		legalSeuo.setEstadoRespuesta(EstadoRespuesta.OK);
		when(sesionParametros.getReportSimulacion()).thenReturn(new ReportSimulacionPrestamosView());
		when(mensajeBo.obtenerMensajePorCodigo("1355")).thenReturn(mensajeError);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		Segmento segmento = new Segmento();
		segmento.setDuo(true);
		segmento.setPyme(false);
		when(sesionCliente.getCliente().getSegmento()).thenReturn(segmento);
		when(legalBo.buscarLegal("1005")).thenReturn(legalSeuo);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(sesionParametros.getContador()).thenReturn(contador);
		when(sesionCliente.getCliente().getCuenta(Matchers.anyString())).thenReturn(cuenta);
		when(simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(configPrestDto);
		when(simuladorPrestamoBo.chequearRangos(Matchers.any(SolicitudSimulacionView.class), Matchers.any(PrestamoPermitidoEntity.class))).thenReturn(errores);
		when(simuladorPrestamoBo.obtenerPrestamo(Matchers.any(Cliente.class), Matchers.any(SolicitudSimulacionView.class),
				Matchers.any(ConfiguracionPrestamoDTO.class), anyString())).thenReturn(simuladorPrestamoOutEntity);
		when(estadisticaBo.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class), Matchers.any(Cliente.class))).thenThrow(BusinessException.class);
		when(simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()).thenReturn(true);
		when(sesionParametros.getOfertasComerciales()).thenReturn(ofertasComerciales);

    	//Then
    	Respuesta<ComprobanteFeedbackView> respuesta = simuladorPrestamoManager.adquirirPrestamo(solicitud);
    	
    	//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
    
	}
	
	
	@Test
	public void grabarEstadisticaVisualizacionComprobanteOK() {
		
		//When
		Cliente cliente = mock(Cliente.class);
		
		ComprobantePrestamoView comprobante = new ComprobantePrestamoView();
		comprobante.setCbu("48483939");
		comprobante.setImporteSeleccionado("10000");
		
		Cuenta cuenta = crearCuenta();
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuenta);
		
		//Then
		Boolean respuesta = simuladorPrestamoManager.grabarEstadisticaVisualizacionComprobante(comprobante);
		
		//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(true, respuesta);
		
	}
	
	
	private Cuenta crearCuenta() {
		
    	Cuenta cuenta = new Cuenta();
		cuenta.setAlias("Cuentita personal");
		cuenta.setCbu("57574849393");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		cuenta.setNroCuentaProducto("0000000000639170");
		cuenta.setNroSucursal("0000");
		cuenta.setSaldoCUPesos("000000011265717");
		cuenta.setContratoAltair("1234567890123");
		cuenta.setOficinaAltair("1234");
		
		return cuenta;
	}
	
	private PrestamoOutEntity crearPrestamoOutEntity() {
		
		PrestamoOutEntity simuladorPrestamoOutEntity = new PrestamoOutEntity();

		simuladorPrestamoOutEntity.setImporteAbono("00000000010000000");
		simuladorPrestamoOutEntity.setImporteCargo("00000000010000000");
		simuladorPrestamoOutEntity.setTotCuotaTotal("00000000003573700");
		simuladorPrestamoOutEntity.setCuotaPura("00000000003509000");
		simuladorPrestamoOutEntity.setTotSeguroCuota("00000000000000000");
		simuladorPrestamoOutEntity.setTotIvaCuota("00000000000055200");
		simuladorPrestamoOutEntity.setTotRestoImpuCuota("00000000000009500");
		simuladorPrestamoOutEntity.setTasa("032000000");
		simuladorPrestamoOutEntity.setTea("037136652");
		simuladorPrestamoOutEntity.setCftna("038430000");
		simuladorPrestamoOutEntity.setCftnasimp("031750000");
		simuladorPrestamoOutEntity.setFecha("20170321144928");
		simuladorPrestamoOutEntity.setNroComprobante("474838392");
		
		return simuladorPrestamoOutEntity;
	}
	
	private PrestamoPermitidoEntity crearPrestamoPermitidoEntity() {
		
		PrestamoPermitidoEntity infoPrestamo = new PrestamoPermitidoEntity();
		infoPrestamo.setMinCantCuotas("003");
		infoPrestamo.setMaxCantCuotas("036");
		infoPrestamo.setTpoTasa("F");
		infoPrestamo.setIndLineaUVA("N");
		infoPrestamo.setValorTasa("032000000");
		
		return infoPrestamo;
		
	}
	
	private SolicitudSimulacionView crearSolicitudSimulacionViewCorrecto() {
		
    	SolicitudSimulacionView solicitud = new SolicitudSimulacionView();
    	solicitud.setCbu("0720000788000006391704");
    	solicitud.setCuotaSeleccionada("3");
    	solicitud.setFechaSeleccionada("31/03/2017");
    	solicitud.setImporteSeleccionado("1000");
    	solicitud.setIdRangoSeleccionado("3363200NF");
    	solicitud.setTasaFija(true);
    	solicitud.setUVA(false);

    	DestinoPrestamoSeleccionView destinoView = new DestinoPrestamoSeleccionView();
    	destinoView.setDescripcion("Viajes y turismo");
    	destinoView.setId("350023ARS35001");
    	
    	solicitud.setMotivoSeleccionado(destinoView);
    	solicitud.setLegal("Legal solicitud");
    	return solicitud;
	
	}
	
	private List<RangoCuota> crearListaDeCuotas() {
		
		List<RangoCuota> rangosDeCuotas = new ArrayList<RangoCuota>();
		RangoCuota rangoCuota = new RangoCuota();
		rangoCuota.setCantMaxCuotas("036");
		rangoCuota.setCantMinCuotas("003");
		rangoCuota.setTipoDeTasa("F");
		rangoCuota.setTna("032000000");
		rangosDeCuotas.add(rangoCuota);
		
		return rangosDeCuotas;
		
	}
	
	
}
