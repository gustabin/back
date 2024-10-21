package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

public class ComprobanteHistorialView {

	private String id;
	private String fecha;
	private String importe;
	private String importeDolares;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * @return the importeDolares
	 */
	public String getImporteDolares() {
		return importeDolares;
	}

	/**
	 * @param importeDolares the importeDolares to set
	 */
	public void setImporteDolares(String importeDolares) {
		this.importeDolares = importeDolares;
	}
}
