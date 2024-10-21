/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class SimulacionClienteVendeEntity.
 *
 * @author sabrina.cis
 */
public class SimulacionClienteVendeEntity {

	/** The importe cotizacion. */
	private String importeCotizacion;

	/** The importe credito. */
	private String importeCredito;

	/** The importe debito. */
	private String importeDebito;

	/** The spred. */
	private String spred;

	/** The cod error. */
	private Integer codError;

	/** The tiene error. */
	private Boolean tieneError = Boolean.FALSE;
	
	/** The mensaje error. */	
	private String mensajeError;

	/**
	 * Instantiates a new simulacion cliente vende Entity.
	 */
	public SimulacionClienteVendeEntity() {
		super();
	}

	/**
	 * Instantiates a new simulacion cliente vende Entity.
	 *
	 * @param codError
	 *            the cod error
	 */
	public SimulacionClienteVendeEntity(Integer codError) {
		super();
		this.setCodError(codError);
		this.setTieneError(true);
	}

	public SimulacionClienteVendeEntity(int errorCode, String mensajeError) {
		super();
		this.setCodError(errorCode);
		this.setMensajeError(mensajeError);
		this.setTieneError(true);
	}

	/**
	 * Gets the importe cotizacion.
	 *
	 * @return the importeCotizacion
	 */
	public String getImporteCotizacion() {
		return importeCotizacion;
	}

	/**
	 * Sets the importe cotizacion.
	 *
	 * @param importeCotizacion
	 *            the importeCotizacion to set
	 */
	public void setImporteCotizacion(String importeCotizacion) {
		this.importeCotizacion = importeCotizacion;
	}

	/**
	 * Gets the importe credito.
	 *
	 * @return the importeCredito
	 */
	public String getImporteCredito() {
		return importeCredito;
	}

	/**
	 * Sets the importe credito.
	 *
	 * @param importeCredito
	 *            the importeCredito to set
	 */
	public void setImporteCredito(String importeCredito) {
		this.importeCredito = importeCredito;
	}

	/**
	 * Gets the importe debito.
	 *
	 * @return the importeDebito
	 */
	public String getImporteDebito() {
		return importeDebito;
	}

	/**
	 * Sets the importe debito.
	 *
	 * @param importeDebito
	 *            the importeDebito to set
	 */
	public void setImporteDebito(String importeDebito) {
		this.importeDebito = importeDebito;
	}

	/**
	 * Gets the spred.
	 *
	 * @return the spred
	 */
	public String getSpred() {
		return spred;
	}

	/**
	 * Sets the spred.
	 *
	 * @param spred
	 *            the spred to set
	 */
	public void setSpred(String spred) {
		this.spred = spred;
	}

	/**
	 * Gets the cod error.
	 *
	 * @return the cod error
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * Sets the cod error.
	 *
	 * @param codError
	 *            the new cod error
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	/**
	 * Gets the tiene error.
	 *
	 * @return the tiene error
	 */
	public Boolean getTieneError() {
		return tieneError;
	}

	/**
	 * Sets the tiene error.
	 *
	 * @param tieneError
	 *            the new tiene error
	 */
	public void setTieneError(Boolean tieneError) {
		this.tieneError = tieneError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(importeCotizacion);
		hcb.append(importeCredito);
		hcb.append(importeDebito);
		hcb.append(spred);
		hcb.append(codError);
		hcb.append(tieneError);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
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
		SimulacionClienteVendeEntity other = (SimulacionClienteVendeEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(importeCotizacion, other.getImporteCotizacion());
		eb.append(importeCredito, other.getImporteCredito());
		eb.append(importeDebito, other.getImporteDebito());
		eb.append(spred, other.getSpred());
		eb.append(codError, other.getCodError());
		eb.append(tieneError, getTieneError());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("importeCotizacion", importeCotizacion)
				.append("importeCredito", importeCredito).append("importeDebito", importeDebito).append("spred", spred)
				.append("codError", codError).append("tieneError", tieneError).toString();
	}

}