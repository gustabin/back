package ar.com.santanderrio.obp.base.reporte.entities;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte.
 */
public class Reporte extends ReporteBase implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The bytes. */
	private String nombre = "";

	/** The tipo archivo. */
	private TipoArchivoEnum tipoArchivo;

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the tipo archivo.
	 *
	 * @return the tipo archivo
	 */
	public TipoArchivoEnum getTipoArchivo() {
		return tipoArchivo;
	}

	/**
	 * Sets the tipo archivo.
	 *
	 * @param tipoArchivo
	 *            the new tipo archivo
	 */
	public void setTipoArchivo(TipoArchivoEnum tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
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
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tipoArchivo == null) ? 0 : tipoArchivo.hashCode());
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
		Reporte other = (Reporte) obj;
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (tipoArchivo != other.tipoArchivo) {
			return false;
		}
		return true;
	}

}
