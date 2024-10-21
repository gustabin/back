/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class PosicionConsolidadaEntity. Clase que representa el tag
 * /tarjetas/tarjeta/posicionConsolidada/
 */
@XmlRootElement(name = "posicionConsolidada")
@XmlAccessorType(XmlAccessType.FIELD)
public class PosicionConsolidadaEntity extends Consolidada {

	/** The session ID. */
	@XmlAttribute(name = "sessionID")
	private String sessionID;

	/** The datos. */
	@XmlElement(name = "datos")
	private DatosEntity datos;

	/** The nombre. */
	@XmlElement(name = "nombre")
	private String nombre;

	/** The autorizaciones. */
	@XmlElement(name = "autorizaciones")
	private ConsumosCuentasAuthEntity autorizaciones;

	/** The tarjetas. */
	@XmlElement(name = "tarjeta")
	private List<TarjetaPosicionConsolidadaEntity> tarjetas;

	/**
	 * Gets the session ID.
	 *
	 * @return the sessionID
	 */
	public String getSessionID() {
		return sessionID;
	}

	/**
	 * Sets the session ID.
	 *
	 * @param sessionID
	 *            the sessionID to set
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosEntity datos) {
		this.datos = datos;
	}

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
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the autorizaciones.
	 *
	 * @return the autorizaciones
	 */
	public ConsumosCuentasAuthEntity getAutorizaciones() {
		return autorizaciones;
	}

	/**
	 * Sets the autorizaciones.
	 *
	 * @param autorizaciones
	 *            the autorizaciones to set
	 */
	public void setAutorizaciones(ConsumosCuentasAuthEntity autorizaciones) {
		this.autorizaciones = autorizaciones;
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaPosicionConsolidadaEntity> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the tarjetas to set
	 */
	public void setTarjetas(List<TarjetaPosicionConsolidadaEntity> tarjetas) {
		this.tarjetas = tarjetas;
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
		result = prime * result + ((sessionID == null) ? 0 : sessionID.hashCode());
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
		PosicionConsolidadaEntity other = (PosicionConsolidadaEntity) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (sessionID == null) {
			if (other.sessionID != null)
				return false;
		} else if (!sessionID.equals(other.sessionID))
			return false;
		return true;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PosicionConsolidadaEntity [sessionID=" + sessionID + ", datos=" + datos + ", nombre=" + nombre
				+ ", autorizaciones=" + autorizaciones + ", tarjetas=" + tarjetas + "]";
	}

}
