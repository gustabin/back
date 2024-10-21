/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.math.BigDecimal;
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
public class TenenciasOutEntity {

	/** The cuentas entity. */
	private List<CuentasEntity> cuentasEntity;

	/** The custodia entity. */
	private List<CustodiaEntity> custodiaEntity;

	/** The fondos entity. */
	private List<FondosRimpEntity> fondosEntity;

	/** The fondos pendientes entity. */
	private List<FondosPendientesEntity> fondosPendientesEntity;

	/** The impuestos entity. */
	private List<ImpuestosEntity> impuestosEntity;

	/** The plazo fijo entity. */
	private List<PlazoFijoEntity> plazoFijoEntity;

	/** The prestamos entity. */
	private List<PrestamosEntity> prestamosEntity;

	/** The tarjetas entity. */
	private List<TarjetasEntity> tarjetasEntity;

	/** The rendimiento fondos entity. */
	private List<RendimientoFondosEntity> rendimientoFondosEntity;

	/** The impuesto moneda extranjera entity. */
	private List<ImpuestoMonedaExtranjeraEntity> impuestoMonedaExtranjeraEntity;

	/** The coti dolar. */
	private BigDecimal cotiDolar;

	/**
	 * Gets the cuentas entity.
	 *
	 * @return the cuentasEntity
	 */
	public List<CuentasEntity> getCuentasEntity() {
		return cuentasEntity;
	}

	/**
	 * Sets the cuentas entity.
	 *
	 * @param cuentasEntity
	 *            the cuentasEntity to set
	 */
	public void setCuentasEntity(List<CuentasEntity> cuentasEntity) {
		this.cuentasEntity = cuentasEntity;
	}

	/**
	 * Gets the custodia entity.
	 *
	 * @return the custodiaEntity
	 */
	public List<CustodiaEntity> getCustodiaEntity() {
		return custodiaEntity;
	}

	/**
	 * Sets the custodia entity.
	 *
	 * @param custodiaEntity
	 *            the custodiaEntity to set
	 */
	public void setCustodiaEntity(List<CustodiaEntity> custodiaEntity) {
		this.custodiaEntity = custodiaEntity;
	}

	/**
	 * Gets the fondos entity.
	 *
	 * @return the fondosEntity
	 */
	public List<FondosRimpEntity> getFondosEntity() {
		return fondosEntity;
	}

	/**
	 * Sets the fondos entity.
	 *
	 * @param fondosEntity
	 *            the fondosEntity to set
	 */
	public void setFondosEntity(List<FondosRimpEntity> fondosEntity) {
		this.fondosEntity = fondosEntity;
	}

	/**
	 * Gets the fondos pendientes entity.
	 *
	 * @return the fondosPendientesEntity
	 */
	public List<FondosPendientesEntity> getFondosPendientesEntity() {
		return fondosPendientesEntity;
	}

	/**
	 * Sets the fondos pendientes entity.
	 *
	 * @param fondosPendientesEntity
	 *            the fondosPendientesEntity to set
	 */
	public void setFondosPendientesEntity(List<FondosPendientesEntity> fondosPendientesEntity) {
		this.fondosPendientesEntity = fondosPendientesEntity;
	}

	/**
	 * Gets the impuestos entity.
	 *
	 * @return the impuestosEntity
	 */
	public List<ImpuestosEntity> getImpuestosEntity() {
		return impuestosEntity;
	}

	/**
	 * Sets the impuestos entity.
	 *
	 * @param impuestosEntity
	 *            the impuestosEntity to set
	 */
	public void setImpuestosEntity(List<ImpuestosEntity> impuestosEntity) {
		this.impuestosEntity = impuestosEntity;
	}

	/**
	 * Gets the plazo fijo entity.
	 *
	 * @return the plazoFijoEntity
	 */
	public List<PlazoFijoEntity> getPlazoFijoEntity() {
		return plazoFijoEntity;
	}

	/**
	 * Sets the plazo fijo entity.
	 *
	 * @param plazoFijoEntity
	 *            the plazoFijoEntity to set
	 */
	public void setPlazoFijoEntity(List<PlazoFijoEntity> plazoFijoEntity) {
		this.plazoFijoEntity = plazoFijoEntity;
	}

	/**
	 * Gets the prestamos entity.
	 *
	 * @return the prestamosEntity
	 */
	public List<PrestamosEntity> getPrestamosEntity() {
		return prestamosEntity;
	}

