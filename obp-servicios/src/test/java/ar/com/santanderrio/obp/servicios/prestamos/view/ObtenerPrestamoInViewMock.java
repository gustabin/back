package ar.com.santanderrio.obp.servicios.prestamos.view;

/**
 * The Class ObtenerPrestamoInViewMock.
 *
 * @author florencia.n.martinez
 */
public final class ObtenerPrestamoInViewMock {

    private ObtenerPrestamoInViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtiene el objeto prestamo in view personal.
     *
     * @return the obtener prestamo in view
     */
    public static ObtenerPrestamoInView obtenerPrestamoInViewPersonal() {
        ObtenerPrestamoInView obtenerPrestamoInView = new ObtenerPrestamoInView();
        obtenerPrestamoInView.setIsHipotecario(false);
        obtenerPrestamoInView.setIsPersonal(true);
        obtenerPrestamoInView.setIsPrendario(false);
        return obtenerPrestamoInView;
    }

    /**
     * Obtiene el objeto prestamo in view hipotecario.
     *
     * @return the obtener prestamo in view
     */
    public static ObtenerPrestamoInView obtenerPrestamoInViewHipotecario() {
        ObtenerPrestamoInView obtenerPrestamoInView = new ObtenerPrestamoInView();
        obtenerPrestamoInView.setIsHipotecario(true);
        obtenerPrestamoInView.setIsPersonal(false);
        obtenerPrestamoInView.setIsPrendario(false);
        return obtenerPrestamoInView;
    }

    /**
     * Obtiene el objeto prestamo in view prendario.
     *
     * @return the obtener prestamo in view
     */
    public static ObtenerPrestamoInView obtenerPrestamoInViewPrendario() {
        ObtenerPrestamoInView obtenerPrestamoInView = new ObtenerPrestamoInView();
        obtenerPrestamoInView.setIsHipotecario(false);
        obtenerPrestamoInView.setIsPersonal(false);
        obtenerPrestamoInView.setIsPrendario(true);
        return obtenerPrestamoInView;
    }
}
