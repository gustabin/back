/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dto;

import ar.com.santanderrio.obp.base.entities.DTO;

/**
 * The Class LimiteClaseDTO.
 */
public class LimiteClaseDTO extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The clase tarjeta debito. */
	private String claseTarjetaDebito;

	/** The id cuenta. */
	private String idCuenta;

	/**
	 * Instantiates a new limite clase DTO.
	 */
	public LimiteClaseDTO() {
		super();
	}

	/**
	 * Instantiates a new limite clase DTO.
	 *
	 * @param claseTarjetaDebito
	 *            the clase tarjeta debito
	 * @param idCuenta
	 *            the id cuenta
	 */
	public LimiteClaseDTO(String claseTarjetaDebito, String idCuenta) {
		super();
		this.claseTarjetaDebito = claseTarjetaDebito;
		this.idCuenta = idCuenta;
	}

	/**
	 * Gets the clase tarjeta debito.
	 *
	 * @return the clase tarjeta debito
	 */
	public String getClaseTarjetaDebito() {
		return claseTarjetaDebito;
	}

	/**
	 * Sets the clase tarjeta debito.
	 *
	 * @param claseTarjetaDebito
	 *            the new clase tarjeta debito
	 */
	public void setClaseTarjetaDebito(String claseTarjetaDebito) {
		this.claseTarjetaDebito = claseTarjetaDebito;
	}

	/**
	 * Gets the id cuenta.
	 *
	 * @return the id cuenta
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * Sets the id cuenta.
	 *
	 * @param idCuenta
	 *            the new id cuenta
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((claseTarjetaDebito == null) ? 0 : claseTarjetaDebito.hashCode());
		result = prime * result + ((idCuenta == null) ? 0 : idCuenta.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LimiteClaseDTO other = (LimiteClaseDTO) obj;
		if (claseTarjetaDebito == null) {
			if (other.claseTarjetaDebito != null) {
				return false;
			}
		} else if (!claseTarjetaDebito.equals(other.claseTarjetaDebito)) {
			return false;
		}
		if (idCuenta == null) {
			if (other.idCuenta != null) {
				return false;
			}
		} else if (!idCuenta.equals(other.idCuenta)) {
			return false;
		}
		return true;
	}

}
