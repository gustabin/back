/**
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
 * The Class Importe.
 *
 * @author florencia.n.martinez
 */
@XmlRootElement(name = "importe")
@XmlAccessorType(XmlAccessType.FIELD)
public class Importe {

	/** The String codigoTarjeta. */
	@XmlAttribute(name = "codigoTarjeta")
	private String codigoTarjeta;

	/** The String valor. */
	@XmlValue
	private String valor;

	/**
	 * Getter de codigoTarjeta.
	 * 
	 * @return the codigoTarjeta
	 */
	public String getCodigoTarjeta() {
		return codigoTarjeta;
	}

	/**
	 * Setter de codigoTarjeta.
	 * 
	 * @param codigoTarjeta
	 *            the codigoTarjeta to set
	 */
	public void setCodigoTarjeta(String codigoTarjeta) {
		this.codigoTarjeta = codigoTarjeta;
	}

	/**
	 * Getter de valor.
	 * 
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Setter de valor.
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
		HashCodeBuilder hcb = new HashCodeBuilder().append(codigoTarjeta).append(valor);
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
		Importe other = (Importe) obj;
		return new EqualsBuilder().append(codigoTarjeta, other.codigoTarjeta).append(valor, other.valor).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Importe [codigoTarjeta=" + codigoTarjeta + ", valor=" + valor + "]";
	}

}
