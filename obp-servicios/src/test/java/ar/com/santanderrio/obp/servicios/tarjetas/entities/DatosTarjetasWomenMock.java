package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import ar.com.santanderrio.obp.servicios.solicitudes.view.DatosTarjetaWomen;

import java.util.ArrayList;
import java.util.List;

public class DatosTarjetasWomenMock {

    public static List<DatosTarjetaWomen> completarInfoDatosTarjetasWomenVisa() {
        DatosTarjetaWomen datosTarjetaWomen = infoDatosTarjetaWomenVisa();
        List<DatosTarjetaWomen> datosTarjetaWomenList = new ArrayList<DatosTarjetaWomen>();
        datosTarjetaWomenList.add(datosTarjetaWomen);

        return datosTarjetaWomenList;
    }

    public static List<DatosTarjetaWomen> completarInfoDatosTarjetasWomenAmex() {
        DatosTarjetaWomen datosTarjetaWomen = infoDatosTarjetaWomenAmex();
        List<DatosTarjetaWomen> datosTarjetaWomenList = new ArrayList<DatosTarjetaWomen>();
        datosTarjetaWomenList.add(datosTarjetaWomen);

        return datosTarjetaWomenList;
    }

    private static DatosTarjetaWomen infoDatosTarjetaWomenAmex() {
        DatosTarjetaWomen datosTarjetaWomen = new DatosTarjetaWomen();
        datosTarjetaWomen.setTarjeta("AMEX");
        return datosTarjetaWomen;
    }

    private static DatosTarjetaWomen infoDatosTarjetaWomenVisa() {
        DatosTarjetaWomen datosTarjetaWomen = new DatosTarjetaWomen();
        datosTarjetaWomen.setTarjeta("VISA");
        return datosTarjetaWomen;
    }
}
