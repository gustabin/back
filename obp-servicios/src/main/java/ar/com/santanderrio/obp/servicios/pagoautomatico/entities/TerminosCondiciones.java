/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.entities;

import java.io.Serializable;

/**
 * Extrae titulo y cuerpo de los terminos y condiciones para armar Json y pasar
 * a la vista.
 * 
 * @author mariano.g.pulera
 * 
 */

public class TerminosCondiciones implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The titulo. */
	private String titulo;

	/** The cuerpo. */
	private String cuerpo;

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the cuerpo.
	 *
	 * @return the cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}

	/**
	 * Sets the cuerpo.
	 *
	 * @param cuerpo
	 *            the new cuerpo
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
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
		result = prime * result + ((cuerpo == null) ? 0 : cuerpo.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		TerminosCondiciones other = (TerminosCondiciones) obj;
		if (cuerpo == null) {
			if (other.cuerpo != null) {
				return false;
			}
		} else if (!cuerpo.equals(other.cuerpo)) {
			return false;
		}
		if (titulo == null) {
			if (other.titulo != null) {
				return false;
			}
		} else if (!titulo.equals(other.titulo)) {
			return false;
		}
		return true;
	}

}
