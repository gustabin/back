/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.banelco.bo.BanelcoBO;

/**
 * The Class BanelcoDesafio.
 */
@Component
public class BanelcoDesafio extends Desafio<AutentificacionDTO> {

    /** The banelco BO. */
    @Autowired
    private BanelcoBO banelcoBO;

    /**
     * solicita los datos de autenticacion.
     *
     * @return Respuesta<AutentificacionDTO>
     */
    @Override
    public Respuesta<AutentificacionDTO> solicitar() {
        return banelcoBO.obtenerUltimosDigitosValidacion();
    }

    /**
     * ejecuta autientificacion.
     *
     * @param autentificacionDTO
     *            the auntentificacion DTO
     * @return Respuesta<AutentificacionDTO>
     */
    @Override
    public Respuesta<AutentificacionDTO> ejecutar(AutentificacionDTO autentificacionDTO) {
        return banelcoBO.validarOchoDigitos(autentificacionDTO, super.codigoEstadisticaValidacion);
    }

    /**
     * logica de comparacion.
     *
     * @param o
     *            the o
     * @return the int
     */
    @Override
    public int compareTo(Desafio<AutentificacionDTO> o) {
        return Comparators.PRIORIDAD.compare(this, o);
    }

}
