/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class MovimientoView.
 *
 * @author Jonatan_Bocian
 */
public class MovimientoView {

	/** The last page. */
	private Boolean lastPage;

	/** The remaining. */
	private Integer remaining;

	/** The id cuenta. */
	private String idCuenta;

	/** The items movimiento. */
	private List<ItemMovimiento> itemsMovimiento;

	/** The movimientos pendientes. */
	private MovimientosPendientesDeConfirmacionView movimientosPendientes;
	

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

	/**
	 * Gets the remaining.
	 *
	 * @return the remaining
	 */
	public Integer getRemaining() {
		return remaining;
	}

	/**
	 * Setter para remaining.
	 *
	 * @param remaining
	 *            el nuevo remaining
	 */
	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}

	/**
	 * Setter para items movimiento.
	 *
	 * @param itemsMovimiento
	 *            el nuevo items movimiento
	 */
	public void setItemsMovimiento(List<ItemMovimiento> itemsMovimiento) {
		this.itemsMovimiento = itemsMovimiento;
	}

	/**
	 * Gets the id cuenta.
	 *
	 * @return the id cuenta
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * Setter para id cuenta.
	 *
	 * @param idCuenta
	 *            el nuevo id cuenta
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	/**
	 * Adds the item movimiento.
	 *
	 * @param im
	 *            the im
	 */
	public void addItemMovimiento(ItemMovimiento im) {
		if (itemsMovimiento == null) {
			itemsMovimiento = new ArrayList<ItemMovimiento>();
		}
		itemsMovimiento.add(im);
	}

	/**
	 * Gets the items movimiento.
	 *
	 * @return the itemsMovimiento
	 */
	public List<ItemMovimiento> getItemsMovimiento() {
		return itemsMovimiento;
	}

	/**
	 * Gets the movimientos pendientes.
	 *
	 * @return the movimientos pendientes
	 */
	public MovimientosPendientesDeConfirmacionView getMovimientosPendientes() {
		return movimientosPendientes;
	}

	/**
	 * Setter para movimientos pendientes.
	 *
	 * @param movimientosPendientes
	 *            el nuevo movimientos pendientes
	 */
	public void setMovimientosPendientes(MovimientosPendientesDeConfirmacionView movimientosPendientes) {
		this.movimientosPendientes = movimientosPendientes;
	}
	
}