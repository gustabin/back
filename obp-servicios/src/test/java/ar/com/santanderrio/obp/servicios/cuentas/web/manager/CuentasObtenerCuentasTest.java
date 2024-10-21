package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.impl.LegalBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.impl.ConsultarSucursalesBOImpl;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.AliasCuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.SaldosConsolidadosCuentaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.SelectorCuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CuentasObtenerCuentasTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasObtenerCuentasTest {

    /** The cuenta manager. */
    @InjectMocks
    private CuentaManagerImpl cuentaManager = new CuentaManagerImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The cuentas service. */
    @Mock
    private CuentaBO cuentaBO;

    /** The session detalle cuentas. */
    @Mock
    private SessionDetalleCuentas sessionDetalleCuentas;
    
    /** The session MOvimientos cuentas. */
    @Mock
    private SessionMovimientos SessionMovimientos;
    /** The alias cuenta. */
    @Mock
    private AliasCuentaBO aliasCuentaBO = new AliasCuentaBOImpl();
    /** The alias cuenta. */
    
    @Mock
    private AliasCuentaManager aliasCuenta;
    /** The alias cuenta. */
    @Mock
    private ConsultarSucursalesBO consultarSucursalesBO = new ConsultarSucursalesBOImpl();

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	/** The mensaje BO. */
	@Mock
    private MensajeBO mensajeBO;
    
	/** The legal BO. */
	@Mock
	private LegalBO legalBO = new LegalBOImpl();
	
    /**
     * Gets the cuenta test.
     *
     * @return the cuenta test
     * @throws BusinessException the business exception
     */
    @Test
    public void getCuentaTest() throws BusinessException{
    	Respuesta<List<ResumenDetalleCuenta>> respuestaCuentas = new Respuesta<List<ResumenDetalleCuenta>>();
    	List<ResumenDetalleCuenta> listResumenDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
    	ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
    	
    	Cliente cliente = new Cliente();
    	cliente.setNombre("Juan");
    	cliente.setApellido1("Lopez");
    	cliente.setApellido2("Perez");
    	cliente.setDni("2323");
    	cliente.setTipoDocumento("N");
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
    	respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        resumenDetalleCuenta.setNroSucursal("000");
        resumenDetalleCuenta.setNroCuentaProducto("1234567");
        resumenDetalleCuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString());
        resumenDetalleCuenta.setCliente(cliente);
        listResumenDetalleCuenta.add(resumenDetalleCuenta);
        respuestaCuentas.setRespuesta(listResumenDetalleCuenta);
        respuestaCuentas.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
    	
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Cualquier mensaje");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
		when(cuentaBO.obtenerInfoCuentas(Matchers.any(Cliente.class))).thenReturn(respuestaCuentas);
		SaldosConsolidadosCuentaDTO saldosConsolidadosDTO = new SaldosConsolidadosCuentaDTO();
		Respuesta<SaldosConsolidadosCuentaDTO> respuesta = new Respuesta<SaldosConsolidadosCuentaDTO>();
		respuesta.setRespuesta(saldosConsolidadosDTO);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		when(cuentaBO.obtenerSaldoConsolidadoCliente(listResumenDetalleCuenta)).thenReturn(respuesta);
		Respuesta<SelectorCuentasView> resp = cuentaManager.getCuentas("","");
    	assertNotNull(resp);
    	
    	
    }
    
    
    /**
     * Gets the cuentas service test.
     *
     * @return the cuentas service test
     * @throws BusinessException
     *             the service exception
     * @throws DAOException 
     */
    @Test
    public void getcuentaBOTest() throws BusinessException, DAOException {

    	Mensaje mensaje = new Mensaje();
    	mensaje.setMensaje("Cualquier mensaje");
    	Cliente cliente = new Cliente();
    	cliente.setNombre("Juan");
    	cliente.setApellido1("Lopez");
    	cliente.setApellido2("Perez");
    	cliente.setDni("2323");
    	cliente.setTipoDocumento("N");
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        List<ResumenDetalleCuenta> listResumenDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
        Respuesta<List<ResumenDetalleCuenta>> respuestaCuentas = new Respuesta<List<ResumenDetalleCuenta>>();
        respuestaCuentas.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        Respuesta<Integer> cuentaSeleccionada = new Respuesta<Integer>();
        List<ItemMensajeRespuesta> itemsSeleccionada = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        Respuesta<SaldosConsolidadosCuentaDTO> saldoConsolidadosCuentaDTO = new  Respuesta<SaldosConsolidadosCuentaDTO>();
        SaldosConsolidadosCuentaDTO saldo = new SaldosConsolidadosCuentaDTO();
        saldoConsolidadosCuentaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        saldo.setSaldoPesos("0");
        saldo.setSaldoPesosValue(new BigDecimal(0));
        saldo.setSaldoDolares("0");
        saldo.setSaldoDolaresValue(new BigDecimal(0));
        saldoConsolidadosCuentaDTO.setRespuesta(saldo);
        respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentas.setRespuesta(listResumenDetalleCuenta);
        resumenDetalleCuenta.setNroSucursal("000");
        resumenDetalleCuenta.setNroCuentaProducto("1234567");
        resumenDetalleCuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString());
        resumenDetalleCuenta.setCliente(cliente);
        listResumenDetalleCuenta.add(resumenDetalleCuenta);
        cliente.setNombre("James Douglas");
        cliente.setApellido1("Morrison");
        cliente.setTipoDocumento("N");
        cliente.setDni("12345678");
        cuentaSeleccionada.setRespuesta(0);
        itemsSeleccionada.add(itemMensajeRespuesta);
        Respuesta<DetalleCBUView> detalleCBU = new Respuesta<DetalleCBUView>();
        detalleCBU.setEstadoRespuesta(EstadoRespuesta.OK);
        detalleCBU.setRespuesta(new DetalleCBUView());
        
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setNroDocumento("32323232333");
        when(aliasCuentaBO.obtenerDocumentoValidoDTO(cliente)).thenReturn(detalleDocumentoDTO);
        when(aliasCuentaBO.obtenerCuitPorDetalle(detalleDocumentoDTO)).thenReturn("23-32323232-3");

        Respuesta<Sucursal> respSucursal = new Respuesta<Sucursal>();
        Sucursal sucursal = new Sucursal();
        sucursal.setDescripcion("TRIBUNALES - 033");
        respSucursal.setEstadoRespuesta(EstadoRespuesta.OK);
        respSucursal.setRespuesta(sucursal);
        when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(respSucursal);
        
        when(aliasCuenta.obtenerAliasCBU(Matchers.anyString(),Matchers.anyString())).thenReturn(detalleCBU);
        when(cuentaBO.obtenerInfoCuentas(Matchers.any(Cliente.class))).thenReturn(respuestaCuentas);
        when(cuentaBO.obtenerCuentaSeleccionada(respuestaCuentas)).thenReturn(cuentaSeleccionada);
        when(cuentaBO.obtenerSaldoConsolidadoCliente(listResumenDetalleCuenta)).thenReturn(saldoConsolidadosCuentaDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<SelectorCuentasView> respuesta = cuentaManager.getCuentas("","");

        assertNotNull(respuesta);
        assertTrue(respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK));
        assertNotNull(respuesta.getRespuesta());
        assertNotNull(respuesta.getRespuesta().getCuentas());
        assertTrue(respuesta.getRespuesta().getCuentas().size() > 0);

    }

    /**
     * Gets the cuentas service estado error test.
     *
     * @return the cuentas service estado error test
     * @throws BusinessException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getCuentaBOEstadoErrorTest() throws BusinessException {

        Cliente cliente = new Cliente();
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        Respuesta<List<ResumenDetalleCuenta>> respuestaCuentas = new Respuesta<List<ResumenDetalleCuenta>>();
        Respuesta<Integer> cuentaSeleccionada = new Respuesta<Integer>();
        List<ItemMensajeRespuesta> itemsRepuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();

        respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaCuentas.setItemMensajeRespuesta(itemsRepuesta);
        respuestaCuentas.setRespuestaVacia(Boolean.TRUE);
        resumenDetalleCuenta.setNroSucursal("000");
        resumenDetalleCuenta.setNroCuentaProducto("1234567");
        resumenDetalleCuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString());
        resumenDetalleCuenta.setCliente(cliente);
        cliente.setNombre("James Douglas");
        cliente.setApellido1("Morrison");
        cliente.setTipoDocumento("N");
        cliente.setDni("12345678");
        cuentaSeleccionada.setRespuesta(0);
        itemsRepuesta.add(itemMensajeRespuesta);

        when(cuentaBO.obtenerInfoCuentas(Matchers.any(Cliente.class))).thenReturn(respuestaCuentas);
        when(cuentaBO.obtenerCuentaSeleccionada((Respuesta<List<ResumenDetalleCuenta>>) Matchers.any(List.class)))
                .thenReturn(cuentaSeleccionada);
        Respuesta<SelectorCuentasView> respuesta = cuentaManager.getCuentas("","");

        assertNotNull(respuesta);
        assertTrue(respuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR));
        assertTrue(respuesta.isRespuestaVacia());
        assertNotNull(respuesta.getItemsMensajeRespuesta());
        verify(estadisticaManager, Mockito.atLeastOnce()).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Gets the cuentas service con item error test.
     *
     * @return the cuentas service con item error test
     * @throws BusinessException
     *             the service exception
     * @throws DAOException 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getcuentaBOConItemErrorTest() throws BusinessException, DAOException {

        Respuesta<SaldosConsolidadosCuentaDTO> saldoConsolidadosCuentaDTO = new  Respuesta<SaldosConsolidadosCuentaDTO>();
        SaldosConsolidadosCuentaDTO saldo = new SaldosConsolidadosCuentaDTO();
        saldoConsolidadosCuentaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        saldo.setSaldoPesos("0");
        saldo.setSaldoPesosValue(new BigDecimal(0));
        saldo.setSaldoDolares("0");
        saldo.setSaldoDolaresValue(new BigDecimal(0));
        saldoConsolidadosCuentaDTO.setRespuesta(saldo);
        
        Cliente cliente = new Cliente();
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        List<ResumenDetalleCuenta> listResumenDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
        Respuesta<List<ResumenDetalleCuenta>> respuestaCuentas = new Respuesta<List<ResumenDetalleCuenta>>();
        Respuesta<Integer> cuentaSeleccionada = new Respuesta<Integer>();
        List<ItemMensajeRespuesta> itemsSeleccionada = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        ItemMensajeRespuesta itemMensajeRespuesta2 = new ItemMensajeRespuesta();
        ItemMensajeRespuesta itemMensajeRespuesta3 = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        List<ItemMensajeRespuesta> itemsRespuesta = new ArrayList<ItemMensajeRespuesta>();

        respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentas.setRespuesta(listResumenDetalleCuenta);
        respuestaCuentas.setItemMensajeRespuesta(items);
        listResumenDetalleCuenta.add(resumenDetalleCuenta);
        resumenDetalleCuenta.setNroSucursal("000");
        resumenDetalleCuenta.setNroCuentaProducto("1234567");
        resumenDetalleCuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString());
        resumenDetalleCuenta.setCliente(cliente);
        cliente.setNombre("James Douglas");
        cliente.setApellido1("Morrison");
        cliente.setTipoDocumento("N");
        cliente.setDni("12345678");
        cuentaSeleccionada.setRespuesta(0);
        itemsSeleccionada.add(itemMensajeRespuesta);
        itemMensajeRespuesta2.setTipoError(TipoError.ERROR_ITEM_CUENTA_SELECCIONADA.getDescripcion());
        items.add(itemMensajeRespuesta2);
        cuentaSeleccionada.setItemMensajeRespuesta(items);
        itemMensajeRespuesta3.setTipoError(TipoError.ERROR_OBTENER_FAVORITO.getDescripcion());
        itemsRespuesta.add(itemMensajeRespuesta3);
        Respuesta<DetalleCBUView> detalleCBU = new Respuesta<DetalleCBUView>();
        detalleCBU.setEstadoRespuesta(EstadoRespuesta.OK);
        detalleCBU.setRespuesta(new DetalleCBUView());
        when(aliasCuenta.obtenerAliasCBU(Matchers.anyString(),Matchers.anyString())).thenReturn(detalleCBU);
        when(cuentaBO.obtenerInfoCuentas(Matchers.any(Cliente.class))).thenReturn(respuestaCuentas);
        when(cuentaBO.obtenerCuentaSeleccionada((Respuesta<List<ResumenDetalleCuenta>>) Matchers.any(List.class))).thenReturn(cuentaSeleccionada);
        when(cuentaBO.obtenerSaldoConsolidadoCliente(listResumenDetalleCuenta)).thenReturn(saldoConsolidadosCuentaDTO);

        when(sesionCliente.getItemsRespuesta()).thenReturn(itemsRespuesta);
        
        when(sesionCliente.getCliente()).thenReturn(cliente);

        
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setNroDocumento("32323232333");
        when(aliasCuentaBO.obtenerDocumentoValidoDTO(Matchers.any(Cliente.class))).thenReturn(detalleDocumentoDTO);
        when(aliasCuentaBO.obtenerCuitPorDetalle(detalleDocumentoDTO)).thenReturn("23-32323232-3");

        Respuesta<Sucursal> respSucursal = new Respuesta<Sucursal>();
        Sucursal sucursal = new Sucursal();
        sucursal.setDescripcion("TRIBUNALES - 033");
        respSucursal.setEstadoRespuesta(EstadoRespuesta.OK);
        respSucursal.setRespuesta(sucursal);
        when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(respSucursal);
		
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Error, Error!");
        respuestaMensaje.setRespuesta(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<SelectorCuentasView> respuesta = cuentaManager.getCuentas("","");

        assertNotNull(respuesta);
        assertNotNull(respuesta.getItemsMensajeRespuesta());
        assertTrue(respuesta.getItemsMensajeRespuesta().size() > 0);
        assertTrue(respuesta.getItemsMensajeRespuesta().get(0).getTipoError().equals(TipoError.ERROR_ITEM_CUENTA_SELECCIONADA.getDescripcion()));
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Gets the cuentas service exception test.
     *
     * @return the cuentas service exception test
     * @throws BusinessException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getcuentaBOExceptionTest() throws BusinessException {
    	
    	Cliente cliente = new Cliente();
    	cliente.setNombre("Juan");
    	cliente.setApellido1("Lopez");
    	cliente.setApellido2("Perez");
    	cliente.setDni("2323");
    	cliente.setTipoDocumento("N");
        when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Error, Error!");
        respuestaMensaje.setRespuesta(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        when(cuentaBO.obtenerInfoCuentas(Matchers.any(Cliente.class))).thenThrow(BusinessException.class);
        Respuesta<SelectorCuentasView> respuesta = cuentaManager.getCuentas("","");

        assertNotNull(respuesta);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

    }

    /**
     * Gets the cuentas runtime exception test.
     *
     * @return the cuentas runtime exception test
     * @throws BusinessException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getCuentasRuntimeExceptionTest() throws BusinessException {

    	Cliente cliente = new Cliente();
    	cliente.setNombre("Juan");
    	cliente.setApellido1("Lopez");
    	cliente.setApellido2("Perez");
    	cliente.setDni("2323");
    	cliente.setTipoDocumento("N");
    	
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Error, Error!");
        respuestaMensaje.setRespuesta(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerInfoCuentas(Matchers.any(Cliente.class))).thenThrow(RuntimeException.class);
        Respuesta<SelectorCuentasView> respuesta = cuentaManager.getCuentas("","");

        assertNotNull(respuesta);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

    }

    /**
     * Gets the item cuenta error.
     *
     * @return the item cuenta error
     * @throws BusinessException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getItemCuentaError() throws BusinessException {
        Cliente cliente = new Cliente();
        
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Error, Error!");
        respuestaMensaje.setRespuesta(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        Respuesta<List<ResumenDetalleCuenta>> respuestaCuentas = new Respuesta<List<ResumenDetalleCuenta>>();
        Respuesta<Integer> cuentaSeleccionada = new Respuesta<Integer>();
        List<ResumenDetalleCuenta> listResumenDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
        
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_ITEM_CUENTA.getDescripcion());
        itemMensajeRespuesta.setTag("cuentas[000-123456/7]");
        items.add(itemMensajeRespuesta);
        respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentas.setItemMensajeRespuesta(items);

        resumenDetalleCuenta.setNroSucursal("000");
        resumenDetalleCuenta.setNroCuentaProducto("1234567");
        resumenDetalleCuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString());
        resumenDetalleCuenta.setCliente(cliente);
        cliente.setNombre("James Douglas");
        cliente.setApellido1("Morrison");
        cliente.setTipoDocumento("N");
        cliente.setDni("12345678");

        listResumenDetalleCuenta.add(resumenDetalleCuenta);
        listResumenDetalleCuenta.add(resumenDetalleCuenta);
        respuestaCuentas.setRespuesta(listResumenDetalleCuenta);
        cuentaSeleccionada.setRespuesta(0);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<DetalleCBUView> detalleCBU = new Respuesta<DetalleCBUView>();
        detalleCBU.setEstadoRespuesta(EstadoRespuesta.WARNING);
        detalleCBU.setRespuesta(new DetalleCBUView());
        when(aliasCuenta.obtenerAliasCBU(Matchers.anyString(),Matchers.anyString())).thenReturn(detalleCBU);
        when(cuentaBO.obtenerInfoCuentas(Matchers.any(Cliente.class))).thenReturn(respuestaCuentas);
        when(cuentaBO.obtenerCuentaSeleccionada((Respuesta<List<ResumenDetalleCuenta>>) Matchers.any(List.class)))
                .thenReturn(cuentaSeleccionada);
        Respuesta<SaldosConsolidadosCuentaDTO> respuestaSaldosConsolidados = new Respuesta<SaldosConsolidadosCuentaDTO>();
        when(cuentaBO.obtenerSaldoConsolidadoCliente((List<ResumenDetalleCuenta>) Matchers.any(List.class)))
                .thenReturn(respuestaSaldosConsolidados);        
        Respuesta<Sucursal> respuestaSucursal = new Respuesta<Sucursal>();
        when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(respuestaSucursal);        
        
        Respuesta<SelectorCuentasView> respuesta = cuentaManager.getCuentas("","");

        assertNotNull(respuesta);
        assertNotNull(respuesta.getItemsMensajeRespuesta());
        assertEquals(respuesta.getRespuesta().getCuentas().get(0).getSaldoPesos(), "-");
        assertEquals(respuesta.getRespuesta().getCuentas().get(0).getSaldoDolares(), "-");
        assertTrue(respuesta.getItemsMensajeRespuesta().size() > 0);
        assertTrue(respuesta.getItemsMensajeRespuesta().get(0).getTipoError()
                .equals(TipoError.ERROR_ITEM_CUENTA.getDescripcion()));
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

}
