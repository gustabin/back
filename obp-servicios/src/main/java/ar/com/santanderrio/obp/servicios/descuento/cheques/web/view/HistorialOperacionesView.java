/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionPrecargadaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionesPrecargadasDTO;

/**
 * The Class HistorialOperacionesView.
 */
public class HistorialOperacionesView extends OperacionesGrillaView{
	
	/** The operaciones. */
	private List<OperacionHistoricoView> operaciones = new ArrayList<OperacionHistoricoView>();
	
	/** The estado filtro. */
	private String estadoFiltro;

	/**
	 * Instantiates a new historial operaciones view.
	 */
	public HistorialOperacionesView(){
		super();
	}
	
	/**
	 * Instantiates a new historial operaciones view.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param isMobile
	 *            the is mobile
	 */
	public HistorialOperacionesView(OperacionesPrecargadasDTO respuesta, Boolean isMobile) {
		hayRellamada = respuesta.getTieneMasOperaciones();
		for (OperacionPrecargadaDTO operacion : respuesta.getDto()) {
			operaciones.add(new OperacionHistoricoView(operacion, isMobile));
		}
	}

	/**
	 * Gets the operaciones.
	 *
	 * @return the operaciones
	 */
	public List<OperacionHistoricoView> getOperaciones() {
		return operaciones;
	}

	/**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the new operaciones
	 */
	public void setOperaciones(List<OperacionHistoricoView> operaciones) {
		this.operaciones = operaciones;
	}

	/**
	 * Gets the estado filtro.
	 *
	 * @return the estado filtro
	 */
	public String getEstadoFiltro() {
		return estadoFiltro;
	}

	/**
	 * Sets the estado filtro.
	 *
	 * @param estadoFiltro
	 *            the new estado filtro
	 */
	public void setEstadoFiltro(String estadoFiltro) {
		this.estadoFiltro = estadoFiltro;
	}
	
	

}
