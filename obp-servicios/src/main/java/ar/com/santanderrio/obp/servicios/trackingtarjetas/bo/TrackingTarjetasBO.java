/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dto.TrackingPiezaDTO;

/**
 * TrackingTarjetasBO.
 *
 * @author Silvina_Luque
 */
public interface TrackingTarjetasBO {

    /**
	 * obtenerTrackingTarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<List<TrackingPiezaDTO>> obtenerTrackingTarjetas(Cliente cliente) ;



}
