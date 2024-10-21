/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.view;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * TrackingTarjetasView.
 *
 * @author Silvina_Luque
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class TrackingTarjetasView {
    
    /** Tarjetas. */
    private List<DatosTrackingTarjetaView> tarjetas = new ArrayList<DatosTrackingTarjetaView>();

    /**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
    public List<DatosTrackingTarjetaView> getTarjetas() {
        return tarjetas;
    }

    /**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
    public void setTarjetas(List<DatosTrackingTarjetaView> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
    /**
	 * Adds the datos tarjeta view.
	 *
	 * @param datosTarjeta
	 *            the datos tarjeta
	 */
    public void addDatosTarjetaView(DatosTrackingTarjetaView datosTarjeta) {
        this.tarjetas.add(datosTarjeta);
        
    }

}
