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
 * The Class ConsultaCitaRequest.
 *
 * @author florencia.n.martinez
 */
@XmlRootElement(name = "ConsultaCitaRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsultaCitaRequest {

	/** The parametros. */
	@XmlElement(name = "parametros")
	private ParametrosCita parametros;

	/**
	 * Gets the parametros.
	 *
	 * @return the parametros
	 */
	public final ParametrosCita getParametros() {
		return parametros;
	}

	/**
	 * Sets the parametros.
	 *
	 * @param parametros
	 *            the parametros to set
	 */
	public final void setParametros(ParametrosCita parametros) {
		this.parametros = parametros;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(parametros);
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
		ConsultaCitaRequest other = (ConsultaCitaRequest) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(parametros, other.getParametros());
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
		return new ToStringBuilder(this).append("parametros", parametros).toString();
	}

}
