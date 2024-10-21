/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.mock;

import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaOutDTO;

/**
 * The Class NuevaRecargaOutDTOMock.
 *
 * @author florencia.n.martinez
 */
public class NuevaRecargaOutDTOMock {

    /**
     * Completa la informacion del objeto NuevaRecargaOutDTO OK para prueba
     * unitaria.
     *
     * @return the nueva recarga out DTO
     */
    public static NuevaRecargaOutDTO completarInfo(Boolean respuestaOK, TipoError tipoError) {
        NuevaRecargaOutDTO nuevaRecargaOutDTO = new NuevaRecargaOutDTO();
        nuevaRecargaOutDTO.setRespuestaOK(respuestaOK);
        nuevaRecargaOutDTO.setTipoError(tipoError);
        nuevaRecargaOutDTO.setNumeroControl("00");
        nuevaRecargaOutDTO.setNroComprobante("12345");
        return nuevaRecargaOutDTO;
    }
}
