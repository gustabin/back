/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.DatosAltaTagMonederoEntity;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosParaActivacionView;

/**
 * The Interface AltaTagMonederoBO.
 */
public interface AltaTagMonederoBO {

	/**
	 * Ejecutar alta tag monedero.
	 *
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaTagMonederoView> ejecutarAltaTagMonedero(DatosAltaTagMonederoEntity datosAltaTagMonedero,
			Cliente cliente);

	/**
	 * Generar comprobante alta tag monedero.
	 *
	 * @param comprobanteAltaTagMonederoView
	 *            the comprobante alta tag monedero view
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobanteAltaTagMonedero(ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView);

	/**
	 * Activar monedero tag.
	 *
	 * @param datosParaActivacionView
	 *            the datos para activacion view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ResultadoTransaccion> activarMonederoTag(DatosParaActivacionView datosParaActivacionView,
			Cliente cliente);

	/**
	 * Es persona habilitada.
	 *
	 * @param entity
	 *            the entity
	 * @return the consulta inhabilitados out entity
	 */
	ConsultaInhabilitadosOutEntity esPersonaHabilitada(ConsultaInhabilitadosInEntity entity);

	/**
	 * Obtener datos sucursal.
	 *
	 * @param consultaUnidadControlInEntity
	 *            the consulta unidad control in entity
	 * @return the consulta unidad control out entity
	 */
	ConsultaUnidadControlOutEntity obtenerDatosSucursal(ConsultaUnidadControlInEntity consultaUnidadControlInEntity);

	/**
	 * Generar comprobante activacion tag monedero.
	 *
	 * @param comprobanteActivacionTagMonederoView
	 *            the comprobante activacion tag monedero view
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobanteActivacionTagMonedero(
			ComprobanteActivacionTagMonederoView comprobanteActivacionTagMonederoView);

}
