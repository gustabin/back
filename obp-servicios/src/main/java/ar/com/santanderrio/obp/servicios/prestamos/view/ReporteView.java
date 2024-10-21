package ar.com.santanderrio.obp.servicios.prestamos.view;

import org.apache.commons.codec.binary.Base64;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteView.
 */
public class ReporteView {
	
	/** The id. */
	private String id;

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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * From reporte.
	 *
	 * @param reporte
	 *            the reporte
	 * @return the reporte view
	 */
	public static ReporteView fromReporte(Reporte reporte) {
		ReporteView reporteView = null;
		if (reporte != null) {
			reporteView = new ReporteView();

			reporteView.setBytes(Base64.encodeBase64String(reporte.getBytes()));
			reporteView.setByteArray(reporte.getBytes());
			reporteView.setNombre(reporte.getNombre());
			reporteView.setTipoArchivo(reporte.getTipoArchivo().getMimeTipe());
		}
		return reporteView;
	}
}
