/**
 *
 */
package ar.com.santanderrio.obp.servicios.tenencias.dto;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado junto a Respuesta<T> desde el BO al Manager.
 * 
 * @author
 *
 */
public class TenenciasDTO {

	/** The cuentas DTO. */
	private List<CuentaDTO> cuentasDTO;

	/** The custodia DTO. */
	private List<CustodiaDTO> custodiaDTO;

	/** The fondos DTO. */
	private List<FondoDTO> fondosDTO;

	/** The fondos pendientes DTO. */
	private List<FondoPendienteDTO> fondosPendientesDTO;

	/** The impuestos DTO. */
	private List<ImpuestoDTO> impuestosDTO;

	/** The plazo fijo DTO. */
	private List<PlazoFijoDTO> plazoFijoDTO;

	/** The prestamos DTO. */
	private List<PrestamoDTO> prestamosDTO;

	/** The tarjetas DTO. */
	private List<TarjetasDTO> tarjetasDTO;

	/** The rendimiento fondos DTO. */
	private List<RendimientoFondoDTO> rendimientoFondosDTO;

	/** The impuesto moneda extranjera DTO. */
	private List<ImpuestoMonedaExtranjeraDTO> impuestoMonedaExtranjeraDTO;

	/** The coti dolar. */
	private BigDecimal cotiDolar;

	/**
	 * Gets the cuentas DTO.
	 *
	 * @return the cuentasDTO
	 */
	public List<CuentaDTO> getCuentasDTO() {
		return cuentasDTO;
	}

	/**
	 * Sets the cuentas DTO.
	 *
	 * @param cuentasDTO
	 *            the cuentasDTO to set
	 */
	public void setCuentasDTO(List<CuentaDTO> cuentasDTO) {
		this.cuentasDTO = cuentasDTO;
	}

	/**
	 * Gets the custodia DTO.
	 *
	 * @return the custodiaDTO
	 */
	public List<CustodiaDTO> getCustodiaDTO() {
		return custodiaDTO;
	}

	/**
	 * Sets the custodia DTO.
	 *
	 * @param custodiaDTO
	 *            the custodiaDTO to set
	 */
	public void setCustodiaDTO(List<CustodiaDTO> custodiaDTO) {
		this.custodiaDTO = custodiaDTO;
	}

	/**
	 * Gets the fondos DTO.
	 *
	 * @return the fondosDTO
	 */
	public List<FondoDTO> getFondosDTO() {
		return fondosDTO;
	}

	/**
	 * Sets the fondos DTO.
	 *
	 * @param fondosDTO
	 *            the fondosDTO to set
	 */
	public void setFondosDTO(List<FondoDTO> fondosDTO) {
		this.fondosDTO = fondosDTO;
	}

	/**
	 * Gets the fondos pendientes DTO.
	 *
	 * @return the fondosPendientesDTO
	 */
	public List<FondoPendienteDTO> getFondosPendientesDTO() {
		return fondosPendientesDTO;
	}

	/**
	 * Sets the fondos pendientes DTO.
	 *
	 * @param fondosPendientesDTO
	 *            the fondosPendientesDTO to set
	 */
	public void setFondosPendientesDTO(List<FondoPendienteDTO> fondosPendientesDTO) {
		this.fondosPendientesDTO = fondosPendientesDTO;
	}

	/**
	 * Gets the impuestos DTO.
	 *
	 * @return the impuestosDTO
	 */
	public List<ImpuestoDTO> getImpuestosDTO() {
		return impuestosDTO;
	}

	/**
	 * Sets the impuestos DTO.
	 *
	 * @param impuestosDTO
	 *            the impuestosDTO to set
	 */
	public void setImpuestosDTO(List<ImpuestoDTO> impuestosDTO) {
		this.impuestosDTO = impuestosDTO;
	}

	/**
	 * Gets the plazo fijo DTO.
	 *
	 * @return the plazoFijoDTO
	 */
	public List<PlazoFijoDTO> getPlazoFijoDTO() {
		return plazoFijoDTO;
	}

	/**
	 * Sets the plazo fijo DTO.
	 *
	 * @param plazoFijoDTO
	 *            the plazoFijoDTO to set
	 */
	public void setPlazoFijoDTO(List<PlazoFijoDTO> plazoFijoDTO) {
		this.plazoFijoDTO = plazoFijoDTO;
	}

	/**
	 * Gets the prestamos DTO.
	 *
	 * @return the prestamosDTO
	 */
	public List<PrestamoDTO> getPrestamosDTO() {
		return prestamosDTO;
	}

	/**
	 * Sets the prestamos DTO.
	 *
	 * @param prestamosDTO
	 *            the prestamosDTO to set
	 */
	public void setPrestamosDTO(List<PrestamoDTO> prestamosDTO) {
		this.prestamosDTO = prestamosDTO;
	}

	/**
	 * Gets the tarjetas DTO.
	 *
	 * @return the tarjetasDTO
	 */
	public List<TarjetasDTO> getTarjetasDTO() {
		return tarjetasDTO;
	}

	/**
	 * Sets the tarjetas DTO.
	 *
	 * @param tarjetasDTO
	 *            the tarjetasDTO to set
	 */
	public void setTarjetasDTO(List<TarjetasDTO> tarjetasDTO) {
		this.tarjetasDTO = tarjetasDTO;
	}

	/**
	 * Gets the rendimiento fondos DTO.
	 *
	 * @return the rendimientoFondosDTO
	 */
	public List<RendimientoFondoDTO> getRendimientoFondosDTO() {
		return rendimientoFondosDTO;
	}

	/**
	 * Sets the rendimiento fondos DTO.
	 *
	 * @param rendimientoFondosDTO
	 *            the rendimientoFondosDTO to set
	 */
	public void setRendimientoFondosDTO(List<RendimientoFondoDTO> rendimientoFondosDTO) {
		this.rendimientoFondosDTO = rendimientoFondosDTO;
	}

	/**
	 * Gets the impuesto moneda extranjera DTO.
	 *
	 * @return the impuestoMonedaExtranjeraDTO
	 */
	public List<ImpuestoMonedaExtranjeraDTO> getImpuestoMonedaExtranjeraDTO() {
		return impuestoMonedaExtranjeraDTO;
	}

	/**
	 * Sets the impuesto moneda extranjera DTO.
	 *
	 * @param impuestoMonedaExtranjeraDTO
	 *            the impuestoMonedaExtranjeraDTO to set
	 */
	public void setImpuestoMonedaExtranjeraDTO(List<ImpuestoMonedaExtranjeraDTO> impuestoMonedaExtranjeraDTO) {
		this.impuestoMonedaExtranjeraDTO = impuestoMonedaExtranjeraDTO;
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

		TenenciasDTO other = (TenenciasDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
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
		return new ToStringBuilder(this)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}
}