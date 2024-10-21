/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado para retornar del DAO.
 *
 * @author
 *
 */
public class FirmantesOutEntity {

	/** The cuentas. */
	private List<FirmanteEntity> cuentas;

	/** The custodias. */
	private List<FirmanteEntity> custodias;

	/** The fondos. */
	private List<FirmanteEntity> fondos;

	/** The plazo fijo. */
	private List<FirmanteEntity> plazoFijo;

	/** The prestamos. */
	private List<FirmantePrestamoEntity> prestamos;

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<FirmanteEntity> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the cuentas to set
	 */
	public void setCuentas(List<FirmanteEntity> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the custodias.
	 *
	 * @return the custodias
	 */
	public List<FirmanteEntity> getCustodias() {
		return custodias;
	}

	/**
	 * Sets the custodias.
	 *
	 * @param custodias
	 *            the custodias to set
	 */
	public void setCustodias(List<FirmanteEntity> custodias) {
		this.custodias = custodias;
	}

	/**
	 * Gets the fondos.
	 *
	 * @return the fondos
	 */
	public List<FirmanteEntity> getFondos() {
		return fondos;
	}

	/**
	 * Sets the fondos.
	 *
	 * @param fondos
	 *            the fondos to set
	 */
	public void setFondos(List<FirmanteEntity> fondos) {
		this.fondos = fondos;
	}

	/**
	 * Gets the plazo fijo.
	 *
	 * @return the plazoFijo
	 */
	public List<FirmanteEntity> getPlazoFijo() {
		return plazoFijo;
	}

	/**
	 * Sets the plazo fijo.
	 *
	 * @param plazoFijo
	 *            the plazoFijo to set
	 */
	public void setPlazoFijo(List<FirmanteEntity> plazoFijo) {
		this.plazoFijo = plazoFijo;
	}

	/**
	 * Gets the prestamos.
	 *
	 * @return the prestamos
	 */
	public List<FirmantePrestamoEntity> getPrestamos() {
		return prestamos;
	}

	/**
	 * Sets the prestamos.
	 *
	 * @param prestamos
	 *            the prestamos to set
	 */
	public void setPrestamos(List<FirmantePrestamoEntity> prestamos) {
		this.prestamos = prestamos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cuentas).append(custodias).append(fondos).append(plazoFijo)
				.append(prestamos)
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

		FirmantesOutEntity other = (FirmantesOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cuentas, other.getCuentas());
		eb.append(custodias, other.getCustodias());
		eb.append(fondos, other.getFondos());
		eb.append(plazoFijo, other.getPlazoFijo());
		eb.append(prestamos, other.getPrestamos());
		return eb
				// agregar los appends que corresponda segun los atributos
				// cargados, Ej: .append(extra, other.getExtra())
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this).append("Cuentas", cuentas).append("Custodias", custodias)
				.append("Fondos", fondos).append("PlazoFijo", plazoFijo).append("prestamos", prestamos)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}

}