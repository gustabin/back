/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoOutEntity;

/**
 * Acceso al servicio para retornar los comprobantes por cada empresa adherida
 * retornada en el servicio CNSDDIADHG 110.
 * 
 * @author dante.omar.olmedo
 *
 */
public interface ConsultaDebitoDirectoDAO {

	/**
	 * Realiza la consulta llamando al servicio de IATX correspondiente.
	 *
	 * @param inEntity
	 *            the inEntity
	 * @return the consulta comprobante out entity
	 */
	ComprobanteDebitoAutomaticoOutEntity consultar(ComprobanteDebitoAutomaticoInEntity inEntity);

	/**
	 * Realiza la consulta de manera asincronica.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the future
	 */
	Future<ComprobanteDebitoAutomaticoOutEntity> consultarAsync(ComprobanteDebitoAutomaticoInEntity inEntity);

}
