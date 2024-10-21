/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.CuentasTituloHabilitadasCompra;

/**
 * The Class InicioOrdenesCompraView.
 */
public class InicioOrdenesCompraView {
	
	/** The lista cuentas habilitadas. */
	List<CuentasTituloHabilitadasCompra> listaCuentasHabilitadas = new ArrayList<CuentasTituloHabilitadasCompra>();

	/**
	 * Gets the lista cuentas habilitadas.
	 *
	 * @return the listaCuentasHabilitadas
	 */
	public List<CuentasTituloHabilitadasCompra> getListaCuentasHabilitadas() {
		return listaCuentasHabilitadas;
	}

	/**
	 * Sets the lista cuentas habilitadas.
	 *
	 * @param listaCuentasHabilitadas
	 *            the listaCuentasHabilitadas to set
	 */
	public void setListaCuentasHabilitadas(List<CuentasTituloHabilitadasCompra> listaCuentasHabilitadas) {
		this.listaCuentasHabilitadas = listaCuentasHabilitadas;
	}
}
