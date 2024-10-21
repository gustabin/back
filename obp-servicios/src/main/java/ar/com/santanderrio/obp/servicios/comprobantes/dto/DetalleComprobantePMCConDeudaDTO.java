/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

/**
 * The Class DetalleComprobantePMCConDeudaDTO.
 */
public class DetalleComprobantePMCConDeudaDTO extends DetalleComprobantePagoMisCuentasDTO {

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
     * DetalleComprobantePagoMisCuentasDTO#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DetalleComprobantePMCConDeudaDTO [factura=");
        sb.append(factura);
        sb.append("]");
        return sb.toString();
    }

}
