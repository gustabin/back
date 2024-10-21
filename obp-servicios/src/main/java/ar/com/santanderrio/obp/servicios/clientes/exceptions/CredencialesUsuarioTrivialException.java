package ar.com.santanderrio.obp.servicios.clientes.exceptions;

public class CredencialesUsuarioTrivialException extends CredencialesException {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instancia una nueva CredencialesUsuarioBloqueadoException.
     *
     * @param mensaje
     *            the mensaje
     */
    public CredencialesUsuarioTrivialException(String mensaje) {
        super(mensaje);
    }
    
}
