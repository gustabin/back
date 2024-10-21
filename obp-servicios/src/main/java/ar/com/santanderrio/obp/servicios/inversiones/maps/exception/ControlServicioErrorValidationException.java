package ar.com.santanderrio.obp.servicios.inversiones.maps.exception;

public class ControlServicioErrorValidationException  extends ControlMapValidationException {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * The ControlServicioValidationException.
     *
     * @param mensaje
     *            the mensaje
     */
    public ControlServicioErrorValidationException(String mensaje) {
        super(mensaje);
    }
}
