/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Cotizacion.
 *
 * @author sabrina.cis
 */
public class Cotizacion {

	/** The is dolar. */
	private Boolean isDolar;

	/** The cuenta origen. */
	private String cuentaOrigen;
	
	/** The cuenta destino. */
	private String cuentaDestino;

	/** The fromCuentas. */
	private boolean fromCuentas;

	/**
	 * Gets the checks if is dolar.
	 *
	 * @return the isDolar
	 */
	public Boolean getIsDolar() {
		return isDolar;
	}

	/**
	 * Sets the checks if is dolar.
	 *
	 * @param isDolar
	 *            the isDolar to set
	 */
	public void setIsDolar(Boolean isDolar) {
		this.isDolar = isDolar;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return the cuentaOrigen
	 */
	public String getCuentaOrigen() {
		return cuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the cuentaOrigen to set
	 */
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	/**
	 * Gets the cuenta destino.
	 *
	 * @return the cuenta destino
	 */
	public String getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino the new cuenta destino
	 */
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	/**
	 * Gets the from cuentas.
	 *
	 * @return the from cuentas
	 */
	public boolean getFromCuentas() {
		return fromCuentas;
	}

	/**
	 * Sets the from cuentas.
	 *
	 * @param fromCuentas the new from cuentas
	 */
	public void setFromCuentas(boolean fromCuentas) {
		this.fromCuentas = fromCuentas;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuentaOrigen == null) ? 0 : cuentaOrigen.hashCode());
		result = prime * result + ((isDolar == null) ? 0 : isDolar.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cotizacion other = (Cotizacion) obj;
		if (cuentaOrigen == null) {
			if (other.cuentaOrigen != null) {
				return false;
			}
		} else if (!cuentaOrigen.equals(other.cuentaOrigen)) {
			return false;
		}
		if (isDolar == null) {
			if (other.isDolar != null) {
				return false;
			}
		} else if (!isDolar.equals(other.isDolar)) {
			return false;
		}
		return true;
	}
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cotizacion [isDolar=" + isDolar + ", cuentaOrigen=" + cuentaOrigen + "]";
	}

}
