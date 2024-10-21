/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PrismaTimeOutException;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoMultipleDTO;

/**
 * The Interface PagoMisCuentasDAO.
 *
 * @author dante.omar.olmedo
 * @lastUpdate emilio.watemberg - Jan 17, 2017: pago multiple.
 */
public interface PagoMisCuentasDAO {

    /**
	 * Invocar pago mis cuentas.
	 *
	 * @param informacionOperacionMultiple
	 *            the informacion operacion multiple
	 * @param cliente
	 *            the cliente
	 * @return the pago PMC
	 * @throws DAOException
	 *             the DAO exception
	 * @throws PrismaTimeOutException
	 *             the prisma time out exception
	 */
    PagoPMC invocarPagoMisCuentas(List<PagoInEntity> informacionOperacionMultiple, Cliente cliente) throws DAOException, PrismaTimeOutException;

    /**
     * Ejecutar pago multiple.
     *
     * @param pagos
     *            the pagos
     * @param cliente
     *            the cliente
     * @return the pago multiple DTO
     * @throws DAOException
     *             the DAO exception
     */
    PagoMultipleDTO ejecutarPagoMultiple(List<PagoInEntity> pagos, Cliente cliente) throws DAOException;

    /**
     * Invocar PMC con tarjeta credito.
     *
     * @param informacionPago
     *            the informacion pago
     * @param cliente
     *            the cliente
     * @return the pago PMC
     * @throws DAOException
     *             the DAO exception
     */
    PagoPMC invocarPMCConTarjetaCredito(List<PagoInEntity> informacionPago, Cliente cliente) throws DAOException;
}
