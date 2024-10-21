/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;

/**
 * The Class EmpresaRecargaCelularView.
 */
public class EmpresaRecargaCelularView {

	/** The empresas. */
	private List<RecargaCelularView> empresas;

	/** The mensaje error. */
	private String mensajeError;
	
	private List<CelularView> numerosAgendados;
	
	/**
	 * Gets the empresas.
	 *
	 * @return the empresas
	 */
	public List<RecargaCelularView> getEmpresas() {
		return empresas;
	}

	/**
	 * Sets the empresas.
	 *
	 * @param empresas
	 *            the new empresas
	 */
	public void setEmpresas(List<RecargaCelularView> empresas) {
		this.empresas = empresas;
	}

	/**
	 * Gets the mensaje error.
	 *
	 * @return the mensaje error
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Sets the mensaje error.
	 *
	 * @param mensajeError
	 *            the new mensaje error
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public List<CelularView> getNumerosAgendados() {
		return numerosAgendados;
	}

	public void setNumerosAgendados(List<CelularView> numerosAgendados) {
		this.numerosAgendados = numerosAgendados;
	}

	
}
