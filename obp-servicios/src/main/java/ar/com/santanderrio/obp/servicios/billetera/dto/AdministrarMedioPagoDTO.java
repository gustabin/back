/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dto;

import java.util.List;

/**
 * The Class AdministrarMedioPagoDTO.
 */
public class AdministrarMedioPagoDTO {

	/**
	 * The Class AdministrarMedioPagoItem.
	 */
	public class AdministrarMedioPagoItem {

		/** The numero tarjeta. */
		private String numeroTarjeta;

		/** The status. */
		private String status;

		/**
		 * Gets the numero tarjeta.
		 *
		 * @return the numeroTarjeta
		 */
		public String getNumeroTarjeta() {
			return numeroTarjeta;
		}

		/**
		 * Gets the status.
		 *
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * Sets the numero tarjeta.
		 *
		 * @param numeroTarjeta
		 *            the numeroTarjeta to set
		 */
		public void setNumeroTarjeta(String numeroTarjeta) {
			this.numeroTarjeta = numeroTarjeta;
		}

		/**
		 * Sets the status.
		 *
		 * @param status
		 *            the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}

	}

	/** The items. */
	private List<AdministrarMedioPagoItem> items;

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public List<AdministrarMedioPagoItem> getItems() {
		return items;
	}

	/**
	 * Sets the items.
	 *
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<AdministrarMedioPagoItem> items) {
		this.items = items;
	}
}
