/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view;

import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class AumentoTransferenciaView.
 */
public class AumentoTransferenciaView extends TransferenciaView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2113682749353085716L;

	/** The importe min pesos. */
	private String importeMinPesos;

	/** The importe min dolares. */
	private String importeMinDolares;

	/** The mensaje ayuda aumento limite. */
	private String mensajeAyudaAumentoLimite;

	/**
	 * Gets the mensaje ayuda aumento limite.
	 *
	 * @return the mensaje ayuda aumento limite
	 */
	public String getMensajeAyudaAumentoLimite() {
		return mensajeAyudaAumentoLimite;
	}

	/**
	 * Sets the mensaje ayuda aumento limite.
	 *
	 * @param mensajeAyudaAumentoLimite
	 *            the new mensaje ayuda aumento limite
	 */
	public void setMensajeAyudaAumentoLimite(String mensajeAyudaAumentoLimite) {
		this.mensajeAyudaAumentoLimite = mensajeAyudaAumentoLimite;
	}

	/**
	 * Gets the importe min pesos.
	 *
	 * @return the importe min pesos
	 */
	public String getImporteMinPesos() {
		return importeMinPesos;
	}

	/**
	 * Sets the importe min pesos.
	 *
	 * @param importeMinPesos
	 *            the new importe min pesos
	 */
	public void setImporteMinPesos(String importeMinPesos) {
		this.importeMinPesos = importeMinPesos;
	}

	/**
	 * Gets the importe min dolares.
	 *
	 * @return the importe min dolares
	 */
	public String getImporteMinDolares() {
		return importeMinDolares;
	}

	/**
	 * Sets the importe min dolares.
	 *
	 * @param importeMinDolares
	 *            the new importe min dolares
	 */
	public void setImporteMinDolares(String importeMinDolares) {
		this.importeMinDolares = importeMinDolares;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((importeMinDolares == null) ? 0 : importeMinDolares.hashCode());
		result = prime * result + ((importeMinPesos == null) ? 0 : importeMinPesos.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.view.
	 * TransferenciaView#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AumentoTransferenciaView other = (AumentoTransferenciaView) obj;
		if (importeMinDolares == null) {
			if (other.importeMinDolares != null)
				return false;
		} else if (!importeMinDolares.equals(other.importeMinDolares))
			return false;
		if (importeMinPesos == null) {
			if (other.importeMinPesos != null)
				return false;
		} else if (!importeMinPesos.equals(other.importeMinPesos))
			return false;
		return true;
	}

}
