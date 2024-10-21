package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteTransferenciaViewTest {

    @Test
    public void ponerParametrosTransferenciaTest(){
        DetalleComprobanteTransferenciaView view = new DetalleComprobanteTransferenciaView();
        view.setBanco("banco");
        view.setComentarios("Comentarios");
        
        HashMap<String, Object> diccionario = new HashMap<String, Object>();
        view.ponerParametrosTransferencia(diccionario);
        
        Assert.assertEquals("banco", diccionario.get("BANCO"));
    }

}
