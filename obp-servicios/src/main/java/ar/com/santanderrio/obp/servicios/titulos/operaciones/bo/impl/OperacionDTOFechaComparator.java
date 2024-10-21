/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.impl;

import java.util.Comparator;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.titulos.operaciones.dto.OperacionTitulosDTO;

/**
 * The Class OperacionDTOFechaComparator.
 */
public class OperacionDTOFechaComparator implements Comparator<OperacionTitulosDTO> {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(OperacionTitulosDTO operacion1, OperacionTitulosDTO operacion2) {
        Date fecha1 = operacion1.getFecha();
        Date fecha2 = operacion2.getFecha();
        if (fecha1 == null || fecha2 == null)
            return 0;
        return operacion2.getFecha().compareTo(operacion1.getFecha());
    }

}
