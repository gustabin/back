/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que indica el poder de compra asociado a una cuenta (inversiones).
 *
 * @author juan.pablo.picate
 */
public class PoderCompraView {
	
	/** The lista saldos. */
	List<PlazoPDCView> listaSaldos = new ArrayList<PlazoPDCView>();

	/**
	 * Gets the lista saldos.
	 *
	 * @return the listaSaldos
	 */
	public List<PlazoPDCView> getListaSaldos() {
		return listaSaldos;
	}

	/**
	 * Sets the lista saldos.
	 *
	 * @param listaSaldos
	 *            the listaSaldos to set
	 */
	public void setListaSaldos(List<PlazoPDCView> listaSaldos) {
		this.listaSaldos = listaSaldos;
	}

}
