/*
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.web.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import ar.com.santanderrio.obp.servicios.suscripciones.dto.SuscripcionesDTO;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.MensajeSuscripcion;

/**
 * The Class InicioGeneralSuscripcionMyAView.
 */
public class InicioProductoSuscripcionMyAView {

	/** The cabecera. */
	private String cabecera;

	/** The opcionesAlertas. */
	private List<OpcionAlertaMyAView> opcionesAlertas;

	/**
	 * Constructor de InicioProductoSuscripcionMyAView desde un objeto
	 * SuscripcionesDTO.
	 *
	 * @param suscripcionesDTO
	 *            the suscripciones DTO
	 */
	public InicioProductoSuscripcionMyAView(SuscripcionesDTO suscripcionesDTO) {
		this.cabecera = suscripcionesDTO.getTituloSuscripcion();
		List<OpcionAlertaMyAView> listaOpcionesAlertas = new ArrayList<OpcionAlertaMyAView>();
		if (CollectionUtils.isNotEmpty(suscripcionesDTO.getMensajes())) {
			for (MensajeSuscripcion mensaje : suscripcionesDTO.getMensajes()) {
				listaOpcionesAlertas.add(new OpcionAlertaMyAView(mensaje));
			}
		}
		this.opcionesAlertas = listaOpcionesAlertas;
	}

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public String getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the cabecera to set
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * Gets the opciones alertas.
	 *
	 * @return the opcionesAlertas
	 */
	public List<OpcionAlertaMyAView> getOpcionesAlertas() {
		return opcionesAlertas;
	}

	/**
	 * Sets the opciones alertas.
	 *
	 * @param opcionesAlertas
	 *            the opcionesAlertas to set
	 */
	public void setOpcionesAlertas(List<OpcionAlertaMyAView> opcionesAlertas) {
		this.opcionesAlertas = opcionesAlertas;
	}

}
