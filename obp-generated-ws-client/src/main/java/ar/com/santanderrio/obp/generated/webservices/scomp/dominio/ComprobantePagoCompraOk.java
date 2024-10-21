//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.28 at 06:20:31 PM ART 
//

package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{}Canal"/>
 *         &lt;element ref="{}SubCanal"/>
 *         &lt;element ref="{}TpoComprobante"/>
 *         &lt;element ref="{}SubTpoComprobante"/>
 *         &lt;element ref="{}EstadoOper"/>
 *         &lt;element ref="{}Cliente"/>
 *         &lt;element ref="{}NombreServicio"/>
 *         &lt;element ref="{}NombreEmpresa"/>
 *         &lt;element ref="{}IdEmpresa"/>
 *         &lt;element ref="{}CodProdAcuerdoEmp"/>
 *         &lt;element ref="{}NroInstanciaAcuerdoEmp"/>
 *         &lt;element ref="{}Identificacion"/>
 *         &lt;element ref="{}FechaOper"/>
 *         &lt;element ref="{}ImporteTotalDebito"/>
 *         &lt;element ref="{}NroBoleta"/>
 *         &lt;element ref="{}DescCuenta"/>
 *         &lt;element ref="{}TipoCuentaDeb"/>
 *         &lt;element ref="{}SucCtaDeb"/>
 *         &lt;element ref="{}NroCtaDeb"/>
 *         &lt;element ref="{}MonedaCtaDeb"/>
 *         &lt;element ref="{}Pagos"/>
 *         &lt;element ref="{}NroComprobante"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlType
public class ComprobantePagoCompraOk extends Comprobante {
    @XmlElement(name = "EstadoOper")
    protected OperacionEstado estadoOper;
    @XmlElement(name = "Cliente")
    protected Cliente cliente;
    @XmlElement(name = "NombreServicio")
    protected String nombreServicio;
    @XmlElement(name = "NombreEmpresa")
    protected String nombreEmpresa;
    @XmlElement(name = "IdEmpresa")
    protected String idEmpresa;
    @XmlElement(name = "CodProdAcuerdoEmp")
    protected String codProdAcuerdoEmp;
    @XmlElement(name = "NroInstanciaAcuerdoEmp")
    protected String nroInstanciaAcuerdoEmp;
    @XmlElement(name = "Identificacion")
    protected String identificacion;
    @XmlElement(name = "FechaOper")
    protected String fechaOper;
    @XmlElement(name = "ImporteTotalDebito")
    protected String importeTotalDebito;
    @XmlElement(name = "NroBoleta")
    protected String nroBoleta;
    @XmlElement(name = "DescCuenta")
    protected String descCuenta;
    @XmlElement(name = "TipoCuentaDeb")
    protected String tipoCuentaDeb;
    @XmlElement(name = "SucCtaDeb")
    protected String sucCtaDeb;
    @XmlElement(name = "NroCtaDeb")
    protected String nroCtaDeb;
    @XmlElement(name = "MonedaCtaDeb")
    protected String monedaCtaDeb;
    @XmlElement(name = "Pagos")
    protected Pagos pagos;
    @XmlElement(name = "NroComprobante")
    protected String nroComprobante;

    /**
     * Gets the value of the estadoOper property.
     * 
     * @return possible object is {@link OperacionEstado }
     * 
     */
    public OperacionEstado getEstadoOper() {
        return estadoOper;
    }

    /**
     * Sets the value of the estadoOper property.
     * 
     * @param value
     *            allowed object is {@link OperacionEstado }
     * 
     */
    public void setEstadoOper(OperacionEstado value) {
        this.estadoOper = value;
    }

