/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class DeudasPagoComprasEntity.
 */
@Record
public class DeudasPagoComprasEntity {

    /** The header trama. */
    @Field
    private String headerTrama;

    /** The codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;

    /** The id empresa. */
    @Field
    private String idEmpresa;

    /** The nombre empresa. */
    @Field
    private String nombreEmpresa;

    /** The codigo producto acuerdo empresa. */
    @Field
    private String codigoProductoAcuerdoEmpresa;

    /** The nro instancia acuerdo empresa. */
    @Field
    private String nroInstanciaAcuerdoEmpresa;

    /** The tipode comision acuerdo empresa. */
    @Field
    private String tipodeComisionAcuerdoEmpresa;

    /** The id cliente terceros. */
    @Field
    private String idClienteTerceros;

    /** The nombre cliente terceros. */
    @Field
    private String nombreClienteTerceros;

    /** The cuit cliente terceros. */
    @Field
    private String cuitClienteTerceros;

    /** The condicion iva. */
    @Field
    private String condicionIva;

    /** The tipo ingresos brutos. */
    @Field
    private String tipoIngresosBrutos;

    /** The indicador excepcion comision. */
    @Field
    private String indicadorExcepcionComision;

    /** The indicador cliente propio. */
    @Field
    private String indicadorClientePropio;

    /** The indicador registros adicionales. */
    @Field
    private String indicadorRegistrosAdicionales;

    /** The cantidad comprobante deuda. */
    @Field
    private Integer cantidadComprobanteDeuda;

    /** The lista deudas. */
    @Segment(occursRef = "cantidadComprobanteDeuda")
    private List<DeudaPagoComprasEntity> listaDeudas = new ArrayList<DeudaPagoComprasEntity>();

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
     * Gets the id empresa.
     *
     * @return the id empresa
     */
    public String getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Sets the id empresa.
     *
     * @param idEmpresa
     *            the new id empresa
     */
    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
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
     * Gets the codigo producto acuerdo empresa.
     *
     * @return the codigo producto acuerdo empresa
     */
    public String getCodigoProductoAcuerdoEmpresa() {
        return codigoProductoAcuerdoEmpresa;
    }

    /**
     * Sets the codigo producto acuerdo empresa.
     *
     * @param codigoProductoAcuerdoEmpresa
     *            the new codigo producto acuerdo empresa
     */
    public void setCodigoProductoAcuerdoEmpresa(String codigoProductoAcuerdoEmpresa) {
        this.codigoProductoAcuerdoEmpresa = codigoProductoAcuerdoEmpresa;
    }

    /**
     * Gets the nro instancia acuerdo empresa.
     *
     * @return the nro instancia acuerdo empresa
     */
    public String getNroInstanciaAcuerdoEmpresa() {
        return nroInstanciaAcuerdoEmpresa;
    }

    /**
     * Sets the nro instancia acuerdo empresa.
     *
     * @param nroInstanciaAcuerdoEmpresa
     *            the new nro instancia acuerdo empresa
     */
    public void setNroInstanciaAcuerdoEmpresa(String nroInstanciaAcuerdoEmpresa) {
        this.nroInstanciaAcuerdoEmpresa = nroInstanciaAcuerdoEmpresa;
    }

    /**
     * Gets the tipode comision acuerdo empresa.
     *
     * @return the tipode comision acuerdo empresa
     */
    public String getTipodeComisionAcuerdoEmpresa() {
        return tipodeComisionAcuerdoEmpresa;
    }

