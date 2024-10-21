/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import org.apache.commons.codec.binary.Base64;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;

/**
 * Clase para portar el reporte.
 *
 * @author desa
 */
public class ReporteView {
	/** The bytes. */
	private transient String bytes;

	/** The nombre. */
	private String nombre;

	/** The tipo archivo. */
	private String tipoArchivo;

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
	 *            the bytes to set
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
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the tipo archivo.
	 *
	 * @return the tipoArchivo
	 */
	public String getTipoArchivo() {
		return tipoArchivo;
	}

	/**
	 * Sets the tipo archivo.
	 *
	 * @param tipoArchivo
	 *            the tipoArchivo to set
	 */
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
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
			reporteView.setNombre(reporte.getNombre());
			reporteView.setTipoArchivo(reporte.getTipoArchivo().getMimeTipe());
		}
		return reporteView;
	}

}
