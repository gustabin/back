package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.Totales;

@RunWith(MockitoJUnitRunner.class)
public class InformeDebitosAutomaticosEntityTest {

    
    @Test
    public void equalsTest(){
        Totales totales = new Totales();
        totales.setDolares("12,50");
        totales.setPesos("11,20");
        
        ConsumoPromedioEntity consumoPromedio = new ConsumoPromedioEntity();
        consumoPromedio.setDolares("31,41");
        consumoPromedio.setPesos("32,41");
        
        DebitosTarjetaEntity debitosTarjeta = new DebitosTarjetaEntity();
        debitosTarjeta.setNombreTarjeta("tarjeta 1");
        debitosTarjeta.setTotales(totales);
        debitosTarjeta.setListDebitos(new ArrayList<DebitoEntity>());
        
        InformeDebitosAutomaticosEntity debitos = new InformeDebitosAutomaticosEntity();
        debitos.setDesde("12/12/2016");
        debitos.setHasta("14/09/2017");
        debitos.setTotales(totales);
        debitos.setConsumoPromedio(consumoPromedio);
        debitos.setDebitosTarjeta(Arrays.asList(debitosTarjeta));
        
        Totales totales2 = new Totales();
        totales2.setDolares("12,50");
        totales2.setPesos("11,20");
        
        ConsumoPromedioEntity consumoPromedio2 = new ConsumoPromedioEntity();
        consumoPromedio2.setDolares("31,41");
        consumoPromedio2.setPesos("32,41");
        
        DebitosTarjetaEntity debitosTarjeta2 = new DebitosTarjetaEntity();
        debitosTarjeta2.setNombreTarjeta("tarjeta 2");
        debitosTarjeta2.setTotales(totales2);
        debitosTarjeta2.setListDebitos(new ArrayList<DebitoEntity>());
        
        InformeDebitosAutomaticosEntity debitos2 = new InformeDebitosAutomaticosEntity();
        debitos2.setDesde("12/12/2016");
        debitos2.setHasta("14/09/2017");
        debitos2.setTotales(totales2);
        debitos2.setConsumoPromedio(consumoPromedio2);
        debitos2.setDebitosTarjeta(Arrays.asList(debitosTarjeta2));
        
        Assert.assertTrue(!debitos.equals(debitos2));
        Assert.assertTrue(debitos.equals(debitos));
    }
    
    @Test
    public void hashTest(){
        Totales totales = new Totales();
        totales.setDolares("12,50");
        totales.setPesos("11,20");
        
        ConsumoPromedioEntity consumoPromedio = new ConsumoPromedioEntity();
        consumoPromedio.setDolares("31,41");
        consumoPromedio.setPesos("32,41");
        
        DebitosTarjetaEntity debitosTarjeta = new DebitosTarjetaEntity();
        debitosTarjeta.setNombreTarjeta("tarjeta 1");
        debitosTarjeta.setTotales(totales);
        debitosTarjeta.setListDebitos(new ArrayList<DebitoEntity>());
        
        InformeDebitosAutomaticosEntity debitos = new InformeDebitosAutomaticosEntity();
        debitos.setDesde("12/12/2016");
        debitos.setHasta("14/09/2017");
        debitos.setTotales(totales);
        debitos.setConsumoPromedio(consumoPromedio);
        debitos.setDebitosTarjeta(Arrays.asList(debitosTarjeta));
        
        Totales totales2 = new Totales();
        totales2.setDolares("12,50");
        totales2.setPesos("11,20");
        
        ConsumoPromedioEntity consumoPromedio2 = new ConsumoPromedioEntity();
        consumoPromedio2.setDolares("31,41");
        consumoPromedio2.setPesos("32,41");
        
        DebitosTarjetaEntity debitosTarjeta2 = new DebitosTarjetaEntity();
        debitosTarjeta2.setNombreTarjeta("tarjeta 2");
        debitosTarjeta2.setTotales(totales2);
        debitosTarjeta2.setListDebitos(new ArrayList<DebitoEntity>());
        
        InformeDebitosAutomaticosEntity debitos2 = new InformeDebitosAutomaticosEntity();
        debitos2.setDesde("12/12/2016");
        debitos2.setHasta("14/09/2017");
        debitos2.setTotales(totales2);
        debitos2.setConsumoPromedio(consumoPromedio2);
        debitos2.setDebitosTarjeta(Arrays.asList(debitosTarjeta2));
        
        Assert.assertTrue(debitos.hashCode() != debitos2.hashCode());
        Assert.assertTrue(debitos2.hashCode() == debitos2.hashCode());
    }
    
    @Test
    public void toStringTest(){
        Totales totales = new Totales();
        totales.setDolares("12,50");
        totales.setPesos("11,20");
        
        ConsumoPromedioEntity consumoPromedio = new ConsumoPromedioEntity();
        consumoPromedio.setDolares("31,41");
        consumoPromedio.setPesos("32,41");
        
        DebitosTarjetaEntity debitosTarjeta = new DebitosTarjetaEntity();
        debitosTarjeta.setNombreTarjeta("tarjeta 1");
        debitosTarjeta.setTotales(totales);
        debitosTarjeta.setListDebitos(new ArrayList<DebitoEntity>());
        
        InformeDebitosAutomaticosEntity debitos = new InformeDebitosAutomaticosEntity();
        debitos.setDesde("12/12/2016");
        debitos.setHasta("14/09/2017");
        debitos.setTotales(totales);
        debitos.setConsumoPromedio(consumoPromedio);
        debitos.setDebitosTarjeta(Arrays.asList(debitosTarjeta));
        
        String result = debitos.toString();
        
        Assert.assertTrue(result.contains("desde=12/12/2016"));
        Assert.assertTrue(result.contains("hasta=14/09/2017"));
        Assert.assertTrue(result.contains("dolares=12,50"));
    }
    
    
}
