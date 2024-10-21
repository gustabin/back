package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class PrestamoViewMock.
 *
 * @author florencia.n.martinez
 */
public final class PrestamoViewMock {
    
    private PrestamoViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /**
     * Obtiene la lista de prestamos.
     *
     * @return the list
     */
    public static List<PrestamoView> obtenerListaPrestamosOK() {
        List<PrestamoView> prestamos = new ArrayList<PrestamoView>();
        prestamos.add(obtenerPrestamo());
        return prestamos;
    }
    
    /**
     * Obtiene la lista de prestamos con error de preformalizacion.
     *
     * @return the list
     */
    public static List<PrestamoView> obtenerListaPrestamosConErrorPreformalizacion() {
        List<PrestamoView> prestamos = new ArrayList<PrestamoView>();
        prestamos.add(obtenerPrestamo());
        prestamos.get(0).setPlazo("-");
        prestamos.get(0).setFechaInicio("-");
        return prestamos;
    }

    /**
     * Obtiene el prestamo.
     *
     * @return the prestamo view
     */
    private static PrestamoView obtenerPrestamo() {
        PrestamoView prestamo = new PrestamoView();
        prestamo.setCuota("1");
        prestamo.setFechaInicio("2014-09-17");
        prestamo.setFechaVencimiento("05/04/2017");
        prestamo.setId("29");
        prestamo.setHasAlias(false);
        prestamo.setImporteMaximaCuota("1.369,13");
        prestamo.setIsCuotaVencida(new Date(04 / 05 / 2017));
        prestamo.setIsUltimaCuota(false);
        prestamo.setNroPrestamo("000-3599263534/9");
        prestamo.setPlazo("12");
        prestamo.setTipoPrestamo("Súper Préstamo Personal");
        return prestamo;
    }
}
