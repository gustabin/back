/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.view;

/**
 * The Class CuentaDebinView.
 */
public class CuentaDebinView {

    
    /** alias. */
    private String alias;
    
    /** nroCuenta. */
    private String nroCuenta;
    
    /** tipoCuenta. */
    private String descripcion;
    
    /** cbu. */
    private String cbu;

    
    
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
	 *            the cbu to set
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
	 *            the alias to set
	 */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
	 * Gets the nro cuenta.
	 *
	 * @return the nroCuenta
	 */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the nroCuenta to set
	 */
    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    /**
	 * Gets the descripcion.
	 *
	 * @return the tipoCuenta
	 */
    public String getDescripcion() {
        return descripcion;
    }

    /**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
