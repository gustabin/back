package ar.com.santanderrio.obp.servicios.monedero.manager;


import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.BanelcoOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenDesafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.bo.AltaTagMonederoBO;
import ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO;
import ar.com.santanderrio.obp.servicios.monedero.dto.AltaTag;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.DatosAltaTagMonederoEntity;
import ar.com.santanderrio.obp.servicios.monedero.service.AltaTagMonederoService;
import ar.com.santanderrio.obp.servicios.monedero.web.manager.impl.AltaTagMonederoManagerImpl;
import ar.com.santanderrio.obp.servicios.monedero.web.view.AltaTagRSAReqView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.AltaTagRSAView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosParaActivacionView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteConfirmadoInOutView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteConfirmadoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteResponseView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;

/**
 * The Class AltaTagMonederoManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class AltaTagMonederoManagerTest {

    /** Alta Tag Monedero Manager. */
	@Spy
    @InjectMocks
    private AltaTagMonederoManagerImpl altaTagMonederoManager = new AltaTagMonederoManagerImpl();
    
    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;
    
    /** The Mensaje Manager. */
    @Mock
    private MensajeManager mensajeManager;
    
    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /** The Cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;
    
    /** The alta Tag Monedero BO. */
    @Mock
    private AltaTagMonederoBO altaTagMonederoBO;
    
    /** The legal BO. */
    @Mock
    private LegalBO legalBO;
    
    /** The sesion Cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    /** The datos Solicitante BO. */
    @Mock
    private DatosSolicitanteBO datosSolicitanteBO;
    
    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;
    
    /** The alta Tag Monedero Service. */
    @Mock
    private AltaTagMonederoService altaTagMonederoService;
    
    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;
    
    /** The estados rsa. */
    @Mock
    Map<OperacionesRSAEnum, Boolean> estadosRsa;
    
    /** The autentificacion Manager. */
    @Mock
    private AutentificacionManager autentificacionManager;

    @Mock
    private DesafioOperacionRSA<DatosSolicitanteConfirmadoInOutView> desafioOperacionRSA;

    @Before
    public void init()  {
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Un mensaje");
        MockitoAnnotations.initMocks(this);
        when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensaje);
    }
    
    @Test
    public void generarComprobanteAltaTagMonedero(){
        
    	Respuesta<Reporte> out = new Respuesta<Reporte>();
    	Reporte reporte = new Reporte();
    	reporte.setTipoArchivo(TipoArchivoEnum.PDF);
		out.setRespuesta(reporte);
    	
    	Mockito.when(altaTagMonederoService.generarComprobanteAltaTagMonedero(Matchers.any(ComprobanteAltaTagMonederoView.class))).thenReturn(out);
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	
    	ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView = new ComprobanteAltaTagMonederoView();
		Respuesta<ReporteView> respuesta = altaTagMonederoManager.generarComprobanteAltaTagMonedero(comprobanteAltaTagMonederoView );
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals("application/pdf", respuesta.getRespuesta().getTipoArchivo());
    }
    
    @Test
    public void activarMonederoTagError(){
    	
    	Respuesta<ResultadoTransaccion> out = new Respuesta<ResultadoTransaccion>();
    	
    	Mockito.when(altaTagMonederoService.activarMonederoTag(Matchers.any(DatosParaActivacionView.class), Matchers.any(Cliente.class))).thenReturn(out);
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	
    	DatosParaActivacionView datosParaActivacionView = new DatosParaActivacionView();
		Cliente cliente = new Cliente();
		Respuesta<ComprobanteActivacionTagMonederoView> respuesta = altaTagMonederoManager.activarMonederoTag(datosParaActivacionView, cliente);
	
		Assert.assertNotNull(respuesta);
    }
    
    @Test
    public void activarMonederoTagOK() throws DAOException{
    	
    	Respuesta<ResultadoTransaccion> out = new Respuesta<ResultadoTransaccion>();
    	out.setEstadoRespuesta(EstadoRespuesta.OK);
    	ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
    	resultadoTransaccion.setCodigoRespuesta(1);
    	resultadoTransaccion.setFechaTransaccion(new Date());
    	resultadoTransaccion.setMensajeError("MENSAJE ERROR");
    	resultadoTransaccion.setNumeroComprobante("9988877");
		out.setRespuesta(resultadoTransaccion );
    	
    	Cuenta cuenta = new Cuenta();
    	cuenta.setNroTarjetaCredito("456");
    	
    	Mockito.when(altaTagMonederoService.activarMonederoTag(Matchers.any(DatosParaActivacionView.class), Matchers.any(Cliente.class))).thenReturn(out);
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(cuentaBO.obtenerCuentaPorId(Matchers.any(Cliente.class), Matchers.any(String.class))).thenReturn(cuenta);
    	Mockito.when(legalBO.obtenerLegal(Matchers.any(String.class))).thenReturn("");
    	
    	DatosParaActivacionView datosParaActivacionView = new DatosParaActivacionView();
		Cliente clienteIn = new Cliente();
		Respuesta<ComprobanteActivacionTagMonederoView> respuesta = altaTagMonederoManager.activarMonederoTag(datosParaActivacionView, clienteIn);
	
		Assert.assertNotNull(respuesta);
    }
    
    @Test
    public void generarComprobanteActivacionTagMonedero(){
    	
    	Respuesta<Reporte> out = new Respuesta<Reporte>();
    	Reporte reporte = new Reporte();
    	reporte.setTipoArchivo(TipoArchivoEnum.PDF);
		out.setRespuesta(reporte);
		
    	Mockito.when(altaTagMonederoService.generarComprobanteActivacionTagMonedero(Matchers.any(ComprobanteActivacionTagMonederoView.class))).thenReturn(out);
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);

    	ComprobanteActivacionTagMonederoView comprobanteActivacionTagMonederoView = new ComprobanteActivacionTagMonederoView();
		Respuesta<ReporteView> respuesta = altaTagMonederoManager.generarComprobanteActivacionTagMonedero(comprobanteActivacionTagMonederoView);	
		Assert.assertNotNull(respuesta);
		Assert.assertEquals("application/pdf", respuesta.getRespuesta().getTipoArchivo());
    }
    
    @Test
    public void validarOperacionRSA() throws IllegalAccessException{
    	
        FieldUtils.writeField(altaTagMonederoManager, "valorDesafioMonedero", 3, true);

        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setId(9876L);
        cuenta.setSucursalPaquete("123");
        cuenta.setNroCuentaProducto("12345678901234567890");
        cuenta.setNroCuentaProducto("12345678901234567890");
        cuenta.setTipoCuenta("05");
        cuenta.setNroTarjetaCredito("12345678");
        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");

        Respuesta<AutentificacionDTO> out = new Respuesta<AutentificacionDTO>();
        out.setEstadoRespuesta(EstadoRespuesta.OK);
        
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        Mockito.when(sesionParametros.getRsaEstado()).thenReturn(estadosRsa);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(out);

        Mockito.doReturn(StringUtils.EMPTY).when(altaTagMonederoManager).horarioBancario();
        
		AltaTag altaTag = new AltaTag();
		altaTag.setNroCuenta("9876");
		Respuesta<AltaTagRSAView> respuesta = altaTagMonederoManager.validarOperacionRSA(altaTag);
		
		Assert.assertNotNull(respuesta);
    }

    @Test
    public void validarMetodoSeguridadWarning(){
    	
    	Respuesta<AutentificacionDTO> out = new Respuesta<AutentificacionDTO>();
    	AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
    	autentificacionDTO.setReintentosAgotados(false);
		out.setRespuesta(autentificacionDTO);
		out.setEstadoRespuesta(EstadoRespuesta.WARNING);
    	
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje warning");
    	
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(autentificacionManager.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(out);
		Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	AltaTagRSAReqView altaTagRSAReqView = new AltaTagRSAReqView();
    	CoordenadasOperacionDTO coordenadasOperacion = new CoordenadasOperacionDTO();
		altaTagRSAReqView.setCoordenadasOperacion(coordenadasOperacion);
    	
		Respuesta<AltaTagRSAReqView> respuesta = altaTagMonederoManager.validarMetodoSeguridad(altaTagRSAReqView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "ERROR_DESAFIO");
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getMensaje(), "mensaje warning");

		
    }
    
    @Test
    public void validarMetodoSeguridadOK(){
    	
    	Respuesta<AutentificacionDTO> out = new Respuesta<AutentificacionDTO>();
    	AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
    	autentificacionDTO.setReintentosAgotados(false);
		out.setRespuesta(autentificacionDTO);
		out.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(autentificacionManager.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(out);
    	
    	AltaTagRSAReqView altaTagRSAReqView = new AltaTagRSAReqView();
    	CoordenadasOperacionDTO coordenadasOperacion = new CoordenadasOperacionDTO();
		altaTagRSAReqView.setCoordenadasOperacion(coordenadasOperacion);
    	
		Respuesta<AltaTagRSAReqView> respuesta = altaTagMonederoManager.validarMetodoSeguridad(altaTagRSAReqView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarMetodoSeguridadError(){
    	
    	Respuesta<AutentificacionDTO> out = new Respuesta<AutentificacionDTO>();
    	AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
    	autentificacionDTO.setReintentosAgotados(false);
		out.setRespuesta(autentificacionDTO);
		out.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje error");
    	
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(autentificacionManager.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(out);
		Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	AltaTagRSAReqView altaTagRSAReqView = new AltaTagRSAReqView();
    	CoordenadasOperacionDTO coordenadasOperacion = new CoordenadasOperacionDTO();
		altaTagRSAReqView.setCoordenadasOperacion(coordenadasOperacion);
    	
		Respuesta<AltaTagRSAReqView> respuesta = altaTagMonederoManager.validarMetodoSeguridad(altaTagRSAReqView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getMensaje(), "mensaje error");
    }
    
    @Test
    @Ignore
    public void ejecutarAltaTagMonederoRsaFueraHorario() throws IllegalAccessException{
    	
    	FieldUtils.writeField(altaTagMonederoManager, "horaHastaALTA", "8:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "horaDesdeALTA", "8:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "valorDesafioMonedero", 1, true);
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        
        Respuesta<Object> respuestaDatosSolicitanteResponseViewError= new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaDatosSolicitanteResponseViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatosSolicitanteResponseViewError.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
        Respuesta<AutentificacionDTO> respuestaAutentificacion = new Respuesta<AutentificacionDTO>();
        respuestaAutentificacion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaAutentificacion.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatosSolicitanteResponseViewError);
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(respuestaAutentificacion);

        
    	DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoInOutView = new DatosSolicitanteConfirmadoInOutView();
		Respuesta<DatosSolicitanteConfirmadoInOutView> respuesta = altaTagMonederoManager.ejecutarAltaTagMonederoRsa(datosSolicitanteConfirmadoInOutView);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        
    }
    
    @Test
    public void ejecutarAltaTagMonederoRsa() throws IllegalAccessException{
    	
    	FieldUtils.writeField(altaTagMonederoManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "horaDesdeALTA", "8:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "valorDesafioMonedero", 3, true);
        
        Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
        autentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Cuenta cuentaPorId = new Cuenta();
        cuentaPorId.setNroTarjetaCredito("12345678901234567890");
        
        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setProductoAltair("43");
        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
        
        Respuesta<ComprobanteAltaTagMonederoView> out = new Respuesta<ComprobanteAltaTagMonederoView>();
        ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView = new ComprobanteAltaTagMonederoView();
        DatosSolicitanteConfirmadoView datosSolicitanteConfirmadoView = new DatosSolicitanteConfirmadoView();
		comprobanteAltaTagMonederoView.setDatosSolicitanteConfirmadoView(datosSolicitanteConfirmadoView );
		out.setRespuesta(comprobanteAltaTagMonederoView );
		out.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(autentificacion);
		Mockito.when(cuentaBO.obtenerCuentaPorId(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(cuentaPorId);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(altaTagMonederoService.ejecutarAltaTagMonedero(Matchers.any(DatosAltaTagMonederoEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);


    	DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoInOutView = new DatosSolicitanteConfirmadoInOutView();
    	datosSolicitanteConfirmadoInOutView.setImporteSeleccionado("12345678");
    	datosSolicitanteConfirmadoInOutView.setLimiteSeleccionado("123456789");

    	Respuesta<DatosSolicitanteConfirmadoInOutView> respuestaRSA = new Respuesta<DatosSolicitanteConfirmadoInOutView>();
    	respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
    	respuestaRSA.setRespuesta(datosSolicitanteConfirmadoInOutView);
    	when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(DatosSolicitanteConfirmadoInOutView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
    	
    	Mockito.doReturn(StringUtils.EMPTY).when(altaTagMonederoManager).horarioBancario();
		Respuesta<DatosSolicitanteConfirmadoInOutView> respuesta = altaTagMonederoManager.ejecutarAltaTagMonederoRsa(datosSolicitanteConfirmadoInOutView);
         
        Assert.assertNotNull(respuesta);
        
    }
    
    @Test
    public void ejecutarAltaTagMonederoRsaWarning() throws IllegalAccessException{
    	
    	FieldUtils.writeField(altaTagMonederoManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "horaDesdeALTA", "8:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "valorDesafioMonedero", 3, true);
        
        Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
        autentificacion.setEstadoRespuesta(EstadoRespuesta.WARNING);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setTipoDesafio(TipoDesafioEnum.COORDENADAS);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTipoError("TIPO ERROR");
		itemMensajeRespuestaList.add(itemMensajeRespuesta );
		autentificacion.setItemMensajeRespuesta(itemMensajeRespuestaList );
		autentificacion.setRespuesta(autentificacionDTO );
        
        Cuenta cuentaPorId = new Cuenta();
        cuentaPorId.setNroTarjetaCredito("12345678901234567890");
        
        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setProductoAltair("43");
        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
        
        Respuesta<ComprobanteAltaTagMonederoView> out = new Respuesta<ComprobanteAltaTagMonederoView>();
        ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView = new ComprobanteAltaTagMonederoView();
        DatosSolicitanteConfirmadoView datosSolicitanteConfirmadoView = new DatosSolicitanteConfirmadoView();
		comprobanteAltaTagMonederoView.setDatosSolicitanteConfirmadoView(datosSolicitanteConfirmadoView );
		out.setRespuesta(comprobanteAltaTagMonederoView );
        
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(autentificacion);
		Mockito.when(cuentaBO.obtenerCuentaPorId(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(cuentaPorId);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(altaTagMonederoService.ejecutarAltaTagMonedero(Matchers.any(DatosAltaTagMonederoEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        
    	DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoInOutView = new DatosSolicitanteConfirmadoInOutView();
    	datosSolicitanteConfirmadoInOutView.setImporteSeleccionado("12345678");
    	datosSolicitanteConfirmadoInOutView.setLimiteSeleccionado("123456789");
 
        Respuesta<DatosSolicitanteConfirmadoInOutView> respuestaRSA = new Respuesta<DatosSolicitanteConfirmadoInOutView>();
        respuestaRSA.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaRSA.setRespuesta(datosSolicitanteConfirmadoInOutView);
        when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(DatosSolicitanteConfirmadoInOutView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
        Mockito.doReturn(StringUtils.EMPTY).when(altaTagMonederoManager).horarioBancario();
		Respuesta<DatosSolicitanteConfirmadoInOutView> respuesta = altaTagMonederoManager.ejecutarAltaTagMonederoRsa(datosSolicitanteConfirmadoInOutView);
         
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        
    }
    
    @Test
    public void ejecutarAltaTagMonederoRsaError() throws IllegalAccessException{
    	
    	FieldUtils.writeField(altaTagMonederoManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "horaDesdeALTA", "8:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "valorDesafioMonedero", 3, true);
        
        Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
        autentificacion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setTipoDesafio(TipoDesafioEnum.COORDENADAS);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTipoError("TIPO ERROR");
		itemMensajeRespuestaList.add(itemMensajeRespuesta );
		autentificacion.setItemMensajeRespuesta(itemMensajeRespuestaList );
		autentificacion.setRespuesta(autentificacionDTO );
        
        Cuenta cuentaPorId = new Cuenta();
        cuentaPorId.setNroTarjetaCredito("12345678901234567890");
        
        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setProductoAltair("43");
        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
        
        Respuesta<ComprobanteAltaTagMonederoView> out = new Respuesta<ComprobanteAltaTagMonederoView>();
        ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView = new ComprobanteAltaTagMonederoView();
        DatosSolicitanteConfirmadoView datosSolicitanteConfirmadoView = new DatosSolicitanteConfirmadoView();
		comprobanteAltaTagMonederoView.setDatosSolicitanteConfirmadoView(datosSolicitanteConfirmadoView );
		out.setRespuesta(comprobanteAltaTagMonederoView );
        
		
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(autentificacion);
		Mockito.when(cuentaBO.obtenerCuentaPorId(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(cuentaPorId);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(altaTagMonederoService.ejecutarAltaTagMonedero(Matchers.any(DatosAltaTagMonederoEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);

    	DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoInOutView = new DatosSolicitanteConfirmadoInOutView();
    	datosSolicitanteConfirmadoInOutView.setImporteSeleccionado("12345678");
    	datosSolicitanteConfirmadoInOutView.setLimiteSeleccionado("123456789");
    	Respuesta<DatosSolicitanteConfirmadoInOutView> respuestaRSA = new Respuesta<DatosSolicitanteConfirmadoInOutView>();
    	respuestaRSA.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	respuestaRSA.setRespuesta(datosSolicitanteConfirmadoInOutView);
    	when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(DatosSolicitanteConfirmadoInOutView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
    	Mockito.doReturn(StringUtils.EMPTY).when(altaTagMonederoManager).horarioBancario();
		Respuesta<DatosSolicitanteConfirmadoInOutView> respuesta = altaTagMonederoManager.ejecutarAltaTagMonederoRsa(datosSolicitanteConfirmadoInOutView);
         
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        
    }
    
	@Test
    public void ejecutarAltaTagMonederoRsaDesafioEnCurso() throws IllegalAccessException{
    	
    	FieldUtils.writeField(altaTagMonederoManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "horaDesdeALTA", "8:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "valorDesafioMonedero", 3, true);
        
        Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
        autentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Cuenta cuentaPorId = new Cuenta();
        cuentaPorId.setNroTarjetaCredito("12345678901234567890");
        
        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setProductoAltair("43");
        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
        
        Respuesta<ComprobanteAltaTagMonederoView> out = new Respuesta<ComprobanteAltaTagMonederoView>();
        ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView = new ComprobanteAltaTagMonederoView();
        DatosSolicitanteConfirmadoView datosSolicitanteConfirmadoView = new DatosSolicitanteConfirmadoView();
		comprobanteAltaTagMonederoView.setDatosSolicitanteConfirmadoView(datosSolicitanteConfirmadoView );
		out.setRespuesta(comprobanteAltaTagMonederoView );
        
		TokenDesafio desafioEnCurso = new TokenDesafio();
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		BanelcoOperacionDTO banelcoOperacion = new BanelcoOperacionDTO();
		banelcoOperacion.setTipoDesafio(TipoDesafioEnum.COORDENADAS);
		autentificacionDTO.setBanelcoOperacion(banelcoOperacion);
		desafioEnCurso.setAutentificacionDTO(autentificacionDTO);
		
		Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = new Respuesta<AutentificacionDTO>();
		
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(autentificacion);
		Mockito.when(cuentaBO.obtenerCuentaPorId(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(cuentaPorId);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(altaTagMonederoService.ejecutarAltaTagMonedero(Matchers.any(DatosAltaTagMonederoEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
		Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(desafioEnCurso);
		Mockito.when(autentificacionManager.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(respuestaAutentificacionDTO );

		
    	DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoInOutView = new DatosSolicitanteConfirmadoInOutView();
    	datosSolicitanteConfirmadoInOutView.setImporteSeleccionado("12345678");
    	datosSolicitanteConfirmadoInOutView.setLimiteSeleccionado("123456789");
    	AutentificacionDTO desafio = new AutentificacionDTO();
    	desafio.setTipoDesafio(TipoDesafioEnum.COORDENADAS);
		datosSolicitanteConfirmadoInOutView.setDesafio(desafio );
		
        Respuesta<DatosSolicitanteConfirmadoInOutView> respuestaRSA = new Respuesta<DatosSolicitanteConfirmadoInOutView>();
        respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaRSA.setRespuesta(datosSolicitanteConfirmadoInOutView);
        when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(DatosSolicitanteConfirmadoInOutView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
        Mockito.doReturn(StringUtils.EMPTY).when(altaTagMonederoManager).horarioBancario();
        
		Respuesta<DatosSolicitanteConfirmadoInOutView> respuesta = altaTagMonederoManager.ejecutarAltaTagMonederoRsa(datosSolicitanteConfirmadoInOutView);
         
        Assert.assertNotNull(respuesta);
        
    }
    
    @Test
    public void ejecutarAltaTagMonederoRsaAdicionalTrue() throws IllegalAccessException, BusinessException{
    	
    	FieldUtils.writeField(altaTagMonederoManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "horaDesdeALTA", "8:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "valorDesafioMonedero", 3, true);
        
        Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
        autentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Cuenta cuentaPorId = new Cuenta();
        cuentaPorId.setNroTarjetaCredito("12345678901234567890");
        
        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setProductoAltair("43");
        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
        
        Respuesta<ComprobanteAltaTagMonederoView> out = new Respuesta<ComprobanteAltaTagMonederoView>();
        ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView = new ComprobanteAltaTagMonederoView();
        DatosSolicitanteConfirmadoView datosSolicitanteConfirmadoView = new DatosSolicitanteConfirmadoView();
		comprobanteAltaTagMonederoView.setDatosSolicitanteConfirmadoView(datosSolicitanteConfirmadoView );
		out.setRespuesta(comprobanteAltaTagMonederoView );
        
		Respuesta<DatosSolicitanteDTO> respuestaDatosSolicitanteDTO = new Respuesta<DatosSolicitanteDTO>();
		DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
		datosSolicitanteDTO.setNup("12345");
		datosSolicitanteDTO.setFechaNacimiento("01/02/1993");
		respuestaDatosSolicitanteDTO.setRespuesta(datosSolicitanteDTO );
		
		ConsultaInhabilitadosOutEntity consultaInhabilitadosOutEntity = new ConsultaInhabilitadosOutEntity();
		consultaInhabilitadosOutEntity.setCodigoRetornoExtendido("00000000");
		
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(autentificacion);
		Mockito.when(cuentaBO.obtenerCuentaPorId(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(cuentaPorId);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(altaTagMonederoService.ejecutarAltaTagMonedero(Matchers.any(DatosAltaTagMonederoEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
		Mockito.when(datosSolicitanteBO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaDatosSolicitanteDTO);
		Mockito.when(altaTagMonederoBO.obtenerDatosSucursal(Matchers.any(ConsultaUnidadControlInEntity.class))).thenReturn(new ConsultaUnidadControlOutEntity());
		Mockito.when(altaTagMonederoBO.esPersonaHabilitada(Matchers.any(ConsultaInhabilitadosInEntity.class))).thenReturn(consultaInhabilitadosOutEntity);

    	DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoInOutView = new DatosSolicitanteConfirmadoInOutView();
    	datosSolicitanteConfirmadoInOutView.setImporteSeleccionado("12345678");
    	datosSolicitanteConfirmadoInOutView.setLimiteSeleccionado("123456789");
    	datosSolicitanteConfirmadoInOutView.setAdicional(true);
    	datosSolicitanteConfirmadoInOutView.setDocTipo("DNI");
    	datosSolicitanteConfirmadoInOutView.setFechaNacimiento("01/02/1993");
    	datosSolicitanteConfirmadoInOutView.setApellido("apellido");
    	datosSolicitanteConfirmadoInOutView.setNombre("Nombre");
    	datosSolicitanteConfirmadoInOutView.setSexo("M");
    	datosSolicitanteConfirmadoInOutView.setDocTipo("DNI");
    	datosSolicitanteConfirmadoInOutView.setDoc("15958653");
    	datosSolicitanteConfirmadoInOutView.setLimite("asd 123 asd");
    	datosSolicitanteConfirmadoInOutView.setImporte("$ 50");

    	Respuesta<DatosSolicitanteConfirmadoInOutView> respuestaRSA = new Respuesta<DatosSolicitanteConfirmadoInOutView>();
    	respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
    	respuestaRSA.setRespuesta(datosSolicitanteConfirmadoInOutView);
    	when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(DatosSolicitanteConfirmadoInOutView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
    	Mockito.doReturn(StringUtils.EMPTY).when(altaTagMonederoManager).horarioBancario();
    	
		Respuesta<DatosSolicitanteConfirmadoInOutView> respuesta = altaTagMonederoManager.ejecutarAltaTagMonederoRsa(datosSolicitanteConfirmadoInOutView);
         
        Assert.assertNotNull(respuesta);
        
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void ejecutarAltaTagMonederoRsaAdicionalTrueLlamarCnsPadCuit() throws IllegalAccessException, BusinessException, DAOException{
    	
    	FieldUtils.writeField(altaTagMonederoManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "horaDesdeALTA", "8:00", true);
        FieldUtils.writeField(altaTagMonederoManager, "valorDesafioMonedero", 3, true);
        
        Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
        autentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Cuenta cuentaPorId = new Cuenta();
        cuentaPorId.setNroTarjetaCredito("12345678901234567890");
        
        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setProductoAltair("43");
        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
        
        Respuesta<ComprobanteAltaTagMonederoView> out = new Respuesta<ComprobanteAltaTagMonederoView>();
        ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView = new ComprobanteAltaTagMonederoView();
        DatosSolicitanteConfirmadoView datosSolicitanteConfirmadoView = new DatosSolicitanteConfirmadoView();
		comprobanteAltaTagMonederoView.setDatosSolicitanteConfirmadoView(datosSolicitanteConfirmadoView );
		out.setRespuesta(comprobanteAltaTagMonederoView );
        
		Respuesta<DatosSolicitanteDTO> respuestaDatosSolicitanteDTO = new Respuesta<DatosSolicitanteDTO>();
		DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
		datosSolicitanteDTO.setFechaNacimiento("01/02/1993");
		respuestaDatosSolicitanteDTO.setRespuesta(datosSolicitanteDTO );
		
		ConsultaInhabilitadosOutEntity consultaInhabilitadosOutEntity = new ConsultaInhabilitadosOutEntity();
		consultaInhabilitadosOutEntity.setCodigoRetornoExtendido("00000000");
		
		Respuesta<ConsultaPadronCuitOutEntity> datosPadron = new Respuesta<ConsultaPadronCuitOutEntity>();
        out.setEstadoRespuesta(EstadoRespuesta.OK);
        ConsultaPadronCuitOutEntity consultaPadronCuitOutEntity = new ConsultaPadronCuitOutEntity();
        consultaPadronCuitOutEntity.setAbaNroCuit("123456789");
        consultaPadronCuitOutEntity.setAbaTipoDocumento("N");
        consultaPadronCuitOutEntity.setAbaNroDocumento("35969686");
        consultaPadronCuitOutEntity.setAbaNombre("Jose");
        consultaPadronCuitOutEntity.setAbaApellido("Perez");
        consultaPadronCuitOutEntity.setAbaFechaNacimiento("01/02/1993");
        consultaPadronCuitOutEntity.setAbaSexo("H");
        datosPadron.setRespuesta(consultaPadronCuitOutEntity);

        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(autentificacion);
		Mockito.when(cuentaBO.obtenerCuentaPorId(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(cuentaPorId);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(altaTagMonederoService.ejecutarAltaTagMonedero(Matchers.any(DatosAltaTagMonederoEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
		Mockito.when(datosSolicitanteBO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaDatosSolicitanteDTO);
		Mockito.when(altaTagMonederoBO.obtenerDatosSucursal(Matchers.any(ConsultaUnidadControlInEntity.class))).thenReturn(new ConsultaUnidadControlOutEntity());
		Mockito.when(altaTagMonederoBO.esPersonaHabilitada(Matchers.any(ConsultaInhabilitadosInEntity.class))).thenReturn(consultaInhabilitadosOutEntity);
		Mockito.when(datosSolicitanteBO.getDatosPadronBO(Matchers.any(DatosSolicitanteCriterioView.class), Matchers.any(Cliente.class))).thenReturn(datosPadron);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(DatosSolicitanteResponseView.class))).thenReturn(out);

    	DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoInOutView = new DatosSolicitanteConfirmadoInOutView();
    	datosSolicitanteConfirmadoInOutView.setImporteSeleccionado("12345678");
    	datosSolicitanteConfirmadoInOutView.setLimiteSeleccionado("123456789");
    	datosSolicitanteConfirmadoInOutView.setAdicional(true);
    	datosSolicitanteConfirmadoInOutView.setDocTipo("DNI");
    	datosSolicitanteConfirmadoInOutView.setFechaNacimiento("01/02/1993");
    	datosSolicitanteConfirmadoInOutView.setApellido("apellido");
    	datosSolicitanteConfirmadoInOutView.setNombre("Nombre");
    	datosSolicitanteConfirmadoInOutView.setSexo("M");
    	datosSolicitanteConfirmadoInOutView.setDocTipo("DNI");
    	datosSolicitanteConfirmadoInOutView.setDoc("15958653");
    	datosSolicitanteConfirmadoInOutView.setLimite("asd 123 asd");
    	datosSolicitanteConfirmadoInOutView.setImporte("$ 50");

    	Respuesta<DatosSolicitanteConfirmadoInOutView> respuestaRSA = new Respuesta<DatosSolicitanteConfirmadoInOutView>();
    	respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
    	respuestaRSA.setRespuesta(datosSolicitanteConfirmadoInOutView);
    	when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(DatosSolicitanteConfirmadoInOutView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
    	Mockito.doReturn(StringUtils.EMPTY).when(altaTagMonederoManager).horarioBancario();
		Respuesta<DatosSolicitanteConfirmadoInOutView> respuesta = altaTagMonederoManager.ejecutarAltaTagMonederoRsa(datosSolicitanteConfirmadoInOutView);
         
        Assert.assertNotNull(respuesta);
        
    }
    
    @Test
    public void cargarTerminosCondiciones(){
    	
    	Respuesta<String> legal = new Respuesta<String>();
    	legal.setRespuesta("");
    	
    	Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(legal);    	
    	
    	Respuesta<TerminosCondiciones> respuesta = altaTagMonederoManager.cargarTerminosCondiciones();
    	
    	Assert.assertNotNull(respuesta);
    	
    }
    
   }
