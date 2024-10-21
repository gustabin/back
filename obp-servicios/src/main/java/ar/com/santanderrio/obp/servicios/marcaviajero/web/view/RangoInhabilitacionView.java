/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.web.view;

/**
 * The Class RangoInhabilitacion.
 */
public class RangoInhabilitacionView {

    /** The desde. */
    private String desde;

    /** The hasta. */
    private String hasta;

    /**
	 * Gets the desde.
	 *
	 * @return the desde
	 */
    public String getDesde() {
        return desde;
    }

    /**
	 * Sets the desde.
	 *
	 * @param desde
	 *            the desde to set
	 */
    public void setDesde(String desde) {
        this.desde = desde;
    }

    /**
	 * Gets the hasta.
	 *
	 * @return the hasta
	 */
    public String getHasta() {
        return hasta;
    }

    /**
	 * Sets the hasta.
	 *
	 * @param hasta
	 *            the hasta to set
	 */
    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RangoInhabilitacionView [desde=" + desde + ", hasta=" + hasta + "]";
    }
}
