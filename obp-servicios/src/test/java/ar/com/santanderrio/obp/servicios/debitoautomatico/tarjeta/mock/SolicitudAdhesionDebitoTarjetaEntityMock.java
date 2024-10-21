/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.SolicitudAdhesionDebitoTarjetaEntity;

/**
 * The Class SolicitudAdhesionDebitoTarjetaEntityMock.
 *
 * @author florencia.n.martinez
 */
public class SolicitudAdhesionDebitoTarjetaEntityMock {

    /**
     * Completa info SolicitudAdhesionDebitoTarjetaEntity.
     *
     * @return the solicitud adhesion debito tarjeta entity
     */
    public static SolicitudAdhesionDebitoTarjetaEntity completarInfo() {
        SolicitudAdhesionDebitoTarjetaEntity inEntity = new SolicitudAdhesionDebitoTarjetaEntity();
        inEntity.setIdSolicitud(BigDecimal.valueOf(3363));
        return inEntity;
    }
}
