package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;

@RunWith(MockitoJUnitRunner.class)
public class RetornoTarjetasEntityTest {

    @Test
    public void equalsTest(){
        ErrorTarjetasEntity errorTarjetasEntity = new ErrorTarjetasEntity();
        errorTarjetasEntity.setCodigo("1234125");
        errorTarjetasEntity.setErrorID("12519623857");
        errorTarjetasEntity.setSessionID("1897f379g1701234897a");
        
        TarjetasEntity tarjetas = new TarjetasEntity();
        tarjetas.setTarjetaList(new ArrayList<TarjetaEntity>());
        
        RetornoTarjetasEntity retorno = new RetornoTarjetasEntity();
        retorno.setError(false);
        retorno.setErrorTarjetas(errorTarjetasEntity);
        retorno.setTarjetas(tarjetas);
        
        ErrorTarjetasEntity errorTarjetasEntity2 = new ErrorTarjetasEntity();
        errorTarjetasEntity2.setCodigo("1234125");
        errorTarjetasEntity2.setErrorID("12519623857");
        errorTarjetasEntity2.setSessionID("1897f379gasd1701234897a");
        
        TarjetasEntity tarjetas2 = new TarjetasEntity();
        tarjetas2.setTarjetaList(new ArrayList<TarjetaEntity>());
        
        RetornoTarjetasEntity retorno2 = new RetornoTarjetasEntity();
        retorno2.setError(false);
        retorno2.setErrorTarjetas(errorTarjetasEntity2);
        retorno2.setTarjetas(tarjetas2);
        
        Assert.assertTrue(!retorno.equals(retorno2));
        Assert.assertTrue(retorno.equals(retorno));
    }
    
    @Test
    public void hashTest(){
        ErrorTarjetasEntity errorTarjetasEntity = new ErrorTarjetasEntity();
        errorTarjetasEntity.setCodigo("1234125");
        errorTarjetasEntity.setErrorID("12519623857");
        errorTarjetasEntity.setSessionID("1897f379g1701234897a");
        
        TarjetasEntity tarjetas = new TarjetasEntity();
        tarjetas.setTarjetaList(new ArrayList<TarjetaEntity>());
        
        RetornoTarjetasEntity retorno = new RetornoTarjetasEntity();
        retorno.setError(false);
        retorno.setErrorTarjetas(errorTarjetasEntity);
        retorno.setTarjetas(tarjetas);
        
        ErrorTarjetasEntity errorTarjetasEntity2 = new ErrorTarjetasEntity();
        errorTarjetasEntity2.setCodigo("1234125");
        errorTarjetasEntity2.setErrorID("12519623857");
        errorTarjetasEntity2.setSessionID("1897f379gasd1701234897a");
        
        TarjetasEntity tarjetas2 = new TarjetasEntity();
        tarjetas2.setTarjetaList(new ArrayList<TarjetaEntity>());
        
        RetornoTarjetasEntity retorno2 = new RetornoTarjetasEntity();
        retorno2.setError(false);
        retorno2.setErrorTarjetas(errorTarjetasEntity2);
        retorno2.setTarjetas(tarjetas2);
        
        Assert.assertTrue(retorno.hashCode() != retorno2.hashCode());
        Assert.assertTrue(retorno.hashCode() == retorno.hashCode());
    }
    
    @Test
    public void toStringTest(){
        ErrorTarjetasEntity errorTarjetasEntity = new ErrorTarjetasEntity();
        errorTarjetasEntity.setCodigo("1234125");
        errorTarjetasEntity.setErrorID("12519623857");
        errorTarjetasEntity.setSessionID("1897f379g1701234897a");
        
        TarjetasEntity tarjetas = new TarjetasEntity();
        tarjetas.setTarjetaList(new ArrayList<TarjetaEntity>());
        
        RetornoTarjetasEntity retorno = new RetornoTarjetasEntity();
        retorno.setError(false);
        retorno.setErrorTarjetas(errorTarjetasEntity);
        retorno.setTarjetas(tarjetas);
        
        String res = retorno.toString();
        
        Assert.assertTrue(res.contains("codigo=1234125"));
    }
}
