/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.view;

/**
 * The class ConceptoDebinWSView.
 *
 * @author Silvina_Luque
 */
public class ConceptoDebinWSView {
    
    /** codigo */
    private String id;
    
    
    /** descripcion. */
    private String descripcion;
    
    /**
	 * 
	 */
	public ConceptoDebinWSView() {
	}

	/**
	 * @param id
	 * @param descripcion
	 */
	public ConceptoDebinWSView(String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
  

}
