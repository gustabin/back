
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity.DatosEntradaAgendaDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei.impl.AumentoLimiteTransferenciaSEIImpl;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.AumentoLimiteTransferenciasManager;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AgendaDestinatarioLimiteTransferenciasView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

@RunWith(MockitoJUnitRunner.class)
public class AumentoLimiteTransferenciaSEIT{

    @InjectMocks
    private AumentoLimiteTransferenciaSEIImpl aumentoLimiteTransferenciaSEIImpl = new AumentoLimiteTransferenciaSEIImpl();
    
    @Mock
    private AgendaDestinatariosManager agendaDestinatariosManager;
    
    @Mock
    private TransferenciaManager transferenciaManager;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private AumentoLimiteTransferenciasManager aumentoLimiteTransferenciasManager;
    
    @Test
    public void obtenerAgendaDestinatarios() {
        Respuesta<AgendaDestinatarioLimiteTransferenciasView> respuestaFinal = new Respuesta<AgendaDestinatarioLimiteTransferenciasView>();
        respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(aumentoLimiteTransferenciasManager.verificarTokenAsociado()).thenReturn(respuestaFinal);
        Respuesta<AgendaDestinatarioView> respuestaAgendaDestinatariosManager = new Respuesta<AgendaDestinatarioView>();
        respuestaAgendaDestinatariosManager.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosEntradaAgendaDestinatario dato = new DatosEntradaAgendaDestinatario();
        Mockito.when(agendaDestinatariosManager.obtenerAgendaDestinatarios(dato)).thenReturn(respuestaAgendaDestinatariosManager);
        
        respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Mockito.when(aumentoLimiteTransferenciasManager.obtenerAgendaDestinatarios(respuestaAgendaDestinatariosManager)).thenReturn(respuestaFinal);
        respuestaFinal = aumentoLimiteTransferenciaSEIImpl.obtenerAgendaDestinatarios(dato);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
    }
    
    @Test
    public void obtenerInformacionDestinatario() {
        Respuesta<TransferenciaView> respuestaTransferenciaManager = new Respuesta<TransferenciaView>();
        Respuesta<AumentoTransferenciaView> respuestaFinal = new Respuesta<AumentoTransferenciaView>();
        respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
        
        
        DestinatarioView destinatarioView = new DestinatarioView(obtenerDestinatarioAgendaDTO());
        Mockito.when(aumentoLimiteTransferenciasManager.obtenerInformacionDestinatario(Matchers.any(DestinatarioView.class))).thenReturn(respuestaFinal);
        respuestaFinal = aumentoLimiteTransferenciaSEIImpl.obtenerInformacionDestinatario(destinatarioView);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
    }
    
    
    @Test
    public void consultarTitularidad() {
        Respuesta<TransferenciaView> respuestaFinal = new Respuesta<TransferenciaView>();
        respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
        TransferenciaView transferencia = new TransferenciaView();
        Mockito.when(transferenciaManager.consultarTitularidad(Matchers.any(TransferenciaView.class), Matchers.anyString())).thenReturn(respuestaFinal);
        respuestaFinal = aumentoLimiteTransferenciaSEIImpl.consultarTitularidad(transferencia, Matchers.any(MessageContext.class));
        Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
    }
    
    @Test
    public void altaSolicitudAumentoLimiteTransferencia() {
        Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal = new Respuesta<AumentoLimiteTransferenciaInOutView>();
        respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
        AumentoLimiteTransferenciaInOutView inView = new AumentoLimiteTransferenciaInOutView();
        Mockito.when(aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(Matchers.any(AumentoLimiteTransferenciaInOutView.class))).thenReturn(respuestaFinal);
        respuestaFinal = aumentoLimiteTransferenciaSEIImpl.altaSolicitudAumentoLimiteTransferencia(inView);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
    }

    @Test
    public void generarComprobanteAumentoLimiteTransferencia() {
        Respuesta<ReporteView> respuestaReporte = new Respuesta<ReporteView>();
        respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(aumentoLimiteTransferenciasManager.generarComprobanteAumentoLimiteTransferencia()).thenReturn(respuestaReporte);
        respuestaReporte = aumentoLimiteTransferenciaSEIImpl.generarComprobanteAumentoLimiteTransferencia();
        Assert.assertEquals(EstadoRespuesta.OK, respuestaReporte.getEstadoRespuesta());
    }
    
    private DestinatarioAgendaDTO obtenerDestinatarioAgendaDTO() {
        DestinatarioAgendaDTO dto = new DestinatarioAgendaDTO();
        dto.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        dto.setCuitCuil("-");
        dto.setEsCuentaPropia(Boolean.FALSE);
        dto.setMuestraReferenciaApodo(Boolean.TRUE);
        dto.setNroCuenta("345-567890/1");
        dto.setReferenciaApodo("Club Arg");
        dto.setTipoAgendaEnum(TipoAgendaEnum.AGENDA_RIO);
        dto.setTipoCuenta("Cuenta corriente en u$s");
        dto.setTipoCuentaAbreviatura("CCD");
        dto.setTitular("Club Arg");
        return dto;
    }
}
