/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ReporteMultipleView.
 */
public class ReporteMultipleView {

	/** The lista reporte. */
	private List<ReporteView> listaReporte;

	/** The lista vistos. */
	private List<Integer> listaVistos;

	/** The lista con error. */
	private List<Integer> listaConError;

	/**
	 * Gets the lista reporte.
	 *
	 * @return the lista reporte
	 */
	public List<ReporteView> getListaReporte() {
		return listaReporte;
	}

	/**
	 * Sets the lista reporte.
	 *
	 * @param listaReporte
	 *            the new lista reporte
	 */
	public void setListaReporte(List<ReporteView> listaReporte) {
		this.listaReporte = listaReporte;
	}

	/**
	 * Gets the lista vistos.
	 *
	 * @return the lista vistos
	 */
	public List<Integer> getListaVistos() {
		return listaVistos;
	}

	/**
	 * Sets the lista vistos.
	 *
	 * @param listaVistos
	 *            the new lista vistos
	 */
	public void setListaVistos(List<Integer> listaVistos) {
		this.listaVistos = listaVistos;
	}

	/**
	 * Gets the lista con error.
	 *
	 * @return the lista con error
	 */
	public List<Integer> getListaConError() {
		return listaConError;
	}

	/**
	 * Sets the lista con error.
	 *
	 * @param listaConError
	 *            the new lista con error
	 */
	public void setListaConError(List<Integer> listaConError) {
		this.listaConError = listaConError;
	}

	/**
	 * Instantiates a new reporte multiple view.
	 */
	public ReporteMultipleView() {
		listaVistos = new ArrayList<Integer>();
		listaConError = new ArrayList<Integer>();
		listaReporte = new ArrayList<ReporteView>();
	}

}
