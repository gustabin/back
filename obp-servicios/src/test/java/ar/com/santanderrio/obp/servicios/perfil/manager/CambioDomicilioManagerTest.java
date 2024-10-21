package ar.com.santanderrio.obp.servicios.perfil.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.service.DatosSelectoresService;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinError1Exception;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.DatosDomTelOutDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ModificacionCambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ProductoDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ResultadoModificacionDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.manager.impl.CambioDomicilioManagerImpl;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ComprobanteCambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosDomicilioView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomiciliosDisponiblesView;

@RunWith(MockitoJUnitRunner.class)
public class CambioDomicilioManagerTest {

    /** The cliente manager. */
    @InjectMocks
    private CambioDomicilioManager cambioDomicilioManager = new CambioDomicilioManagerImpl();

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;;

    @Mock
    private CambioDomicilioBO cambioDomicilioBO;

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
    private DatosSelectoresService datosSelectoresService;

    @Mock
    private AutentificacionManager autentificacionManager;

    @Mock
    private SesionCliente sesionCliente;
    
    Mensaje mensaje = new Mensaje();
    ModificacionCambioDomicilioDTO domicilioModif = new ModificacionCambioDomicilioDTO();
    List<CambioDomicilioDTO> lCdDTO = new ArrayList<CambioDomicilioDTO>();
    CambioDomicilioView cdView = new CambioDomicilioView();
    CambioDomicilioDTO domi = new CambioDomicilioDTO();
    List<Opcion> provincias = new ArrayList<Opcion>();

    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        // Configuracion lista domicilios en sesion

        domi.setDomicilioId("99999999");
        domi.setApt("1234");
        domi.setCalle("Calle");
        domi.setCodigoPostal("1234");
        domi.setComuna("");
        domi.setPiso("1");
        domi.setDepartamento("A");
        domi.setLocalidad("Avellaneda");
        domi.setMarcaDomErroneo("");
        domi.setObservaciones("");
        domi.setPais("02");
        domi.setSecuenciaDomicilio("001");
        domi.setSucursal("");
        domi.setProvincia("08");
        domi.setTelefono("34567");
        domi.setPrefijo("011");
        domi.setCaracteristica("22");
        domi.setNumeroTelefono("344543634543");
        domi.setTipoDomicilio("PRI");
        domi.setDescProvincia("Buenos Aires");
        domi.setDescPais("Argentina");
        domi.setListaProductos(new ArrayList<ProductoDTO>());
        lCdDTO.add(domi);
        // Configuracion domicilio modificado de sesion
        domicilioModif.setSecuenciaDomicilio("001");
        domicilioModif.setDomicilioId("99999999");
        domicilioModif.setApt("345");
        domicilioModif.setPiso("3");
        domicilioModif.setDepartamento("A");
        domicilioModif.setCodigoPostal("1234");
        domicilioModif.setDescProvincia("Buenos Aires");
        domicilioModif.setLocalidad("La localidad");
        domicilioModif.setCalle("La calle");
        domicilioModif.setPrefijo("011");
        domicilioModif.setNumeroTelefono("23232");
        domicilioModif.setCaracteristica("54");

        DatosDomTelOutDTO datosDomTelDTO = new DatosDomTelOutDTO();
        datosDomTelDTO.setSecuenciaDomicilio("");
        datosDomTelDTO.setTipoDomicilio("");
        datosDomTelDTO.setTipoVia("");
        datosDomTelDTO.setTipoConstruccion("");
        datosDomTelDTO.setTipoNucleoUrbano("");
        datosDomTelDTO.setObservaciones2("");
        datosDomTelDTO.setSucursalCasilla("");
        datosDomTelDTO.setLocalidad("");
        datosDomTelDTO.setComuna("");
        datosDomTelDTO.setRutaCartero("");
        datosDomTelDTO.setCodigoPais("");
        datosDomTelDTO.setTituDomicilio("");
        datosDomTelDTO.setFechaVerificacion("");
        datosDomTelDTO.setMotivoDevolucion("");
        datosDomTelDTO.setUsuarioAlta("");
        datosDomTelDTO.setFechaAltaRegistro("");
        datosDomTelDTO.setUsuarioUltimaMod("");
        datosDomTelDTO.setNroTerminalUltMod("");
        datosDomTelDTO.setSucursalUltMod("");
        datosDomTelDTO.setTimestampUltMod("");
        datosDomTelDTO.setSecuenciaTelefono("");
        datosDomTelDTO.setTipoTelefono("");
        datosDomTelDTO.setClaseTelefono("");
        datosDomTelDTO.setNroInterno("");
        datosDomTelDTO.setObservacionesSemaforo("");
        datosDomTelDTO.setTimestamp("");
        domicilioModif.setDatosAdicionales(datosDomTelDTO);
        ResultadoModificacionDomicilioDTO rM = new ResultadoModificacionDomicilioDTO();
        domicilioModif.setResultadoModificacion(rM);

