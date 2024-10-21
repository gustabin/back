package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType
public class TransferenciaPMCVEP {
    /** The empresa. */
    @XmlElement(name = "Empresa")
    protected String empresa;
    
    /** The moneda. */
    @XmlElement(name = "Moneda")
    protected String moneda;
    
    /** The importe. */
    @XmlElement(name = "Importe")
    protected String importe;
    
    /** The fecha hora pago. */
    @XmlElement(name = "FechaHoraPago")
    protected String fechaHoraPago;
    
    /** The fecha vencimiento. */
    @XmlElement(name = "FechaVencimiento")
    protected String fechaVencimiento;
    
    /** The identificacion. */
    @XmlElement(name = "Identificacion")
    protected String identificacion;
    
    /** The identificacion2. */
    @XmlElement(name = "Identificacion2")
    protected String identificacion2;
    
    /** The numero vep. */
    @XmlElement(name = "NumeroVep")
    protected String numeroVep;
    
    /** The periodo. */
    @XmlElement(name = "Periodo")
    protected String periodo;
    
    /** The anticipo cuota. */
    @XmlElement(name = "AnticipoCuota")
    protected String anticipoCuota;    
    
    /** The tipo cuenta debito. */
    @XmlElement(name = "TipoCuentaDebito")
    protected String tipoCuentaDebito;
    
    /** The sucursal cuenta debito. */
    @XmlElement(name = "SucursalCuentaDebito")
    protected String sucursalCuentaDebito;
    
    /** The numero cuenta debito. */
    @XmlElement(name = "NumeroCuentaDebito")
    protected String numeroCuentaDebito;
    
    /** The tipo tarjeta credito. */
    @XmlElement(name = "TipoTarjetaCredito")
    protected String tipoTarjetaCredito;

    /** The numero tarjeta credito. */
    @XmlElement(name = "NumeroTarjetaCredito")
    protected String numeroTarjetaCredito;
    
    /** The num control. */
    @XmlElement(name = "NumControl")
    protected String numControl;
    
    /** The nro comprobante. */
    @XmlElement(name = "NroComprobante")
    protected String nroComprobante;

    /**
     * Gets the empresa.
     *
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Sets the empresa.
     *
     * @param empresa the new empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Gets the moneda.
     *
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Sets the moneda.
     *
     * @param moneda the new moneda
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * Gets the importe.
     *
     * @return the importe
     */
    public String getImporte() {
        return importe;
    }

    /**
     * Sets the importe.
     *
     * @param importe the new importe
     */
    public void setImporte(String importe) {
        this.importe = importe;
    }

    /**
     * Gets the identificacion.
     *
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Sets the identificacion.
     *
     * @param identificacion the new identificacion
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Gets the fecha hora pago.
     *
     * @return the fecha hora pago
     */
    public String getFechaHoraPago() {
        return fechaHoraPago;
    }

    /**
     * Sets the fecha hora pago.
     *
     * @param fechaHoraPago the new fecha hora pago
     */
    public void setFechaHoraPago(String fechaHoraPago) {
        this.fechaHoraPago = fechaHoraPago;
    }

    /**
     * Gets the fecha vencimiento.
     *
     * @return the fecha vencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the fecha vencimiento.
     *
     * @param fechaVencimiento the new fecha vencimiento
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Gets the tipo cuenta debito.
     *
     * @return the tipo cuenta debito
     */
    public String getTipoCuentaDebito() {
        return tipoCuentaDebito;
    }

    /**
     * Sets the tipo cuenta debito.
     *
     * @param tipoCuentaDebito the new tipo cuenta debito
     */
    public void setTipoCuentaDebito(String tipoCuentaDebito) {
        this.tipoCuentaDebito = tipoCuentaDebito;
    }

    /**
     * Gets the sucursal cuenta debito.
     *
     * @return the sucursal cuenta debito
     */
    public String getSucursalCuentaDebito() {
        return sucursalCuentaDebito;
    }

    /**
     * Sets the sucursal cuenta debito.
     *
     * @param sucursalCuentaDebito the new sucursal cuenta debito
     */
    public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
        this.sucursalCuentaDebito = sucursalCuentaDebito;
    }

    /**
     * Gets the numero cuenta debito.
     *
     * @return the numero cuenta debito
     */
    public String getNumeroCuentaDebito() {
        return numeroCuentaDebito;
    }

    /**
     * Sets the numero cuenta debito.
     *
     * @param numeroCuentaDebito the new numero cuenta debito
     */
    public void setNumeroCuentaDebito(String numeroCuentaDebito) {
        this.numeroCuentaDebito = numeroCuentaDebito;
    }

    /**
     * Gets the num control.
     *
     * @return the num control
     */
    public String getNumControl() {
        return numControl;
    }

    /**
     * Sets the num control.
     *
     * @param numControl the new num control
     */
    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    /**
     * Gets the nro comprobante.
     *
     * @return the nro comprobante
     */
    public String getNroComprobante() {
        return nroComprobante;
    }

    /**
     * Sets the nro comprobante.
     *
     * @param nroComprobante the new nro comprobante
     */
    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public String getIdentificacion2() {
        return identificacion2;
    }

    public void setIdentificacion2(String identificacion2) {
        this.identificacion2 = identificacion2;
    }

    public String getNumeroVep() {
        return numeroVep;
    }

    public void setNumeroVep(String numeroVep) {
        this.numeroVep = numeroVep;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getAnticipoCuota() {
        return anticipoCuota;
    }

    public void setAnticipoCuota(String anticipoCuota) {
        this.anticipoCuota = anticipoCuota;
    }

	/**
	 * @return the tipoTarjetaCredito
	 */
	public String getTipoTarjetaCredito() {
		return tipoTarjetaCredito;
	}

	/**
	 * @param tipoTarjetaCredito the tipoTarjetaCredito to set
	 */
	public void setTipoTarjetaCredito(String tipoTarjetaCredito) {
		this.tipoTarjetaCredito = tipoTarjetaCredito;
	}

	/**
	 * @return the numeroTarjetaCredito
	 */
	public String getNumeroTarjetaCredito() {
		return numeroTarjetaCredito;
	}

	/**
	 * @param numeroTarjetaCredito the numeroTarjetaCredito to set
	 */
	public void setNumeroTarjetaCredito(String numeroTarjetaCredito) {
		this.numeroTarjetaCredito = numeroTarjetaCredito;
	}
    
    
}
