/**
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

import java.util.Date;
import java.util.List;

/**
 * The Class TarjetaDelSocio.
 */
public class TarjetaDelSocio {

    /** The apellido nombre. */
    private String apellidoNombre;
    
    /** The ultimos cuatro. */
    private String ultimosCuatro;
    
    /** The condicion. */
    private String condicion;
    
    /** The producto. */
    private String producto;
    
    /** The fechas inhabilitadas. */
    private List<Date> fechasInhabilitadas;
    
    /** The numero. */
    private String numero;
    
    /** The alias. */
    private String alias;

    /** The prioridad. */
    private Integer prioridad;

    /** The nombre Ws. */
    private String nombreWs;

    /**
     * Instantiates a new tarjeta del socio.
     */
    public TarjetaDelSocio() {
        super();
        this.prioridad = 1;
    }

    /**
     * Gets the apellido nombre.
     *
     * @return the apellidoNombre
     */
    public String getApellidoNombre() {
        return apellidoNombre;
    }

    /**
     * Sets the apellido nombre.
     *
     * @param apellidoNombre            the apellidoNombre to set
     */
    public void setApellidoNombre(String apellidoNombre) {
        this.apellidoNombre = apellidoNombre;
    }

    /**
     * Gets the ultimos cuatro.
     *
     * @return the ultimosCuatro
     */
    public String getUltimosCuatro() {
        return ultimosCuatro;
    }

    /**
     * Sets the ultimos cuatro.
     *
     * @param ultimosCuatro            the ultimosCuatro to set
     */
    public void setUltimosCuatro(String ultimosCuatro) {
        this.ultimosCuatro = ultimosCuatro;
    }

    /**
     * Gets the condicion.
     *
     * @return the condicion
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * Sets the condicion.
     *
     * @param condicion            the condicion to set
     */
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    /**
     * Gets the producto.
     *
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Sets the producto.
     *
     * @param producto            the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * Gets the numero.
     *
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the numero.
     *
     * @param numero            the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Gets the alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias.
     *
     * @param alias            the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the fechas inhabilitadas.
     *
     * @return the fechas inhabilitadas
     */
    public List<Date> getFechasInhabilitadas() {
        return fechasInhabilitadas;
    }

    /**
     * Sets the fechas inhabilitadas.
     *
     * @param fechasInhabilitadas the new fechas inhabilitadas
     */
    public void setFechasInhabilitadas(List<Date> fechasInhabilitadas) {
        this.fechasInhabilitadas = fechasInhabilitadas;
    }

    /**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
    public Integer getPrioridad() {
        return prioridad;
    }

    /**
	 * Sets the prioridad.
	 *
	 * @param prioridad
	 *            the new prioridad
	 */
    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    /**
	 * Gets the producto prioridad.
	 *
	 * @return the producto prioridad
	 */
    public Integer getProductoPrioridad() {
        if("CREDITO".equalsIgnoreCase(producto)){
            return 0;
        }
        if("AMEX".equalsIgnoreCase(producto)){
            return 1;
        }
        return 2;
    }

    /**
	 * Gets the nombre ws.
	 *
	 * @return the nombreWs
	 */
    public String getNombreWs() {
        return nombreWs;
    }

    /**
	 * Sets the nombre ws.
	 *
	 * @param nombreWs
	 *            the nombreWs to set
	 */
    public void setNombreWs(String nombreWs) {
        this.nombreWs = nombreWs;
    }

}
