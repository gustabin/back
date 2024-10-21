/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.view;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.WebContentView;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;

/**
 * Created by pablo.martin.gore on 9/2/2016.
 */
public class LoginResponseView extends View {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8522858857012614058L;
    /** The token. */
    private String token;

    /** The credenciales mya. */
    private CredencialesMya credencialesMya;

    /** The terminos condiciones. */
    private String terminosCondiciones;

    /** The mensaje. */
    private String mensaje;

    /** The mensaje. */
    private String nup;

    /**
     * nombre del usuario para utilizarse en el topbar.
     */
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String nombreUsuario;

    /**
     * Salto desde el login si es que corresponde
     */
    private WebContentView contenido;
    
    /**
     * Gets the token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token
     *            the new token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the credenciales mya.
     *
     * @return the credenciales mya
     */
    public CredencialesMya getCredencialesMya() {
        return credencialesMya;
    }

    /**
     * Sets the credenciales mya.
     *
     * @param credencialesMya
     *            the new credenciales mya
     */
    public void setCredencialesMya(CredencialesMya credencialesMya) {
        this.credencialesMya = credencialesMya;
    }

    /**
     * Gets the terminos condiciones.
     *
     * @return the terminos condiciones
     */
    public String getTerminosCondiciones() {
        return terminosCondiciones;
    }

    /**
     * Sets the terminos condiciones.
     *
     * @param terminosCondiciones
     *            the new terminos condiciones
     */
    public void setTerminosCondiciones(String terminosCondiciones) {
        this.terminosCondiciones = terminosCondiciones;
    }

    /**
     * Gets the mensaje.
     *
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the mensaje.
     *
     * @param mensaje
     *            the new mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Gets the nup.
     *
     * @return the nup
     */
    public String getNup() {
        return nup;
    }

    /**
     * Sets the nup.
     *
     * @param nup
     *            the nup to set
     */
    public void setNup(String nup) {
        this.nup = nup;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(token).toString();
    }

    public WebContentView getContenido() {
        return contenido;
    }

    public void setContenido(WebContentView contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario
     *            the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        if (StringUtils.isNotBlank(nombreUsuario)) {
            this.nombreUsuario = WordUtils.capitalizeFully(nombreUsuario.trim());
        }
    }


}
