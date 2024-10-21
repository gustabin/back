/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.util.List;

/**
 * The Class UltimosMovimientosDTO.
 */
public class UltimosMovimientosDTO {

	/** The fecha saldo. */
	private String fechaSaldo;

	/** The saldo final pesos. */
	private String saldoFinalPesos;

	/** The saldo final dolares. */
	private String saldoFinalDolares;

	/** The saldo acte. */
	private String saldoACTE;

	/** The saldo acah. */
	private String saldoACAH;

	/** The saldo accd. */
	private String saldoACCD;

	/** The saldo acad. */
	private String saldoACAD;

	/** The cantidad movientos. */
	private String cantidadMovientos;

	/** The movimientos. */
	private List<Movimiento> movimientos;

	/**
	 * Gets the fecha saldo.
	 *
	 * @return the fecha saldo
	 */
	public String getFechaSaldo() {
		return fechaSaldo;
	}

	/**
	 * Setter para fecha saldo.
	 *
	 * @param fechaSaldo
	 *            el nuevo fecha saldo
	 */
	public void setFechaSaldo(String fechaSaldo) {
		this.fechaSaldo = fechaSaldo;
	}

	/**
	 * Gets the saldo final pesos.
	 *
	 * @return the saldo final pesos
	 */
	public String getSaldoFinalPesos() {
		return saldoFinalPesos;
	}

	/**
	 * Setter para saldo final pesos.
	 *
	 * @param saldoFinalPesos
	 *            el nuevo saldo final pesos
	 */
	public void setSaldoFinalPesos(String saldoFinalPesos) {
		this.saldoFinalPesos = saldoFinalPesos;
	}

	/**
	 * Gets the saldo final dolares.
	 *
	 * @return the saldo final dolares
	 */
	public String getSaldoFinalDolares() {
		return saldoFinalDolares;
	}

	/**
	 * Setter para saldo final dolares.
	 *
	 * @param saldoFinalDolares
	 *            el nuevo saldo final dolares
	 */
	public void setSaldoFinalDolares(String saldoFinalDolares) {
		this.saldoFinalDolares = saldoFinalDolares;
	}

	/**
	 * Gets the saldo acte.
	 *
	 * @return the saldo acte
	 */
	public String getSaldoACTE() {
		return saldoACTE;
	}

	/**
	 * Setter para saldo acte.
	 *
	 * @param saldoACTE
	 *            el nuevo saldo acte
	 */
	public void setSaldoACTE(String saldoACTE) {
		this.saldoACTE = saldoACTE;
	}

	/**
	 * Gets the saldo acah.
	 *
	 * @return the saldo acah
	 */
	public String getSaldoACAH() {
		return saldoACAH;
	}

	/**
	 * Setter para saldo acah.
	 *
	 * @param saldoACAH
	 *            el nuevo saldo acah
	 */
	public void setSaldoACAH(String saldoACAH) {
		this.saldoACAH = saldoACAH;
	}

	/**
	 * Gets the saldo accd.
	 *
	 * @return the saldo accd
	 */
	public String getSaldoACCD() {
		return saldoACCD;
	}

	/**
	 * Setter para saldo accd.
	 *
	 * @param saldoACCD
	 *            el nuevo saldo accd
	 */
	public void setSaldoACCD(String saldoACCD) {
		this.saldoACCD = saldoACCD;
	}

	/**
	 * Gets the saldo acad.
	 *
	 * @return the saldo acad
	 */
	public String getSaldoACAD() {
		return saldoACAD;
	}

	/**
	 * Setter para saldo acad.
	 *
	 * @param saldoACAD
	 *            el nuevo saldo acad
	 */
	public void setSaldoACAD(String saldoACAD) {
		this.saldoACAD = saldoACAD;
	}

	/**
	 * Gets the cantidad movientos.
	 *
	 * @return the cantidad movientos
	 */
	public String getCantidadMovientos() {
		return cantidadMovientos;
	}

	/**
	 * Setter para cantidad movientos.
	 *
	 * @param cantidadMovientos
	 *            el nuevo cantidad movientos
	 */
	public void setCantidadMovientos(String cantidadMovientos) {
		this.cantidadMovientos = cantidadMovientos;
	}

	/**
	 * Gets the movimientos.
	 *
	 * @return the movimientos
	 */
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	/**
	 * Setter para movimientos.
	 *
	 * @param movimientos
	 *            el nuevo movimientos
	 */
	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UltimosMovimientosDTO [fechaSaldo=" + fechaSaldo + ", saldoFinalPesos=" + saldoFinalPesos
				+ ", saldoFinalDolares=" + saldoFinalDolares + ", saldoACTE=" + saldoACTE + ", saldoACAH=" + saldoACAH
				+ ", saldoACCD=" + saldoACCD + ", saldoACAD=" + saldoACAD + ", cantidadMovientos=" + cantidadMovientos
				+ ", movimientos=" + movimientos + "]";
	}
}
