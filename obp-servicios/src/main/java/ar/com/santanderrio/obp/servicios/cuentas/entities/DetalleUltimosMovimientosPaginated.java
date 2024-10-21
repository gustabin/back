/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.util.List;

/**
 * The Class DetalleUltimosMovimientosPaginated.
 */
public class DetalleUltimosMovimientosPaginated extends DetalleUltimosMovimientos {

	/** The total results. */
	private Integer totalResults;

	/** The remainig. */
	private Integer remainig;

	/** The last page. */
	private Boolean lastPage;

	/**
	 * Instantiates a new detalle ultimos movimientos paginated.
	 *
	 * @param detalleMovimientoList
	 *            the detalle movimiento list
	 */
	public DetalleUltimosMovimientosPaginated(List<DetalleMovimientoEntity> detalleMovimientoList) {
		super(detalleMovimientoList);
	}

	/**
	 * Gets the total results.
	 *
	 * @return the total results
	 */
	public Integer getTotalResults() {
		return totalResults;
	}

	/**
	 * Setter para total results.
	 *
	 * @param totalResults
	 *            el nuevo total results
	 */
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	/**
	 * Gets the remainig.
	 *
	 * @return the remainig
	 */
	public Integer getRemainig() {
		return remainig;
	}

	/**
	 * Setter para remainig.
	 *
	 * @param remainig
	 *            el nuevo remainig
	 */
	public void setRemainig(Integer remainig) {
		this.remainig = remainig;
	}

	/**
	 * Gets the last page.
	 *
	 * @return the last page
	 */
	public Boolean getLastPage() {
		return lastPage;
	}

	/**
	 * Setter para last page.
	 *
	 * @param lastPage
	 *            el nuevo last page
	 */
	public void setLastPage(Boolean lastPage) {
		this.lastPage = lastPage;
	}

}
