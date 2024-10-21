package ar.com.santanderrio.obp.iatx;

import org.mockito.ArgumentMatcher;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;

/**
 * The Class IatxMatcherPaginadoAgendaDestinatario.
 */
public class IatxMatcherPaginadoAgendaDestinatario extends ArgumentMatcher<IatxRequest> {

    /** The prefijo. */
    private final String prefijo;

    /** The version. */
    private final String version;

    /** The llamada. */
    private final String llamada;

    /**
     * Instantiates a new iatx matcher.
     *
     * @param prefijo
     *            the prefijo
     * @param version
     *            the version
     * @param llamada
     *            "CN" indica mas registros; "PR" no hay mas registros.
     */
    public IatxMatcherPaginadoAgendaDestinatario(String prefijo, String version, String llamada) {
        this.prefijo = prefijo;
        this.version = version;
        this.llamada = llamada;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mockito.ArgumentMatcher#matches(java.lang.Object)
     */
    public boolean matches(Object object) {
        IatxRequest iatxRequest = (IatxRequest) object;
        if (iatxRequest != null) {
            String trama = iatxRequest.getData().getData();
            Boolean llamada = trama.contains("õ" + this.llamada + "õ");
            if (iatxRequest.getNombreServicio().trim().equals(prefijo)
                    && iatxRequest.getVersionServicio().trim().equals(version) && llamada) {
                return true;
            }
        }
        return false;
    }
}
