/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;

/**
 * @author sergio.e.goldentair
 *
 */
public abstract class RsaDTOParaDesafio extends RsaDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** The desafio. */
    @JsonIgnore
    @JsonManagedReference
    private AutentificacionDTO desafio;

    /**
     * @param tipoOperacion
     */
    public RsaDTOParaDesafio(OperacionesRSAEnum tipoOperacion) {
        super(tipoOperacion);
    }

    /**
     * Gets the desafio.
     *
     * @return the desafio
     */
    public AutentificacionDTO getDesafio() {
        return desafio;
    }

    /**
     * Sets the desafio.
     *
     * @param desafio
     *            the new desafio
     */
    public void setDesafio(AutentificacionDTO desafio) {
        this.desafio = desafio;
    }

}
