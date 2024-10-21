package ar.com.santanderrio.obp.servicios.pagocompras.entities;

import org.apache.commons.lang3.StringUtils;

public class PagoCompraDeudaInEntity {
    /** The monto. */
    private String monto;
    
    /** The tipo comprobante deuda. */
    private String tipoComprobanteDeuda;

    /** The nro comprobante deuda. */
    private String nroComprobanteDeuda;

    /** The nro cuota deuda. */
    private String nroCuotaDeuda;

    /** The fecha vencimiento deuda. */
    private String fechaVencimientoDeuda;

    /** The importe punitorios deuda. */
    private String importePunitoriosDeuda;

    /** The importe iva pago. */
    private String importeIvaPago;

    /** The importe iva adicional pago. */
    private String importeIvaAdicionalPago;

    /** The texto 1. */
    private String texto1;

    /** The texto 2. */
    private String texto2;

    /** The texto 3. */
    private String texto3;

    /** The texto 4. */
    private String texto4;

    /** The texto 5. */
    private String texto5;
    
    public PagoCompraDeudaInEntity(DeudaPagoComprasEntity deuda, String monto) {
        this.monto = monto;
        this.tipoComprobanteDeuda = deuda.getTipoComprobanteDeuda();
        this.nroComprobanteDeuda = deuda.getNroComprobanteDeuda();
        this.nroCuotaDeuda = deuda.getNroCuotaDeuda();
        this.fechaVencimientoDeuda = deuda.getFechaVencimientoDeuda();
        this.importePunitoriosDeuda = deuda.getImportePunitoriosDeuda();
        this.importeIvaPago = deuda.getImporteIvaInteresesDeuda();
        this.importeIvaAdicionalPago = deuda.getImporteIvaAdicionalInteresesDeuda();
        this.texto1 = StringUtils.rightPad(StringUtils.defaultString(deuda.getTexto1()), 18);
        this.texto2 = StringUtils.rightPad(StringUtils.defaultString(deuda.getTexto2()), 15);
        this.texto3 = StringUtils.rightPad(StringUtils.defaultString(deuda.getTexto3()), 15);
        this.texto4 = StringUtils.rightPad(StringUtils.defaultString(deuda.getTexto4()), 15);
        this.texto5 = StringUtils.rightPad(StringUtils.defaultString(deuda.getTexto5()), 15);
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getTipoComprobanteDeuda() {
        return tipoComprobanteDeuda;
    }

    public void setTipoComprobanteDeuda(String tipoComprobanteDeuda) {
        this.tipoComprobanteDeuda = tipoComprobanteDeuda;
    }

    public String getNroComprobanteDeuda() {
        return nroComprobanteDeuda;
    }

    public void setNroComprobanteDeuda(String nroComprobanteDeuda) {
        this.nroComprobanteDeuda = nroComprobanteDeuda;
    }

    public String getNroCuotaDeuda() {
        return nroCuotaDeuda;
    }

    public void setNroCuotaDeuda(String nroCuotaDeuda) {
        this.nroCuotaDeuda = nroCuotaDeuda;
    }

    public String getFechaVencimientoDeuda() {
        return fechaVencimientoDeuda;
    }

    public void setFechaVencimientoDeuda(String fechaVencimientoDeuda) {
        this.fechaVencimientoDeuda = fechaVencimientoDeuda;
    }

    public String getImportePunitoriosDeuda() {
        return importePunitoriosDeuda;
    }

    public void setImportePunitoriosDeuda(String importePunitoriosDeuda) {
        this.importePunitoriosDeuda = importePunitoriosDeuda;
    }

    public String getImporteIvaPago() {
        return importeIvaPago;
    }

    public void setImporteIvaPago(String importeIvaPago) {
        this.importeIvaPago = importeIvaPago;
    }

    public String getImporteIvaAdicionalPago() {
        return importeIvaAdicionalPago;
    }

    public void setImporteIvaAdicionalPago(String importeIvaAdicionalPago) {
        this.importeIvaAdicionalPago = importeIvaAdicionalPago;
    }

    public String getTexto1() {
        return texto1;
    }

    public void setTexto1(String texto1) {
        this.texto1 = texto1;
    }

    public String getTexto2() {
        return texto2;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }

    public String getTexto3() {
        return texto3;
    }

    public void setTexto3(String texto3) {
        this.texto3 = texto3;
    }

    public String getTexto4() {
        return texto4;
    }

    public void setTexto4(String texto4) {
        this.texto4 = texto4;
    }

    public String getTexto5() {
        return texto5;
    }

    public void setTexto5(String texto5) {
        this.texto5 = texto5;
    }
}
