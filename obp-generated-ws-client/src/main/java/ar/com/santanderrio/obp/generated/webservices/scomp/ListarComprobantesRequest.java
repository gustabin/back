/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.scomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.santanderrio.obp.generated.webservices.scomp.dominio.ScomServicioNombreEnum;

/**
 * Request para las operaciones de lista de comprobantes.
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlRootElement(name = "consulta")
@XmlType(name = "", propOrder = { "nombre", "version", "canal", "subcanal", "filtros", "parametros" })
@XmlAccessorType(XmlAccessType.FIELD)
public class ListarComprobantesRequest {

    /** The nombre. */
    @XmlElement(required = true)
    protected ScomServicioNombreEnum nombre;

    /** The version. */
    @XmlElement
    protected String version;

    /** The canal. */
    @XmlElement
    protected String canal;

    /** The subcanal. */
    @XmlElement
    protected String subcanal;

    /** The filtros. */
    @XmlElement(required = true)
    protected Filtros filtros;

    /** The parametros. */
    @XmlElement(required = true)
    protected Parametros parametros;

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public ScomServicioNombreEnum getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(ScomServicioNombreEnum nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets the canal.
     *
     * @return the canal
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the canal.
     *
     * @param canal
     *            the canal to set
     */
    public void setCanal(String canal) {
        this.canal = canal;
    }

    /**
     * Gets the subcanal.
     *
     * @return the subcanal
     */
    public String getSubcanal() {
        return subcanal;
    }

    /**
     * Sets the subcanal.
     *
     * @param subcanal
     *            the subcanal to set
     */
    public void setSubcanal(String subcanal) {
        this.subcanal = subcanal;
    }

    /**
     * Gets the filtros.
     *
     * @return the filtros
     */
    public Filtros getFiltros() {
        return filtros;
    }

    /**
     * Sets the filtros.
     *
     * @param filtros
     *            the filtros to set
     */
    public void setFiltros(Filtros filtros) {
        this.filtros = filtros;
    }

    /**
     * Gets the parametros.
     *
     * @return the parametros
     */
    public Parametros getParametros() {
        return parametros;
    }

    /**
     * Sets the parametros.
     *
     * @param parametros
     *            the parametros to set
     */
    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }
}
