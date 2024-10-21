package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo.ExtraccionYComprasExteriorBO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorInDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorOutDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CuentaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.DatosExtraccionYComprasExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.TarjetaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager.impl.ExtraccionYComprasExteriorManagerImpl;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.mock.ExtraccionYComprasExteriorObjectsMock;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.CuentaOperacionExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.DatosTarjetasExtraccionYComprasExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.ModifTarjetaOperaExtraccionView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.TarjetaOperacionExteriorView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;


@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ExtraccionYComprasExteriorManagerTest {

    
    /** The ExtraccionYComprasExteriorManager. */
    @InjectMocks
    private ExtraccionYComprasExteriorManager extraccionYComprasExteriorManager = new ExtraccionYComprasExteriorManagerImpl();

    
    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;;
    
    @Mock
    private ExtraccionYComprasExteriorBO extraccionYComprasExteriorBO;

    
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private MensajeDAO mensajeDAO;
    
    @Mock
    private MensajeManager mensajeManager;
    
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    private SesionParametros sesionParametros;
    
    @Mock
    private SesionCliente sesionCliente;

    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    @Mock
    private AutentificacionManager autentificacionManager;
    
    /** The Cuenta BO. */
    @Mock
    private DesafioOperacionRSA<ModifTarjetaOperaExtraccionView> desafioOperacionRSA;
    
    Mensaje mensaje = new Mensaje();

    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("Silvina");
        cliente.setApellido1("Luque");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("123456789");

    }     
    
    
    @Test
    public void consultarCuentasOperacionExteriorTest() {
        TarjetaOperacionExteriorView tarjetaView = new TarjetaOperacionExteriorView();
        tarjetaView.setNumeroTarjeta("123412341234");
        tarjetaView.setId("1234-1234");
        DatosExtraccionYComprasExteriorDTO datosOperaExteriorDTO = ExtraccionYComprasExteriorObjectsMock.obtenerDatosOperaExteriorDTO();
        when(sesionParametros.getDatosExtraccionYComprasExterior()).thenReturn(datosOperaExteriorDTO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<List<CuentaOperacionExteriorDTO>> respuestaBO =  new Respuesta<List<CuentaOperacionExteriorDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(ExtraccionYComprasExteriorObjectsMock.obtenerListaCuentasDTO());
        Respuesta<List<CuentaOperacionExteriorView>> respuesta = new Respuesta<List<CuentaOperacionExteriorView>>();
        when(extraccionYComprasExteriorBO.consultarCuentasOperaExterior(Matchers.anyString())).thenReturn(respuestaBO);
        respuesta = extraccionYComprasExteriorManager.consultarCuentasOperacionExterior(tarjetaView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    
    @Test
    public void consultarCuentasOperacionExteriorErrorTest() {
        TarjetaOperacionExteriorView tarjetaView = new TarjetaOperacionExteriorView();
        tarjetaView.setNumeroTarjeta("123412341234");
        tarjetaView.setId("1234-1234");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        DatosExtraccionYComprasExteriorDTO datosOperaExteriorDTO = ExtraccionYComprasExteriorObjectsMock.obtenerDatosOperaExteriorDTO();
        when(sesionParametros.getDatosExtraccionYComprasExterior()).thenReturn(datosOperaExteriorDTO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<List<CuentaOperacionExteriorDTO>> respuestaBO =  new Respuesta<List<CuentaOperacionExteriorDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Respuesta<List<CuentaOperacionExteriorView>> respuesta = new Respuesta<List<CuentaOperacionExteriorView>>();
        when(extraccionYComprasExteriorBO.consultarCuentasOperaExterior(Matchers.anyString())).thenReturn(respuestaBO);
        respuesta = extraccionYComprasExteriorManager.consultarCuentasOperacionExterior(tarjetaView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    @Test
    public void consultarCuentasOperacionExteriorError2Test() {
        TarjetaOperacionExteriorView tarjetaView = new TarjetaOperacionExteriorView();
        tarjetaView.setNumeroTarjeta("123412341234");
        tarjetaView.setId("1234-9999");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        DatosExtraccionYComprasExteriorDTO datosOperaExteriorDTO = ExtraccionYComprasExteriorObjectsMock.obtenerDatosOperaExteriorDTO();
        when(sesionParametros.getDatosExtraccionYComprasExterior()).thenReturn(datosOperaExteriorDTO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<List<CuentaOperacionExteriorDTO>> respuestaBO =  new Respuesta<List<CuentaOperacionExteriorDTO>>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Respuesta<List<CuentaOperacionExteriorView>> respuesta = new Respuesta<List<CuentaOperacionExteriorView>>();
        when(extraccionYComprasExteriorBO.consultarCuentasOperaExterior(Matchers.anyString())).thenReturn(respuestaBO);
        respuesta = extraccionYComprasExteriorManager.consultarCuentasOperacionExterior(tarjetaView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    
    
    @Test
    public void consultarTarjetasOperacionExteriorTest() {
        DatosExtraccionYComprasExteriorDTO datosOperaExteriorDTO = ExtraccionYComprasExteriorObjectsMock.obtenerDatosOperaExteriorDTO();
        when(sesionParametros.getDatosExtraccionYComprasExterior()).thenReturn(datosOperaExteriorDTO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<List<CuentaOperacionExteriorDTO>> respuestaCuentasBO = new Respuesta<List<CuentaOperacionExteriorDTO>>();
        respuestaCuentasBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentasBO.setRespuesta(ExtraccionYComprasExteriorObjectsMock.obtenerListaCuentasDTO());
        Respuesta<List<TarjetaOperacionExteriorDTO>> respuestaTarjetasBO =  new Respuesta<List<TarjetaOperacionExteriorDTO>>();
        respuestaTarjetasBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaTarjetasBO.setRespuesta(ExtraccionYComprasExteriorObjectsMock.obtenerListaTarjetasDTO());
        Respuesta<DatosTarjetasExtraccionYComprasExteriorView>  respuesta = new Respuesta<DatosTarjetasExtraccionYComprasExteriorView> ();
        when(extraccionYComprasExteriorBO.consultarTarjetasOperaExterior()).thenReturn(respuestaTarjetasBO);
        when(extraccionYComprasExteriorBO.consultarCuentasOperaExterior(Matchers.anyString())).thenReturn(respuestaCuentasBO);
        respuesta = extraccionYComprasExteriorManager.consultarTarjetasOperacionExterior();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    
    @Test
    public void consultarTarjetasOperacionExteriorError2Test() {
        DatosExtraccionYComprasExteriorDTO datosOperaExteriorDTO = ExtraccionYComprasExteriorObjectsMock.obtenerDatosOperaExteriorDTO();
        when(sesionParametros.getDatosExtraccionYComprasExterior()).thenReturn(datosOperaExteriorDTO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<List<CuentaOperacionExteriorDTO>> respuestaCuentasBO = new Respuesta<List<CuentaOperacionExteriorDTO>>();
        respuestaCuentasBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<TarjetaOperacionExteriorDTO>> respuestaTarjetasBO =  new Respuesta<List<TarjetaOperacionExteriorDTO>>();
        respuestaTarjetasBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaTarjetasBO.setRespuesta(ExtraccionYComprasExteriorObjectsMock.obtenerListaTarjetasDTO());
        Respuesta<DatosTarjetasExtraccionYComprasExteriorView>  respuesta = new Respuesta<DatosTarjetasExtraccionYComprasExteriorView> ();
        when(extraccionYComprasExteriorBO.consultarTarjetasOperaExterior()).thenReturn(respuestaTarjetasBO);
        when(extraccionYComprasExteriorBO.consultarCuentasOperaExterior(Matchers.anyString())).thenReturn(respuestaCuentasBO);
        respuesta = extraccionYComprasExteriorManager.consultarTarjetasOperacionExterior();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
   
    @Test
    public void consultarTarjetasOperacionExteriorErrorTest() {
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<TarjetaOperacionExteriorDTO>> respuestaTarjetasBO =  new Respuesta<List<TarjetaOperacionExteriorDTO>>();
        respuestaTarjetasBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Respuesta<DatosTarjetasExtraccionYComprasExteriorView>  respuesta = new Respuesta<DatosTarjetasExtraccionYComprasExteriorView> ();
        when(extraccionYComprasExteriorBO.consultarTarjetasOperaExterior()).thenReturn(respuestaTarjetasBO);
        respuesta = extraccionYComprasExteriorManager.consultarTarjetasOperacionExterior();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    
    
   
    @Test
    public void modificarTarjetaOperacionExteriorTest() {
        DatosExtraccionYComprasExteriorDTO datosOperaExteriorDTO = ExtraccionYComprasExteriorObjectsMock.obtenerDatosOperaExteriorDTO();
        when(sesionParametros.getDatosExtraccionYComprasExterior()).thenReturn(datosOperaExteriorDTO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        Mensaje mensajeFeedback = new Mensaje();
        mensajeFeedback.setMensaje("<p>El cambio de cuenta de extracción y compra en el exterior a la <b>{0}</b> se realizó con éxito.</p>");
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
        Respuesta<CambioTarjetaOperaExteriorOutDTO> respuestaBO =  new Respuesta<CambioTarjetaOperaExteriorOutDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(ExtraccionYComprasExteriorObjectsMock.obtenerCambioTarjetaOutDTO());
        Respuesta<ModifTarjetaOperaExtraccionView>  respuesta = new Respuesta<ModifTarjetaOperaExtraccionView>();
        when(extraccionYComprasExteriorBO.cambioTarjetaOperaExterior(Matchers.any(CambioTarjetaOperaExteriorInDTO.class))).thenReturn(respuestaBO);
        ModifTarjetaOperaExtraccionView modifTarjetaOperaExteriorView = ExtraccionYComprasExteriorObjectsMock.obtenerModifTarjetaView();
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("idTarjeta", modifTarjetaOperaExteriorView.getIdTarjeta() != null ? modifTarjetaOperaExteriorView.getIdTarjeta() : "");
        mapaAtributos.put("idCuenta", modifTarjetaOperaExteriorView.getIdCuenta() != null ? modifTarjetaOperaExteriorView.getIdCuenta() : "");
        String hashView = HashUtils.obtenerHash(mapaAtributos);
        Respuesta<AutentificacionDTO> respuestaAutentificacion = new Respuesta<AutentificacionDTO>();
        respuestaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        when(sesionParametros.getValidacionHash()).thenReturn(hashView);
        when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(respuestaAutentificacion);
        
        Respuesta<ModifTarjetaOperaExtraccionView> respuestaRSA = new Respuesta<ModifTarjetaOperaExtraccionView>();
        respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ModifTarjetaOperaExtraccionView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
        
        respuesta = extraccionYComprasExteriorManager.modificarTarjetaOperacionExterior(modifTarjetaOperaExteriorView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @Test
    public void modificarTarjetaOperacionExteriorErrorTest() {
        DatosExtraccionYComprasExteriorDTO datosOperaExteriorDTO = ExtraccionYComprasExteriorObjectsMock.obtenerDatosOperaExteriorDTO();
        when(sesionParametros.getDatosExtraccionYComprasExterior()).thenReturn(datosOperaExteriorDTO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<CambioTarjetaOperaExteriorOutDTO> respuestaBO =  new Respuesta<CambioTarjetaOperaExteriorOutDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Respuesta<ModifTarjetaOperaExtraccionView>  respuesta = new Respuesta<ModifTarjetaOperaExtraccionView>();
        when(extraccionYComprasExteriorBO.cambioTarjetaOperaExterior(Matchers.any(CambioTarjetaOperaExteriorInDTO.class))).thenReturn(respuestaBO);
        ModifTarjetaOperaExtraccionView modifTarjetaOperaExteriorView = ExtraccionYComprasExteriorObjectsMock.obtenerModifTarjetaView();
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("idTarjeta", modifTarjetaOperaExteriorView.getIdTarjeta() != null ? modifTarjetaOperaExteriorView.getIdTarjeta() : "");
        mapaAtributos.put("idCuenta", modifTarjetaOperaExteriorView.getIdCuenta() != null ? modifTarjetaOperaExteriorView.getIdCuenta() : "");
        String hashView = HashUtils.obtenerHash(mapaAtributos);
        Respuesta<AutentificacionDTO> respuestaAutentificacion = new Respuesta<AutentificacionDTO>();
        respuestaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        Mensaje mensajeFeedback = new Mensaje();
        mensajeFeedback.setMensaje("<p>No pudimos realizar el cambio de cuenta de extracción y compra en el exterior de la <b>{0}</b></p>");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
        when(sesionParametros.getValidacionHash()).thenReturn(hashView);
        when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(respuestaAutentificacion);

        Respuesta<ModifTarjetaOperaExtraccionView> respuestaRSA = new Respuesta<ModifTarjetaOperaExtraccionView>();
        respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ModifTarjetaOperaExtraccionView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);

        respuesta = extraccionYComprasExteriorManager.modificarTarjetaOperacionExterior(modifTarjetaOperaExteriorView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    @Test
    public void descargarComprobanteTest() {
        Respuesta<Reporte> respuestaBO =  new Respuesta<Reporte>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        when(extraccionYComprasExteriorBO.descargarComprobante()).thenReturn(respuestaBO);
        Respuesta<ReporteView> respuesta = extraccionYComprasExteriorManager.descargarComprobante();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        
    }
    
    
}
