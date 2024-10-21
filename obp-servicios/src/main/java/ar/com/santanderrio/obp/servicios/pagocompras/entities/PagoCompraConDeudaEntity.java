/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class PagoCompraConDeudaEntity.
 *
 * @author florencia.n.martinez
 */
@Record
public class PagoCompraConDeudaEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The header trama. */
    @Field
    private String headerTrama;

    /** The codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;

    /** The nro comprobante pago. */
    @Field
    private String nroComprobantePago;

    /** The nombre empresa. */
    @Field
    private String nombreEmpresa;

    /** The id cliente terceros. */
    @Field
    private String idClienteTerceros;

    /** The cuit cliente terceros. */
    @Field
    private String cuitClienteTerceros;

    /** The indicador excepcion comision. */
    @Field
    private String indicadorExcepcionComision;

    /** The indicador cliente propio. */
    @Field
    private String indicadorClientePropio;

    /** The id banco corresponsal. */
    @Field
    private String idBancoCorresponsal;

    /** The cotizacion dolar. */
    @Field
    private String cotizacionDolar;

    /** The importe total pago. */
    @Field
    private String importeTotalPago;

    /** The condicion iva. */
    @Field
    private String condicionIva;

    /** The tipo ingresos brutos. */
    @Field
    private String tipoIngresosBrutos;

    /** The imp retencion ing brutos. */
    @Field
    private String impRetencionIngBrutos;

    /** The importe comision terceros. */
    @Field
    private String importeComisionTerceros;

    /** The importe iva comision. */
    @Field
    private String importeIvaComision;

    /** The importe iva adicional comision. */
    @Field
    private String importeIvaAdicionalComision;

    /** The forma pago comision. */
    @Field
    private String formaPagoComision;

    /** The codigo moneda. */
    @Field
    private String codigoMoneda;

    /** The ide cta terceros. */
    @Field
    private String ideCtaTerceros;

    /** The importe comision pago. */
    @Field
    private String importeComisionPago;

    /** The cant instrum pago. */
    @Field
    private Integer cantInstrumPago;

    /** The instrumentos pago. */
    @Segment(minOccurs = 15, maxOccurs = 15)
    private List<InstrumentoPagoEntity> instrumentosPago = new ArrayList<InstrumentoPagoEntity>();

    /** The cantidad comprobantes deuda. */
    @Field
    private Integer cantComprobDeuda;

    /** The comprobantes deuda. */
    @Segment(occursRef = "cantComprobDeuda")
    private List<ComprobanteDeudaEntity> comprobantesDeuda = new ArrayList<ComprobanteDeudaEntity>();

    /** The tiene error. */
    private Boolean tieneError = Boolean.FALSE;

    /**
     * Gets the header trama.
     *
     * @return the header trama
     */
    public String getHeaderTrama() {
        return headerTrama;
    }

    /**
     * Sets the header trama.
     *
     * @param headerTrama
     *            the new header trama
     */
    public void setHeaderTrama(String headerTrama) {
        this.headerTrama = headerTrama;
    }

    /**
     * Gets the codigo retorno extendido.
     *
     * @return the codigo retorno extendido
     */
    public String getCodigoRetornoExtendido() {
        return codigoRetornoExtendido;
    }

    /**
     * Sets the codigo retorno extendido.
     *
     * @param codigoRetornoExtendido
     *            the new codigo retorno extendido
     */
    public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
        this.codigoRetornoExtendido = codigoRetornoExtendido;
    }

    /**
     * Gets the nro comprobante pago.
     *
     * @return the nro comprobante pago
     */
    public String getNroComprobantePago() {
        return nroComprobantePago;
    }

    /**
     * Sets the nro comprobante pago.
     *
     * @param nroComprobantePago
     *            the new nro comprobante pago
     */
    public void setNroComprobantePago(String nroComprobantePago) {
        this.nroComprobantePago = nroComprobantePago;
    }

    /**
     * Gets the nombre empresa.
     *
     * @return the nombre empresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Sets the nombre empresa.
     *
     * @param nombreEmpresa
     *            the new nombre empresa
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * Gets the id cliente terceros.
     *
     * @return the id cliente terceros
     */
    public String getIdClienteTerceros() {
        return idClienteTerceros;
    }

    /**
     * Sets the id cliente terceros.
     *
     * @param idClienteTerceros
     *            the new id cliente terceros
     */
    public void setIdClienteTerceros(String idClienteTerceros) {
        this.idClienteTerceros = idClienteTerceros;
    }

    /**
     * Gets the cuit cliente terceros.
     *
     * @return the cuit cliente terceros
     */
    public String getCuitClienteTerceros() {
        return cuitClienteTerceros;
    }

    /**
     * Sets the cuit cliente terceros.
     *
     * @param cuitClienteTerceros
     *            the new cuit cliente terceros
     */
    public void setCuitClienteTerceros(String cuitClienteTerceros) {
        this.cuitClienteTerceros = cuitClienteTerceros;
    }

    /**
     * Gets the indicador excepcion comision.
     *
     * @return the indicador excepcion comision
     */
    public String getIndicadorExcepcionComision() {
        return indicadorExcepcionComision;
    }

    /**
     * Sets the indicador excepcion comision.
     *
     * @param indicadorExcepcionComision
     *            the new indicador excepcion comision
     */
    public void setIndicadorExcepcionComision(String indicadorExcepcionComision) {
        this.indicadorExcepcionComision = indicadorExcepcionComision;
    }

    /**
     * Gets the indicador cliente propio.
     *
     * @return the indicador cliente propio
     */
    public String getIndicadorClientePropio() {
        return indicadorClientePropio;
    }

    /**
     * Sets the indicador cliente propio.
     *
     * @param indicadorClientePropio
     *            the new indicador cliente propio
     */
    public void setIndicadorClientePropio(String indicadorClientePropio) {
        this.indicadorClientePropio = indicadorClientePropio;
    }

    /**
     * Gets the id banco corresponsal.
     *
     * @return the id banco corresponsal
     */
    public String getIdBancoCorresponsal() {
        return idBancoCorresponsal;
    }

    /**
     * Sets the id banco corresponsal.
     *
     * @param idBancoCorresponsal
     *            the new id banco corresponsal
     */
    public void setIdBancoCorresponsal(String idBancoCorresponsal) {
        this.idBancoCorresponsal = idBancoCorresponsal;
    }

    /**
     * Gets the cotizacion dolar.
     *
     * @return the cotizacion dolar
     */
    public String getCotizacionDolar() {
        return cotizacionDolar;
    }

    /**
     * Sets the cotizacion dolar.
     *
     * @param cotizacionDolar
     *            the new cotizacion dolar
     */
    public void setCotizacionDolar(String cotizacionDolar) {
        this.cotizacionDolar = cotizacionDolar;
    }

    /**
     * Gets the importe total pago.
     *
     * @return the importe total pago
     */
    public String getImporteTotalPago() {
        return importeTotalPago;
    }

    /**
     * Sets the importe total pago.
     *
     * @param importeTotalPago
     *            the new importe total pago
     */
    public void setImporteTotalPago(String importeTotalPago) {
        this.importeTotalPago = importeTotalPago;
    }

    /**
     * Gets the condicion iva.
     *
     * @return the condicion iva
     */
    public String getCondicionIva() {
        return condicionIva;
    }

    /**
     * Sets the condicion iva.
     *
     * @param condicionIva
     *            the new condicion iva
     */
    public void setCondicionIva(String condicionIva) {
        this.condicionIva = condicionIva;
    }

    /**
     * Gets the tipo ingresos brutos.
     *
     * @return the tipo ingresos brutos
     */
    public String getTipoIngresosBrutos() {
        return tipoIngresosBrutos;
    }

    /**
     * Sets the tipo ingresos brutos.
     *
     * @param tipoIngresosBrutos
     *            the new tipo ingresos brutos
     */
    public void setTipoIngresosBrutos(String tipoIngresosBrutos) {
        this.tipoIngresosBrutos = tipoIngresosBrutos;
    }

    /**
     * Gets the imp retencion ing brutos.
     *
     * @return the imp retencion ing brutos
     */
    public String getImpRetencionIngBrutos() {
        return impRetencionIngBrutos;
    }

    /**
     * Sets the imp retencion ing brutos.
     *
     * @param impRetencionIngBrutos
     *            the new imp retencion ing brutos
     */
    public void setImpRetencionIngBrutos(String impRetencionIngBrutos) {
        this.impRetencionIngBrutos = impRetencionIngBrutos;
    }

    /**
     * Gets the importe comision terceros.
     *
     * @return the importe comision terceros
     */
    public String getImporteComisionTerceros() {
        return importeComisionTerceros;
    }

    /**
     * Sets the importe comision terceros.
     *
     * @param importeComisionTerceros
     *            the new importe comision terceros
     */
    public void setImporteComisionTerceros(String importeComisionTerceros) {
        this.importeComisionTerceros = importeComisionTerceros;
    }

    /**
     * Gets the importe iva comision.
     *
     * @return the importe iva comision
     */
    public String getImporteIvaComision() {
        return importeIvaComision;
    }

    /**
     * Sets the importe iva comision.
     *
     * @param importeIvaComision
     *            the new importe iva comision
     */
    public void setImporteIvaComision(String importeIvaComision) {
        this.importeIvaComision = importeIvaComision;
    }

    /**
     * Gets the importe iva adicional comision.
     *
     * @return the importe iva adicional comision
     */
    public String getImporteIvaAdicionalComision() {
        return importeIvaAdicionalComision;
    }

    /**
     * Sets the importe iva adicional comision.
     *
     * @param importeIvaAdicionalComision
     *            the new importe iva adicional comision
     */
    public void setImporteIvaAdicionalComision(String importeIvaAdicionalComision) {
        this.importeIvaAdicionalComision = importeIvaAdicionalComision;
    }

    /**
     * Gets the forma pago comision.
     *
     * @return the forma pago comision
     */
    public String getFormaPagoComision() {
        return formaPagoComision;
    }

    /**
     * Sets the forma pago comision.
     *
     * @param formaPagoComision
     *            the new forma pago comision
     */
    public void setFormaPagoComision(String formaPagoComision) {
        this.formaPagoComision = formaPagoComision;
    }

    /**
     * Gets the codigo moneda.
     *
     * @return the codigo moneda
     */
    public String getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * Sets the codigo moneda.
     *
     * @param codigoMoneda
     *            the new codigo moneda
     */
    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    /**
     * Gets the ide cta terceros.
     *
     * @return the ide cta terceros
     */
    public String getIdeCtaTerceros() {
        return ideCtaTerceros;
    }

    /**
     * Sets the ide cta terceros.
     *
     * @param ideCtaTerceros
     *            the new ide cta terceros
     */
    public void setIdeCtaTerceros(String ideCtaTerceros) {
        this.ideCtaTerceros = ideCtaTerceros;
    }

    /**
     * Gets the importe comision pago.
     *
     * @return the importe comision pago
     */
    public String getImporteComisionPago() {
        return importeComisionPago;
    }

    /**
     * Sets the importe comision pago.
     *
     * @param importeComisionPago
     *            the new importe comision pago
     */
    public void setImporteComisionPago(String importeComisionPago) {
        this.importeComisionPago = importeComisionPago;
    }

    /**
     * Gets the cant instrum pago.
     *
     * @return the cant instrum pago
     */
    public Integer getCantInstrumPago() {
        return cantInstrumPago;
    }

    /**
     * Sets the cant instrum pago.
     *
     * @param cantInstrumPago
     *            the new cant instrum pago
     */
    public void setCantInstrumPago(Integer cantInstrumPago) {
        this.cantInstrumPago = cantInstrumPago;
    }

    /**
     * Gets the instrumentos pago.
     *
     * @return the instrumentos pago
     */
    public List<InstrumentoPagoEntity> getInstrumentosPago() {
        return instrumentosPago;
    }

    /**
     * Sets the instrumentos pago.
     *
     * @param instrumentosPago
     *            the new instrumentos pago
     */
    public void setInstrumentosPago(List<InstrumentoPagoEntity> instrumentosPago) {
        this.instrumentosPago = instrumentosPago;
    }

    /**
     * Gets the cant comprob deuda.
     *
     * @return the cant comprob deuda
     */
    public Integer getCantComprobDeuda() {
        return cantComprobDeuda;
    }

    /**
     * Sets the cant comprob deuda.
     *
     * @param cantComprobDeuda
     *            the new cant comprob deuda
     */
    public void setCantComprobDeuda(Integer cantComprobDeuda) {
        this.cantComprobDeuda = cantComprobDeuda;
    }

    /**
     * Gets the comprobantes deuda.
     *
     * @return the comprobantes deuda
     */
    public List<ComprobanteDeudaEntity> getComprobantesDeuda() {
        return comprobantesDeuda;
    }

    /**
     * Sets the comprobantes deuda.
     *
     * @param comprobantesDeuda
     *            the new comprobantes deuda
     */
    public void setComprobantesDeuda(List<ComprobanteDeudaEntity> comprobantesDeuda) {
        this.comprobantesDeuda = comprobantesDeuda;
    }

    /**
     * Gets the tiene error.
     *
     * @return the tiene error
     */
    public Boolean getTieneError() {
        return tieneError;
    }

    /**
     * Sets the tiene error.
     *
     * @param tieneError
     *            the new tiene error
     */
    public void setTieneError(Boolean tieneError) {
        this.tieneError = tieneError;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(nroComprobantePago);
        hcb.append(nombreEmpresa);
        hcb.append(idClienteTerceros);
        hcb.append(cuitClienteTerceros);
        hcb.append(cotizacionDolar);
        hcb.append(idBancoCorresponsal);
        hcb.append(nombreEmpresa);
        hcb.append(importeTotalPago);
        hcb.append(formaPagoComision);
        return hcb.toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj
     *            the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PagoCompraConDeudaEntity other = (PagoCompraConDeudaEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(nroComprobantePago, other.getNroComprobantePago());
        eb.append(nombreEmpresa, other.getNombreEmpresa());
        eb.append(idClienteTerceros, other.getIdClienteTerceros());
        eb.append(cuitClienteTerceros, other.getIdClienteTerceros());
        eb.append(cotizacionDolar, other.getCotizacionDolar());
        eb.append(idBancoCorresponsal, other.getIdBancoCorresponsal());
        eb.append(nombreEmpresa, other.getNombreEmpresa());
        eb.append(importeTotalPago, other.getImporteTotalPago());
        eb.append(formaPagoComision, other.getFormaPagoComision());
        return eb.isEquals();
    }

    /**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("headerTrama", headerTrama)
                .append("codigoRetornoExtendido", codigoRetornoExtendido)
                .append("nroComprobantePago", nroComprobantePago).append("nombreEmpresa", nombreEmpresa)
                .append("idClienteTerceros", idClienteTerceros).append("cuitClienteTerceros", cuitClienteTerceros)
                .append("indicadorComisionExcepcion", indicadorExcepcionComision)
                .append("indicadorClientePropio", indicadorClientePropio)
                .append("idBancoCorresponsal", idBancoCorresponsal).append("cotizacionDolar", cotizacionDolar)
                .append("importeTotalPago", importeTotalPago).append("condicionIva", condicionIva)
                .append("tipoIngresosBrutos", tipoIngresosBrutos).append("formaPagocomision", formaPagoComision)
                .append("codigoMoneda", codigoMoneda).append("ideCtaTerceros", ideCtaTerceros)
                .append("cantInstrumPago", cantInstrumPago).append("instrumentosPago", instrumentosPago)
                .append("cantComprobDeuda", cantComprobDeuda).append("comprobantesDeuda", comprobantesDeuda)
                .append("tieneError", tieneError).toString();
    }

}
