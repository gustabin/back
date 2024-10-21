/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionImpuestosPlazoFijoEntity;

/**
 * The Class AltaPlazoFijoDTO.
 */
public class AltaComprobantePlazoFijoDTO {
    
    /** The fecha alta real. */
    private String fechaAltaReal;
    
    /** The plazo. */
    private String plazo;
    
    /** The cotizacionCodigoUr. */
    private BigDecimal cotizacionCodigoUr;
    
    /** The saldoInicUr. */
    private BigDecimal saldoInicUr;
    
    /** The interes. */
    private BigDecimal intereses;
    
    /** The fecha vencimiento. */
    private String fechaVencimiento;
    
    /** The entidad cuenta plazo. */
    private String entidadCuentaPlazo;

    /** The numero cuenta plazo. */
    private String numeroCuentaPlazo;

    /** The secuencia. */
    private String secuencia;
    
    /** The importe certificado. */
    private BigDecimal importeCertificado;
    
    /** The nro cuenta debito. */
    private String nroCuentaDebito;
    
    /** The codigo plazo. */
    private String codigoPlazo;
    
    /** The descripcion accion vencimiento. */
    private String descripcionAccionVencimiento;

    /** The moneda. */
    private String moneda;
    
    /** The tasa nominal. */
    private String tasaNominal;
    
    /** The tasa variable. */
    private BigDecimal tasaEfectiva;
    
    /** The impuestosPF. */
    private List<ImposicionImpuestosPlazoFijoEntity> impuestosPF = new ArrayList<ImposicionImpuestosPlazoFijoEntity>();

    /**
	 * Gets the fecha alta real.
	 *
	 * @return the fechaAltaReal
	 */
    public String getFechaAltaReal() {
        return fechaAltaReal;
    }

    /**
	 * Sets the fecha alta real.
	 *
	 * @param fechaAltaReal
	 *            the fechaAltaReal to set
	 */
    public void setFechaAltaReal(String fechaAltaReal) {
        this.fechaAltaReal = fechaAltaReal;
    }

    /**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
    public String getPlazo() {
        return plazo;
    }

    /**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    /**
	 * Gets the cotizacion codigo ur.
	 *
	 * @return the cotizacionCodigoUr
	 */
    public BigDecimal getCotizacionCodigoUr() {
        return cotizacionCodigoUr;
    }

    /**
	 * Sets the cotizacion codigo ur.
	 *
	 * @param cotizacionCodigoUr
	 *            the cotizacionCodigoUr to set
	 */
    public void setCotizacionCodigoUr(BigDecimal cotizacionCodigoUr) {
        this.cotizacionCodigoUr = cotizacionCodigoUr;
    }

    /**
	 * Gets the saldo inic ur.
	 *
	 * @return the saldoInicUr
	 */
    public BigDecimal getSaldoInicUr() {
        return saldoInicUr;
    }

    /**
	 * Sets the saldo inic ur.
	 *
	 * @param saldoInicUr
	 *            the saldoInicUr to set
	 */
    public void setSaldoInicUr(BigDecimal saldoInicUr) {
        this.saldoInicUr = saldoInicUr;
    }

    /**
	 * Gets the intereses.
	 *
	 * @return the intereses
	 */
    public BigDecimal getIntereses() {
        return intereses;
    }

    /**
	 * Sets the intereses.
	 *
	 * @param intereses
	 *            the intereses to set
	 */
    public void setIntereses(BigDecimal intereses) {
        this.intereses = intereses;
    }

    /**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fechaVencimiento
	 */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
	 * Gets the entidad cuenta plazo.
	 *
	 * @return the entidadCuentaPlazo
	 */
    public String getEntidadCuentaPlazo() {
        return entidadCuentaPlazo;
    }

    /**
	 * Sets the entidad cuenta plazo.
	 *
	 * @param entidadCuentaPlazo
	 *            the entidadCuentaPlazo to set
	 */
    public void setEntidadCuentaPlazo(String entidadCuentaPlazo) {
        this.entidadCuentaPlazo = entidadCuentaPlazo;
    }

