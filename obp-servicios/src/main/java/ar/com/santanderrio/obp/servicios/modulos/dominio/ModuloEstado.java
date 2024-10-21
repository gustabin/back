/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos.dominio;

/**
 * Indica si el modulo se debe mostrar (indice 0), ocultar (indice 1) o mostrar
 * con mensaje (indice 2) segun lo indicado en la tabla HB_MODULOS_PERMISOS.
 * 
 * @author sergio.e.goldentair
 *
 */
public enum ModuloEstado {

	/** The mostrar. */
	MOSTRAR,
	/** The ocultar. */
	OCULTAR,
	/** The mostrar mensaje. */
	MOSTRAR_MENSAJE;
}
