/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.TransaccionEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;

/**
 * The Interface CoordinadorComprobantesBO.
 * 
 * @author sabrina.cis
 */
public interface CoordinadorComprobantesBO {

    /**
     * Obtener comprobantes.
     *
     * @param cliente
     *            the cliente
     * @param dto
     *            the dto
     * @return the respuesta
     */
    Respuesta<ComprobantesDTO> obtenerComprobantes(Cliente cliente, FiltroComprobanteDTO dto);

    /**
     * Limpiar cache.
     * 
     * @param cliente
     *            the cliente
     */
    void limpiarCache(Cliente cliente);

    /**
     * Recibe el total de comprobantes y filtra por pagina y dispositivo.
     * 
     * @param pagina
     *            the pagina
     * @param isMobile
     *            the is mobile
     * @param comprobantes
     *            the comprobantes
     * @return the list
     */
    List<ComprobanteDTO> filtrar(Integer pagina, Boolean isMobile, List<ComprobanteDTO> comprobantes);

    /**
     * Hay mas comprobantes.
     * 
     * @param paginado
     *            the paginado
     * @param comprobantes
     *            the comprobantes
     * @param isMobile
     *            the is mobile
     * @param contadorPagina
     *            the contador pagina
     * @return the boolean
     */
    Boolean hayMasComprobantes(List<ComprobanteDTO> paginado, List<ComprobanteDTO> comprobantes, Boolean isMobile,
            Integer contadorPagina);

    TransaccionEnum solapaPorDefecto(Cliente cliente);

}
