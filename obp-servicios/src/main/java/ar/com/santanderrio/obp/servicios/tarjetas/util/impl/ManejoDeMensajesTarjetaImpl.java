/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta;

/**
 * The Class ManejoDeMensajesTarjetaImpl.
 */
@Component("mensajesTarjeta")
public class ManejoDeMensajesTarjetaImpl implements ManejoDeMensajesTarjeta {

	/** The Constant TAG_TARJETA. */
	private static final String TAG_TARJETA = "tarjetas[";

	/** The Constant TAG_TARJETA_CIERRE. */
	private static final String TAG_TARJETA_CIERRE = "]";

	/** The Constant CONSUMO_TARJETA_DEFAULT. */
	private static final String CONSUMO_TARJETA_DEFAULT = "consumoTarjetaDefault";

	/** The Constant ULTIMO_RESUMEN. */
	private static final String ULTIMO_RESUMEN = "ultimoResumen";

	/** The Constant CONSUMOS_PENDIENTES. */
	private static final String CONSUMOS_PENDIENTES = "consumosPendientes";

	/** The Mensaje dao. */
	@Autowired
	private MensajeDAO mensajeDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerDatoItemSiNoHayResumenes()
	 */
	/*
	 * TipoError.ERROR_CONSULTA_MOVIMIENTOS (00003)
	 * 
	 * @param i
	 * 
	 * @return ItemMensajeRespuesta
	 */
	@Override
	public DatoItemMensaje obtenerDatoItemSiNoHayResumenes() {
		String codigoMensaje = TarjetaBOUtils.getCodigoErrorGenerico();
		return generarDatoItemErrorGenerico(codigoMensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerDatoItemErrorConsultaMovimientos(java.lang.Integer)
	 */
	/*
	 * TipoError.ERROR_CONSULTA_MOVIMIENTOS (1067)
	 * 
	 * @param i
	 * 
	 * @return ItemMensajeRespuesta
	 */
	@Override
	public DatoItemMensaje obtenerDatoItemErrorConsultaMovimientos(Integer i) {
		String codigoMensaje = TarjetaBOUtils.getCodigoErrorAlConsultarDisponibles();
		return generarItemErrorConsultaMovimiento(i, codigoMensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerDatoItemErrorTotal(java.lang.Integer)
	 */
	@Override
	public DatoItemMensaje obtenerDatoItemErrorTotal(Integer i) {
		String codigoMensaje = TarjetaBOUtils.getCodigoErrorTotal();
		return generarItemErrorTotal(i, codigoMensaje);
	}

	/*
	 * TipoError.WARNING (1068)
	 * 
	 * @param i
	 * 
	 * @param mensaje
	 * 
	 * @return ItemMensajeRespuesta
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerDatoItemWarningAlConsultarConsumos(java.lang.Integer)
	 */
	@Override
	public DatoItemMensaje obtenerDatoItemWarningAlConsultarConsumos(Integer i) {
		String codigoMensaje = TarjetaBOUtils.getCodigoErrorAlConsultarConsumos();
		return generarDatoItemWarning(i, codigoMensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerItemErrorActualizarAlias()
	 */
	/*
	 * TipoError.ERROR_ACTUALIZAR_ALIAS (1071)
	 * 
	 * @return ItemMensajeRespuesta
	 */
	@Override
	public ItemMensajeRespuesta obtenerItemErrorActualizarAlias() {
		String mensaje = obtenerMensajeErrorActualizarAlias();
		return generarItemErrorActualizarAlias(mensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerItemErrorCargaUltimoResumen()
	 */
	/*
	 * TipoError.ERROR_ACTUALIZAR_ALIAS (1071)
	 * 
	 * @return ItemMensajeRespuesta
	 */
	@Override
	public ItemMensajeRespuesta obtenerItemErrorCargaUltimoResumen() {
		String mensaje = obtenerMensajeErrorCargaUltimoResumen();
		return generarItemErrorCargaUltimoResumen(mensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerItemErrorActualizarFavoritoTarjeta()
	 */
	/*
	 * TipoError.ERROR_ACTUALIZAR_FAVORITO_TARJETA (1070)
	 * 
	 * @param i
	 * 
	 * @param mensaje
	 * 
	 * @return ItemMensajeRespuesta
	 */
	@Override
	public ItemMensajeRespuesta obtenerItemErrorActualizarFavoritoTarjeta() {
		String mensaje = obtenerMensajeErrorActualizarFavoritoTarjeta();
		return generarItemErrorActualizarFavoritoTarjeta(mensaje);
	}

	/**
	 * Generar dato item warning.
	 *
	 * @param iTarjeta
	 *            the i tarjeta
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the dato item mensaje
	 */
	private DatoItemMensaje generarDatoItemWarning(Integer iTarjeta, String codigoMensaje) {
		String tag = String.valueOf(iTarjeta);
		return respuestaFactory.generarDatoItemMensaje(TAG_TARJETA + tag + TAG_TARJETA_CIERRE, TipoError.WARNING,
				codigoMensaje);
	}

	/**
	 * Generar item error actualizar favorito tarjeta.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the item mensaje respuesta
	 */
	public ItemMensajeRespuesta generarItemErrorActualizarFavoritoTarjeta(String mensaje) {
		return generarItemMensajeRespuesta(null, TipoError.ERROR_ACTUALIZAR_FAVORITO_TARJETA, mensaje);
	}

	/**
	 * Generar item error actualizar alias.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the item mensaje respuesta
	 */
	public ItemMensajeRespuesta generarItemErrorActualizarAlias(String mensaje) {
		return generarItemMensajeRespuesta(null, TipoError.ERROR_ACTUALIZAR_ALIAS, mensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerItemErrorCargaConsumosPendientesConfirmacion()
	 */
	/*
	 * TipoError.ERROR_CARGA_ULTIMOS_CONSUMOS (1072)
	 * 
	 * @param i
	 * 
	 * @param mensaje
	 * 
	 * @return ItemMensajeRespuesta
	 */
	@Override
	public ItemMensajeRespuesta obtenerItemErrorCargaConsumosPendientesConfirmacion() {
		String mensaje = obtenerMensajeErrorCargaConsumosPendientesConfirmacion();
		return generarItemErrorConsumosPendientes(mensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerItemErrorCargaUltimosConsumos()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerItemErrorSinConsumos()
	 */
	/*
	 * TipoError.ERROR_SIN_CONSUMOS (1073)
	 * 
	 * @param i
	 * 
	 * @param mensaje
	 * 
	 * @return ItemMensajeRespuesta
	 */
	@Override
	public ItemMensajeRespuesta obtenerItemErrorSinConsumos() {
		String mensaje = obtenerMensajeErrorSinConsumos();
		return generarItemErrorSinConsumos(mensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerDatoItemErrorSinTarjetas()
	 */
	/*
	 * TipoError.ERROR_SIN_FINANCIACIONES (1075)
	 * 
	 * @return ItemMensajeRespuesta
	 */
	@Override
	public DatoItemMensaje obtenerDatoItemErrorSinTarjetas() {
		String codigoMensaje = TarjetaBOUtils.getCodigoErrorSinTarjetas();
		String tag = String.valueOf(0);
		return respuestaFactory.generarDatoItemMensaje(TAG_TARJETA + tag + TAG_TARJETA_CIERRE, TipoError.ERROR_GENERICO,
				codigoMensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerItemErrorSinUltimoResumen()
	 */
	/*
	 * TipoError.ERROR_SIN_FINANCIACIONES (vvvvvvv)
	 * 
	 * @return ItemMensajeRespuesta
	 */
	@Override
	public ItemMensajeRespuesta obtenerItemErrorSinUltimoResumen() {
		String mensaje = obtenerMensajeErrorSinUltimoResumen();
		return generarItemErrorSinUltimoResumen(mensaje);
	}

	/**
	 * Generar item error carga ultimos consumos.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the item mensaje respuesta
	 */
	private ItemMensajeRespuesta generarItemErrorCargaUltimosConsumos(String mensaje) {
		return generarItemMensajeRespuesta(CONSUMO_TARJETA_DEFAULT, TipoError.ERROR_CARGA_ULTIMOS_CONSUMOS, mensaje);
	}

	/**
	 * Generar item error sin consumos.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the item mensaje respuesta
	 */
	private ItemMensajeRespuesta generarItemErrorSinConsumos(String mensaje) {
		return generarItemMensajeRespuesta(CONSUMO_TARJETA_DEFAULT, TipoError.ERROR_SIN_CONSUMOS, mensaje);
	}

	/**
	 * Generar item error consumos pendientes.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the item mensaje respuesta
	 */
	private ItemMensajeRespuesta generarItemErrorConsumosPendientes(String mensaje) {
		return generarItemMensajeRespuesta(CONSUMOS_PENDIENTES, TipoError.ERROR_CONSUMOS_PENDIENTES, mensaje);
	}

	/**
	 * Generar item error carga ultimo resumen.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the item mensaje respuesta
	 */
	private ItemMensajeRespuesta generarItemErrorCargaUltimoResumen(String mensaje) {
		return generarItemMensajeRespuesta(ULTIMO_RESUMEN, TipoError.ERROR_CARGA_ULTIMO_RESUMEN, mensaje);
	}

	/**
	 * Generar item error sin ultimo resumen.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the item mensaje respuesta
	 */
	private ItemMensajeRespuesta generarItemErrorSinUltimoResumen(String mensaje) {
		return generarItemMensajeRespuesta(ULTIMO_RESUMEN, TipoError.ERROR_SIN_ULTIMO_RESUMEN, mensaje);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Generar dato item error generico.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the dato item mensaje
	 */
	/*
	 * TipoError.ERROR_CONSULTA_MOVIMIENTOS (00003)
	 */
	public DatoItemMensaje generarDatoItemErrorGenerico(String codigoMensaje) {
		String tag = String.valueOf(0);
		return respuestaFactory.generarDatoItemMensaje(TAG_TARJETA + tag + TAG_TARJETA_CIERRE, TipoError.ERROR_GENERICO,
				codigoMensaje);
	}

	/**
	 * Generar item error consulta movimiento.
	 *
	 * @param i
	 *            the i
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the dato item mensaje
	 */
	/*
	 * TipoError.ERROR_CONSULTA_MOVIMIENTOS (1067)
	 */
	public DatoItemMensaje generarItemErrorConsultaMovimiento(Integer i, String codigoMensaje) {
		String tag = String.valueOf(i);
		return respuestaFactory.generarDatoItemMensaje(TAG_TARJETA + tag + TAG_TARJETA_CIERRE,
				TipoError.ERROR_CONSULTA_MOVIMIENTOS, codigoMensaje);
	}

	/**
	 * Genera dato item con informacion para el error de disponibles y consumos
	 * (error total).
	 *
	 * @param i
	 *            the i
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the dato item mensaje
	 */
	public DatoItemMensaje generarItemErrorTotal(Integer i, String codigoMensaje) {
		String tag = String.valueOf(i);
		return respuestaFactory.generarDatoItemMensaje(TAG_TARJETA + tag + TAG_TARJETA_CIERRE, TipoError.ERROR_GENERICO,
				codigoMensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerDatoItemMensaje(ar.com.santanderrio.obp.servicios.tarjetas.
	 * entities.ResumenTarjeta, java.lang.Integer)
	 */
	/*
	 * Obtiene el item mensaje respuesta, para un resumenTarjeta con error o
	 * warnning . Items para: ERROR_CONSULTA_MOVIMIENTOS (1067), WARNING (1068)
	 * 
	 */
	@Override
	public DatoItemMensaje obtenerDatoItemMensaje(ResumenTarjetaDTO resumenTarjeta, Integer i) {
		if (resumenTarjeta.getErrorDisponibles() && resumenTarjeta.getErrorConsumos()) {
			return obtenerDatoItemErrorTotal(i);
		}
		if (resumenTarjeta.getErrorDisponibles()) {
			return obtenerDatoItemErrorConsultaMovimientos(i);
		}
		if (resumenTarjeta.getErrorConsumos()) {
			return obtenerDatoItemWarningAlConsultarConsumos(i);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerMensajeErrorAlConsultarConsumos()
	 */
	/*
	 * CODIGO_ERROR_CARGA_ULTIMOS_CONSUMOS (1068)
	 * 
	 * @return
	 */
	@Override
	public String obtenerMensajeErrorAlConsultarConsumos() {
		return buscarMensaje(TarjetaBOUtils.getCodigoErrorAlConsultarConsumos());
	}

	/**
	 * Obtener mensaje error actualizar favorito tarjeta.
	 *
	 * @return the string
	 */
	private String obtenerMensajeErrorActualizarFavoritoTarjeta() {
		return buscarMensaje(TarjetaBOUtils.getCodigoErrorAlModificarFavorito());
	}

	/**
	 * Obtener mensaje error actualizar alias.
	 *
	 * @return the string
	 */
	private String obtenerMensajeErrorActualizarAlias() {
		return buscarMensaje(TarjetaBOUtils.getCodigoErrorAlModificarAlias());
	}

	/**
	 * Obtener mensaje error carga ultimos consumos.
	 *
	 * @return the string
	 */
	private String obtenerMensajeErrorCargaUltimosConsumos() {
		return new String();/** buscarMensaje(TarjetaBOUtils.getCodigoErrorGrillaConsumos()); */
	}

	/**
	 * Obtener mensaje error sin consumos.
	 *
	 * @return the string
	 */
	private String obtenerMensajeErrorSinConsumos() {
		return buscarMensaje(TarjetaBOUtils.getCodigoErrorSinConsumos());
	}

	/**
	 * Obtener mensaje error carga consumos pendientes confirmacion.
	 *
	 * @return the string
	 */
	private String obtenerMensajeErrorCargaConsumosPendientesConfirmacion() {
		return buscarMensaje(TarjetaBOUtils.getCodigoErrorConsumosPendientesConfirmacion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerTooltipNoFavorito()
	 */
	/*
	 * TipoError.CODIGO_TOOLTIP_FAVORITO (1078)
	 */
	@Override
	public String obtenerTooltipNoFavorito() {
		return buscarMensaje(TarjetaBOUtils.getCodigoTooltipFavorito());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta#
	 * obtenerTooltipFavorito()
	 */
	/*
	 * TipoError.CODIGO_TOOLTIP_NO_FAVORITO (1079)
	 */
	@Override
	public String obtenerTooltipFavorito() {
		return buscarMensaje(TarjetaBOUtils.getCodigoTooltipNoFavorito());
	}

	/**
	 * Obtener mensaje error carga ultimo resumen.
	 *
	 * @return the string
	 */
	private String obtenerMensajeErrorCargaUltimoResumen() {
		return buscarMensaje(TarjetaBOUtils.getCodigoErrorCargaUltimoResumen());
	}

	/**
	 * Obtener mensaje error sin ultimo resumen.
	 *
	 * @return the string
	 */
	private String obtenerMensajeErrorSinUltimoResumen() {
		return buscarMensaje(TarjetaBOUtils.getCodigoErrorSinUltimoResumen());
	}

	/**
	 * Buscar mensaje.
	 *
	 * @param codigoError
	 *            the codigo error
	 * @return the string
	 */
	private String buscarMensaje(String codigoError) {

		Mensaje msg = mensajeDAO.obtenerMensajePorCodigo(codigoError);

		return msg.getMensaje();
	}

	/**
	 * Generar item mensaje respuesta.
	 *
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param mensaje
	 *            the mensaje
	 * @return the item mensaje respuesta
	 */
	/*
	 * Genera ItemMensajeRespuesta, obteniendo desde el mensajeDAO el mensaje de
	 * error que se carga en el item
	 */
	private ItemMensajeRespuesta generarItemMensajeRespuesta(String tag, TipoError tipoError, String mensaje) {
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setTipoError(tipoError.getDescripcion());
		itemMensajeRespuesta.setTag(tag);
		itemMensajeRespuesta.setMensaje(mensaje);
		return itemMensajeRespuesta;
	}

}
