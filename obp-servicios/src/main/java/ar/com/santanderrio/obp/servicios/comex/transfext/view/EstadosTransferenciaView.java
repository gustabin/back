/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

/**
 * The Class EstadosTransferenciaView.
 */
public class EstadosTransferenciaView {
	
	/** The id. */
	private String id;
	
	/** The descripcion. */
	private String descripcion;
	
	

	/**
	 * Instantiates a new estados transferencia view.
	 */
	public EstadosTransferenciaView() {
		super();
	}

	/**
	 * Instantiates a new estados transferencia view.
	 *
	 * @param id
	 *            the id
	 * @param descripcion
	 *            the descripcion
	 */
	public EstadosTransferenciaView(String id, String descripcion) {
		super();
		this.id = id;
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
	 *            the id to set
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
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
