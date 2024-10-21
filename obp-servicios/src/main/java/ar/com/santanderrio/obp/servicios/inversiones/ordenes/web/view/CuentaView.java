/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class CuentaView.
 * 
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @since Mon 23, 2017
 */
@XmlRootElement(name = "cuentaView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CuentaView {

    /** The numero. */
    private String numero;

    /** The numero formateado. */
    private String numeroFormateado;

    /** The cbu. */
    private String cbu;

    /** The capital. */
    private String capital;

    /** The alias. */
    private String alias;

    /** The descripcion tipo cuenta. */
    private String descripcionTipoCuenta;

    /** The abreviatura tipo cuenta. */
    private String abreviaturaTipoCuenta;

    /** The is favorito. */
    private Boolean isFavorito;

    /** The intervinientes. titular y cotitulares */
    private List<Interviniente> intervinientes;

    /** The titulo cuenta. */
    private String tituloCuenta;

    /**
     * Instantiates a new cuenta view.
     */
    public CuentaView() {
    }

    /**
     * Gets the numero.
     *
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the numero.
     *
     * @param numero
     *            the new numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Gets the cbu.
     *
     * @return the cbu
     */
    public String getCbu() {
        return cbu;
    }

    /**
     * Sets the cbu.
     *
     * @param cbu
     *            the new cbu
     */
    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    /**
     * Gets the alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias.
     *
     * @param alias
     *            the new alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the descripcion tipo cuenta.
     *
     * @return the descripcion tipo cuenta
     */
    public String getDescripcionTipoCuenta() {
        return descripcionTipoCuenta;
    }

    /**
     * Sets the descripcion tipo cuenta.
     *
     * @param descripcionTipoCuenta
     *            the new descripcion tipo cuenta
     */
    public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
        this.descripcionTipoCuenta = descripcionTipoCuenta;
    }

    /**
     * Gets the abreviatura tipo cuenta.
     *
     * @return the abreviatura tipo cuenta
     */
    public String getAbreviaturaTipoCuenta() {
        return abreviaturaTipoCuenta;
    }

    /**
     * Sets the abreviatura tipo cuenta.
     *
     * @param abreviaturaTipoCuenta
     *            the new abreviatura tipo cuenta
     */
    public void setAbreviaturaTipoCuenta(String abreviaturaTipoCuenta) {
        this.abreviaturaTipoCuenta = abreviaturaTipoCuenta;
    }

    /**
     * Gets the checks if is favorito.
     *
     * @return the checks if is favorito
     */
    public Boolean getIsFavorito() {
        return isFavorito;
    }

    /**
     * Sets the checks if is favorito.
     *
     * @param isFavorito
     *            the new checks if is favorito
     */
    public void setIsFavorito(Boolean isFavorito) {
        this.isFavorito = isFavorito;
    }

    /**
     * Gets the numero formateado.
     *
     * @return the numero formateado
     */
    public String getNumeroFormateado() {
        return numeroFormateado;
    }

    /**
     * Sets the numero formateado.
     *
     * @param numeroFormateado
     *            the new numero formateado
     */
    public void setNumeroFormateado(String numeroFormateado) {
        this.numeroFormateado = numeroFormateado;
    }

    /**
     * Gets the intervinientes.
     *
     * @return the intervinientes
     */
    public List<Interviniente> getIntervinientes() {
        return intervinientes;
    }

    /**
     * Sets the intervinientes.
     *
     * @param intervinientes
     *            the new intervinientes
     */
    public void setIntervinientes(List<Interviniente> intervinientes) {
        this.intervinientes = intervinientes;
    }

    /**
	 * Gets the titulo cuenta.
	 *
	 * @return the titulo cuenta
	 */
    public String getTituloCuenta() {
        return tituloCuenta;
    }

    /**
	 * Sets the titulo cuenta.
	 *
	 * @param tituloCuenta
	 *            the new titulo cuenta
	 */
    public void setTituloCuenta(String tituloCuenta) {
        this.tituloCuenta = tituloCuenta;
    }

    /**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
    public String getCapital() {
        return capital;
    }

    /**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
    public void setCapital(String capital) {
        this.capital = capital;
    }

}
