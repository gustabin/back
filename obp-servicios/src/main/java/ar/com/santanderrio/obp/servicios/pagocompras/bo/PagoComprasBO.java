/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasInDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasOutDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoComprasMultiple;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Interface PagoComprasBO.
 */
public interface PagoComprasBO {

    /**
     * Obtener deudas.
     *
     * @param cliente
     *            the cliente
     * @param medioPago
     *            the medio pago
     * @param identificacion
     *            the identificacion
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    List<DeudaPagoComprasEntity> obtenerDeudas(Cliente cliente, MedioPago medioPago, String identificacion)
            throws BusinessException;

    /**
     * Obtener cuentas.
     *
     * @param cliente
     *            the cliente
     * @param divisa
     *            the divisa
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    List<Cuenta> obtenerCuentas(Cliente cliente, DivisaEnum divisa) throws BusinessException;

    /**
     * Obtener divisa.
     *
     * @param medioPago
     *            the medio pago
     * @return the divisa enum
     */
    DivisaEnum obtenerDivisa(MedioPago medioPago);

    /**
     * Obtener divisa.
     *
     * @param moneda
     *            the moneda
     * @return the divisa enum
     */
    DivisaEnum obtenerDivisa(String moneda);

    /**
     * Ejecuta el pago de compras sin deuda.
     *
     * @param cliente
     *            the cliente
     * @param inDTO
     *            the in DTO
     * @return the pago compras out DTO
     * @throws BusinessException
     *             the business exception
     */
    PagoComprasOutDTO ejecutarPagoComprasSinDeuda(Cliente cliente, PagoComprasInDTO inDTO) throws BusinessException;

    /**
     * Ejecutar pago compras con deuda.
     *
     * @param cliente
     *            the cliente
     * @param pagoCompras
     *            the pago compras
     * @param medioPago
     *            the medio pago
     * @param cuenta
     *            the cuenta
     * @param divisa
     *            the divisa
     * @return the pago compras out DTO
     * @throws BusinessException
     *             the business exception
     */
    PagoComprasOutDTO ejecutarPagoComprasConDeuda(Cliente cliente, NuevoPago pagoCompras, MedioPago medioPago,
            Cuenta cuenta, DivisaEnum divisa) throws BusinessException;

    /**
     * Realizar adhesion.
     *
     * @param cliente
     *            the cliente
     * @param medioPago
     *            the medio pago
     * @param identificacion
     *            the identificacion
     */
    void realizarAdhesion(Cliente cliente, MedioPago medioPago, String identificacion);

    PagoComprasOutDTO ejecutarPagoComprasConDeuda(Cliente cliente, PagoComprasMultiple pagoCompras, MedioPago medioPago,
            DivisaEnum divisa) throws BusinessException;

}