    /**
     * Gets the value of the cliente property.
     * 
     * @return possible object is {@link Cliente }
     * 
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *            allowed object is {@link Cliente }
     * 
     */
    public void setCliente(Cliente value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the nombreServicio property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNombreServicio() {
        return nombreServicio;
    }

    /**
     * Sets the value of the nombreServicio property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNombreServicio(String value) {
        this.nombreServicio = value;
    }

    /**
     * Gets the value of the nombreEmpresa property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Sets the value of the nombreEmpresa property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNombreEmpresa(String value) {
        this.nombreEmpresa = value;
    }

    /**
     * Gets the value of the idEmpresa property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Sets the value of the idEmpresa property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setIdEmpresa(String value) {
        this.idEmpresa = value;
    }

    /**
     * Gets the value of the codProdAcuerdoEmp property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodProdAcuerdoEmp() {
        return codProdAcuerdoEmp;
    }

    /**
     * Sets the value of the codProdAcuerdoEmp property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setCodProdAcuerdoEmp(String value) {
        this.codProdAcuerdoEmp = value;
    }

    /**
     * Gets the value of the nroInstanciaAcuerdoEmp property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNroInstanciaAcuerdoEmp() {
        return nroInstanciaAcuerdoEmp;
    }

    /**
     * Sets the value of the nroInstanciaAcuerdoEmp property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNroInstanciaAcuerdoEmp(String value) {
        this.nroInstanciaAcuerdoEmp = value;
    }

    /**
     * Gets the value of the identificacion property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Sets the value of the identificacion property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setIdentificacion(String value) {
        this.identificacion = value;
    }

    /**
     * Gets the value of the fechaOper property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getFechaOper() {
        return fechaOper;
    }

    /**
     * Sets the value of the fechaOper property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setFechaOper(String value) {
        this.fechaOper = value;
    }

    /**
     * Gets the value of the importeTotalDebito property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getImporteTotalDebito() {
        return importeTotalDebito;
    }

    /**
     * Sets the value of the importeTotalDebito property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setImporteTotalDebito(String value) {
        this.importeTotalDebito = value;
    }

    /**
     * Gets the value of the nroBoleta property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNroBoleta() {
        return nroBoleta;
    }

    /**
     * Sets the value of the nroBoleta property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNroBoleta(String value) {
        this.nroBoleta = value;
    }

    /**
     * Gets the value of the descCuenta property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescCuenta() {
        return descCuenta;
    }

    /**
     * Sets the value of the descCuenta property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDescCuenta(String value) {
        this.descCuenta = value;
    }

    /**
     * Gets the value of the tipoCuentaDeb property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTipoCuentaDeb() {
        return tipoCuentaDeb;
    }

    /**
     * Sets the value of the tipoCuentaDeb property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setTipoCuentaDeb(String value) {
        this.tipoCuentaDeb = value;
    }

    /**
     * Gets the value of the sucCtaDeb property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSucCtaDeb() {
        return sucCtaDeb;
    }

    /**
     * Sets the value of the sucCtaDeb property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setSucCtaDeb(String value) {
        this.sucCtaDeb = value;
    }

    /**
     * Gets the value of the nroCtaDeb property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNroCtaDeb() {
        return nroCtaDeb;
    }

    /**
     * Sets the value of the nroCtaDeb property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNroCtaDeb(String value) {
        this.nroCtaDeb = value;
    }

    /**
     * Gets the value of the monedaCtaDeb property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getMonedaCtaDeb() {
        return monedaCtaDeb;
    }

    /**
     * Sets the value of the monedaCtaDeb property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setMonedaCtaDeb(String value) {
        this.monedaCtaDeb = value;
    }

    /**
     * Gets the value of the pagos property.
     * 
     * @return possible object is {@link Pagos }
     * 
     */
    public Pagos getPagos() {
        return pagos;
    }

    /**
     * Sets the value of the pagos property.
     * 
     * @param value
     *            allowed object is {@link Pagos }
     * 
     */
    public void setPagos(Pagos value) {
        this.pagos = value;
    }

    /**
     * Gets the value of the nroComprobante property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNroComprobante() {
        return nroComprobante;
    }

    /**
     * Sets the value of the nroComprobante property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNroComprobante(String value) {
        this.nroComprobante = value;
    }

}
