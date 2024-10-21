/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.entities;

/**
 * The Class ImporteCuentasView.
 */
public class ImporteCuentasView {

	/** The cuentasUsuario. */
	private String nroCuenta;

	/** The importe. */
	private String importe;

	/** The is saldo pesos negativo. */
	private Boolean isSaldoPesosNegativo;

	
	/** The is alias. */
	private String alias;

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
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

    /**
	 * Gets the checks if is saldo pesos negativo.
	 *
	 * @return the isSaldoPesosNegativo
	 */
    public Boolean getIsSaldoPesosNegativo() {
        return isSaldoPesosNegativo;
    }

    /**
	 * Sets the checks if is saldo pesos negativo.
	 *
	 * @param isSaldoPesosNegativo
	 *            the isSaldoPesosNegativo to set
	 */
    public void setIsSaldoPesosNegativo(Boolean isSaldoPesosNegativo) {
        this.isSaldoPesosNegativo = isSaldoPesosNegativo;
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
