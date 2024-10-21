package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.impl.AnalisisCarteraBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.DetalleRentabilidadTotalDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroCarteraDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroPorFechaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.RentabilidadPeriodoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.DatosRespuestaRentabilidadTotal;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.DetalleRentabilidadTotalEntity;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.RentTotalPeriodoMoneda;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.RentabilidadTotalRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock.FiltroCarteraEntityMock;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock.FiltroFechaEntityMock;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock.RentabilidadPeriodoEntityMock;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.CuentaACView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.FiltroPorFechaRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadPeriodoRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosRespuestaRentabilidadPeriodoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroCarteraEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroPorFechaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroPorFechaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RespuestaRentabilidadPeriodoEntity;

@RunWith(MockitoJUnitRunner.class)
public class AnalisisCarteraBOTest {

	@InjectMocks
	AnalisisCarteraBOImpl analisisCarteraBO;
	
	@Mock
	RendimientoTenenciaDAO rendimientoTenenciaDAO;
	
    @Spy 
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	@Test
	public void obtenerRentabilidadTotalOKRetail() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasRetail(cuentas);
		
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    			
    	when(rendimientoTenenciaDAO.obtenerRentabilidadTotal(Matchers.any(RentabilidadTotalRequestEntity.class))).thenReturn(armarEntity());
	
