/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class SeccionView.
 */
public class SeccionView {

	/** The titulo. */
	private String titulo;

	/** The items. */
	private List<SeccionView> items;

	/** The accion. */
	private String accion;

	/** The mensaje si el estado es mostrar mensaje. */
	private String mensajeNoDisponible;
	
	/** The url. */
	private String url;

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
	 * Gets the items.
	 *
	 * @return the items
	 */
	public List<SeccionView> getItems() {
		return items;
	}

	/**
	 * Sets the items.
	 *
	 * @param items
	 *            the new items
	 */
	public void setItems(List<SeccionView> items) {
		this.items = items;
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
		

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(accion);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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

		SeccionView other = (SeccionView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(accion, other.getAccion());
		return eb.isEquals();
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("Accion", accion).append("Titulo", titulo)
				.append("Mensaje no disponible", mensajeNoDisponible).append("url", url).toString();
	}

}
