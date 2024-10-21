/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado para el Input del TenenciasSEI.
 * 
 * @author
 *
 */
/**
 * @author A279017
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TipoClienteCitiView {
	

	/** The codResultado. */
	private String codResultado;

	/** The descErrorTec. */
	private String descErrorTec;

	/** The descErrorAmig. */
	private String descErrorAmig;



	/**
	 * Gets the cod resultado.
	 *
	 * @return the cod resultado
	 */
	public String getCodResultado() {
		return codResultado;
	}

	/**
	 * Sets the cod resultado.
	 *
	 * @param codResultado
	 *            the new cod resultado
	 */
	public void setCodResultado(String codResultado) {
		this.codResultado = codResultado;
	}

	/**
	 * Gets the desc error tec.
	 *
	 * @return the desc error tec
	 */
	public String getDescErrorTec() {
		return descErrorTec;
	}

	/**
	 * Sets the desc error tec.
	 *
	 * @param descErrorTec
	 *            the new desc error tec
	 */
	public void setDescErrorTec(String descErrorTec) {
		this.descErrorTec = descErrorTec;
	}

	/**
	 * Gets the desc error amig.
	 *
	 * @return the desc error amig
	 */
	public String getDescErrorAmig() {
		return descErrorAmig;
	}

	/**
	 * Sets the desc error amig.
	 *
	 * @param descErrorAmig
	 *            the new desc error amig
	 */
	public void setDescErrorAmig(String descErrorAmig) {
		this.descErrorAmig = descErrorAmig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				// agregar los append con los atributos que correspondan
				// .append(extra)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		TipoClienteCitiView other = (TipoClienteCitiView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb

				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}

}
