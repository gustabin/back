/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultarCondicionesInteresanteEntity;

/**
 * The Class InteresesDTO.
 */
public class InteresesDTO {

	/** The interesante PF. */
	List<ConsultarCondicionesInteresanteEntity> interesantePF = new ArrayList<ConsultarCondicionesInteresanteEntity>();

	/**
	 * Gets the interesante PF.
	 *
	 * @return the interesante PF
	 */
	public List<ConsultarCondicionesInteresanteEntity> getInteresantePF() {
		return interesantePF;
	}

	/**
	 * Sets the interesante PF.
	 *
	 * @param interesantePF
	 *            the new interesante PF
	 */
	public void setInteresantePF(List<ConsultarCondicionesInteresanteEntity> interesantePF) {
		this.interesantePF = interesantePF;
	}

}
