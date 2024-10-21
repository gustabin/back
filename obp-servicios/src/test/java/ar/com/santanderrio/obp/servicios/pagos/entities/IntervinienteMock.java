package ar.com.santanderrio.obp.servicios.pagos.entities;

/**
 * The Class IntervinienteMock.
 *
 * @author florencia.n.martinez
 */
public final class IntervinienteMock {

    private IntervinienteMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completa la informacion del objeto Interviniente.
     *
     * @param apellido
     *            the apellido
     * @param cuitcuil
     *            the cuitcuil
     * @param nombre
     *            the nombre
     * @param ordenParticipacion
     *            the orden participacion
     * @param tipoInscripcion
     *            the tipo inscripcion
     * @return the interviniente
     */
    public static Interviniente completarInfoInterviniente(String apellido, String cuitcuil, String nombre,
            String ordenParticipacion, String tipoInscripcion) {
        Interviniente interviniente = new Interviniente();
        interviniente.setApellido(apellido);
        interviniente.setCuitcuil(cuitcuil);
        interviniente.setNombre(nombre);
        interviniente.setOrdenParticipacion(ordenParticipacion);
        interviniente.setTipoInscripcion(tipoInscripcion);
        return interviniente;
    }
}
