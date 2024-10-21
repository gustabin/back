/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import java.util.List;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;

/**
 * @author florencia.n.martinez
 *
 */
public class ConsultaAgendaDestinatarioOutEntityMock {

    public ConsultaAgendaDestinatarioOutEntity completarInfoConCtaPropiaEnPesos(List<DestinatarioEntity> destinatarios) {
        ConsultaAgendaDestinatarioOutEntity outEntity = new ConsultaAgendaDestinatarioOutEntity();
        outEntity.setDestinatarios(destinatarios);
        outEntity.setCantidadRegistros(Long.valueOf(outEntity.getDestinatarios().size()));
        outEntity.setCodigoRetornoExtendido("00000000");
        outEntity.setErrorRellamada(Boolean.FALSE);
        return outEntity;
    }
    
}