    /**
     * Sets the tipode comision acuerdo empresa.
     *
     * @param tipodeComisionAcuerdoEmpresa
     *            the new tipode comision acuerdo empresa
     */
    public void setTipodeComisionAcuerdoEmpresa(String tipodeComisionAcuerdoEmpresa) {
        this.tipodeComisionAcuerdoEmpresa = tipodeComisionAcuerdoEmpresa;
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
     * Gets the nombre cliente terceros.
     *
     * @return the nombre cliente terceros
     */
    public String getNombreClienteTerceros() {
        return nombreClienteTerceros;
    }

    /**
     * Sets the nombre cliente terceros.
     *
     * @param nombreClienteTerceros
     *            the new nombre cliente terceros
     */
    public void setNombreClienteTerceros(String nombreClienteTerceros) {
        this.nombreClienteTerceros = nombreClienteTerceros;
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
     * Gets the indicador registros adicionales.
     *
     * @return the indicador registros adicionales
     */
    public String getIndicadorRegistrosAdicionales() {
        return indicadorRegistrosAdicionales;
    }

    /**
     * Sets the indicador registros adicionales.
     *
     * @param indicadorRegistrosAdicionales
     *            the new indicador registros adicionales
     */
    public void setIndicadorRegistrosAdicionales(String indicadorRegistrosAdicionales) {
        this.indicadorRegistrosAdicionales = indicadorRegistrosAdicionales;
    }

    /**
     * Gets the cantidad comprobante deuda.
     *
     * @return the cantidad comprobante deuda
     */
    public Integer getCantidadComprobanteDeuda() {
        return cantidadComprobanteDeuda;
    }

    /**
     * Sets the cantidad comprobante deuda.
     *
     * @param cantidadComprobanteDeuda
     *            the new cantidad comprobante deuda
     */
    public void setCantidadComprobanteDeuda(Integer cantidadComprobanteDeuda) {
        this.cantidadComprobanteDeuda = cantidadComprobanteDeuda;
    }

    /**
     * Gets the lista deudas.
     *
     * @return the lista deudas
     */
    public List<DeudaPagoComprasEntity> getListaDeudas() {
        return listaDeudas;
    }

    /**
     * Sets the lista deudas.
     *
     * @param listaDeudas
     *            the new lista deudas
     */
    public void setListaDeudas(List<DeudaPagoComprasEntity> listaDeudas) {
        this.listaDeudas = listaDeudas;
    }

    /**
     * Obtener deuda por id.
     *
     * @param idDeuda
     *            the id deuda
     * @return the deuda pago compras entity
     */
    public DeudaPagoComprasEntity obtenerDeudaPorId(String idDeuda) {
        for (DeudaPagoComprasEntity d : this.getListaDeudas()) {
            if (StringUtils.equals(
                    StringUtils.trimToEmpty(d.getNroComprobanteDeuda()) + "-" + StringUtils.trimToEmpty(d.getNroCuotaDeuda()),
                    idDeuda)) {
                return d;
            }
        }
        return null;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(cuitClienteTerceros);
        hcb.append(idClienteTerceros);
        hcb.append(idEmpresa);
        hcb.append(nombreClienteTerceros);
        hcb.append(nombreEmpresa);
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeudasPagoComprasEntity other = (DeudasPagoComprasEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(cuitClienteTerceros, other.getCuitClienteTerceros());
        eb.append(idClienteTerceros, other.getIdClienteTerceros());
        eb.append(idEmpresa, other.getIdEmpresa());
        eb.append(nombreClienteTerceros, other.getNombreClienteTerceros());
        eb.append(nombreEmpresa, other.getNombreEmpresa());
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
        return new ToStringBuilder(this).append("codigoRetornoExtendido", codigoRetornoExtendido)
                .append("idEmpresa", idEmpresa).append("nombreEmpresa", nombreEmpresa)
                .append("codigoProductoAcuerdoEmpresa", codigoProductoAcuerdoEmpresa)
                .append("nroInstanciaAcuerdoEmpresa", nroInstanciaAcuerdoEmpresa)
                .append("tipodeComisionAcuerdoEmpresa", tipodeComisionAcuerdoEmpresa)
                .append("idClienteTerceros", idClienteTerceros).append("nombreClienteTerceros", nombreClienteTerceros)
                .append("cuitClienteTerceros", cuitClienteTerceros).append("condicionIva", condicionIva)
                .append("tipoIngresosBrutos", tipoIngresosBrutos)
                .append("indicadorExcepcionComision", indicadorExcepcionComision)
                .append("indicadorClientePropio", indicadorClientePropio)
                .append("indicadorRegistrosAdicionales", indicadorRegistrosAdicionales)
                .append("cantidadComprobanteDeuda", cantidadComprobanteDeuda).append("listaDeudas", listaDeudas)
                .toString();
    }
}
