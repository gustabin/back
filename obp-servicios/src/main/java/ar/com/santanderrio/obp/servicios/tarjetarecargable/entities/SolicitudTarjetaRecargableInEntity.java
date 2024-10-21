/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.entities;

/**
 * The Class SolicitudTarjetaRecargableInEntity.
 */
public class SolicitudTarjetaRecargableInEntity {

	/** The sp atributos. */
	private String spAtributos;

	/**
	 * Gets the sp atributos.
	 *
	 * @return the sp atributos
	 */
	public String getSpAtributos() {
		return spAtributos;
	}

	/**
	 * Sets the sp atributos.
	 *
	 * @param spAtributos
	 *            the new sp atributos
	 */
	public void setSpAtributos(String spAtributos) {
		this.spAtributos = spAtributos;
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
		result = prime * result + ((spAtributos == null) ? 0 : spAtributos.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolicitudTarjetaRecargableInEntity other = (SolicitudTarjetaRecargableInEntity) obj;
		if (spAtributos == null) {
			if (other.spAtributos != null)
				return false;
		} else if (!spAtributos.equals(other.spAtributos))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SolicitudTarjetaRecargableInEntity [spAtributos=" + spAtributos + "]";
	}

}
