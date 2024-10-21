/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenFilaBean;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSUltimoResumenImpl;

/**
 * @author sergio.e.goldentair
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ParseadorWSUltimoResumenTest {
    /** The cuotas pendientes BO. */
    @InjectMocks
    private ParseadorWSUltimoResumenImpl parseador;

    @Test
    public void obtenerCamposDetalleLiquidacion() {
        String linea = "22/07/18 TIENDA DIA NÂº141 - CLAYPOLE          000505*      853,86                  ";
        UltimoResumenFilaBean fila = parseador.obtenerCamposDetalleLiquidacion(linea, "");
        Assert.assertNotNull(fila);
    }

    @Test
    public void obtenerUltimosCuatroTarjeta() {
        String ultimosDigitos = "1234";
        String expectedResult = "XXXX - " + ultimosDigitos;
        String linea = " TOTAL TARJETA 3715 XXXX XXXX "+ultimosDigitos+" ...... 32.663,01 * ";
        UltimoResumenFilaBean ultimoResumenFilaBean = new UltimoResumenFilaBean();
        ultimoResumenFilaBean.setTotal(linea);
        String result = parseador.obtenerNumeroTarjeta(ultimoResumenFilaBean);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void obtenerUltimosCuatroTarjetaSinDigitos() {
        String ultimosDigitos = "1234";
        String expectedResult = "XXXX - " + ultimosDigitos;
        String linea = " TOTAL TARJETA XXXX XXXX XXXX "+ultimosDigitos+" ...... 32.663,01 * ";
        UltimoResumenFilaBean ultimoResumenFilaBean = new UltimoResumenFilaBean();
        ultimoResumenFilaBean.setTotal(linea);
        String result = parseador.obtenerNumeroTarjeta(ultimoResumenFilaBean);
        Assert.assertEquals(expectedResult, result);
    }
    @Test
    public void obtenerUltimosCuatroTarjetaSinDigitosPrincipioFin() {
        String linea = " TOTAL TARJETA XXXX XXXX XXXX XXXX ...... 32.663,01 * ";
        UltimoResumenFilaBean ultimoResumenFilaBean = new UltimoResumenFilaBean();
        ultimoResumenFilaBean.setTotal(linea);
        String result = parseador.obtenerNumeroTarjeta(ultimoResumenFilaBean);
        Assert.assertNull(result);
    }
    @Test
    public void obtenerUltimosCuatroTarjetaSinEspacios() {
        String ultimosDigitos = "1234";
        String expectedResult = "XXXX - " + ultimosDigitos;
        String linea = " TOTAL TARJETA XXXXXXXXXXXX"+ultimosDigitos+" ...... 32.663,01 * ";
        UltimoResumenFilaBean ultimoResumenFilaBean = new UltimoResumenFilaBean();
        ultimoResumenFilaBean.setTotal(linea);
        String result = parseador.obtenerNumeroTarjeta(ultimoResumenFilaBean);
        Assert.assertEquals(expectedResult, result);
    }
    @Test
    public void obtenerUltimosCuatroTarjetaDosEspacios() {
        String ultimosDigitos = "1234";
        String expectedResult = "XXXX - " + ultimosDigitos;
        String linea = " TOTAL TARJETA XXXX  XXXX  XXXX  "+ultimosDigitos+" ...... 32.663,01 * ";
        UltimoResumenFilaBean ultimoResumenFilaBean = new UltimoResumenFilaBean();
        ultimoResumenFilaBean.setTotal(linea);
        String result = parseador.obtenerNumeroTarjeta(ultimoResumenFilaBean);
        Assert.assertEquals(expectedResult, result);
    }
    @Test
    public void obtenerUltimosCuatroTarjetaNoInformada() {
        String linea = " TOTAL TARJETA  ...... 32.663,01 * ";
        UltimoResumenFilaBean ultimoResumenFilaBean = new UltimoResumenFilaBean();
        ultimoResumenFilaBean.setTotal(linea);
        String result = parseador.obtenerNumeroTarjeta(ultimoResumenFilaBean);
        Assert.assertNull(result);
    }
    @Test
    public void obtenerUltimosCuatroTarjetaSinEsbozar() {
        String ultimosDigitos = "1234";
        String expectedResult = "XXXX - " + ultimosDigitos;
        String linea = " TOTAL TARJETA 3222  2222  3212  "+ultimosDigitos+" ...... 32.663,01 * ";
        UltimoResumenFilaBean ultimoResumenFilaBean = new UltimoResumenFilaBean();
        ultimoResumenFilaBean.setTotal(linea);
        String result = parseador.obtenerNumeroTarjeta(ultimoResumenFilaBean);
        Assert.assertEquals(expectedResult, result);
    }
}
