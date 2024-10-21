/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoTC;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaPago;

/**
 * The Interface PagoTarjetaCreditoBO.
 */
public interface PagoTarjetaCreditoBO extends BusinessObject {

    /**
     * Realiza el pago de una tarjeta de credito, en el dia de la fecha.
     *
     * @param datosPagoTC
     *            datos del pago
     * @param cliente
     *            cliente que va a realizar el pago
     * @return the respuesta
     */
    Respuesta<ComprobantePagoTarjeta> pagar(DatosPagoTC datosPagoTC, Cliente cliente);

    /**
     * Programa el pago de una tarjeta de credito.
     *
     * @param datosPagoTC
     *            datos del pago
     * @param cliente
     *            cliente que va a realizar el pago
     * @return the respuesta
     */
    Respuesta<ComprobantePagoTarjeta> programarPago(DatosPagoTC datosPagoTC, Cliente cliente);

    /**
     * Devuelve la lista de debitos automaticos para una tarjeta.
     *
     * @param cliente
     *            the cliente
     * @param tarjetaActiva
     *            the tarjeta activa
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    List<DebitoAutomatico> obtenerDeudasConDebitoAutomatico(Cliente cliente, Cuenta tarjetaActiva)
            throws BusinessException;

    /**
     * Obtener datos de pago correspondiente a una tarjeta.
     *
     * @param numeroTarjeta
     *            the numero tarjeta
     * @param fecha
     *            the fecha
     * @param tipoDePago
     *            the tipo de pago
     * @return the detalle tarjeta pago
     * @throws BusinessException
     *             the business exception
     */
    DetalleTarjetaPago obtenerDetalleTarjetaPago(String numeroTarjeta, Date fecha, TipoDePagoEnum tipoDePago)
            throws BusinessException;

    /**
     * Obtener datos tarjeta pago.
     *
     * @param numeroTarjeta the numero tarjeta
     * @return the datos tarjeta
     * @throws BusinessException the business exception
     */
    DatosTarjeta obtenerDatosTarjetaPago(String numeroTarjeta) throws BusinessException;

    /**
     * Ejecutar baja pago programado de tarjeta credito. ver: DTF: 10303 iatx:
     * CNSTJCPAGP
     *
     * @author B041299 Manuel.Vargas
     * @param pagoPendiente
     *            the pago pendiente
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    Respuesta<ResultadoTransaccion> ejecutarBajaPagoProgramadoDeTarjetaCredito(PagoPendiente pagoPendiente,
            Cliente cliente);

    /**
	 * Actualizar stop debit.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pagosPendientes
	 *            the pagos pendientes
	 */
    void actualizarStopDebit(Cliente cliente, List<PagoPendiente> pagosPendientes);

}
