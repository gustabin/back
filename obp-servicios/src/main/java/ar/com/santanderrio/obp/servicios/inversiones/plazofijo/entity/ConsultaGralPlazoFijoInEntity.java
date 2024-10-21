/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author juan.pablo.picate
 *
 */
public class ConsultaGralPlazoFijoInEntity {

	/** The sucursal cuenta. */
	private String sucursalCuenta;

	/** The cuenta plazo. */
	private String cuentaPlazo;

	/** The ejecutivo. */
	private String ejecutivo;

	/** The indicador estado PF. */
	private String indicadorEstadoPF;

	/** The custodia. */
	private String custodia;

	/** The tipo fecha. */
	private String tipoFecha;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The fecha contable. */
	private String fechaContable;

	/** The indicador estado 2. */
	private String indicadorEstado2;

	/** The cantidad registros A consultar. */
	private String cantidadRegistrosAConsultar;

	/** The cuenta rellamada. */
	private String cuentaRellamada;

	/** The nro secuencia rellamada. */
	private String nroSecuenciaRellamada;

	/** The nro secuencia. */
	private String nroSecuencia;

	/**
	 * Gets the nro secuencia rellamada.
	 *
	 * @return the nroSecuenciaRellamada
	 */
	public String getNroSecuenciaRellamada() {
		return nroSecuenciaRellamada;
	}

	/**
	 * Sets the nro secuencia rellamada.
	 *
	 * @param nroSecuenciaRellamada
	 *            the nroSecuenciaRellamada to set
	 */
	public void setNroSecuenciaRellamada(String nroSecuenciaRellamada) {
		this.nroSecuenciaRellamada = nroSecuenciaRellamada;
	}

	/**
	 * Gets the nro secuencia.
	 *
	 * @return the nroSecuencia
	 */
	public String getNroSecuencia() {
		return nroSecuencia;
	}

	/**
	 * Sets the nro secuencia.
	 *
	 * @param nroSecuencia
	 *            the nroSecuencia to set
	 */
	public void setNroSecuencia(String nroSecuencia) {
		this.nroSecuencia = nroSecuencia;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the cuenta plazo.
	 *
	 * @return the cuenta plazo
	 */
	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	/**
	 * Sets the cuenta plazo.
	 *
	 * @param cuentaPlazo
	 *            the new cuenta plazo
	 */
	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	/**
	 * Gets the ejecutivo.
	 *
	 * @return the ejecutivo
	 */
	public String getEjecutivo() {
		return ejecutivo;
	}

	/**
	 * Sets the ejecutivo.
	 *
	 * @param ejecutivo
	 *            the new ejecutivo
	 */
	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}

	/**
	 * Gets the indicador estado PF.
	 *
	 * @return the indicador estado PF
	 */
	public String getIndicadorEstadoPF() {
		return indicadorEstadoPF;
	}

	/**
	 * Sets the indicador estado PF.
	 *
	 * @param indicadorEstadoPF
	 *            the new indicador estado PF
	 */
	public void setIndicadorEstadoPF(String indicadorEstadoPF) {
		this.indicadorEstadoPF = indicadorEstadoPF;
	}

	/**
	 * Gets the custodia.
	 *
	 * @return the custodia
	 */
	public String getCustodia() {
		return custodia;
	}

	/**
	 * Sets the custodia.
	 *
	 * @param custodia
	 *            the new custodia
	 */
	public void setCustodia(String custodia) {
		this.custodia = custodia;
	}

	/**
	 * Gets the tipo fecha.
	 *
	 * @return the tipo fecha
	 */
	public String getTipoFecha() {
		return tipoFecha;
	}

	/**
	 * Sets the tipo fecha.
	 *
	 * @param tipoFecha
	 *            the new tipo fecha
	 */
	public void setTipoFecha(String tipoFecha) {
		this.tipoFecha = tipoFecha;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the fecha contable.
	 *
	 * @return the fecha contable
	 */
	public String getFechaContable() {
		return fechaContable;
	}

	/**
	 * Sets the fecha contable.
	 *
	 * @param fechaContable
	 *            the new fecha contable
	 */
	public void setFechaContable(String fechaContable) {
		this.fechaContable = fechaContable;
	}

	/**
	 * Gets the indicador estado 2.
	 *
	 * @return the indicador estado 2
	 */
	public String getIndicadorEstado2() {
		return indicadorEstado2;
	}

	/**
	 * Sets the indicador estado 2.
	 *
	 * @param indicadorEstado2
	 *            the new indicador estado 2
	 */
	public void setIndicadorEstado2(String indicadorEstado2) {
		this.indicadorEstado2 = indicadorEstado2;
	}

	/**
	 * Gets the cantidad registros A consultar.
	 *
	 * @return the cantidad registros A consultar
	 */
	public String getCantidadRegistrosAConsultar() {
		return cantidadRegistrosAConsultar;
	}

	/**
	 * Sets the cantidad registros A consultar.
	 *
	 * @param cantidadRegistrosAConsultar
	 *            the new cantidad registros A consultar
	 */
	public void setCantidadRegistrosAConsultar(String cantidadRegistrosAConsultar) {
		this.cantidadRegistrosAConsultar = cantidadRegistrosAConsultar;
	}

	/**
	 * Gets the cuenta rellamada.
	 *
	 * @return the cuenta rellamada
	 */
	public String getCuentaRellamada() {
		return cuentaRellamada;
	}

	/**
	 * Sets the cuenta rellamada.
	 *
	 * @param cuentaRellamada
	 *            the new cuenta rellamada
	 */
	public void setCuentaRellamada(String cuentaRellamada) {
		this.cuentaRellamada = cuentaRellamada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(sucursalCuenta).append(cuentaPlazo).append(ejecutivo)
				.append(indicadorEstadoPF).append(custodia).append(tipoFecha).append(fechaDesde).append(fechaHasta)
				.append(fechaContable).append(indicadorEstado2).append(cantidadRegistrosAConsultar)
				.append(cuentaRellamada).append(nroSecuenciaRellamada).append(nroSecuencia).toHashCode();
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

		ConsultaGralPlazoFijoInEntity other = (ConsultaGralPlazoFijoInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder().append("sucursalCuenta", other.sucursalCuenta)
				.append("cuentaPlazo", other.cuentaPlazo).append("ejecutivo", other.ejecutivo)
				.append("indicadorEstadoPF", other.indicadorEstadoPF).append("custodia", other.custodia)
				.append("tipoFecha", other.tipoFecha).append("fechaDesde", other.fechaDesde)
				.append("fechaHasta", other.fechaHasta).append("fechaContable", other.fechaContable)
				.append("indicadorEstado2", other.indicadorEstado2)
				.append("cantidadRegistrosAConsultar", other.cantidadRegistrosAConsultar)
				.append("cuentaRellamada", other.cuentaRellamada)
				.append("nroSecuenciaRellamada", other.nroSecuenciaRellamada)
				.append("nroSecuencia", other.nroSecuencia);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("sucursalCuenta", sucursalCuenta).append("cuentaPlazo", cuentaPlazo)
				.append("ejecutivo", ejecutivo).append("indicadorEstadoPF", indicadorEstadoPF)
				.append("custodia", custodia).append("tipoFecha", tipoFecha).append("fechaDesde", fechaDesde)
				.append("fechaHasta", fechaHasta).append("fechaContable", fechaContable)
				.append("indicadorEstado2", indicadorEstado2)
				.append("cantidadRegistrosAConsultar", cantidadRegistrosAConsultar)
				.append("nroSecuenciaRellamada", nroSecuenciaRellamada).append("nroSecuencia", nroSecuencia).toString();
	}
}
