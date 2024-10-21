/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

/**
 * The Interface ComprobantesPMCUnificadoBO.
 */
public interface ComprobantesPMCUnificadoBO {

    /**
     * Obtiene los comprobantes de pago mis cuentas de manera asincronica con fecha.
     *
     * @param cliente
     *            the cliente
     * @param transaccion
     *            the transaccion
     * @return the future
     */
    Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCAsync(Cliente cliente, TransaccionDTO transaccion);

    /**
     * Obtener comprobantes PMC por empresa.
     *
     * @param cliente
     *            the cliente
     * @param transaccion
     *            the transaccion
     * @return the respuesta
     */
    Respuesta<ComprobantesDTO> obtenerComprobantesPMCPorEmpresa(Cliente cliente, TransaccionDTO transaccion);

    /**
     * Obtener comprobantes PMC por empresa async.
     *
     * @param cliente
     *            the cliente
     * @param transaccion
     *            the transaccion
     * @return the future
     */
    Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCPorEmpresaAsync(Cliente cliente,
            TransaccionDTO transaccion);

    /**
     * Obtiene los comprobantes de pago mis cuentas de manera asincronica con fecha.
     *
     * @param cliente
     *            the cliente
     * @param transaccion
     *            the transaccion
     * @return the future
     */
    Respuesta<ComprobantesDTO> obtenerComprobantesPMC(Cliente cliente, TransaccionDTO transaccion);
}
