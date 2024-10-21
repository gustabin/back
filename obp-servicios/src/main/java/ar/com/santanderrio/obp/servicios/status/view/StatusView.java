/**
 * 
 */
package ar.com.santanderrio.obp.servicios.status.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;

/**
 * Informacion que se le devuelve al cliente del estado del servidor.
 * 
 * @author sergio.e.goldentair
 *
 */
public class StatusView implements Serializable {
    /** The serialVersionUID. */
    private static final long serialVersionUID = -8984406486055155347L;

    /** The version enpaquetado. */
    private String versionEnpaquetado;

    /** The fecha distribucion. */
    private String fechaDistribucion;

    /** The servidor. */
    private String servidor;

    /** The bc. */
    private String bc;

    /** The base datos. */
    private String baseDatos;

    /** The iatx. */
    private String iatx;

    /** The pesSubempresas. */
    private String pesSubempresas;

    /** The props. */
    private Map<String, Object> props;

    /** The legales. */
    private Map<String, String> legales;

    /** The ModuloPermiso Map. */
    private Map<String, ModuloPermiso> mapModuloPermiso;

    /** The destinos prestamo. */
    private List<DestinoPrestamo> destinosPrestamo;

    /**
     * Gets the version enpaquetado.
     *
     * @return the versionEnpaquetado
     */
    public String getVersionEnpaquetado() {
        return versionEnpaquetado;
    }

    /**
     * Sets the version enpaquetado.
     *
     * @param versionEnpaquetado
     *            the versionEnpaquetado to set
     */
    public void setVersionEnpaquetado(String versionEnpaquetado) {
        this.versionEnpaquetado = versionEnpaquetado;
    }

    /**
     * Gets the fecha distribucion.
     *
     * @return the fechaDistribucion
     */
    public String getFechaDistribucion() {
        return fechaDistribucion;
    }

    /**
     * Sets the fecha distribucion.
     *
     * @param fechaDistribucion
     *            the fechaDistribucion to set
     */
    public void setFechaDistribucion(String fechaDistribucion) {
        this.fechaDistribucion = fechaDistribucion;
    }

    /**
     * Gets the servidor.
     *
     * @return the servidor
     */
    public String getServidor() {
        return servidor;
    }

    /**
     * Sets the servidor.
     *
     * @param servidor
     *            the servidor to set
     */
    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    /**
     * Gets the bc.
     *
     * @return the bc
     */
    public String getBc() {
        return bc;
    }

    /**
     * Sets the bc.
     *
     * @param bc
     *            the bc to set
     */
    public void setBc(String bc) {
        this.bc = bc;
    }

    /**
     * Gets the props.
     *
     * @return the props
     */
    public Map<String, Object> getProps() {
        return props;
    }

    /**
     * Sets the props.
     *
     * @param props
     *            the props to set
     */
    public void setProps(Map<String, Object> props) {
        this.props = props;
    }

    /**
     * Gets the legales.
     *
     * @return the legales
     */
    public Map<String, String> getLegales() {
        return legales;
    }

    /**
     * Sets the legales.
     *
     * @param legales
     *            the legales to set
     */
    public void setLegales(Map<String, String> legales) {
        this.legales = legales;
    }

    /**
     * Gets the destinos prestamo.
     *
     * @return the destinos prestamo
     */
    public List<DestinoPrestamo> getDestinosPrestamo() {
        return destinosPrestamo;
    }

    /**
     * Gets the map modulo permiso.
     *
     * @return the mapModuloPermiso
     */
    public Map<String, ModuloPermiso> getMapModuloPermiso() {
        return mapModuloPermiso;
    }

    /**
     * Sets the map modulo permiso.
     *
     * @param mapModuloPermiso
     *            the mapModuloPermiso to set
     */
    public void setMapModuloPermiso(Map<String, ModuloPermiso> mapModuloPermiso) {
        this.mapModuloPermiso = mapModuloPermiso;
    }

    /**
     * Sets the destinos prestamo.
     *
     * @param destinosPrestamo
     *            the new destinos prestamo
     */
    public void setDestinosPrestamo(List<DestinoPrestamo> destinosPrestamo) {
        this.destinosPrestamo = destinosPrestamo;
    }

    /**
     * Gets the pes subempresas.
     *
     * @return the pesSubempresas
     */
    public String getPesSubempresas() {
        return pesSubempresas;
    }

    /**
     * Sets the pes subempresas.
     *
     * @param pesSubempresas
     *            the pesSubempresas to set
     */
    public void setPesSubempresas(String pesSubempresas) {
        this.pesSubempresas = pesSubempresas;
    }

    /**
	 * Gets the base datos.
	 *
	 * @return the baseDatos
	 */
    public String getBaseDatos() {
        return baseDatos;
    }

    /**
	 * Sets the base datos.
	 *
	 * @param baseDatos
	 *            the baseDatos to set
	 */
    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    /**
	 * Gets the iatx.
	 *
	 * @return the iatx
	 */
    public String getIatx() {
        return iatx;
    }

    /**
	 * Sets the iatx.
	 *
	 * @param iatx
	 *            the iatx to set
	 */
    public void setIatx(String iatx) {
        this.iatx = iatx;
    }
}