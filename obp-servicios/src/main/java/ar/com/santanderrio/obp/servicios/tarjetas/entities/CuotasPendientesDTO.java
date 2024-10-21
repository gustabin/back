/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Objeto View para la pantalla de Cuotas Pendientes de tarjetas.
 */
public class CuotasPendientesDTO {

	/** The total cuotas pendientes. */
	private BigDecimal totalCuotasPendientes;

	/** The tarjetas cuotas pendientes. */
	private List<CuotasPendientesTarjetaDTO> tarjetasCuotasPendientes;

	/** The fecha reporte. */
	private String fechaReporte;

	/**
	 * Instantiates a new cuotas pendientes DTO.
	 *
	 * @param totalPendientes
	 *            the total pendientes
	 * @param tarjetasPendientes
	 *            the tarjetas pendientes
	 */
	public CuotasPendientesDTO(BigDecimal totalPendientes, List<CuotasPendientesTarjetaDTO> tarjetasPendientes) {
		totalCuotasPendientes = totalPendientes;
		tarjetasCuotasPendientes = tarjetasPendientes;
	}

	/**
	 * Instantiates a new cuotas pendientes DTO.
	 *
	 * @param totalPendientes
	 *            the total pendientes
	 * @param tarjetasPendientes
	 *            the tarjetas pendientes
	 * @param fechaReporteIn
	 *            the fecha reporte in
	 */
	public CuotasPendientesDTO(BigDecimal totalPendientes, List<CuotasPendientesTarjetaDTO> tarjetasPendientes,
			String fechaReporteIn) {
		totalCuotasPendientes = totalPendientes;
		tarjetasCuotasPendientes = tarjetasPendientes;
		Boolean variable = tarjetasPendientes.size()>1;
		for(CuotasPendientesTarjetaDTO cuotas:  tarjetasPendientes){
			cuotas.setNecesitaSubTotalExcel(variable);
		}
		fechaReporte = fechaReporteIn;
	}

	/**
	 * Instantiates a new cuotas pendientes DTO.
	 */
	public CuotasPendientesDTO() {
	}

	/**
	 * Gets the total cuotas pendientes.
	 *
	 * @return the total cuotas pendientes
	 */
	public BigDecimal getTotalCuotasPendientes() {
		return totalCuotasPendientes;
	}

	/**
	 * Sets the total cuotas pendientes.
	 *
	 * @param totalCuotasPendientes
	 *            the new total cuotas pendientes
	 */
	public void setTotalCuotasPendientes(BigDecimal totalCuotasPendientes) {
		this.totalCuotasPendientes = totalCuotasPendientes;
	}

	/**
	 * Gets the tarjetas cuotas pendientes.
	 *
	 * @return the tarjetas cuotas pendientes
	 */
	public List<CuotasPendientesTarjetaDTO> getTarjetasCuotasPendientes() {
		return tarjetasCuotasPendientes;
	}

	/**
	 * Sets the tarjetas cuotas pendientes.
	 *
	 * @param tarjetasCuotasPendientes
	 *            the new tarjetas cuotas pendientes
	 */
	public void setTarjetasCuotasPendientes(List<CuotasPendientesTarjetaDTO> tarjetasCuotasPendientes) {
		this.tarjetasCuotasPendientes = tarjetasCuotasPendientes;
	}

	
	/**
	 * Gets the tiene mas de una tarjeta.
	 *
	 * @return the tiene mas de una tarjeta
	 */
	public Boolean getTieneMasDeUnaTarjeta(){
		return tarjetasCuotasPendientes.size()>1;
	}

	/**
	 * Gets the fecha reporte.
	 *
	 * @return the fecha reporte
	 */
	public String getFechaReporte() {
		return fechaReporte;
	}

	/**
	 * Sets the fecha reporte.
	 *
	 * @param fechaReporte
	 *            the new fecha reporte
	 */
	public void setFechaReporte(String fechaReporte) {
		this.fechaReporte = fechaReporte;
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
		CuotasPendientesDTO other = (CuotasPendientesDTO) obj;
		return new EqualsBuilder().append(tarjetasCuotasPendientes, other.tarjetasCuotasPendientes)
				.append(totalCuotasPendientes, other.totalCuotasPendientes).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuotasPendientesDTO [totalCuotasPendientes=" + totalCuotasPendientes + ", tarjetasCuotasPendientes="
				+ tarjetasCuotasPendientes + "]";
	}
}