        // Configuracion view
        cdView.setDomicilioId("99999999");
        cdView.setApt("1234");
        cdView.setCalle("Calle");
        cdView.setCaracteristica("22");
        cdView.setPrefijo("011");
        cdView.setNumeroTelefono("344543634543");
        cdView.setTelefono("344543634543");
        cdView.setLocalidad("Avellaneda");
        cdView.setDescProvincia("Buenos Aires");
        cdView.setProvincia("02");
        cdView.setCodigoPostal("1234");
        cdView.setDepartamento("A");
        cdView.setPiso("2");
        cdView.setDescPais("Argentina");
        cdView.setTipoDomicilio("PRI");

        Opcion pV1 = new Opcion();
        pV1.setOpcion("Buenos Aires");
        pV1.setId("1");
        Opcion pV2 = new Opcion();
        pV2.setOpcion("Mendoza");
        pV2.setId("2");
        Opcion pV3 = new Opcion();
        pV3.setOpcion("Corrientes");
        pV3.setId("3");
        provincias.add(pV1);
        provincias.add(pV2);
        provincias.add(pV3);
        
        Cliente cliente = new Cliente();
        when(sesionCliente.getCliente()).thenReturn(cliente);
    }

    @Test
    public void obtenerInfoAdicionalDomTelTest() {
        String cambioDomicilioId = "";
        CambioDomicilioView cambioViiew = new CambioDomicilioView();
        cambioViiew.setDomicilioId(cambioDomicilioId);
        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
        Respuesta<DatosDomTelOutDTO> rta = new Respuesta<DatosDomTelOutDTO>();
        rta.setEstadoRespuesta(EstadoRespuesta.OK);
        when(cambioDomicilioBO.obtenerInfoAdicionalDomTel(Matchers.anyString())).thenReturn(rta);
        respuesta = cambioDomicilioManager.obtenerInfoAdicionalDomTel(cambioViiew);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void obtenerInfoAdicionalDomTelErrorTest() {
        String cambioDomicilioId = "";
        CambioDomicilioView cambioViiew = new CambioDomicilioView();
        cambioViiew.setDomicilioId(cambioDomicilioId);
        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
        Respuesta<DatosDomTelOutDTO> rta = new Respuesta<DatosDomTelOutDTO>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(cambioDomicilioBO.obtenerInfoAdicionalDomTel(Matchers.anyString())).thenReturn(rta);
        respuesta = cambioDomicilioManager.obtenerInfoAdicionalDomTel(cambioViiew);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void guardarCambioDomicilioTest() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException, InstantiationException {
        CambioDomicilioView cambioDomicilioView = new CambioDomicilioView();
        Respuesta<AutentificacionDTO> respuestaAutentificacion = new Respuesta<AutentificacionDTO>();
        respuestaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<ResultadoModificacionDomicilioDTO> respuestaBo = new Respuesta<ResultadoModificacionDomicilioDTO>();
        respuestaBo.setEstadoRespuesta(EstadoRespuesta.OK);
        when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaAutentificacion);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaAutentificacion);
        when(cambioDomicilioBO.guardarCambioDomicilio(Matchers.any(CambioDomicilioView.class))).thenReturn(respuestaBo);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<CambioDomicilioView> respuesta = new Respuesta<CambioDomicilioView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        Mockito.doReturn(respuesta).when(autentificacionManager)
                .verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class),Matchers.anyInt());
        FieldUtils.writeDeclaredField(cambioDomicilioManager, "valorDesafioPerfilCambioDomicilio", 3, true);
        respuesta = cambioDomicilioManager.guardarCambioDomicilio(cambioDomicilioView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void guardarCambioDomicilioErrorTest() throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException, InstantiationException {
        CambioDomicilioView cambioDomicilioView = new CambioDomicilioView();
        Respuesta<AutentificacionDTO> respuestaAutentificacion = new Respuesta<AutentificacionDTO>();
        respuestaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<ResultadoModificacionDomicilioDTO> respuestaBo = new Respuesta<ResultadoModificacionDomicilioDTO>();
        Respuesta<CambioDomicilioView> respuesta = new Respuesta<CambioDomicilioView>();
        respuestaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
        
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaAutentificacion);
        when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaAutentificacion);
        when(cambioDomicilioBO.guardarCambioDomicilio(Matchers.any(CambioDomicilioView.class))).thenReturn(respuestaBo);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        Mockito.doReturn(respuesta).when(autentificacionManager)
                .verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class),Matchers.anyInt());
        FieldUtils.writeDeclaredField(cambioDomicilioManager, "valorDesafioPerfilCambioDomicilio", 3, true);

        
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setReintentosAgotados(false);
        cambioDomicilioView.setDesafio(autentificacionDTO);
        respuesta = cambioDomicilioManager.guardarCambioDomicilio(cambioDomicilioView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void normalizarDomicilioErrorTest() throws MerlinError1Exception {
        CambioDomicilioView cambioDomicilioView = new CambioDomicilioView();
        Respuesta<List<CambioDomicilioDTO>> respuestaMerlinDTO = new Respuesta<List<CambioDomicilioDTO>>();
        Respuesta<List<CambioDomicilioView>> respuesta = new Respuesta<List<CambioDomicilioView>>();
        respuestaMerlinDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(cambioDomicilioBO.normalizarDomicilio(Matchers.any(CambioDomicilioView.class)))
                .thenReturn(respuestaMerlinDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        respuesta = cambioDomicilioManager.normalizarDomicilio(cambioDomicilioView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void normalizarDomicilioTest() throws MerlinError1Exception {
        CambioDomicilioView cambioDomicilioView = new CambioDomicilioView();
        Respuesta<List<CambioDomicilioDTO>> respuestaMerlinDTO = new Respuesta<List<CambioDomicilioDTO>>();
        List<CambioDomicilioDTO> listCambioDto = new ArrayList<CambioDomicilioDTO>();
        listCambioDto.add(domi);
        respuestaMerlinDTO.setRespuesta(listCambioDto);
        Respuesta<List<CambioDomicilioView>> respuesta = new Respuesta<List<CambioDomicilioView>>();
        respuestaMerlinDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        when(cambioDomicilioBO.normalizarDomicilio(Matchers.any(CambioDomicilioView.class)))
                .thenReturn(respuestaMerlinDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        respuesta = cambioDomicilioManager.normalizarDomicilio(cambioDomicilioView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void obtenerDomiciliosContactoTest() {
        Respuesta<List<CambioDomicilioDTO>> respuestaBo = new Respuesta<List<CambioDomicilioDTO>>();
        Respuesta<DatosDomicilioView> respuesta = new Respuesta<DatosDomicilioView>();
        respuestaBo.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBo.setRespuesta(lCdDTO);
        when(cambioDomicilioBO.consultarDomiciliosRegistrados()).thenReturn(respuestaBo);
        when(datosSelectoresService.obtenerProvincias()).thenReturn(provincias);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        respuesta = cambioDomicilioManager.obtenerDomiciliosContacto(Boolean.FALSE);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void obtenerDomiciliosContactoErrorTest() {
        Respuesta<List<CambioDomicilioDTO>> respuestaBo = new Respuesta<List<CambioDomicilioDTO>>();
        Respuesta<DatosDomicilioView> respuesta = new Respuesta<DatosDomicilioView>();
        respuestaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setMensaje("Domicilio con error mockeado");
        imr.setTipoError("DOM ERROR");
        itemsMensajeRespuesta.add(imr);
        respuestaBo.setItemMensajeRespuesta(itemsMensajeRespuesta);
        when(cambioDomicilioBO.consultarDomiciliosRegistrados()).thenReturn(respuestaBo);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(datosSelectoresService.obtenerProvincias()).thenReturn(provincias);
        respuesta = cambioDomicilioManager.obtenerDomiciliosContacto(Boolean.FALSE);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void verComprobante() {
        when(sesionParametros.getDomiciliosCliente()).thenReturn(lCdDTO);
        when(sesionParametros.getDomicilioModificado()).thenReturn(domicilioModif);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ComprobanteCambioDomicilioView> comprobante = cambioDomicilioManager.verComprobante();
        Assert.assertNotNull(comprobante);
        Assert.assertEquals(comprobante.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void verComprobanteError() {
        when(sesionParametros.getDomiciliosCliente()).thenReturn(lCdDTO);
        when(sesionParametros.getDomicilioModificado()).thenReturn(null);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ComprobanteCambioDomicilioView> comprobante = cambioDomicilioManager.verComprobante();
        Assert.assertNotNull(comprobante);
        Assert.assertEquals(comprobante.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void descargarComprobanteTest() {
        when(sesionParametros.getDomiciliosCliente()).thenReturn(lCdDTO);
        when(sesionParametros.getDomicilioModificado()).thenReturn(domicilioModif);
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        when(cambioDomicilioBO.descargarComprobante(Matchers.any(DatosComprobanteEntity.class)))
                .thenReturn(respuestaReporte);
        respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<ReporteView> respuesta = cambioDomicilioManager.descargarComprobante();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    @Test
    public void descargarComprobanteErrorTest() {
        when(sesionParametros.getDomiciliosCliente()).thenReturn(lCdDTO);
        when(sesionParametros.getDomicilioModificado()).thenReturn(null);
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(cambioDomicilioBO.descargarComprobante(Matchers.any(DatosComprobanteEntity.class)))
                .thenReturn(respuestaReporte);
        respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<ReporteView> respuesta = cambioDomicilioManager.descargarComprobante();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

    }
    
    
    /**
     * consultar domicilio principal laboral: OK.
     */
    @Test
    public void consultarDomicilioPrincipalLaboralTestOK() {
    	
    	 Respuesta<List<CambioDomicilioDTO>> respuestaBo = new Respuesta<List<CambioDomicilioDTO>>();
         respuestaBo.setEstadoRespuesta(EstadoRespuesta.OK);
         respuestaBo.setRespuesta(lCdDTO);
         
         domi.setTipoDomicilio("LAB");
         domi.setDomicilioId("99999999");
         domi.setApt("1234");
         domi.setCalle("Calle");
         domi.setCaracteristica("22");
         domi.setCodigoPostal("1234");
         domi.setLocalidad("Avellaneda");
         domi.setDescProvincia("Buenos Aires");
         domi.setDescPais("Argentina");
         
         domi.setComuna("");
         domi.setDepartamento("");
         domi.setNumeroTelefono("");
         domi.setPiso("");
         domi.setPrefijo("");
         domi.setProvincia("");
         domi.setTelefono("");
         domi.setSecuenciaDomicilio("");
         
         domi.setListaProductos(new ArrayList<ProductoDTO>());
         
         lCdDTO.add(domi);
         

         domi = new CambioDomicilioDTO();
         domi.setTipoDomicilio("LAB");
         domi.setDomicilioId("222");

         domi.setApt("1234");
         domi.setCalle("Calle");
         domi.setCaracteristica("22");
         domi.setCodigoPostal("1234");
         domi.setLocalidad("Avellaneda");
         domi.setDescProvincia("Jujuy");
         domi.setDescPais("Argentina");
         
         domi.setComuna("");
         domi.setDepartamento("");
         domi.setNumeroTelefono("");
         domi.setPiso("");
         domi.setPrefijo("");
         domi.setProvincia("");
         domi.setTelefono("");
         domi.setSecuenciaDomicilio("");
         
         domi.setListaProductos(new ArrayList<ProductoDTO>());
         domi.setComuna("comuna1");
         lCdDTO.add(domi);
         
         domi.setApt("1234");
         domi.setCalle("Calle");
         domi.setCaracteristica("22");
         domi.setCodigoPostal("1234");
         domi.setLocalidad("Avellaneda");
         domi.setDescProvincia("Cordoba");
         domi.setDescPais("Argentina");
         
         domi.setComuna("");
         domi.setDepartamento("");
         domi.setNumeroTelefono("");
         domi.setPiso("");
         domi.setPrefijo("");
         domi.setProvincia("");
         domi.setTelefono("");
         domi.setSecuenciaDomicilio("");
         domi.setListaProductos(new ArrayList<ProductoDTO>());
         domi.setComuna("comuna1");
         lCdDTO.add(domi);
         

         domi.setTipoDomicilio("PRI");
         domi.setDomicilioId("99999999");
         domi.setApt("1234");
         domi.setCalle("Calle");
         domi.setCaracteristica("22");
         domi.setCodigoPostal("1234");
         domi.setLocalidad("fabela");
         domi.setDescProvincia("Brasilia");
         domi.setDescPais("Brasil");
         
         domi.setComuna("");
         domi.setDepartamento("");
         domi.setNumeroTelefono("");
         domi.setPiso("");
         domi.setPrefijo("");
         domi.setProvincia("");
         domi.setTelefono("");
         domi.setSecuenciaDomicilio("");
         domi.setListaProductos(new ArrayList<ProductoDTO>());
         domi.setComuna("comuna1");
         lCdDTO.add(domi);
           	
    	when(sesionParametros.getDomiciliosCliente()).thenReturn(lCdDTO);
    	when(cambioDomicilioBO.consultarDomiciliosRegistrados()).thenReturn(respuestaBo);
    	
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<DomiciliosDisponiblesView> respuesta = cambioDomicilioManager.consultarDomicilioPrincipalLaboral();
    	
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    /**
     * consultar domicilio principal laboral: ERROR el cliente es null y respuestaBo tiene un estado de respuesta "ERROR"
     */
    @Test
    public void consultarDomicilioPrincipalLaboralTestERROR() {
    	
    	 Respuesta<List<CambioDomicilioDTO>> respuestaBo = new Respuesta<List<CambioDomicilioDTO>>();
         respuestaBo.setRespuesta(lCdDTO);
         respuestaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
         
    	
    	when(sesionParametros.getDomiciliosCliente()).thenReturn(null);
    	when(cambioDomicilioBO.consultarDomiciliosRegistrados()).thenReturn(respuestaBo);
    	
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	Respuesta<DomiciliosDisponiblesView> respuesta = cambioDomicilioManager.consultarDomicilioPrincipalLaboral();
    	
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
}
