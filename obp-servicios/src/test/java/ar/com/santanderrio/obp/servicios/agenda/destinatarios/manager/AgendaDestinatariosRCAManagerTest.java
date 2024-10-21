package ar.com.santanderrio.obp.servicios.agenda.destinatarios.manager;

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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosRCAManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.impl.AgendaDestinatariosRCAManagerImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class AgendaDestinatariosRCAManagerTest {

    @InjectMocks
    AgendaDestinatariosRCAManager agendaDestinatariosRCAManager = new AgendaDestinatariosRCAManagerImpl();

    @Mock
    private AutentificacionManager autentificacionManager;
    
    @Mock
    private SesionAgendaDestinatarios sesionAgenda;

    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Before
    public void initMocks() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(agendaDestinatariosRCAManager, "valorDesafio", "1", true);
    }

    @Test
    public void validacionRSAConDesafioPrevioParaAltaDestinatarioOK() {
        // When
        ConfirmacionAltaDestinatarioView confirmacionAltaDestinatario = new ConfirmacionAltaDestinatarioView();
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        confirmacionAltaDestinatario.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                confirmacionAltaDestinatario, TipoOperacionACTAGEDESTEnum.ALTA, TipoAgendaEnum.AGENDA_OTROS_BANCOS);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
   
    
    @Test
    public void validacionRSASinDesafioPrevioParaAltaDestinatarioOK() {
    	
        // When
        ConfirmacionAltaDestinatarioView confirmacionAltaDestinatario = new ConfirmacionAltaDestinatarioView();
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        confirmacionAltaDestinatario.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respAutentificacionDTO.setRespuesta(autentificacionDTO);
        respAutentificacionDTO.getRespuesta().setBloquearUsuario(false);
        
        List<ItemMensajeRespuesta> listaItemMensaje = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta mensaje = new ItemMensajeRespuesta();
        mensaje.setTipoError("ERROR GENERICO");
        listaItemMensaje.add(mensaje);
        respAutentificacionDTO.setItemMensajeRespuesta(listaItemMensaje);
        
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);
        
        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                confirmacionAltaDestinatario, TipoOperacionACTAGEDESTEnum.ALTA, TipoAgendaEnum.AGENDA_OTROS_BANCOS);
        
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
    
    
    @Test
    public void validacionRSASinDesafioPrevioParaAltaDestinatarioAliasRioOK() {
        // When
        ConfirmacionAltaDestinatarioView confirmacionAltaDestinatario = new ConfirmacionAltaDestinatarioView();
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        confirmacionAltaDestinatario.setDesafio(autentificacionDTO);
        confirmacionAltaDestinatario.setIsRio(true);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        
        respAutentificacionDTO.setRespuesta(autentificacionDTO);
        respAutentificacionDTO.getRespuesta().setBloquearUsuario(false);
        
        List<ItemMensajeRespuesta> listaItemMensaje = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta mensaje = new ItemMensajeRespuesta();
        mensaje.setTipoError("ERROR GENERICO");
        listaItemMensaje.add(mensaje);
        respAutentificacionDTO.setItemMensajeRespuesta(listaItemMensaje);
        
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                confirmacionAltaDestinatario, TipoOperacionACTAGEDESTEnum.ALTA, TipoAgendaEnum.AGENDA_ALIAS);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validacionRSASinDesafioPrevioParaAltaDestinatarioAliasOtrosBancosOK() {
        // When
        ConfirmacionAltaDestinatarioView confirmacionAltaDestinatario = new ConfirmacionAltaDestinatarioView();
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        confirmacionAltaDestinatario.setDesafio(autentificacionDTO);
        confirmacionAltaDestinatario.setIsRio(false);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
       
        respAutentificacionDTO.setRespuesta(autentificacionDTO);
        respAutentificacionDTO.getRespuesta().setBloquearUsuario(false);
        
        List<ItemMensajeRespuesta> listaItemMensaje = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta mensaje = new ItemMensajeRespuesta();
        mensaje.setTipoError("ERROR GENERICO");
        listaItemMensaje.add(mensaje);
        respAutentificacionDTO.setItemMensajeRespuesta(listaItemMensaje);
        
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                confirmacionAltaDestinatario, TipoOperacionACTAGEDESTEnum.ALTA, TipoAgendaEnum.AGENDA_ALIAS);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
    
    
    @Test
    public void validacionRSASinDesafioPrevioParaAltaDestinatarioErrorSinDesafioHabilitado() {
        // When
        ConfirmacionAltaDestinatarioView confirmacionAltaDestinatario = new ConfirmacionAltaDestinatarioView();
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        confirmacionAltaDestinatario.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                confirmacionAltaDestinatario, TipoOperacionACTAGEDESTEnum.ALTA, TipoAgendaEnum.AGENDA_OTROS_BANCOS);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    
    @Test
    public void validacionRSASinDesafioPrevioParaModificacionDestinatarioOK() {
        // When
        ConfirmacionAltaDestinatarioView confirmacionAltaDestinatario = new ConfirmacionAltaDestinatarioView();
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        confirmacionAltaDestinatario.setDesafio(autentificacionDTO);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);

        respAutentificacionDTO.setRespuesta(autentificacionDTO);
        respAutentificacionDTO.getRespuesta().setBloquearUsuario(false);
        
        List<ItemMensajeRespuesta> listaItemMensaje = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta mensaje = new ItemMensajeRespuesta();
        mensaje.setTipoError("ERROR GENERICO");
        listaItemMensaje.add(mensaje);
        respAutentificacionDTO.setItemMensajeRespuesta(listaItemMensaje);
        
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                confirmacionAltaDestinatario, TipoOperacionACTAGEDESTEnum.MODIFICACION, TipoAgendaEnum.AGENDA_OTROS_BANCOS);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void validarSiPoseeSegundoFactorAutenticacionTest() {
		AutentificacionDTO autenticacionDTO = new AutentificacionDTO();
		autenticacionDTO.setOperacion(Integer.parseInt("1"));
		Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
    	when(autentificacionManager.tieneAlgunDesafioHabilitadoParaLaOperacion(autenticacionDTO)).thenReturn(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
}
