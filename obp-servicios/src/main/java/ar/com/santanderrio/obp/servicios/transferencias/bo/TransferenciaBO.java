/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;

/**
 * The Interface TransferenciaBO.
 *
 * @author B039636
 */
public interface TransferenciaBO {

    /**
     * Adherir contrato transferencia.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    Respuesta<Boolean> adherirContratoTransferencia(Cliente cliente);

    /**
     * Checks if is cliente habilitado para transferir.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    Respuesta<Boolean> isClienteHabilitadoParaTransferir(Cliente cliente);

    /**
     * Validar origen destino transferencia.
     *
     * @param cliente
     *            the cliente
     * @param transferencia
     *            the transferencia
     * @param nroTarjetaActiva
     *            the nro tarjeta activa
     * @param monedaSeleccionada
     *            the moneda seleccionada
     * @param cuentaOrigenSeleccionada
     *            the cuenta origen seleccionada
     * @param userAgent
     *            the user agent
     * @return the respuesta
     */
    Respuesta<TransferenciaDTO> validarOrigenDestinoTransferencia(Cliente cliente, TransferenciaDTO transferencia,
            String nroTarjetaActiva, String monedaSeleccionada, Cuenta cuentaOrigenSeleccionada, String userAgent);

    /**
     * Realizar transferencia.
     *
     * @param cliente
     *            the cliente
     * @param transferencia
     *            the transferencia
     * @param isFromAgendaDestinatario
     *            the is from agenda destinatario
     * @param isProgramada
     *            the is programada
     * @return the respuesta
     */
    Respuesta<TransferenciaDTO> ejecutarTransferenciaInmediata(Cliente cliente, TransferenciaDTO transferencia,
            Boolean isFromAgendaDestinatario, Boolean isProgramada);

    /**
     * Generar comprobante transferencia.
     *
     * @param transferencia
     *            the transferencia
     * @return the respuesta
     */
    Respuesta<Reporte> generarComprobanteTransferencia(TransferenciaDTO transferencia);

    /**
     * Ejecutar transferencia programada.
     *
     * @param cliente
     *            the cliente
     * @param transferenciaDTO
     *            the transferencia DTO
     * @param isFromAgendaDestinatario
     *            the is from agenda destinatario
     * @param isProgramada
     *            the is programada
     * @return the respuesta
     */
    Respuesta<TransferenciaDTO> ejecutarTransferenciaProgramada(Cliente cliente, TransferenciaDTO transferenciaDTO,
            Boolean isFromAgendaDestinatario, Boolean isProgramada);

}
