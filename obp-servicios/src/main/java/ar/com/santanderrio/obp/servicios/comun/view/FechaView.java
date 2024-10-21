/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.view;

import ar.com.santanderrio.base.web.view.View;

/**
 * Clase utilizada para representar una fecha con los parametros del dia
 * separados.
 */
public class FechaView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7614249186932992165L;

	/** The day. */
	private Integer day;

	/** The month. */
	private Integer month;

	/** The year. */
	private Integer year;

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day
	 *            the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * Sets the month.
	 *
	 * @param month
	 *            the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year
	 *            the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

}
