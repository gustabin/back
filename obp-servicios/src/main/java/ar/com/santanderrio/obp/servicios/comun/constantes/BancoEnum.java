/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.constantes;

/**
 * Enum para nombres de bancos.
 *
 * @author florencia.n.martinez
 */
public enum BancoEnum {

    /** The banco Santander. */
    SANTANDER_RIO("Santander");

    /** The descripcion. */
    String descripcion;

    /**
     * Instantiates a new banco enum.
     *
     * @param descripcion
     *            the descripcion
     */
    BancoEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

}