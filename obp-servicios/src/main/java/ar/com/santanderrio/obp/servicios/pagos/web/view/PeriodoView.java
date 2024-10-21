/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class PeriodoView.
 *
 * @author B039636
 */
public class PeriodoView {

	/** The dia. */
	private int diaInt = 1;

	/** The mes. */
	private int mesInt;

	/** The anio. */
	private int anioInt;
	
	/** The dia. */
	private String dia;
	
	/** The mes. */
	private String mes;
	
	/** The anio. */
	private String anio;

	/** The cantidad dias del mes. */
	private int cantidadDias;

	/** The mes nombre. */
	private String mesNombre;

	/** The cantidad pagos. */
	private int cantidadPagos = 0;

	/** The total pesos. */
	private String totalPesos;

	/** The total dolares. */
	private String totalDolares;

	/** The total pesos D. */
	private BigDecimal totalPesosD = BigDecimal.ZERO;

	/** The total dolares D. */
	private BigDecimal totalDolaresD = BigDecimal.ZERO;

	/**
	 * Instantiates a new periodo view.
	 */
	public PeriodoView() {
		Calendar fecha = Calendar.getInstance();
		this.diaInt = fecha.get(Calendar.DATE);
		this.mesInt = fecha.get(Calendar.MONTH) + 1;
		this.anioInt = fecha.get(Calendar.YEAR);
		this.dia = StringUtils.leftPad(String.valueOf(this.diaInt), 2, "0");
		this.mes = String.valueOf(this.mesInt);
		this.anio = String.valueOf(this.anioInt);
		this.cantidadDias = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
		this.mesNombre = getNombreMes();
	}

	/**
	 * Instantiates a new periodo view.
	 *
	 * @param mes
	 *            the mes
	 * @param anio
	 *            the anio
	 */
	public PeriodoView(int mes, int anio) {
		this.mesInt = mes;
		this.anioInt = anio;
		this.mes = String.valueOf(mes);
		this.anio = String.valueOf(anio);
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.MONTH, mes + 1);
		calendario.set(Calendar.YEAR, anio);
		this.cantidadDias = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
		this.mesNombre = getNombreMes();
	}

	/**
	 * Instantiates a new periodo view.
	 *
	 * @param dia
	 *            the dia
	 * @param mes
	 *            the mes
	 * @param anio
	 *            the anio
	 */
	public PeriodoView(int dia, int mes, int anio) {
		this.diaInt = dia;
		this.mesInt = mes;
		this.anioInt = anio;
		this.dia = String.valueOf(dia);
		this.mes = String.valueOf(mes);
		this.anio = String.valueOf(anio);
		Calendar calendario = Calendar.getInstance();
		calendario.set(anio, mes - 1, dia);
		this.cantidadDias = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
		this.mesNombre = getNombreMes();
	}

	/**
	 * Gets the nombre mes.
	 *
	 * @return the nombre mes
	 */
	private String getNombreMes() {
		SimpleDateFormat formateador = new SimpleDateFormat("MMMM", new Locale("es", "ES"));
		Calendar fecha = Calendar.getInstance();
		fecha.set(this.anioInt, this.mesInt - 1, 1);
		return StringUtils.capitalize(formateador.format(fecha.getTime()));
	}

	/**
	 * Gets the mes nombre.
	 *
	 * @return the mesNombre
	 */
	public String getMesNombre() {
		return mesNombre;
	}

	/**
	 * Sets the mes nombre.
	 *
	 * @param mesNombre
	 *            the mesNombre to set
	 */
	public void setMesNombre(String mesNombre) {
		this.mesNombre = mesNombre;
	}

	/**
	 * Gets the total pesos.
	 *
	 * @return the totalPesos
	 */
	public String getTotalPesos() {
		return totalPesos;
	}

	/**
	 * Sets the total pesos.
	 *
	 * @param totalPesos
	 *            the totalPesos to set
	 */
	public void setTotalPesos(String totalPesos) {
		this.totalPesos = totalPesos;
	}

	/**
	 * Gets the total dolares.
	 *
	 * @return the totalDolares
	 */
	public String getTotalDolares() {
		return totalDolares;
	}

	/**
	 * Sets the total dolares.
	 *
	 * @param totalDolares
	 *            the totalDolares to set
	 */
	public void setTotalDolares(String totalDolares) {
		this.totalDolares = totalDolares;
	}

	/**
	 * Gets the total pesos Desplegable.
	 *
	 * @return the totalPesosD
	 */
	public BigDecimal getTotalPesosD() {
		return totalPesosD;
	}

	/**
	 * Adds the total pesos D.
	 *
	 * @param totalPesosD
	 *            the totalPesosD to set
	 */
	public void addTotalPesosD(BigDecimal totalPesosD) {
		this.totalPesosD = this.totalPesosD.add(totalPesosD);
	}

	/**
	 * Gets the total dolares D.
	 *
	 * @return the totalDolaresD
	 */
	public BigDecimal getTotalDolaresD() {
		return totalDolaresD;
	}

	/**
	 * Adds the total dolares D.
	 *
	 * @param totalDolaresD
	 *            the totalDolaresD to set
	 */
	public void addTotalDolaresD(BigDecimal totalDolaresD) {
		this.totalDolaresD = this.totalDolaresD.add(totalDolaresD);
	}

	/**
	 * Gets the cantidad pagos.
	 *
	 * @return the cantidadPagos
	 */
	public int getCantidadPagos() {
		return cantidadPagos;
	}

	/**
	 * Sumar pago.
	 */
	public void sumarPago() {
		cantidadPagos++;
	}

	/**
	 * Gets the cantidad dias.
	 *
	 * @return the cantidad dias
	 */
	public int getCantidadDias() {
		return cantidadDias;
	}

	/**
	 * Sets the cantidad dias.
	 *
	 * @param cantidadDias
	 *            the new cantidad dias
	 */
	public void setCantidadDias(int cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	/**
	 * Gets the dia int.
	 *
	 * @return the diaInt
	 */
	public int getDiaInt() {
		return diaInt;
	}

	/**
	 * Sets the dia int.
	 *
	 * @param diaInt
	 *            the diaInt to set
	 */
	public void setDiaInt(int diaInt) {
		this.diaInt = diaInt;
	}

	/**
	 * Gets the mes int.
	 *
	 * @return the mesInt
	 */
	public int getMesInt() {
		return mesInt;
	}

	/**
	 * Sets the mes int.
	 *
	 * @param mesInt
	 *            the mesInt to set
	 */
	public void setMesInt(int mesInt) {
		this.mesInt = mesInt;
	}

	/**
	 * Gets the anio int.
	 *
	 * @return the anioInt
	 */
	public int getAnioInt() {
		return anioInt;
	}

	/**
	 * Sets the anio int.
	 *
	 * @param anioInt
	 *            the anioInt to set
	 */
	public void setAnioInt(int anioInt) {
		this.anioInt = anioInt;
	}

	/**
	 * Gets the dia.
	 *
	 * @return the dia
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * Sets the dia.
	 *
	 * @param dia
	 *            the dia to set
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

}
