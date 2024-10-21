/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

import java.util.Date;

/**
 * The Class RangoInhabilitacion.
 */
public class RangoInhabilitacion {

    /** The desde. */
    private Date desde;

    /** The hasta. */
    private Date hasta;

    /**
	 * Instantiates a new rango inhabilitacion.
	 */
    public RangoInhabilitacion() {
        super();
    }

    /**
	 * Instantiates a new rango inhabilitacion.
	 *
	 * @param desde
	 *            the desde
	 * @param hasta
	 *            the hasta
	 */
    public RangoInhabilitacion(Date desde, Date hasta) {
        super();
        this.desde = desde;
        this.hasta = hasta;
    }

    /**
	 * Gets the desde.
	 *
	 * @return the desde
	 */
    public Date getDesde() {
        return desde;
    }

    /**
	 * Sets the desde.
	 *
	 * @param desde
	 *            the desde to set
	 */
    public void setDesde(Date desde) {
        this.desde = desde;
    }

    /**
	 * Gets the hasta.
	 *
	 * @return the hasta
	 */
    public Date getHasta() {
        return hasta;
    }

    /**
	 * Sets the hasta.
	 *
	 * @param hasta
	 *            the hasta to set
	 */
    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
    
}
