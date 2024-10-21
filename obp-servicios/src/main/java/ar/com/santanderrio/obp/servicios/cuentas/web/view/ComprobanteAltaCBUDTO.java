/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ar.com.santanderrio.obp.servicios.comun.comprobante.entities.BasicComprobante;

/**
 * The Class ComprobanteAltaCBUDTO.
 */
public class ComprobanteAltaCBUDTO extends BasicComprobante {

	/** The reasigna. */
	private String reasigna;

	/**
	 * Instantiates a new comprobante alta CBUDTO.
	 *
	 * @param mensajeTabla
	 *            the mensaje tabla
	 * @param alias
	 *            the alias
	 * @param numeroCuenta
	 *            the numero cuenta
	 */
	public ComprobanteAltaCBUDTO(String mensajeTabla, String alias, String numeroCuenta) {
		setFecha(new Date());
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new
																// calendar
																// instance
		calendar.setTime(getFecha()); // assigns calendar to given date
		String anio = String.valueOf(calendar.get(Calendar.YEAR)).substring(2);
		String mes = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.MONTH) + 1), 2, "0");
		String dia = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2, "0");
		String horas = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2, "0");
		String minutos = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.MINUTE)), 2, "0");
		String segundos = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.SECOND)), 2, "0");
		setHora(horas + ":" + minutos);
		setNroComprobante(anio + mes + dia + horas + minutos + segundos);
		setMensajeEfectivizacion(StringUtils.replace(mensajeTabla, "{0}", alias));
		setMensajeEfectivizacion(StringUtils.replace(getMensajeEfectivizacion(), "{1}", numeroCuenta));

	}

	/**
	 * Gets the reasigna.
	 *
	 * @return the reasigna
	 */
	public String getReasigna() {
		return reasigna;
	}

	/**
	 * Sets the reasigna.
	 *
	 * @param reasigna
	 *            the new reasigna
	 */
	public void setReasigna(String reasigna) {
		this.reasigna = reasigna;
	}

}
