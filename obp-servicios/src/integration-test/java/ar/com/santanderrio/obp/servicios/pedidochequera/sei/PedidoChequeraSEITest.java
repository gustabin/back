package ar.com.santanderrio.obp.servicios.pedidochequera.sei;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.manager.PedidoChequeraManager;
import ar.com.santanderrio.obp.servicios.chequera.sei.impl.ChequeraSEIImpl;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionInView;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraViewResponse;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteView;


@RunWith(MockitoJUnitRunner.class)
public class PedidoChequeraSEITest {

    @InjectMocks
    private ChequeraSEIImpl chequerasSEI = new ChequeraSEIImpl();
    
    @Mock
    private PedidoChequeraManager pedidoChequeraManager;
    
    @Test
    public void consultarChequera() {
        Respuesta<ChequeraViewResponse> respuesta = new Respuesta<ChequeraViewResponse>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(pedidoChequeraManager.getDatosChequera()).thenReturn(respuesta);
        respuesta = chequerasSEI.consultarChequera();
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void confirmarChequera(){
        Respuesta<ChequeraConfirmacionViewResponse> respuesta = new Respuesta<ChequeraConfirmacionViewResponse>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        ChequeraConfirmacionInView viewRequest = new ChequeraConfirmacionInView();
        
        Mockito.when(pedidoChequeraManager.confirmarPedidoChequera(viewRequest)).thenReturn(respuesta);
        respuesta = chequerasSEI.confirmarChequera(viewRequest);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void confirmarChequeraSeisMeses(){
        Respuesta<ChequeraConfirmacionViewResponse> respuesta = new Respuesta<ChequeraConfirmacionViewResponse>();
        
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ChequeraConfirmacionInView viewRequest = new ChequeraConfirmacionInView();

        Mockito.when(pedidoChequeraManager.confirmarPedidoChequera(viewRequest)).thenReturn(respuesta);
        respuesta = chequerasSEI.confirmarChequera(viewRequest);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void generarComprobanteChequera() {
        Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
        ChequeraConfirmacionViewResponse chequeraConfirmacionView = new ChequeraConfirmacionViewResponse();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Mockito.when(pedidoChequeraManager.generarComprobanteChequera(chequeraConfirmacionView)).thenReturn(respuesta);
        respuesta = chequerasSEI.generarComprobanteChequera(chequeraConfirmacionView);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
}
