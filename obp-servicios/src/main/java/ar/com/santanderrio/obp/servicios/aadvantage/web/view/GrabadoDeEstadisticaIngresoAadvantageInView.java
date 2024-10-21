package ar.com.santanderrio.obp.servicios.aadvantage.web.view;

/**
 * The class GrabadoDeEstadisticaIngresoAadvantageInView
 * 
 * @author ITResources
 *
 */
public class GrabadoDeEstadisticaIngresoAadvantageInView {

	/** The ingreso desde home controller. */
	private Boolean ingresoDesdeHomeController = Boolean.FALSE;

	
	/**
	 * 
	 */
	public GrabadoDeEstadisticaIngresoAadvantageInView() {
		super();
	}

	/**
	 * @param ingresoDesdeHomeController
	 */
	public GrabadoDeEstadisticaIngresoAadvantageInView(Boolean ingresoDesdeHomeController) {
		super();
		this.ingresoDesdeHomeController = ingresoDesdeHomeController;
	}

	/**
	 * @return the ingresoDesdeHomeController
	 */
	public Boolean getIngresoDesdeHomeController() {
		return ingresoDesdeHomeController;
	}

	/**
	 * @param ingresoDesdeHomeController the ingresoDesdeHomeController to set
	 */
	public void setIngresoDesdeHomeController(Boolean ingresoDesdeHomeController) {
		this.ingresoDesdeHomeController = ingresoDesdeHomeController;
	}
	
	
	
}
