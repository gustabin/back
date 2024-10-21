/**
 * 
 */
package ar.com.santanderrio.obp.servicios.scomp.client.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class Filtros.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "tpoComp", "subTpoComp", "estadoOper", "fecha", "nup", "tpoDoc", "nroDoc",
        "idEmpServ", "idMedioPago", "idClienteEmp", "nomEmpServ", "categoriasList" })
public class Filtros {

    /** The tpo comp. */
    @XmlElement(name = "tpo-comp")
    protected String tpoComp;

    /** The sub tpo comp. */
    @XmlElement(name = "sub-tpo-comp")
    protected String subTpoComp;

    /** The estado oper. */
    @XmlElement(name = "estado-oper")
    protected String estadoOper;

    /** The fecha. */
    @XmlElement
    protected Fecha fecha;

    /** The nup. */
    @XmlElement
    protected String nup;

    /** The tpo doc. */
    @XmlElement(name = "tpo-doc")
    protected String tpoDoc;

    /** The nro doc. */
    @XmlElement(name = "nro-doc")
    protected String nroDoc;

    /** The id emp serv. */
    @XmlElement(name = "id-emp-serv")
    protected String idEmpServ;

    /** The id medio pago. */
    @XmlElement(name = "id-medio-pago")
    protected String idMedioPago;

    /** The id cliente emp. */
    @XmlElement(name = "id-cliente-emp")
    protected String idClienteEmp;

    /** The nom emp serv. */
    @XmlElement(name = "nom-emp-serv")
    protected NomEmpServ nomEmpServ;

    /** The categorias list. */
    @XmlElementWrapper(name = "Categorias")
    @XmlElement(name = "Categoria")
    protected List<Categoria> categoriasList;

    /**
     * Gets the tpo comp.
     *
     * @return the tpoComp
     */
    public String getTpoComp() {
        return tpoComp;
    }

    /**
     * Sets the tpo comp.
     *
     * @param tpoComp
     *            the tpoComp to set
     */
    public void setTpoComp(String tpoComp) {
        this.tpoComp = tpoComp;
    }

    /**
     * Gets the sub tpo comp.
     *
     * @return the subTpoComp
     */
    public String getSubTpoComp() {
        return subTpoComp;
    }

    /**
     * Sets the sub tpo comp.
     *
     * @param subTpoComp
     *            the subTpoComp to set
     */
    public void setSubTpoComp(String subTpoComp) {
        this.subTpoComp = subTpoComp;
    }

    /**
     * Gets the estado oper.
     *
     * @return the estadoOper
     */
    public String getEstadoOper() {
        return estadoOper;
    }

    /**
     * Sets the estado oper.
     *
     * @param estadoOper
     *            the estadoOper to set
     */
    public void setEstadoOper(String estadoOper) {
        this.estadoOper = estadoOper;
    }

    /**
     * Gets the fecha.
     *
     * @return the fecha
     */
    public Fecha getFecha() {
        return fecha;
    }

    /**
     * Sets the fecha.
     *
     * @param fecha
     *            the fecha to set
     */
    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets the nup.
     *
     * @return the nup
     */
    public String getNup() {
        return nup;
    }

    /**
     * Sets the nup.
     *
     * @param nup
     *            the nup to set
     */
    public void setNup(String nup) {
        this.nup = nup;
    }

    /**
     * Gets the tpo doc.
     *
     * @return the tpoDoc
     */
    public String getTpoDoc() {
        return tpoDoc;
    }

    /**
     * Sets the tpo doc.
     *
     * @param tpoDoc
     *            the tpoDoc to set
     */
    public void setTpoDoc(String tpoDoc) {
        this.tpoDoc = tpoDoc;
    }

    /**
     * Gets the nro doc.
     *
     * @return the nroDoc
     */
    public String getNroDoc() {
        return nroDoc;
    }

    /**
     * Sets the nro doc.
     *
     * @param nroDoc
     *            the nroDoc to set
     */
    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    /**
     * Gets the id emp serv.
     *
     * @return the idEmpServ
     */
    public String getIdEmpServ() {
        return idEmpServ;
    }

    /**
     * Sets the id emp serv.
     *
     * @param idEmpServ
     *            the idEmpServ to set
     */
    public void setIdEmpServ(String idEmpServ) {
        this.idEmpServ = idEmpServ;
    }

    /**
     * Gets the id medio pago.
     *
     * @return the idMedioPago
     */
    public String getIdMedioPago() {
        return idMedioPago;
    }

    /**
     * Sets the id medio pago.
     *
     * @param idMedioPago
     *            the idMedioPago to set
     */
    public void setIdMedioPago(String idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    /**
     * Gets the id cliente emp.
     *
     * @return the idClienteEmp
     */
    public String getIdClienteEmp() {
        return idClienteEmp;
    }

    /**
     * Sets the id cliente emp.
     *
     * @param idClienteEmp
     *            the idClienteEmp to set
     */
    public void setIdClienteEmp(String idClienteEmp) {
        this.idClienteEmp = idClienteEmp;
    }

    /**
     * Gets the nom emp serv.
     *
     * @return the nomEmpServ
     */
    public NomEmpServ getNomEmpServ() {
        return nomEmpServ;
    }

    /**
     * Sets the nom emp serv.
     *
     * @param nomEmpServ
     *            the nomEmpServ to set
     */
    public void setNomEmpServ(NomEmpServ nomEmpServ) {
        this.nomEmpServ = nomEmpServ;
    }

    /**
     * Gets the categorias list.
     *
     * @return the categoriasList
     */
    public List<Categoria> getCategoriasList() {
        return categoriasList;
    }

    /**
     * Sets the categorias list.
     *
     * @param categoriasList
     *            the categoriasList to set
     */
    public void setCategoriasList(List<Categoria> categoriasList) {
        this.categoriasList = categoriasList;
    }

}
