/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.mock;

import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteCriterioDTO;

/**
 * The Class DatosSolicitanteCriterioDTOMock.
 *
 * @author florencia.n.martinez
 */
public class DatosSolicitanteCriterioDTOMock {

    /**
     * Completar datos solicitante criterio DTO.
     *
     * @return the datos solicitante criterio DTO
     */
    public static DatosSolicitanteCriterioDTO completarDatosSolicitanteCriterioDTO() {
        DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO = new DatosSolicitanteCriterioDTO();
        datosSolicitanteCriterioDTO.setDocTipo("DNI");
        datosSolicitanteCriterioDTO.setDoc("30882323");
        return datosSolicitanteCriterioDTO;
    }
}
