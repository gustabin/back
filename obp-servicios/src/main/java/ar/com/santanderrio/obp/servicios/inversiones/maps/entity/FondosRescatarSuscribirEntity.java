package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import java.util.List;

public class FondosRescatarSuscribirEntity {
	
	private List<String> puedeRescatar;
	
	private List<String> puedeSuscribir;

	/**
	 * @return the puedeRescatar
	 */
	public List<String> getPuedeRescatar() {
		return puedeRescatar;
	}

	/**
	 * @param puedeRescatar the puedeRescatar to set
	 */
	public void setPuedeRescatar(List<String> puedeRescatar) {
		this.puedeRescatar = puedeRescatar;
	}

	/**
	 * @return the puedeSuscribir
	 */
	public List<String> getPuedeSuscribir() {
		return puedeSuscribir;
	}

	/**
	 * @param puedeSuscribir the puedeSuscribir to set
	 */
	public void setPuedeSuscribir(List<String> puedeSuscribir) {
		this.puedeSuscribir = puedeSuscribir;
	}
	
	

}
