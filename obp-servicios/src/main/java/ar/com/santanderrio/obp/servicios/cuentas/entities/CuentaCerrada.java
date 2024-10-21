/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class CuentaCerrada.
 */
public class CuentaCerrada extends AbstractCuenta {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The subproducto altair. */
	private String subproductoAltair = "";

	/** The calidad de participacion. */
	private String calidadDeParticipacion = "";

	/** The orden de participacion. */
	private String ordenDeParticipacion = "";

	/** The fecha baja contrato. */
	private String fechaBajaContrato = "";

	/** The estado relacion. */
	private String estadoRelacion = "";

	/** The responsabilidad en intervencion. */
	private String responsabilidadEnIntervencion = "";

	/** The motivo baja. */
	private String motivoBaja = "";

	/** The timestamp. */
	private String timestamp = "";

	/** The fecha alta contrato. */
	private String fechaAltaContrato = "";
	
	/** The nro sucursal continuadora. */
	private String nroSucursalContinuadora = "";
	
	/** The nro producto continuador. */
	private String nroCuentaProductoContinuador = "";
	
	/**
	 * Gets the subproducto altair.
	 *
	 * @return the subproductoAltair
	 */
	public String getSubproductoAltair() {
		return subproductoAltair;
	}

	/**
	 * Sets the subproducto altair.
	 *
	 * @param subproductoAltair
	 *            the subproductoAltair to set
	 */
	public void setSubproductoAltair(String subproductoAltair) {
		this.subproductoAltair = subproductoAltair;
	}

	/**
	 * Gets the calidad de participacion.
	 *
	 * @return the calidadDeParticipacion
	 */
	public String getCalidadDeParticipacion() {
		return calidadDeParticipacion;
	}

	/**
	 * Sets the calidad de participacion.
	 *
	 * @param calidadDeParticipacion
	 *            the calidadDeParticipacion to set
	 */
	public void setCalidadDeParticipacion(String calidadDeParticipacion) {
		this.calidadDeParticipacion = calidadDeParticipacion;
	}

	/**
	 * Gets the orden de participacion.
	 *
	 * @return the ordenDeParticipacion
	 */
	public String getOrdenDeParticipacion() {
		return ordenDeParticipacion;
	}

	/**
	 * Sets the orden de participacion.
	 *
	 * @param ordenDeParticipacion
	 *            the ordenDeParticipacion to set
	 */
	public void setOrdenDeParticipacion(String ordenDeParticipacion) {
		this.ordenDeParticipacion = ordenDeParticipacion;
	}

	/**
	 * Gets the fecha baja contrato.
	 *
	 * @return the fechaBajaContrato
	 */
	public String getFechaBajaContrato() {
		return fechaBajaContrato;
	}

	/**
	 * Sets the fecha baja contrato.
	 *
	 * @param fechaBajaContrato
	 *            the fechaBajaContrato to set
	 */
	public void setFechaBajaContrato(String fechaBajaContrato) {
		this.fechaBajaContrato = fechaBajaContrato;
	}

	/**
	 * Gets the estado relacion.
	 *
	 * @return the estadoRelacion
	 */
	public String getEstadoRelacion() {
		return estadoRelacion;
	}

	/**
	 * Sets the estado relacion.
	 *
	 * @param estadoRelacion
	 *            the estadoRelacion to set
	 */
	public void setEstadoRelacion(String estadoRelacion) {
		this.estadoRelacion = estadoRelacion;
	}

	/**
	 * Gets the responsabilidad en intervencion.
	 *
	 * @return the responsabilidadEnIntervencion
	 */
	public String getResponsabilidadEnIntervencion() {
		return responsabilidadEnIntervencion;
	}

	/**
	 * Sets the responsabilidad en intervencion.
	 *
	 * @param responsabilidadEnIntervencion
	 *            the responsabilidadEnIntervencion to set
	 */
	public void setResponsabilidadEnIntervencion(String responsabilidadEnIntervencion) {
		this.responsabilidadEnIntervencion = responsabilidadEnIntervencion;
	}

	/**
	 * Gets the motivo baja.
	 *
	 * @return the motivoBaja
	 */
	public String getMotivoBaja() {
		return motivoBaja;
	}

	/**
	 * Sets the motivo baja.
	 *
	 * @param motivoBaja
	 *            the motivoBaja to set
	 */
	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the fecha alta contrato.
	 *
	 * @return the fechaAltaContrato
	 */
	public String getFechaAltaContrato() {
		return fechaAltaContrato;
	}

	/**
	 * Sets the fecha alta contrato.
	 *
	 * @param fechaAltaContrato
	 *            the fechaAltaContrato to set
	 */
	public void setFechaAltaContrato(String fechaAltaContrato) {
		this.fechaAltaContrato = fechaAltaContrato;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta#
	 * isCuentaCerrada()
	 */
	@Override
	public Boolean isCuentaCerrada() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta#isCuentaUnica()
	 */
	@Override
	public boolean isCuentaUnica() {
		return "02".equals(getTipoCuenta());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("subproductoAltair", subproductoAltair)
				.append("calidadDeParticipacion", calidadDeParticipacion)
				.append("ordenDeParticipacion", ordenDeParticipacion).append("fechaBajaContrato", fechaBajaContrato)
				.append("estadoRelacion", estadoRelacion)
				.append("responsabilidadEnIntervencion", responsabilidadEnIntervencion).append("motivoBaja", motivoBaja)
				.append("timestamp", timestamp).append("fechaAltaContrato", fechaAltaContrato).toString();
	}

	/**
	 * Gets the nro sucursal continuadora.
	 *
	 * @return the nro sucursal continuadora
	 */
	public String getNroSucursalContinuadora() {
		return nroSucursalContinuadora;
	}

	/**
	 * Sets the nro sucursal continuadora.
	 *
	 * @param nroSucursalContinuadora
	 *            the new nro sucursal continuadora
	 */
	public void setNroSucursalContinuadora(String nroSucursalContinuadora) {
		this.nroSucursalContinuadora = nroSucursalContinuadora;
	}

	/**
	 * Gets the nro cuenta producto continuador.
	 *
	 * @return the nro cuenta producto continuador
	 */
	public String getNroCuentaProductoContinuador() {
		return nroCuentaProductoContinuador;
	}

	/**
	 * Sets the nro cuenta producto continuador.
	 *
	 * @param nroCuentaProductoContinuador
	 *            the new nro cuenta producto continuador
	 */
	public void setNroCuentaProductoContinuador(String nroCuentaProductoContinuador) {
		this.nroCuentaProductoContinuador = nroCuentaProductoContinuador;
	}

}
