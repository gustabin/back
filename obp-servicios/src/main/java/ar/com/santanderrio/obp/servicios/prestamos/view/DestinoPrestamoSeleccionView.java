/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;

/**
 * The Class DestinoPrestamoSeleccionView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DestinoPrestamoSeleccionView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5973905810192836981L;

	/** The id. */
	private String id;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new destino prestamo seleccion view.
	 */
	public DestinoPrestamoSeleccionView() {
		super();
	}

	/**
	 * Instantiates a new destino prestamo seleccion view.
	 *
	 * @param destinoPrestamo
	 *            the destino prestamo
	 */
	public DestinoPrestamoSeleccionView(DestinoPrestamo destinoPrestamo) {
		this.id = destinoPrestamo.obtenerId();
		this.descripcion = destinoPrestamo.getDescripcionUG();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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

		DestinoPrestamoSeleccionView other = (DestinoPrestamoSeleccionView) obj;

		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
