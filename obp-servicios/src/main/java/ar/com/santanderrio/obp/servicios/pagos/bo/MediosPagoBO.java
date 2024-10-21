/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Interface MediosPagoBO.
 *
 * @author B039636
 */
public interface MediosPagoBO extends BusinessObject {

    /**
     * Gets the by codigo.
     *
     * @param empresa
     *            the empresa
     * @return the by codigo
     */
    Respuesta<MedioPago> getByCodigo(String empresa);

    /**
     * Gets the by cuit servicio.
     *
     * @param cuit
     *            the cuit
     * @param servicio
     *            the servicio
     * @return the by cuit servicio
     */
    Respuesta<MedioPago> getByCuitServicio(String cuit, String servicio);

    /**
     * Obtener por codigo.
     *
     * @param codigo
     *            the codigo
     * @return the medio pago
     */
    MedioPago obtenerPorCodigo(String codigo);

    /**
     * Obtener por nombre fantasia.
     *
     * @param termino
     *            the termino
     * @return the medio pago
     */
    MedioPago obtenerPorNombreFantasia(String termino);

    /**
     * Obtener por cod establecimiento.
     *
     * @param codigo
     *            the codigo
     * @return the medio pago
     */
    MedioPago obtenerPorCodEstablecimiento(String codigo);

    /**
     * Obtener por nombre fantasia exacto.
     *
     * @param termino
     *            the termino
     * @return the medio pago
     */
    MedioPago obtenerPorNombreFantasiaExactoTarjeta(String termino);

    /**
     * Obtiene el tipoDePagoEnum que corresponde al medio de pago.
     *
     * @param medioPago
     *            the medio pago
     * @return the tipo medio pago BO
     */
    TipoMedioPagoBO obtenerTipoMedioPago(MedioPago medioPago);

    /**
     * Obtener medio pago por codigo.
     *
     * @param codigo
     *            the codigo
     * @return the medio pago
     */
    MedioPago obtenerMedioPagoPorCodigo(String codigo);

    /**
     * Obtener tipo nuevo pago enum.
     *
     * @param medioPago
     *            the medio pago
     * @return the tipo nuevo pago enum
     */
    TipoNuevoPagoEnum obtenerTipoNuevoPagoEnum(MedioPago medioPago);

    /**
     * Obtener empresa por id acuerdo compras.
     *
     * @param termino
     *            the termino
     * @return the medio pago
     */
    MedioPago obtenerEmpresaPorIdAcuerdoCompras(String termino);

    MedioPago obtenerPorNombreFantasiaPagoAutomatico(String termino);
}
