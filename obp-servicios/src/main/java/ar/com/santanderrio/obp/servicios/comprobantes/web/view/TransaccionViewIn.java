/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class TransaccionViewIn.
 * 
 */
public class TransaccionViewIn extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idTransaccion. */
	private Integer idTransaccion;

	/**
	 * Instantiates a new transaccion view in.
	 */
	public TransaccionViewIn() {
		super();
	}

	/**
	 * Instantiates a new transaccion view in.
	 * 
	 * @param idTransaccion
	 *            the id transaccion
	 */
	public TransaccionViewIn(Integer idTransaccion) {
		super();
		this.idTransaccion = idTransaccion;
	}

	/**
	 * Gets the id transaccion.
	 * 
	 * @return the id transaccion
	 */
	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Sets the id transaccion.
	 * 
	 * @param idTransaccion
	 *            the new id transaccion
	 */
	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(idTransaccion);
		return hcb.toHashCode();
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
		TransaccionViewIn other = (TransaccionViewIn) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(idTransaccion, other.getIdTransaccion());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(idTransaccion);
		return sb.toString();
	}

}
