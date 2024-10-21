package ar.com.santanderrio.obp.servicios.debinws.view;

import java.util.List;

/**
 * The Class ConfiguracionGrillaDebinWSView.
 */
public class ConfiguracionGrillaDebinWSView {

	/** The habilita action sheet debin plf. */
	private Boolean habilitaActionSheetDebinPlf = Boolean.FALSE;

	/** The lista estado debin. */
	private List<ConceptoDebinWSView> listaEstadoDebin;

	/** The lista estado recurrencia. */
	private List<EstadoRecurrenciaWSView> listaEstadoRecurrencia;

	/**
	 * Gets the habilita action sheet debin plf.
	 *
	 * @return the habilita action sheet debin plf
	 */
	public Boolean getHabilitaActionSheetDebinPlf() {
		return habilitaActionSheetDebinPlf;
	}

	/**
	 * Sets the habilita action sheet debin plf.
	 *
	 * @param habilitaActionSheetDebinPlf the new habilita action sheet debin plf
	 */
	public void setHabilitaActionSheetDebinPlf(Boolean habilitaActionSheetDebinPlf) {
		this.habilitaActionSheetDebinPlf = habilitaActionSheetDebinPlf;
	}

	/**
	 * Gets the lista estado debin.
	 *
	 * @return the lista estado debin
	 */
	public List<ConceptoDebinWSView> getListaEstadoDebin() {
		return listaEstadoDebin;
	}

	/**
	 * Gets the lista estado recurrencia.
	 *
	 * @return the lista estado recurrencia
	 */
	public List<EstadoRecurrenciaWSView> getListaEstadoRecurrencia() {
		return listaEstadoRecurrencia;
	}

	/**
	 * Sets the lista estado debin.
	 *
	 * @param listaEstadoDebin the new lista estado debin
	 */
	public void setListaEstadoDebin(List<ConceptoDebinWSView> listaEstadoDebin) {
		this.listaEstadoDebin = listaEstadoDebin;
	}

	public void setListaEstadoRecurrencia(List<EstadoRecurrenciaWSView> listaEstadoRecurrencia) {
		this.listaEstadoRecurrencia = listaEstadoRecurrencia;
	}

}
