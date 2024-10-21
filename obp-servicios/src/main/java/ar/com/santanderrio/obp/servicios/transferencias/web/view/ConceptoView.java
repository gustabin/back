/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

/**
 * The Class ConceptoView.
 */
public class ConceptoView {

	/** The descripcion. */
	private String descripcion;

	/** The id, es el ordinal. */
	private String id;

	/** The concepto. */
	private String codigo;
	
	/** The descripcionAbreviada. */
	private String descripcionAbreviada;
	
	/** The habilitaLegal. */
	private int habilitaLegal;


	/**
	 * Instantiates a new concepto view.
	 */
	public ConceptoView() {

	}
	
	/**
	 * Instantiates a new concepto view.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @param id
	 *            the id
	 * @param codigo
	 *            the codigo
	 * @param descripcionAbreviada
	 *            the descripcion abreviada
	 * @param habilitaLegal
	 *            the habilita legal
	 */
	public ConceptoView(String descripcion, String id, String codigo, String descripcionAbreviada, int habilitaLegal) {
		this.descripcion = descripcion;
		this.id = id;
		this.codigo = codigo;
		this.descripcionAbreviada = descripcionAbreviada;
		this.habilitaLegal = habilitaLegal;
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
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the descripcion abreviada.
	 *
	 * @return the descripcionAbreviada
	 */
	public String getDescripcionAbreviada() {
		return descripcionAbreviada;
	}

	/**
	 * Sets the descripcion abreviada.
	 *
	 * @param descripcionAbreviada
	 *            the descripcionAbreviada to set
	 */
	public void setDescripcionAbreviada(String descripcionAbreviada) {
		this.descripcionAbreviada = descripcionAbreviada;
	}

	/**
	 * Gets the habilita legal.
	 *
	 * @return the habilitaLegal
	 */
	public int getHabilitaLegal() {
		return habilitaLegal;
	}

	/**
	 * Sets the habilita legal.
	 *
	 * @param habilitaLegal
	 *            the habilitaLegal to set
	 */
	public void setHabilitaLegal(int habilitaLegal) {
		this.habilitaLegal = habilitaLegal;
	}

	
	
}
