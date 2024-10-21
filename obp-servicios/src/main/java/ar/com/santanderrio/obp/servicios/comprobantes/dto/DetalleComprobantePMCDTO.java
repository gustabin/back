/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class DetalleComprobantePMCDTO.
 */
public class DetalleComprobantePMCDTO extends DetalleComprobantePagoMisCuentasDTO {

    /** The factura. */
    private String factura;

    /**
     * Gets the factura.
     *
     * @return the factura
     */
    public String getFactura() {
        return factura;
    }

    /**
     * Sets the factura.
     *
     * @param factura
     *            the factura to set
     */
    public void setFactura(String factura) {
        this.factura = factura;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
     * DetalleComprobantePagoMisCuentasDTO#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hb = new HashCodeBuilder();
        hb.append(factura);
        return hb.toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
     * DetalleComprobantePagoMisCuentasDTO#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }

        if (a == null) {
            return false;
        }

        if (getClass() != a.getClass()) {
            return false;
        }
        DetalleComprobantePMCDTO detalle = new DetalleComprobantePMCDTO();
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(factura, detalle.getFactura());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
     * DetalleComprobantePagoMisCuentasDTO#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DetalleComprobantePMCDTO [factura=");
        sb.append(factura);
        sb.append("]");
        return sb.toString();
    }

}
