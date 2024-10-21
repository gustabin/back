package ar.com.santanderrio.obp.servicios.inversiones.titulos.manager;


import static org.mockito.Mockito.when;

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

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.TitulosBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.NuevaLicitacionDTOResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosCompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaEspeciesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.EspeciesEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RespuestaSimulacionERI;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.TitulosManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.BuscadorEspeciesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.BusquedaOrdenesCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteLicitacionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConsultarOrdenesPorCuentaOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.CuentaTituloParaLicitarView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewResponse;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaRequestView;


//@Ignore
@RunWith(MockitoJUnitRunner.class)
public class TitulosManagerTest {
	
	
    /** The plazo fijo  BO. */
    @Mock
    TitulosBO licitacionesBO;
    
	
	/** Titulos Manager. */
    @InjectMocks
    private TitulosManagerImpl titulosManagerImpl = new TitulosManagerImpl();
	
	
    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;
    

    /** The fondo BO. */
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    /** The fondo BO. */
    @Mock
    private CuentaManager cuentaManager;
    
    /** The fondo BO. */
    @Mock
    private MensajeBO mensajeBO;
    
    @Spy 
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();


    /**
     * nuevaLicitacion OK
     */
    
	@SuppressWarnings("unchecked")
	@Test
	public void nuevaLicitacion_OK() {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
		NuevaLicitacionViewReq request = new NuevaLicitacionViewReq();
		request.setCuentasTitulo(cuentasTitulo);
		when(sesionCliente.getCliente()).thenReturn(new Cliente());
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class)))
				.thenReturn(respuestaEstadistica);
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);

		NuevaLicitacionDTOResponse nuevaLicitacionDTOResponse = new NuevaLicitacionDTOResponse();
		List<CuentaTituloParaLicitarView> cuentasTitulos = new ArrayList<CuentaTituloParaLicitarView>();
		CuentaTituloParaLicitarView cuentaTit = new CuentaTituloParaLicitarView();
		cuentasTitulos.add(cuentaTit);
		nuevaLicitacionDTOResponse.setCuentasTitulo(cuentasTitulos);
		Respuesta<NuevaLicitacionDTOResponse> rtaBo = new Respuesta<NuevaLicitacionDTOResponse>();
		rtaBo.setEstadoRespuesta(EstadoRespuesta.OK);
		rtaBo.setItemMensajeRespuesta(itemMensajeRespuestas);
		rtaBo.setRespuesta(nuevaLicitacionDTOResponse);
		
		
		when(licitacionesBO.nuevaLicitacion(Matchers.any(Cliente.class), Matchers.any(NuevaLicitacionViewReq.class),Matchers.any(ArrayList.class), Matchers.any(ArrayList.class)))
				.thenReturn(rtaBo);
		
		Respuesta<CuentasView> respuestaCuentaManager = new Respuesta<CuentasView>();
        respuestaCuentaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        CuentasView cuentasView = new CuentasView();
        respuestaCuentaManager.setRespuesta(cuentasView);
        List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
        cuentasView.setCuentas(cuentas);
        CuentasAdhesionDebitoView cuenta = new CuentasAdhesionDebitoView();
        cuentas.add(cuenta);
        when(cuentaManager.getCuentasSaldoPorMoneda(Matchers.any(String.class))).thenReturn(respuestaCuentaManager);

		Respuesta<NuevaLicitacionViewResponse> respuesta = titulosManagerImpl.nuevaLicitacion(request);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void verComprobanteLicitacion_OK() {
			
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class)))
				.thenReturn(respuestaEstadistica);			
		Respuesta<ComprobanteLicitacionOutView> rtaFactory = new Respuesta<ComprobanteLicitacionOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(AccionesAlVencimientoOutView.class))).thenReturn(rtaFactory);
					
		Respuesta<ComprobanteLicitacionOutView> respuesta = titulosManagerImpl.verComprobanteLicitacion();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void verComprobanteReversaVentaPrivada(){
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);
		Respuesta<Void> rtaFactory = new Respuesta<Void>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class))).thenReturn(rtaFactory);
		ReversarCompraVentaRequestView request = new ReversarCompraVentaRequestView();
		request.setTipoBanca("BP");
		request.setTipoOperacion("V");
		Respuesta<Void> rtaComprobante = titulosManagerImpl.verComprobanteReversa(request);
		Assert.assertEquals(EstadoRespuesta.OK, rtaComprobante.getEstadoRespuesta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void verComprobanteReversaCompraPrivada(){
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);
		Respuesta<Void> rtaFactory = new Respuesta<Void>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class))).thenReturn(rtaFactory);
		ReversarCompraVentaRequestView request = new ReversarCompraVentaRequestView();
		request.setTipoBanca("BP");
		request.setTipoOperacion("C");
		Respuesta<Void> rtaComprobante = titulosManagerImpl.verComprobanteReversa(request);
		Assert.assertEquals(EstadoRespuesta.OK, rtaComprobante.getEstadoRespuesta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void verComprobanteReversaVentaPersonal(){
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);
		Respuesta<Void> rtaFactory = new Respuesta<Void>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class))).thenReturn(rtaFactory);
		ReversarCompraVentaRequestView request = new ReversarCompraVentaRequestView();
		request.setTipoBanca("BR");
		request.setTipoOperacion("V");
		Respuesta<Void> rtaComprobante = titulosManagerImpl.verComprobanteReversa(request);
		Assert.assertEquals(EstadoRespuesta.OK, rtaComprobante.getEstadoRespuesta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void verComprobanteReversaCompraPersonal(){
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);
		Respuesta<Void> rtaFactory = new Respuesta<Void>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class))).thenReturn(rtaFactory);
		ReversarCompraVentaRequestView request = new ReversarCompraVentaRequestView();
		request.setTipoBanca("BR");
		request.setTipoOperacion("C");
		Respuesta<Void> rtaComprobante = titulosManagerImpl.verComprobanteReversa(request);
		Assert.assertEquals(EstadoRespuesta.OK, rtaComprobante.getEstadoRespuesta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarLicitacionesRTL() {

		Cliente cliente = new Cliente();

		List<Cuenta> cuentasOperativas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();

		Respuesta<ConsultarOrdenesOutDTO> response = new Respuesta<ConsultarOrdenesOutDTO>();
		ConsultarOrdenesOutDTO valor = new ConsultarOrdenesOutDTO();
		response.setEstadoRespuesta(EstadoRespuesta.OK);
		response.setRespuesta(valor);
		List<ConsultarOrdenesDTO> list = new ArrayList<ConsultarOrdenesDTO>();
		ConsultarOrdenesDTO item = new ConsultarOrdenesDTO();

		for (int i = 0; i < 2; i++) {
			cuenta.setNroCuentaProducto("136654" + i);
			cuentasOperativas.add(cuenta);
			item.setCuentaTitulo("136654" + i);
			list.add(item);
		}
		response.getRespuesta().setList(list);
		cliente.setCuentasRetail(cuentasOperativas);

		when(licitacionesBO.consultarLicitaciones(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class),
				Matchers.anyBoolean())).thenReturn(response);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		Mensaje respuestaMensaje = new Mensaje();
		respuestaMensaje.setMensaje("Mensaje minimo maximo mockeado");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(respuestaMensaje);

		Respuesta<Void> rtaFactory = new Respuesta<Void>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(Class.class)))
				.thenReturn(rtaFactory);
		when(licitacionesBO.consultarLicitaciones(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class),
				Matchers.anyBoolean())).thenReturn(response);

		Respuesta<ConsultarOrdenesPorCuentaOutView> respuestaBO = titulosManagerImpl.consultarLicitacionesRTL();
		Assert.assertNotNull(respuestaBO);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void busquedaOrdenesCompra(){
		
		BusquedaOrdenesCompra request = new BusquedaOrdenesCompra();
		
		Respuesta<DatosConsultaEspeciesResponse> respBo = new Respuesta<DatosConsultaEspeciesResponse>();
		respBo.setEstadoRespuesta(EstadoRespuesta.OK);
		DatosConsultaEspeciesResponse especie = new DatosConsultaEspeciesResponse();
		respBo.setRespuesta(especie);
		
		 List<EspeciesEntity> listaEspecies = new ArrayList<EspeciesEntity>();
		 EspeciesEntity item = new EspeciesEntity();
		 
		 for(int i = 0 ; i < 2 ; i++ ){
			 item.setCodigoAmigable("mock"+i);
			 item.setCodigoEspecieCajaValores("mock"+i);
			 item.setCodigoEspecieRossi("mock"+i);
			 item.setDescripcionEspecie("mock"+i);
			 item.setEmisionMonedaEspecie("mock"+i);
			 item.setInstrumento("mock"+i);
			 item.setInstrumentoCodigo("mock"+i);
			 item.setPermiteMonto("mock"+i);
			 item.setRequierePrecioLimite("mock"+i);
			 item.setTipoEspecie("mock"+i);
			 listaEspecies.add(item);
		 }
		 especie.setListaEspecies(listaEspecies);
		 
			Mensaje respuestaMensaje = new Mensaje();
			respuestaMensaje.setMensaje("Mensaje mokiado");
			when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(respuestaMensaje);
			
		
		when(licitacionesBO.busquedaOrdenesCompra(Matchers.any(BusquedaOrdenesCompra.class),Matchers.any(Cliente.class),Matchers.any(TipoBancaEnum.class))).thenReturn(respBo);
		
		Respuesta<Void> rtaFactory = new Respuesta<Void>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(Class.class)))
				.thenReturn(rtaFactory);
		
		Respuesta<BuscadorEspeciesView> respuesta  = titulosManagerImpl.busquedaOrdenesCompra(request, TipoBancaEnum.BANCA_PERSONAL);
		Assert.assertNotNull(respuesta);
		
	}

	
	@SuppressWarnings("unchecked")
	@Test
	public void simularOrdenCompra(){		
		
		ConfirmarOrdenCompraRequest request = new ConfirmarOrdenCompraRequest();
		request.setAceptarContrato(true);
		
		Respuesta<CompraVtaTitulosResponse> respuesta = new Respuesta<CompraVtaTitulosResponse>();	
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		CompraVtaTitulosResponse xx = new CompraVtaTitulosResponse();
		DatosCompraVtaTitulosResponse datos = new DatosCompraVtaTitulosResponse();
		respuesta.setRespuesta(xx);
		respuesta.getRespuesta().setDatos(datos);
		respuesta.getRespuesta().getDatos().setRespuestaSimulacionERI(new RespuestaSimulacionERI());
		respuesta.getRespuesta().getDatos().getRespuestaSimulacionERI().setIdEvaluacion(7818);
		respuesta.getRespuesta().getDatos().getRespuestaSimulacionERI().setDisclaimer("<Mensaje><CantidadDeDisclaimers>1</CantidadDeDisclaimers><Disclaimers><Disclaimer><Titulo></Titulo><Text>Que el presente es un instrumento financiero complejo que puede resultar de dif?cil comprensi?n y cuya adquisici?n no se considera adecuada para inversores minoristas.</Text></Disclaimer></Disclaimers></Mensaje>");
		respuesta.getRespuesta().getDatos().getRespuestaSimulacionERI().setDisclaimerCumplimiento( "<Mensaje><CantidadDeDisclaimers>0</CantidadDeDisclaimers><Disclaimers></Disclaimers></Mensaje>");
		respuesta.getRespuesta().getDatos().getRespuestaSimulacionERI().setCabecera("MOCK");
		respuesta.getRespuesta().getDatos().getRespuestaSimulacionERI().setPie("MockPie");
		
		
		
		when(licitacionesBO.confirmarOrdenCompra(Matchers.any(ConfirmarOrdenCompraRequest.class), Matchers.any(Cliente.class),  Matchers.anyString(), Matchers.any(TipoBancaEnum.class))).thenReturn(respuesta);
		Respuesta<Void> rtaFactory = new Respuesta<Void>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(Class.class)))
				.thenReturn(rtaFactory);
		
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(Class.class),Matchers.anyList())).thenReturn(rtaFactory);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Error");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje );
		
		for(int i = 0 ; i<2 ; i++){
			respuesta.getRespuesta().getDatos().setCodigoRespERI(i);
			if(i==0){
				for(int x = 1 ; x<3 ; x++){
					respuesta.getRespuesta().getDatos().getRespuestaSimulacionERI().setTipoDisclaimer(x);
					Respuesta<ConfirmarOrdenCompraResponse> respuestafinal  = titulosManagerImpl.simularOrdenCompra(request, TipoBancaEnum.BANCA_PERSONAL);
					Assert.assertNotNull(respuestafinal);					
				}
			}else{
				Respuesta<ConfirmarOrdenCompraResponse> respuestafinal  = titulosManagerImpl.simularOrdenCompra(request, TipoBancaEnum.BANCA_PERSONAL);
				Assert.assertNotNull(respuestafinal);
			}

		}	
	}
	
}
