/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenTarjetaDTO;

/**
 * The Interface ManejoDeMensajesTarjeta.
 */
public interface ManejoDeMensajesTarjeta {

	/**
	 * Obtener tooltip favorito.
	 *
	 * @return the string
	 */
	String obtenerTooltipFavorito();

	/**
	 * Obtener tooltip no favorito.
	 *
	 * @return the string
	 */
	String obtenerTooltipNoFavorito();

	/**
	 * Obtener mensaje error al consultar consumos.
	 *
	 * @return the string
	 */
	String obtenerMensajeErrorAlConsultarConsumos();

	/**
	 * Obtener item error carga consumos pendientes confirmacion.
	 *
	 * @return the item mensaje respuesta
	 */
	ItemMensajeRespuesta obtenerItemErrorCargaConsumosPendientesConfirmacion();

	/**
	 * Obtener item error actualizar alias.
	 *
	 * @return the item mensaje respuesta
	 */
	ItemMensajeRespuesta obtenerItemErrorActualizarAlias();

	/**
	 * Obtener item error actualizar favorito tarjeta.
	 *
	 * @return the item mensaje respuesta
	 */
	ItemMensajeRespuesta obtenerItemErrorActualizarFavoritoTarjeta();

	/**
	 * Obtener item error carga ultimo resumen.
	 *
	 * @return the item mensaje respuesta
	 */
	ItemMensajeRespuesta obtenerItemErrorCargaUltimoResumen();

	/**
	 * Obtener item error sin ultimo resumen.
	 *
	 * @return the item mensaje respuesta
	 */
	ItemMensajeRespuesta obtenerItemErrorSinUltimoResumen();

	/**
	 * Obtener item error sin consumos.
	 *
	 * @return the item mensaje respuesta
	 */
	ItemMensajeRespuesta obtenerItemErrorSinConsumos();

	/**
	 * Obtener dato item error consulta movimientos.
	 *
	 * @param i
	 *            the i
	 * @return the dato item mensaje
	 */
	DatoItemMensaje obtenerDatoItemErrorConsultaMovimientos(Integer i);

	/**
	 * Obtener dato item warning al consultar consumos.
	 *
	 * @param i
	 *            the i
	 * @return the dato item mensaje
	 */
	DatoItemMensaje obtenerDatoItemWarningAlConsultarConsumos(Integer i);

	/**
	 * Obtener dato item mensaje.
	 *
	 * @param resumenTarjeta
	 *            the resumen tarjeta
	 * @param i
	 *            the i
	 * @return the dato item mensaje
	 */
	DatoItemMensaje obtenerDatoItemMensaje(ResumenTarjetaDTO resumenTarjeta, Integer i);

	/**
	 * Obtener dato item si no hay resumenes.
	 *
	 * @return the dato item mensaje
	 */
	DatoItemMensaje obtenerDatoItemSiNoHayResumenes();

	/**
	 * Obtener dato item error total.
	 *
	 * @param i
	 *            the i
	 * @return the dato item mensaje
	 */
	DatoItemMensaje obtenerDatoItemErrorTotal(Integer i);

	/**
	 * Obtener dato item error sin tarjetas.
	 *
	 * @return the dato item mensaje
	 */
	DatoItemMensaje obtenerDatoItemErrorSinTarjetas();

}
