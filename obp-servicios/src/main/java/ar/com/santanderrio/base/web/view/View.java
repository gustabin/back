/*
 * 
 */
package ar.com.santanderrio.base.web.view;

import java.io.Serializable;
import java.util.Random;

import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;

/**
 * The Class View.
 */
public class View implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MAX_RANGO. */
	static final long MAX_RANGO = 9223372036854775807L;

	/** The Constant MIN_RANGO. */
	static final long MIN_RANGO = -9223372036854775808L;

	/** The id. */
	private String id = null;

	/** The id sesion. */
	private String idSesion = null;

	/**
	 * Instantiates a new view. constructor por defecto
	 */
	public View() {
	}

	/**
	 * Instantiates a new view.
	 *
	 * @param id
	 *            the id
	 */
	public View(String id) {
		this.id = id;
		generarNuevoIdSesion();
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
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the id sesion.
	 *
	 * @return the id sesion
	 */
	public String getIdSesion() {
		return idSesion;
	}

	/**
	 * Sets the new id sesion.
	 *
	 * @param idSesion
	 *            the new id sesion
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	/**
	 * Generar nuevo id sesion. Se utiliza para contar los reintentos
	 *
	 * @author emilio.watemberg
	 * @see {@link ContadorIntentos}
	 */
	public void generarNuevoIdSesion() {
		Random random = new Random();
		idSesion = String.valueOf(MIN_RANGO + (long) (random.nextDouble() * (MIN_RANGO)));
	}

}
