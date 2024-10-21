/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class TipoDocumento. Clase que representa el tag
 * /tarjetas/tarjeta/posicionConsolidada/datos/tipoDocumento/
 */
@XmlRootElement(name = "tipoDocumento")
@XmlAccessorType(XmlAccessType.FIELD)
public class TipoDocumento {

	/** The codigo. */
	@XmlAttribute(name = "codigo")
	private String codigo;

	/** The valor. */
	@XmlValue
	private String valor;

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigo);
		hcb.append(valor);
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TipoDocumento other = (TipoDocumento) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codigo, other.getCodigo());
		eb.append(valor, other.getValor());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "TipoDocumento [codigo=" + codigo + ", valor=" + valor + "]";
	}

}
