/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetaView;

/**
 * The Class DatosResumenTarjeta.
 */
public class DatosResumenTarjeta {

	/** The items mensaje respuesta. */
	List<ItemMensajeRespuesta> itemsMensajeRespuesta;

	/** The list. */
	List<TarjetaView> list;

	/** The identificacion cuenta. */
	IdentificacionCuenta identificacionCuenta;

	/**
	 * Instantiates a new datos resumen tarjeta.
	 *
	 * @param itemsMensajeRespuesta
	 *            the items mensaje respuesta
	 * @param list
	 *            the list
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 */
	public DatosResumenTarjeta(List<ItemMensajeRespuesta> itemsMensajeRespuesta, List<TarjetaView> list,
			IdentificacionCuenta identificacionCuenta) {
		super();
		this.itemsMensajeRespuesta = itemsMensajeRespuesta;
		this.list = list;
		this.identificacionCuenta = identificacionCuenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatosResumenTarjeta [itemsMensajeRespuesta=" + itemsMensajeRespuesta + ", list=" + list
				+ ", identificacionCuenta=" + identificacionCuenta + "]";
	}

	/**
	 * Gets the items mensaje respuesta.
	 *
	 * @return the items mensaje respuesta
	 */
	public List<ItemMensajeRespuesta> getItemsMensajeRespuesta() {
		return itemsMensajeRespuesta;
	}

	/**
	 * Sets the items mensaje respuesta.
	 *
	 * @param itemsMensajeRespuesta
	 *            the new items mensaje respuesta
	 */
	public void setItemsMensajeRespuesta(List<ItemMensajeRespuesta> itemsMensajeRespuesta) {
		this.itemsMensajeRespuesta = itemsMensajeRespuesta;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<TarjetaView> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list
	 *            the new list
	 */
	public void setList(List<TarjetaView> list) {
		this.list = list;
	}

	/**
	 * Gets the identificacion cuenta.
	 *
	 * @return the identificacion cuenta
	 */
	public IdentificacionCuenta getIdentificacionCuenta() {
		return identificacionCuenta;
	}

	/**
	 * Sets the identificacion cuenta.
	 *
	 * @param identificacionCuenta
	 *            the new identificacion cuenta
	 */
	public void setIdentificacionCuenta(IdentificacionCuenta identificacionCuenta) {
		this.identificacionCuenta = identificacionCuenta;
	}
}
