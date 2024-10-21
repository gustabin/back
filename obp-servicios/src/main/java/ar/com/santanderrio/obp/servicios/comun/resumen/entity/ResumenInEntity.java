/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.resumen.entity;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author pablo.d.gargaglione
 *
 */
public class ResumenInEntity {

	/** The nup. */
	private String nup;

	/** The motivo. */
	private String motivo;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the motivo.
	 *
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * Sets the motivo.
	 *
	 * @param motivo
	 *            the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResumenInEntity [nup=" + nup + ", motivo=" + motivo + "]";
	}

}
