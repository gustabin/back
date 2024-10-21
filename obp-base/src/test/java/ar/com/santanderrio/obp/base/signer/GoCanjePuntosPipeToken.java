/**
 * 
 */
package ar.com.santanderrio.obp.base.signer;

import ar.com.santanderrio.obp.base.signer.token.PipeToken;

/**
 * The Class GoCanjePuntosPipeToken.
 *
 * @author sergio.e.goldentair
 */
public class GoCanjePuntosPipeToken extends PipeToken {

    /** The canal. */
    private String canal;

    /** The habilitado canje. */
    private String habilitadoCanje;

    /**
     * Gets the habilitado canje.
     *
     * @return the habilitadoCanje
     */
    public String getHabilitadoCanje() {
        return habilitadoCanje;
    }

    /**
     * Sets the habilitado canje.
     *
     * @param habilitadoCanje
     *            the habilitadoCanje to set
     */
    public void setHabilitadoCanje(String habilitadoCanje) {
        this.habilitadoCanje = habilitadoCanje;
    }

    /**
     * Gets the canal.
     *
     * @return the canal
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the canal.
     *
     * @param canal
     *            the canal to set
     */
    public void setCanal(String canal) {
        this.canal = canal;
    }

}
