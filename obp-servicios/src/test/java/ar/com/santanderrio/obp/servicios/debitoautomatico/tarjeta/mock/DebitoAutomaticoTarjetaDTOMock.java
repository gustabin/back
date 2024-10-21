/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock;

import java.util.Date;

import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DebitoAutomaticoTarjetaDTO;

/**
 * @author florencia.n.martinez
 *
 */
public class DebitoAutomaticoTarjetaDTOMock {

    public static DebitoAutomaticoTarjetaDTO completarInfo() {
        DebitoAutomaticoTarjetaDTO dto = new DebitoAutomaticoTarjetaDTO();
        dto.setComprobante("3363");
        dto.setFecha(new Date());
        dto.setMensajeFeedback("<p>La solicitud de adhesión a débito automático de <b>Telecom</b> en tarjeta de crédito <b>VISA XXXX - 1234</b> se realizó con éxito.</p>");
        return dto;
    }

}
