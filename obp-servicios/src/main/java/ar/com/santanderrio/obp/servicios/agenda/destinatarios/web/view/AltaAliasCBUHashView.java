/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class AltaAliasCBUHash.
 *
 * @author florencia.n.martinez
 */
public class AltaAliasCBUHashView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The titular. */
	private String titular;

	/** The numero cuil. */
	private String numeroCuil;

	/** The banco. */
	private String banco;

	/** The alias. */
	private String alias;

	/** The cbu. */
	private String cbu;

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public final String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the titular to set
	 */
	public final void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the numero cuil.
	 *
	 * @return the numeroCuil
	 */
	public final String getNumeroCuil() {
		return numeroCuil;
	}

	/**
	 * Sets the numero cuil.
	 *
	 * @param numeroCuil
	 *            the numeroCuil to set
	 */
	public final void setNumeroCuil(String numeroCuil) {
		this.numeroCuil = numeroCuil;
	}

	/**
	 * Gets the banco.
	 *
	 * @return the banco
	 */
	public final String getBanco() {
		return banco;
	}

	/**
	 * Sets the banco.
	 *
	 * @param banco
	 *            the banco to set
	 */
	public final void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public final String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the alias to set
	 */
	public final void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public final String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the cbu to set
	 */
	public final void setCbu(String cbu) {
		this.cbu = cbu;
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
		hcb.append(banco);
		hcb.append(cbu);
		hcb.append(numeroCuil);
		hcb.append(titular);
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
		AltaAliasCBUHashView other = (AltaAliasCBUHashView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(alias, other.getAlias());
		eb.append(banco, other.getBanco());
		eb.append(cbu, other.getCbu());
		eb.append(numeroCuil, other.getNumeroCuil());
		eb.append(titular, other.getTitular());
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
		return new ToStringBuilder(this).append("titular", titular).append("numeroCuil", numeroCuil)
				.append("banco", banco).append("alias", alias).append("cbu", cbu).toString();
	}
}
