/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class PedidoTransferenciaAgendada.
 */
public class PedidoTransferenciaAgendada extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The configuracion transferencia agendada. */
	private ConfiguracionTransferenciaAgendada configuracionTransferenciaAgendada;

	/** The datos transferencia agendada. */
	private DatosTransferenciaAgendadaEntity datosTransferenciaAgendada;

	/**
	 * Gets the configuracion transferencia agendada.
	 *
	 * @return the configuracion transferencia agendada
	 */
	public ConfiguracionTransferenciaAgendada getConfiguracionTransferenciaAgendada() {
		return configuracionTransferenciaAgendada;
	}

	/**
	 * Sets the configuracion transferencia agendada.
	 *
	 * @param configuracionTransferenciaAgendada
	 *            the new configuracion transferencia agendada
	 */
	public void setConfiguracionTransferenciaAgendada(
			ConfiguracionTransferenciaAgendada configuracionTransferenciaAgendada) {
		this.configuracionTransferenciaAgendada = configuracionTransferenciaAgendada;
	}

	/**
	 * Gets the datos transferencia agendada.
	 *
	 * @return the datos transferencia agendada
	 */
	public DatosTransferenciaAgendadaEntity getDatosTransferenciaAgendada() {
		return datosTransferenciaAgendada;
	}

	/**
	 * Sets the datos transferencia agendada.
	 *
	 * @param datosTransferenciaAgendada
	 *            the new datos transferencia agendada
	 */
	public void setDatosTransferenciaAgendada(DatosTransferenciaAgendadaEntity datosTransferenciaAgendada) {
		this.datosTransferenciaAgendada = datosTransferenciaAgendada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((configuracionTransferenciaAgendada == null) ? 0 : configuracionTransferenciaAgendada.hashCode());
		result = prime * result + ((datosTransferenciaAgendada == null) ? 0 : datosTransferenciaAgendada.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PedidoTransferenciaAgendada other = (PedidoTransferenciaAgendada) obj;
		if (configuracionTransferenciaAgendada == null) {
			if (other.configuracionTransferenciaAgendada != null) {
				return false;
			}
		} else if (!configuracionTransferenciaAgendada.equals(other.configuracionTransferenciaAgendada)) {
			return false;
		}
		if (datosTransferenciaAgendada == null) {
			if (other.datosTransferenciaAgendada != null) {
				return false;
			}
		} else if (!datosTransferenciaAgendada.equals(other.datosTransferenciaAgendada)) {
			return false;
		}
		return true;
	}

}
