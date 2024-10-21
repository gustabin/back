/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class DatosTransferenciaAgendadaRioRio.
 */
public class DatosTransferenciaAgendadaRioRioEntity extends DatosTransferenciaAgendadaEntity {

	/** The importe debito. */
	private String importeDebito;

	/** The tipo cuenta credito. */
	private String tipoCuentaCredito;

	/** The cotizacion transferencia. */
	private String cotizacionTransferencia;

	/** The importe credito. */
	private String importeCredito;

	/** The nro comprobante. */
	private String nroComprobante;

	/** The indicador limite max. */
	private String indicadorLimiteMax;

	/** The indicador afectar disponible. */
	private String indicadorAfectarDisponible;

	/** The ind trf dif. */
	private String indTrfDif;

	/** The cuenta propia. */
	private String cuentaPropia;

	/** The email. */
	private String email;

	/**
	 * Gets the importe debito.
	 *
	 * @return the importe debito
	 */
	public String getImporteDebito() {
		return importeDebito;
	}

	/**
	 * Sets the importe debito.
	 *
	 * @param importeDebito
	 *            the new importe debito
	 */
	public void setImporteDebito(String importeDebito) {
		this.importeDebito = importeDebito;
	}

	/**
	 * Gets the tipo cuenta credito.
	 *
	 * @return the tipo cuenta credito
	 */
	public String getTipoCuentaCredito() {
		return tipoCuentaCredito;
	}

	/**
	 * Sets the tipo cuenta credito.
	 *
	 * @param tipoCuentaCredito
	 *            the new tipo cuenta credito
	 */
	public void setTipoCuentaCredito(String tipoCuentaCredito) {
		this.tipoCuentaCredito = tipoCuentaCredito;
	}

	/**
	 * Gets the cotizacion transferencia.
	 *
	 * @return the cotizacion transferencia
	 */
	public String getCotizacionTransferencia() {
		return cotizacionTransferencia;
	}

	/**
	 * Sets the cotizacion transferencia.
	 *
	 * @param cotizacionTransferencia
	 *            the new cotizacion transferencia
	 */
	public void setCotizacionTransferencia(String cotizacionTransferencia) {
		this.cotizacionTransferencia = cotizacionTransferencia;
	}

	/**
	 * Gets the importe credito.
	 *
	 * @return the importe credito
	 */
	public String getImporteCredito() {
		return importeCredito;
	}

	/**
	 * Sets the importe credito.
	 *
	 * @param importeCredito
	 *            the new importe credito
	 */
	public void setImporteCredito(String importeCredito) {
		this.importeCredito = importeCredito;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the indicador limite max.
	 *
	 * @return the indicador limite max
	 */
	public String getIndicadorLimiteMax() {
		return indicadorLimiteMax;
	}

	/**
	 * Sets the indicador limite max.
	 *
	 * @param indicadorLimiteMax
	 *            the new indicador limite max
	 */
	public void setIndicadorLimiteMax(String indicadorLimiteMax) {
		this.indicadorLimiteMax = indicadorLimiteMax;
	}

	/**
	 * Gets the indicador afectar disponible.
	 *
	 * @return the indicador afectar disponible
	 */
	public String getIndicadorAfectarDisponible() {
		return indicadorAfectarDisponible;
	}

	/**
	 * Sets the indicador afectar disponible.
	 *
	 * @param indicadorAfectarDisponible
	 *            the new indicador afectar disponible
	 */
	public void setIndicadorAfectarDisponible(String indicadorAfectarDisponible) {
		this.indicadorAfectarDisponible = indicadorAfectarDisponible;
	}

	/**
	 * Gets the ind trf dif.
	 *
	 * @return the ind trf dif
	 */
	public String getIndTrfDif() {
		return indTrfDif;
	}

	/**
	 * Sets the ind trf dif.
	 *
	 * @param indTrfDif
	 *            the new ind trf dif
	 */
	public void setIndTrfDif(String indTrfDif) {
		this.indTrfDif = indTrfDif;
	}

	/**
	 * Gets the cuenta propia.
	 *
	 * @return the cuenta propia
	 */
	public String getCuentaPropia() {
		return cuentaPropia;
	}

	/**
	 * Sets the cuenta propia.
	 *
	 * @param cuentaPropia
	 *            the new cuenta propia
	 */
	public void setCuentaPropia(String cuentaPropia) {
		this.cuentaPropia = cuentaPropia;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.entities.
	 * DatosTransferenciaAgendada#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cotizacionTransferencia);
		hcb.append(cuentaPropia);
		hcb.append(email);
		hcb.append(importeCredito);
		hcb.append(importeDebito);
		hcb.append(indTrfDif);
		hcb.append(indicadorAfectarDisponible);
		hcb.append(indicadorLimiteMax);
		hcb.append(nroComprobante);
		hcb.append(tipoCuentaCredito);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.entities.
	 * DatosTransferenciaAgendada#equals(java.lang.Object)
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
		DatosTransferenciaAgendadaRioRioEntity other = (DatosTransferenciaAgendadaRioRioEntity) obj;

		EqualsBuilder eb = new EqualsBuilder().append(cotizacionTransferencia, other.cotizacionTransferencia)
				.append(cuentaPropia, other.cuentaPropia).append(email, other.email)
				.append(importeCredito, other.importeCredito).append(importeDebito, other.importeDebito)
				.append(indTrfDif, other.indTrfDif).append(indicadorAfectarDisponible, other.indicadorAfectarDisponible)
				.append(indicadorLimiteMax, other.indicadorLimiteMax).append(nroComprobante, other.nroComprobante)
				.append(tipoCuentaCredito, other.tipoCuentaCredito);
		return eb.isEquals();

	}

}
