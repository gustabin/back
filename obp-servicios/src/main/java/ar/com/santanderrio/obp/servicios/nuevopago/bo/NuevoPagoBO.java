/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaInDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaOutDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Factura;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Interface NuevoPagoBO.
 */
public interface NuevoPagoBO extends BusinessObject {

    /** The no perminte modificacion. */
    // constantes correspondientes a TipoImporte
    String NO_PERMINTE_MODIFICACION = "0";

    /** The modifica solo por importe igual o mayor. */
    String MODIFICA_SOLO_POR_IMPORTE_IGUAL_O_MAYOR = "1";

    /** The modifica por cualquier importe. */
    String MODIFICA_POR_CUALQUIER_IMPORTE = "2";

    /**
     * Obtiene facturas pendiente de pago por pesFiid codigo de empresa y codigo
     * pago electronico.
     *
     * @param cliente
     *            the cliente
     * @param medioPagoView
     *            the medio pago view
     * @return the respuesta
     */
    Respuesta<MedioPagoSelectionView> obtenerPagos(Cliente cliente, MedioPagoView medioPagoView);

    /**
     * Obtener cuentas.
     *
     * @param cliente
     *            the cliente
     * @return the medio pago selection view
     * @throws BusinessException
     *             the business exception
     */
    MedioPagoSelectionView obtenerCuentas(Cliente cliente) throws BusinessException;

    /**
     * Obtener facturas.
     *
     * @param cliente
     *            the cliente
     * @param nuevoPago
     *            the nuevo pago
     * @return the respuesta
     */
    Respuesta<List<Factura>> obtenerFacturas(Cliente cliente, NuevoPago nuevoPago);

    /**
     * Validar importe factura.
     *
     * @param cliente
     *            the cliente
     * @param nuevoPago
     *            the nuevo pago
     * @return the respuesta
     */
    Respuesta<Boolean> validarImporteFactura(Cliente cliente, NuevoPago nuevoPago);

    /**
     * Transforma la informacion validada del manager en un objeto de tipo
     * MultiPago, con la informacion que tenemos que enviar por el request.
     *
     * @param medioPago
     *            the medio pago
     * @param pagoMisCuentasDTO
     *            the nuevo pago
     * @param cliente
     *            the cliente
     * @return respuesta<DetalleLoteDatos>
     * @throws BusinessException
     *             the business exception
     */
    NuevaRecargaOutDTO pagar(MedioPago medioPago, PagoMisCuentasDTO pagoMisCuentasDTO, Cliente cliente)
            throws BusinessException;

    /**
     * Efectua el pago de una recarga.
     *
     * @param cliente
     *            the cliente
     * @param cuenta
     *            the cuenta
     * @param nuevaRecargaInDTO
     *            the nueva recarga in DTO
     * @return the nueva recarga out DTO
     * @throws BusinessException
     *             the business exception
     */
    NuevaRecargaOutDTO pagarRecarga(Cliente cliente, Cuenta cuenta, NuevaRecargaInDTO nuevaRecargaInDTO)
            throws BusinessException;

    /**
     * Validar cuit.
     *
     * @param cuit
     *            the cuit
     * @return the respuesta
     */
    Respuesta<String> validarCuit(String cuit);

    /**
	 * Checks if is formato codigo pago electronico valid.
	 *
	 * @param medioPagoView
	 *            the medio pago view
	 * @return the boolean
	 */
    Boolean isFormatoCodigoPagoElectronicoValid(MedioPagoView medioPagoView);

}
