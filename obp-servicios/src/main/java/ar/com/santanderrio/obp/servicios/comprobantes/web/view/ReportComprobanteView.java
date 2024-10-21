/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * The Class ReportComprobanteView.
 */
public class ReportComprobanteView {
	
	/** The titulo comprobante. */
	protected String tituloComprobante;

	/** The tipo prestamo. */
	protected String tipoPrestamo;

	/** The fecha hora actual key. */
	protected final String fechaHoraActualKey = "FECHA_ACTUAL";

	/** The titulo comprobante key. */
	protected final String tituloComprobanteKey = "TITULO_COMPROBANTE";
	
	/** The path. */
	protected final String path = "classpath:/report/prestamos/comprobante/";

	/**
	 * Gets the titulo comprobante.
	 *
	 * @return the titulo comprobante
	 */
	public String getTituloComprobante() {
		return tituloComprobante;
	}

	/**
	 * Sets the titulo comprobante.
	 *
	 * @param tituloComprobante
	 *            the new titulo comprobante
	 */
	public void setTituloComprobante(String tituloComprobante) {
		this.tituloComprobante = tituloComprobante;
	}

	/**
	 * Obtener jasper.
	 *
	 * @return the string
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public String obtenerJasper() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener parametros PDF.
	 *
	 * @return the hash map
	 */
	public HashMap<String, Object> obtenerParametrosPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener parametros PDF.
	 *
	 * @param isUva
	 *            the is uva
	 * @return the hash map
	 */
	public HashMap<String, Object> obtenerParametrosPDF(boolean isUva) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Gets the tipo prestamo.
	 *
	 * @return the tipo prestamo
	 */
	public String getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * Sets the tipo prestamo.
	 *
	 * @param tipoPrestamo
	 *            the new tipo prestamo
	 */
	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

}
