/*
 * 
 */
package ar.com.santanderrio.obp.servicios.simuladorprestamo.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.view.DestinoPrestamoSeleccionView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;

/**
 * The Interface DestinoPrestamoDAO.
 */
public interface DestinoPrestamoDAO {

	/**
	 * Obtener.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DestinoPrestamo> obtener() throws DAOException;

	DestinoPrestamo obtenerPorProductoSubproductoDestino(String producto, String subproducto, String destinoFondo);

	DestinoPrestamoSeleccionView obtenerViewPorProductoSubproductoDestino(String producto, String subproducto, String destinoFondo);

	/**
	 * Vaciar destinos prestamo.
	 */
	void vaciarDestinosPrestamo();

}
