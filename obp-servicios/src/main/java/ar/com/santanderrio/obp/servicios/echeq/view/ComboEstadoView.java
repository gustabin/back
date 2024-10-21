package ar.com.santanderrio.obp.servicios.echeq.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class ComboEstadoView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ComboEstadoView {
	
	/** The id. */
	private String id;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new combo estado view.
	 */
	public ComboEstadoView() {
		super();
	}

	/**
	 * Instantiates a new combo estado view.
	 *
	 * @param descripcion the descripcion
	 * @param id the id
	 */
	public ComboEstadoView(String id, String descripcion) {
		super();
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
