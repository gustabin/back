/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.entities;

import javax.validation.constraints.Pattern;

import org.beanio.annotation.Field;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaCitiEntity.
 */
public class CuentaCitiEntity {

    /** The cuenta citi. */
    @Field
    @Pattern(regexp = "^[a-zA-Z0-9]{24}|[ ]{24}$")
    private String cuentaCiti;
    
    /** The suc cuenta citi. */
    @Field
    @Pattern(regexp = "^[a-zA-Z0-9]{24}|[ ]{24}$")
    private String sucCuentaCiti;
    
    /** The moneda cuenta citi. */
    @Field
    @Pattern(regexp = "^[a-zA-Z0-9]{24}|[ ]{24}$")
    private String monedaCuentaCiti;
    
    /** The cuenta citi formato CBU. */
    @Field
    @Pattern(regexp = "^[a-zA-Z0-9]{24}|[ ]{24}$")
    private String cuentaCitiFormatoCBU;
    
    /** The tipo cuenta citi. */
    @Field
    @Pattern(regexp = "^[a-zA-Z0-9]{24}|[ ]{24}$")
    private String tipoCuentaCiti;
    
    /** The estado migracion. */
    @Field
    @Pattern(regexp = "^[a-zA-Z0-9]{24}|[ ]{24}$")
    private String estadoMigracion;

    /**
     * Gets the cuenta citi.
     *
     * @return the cuenta citi
     */
    public String getCuentaCiti() {
        return cuentaCiti;
    }

    /**
     * Sets the cuenta citi.
     *
     * @param cuentaCiti the new cuenta citi
     */
    public void setCuentaCiti(String cuentaCiti) {
        this.cuentaCiti = cuentaCiti;
    }

    /**
     * Gets the suc cuenta citi.
     *
     * @return the suc cuenta citi
     */
    public String getSucCuentaCiti() {
        return sucCuentaCiti;
    }

    /**
     * Sets the suc cuenta citi.
     *
     * @param sucCuentaCiti the new suc cuenta citi
     */
    public void setSucCuentaCiti(String sucCuentaCiti) {
        this.sucCuentaCiti = sucCuentaCiti;
    }

    /**
     * Gets the moneda cuenta citi.
     *
     * @return the moneda cuenta citi
     */
    public String getMonedaCuentaCiti() {
        return monedaCuentaCiti;
    }

    /**
     * Sets the moneda cuenta citi.
     *
     * @param monedaCuentaCiti the new moneda cuenta citi
     */
    public void setMonedaCuentaCiti(String monedaCuentaCiti) {
        this.monedaCuentaCiti = monedaCuentaCiti;
    }

    /**
     * Gets the cuenta citi formato CBU.
     *
     * @return the cuenta citi formato CBU
     */
    public String getCuentaCitiFormatoCBU() {
        return cuentaCitiFormatoCBU;
    }

    /**
     * Sets the cuenta citi formato CBU.
     *
     * @param cuentaCitiFormatoCBU the new cuenta citi formato CBU
     */
    public void setCuentaCitiFormatoCBU(String cuentaCitiFormatoCBU) {
        this.cuentaCitiFormatoCBU = cuentaCitiFormatoCBU;
    }

    /**
     * Gets the tipo cuenta citi.
     *
     * @return the tipo cuenta citi
     */
    public String getTipoCuentaCiti() {
        return tipoCuentaCiti;
    }

    /**
     * Sets the tipo cuenta citi.
     *
     * @param tipoCuentaCiti the new tipo cuenta citi
     */
    public void setTipoCuentaCiti(String tipoCuentaCiti) {
        this.tipoCuentaCiti = tipoCuentaCiti;
    }

    /**
     * Gets the estado migracion.
     *
     * @return the estado migracion
     */
    public String getEstadoMigracion() {
        return estadoMigracion;
    }

    /**
     * Sets the estado migracion.
     *
     * @param estadoMigracion the new estado migracion
     */
    public void setEstadoMigracion(String estadoMigracion) {
        this.estadoMigracion = estadoMigracion;
    }
    
}
