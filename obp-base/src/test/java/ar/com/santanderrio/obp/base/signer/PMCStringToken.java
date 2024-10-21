/**
 * 
 */
package ar.com.santanderrio.obp.base.signer;

import ar.com.santanderrio.obp.base.signer.token.StringToken;
import ar.com.santanderrio.obp.base.signer.token.StringTokenFormat;

/**
 * Token que extiende de StringToke con finalidad de test.
 * 
 * @author sergio.e.goldentair
 *
 */
public class PMCStringToken extends StringToken {

    /** The direccion cliente. */
    @StringTokenFormat("Direccion-Cliente:")
    private String direccionCliente;

    /** The tipo documento. */
    @StringTokenFormat("Tipo-Documento:")
    private String tipoDocumento;

    /** The prueba. */
    private String prueba;

    /** The erroneo. */
    private Long erroneo;

    /** The erroneo con formato. */
    @StringTokenFormat("ERRONEO:")
    private Long erroneoConFormato;

    /**
     * Gets the erroneo con formato.
     *
     * @return the erroneoConFormato
     */
    public Long getErroneoConFormato() {
        return erroneoConFormato;
    }

    /**
     * Sets the erroneo con formato.
     *
     * @param erroneoConFormato
     *            the erroneoConFormato to set
     */
    public void setErroneoConFormato(Long erroneoConFormato) {
        this.erroneoConFormato = erroneoConFormato;
    }

    /**
     * Gets the erroneo.
     *
     * @return the erroneo
     */
    public Long getErroneo() {
        return erroneo;
    }

    /**
     * Sets the erroneo.
     *
     * @param erroneo
     *            the erroneo to set
     */
    public void setErroneo(Long erroneo) {
        this.erroneo = erroneo;
    }

    /**
     * Gets the prueba.
     *
     * @return the prueba
     */
    public String getPrueba() {
        return prueba;
    }

    /**
     * Sets the prueba.
     *
     * @param prueba
     *            the prueba to set
     */
    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    /**
     * Gets the tipo documento.
     *
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the tipo documento.
     *
     * @param tipoDocumento
     *            the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Gets the direccion cliente.
     *
     * @return the direccionCliente
     */
    public String getDireccionCliente() {
        return direccionCliente;
    }

    /**
     * Sets the direccion cliente.
     *
     * @param direccionCliente
     *            the direccionCliente to set
     */
    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }
}
