/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.ondemand.ws;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Ej:
 * 
 * <pre>
 * 	<code>
 * 		<msj-error cod="0" desc="Error en Dispatcher.execute():"/>
 * 	</code>
 * </pre>
 * 
 * @author sergio.e.goldentair
 *
 */
public class OnDemandResponseData {

	/** The Constant CODIGO. */
	public static final String CODIGO = "cod";

	/** The Constant DESCRIPCION. */
	public static final String DESCRIPCION = "desc";

	/** The cod. */
	private String cod;

	/** The desc. */
	private String desc;

	/**
	 * Gets the cod.
	 *
	 * @return the cod
	 */
	public String getCod() {
		return cod;
	}

	/**
	 * Sets the cod.
	 *
	 * @param cod
	 *            the cod to set
	 */
	public void setCod(String cod) {
		this.cod = cod;
	}

	/**
	 * Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the desc.
	 *
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cod).append(desc).hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OnDemandResponseData other = (OnDemandResponseData) obj;
		return new EqualsBuilder().append(cod, other.getCod()).append(desc, other.getDesc()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("codigo", cod).append("descripcion", desc).toString();
	}
}
