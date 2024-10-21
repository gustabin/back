package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.AperturaGraficaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.DetalleRentabilidadTotalDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.DetalleSubclasificionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroCarteraDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroPorFechaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.GrillasProductosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.RentabilidadPeriodoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.RentTotalPeriodoMoneda;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.impl.AnalisisCarteraManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock.AperturaGraficaEntityMock;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock.FiltroCarteraDTOMock;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock.FiltroPorFechaDTOMock;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock.RentabilidadPeriodoEntityMock;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DetalleGrillaRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DetalleProductoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.FiltroPorFechaRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoCircularView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadPeriodoRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalInView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ResponseDashboard;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetallePorAgrupacionEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetallePorInstrumentoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentPorSubclasifEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RespuestaRentabilidadPeriodoEntity;

@RunWith(MockitoJUnitRunner.class)
public class AnalisisCarteraManagerTest {

	@InjectMocks
	AnalisisCarteraManagerImpl analisisCarteraManager;
	
	@Mock
	AnalisisCarteraBO analisisCarteraBO;
	
	@Mock
	private SesionCliente sesionCliente;
	
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private SesionParametros sesionParametros;
    
    @Mock
    private LegalBO legalBO;
    
    @Spy 
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
	private static final String MENSAJE_AYUDA_RENTABILIDAD_PESOS = "10558";
	
	private static final String MENSAJE_AYUDA_RENTABILIDAD_DOLARES = "10559";
	
	private static final String CODIGO_ERROR_INICIO_BASE_ANALISIS_CARTERA = "10485";
    
   	Mensaje mensajePesos = new Mensaje();

	Mensaje mensajeDolares = new Mensaje();
   	
	Mensaje mensajeError = new Mensaje();
	
	Mensaje mensajeRentabilidadRealizada = new Mensaje();

	Mensaje mensajeRentabilidadNoRealizada = new Mensaje();
	
	Mensaje mensajeRentabilidadTotal = new Mensaje();
	
	Mensaje mensajeErrorDashboard = new Mensaje();
	
	Respuesta<String> legal = new Respuesta<String>();
	
	Cliente cliente = mock(Cliente.class);
	
	RegistroSesion registroSesion = mock(RegistroSesion.class);


