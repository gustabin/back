package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.ArrayList;
import java.util.List;

/**
 * @author florencia.n.martinez
 *
 */
public final class CuotasFechaViewMock {

    private CuotasFechaViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    public static List<CuotasFechaView> completarInfoListaCuotasFechaView() {
        List<CuotasFechaView> cuotasFechas = new ArrayList<CuotasFechaView>();
        cuotasFechas.add(completarInfoCuotasFechaView("2017"));
        return cuotasFechas;
    }

    private static CuotasFechaView completarInfoCuotasFechaView(String fecha) {
        CuotasFechaView cuotasFechaView = new CuotasFechaView(fecha);
        return cuotasFechaView;
    }
}
