package ar.com.santanderrio.obp.servicios.debinapi.dto;

import org.junit.Assert;
import org.junit.Test;

public class CurrencyTypeTest {
    @Test
    public void testCurrencyTypeFromString(){
       Assert.assertEquals(CurrencyType.ARS, CurrencyType.fromString("ARS"));
        Assert.assertEquals(CurrencyType.USD, CurrencyType.fromString("USD"));
        Assert.assertEquals(CurrencyType.ARS, CurrencyType.fromString("032"));
        Assert.assertEquals(CurrencyType.USD, CurrencyType.fromString("840"));
        Assert.assertEquals(null, CurrencyType.fromString("cualquiera"));
    }
}
