/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

/**
 * The Interface FinalizarRescateHash.
 */
public interface FinalizarRescateHash {
	
	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	String getCodigoFondo();
	
	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	String getCuentaTitulo();
	
	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	String getImporte();
	
	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	String getMoneda();
	
	/**
	 * Gets the numero cta cred.
	 *
	 * @return the numero cta cred
	 */
	String getNumeroCtaCred();
	
	/**
	 * Gets the sucursal cta cred.
	 *
	 * @return the sucursal cta cred
	 */
	String getSucursalCtaCred();

}
