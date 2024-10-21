package ar.com.santanderrio.obp.servicios.prestamos.view;

import ar.com.santanderrio.obp.servicios.comun.estadistica.web.view.EstadisticaView;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FeedbackCancelacionView implements EstadisticaView {

    private String resultadoOperacion;

    @Override
    public Estadistica cargarEstadistica() {
        return new Estadistica(EstadisticasConstants.CANCELACION_PRESTAMO_FEEDBACK,
                "OK".equals(resultadoOperacion) ? "1" : "2" );
    }

    public String getResultadoOperacion() {
        return resultadoOperacion;
    }

    public void setResultadoOperacion(String resultadoOperacion) {
        this.resultadoOperacion = resultadoOperacion;
    }
}
