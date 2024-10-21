/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.nio.charset.Charset;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class TerminoYCondicionUltimoResumenView.
 *
 * @author florencia.n.martinez
 */
public class TerminoYCondicionUltimoResumenView {

	/** The legal. */
	private String legal;

	/**
	 * Instantiates a new termino y condicion ultimo resumen view.
	 */
	public TerminoYCondicionUltimoResumenView() {
		super();
	}

	/**
	 * Instantiates a new termino y condicion ultimo resumen view.
	 *
	 * @param legal
	 *            the legal
	 */
	public TerminoYCondicionUltimoResumenView(String legal) {
		this.setLegal(legal);
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
	    this.legal = new String(legal.getBytes(),Charset.forName("UTF-8"));
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(legal);
		return hcb.hashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
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
		TerminoYCondicionUltimoResumenView other = (TerminoYCondicionUltimoResumenView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(legal, other.getLegal());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("legal", legal).toString();
	}

}