    /**
	 * Gets the numero cuenta plazo.
	 *
	 * @return the numeroCuentaPlazo
	 */
    public String getNumeroCuentaPlazo() {
        return numeroCuentaPlazo;
    }

    /**
	 * Sets the numero cuenta plazo.
	 *
	 * @param numeroCuentaPlazo
	 *            the numeroCuentaPlazo to set
	 */
    public void setNumeroCuentaPlazo(String numeroCuentaPlazo) {
        this.numeroCuentaPlazo = numeroCuentaPlazo;
    }

    /**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
    public String getSecuencia() {
        return secuencia;
    }

    /**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the secuencia to set
	 */
    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    /**
	 * Gets the importe certificado.
	 *
	 * @return the importeCertificado
	 */
    public BigDecimal getImporteCertificado() {
        return importeCertificado;
    }

    /**
	 * Sets the importe certificado.
	 *
	 * @param importeCertificado
	 *            the importeCertificado to set
	 */
    public void setImporteCertificado(BigDecimal importeCertificado) {
        this.importeCertificado = importeCertificado;
    }

    /**
	 * Gets the nro cuenta debito.
	 *
	 * @return the nroCuentaDebito
	 */
    public String getNroCuentaDebito() {
        return nroCuentaDebito;
    }

    /**
	 * Sets the nro cuenta debito.
	 *
	 * @param nroCuentaDebito
	 *            the nroCuentaDebito to set
	 */
    public void setNroCuentaDebito(String nroCuentaDebito) {
        this.nroCuentaDebito = nroCuentaDebito;
    }

    /**
	 * Gets the codigo plazo.
	 *
	 * @return the codigoPlazo
	 */
    public String getCodigoPlazo() {
        return codigoPlazo;
    }

    /**
	 * Sets the codigo plazo.
	 *
	 * @param codigoPlazo
	 *            the codigoPlazo to set
	 */
    public void setCodigoPlazo(String codigoPlazo) {
        this.codigoPlazo = codigoPlazo;
    }

    /**
	 * Gets the descripcion accion vencimiento.
	 *
	 * @return the descripcionAccionVencimiento
	 */
    public String getDescripcionAccionVencimiento() {
        return descripcionAccionVencimiento;
    }

    /**
	 * Sets the descripcion accion vencimiento.
	 *
	 * @param descripcionAccionVencimiento
	 *            the descripcionAccionVencimiento to set
	 */
    public void setDescripcionAccionVencimiento(String descripcionAccionVencimiento) {
        this.descripcionAccionVencimiento = descripcionAccionVencimiento;
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
	 * @param moneda
	 *            the moneda to set
	 */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
	 * Gets the tasa nominal.
	 *
	 * @return the tasaNominal
	 */
    public String getTasaNominal() {
        return tasaNominal;
    }

    /**
	 * Sets the tasa nominal.
	 *
	 * @param tasaNominal
	 *            the tasaNominal to set
	 */
    public void setTasaNominal(String tasaNominal) {
        this.tasaNominal = tasaNominal;
    }

    /**
	 * Gets the tasa efectiva.
	 *
	 * @return the tasaEfectiva
	 */
    public BigDecimal getTasaEfectiva() {
        return tasaEfectiva;
    }

    /**
	 * Sets the tasa efectiva.
	 *
	 * @param tasaEfectiva
	 *            the tasaEfectiva to set
	 */
    public void setTasaEfectiva(BigDecimal tasaEfectiva) {
        this.tasaEfectiva = tasaEfectiva;
    }

    /**
	 * Gets the impuestos PF.
	 *
	 * @return the impuestosPF
	 */
    public List<ImposicionImpuestosPlazoFijoEntity> getImpuestosPF() {
        return impuestosPF;
    }

    /**
	 * Sets the impuestos PF.
	 *
	 * @param impuestosPF
	 *            the impuestosPF to set
	 */
    public void setImpuestosPF(List<ImposicionImpuestosPlazoFijoEntity> impuestosPF) {
        this.impuestosPF = impuestosPF;
    }

    
    


}
