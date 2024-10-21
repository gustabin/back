package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The class PagoDebin
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType
public class PagoDebin {

    @XmlElement(name = "TipoCuenta")
    protected String tipoDeCuenta;

    @XmlElement(name = "NumeroCuenta")
    protected String numeroCuenta;

    @XmlElement(name = "ImporteDebitado")
    protected String importeDebitado;

    @XmlElement(name = "FechaSolicitud")
    protected String fechaSolicitado;
    
    @XmlElement(name = "FechaVencimiento")
    protected String fechaVencimiento;

    @XmlElement(name = "AliasCuenta")
    protected String aliasCuenta;

    @XmlElement(name = "Solicitante")
    protected String solicitante;

    @XmlElement(name = "IdSolicitante")
    protected String idSolicitante;

    @XmlElement(name = "CBU")
    protected String cbu;

    @XmlElement(name = "Alias")
    protected String alias;

    @XmlElement(name = "Descripcion")
    protected String descripcion;

    @XmlElement(name = "Concepto")
    protected String concepto;

    @XmlElement(name = "IdDebin")
    protected String idDebin;

    @XmlElement(name = "NroComprobante")
    protected String nroComprobante;

	/**
	 * @return the tipoDeCuenta
	 */
	public String getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	/**
	 * @param tipoDeCuenta the tipoDeCuenta to set
	 */
	public void setTipoDeCuenta(String tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the importeDebitado
	 */
	public String getImporteDebitado() {
		return importeDebitado;
	}

	/**
	 * @param importeDebitado the importeDebitado to set
	 */
	public void setImporteDebitado(String importeDebitado) {
		this.importeDebitado = importeDebitado;
	}

	/**
	 * @return the fechaSolicitado
	 */
	public String getFechaSolicitado() {
		return fechaSolicitado;
	}

	/**
	 * @param fechaSolicitado the fechaSolicitado to set
	 */
	public void setFechaSolicitado(String fechaSolicitado) {
		this.fechaSolicitado = fechaSolicitado;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the aliasCuenta
	 */
	public String getAliasCuenta() {
		return aliasCuenta;
	}

	/**
	 * @param aliasCuenta the aliasCuenta to set
	 */
	public void setAliasCuenta(String aliasCuenta) {
		this.aliasCuenta = aliasCuenta;
	}

	/**
	 * @return the solicitante
	 */
	public String getSolicitante() {
		return solicitante;
	}

	/**
	 * @param solicitante the solicitante to set
	 */
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	/**
	 * @return the idSolicitante
	 */
	public String getIdSolicitante() {
		return idSolicitante;
	}

	/**
	 * @param idSolicitante the idSolicitante to set
	 */
	public void setIdSolicitante(String idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	/**
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * @param cbu the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the idDebin
	 */
	public String getIdDebin() {
		return idDebin;
	}

	/**
	 * @param idDebin the idDebin to set
	 */
	public void setIdDebin(String idDebin) {
		this.idDebin = idDebin;
	}

	/**
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * @param nroComprobante the nroComprobante to set
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	
}
