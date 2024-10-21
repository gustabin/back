/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class CuentaSelectorView.
 */
@XmlRootElement(name = "cuentaView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuentaSeleccionView implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3280310530540455574L;

    /** The alias. */
    private String alias;

    /** The descripcion tipo cuenta. */
    private String descripcionTipoCuenta;

    /** The numero. */
    private String numero;

    /** The saldo pesos. */
    private String saldoPesos;
    
    /** The saldo dolares. */
    private String saldoDolares;

    /** The cbu. */
    private String cbu;

    /** The is saldo pesos negativo. */
    private Boolean isSaldoPesosNegativo;

    /** The is saldo dolares negativo. */
    private Boolean isSaldoDolaresNegativo;

    /** The is mobile. */
    private Boolean isMobile;

    /** The linea disponible. */
    private String lineaDisponible;

    /** The linea total. */
    private String lineaTotal;
    
    /** The is tarjeta. */
    private boolean isTarjeta;

    /**
     * Instantiates a new cuenta seleccion view.
     */
    public CuentaSeleccionView() {
        super();
    }

    /**
     * Instantiates a new cuenta seleccion view.
     *
     * @param cuenta
     *            the c
     */
    public CuentaSeleccionView(Cuenta cuenta) {
        this.alias = cuenta.getAlias();
        this.cbu = cuenta.getCbu();
        if (cuenta.esTarjetaDeCredito()) {
            this.descripcionTipoCuenta = StringUtils.upperCase(cuenta.getTipoCuentaEnum().getDescripcion());
            this.numero = TarjetaUtils.obtenerNroTarjetaEnmascarada(cuenta);
            this.isTarjeta = true;
        } else {
            if (cuenta.esCuentaUnica()) {
                this.descripcionTipoCuenta = cuenta.getTipoCuentaEnum().getDescripcion();
            } else {
                this.descripcionTipoCuenta = cuenta.getTipoCuentaEnum().getDescripcionConMoneda();
            }
            this.numero = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);
            this.saldoPesos = cuenta.obtenerSaldoFormateado();
            this.isSaldoPesosNegativo = StringUtils.contains(this.saldoPesos, "-");
            if (this.isSaldoPesosNegativo) {
                this.saldoPesos = StringUtils.remove(this.saldoPesos, '-');
            }
            this.saldoDolares = cuenta.obtenerSaldoDolaresFormateado();
            this.isSaldoDolaresNegativo = StringUtils.contains(this.saldoDolares, "-");
            if (this.isSaldoDolaresNegativo) {
                this.saldoDolares = StringUtils.remove(this.saldoDolares, '-');
            }
        }
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
     * Gets the saldo pesos.
     *
     * @return the saldo pesos
     */
    public String getSaldoPesos() {
        return saldoPesos;
    }

    /**
     * Sets the saldo pesos.
     *
     * @param saldoPesos
     *            the new saldo pesos
     */
    public void setSaldoPesos(String saldoPesos) {
        this.saldoPesos = saldoPesos;
    }

    /**
	 * Gets the saldo dolares.
	 *
	 * @return the saldo dolares
	 */
    public String getSaldoDolares() {
        return saldoDolares;
    }

    /**
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the new saldo dolares
	 */
    public void setSaldoDolares(String saldoDolares) {
        this.saldoDolares = saldoDolares;
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
     * Gets the checks if is saldo pesos negativo.
     *
     * @return the checks if is saldo pesos negativo
     */
    public Boolean getIsSaldoPesosNegativo() {
        return isSaldoPesosNegativo;
    }

    /**
     * Sets the checks if is saldo pesos negativo.
     *
     * @param isSaldoPesosNegativo
     *            the new checks if is saldo pesos negativo
     */
    public void setIsSaldoPesosNegativo(Boolean isSaldoPesosNegativo) {
        this.isSaldoPesosNegativo = isSaldoPesosNegativo;
    }

    /**
     * Gets the checks if is saldo dolares negativo.
     *
     * @return the checks if is saldo dolares negativo
     */
    public Boolean getIsSaldoDolaresNegativo() {
        return isSaldoDolaresNegativo;
    }

    /**
     * Sets the checks if is saldo dolares negativo.
     *
     * @param isSaldoDolaresNegativo
     *            the new checks if is saldo dolares negativo
     */
    public void setIsSaldoDolaresNegativo(Boolean isSaldoDolaresNegativo) {
        this.isSaldoDolaresNegativo = isSaldoDolaresNegativo;
    }

    /**
     * Gets the checks if is mobile.
     *
     * @return the checks if is mobile
     */
    public Boolean getIsMobile() {
        return isMobile;
    }

    /**
     * Sets the checks if is mobile.
     *
     * @param isMobile
     *            the new checks if is mobile
     */
    public void setIsMobile(Boolean isMobile) {
        this.isMobile = isMobile;
    }

    /**
     * Gets the linea disponible.
     *
     * @return the linea disponible
     */
    public String getLineaDisponible() {
        return lineaDisponible;
    }

    /**
     * Sets the linea disponible.
     *
     * @param lineaDisponible
     *            the new linea disponible
     */
    public void setLineaDisponible(String lineaDisponible) {
        this.lineaDisponible = lineaDisponible;
    }

    /**
     * Gets the linea total.
     *
     * @return the linea total
     */
    public String getLineaTotal() {
        return lineaTotal;
    }

    /**
     * Sets the linea total.
     *
     * @param lineaTotal
     *            the new linea total
     */
    public void setLineaTotal(String lineaTotal) {
        this.lineaTotal = lineaTotal;
    }

    /**
	 * Checks if is tarjeta.
	 *
	 * @return true, if is tarjeta
	 */
    public boolean isTarjeta() {
        return isTarjeta;
    }

    /**
	 * Sets the tarjeta.
	 *
	 * @param isTarjeta
	 *            the new tarjeta
	 */
    public void setTarjeta(boolean isTarjeta) {
        this.isTarjeta = isTarjeta;
    }
    
    /**
	 * Obtener numero tarjeta.
	 *
	 * @return the string
	 */
    public String obtenerNumeroTarjeta() {
        return this.descripcionTipoCuenta + " " + this.numero;
    }

}
