package ar.com.santanderrio.obp.servicios.prestamos.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.SimuladorPrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dao.CalificacionCrediticiaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CalificacionCrediticiaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.DestinoPrestamoSeleccionView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.DestinoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dto.ConfiguracionPrestamoDTO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;

/**
 * The Class SimuladorPrestamoBOTest.
 *
 * @author mariano.g.pulera
 *
 */

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class SimuladorPrestamoBOTest {

	@InjectMocks
	private SimuladorPrestamoBOImpl simuladorPrestamoBo;
	
	@Mock
	private CalificacionCrediticiaDAO calificacionCrediticiaDao;
	
	@Mock
	private PrestamoBO prestamoBo;
	
	@Mock
	private DestinoPrestamoDAO destinoPrestamoDao;

	@Mock
	private PrestamoDAO prestamoDAO;
	
	@Mock
	private MensajeBO mensajeBo;
	
	
    @Before
    public void init() throws IllegalAccessException, InvocationTargetException {
    	simuladorPrestamoBo.setCantidadMaximaDias(90);
    	simuladorPrestamoBo.setCantidadMinimaDias(30);
    	simuladorPrestamoBo.getCantidadMaximaDias();
    	simuladorPrestamoBo.getCantidadMinimaDias();
    }
    
	     
	@Test
	public void obtenerConfiguracionSimulacionPrestamoOK() throws DAOException, BusinessException {
		
		//When
        Cliente cliente = mock(Cliente.class);
        Cuenta cuenta = mock(Cuenta.class);
        
        CalificacionCrediticiaOutEntity calificacionOut = new CalificacionCrediticiaOutEntity();
        calificacionOut.setCodigoInhabilitado("001");
        calificacionOut.setImporteDisponiblePrestamo("00001110000");
        
        PrestamoPermitidoOutEntity prestamoOut = new PrestamoPermitidoOutEntity();
        
		PrestamoPermitidoEntity infoPrestamo = new PrestamoPermitidoEntity();
		infoPrestamo.setMinCantCuotas("003");
		infoPrestamo.setMaxCantCuotas("036");
		infoPrestamo.setTpoTasa("F");
		infoPrestamo.setValorTasa("032000000");
		infoPrestamo.setMaxImpPrest("000010001100");
		infoPrestamo.setMinImpPrest("0000001100000");
		
		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoOut.setListaResult(listaInfoPrestamos);
        		
        when(calificacionCrediticiaDao.obtenerSituacionCrediticia(Matchers.any(Cuenta.class), Matchers.anyString())).thenReturn(calificacionOut);
        when(prestamoBo.obtenerInfoPrestamosPermitidos(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(prestamoOut);
        
		//Then
        ConfiguracionPrestamoDTO respuesta = simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(cliente, cuenta);
		
		//Expected
		Assert.assertNotNull(respuesta);
	}
	
	
	@Test
	public void obtenerConfiguracionSimulacionPrestamoOKLineaCrediticiaSinMontoPermitido() throws DAOException, BusinessException {
		
		//When
        Cliente cliente = mock(Cliente.class);
        Cuenta cuenta = mock(Cuenta.class);
        
        CalificacionCrediticiaOutEntity calificacionOut = new CalificacionCrediticiaOutEntity();
        calificacionOut.setCodigoInhabilitado("001");
        calificacionOut.setImporteDisponiblePrestamo("00000111000");
        
        PrestamoPermitidoOutEntity prestamoOut = new PrestamoPermitidoOutEntity();
        
		PrestamoPermitidoEntity infoPrestamo = new PrestamoPermitidoEntity();
		infoPrestamo.setMinCantCuotas("003");
		infoPrestamo.setMaxCantCuotas("036");
		infoPrestamo.setTpoTasa("F");
		infoPrestamo.setValorTasa("032000000");
		infoPrestamo.setMaxImpPrest("0000000009000");
		infoPrestamo.setMinImpPrest("000010001100");
		
		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoOut.setListaResult(listaInfoPrestamos);
        		
		Mensaje mensajeError = new Mensaje();
		mensajeError.setMensaje("Linea crediticia sin monto permitido");
		
        when(calificacionCrediticiaDao.obtenerSituacionCrediticia(Matchers.any(Cuenta.class), Matchers.anyString())).thenReturn(calificacionOut);
        when(prestamoBo.obtenerInfoPrestamosPermitidos(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(prestamoOut);
        when(mensajeBo.obtenerMensajePorCodigo("1140")).thenReturn(mensajeError);
        
		//Then
        ConfiguracionPrestamoDTO respuesta = simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(cliente, cuenta);
		
		//Expected
		Assert.assertNotNull(respuesta);
	}
	
	
	@Test(expected = BusinessException.class)
	public void obtenerConfiguracionSimulacionPrestamoErrorLineaCrediticiaNoHabilitada() throws DAOException, BusinessException {
		
		//When
        Cliente cliente = mock(Cliente.class);
        Cuenta cuenta = mock(Cuenta.class);
        
        CalificacionCrediticiaOutEntity calificacionOut = new CalificacionCrediticiaOutEntity();
        calificacionOut.setCodigoInhabilitado("002");
        calificacionOut.setImporteDisponiblePrestamo("00001110000");
        
        PrestamoPermitidoOutEntity prestamoOut = new PrestamoPermitidoOutEntity();
        
		PrestamoPermitidoEntity infoPrestamo = new PrestamoPermitidoEntity();
		infoPrestamo.setMinCantCuotas("003");
		infoPrestamo.setMaxCantCuotas("036");
		infoPrestamo.setTpoTasa("F");
		infoPrestamo.setValorTasa("032000000");
		infoPrestamo.setMaxImpPrest("000010001100");
		infoPrestamo.setMinImpPrest("0000001100000");
		
		List<PrestamoPermitidoEntity> listaInfoPrestamos = new ArrayList<PrestamoPermitidoEntity>();
		listaInfoPrestamos.add(infoPrestamo);
		prestamoOut.setListaResult(listaInfoPrestamos);
        
        when(calificacionCrediticiaDao.obtenerSituacionCrediticia(Matchers.any(Cuenta.class), Matchers.anyString())).thenReturn(calificacionOut);
        when(prestamoBo.obtenerInfoPrestamosPermitidos(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(prestamoOut);
        
		//Then
        ConfiguracionPrestamoDTO respuesta = simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(cliente, cuenta);
		
		//Expected
		Assert.assertNotNull(respuesta);
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(expected=BusinessException.class)
	public void obtenerConfiguracionSimulacionPrestamoErrorBusinessException() throws DAOException, BusinessException {
		
		//When
        Cliente cliente = mock(Cliente.class);
        Cuenta cuenta = mock(Cuenta.class);
        
        when(prestamoBo.obtenerInfoPrestamosPermitidos(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenThrow(BusinessException.class);
        
		//Then
        ConfiguracionPrestamoDTO respuesta = simuladorPrestamoBo.obtenerConfiguracionSimulacionPrestamo(cliente, cuenta);
		
		//Expected
		Assert.assertNull(respuesta);
	}
	
	
	@Test
	public void obtenerDestinoPrestamoOk() throws BusinessException, DAOException {
		
		//When
		List<DestinoPrestamo> destinosPrestamo = new ArrayList<DestinoPrestamo>();
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setProductoUG("01");
		destino.setSubproductoUG("0003");
		destino.setDivisaProductoUG("AAA");
		destino.setDestinoDeFondosUG("09AB");
		destino.setDescripcionUG("Automotores");
		destinosPrestamo.add(destino);
		
		when(destinoPrestamoDao.obtener()).thenReturn(destinosPrestamo);
		
		//Then
		List<DestinoPrestamoSeleccionView> listaDestinos = simuladorPrestamoBo.obtenerDestinosPrestamo("01", "0003");
		
		//Expected
		Assert.assertNotNull(listaDestinos);
		Assert.assertEquals("Automotores", listaDestinos.get(0).getDescripcion());
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(expected = BusinessException.class) 
	public void obtenerDestinoPrestamoErrorDAOException() throws BusinessException, DAOException {
		
		//When
		when(destinoPrestamoDao.obtener()).thenThrow(DAOException.class);
		
		//Then
		simuladorPrestamoBo.obtenerDestinosPrestamo("01", "0003");
	
	}
	
	@Test
	public void obtenerDestinoPrestamoErrorNoDevuelveNadaNoHayCoincidencias() throws BusinessException, DAOException {
		
		//When
		List<DestinoPrestamo> destinosPrestamo = new ArrayList<DestinoPrestamo>();
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setProductoUG("01");
		destino.setSubproductoUG("0003");
		destino.setDivisaProductoUG("AAA");
		destino.setDestinoDeFondosUG("09AB");
		destino.setDescripcionUG("Automotores");
		destinosPrestamo.add(destino);
		
		when(destinoPrestamoDao.obtener()).thenReturn(destinosPrestamo);
		
		//Then
		List<DestinoPrestamoSeleccionView> listaDestinos = simuladorPrestamoBo.obtenerDestinosPrestamo("02", "0004");
		
		//Expected
		Assert.assertEquals(new ArrayList<DestinoPrestamoSeleccionView>(), listaDestinos);
	}
	
	@Test
	public void obtenerDestinoPrestamoErrorNoDevuelveNadaHayUnaSolaCoincidencia() throws BusinessException, DAOException {
		
		//When
		List<DestinoPrestamo> destinosPrestamo = new ArrayList<DestinoPrestamo>();
		DestinoPrestamo destino = new DestinoPrestamo();
		destino.setProductoUG("01");
		destino.setSubproductoUG("0003");
		destino.setDivisaProductoUG("AAA");
		destino.setDestinoDeFondosUG("09AB");
		destino.setDescripcionUG("Automotores");
		destinosPrestamo.add(destino);
		
		when(destinoPrestamoDao.obtener()).thenReturn(destinosPrestamo);
		
		//Then
		List<DestinoPrestamoSeleccionView> listaDestinos = simuladorPrestamoBo.obtenerDestinosPrestamo("01", "0004");
		
		//Expected
		Assert.assertEquals(new ArrayList<DestinoPrestamoSeleccionView>(), listaDestinos);
	}
	
//	@Test
//	public void simularOk() throws BusinessException, DAOException {
//		
//		//When
//		Cliente cliente = mock(Cliente.class);
//		
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("1853");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		DestinoPrestamoSeleccionView motivoSeleccionado = new DestinoPrestamoSeleccionView();
//		motivoSeleccionado.setId("350016ARS35139");
//		solicitudSimulacion.setMotivoSeleccionado(motivoSeleccionado);
//		
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		
//		Cuenta cuenta = new Cuenta();
//		cuenta.setTipoCuentaSinUnificar("09");
//		cuenta.setNroCuentaProducto("0000000003602500");
//		cuenta.setNroSucursal("0000");		
//		cuenta.setNumeroPaquete("0000000345643200000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//				
//		PrestamoOutEntity prestamoOutEntity = new PrestamoOutEntity();
//		prestamoOutEntity.setCodigoRetornoExtendido("00000000");
//		
//		when(prestamoDAO.simularAdquirir(Matchers.any(Cliente.class), Matchers.any(PrestamoInEntity.class))).thenReturn(prestamoOutEntity);
//		when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuenta);
//		
//		//Then
//		PrestamoOutEntity prestamoOut = simuladorPrestamoBo.simular(cliente, solicitudSimulacion, configuracionPrestamoDto);
//
//		//Expected
//		Assert.assertNotNull(prestamoOut);
//
//	}
//
//	
//	@Test(expected = BusinessException.class) 
//	public void simularErrorDiaFueraDeRango() throws BusinessException, DAOException {
//		
//		//When
//		Cliente cliente = mock(Cliente.class);
//		
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("1853");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		DestinoPrestamoSeleccionView motivoSeleccionado = new DestinoPrestamoSeleccionView();
//        motivoSeleccionado.setId("350016ARS35139");
//        solicitudSimulacion.setMotivoSeleccionado(motivoSeleccionado);
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		
//		Cuenta cuenta = new Cuenta();
//		cuenta.setTipoCuentaSinUnificar("09");
//		cuenta.setNroCuentaProducto("0000000003602500");
//		cuenta.setNroSucursal("0000");		
//		cuenta.setNumeroPaquete("0000000345643200000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//				
//		PrestamoOutEntity prestamoOutEntity = new PrestamoOutEntity();
//		prestamoOutEntity.setCodigoRetornoExtendido("10000001");
//		
//		when(prestamoDAO.simularAdquirir(Matchers.any(Cliente.class), Matchers.any(PrestamoInEntity.class))).thenReturn(prestamoOutEntity);
//		when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuenta);
//		
//		//Then
//		simuladorPrestamoBo.simular(cliente, solicitudSimulacion, configuracionPrestamoDto);
//
//	}
//
//	
//	@Test(expected = BusinessException.class) 
//	public void simularErrorDiaNoHabil() throws BusinessException, DAOException {
//		
//		//When
//		Cliente cliente = mock(Cliente.class);
//		
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("1853");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		DestinoPrestamoSeleccionView motivoSeleccionado = new DestinoPrestamoSeleccionView();
//        motivoSeleccionado.setId("350016ARS35139");
//        solicitudSimulacion.setMotivoSeleccionado(motivoSeleccionado);
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		
//		Cuenta cuenta = new Cuenta();
//		cuenta.setTipoCuentaSinUnificar("09");
//		cuenta.setNroCuentaProducto("0000000003602500");
//		cuenta.setNroSucursal("0000");		
//		cuenta.setNumeroPaquete("0000000345643200000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//				
//		PrestamoOutEntity prestamoOutEntity = new PrestamoOutEntity();
//		prestamoOutEntity.setCodigoRetornoExtendido("10001306");
//		
//		when(prestamoDAO.simularAdquirir(Matchers.any(Cliente.class), Matchers.any(PrestamoInEntity.class))).thenReturn(prestamoOutEntity);
//		when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuenta);
//		
//		//Then
//		simuladorPrestamoBo.simular(cliente, solicitudSimulacion, configuracionPrestamoDto);
//
//	}
//	
//	
//	@Test(expected = BusinessException.class) 
//	public void simularErrorCantidadCuotas() throws BusinessException, DAOException {
//		
//		//When
//		Cliente cliente = mock(Cliente.class);
//		
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("1853");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		DestinoPrestamoSeleccionView motivoSeleccionado = new DestinoPrestamoSeleccionView();
//        motivoSeleccionado.setId("350016ARS35139");
//        solicitudSimulacion.setMotivoSeleccionado(motivoSeleccionado);
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		
//		Cuenta cuenta = new Cuenta();
//		cuenta.setTipoCuentaSinUnificar("09");
//		cuenta.setNroCuentaProducto("0000000003602500");
//		cuenta.setNroSucursal("0000");		
//		cuenta.setNumeroPaquete("0000000345643200000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//				
//		PrestamoOutEntity prestamoOutEntity = new PrestamoOutEntity();
//		prestamoOutEntity.setCodigoRetornoExtendido("16010023");
//		
//		when(prestamoDAO.simularAdquirir(Matchers.any(Cliente.class), Matchers.any(PrestamoInEntity.class))).thenReturn(prestamoOutEntity);
//		when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuenta);
//		
//		//Then
//		simuladorPrestamoBo.simular(cliente, solicitudSimulacion, configuracionPrestamoDto);
//
//	}
//	
//	
//	@Test(expected = BusinessException.class) 
//	public void simularErrorGenerico() throws BusinessException, DAOException {
//		
//		//When
//		Cliente cliente = mock(Cliente.class);
//		
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("1853");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		DestinoPrestamoSeleccionView motivoSeleccionado = new DestinoPrestamoSeleccionView();
//        motivoSeleccionado.setId("350016ARS35139");
//        solicitudSimulacion.setMotivoSeleccionado(motivoSeleccionado);
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		
//		Cuenta cuenta = new Cuenta();
//		cuenta.setTipoCuentaSinUnificar("09");
//		cuenta.setNroCuentaProducto("0000000003602500");
//		cuenta.setNroSucursal("0000");		
//		cuenta.setNumeroPaquete("0000000345643200000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//				
//		PrestamoOutEntity prestamoOutEntity = new PrestamoOutEntity();
//		prestamoOutEntity.setCodigoRetornoExtendido("1000334306");
//		
//		when(prestamoDAO.simularAdquirir(Matchers.any(Cliente.class), Matchers.any(PrestamoInEntity.class))).thenReturn(prestamoOutEntity);
//		when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuenta);
//		
//		//Then
//		simuladorPrestamoBo.simular(cliente, solicitudSimulacion, configuracionPrestamoDto);
//
//	}
//	
//	
//	@SuppressWarnings("unchecked")
//	@Test(expected = BusinessException.class) 
//	public void simularErrorGenericoDAOException() throws BusinessException, DAOException {
//		
//		//When
//		Cliente cliente = mock(Cliente.class);
//		
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("1853");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		DestinoPrestamoSeleccionView motivoSeleccionado = new DestinoPrestamoSeleccionView();
//        motivoSeleccionado.setId("350016ARS35139");
//        solicitudSimulacion.setMotivoSeleccionado(motivoSeleccionado);
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		
//		Cuenta cuenta = new Cuenta();
//		cuenta.setTipoCuentaSinUnificar("09");
//		cuenta.setNroCuentaProducto("0000000003602500");
//		cuenta.setNroSucursal("0000");		
//		cuenta.setNumeroPaquete("0000000345643200000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//				
//		PrestamoOutEntity prestamoOutEntity = new PrestamoOutEntity();
//		prestamoOutEntity.setCodigoRetornoExtendido("1000334306");
//		
//		when(prestamoDAO.simularAdquirir(Matchers.any(Cliente.class), Matchers.any(PrestamoInEntity.class))).thenThrow(DAOException.class);
//		when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuenta);
//		
//		//Then
//		simuladorPrestamoBo.simular(cliente, solicitudSimulacion, configuracionPrestamoDto);
//
//	}
//	
//	
//	@Test(expected = BusinessException.class) 
//	public void simularErrorGenericoTimeOutException() throws BusinessException, DAOException {
//		
//		//When
//		Cliente cliente = mock(Cliente.class);
//		
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("1853");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		DestinoPrestamoSeleccionView motivoSeleccionado = new DestinoPrestamoSeleccionView();
//        motivoSeleccionado.setId("350016ARS35139");
//        solicitudSimulacion.setMotivoSeleccionado(motivoSeleccionado);
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		
//		Cuenta cuenta = new Cuenta();
//		cuenta.setTipoCuentaSinUnificar("09");
//		cuenta.setNroCuentaProducto("0000000003602500");
//		cuenta.setNroSucursal("0000");		
//		cuenta.setNumeroPaquete("0000000345643200000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//				
//		DAOException daoException = new DAOException("iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ");
//		
//		PrestamoOutEntity prestamoOutEntity = new PrestamoOutEntity();
//		prestamoOutEntity.setCodigoRetornoExtendido("1000334306");
//		
//		when(prestamoDAO.simularAdquirir(Matchers.any(Cliente.class), Matchers.any(PrestamoInEntity.class))).thenThrow(daoException);
//		when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuenta);
//		
//		//Then
//		simuladorPrestamoBo.simular(cliente, solicitudSimulacion, configuracionPrestamoDto);
//
//	}
//	
//	@Test
//	public void adquirirPrestamoOk() throws BusinessException, DAOException {
//		
//		//When
//		Cliente cliente = mock(Cliente.class);
//		
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("1853");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		DestinoPrestamoSeleccionView motivoSeleccionado = new DestinoPrestamoSeleccionView();
//        motivoSeleccionado.setId("350016ARS35139");
//        solicitudSimulacion.setMotivoSeleccionado(motivoSeleccionado);
//		
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		
//		Cuenta cuenta = new Cuenta();
//		cuenta.setTipoCuentaSinUnificar("09");
//		cuenta.setNroCuentaProducto("0000000003602500");
//		cuenta.setNroSucursal("0000");		
//		cuenta.setNumeroPaquete("0000000345643200000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//				
//		PrestamoOutEntity prestamoOutEntity = new PrestamoOutEntity();
//		prestamoOutEntity.setCodigoRetornoExtendido("00000000");
//		
//		when(prestamoDAO.simularAdquirir(Matchers.any(Cliente.class), Matchers.any(PrestamoInEntity.class))).thenReturn(prestamoOutEntity);
//		when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuenta);
//		
//		//Then
//		PrestamoOutEntity prestamoOut = simuladorPrestamoBo.adquirirPrestamo(cliente, solicitudSimulacion, configuracionPrestamoDto);
//
//		//Expected
//		Assert.assertNotNull(prestamoOut);
//
//	}
//	
//	
//	@Test
//	public void chequearRangosOk() throws BusinessException {
//		
//		//When
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("1853");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		calificacionCrediticaOutEntity.setCodigoInhabilitado("001");
//		calificacionCrediticaOutEntity.setPorcentajeLimitePrestamo("60000000");
//		calificacionCrediticaOutEntity.setImporteDisponiblePrestamo("600000000");
//		
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		prestamoPermitidoEntity.setMaxImpPrest("600000000");
//		prestamoPermitidoEntity.setMinImpPrest("010000000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//		
//		
//		//Then
//		List<ItemMensajeRespuesta> errores = simuladorPrestamoBo.chequearRangos(solicitudSimulacion, configuracionPrestamoDto);
//		
//		//Expected
//		Assert.assertNotNull(errores);
//		
//	}
//	
//	
//	@Test
//	public void chequearRangosWarningImporteMenorAlMinimoPermitido() throws BusinessException {
//		
//		//When
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("200");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		calificacionCrediticaOutEntity.setCodigoInhabilitado("001");
//		calificacionCrediticaOutEntity.setPorcentajeLimitePrestamo("60000000");
//		calificacionCrediticaOutEntity.setImporteDisponiblePrestamo("600000000");
//		
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		prestamoPermitidoEntity.setMaxImpPrest("600000000");
//		prestamoPermitidoEntity.setMinImpPrest("010000000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//		
//		Mensaje mensajeWarning = new Mensaje();
//		mensajeWarning.setMensaje("ERROR IMPORTE SOLICITADO MENOR AL MINIMO DISPONIBLE");
//		
//		when(mensajeBo.obtenerMensajePorCodigo("1348")).thenReturn(mensajeWarning);
//		
//		//Then
//		List<ItemMensajeRespuesta> errores = simuladorPrestamoBo.chequearRangos(solicitudSimulacion, configuracionPrestamoDto);
//		
//		//Expected
//		Assert.assertNotNull(errores);
//		Assert.assertEquals("ERROR IMPORTE SOLICITADO MENOR AL MINIMO DISPONIBLE", errores.get(0).getMensaje());
//		
//	}
//	
//	
//	@Test
//	public void chequearRangosWarningImporteMayorAlMaximoPermitido() throws BusinessException {
//		
//		//When
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("75000");
//		solicitudSimulacion.setCuotaSeleccionada("4");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		calificacionCrediticaOutEntity.setCodigoInhabilitado("001");
//		calificacionCrediticaOutEntity.setPorcentajeLimitePrestamo("60000000");
//		calificacionCrediticaOutEntity.setImporteDisponiblePrestamo("600000000");
//		
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		prestamoPermitidoEntity.setMaxImpPrest("006000000");
//		prestamoPermitidoEntity.setMinImpPrest("010000000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//		
//		Mensaje mensajeWarning = new Mensaje();
//		mensajeWarning.setMensaje("ERROR IMPORTE SOLICITADO MAYOR AL MAXIMO DISPONIBLE");
//		
//		when(mensajeBo.obtenerMensajePorCodigo("1348")).thenReturn(mensajeWarning);
//		
//		//Then
//		List<ItemMensajeRespuesta> errores = simuladorPrestamoBo.chequearRangos(solicitudSimulacion, configuracionPrestamoDto);
//		
//		//Expected
//		Assert.assertNotNull(errores);
//		Assert.assertEquals("ERROR IMPORTE SOLICITADO MAYOR AL MAXIMO DISPONIBLE", errores.get(0).getMensaje());
//		
//	}
//	
//	@Test
//	public void chequearRangosWarningCuotasElegidasMenoresALasMinimasPermitidas() throws BusinessException {
//		
//		//When
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("7000");
//		solicitudSimulacion.setCuotaSeleccionada("2");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		calificacionCrediticaOutEntity.setCodigoInhabilitado("001");
//		calificacionCrediticaOutEntity.setPorcentajeLimitePrestamo("60000000");
//		calificacionCrediticaOutEntity.setImporteDisponiblePrestamo("600000000");
//		
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		prestamoPermitidoEntity.setMaxImpPrest("600000000");
//		prestamoPermitidoEntity.setMinImpPrest("010000000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//		
//		Mensaje mensajeWarning = new Mensaje();
//		mensajeWarning.setMensaje("ERROR CANTIDAD DE CUOTAS SELECCIONADAS INVALIDA");
//		
//		when(mensajeBo.obtenerMensajePorCodigo("1349")).thenReturn(mensajeWarning);
//		
//		//Then
//		List<ItemMensajeRespuesta> errores = simuladorPrestamoBo.chequearRangos(solicitudSimulacion, configuracionPrestamoDto);
//		
//		//Expected
//		Assert.assertNotNull(errores);
//		Assert.assertEquals("ERROR CANTIDAD DE CUOTAS SELECCIONADAS INVALIDA", errores.get(0).getMensaje());
//		
//	}
//	
//	
//	@Test
//	public void chequearRangosWarningCuotasElegidasMayoresALasMaximasPermitidas() throws BusinessException {
//		
//		//When
//		SolicitudSimulacionView solicitudSimulacion = new SolicitudSimulacionView();
//		solicitudSimulacion.setImporteSeleccionado("7000");
//		solicitudSimulacion.setCuotaSeleccionada("40");
//		solicitudSimulacion.setFechaSeleccionada("03/04/2017");
//		
//		
//		CalificacionCrediticiaOutEntity calificacionCrediticaOutEntity = new CalificacionCrediticiaOutEntity();
//		calificacionCrediticaOutEntity.setCodigoInhabilitado("001");
//		calificacionCrediticaOutEntity.setPorcentajeLimitePrestamo("60000000");
//		calificacionCrediticaOutEntity.setImporteDisponiblePrestamo("600000000");
//		
//		PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
//		PrestamoPermitidoEntity prestamoPermitidoEntity = new PrestamoPermitidoEntity();
//		prestamoPermitidoEntity.setMinCantCuotas("003");
//		prestamoPermitidoEntity.setMaxCantCuotas("036");
//		prestamoPermitidoEntity.setTpoTasa("F");
//		prestamoPermitidoEntity.setValorTasa("0000000320000");
//		prestamoPermitidoEntity.setMarcaEmpl("S");
//		prestamoPermitidoEntity.setNroCuentaProve("0000000000000000");
//		prestamoPermitidoEntity.setMaxImpPrest("600000000");
//		prestamoPermitidoEntity.setMinImpPrest("010000000");
//		
//		List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();
//		listaResult.add(prestamoPermitidoEntity);
//		prestamoPermitidoOutEntity.setListaResult(listaResult);
//		ConfiguracionPrestamoDTO configuracionPrestamoDto = new ConfiguracionPrestamoDTO(calificacionCrediticaOutEntity, prestamoPermitidoOutEntity);
//		
//		Mensaje mensajeWarning = new Mensaje();
//		mensajeWarning.setMensaje("ERROR CANTIDAD DE CUOTAS SELECCIONADAS INVALIDA");
//		
//		when(mensajeBo.obtenerMensajePorCodigo("1349")).thenReturn(mensajeWarning);
//		
//		//Then
//		List<ItemMensajeRespuesta> errores = simuladorPrestamoBo.chequearRangos(solicitudSimulacion, configuracionPrestamoDto);
//		
//		//Expected
//		Assert.assertNotNull(errores);
//		Assert.assertEquals("ERROR CANTIDAD DE CUOTAS SELECCIONADAS INVALIDA", errores.get(0).getMensaje());
//		
//	}
//	
	
	//CORREGIR, DEPENDIENDO DE LA HORA Y EL DIA EL TEST FUNCIONA O NO
	@Test
	@Ignore
	public void chequearHorarioOperacionEnHora() throws IllegalAccessException {
		//When
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.MINUTE, -5);
	    String startTime = simpleDateFormat.format(cal.getTime());
	    cal.add(Calendar.MINUTE, 10);
	    String endTime = simpleDateFormat.format(cal.getTime());
		FieldUtils.writeDeclaredField(simuladorPrestamoBo, "horaInicioOperaciones", startTime, true);
		FieldUtils.writeDeclaredField(simuladorPrestamoBo, "horaFinOperaciones", endTime, true);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("<p>No podemos realizar esta operación en este momento. " + 
		"El horario de simulación y alta de  <b>Súper Préstamo </b>es de <b>Lunes a Viernes de {0} a  {1} hs.</b></p>");
		
		when(mensajeBo.obtenerMensajePorCodigo("1400")).thenReturn(mensaje);
		
		//Then
		Boolean respuesta = simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion();
		
		//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(true, respuesta);
	}
	
	
	//@Test
	public void chequearHorarioOperacionFueraDeHora() throws IllegalAccessException {
		//When
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 5);
        String startTime = simpleDateFormat.format(cal.getTime());
        cal.add(Calendar.MINUTE, 5);
        String endTime = simpleDateFormat.format(cal.getTime());
		FieldUtils.writeDeclaredField(simuladorPrestamoBo, "horaInicioOperaciones", startTime, true);
		FieldUtils.writeDeclaredField(simuladorPrestamoBo, "horaFinOperaciones", endTime, true);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("FUERA DE HORA");
		
		when(mensajeBo.obtenerMensajePorCodigo("1400")).thenReturn(mensaje);
		
		//Then
		Boolean respuesta = simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion();
		
		//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(false, respuesta);
	}
	
}