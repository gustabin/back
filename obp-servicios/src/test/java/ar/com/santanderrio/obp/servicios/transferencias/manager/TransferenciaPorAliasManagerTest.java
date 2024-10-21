/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.manager;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances.FundsMessageHelper;
import ar.com.santanderrio.obp.servicios.transferencias.dao.AlycsDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DatosTransferenciaDestino;
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
import org.mockito.runners.MockitoJUnitRunner;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.web.view.AyudaView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.TransferenciaPorAliasBOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.impl.TransferenciaPorAliasManagerImpl;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Manager tests
 * 
 * @author Manuel.Vargas B041299
 * @version 1
 */
@RunWith(MockitoJUnitRunner.class)
public class TransferenciaPorAliasManagerTest {

	/** The mock estadistica manager. */
	@Mock
	private EstadisticaManagerImpl mockEstadisticaManager;
	
	/** The respuesta factory. */
	@Spy
	private RespuestaFactory respuestaFactory;
	
	/** The mock mensaje manager. */
	@Mock
	private MensajeManager mockMensajeManager;
	
	@Mock
	private Cliente clienteMock;
	
	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;
	
	/** The sesion del cliente. */
	@Mock
	private SesionCliente sesionCliente;

	/** The transferencia view. */
	@Mock
	private TransferenciaView transferenciaView;
	
	@Mock
	private TransferenciaManager transferenciaManager;
	
	@InjectMocks
	private TransferenciaPorAliasManagerImpl transferenciaPorAliasManager;

	@Mock
	private TransferenciaPorAliasBOImpl transferenciaPorAliasBO;

	@Mock
	private AlycsDAO alycsDAO;

	@Mock
	private FundsMessageHelper fundsMessageHelper;
	
	Respuesta<TransferenciaDTO> respuestaTransferenciaDTO1;
	Respuesta<TransferenciaDTO> respuestaTransferenciaDTO2;
	Respuesta<TransferenciaDTO> respuestaTransferenciaDTO3;
	Respuesta<TransferenciaDTO> respuestaTransferenciaDTO4;
	
    /** The Constant PESOS. */
	private static final String PESOS = "Pesos";
	
    /** The Constant DOLARES. */
	private static final String DOLARES = "Dólares";
	
	private String estadoEsperadoOK = "OK";
	private String estadoEsperadoERR = "ERROR";
	private String estadoEsperadoWARN = "WARNING";
	
	private List<AyudaView> ayudaViewList;
	
	private Cliente cliente;

	List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
	
	String agent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
	
