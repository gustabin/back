/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.mock;

import ar.com.santanderrio.obp.servicios.prestamos.entity.CuotaPrestamoEntity;

/**
 * The Class CuotaPrestamoEntityMock.
 *
 * @author florencia.n.martinez
 */
public class CuotaPrestamoEntityMock {

    /**
     * Completar info.
     *
     * @return the cuota prestamo entity
     */
    public static CuotaPrestamoEntity completarInfo() {
        CuotaPrestamoEntity cuotaPrestamoEntity = new CuotaPrestamoEntity();
        cuotaPrestamoEntity.setCftna("512345567");
        cuotaPrestamoEntity.setTae("645378946");
        return cuotaPrestamoEntity;
    }
}
