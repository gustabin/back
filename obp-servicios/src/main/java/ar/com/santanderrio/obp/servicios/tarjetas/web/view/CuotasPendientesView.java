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
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesTarjetaDTO;

/**
 * Objeto View para la pantalla de Cuotas Pendientes de tarjetas.
 */
public class CuotasPendientesView {

	/** The total cuotas pendientes. */
	private String totalCuotasPendientes;

	/** The tarjetas cuotas pendientes. */
	private List<CuotasPendientesTarjetaView> tarjetasCuotasPendientes;

	/**
	 * Instantiates a new cuotas pendientes view.
	 */
	public CuotasPendientesView() {
		super();
	}

	/**
	 * Instantiates a new cuotas pendientes view.
	 *
	 * @param dto
	 *            the dto
	 */
	public CuotasPendientesView(CuotasPendientesDTO dto) {
		super();
		this.setTotalCuotasPendientes(dto.getTotalCuotasPendientes());
		List<CuotasPendientesTarjetaView> listTarjetasCuotasPendientes = new ArrayList<CuotasPendientesTarjetaView>();
		for (CuotasPendientesTarjetaDTO cuotasPendientesTarjetaDTO : dto.getTarjetasCuotasPendientes()) {
			CuotasPendientesTarjetaView cuotasPendientesTarjetaView = new CuotasPendientesTarjetaView(
					cuotasPendientesTarjetaDTO);
			listTarjetasCuotasPendientes.add(cuotasPendientesTarjetaView);
		}
		this.tarjetasCuotasPendientes = listTarjetasCuotasPendientes;
	}

	/**
	 * Gets the total cuotas pendientes.
	 *
	 * @return the total cuotas pendientes
	 */
	public String getTotalCuotasPendientes() {
		return totalCuotasPendientes;
	}

	/**
	 * Sets the total cuotas pendientes.
	 *
	 * @param totalCuotasPendientes
	 *            the new total cuotas pendientes
	 */
	public void setTotalCuotasPendientes(BigDecimal totalCuotasPendientes) {
		this.totalCuotasPendientes = ISBANStringUtils.formatearSaldo(totalCuotasPendientes);
	}

	/**
	 * Gets the tarjetas cuotas pendientes.
	 *
	 * @return the tarjetas cuotas pendientes
	 */
	public List<CuotasPendientesTarjetaView> getTarjetasCuotasPendientes() {
		return tarjetasCuotasPendientes;
	}

	/**
	 * Sets the tarjetas cuotas pendientes.
	 *
	 * @param tarjetasCuotasPendientes
	 *            the new tarjetas cuotas pendientes
	 */
	public void setTarjetasCuotasPendientes(List<CuotasPendientesTarjetaView> tarjetasCuotasPendientes) {
		this.tarjetasCuotasPendientes = tarjetasCuotasPendientes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(tarjetasCuotasPendientes);
		hcb.append(totalCuotasPendientes);
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
		CuotasPendientesView other = (CuotasPendientesView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(tarjetasCuotasPendientes, other.tarjetasCuotasPendientes);
		eb.append(totalCuotasPendientes, other.totalCuotasPendientes);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuotasPendientesView [totalCuotasPendientes=" + totalCuotasPendientes + ", tarjetasCuotasPendientes="
				+ tarjetasCuotasPendientes + "]";
	}
}
