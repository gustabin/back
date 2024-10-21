package ar.com.santanderrio.obp.servicios.perfil.manager;

import static org.mockito.Mockito.when;

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

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.PreguntasSeguridadBO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.DatosDomTelOutDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ModificacionCambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ProductoDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ResultadoModificacionDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.manager.impl.PreguntasSeguridadManagerImpl;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ComprobantePreguntasSeguridadView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntaSeguridadView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntasSeguridadView;

@RunWith(MockitoJUnitRunner.class)
public class PreguntasSeguridadManagerTest {

    
    /** The preguntasSeguridadManager . */
    @InjectMocks
    private PreguntasSeguridadManager preguntasSeguridadManager = new PreguntasSeguridadManagerImpl();

    
    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;;
    
    @Mock
    private PreguntasSeguridadBO preguntasSeguridadBO;

    
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private MensajeDAO mensajeDAO;
    
    @Mock
    private LegalBO legalBO;
    
    @Mock
    private MensajeManager mensajeManager;
    
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    private SesionParametros sesionParametros;


    @Mock
    private AutentificacionManager autentificacionManager;
   
    /** The desafioOperacionRSA. */
    @Mock
    private DesafioOperacionRSA<PreguntasSeguridadView> desafioOperacionRSA;
       
    Mensaje mensaje = new Mensaje();
    ModificacionCambioDomicilioDTO domicilioModif = new ModificacionCambioDomicilioDTO();
    List<CambioDomicilioDTO> lCdDTO = new ArrayList<CambioDomicilioDTO>();
    CambioDomicilioView cdView = new CambioDomicilioView();
    
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        //Configuracion lista domicilios en sesion
        CambioDomicilioDTO cdDTO = new CambioDomicilioDTO();
        cdDTO.setDomicilioId("99999999");
        cdDTO.setApt("1234");
        cdDTO.setCalle("Calle");
        cdDTO.setCodigoPostal("1234");
        cdDTO.setComuna("");
        cdDTO.setPiso("1");
        cdDTO.setDepartamento("A");
        cdDTO.setLocalidad("Avellaneda");
        cdDTO.setMarcaDomErroneo("");
        cdDTO.setObservaciones("");
        cdDTO.setPais("02");
        cdDTO.setSecuenciaDomicilio("001");
        cdDTO.setSucursal("");
        cdDTO.setProvincia("08");
        cdDTO.setTelefono("34567");
        cdDTO.setPrefijo("011");
        cdDTO.setCaracteristica("22");
        cdDTO.setNumeroTelefono("344543634543");
        cdDTO.setTipoDomicilio("PRI");
        cdDTO.setDescProvincia("Buenos Aires");
        cdDTO.setDescPais("Argentina");
        cdDTO.setListaProductos(new ArrayList<ProductoDTO>());
        lCdDTO.add(cdDTO);
        //Configuracion domicilio modificado de sesion
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
        
