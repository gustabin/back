/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class TipoDeCuentaView.
 *
 * @author b039542
 * @lastUpdate emilio.watemberg
 */
public class TipoDeCuentaView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private String id;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipo de cuenta view.
	 */
	public TipoDeCuentaView() {
		super();
	}

	/**
	 * Constructor para par de valores.
	 *
	 * @param id
	 *            the id
	 * @param descripcion
	 *            the descripcion
	 */
	public TipoDeCuentaView(String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.base.web.view.View#getId()
	 */
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.base.web.view.View#setId(java.lang.String)
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
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(id);
		hcb.append(descripcion);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		TipoDeCuentaView other = (TipoDeCuentaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(descripcion, other.getDescripcion());
		eb.append(id, other.getId());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TipoDeCuentaView [id=" + id + ", descripcion=" + descripcion + "]";
	}

}
