/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.ConsultaDatosTarjetaDebitoEntity;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.ModificarLimiteDebitoEntity;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteDescargaCambioLimiteView;

/**
 * The Interface ModifLimiteDebitoDAO.
 */
public interface ModifLimiteDebitoDAO {

	/**
	 * Gets the clase tarjeta banelco.
	 *
	 * @param sucursal
	 *            the sucursal
	 * @param numTarjeta
	 *            the num tarjeta
	 * @param cliente
	 *            the cliente
	 * @return the clase tarjeta banelco
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaDatosTarjetaDebitoEntity getClaseTarjetaBanelco(String sucursal, String numTarjeta, Cliente cliente)
			throws DAOException;

	/**
	 * Modificar limites extraccion.
	 *
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<ResultadoTransaccion> modificarLimitesExtraccion(ModificarLimiteDebitoEntity entity) throws DAOException;

	/**
	 * Modificar limites extraccion.
	 *
	 * @param entity
	 *            the entity
	 * @param ip
	 *            the ip
	 * @param userAgent
	 *            the userAgent
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<ResultadoTransaccion> modificarLimitesExtraccion(ModificarLimiteDebitoEntity entity, String ip, String userAgent) throws DAOException;

	/**
	 * Comprobante modif limites extraccion.
	 *
	 * @param comprobanteView
	 *            the comprobante view
	 * @return the reporte
	 */
	Reporte comprobanteModifLimitesExtraccion(ComprobanteDescargaCambioLimiteView comprobanteView);

}
