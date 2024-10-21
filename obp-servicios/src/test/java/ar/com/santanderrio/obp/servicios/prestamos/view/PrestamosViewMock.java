package ar.com.santanderrio.obp.servicios.prestamos.view;

/**
 * The Class PrestamosViewMock.
 *
 * @author florencia.n.martinez
 */
public final class PrestamosViewMock {

    private PrestamosViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtiene los pretamos view OK.
     *
     * @return the prestamos view
     */
    public static PrestamosView obtenerPretamosViewOK() {
        PrestamosView prestamosView = new PrestamosView();
        prestamosView.setPrestamos(PrestamoViewMock.obtenerListaPrestamosOK());
        return prestamosView;
    }

    /**
     * Obtiene los prestamos con error de preformalizacion.
     * 
     * @return
     */
    public static PrestamosView obtenerPretamosViewConErrorPreformalizacion() {
        PrestamosView prestamosView = new PrestamosView();
        prestamosView.setPrestamos(PrestamoViewMock.obtenerListaPrestamosConErrorPreformalizacion());
        return prestamosView;
    }

}