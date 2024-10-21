/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConfirmarOrdenResponse;

/**
 * The Class FinalizarLicitacionViewResp.
 */
public class FinalizarLicitacionViewResp{

	/** The mensaje. */
	private String mensaje;

	/** The leyenda legal. */
	private String leyendaLegal;

	/** The leyenda legal can. */
	private String leyendaLegalCan;

	/** The datos confirmacion. */
	private DatosConfirmarOrdenResponse datosConfirmacion;

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the leyenda legal.
	 *
	 * @return the leyenda legal
	 */
	public String getLeyendaLegal() {
		return leyendaLegal;
	}

	/**
	 * Sets the leyenda legal.
	 *
	 * @param leyendaLegal
	 *            the new leyenda legal
	 */
	public void setLeyendaLegal(String leyendaLegal) {
		this.leyendaLegal = leyendaLegal;
	}

	/**
	 * Gets the leyenda legal can.
	 *
	 * @return the leyenda legal can
	 */
	public String getLeyendaLegalCan() {
		return leyendaLegalCan;
	}

	/**
	 * Sets the leyenda legal can.
	 *
	 * @param leyendaLegalCan
	 *            the new leyenda legal can
	 */
	public void setLeyendaLegalCan(String leyendaLegalCan) {
		this.leyendaLegalCan = leyendaLegalCan;
	}

	/**
	 * Gets the datos confirmacion.
	 *
	 * @return the datosConfirmacion
	 */
	public DatosConfirmarOrdenResponse getDatosConfirmacion() {
		return datosConfirmacion;
	}

	/**
	 * Sets the datos confirmacion.
	 *
	 * @param datosConfirmacion
	 *            the datosConfirmacion to set
	 */
	public void setDatosConfirmacion(DatosConfirmarOrdenResponse datosConfirmacion) {
		this.datosConfirmacion = datosConfirmacion;
	}

}