		//Then
		DetalleRentabilidadTotalDTO respuesta = analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, false);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(false, respuesta.getHayError());
		
	}
	
	@Test
	public void obtenerRentabilidadTotalOKNoHayDatos() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasRetail(cuentas);
		
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    			
    	DetalleRentabilidadTotalEntity entity = new DetalleRentabilidadTotalEntity();
    	DatosRespuestaRentabilidadTotal datos = new DatosRespuestaRentabilidadTotal();
    	entity.setDatos(datos);
    	
    	when(rendimientoTenenciaDAO.obtenerRentabilidadTotal(Matchers.any(RentabilidadTotalRequestEntity.class))).thenReturn(entity);
	
		//Then
		DetalleRentabilidadTotalDTO respuesta = analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, false);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(true, respuesta.getHayError());
		
	}
	
	@Test
	public void obtenerRentabilidadTotalOKPrivada() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    			
    	when(rendimientoTenenciaDAO.obtenerRentabilidadTotal(Matchers.any(RentabilidadTotalRequestEntity.class))).thenReturn(armarEntity());
	
		//Then
		DetalleRentabilidadTotalDTO respuesta = analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, true);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(false, respuesta.getHayError());
		
	}
	
	
	@Test
	public void obtenerRentabilidadTotalNoHayBancaPrivada() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    			
		//Then
		DetalleRentabilidadTotalDTO respuesta = analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, true);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(false, respuesta.getHayError());
		
	}
	
	
	@Test
	public void obtenerRentabilidadTotalNoHayBancaRetail() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    			
		//Then
		DetalleRentabilidadTotalDTO respuesta = analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, false);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(false, respuesta.getHayError());
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerRentabilidadTotalDAOException() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		
    	RentabilidadTotalInView rentabilidadInView = new RentabilidadTotalInView();
    	rentabilidadInView.setCarteraAConsultar("TOT");
    	rentabilidadInView.setPeriodo("30D");
    			
		when(rendimientoTenenciaDAO.obtenerRentabilidadTotal(Matchers.any(RentabilidadTotalRequestEntity.class))).thenThrow(DAOException.class);
	
		//Then
		DetalleRentabilidadTotalDTO respuesta = analisisCarteraBO.obtenerRentabilidadTotal(cliente, rentabilidadInView, true);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(true, respuesta.getHayError());
		
	}
	
	@Test
	public void obtenerFiltroCarteraRTLOK() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
			
		FiltroCarteraEntity filtroCartera = FiltroCarteraEntityMock.armarFiltroCarteraEntity();
		filtroCartera.getDatos().getResultado().setMultiseleccion("N");
		
		when(rendimientoTenenciaDAO.obtenerFiltroCartera(Matchers.any(ObtenerFiltroCarteraRequestEntity.class))).thenReturn(filtroCartera);
		
		//Then
		Respuesta<FiltroCarteraDTO> respuesta = analisisCarteraBO.obtenerFiltroCartera(cliente, TipoBancaEnum.BANCA_PERSONAL);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}
	
	@Test
	public void obtenerFiltroCarteraBPOK() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
			
		when(rendimientoTenenciaDAO.obtenerFiltroCartera(Matchers.any(ObtenerFiltroCarteraRequestEntity.class))).thenReturn(FiltroCarteraEntityMock.armarFiltroCarteraEntity());
		
		//Then
		Respuesta<FiltroCarteraDTO> respuesta = analisisCarteraBO.obtenerFiltroCartera(cliente, TipoBancaEnum.BANCA_PRIVADA);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerFiltroCarteraDAOException() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
			
		when(rendimientoTenenciaDAO.obtenerFiltroCartera(Matchers.any(ObtenerFiltroCarteraRequestEntity.class))).thenThrow(DAOException.class);
		
		//Then
		Respuesta<FiltroCarteraDTO> respuesta = analisisCarteraBO.obtenerFiltroCartera(cliente, TipoBancaEnum.BANCA_PERSONAL);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
	}
	
	@Test
	public void obtenerFiltroFechaRTLOK() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		FiltroPorFechaRequestView filtroFechaRequest = new FiltroPorFechaRequestView();
		filtroFechaRequest.setCarteraAConsultar("CTA");
		filtroFechaRequest.setEsBancaPrivada(Boolean.FALSE);	
		
		List<CuentaACView> listaCuentas = new ArrayList<CuentaACView>();
		CuentaACView cuentaView = new CuentaACView();
		cuentaView.setNumeroCuenta("123456");
		cuentaView.setSucursal("0");
		listaCuentas.add(cuentaView);
		filtroFechaRequest.setListaCuentas(listaCuentas);
		
		when(rendimientoTenenciaDAO.obtenerFiltroPorFecha(Matchers.any(ObtenerFiltroPorFechaRequestEntity.class)))
			.thenReturn(FiltroFechaEntityMock.armarFiltroFechaEntity());
		
		//Then
		Respuesta<FiltroPorFechaDTO> respuesta = analisisCarteraBO.obtenerFiltroPorFecha(cliente, filtroFechaRequest, TipoBancaEnum.BANCA_PERSONAL);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}	
	
	@Test
	public void obtenerFiltroFechaBPOK() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		FiltroPorFechaRequestView filtroFechaRequest = new FiltroPorFechaRequestView();
		filtroFechaRequest.setCarteraAConsultar("PAR");
		filtroFechaRequest.setEsBancaPrivada(Boolean.TRUE);		
		
		List<CuentaACView> listaCuentas = new ArrayList<CuentaACView>();
		CuentaACView cuentaView = new CuentaACView();
		cuentaView.setNumeroCuenta("123456");
		cuentaView.setSucursal("0");
		listaCuentas.add(cuentaView);
		filtroFechaRequest.setListaCuentas(listaCuentas);
		
		FiltroPorFechaEntity filtroFecha = FiltroFechaEntityMock.armarFiltroFechaEntity();
		filtroFecha.getDatos().getResultado().get(0).setFechaInicio("fechaPepeLolaPaco");
		
		when(rendimientoTenenciaDAO.obtenerFiltroPorFecha(Matchers.any(ObtenerFiltroPorFechaRequestEntity.class)))
			.thenReturn(filtroFecha);
		
		//Then
		Respuesta<FiltroPorFechaDTO> respuesta = analisisCarteraBO.obtenerFiltroPorFecha(cliente, filtroFechaRequest, TipoBancaEnum.BANCA_PRIVADA);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerFiltroFechaDAOException() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		FiltroPorFechaRequestView filtroFechaRequest = new FiltroPorFechaRequestView();
		filtroFechaRequest.setCarteraAConsultar("TOT");
		filtroFechaRequest.setEsBancaPrivada(Boolean.TRUE);		
		
		when(rendimientoTenenciaDAO.obtenerFiltroPorFecha(Matchers.any(ObtenerFiltroPorFechaRequestEntity.class)))
			.thenThrow(DAOException.class);
		
		//Then
		Respuesta<FiltroPorFechaDTO> respuesta = analisisCarteraBO.obtenerFiltroPorFecha(cliente, filtroFechaRequest, TipoBancaEnum.BANCA_PRIVADA);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
	}	
	
	@Test
	public void obtenerRentabilidadPeriodoRTLOK() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		RentabilidadPeriodoRequestView rentabilidadPeriodoRequestView = new RentabilidadPeriodoRequestView();
		rentabilidadPeriodoRequestView.setCarteraAConsultar("TOT");
		rentabilidadPeriodoRequestView.setTipoBancaEnum(TipoBancaEnum.BANCA_PERSONAL);		
		
		when(rendimientoTenenciaDAO.obtenerRentabilidadPeriodo(Matchers.any(RentabilidadPeriodoRequestEntity.class)))
			.thenReturn(armarRentabilidadPeriodoEntity());
		
		//Then
		Respuesta<RentabilidadPeriodoDTO> respuesta = analisisCarteraBO.obtenerRentabilidadPeriodo(cliente, rentabilidadPeriodoRequestView, TipoBancaEnum.BANCA_PERSONAL );
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}	
	
	@Test
	public void obtenerRentabilidadPeriodoBPOK() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		RentabilidadPeriodoRequestView rentabilidadPeriodoRequestView = new RentabilidadPeriodoRequestView();
		rentabilidadPeriodoRequestView.setCarteraAConsultar("TOT");
		rentabilidadPeriodoRequestView.setTipoBancaEnum(TipoBancaEnum.BANCA_PRIVADA);		
		rentabilidadPeriodoRequestView.setPeriodo("PER");
		rentabilidadPeriodoRequestView.setPeriodoFechaInicial("01-08-2018");
		rentabilidadPeriodoRequestView.setPeriodoFechaFin("24-08-2018");
		
		when(rendimientoTenenciaDAO.obtenerRentabilidadPeriodo(Matchers.any(RentabilidadPeriodoRequestEntity.class)))
			.thenReturn(armarRentabilidadPeriodoEntity());
		
		//Then
		Respuesta<RentabilidadPeriodoDTO> respuesta = analisisCarteraBO.obtenerRentabilidadPeriodo(cliente, rentabilidadPeriodoRequestView, TipoBancaEnum.BANCA_PRIVADA);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerRentabilidadPeriodoDAOException() throws DAOException {
		
		//When
		Cliente cliente = new Cliente();
		RentabilidadPeriodoRequestView rentabilidadPeriodoRequestView = new RentabilidadPeriodoRequestView();
		rentabilidadPeriodoRequestView.setCarteraAConsultar("TOT");
		rentabilidadPeriodoRequestView.setTipoBancaEnum(TipoBancaEnum.BANCA_PERSONAL);		
		
		when(rendimientoTenenciaDAO.obtenerRentabilidadPeriodo(Matchers.any(RentabilidadPeriodoRequestEntity.class)))
			.thenThrow(DAOException.class);
		
		//Then
		Respuesta<RentabilidadPeriodoDTO> respuesta = analisisCarteraBO.obtenerRentabilidadPeriodo(cliente, rentabilidadPeriodoRequestView, TipoBancaEnum.BANCA_PERSONAL);
		
		//Expected
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
	}
	
	@Test
	public void armarListaEscalaGraficoDistribucionRentabilidadOKCientos() {
		
		//When
		List<String> listaEscalas = analisisCarteraBO.armarListaEscalaGraficoDistribucionRentabilidad(new BigDecimal("150.0"));
		
		//Expected
    	Assert.assertNotNull(listaEscalas);
		
	}

	@Test
	public void armarListaEscalaGraficoDistribucionRentabilidadOKMiles1() {
		
		//When
		List<String> listaEscalas = analisisCarteraBO.armarListaEscalaGraficoDistribucionRentabilidad(new BigDecimal("1000.0"));
		
		//Expected
    	Assert.assertNotNull(listaEscalas);
		
	}
	
	@Test
	public void armarListaEscalaGraficoDistribucionRentabilidadOKMiles2() {
		
		//When
		List<String> listaEscalas = analisisCarteraBO.armarListaEscalaGraficoDistribucionRentabilidad(new BigDecimal("1500.0"));
		
		//Expected
    	Assert.assertNotNull(listaEscalas);
		
	}

	@Test
	public void armarListaEscalaGraficoDistribucionRentabilidadOKMillones1() {
		
		//When
		List<String> listaEscalas = analisisCarteraBO.armarListaEscalaGraficoDistribucionRentabilidad(new BigDecimal("1000000.0"));
		
		//Expected
    	Assert.assertNotNull(listaEscalas);
		
	}
	
	@Test
	public void armarListaEscalaGraficoDistribucionRentabilidadOKMillones2() {
		
		//When
		List<String> listaEscalas = analisisCarteraBO.armarListaEscalaGraficoDistribucionRentabilidad(new BigDecimal("1500000.0"));
		
		//Expected
    	Assert.assertNotNull(listaEscalas);
		
	}
	
	private DetalleRentabilidadTotalEntity armarEntity() {
		
		DetalleRentabilidadTotalEntity entity = new DetalleRentabilidadTotalEntity();
		DatosRespuestaRentabilidadTotal datos = new DatosRespuestaRentabilidadTotal();
		RentTotalPeriodoMoneda rentTotalPeriodoMoneda = new RentTotalPeriodoMoneda();
		
    	rentTotalPeriodoMoneda.setCodigoPeriodo("30D");
    	rentTotalPeriodoMoneda.setFechaFin("2018-06-16T00:00:00");
    	rentTotalPeriodoMoneda.setFechaInicio("2018-07-16T00:00:00");
    	rentTotalPeriodoMoneda.setFechaInfoDisponible("2018-07-16T00:00:00");
    	rentTotalPeriodoMoneda.setGuidError(null);
    	rentTotalPeriodoMoneda.setRentabilidadPesos(new BigDecimal("3212.0"));
    	rentTotalPeriodoMoneda.setRentabilidadDolares(null);
		
    	datos.setRentTotalPeriodoMoneda(rentTotalPeriodoMoneda);
    	entity.setDatos(datos);		
		
		return entity;
	}
			
	private RentabilidadPeriodoEntity armarRentabilidadPeriodoEntity() {
		
		RentabilidadPeriodoEntity rentabilidadPeriodoEntity = new RentabilidadPeriodoEntity();
		DatosRespuestaRentabilidadPeriodoEntity datosRespuestaRentabilidadPeriodoEntity = new DatosRespuestaRentabilidadPeriodoEntity();
    	RespuestaRentabilidadPeriodoEntity respuestaRentabilidadPeriodoEntity = RentabilidadPeriodoEntityMock.armarRentabilidadPeriodoEntityMock();
		datosRespuestaRentabilidadPeriodoEntity.setResultado(respuestaRentabilidadPeriodoEntity);
		rentabilidadPeriodoEntity.setDatos(datosRespuestaRentabilidadPeriodoEntity);
				
		return rentabilidadPeriodoEntity;
	}
	
	
}
