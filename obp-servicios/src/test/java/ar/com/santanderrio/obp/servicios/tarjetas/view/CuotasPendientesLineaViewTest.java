package ar.com.santanderrio.obp.servicios.tarjetas.view;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesLineaView;

/**
 * The Class CuotasPendientesLineaViewTest.
 *
 * @author sabrina.cis
 */
public class CuotasPendientesLineaViewTest {

    /** The view. */
    private CuotasPendientesLineaView view = new CuotasPendientesLineaView();

    /**
     * Gets the getEstablecimientoTest.
     *
     * @return the establecimiento
     */
    @Test
    public void getEstablecimientoTest() {
        String dato = "GARBARINO";
        view.setEstablecimiento("GARBARINO                          ");
        Assert.assertEquals(dato, view.getEstablecimiento());
    }

    /**
     * Gets the getOperacion.
     *
     * @return the operacion
     */
    @Test
    public void getOperacion() {
        String dato = "";
        view.setOperacion("");
        Assert.assertEquals(dato, view.getOperacion());
    }

    /**
     * Gets the fecha test.
     *
     * @return the fecha test
     */
    @Test
    public void getFechaTest() {
        Date date = new Date();
        String dato = ISBANStringUtils.formatearFecha(date);
        view.setFecha(date);
        Assert.assertEquals(dato, view.getFecha());
    }

    /**
     * Gets the getCantidadCuotas.
     *
     * @return the cantidadCuotas
     */
    @Test
    public void getCantidadCuotas() {
        String dato = "4";
        view.setCantidadCuotas(4);
        Assert.assertEquals(dato, view.getCantidadCuotas());
    }

    /**
     * Gets the getCuotasPendientes.
     *
     * @return the cuotasPendientes
     */
    @Test
    public void getCuotasPendientes() {
        String dato = "5";
        view.setCuotasPendientes(5);
        Assert.assertEquals(dato, view.getCuotasPendientes());
    }

    /**
     * Gets the getRestante.
     *
     * @return the restante
     */
    @Test
    public void getRestante() {
        String dato = "10.000,00";
        view.setRestante(new BigDecimal(10000));
        Assert.assertEquals(dato, view.getRestante());

        String n1 = "13.000,00";
        String n2 = n1.replace(".", "");
        String n3 = n2.replace(",", ".");

        BigDecimal n = new BigDecimal(n3);
        BigDecimal n4 = new BigDecimal(15000);
        boolean b = n.compareTo(n4) < 0;
        Assert.assertEquals(dato, view.getRestante());
    }

}