        //Configuracion view
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
        
    }     

    
    @Test
    public void guardarPreguntasSeguridadTest() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        List<PreguntaSeguridadView> preguntas = new ArrayList<PreguntaSeguridadView>();
        PreguntaSeguridadView r1 = new PreguntaSeguridadView();
        r1.setRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r1.setId("050");
        PreguntaSeguridadView r2 = new PreguntaSeguridadView();
        r2.setRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r2.setId("051");
        PreguntaSeguridadView r3 = new PreguntaSeguridadView();
        r3.setRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r3.setId("052");
        PreguntaSeguridadView r4 = new PreguntaSeguridadView();
        r4.setRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r4.setId("053");
        PreguntaSeguridadView r5 = new PreguntaSeguridadView();
        r5.setRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r5.setId("054");
        preguntas.add(r1);
        preguntas.add(r2);
        preguntas.add(r3);
        preguntas.add(r4);
        preguntas.add(r5);
        
        PreguntasSeguridadView preguView = new PreguntasSeguridadView();
        preguView.setListaPreguntas(preguntas);
        Respuesta<AutentificacionDTO> respuestaAutentificacion = new Respuesta<AutentificacionDTO>();
        respuestaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Respuesta<ModificacionPreguntasSeguridadOutEntity> respuestaBo = new Respuesta<ModificacionPreguntasSeguridadOutEntity>();
        ModificacionPreguntasSeguridadOutEntity modif = new ModificacionPreguntasSeguridadOutEntity();
        modif.setNroComprobante("12312321");
        respuestaBo.setRespuesta(modif);
        
        Respuesta<PreguntasSeguridadView> respuesta = new Respuesta<PreguntasSeguridadView>();
        respuestaBo.setEstadoRespuesta(EstadoRespuesta.OK);
        when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(respuestaAutentificacion);
        when(preguntasSeguridadBO.guardarPreguntasSeguridad(preguntas)).thenReturn(respuestaBo); 
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
 
        Respuesta<PreguntasSeguridadView> respuestaRSA = new Respuesta<PreguntasSeguridadView>();
        respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(PreguntasSeguridadView.class),Matchers.any(Integer.class), Matchers.isNull(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
        AutentificacionDTO dto = new AutentificacionDTO();
        dto.setTipoDesafio(TipoDesafioEnum.TOKEN);
        preguView.setDesafio(dto);
        respuesta = preguntasSeguridadManager.guardarPreguntasSeguridad(preguView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
      
    
    
    
    @Test
    public void guardarPreguntasSeguridadErrorTest() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        List<PreguntaSeguridadView> preguntas = new ArrayList<PreguntaSeguridadView>();
        PreguntasSeguridadView preguView = new PreguntasSeguridadView();
        preguView.setListaPreguntas(preguntas);
        Respuesta<AutentificacionDTO> respuestaAutentificacion = new Respuesta<AutentificacionDTO>();
        respuestaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<ModificacionPreguntasSeguridadOutEntity> respuestaBo = new Respuesta<ModificacionPreguntasSeguridadOutEntity>();
        Respuesta<PreguntasSeguridadView> respuesta = new Respuesta<PreguntasSeguridadView>();
        respuestaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(respuestaAutentificacion);
        when(preguntasSeguridadBO.guardarPreguntasSeguridad(preguntas)).thenReturn(respuestaBo); 
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<PreguntasSeguridadView> respuestaRSA = new Respuesta<PreguntasSeguridadView>();
        respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(PreguntasSeguridadView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
               
        AutentificacionDTO dto = new AutentificacionDTO();
        dto.setTipoDesafio(TipoDesafioEnum.TOKEN);
        preguView.setDesafio(dto);
        respuesta = preguntasSeguridadManager.guardarPreguntasSeguridad(preguView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    

    @Test
    public void consultarPreguntasSeguridadTest() {
        Respuesta<PreguntasSeguridadView> respuestaBo = new  Respuesta<PreguntasSeguridadView>();
        Respuesta<PreguntasSeguridadView>   respuestaFinal =  new Respuesta<PreguntasSeguridadView>(); 
        PreguntasSeguridadView pregView = new PreguntasSeguridadView();
        pregView.setTienePreguntasCargadas(true);
        respuestaBo.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBo.setRespuesta(pregView);
        when(preguntasSeguridadBO.consultarPreguntasSeguridad()).thenReturn(respuestaBo); 
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        respuestaFinal = preguntasSeguridadManager.consultaPreguntasSeguridad();
        Assert.assertNotNull(respuestaFinal);
        Assert.assertEquals(respuestaFinal.getEstadoRespuesta(), EstadoRespuesta.OK);
    }    
    
    
    @Test
    public void consultarPreguntasSeguridadErrorTest() {
        Respuesta<PreguntasSeguridadView> respuestaBo = new  Respuesta<PreguntasSeguridadView>();
        Respuesta<PreguntasSeguridadView>   respuestaFinal =  new Respuesta<PreguntasSeguridadView>(); 
        respuestaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
        PreguntasSeguridadView pregView = new PreguntasSeguridadView();
        pregView.setTienePreguntasCargadas(true);
        respuestaBo.setRespuesta(pregView);
        respuestaBo.getRespuesta().setTienePreguntasCargadas(false);
        when(preguntasSeguridadBO.consultarPreguntasSeguridad()).thenReturn(respuestaBo); 
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        respuestaFinal = preguntasSeguridadManager.consultaPreguntasSeguridad();
        Assert.assertNotNull(respuestaFinal);
        Assert.assertEquals(respuestaFinal.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }    
    
    

    @Test
    public  void verComprobante() throws DAOException{
        when(sesionParametros.getNroComprobante()).thenReturn("12345679");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn("Conserve este comprobante SEUO");
        Respuesta<ComprobantePreguntasSeguridadView>  comprobante = preguntasSeguridadManager.verComprobante();
        Assert.assertNotNull(comprobante);
        Assert.assertEquals(comprobante.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    

    
    @Test
    public void descargarComprobanteTest(){
         Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>(); 
         when(preguntasSeguridadBO.descargarComprobante(Matchers.any(DatosComprobanteEntity.class))).thenReturn(respuestaReporte); 
         respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
         Respuesta<ReporteView> respuesta = preguntasSeguridadManager.descargarComprobante();
         Assert.assertNotNull(respuesta);
         Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
         

    }
    @Test
    public void descargarComprobanteErrorTest(){
         Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>(); 
         when(preguntasSeguridadBO.descargarComprobante(Matchers.any(DatosComprobanteEntity.class))).thenReturn(respuestaReporte); 
         respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);
         when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
         when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
         Respuesta<ReporteView> respuesta = preguntasSeguridadManager.descargarComprobante();
         Assert.assertNotNull(respuesta);
         Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
         

    }
    
    
    
}