	/**
	 * Sets the prestamos entity.
	 *
	 * @param prestamosEntity
	 *            the prestamosEntity to set
	 */
	public void setPrestamosEntity(List<PrestamosEntity> prestamosEntity) {
		this.prestamosEntity = prestamosEntity;
	}

	/**
	 * Gets the tarjetas entity.
	 *
	 * @return the tarjetasEntity
	 */
	public List<TarjetasEntity> getTarjetasEntity() {
		return tarjetasEntity;
	}

	/**
	 * Sets the tarjetas entity.
	 *
	 * @param tarjetasEntity
	 *            the tarjetasEntity to set
	 */
	public void setTarjetasEntity(List<TarjetasEntity> tarjetasEntity) {
		this.tarjetasEntity = tarjetasEntity;
	}

	/**
	 * Gets the rendimiento fondos entity.
	 *
	 * @return the rendimientoFondosEntity
	 */
	public List<RendimientoFondosEntity> getRendimientoFondosEntity() {
		return rendimientoFondosEntity;
	}

	/**
	 * Sets the rendimiento fondos entity.
	 *
	 * @param rendimientoFondosEntity
	 *            the rendimientoFondosEntity to set
	 */
	public void setRendimientoFondosEntity(List<RendimientoFondosEntity> rendimientoFondosEntity) {
		this.rendimientoFondosEntity = rendimientoFondosEntity;
	}

	/**
	 * Gets the impuesto moneda extranjera entity.
	 *
	 * @return the impuestoMonedaExtranjeraEntity
	 */
	public List<ImpuestoMonedaExtranjeraEntity> getImpuestoMonedaExtranjeraEntity() {
		return impuestoMonedaExtranjeraEntity;
	}

	/**
	 * Sets the impuesto moneda extranjera entity.
	 *
	 * @param impuestoMonedaExtranjeraEntity
	 *            the impuestoMonedaExtranjeraEntity to set
	 */
	public void setImpuestoMonedaExtranjeraEntity(List<ImpuestoMonedaExtranjeraEntity> impuestoMonedaExtranjeraEntity) {
		this.impuestoMonedaExtranjeraEntity = impuestoMonedaExtranjeraEntity;
	}

	/**
	 * Gets the coti dolar.
	 *
	 * @return the cotiDolar
	 */
	public BigDecimal getCotiDolar() {
		return cotiDolar;
	}

	/**
	 * Sets the coti dolar.
	 *
	 * @param cotiDolar
	 *            the cotiDolar to set
	 */
	public void setCotiDolar(BigDecimal cotiDolar) {
		this.cotiDolar = cotiDolar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cuentasEntity).append(custodiaEntity).append(fondosEntity)
				.append(fondosPendientesEntity).append(impuestosEntity).append(plazoFijoEntity).append(prestamosEntity)
				.append(rendimientoFondosEntity).append(impuestoMonedaExtranjeraEntity).append(tarjetasEntity)
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

		TenenciasOutEntity other = (TenenciasOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cuentasEntity, other.getCuentasEntity());
		eb.append(custodiaEntity, other.getCustodiaEntity());
		eb.append(fondosEntity, other.getFondosEntity());
		eb.append(fondosPendientesEntity, other.getFondosPendientesEntity());
		eb.append(impuestosEntity, other.getImpuestosEntity());
		eb.append(plazoFijoEntity, other.getPlazoFijoEntity());
		eb.append(prestamosEntity, other.getPrestamosEntity());
		eb.append(tarjetasEntity, other.getTarjetasEntity());
		eb.append(impuestoMonedaExtranjeraEntity, other.getImpuestoMonedaExtranjeraEntity());
		eb.append(rendimientoFondosEntity, other.getRendimientoFondosEntity());

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

		return new ToStringBuilder(this).append("cuentasEntity", cuentasEntity).append("custodiaEntity", custodiaEntity)
				.append("fondosEntity", fondosEntity).append("fondosPendientesEntity", fondosPendientesEntity)
				.append("impuestosEntity", impuestosEntity).append("plazoFijoEntity", plazoFijoEntity)
				.append("prestamosEntity", prestamosEntity).append("tjccurtyp", tarjetasEntity)
				.append("impuestoMonedaExtranjeraEntity", impuestoMonedaExtranjeraEntity)
				.append("rendimientoFondosEntity", rendimientoFondosEntity)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}

}