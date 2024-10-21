package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobantePMCDTOTest {

    @Test
    public void equalsTest(){
        DetalleComprobantePMCDTO detalle = new DetalleComprobantePMCDTO();
        detalle.setFactura("123A");
        
        DetalleComprobantePMCDTO detalle2 = new DetalleComprobantePMCDTO();
        detalle2.setFactura("123C");
        
        Assert.assertTrue(!detalle.equals(detalle2));
        Assert.assertTrue(detalle.equals(detalle));
    }
    
    @Test
    public void hashTest(){
        DetalleComprobantePMCDTO detalle = new DetalleComprobantePMCDTO();
        detalle.setFactura("123A");
        
        DetalleComprobantePMCDTO detalle2 = new DetalleComprobantePMCDTO();
        detalle2.setFactura("123C");
        
        Assert.assertTrue(detalle.hashCode()!=detalle2.hashCode());
        Assert.assertTrue(detalle.hashCode()==detalle.hashCode());
    }
    
    @Test
    public void toStringTest(){
        DetalleComprobantePMCDTO detalle = new DetalleComprobantePMCDTO();
        detalle.setFactura("123A");
        
        String res = detalle.toString();
        
        Assert.assertTrue(res.contains("DetalleComprobantePMCDTO [factura=123A]"));
    }
}
