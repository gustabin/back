/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class CitaOutView.
 *
 * @author IT Resources
 */
public class CitaOutView {
	
	/** The gestiones. */
	private List<CitaView> gestiones = new ArrayList<CitaView>();

	/** The action sheet. */
	private List<CitaView> actionSheet = new ArrayList<CitaView>();
	
	/** The datos mya. */
	private DatosMYAView datosMYA;
	
	/** The es cliente select. */
	private Boolean esClienteSelect = Boolean.FALSE;

	/** The nro documento. */
	private String nroDocumento;

	/** The nombre apellido. */
	private String nombreApellido;

	/**
	 * Gets the gestiones.
	 *
	 * @return the gestiones
	 */
	public List<CitaView> getGestiones() {
		return gestiones;
	}

	/**
	 * Sets the gestiones.
	 *
	 * @param gestiones
	 *            the gestiones to set
	 */
	public void setGestiones(List<CitaView> gestiones) {
		this.gestiones = gestiones;
	}

	/**
	 * Gets the action sheet.
	 *
	 * @return the actionSheet
	 */
	public List<CitaView> getActionSheet() {
		return actionSheet;
	}

	/**
	 * Sets the action sheet.
	 *
	 * @param actionSheet
	 *            the actionSheet to set
	 */
	public void setActionSheet(List<CitaView> actionSheet) {
		this.actionSheet = actionSheet;
	}

	/**
	 * Gets the datos MYA.
	 *
	 * @return the datosMYA
	 */
	public DatosMYAView getDatosMYA() {
		return datosMYA;
	}

	/**
	 * Sets the datos MYA.
	 *
	 * @param datosMYA
	 *            the datosMYA to set
	 */
	public void setDatosMYA(DatosMYAView datosMYA) {
		this.datosMYA = datosMYA;
	}

	/**
	 * Gets the es cliente select.
	 *
	 * @return the esClienteSelect
	 */
	public Boolean getEsClienteSelect() {
		return esClienteSelect;
	}

	/**
	 * Sets the es cliente select.
	 *
	 * @param esClienteSelect
	 *            the esClienteSelect to set
	 */
	public void setEsClienteSelect(Boolean esClienteSelect) {
		this.esClienteSelect = esClienteSelect;
	}

	/**
	 * Gets the nro documento.
	 *
	 * @return the nro documento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * Sets the nro documento.
	 *
	 * @param nroDocumento the new nro documento
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * Gets the nombre apellido.
	 *
	 * @return the nombre apellido
	 */
	public String getNombreApellido() {
		return nombreApellido;
	}

	/**
	 * Sets the nombre apellido.
	 *
	 * @param nombreApellido the new nombre apellido
	 */
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

}
