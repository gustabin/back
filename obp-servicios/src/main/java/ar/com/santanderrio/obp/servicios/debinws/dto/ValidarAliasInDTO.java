/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class ValidarAliasInDTO.
 */
public class ValidarAliasInDTO {
    
    
    /** moneda. */
    private String moneda;
    
    /** The cuenta. */
    private Cuenta cuenta;
    
    /** alias. */
    private String alias;
    
    /** userAgnet. */
    private String userAgent;
    
    




    /**
	 * Gets the user agent.
	 *
	 * @return the userAgent
	 */
    public String getUserAgent() {
        return userAgent;
    }

    /**
	 * Sets the user agent.
	 *
	 * @param userAgent
	 *            the userAgent to set
	 */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
    public String getMoneda() {
        return moneda;
    }

    /**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }



    /**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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
	 *            the alias to set
	 */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    

}
