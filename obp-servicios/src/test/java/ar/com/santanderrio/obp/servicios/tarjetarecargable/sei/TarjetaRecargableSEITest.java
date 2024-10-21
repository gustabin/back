package ar.com.santanderrio.obp.servicios.tarjetarecargable.sei;

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
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.sei.impl.TarjetaRecargableSEIImpl;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.manager.TarjetaRecargableManager;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.ComprobanteAltaSolicitudTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosSolicitanteTarjetaRecargableView;

@RunWith(MockitoJUnitRunner.class)
public class TarjetaRecargableSEITest {
    
    @InjectMocks
    private TarjetaRecargablelSEI tarjetaRecargablesSEI = new TarjetaRecargableSEIImpl();
    
    @Mock
    private TarjetaRecargableManager tarjetaRecargableManager;
    
    @Test
    public void obtenerDatosIniciales() {
        Respuesta<DatosSolicitanteTarjetaRecargableView> respuesta = new Respuesta<DatosSolicitanteTarjetaRecargableView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Mockito.when(tarjetaRecargableManager.obtenerDatosIniciales()).thenReturn(respuesta);
        respuesta = tarjetaRecargablesSEI.obtenerDatosIniciales();
        
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void altaSolicitudTarjetaRecargable() {
        Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> respuesta = new Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        ComprobanteAltaSolicitudTarjetaRecargableView comprobante = new ComprobanteAltaSolicitudTarjetaRecargableView();
        
        Mockito.when(tarjetaRecargableManager.altaSolicitudTarjetaRecargable(Matchers.any(ComprobanteAltaSolicitudTarjetaRecargableView.class))).thenReturn(respuesta);
        respuesta = tarjetaRecargablesSEI.altaSolicitudTarjetaRecargable(comprobante);
        
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void comprobanteSolicitudTarjetaRecargable() {
        Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Mockito.when(tarjetaRecargableManager.comprobanteSolicitudTarjetaRecargable()).thenReturn(respuesta);
        respuesta = tarjetaRecargablesSEI.comprobanteSolicitudTarjetaRecargable();
        
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void vaciarDesafio() {
        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Mockito.when(tarjetaRecargableManager.vaciarDesafio()).thenReturn(respuesta);
        respuesta = (Respuesta<Void>) tarjetaRecargablesSEI.vaciarDesafio();
        
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
}
