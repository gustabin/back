/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosComprobanteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CancelarPrestamoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CancelarPrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosCanceladosOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteCancelacionTotalPrestamoView;

/**
 * The Interface PrestamoDAO.
 *
 * @author florencia.n.martinez
 */
public interface PrestamoDAO {

	/**
	 * Obtiene la simulacion o la solicitud de un prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the simulador prestamo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	PrestamoOutEntity simularAdquirir(Cliente cliente, PrestamoInEntity inEntity) throws DAOException;

	/**
	 * Obtener cancelados.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the prestamos cancelados out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	PrestamosCanceladosOutEntity obtenerCancelados(Cliente cliente) throws DAOException;
	
	CancelarPrestamoOutEntity consultarServicioCancelarPrestamos(Cliente cliente, CancelarPrestamoInEntity inEntity) throws DAOException;

	Reporte generarComprobantePDF(ComprobanteCancelacionTotalPrestamoView datosComprobante); 
	
	boolean getIsPrestamosCOM12123(String numeroPrestamo) throws DAOException;
	
}