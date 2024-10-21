/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

/**
 * The Class ResponseDashboard.
 */
public class ResponseDashboard {
	
	/** The filtro cartera view. */
	private FiltroCarteraView filtroCarteraView = new FiltroCarteraView();
	
	/** The filtros por fecha. */
	private FiltroPorFechaView filtrosPorFecha = new FiltroPorFechaView();
	
	/** The rentabilidad periodo. */
	private RentabilidadPeriodoView rentabilidadPeriodo = new RentabilidadPeriodoView();
	
	/** The distribucion rentabilidad view. */
	private DistribucionRentabilidadView distribucionRentabilidadView = new DistribucionRentabilidadView();
	
	/** The legales. */
	private String legales;
	
	/** The fecha rentabilidad. */
	private String fechaRentabilidad;
	

	/**
	 * Gets the filtro cartera view.
	 *
	 * @return the filtro cartera view
	 */
	public FiltroCarteraView getFiltroCarteraView() {
		return filtroCarteraView;
	}

	/**
	 * Sets the filtro cartera view.
	 *
	 * @param filtroCarteraView
	 *            the new filtro cartera view
	 */
	public void setFiltroCarteraView(FiltroCarteraView filtroCarteraView) {
		this.filtroCarteraView = filtroCarteraView;
	}

	/**
	 * Gets the filtros por fecha.
	 *
	 * @return the filtros por fecha
	 */
	public FiltroPorFechaView getFiltrosPorFecha() {
		return filtrosPorFecha;
	}

	/**
	 * Sets the filtros por fecha.
	 *
	 * @param filtrosPorFecha
	 *            the new filtros por fecha
	 */
	public void setFiltrosPorFecha(FiltroPorFechaView filtrosPorFecha) {
		this.filtrosPorFecha = filtrosPorFecha;
	}

	/**
	 * Gets the rentabilidad periodo.
	 *
	 * @return the rentabilidad periodo
	 */
	public RentabilidadPeriodoView getRentabilidadPeriodo() {
		return rentabilidadPeriodo;
	}

	/**
	 * Sets the rentabilidad periodo.
	 *
	 * @param rentabilidadPeriodo
	 *            the new rentabilidad periodo
	 */
	public void setRentabilidadPeriodo(RentabilidadPeriodoView rentabilidadPeriodo) {
		this.rentabilidadPeriodo = rentabilidadPeriodo;
	}

	/**
	 * Gets the distribucion rentabilidad view.
	 *
	 * @return the distribucion rentabilidad view
	 */
	public DistribucionRentabilidadView getDistribucionRentabilidadView() {
		return distribucionRentabilidadView;
	}

	/**
	 * Sets the distribucion rentabilidad view.
	 *
	 * @param distribucionRentabilidadView
	 *            the new distribucion rentabilidad view
	 */
	public void setDistribucionRentabilidadView(DistribucionRentabilidadView distribucionRentabilidadView) {
		this.distribucionRentabilidadView = distribucionRentabilidadView;
	}

	/**
	 * Gets the fecha rentabilidad.
	 *
	 * @return the fecha rentabilidad
	 */
	public String getFechaRentabilidad() {
		return fechaRentabilidad;
	}

	/**
	 * Sets the fecha rentabilidad.
	 *
	 * @param fechaRentabilidad
	 *            the new fecha rentabilidad
	 */
	public void setFechaRentabilidad(String fechaRentabilidad) {
		this.fechaRentabilidad = fechaRentabilidad;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}
	
}
