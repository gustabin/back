package ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.dao;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.entity.ConfirmarRescateEspecieIn;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.entity.ConfirmarRescateEspecieOut;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.entity.ConsultarRescateEspecieIn;

public interface RescateFondosPorEspecieDAO {

    String consultarRescate(ConsultarRescateEspecieIn entity);

    /**
     * Notificar el rescate y obtener el numero de orden generado.
     * 
     * @param entity
     * @return
     */
    ConfirmarRescateEspecieOut confirmarRescate(ConfirmarRescateEspecieIn entity);
}
