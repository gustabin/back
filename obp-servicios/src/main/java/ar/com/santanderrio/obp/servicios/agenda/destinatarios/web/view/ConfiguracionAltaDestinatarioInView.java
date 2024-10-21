/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class ConfiguracionAltaDestinatarioInView.
 */
@XmlRootElement(name = "configuracionAltaDestinatarioInView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class ConfiguracionAltaDestinatarioInView extends AltaDestinatarioInView {

	/** The nro cuenta. */
	@Pattern(regexp = "^[0-9]{22}$")
	private String nroCbu;

	/** The alias. */
	@Pattern(regexp = "^[0-9|a-z|A-Z|\\.|\\-]{6,20}$")
	private String alias;

	/** The moneda. */
	private Boolean isPesos;

	/** The email. */
	private String email;

	/** The referencia apodo. */
	private String referenciaApodo;

	/**
	 * Gets the nro cbu.
	 *
	 * @return the nro cbu
	 */
	public String getNroCbu() {
		return nroCbu;
	}

	/**
	 * Sets the nro cbu.
	 *
	 * @param nroCbu
	 *            the new nro cbu
	 */
	public void setNroCbu(String nroCbu) {
		this.nroCbu = nroCbu;
	}

	/**
	 * Gets the checks if is pesos.
	 *
	 * @return the isPesos
	 */
	public Boolean getIsPesos() {
		return isPesos;
	}

	/**
	 * Sets the checks if is pesos.
	 *
	 * @param isPesos
	 *            the isPesos to set
	 */
	public void setIsPesos(Boolean isPesos) {
		this.isPesos = isPesos;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the referencia apodo.
	 *
	 * @return the referencia apodo
	 */
	public String getReferenciaApodo() {
		return referenciaApodo;
	}

	/**
	 * Sets the referencia apodo.
	 *
	 * @param referenciaApodo
	 *            the new referencia apodo
	 */
	public void setReferenciaApodo(String referenciaApodo) {
		this.referenciaApodo = referenciaApodo;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(alias);
		hcb.append(email);
		hcb.append(isPesos);
		hcb.append(nroCbu);
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
		ConfiguracionAltaDestinatarioInView other = (ConfiguracionAltaDestinatarioInView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(alias, other.getAlias());
		eb.append(email, other.getEmail());
		eb.append(isPesos, other.getIsPesos());
		eb.append(nroCbu, other.getNroCbu());
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
		return new ToStringBuilder(this).append("nroCbu", nroCbu).append("alias", alias).append("isPesos", isPesos)
				.append("email", email).toString();
	}

}
