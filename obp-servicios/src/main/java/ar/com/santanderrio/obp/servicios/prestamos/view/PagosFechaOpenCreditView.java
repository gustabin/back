/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Open Credit OLYMPUS.
 *
 * @author Silvina_Luque
 */
public class PagosFechaOpenCreditView {

	/** fecha. */
	private String fecha;

	/** Cuotas pagas por fecha. */
	private List<PagoOpenCreditView> pagos = new ArrayList<PagoOpenCreditView>();

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the pagos.
	 *
	 * @return the pagos
	 */
	public List<PagoOpenCreditView> getPagos() {
		return pagos;
	}

	/**
	 * Sets the pagos.
	 *
	 * @param pagos
	 *            the new pagos
	 */
	public void setPagos(List<PagoOpenCreditView> pagos) {
		this.pagos = pagos;
	}

}
