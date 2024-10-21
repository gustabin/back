/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import java.io.Serializable;
import java.util.Comparator;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenFilaBean;

/**
 * The Class UltimoResumenFilaComparator.
 */
public class UltimoResumenFilaComparator implements Comparator<UltimoResumenFilaBean>, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1581811263990334243L;

	/** The Constant COL_FECHA. */
	public static final int COL_FECHA = 0;

	/** The Constant COL_DESCR. */
	public static final int COL_DESCR = 1;

	/** The Constant COL_PESOS. */
	public static final int COL_PESOS = 2;

	/** The Constant COL_DOLARES. */
	public static final int COL_DOLARES = 3;

	/** The Constant COL_COMPROBANTE. */
	public static final int COL_COMPROBANTE = 4;

	/** The Constant ORDEN_A. */
	public static final int ORDEN_A = 0;

	/** The Constant ORDEN_D. */
	public static final int ORDEN_D = 1;

	/** The columna. */
	private int columna;

	/** The tipo. */
	private int tipo;

	/**
	 * Instantiates a new ultimo resumen fila comparator.
	 *
	 * @param columna
	 *            the columna
	 * @param tipo
	 *            the tipo
	 */
	public UltimoResumenFilaComparator(int columna, int tipo) {
		this.columna = columna;
		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(UltimoResumenFilaBean o1, UltimoResumenFilaBean o2) {
		int inv = this.tipo == ORDEN_A ? 1 : -1;

		if ("".equals(o1.getDescripcion()) || "".equals(o2.getDescripcion())) {
			return 0;
		}

		switch (this.columna) {
		case COL_DESCR:
			return inv * o1.getDescripcion().compareTo(o2.getDescripcion());
		case COL_COMPROBANTE:
			return inv * o1.getComprobante().compareTo(o2.getComprobante());
		case COL_PESOS:
			return inv * o1.getPesos().compareTo(o2.getPesos());
		case COL_DOLARES:
			return inv * o1.getDolares().compareTo(o2.getDolares());
		case COL_FECHA:
		default:
			return inv * o1.getFecha().compareTo(o2.getFecha());
		}
	}

}
