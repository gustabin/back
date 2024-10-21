package ar.com.santanderrio.obp.servicios.clave.online.view;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Itr
 *
 */
public class OpcionesCelularView extends MetodoAutenticacionView {

	private List<String> opcionesCelular = new ArrayList<String>();

	/**
	 * @return the opcionesCelular
	 */
	public List<String> getOpcionesCelular() {
		return opcionesCelular;
	}

	/**
	 * @param opcionesCelular the opcionesCelular to set
	 */
	public void setOpcionesCelular(List<String> opcionesCelular) {
		this.opcionesCelular = opcionesCelular;
	}

}
