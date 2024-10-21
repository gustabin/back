package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.PeriodoActualOutEntity;

public class PeriodoActualDTO {

	/** The total chances periodo. */
	private String totalChancesPeriodo;
	
	/** The fecha corte. */
	private String fechaCorte;
	
	public PeriodoActualDTO() {}
	
	public PeriodoActualDTO(PeriodoActualOutEntity periodoActual) {
		this.totalChancesPeriodo = periodoActual.getTotalChancesPeriodo();
		this.fechaCorte = periodoActual.getFechaCorte();
	}
	
	/**
     * Gets the total chances periodo.
     *
	 * @return the total chances periodo
	 */
	public String getTotalChancesPeriodo() {
		return totalChancesPeriodo;
	}

	/**
     * Sets the total chances periodo.
     *
	 * @param totalChancesPeriodo
	 * 		 the total chances periodo to set
	 */
	public void setTotalChancesPeriodo(String totalChancesPeriodo) {
		this.totalChancesPeriodo = totalChancesPeriodo;
	}
	
	/**
     * Gets the fecha corte.
     *
	 * @return the fecha corte
	 */
	public String getFechaCorte() {
		return fechaCorte;
	}

	/**
     * Sets the fecha corte.
     *
	 * @param fechaCorte
	 * 		 the fecha corte to set
	 */
	public void setFechaCorte(String fechaCorte) {
		this.fechaCorte = fechaCorte;
	}
}
