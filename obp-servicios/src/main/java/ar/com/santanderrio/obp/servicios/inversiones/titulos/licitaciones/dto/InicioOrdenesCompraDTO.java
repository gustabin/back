/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class InicioOrdenesCompraDTO.
 */
public class InicioOrdenesCompraDTO {
	
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
