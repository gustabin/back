/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

import java.util.List;

/**
 * The Class ViajesHabilitados.
 */
public class ViajesHabilitados {

    /** The viajes habilitados. */
    private List<ViajeHabilitado> viajesHabilitados;

    /** The tiene black. */
    private Boolean tieneBlack;

    /**
     * Gets the viajes habilitados.
     *
     * @return the viajesHabilitados
     */
    public List<ViajeHabilitado> getViajesHabilitados() {
        return viajesHabilitados;
    }

    /**
     * Sets the viajes habilitados.
     *
     * @param viajesHabilitados the viajesHabilitados to set
     */
    public void setViajesHabilitados(List<ViajeHabilitado> viajesHabilitados) {
        this.viajesHabilitados = viajesHabilitados;
    }

    /**
     * Gets the tiene black.
     *
     * @return the tieneBlack
     */
    public Boolean getTieneBlack() {
        return tieneBlack;
    }

    /**
     * Sets the tiene black.
     *
     * @param tieneBlack the tieneBlack to set
     */
    public void setTieneBlack(Boolean tieneBlack) {
        this.tieneBlack = tieneBlack;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ViajesHabilitados [viajesHabilitados=" + viajesHabilitados
                + ", tieneBlack=" + tieneBlack + "]";
    }

    
}
