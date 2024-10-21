/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debin.entities;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * Salto a debin.
 * 
 * @author sergio.e.goldentair
 *
 */
public class DebinView extends RsaDTO implements Serializable {
    /** The Serial Id. */
    private static final long serialVersionUID = -8827388797262435590L;
    /** The desafio. */
    @JsonIgnore
    @JsonManagedReference
    private AutentificacionDTO desafio;

    /** The celular my A. Se usa en RSA. */
    private boolean celularMyA = false;

    /**
     * Token para salto debin.
     */
    private TokenView tokenView;

    /**
	 * Instantiates a new debin view.
	 */
    public DebinView() {
        super(OperacionesRSAEnum.DEBIN);
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
	 *            the desafio to set
	 */
    public void setDesafio(AutentificacionDTO desafio) {
        this.desafio = desafio;
    }

    /**
	 * Checks if is celular my A.
	 *
	 * @return the celularMyA
	 */
    public boolean isCelularMyA() {
        return celularMyA;
    }

    /**
	 * Sets the celular my A.
	 *
	 * @param celularMyA
	 *            the celularMyA to set
	 */
    public void setCelularMyA(boolean celularMyA) {
        this.celularMyA = celularMyA;
    }

    /**
	 * Gets the token view.
	 *
	 * @return the tokenView
	 */
    public TokenView getTokenView() {
        return tokenView;
    }

    /**
	 * Sets the token view.
	 *
	 * @param tokenView
	 *            the tokenView to set
	 */
    public void setTokenView(TokenView tokenView) {
        this.tokenView = tokenView;
    }
}