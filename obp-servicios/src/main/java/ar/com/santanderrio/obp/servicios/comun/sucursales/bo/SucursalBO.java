/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.bo;

/**
 * @author sergio.e.goldentair
 *
 */
public interface SucursalBO {
    /**
     * Obtener las sucursales desde iatx y actualizar el xml con las mismas en el
     * filesystem.
     */
    void actualizarSucursales();
}
