package ar.com.santanderrio.obp.iatx;

import org.mockito.ArgumentMatcher;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;

/**
 * The Class IatxMatcher.
 */
public class IatxMatcher extends ArgumentMatcher<IatxRequest> {

    /** The prefijo. */
    private final String prefijo;

    /** The version. */
    private final String version;

    /**
     * Instantiates a new iatx matcher.
     *
     * @param prefijo
     *            the prefijo
     * @param version
     *            the version
     */
    public IatxMatcher(String prefijo, String version) {
        this.prefijo = prefijo;
        this.version = version;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mockito.ArgumentMatcher#matches(java.lang.Object)
     */
    public boolean matches(Object object) {
        IatxRequest iatxRequest = (IatxRequest) object;
        if (iatxRequest != null && iatxRequest.getNombreServicio().trim().equals(prefijo)
                && iatxRequest.getVersionServicio().trim().equals(version)) {
            return true;
        }
        return false;
    }
}
