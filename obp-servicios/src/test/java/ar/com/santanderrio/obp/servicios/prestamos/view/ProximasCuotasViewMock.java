package ar.com.santanderrio.obp.servicios.prestamos.view;

/**
 * @author florencia.n.martinez
 *
 */
public final class ProximasCuotasViewMock {
    
    private ProximasCuotasViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    public static ProximasCuotasView completarInfoProximasCuotasView() {
        ProximasCuotasView view = new ProximasCuotasView();
        view.setCuotas(ProximaCuotaViewMock.completarInfoListaProximaCuotaView());
        view.setCuotasFechas(CuotasFechaViewMock.completarInfoListaCuotasFechaView());
        return view;
    }

}
