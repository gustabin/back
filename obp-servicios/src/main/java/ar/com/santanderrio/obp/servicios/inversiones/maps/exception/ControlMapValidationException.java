package ar.com.santanderrio.obp.servicios.inversiones.maps.exception;

public class ControlMapValidationException  extends Exception {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * The ControlServicioInversionesValidationException.
     *
     * @param mensaje
     *            the mensaje
     */
    public ControlMapValidationException(String mensaje) {
        super(mensaje);
    }
}
