/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobanteRecargaTarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosRecargaTREntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.TarjetaRecargableEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView;

/**
 * The Interface RecargaTarjetaDAO.
 */
public interface RecargaTarjetaDAO {

	/**
	 * Recargar.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosRecargaTR
	 *            the datos recarga TR
	 * @return the comprobante recarga tarjeta entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ComprobanteRecargaTarjetaEntity recargar(Cliente cliente, DatosRecargaTREntity datosRecargaTR) throws DAOException;

	/**
	 * Programar recarga.
	 *
	 * @param cliente
	 *            the cliente
	 * @param trEntity
	 *            the tr entity
	 * @return the comprobante recarga tarjeta entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ServiceException
	 *             the service exception
	 */
	ComprobanteRecargaTarjetaEntity programarRecarga(Cliente cliente, TarjetaRecargableEntity trEntity)
			throws DAOException, ServiceException;

	/**
	 * Generar comprobante recarga.
	 *
	 * @param comprobanteRecargaTarjetaView
	 *            the comprobante recarga tarjeta view
	 * @return the reporte
	 */
	Reporte generarComprobanteRecarga(ComprobanteRecargaTarjetaView comprobanteRecargaTarjetaView);

	/**
	 * Generar comprobante recarga agendar.
	 *
	 * @param comprobanteRecargaTarjetaView
	 *            the comprobante recarga tarjeta view
	 * @return the reporte
	 */
	Reporte generarComprobanteRecargaAgendar(ComprobanteRecargaTarjetaEntity comprobanteRecargaTarjetaView);

	/**
	 * Generar comprobante recarga modificar agendar.
	 *
	 * @param comprobanteRecargaTarjetaView
	 *            the comprobante recarga tarjeta view
	 * @return the reporte
	 */
	Reporte generarComprobanteRecargaModificarAgendar(ComprobanteRecargaTarjetaEntity comprobanteRecargaTarjetaView);

	/**
	 * Generar comprobante recarga stop debit agendar.
	 *
	 * @param comprobanteRecargaTarjetaView
	 *            the comprobante recarga tarjeta view
	 * @return the reporte
	 */
	Reporte generarComprobanteRecargaStopDebitAgendar(ComprobanteRecargaTarjetaEntity comprobanteRecargaTarjetaView);
}
