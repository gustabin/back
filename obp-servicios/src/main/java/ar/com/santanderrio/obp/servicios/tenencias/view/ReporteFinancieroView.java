package ar.com.santanderrio.obp.servicios.tenencias.view;

import org.apache.commons.codec.binary.Base64;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;

/**
 * The Class ReporteView.
 */
public class ReporteFinancieroView {

	/** The bytes. */
	private transient String bytes;

	/** The nombre. */
	private String nombre;

	/** The tipo archivo. */
	private String tipoArchivo;

	/** The byte array. */
	private byte[] byteArray;

	/**
	 * Gets the bytes.
	 *
	 * @return the bytes
	 */
	public String getBytes() {
		return bytes;
	}

	/**
	 * Sets the bytes.
	 *
	 * @param bytes
	 *            the new bytes
	 */
	public void setBytes(String bytes) {
		this.bytes = bytes;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the tipo archivo.
	 *
	 * @return the tipo archivo
	 */
	public String getTipoArchivo() {
		return tipoArchivo;
	}

	/**
	 * Sets the tipo archivo.
	 *
	 * @param tipoArchivo
	 *            the new tipo archivo
	 */
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	/**
	 * Gets the byte array.
	 *
	 * @return the byteArray
	 */
	public byte[] getByteArray() {
		return byteArray;
	}

	/**
	 * Sets the byte array.
	 *
	 * @param byteArray
	 *            the byteArray to set
	 */
	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	/**
	 * From reporte.
	 *
	 * @param reporte
	 *            the reporte
	 * @return the reporte view
	 */
	public static ReporteFinancieroView fromReporte(Reporte reporte) {
		ReporteFinancieroView reporteView = null;
		if (reporte != null) {
			reporteView = new ReporteFinancieroView();

			reporteView.setBytes(Base64.encodeBase64String(reporte.getBytes()));
			reporteView.setByteArray(reporte.getBytes());
			reporteView.setNombre(reporte.getNombre());
			reporteView.setTipoArchivo(reporte.getTipoArchivo().getMimeTipe());
		}
		return reporteView;
	}
}
