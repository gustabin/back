/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.inversiones.TipoPapel;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author pablo.d.gargaglione
 *
 */

public class ConsultaTenenciaFCIInEntity {

	/** The Constant CINCO_ESPACIOS. */
	private static final String CINCO_ESPACIOS = "     ";

	/** The tipo papel enum. */
	private TipoPapel tipoPapelEnum;

	/** The especie codigo. */
	private String especieCodigo = CINCO_ESPACIOS;
	
	/** The fecha periodo. */
	// FORMATO YYYYMMDD
	private String fechaPeriodo;

	/** The hora periodo. */
	// FORMATO (HH:MM:SS)
	private String horaPeriodo;

	/** The sucursal cuenta list. */
	private List<SucursalCuentaEntity> sucursalCuentaList;

	/**
	 * Gets the tipo papel enum.
	 *
	 * @return the tipo papel enum
	 */
	public TipoPapel getTipoPapelEnum() {
		return tipoPapelEnum;
	}

	/**
	 * Sets the tipo papel enum.
	 *
	 * @param tipoPapelEnum
	 *            the new tipo papel enum
	 */
	public void setTipoPapelEnum(TipoPapel tipoPapelEnum) {
		this.tipoPapelEnum = tipoPapelEnum;
	}

	/**
	 * Gets the especie codigo.
	 *
	 * @return the especie codigo
	 */
	public String getEspecieCodigo() {
		return especieCodigo;
	}

	/**
	 * Sets the especie codigo.
	 *
	 * @param especieCodigo
	 *            the new especie codigo
	 */
	public void setEspecieCodigo(String especieCodigo) {
		this.especieCodigo = especieCodigo;
	}

	/**
	 * Gets the fecha periodo.
	 *
	 * @return the fecha periodo
	 */
	public String getFechaPeriodo() {
		return fechaPeriodo;
	}

	/**
	 * Sets the fecha periodo.
	 *
	 * @param fechaPeriodo
	 *            the new fecha periodo
	 */
	public void setFechaPeriodo(String fechaPeriodo) {
		this.fechaPeriodo = fechaPeriodo;
	}

	/**
	 * Gets the hora periodo.
	 *
	 * @return the hora periodo
	 */
	public String getHoraPeriodo() {
		return horaPeriodo;
	}

	/**
	 * Sets the hora periodo.
	 *
	 * @param horaPeriodo
	 *            the new hora periodo
	 */
	public void setHoraPeriodo(String horaPeriodo) {
		this.horaPeriodo = horaPeriodo;
	}

	/**
	 * Gets the sucursal cuenta list.
	 *
	 * @return the sucursal cuenta list
	 */
	public List<SucursalCuentaEntity> getSucursalCuentaList() {
		return sucursalCuentaList;
	}

	/**
	 * Sets the sucursal cuenta list.
	 *
	 * @param sucursalCuentaList
	 *            the new sucursal cuenta list
	 */
	public void setSucursalCuentaList(List<SucursalCuentaEntity> sucursalCuentaList) {
		this.sucursalCuentaList = sucursalCuentaList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("tipoPapelEnum", tipoPapelEnum).append("especieCodigo", especieCodigo)
				.append("fechaPeriodo", fechaPeriodo).append("horaPeriodo", horaPeriodo)
				.append("sucursalCuentaList", sucursalCuentaList).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	/*
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(tipoPapelEnum).append(especieCodigo).append(fechaPeriodo)
				.append(horaPeriodo).append(sucursalCuentaList).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*
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

		ConsultaTenenciaFCIInEntity other = (ConsultaTenenciaFCIInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(tipoPapelEnum, other.tipoPapelEnum).append(especieCodigo, other.especieCodigo)
				.append(fechaPeriodo, other.fechaPeriodo).append(horaPeriodo, other.horaPeriodo)
				.append(sucursalCuentaList, other.sucursalCuentaList).isEquals();
	}
}
