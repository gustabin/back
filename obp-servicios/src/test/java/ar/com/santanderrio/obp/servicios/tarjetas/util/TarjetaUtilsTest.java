/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoCuentaCreditoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * The Class TarjetaUtilsTest.
 *
 * @author sabrina.cis
 */
public class TarjetaUtilsTest {

    /**
     * Cortar Y formatear numero tarjeta test.
     */
    @Test
    public void cortarYFormatearNumeroTarjetaTest() {
        String numeroTarjeta = "4050710080543791";
        String marca = "AMEX";
        String respuesta = TarjetaUtils.cortarYFormatearNumeroTarjeta(numeroTarjeta, marca);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Crear mascara nro tarjeta.
     */
    @Test
    public void crearMascaraNroTarjeta() {
        String numeroTarjeta = "4050710080543791";
        String respuesta = TarjetaUtils.crearMascaraNroTarjeta(numeroTarjeta, TipoCuentaTarjeta.TIPOCTA_AMEX);
        Assert.assertNotNull(respuesta);
    }

    @Test
    public void testInvalidDateMapping() {
        Assert.assertEquals("-/-/-", TarjetaUtils.mapSettlementDateDate(null));
        Assert.assertEquals("-/-/-", TarjetaUtils.mapSettlementDateDate(""));
        Assert.assertEquals("-/-/-", TarjetaUtils.mapSettlementDateDate("    "));
        Assert.assertEquals("-/-/-", TarjetaUtils.mapSettlementDateDate("01/01/1970"));
    }

    @Test
    public void testCorrectDateMapping() {
        Assert.assertEquals("10/06/2020", TarjetaUtils.mapSettlementDateDate("10/06/2020"));
    }

    @Test
    public void testIsRecargableAccount() {
        Cuenta cuenta = Mockito.mock(Cuenta.class);
        Mockito.when(cuenta.getTipoCuenta()).thenReturn(TipoCuenta.VISA_RECARGABLE.getCodigo().toString());
        Assert.assertTrue(TarjetaUtils.esRecargable(cuenta));
    }

    @Test
    public void testIsNotRecargableAccount() {
        Cuenta cuenta = Mockito.mock(Cuenta.class);
        Mockito.when(cuenta.getTipoCuenta()).thenReturn(TipoCuenta.AMEX.getCodigo().toString());
        Assert.assertFalse(TarjetaUtils.esRecargable(cuenta));
    }

}
