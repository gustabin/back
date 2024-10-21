/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.ImporteCuotaHipotecarioUVaException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.NoTieneFondosSuficientesException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PagoConAnterioridadException;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Interface PagoPrestamoDAO.
 */
public interface PagoPrestamoDAO {

	/**
	 * Pagar.
	 *
	 * @param prestamo
	 *            the prestamo
	 * @param cuenta
	 *            the cuenta
	 * @return the comprobante prestamo
	 * @throws PagoConAnterioridadException
	 *             the pago con anterioridad exception
	 * @throws NoTieneFondosSuficientesException
	 *             the no tiene fondos suficientes exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ImporteCuotaHipotecarioUVaException 
	 */
	ComprobantePrestamo pagar(Prestamo prestamo, Cuenta cuenta)
			throws PagoConAnterioridadException, NoTieneFondosSuficientesException, DAOException, ImporteCuotaHipotecarioUVaException;

}
