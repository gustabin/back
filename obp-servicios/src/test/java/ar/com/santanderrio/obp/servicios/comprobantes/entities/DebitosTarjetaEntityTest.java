package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.Totales;

@RunWith(MockitoJUnitRunner.class)
public class DebitosTarjetaEntityTest {

    @Test
    public void debitoTarjetaEntityEqualsTest() {
        Totales totales = new Totales();
        totales.setDolares("125,54");
        totales.setPesos("134,55");
        
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
        
        DebitosTarjetaEntity debitos = new DebitosTarjetaEntity();
        debitos.setListDebitos(new ArrayList<DebitoEntity>());
        debitos.setNombreTarjeta("Tarjeta santander");
        debitos.setTotales(totales );
        
        DebitosTarjetaEntity debitos2 = new DebitosTarjetaEntity();
        debitos2.setListDebitos(new ArrayList<DebitoEntity>());
        debitos2.getListDebitos().add(debito);
        debitos2.setNombreTarjeta("Tarjeta santander");
        debitos2.setTotales(totales );
        
        Assert.assertTrue(!debitos.equals(debitos2));
        Assert.assertTrue(debitos.equals(debitos));
    }
    
    @Test
    public void debitoTarjetaEntityHashTest() {
        Totales totales = new Totales();
        totales.setDolares("125,54");
        totales.setPesos("134,55");
        
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
        
        DebitosTarjetaEntity debitos = new DebitosTarjetaEntity();
        debitos.setListDebitos(new ArrayList<DebitoEntity>());
        debitos.setNombreTarjeta("Tarjeta santander");
        debitos.setTotales(totales );
        
        DebitosTarjetaEntity debitos2 = new DebitosTarjetaEntity();
        debitos2.setListDebitos(new ArrayList<DebitoEntity>());
        debitos2.getListDebitos().add(debito);
        debitos2.setNombreTarjeta("Tarjeta santander");
        debitos2.setTotales(totales );
        
        Assert.assertTrue(debitos.hashCode() != debitos2.hashCode());
        Assert.assertTrue(debitos.hashCode() == debitos.hashCode());
    }
    
    @Test
    public void debitoTarjetaEntityStringTest() {
        Totales totales = new Totales();
        totales.setDolares("125,54");
        totales.setPesos("134,55");
        
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
        
        DebitosTarjetaEntity debitos = new DebitosTarjetaEntity();
        debitos.setListDebitos(new ArrayList<DebitoEntity>());
        debitos.setNombreTarjeta("Tarjeta santander");
        debitos.setTotales(totales );
        
        Assert.assertEquals("Tarjeta santanderTotales [dolares=125,54, pesos=134,55][]", debitos.toString());
    }
}
