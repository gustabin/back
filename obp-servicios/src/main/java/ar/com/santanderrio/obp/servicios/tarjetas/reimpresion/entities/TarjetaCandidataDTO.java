/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class TarjetaCandidataDTO.
 */
public class TarjetaCandidataDTO {

	/** The nro tarjeta con formato. */
	private String nroTarjetaConFormato;

	/** The nro. */
	private String nro;

	/** The alias. */
	private String alias;

	/** The titular. */
	private String titular;

	/** The tipoDescripcion. */
	private String tipoDescripcion;
	
	/** Cuenta. */
	private Cuenta cuentaRel;
	


    /**
	 * Gets the nro tarjeta con formato.
	 *
	 * @return the nroTarjetaConFormato
	 */
	public String getNroTarjetaConFormato() {
		return nroTarjetaConFormato;
	}

	/**
	 * Sets the nro tarjeta con formato.
	 *
	 * @param nroTarjetaConFormato
	 *            the nroTarjetaConFormato to set
	 */
	public void setNroTarjetaConFormato(String nroTarjetaConFormato) {
		this.nroTarjetaConFormato = nroTarjetaConFormato;
	}

	/**
	 * Gets the nro.
	 *
	 * @return the nro
	 */
	public String getNro() {
		return nro;
	}

	/**
	 * Sets the nro.
	 *
	 * @param nro
	 *            the nro to set
	 */
	public void setNro(String nro) {
		this.nro = nro;
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
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the tipo descripcion.
	 *
	 * @return the tipo descripcion
	 */
	public String getTipoDescripcion() {
		return tipoDescripcion;
	}

	/**
	 * Sets the tipo descripcion.
	 *
	 * @param tipoDescripcion
	 *            the new tipo descripcion
	 */
	public void setTipoDescripcion(String tipoDescripcion) {
		this.tipoDescripcion = tipoDescripcion;
	}

	/**
	 * cuentaRel.
	 *
	 * @return the cuenta rel
	 */
    public Cuenta getCuentaRel() {
        return cuentaRel;
    }

    /**
	 * setCuentaRel.
	 *
	 * @param cuentaRel
	 *            the new cuenta rel
	 */
    public void setCuentaRel(Cuenta cuentaRel) {
        this.cuentaRel = cuentaRel;
    }
	
	

}
