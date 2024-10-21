/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ParametrosCita.
 *
 * @author florencia.n.martinez
 */
@XmlRootElement(name = "parametros")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParametrosCita {

	/** The nup. */
	@XmlElement
	private String nup;

	/**
	 * Instantiates a new parametros cita.
	 */
	public ParametrosCita() {
		super();
	}
	
	/**
	 * Instantiates a new parametros cita.
	 *
	 * @param nup the nup
	 */
	public ParametrosCita(String nup) {
		this.nup = nup;
	}
	
	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public final String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public final void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nup);
		return hcb.toHashCode();
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParametrosCita other = (ParametrosCita) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(nup, other.getNup());
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
		return new ToStringBuilder(this).append("nup", nup).toString();
	}

}
