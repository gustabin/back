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
 * The Class PagoCompraSinDeudaEntity.
 *
 * @author florencia.n.martinez
 */
@Record
public class PagoCompraSinDeudaEntity implements Serializable {

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

    /** The id banco corresponsal. */
    @Field
    private String idBancoCorresponsal;

    /** The cotizacion dolar. */
    @Field
    private String cotizacionDolar;

    /** The importe total pago. */
    @Field
    private String importeTotalPago;

    /** The cantidad instrumentos pago. */
    @Field
    private Integer cantidadInstrumentosPago;

    /** The detalle pago. */
    @Segment(occursRef = "cantidadInstrumentosPago")
    private List<DetallePagoPCEntity> detallePago = new ArrayList<DetallePagoPCEntity>();

    /** The cantidad comprobantes deuda. */
    @Field
    private Integer cantidadComprobantesDeuda;

    /** The detalle deuda. */
    @Segment(occursRef = "cantidadComprobantesDeuda")
    private List<DetalleDeudaPCEntity> detalleDeuda = new ArrayList<DetalleDeudaPCEntity>();

    /** The tiene error. */
    private Boolean tieneError = Boolean.FALSE;

    /**
     * Gets the header trama.
     *
     * @return the headerTrama
     */
    public String getHeaderTrama() {
        return headerTrama;
    }

    /**
     * Sets the header trama.
     *
     * @param headerTrama
     *            the headerTrama to set
     */
    public void setHeaderTrama(String headerTrama) {
        this.headerTrama = headerTrama;
    }

    /**
     * Gets the codigo retorno extendido.
     *
     * @return the codigoRetornoExtendido
     */
    public String getCodigoRetornoExtendido() {
        return codigoRetornoExtendido;
    }

    /**
     * Sets the codigo retorno extendido.
     *
     * @param codigoRetornoExtendido
     *            the codigoRetornoExtendido to set
     */
    public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
        this.codigoRetornoExtendido = codigoRetornoExtendido;
    }

    /**
     * Gets the nro comprobante pago.
     *
     * @return the nroComprobantePago
     */
    public String getNroComprobantePago() {
        return nroComprobantePago;
    }

    /**
     * Sets the nro comprobante pago.
     *
     * @param nroComprobantePago
     *            the nroComprobantePago to set
     */
    public void setNroComprobantePago(String nroComprobantePago) {
        this.nroComprobantePago = nroComprobantePago;
    }

    /**
     * Gets the nombre empresa.
     *
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Sets the nombre empresa.
     *
     * @param nombreEmpresa
     *            the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * Gets the id banco corresponsal.
     *
     * @return the idBancoCorresponsal
     */
    public String getIdBancoCorresponsal() {
        return idBancoCorresponsal;
    }

    /**
     * Sets the id banco corresponsal.
     *
     * @param idBancoCorresponsal
     *            the idBancoCorresponsal to set
     */
    public void setIdBancoCorresponsal(String idBancoCorresponsal) {
        this.idBancoCorresponsal = idBancoCorresponsal;
    }

    /**
     * Gets the cotizacion dolar.
     *
     * @return the cotizacionDolar
     */
    public String getCotizacionDolar() {
        return cotizacionDolar;
    }

    /**
     * Sets the cotizacion dolar.
     *
     * @param cotizacionDolar
     *            the cotizacionDolar to set
     */
    public void setCotizacionDolar(String cotizacionDolar) {
        this.cotizacionDolar = cotizacionDolar;
    }

    /**
     * Gets the importe total pago.
     *
     * @return the importeTotalPago
     */
    public String getImporteTotalPago() {
        return importeTotalPago;
    }

    /**
     * Sets the importe total pago.
     *
     * @param importeTotalPago
     *            the importeTotalPago to set
     */
    public void setImporteTotalPago(String importeTotalPago) {
        this.importeTotalPago = importeTotalPago;
    }

    /**
     * Gets the cantidad instrumentos pago.
     *
     * @return the cantidadInstrumentosPago
     */
    public Integer getCantidadInstrumentosPago() {
        return cantidadInstrumentosPago;
    }

    /**
     * Sets the cantidad instrumentos pago.
     *
     * @param cantidadInstrumentosPago
     *            the cantidadInstrumentosPago to set
     */
    public void setCantidadInstrumentosPago(Integer cantidadInstrumentosPago) {
        this.cantidadInstrumentosPago = cantidadInstrumentosPago;
    }

    /**
     * Gets the detalle pago.
     *
     * @return the detallePago
     */
    public List<DetallePagoPCEntity> getDetallePago() {
        return detallePago;
    }

    /**
     * Sets the detalle pago.
     *
     * @param detallePago
     *            the detallePago to set
     */
    public void setDetallePago(List<DetallePagoPCEntity> detallePago) {
        this.detallePago = detallePago;
    }

    /**
     * Gets the cantidad comprobantes deuda.
     *
     * @return the cantidadComprobantesDeuda
     */
    public Integer getCantidadComprobantesDeuda() {
        return cantidadComprobantesDeuda;
    }

    /**
     * Sets the cantidad comprobantes deuda.
     *
     * @param cantidadComprobantesDeuda
     *            the cantidadComprobantesDeuda to set
     */
    public void setCantidadComprobantesDeuda(Integer cantidadComprobantesDeuda) {
        this.cantidadComprobantesDeuda = cantidadComprobantesDeuda;
    }

    /**
     * Gets the detalle deuda.
     *
     * @return the detalleDeuda
     */
    public List<DetalleDeudaPCEntity> getDetalleDeuda() {
        return detalleDeuda;
    }

    /**
     * Sets the detalle deuda.
     *
     * @param detalleDeuda
     *            the detalleDeuda to set
     */
    public void setDetalleDeuda(List<DetalleDeudaPCEntity> detalleDeuda) {
        this.detalleDeuda = detalleDeuda;
    }

    /**
     * Gets the tiene error.
     *
     * @return the tieneError
     */
    public Boolean getTieneError() {
        return tieneError;
    }

    /**
     * Sets the tiene error.
     *
     * @param tieneError
     *            the tieneError to set
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
        hcb.append(cantidadComprobantesDeuda);
        hcb.append(cantidadInstrumentosPago);
        hcb.append(cotizacionDolar);
        hcb.append(idBancoCorresponsal);
        hcb.append(nombreEmpresa);
        hcb.append(importeTotalPago);
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
        PagoCompraSinDeudaEntity other = (PagoCompraSinDeudaEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(cantidadComprobantesDeuda, other.getCantidadComprobantesDeuda());
        eb.append(cantidadInstrumentosPago, other.getCantidadInstrumentosPago());
        eb.append(cotizacionDolar, other.getCotizacionDolar());
        eb.append(idBancoCorresponsal, other.getIdBancoCorresponsal());
        eb.append(nombreEmpresa, other.getNombreEmpresa());
        eb.append(importeTotalPago, other.getImporteTotalPago());
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
                .append("codigoRetornoExtendido", codigoRetornoExtendido).append("nombreEmpresa", nombreEmpresa)
                .append("cotizacionDolar", cotizacionDolar).append("cantidadInstrumentosPago", cantidadInstrumentosPago)
                .append("detallePago", detallePago).append("cantidadComprobantesDeuda", cantidadComprobantesDeuda)
                .append("detalleDeuda", detalleDeuda).append("tieneError", tieneError).toString();
    }

}
