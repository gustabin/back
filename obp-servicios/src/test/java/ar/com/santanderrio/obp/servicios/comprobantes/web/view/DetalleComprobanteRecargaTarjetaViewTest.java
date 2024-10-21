package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class DetalleComprobanteRecargaTarjetaViewTest {
    @Test
    public void ponerParametrosTransferenciaTest() throws IOException{
        DetalleComprobanteRecargaTarjetaView view = new DetalleComprobanteRecargaTarjetaView();
        view.setCuit("20378668817");
        view.setEmpresa("Claro");
        view.setDestinatario("jose hernandez");        
        HashMap<String, Object> diccionario = new HashMap<String, Object>();
        view.ponerParametrosTarjetaRecargable(diccionario);
        
        Assert.assertEquals("jose hernandez", diccionario.get("DESTINATARIO"));
    }
}
