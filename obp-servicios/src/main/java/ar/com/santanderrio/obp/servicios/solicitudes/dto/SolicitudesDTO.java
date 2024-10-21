/*
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * The Class SolicitudesDTO.
 */
public class SolicitudesDTO {

	/** The gestiones. */
	private List<String> gestiones;

	/** The action sheet. */
	private List<String> actionSheet;

	/**
	 * Instantiates a new solicitudes DTO.
	 */
	public SolicitudesDTO() {
		super();
	}

	/**
	 * Instantiates a new solicitudes DTO.
	 *
	 * @param gestiones
	 *            the gestiones
	 * @param actionSheet
	 *            the action sheet
	 */
	public SolicitudesDTO(List<String> gestiones, List<String> actionSheet) {
		this.gestiones = gestiones;
		this.actionSheet = actionSheet;
	}

	/**
	 * Gets the gestiones.
	 *
	 * @return the gestiones
	 */
	public List<String> getGestiones() {
		return gestiones;
	}

	/**
	 * Sets the gestiones.
	 *
	 * @param gestiones
	 *            the new gestiones
	 */
	public void setGestiones(List<String> gestiones) {
		this.gestiones = gestiones;
	}

	/**
	 * Gets the action sheet.
	 *
	 * @return the action sheet
	 */
	public List<String> getActionSheet() {
		return actionSheet;
	}

	/**
	 * Sets the action sheet.
	 *
	 * @param actionSheet
	 *            the new action sheet
	 */
	public void setActionSheet(List<String> actionSheet) {
		this.actionSheet = actionSheet;
	}

	/**
	 * Adds the gestiones.
	 *
	 * @param solicitud
	 *            the solicitud
	 */
	public void addGestiones(String solicitud) {
		if (CollectionUtils.isEmpty(this.gestiones)) {
			this.gestiones = new ArrayList<String>();
		}
		this.gestiones.add(solicitud);
	}

	/**
	 * Adds the action sheet.
	 *
	 * @param solicitud
	 *            the solicitud
	 */
	public void addActionSheet(String solicitud) {
		if (CollectionUtils.isEmpty(this.actionSheet)) {
			this.actionSheet = new ArrayList<String>();
		}
		this.actionSheet.add(solicitud);
	}

}
