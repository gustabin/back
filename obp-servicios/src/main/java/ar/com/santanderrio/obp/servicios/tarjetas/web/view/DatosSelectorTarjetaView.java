/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;

/**
 * The Class DatosSelectorTarjetaView.
 */
public class DatosSelectorTarjetaView {

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
	public DatosSelectorTarjetaView(List<ItemMensajeRespuesta> itemsMensajeRespuesta, List<TarjetaView> list,
			IdentificacionCuenta identificacionCuenta) {
		super();
		this.setList(list);
		this.setIdentificacionCuenta(identificacionCuenta);
		this.setItemsMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
		if (itemsMensajeRespuesta != null) {
			this.setItemsMensajeRespuesta(itemsMensajeRespuesta);
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatosSelectorTarjetaView [itemsMensajeRespuesta=" + itemsMensajeRespuesta + ", list=" + list
				+ ", identificacionCuenta=" + identificacionCuenta + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificacionCuenta == null) ? 0 : identificacionCuenta.hashCode());
		result = prime * result + ((itemsMensajeRespuesta == null) ? 0 : itemsMensajeRespuesta.hashCode());
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DatosSelectorTarjetaView other = (DatosSelectorTarjetaView) obj;
		return new EqualsBuilder().append(identificacionCuenta, other.identificacionCuenta)
				.append(itemsMensajeRespuesta, other.itemsMensajeRespuesta).append(list, other.list).isEquals();
	}
}
