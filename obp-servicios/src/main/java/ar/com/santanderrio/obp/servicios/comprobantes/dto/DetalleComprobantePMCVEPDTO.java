/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

/**
 * The Class DetalleComprobantePMCVEPDTO.
 */
public class DetalleComprobantePMCVEPDTO extends DetalleComprobantePagoMisCuentasDTO {

    /** The numero VEP. */
    private String nroVEP;

    /** The periodo. */
    private String periodo;

    /** The anticipo/Cuota. */
    private String anticipo;

    /**
     * Gets the nro VEP.
     *
     * @return the nro VEP
     */
    public String getNroVEP() {
        return nroVEP;
    }

    /**
     * Sets the nro VEP.
     *
     * @param nroVEP
     *            the new nro VEP
     */
    public void setNroVEP(String nroVEP) {
        this.nroVEP = nroVEP;
    }

    /**
     * Gets the periodo.
     *
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * Sets the periodo.
     *
     * @param periodo
     *            the new periodo
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * Gets the anticipo.
     *
     * @return the anticipo
     */
    public String getAnticipo() {
        return anticipo;
    }

    /**
     * Sets the anticipo.
     *
     * @param anticipo
     *            the new anticipo
     */
    public void setAnticipo(String anticipo) {
        this.anticipo = anticipo;
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
        sb.append("DetalleComprobantePMCVEPDTO [nroVEP=");
        sb.append(nroVEP);
        sb.append(", periodo= ");
        sb.append(periodo);
        sb.append(", anticipo= ");
        sb.append(anticipo);
        sb.append("]");
        return sb.toString();
    }

}