	@Before
	public void init() {
	
    	mensajePesos.setMensaje("Mensaje ayuda rentabilidad total pesos");
    	mensajeDolares.setMensaje("Mensaje ayuda rentabilidad total dolares");
    	mensajeError.setMensaje("Mensaje error");
    	
    	mensajeRentabilidadRealizada.setMensaje("Mensaje Rentabilidad Realizada");
    	mensajeRentabilidadNoRealizada.setMensaje("Mensaje Rentabilidad no Realizada");
    	mensajeRentabilidadTotal.setMensaje("Mensaje Rentabilidad Total");
    	
    	mensajeErrorDashboard.setMensaje("Mensaje error Dashboard");
    	
    	legal.setRespuesta("RESPUESTA LEGAL");    
    	
		when(mensajeBO.obtenerMensajePorCodigo(MENSAJE_AYUDA_RENTABILIDAD_PESOS)).thenReturn(mensajePesos);
		when(mensajeBO.obtenerMensajePorCodigo(MENSAJE_AYUDA_RENTABILIDAD_DOLARES)).thenReturn(mensajeDolares);
		when(mensajeBO.obtenerMensajePorCodigo(CODIGO_ERROR_INICIO_BASE_ANALISIS_CARTERA)).thenReturn(mensajeError);
				
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TOOLTIP_RENTABILIDAD_REALIZADA)).thenReturn(mensajePesos);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TOOLTIP_RENTABILIDAD_NO_REALIZADA)).thenReturn(mensajeDolares);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TOOLTIP_RENTABILIDAD_TOTAL)).thenReturn(mensajeError);
		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA)).thenReturn(mensajeErrorDashboard);
		
    	when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);    	
		
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	
    	when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		
	}
	
	@Test
    public void obtenerRentabilidadTotalOk() {
    	
    	//When
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    	
		when(analisisCarteraBO.obtenerRentabilidadTotal(Matchers.any(Cliente.class), Matchers.any(RentabilidadTotalInView.class), 
				Matchers.any(Boolean.class))).thenReturn(armarDTO());
		
    	//Then
		Respuesta<RentabilidadTotalView> respuesta = analisisCarteraManager.obtenerRentabilidadTotal(rentabilidadInView);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        	
    }
     
    @Test
    public void obtenerRentabilidadTotalErrorAmbasBancas() {
    	
    	//When
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    	    	
    	DetalleRentabilidadTotalDTO dto = new DetalleRentabilidadTotalDTO();
    	dto.setHayError(true);
    	
		when(analisisCarteraBO.obtenerRentabilidadTotal(Matchers.any(Cliente.class), Matchers.any(RentabilidadTotalInView.class), 
				Matchers.any(Boolean.class))).thenReturn(dto);
		
    	//Then
		Respuesta<RentabilidadTotalView> respuesta = analisisCarteraManager.obtenerRentabilidadTotal(rentabilidadInView);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
    }    
    

    @Test
    public void obtenerRentabilidadTotalNoHayInversionesAmbasBancas() {
    	
    	//When
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    	    	
    	DetalleRentabilidadTotalDTO dto = new DetalleRentabilidadTotalDTO();
    	dto.setHayError(false);
    	dto.setNoTieneRentabilidad(true);
    	
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajePesos);
		when(analisisCarteraBO.obtenerRentabilidadTotal(Matchers.any(Cliente.class), Matchers.any(RentabilidadTotalInView.class), 
				Matchers.any(Boolean.class))).thenReturn(dto);
		
    	//Then
		Respuesta<RentabilidadTotalView> respuesta = analisisCarteraManager.obtenerRentabilidadTotal(rentabilidadInView);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
    }   
    
    @Test
    public void obtenerRentabilidadTotalErrorBancaRetail() {
    	
    	//When
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    	    	
    	DetalleRentabilidadTotalDTO dto = new DetalleRentabilidadTotalDTO();
    	dto.setHayError(true);
    	dto.setCorrespondeBanca(true);

    	when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, true)).thenReturn(armarDTO());
		when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, false)).thenReturn(dto);
		
    	//Then
		Respuesta<RentabilidadTotalView> respuesta = analisisCarteraManager.obtenerRentabilidadTotal(rentabilidadInView);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
		
    }    
    
    @Test
    public void obtenerRentabilidadTotalErrorBancaPrivada() {
    	
    	//When
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    	    	
    	DetalleRentabilidadTotalDTO dto = new DetalleRentabilidadTotalDTO();
    	dto.setHayError(true);
    	dto.setCorrespondeBanca(true);
 
    	DetalleRentabilidadTotalDTO rentabilidadPrivada = armarDTO();
    	rentabilidadPrivada.setNoTieneRentabilidad(false);
    	
    	when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, true)).thenReturn(dto);
		when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, false)).thenReturn(rentabilidadPrivada);
		
    	//Then
		Respuesta<RentabilidadTotalView> respuesta = analisisCarteraManager.obtenerRentabilidadTotal(rentabilidadInView);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
		
    }    
    
    @Test
    public void obtenerRentabilidadOKSoloRetail() {
    	
    	//When
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");

    	DetalleRentabilidadTotalDTO dto = new DetalleRentabilidadTotalDTO();
    	dto.setCorrespondeBanca(false);

    	DetalleRentabilidadTotalDTO dtoOk = armarDTO();
    	dtoOk.getRentTotalPeriodoMoneda().setRentabilidadDolares(new BigDecimal("3212.0"));
    	
    	when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, true)).thenReturn(dto);
		when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, false)).thenReturn(dtoOk);
		
    	//Then
		Respuesta<RentabilidadTotalView> respuesta = analisisCarteraManager.obtenerRentabilidadTotal(rentabilidadInView);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
    }	
    
    @Test
    public void obtenerRentabilidadOKSoloPrivada() {
    	
    	//When
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");

    	DetalleRentabilidadTotalDTO dto = new DetalleRentabilidadTotalDTO();
    	dto.setCorrespondeBanca(false);

    	DetalleRentabilidadTotalDTO dtoOk = armarDTO();
    	dtoOk.getRentTotalPeriodoMoneda().setRentabilidadDolares(new BigDecimal("3212.0"));
    	dtoOk.getRentTotalPeriodoMoneda().setRentabilidadPesos(null);
    	    	
    	when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, true)).thenReturn(dtoOk);
		when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, false)).thenReturn(dto);
		
    	//Then
		Respuesta<RentabilidadTotalView> respuesta = analisisCarteraManager.obtenerRentabilidadTotal(rentabilidadInView);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
    }    
    
    @Test
    public void obtenerRentabilidadErrorSoloRetail() {
    	
    	//When
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");

    	DetalleRentabilidadTotalDTO dto = new DetalleRentabilidadTotalDTO();
    	dto.setCorrespondeBanca(false);
    	
    	DetalleRentabilidadTotalDTO dtoError = new DetalleRentabilidadTotalDTO();
    	dtoError.setHayError(true);
    	dtoError.setCorrespondeBanca(true);
    	
    	when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, true)).thenReturn(dto);
		when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, false)).thenReturn(dtoError);
		
    	//Then
		Respuesta<RentabilidadTotalView> respuesta = analisisCarteraManager.obtenerRentabilidadTotal(rentabilidadInView);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
    }    
    
    @Test
    public void obtenerRentabilidadErrorSoloPrivada() {
    	
    	//When
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");

    	DetalleRentabilidadTotalDTO dto = new DetalleRentabilidadTotalDTO();
    	dto.setCorrespondeBanca(false);
    	
    	DetalleRentabilidadTotalDTO dtoError = new DetalleRentabilidadTotalDTO();
    	dtoError.setHayError(true);
    	dtoError.setCorrespondeBanca(true);

    	when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, true)).thenReturn(dtoError);
		when(analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, false)).thenReturn(dto);
    	
    	//Then
		Respuesta<RentabilidadTotalView> respuesta = analisisCarteraManager.obtenerRentabilidadTotal(rentabilidadInView);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
    }    
	
    @Test
    public void obtenerDashboardOK() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.TRUE);

    	Respuesta<FiltroCarteraDTO> filtroCartera = respuestaFactory.crearRespuestaOk(FiltroCarteraDTOMock.armarFiltroCarteraDTOMock());
    	filtroCartera.getRespuesta().getResultadoCarteraCliente().get(0).setPorDefecto(Boolean.FALSE);
       	filtroCartera.getRespuesta().getResultadoCarteraCliente().get(1).setPorDefecto(Boolean.TRUE);
       	filtroCartera.getRespuesta().getResultadoCarteraCliente().get(1).setCodigoCartera("PAR");
    	Respuesta<FiltroPorFechaDTO> filtroFecha = respuestaFactory.crearRespuestaOk(FiltroPorFechaDTOMock.armarFiltroPorFechaDTOMock());
    	Respuesta<RentabilidadPeriodoDTO> rentabilidadPeriodoDTO = respuestaFactory.crearRespuestaOk(armarRentabilidadPeriodoDTO());
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	when(analisisCarteraBO.obtenerFiltroPorFecha(Matchers.any(Cliente.class), Matchers.any(FiltroPorFechaRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroFecha);
    	when(analisisCarteraBO.obtenerRentabilidadPeriodo(Matchers.any(Cliente.class), Matchers.any(RentabilidadPeriodoRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(rentabilidadPeriodoDTO);
    	    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboard(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    	    	
    }
    
    @Test
    public void obtenerDashboardErrorFiltroCartera() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.TRUE);

    	Respuesta<FiltroCarteraDTO> filtroCartera = new Respuesta<FiltroCarteraDTO>();
    	filtroCartera.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboard(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	    	
    }
    
    @Test
    public void obtenerDashboardErrorFiltroFecha() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.TRUE);

    	Respuesta<FiltroCarteraDTO> filtroCartera = respuestaFactory.crearRespuestaOk(FiltroCarteraDTOMock.armarFiltroCarteraDTOMock());
    	Respuesta<FiltroPorFechaDTO> filtroFecha = new Respuesta<FiltroPorFechaDTO>();
    	filtroFecha.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	when(analisisCarteraBO.obtenerFiltroPorFecha(Matchers.any(Cliente.class), Matchers.any(FiltroPorFechaRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroFecha);
    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboard(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	    	
    }
    
    
    @Test
    public void obtenerDashboardErrorRentabilidadPeriodo() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.TRUE);

    	Respuesta<FiltroCarteraDTO> filtroCartera = respuestaFactory.crearRespuestaOk(FiltroCarteraDTOMock.armarFiltroCarteraDTOMock());
    	Respuesta<FiltroPorFechaDTO> filtroFecha = respuestaFactory.crearRespuestaOk(FiltroPorFechaDTOMock.armarFiltroPorFechaDTOMock());
    	Respuesta<RentabilidadPeriodoDTO> rentabilidadPeriodoDTO = new Respuesta<RentabilidadPeriodoDTO>();
    	rentabilidadPeriodoDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	when(analisisCarteraBO.obtenerFiltroPorFecha(Matchers.any(Cliente.class), Matchers.any(FiltroPorFechaRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroFecha);
    	when(analisisCarteraBO.obtenerRentabilidadPeriodo(Matchers.any(Cliente.class), Matchers.any(RentabilidadPeriodoRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(rentabilidadPeriodoDTO);
    	    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboard(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	    	
    }
    
    @Test
    public void obtenerDashboardErrorFiltroFechaNoDefault() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.FALSE);

    	Respuesta<FiltroCarteraDTO> filtroCartera = respuestaFactory.crearRespuestaOk(FiltroCarteraDTOMock.armarFiltroCarteraDTOMock());
    	Respuesta<FiltroPorFechaDTO> filtroFecha = new Respuesta<FiltroPorFechaDTO>();
    	filtroFecha.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	Respuesta<RentabilidadPeriodoDTO> rentabilidadPorPeriodo = new Respuesta<RentabilidadPeriodoDTO>();
    	rentabilidadPorPeriodo.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	when(analisisCarteraBO.obtenerFiltroPorFecha(Matchers.any(Cliente.class), Matchers.any(FiltroPorFechaRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroFecha);
    	when(analisisCarteraBO.obtenerRentabilidadPeriodo(Matchers.any(Cliente.class), Matchers.any(RentabilidadPeriodoRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(rentabilidadPorPeriodo);
    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboard(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        	
    }
    
    @Test
    public void obtenerDashboardOKNoDefault() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.FALSE);
   	
    	Respuesta<FiltroCarteraDTO> filtroCartera = respuestaFactory.crearRespuestaOk(FiltroCarteraDTOMock.armarFiltroCarteraDTOMock());
    	Respuesta<FiltroPorFechaDTO> filtroFecha = respuestaFactory.crearRespuestaOk(FiltroPorFechaDTOMock.armarFiltroPorFechaDTOMock());
    	Respuesta<RentabilidadPeriodoDTO> rentabilidadPeriodoDTO = respuestaFactory.crearRespuestaOk(armarRentabilidadPeriodoDTO());	
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	when(analisisCarteraBO.obtenerFiltroPorFecha(Matchers.any(Cliente.class), Matchers.any(FiltroPorFechaRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroFecha);
    	when(analisisCarteraBO.obtenerRentabilidadPeriodo(Matchers.any(Cliente.class), Matchers.any(RentabilidadPeriodoRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(rentabilidadPeriodoDTO);
    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboard(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    	    	
    }
       
    @Test
    public void obtenerDashboardBPOK() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.TRUE);
    	Respuesta<FiltroCarteraDTO> filtroCartera = respuestaFactory.crearRespuestaOk(FiltroCarteraDTOMock.armarFiltroCarteraDTOMock());
    	filtroCartera.getRespuesta().getResultadoCarteraCliente().get(0).setPorDefecto(Boolean.FALSE);
       	filtroCartera.getRespuesta().getResultadoCarteraCliente().get(1).setPorDefecto(Boolean.TRUE);
    	
       	Respuesta<FiltroPorFechaDTO> filtroFecha = respuestaFactory.crearRespuestaOk(FiltroPorFechaDTOMock.armarFiltroPorFechaDTOMock());
    	filtroFecha.getRespuesta().getPeriodos().get(0).getListaMonedas().get(0).setDefecto(Boolean.FALSE);
    	
    	Respuesta<RentabilidadPeriodoDTO> rentabilidadPeriodoDTO = respuestaFactory.crearRespuestaOk(armarRentabilidadPeriodoDTO());
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	when(analisisCarteraBO.obtenerFiltroPorFecha(Matchers.any(Cliente.class), Matchers.any(FiltroPorFechaRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroFecha);
    	when(analisisCarteraBO.obtenerRentabilidadPeriodo(Matchers.any(Cliente.class), Matchers.any(RentabilidadPeriodoRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(rentabilidadPeriodoDTO);
    	    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboardBPriv(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        	
    }
        
    @Test
    public void obtenerDashboardBPOKNoDefault() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.FALSE);
    	Respuesta<FiltroCarteraDTO> filtroCartera = respuestaFactory.crearRespuestaOk(FiltroCarteraDTOMock.armarFiltroCarteraDTOMock());
    	Respuesta<FiltroPorFechaDTO> filtroFecha = respuestaFactory.crearRespuestaOk(FiltroPorFechaDTOMock.armarFiltroPorFechaDTOMock());
    	Respuesta<RentabilidadPeriodoDTO> rentabilidadPeriodoDTO = respuestaFactory.crearRespuestaOk(armarRentabilidadPeriodoDTO());
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	when(analisisCarteraBO.obtenerFiltroPorFecha(Matchers.any(Cliente.class), Matchers.any(FiltroPorFechaRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroFecha);
    	when(analisisCarteraBO.obtenerRentabilidadPeriodo(Matchers.any(Cliente.class), Matchers.any(RentabilidadPeriodoRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(rentabilidadPeriodoDTO);
    	    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboardBPriv(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        	
    }
    
    @Test
    public void obtenerDashboardBPErrorFiltroCartera() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.TRUE);

    	Respuesta<FiltroCarteraDTO> filtroCartera = new Respuesta<FiltroCarteraDTO>();
    	filtroCartera.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboardBPriv(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	    	
    }
    
    @Test
    public void obtenerDashboardBPErrorFiltroFecha() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.TRUE);

    	Respuesta<FiltroCarteraDTO> filtroCartera = respuestaFactory.crearRespuestaOk(FiltroCarteraDTOMock.armarFiltroCarteraDTOMock());
    	Respuesta<FiltroPorFechaDTO> filtroFecha = new Respuesta<FiltroPorFechaDTO>();
    	filtroFecha.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	when(analisisCarteraBO.obtenerFiltroPorFecha(Matchers.any(Cliente.class), Matchers.any(FiltroPorFechaRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroFecha);
    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboardBPriv(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	    	
    }
        
    @Test
    public void obtenerDashboardBPErrorRentabilidadPeriodo() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.TRUE);

    	Respuesta<FiltroCarteraDTO> filtroCartera = respuestaFactory.crearRespuestaOk(FiltroCarteraDTOMock.armarFiltroCarteraDTOMock());
    	Respuesta<FiltroPorFechaDTO> filtroFecha = respuestaFactory.crearRespuestaOk(FiltroPorFechaDTOMock.armarFiltroPorFechaDTOMock());
    	Respuesta<RentabilidadPeriodoDTO> rentabilidadPeriodoDTO = new Respuesta<RentabilidadPeriodoDTO>();
    	rentabilidadPeriodoDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	when(analisisCarteraBO.obtenerFiltroPorFecha(Matchers.any(Cliente.class), Matchers.any(FiltroPorFechaRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroFecha);
    	when(analisisCarteraBO.obtenerRentabilidadPeriodo(Matchers.any(Cliente.class), Matchers.any(RentabilidadPeriodoRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(rentabilidadPeriodoDTO);
    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboardBPriv(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	    	
    }
    
    @Test
    public void obtenerDashboardBPErrorFiltroFechaNoDefault() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setDefecto(Boolean.FALSE);

    	Respuesta<FiltroCarteraDTO> filtroCartera = respuestaFactory.crearRespuestaOk(FiltroCarteraDTOMock.armarFiltroCarteraDTOMock());
    	Respuesta<FiltroPorFechaDTO> filtroFecha = new Respuesta<FiltroPorFechaDTO>();
    	filtroFecha.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	Respuesta<RentabilidadPeriodoDTO> rentabilidadPorPeriodo = new Respuesta<RentabilidadPeriodoDTO>();
    	rentabilidadPorPeriodo.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	when(analisisCarteraBO.obtenerFiltroCartera(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroCartera);
    	when(analisisCarteraBO.obtenerFiltroPorFecha(Matchers.any(Cliente.class), Matchers.any(FiltroPorFechaRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(filtroFecha);
    	when(analisisCarteraBO.obtenerRentabilidadPeriodo(Matchers.any(Cliente.class), Matchers.any(RentabilidadPeriodoRequestView.class), Matchers.any(TipoBancaEnum.class))).thenReturn(rentabilidadPorPeriodo);
    	
    	//Then
    	Respuesta<ResponseDashboard> respuesta = analisisCarteraManager.obtenerDashboardBPriv(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        	
    }
    
    @Test
    public void grabarEstadisticaRentabilidadMobileRTL() {
    	
    	analisisCarteraManager.grabarEstadisticaRentabilidadMobileRTL();
    	
    }
    
    
    @Test
    public void obtenerRentabilidadRendimientoRTLOK() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setArmarFiltro(Boolean.TRUE);
    	request.setCarteraAConsultar("TOT");
    	
    	Respuesta<AperturaGraficaDTO> respuestaAperturaGraficaDTO = new Respuesta<AperturaGraficaDTO>();
    	AperturaGraficaEntity aperturaGraficaEntity = AperturaGraficaEntityMock.armarAperturaGraficaEntityMock();
    	AperturaGraficaDTO aperturaGraficaDTO = new AperturaGraficaDTO();
    	aperturaGraficaDTO.setListaResultado(aperturaGraficaEntity.getDatos().getResultado());
    	respuestaAperturaGraficaDTO.setRespuesta(aperturaGraficaDTO);
    	respuestaAperturaGraficaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	Respuesta<GrillasProductosDTO> respuestaGrillasDTO = new Respuesta<GrillasProductosDTO>();
    	GrillasProductosDTO respuestaDTO = new GrillasProductosDTO();
    	respuestaDTO.setListaGraficoCircular(new ArrayList<GraficoCircularView>());
    	respuestaDTO.setListaProductos(new ArrayList<DetalleProductoView>());
    	respuestaDTO.setLlamarRendimiento(true);
    	respuestaDTO.setLlamarRentabilidad(true);
    	respuestaDTO.setMoneda("pesos");
		respuestaGrillasDTO.setRespuesta(respuestaDTO);
    	respuestaGrillasDTO.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	when(analisisCarteraBO.obtenerFiltroRentabilidad(Matchers.any(Cliente.class), Matchers.any(RequestDashboard.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respuestaAperturaGraficaDTO);
    	when(analisisCarteraBO.obtenerGrillasDTO(Matchers.any(Cliente.class), Matchers.any(RequestDashboard.class), Matchers.any(TipoBancaEnum.class), Matchers.anyBoolean())).thenReturn(respuestaGrillasDTO);
    	
    	//Then
    	Respuesta<RentabilidadRendimientoView> respuesta = analisisCarteraManager.obtenerRentabilidadRendimiento(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }
    
    
    @Test
    public void obtenerRentabilidadRendimientoRTLErrorFiltro() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setArmarFiltro(Boolean.TRUE);
    	request.setCarteraAConsultar("TOT");
    	
    	Respuesta<AperturaGraficaDTO> respuestaAperturaGraficaDTO = new Respuesta<AperturaGraficaDTO>();
    	respuestaAperturaGraficaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	Respuesta<GrillasProductosDTO> respuestaGrillasDTO = new Respuesta<GrillasProductosDTO>();
    	respuestaGrillasDTO.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	when(analisisCarteraBO.obtenerFiltroRentabilidad(Matchers.any(Cliente.class), Matchers.any(RequestDashboard.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respuestaAperturaGraficaDTO);
    	when(analisisCarteraBO.obtenerGrillasDTO(Matchers.any(Cliente.class), Matchers.any(RequestDashboard.class), Matchers.any(TipoBancaEnum.class), Matchers.any(Boolean.class))).thenReturn(respuestaGrillasDTO);
    	
    	//Then
    	Respuesta<RentabilidadRendimientoView> respuesta = analisisCarteraManager.obtenerRentabilidadRendimiento(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }
    
    
    @Test
    public void obtenerRentabilidadRendimientoRTLErrorGrillas() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setArmarFiltro(Boolean.FALSE);
    	request.setCarteraAConsultar("TOT");
    	
    	Respuesta<GrillasProductosDTO> respuestaGrillasDTO = new Respuesta<GrillasProductosDTO>();
    	respuestaGrillasDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	when(analisisCarteraBO.obtenerGrillasDTO(Matchers.any(Cliente.class), Matchers.any(RequestDashboard.class), Matchers.any(TipoBancaEnum.class), Matchers.anyBoolean())).thenReturn(respuestaGrillasDTO);
    	
    	//Then
    	Respuesta<RentabilidadRendimientoView> respuesta = analisisCarteraManager.obtenerRentabilidadRendimiento(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }
    
    @Test
    public void obtenerRentabilidadRendimientoBPOK() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setArmarFiltro(Boolean.TRUE);
    	request.setCarteraAConsultar("TOT");
    	
    	Respuesta<AperturaGraficaDTO> respuestaAperturaGraficaDTO = new Respuesta<AperturaGraficaDTO>();
    	AperturaGraficaEntity aperturaGraficaEntity = AperturaGraficaEntityMock.armarAperturaGraficaEntityMock();
    	AperturaGraficaDTO aperturaGraficaDTO = new AperturaGraficaDTO();
    	aperturaGraficaDTO.setListaResultado(aperturaGraficaEntity.getDatos().getResultado());
    	respuestaAperturaGraficaDTO.setRespuesta(aperturaGraficaDTO);
    	respuestaAperturaGraficaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	Respuesta<GrillasProductosDTO> respuestaGrillasDTO = new Respuesta<GrillasProductosDTO>();
    	respuestaGrillasDTO.setEstadoRespuesta(EstadoRespuesta.OK);
    	List<DetalleProductoView> listaDetalle = new ArrayList<DetalleProductoView>();
    	GrillasProductosDTO grillasDTO = new GrillasProductosDTO();
    	grillasDTO.setListaProductos(listaDetalle);
    	respuestaGrillasDTO.setRespuesta(grillasDTO);
    	
    	when(analisisCarteraBO.obtenerFiltroRentabilidad(Matchers.any(Cliente.class), Matchers.any(RequestDashboard.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respuestaAperturaGraficaDTO);
    	when(analisisCarteraBO.obtenerGrillasDTO(Matchers.any(Cliente.class), Matchers.any(RequestDashboard.class), Matchers.any(TipoBancaEnum.class), Matchers.any(Boolean.class))).thenReturn(respuestaGrillasDTO);
    	
    	//Then
    	Respuesta<RentabilidadRendimientoView> respuesta = analisisCarteraManager.obtenerRentabilidadRendimientoBPriv(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
    	System.out.println(json);
    }
        
    
    @Test
    public void obtenerRentabilidadRendimientoBPErrorFiltro() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setArmarFiltro(Boolean.TRUE);
    	request.setCarteraAConsultar("TOT");
    	
    	Respuesta<AperturaGraficaDTO> respuestaAperturaGraficaDTO = new Respuesta<AperturaGraficaDTO>();
    	respuestaAperturaGraficaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	Respuesta<GrillasProductosDTO> respuestaGrillasDTO = new Respuesta<GrillasProductosDTO>();
    	respuestaGrillasDTO.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	when(analisisCarteraBO.obtenerFiltroRentabilidad(Matchers.any(Cliente.class), Matchers.any(RequestDashboard.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respuestaAperturaGraficaDTO);
    	when(analisisCarteraBO.obtenerGrillasDTO(Matchers.any(Cliente.class), Matchers.any(RequestDashboard.class), Matchers.any(TipoBancaEnum.class), Matchers.any(Boolean.class))).thenReturn(respuestaGrillasDTO);
    	
    	//Then
    	Respuesta<RentabilidadRendimientoView> respuesta = analisisCarteraManager.obtenerRentabilidadRendimientoBPriv(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }
    
    
    @Test
    public void obtenerDetalleGrillasRTLOK() {
    	
    	//When
    	RequestDashboard request = new RequestDashboard();
    	request.setCarteraAConsultar("TOT");
    	request.setClasificacion("PROD");
    	request.setSubclasificacion("TOT");
    	request.setProducto("PUB");
    	request.setMoneda("USD");
    	request.setPeriodo("30D");
    	request.setCategoria("TV");
    	    	
    	Respuesta<DetalleSubclasificionDTO> respuestaDetalleSubclasificacionDTO = armarDetalleSubclasificacionDTO();
    	
    	when(analisisCarteraBO.obtenerDetalleSubClasificacion(Matchers.any(Cliente.class), Matchers.any(RequestDashboard.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respuestaDetalleSubclasificacionDTO);
    	
    	//Then
    	Respuesta<DetalleGrillaRentabilidadView> respuesta = analisisCarteraManager.obtenerDetalleGrillaBPriv(request);
    	
    	//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
    	System.out.println(json);

    }    
    
    
    private DetalleRentabilidadTotalDTO armarDTO() {
    	
    	DetalleRentabilidadTotalDTO dto = new DetalleRentabilidadTotalDTO();
       	RentTotalPeriodoMoneda rentTotalPeriodoMoneda = new RentTotalPeriodoMoneda();
    	
    	rentTotalPeriodoMoneda.setCodigoPeriodo("30D");
    	rentTotalPeriodoMoneda.setFechaFin("2018-06-16T00:00:00");
    	rentTotalPeriodoMoneda.setFechaInicio("2018-07-16T00:00:00");
    	rentTotalPeriodoMoneda.setGuidError(null);
    	rentTotalPeriodoMoneda.setRentabilidadPesos(new BigDecimal("3212.0"));
    	rentTotalPeriodoMoneda.setRentabilidadDolares(null);
    	
    	dto.setRentTotalPeriodoMoneda(rentTotalPeriodoMoneda);
    	dto.setCorrespondeBanca(true);
    	
    	return dto;
    }
    
    private RentabilidadPeriodoDTO armarRentabilidadPeriodoDTO() {
    	
    	RespuestaRentabilidadPeriodoEntity rentabilidad = RentabilidadPeriodoEntityMock.armarRentabilidadPeriodoEntityMock();
    	RentabilidadPeriodoDTO rentabilidadDTO = new RentabilidadPeriodoDTO();
    	rentabilidadDTO.setDatos(rentabilidad);
    	return rentabilidadDTO;
    	
    }
    
//    private Respuesta<DetalleSubclasificionDTO> armarDetalleSubclasificacionDTO() {
//    	
//    	Respuesta<DetalleSubclasificionDTO> respuesta = new Respuesta<DetalleSubclasificionDTO>();
//    	DetalleSubclasificionDTO detalleSubclasificacionDTO = new DetalleSubclasificionDTO();
//    	DetalleRentPorSubclasifEntity resultado = new DetalleRentPorSubclasifEntity();
//    	resultado.setCodigoClasificacion("PROD");
//    	resultado.setCodigoPeriodo("30D");
//    	resultado.setCodigoSubclasificacion("TV");
//    	resultado.setDescripcionClasificacion("Productos");
//    	resultado.setDescripcionPeriodo("ULTIMOS 30 DIAS");
//    	resultado.setDescripcionSubclasificacion("Titulos Valores");
//    	resultado.setMoneda("USD");
//    	resultado.setPeriodoFechaFinal("24/08/2018");
//    	resultado.setPeriodoFechaInicial("25/07/2018");
//    	resultado.setRendimiento(new BigDecimal("30.25"));
//    	resultado.setRentabilidadNeta(new BigDecimal("100000.25"));
//    	resultado.setTna(new BigDecimal("15.25"));
//    	
//    	List<DetallePorAgrupacionEntity> listaAgrupacion = new ArrayList<DetallePorAgrupacionEntity>();
//    	DetallePorAgrupacionEntity agrupacion = new DetallePorAgrupacionEntity();
//    	agrupacion.setDescripcionAgrupacion("Titulos Publicos");
//    	agrupacion.setCodigoAgrupacion("PUB");
//    	agrupacion.setRentabilidadNeta(new BigDecimal("15430.25"));
//    	agrupacion.setRendimiento(new BigDecimal("11.76"));
//    	agrupacion.setTna(new BigDecimal("34.25"));
//    	
//    	List<DetallePorInstrumentoEntity> resultadoInstrumento = new ArrayList<DetallePorInstrumentoEntity>();
//    	DetallePorInstrumentoEntity detallePorInstrumentoEntity = new DetallePorInstrumentoEntity();
//    	
//    	detallePorInstrumentoEntity.setDescripcionInstrumento("AUSOL");
//    	detallePorInstrumentoEntity.setRentabilidadNeta(new BigDecimal("15472.25"));
//    	detallePorInstrumentoEntity.setRendimiento(new BigDecimal("30.25"));
//    	detallePorInstrumentoEntity.setTna(new BigDecimal("15.25"));
//    	
//    	resultadoInstrumento.add(detallePorInstrumentoEntity);
//    	agrupacion.setResultadoInstrumento(resultadoInstrumento);
//    	listaAgrupacion.add(agrupacion);
//    	resultado.setResultadoAgrupacion(listaAgrupacion);
//    	
//    	detalleSubclasificacionDTO.setResultado(resultado);
//    	respuesta.setRespuesta(detalleSubclasificacionDTO);
//    	respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
//    	return respuesta;
//    	
//    }
    
    private Respuesta<DetalleSubclasificionDTO> armarDetalleSubclasificacionDTO() {
    	
    	Respuesta<DetalleSubclasificionDTO> respuesta = new Respuesta<DetalleSubclasificionDTO>();
    	DetalleSubclasificionDTO detalleSubclasificacionDTO = new DetalleSubclasificionDTO();
    	DetalleRentPorSubclasifEntity resultado = new DetalleRentPorSubclasifEntity();
    	resultado.setCodigoClasificacion("PROD");
    	resultado.setCodigoPeriodo("30D");
    	resultado.setCodigoSubclasificacion("TV");
    	resultado.setDescripcionClasificacion("Productos");
    	resultado.setDescripcionPeriodo("ULTIMOS 30 DIAS");
    	resultado.setDescripcionSubclasificacion("Titulos Valores");
    	resultado.setMoneda("USD");
    	resultado.setPeriodoFechaFinal("24/08/2018");
    	resultado.setPeriodoFechaInicial("25/07/2018");
    	resultado.setRendimiento(new BigDecimal("30.25"));
    	resultado.setRentabilidadNeta(new BigDecimal("12005430.25"));
    	resultado.setTna(new BigDecimal("15.25"));
    	
    	List<DetallePorAgrupacionEntity> listaAgrupacion = new ArrayList<DetallePorAgrupacionEntity>();
    	DetallePorAgrupacionEntity agrupacion = new DetallePorAgrupacionEntity();
    	agrupacion.setDescripcionAgrupacion("Titulos Publicos");
    	agrupacion.setCodigoAgrupacion("PUB");
    	agrupacion.setRentabilidadNeta(new BigDecimal("42430.25"));
    	agrupacion.setRendimiento(new BigDecimal("30.25"));
    	agrupacion.setTna(new BigDecimal("15.25"));
    	
    	List<DetallePorInstrumentoEntity> resultadoInstrumento = new ArrayList<DetallePorInstrumentoEntity>();
    	DetallePorInstrumentoEntity instrumento1 = new DetallePorInstrumentoEntity();
    	instrumento1.setDescripcionInstrumento("BONAR 2017");
    	instrumento1.setRentabilidadNeta(new BigDecimal("1430.25"));
    	instrumento1.setRendimiento(new BigDecimal("30.25"));
    	instrumento1.setTna(new BigDecimal("15.25"));
    	
    	resultadoInstrumento.add(instrumento1);
    	
    	DetallePorInstrumentoEntity instrumento2 = new DetallePorInstrumentoEntity();
    	instrumento2.setDescripcionInstrumento("DICA");
    	instrumento2.setRentabilidadNeta(new BigDecimal("430.25"));
    	instrumento2.setRendimiento(new BigDecimal("30.25"));
    	instrumento2.setTna(new BigDecimal("15.25"));
    	
    	resultadoInstrumento.add(instrumento2);
    	
    	agrupacion.setResultadoInstrumento(resultadoInstrumento);
    	
    	DetallePorAgrupacionEntity agrupacion2 = new DetallePorAgrupacionEntity();
    	agrupacion2.setDescripcionAgrupacion("Acciones");
    	agrupacion2.setCodigoAgrupacion("ACC");
    	agrupacion2.setRentabilidadNeta(new BigDecimal("42430.25"));
    	agrupacion2.setRendimiento(new BigDecimal("30.25"));
    	agrupacion2.setTna(new BigDecimal("15.25"));
    	
    	List<DetallePorInstrumentoEntity> resultadoInstrumento2 = new ArrayList<DetallePorInstrumentoEntity>();
    	DetallePorInstrumentoEntity instrumento3 = new DetallePorInstrumentoEntity();
    	instrumento3.setDescripcionInstrumento("AUSOL");
    	instrumento3.setRentabilidadNeta(new BigDecimal("1430.25"));
    	instrumento3.setRendimiento(new BigDecimal("30.25"));
    	instrumento3.setTna(new BigDecimal("15.25"));
    	
    	resultadoInstrumento2.add(instrumento3);
    	
    	DetallePorInstrumentoEntity instrumento4 = new DetallePorInstrumentoEntity();
    	instrumento4.setDescripcionInstrumento("YPF");
    	instrumento4.setRentabilidadNeta(new BigDecimal("430.25"));
    	instrumento4.setRendimiento(new BigDecimal("-30.25"));
    	instrumento4.setTna(new BigDecimal("-15.25"));
    	
    	resultadoInstrumento2.add(instrumento4);
    	
    	agrupacion2.setResultadoInstrumento(resultadoInstrumento2);
    	
    	DetallePorAgrupacionEntity agrupacion3 = new DetallePorAgrupacionEntity();
    	agrupacion3.setDescripcionAgrupacion("Cedears");
    	agrupacion3.setCodigoAgrupacion("CED");
    	agrupacion3.setRentabilidadNeta(new BigDecimal("12430.25"));
    	agrupacion3.setRendimiento(new BigDecimal("-30.25"));
    	agrupacion3.setTna(new BigDecimal("15.25"));
    	
    	List<DetallePorInstrumentoEntity> resultadoInstrumento3 = new ArrayList<DetallePorInstrumentoEntity>();
    	DetallePorInstrumentoEntity instrumento5 = new DetallePorInstrumentoEntity();
    	instrumento5.setDescripcionInstrumento("GOOGLE");
    	instrumento5.setRentabilidadNeta(new BigDecimal("1430.25"));
    	instrumento5.setRendimiento(new BigDecimal("30.25"));
    	instrumento5.setTna(new BigDecimal("15.25"));
    	
    	resultadoInstrumento3.add(instrumento5);
    	
    	DetallePorInstrumentoEntity instrumento6 = new DetallePorInstrumentoEntity();
    	instrumento6.setDescripcionInstrumento("MSFT");
    	instrumento6.setRentabilidadNeta(new BigDecimal("430.25"));
    	instrumento6.setRendimiento(new BigDecimal("-30.25"));
    	instrumento6.setTna(new BigDecimal("-15.25"));
    	
    	resultadoInstrumento3.add(instrumento6);
    	
    	agrupacion3.setResultadoInstrumento(resultadoInstrumento3);    	
    	
    	listaAgrupacion.add(agrupacion);
    	listaAgrupacion.add(agrupacion2);
    	listaAgrupacion.add(agrupacion3);
    	resultado.setResultadoAgrupacion(listaAgrupacion);
    	
    	detalleSubclasificacionDTO.setResultado(resultado);
    	respuesta.setRespuesta(detalleSubclasificacionDTO);
    	respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
    	return respuesta;
    	
    }
    
}
