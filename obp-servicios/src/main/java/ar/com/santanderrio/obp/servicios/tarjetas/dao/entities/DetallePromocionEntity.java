package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class DetallePromocionEntity.
 * 
 * Representa el tag
 * /tarjetas/tarjeta/document/movimientos/tarjeta/../detallePromocion/
 */
@XmlRootElement(name = "detallePromocion")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetallePromocionEntity {

	/** The descuento establecimiento. */
	@XmlElement(name = "DescuentoEstablecimiento")
	private String descuentoEstablecimiento;

	/** The descuento usuario. */
	@XmlElement(name = "DescuentoUsuario")
	private String descuentoUsuario;

	/** The Porcentaje establecimiento. */
	@XmlElement(name = "PorcentajeEstablecimiento")
	private String porcentajeEstablecimiento;

	/** The Porcentaje tope establecimiento. */
	@XmlElement(name = "PorcentajeTopeEstablecimiento")
	private String porcentajeTopeEstablecimiento;

	/** The Porcentaje tope usuario. */
	@XmlElement(name = "PorcentajeTopeUsuario")
	private String porcentajeTopeUsuario;

	/** The porcentaje usuario. */
	@XmlElement(name = "PorcentajeUsuario")
	private String porcentajeUsuario;

	/** The codigo campania banco. */
	@XmlElement(name = "codigoCampaniaBanco")
	private String codigoCampaniaBanco;

	/** The codigo campania visa. */
	@XmlElement(name = "codigoCampaniaVisa")
	private String codigoCampaniaVisa;

	/** The denominacion campania. */
	@XmlElement(name = "denominacionCampania")
	private String denominacionCampania;

	/**
	 * Gets the descuento establecimiento.
	 *
	 * @return the descuento establecimiento
	 */
	public String getDescuentoEstablecimiento() {
		return descuentoEstablecimiento;
	}

	/**
	 * Sets the descuento establecimiento.
	 *
	 * @param descuentoEstablecimiento the new descuento establecimiento
	 */
	public void setDescuentoEstablecimiento(String descuentoEstablecimiento) {
		this.descuentoEstablecimiento = descuentoEstablecimiento;
	}

	/**
	 * Gets the descuento usuario.
	 *
	 * @return the descuento usuario
	 */
	public String getDescuentoUsuario() {
		return descuentoUsuario;
	}

	/**
	 * Sets the descuento usuario.
	 *
	 * @param descuentoUsuario the new descuento usuario
	 */
	public void setDescuentoUsuario(String descuentoUsuario) {
		this.descuentoUsuario = descuentoUsuario;
	}

	/**
	 * Gets the porcentaje establecimiento.
	 *
	 * @return the porcentaje establecimiento
	 */
	public String getPorcentajeEstablecimiento() {
		return porcentajeEstablecimiento;
	}

	/**
	 * Sets the porcentaje establecimiento.
	 *
	 * @param porcentajeEstablecimiento the new porcentaje establecimiento
	 */
	public void setPorcentajeEstablecimiento(String porcentajeEstablecimiento) {
		this.porcentajeEstablecimiento = porcentajeEstablecimiento;
	}

	/**
	 * Gets the porcentaje tope establecimiento.
	 *
	 * @return the porcentaje tope establecimiento
	 */
	public String getPorcentajeTopeEstablecimiento() {
		return porcentajeTopeEstablecimiento;
	}

	/**
	 * Sets the porcentaje tope establecimiento.
	 *
	 * @param porcentajeTopeEstablecimiento the new porcentaje tope establecimiento
	 */
	public void setPorcentajeTopeEstablecimiento(String porcentajeTopeEstablecimiento) {
		this.porcentajeTopeEstablecimiento = porcentajeTopeEstablecimiento;
	}

	/**
	 * Gets the porcentaje tope usuario.
	 *
	 * @return the porcentaje tope usuario
	 */
	public String getPorcentajeTopeUsuario() {
		return porcentajeTopeUsuario;
	}

	/**
	 * Sets the porcentaje tope usuario.
	 *
	 * @param porcentajeTopeUsuario the new porcentaje tope usuario
	 */
	public void setPorcentajeTopeUsuario(String porcentajeTopeUsuario) {
		this.porcentajeTopeUsuario = porcentajeTopeUsuario;
	}

	/**
	 * Gets the porcentaje usuario.
	 *
	 * @return the porcentaje usuario
	 */
	public String getPorcentajeUsuario() {
		return porcentajeUsuario;
	}

	/**
	 * Sets the porcentaje usuario.
	 *
	 * @param porcentajeUsuario the new porcentaje usuario
	 */
	public void setPorcentajeUsuario(String porcentajeUsuario) {
		this.porcentajeUsuario = porcentajeUsuario;
	}

	/**
	 * Gets the codigo campania banco.
	 *
	 * @return the codigo campania banco
	 */
	public String getCodigoCampaniaBanco() {
		return codigoCampaniaBanco;
	}

	/**
	 * Sets the codigo campania banco.
	 *
	 * @param codigoCampaniaBanco the new codigo campania banco
	 */
	public void setCodigoCampaniaBanco(String codigoCampaniaBanco) {
		this.codigoCampaniaBanco = codigoCampaniaBanco;
	}

	/**
	 * Gets the codigo campania visa.
	 *
	 * @return the codigo campania visa
	 */
	public String getCodigoCampaniaVisa() {
		return codigoCampaniaVisa;
	}

	/**
	 * Sets the codigo campania visa.
	 *
	 * @param codigoCampaniaVisa the new codigo campania visa
	 */
	public void setCodigoCampaniaVisa(String codigoCampaniaVisa) {
		this.codigoCampaniaVisa = codigoCampaniaVisa;
	}

	/**
	 * Gets the denominacion campania.
	 *
	 * @return the denominacion campania
	 */
	public String getDenominacionCampania() {
		return denominacionCampania;
	}

	/**
	 * Sets the denominacion campania.
	 *
	 * @param denominacionCampania the new denominacion campania
	 */
	public void setDenominacionCampania(String denominacionCampania) {
		this.denominacionCampania = denominacionCampania;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(descuentoEstablecimiento);
		hcb.append(descuentoUsuario);
		hcb.append(porcentajeEstablecimiento);
		hcb.append(porcentajeTopeEstablecimiento);
		hcb.append(porcentajeTopeUsuario);
		hcb.append(porcentajeUsuario);
		hcb.append(codigoCampaniaBanco);
		hcb.append(codigoCampaniaVisa);
		hcb.append(denominacionCampania);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
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
		DetallePromocionEntity other = (DetallePromocionEntity) obj;
		return new EqualsBuilder().append(descuentoEstablecimiento, other.descuentoEstablecimiento)
				.append(descuentoUsuario, other.descuentoUsuario)
				.append(porcentajeEstablecimiento, other.porcentajeEstablecimiento)
				.append(porcentajeTopeEstablecimiento, other.porcentajeTopeEstablecimiento)
				.append(porcentajeTopeUsuario, other.porcentajeTopeUsuario)
				.append(porcentajeUsuario, other.porcentajeUsuario)
				.append(codigoCampaniaBanco, other.codigoCampaniaBanco)
				.append(codigoCampaniaVisa, other.codigoCampaniaVisa)
				.append(denominacionCampania, other.denominacionCampania).isEquals();
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Establecimiento [descuentoEstablecimiento=" + descuentoEstablecimiento + ", descuentoUsuario="
				+ descuentoUsuario + ", porcentajeEstablecimiento=" + porcentajeEstablecimiento
				+ ", porcentajeTopeEstablecimiento=" + porcentajeTopeEstablecimiento + ", porcentajeTopeUsuario="
				+ porcentajeTopeUsuario + ", porcentajeUsuario=" + porcentajeUsuario + ", codigoCampaniaBanco="
				+ codigoCampaniaBanco + ", codigoCampaniaVisa=" + codigoCampaniaVisa + ", denominacionCampania="
				+ denominacionCampania + "]";
	}

}
