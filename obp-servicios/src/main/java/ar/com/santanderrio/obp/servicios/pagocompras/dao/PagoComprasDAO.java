/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasInDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudasPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraConDeudaEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraConDeudaInEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraSinDeudaEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Interface PagoComprasDAO.
 */
public interface PagoComprasDAO {

    /**
     * Obtener lista deudas.
     *
     * @param cliente
     *            the cliente
     * @param medioPago
     *            the medio pago
     * @param identificacion
     *            the identificacion
     * @return the deudas pago compras entity
     * @throws DAOException
     *             the DAO exception
     */
    DeudasPagoComprasEntity obtenerListaDeudas(Cliente cliente, MedioPago medioPago, String identificacion)
            throws DAOException;

    /**
     * Ejecuta el pago de compras sin deuda.
     *
     * @param cliente
     *            the cliente
     * @param inDTO
     *            the in DTO
     * @return the pago PC entity
     * @throws DAOException
     *             the DAO exception
     */
    PagoCompraSinDeudaEntity ejecutarPagoComprasSinDeuda(Cliente cliente, PagoComprasInDTO inDTO) throws DAOException;

    /**
     * Ejecuta el pago de compras con deuda.
     *
     * @param cliente
     *            the cliente
     * @param inEntity
     *            the in entity
     * @return the pago compra con deuda entity
     * @throws DAOException
     *             the DAO exception
     */
    PagoCompraConDeudaEntity ejecutarPagoComprasConDeuda(Cliente cliente, PagoCompraConDeudaInEntity inEntity)
            throws DAOException;

    /**
     * Tiene adhesion.
     *
     * @param cliente
     *            the cliente
     * @param medioPago
     *            the medio pago
     * @return true, if successful
     * @throws DAOException
     *             the DAO exception
     */
    boolean tieneAdhesion(Cliente cliente, MedioPago medioPago) throws DAOException;

    /**
     * Alta adhesion.
     *
     * @param cliente
     *            the cliente
     * @param medioPago
     *            the medio pago
     * @param identificacion
     *            the identificacion
     */
    void altaAdhesion(Cliente cliente, MedioPago medioPago, String identificacion);

}
