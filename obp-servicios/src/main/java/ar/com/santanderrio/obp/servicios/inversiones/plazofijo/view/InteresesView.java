/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultarCondicionesInteresanteEntity;

/**
 * The Class InteresesView.
 */
public class InteresesView {

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
