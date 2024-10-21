/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ItemSeccionView.
 * 
 * @deprecated utilizar {@link SeccionView}
 */
@Deprecated
public class ItemSeccionView {
	/** The titulo. */
	private String titulo;
	/** The accion. */
	private String accion;
	/** The mensaje si el estado es mostrar mensaje. */
	private String mensajeNoDisponible;

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
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion
	 *            the new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Gets the mensaje no disponible.
	 *
	 * @return the mensajeNoDisponible
	 */
	public String getMensajeNoDisponible() {
		return mensajeNoDisponible;
	}

	/**
	 * Sets the mensaje no disponible.
	 *
	 * @param mensajeNoDisponible
	 *            the mensajeNoDisponible to set
	 */
	public void setMensajeNoDisponible(String mensajeNoDisponible) {
		this.mensajeNoDisponible = mensajeNoDisponible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(titulo);
		hcb.append(accion);
		hcb.append(mensajeNoDisponible);
		return hcb.toHashCode();
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

		ItemSeccionView other = (ItemSeccionView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(titulo, other.getTitulo());
		eb.append(accion, other.getAccion());
		eb.append(mensajeNoDisponible, other.getMensajeNoDisponible());
		return eb.isEquals();
	}

}
