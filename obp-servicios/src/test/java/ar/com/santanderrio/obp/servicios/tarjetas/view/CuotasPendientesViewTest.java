package ar.com.santanderrio.obp.servicios.tarjetas.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesLineaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesTarjetaView;

/**
 * The Class CuotasPendientesViewTest.
 */
public class CuotasPendientesViewTest {

    /** The view. */
    private CuotasPendientesTarjetaView view = new CuotasPendientesTarjetaView();

    /**
     * Gets the marca.
     *
     * @return the marca
     */
    @Test
    public void getMarcaTest() {
        String marca = "VISA";
        view.setMarca("VISA");
        Assert.assertEquals(marca, view.getMarca());
    }

    /**
     * Gets the numero.
     *
     * @return the numero
     */
    @Test
    public void getNumeroTest() {
        String numero = "03";
        view.setNumero(numero);
        Assert.assertEquals(numero, view.getNumero());
    }

    /**
     * Gets the es titular.
     *
     * @return the es titular
     */
    @Test
    public void getEsTitular() {
        view.setEsTitular(true);
        Assert.assertEquals(true, view.getEsTitular());
    }

    /**
     * Gets the nombre adicional.
     *
     * @return the nombre adicional
     */
    @Test
    public void getNombreAdicional() {
        String nombreAdicional = "Federico N Garay O";
        view.setNombreAdicional("GARAY O/FEDERICO N");
        Assert.assertEquals(nombreAdicional, view.getNombreAdicional());
    }

    /**
     * Gets the total.
     *
     * @return the total
     */
    @Test
    public void getTotal() {
        String total = "150,00";
        view.setTotal(new BigDecimal(150.00));
        Assert.assertEquals(total, view.getTotal());
    }

    /**
     * Gets the lineas cuotas pendientes.
     *
     * @return the lineas cuotas pendientes
     */
    @Test
    public void getLineasCuotasPendientes() {
        List<CuotasPendientesLineaView> lineasCuotasPendientes = new ArrayList<CuotasPendientesLineaView>();
        view.setLineasCuotasPendientes(lineasCuotasPendientes);
        Assert.assertEquals(lineasCuotasPendientes, view.getLineasCuotasPendientes());
    }

}
