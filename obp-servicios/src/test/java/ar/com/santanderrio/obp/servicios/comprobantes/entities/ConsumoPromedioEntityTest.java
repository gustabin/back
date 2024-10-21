package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConsumoPromedioEntityTest {

    
    @Test
    public void hashEqualsTest(){
        ConsumoPromedioEntity consumo = new ConsumoPromedioEntity();
        consumo.setDolares("124,50");
        consumo.setPesos("133,20");
        
        ConsumoPromedioEntity consumo2 = new ConsumoPromedioEntity();
        consumo2.setDolares("125,50");
        consumo2.setPesos("134,20");
        
        Assert.assertTrue(!consumo.equals(consumo2));
        Assert.assertTrue(consumo.equals(consumo));
        
        Assert.assertTrue(consumo.hashCode() != consumo2.hashCode());
        Assert.assertTrue(consumo.hashCode() == consumo.hashCode());
    }
    
    @Test
    public void toStringTest(){
        ConsumoPromedioEntity consumo = new ConsumoPromedioEntity();
        consumo.setDolares("124,50");
        consumo.setPesos("133,20");
        Assert.assertEquals("124,50, 133,20", consumo.toString());
    }
}
