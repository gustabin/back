/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesLineaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * Representa una tarjeta de la pantalla de cuotas pendientes de tarjetas.
 */
public class CuotasPendientesTarjetaView {

	/** The marca. */
	private String marca;

	/** The numero. */
	private String numero;

	/** The es titular. */
	private Boolean esTitular;

	/** The nombre adicional. */
	private String nombreAdicional;

	/** The total. */
	private String total;

	/** The mostrar nombre adicional. */
	private Boolean mostrarNombreAdicional = Boolean.FALSE;

	/** The lineas cuotas pendientes. */
	private List<CuotasPendientesLineaView> lineasCuotasPendientes;

	/**
	 * Instantiates a new cuotas pendientes tarjeta view.
	 */
	public CuotasPendientesTarjetaView() {
		super();
	}

	/**
	 * Instantiates a new cuotas pendientes tarjeta view.
	 *
	 * @param cuotasPendientesTarjetaDTO
	 *            the cuotas pendientes tarjeta DTO
	 */
	public CuotasPendientesTarjetaView(CuotasPendientesTarjetaDTO cuotasPendientesTarjetaDTO) {
		super();
		this.setMarca(cuotasPendientesTarjetaDTO.getMarca());
		this.setNumero(cuotasPendientesTarjetaDTO.getNumero());
		this.setEsTitular(cuotasPendientesTarjetaDTO.getEsTitular());
		this.setNombreAdicional(cuotasPendientesTarjetaDTO.getNombreAdicional());
		this.setTotal(cuotasPendientesTarjetaDTO.getTotal());
		List<CuotasPendientesLineaView> listaLineasCuotasPendientes = new ArrayList<CuotasPendientesLineaView>();
		for (CuotasPendientesLineaDTO cuotasPendientesLineaDTO : cuotasPendientesTarjetaDTO
				.getLineasCuotasPendientes()) {
			CuotasPendientesLineaView cuotasPendientesLineaView = new CuotasPendientesLineaView(
					cuotasPendientesLineaDTO);
			listaLineasCuotasPendientes.add(cuotasPendientesLineaView);
		}
		this.setLineasCuotasPendientes(listaLineasCuotasPendientes);
	}

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
		this.nombreAdicional = TarjetaUtils.obtenerNombreFormateado(nombreAdicional);
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the new total
	 */
	public void setTotal(BigDecimal total) {
		this.total = ISBANStringUtils.formatearSaldo(total);
	}

	/**
	 * Gets the lineas cuotas pendientes.
	 *
	 * @return the lineas cuotas pendientes
	 */
	public List<CuotasPendientesLineaView> getLineasCuotasPendientes() {
		return lineasCuotasPendientes;
	}

	/**
	 * Sets the lineas cuotas pendientes.
	 *
	 * @param lineasCuotasPendientes
	 *            the new lineas cuotas pendientes
	 */
	public void setLineasCuotasPendientes(List<CuotasPendientesLineaView> lineasCuotasPendientes) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuotasPendientesTarjetaView [marca=" + marca + ", numero=" + numero + ", esTitular=" + esTitular
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
		CuotasPendientesTarjetaView other = (CuotasPendientesTarjetaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(esTitular, other.esTitular);
		eb.append(lineasCuotasPendientes, other.lineasCuotasPendientes);
		eb.append(marca, other.marca);
		eb.append(nombreAdicional, other.nombreAdicional);
		eb.append(numero, other.numero);
		eb.append(total, other.total);
		return eb.isEquals();
	}

}