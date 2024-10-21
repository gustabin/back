/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo.SoftTokenBO;

/**
 * Esta clase la implementa {@link Auntentificacion} para validaciones de token.
 *
 * @author ignacio.valek
 * @author emilio.watemberg
 * @since Sep 22, 2016.
 */
@Component
public class TokenDesafio extends Desafio<AutentificacionDTO> {

    /** The soft token BO. */
    @Autowired
    private SoftTokenBO softTokenBO;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio#
     * solicitar()
     */
    @Override
    public Respuesta<AutentificacionDTO> solicitar() {
        return softTokenBO.tieneSoftTokenHabilitado();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio#
     * ejecutar(java.lang.Object)
     */
    @Override
    public Respuesta<AutentificacionDTO> ejecutar(AutentificacionDTO autentificacionDTO) {
        return softTokenBO.validarSoftToken(autentificacionDTO, super.codigoEstadisticaValidacion);

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Desafio<AutentificacionDTO> o) {
        return Comparators.PRIORIDAD.compare(this, o);
    }

}
