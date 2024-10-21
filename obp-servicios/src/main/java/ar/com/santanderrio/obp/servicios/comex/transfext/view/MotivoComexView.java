/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class MotivosComexView.
 *
 * @author B040619
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class MotivoComexView {
	
	/** The id. */
	private String id;
	
	/** The idConcepto. */
	private String idConcepto;
	
	/** The descripcion. */
	private String descripcion;

	/** The template. */
	private String template;
	
	/** The habilitarArgentina. */
	private Boolean habilitarArgentina;
	
	private String mensaje;


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
	 * Gets the id concepto.
	 *
	 * @return the idConcepto
	 */
	public String getIdConcepto() {
		return idConcepto;
	}

	/**
	 * Sets the id concepto.
	 *
	 * @param idConcepto
	 *            the idConcepto to set
	 */
	public void setIdConcepto(String idConcepto) {
		this.idConcepto = idConcepto;
	}

	/**
	 * Gets the template.
	 *
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * Sets the template.
	 *
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	public Boolean getHabilitarArgentina() {
		return habilitarArgentina;
	}

	public void setHabilitarArgentina(Boolean habilitarArgentina) {
		this.habilitarArgentina = habilitarArgentina;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
