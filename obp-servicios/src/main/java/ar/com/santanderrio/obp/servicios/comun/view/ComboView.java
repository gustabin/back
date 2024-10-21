package ar.com.santanderrio.obp.servicios.comun.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Clase para devolver al front y usar el componente generico obp-selector-generico
 * 
 * The Class ComboView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ComboView {

	/** The descripcion. */
	private String descripcion;

	/** The id. */
	private String id;

	/**
	 * 
	 */
	public ComboView() {
	}

	/**
	 * @param descripcion
	 * @param id
	 */
	public ComboView(String descripcion, String id) {
		this.descripcion = descripcion;
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
	
}

