/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Representa una tarjeta de la pantalla de cuotas pendientes de tarjetas.
 */
public class CuotasPendientesTarjetaDTO {

	/** The marca. */
	private String marca;

	/** The numero. */
	private String numero;

	/** The es titular. */
	private Boolean esTitular;

	/** The nombre adicional. */
	private String nombreAdicional;
	
	/** The necesita sub total excel. */
	private Boolean necesitaSubTotalExcel;

	/** The total. */
	private BigDecimal total;

	/** The mostrar nombre adicional. */
	private Boolean mostrarNombreAdicional = Boolean.FALSE;

	/** The lineas cuotas pendientes. */
	private List<CuotasPendientesLineaDTO> lineasCuotasPendientes;

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca
	 *            the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the es titular.
	 *
	 * @return the es titular
	 */
	public Boolean getEsTitular() {
		return esTitular;
	}

	/**
	 * Sets the es titular.
	 *
	 * @param esTitular
	 *            the new es titular
	 */
	public void setEsTitular(Boolean esTitular) {
		this.esTitular = esTitular;
	}

	/**
	 * Gets the nombre adicional.
	 *
	 * @return the nombre adicional
	 */
	public String getNombreAdicional() {
		return nombreAdicional;
	}

	/**
	 * Sets the nombre adicional.
	 *
	 * @param nombreAdicional
	 *            the new nombre adicional
	 */
	public void setNombreAdicional(String nombreAdicional) {
		this.nombreAdicional = nombreAdicional;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the new total
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * Gets the lineas cuotas pendientes.
	 *
	 * @return the lineas cuotas pendientes
	 */
	public List<CuotasPendientesLineaDTO> getLineasCuotasPendientes() {
		return lineasCuotasPendientes;
	}

	/**
	 * Sets the lineas cuotas pendientes.
	 *
	 * @param lineasCuotasPendientes
	 *            the new lineas cuotas pendientes
	 */
	public void setLineasCuotasPendientes(List<CuotasPendientesLineaDTO> lineasCuotasPendientes) {
		this.lineasCuotasPendientes = lineasCuotasPendientes;
	}

	/**
	 * Gets the mostrar nombre adicional.
	 *
	 * @return the mostrarNombreAdicional
	 */
	public Boolean getMostrarNombreAdicional() {
		return mostrarNombreAdicional;
	}

	/**
	 * Sets the mostrar nombre adicional.
	 *
	 * @param mostrarNombreAdicional
	 *            the mostrarNombreAdicional to set
	 */
	public void setMostrarNombreAdicional(Boolean mostrarNombreAdicional) {
		this.mostrarNombreAdicional = mostrarNombreAdicional;
	}
	
	

	/**
	 * Gets the necesita sub total excel.
	 *
	 * @return the necesita sub total excel
	 */
	public Boolean getNecesitaSubTotalExcel() {
		return necesitaSubTotalExcel;
	}

	/**
	 * Sets the necesita sub total excel.
	 *
	 * @param necesitaSubTotalExcel
	 *            the new necesita sub total excel
	 */
	public void setNecesitaSubTotalExcel(Boolean necesitaSubTotalExcel) {
		this.necesitaSubTotalExcel = necesitaSubTotalExcel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuotasPendientesTarjetaDTO [marca=" + marca + ", numero=" + numero + ", esTitular=" + esTitular
				+ ", nombreAdicional=" + nombreAdicional + ", total=" + total + ", lineasCuotasPendientes="
				+ lineasCuotasPendientes + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(esTitular);
		hcb.append(lineasCuotasPendientes);
		hcb.append(marca);
		hcb.append(nombreAdicional);
		hcb.append(numero);
		hcb.append(total);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
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
		CuotasPendientesTarjetaDTO other = (CuotasPendientesTarjetaDTO) obj;
		return new EqualsBuilder().append(esTitular, other.esTitular)
				.append(lineasCuotasPendientes, other.lineasCuotasPendientes).append(marca, other.marca)
				.append(nombreAdicional, other.nombreAdicional).append(numero, other.numero).append(total, other.total)
				.isEquals();
	}

}
