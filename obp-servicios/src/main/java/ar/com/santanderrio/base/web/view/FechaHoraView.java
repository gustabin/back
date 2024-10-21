/*
 * 
 */
package ar.com.santanderrio.base.web.view;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class FechaHoraView.
 */
public class FechaHoraView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fecha. */
	private String fecha;

	/** The hora. */
	private String hora;

	/**
	 * Instantiates a new fecha hora view.
	 *
	 * @param date
	 *            the date
	 */
	public FechaHoraView(Date date) {
		this.fecha = new SimpleDateFormat("dd/MM/yyyy").format(date);
		this.hora = new SimpleDateFormat("hh:mm").format(date);
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return this.fecha;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return this.hora;
	}
}