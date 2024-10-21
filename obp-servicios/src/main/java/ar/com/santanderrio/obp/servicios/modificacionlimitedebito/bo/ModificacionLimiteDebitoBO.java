/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dto.ConsultaDatosTarjetaDebitoDTO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dto.LimitesExtraccionDebitoDTO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteDescargaCambioLimiteView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteModificacionLimiteDebitoView;

/**
 * The Interface ModificacionLimiteDebitoBO.
 */
public interface ModificacionLimiteDebitoBO {

	/**
	 * Gets the clase tarjeta debito.
	 *
	 * @param sucursal
	 *            the sucursal
	 * @param numTarjeta
	 *            the num tarjeta
	 * @param cliente
	 *            the cliente
	 * @return the clase tarjeta debito
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ConsultaDatosTarjetaDebitoDTO> getClaseTarjetaDebito(String sucursal, String numTarjeta, Cliente cliente)
			throws BusinessException;

	/**
	 * Gets the limites archivo.
	 *
	 * @param clase
	 *            the clase
	 * @return the limites archivo
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<LimitesExtraccionDebitoDTO> getLimitesArchivo(String clase) throws BusinessException;

	/**
	 * Modificar limites extraccion.
	 *
	 * @param comprobanteModificacionLimiteDebitoView
	 *            the comprobante modificacion limite debito view
	 * @param numTarjeta
	 *            the num tarjeta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ResultadoTransaccion> modificarLimitesExtraccion(
			ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView, String numTarjeta,
			Cliente cliente) throws BusinessException;

	/**
	 * Modificar limites extraccion.
	 *
	 * @param comprobanteModificacionLimiteDebitoView
	 *            the comprobante modificacion limite debito view
	 * @param numTarjeta
	 *            the num tarjeta
	 * @param cliente
	 *            the cliente
	 * @param ip
	 *            the ip
	 * @param userAgent
	 *            the userAgent
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ResultadoTransaccion> modificarLimitesExtraccion(
			ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView, String numTarjeta,
			Cliente cliente, String ip, String userAgent) throws BusinessException;

	/**
	 * Comprobante modif limites extraccion.
	 *
	 * @param comprobanteView
	 *            the comprobante view
	 * @return the respuesta
	 */
	Respuesta<Reporte> comprobanteModifLimitesExtraccion(ComprobanteDescargaCambioLimiteView comprobanteView);

}
