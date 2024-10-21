/*
 *
 */
package ar.com.santanderrio.obp.servicios.nuevopago.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.*;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaRSADTO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionRecargaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaConfiguracionView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
import ar.com.santanderrio.obp.servicios.seguros.entities.GastoProtegido;
import ar.com.santanderrio.obp.servicios.seguros.entities.Poliza;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;

/**
 * The Interface NuevoPagoManager.
 */
public interface NuevoPagoManager {

    /**
     * Obtener facturas.
     *
     * @param medioPagoView
     *            the medio pago view
     * @return the respuesta
     */
    Respuesta<MedioPagoSelectionView> obtenerFacturas(MedioPagoView medioPagoView);

    /**
     * Obtiene la lista de cuentas del cliente pasado como parametro.
     *
     * @param consulta
     *            the consulta
     * @return the respuesta
     */
    Respuesta<MedioPagoSelectionView> obtenerCuentas(ConsultaConfiguracionView consulta);

    /**
     * Valida el cuit pasado por parametro, si la respuesta no es ok retorna un
     * mensaje de bd.
     *
     * @param cuit
     *            the cuit
     * @return the respuesta
     */
    Respuesta<String> validarCuit(String cuit);

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
     * Realiza el pago de una lista elementos a pagar. Devuelve una lista con la
     * informacion del resultado de cada pago
     *
     * @param nuevoPago
     *            the nuevo pago
     * @return Respuesta<DetalleLoteDatos>
     */
    Respuesta<NuevoPago> pagoPuntual(NuevoPago nuevoPago);

    /**
     * Realiza la solicitud de pago/recarga de la lista de calendario.
     *
     * @param pagoPendienteView
     *            the nuevo pago
     */
    void grabarEstadisticaPagoPuntual(PagoPendienteView pagoPendienteView);

    /**
     * Estadistica comprobante nuevo pago.
     *
     * @param seleccion
     *            the seleccion
     */
    void estadisticaComprobanteNuevoPago(CuentaSeleccionView seleccion);

    /**
     * Estadistica Nueva recarga – Ingreso N° identificador – Acceso.
     *
     * @param puntoDeAcceso
     *            the punto de acceso
     * @return the respuesta
     */
    Respuesta<Void> grabarEstadisticaRecargaIngresoNumero(PuntoDeAccesoView puntoDeAcceso);

    /**
     * Obtiene la lista de cuentas e importes para realizar una recarga de telefono
     * o sube.
     *
     * @param consultaConfiguracionRecargaView
     *            the consulta configuracion recarga view
     * @return the respuesta
     */
    Respuesta<ConfiguracionRecargaView> obtenerConfiguracionRecarga(
            ConsultaConfiguracionView consultaConfiguracionRecargaView);

    /**
     * Verificar factores autenticacion.
     *
     * @return the respuesta
     */
    Respuesta<Boolean> verificarFactoresAutenticacion();

    /**
     * Descargar comprobante PDF.
     *
     * @param nuevoPagoView
     *            the fecha vencimiento
     * @return the respuesta
     */
    Respuesta<ReporteView> descargarComprobantePDF(NuevoPago nuevoPagoView);

    /**
     * Continuar Pago – Carga en sesion el hash del pago.
     *
     * @param nuevoPago
     *            the nuevo pago
     * @return the respuesta
     */
    Respuesta<Void> continuarPago(NuevoPago nuevoPago);

    Respuesta<EmisionGastosProtegidosView> emitirGastosProtegidos(EmisionOfertaIntegrada nuevaEmisioView);

    Respuesta<GastoProtegidoView> consultarGastosProtegidos(GastoProtegido gastoProtegido);

    Respuesta<FlagCompraProtegidaView> getFlagCompraProtegida();

    Respuesta<Poliza> consultarPolizaExistente(String tokenJwt, String ramo);

	void cargarUltimosDiasDesdeCambioDeClaveToken(NuevaRecargaRSADTO nuevaRecargaRSADTO);
}
