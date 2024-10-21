/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

// TODO: Auto-generated Javadoc
/**
 * The Class AdjuntarArchivosDTO.
 *
 * @author IT Resources
 */
public class AdjuntarArchivosDTO {
	
	/** The id. */
	private String id; 

	
	/** The archivo. */
	private ReporteView archivo = new ReporteView();
	

	/**
	 * Gets the archivo.
	 *
	 * @return the archivo
	 */
	public ReporteView getArchivo() {
		return archivo;
	}

	/**
	 * Sets the archivo.
	 *
	 * @param archivo
	 *            the archivo to set
	 */
	public void setArchivo(ReporteView archivo) {
		this.archivo = archivo;
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
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}	

}
