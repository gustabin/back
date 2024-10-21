package ar.com.santanderrio.obp.iatx;

import org.mockito.ArgumentMatcher;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;

/**
 * The Class IatxMatcher.
 */
public class IatxMatcherRellamado extends ArgumentMatcher<IatxRequest> {

    /** The prefijo. */
    private final String prefijo;

    /** The version. */
    private final String version;

    /** The nro mje multiple. */
    private final String nroMjeMultiple;

    /**
     * Instantiates a new iatx matcher.
     *
     * @param prefijo
     *            the prefijo
     * @param version
     *            the version
     * @param nroMjeMultiple
     *            the nro mje multiple
     */
    public IatxMatcherRellamado(String prefijo, String version, String nroMjeMultiple) {
        this.prefijo = prefijo;
        this.version = version;
        this.nroMjeMultiple = nroMjeMultiple;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mockito.ArgumentMatcher#matches(java.lang.Object)
     */
    public boolean matches(Object object) {
        IatxRequest iatxRequest = (IatxRequest) object;
        if (iatxRequest != null && iatxRequest.getNombreServicio().trim().equals(prefijo)
                && iatxRequest.getVersionServicio().trim().equals(version)
                && iatxRequest.getData().getNroMjeMultiple().equals(nroMjeMultiple)) {
            return true;
        }
        return false;
    }
}
