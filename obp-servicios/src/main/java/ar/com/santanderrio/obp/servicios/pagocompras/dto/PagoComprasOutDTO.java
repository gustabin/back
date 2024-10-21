/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.dto;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.pagocompras.entities.ComprobanteDeudaEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraConDeudaEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraSinDeudaEntity;

/**
 * The Class PagoComprasOutDTO.
 *
 * @author florencia.n.martinez
 */
public class PagoComprasOutDTO {

    /** The numero boleta. */
    private String numeroBoleta;

    /** The numero orden. */
    private String numeroOrden;

    /** The comprobante. */
    private String comprobante;

    /** The cod error. */
    private String codError;

    /** The tiene error. */
    private boolean tieneError;

    private List<ComprobanteDeudaEntity> comprobantesDeuda;

    /**
     * Instantiates a new pago compras out DTO.
     */
    public PagoComprasOutDTO() {
        super();
    }

    /**
     * Instantiates a new pago compras out DTO.
     *
     * @param entity
     *            the entity
     */
    public PagoComprasOutDTO(PagoCompraSinDeudaEntity entity) {
        this.codError = entity.getCodigoRetornoExtendido();
        this.tieneError = true;
    }

    /**
     * Instantiates a new pago compras out DTO.
     *
     * @param entity
     *            the entity
     */
    public PagoComprasOutDTO(PagoCompraConDeudaEntity entity) {
        if (entity.getTieneError()) {
            this.codError = entity.getCodigoRetornoExtendido();
            this.tieneError = true;
        } else {
            this.comprobantesDeuda = entity.getComprobantesDeuda();
            this.numeroOrden = StringUtils.trim(entity.getComprobantesDeuda().get(0).getNroCprb()) + "-" + StringUtils.trim(entity.getComprobantesDeuda().get(0).getNroCuo());
            this.comprobante = entity.getNroComprobantePago();
        }
    }

    /**
     * Instantiates a new pago compras out DTO.
     *
     * @param entity
     *            the entity
     * @param pid
     *            the pid
     */
    public PagoComprasOutDTO(PagoCompraSinDeudaEntity entity, String pid) {
        this.numeroBoleta = pid;
        this.comprobante = entity.getNroComprobantePago();
    }

    /**
     * Gets the numero boleta.
     *
     * @return the numeroBoleta
     */
    public String getNumeroBoleta() {
        return numeroBoleta;
    }

    /**
     * Sets the numero boleta.
     *
     * @param numeroBoleta
     *            the numeroBoleta to set
     */
    public void setNumeroBoleta(String numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }

    /**
     * Gets the numero orden.
     *
     * @return the numeroOrden
     */
    public String getNumeroOrden() {
        return numeroOrden;
    }

    /**
     * Sets the numero orden.
     *
     * @param numeroOrden
     *            the numeroOrden to set
     */
    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    /**
     * Gets the comprobante.
     *
     * @return the comprobante
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * Sets the comprobante.
     *
     * @param comprobante
     *            the comprobante to set
     */
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * Gets the cod error.
     *
     * @return the codError
     */
    public String getCodError() {
        return codError;
    }

    /**
     * Sets the cod error.
     *
     * @param codError
     *            the codError to set
     */
    public void setCodError(String codError) {
        this.codError = codError;
    }

    /**
     * Gets the tiene error.
     *
     * @return the tieneError
     */
    public boolean getTieneError() {
        return tieneError;
    }

    /**
     * Sets the tiene error.
     *
     * @param tieneError
     *            the tieneError to set
     */
    public void setTieneError(boolean tieneError) {
        this.tieneError = tieneError;
    }


    public List<ComprobanteDeudaEntity> getComprobantesDeuda() {
        return comprobantesDeuda;
    }

    public void setComprobantesDeuda(List<ComprobanteDeudaEntity> comprobantesDeuda) {
        this.comprobantesDeuda = comprobantesDeuda;
    }
    
    public ComprobanteDeudaEntity obtenerDeudaPorId(String idDeuda) {
        for (ComprobanteDeudaEntity d : this.comprobantesDeuda) {
            if (StringUtils.equals(
                    StringUtils.trimToEmpty(d.getNroCprb()) + "-" + StringUtils.trimToEmpty(d.getNroCuo()), idDeuda)) {
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
        hcb.append(codError);
        hcb.append(comprobante);
        hcb.append(numeroBoleta);
        hcb.append(numeroOrden);
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
        PagoComprasOutDTO other = (PagoComprasOutDTO) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(codError, other.getCodError());
        eb.append(comprobante, other.getComprobante());
        eb.append(numeroBoleta, other.getNumeroBoleta());
        eb.append(numeroOrden, other.getNumeroOrden());
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
        return new ToStringBuilder(this).append("numeroBoleta", numeroBoleta).append("numeroOrden", numeroOrden)
                .append("comprobante", comprobante).append("codError", codError).append("tieneError", tieneError)
                .toString();
    }

}
