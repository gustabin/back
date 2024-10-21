package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * 
 * @author dante.omar.olmedo
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DebitoEntityTest {

    @Test
    public void debitoEntityEqualsTest() {
        EstablecimientoEntity establecimiento = new EstablecimientoEntity();
        establecimiento.setCodEstablecimiento("13523");
        establecimiento.setRubro("Comercio");
        
        ImporteEntity importe = new ImporteEntity();
        importe.setDolares("202,59");
        importe.setPesos("304,12");
        
        DebitoEntity debito = new DebitoEntity();
        debito.setDescripcion("descripcion");
        debito.setFecha("20170802");
        debito.setImporte(importe );
        debito.setEstablecimiento(establecimiento);
        
        
        EstablecimientoEntity establecimiento2 = new EstablecimientoEntity();
        establecimiento2.setCodEstablecimiento("13523");
        establecimiento2.setRubro("Comercio");
        
        ImporteEntity importe2 = new ImporteEntity();
        importe2.setDolares("202,59");
        importe2.setPesos("304,12");
        
        DebitoEntity debito2 = new DebitoEntity();
        debito2.setDescripcion("descripcion");
        debito2.setFecha("20170802");
        debito2.setImporte(importe2 );
        debito2.setEstablecimiento(establecimiento2);
        
        //Mismos campos en base, entonces iguales
        Assert.assertTrue(debito.equals(debito2));
        
        establecimiento2.setCodEstablecimiento("13524");
        
        //Establecimiento distinto rompe igualdad observacional
        Assert.assertTrue(!debito.equals(debito2));
        
        establecimiento2.setCodEstablecimiento("13523");
        Assert.assertTrue(debito.equals(debito2));
        
        importe2.setDolares("203,60");
        
        //ImporteEntity distinto rompe igualdad observacional tambien
        Assert.assertTrue(!debito.equals(debito2));
        
        importe2.setDolares("202,59");
        Assert.assertTrue(debito.equals(debito2));
        
        debito2.setFecha("20160802");
        
        //Fecha en la entidad principal tambien
        Assert.assertTrue(!debito.equals(debito2));
    }
    
    @Test
    public void debitoEntityHashTest() {
        EstablecimientoEntity establecimiento = new EstablecimientoEntity();
        establecimiento.setCodEstablecimiento("13523");
        establecimiento.setRubro("Comercio");
        
        ImporteEntity importe = new ImporteEntity();
        importe.setDolares("202,59");
        importe.setPesos("304,12");
        
        DebitoEntity debito = new DebitoEntity();
        debito.setDescripcion("descripcion");
        debito.setFecha("20170802");
        debito.setImporte(importe );
        debito.setEstablecimiento(establecimiento);
        
        
        EstablecimientoEntity establecimiento2 = new EstablecimientoEntity();
        establecimiento2.setCodEstablecimiento("13523");
        establecimiento2.setRubro("Comercio");
        
        ImporteEntity importe2 = new ImporteEntity();
        importe2.setDolares("202,59");
        importe2.setPesos("304,12");
        
        DebitoEntity debito2 = new DebitoEntity();
        debito2.setDescripcion("descripcion");
        debito2.setFecha("20170802");
        debito2.setImporte(importe2 );
        debito2.setEstablecimiento(establecimiento2);
        
        //Mismos campos en base, entonces iguales
        Assert.assertTrue(debito.hashCode() == debito2.hashCode());
        
        establecimiento2.setCodEstablecimiento("13524");
        
        //Establecimiento distinto rompe igualdad observacional
        Assert.assertTrue(debito.hashCode() != debito2.hashCode());
        
        establecimiento2.setCodEstablecimiento("13523");
        Assert.assertTrue(debito.hashCode() == debito2.hashCode());
        
        importe2.setDolares("203,60");
        
        //ImporteEntity distinto rompe igualdad observacional tambien
        Assert.assertTrue(debito.hashCode() != debito2.hashCode());
        
        importe2.setDolares("202,59");
        Assert.assertTrue(debito.hashCode() == debito2.hashCode());
        
        debito2.setFecha("20160802");
        
        //Fecha en la entidad principal tambien
        Assert.assertTrue(debito.hashCode() != debito2.hashCode());
    }
    
}