	/**
	 * Inits tests.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		ayudaViewList = new ArrayList<AyudaView>();
		AyudaView ayuda = new AyudaView("title","message");
		ayudaViewList.add(ayuda);
		
		cliente = new Cliente();
		Cuenta cuenta1 = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		cuenta1.setEstadoTarjetaCredito("01");
		cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		cuenta1.setTipoCuenta("01");
		cuenta1.setNroSucursal("152");
		cuenta1.setNroTarjetaCredito("4517660024736620");
		cuenta1.setNroCuentaProducto("0000000000639170");

		cuenta2.setEstadoTarjetaCredito("01");
		cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
		cuenta2.setTipoCuenta("02");
		cuenta2.setNroSucursal("152");
		cuenta2.setNroTarjetaCredito("4517660024736620");

		listaCuentas.add(cuenta1);
		listaCuentas.add(cuenta2);
		cliente.setCuentas(listaCuentas);
		
		respuestaTransferenciaDTO1 = new Respuesta<TransferenciaDTO>();
		respuestaTransferenciaDTO1.setEstadoRespuesta(EstadoRespuesta.OK);
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO.setHaciaCuentaPropia(true);
		transferenciaDTO.setHaciaOtroBanco(false);
		respuestaTransferenciaDTO1.setRespuesta(transferenciaDTO );
		respuestaTransferenciaDTO2 = new Respuesta<TransferenciaDTO>();
		respuestaTransferenciaDTO3 = new Respuesta<TransferenciaDTO>();
		respuestaTransferenciaDTO4 = new Respuesta<TransferenciaDTO>();
	}
	
	@Test
	public void solicitarNuevaTransferencia_PesosConAlias_OK() throws BusinessException, DAOException {
		String ALIASTEST = "ALIASTEST";
		Mockito.when(mockEstadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(true);
		Mockito.when(transferenciaView.getMoneda()).thenReturn(PESOS);
		transferenciaView.setAliasDestino("AliasTest");
		Mockito.when(transferenciaView.getAliasDestino()).thenReturn(ALIASTEST);
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(sesionParametros.getDatosTransferenciaDestino()).thenReturn(new DatosTransferenciaDestino());
		Mockito.when(clienteMock.getCuentas()).thenReturn(listaCuentas);
		Mockito.when(transferenciaManager.cargarVistaSolicitar (Matchers.any(TransferenciaView.class),
																Matchers.any(Cliente.class),
																Matchers.any(CuentasView.class),
																Matchers.any(TransferenciaDTO.class),
																Matchers.any(Boolean.class),
																Matchers.any(Boolean.class))).thenReturn(transferenciaView);
		Mockito.when(transferenciaManager.obtenerAyudasConTemplate()).thenReturn(ayudaViewList);
		Respuesta<TransferenciaView> respuesta;
		Mockito.when(transferenciaPorAliasBO.consultarDatosTitularidadExtendida(transferenciaView, cliente, agent)).thenReturn(respuestaTransferenciaDTO1);
		Mockito.when(alycsDAO.getCuitsAlycs()).thenReturn(new ArrayList<String>());
		//
		respuesta = transferenciaPorAliasManager.solicitarNuevaTransferencia(transferenciaView, agent);
		//
		Assert.assertNotNull(respuesta);
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(estadoEsperadoOK , respuesta.getEstadoRespuesta().toString());
	}

	@Test
	public void solicitarNuevaTransferenciaConAlias_Error_CLIENTE_SIN_CTAS_CON_MONEDA_INDICADA() throws BusinessException{
		Cliente clienteConCtasEnDOLARES = new Cliente();
		List<Cuenta> listaCuentasEnDOLARES = new ArrayList<Cuenta>();
		Cuenta cuenta1 = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		cuenta1.setEstadoTarjetaCredito("01");
		cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
		cuenta1.setTipoCuenta("04");
		cuenta1.setNroSucursal("152");
		cuenta1.setNroTarjetaCredito("4517660024736620");
		cuenta1.setNroCuentaProducto("0000000000639170");
		cuenta2.setEstadoTarjetaCredito("01");
		cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
		cuenta2.setTipoCuenta("03");
		cuenta2.setNroSucursal("152");
		cuenta2.setNroTarjetaCredito("4517660024736620");
		listaCuentasEnDOLARES.add(cuenta1);
		listaCuentasEnDOLARES.add(cuenta2);
		clienteConCtasEnDOLARES.setCuentas(listaCuentasEnDOLARES);
		Mensaje mensaje = new Mensaje();
		String msjCUENTA_SIN_CTAS_CON_MONEDA_INDICADA = "CUENTA_SIN_CTAS_CON_MONEDA_INDICADA";
		mensaje.setMensaje(msjCUENTA_SIN_CTAS_CON_MONEDA_INDICADA );
		String ALIASTEST = "ALIASTEST";
		Mockito.when(mockMensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		Mockito.when(mockEstadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(true);
		Mockito.when(transferenciaView.getMoneda()).thenReturn(PESOS);
		Mockito.when(transferenciaView.getAliasDestino()).thenReturn(ALIASTEST);
		Mockito.when(sesionCliente.getCliente()).thenReturn(clienteConCtasEnDOLARES);
		Mockito.when(sesionParametros.getDatosTransferenciaDestino()).thenReturn(new DatosTransferenciaDestino());
		Mockito.when(clienteMock.getCuentas()).thenReturn(listaCuentasEnDOLARES);
		Mockito.when(transferenciaManager.cargarVistaSolicitar (Matchers.any(TransferenciaView.class),
																Matchers.any(Cliente.class),
																Matchers.any(CuentasView.class),
																Matchers.any(TransferenciaDTO.class),
																Matchers.any(Boolean.class),
																Matchers.any(Boolean.class))).thenReturn(transferenciaView);
		Respuesta<TransferenciaView> respuesta;
		Mockito.when(transferenciaPorAliasBO.consultarDatosTitularidadExtendida(transferenciaView, clienteConCtasEnDOLARES, agent)).thenReturn(respuestaTransferenciaDTO1);
		//
		respuesta = transferenciaPorAliasManager.solicitarNuevaTransferencia(transferenciaView, agent);
		//
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(estadoEsperadoWARN , respuesta.getEstadoRespuesta().toString());
		Assert.assertEquals(TipoError.CLIENTE_NO_COINCIDE_MONEDA.getDescripcion() , respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
	}
	
	@Test
	public void solicitarNuevaTransferenciaConAlias_WARNING_enConsultaAlias() throws BusinessException, DAOException {
		String ALIASTEST = "ALIASTEST";
		String msjCUENTA_SIN_CTAS_CON_MONEDA_INDICADA = "CUENTA_SIN_CTAS_CON_MONEDA_INDICADA";
		respuestaTransferenciaDTO2.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuestaTransferenciaDTO2.setItemMensajeRespuesta(obtenerListaMensajesRespuesta(TipoError.WARNING, msjCUENTA_SIN_CTAS_CON_MONEDA_INDICADA));
		Mockito.when(mockEstadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(true);
		Mockito.when(transferenciaView.getMoneda()).thenReturn(PESOS);
		Mockito.when(transferenciaView.getAliasDestino()).thenReturn(ALIASTEST);
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(sesionParametros.getDatosTransferenciaDestino()).thenReturn(new DatosTransferenciaDestino());
		Mockito.when(clienteMock.getCuentas()).thenReturn(listaCuentas);
		Mockito.when(transferenciaManager.cargarVistaSolicitar (Matchers.any(TransferenciaView.class),
																Matchers.any(Cliente.class),
																Matchers.any(CuentasView.class),
																Matchers.any(TransferenciaDTO.class),
																Matchers.any(Boolean.class),
																Matchers.any(Boolean.class))).thenReturn(transferenciaView);
		Mockito.when(transferenciaManager.obtenerAyudasConTemplate()).thenReturn(ayudaViewList);
		Respuesta<TransferenciaView> respuesta;
		Mockito.when(transferenciaPorAliasBO.consultarDatosTitularidadExtendida(transferenciaView, cliente, agent)).thenReturn(respuestaTransferenciaDTO2);
		Mockito.when(alycsDAO.getCuitsAlycs()).thenReturn(new ArrayList<String>());
		//
		respuesta = transferenciaPorAliasManager.solicitarNuevaTransferencia(transferenciaView, agent);
		//
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(estadoEsperadoWARN , respuesta.getEstadoRespuesta().toString());
	}

	@Test
	public void solicitarNuevaTransferenciaConAlias_ERROR_enConsultaAlias() throws BusinessException{
		String ALIASTEST = "ALIASTEST";
		String msjCUENTA_SIN_CTAS_CON_MONEDA_INDICADA = "CUENTA_SIN_CTAS_CON_MONEDA_INDICADA";
		respuestaTransferenciaDTO3.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaTransferenciaDTO3.setItemMensajeRespuesta(obtenerListaMensajesRespuesta(TipoError.WARNING, msjCUENTA_SIN_CTAS_CON_MONEDA_INDICADA));
		Mockito.when(mockEstadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(true);
		Mockito.when(transferenciaView.getMoneda()).thenReturn(PESOS);
		Mockito.when(transferenciaView.getAliasDestino()).thenReturn(ALIASTEST);
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(sesionParametros.getDatosTransferenciaDestino()).thenReturn(new DatosTransferenciaDestino());
		Mockito.when(clienteMock.getCuentas()).thenReturn(listaCuentas);
		Mockito.when(transferenciaManager.cargarVistaSolicitar (Matchers.any(TransferenciaView.class),
																Matchers.any(Cliente.class),
																Matchers.any(CuentasView.class),
																Matchers.any(TransferenciaDTO.class),
																Matchers.any(Boolean.class),
																Matchers.any(Boolean.class))).thenReturn(transferenciaView);
		Mockito.when(transferenciaManager.obtenerAyudasConTemplate()).thenReturn(ayudaViewList);
		Respuesta<TransferenciaView> respuesta;
		Mockito.when(transferenciaPorAliasBO.consultarDatosTitularidadExtendida(transferenciaView, cliente, agent)).thenReturn(respuestaTransferenciaDTO3);
		//
		respuesta = transferenciaPorAliasManager.solicitarNuevaTransferencia(transferenciaView, agent);
		//
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(estadoEsperadoERR , respuesta.getEstadoRespuesta().toString());
	}

	
    /**
     * Obtener lista mensajes respuesta.
     * Util.
     * @param te the te
     * @param msj the msj
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerListaMensajesRespuesta(TipoError te, String msj){
        
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(te.ERROR_GENERICO.getDescripcion());
        item.setMensaje(msj);
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        items.add(item);
        return items;
        
    }
}
