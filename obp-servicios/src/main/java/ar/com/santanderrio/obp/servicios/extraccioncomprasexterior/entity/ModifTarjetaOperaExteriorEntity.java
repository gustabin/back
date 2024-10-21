/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * ModifTarjetaOperaExteriorEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class ModifTarjetaOperaExteriorEntity {

	/** The numero tarjeta. */
	@Field
	private String numeroTarjeta;

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

}
