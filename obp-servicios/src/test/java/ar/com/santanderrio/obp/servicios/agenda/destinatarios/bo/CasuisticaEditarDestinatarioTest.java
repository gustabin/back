package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.CasuisticaEdicionDestinatarioImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.DestinatarioCommon;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class CasuisticaEditarDestinatarioTest {
    
    /** The casuistica agenda destinatarios. */
    @InjectMocks
    private CasuisticaEdicionDestinatario casuisticaEdicionDestinatario = new CasuisticaEdicionDestinatarioImpl();
    
    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;
    
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private DestinatarioCommon destinatario;
    
    
    @Test
    public void edicionRioOKTest () {
        AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity();
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        inEntity.setDescripcionCuentaDestinatario("Prueba");
        outEntity.setFecha("10102017");
        outEntity.setHora("16:00");
        outEntity.setNroComprobante("12345678");
        outEntity.setCodigoRetornoExtendido("00000000");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = casuisticaEdicionDestinatario.crearRespuestaEdicion(outEntity, inEntity);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        
    }
    
    @Test
    public void edicionRioCtaDadaDeBajaTest() { 
        AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity();
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        inEntity.setDescripcionCuentaDestinatario("Prueba");
        outEntity.setFecha("10102017");
        outEntity.setHora("16:00");
        outEntity.setNroComprobante("12345678");
        outEntity.setCodigoRetornoExtendido("10011634");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = casuisticaEdicionDestinatario.crearRespuestaEdicion(outEntity, inEntity);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(), "errorDestinatarioInvalidoEditar");
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), TipoError.ERROR_EDITAR_DESTINATARIO_INVALIDO.getDescripcion());
        
    }
    
    @Test
    public void edicionRioTimeOutTest() {
        AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity();
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        inEntity.setDescripcionCuentaDestinatario("Prueba");
        outEntity.setFecha("10102017");
        outEntity.setHora("16:00");
        outEntity.setNroComprobante("12345678");
        outEntity.setCodigoRetornoExtendido("23582358");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = casuisticaEdicionDestinatario.crearRespuestaEdicion(outEntity, inEntity);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(), "errorServicio");
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_EDICION.getTipoError().getDescripcion());        
    }
    
    @Test
    public void edicionOBOKTest () {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity();
        inEntity.setDescripcionCuentaDestinatario("Prueba");
        outEntity.setFecha("10102017");
        outEntity.setHora("16:00");
        outEntity.setNroComprobante("12345678");
        outEntity.setCodigoRetornoExtendido("00000000");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = casuisticaEdicionDestinatario.crearRespuestaEdicion(outEntity, inEntity);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        
    }
    
    @Test
    public void edicionOBCtaDadaDeBajaTest () {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity();
        inEntity.setDescripcionCuentaDestinatario("Prueba");
        outEntity.setFecha("10102017");
        outEntity.setHora("16:00");
        outEntity.setNroComprobante("12345678");
        outEntity.setCodigoRetornoExtendido("10014006");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = casuisticaEdicionDestinatario.crearRespuestaEdicion(outEntity, inEntity);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(), "errorDestinatarioInvalidoEditar");
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), TipoError.ERROR_EDITAR_DESTINATARIO_INVALIDO.getDescripcion());
        
    }
    
    @Test
    public void edicionOBTimeOutTest() {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity();
        inEntity.setDescripcionCuentaDestinatario("Prueba");
        outEntity.setFecha("10102017");
        outEntity.setHora("16:00");
        outEntity.setNroComprobante("12345678");
        outEntity.setCodigoRetornoExtendido("23582358");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = casuisticaEdicionDestinatario.crearRespuestaEdicion(outEntity, inEntity);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(), "errorServicio");
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_EDICION.getTipoError().getDescripcion());        
    }
    
    @Test
    public void edicionEnvioEfectivoOKTest () {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity();
        inEntity.setDescripcionCuentaDestinatario("Prueba");
        outEntity.setFecha("10102017");
        outEntity.setHora("16:00");
        outEntity.setNroComprobante("12345678");
        outEntity.setCodigoRetornoExtendido("00000000");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = casuisticaEdicionDestinatario.crearRespuestaEdicion(outEntity, inEntity);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        
    }
    
    @Test
    public void edicionEnvioEfectivoTimeOutTest() {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity();
        inEntity.setDescripcionCuentaDestinatario("Prueba");
        outEntity.setFecha("10102017");
        outEntity.setHora("16:00");
        outEntity.setNroComprobante("12345678");
        outEntity.setCodigoRetornoExtendido("23582358");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = casuisticaEdicionDestinatario.crearRespuestaEdicion(outEntity, inEntity);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(), "errorServicio");
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_EDICION.getTipoError().getDescripcion());        
    }

}
